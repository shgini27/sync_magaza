/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saptaslama.controller.sync;

import com.saptaslama.controller.CompanyConstants;
import com.saptaslama.controller.DbConnection;
import com.saptaslama.controller.receiver.BnFicheReceiver;
import com.saptaslama.controller.receiver.ClCardReceiver;
import com.saptaslama.model.BnFiche;
import com.saptaslama.model.BnfLine;
import com.saptaslama.model.ClCard;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.json.simple.JSONObject;

/**
 *
 * @author Shagy
 */
public class SyncBnFiche {
    private BnFicheReceiver bnFiche;
    
    //private JSONObject jsonBnFiche;
    //private JSONObject jsonBnfLine;
    
    private DbConnection dbLocal;

    private Connection connection;
    
    private String[] columnBnFiche, columnBnfLine;
    
    private final String tableNameBnFiche = CompanyConstants.COMPANY + 
            CompanyConstants.SUBCOMPANY + "BNFICHE";
    private final String tableNameBnfLine = CompanyConstants.COMPANY + 
            CompanyConstants.SUBCOMPANY + "BNFLINE";
    
    private final String tableNameBnFicheSeq = tableNameBnFiche + "SEQ";
    private final String tableNameBnfLineSeq = tableNameBnfLine + "SEQ";
    
    private final String fileNameBnFiche = "BnFiche.json";
    private final String fileNameBnfLine = "BnfLine.json";

    public SyncBnFiche(BnFicheReceiver bnFiche) {
        this.bnFiche = bnFiche;
        
        dbLocal = new DbConnection();
    }
    
    public void init() {
        ResultSet resultSetBnFiche;
        ResultSet resultSetBnfLine;
        
        connection = dbLocal.getConnection();
        
        resultSetBnFiche = dbLocal.querySelectAll(connection, tableNameBnFiche);
        resultSetBnfLine = dbLocal.querySelectAll(connection, tableNameBnfLine);

        System.out.println("\n*********" + tableNameBnFiche + " table column names***************");
        columnBnFiche = bnFiche.getColumnNames(resultSetBnFiche);

        System.out.println("\n*********" + tableNameBnfLine + " table column names***************");
        columnBnfLine = bnFiche.getColumnNames(resultSetBnfLine);

        //here sync methods
        syncBnFiche(bnFiche.getBnFicheArrayList(bnFiche.getJsonObjectFromFile(CompanyConstants
                .FILE_PATH_RECEIVE + fileNameBnFiche)), bnFiche.getJsonObjectFromFile(CompanyConstants
                .FILE_PATH_RECEIVE + fileNameBnFiche));
        syncBnfLine(bnFiche.getBnfLineArrayList(bnFiche.getJsonObjectFromFile(CompanyConstants
                .FILE_PATH_RECEIVE + fileNameBnfLine)), bnFiche.getJsonObjectFromFile(CompanyConstants
                .FILE_PATH_RECEIVE + fileNameBnfLine));

        dbLocal.closeConnection(connection);
        dbLocal.closeResultSet(resultSetBnFiche);
        dbLocal.closeResultSet(resultSetBnfLine);
        dbLocal = null;
        bnFiche = null;
        columnBnFiche = null;
        columnBnfLine = null;
    }
    
    //method to sync and insert BNFICHE
    public void syncBnFiche(ArrayList<BnFiche> bnFicheFromCloud, JSONObject jObject) {
        for (int i = 0; i < bnFicheFromCloud.size(); i++) {
            int bnFicheLogRef = dbLocal.getPrimaryKeyInt(connection, tableNameBnFiche,
                    String.valueOf(bnFicheFromCloud.get(i).getSpecode()), "LOGICALREF", "SPECODE");

            if (bnFicheLogRef != 0) {
                System.err.println("UPDATE BNFICHE TABLE");
                System.err.println("DELETE logicalRef: " + bnFicheLogRef + "AND INSERT AGAIN!");

                dbLocal.deleteLogRef(connection, tableNameBnFiche, "LOGICALREF", bnFicheLogRef);
                dbLocal.insertCards(connection, jObject, tableNameBnFiche,
                        columnBnFiche, bnFicheLogRef, 0, 0, i);
            } else {
                System.err.println("INSERT BNFICHE TABLE");
                int newBnFicheLogRef = dbLocal.getPrimaryKeyInt(connection, tableNameBnFicheSeq,
                        "1", "LASTLREF", "ID");

                newBnFicheLogRef = newBnFicheLogRef + 1;

                dbLocal.insertCards(connection, jObject, tableNameBnFiche,
                        columnBnFiche, newBnFicheLogRef, 0, 0, i);
                //  update BNFICHESEQ tables LASTLREF value with new value
                if(dbLocal.getPrimaryKeyInt(connection, tableNameBnFiche,
                    String.valueOf(newBnFicheLogRef), "LOGICALREF", "LOGICALREF") == newBnFicheLogRef){
                    
                    dbLocal.updateKey(connection, newBnFicheLogRef, tableNameBnFicheSeq);
                }
            }
        }
    }
    
    //method to sync and insert BNFLINE
    public void syncBnfLine(ArrayList<BnfLine> bnfLineFromCloud, JSONObject jObject) {
        for (int i = 0; i < bnfLineFromCloud.size(); i++) {
            int bnfLineLogRef = dbLocal.getPrimaryKeyInt(connection, tableNameBnfLine,
                    String.valueOf(bnfLineFromCloud.get(i).getSpecode()), "LOGICALREF", "SPECODE");
            int bankLogRef = dbLocal.getPrimaryKeyInt(connection, CompanyConstants.COMPANY + "BNCARD", 
                    String.valueOf(bnfLineFromCloud.get(i).getBankRef()), "LOGICALREF", "SPECODE");
            int bnAccRef = dbLocal.getPrimaryKeyInt(connection, CompanyConstants.COMPANY + "BANKACC", 
                    String.valueOf(bnfLineFromCloud.get(i).getBnAccRef()), "LOGICALREF", "SPECODE");
            int clientLogRef = dbLocal.getPrimaryKeyInt(connection, CompanyConstants.COMPANY + "CLCARD", 
                    String.valueOf(bnfLineFromCloud.get(i).getClientRef()), "LOGICALREF", "TAXNR");
            if (clientLogRef == 0 && bnfLineFromCloud.get(i).getClientRef() != 0) {
                ClCardReceiver clCard = new ClCardReceiver();
                ArrayList<ClCard> clCardList = clCard.getClCardArrayList(clCard
                        .getJsonObjectFromFile(CompanyConstants.FILE_PATH_RECEIVE + "ClCard.json"));
                for (int a = 0; a < clCardList.size(); a++) {
                    if (clCardList.get(a).getLogicalRef() == bnfLineFromCloud.get(i).getClientRef()) {
                        clientLogRef = dbLocal.getPrimaryKeyInt(connection, CompanyConstants.COMPANY + "CLCARD",
                                String.valueOf(clCardList.get(a).getTaxNr()),
                                "LOGICALREF", "TAXNR");
                        break;
                    }
                }
            }

            if (bnfLineLogRef != 0) {
                System.err.println("UPDATE BNFLINE TABLE");
                System.err.println("DELETE logicalRef: " + bnfLineLogRef + "AND INSERT AGAIN!");
                
                dbLocal.deleteLogRef(connection, tableNameBnfLine, "LOGICALREF", bnfLineLogRef);
                dbLocal.insertBnfLine(connection, jObject, tableNameBnfLine,
                        columnBnfLine, bnfLineLogRef, bankLogRef, bnAccRef, clientLogRef, bnfLineLogRef, i);
            } else {
                System.err.println("INSERT BNFLINE TABLE");
                int newBnfLineLogRef = dbLocal.getPrimaryKeyInt(connection, tableNameBnfLineSeq,
                        "1", "LASTLREF", "ID");

                newBnfLineLogRef = newBnfLineLogRef + 1;
                dbLocal.insertBnfLine(connection, jObject, tableNameBnfLine,
                        columnBnfLine, newBnfLineLogRef, bankLogRef, bnAccRef, clientLogRef, newBnfLineLogRef, i);
                //  update BNFLINESEQ tables LASTLREF value with new value
                if(dbLocal.getPrimaryKeyInt(connection, tableNameBnfLine,
                    String.valueOf(newBnfLineLogRef), "LOGICALREF", "LOGICALREF") == newBnfLineLogRef){
                    
                    dbLocal.updateKey(connection, newBnfLineLogRef, tableNameBnfLineSeq);
                }
            }
        }
    }
}
