/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saptaslama.controller.sender.sales;

import com.saptaslama.controller.CompanyConstants;
import com.saptaslama.controller.DbConnection;
import com.saptaslama.controller.sender.Sender;
import com.saptaslama.model.BnFiche;
import com.saptaslama.model.BnfLine;
import com.saptaslama.model.JsonObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;

/**
 *
 * @author Shagy
 */
public class BnFicheSender extends Sender{
    
    public void init() {
        Connection con;
        ResultSet resultSetBnFiche;
        ResultSet resultSetBnfLine;
        
        String tabelNameBnFiche = CompanyConstants.COMPANY + CompanyConstants
                .SUBCOMPANY + "BNFICHE";
        String tabelNameBnfLine = CompanyConstants.COMPANY + CompanyConstants
                .SUBCOMPANY + "BNFLINE";
        
        String fileNameBnFiche = "BnFiche.json";
        String fileNameBnfLine = "BnfLine.json";
        
        String condition = "SPECODE LIKE ''";
        
        DbConnection db = new DbConnection();

        con = db.getConnection();
        resultSetBnFiche = db.querySelectStatement(con, tabelNameBnFiche, condition);
        resultSetBnfLine = db.querySelectStatement(con, tabelNameBnfLine, condition);
        
        sendJsonFile(getBnFicheJson(resultSetBnFiche), 
                CompanyConstants.FILE_PATH_SEND + fileNameBnFiche);
        
        sendJsonFile(getBnfLineJson(resultSetBnfLine), 
                CompanyConstants.FILE_PATH_SEND + fileNameBnfLine);
        
        db.closeConnection(con);
        db.closeResultSet(resultSetBnFiche);
        db.closeResultSet(resultSetBnfLine);
    }
    
//*****************************BNFICHE******************************************
    public JsonObject getBnFicheJson(ResultSet resultSet) {
        JsonObject jsonObject = new JsonObject();
        JSONArray logicalRef = new JSONArray();
        JSONArray specode = new JSONArray();
        
        ArrayList<BnFiche> list = new ArrayList<>();
        BnFiche unit;

        try {
            while (resultSet.next()) {
                unit = new BnFiche(
                        resultSet.getInt("LOGICALREF"), resultSet.getString("SPECODE"));
                list.add(unit);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BnFicheSender.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }

        for (int a = 0; a < list.size(); a++) {
            //here we setting the specode with local logicalRef (example: if depo 
            //then we adding "D" infront of LogREf)
            specode.add(list.get(a).getSpecode());
            logicalRef.add(list.get(a).getLogicalRef());
        }
        jsonObject.setObject("LOGICALREF", logicalRef);
        jsonObject.setObject("SPECODE", specode);
        return jsonObject;
    }

//*****************************BNFLINE******************************************
    
    public JsonObject getBnfLineJson(ResultSet resultSet) {
        JsonObject jsonObject = new JsonObject();
        JSONArray logicalRef = new JSONArray();
        JSONArray specode = new JSONArray();
        ArrayList<BnfLine> list = new ArrayList<>();
        BnfLine unit;

        try {
            while (resultSet.next()) {
                unit = new BnfLine(
                        resultSet.getInt("LOGICALREF"), resultSet.getString("SPECODE"));
                list.add(unit);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BnFicheSender.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
        
        for (int a = 0; a < list.size(); a++) {
            //here we setting the specode with local logicalRef (example: if depo 
            //then we adding "D" infront of LogREf)
            specode.add(list.get(a).getSpecode());
            logicalRef.add(list.get(a).getLogicalRef());
        }
        jsonObject.setObject("LOGICALREF", logicalRef);
        jsonObject.setObject("SPECODE", specode);
        return jsonObject;
    }
}
