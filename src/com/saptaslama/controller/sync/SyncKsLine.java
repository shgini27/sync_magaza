/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saptaslama.controller.sync;

import com.saptaslama.controller.CompanyConstants;
import com.saptaslama.controller.DbConnection;
import com.saptaslama.controller.receiver.ClCardReceiver;
import com.saptaslama.controller.receiver.KsLinesReceiver;
import com.saptaslama.model.ClCard;
import com.saptaslama.model.KsLines;
import com.saptaslama.model.PayTrans;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.json.simple.JSONObject;

/**
 *
 * @author Shagy
 */
public class SyncKsLine {

    private KsLinesReceiver ksLine;

    private DbConnection dbLocal;

    private Connection connection;

    private String[] columnKsLine, columnPayTrans;

    private final String tableNameKsLine = CompanyConstants.COMPANY
            + CompanyConstants.SUBCOMPANY + "KSLINES";
    private final String tableNamePayTrans = CompanyConstants.COMPANY
            + CompanyConstants.SUBCOMPANY + "PAYTRANS";

    private final String tableNameKsLineSeq = tableNameKsLine + "SEQ";
    private final String tableNamePayTransSeq = tableNamePayTrans + "SEQ";

    private final String fileNameBnfLine = "KsLines.json";
    private final String fileNamePayTrans = "PayTrans.json";

    public SyncKsLine(KsLinesReceiver ksLine) {

        this.ksLine = ksLine;

        dbLocal = new DbConnection();
    }

    public void init() {
        ResultSet resultSetKsLine;
        ResultSet resultSetPayTrans;

        connection = dbLocal.getConnection();

        resultSetKsLine = dbLocal.querySelectAll(connection, tableNameKsLine);
        resultSetPayTrans = dbLocal.querySelectAll(connection, tableNamePayTrans);

        System.out.println("\n*********" + tableNameKsLine + " table column names***************");
        columnKsLine = ksLine.getColumnNames(resultSetKsLine);

        System.out.println("\n*********" + tableNamePayTrans + " table column names***************");
        columnPayTrans = ksLine.getColumnNames(resultSetPayTrans);

        //here sync methods
        syncKsLine(ksLine.getKsLinesArrayList(ksLine.getJsonObjectFromFile(CompanyConstants.FILE_PATH_RECEIVE + fileNameBnfLine)), ksLine.getJsonObjectFromFile(CompanyConstants.FILE_PATH_RECEIVE + fileNameBnfLine));
        syncPayTrans(ksLine.getPayTransArrayList(ksLine.getJsonObjectFromFile(CompanyConstants.FILE_PATH_RECEIVE + fileNamePayTrans)), ksLine.getJsonObjectFromFile(CompanyConstants.FILE_PATH_RECEIVE + fileNamePayTrans));

        dbLocal.closeConnection(connection);
        dbLocal.closeResultSet(resultSetKsLine);
        dbLocal.closeResultSet(resultSetPayTrans);
        dbLocal = null;
        ksLine = null;
        columnKsLine = null;
        columnPayTrans = null;
    }

    private void syncKsLine(ArrayList<KsLines> ksLineListCloud, JSONObject jObject) {
        for (int i = 0; i < ksLineListCloud.size(); i++) {
            int ksLineLogRef = dbLocal.getPrimaryKeyInt(connection, tableNameKsLine,
                    String.valueOf(ksLineListCloud.get(i).getSpecode()), "LOGICALREF", "SPECODE");
            int ksCardLogRef = dbLocal.getPrimaryKeyInt(connection, CompanyConstants.COMPANY + "KSCARD",
                    String.valueOf(ksLineListCloud.get(i).getCardRef()), "LOGICALREF", "SPECODE");
            int vksCardLogRef = dbLocal.getPrimaryKeyInt(connection, CompanyConstants.COMPANY + "KSCARD",
                    String.valueOf(ksLineListCloud.get(i).getvCardRef()), "LOGICALREF", "SPECODE");

            int transRef;
            switch (ksLineListCloud.get(i).getTrCode()) {
                case 11://cari Hesap Tahsilat
                    transRef = dbLocal.getPrimaryKeyInt(connection, CompanyConstants.COMPANY + CompanyConstants.SUBCOMPANY + "CLFLINE",
                            String.valueOf(ksLineListCloud.get(i).getTransRef()), "LOGICALREF", "SPECODE");
                    System.err.println("ksLines transRef: " + transRef);
                    break;
                case 12://cari Hesap Odeme
                    transRef = dbLocal.getPrimaryKeyInt(connection, CompanyConstants.COMPANY
                            + CompanyConstants.SUBCOMPANY + "CLFLINE",
                            String.valueOf(ksLineListCloud.get(i).getTransRef()), "LOGICALREF", "SPECODE");
                    System.err.println("ksLines transRef: " + transRef);
                    break;
                case 21://bankaya yatirilan
                    transRef = dbLocal.getPrimaryKeyInt(connection, CompanyConstants.COMPANY
                            + CompanyConstants.SUBCOMPANY + "BNFLINE", String.valueOf(ksLineListCloud.get(i).getTransRef()),
                            "LOGICALREF", "SPECODE");
                    System.err.println("ksLines transRef: " + transRef);
                    break;
                case 22://bankadan cekilen
                    transRef = dbLocal.getPrimaryKeyInt(connection, CompanyConstants.COMPANY
                            + CompanyConstants.SUBCOMPANY + "BNFLINE",
                            String.valueOf(ksLineListCloud.get(i).getTransRef()), "LOGICALREF", "SPECODE");
                    System.err.println("ksLines transRef: " + transRef);
                    break;
                case 31://mal alim faturasi
                    transRef = dbLocal.getPrimaryKeyInt(connection, CompanyConstants.COMPANY
                            + CompanyConstants.SUBCOMPANY + "INVOICE",
                            String.valueOf(ksLineListCloud.get(i).getTransRef()), "LOGICALREF", "TRACKNR");
                    System.err.println("ksLines transRef: " + transRef);
                    break;
                case 32://perakende satis faturasi
                    transRef = dbLocal.getPrimaryKeyInt(connection, CompanyConstants.COMPANY
                            + CompanyConstants.SUBCOMPANY + "INVOICE",
                            String.valueOf(ksLineListCloud.get(i).getTransRef()), "LOGICALREF", "TRACKNR");
                    System.err.println("ksLines transRef: " + transRef);
                    break;
                case 33://toptan satis iade faturasi
                    transRef = dbLocal.getPrimaryKeyInt(connection, CompanyConstants.COMPANY
                            + CompanyConstants.SUBCOMPANY + "INVOICE",
                            String.valueOf(ksLineListCloud.get(i).getTransRef()), "LOGICALREF", "TRACKNR");
                    System.err.println("ksLines transRef: " + transRef);
                    break;
                case 34://alinan hizmet faturasi
                    transRef = dbLocal.getPrimaryKeyInt(connection, CompanyConstants.COMPANY
                            + CompanyConstants.SUBCOMPANY + "INVOICE",
                            String.valueOf(ksLineListCloud.get(i).getTransRef()), "LOGICALREF", "TRACKNR");
                    System.err.println("ksLines transRef: " + transRef);
                    break;
                case 35://alim iade faturasi
                    transRef = dbLocal.getPrimaryKeyInt(connection, CompanyConstants.COMPANY
                            + CompanyConstants.SUBCOMPANY + "INVOICE",
                            String.valueOf(ksLineListCloud.get(i).getTransRef()), "LOGICALREF", "TRACKNR");
                    System.err.println("ksLines transRef: " + transRef);
                    break;
                case 36://perakende satis faturasi
                    transRef = dbLocal.getPrimaryKeyInt(connection, CompanyConstants.COMPANY
                            + CompanyConstants.SUBCOMPANY + "INVOICE",
                            String.valueOf(ksLineListCloud.get(i).getTransRef()), "LOGICALREF", "TRACKNR");
                    System.err.println("ksLines transRef: " + transRef);
                    break;
                case 37://toptan satis faturasi
                    transRef = dbLocal.getPrimaryKeyInt(connection, CompanyConstants.COMPANY
                            + CompanyConstants.SUBCOMPANY + "INVOICE",
                            String.valueOf(ksLineListCloud.get(i).getTransRef()), "LOGICALREF", "TRACKNR");
                    System.err.println("ksLines transRef: " + transRef);
                    break;
                case 38://verilen hizmet faturasi
                    transRef = dbLocal.getPrimaryKeyInt(connection, CompanyConstants.COMPANY
                            + CompanyConstants.SUBCOMPANY + "INVOICE",
                            String.valueOf(ksLineListCloud.get(i).getTransRef()), "LOGICALREF", "TRACKNR");
                    System.err.println("ksLines transRef: " + transRef);
                    break;
                case 73://KASA Virman(Alacak)
                    transRef = 0;
                    System.err.println("ksLines transRef: " + transRef);
                    break;
                case 74://KASA Virman(Borc)
                    transRef = 0;
                    System.err.println("ksLines transRef: " + transRef);
                    break;
                default:
                    transRef = 0;
                    System.err.println("ksLines transRef: " + transRef);
                    break;
            }

            if (ksLineLogRef != 0) {
                System.err.println("UPDATE KSLINES TABLE");
                System.err.println("DELETE logicalRef: " + ksLineLogRef + "AND INSERT AGAIN!");

                dbLocal.deleteLogRef(connection, tableNameKsLine, "LOGICALREF", ksLineLogRef);

                boolean flg;
                try {
                    dbLocal.insertKsLine(connection, jObject, tableNameKsLine,
                            columnKsLine, ksLineLogRef, ksCardLogRef, vksCardLogRef, transRef, i);
                    flg = true;
                } catch (Exception e) {
                    flg = false;
                }

                if (flg) {
                    switch (ksLineListCloud.get(i).getTrCode()) {
                        case 11://cari Hesap Tahsilat
                            dbLocal.updateSource(connection, CompanyConstants.COMPANY
                                    + CompanyConstants.SUBCOMPANY + "CLFLINE", "SOURCEFREF",
                                    ksLineLogRef, transRef);
                            System.err.println("CLFLINE is UPDATED");
                            break;
                        case 12://cari Hesap Odeme
                            dbLocal.updateSource(connection, CompanyConstants.COMPANY
                                    + CompanyConstants.SUBCOMPANY + "CLFLINE", "SOURCEFREF",
                                    ksLineLogRef, transRef);
                            System.err.println("CLFLINE is UPDATED");
                            break;
                        case 21://Bankaya Yatirilan
                            dbLocal.updateSource(connection, CompanyConstants.COMPANY
                                    + CompanyConstants.SUBCOMPANY + "BNFLINE", "SOURCEFREF",
                                    ksLineLogRef, transRef);
                            System.err.println("BNFLINE is UPDATED");
                            break;
                        case 22://Bankadan Cekilen
                            dbLocal.updateSource(connection, CompanyConstants.COMPANY
                                    + CompanyConstants.SUBCOMPANY + "BNFLINE", "SOURCEFREF",
                                    ksLineLogRef, transRef);
                            System.err.println("BNFLINE is UPDATED");
                            break;
                        case 31://Mal alim fatura
                            dbLocal.updateSource(connection, CompanyConstants.COMPANY
                                    + CompanyConstants.SUBCOMPANY + "INVOICE", "KASTRANSREF",
                                    ksLineLogRef, transRef);
                            System.err.println("INVOICE is UPDATED");
                            break;
                        case 32://Perakende satis iade Faturasi
                            dbLocal.updateSource(connection, CompanyConstants.COMPANY
                                    + CompanyConstants.SUBCOMPANY + "INVOICE", "KASTRANSREF",
                                    ksLineLogRef, transRef);
                            System.err.println("INVOICE is UPDATED");
                            break;
                        case 33://Toptan satis iade Fatirasi
                            dbLocal.updateSource(connection, CompanyConstants.COMPANY
                                    + CompanyConstants.SUBCOMPANY + "INVOICE", "KASTRANSREF",
                                    ksLineLogRef, transRef);
                            System.err.println("INVOICE is UPDATED");
                            break;
                        case 34://Alinan hizmet Faturasi
                            dbLocal.updateSource(connection, CompanyConstants.COMPANY
                                    + CompanyConstants.SUBCOMPANY + "INVOICE", "KASTRANSREF",
                                    ksLineLogRef, transRef);
                            System.err.println("INVOICE is UPDATED");
                            break;
                        case 35://Alim iade Faturasi
                            dbLocal.updateSource(connection, CompanyConstants.COMPANY
                                    + CompanyConstants.SUBCOMPANY + "INVOICE", "KASTRANSREF",
                                    ksLineLogRef, transRef);
                            System.err.println("INVOICE is UPDATED");
                            break;
                        case 36://Perakende satis faturasi
                            dbLocal.updateSource(connection, CompanyConstants.COMPANY
                                    + CompanyConstants.SUBCOMPANY + "INVOICE", "KASTRANSREF",
                                    ksLineLogRef, transRef);
                            System.err.println("INVOICE is UPDATED");
                            break;
                        case 37://Toptan satis faturasi
                            dbLocal.updateSource(connection, CompanyConstants.COMPANY
                                    + CompanyConstants.SUBCOMPANY + "INVOICE",
                                    "KASTRANSREF", ksLineLogRef, transRef);
                            System.err.println("INVOICE is UPDATED");
                            break;
                        case 38://verilen hizmet Faturasi
                            dbLocal.updateSource(connection, CompanyConstants.COMPANY
                                    + CompanyConstants.SUBCOMPANY + "INVOICE", "KASTRANSREF",
                                    ksLineLogRef, transRef);
                            System.err.println("INVOICE is UPDATED");
                            break;
                    }
                }

            } else {
                System.err.println("INSERT BNFICHE TABLE");
                int newKsLineLogRef = dbLocal.getPrimaryKeyInt(connection, tableNameKsLineSeq,
                        "1", "LASTLREF", "ID");

                newKsLineLogRef = newKsLineLogRef + 1;

                dbLocal.insertKsLine(connection, jObject, tableNameKsLine,
                        columnKsLine, newKsLineLogRef, ksCardLogRef, vksCardLogRef, transRef, i);
                //  update KSLINESSEQ tables LASTLREF value with new value
                if (dbLocal.getPrimaryKeyInt(connection, tableNameKsLine,
                        String.valueOf(newKsLineLogRef), "LOGICALREF", "LOGICALREF") == newKsLineLogRef) {

                    dbLocal.updateKey(connection, newKsLineLogRef, tableNameKsLineSeq);

                    switch (ksLineListCloud.get(i).getTrCode()) {
                        case 11://cari Hesap Tahsilat
                            dbLocal.updateSource(connection, CompanyConstants.COMPANY
                                    + CompanyConstants.SUBCOMPANY + "CLFLINE", "SOURCEFREF",
                                    newKsLineLogRef, transRef);
                            System.err.println("CLFLINE is UPDATED");
                            break;
                        case 12://cari Hesap Odeme
                            dbLocal.updateSource(connection, CompanyConstants.COMPANY
                                    + CompanyConstants.SUBCOMPANY + "CLFLINE", "SOURCEFREF",
                                    newKsLineLogRef, transRef);
                            System.err.println("CLFLINE is UPDATED");
                            break;
                        case 21://Bankaya Yatirilan
                            dbLocal.updateSource(connection, CompanyConstants.COMPANY
                                    + CompanyConstants.SUBCOMPANY + "BNFLINE", "SOURCEFREF",
                                    newKsLineLogRef, transRef);
                            System.err.println("BNFLINE is UPDATED");
                            break;
                        case 22://Bankadan Cekilen
                            dbLocal.updateSource(connection, CompanyConstants.COMPANY
                                    + CompanyConstants.SUBCOMPANY + "BNFLINE", "SOURCEFREF",
                                    newKsLineLogRef, transRef);
                            System.err.println("BNFLINE is UPDATED");
                            break;
                        case 31://Mal alim fatura
                            dbLocal.updateSource(connection, CompanyConstants.COMPANY
                                    + CompanyConstants.SUBCOMPANY + "INVOICE", "KASTRANSREF",
                                    newKsLineLogRef, transRef);
                            System.err.println("INVOICE is UPDATED");
                            break;
                        case 32://Perakende satis iade Faturasi
                            dbLocal.updateSource(connection, CompanyConstants.COMPANY
                                    + CompanyConstants.SUBCOMPANY + "INVOICE", "KASTRANSREF",
                                    newKsLineLogRef, transRef);
                            System.err.println("INVOICE is UPDATED");
                            break;
                        case 33://Toptan satis iade Fatirasi
                            dbLocal.updateSource(connection, CompanyConstants.COMPANY
                                    + CompanyConstants.SUBCOMPANY + "INVOICE", "KASTRANSREF",
                                    newKsLineLogRef, transRef);
                            System.err.println("INVOICE is UPDATED");
                            break;
                        case 34://Alinan hizmet Faturasi
                            dbLocal.updateSource(connection, CompanyConstants.COMPANY
                                    + CompanyConstants.SUBCOMPANY + "INVOICE", "KASTRANSREF",
                                    newKsLineLogRef, transRef);
                            System.err.println("INVOICE is UPDATED");
                            break;
                        case 35://Alim iade Faturasi
                            dbLocal.updateSource(connection, CompanyConstants.COMPANY
                                    + CompanyConstants.SUBCOMPANY + "INVOICE", "KASTRANSREF",
                                    newKsLineLogRef, transRef);
                            System.err.println("INVOICE is UPDATED");
                            break;
                        case 36://Perakende satis faturasi
                            dbLocal.updateSource(connection, CompanyConstants.COMPANY
                                    + CompanyConstants.SUBCOMPANY + "INVOICE", "KASTRANSREF",
                                    newKsLineLogRef, transRef);
                            System.err.println("INVOICE is UPDATED");
                            break;
                        case 37://Toptan satis faturasi
                            dbLocal.updateSource(connection, CompanyConstants.COMPANY
                                    + CompanyConstants.SUBCOMPANY + "INVOICE", "KASTRANSREF",
                                    newKsLineLogRef, transRef);
                            System.err.println("INVOICE is UPDATED");
                            break;
                        case 38://verilen hizmet Faturasi
                            dbLocal.updateSource(connection, CompanyConstants.COMPANY
                                    + CompanyConstants.SUBCOMPANY + "INVOICE", "KASTRANSREF",
                                    newKsLineLogRef, transRef);
                            System.err.println("INVOICE is UPDATED");
                            break;
                    }
                }
            }
        }

        //HERE WE UPDATING KSLINE TRANSREF FOR VIRMAN BORC AND VIRMAN ALACAK
        /*
        SELECT LOGICALREF FROM LG_001_02_KSLINES WHERE TRCODE = 74
            AND CAPIBLOCK_CREADEDDATE = '2017-02-23 00:00:00.000'
            AND CAPIBLOCK_CREATEDHOUR = 12
            AND CAPIBLOCK_CREATEDMIN = 10
            AND CAPIBLOCK_CREATEDSEC = 31
            AND AMOUNT = 10
            AND VCARDREF = 2
         */
        for (int i = 0; i < ksLineListCloud.size(); i++) {
            switch (ksLineListCloud.get(i).getTrCode()) {
                case 73:
                    int ksLineLogRef = dbLocal.getPrimaryKeyInt(connection, tableNameKsLine,
                            String.valueOf(ksLineListCloud.get(i).getSpecode()), "LOGICALREF", "SPECODE");
                    if (ksLineLogRef != 0) {
                        String condition = "TRCODE = 74 AND CAPIBLOCK_CREADEDDATE = '"
                                + ksLineListCloud.get(i).getCapiblockCreadeddate()
                                + "' AND CAPIBLOCK_CREATEDHOUR = '" + ksLineListCloud.get(i)
                                .getCapiblockCreatedhour() + "' AND CAPIBLOCK_CREATEDMIN = '"
                                + ksLineListCloud.get(i).getCapiblockCreatedmin()
                                + "' AND CAPIBLOCK_CREATEDSEC = '" + ksLineListCloud.get(i)
                                .getCapiblockCreatedsec() + "' AND AMOUNT = '"
                                + ksLineListCloud.get(i).getAmount() + "' AND VCARDREF = '"
                                + ksLineListCloud.get(i).getCardRef() + "'";

                        int transRef = dbLocal.getPrimaryKeyInt(connection, tableNameKsLine, condition);
                        if (transRef != 0) {
                            dbLocal.updateSource(connection, tableNameKsLine, "TRANSREF",
                                    transRef, ksLineLogRef);
                        } else {
                            System.err.println("TRANSREF = 0 \n borclu kasa bulunamady!");
                        }
                    } else {
                        System.err.println("LOGICALREF = 0 \n alacakli kasa transref update edilemedi!");
                    }
                    break;
                case 74:
                    int ksLineLogRef2 = dbLocal.getPrimaryKeyInt(connection, tableNameKsLine,
                            String.valueOf(ksLineListCloud.get(i).getSpecode()), "LOGICALREF", "SPECODE");
                    if (ksLineLogRef2 != 0) {
                        String condition = "TRCODE = 73 AND CAPIBLOCK_CREADEDDATE = '"
                                + ksLineListCloud.get(i).getCapiblockCreadeddate()
                                + "' AND CAPIBLOCK_CREATEDHOUR = '" + ksLineListCloud.get(i)
                                .getCapiblockCreatedhour() + "' AND CAPIBLOCK_CREATEDMIN = '"
                                + ksLineListCloud.get(i).getCapiblockCreatedmin()
                                + "' AND CAPIBLOCK_CREATEDSEC = '" + ksLineListCloud.get(i)
                                .getCapiblockCreatedsec() + "' AND AMOUNT = '"
                                + ksLineListCloud.get(i).getAmount() + "' AND VCARDREF = '"
                                + ksLineListCloud.get(i).getCardRef() + "'";

                        int transRef = dbLocal.getPrimaryKeyInt(connection, tableNameKsLine, condition);
                        if (transRef != 0) {
                            dbLocal.updateSource(connection, tableNameKsLine, "TRANSREF",
                                    transRef, ksLineLogRef2);
                        } else {
                            System.err.println("TRANSREF = 0 \n borclu kasa bulunamady!");
                        }
                    } else {
                        System.err.println("LOGICALREF = 0 \n alacakli kasa transref update edilemedi!");
                    }
                    break;
            }
        }
    }

    //METHOD TO SYNC AND INSERT PAYTRANS
    public void syncPayTrans(ArrayList<PayTrans> payTransFromCloud, JSONObject jObject) {
        for (int i = 0; i < payTransFromCloud.size(); i++) {
            int payTransLogRef = dbLocal.getPrimaryKeyInt(connection, tableNamePayTrans,
                    String.valueOf(payTransFromCloud.get(i).getInstalRef()), "LOGICALREF", "INSTALREF");
            int clientLogRef = dbLocal.getPrimaryKeyInt(connection, CompanyConstants.COMPANY + "CLCARD",
                    String.valueOf(payTransFromCloud.get(i).getCardRef()), "LOGICALREF", "TAXNR");
            if (clientLogRef == 0 && payTransFromCloud.get(i).getCardRef() != 0) {
                ClCardReceiver clCard = new ClCardReceiver();
                ArrayList<ClCard> clCardList = clCard.getClCardArrayList(clCard
                        .getJsonObjectFromFile(CompanyConstants.FILE_PATH_RECEIVE + "ClCard.json"));
                for (int a = 0; a < clCardList.size(); a++) {
                    if (clCardList.get(a).getLogicalRef() == payTransFromCloud.get(i).getCardRef()) {
                        clientLogRef = dbLocal.getPrimaryKeyInt(connection, CompanyConstants.COMPANY + "CLCARD",
                                String.valueOf(clCardList.get(a).getTaxNr()),
                                "LOGICALREF", "TAXNR");
                        break;
                    }
                }
            }
            int ficheRef;
            switch (payTransFromCloud.get(i).getModuleNr()) {
                case 4://invoice
                    ficheRef = dbLocal.getPrimaryKeyInt(connection, CompanyConstants.COMPANY
                            + CompanyConstants.SUBCOMPANY + "INVOICE",
                            String.valueOf(payTransFromCloud.get(i).getFicheRef()), "LOGICALREF", "TRACKNR");
                    System.err.println("invoice fishRef: " + ficheRef);
                    break;
                case 5://clfline
                    ficheRef = dbLocal.getPrimaryKeyInt(connection, CompanyConstants.COMPANY
                            + CompanyConstants.SUBCOMPANY + "CLFLINE",
                            String.valueOf(payTransFromCloud.get(i).getFicheRef()), "LOGICALREF", "SPECODE");
                    System.err.println("clfLine fishRef: " + ficheRef);
                    break;
                case 7://bnfline
                    ficheRef = dbLocal.getPrimaryKeyInt(connection, CompanyConstants.COMPANY
                            + CompanyConstants.SUBCOMPANY + "BNFLINE",
                            String.valueOf(payTransFromCloud.get(i).getFicheRef()), "LOGICALREF", "SPECODE");
                    System.err.println("bnfLine fishRef: " + ficheRef);
                    break;
                case 10://kslines
                    ficheRef = dbLocal.getPrimaryKeyInt(connection, tableNameKsLine,
                            String.valueOf(payTransFromCloud.get(i).getFicheRef()), "LOGICALREF", "SPECODE");
                    System.err.println("ksLine fishRef: " + ficheRef);
                    break;
                default:
                    ficheRef = 0;
                    break;
            }

            if (payTransLogRef != 0) {
                System.err.println("UPDATE PAYTRANS TABLE");
                System.err.println("DELETE logicalRef: " + payTransLogRef + "AND INSERT AGAIN!");

                dbLocal.deleteLogRef(connection, tableNamePayTrans, "LOGICALREF", payTransLogRef);
                dbLocal.insertPayTrans(connection, jObject, tableNamePayTrans,
                        columnPayTrans, payTransLogRef, clientLogRef, ficheRef, i);
            } else {
                System.err.println("INSERT PAYTRANS TABLE");
                int newPayTransLogRef = dbLocal.getPrimaryKeyInt(connection, tableNamePayTransSeq,
                        "1", "LASTLREF", "ID");

                newPayTransLogRef = newPayTransLogRef + 1;
                dbLocal.insertPayTrans(connection, jObject, tableNamePayTrans,
                        columnPayTrans, newPayTransLogRef, clientLogRef, ficheRef, i);
                //  update PAYTRANSSEQ tables LASTLREF value with new value

                if (dbLocal.getPrimaryKeyInt(connection, tableNamePayTrans,
                        String.valueOf(newPayTransLogRef), "LOGICALREF", "LOGICALREF") == newPayTransLogRef) {

                    dbLocal.updateKey(connection, newPayTransLogRef, tableNamePayTransSeq);
                }
            }
        }
    }
}
