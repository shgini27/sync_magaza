/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saptaslama.controller.sender.sales;

import com.saptaslama.controller.CompanyConstants;
import com.saptaslama.controller.DbConnection;
import com.saptaslama.controller.sender.Sender;
import com.saptaslama.model.JsonObject;
import com.saptaslama.model.KsLines;
import com.saptaslama.model.PayTrans;
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
public class KsLineSender extends Sender{
    
    public void init() {
        Connection con;
        ResultSet resultSetKsLines;
        ResultSet resultSetPayTrans;
        
//        ArrayList<KsLines> tableValuesKsLines;
//        ArrayList<PayTrans> tableValuesPayTrans;
//        
//        JsonObject jsonKsLines;
//        JsonObject jsonPayTrans;
        
        String tabelNameKsLines = CompanyConstants.COMPANY + CompanyConstants
                .SUBCOMPANY + "KSLINES";
        String tabelNamePayTrans = CompanyConstants.COMPANY + CompanyConstants
                .SUBCOMPANY + "PAYTRANS";
        
        String fileNameKsLines = "KsLines.json";
        String fileNamePayTrans = "PayTrans.json";
        
        String queryCondition = "SPECODE LIKE ''";
        String queryCondition2 = "INSTALREF = '0'";
        
        DbConnection db = new DbConnection();

        con = db.getConnection();
        resultSetKsLines = db.querySelectStatement(con, tabelNameKsLines, queryCondition);
        resultSetPayTrans = db.querySelectStatement(con, tabelNamePayTrans, queryCondition2);

//        tableValuesKsLines = getKsLinesArrayList(resultSetKsLines);
//        tableValuesPayTrans = getPayTransArrayList(resultSetPayTrans);
//        
//        jsonKsLines = getKsLinesJson(getKsLinesArrayList(resultSetKsLines));
//        jsonPayTrans = getPayTransJson(getPayTransArrayList(resultSetPayTrans));
        
        sendJsonFile(getKsLinesJson(getKsLinesArrayList(resultSetKsLines)), 
                CompanyConstants.FILE_PATH_SEND + fileNameKsLines);
        sendJsonFile(getPayTransJson(getPayTransArrayList(resultSetPayTrans)), 
                CompanyConstants.FILE_PATH_SEND + fileNamePayTrans);
        
        db.closeConnection(con);
        db.closeResultSet(resultSetKsLines);
        db.closeResultSet(resultSetPayTrans);
    }

//********************************KSLINES***************************************
    public ArrayList<KsLines> getKsLinesArrayList(ResultSet resultSet) {

        ArrayList<KsLines> tableValue = new ArrayList<>();
        KsLines line;

        try {
            while (resultSet.next()) {
                line = new KsLines(
                        resultSet.getInt("LOGICALREF"), resultSet.getInt("CARDREF"), 
                        resultSet.getInt("VCARDREF"), resultSet.getInt("TRANSREF"), 
                        resultSet.getInt("ACCREF"), resultSet.getInt("CENTERREF"), 
                        resultSet.getInt("CSACCREF"), resultSet.getInt("CSCENTERREF"), 
                        resultSet.getString("DATE_"), resultSet.getShort("HOUR_"), 
                        resultSet.getShort("MINUTE_"), resultSet.getShort("TRCODE"), 
                        resultSet.getShort("BRANCH"), resultSet.getShort("DEPARTMENT"), 
                        resultSet.getShort("DESTBRANCH"), resultSet.getShort("DESTDEPARTMENT"), 
                        resultSet.getString("SPECODE"), resultSet.getString("CYPHCODE"), 
                        resultSet.getString("FICHENO"), resultSet.getString("CUSTTITLE"), 
                        resultSet.getString("LINEEXP"), resultSet.getFloat("AMOUNT"), 
                        resultSet.getFloat("REPORTRATE"), resultSet.getFloat("REPORTNET"), 
                        resultSet.getFloat("TRRATE"), resultSet.getFloat("TRNET"), 
                        resultSet.getShort("TRCURR"), resultSet.getShort("SIGN"), 
                        resultSet.getShort("ACCOUNTED"), resultSet.getShort("CANCELLED"), 
                        resultSet.getInt("ACCFICHEREF"), resultSet.getShort("PRINTCNT"), 
                        resultSet.getShort("CAPIBLOCK_CREATEDBY"), resultSet.getString("CAPIBLOCK_CREADEDDATE"), //date format
                        resultSet.getShort("CAPIBLOCK_CREATEDHOUR"), resultSet.getShort("CAPIBLOCK_CREATEDMIN"), 
                        resultSet.getShort("CAPIBLOCK_CREATEDSEC"), resultSet.getShort("CAPIBLOCK_MODIFIEDBY"), 
/*date format*/         resultSet.getString("CAPIBLOCK_MODIFIEDDATE"), resultSet.getShort("CAPIBLOCK_MODIFIEDHOUR"), 
                        resultSet.getShort("CAPIBLOCK_MODIFIEDMIN"), resultSet.getShort("CAPIBLOCK_MODIFIEDSEC"), 
                        resultSet.getShort("CANCELLEDACC"), resultSet.getShort("GENEXCTYP"), 
                        resultSet.getShort("LINEEXCTYP"), resultSet.getString("TRADINGGRP"), 
                        resultSet.getShort("TEXTINC"), resultSet.getShort("SITEID"),
                        resultSet.getShort("RECSTATUS"), resultSet.getInt("ORGLOGICREF"), 
                        resultSet.getInt("WFSTATUS"), resultSet.getShort("GPOPTYPE"), 
                        resultSet.getFloat("GPINCOMETACRAT"), resultSet.getFloat("GPFUNDSHARERAT"), 
                        resultSet.getString("GPPLATE"), resultSet.getInt("GPTAXACC"), 
                        resultSet.getInt("GPFUNDACC"), resultSet.getString("GPADDR"), 
                        resultSet.getFloat("SMMVATRATE"), resultSet.getInt("SMMVATACREF"), 
                        resultSet.getInt("SMMVATCENTREF"), resultSet.getString("SMMDOCODE"), 
                        resultSet.getFloat("INFIDX"), resultSet.getString("TRANGRPNO"), 
                        resultSet.getShort("TRANGRPLINENO"), resultSet.getString("ORGLOGOID"), 
                        resultSet.getShort("VATINC"), resultSet.getFloat("VATRAT"), 
                        resultSet.getInt("VATACCREF"), resultSet.getFloat("VATTOT"), 
                        resultSet.getInt("PROJECTREF"), resultSet.getInt("CSTRANSREF"), 
                        resultSet.getShort("STATUS"), resultSet.getInt("WFLOWCRDREF"), 
                        resultSet.getShort("AFFECTCOLLATRL"), resultSet.getShort("GRPFIRMTRANS"), 
                        resultSet.getString("TRANNO"), resultSet.getString("DOCODE"), 
                        resultSet.getShort("AFFECTRISK"));
                tableValue.add(line);
            }
            return tableValue;
        } catch (SQLException ex) {
            Logger.getLogger(KsLineSender.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
            return null;
        }
    }
    
    public JsonObject getKsLinesJson(ArrayList<KsLines> list) {
        CompanyConstants c = new CompanyConstants();
        
        JsonObject jsonObject = new JsonObject();
        JSONArray logicalRef = new JSONArray();
        JSONArray cardRef = new JSONArray();
        JSONArray vCardRef = new JSONArray();
        JSONArray transRef = new JSONArray();
        JSONArray accRef = new JSONArray();
        JSONArray centerRef = new JSONArray();
        JSONArray csAccRef = new JSONArray();
        JSONArray csCenterRef = new JSONArray();
        JSONArray date = new JSONArray();
        JSONArray hour = new JSONArray();
        JSONArray minute = new JSONArray();
        JSONArray trCode = new JSONArray();
        JSONArray branch = new JSONArray();
        JSONArray department = new JSONArray();
        JSONArray destBranch = new JSONArray();
        JSONArray destDepartment = new JSONArray();
        JSONArray specode = new JSONArray();
        JSONArray cyphCode = new JSONArray();
        JSONArray ficheNo = new JSONArray();
        JSONArray custTitle = new JSONArray();
        JSONArray lineExp = new JSONArray();
        JSONArray amount = new JSONArray();
        JSONArray reportRate = new JSONArray();
        JSONArray reportNet = new JSONArray();
        JSONArray trRate = new JSONArray();
        JSONArray trNet = new JSONArray();
        JSONArray trCurr = new JSONArray();
        JSONArray sign = new JSONArray();
        JSONArray accounted = new JSONArray();
        JSONArray cancelled = new JSONArray();
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
        JSONArray cancelledAcc = new JSONArray();
        JSONArray genexcTyp = new JSONArray();
        JSONArray lineExcTyp = new JSONArray();
        JSONArray tradingGrp = new JSONArray();
        JSONArray textInc = new JSONArray();
        JSONArray siteId = new JSONArray();
        JSONArray recStatus = new JSONArray();
        JSONArray orgLogicRef = new JSONArray();
        JSONArray wfStatus = new JSONArray();
        JSONArray gpopType = new JSONArray();
        JSONArray gpincometacrat = new JSONArray();
        JSONArray gpFundShareRat = new JSONArray();
        JSONArray gpPlate = new JSONArray();
        JSONArray gpTaxAcc = new JSONArray();
        JSONArray gpFundAcc = new JSONArray();
        JSONArray gpAddr = new JSONArray();
        JSONArray smmVatRate = new JSONArray();
        JSONArray smmVatAcRef = new JSONArray();
        JSONArray smmVatCenterRef = new JSONArray();
        JSONArray smmDoCode = new JSONArray();
        JSONArray infidx = new JSONArray();
        JSONArray tranGrpNo = new JSONArray();
        JSONArray tranGrpLineNo = new JSONArray();
        JSONArray orgLogoId = new JSONArray();
        JSONArray vatInc = new JSONArray();
        JSONArray vatRate = new JSONArray();
        JSONArray vatAccRef = new JSONArray();
        JSONArray vatTot = new JSONArray();
        JSONArray projectRef = new JSONArray();
        JSONArray csTransRef = new JSONArray();
        JSONArray status = new JSONArray();
        JSONArray wFlowCrdRef = new JSONArray();
        JSONArray affectCollatRl = new JSONArray();
        JSONArray grpFirmTrans = new JSONArray();
        JSONArray tranNo = new JSONArray();
        JSONArray doCode = new JSONArray();
        JSONArray affectRisk = new JSONArray();

        for (int a = 0; a < list.size(); a++) {
            
            //here we setting the specode with local logicalRef (example: if depo then we adding "D" infront of LogREf)
            if((c.MAGAZA_CODE - list.get(a).getLogicalRef()) != 499999999){
                specode.add(a, String.valueOf(c.MAGAZA_CODE - list.get(a).getLogicalRef()));
            }else{
                JOptionPane.showMessageDialog(null, "SPECODE icin logRef 499999999 sayisini asmistir!");
            }

            logicalRef.add(list.get(a).getLogicalRef());
            cardRef.add(list.get(a).getCardRef());
            vCardRef.add(list.get(a).getvCardRef());
            transRef.add(list.get(a).getTransRef());
            accRef.add(list.get(a).getAccRef());
            centerRef.add(list.get(a).getCenterRef());
            csAccRef.add(list.get(a).getCsAccRef());
            csCenterRef.add(list.get(a).getCsCenterRef());
            date.add(list.get(a).getDate());
            hour.add(list.get(a).getHour());
            minute.add(list.get(a).getMinute());
            trCode.add(list.get(a).getTrCode());
            branch.add(list.get(a).getBranch());
            department.add(list.get(a).getDepartment());
            destBranch.add(list.get(a).getDestBranch());
            destDepartment.add(list.get(a).getDestDepartment());
            cyphCode.add(list.get(a).getCyphCode());
            ficheNo.add(list.get(a).getFicheNo());
            custTitle.add(list.get(a).getCustTitle());
            lineExp.add(list.get(a).getLineExp());
            amount.add(list.get(a).getAmount());
            reportRate.add(list.get(a).getReportRate());
            reportNet.add(list.get(a).getReportNet());
            trRate.add(list.get(a).getTrRate());
            trNet.add(list.get(a).getTrNet());
            trCurr.add(list.get(a).getTrCurr());
            sign.add(list.get(a).getSign());
            accounted.add(list.get(a).getAccounted());
            cancelled.add(list.get(a).getCancelled());
            accFicheRef.add(list.get(a).getAccFicheRef());
            printCnt.add(list.get(a).getPrintCnt());
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
            cancelledAcc.add(list.get(a).getCancelled());
            genexcTyp.add(list.get(a).getGenexcTyp());
            lineExcTyp.add(list.get(a).getLineExcTyp());
            tradingGrp.add(list.get(a).getTradingGrp());
            textInc.add(list.get(a).getTextInc());
            siteId.add(list.get(a).getSiteId());
            recStatus.add(list.get(a).getRecStatus());
            orgLogicRef.add(list.get(a).getOrgLogicRef());
            wfStatus.add(list.get(a).getWfStatus());
            gpopType.add(list.get(a).getGpopType());
            gpincometacrat.add(list.get(a).getGpincometacrat());
            gpFundShareRat.add(list.get(a).getGpFundShareRat());
            gpPlate.add(list.get(a).getGpPlate());
            gpTaxAcc.add(list.get(a).getGpTaxAcc());
            gpFundAcc.add(list.get(a).getGpFundAcc());
            gpAddr.add(list.get(a).getGpAddr());
            smmVatRate.add(list.get(a).getSmmVatRate());
            smmVatAcRef.add(list.get(a).getSmmVatAcRef());
            smmVatCenterRef.add(list.get(a).getSmmVatCenterRef());
            smmDoCode.add(list.get(a).getSmmDoCode());
            infidx.add(list.get(a).getInfidx());
            tranGrpNo.add(list.get(a).getTranGrpNo());
            tranGrpLineNo.add(list.get(a).getTranGrpLineNo());
            orgLogoId.add(list.get(a).getOrgLogoId());
            vatInc.add(list.get(a).getVatInc());
            vatRate.add(list.get(a).getVatRate());
            vatAccRef.add(list.get(a).getVatAccRef());
            vatTot.add(list.get(a).getVatTot());
            projectRef.add(list.get(a).getProjectRef());
            csTransRef.add(list.get(a).getCsTransRef());
            status.add(list.get(a).getStatus());
            wFlowCrdRef.add(list.get(a).getwFlowCrdRef());
            affectCollatRl.add(list.get(a).getAffectCollatRl());
            grpFirmTrans.add(list.get(a).getGrpFirmTrans());
            tranNo.add(list.get(a).getTranNo());
            doCode.add(list.get(a).getDoCode());
            affectRisk.add(list.get(a).getAffectRisk());
        }
        jsonObject.setObject("LOGICALREF", logicalRef);
        jsonObject.setObject("CARDREF", cardRef);
        jsonObject.setObject("VCARDREF", vCardRef);
        jsonObject.setObject("TRANSREF", transRef);
        jsonObject.setObject("ACCREF", accRef);
        jsonObject.setObject("CENTERREF", centerRef);
        jsonObject.setObject("CSACCREF", csAccRef);
        jsonObject.setObject("CSCENTERREF", csCenterRef);
        jsonObject.setObject("DATE_", date);
        jsonObject.setObject("HOUR_", hour);
        jsonObject.setObject("MINUTE_", minute);
        jsonObject.setObject("TRCODE", trCode);
        jsonObject.setObject("BRANCH", branch);
        jsonObject.setObject("DEPARTMENT", department);
        jsonObject.setObject("DESTBRANCH", destBranch);
        jsonObject.setObject("DESTDEPARTMENT", destDepartment);
        jsonObject.setObject("SPECODE", specode);
        jsonObject.setObject("CYPHCODE", cyphCode);
        jsonObject.setObject("FICHENO", ficheNo);
        jsonObject.setObject("CUSTTITLE", custTitle);
        jsonObject.setObject("LINEEXP", lineExp);
        jsonObject.setObject("AMOUNT", amount);
        jsonObject.setObject("REPORTRATE", reportRate);
        jsonObject.setObject("REPORTNET", reportNet);
        jsonObject.setObject("TRRATE", trRate);
        jsonObject.setObject("TRNET", trNet);
        jsonObject.setObject("TRCURR", trCurr);
        jsonObject.setObject("SIGN", sign);
        jsonObject.setObject("ACCOUNTED", accounted);
        jsonObject.setObject("CANCELLED", cancelled);
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
        jsonObject.setObject("CANCELLEDACC", cancelledAcc);
        jsonObject.setObject("GENEXCTYP", genexcTyp);
        jsonObject.setObject("LINEEXCTYP", lineExcTyp);
        jsonObject.setObject("TRADINGGRP", tradingGrp);
        jsonObject.setObject("TEXTINC", textInc);
        jsonObject.setObject("SITEID", siteId);
        jsonObject.setObject("RECSTATUS", recStatus);
        jsonObject.setObject("ORGLOGICREF", orgLogicRef);
        jsonObject.setObject("WFSTATUS", wfStatus);
        jsonObject.setObject("GPOPTYPE", gpopType);
        jsonObject.setObject("GPINCOMETACRAT", gpincometacrat);
        jsonObject.setObject("GPFUNDSHARERAT", gpFundShareRat);
        jsonObject.setObject("GPPLATE", gpPlate);
        jsonObject.setObject("GPTAXACC", gpTaxAcc);
        jsonObject.setObject("GPFUNDACC", gpFundAcc);
        jsonObject.setObject("GPADDR", gpAddr);
        jsonObject.setObject("SMMVATRATE", smmVatRate);
        jsonObject.setObject("SMMVATACREF", smmVatAcRef);
        jsonObject.setObject("SMMVATCENTREF", smmVatCenterRef);
        jsonObject.setObject("SMMDOCODE", smmDoCode);
        jsonObject.setObject("INFIDX", infidx);
        jsonObject.setObject("TRANGRPNO", tranGrpNo);
        jsonObject.setObject("TRANGRPLINENO", tranGrpLineNo);
        jsonObject.setObject("ORGLOGOID", orgLogoId);
        jsonObject.setObject("VATINC", vatInc);
        jsonObject.setObject("VATRAT", vatRate);
        jsonObject.setObject("VATACCREF", vatAccRef);
        jsonObject.setObject("VATTOT", vatTot);
        jsonObject.setObject("PROJECTREF", projectRef);
        jsonObject.setObject("CSTRANSREF", csTransRef);
        jsonObject.setObject("STATUS", status);
        jsonObject.setObject("WFLOWCRDREF", wFlowCrdRef);
        jsonObject.setObject("AFFECTCOLLATRL", affectCollatRl);
        jsonObject.setObject("GRPFIRMTRANS", grpFirmTrans);
        jsonObject.setObject("TRANNO", tranNo);
        jsonObject.setObject("DOCODE", doCode);
        jsonObject.setObject("AFFECTRISK", affectRisk);
        return jsonObject;
    }

//********************************PAYTRANS**************************************
    public ArrayList<PayTrans> getPayTransArrayList(ResultSet resultSet) {

        ArrayList<PayTrans> tableValue = new ArrayList<>();
        PayTrans unit;

        try {
            while (resultSet.next()) {
                unit = new PayTrans(
                        resultSet.getInt("LOGICALREF"), resultSet.getInt("CARDREF"), 
                        resultSet.getString("DATE_"), resultSet.getShort("MODULENR"), 
                        resultSet.getShort("SIGN"), resultSet.getInt("FICHEREF"), 
                        resultSet.getInt("FICHELINEREF"), resultSet.getShort("TRCODE"), 
                        resultSet.getFloat("TOTAL"), resultSet.getFloat("PAID"), 
                        resultSet.getFloat("EARLYINTRATE"), resultSet.getFloat("LATELYINTRATE"), 
                        resultSet.getInt("CROSSREF"), resultSet.getShort("PAIDINCASH"), 
                        resultSet.getShort("CANCELLED"), resultSet.getString("PROCDATE"), 
                        resultSet.getShort("TRCURR"), resultSet.getFloat("TRRATE"), 
                        resultSet.getFloat("REPORTRATE"), resultSet.getShort("MODIFIED"), 
                        resultSet.getShort("REMINDLEV"), resultSet.getShort("REMINDSENT"), 
                        resultSet.getShort("CROSSCURR"), resultSet.getFloat("CROSSTOTAL"), 
                        resultSet.getShort("DISCFLAG"), resultSet.getShort("SITEID"), 
                        resultSet.getInt("ORGLOGICREF"), resultSet.getInt("WFSTATUS"), 
                        resultSet.getFloat("CLOSINGRATE"), resultSet.getString("DISCDUEDATE"), 
                        resultSet.getShort("OPSTAT"), resultSet.getShort("RECSTATUS"), 
                        resultSet.getFloat("INFIDX"), resultSet.getShort("PAYNO"), 
                        resultSet.getFloat("DELAYTOTAL"), resultSet.getInt("LASTSENDREMLEV"), 
                        resultSet.getShort("POINTTRANS"), resultSet.getString("BANKPAYDATE"), 
                        resultSet.getFloat("POSCOMSN"), resultSet.getFloat("POINTCOMSN"), 
                        resultSet.getInt("BANKACCREF"), resultSet.getShort("PAYMENTTYPE"), 
                        resultSet.getInt("CASHACCREF"), resultSet.getFloat("TRNET"), 
                        resultSet.getInt("REPAYPLANREF"), resultSet.getFloat("DUEDIFFCOMSN"), 
                        resultSet.getShort("CALCTYPE"), resultSet.getFloat("NETTOTAL"), 
                        resultSet.getShort("REPYPLNAPPLIED"), resultSet.getShort("PAYTRCURR"), 
                        resultSet.getFloat("PAYTRRATE"), resultSet.getFloat("PAYTRNET"), 
                        resultSet.getShort("BNTRCREATED"), resultSet.getInt("BNFCHREF"), 
                        resultSet.getInt("BNFLNREF"), resultSet.getShort("INSTALTYPE"), 
                        resultSet.getInt("INSTALREF"), resultSet.getInt("MAININSTALREF"));
                tableValue.add(unit);
            }
            return tableValue;
        } catch (SQLException ex) {
            Logger.getLogger(KsLineSender.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
            return null;
        }
    }
    
    public JsonObject getPayTransJson(ArrayList<PayTrans> list) {
        CompanyConstants c = new CompanyConstants();
        
        JsonObject jsonObject = new JsonObject();
        JSONArray logicalRef = new JSONArray();
        JSONArray cardRef = new JSONArray();
        JSONArray date = new JSONArray();
        JSONArray moduleNr = new JSONArray();
        JSONArray sign = new JSONArray();
        JSONArray ficheRef = new JSONArray();
        JSONArray ficheLineRef = new JSONArray();
        JSONArray trCode = new JSONArray();
        JSONArray total = new JSONArray();
        JSONArray paid = new JSONArray();
        JSONArray earlyIntRate = new JSONArray();
        JSONArray latelyIntRate = new JSONArray();
        JSONArray crossRef = new JSONArray();
        JSONArray paidInCash = new JSONArray();
        JSONArray cancelled = new JSONArray();
        JSONArray procDate = new JSONArray();
        JSONArray trCurr = new JSONArray();
        JSONArray trRate = new JSONArray();
        JSONArray reportRate = new JSONArray();
        JSONArray modified = new JSONArray();
        JSONArray remindLev = new JSONArray();
        JSONArray remindSent = new JSONArray();
        JSONArray crossCurr = new JSONArray();
        JSONArray crossTotal = new JSONArray();
        JSONArray discflag = new JSONArray();
        JSONArray siteId = new JSONArray();
        JSONArray orgLogicRef = new JSONArray();
        JSONArray wfStatus = new JSONArray();
        JSONArray closingRate = new JSONArray();
        JSONArray discDueDate = new JSONArray();
        JSONArray opStat = new JSONArray();
        JSONArray recStatus = new JSONArray();
        JSONArray infidx = new JSONArray();
        JSONArray payNo = new JSONArray();
        JSONArray delayTotal = new JSONArray();
        JSONArray lastSendRemLev = new JSONArray();
        JSONArray pointTrans = new JSONArray();
        JSONArray bankPayDate = new JSONArray();
        JSONArray poscomsn = new JSONArray();
        JSONArray pointcomsn = new JSONArray();
        JSONArray bankAccRef = new JSONArray();
        JSONArray paymentType = new JSONArray();
        JSONArray cashAccRef = new JSONArray();
        JSONArray trNet = new JSONArray();
        JSONArray repayPlanRef = new JSONArray();
        JSONArray dueDiffComsn = new JSONArray();
        JSONArray calcType = new JSONArray();
        JSONArray netTotal = new JSONArray();
        JSONArray repyPlnApplied = new JSONArray();
        JSONArray payTrCurr = new JSONArray();
        JSONArray payTrRate = new JSONArray();
        JSONArray payTrNet = new JSONArray();
        JSONArray bnTrCreated = new JSONArray();
        JSONArray bnFchRef = new JSONArray();
        JSONArray bnFlnRef = new JSONArray();
        JSONArray installType = new JSONArray();
        JSONArray instalRef = new JSONArray();
        JSONArray mainInstalRef = new JSONArray();

        for (int a = 0; a < list.size(); a++) {

            //here we will save our logicalRef for future use
            if((c.MAGAZA_CODE - list.get(a).getLogicalRef()) != 499999999){
                instalRef.add(a, c.MAGAZA_CODE - list.get(a).getLogicalRef());
            }else{
                JOptionPane.showMessageDialog(null, "INSTALREF icin logRef 499999999 sayisini asmistir!");
            }
            
            mainInstalRef.add(list.get(a).getMainInstalRef());
            logicalRef.add(list.get(a).getLogicalRef());
            cardRef.add(list.get(a).getCardRef());
            date.add(list.get(a).getDate());
            moduleNr.add(list.get(a).getModuleNr());
            sign.add(list.get(a).getSign());
            ficheRef.add(list.get(a).getFicheRef());
            ficheLineRef.add(list.get(a).getFicheLineRef());
            trCode.add(list.get(a).getTrCode());
            total.add(list.get(a).getTotal());
            paid.add(list.get(a).getPaid());
            earlyIntRate.add(list.get(a).getEarlyIntRate());
            latelyIntRate.add(list.get(a).getLatelyIntRate());
            crossRef.add(list.get(a).getCrossRef());
            paidInCash.add(list.get(a).getPaidInCash());
            cancelled.add(list.get(a).getCancelled());
            procDate.add(list.get(a).getProcDate());
            trCurr.add(list.get(a).getTrCurr());
            trRate.add(list.get(a).getTrRate());
            reportRate.add(list.get(a).getReportRate());
            modified.add(list.get(a).getModified());
            remindLev.add(list.get(a).getRemindLev());
            remindSent.add(list.get(a).getRemindSent());
            crossCurr.add(list.get(a).getCrossCurr());
            crossTotal.add(list.get(a).getCrossTotal());
            discflag.add(list.get(a).getDiscflag());
            siteId.add(list.get(a).getSiteId());
            orgLogicRef.add(list.get(a).getOrgLogicRef());
            wfStatus.add(list.get(a).getWfStatus());
            closingRate.add(list.get(a).getClosingRate());
            discDueDate.add(list.get(a).getDiscDueDate());
            opStat.add(list.get(a).getOpStat());
            recStatus.add(list.get(a).getRecStatus());
            infidx.add(list.get(a).getInfidx());
            payNo.add(list.get(a).getPayNo());
            delayTotal.add(list.get(a).getDelayTotal());
            lastSendRemLev.add(list.get(a).getLastSendRemLev());
            pointTrans.add(list.get(a).getPointTrans());
            bankPayDate.add(list.get(a).getBankPayDate());
            poscomsn.add(list.get(a).getPoscomsn());
            pointcomsn.add(list.get(a).getPointcomsn());
            bankAccRef.add(list.get(a).getBankAccRef());
            paymentType.add(list.get(a).getPaymentType());
            cashAccRef.add(list.get(a).getCashAccRef());
            trNet.add(list.get(a).getTrNet());
            repayPlanRef.add(list.get(a).getRepayPlanRef());
            dueDiffComsn.add(list.get(a).getDueDiffComsn());
            calcType.add(list.get(a).getCalcType());
            netTotal.add(list.get(a).getNetTotal());
            repyPlnApplied.add(list.get(a).getRepyPlnApplied());
            payTrCurr.add(list.get(a).getPayTrCurr());
            payTrRate.add(list.get(a).getPayTrRate());
            payTrNet.add(list.get(a).getPayTrNet());
            bnTrCreated.add(list.get(a).getBnTrCreated());
            bnFchRef.add(list.get(a).getBnFchRef());
            bnFlnRef.add(list.get(a).getBnFlnRef());
            installType.add(list.get(a).getInstallType());
        }
        jsonObject.setObject("LOGICALREF", logicalRef);
        jsonObject.setObject("CARDREF", cardRef);
        jsonObject.setObject("DATE_", date);
        jsonObject.setObject("MODULENR", moduleNr);
        jsonObject.setObject("SIGN", sign);
        jsonObject.setObject("FICHEREF", ficheRef);
        jsonObject.setObject("FICHELINEREF", ficheLineRef);
        jsonObject.setObject("TRCODE", trCode);
        jsonObject.setObject("TOTAL", total);
        jsonObject.setObject("PAID", paid);
        jsonObject.setObject("EARLYINTRATE", earlyIntRate);
        jsonObject.setObject("LATELYINTRATE", latelyIntRate);
        jsonObject.setObject("CROSSREF", crossRef);
        jsonObject.setObject("PAIDINCASH", paidInCash);
        jsonObject.setObject("CANCELLED", cancelled);
        jsonObject.setObject("PROCDATE", procDate);
        jsonObject.setObject("TRCURR", trCurr);
        jsonObject.setObject("TRRATE", trRate);
        jsonObject.setObject("REPORTRATE", reportRate);
        jsonObject.setObject("MODIFIED", modified);
        jsonObject.setObject("REMINDLEV", remindLev);
        jsonObject.setObject("REMINDSENT", remindSent);
        jsonObject.setObject("CROSSCURR", crossCurr);
        jsonObject.setObject("CROSSTOTAL", crossTotal);
        jsonObject.setObject("DISCFLAG", discflag);
        jsonObject.setObject("SITEID", siteId);
        jsonObject.setObject("ORGLOGICREF", orgLogicRef);
        jsonObject.setObject("WFSTATUS", wfStatus);
        jsonObject.setObject("CLOSINGRATE", closingRate);
        jsonObject.setObject("DISCDUEDATE", discDueDate);
        jsonObject.setObject("OPSTAT", opStat);
        jsonObject.setObject("RECSTATUS", recStatus);
        jsonObject.setObject("INFIDX", infidx);
        jsonObject.setObject("PAYNO", payNo);
        jsonObject.setObject("DELAYTOTAL", delayTotal);
        jsonObject.setObject("LASTSENDREMLEV", lastSendRemLev);
        jsonObject.setObject("POINTTRANS", pointTrans);
        jsonObject.setObject("BANKPAYDATE", bankPayDate);
        jsonObject.setObject("POSCOMSN", poscomsn);
        jsonObject.setObject("POINTCOMSN", pointcomsn);
        jsonObject.setObject("BANKACCREF", bankAccRef);
        jsonObject.setObject("PAYMENTTYPE", paymentType);
        jsonObject.setObject("CASHACCREF", cashAccRef);
        jsonObject.setObject("TRNET", trNet);
        jsonObject.setObject("REPAYPLANREF", repayPlanRef);
        jsonObject.setObject("DUEDIFFCOMSN", dueDiffComsn);
        jsonObject.setObject("CALCTYPE", calcType);
        jsonObject.setObject("NETTOTAL", netTotal);
        jsonObject.setObject("REPYPLNAPPLIED", repyPlnApplied);
        jsonObject.setObject("PAYTRCURR", payTrCurr);
        jsonObject.setObject("PAYTRRATE", payTrRate);
        jsonObject.setObject("PAYTRNET", payTrNet);
        jsonObject.setObject("BNTRCREATED", bnTrCreated);
        jsonObject.setObject("BNFCHREF", bnFchRef);
        jsonObject.setObject("BNFLNREF", bnFlnRef);
        jsonObject.setObject("INSTALTYPE", installType);
        jsonObject.setObject("INSTALREF", instalRef);
        jsonObject.setObject("MAININSTALREF", mainInstalRef);
        return jsonObject;
    }
}
