/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saptaslama.controller.receiver;

import com.saptaslama.controller.sender.Sender;
import com.saptaslama.model.KsLines;
import com.saptaslama.model.PayTrans;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Shagy
 */
public class KsLinesReceiver extends Sender{
    
    public ArrayList<KsLines> getKsLinesArrayList(JSONObject jObject) {

        ArrayList<KsLines> tableValue = new ArrayList<>();
        KsLines items;

        JSONArray logicalRef = (JSONArray) jObject.get("LOGICALREF");
        JSONArray cardRef = (JSONArray) jObject.get("CARDREF");
        JSONArray vCardRef = (JSONArray) jObject.get("VCARDREF");
        JSONArray transRef = (JSONArray) jObject.get("TRANSREF");
        JSONArray accRef = (JSONArray) jObject.get("ACCREF");
        JSONArray centerRef = (JSONArray) jObject.get("CENTERREF");
        JSONArray csAccRef = (JSONArray) jObject.get("CSACCREF");
        JSONArray csCenterRef = (JSONArray) jObject.get("CSCENTERREF");
        JSONArray date = (JSONArray) jObject.get("DATE_");
        JSONArray hour = (JSONArray) jObject.get("HOUR_");
        JSONArray minute = (JSONArray) jObject.get("MINUTE_");
        JSONArray trCode = (JSONArray) jObject.get("TRCODE");
        JSONArray branch = (JSONArray) jObject.get("BRANCH");
        JSONArray department = (JSONArray) jObject.get("DEPARTMENT");
        JSONArray destBranch = (JSONArray) jObject.get("DESTBRANCH");
        JSONArray destDepartment = (JSONArray) jObject.get("DESTDEPARTMENT");
        JSONArray specode = (JSONArray) jObject.get("SPECODE");
        JSONArray cyphCode = (JSONArray) jObject.get("CYPHCODE");
        JSONArray ficheNo = (JSONArray) jObject.get("FICHENO");
        JSONArray custTitle = (JSONArray) jObject.get("CUSTTITLE");
        JSONArray lineExp = (JSONArray) jObject.get("LINEEXP");
        JSONArray amount = (JSONArray) jObject.get("AMOUNT");
        JSONArray reportRate = (JSONArray) jObject.get("REPORTRATE");
        JSONArray reportNet = (JSONArray) jObject.get("REPORTNET");
        JSONArray trRate = (JSONArray) jObject.get("TRRATE");
        JSONArray trNet = (JSONArray) jObject.get("TRNET");
        JSONArray trCurr = (JSONArray) jObject.get("TRCURR");
        JSONArray sign = (JSONArray) jObject.get("SIGN");
        JSONArray accounted = (JSONArray) jObject.get("ACCOUNTED");
        JSONArray cancelled = (JSONArray) jObject.get("CANCELLED");
        JSONArray accFicheRef = (JSONArray) jObject.get("ACCFICHEREF");
        JSONArray printCnt = (JSONArray) jObject.get("PRINTCNT");
        JSONArray capiblockCreatedby = (JSONArray) jObject.get("CAPIBLOCK_CREATEDBY");
        JSONArray capiblockCreadeddate = (JSONArray) jObject.get("CAPIBLOCK_CREADEDDATE");
        JSONArray capiblockCreatedhour = (JSONArray) jObject.get("CAPIBLOCK_CREATEDHOUR");
        JSONArray capiblockCreatedmin = (JSONArray) jObject.get("CAPIBLOCK_CREATEDMIN");
        JSONArray capiblockCreatedsec = (JSONArray) jObject.get("CAPIBLOCK_CREATEDSEC");
        JSONArray capiblockModifiedby = (JSONArray) jObject.get("CAPIBLOCK_MODIFIEDBY");
        JSONArray capiblockModifieddate = (JSONArray) jObject.get("CAPIBLOCK_MODIFIEDDATE");
        JSONArray capiblockModifiedhour = (JSONArray) jObject.get("CAPIBLOCK_MODIFIEDHOUR");
        JSONArray capiblockModifiedmin = (JSONArray) jObject.get("CAPIBLOCK_MODIFIEDMIN");
        JSONArray capiblockModifiedsec = (JSONArray) jObject.get("CAPIBLOCK_MODIFIEDSEC");
        JSONArray cancelledAcc = (JSONArray) jObject.get("CANCELLEDACC");
        JSONArray genexcTyp = (JSONArray) jObject.get("GENEXCTYP");
        JSONArray lineExcTyp = (JSONArray) jObject.get("LINEEXCTYP");
        JSONArray tradingGrp = (JSONArray) jObject.get("TRADINGGRP");
        JSONArray textInc = (JSONArray) jObject.get("TEXTINC");
        JSONArray siteId = (JSONArray) jObject.get("SITEID");
        JSONArray recStatus = (JSONArray) jObject.get("RECSTATUS");
        JSONArray orgLogicRef = (JSONArray) jObject.get("ORGLOGICREF");
        JSONArray wfStatus = (JSONArray) jObject.get("WFSTATUS");
        JSONArray gpopType = (JSONArray) jObject.get("GPOPTYPE");
        JSONArray gpincometacrat = (JSONArray) jObject.get("GPINCOMETACRAT");
        JSONArray gpFundShareRat = (JSONArray) jObject.get("GPFUNDSHARERAT");
        JSONArray gpPlate = (JSONArray) jObject.get("GPPLATE");
        JSONArray gpTaxAcc = (JSONArray) jObject.get("GPTAXACC");
        JSONArray gpFundAcc = (JSONArray) jObject.get("GPFUNDACC");
        JSONArray gpAddr = (JSONArray) jObject.get("GPADDR");
        JSONArray smmVatRate = (JSONArray) jObject.get("SMMVATRATE");
        JSONArray smmVatAcRef = (JSONArray) jObject.get("SMMVATACREF");
        JSONArray smmVatCenterRef = (JSONArray) jObject.get("SMMVATCENTREF");
        JSONArray smmDoCode = (JSONArray) jObject.get("SMMDOCODE");
        JSONArray infidx = (JSONArray) jObject.get("INFIDX");
        JSONArray tranGrpNo = (JSONArray) jObject.get("TRANGRPNO");
        JSONArray tranGrpLineNo = (JSONArray) jObject.get("TRANGRPLINENO");
        JSONArray orgLogoId = (JSONArray) jObject.get("ORGLOGOID");
        JSONArray vatInc = (JSONArray) jObject.get("VATINC");
        JSONArray vatRate = (JSONArray) jObject.get("VATRAT");
        JSONArray vatAccRef = (JSONArray) jObject.get("VATACCREF");
        JSONArray vatTot = (JSONArray) jObject.get("VATTOT");
        JSONArray projectRef = (JSONArray) jObject.get("PROJECTREF");
        JSONArray csTransRef = (JSONArray) jObject.get("CSTRANSREF");
        JSONArray status = (JSONArray) jObject.get("STATUS");
        JSONArray wFlowCrdRef = (JSONArray) jObject.get("WFLOWCRDREF");
        JSONArray affectCollatRl = (JSONArray) jObject.get("AFFECTCOLLATRL");
        JSONArray grpFirmTrans = (JSONArray) jObject.get("GRPFIRMTRANS");
        JSONArray tranNo = (JSONArray) jObject.get("TRANNO");
        JSONArray doCode = (JSONArray) jObject.get("DOCODE");
        JSONArray affectRisk = (JSONArray) jObject.get("AFFECTRISK");

        for (int i = 0; i < logicalRef.size(); i++) {
            items = new KsLines(
                        Integer.parseInt(logicalRef.get(i).toString()), Integer.parseInt(cardRef.get(i).toString()),
                        Integer.parseInt(vCardRef.get(i).toString()), Integer.parseInt(transRef.get(i).toString()), 
                        Integer.parseInt(accRef.get(i).toString()), Integer.parseInt(centerRef.get(i).toString()),
                        Integer.parseInt(csAccRef.get(i).toString()), Integer.parseInt(csCenterRef.get(i).toString()),
                        date.get(i).toString(), Short.parseShort(hour.get(i).toString()), 
                        Short.parseShort(minute.get(i).toString()), Short.parseShort(trCode.get(i).toString()), 
                        Short.parseShort(branch.get(i).toString()), Short.parseShort(department.get(i).toString()), 
                        Short.parseShort(destBranch.get(i).toString()), Short.parseShort(destDepartment.get(i).toString()), 
                        specode.get(i).toString(), cyphCode.get(i).toString(), 
                        ficheNo.get(i).toString(), custTitle.get(i).toString(), 
                        lineExp.get(i).toString(), Float.parseFloat(amount.get(i).toString()), 
                        Float.parseFloat(reportRate.get(i).toString()), Float.parseFloat(reportNet.get(i).toString()), 
                        Float.parseFloat(trRate.get(i).toString()), Float.parseFloat(trNet.get(i).toString()), 
                        Short.parseShort(trCurr.get(i).toString()), Short.parseShort(sign.get(i).toString()), 
                        Short.parseShort(accounted.get(i).toString()), Short.parseShort(cancelled.get(i).toString()), 
                        Integer.parseInt(accFicheRef.get(i).toString()), Short.parseShort(printCnt.get(i).toString()), 
                        Short.parseShort(capiblockCreatedby.get(i).toString()), capiblockCreadeddate.get(i).toString(), 
                        Short.parseShort(capiblockCreatedhour.get(i).toString()), Short.parseShort(capiblockCreatedmin.get(i).toString()), 
                        Short.parseShort(capiblockCreatedsec.get(i).toString()), Short.parseShort(capiblockModifiedby.get(i).toString()), 
                        capiblockModifieddate.get(i).toString(), Short.parseShort(capiblockModifiedhour.get(i).toString()), 
                        Short.parseShort(capiblockModifiedmin.get(i).toString()), Short.parseShort(capiblockModifiedsec.get(i).toString()), 
                        Short.parseShort(cancelledAcc.get(i).toString()), Short.parseShort(genexcTyp.get(i).toString()), 
                        Short.parseShort(lineExcTyp.get(i).toString()), tradingGrp.get(i).toString(), 
                        Short.parseShort(textInc.get(i).toString()), Short.parseShort(siteId.get(i).toString()), 
                        Short.parseShort(recStatus.get(i).toString()), Integer.parseInt(orgLogicRef.get(i).toString()), 
                        Integer.parseInt(wfStatus.get(i).toString()), Short.parseShort(gpopType.get(i).toString()), 
                        Float.parseFloat(gpincometacrat.get(i).toString()), Float.parseFloat(gpFundShareRat.get(i).toString()), 
                        gpPlate.get(i).toString(), Integer.parseInt(gpTaxAcc.get(i).toString()), 
                        Integer.parseInt(gpFundAcc.get(i).toString()), gpAddr.get(i).toString(), 
                        Float.parseFloat(smmVatRate.get(i).toString()), Integer.parseInt(smmVatAcRef.get(i).toString()), 
                        Integer.parseInt(smmVatCenterRef.get(i).toString()), smmDoCode.get(i).toString(), 
                        Float.parseFloat(infidx.get(i).toString()), tranGrpNo.get(i).toString(), 
                        Short.parseShort(tranGrpLineNo.get(i).toString()), orgLogoId.get(i).toString(), 
                        Short.parseShort(vatInc.get(i).toString()), Float.parseFloat(vatRate.get(i).toString()), 
                        Integer.parseInt(vatAccRef.get(i).toString()), Float.parseFloat(vatTot.get(i).toString()), 
                        Integer.parseInt(projectRef.get(i).toString()), Integer.parseInt(csTransRef.get(i).toString()), 
    /*     ISONR    */  Short.parseShort(status.get(i).toString()), Integer.parseInt(wFlowCrdRef.get(i).toString()), 
                        Short.parseShort(affectCollatRl.get(i).toString()), Short.parseShort(grpFirmTrans.get(i).toString()), 
                        tranNo.get(i).toString(), doCode.get(i).toString(), 
                        Short.parseShort(affectRisk.get(i).toString()));

            tableValue.add(items);
        }
        return tableValue;
    }
    
    public ArrayList<PayTrans> getPayTransArrayList(JSONObject jObject) {

        ArrayList<PayTrans> tableValue = new ArrayList<>();
        PayTrans card;

        JSONArray logicalRef = (JSONArray) jObject.get("LOGICALREF");
        JSONArray cardRef = (JSONArray) jObject.get("CARDREF");
        JSONArray date = (JSONArray) jObject.get("DATE_");
        JSONArray moduleNr = (JSONArray) jObject.get("MODULENR");
        JSONArray sign = (JSONArray) jObject.get("SIGN");
        JSONArray ficheRef = (JSONArray) jObject.get("FICHEREF");
        JSONArray ficheLineRef = (JSONArray) jObject.get("FICHELINEREF");
        JSONArray trCode = (JSONArray) jObject.get("TRCODE");
        JSONArray total = (JSONArray) jObject.get("TOTAL");
        JSONArray paid = (JSONArray) jObject.get("PAID");
        JSONArray earlyIntRate = (JSONArray) jObject.get("EARLYINTRATE");
        JSONArray latelyIntRate = (JSONArray) jObject.get("LATELYINTRATE");
        JSONArray crossRef = (JSONArray) jObject.get("CROSSREF");
        JSONArray paidInCash = (JSONArray) jObject.get("PAIDINCASH");
        JSONArray cancelled = (JSONArray) jObject.get("CANCELLED");
        JSONArray procDate = (JSONArray) jObject.get("PROCDATE");
        JSONArray trCurr = (JSONArray) jObject.get("TRCURR");
        JSONArray trRate = (JSONArray) jObject.get("TRRATE");
        JSONArray reportRate = (JSONArray) jObject.get("REPORTRATE");
        JSONArray modified = (JSONArray) jObject.get("MODIFIED");
        JSONArray remindLev = (JSONArray) jObject.get("REMINDLEV");
        JSONArray remindSent = (JSONArray) jObject.get("REMINDSENT");
        JSONArray crossCurr = (JSONArray) jObject.get("CROSSCURR");
        JSONArray crossTotal = (JSONArray) jObject.get("CROSSTOTAL");
        JSONArray discflag = (JSONArray) jObject.get("DISCFLAG");
        JSONArray siteId = (JSONArray) jObject.get("SITEID");
        JSONArray orgLogicRef = (JSONArray) jObject.get("ORGLOGICREF");
        JSONArray wfStatus = (JSONArray) jObject.get("WFSTATUS");
        JSONArray closingRate = (JSONArray) jObject.get("CLOSINGRATE");
        JSONArray discDueDate = (JSONArray) jObject.get("DISCDUEDATE");
        JSONArray opStat = (JSONArray) jObject.get("OPSTAT");
        JSONArray recStatus = (JSONArray) jObject.get("RECSTATUS");
        JSONArray infidx = (JSONArray) jObject.get("INFIDX");
        JSONArray payNo = (JSONArray) jObject.get("PAYNO");
        JSONArray delayTotal = (JSONArray) jObject.get("DELAYTOTAL");
        JSONArray lastSendRemLev = (JSONArray) jObject.get("LASTSENDREMLEV");
        JSONArray pointTrans = (JSONArray) jObject.get("POINTTRANS");
        JSONArray bankPayDate = (JSONArray) jObject.get("BANKPAYDATE");
        JSONArray poscomsn = (JSONArray) jObject.get("POSCOMSN");
        JSONArray pointcomsn = (JSONArray) jObject.get("POINTCOMSN");
        JSONArray bankAccRef = (JSONArray) jObject.get("BANKACCREF");
        JSONArray paymentType = (JSONArray) jObject.get("PAYMENTTYPE");
        JSONArray cashAccRef = (JSONArray) jObject.get("CASHACCREF");
        JSONArray trNet = (JSONArray) jObject.get("TRNET");
        JSONArray repayPlanRef = (JSONArray) jObject.get("REPAYPLANREF");
        JSONArray dueDiffComsn = (JSONArray) jObject.get("DUEDIFFCOMSN");
        JSONArray calcType = (JSONArray) jObject.get("CALCTYPE");
        JSONArray netTotal = (JSONArray) jObject.get("NETTOTAL");
        JSONArray repyPlnApplied = (JSONArray) jObject.get("REPYPLNAPPLIED");
        JSONArray payTrCurr = (JSONArray) jObject.get("PAYTRCURR");
        JSONArray payTrRate = (JSONArray) jObject.get("PAYTRRATE");
        JSONArray payTrNet = (JSONArray) jObject.get("PAYTRNET");
        JSONArray bnTrCreated = (JSONArray) jObject.get("BNTRCREATED");
        JSONArray bnFchRef = (JSONArray) jObject.get("BNFCHREF");
        JSONArray bnFlnRef = (JSONArray) jObject.get("BNFLNREF");
        JSONArray installType = (JSONArray) jObject.get("INSTALTYPE");
        JSONArray instalRef = (JSONArray) jObject.get("INSTALREF");
        JSONArray mainInstalRef = (JSONArray) jObject.get("MAININSTALREF");

        for (int i = 0; i < logicalRef.size(); i++) {
            card = new PayTrans(
                    Integer.parseInt(logicalRef.get(i).toString()), Integer.parseInt(cardRef.get(i).toString()),
                    date.get(i).toString(), Short.parseShort(moduleNr.get(i).toString()),
                    Short.parseShort(sign.get(i).toString()), Integer.parseInt(ficheRef.get(i).toString()),
                    Integer.parseInt(ficheLineRef.get(i).toString()), Short.parseShort(trCode.get(i).toString()),
                    Float.parseFloat(total.get(i).toString()), Float.parseFloat(paid.get(i).toString()),
                    Float.parseFloat(earlyIntRate.get(i).toString()), Float.parseFloat(latelyIntRate.get(i).toString()),
                    Integer.parseInt(crossRef.get(i).toString()), Short.parseShort(paidInCash.get(i).toString()),
                    Short.parseShort(cancelled.get(i).toString()), procDate.get(i).toString(),
                    Short.parseShort(trCurr.get(i).toString()), Float.parseFloat(trRate.get(i).toString()),
                    Float.parseFloat(reportRate.get(i).toString()), Short.parseShort(modified.get(i).toString()),
                    Short.parseShort(remindLev.get(i).toString()), Short.parseShort(remindSent.get(i).toString()),
                    Short.parseShort(crossCurr.get(i).toString()), Float.parseFloat(crossTotal.get(i).toString()),
                    Short.parseShort(discflag.get(i).toString()), Short.parseShort(siteId.get(i).toString()),
                    Integer.parseInt(orgLogicRef.get(i).toString()), Integer.parseInt(wfStatus.get(i).toString()),
                    Float.parseFloat(closingRate.get(i).toString()), discDueDate.get(i).toString(),
                    Short.parseShort(opStat.get(i).toString()), Short.parseShort(recStatus.get(i).toString()),
                    Float.parseFloat(infidx.get(i).toString()), Short.parseShort(payNo.get(i).toString()),
                    Float.parseFloat(delayTotal.get(i).toString()), Integer.parseInt(lastSendRemLev.get(i).toString()),
                    Short.parseShort(pointTrans.get(i).toString()), bankPayDate.get(i).toString(),
                    Float.parseFloat(poscomsn.get(i).toString()), Float.parseFloat(pointcomsn.get(i).toString()),
                    Integer.parseInt(bankAccRef.get(i).toString()), Short.parseShort(paymentType.get(i).toString()),
                    Integer.parseInt(cashAccRef.get(i).toString()), Float.parseFloat(trNet.get(i).toString()),
                    Integer.parseInt(repayPlanRef.get(i).toString()), Float.parseFloat(dueDiffComsn.get(i).toString()),
                    Short.parseShort(calcType.get(i).toString()), Float.parseFloat(netTotal.get(i).toString()),
                    Short.parseShort(repyPlnApplied.get(i).toString()), Short.parseShort(payTrCurr.get(i).toString()),
                    Float.parseFloat(payTrRate.get(i).toString()), Float.parseFloat(payTrNet.get(i).toString()),
                    Short.parseShort(bnTrCreated.get(i).toString()), Integer.parseInt(bnFchRef.get(i).toString()),
                    Integer.parseInt(bnFlnRef.get(i).toString()), Short.parseShort(installType.get(i).toString()),
                    Integer.parseInt(instalRef.get(i).toString()), Integer.parseInt(mainInstalRef.get(i).toString()));

            tableValue.add(card);
        }
        return tableValue;
    }
}
