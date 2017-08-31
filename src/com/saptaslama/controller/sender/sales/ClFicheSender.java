/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saptaslama.controller.sender.sales;

import com.saptaslama.controller.CompanyConstants;
import com.saptaslama.controller.DbConnection;
import com.saptaslama.controller.sender.Sender;
import com.saptaslama.model.ClFiche;
import com.saptaslama.model.ClfLine;
import com.saptaslama.model.JsonObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;

/**
 *
 * @author Shagy
 */
public class ClFicheSender extends Sender {
    
    public void init() {
        Connection con;
        ResultSet resultSetClFiche;
        ResultSet resultSetClfLine;
        
        String tabelNameClFiche = CompanyConstants.COMPANY + CompanyConstants.SUBCOMPANY + "CLFICHE";
        String tabelNameClfLine = CompanyConstants.COMPANY + CompanyConstants.SUBCOMPANY + "CLFLINE";
        
        String fileNameClFiche = "ClFiche.json";
        String fileNameClfLine = "ClfLine.json";
        
        DbConnection db = new DbConnection();

        con = db.getConnection();
        
        resultSetClFiche = db.querySelectAll(con, tabelNameClFiche);
        resultSetClfLine = db.querySelectAll(con, tabelNameClfLine);

        sendJsonFile(getClFicheJson(getClFicheArrayList(resultSetClFiche)), CompanyConstants
                .FILE_PATH_SEND + fileNameClFiche);
        sendJsonFile(getClfLineJson(getClfLineArrayList(resultSetClfLine)), CompanyConstants
                .FILE_PATH_SEND + fileNameClfLine);

        db.closeConnection(con);
        db.closeResultSet(resultSetClFiche);
        db.closeResultSet(resultSetClfLine);
    }
//********************************CLFICHE***************************************

    public ArrayList<ClFiche> getClFicheArrayList(ResultSet resultSet) {

        ArrayList<ClFiche> tableValue = new ArrayList<>();
        ClFiche unit;

        try {
            while (resultSet.next()) {
                unit = new ClFiche(
                        resultSet.getInt("LOGICALREF"), resultSet.getString("FICHENO"),
                        resultSet.getString("DATE_"), resultSet.getString("DOCODE"), resultSet.getString("TRCODE"),
                        resultSet.getString("SPECCODE"), resultSet.getString("CYPHCODE"),
                        resultSet.getShort("BRANCH"), resultSet.getShort("DEPARTMENT"),
                        resultSet.getString("GENEXP1"), resultSet.getString("GENEXP2"),
                        resultSet.getString("GENEXP3"), resultSet.getString("GENEXP4"),
                        resultSet.getFloat("DEBIT"), resultSet.getFloat("CREDIT"),
                        resultSet.getFloat("REPDEBIT"), resultSet.getFloat("REPCREDIT"),
                        resultSet.getShort("CAPIBLOCK_CREATEDBY"), resultSet.getString("CAPIBLOCK_CREADEDDATE"),
                        resultSet.getShort("CAPIBLOCK_CREATEDHOUR"), resultSet.getShort("CAPIBLOCK_CREATEDMIN"),
                        resultSet.getShort("CAPIBLOCK_CREATEDSEC"), resultSet.getShort("CAPIBLOCK_MODIFIEDBY"),
                        resultSet.getString("CAPIBLOCK_MODIFIEDDATE"), resultSet.getShort("CAPIBLOCK_MODIFIEDHOUR"),
                        resultSet.getShort("CAPIBLOCK_MODIFIEDMIN"), resultSet.getShort("CAPIBLOCK_MODIFIEDSEC"),
                        resultSet.getShort("ACCOUNTED"), resultSet.getInt("INVOREF"),
                        resultSet.getInt("CASHACCREF"), resultSet.getInt("CASHCENREF"),
                        resultSet.getShort("PRINTCNT"), resultSet.getShort("CANCELLED"),
                        resultSet.getShort("CANCELLEDACC"), resultSet.getInt("ACCFICHEREF"),
                        resultSet.getShort("GENEXCTYP"), resultSet.getShort("LINEEXCTYP"),
                        resultSet.getShort("TEXTINC"), resultSet.getShort("SITEID"),
                        resultSet.getShort("RECSTATUS"), resultSet.getInt("ORGLOGICREF"),
                        resultSet.getInt("WFSTATUS"), resultSet.getInt("TIME"),
                        resultSet.getInt("CLCARDREF"), resultSet.getInt("BANKACCREF"),
                        resultSet.getInt("BNACCREF"), resultSet.getInt("BNCENTERREF"),
                        resultSet.getString("TRADINGGRP"), resultSet.getInt("POSCOMMACCREF"),
                        resultSet.getInt("POSCOMMCENREF"), resultSet.getInt("POINTCOMMACCREF"),
                        resultSet.getInt("POINTCOMMCENREF"), resultSet.getInt("PROJECTREF"),
                        resultSet.getShort("STATUS"), resultSet.getInt("WFLOWCRDREF"),
                        resultSet.getString("ORGLOGOID"), resultSet.getShort("AFFECTCOLLATRL"),
                        resultSet.getShort("GRPFIRMTRANS"), resultSet.getShort("AFFECTRISK"),
                        resultSet.getInt("POSTERMINALNR"), resultSet.getString("POSTERMINALNUM"));
                tableValue.add(unit);
            }
            return tableValue;
        } catch (SQLException ex) {
            Logger.getLogger(ClFicheSender.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
            return null;
        }
    }

    public JsonObject getClFicheJson(ArrayList<ClFiche> list) {
        CompanyConstants c = new CompanyConstants();

        JsonObject jsonObject = new JsonObject();
        JSONArray logicalRef = new JSONArray();
        JSONArray ficheNo = new JSONArray();
        JSONArray date = new JSONArray();
        JSONArray doCode = new JSONArray();
        JSONArray trCode = new JSONArray();
        JSONArray specode = new JSONArray();
        JSONArray cyphCode = new JSONArray();
        JSONArray branch = new JSONArray();
        JSONArray department = new JSONArray();
        JSONArray genexp1 = new JSONArray();
        JSONArray genexp2 = new JSONArray();
        JSONArray genexp3 = new JSONArray();
        JSONArray genexp4 = new JSONArray();
        JSONArray debit = new JSONArray();
        JSONArray credit = new JSONArray();
        JSONArray repDebit = new JSONArray();
        JSONArray repCredit = new JSONArray();
        JSONArray capiblockCreatedby = new JSONArray();
        JSONArray capiblockCreadeddate = new JSONArray();
        JSONArray capiblockCreatedhour = new JSONArray();
        JSONArray capiblockCreatedmin = new JSONArray();
        JSONArray capiblockCreatedsec = new JSONArray();
        JSONArray capiblockModifiedby = new JSONArray();
        JSONArray capiblockModifieddate = new JSONArray();
        JSONArray capiblockModifiedhour = new JSONArray();
        JSONArray capiblockModifiedmin = new JSONArray();
        JSONArray capiblockModifiedsec = new JSONArray();
        JSONArray accounted = new JSONArray();
        JSONArray invoRef = new JSONArray();
        JSONArray cashAccRef = new JSONArray();
        JSONArray cashCentRef = new JSONArray();
        JSONArray printCnt = new JSONArray();
        JSONArray cancelled = new JSONArray();
        JSONArray cancelledAcc = new JSONArray();
        JSONArray accFicheRef = new JSONArray();
        JSONArray genexcTyp = new JSONArray();
        JSONArray lineExcTyp = new JSONArray();
        JSONArray textInc = new JSONArray();
        JSONArray siteId = new JSONArray();
        JSONArray recStatus = new JSONArray();
        JSONArray orgLogicRef = new JSONArray();
        JSONArray wfStatus = new JSONArray();
        JSONArray time = new JSONArray();
        JSONArray clCardRef = new JSONArray();
        JSONArray bankAccRef = new JSONArray();
        JSONArray bnAccRef = new JSONArray();
        JSONArray bnCenterRef = new JSONArray();
        JSONArray tradingGrp = new JSONArray();
        JSONArray poscommAccRef = new JSONArray();
        JSONArray poscommCenRef = new JSONArray();
        JSONArray pointCommAccRef = new JSONArray();
        JSONArray pointCommCenRef = new JSONArray();
        JSONArray projectRef = new JSONArray();
        JSONArray status = new JSONArray();
        JSONArray wFlowCrdRef = new JSONArray();
        JSONArray orgLogoId = new JSONArray();
        JSONArray affectCollatRl = new JSONArray();
        JSONArray grpFirmTrans = new JSONArray();
        JSONArray affectRisk = new JSONArray();
        JSONArray posterminalNr = new JSONArray();
        JSONArray posterminalNum = new JSONArray();

        for (int a = 0; a < list.size(); a++) {

            //here we setting the specode with local logicalRef (example: if depo then we adding "D" infront of LogREf)
            if (list.get(a).getSpecode().equals("")) {
                if ((c.MAGAZA_CODE - list.get(a).getLogicalRef()) != 499999999) {
                    specode.add(a, String.valueOf(c.MAGAZA_CODE - list.get(a).getLogicalRef()));
                } else {
                    JOptionPane.showMessageDialog(null, "OUTPUTIDCODE icin logRef 499999999 sayisini asmistir!");
                }
            } else {
                specode.add(list.get(a).getSpecode());
            }

            logicalRef.add(list.get(a).getLogicalRef());
            date.add(list.get(a).getDate());
            doCode.add(list.get(a).getDoCode());
            trCode.add(list.get(a).getTrCode());
            ficheNo.add(list.get(a).getFicheNo());
            cyphCode.add(list.get(a).getCyphCode());
            branch.add(list.get(a).getBranch());
            department.add(list.get(a).getDepartment());
            genexp1.add(list.get(a).getGenexp1());
            genexp2.add(list.get(a).getGenexp2());
            genexp3.add(list.get(a).getGenexp3());
            genexp4.add(list.get(a).getGenexp4());
            debit.add(list.get(a).getDebit());
            credit.add(list.get(a).getCredit());
            repDebit.add(list.get(a).getRepDebit());
            repCredit.add(list.get(a).getRepCredit());
            capiblockCreatedby.add(list.get(a).getCapiblockCreatedby());
            capiblockCreadeddate.add(list.get(a).getCapiblockCreadeddate());
            capiblockCreatedhour.add(list.get(a).getCapiblockCreatedhour());
            capiblockCreatedmin.add(list.get(a).getCapiblockCreatedmin());
            capiblockCreatedsec.add(list.get(a).getCapiblockCreatedsec());
            capiblockModifiedby.add(list.get(a).getCapiblockModifiedby());
            capiblockModifieddate.add(list.get(a).getCapiblockModifieddate());
            capiblockModifiedhour.add(list.get(a).getCapiblockModifiedhour());
            capiblockModifiedmin.add(list.get(a).getCapiblockModifiedmin());
            capiblockModifiedsec.add(list.get(a).getCapiblockModifiedsec());
            accounted.add(list.get(a).getAccounted());
            invoRef.add(list.get(a).getInvoRef());
            cashAccRef.add(list.get(a).getCashAccRef());
            cashCentRef.add(list.get(a).getCashCentRef());
            printCnt.add(list.get(a).getPrintCnt());
            cancelled.add(list.get(a).getCancelled());
            cancelledAcc.add(list.get(a).getCancelledAcc());
            accFicheRef.add(list.get(a).getAccFicheRef());
            genexcTyp.add(list.get(a).getGenexcTyp());
            lineExcTyp.add(list.get(a).getLineExcTyp());
            textInc.add(list.get(a).getTextInc());
            siteId.add(list.get(a).getSiteId());
            recStatus.add(list.get(a).getRecStatus());
            orgLogicRef.add(list.get(a).getOrgLogicRef());
            wfStatus.add(list.get(a).getWfStatus());
            time.add(list.get(a).getTime());
            clCardRef.add(list.get(a).getClCardRef());
            bankAccRef.add(list.get(a).getBankAccRef());
            bnAccRef.add(list.get(a).getBnAccRef());
            bnCenterRef.add(list.get(a).getBnCenterRef());
            tradingGrp.add(list.get(a).getTradingGrp());
            poscommAccRef.add(list.get(a).getPoscommAccRef());
            poscommCenRef.add(list.get(a).getPoscommCenRef());
            pointCommAccRef.add(list.get(a).getPointCommAccRef());
            pointCommCenRef.add(list.get(a).getPointCommCenRef());
            projectRef.add(list.get(a).getProjectRef());
            status.add(list.get(a).getStatus());
            wFlowCrdRef.add(list.get(a).getwFlowCrdRef());
            orgLogoId.add(list.get(a).getOrgLogoId());
            affectCollatRl.add(list.get(a).getAffectCollatRl());
            grpFirmTrans.add(list.get(a).getGrpFirmTrans());
            affectRisk.add(list.get(a).getAffectRisk());
            posterminalNr.add(list.get(a).getPosterminalNr());
            posterminalNum.add(list.get(a).getPosterminalNum());
        }
        jsonObject.setObject("LOGICALREF", logicalRef);
        jsonObject.setObject("FICHENO", ficheNo);
        jsonObject.setObject("DATE_", date);
        jsonObject.setObject("DOCODE", doCode);
        jsonObject.setObject("TRCODE", trCode);
        jsonObject.setObject("SPECCODE", specode);
        jsonObject.setObject("CYPHCODE", cyphCode);
        jsonObject.setObject("BRANCH", branch);
        jsonObject.setObject("DEPARTMENT", department);
        jsonObject.setObject("GENEXP1", genexp1);
        jsonObject.setObject("GENEXP2", genexp2);
        jsonObject.setObject("GENEXP3", genexp3);
        jsonObject.setObject("GENEXP4", genexp4);
        jsonObject.setObject("DEBIT", debit);
        jsonObject.setObject("CREDIT", credit);
        jsonObject.setObject("REPDEBIT", repDebit);
        jsonObject.setObject("REPCREDIT", repCredit);
        jsonObject.setObject("CAPIBLOCK_CREATEDBY", capiblockCreatedby);
        jsonObject.setObject("CAPIBLOCK_CREADEDDATE", capiblockCreadeddate);
        jsonObject.setObject("CAPIBLOCK_CREATEDHOUR", capiblockCreatedhour);
        jsonObject.setObject("CAPIBLOCK_CREATEDMIN", capiblockCreatedmin);
        jsonObject.setObject("CAPIBLOCK_CREATEDSEC", capiblockCreatedsec);
        jsonObject.setObject("CAPIBLOCK_MODIFIEDBY", capiblockModifiedby);
        jsonObject.setObject("CAPIBLOCK_MODIFIEDDATE", capiblockModifieddate);
        jsonObject.setObject("CAPIBLOCK_MODIFIEDHOUR", capiblockModifiedhour);
        jsonObject.setObject("CAPIBLOCK_MODIFIEDMIN", capiblockModifiedmin);
        jsonObject.setObject("CAPIBLOCK_MODIFIEDSEC", capiblockModifiedsec);
        jsonObject.setObject("ACCOUNTED", accounted);
        jsonObject.setObject("INVOREF", invoRef);
        jsonObject.setObject("CASHACCREF", cashAccRef);
        jsonObject.setObject("CASHCENREF", cashCentRef);
        jsonObject.setObject("PRINTCNT", printCnt);
        jsonObject.setObject("CANCELLED", cancelled);
        jsonObject.setObject("CANCELLEDACC", cancelledAcc);
        jsonObject.setObject("ACCFICHEREF", accFicheRef);
        jsonObject.setObject("GENEXCTYP", genexcTyp);
        jsonObject.setObject("LINEEXCTYP", lineExcTyp);
        jsonObject.setObject("TEXTINC", textInc);
        jsonObject.setObject("SITEID", siteId);
        jsonObject.setObject("RECSTATUS", recStatus);
        jsonObject.setObject("ORGLOGICREF", orgLogicRef);
        jsonObject.setObject("WFSTATUS", wfStatus);
        jsonObject.setObject("TIME", time);
        jsonObject.setObject("CLCARDREF", clCardRef);
        jsonObject.setObject("BANKACCREF", bankAccRef);
        jsonObject.setObject("BNACCREF", bnAccRef);
        jsonObject.setObject("BNCENTERREF", bnCenterRef);
        jsonObject.setObject("TRADINGGRP", tradingGrp);
        jsonObject.setObject("POSCOMMACCREF", poscommAccRef);
        jsonObject.setObject("POSCOMMCENREF", poscommCenRef);
        jsonObject.setObject("POINTCOMMACCREF", pointCommAccRef);
        jsonObject.setObject("POINTCOMMCENREF", pointCommCenRef);
        jsonObject.setObject("PROJECTREF", projectRef);
        jsonObject.setObject("STATUS", status);
        jsonObject.setObject("WFLOWCRDREF", wFlowCrdRef);
        jsonObject.setObject("ORGLOGOID", orgLogoId);
        jsonObject.setObject("AFFECTCOLLATRL", affectCollatRl);
        jsonObject.setObject("GRPFIRMTRANS", grpFirmTrans);
        jsonObject.setObject("AFFECTRISK", affectRisk);
        jsonObject.setObject("POSTERMINALNR", posterminalNr);
        jsonObject.setObject("POSTERMINALNUM", posterminalNum);
        return jsonObject;
    }

//********************************CLFLINE***************************************    
    public ArrayList<ClfLine> getClfLineArrayList(ResultSet resultSet) {

        ArrayList<ClfLine> tableValue = new ArrayList<>();
        ClfLine line;

        try {
            while (resultSet.next()) {
                line = new ClfLine(
                        resultSet.getInt("LOGICALREF"), resultSet.getInt("CLIENTREF"),
                        resultSet.getInt("CLACCREF"), resultSet.getInt("CLCENTERREF"),
                        resultSet.getInt("CASHCENTERREF"), resultSet.getInt("CASHACCOUNTREF"),
                        resultSet.getInt("VIRMANREF"), resultSet.getInt("SOURCEFREF"),
                        resultSet.getString("DATE_"), resultSet.getShort("DEPARTMENT"), resultSet.getShort("BRANCH"),
                        resultSet.getShort("MODULENR"), resultSet.getShort("TRCODE"),
                        resultSet.getShort("LINENR"), resultSet.getString("SPECODE"),
                        resultSet.getString("CYPHCODE"), resultSet.getString("TRANNO"),
                        resultSet.getString("DOCODE"), resultSet.getString("LINEEXP"),
                        resultSet.getShort("ACCOUNTED"), resultSet.getShort("SIGN"),
                        resultSet.getFloat("AMOUNT"), resultSet.getShort("TRCURR"),
                        resultSet.getFloat("TRRATE"), resultSet.getFloat("TRNET"),
                        resultSet.getFloat("REPORTRATE"), resultSet.getFloat("REPORTNET"),
                        resultSet.getInt("EXTENREF"), resultSet.getInt("PAYDEFREF"),
                        resultSet.getInt("ACCFICHEREF"), resultSet.getShort("PRINTCNT"),
                        resultSet.getShort("CAPIBLOCK_CREATEDBY"), resultSet.getString("CAPIBLOCK_CREADEDDATE"), //date format
                        resultSet.getShort("CAPIBLOCK_CREATEDHOUR"), resultSet.getShort("CAPIBLOCK_CREATEDMIN"),
                        resultSet.getShort("CAPIBLOCK_CREATEDSEC"), resultSet.getShort("CAPIBLOCK_MODIFIEDBY"),
                        /*date format*/ resultSet.getString("CAPIBLOCK_MODIFIEDDATE"), resultSet.getShort("CAPIBLOCK_MODIFIEDHOUR"),
                        resultSet.getShort("CAPIBLOCK_MODIFIEDMIN"), resultSet.getShort("CAPIBLOCK_MODIFIEDSEC"),
                        resultSet.getShort("CANCELLED"), resultSet.getShort("TRGFLAG"),
                        resultSet.getString("TRADINGGRP"), resultSet.getShort("LINEEXCTYP"),
                        resultSet.getShort("ONLYONEPAYLINE"), resultSet.getShort("DISCFLAG"),
                        resultSet.getFloat("DISCRATE"), resultSet.getFloat("VATRATE"),
                        resultSet.getFloat("CASHAMOUNT"), resultSet.getInt("DISCACCREF"),
                        resultSet.getInt("DISCCENREF"), resultSet.getInt("VATRACCREF"),
                        resultSet.getInt("VATRCENREF"), resultSet.getInt("PAYMENTREF"),
                        resultSet.getFloat("VATAMOUNT"), resultSet.getShort("SITEID"),
                        resultSet.getShort("RECSTATUS"), resultSet.getInt("ORGLOGICREF"),
                        resultSet.getFloat("INFIDX"), resultSet.getInt("POSCOMMACCREF"),
                        resultSet.getInt("POSCOMMCENREF"), resultSet.getInt("POINTCOMMACCREF"),
                        resultSet.getInt("POINTCOMMCENREF"), resultSet.getString("CHEQINFO"),
                        resultSet.getString("CREDITCNO"), resultSet.getInt("CLPRJREF"),
                        resultSet.getShort("STATUS"), resultSet.getInt("EXIMFILEREF"),
                        resultSet.getShort("EXIMPROCNR"), resultSet.getShort("MONTH_"),
                        resultSet.getShort("YEAR_"), resultSet.getFloat("FUNDSHARERAT"),
                        resultSet.getShort("AFFECTCOLLATRL"), resultSet.getShort("GRPFIRMTRANS"),
                        resultSet.getInt("REFLVATACCREF"), resultSet.getInt("REFLVATOTHACCREF"),
                        resultSet.getShort("AFFECTRISK"), resultSet.getInt("BATCHNR"),
                        resultSet.getInt("APPROVENR"), resultSet.getString("BATCHNUM"),
                        resultSet.getString("APPROVENUM"));
                tableValue.add(line);
            }
            return tableValue;
        } catch (SQLException ex) {
            Logger.getLogger(ClFicheSender.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
            return null;
        }
    }

    public JsonObject getClfLineJson(ArrayList<ClfLine> list) {
        CompanyConstants c = new CompanyConstants();

        JsonObject jsonObject = new JsonObject();
        JSONArray logicalRef = new JSONArray();
        JSONArray clientRef = new JSONArray();
        JSONArray claccRef = new JSONArray();
        JSONArray clCenterRef = new JSONArray();
        JSONArray cashCenterRef = new JSONArray();
        JSONArray cashAccountRef = new JSONArray();
        JSONArray virManRef = new JSONArray();
        JSONArray sourceFRef = new JSONArray();
        JSONArray date = new JSONArray();
        JSONArray department = new JSONArray();
        JSONArray branch = new JSONArray();
        JSONArray moduleNr = new JSONArray();
        JSONArray trCode = new JSONArray();
        JSONArray lineNr = new JSONArray();
        JSONArray specode = new JSONArray();
        JSONArray cyphCode = new JSONArray();
        JSONArray tranNo = new JSONArray();
        JSONArray doCode = new JSONArray();
        JSONArray lineExp = new JSONArray();
        JSONArray accounted = new JSONArray();
        JSONArray sign = new JSONArray();
        JSONArray amount = new JSONArray();
        JSONArray trCurr = new JSONArray();
        JSONArray trRate = new JSONArray();
        JSONArray trNet = new JSONArray();
        JSONArray reportRate = new JSONArray();
        JSONArray reportNet = new JSONArray();
        JSONArray extenRef = new JSONArray();
        JSONArray payDefRef = new JSONArray();
        JSONArray accFicheRef = new JSONArray();
        JSONArray printCnt = new JSONArray();
        JSONArray capiblockCreatedby = new JSONArray();
        JSONArray capiblockCreadeddate = new JSONArray();
        JSONArray capiblockCreatedhour = new JSONArray();
        JSONArray capiblockCreatedmin = new JSONArray();
        JSONArray capiblockCreatedsec = new JSONArray();
        JSONArray capiblockModifiedby = new JSONArray();
        JSONArray capiblockModifieddate = new JSONArray();
        JSONArray capiblockModifiedhour = new JSONArray();
        JSONArray capiblockModifiedmin = new JSONArray();
        JSONArray capiblockModifiedsec = new JSONArray();
        JSONArray cancelled = new JSONArray();
        JSONArray trgFlag = new JSONArray();
        JSONArray tradingGrp = new JSONArray();
        JSONArray lineExpType = new JSONArray();
        JSONArray onlyOnePayLine = new JSONArray();
        JSONArray discFlag = new JSONArray();
        JSONArray discRate = new JSONArray();
        JSONArray vatRate = new JSONArray();
        JSONArray cashAmount = new JSONArray();
        JSONArray discAccRef = new JSONArray();
        JSONArray discCentRef = new JSONArray();
        JSONArray vatrAccRef = new JSONArray();
        JSONArray vatrCenRef = new JSONArray();
        JSONArray paymentRef = new JSONArray();
        JSONArray vatAmount = new JSONArray();
        JSONArray siteId = new JSONArray();
        JSONArray recStatus = new JSONArray();
        JSONArray orgLogicRef = new JSONArray();
        JSONArray infidx = new JSONArray();
        JSONArray poscommAccRef = new JSONArray();
        JSONArray poscommCenRef = new JSONArray();
        JSONArray pointCommAccRef = new JSONArray();
        JSONArray pointCommCenRef = new JSONArray();
        JSONArray cheqInfo = new JSONArray();
        JSONArray creditCno = new JSONArray();
        JSONArray clprjRef = new JSONArray();
        JSONArray status = new JSONArray();
        JSONArray eximFileRef = new JSONArray();
        JSONArray eximProcNr = new JSONArray();
        JSONArray month = new JSONArray();
        JSONArray year = new JSONArray();
        JSONArray fundsShareRat = new JSONArray();
        JSONArray affectCollatRl = new JSONArray();
        JSONArray grpFirmTrans = new JSONArray();
        JSONArray reflVatAccRef = new JSONArray();
        JSONArray reflVatOthAccRef = new JSONArray();
        JSONArray affectRisk = new JSONArray();
        JSONArray batchNr = new JSONArray();
        JSONArray approveNr = new JSONArray();
        JSONArray batchNum = new JSONArray();
        JSONArray approveNum = new JSONArray();

        for (int a = 0; a < list.size(); a++) {

            //here we save local LogicalRef's in specode with starting "D" (means from Depo) for future use
            if (list.get(a).getSpecode().equals("")) {
                
                if ((c.MAGAZA_CODE - list.get(a).getLogicalRef()) != 499999999) {
                    specode.add(a, String.valueOf(c.MAGAZA_CODE - list.get(a).getLogicalRef()));
                } else {
                    JOptionPane.showMessageDialog(null, "TrackNr icin logRef 499999999 sayisini asmistir!");
                }
            } else {
                specode.add(list.get(a).getSpecode());
            }

            logicalRef.add(list.get(a).getLogicalRef());
            clientRef.add(list.get(a).getClientRef());
            claccRef.add(list.get(a).getClaccRef());
            clCenterRef.add(list.get(a).getClCenterRef());
            cashCenterRef.add(list.get(a).getCashCenterRef());
            cashAccountRef.add(list.get(a).getCashAccountRef());
            virManRef.add(list.get(a).getVirManRef());
            sourceFRef.add(list.get(a).getSourceFRef());
            date.add(list.get(a).getDate());
            department.add(list.get(a).getDepartment());
            branch.add(list.get(a).getBranch());
            moduleNr.add(list.get(a).getModuleNr());
            trCode.add(list.get(a).getTrCode());
            lineNr.add(list.get(a).getLineNr());
            cyphCode.add(list.get(a).getCyphCode());
            tranNo.add(list.get(a).getTranNo());
            doCode.add(list.get(a).getDoCode());
            lineExp.add(list.get(a).getLineExp());
            accounted.add(list.get(a).getAccounted());
            sign.add(list.get(a).getSign());
            amount.add(list.get(a).getAmount());
            trCurr.add(list.get(a).getTrCurr());
            trRate.add(list.get(a).getTrRate());
            trNet.add(list.get(a).getTrNet());
            reportRate.add(list.get(a).getReportRate());
            reportNet.add(list.get(a).getReportNet());
            extenRef.add(list.get(a).getExtenRef());
            payDefRef.add(list.get(a).getPayDefRef());
            accFicheRef.add(list.get(a).getAccFicheRef());
            printCnt.add(list.get(a).getPrintCnt());
            cancelled.add(list.get(a).getCancelled());
            trgFlag.add(list.get(a).getTrgFlag());
            tradingGrp.add(list.get(a).getTradingGrp());
            lineExpType.add(list.get(a).getLineExpType());
            onlyOnePayLine.add(list.get(a).getOnlyOnePayLine());
            discFlag.add(list.get(a).getDiscFlag());
            discRate.add(list.get(a).getDiscRate());
            vatRate.add(list.get(a).getVatRate());
            cashAmount.add(list.get(a).getCashAmount());
            discAccRef.add(list.get(a).getDiscAccRef());
            discCentRef.add(list.get(a).getDiscCentRef());
            vatrAccRef.add(list.get(a).getVatrAccRef());
            vatrCenRef.add(list.get(a).getVatrCenRef());
            paymentRef.add(list.get(a).getPaymentRef());
            vatAmount.add(list.get(a).getVatAmount());
            siteId.add(list.get(a).getSiteId());
            recStatus.add(list.get(a).getRecStatus());
            orgLogicRef.add(list.get(a).getOrgLogicRef());
            infidx.add(list.get(a).getInfidx());
            capiblockCreatedby.add(list.get(a).getCapiblockCreatedby());
            capiblockCreadeddate.add(list.get(a).getCapiblockCreadeddate());
            capiblockCreatedhour.add(list.get(a).getCapiblockCreatedhour());
            capiblockCreatedmin.add(list.get(a).getCapiblockCreatedmin());
            capiblockCreatedsec.add(list.get(a).getCapiblockCreatedsec());
            capiblockModifiedby.add(list.get(a).getCapiblockModifiedby());
            capiblockModifieddate.add(list.get(a).getCapiblockModifieddate());
            capiblockModifiedhour.add(list.get(a).getCapiblockModifiedhour());
            capiblockModifiedmin.add(list.get(a).getCapiblockModifiedmin());
            capiblockModifiedsec.add(list.get(a).getCapiblockModifiedsec());
            poscommAccRef.add(list.get(a).getPoscommAccRef());
            poscommCenRef.add(list.get(a).getPoscommCenRef());
            pointCommAccRef.add(list.get(a).getPointCommAccRef());
            pointCommCenRef.add(list.get(a).getPointCommCenRef());
            cheqInfo.add(list.get(a).getCheqInfo());
            creditCno.add(list.get(a).getCreditCno());
            clprjRef.add(list.get(a).getClprjRef());
            status.add(list.get(a).getStatus());
            eximFileRef.add(list.get(a).getEximFileRef());
            eximProcNr.add(list.get(a).getEximProcNr());
            month.add(list.get(a).getMonth());
            year.add(list.get(a).getYear());
            fundsShareRat.add(list.get(a).getFundsShareRat());
            affectCollatRl.add(list.get(a).getAffectCollatRl());
            grpFirmTrans.add(list.get(a).getGrpFirmTrans());
            reflVatAccRef.add(list.get(a).getReflVatAccRef());
            reflVatOthAccRef.add(list.get(a).getReflVatOthAccRef());
            affectRisk.add(list.get(a).getAffectRisk());
            batchNr.add(list.get(a).getBatchNr());
            approveNr.add(list.get(a).getApproveNr());
            batchNum.add(list.get(a).getBatchNum());
            approveNum.add(list.get(a).getApproveNum());
        }
        jsonObject.setObject("LOGICALREF", logicalRef);
        jsonObject.setObject("CLIENTREF", clientRef);
        jsonObject.setObject("CLACCREF", claccRef);
        jsonObject.setObject("CLCENTERREF", clCenterRef);
        jsonObject.setObject("CASHCENTERREF", cashCenterRef);
        jsonObject.setObject("CASHACCOUNTREF", cashAccountRef);
        jsonObject.setObject("VIRMANREF", virManRef);
        jsonObject.setObject("SOURCEFREF", sourceFRef);
        jsonObject.setObject("DATE_", date);
        jsonObject.setObject("DEPARTMENT", department);
        jsonObject.setObject("BRANCH", branch);
        jsonObject.setObject("MODULENR", moduleNr);
        jsonObject.setObject("TRCODE", trCode);
        jsonObject.setObject("LINENR", lineNr);
        jsonObject.setObject("SPECODE", specode);
        jsonObject.setObject("CYPHCODE", cyphCode);
        jsonObject.setObject("TRANNO", tranNo);
        jsonObject.setObject("DOCODE", doCode);
        jsonObject.setObject("LINEEXP", lineExp);
        jsonObject.setObject("ACCOUNTED", accounted);
        jsonObject.setObject("SIGN", sign);
        jsonObject.setObject("AMOUNT", amount);
        jsonObject.setObject("TRCURR", trCurr);
        jsonObject.setObject("TRRATE", trRate);
        jsonObject.setObject("TRNET", trNet);
        jsonObject.setObject("REPORTRATE", reportRate);
        jsonObject.setObject("REPORTNET", reportNet);
        jsonObject.setObject("EXTENREF", extenRef);
        jsonObject.setObject("PAYDEFREF", payDefRef);
        jsonObject.setObject("ACCFICHEREF", accFicheRef);
        jsonObject.setObject("PRINTCNT", printCnt);
        jsonObject.setObject("CAPIBLOCK_CREATEDBY", capiblockCreatedby);
        jsonObject.setObject("CAPIBLOCK_CREADEDDATE", capiblockCreadeddate);
        jsonObject.setObject("CAPIBLOCK_CREATEDHOUR", capiblockCreatedhour);
        jsonObject.setObject("CAPIBLOCK_CREATEDMIN", capiblockCreatedmin);
        jsonObject.setObject("CAPIBLOCK_CREATEDSEC", capiblockCreatedsec);
        jsonObject.setObject("CAPIBLOCK_MODIFIEDBY", capiblockModifiedby);
        jsonObject.setObject("CAPIBLOCK_MODIFIEDDATE", capiblockModifieddate);
        jsonObject.setObject("CAPIBLOCK_MODIFIEDHOUR", capiblockModifiedhour);
        jsonObject.setObject("CAPIBLOCK_MODIFIEDMIN", capiblockModifiedmin);
        jsonObject.setObject("CAPIBLOCK_MODIFIEDSEC", capiblockModifiedsec);
        jsonObject.setObject("CANCELLED", cancelled);
        jsonObject.setObject("TRGFLAG", trgFlag);
        jsonObject.setObject("TRADINGGRP", tradingGrp);
        jsonObject.setObject("LINEEXCTYP", lineExpType);
        jsonObject.setObject("ONLYONEPAYLINE", onlyOnePayLine);
        jsonObject.setObject("DISCFLAG", discFlag);
        jsonObject.setObject("DISCRATE", discRate);
        jsonObject.setObject("VATRATE", vatRate);
        jsonObject.setObject("CASHAMOUNT", cashAmount);
        jsonObject.setObject("DISCACCREF", discAccRef);
        jsonObject.setObject("DISCCENREF", discCentRef);
        jsonObject.setObject("VATRACCREF", vatrAccRef);
        jsonObject.setObject("VATRCENREF", vatrCenRef);
        jsonObject.setObject("PAYMENTREF", paymentRef);
        jsonObject.setObject("VATAMOUNT", vatAmount);
        jsonObject.setObject("SITEID", siteId);
        jsonObject.setObject("RECSTATUS", recStatus);
        jsonObject.setObject("ORGLOGICREF", orgLogicRef);
        jsonObject.setObject("INFIDX", infidx);
        jsonObject.setObject("POSCOMMACCREF", poscommAccRef);
        jsonObject.setObject("POSCOMMCENREF", poscommCenRef);
        jsonObject.setObject("POINTCOMMACCREF", pointCommAccRef);
        jsonObject.setObject("POINTCOMMCENREF", pointCommCenRef);
        jsonObject.setObject("CHEQINFO", cheqInfo);
        jsonObject.setObject("CREDITCNO", creditCno);
        jsonObject.setObject("CLPRJREF", clprjRef);
        jsonObject.setObject("STATUS", status);
        jsonObject.setObject("EXIMFILEREF", eximFileRef);
        jsonObject.setObject("EXIMPROCNR", eximProcNr);
        jsonObject.setObject("MONTH_", month);
        jsonObject.setObject("YEAR_", year);
        jsonObject.setObject("FUNDSHARERAT", fundsShareRat);
        jsonObject.setObject("AFFECTCOLLATRL", affectCollatRl);
        jsonObject.setObject("GRPFIRMTRANS", grpFirmTrans);
        jsonObject.setObject("REFLVATACCREF", reflVatAccRef);
        jsonObject.setObject("REFLVATOTHACCREF", reflVatOthAccRef);
        jsonObject.setObject("AFFECTRISK", affectRisk);
        jsonObject.setObject("BATCHNR", batchNr);
        jsonObject.setObject("APPROVENR", approveNr);
        jsonObject.setObject("BATCHNUM", batchNum);
        jsonObject.setObject("APPROVENUM", approveNum);
        return jsonObject;
    }
}
