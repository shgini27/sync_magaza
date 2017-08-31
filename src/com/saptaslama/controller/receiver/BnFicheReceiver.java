/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saptaslama.controller.receiver;

import com.saptaslama.controller.sender.Sender;
import com.saptaslama.model.BnFiche;
import com.saptaslama.model.BnfLine;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Shagy
 */
public class BnFicheReceiver extends Sender{
    
    public ArrayList<BnFiche> getBnFicheArrayList(JSONObject jObject) {

        ArrayList<BnFiche> tableValue = new ArrayList<>();
        BnFiche card;

        JSONArray logicalRef = (JSONArray) jObject.get("LOGICALREF");
        JSONArray date = (JSONArray) jObject.get("DATE_");
        JSONArray ficheNo = (JSONArray) jObject.get("FICHENO");
        JSONArray specode = (JSONArray) jObject.get("SPECODE");
        JSONArray cyphCode = (JSONArray) jObject.get("CYPHCODE");
        JSONArray branch = (JSONArray) jObject.get("BRANCH");
        JSONArray department = (JSONArray) jObject.get("DEPARMENT");
        JSONArray trCode = (JSONArray) jObject.get("TRCODE");
        JSONArray moduleNr = (JSONArray) jObject.get("MODULENR");
        JSONArray sourceRef = (JSONArray) jObject.get("SOURCEFREF");
        JSONArray accounted = (JSONArray) jObject.get("ACCOUNTED");
        JSONArray cancelled = (JSONArray) jObject.get("CANCELLED");
        JSONArray sign = (JSONArray) jObject.get("SIGN");
        JSONArray debitTot = (JSONArray) jObject.get("DEBITTOT");
        JSONArray creditTot = (JSONArray) jObject.get("CREDITTOT");
        JSONArray genexp1 = (JSONArray) jObject.get("GENEXP1");
        JSONArray genexp2 = (JSONArray) jObject.get("GENEXP2");
        JSONArray genexp3 = (JSONArray) jObject.get("GENEXP3");
        JSONArray genexp4 = (JSONArray) jObject.get("GENEXP4");
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
        JSONArray accFicheRef = (JSONArray) jObject.get("ACCFICHEREF");
        JSONArray genexcTyp = (JSONArray) jObject.get("GENEXCTYP");
        JSONArray lineExcTyp = (JSONArray) jObject.get("LINEEXCTYP");
        JSONArray siteId = (JSONArray) jObject.get("SITEID");
        JSONArray recStatus = (JSONArray) jObject.get("RECSTATUS");
        JSONArray orgLogicRef = (JSONArray) jObject.get("ORGLOGICREF");
        JSONArray repDebit = (JSONArray) jObject.get("REPDEBIT");
        JSONArray repCredit = (JSONArray) jObject.get("REPCREDIT");
        JSONArray textInc = (JSONArray) jObject.get("TEXTINC");
        JSONArray wfStatus = (JSONArray) jObject.get("WFSTATUS");
        JSONArray crCardWzd = (JSONArray) jObject.get("CRCARDWZD");
        JSONArray bnAccountRef = (JSONArray) jObject.get("BNACCOUNTREF");
        JSONArray tranGrpNo = (JSONArray) jObject.get("TRANGRPNO");
        JSONArray projectRef = (JSONArray) jObject.get("PROJECTREF");
        JSONArray collatRollRef = (JSONArray) jObject.get("COLLATROLLREF");
        JSONArray collatTrnRef = (JSONArray) jObject.get("COLLATTRNREF");
        JSONArray bnCrRef = (JSONArray) jObject.get("BNCRREF");

        for (int i = 0; i < logicalRef.size(); i++) {
            card = new BnFiche(
                    Integer.parseInt(logicalRef.get(i).toString()), date.get(i).toString(),
                    ficheNo.get(i).toString(), specode.get(i).toString(), 
                    cyphCode.get(i).toString(), Short.parseShort(branch.get(i).toString()), 
                    Short.parseShort(department.get(i).toString()), Short.parseShort(trCode.get(i).toString()), 
                    Short.parseShort(moduleNr.get(i).toString()), Integer.parseInt(sourceRef.get(i).toString()), 
                    Short.parseShort(accounted.get(i).toString()), Short.parseShort(cancelled.get(i).toString()),
                    Short.parseShort(sign.get(i).toString()), Float.parseFloat(debitTot.get(i).toString()),
                    Float.parseFloat(creditTot.get(i).toString()), genexp1.get(i).toString(),
                    genexp2.get(i).toString(), genexp3.get(i).toString(), 
                    genexp4.get(i).toString(), Short.parseShort(printCnt.get(i).toString()),
                    Short.parseShort(capiblockCreatedby.get(i).toString()), capiblockCreadeddate.get(i).toString(),
                    Short.parseShort(capiblockCreatedhour.get(i).toString()), Short.parseShort(capiblockCreatedmin.get(i).toString()),
                    Short.parseShort(capiblockCreatedsec.get(i).toString()), Short.parseShort(capiblockModifiedby.get(i).toString()),
                    capiblockModifieddate.get(i).toString(), Short.parseShort(capiblockModifiedhour.get(i).toString()), 
                    Short.parseShort(capiblockModifiedmin.get(i).toString()), Short.parseShort(capiblockModifiedsec.get(i).toString()),
                    Short.parseShort(cancelledAcc.get(i).toString()), Integer.parseInt(accFicheRef.get(i).toString()), 
                    Short.parseShort(genexcTyp.get(i).toString()),
                    Short.parseShort(lineExcTyp.get(i).toString()), Short.parseShort(siteId.get(i).toString()),
                    Short.parseShort(recStatus.get(i).toString()), Integer.parseInt(orgLogicRef.get(i).toString()),
                    Float.parseFloat(repDebit.get(i).toString()), Float.parseFloat(repCredit.get(i).toString()),
                    Short.parseShort(textInc.get(i).toString()), Integer.parseInt(wfStatus.get(i).toString()), 
                    Short.parseShort(crCardWzd.get(i).toString()), Integer.parseInt(bnAccountRef.get(i).toString()), 
                    tranGrpNo.get(i).toString(), Integer.parseInt(projectRef.get(i).toString()), 
                    Integer.parseInt(collatRollRef.get(i).toString()), Integer.parseInt(collatTrnRef.get(i).toString()), 
                    Integer.parseInt(bnCrRef.get(i).toString()));

            tableValue.add(card);
        }
        return tableValue;
    }
    
    public ArrayList<BnfLine> getBnfLineArrayList(JSONObject jObject) {

        ArrayList<BnfLine> tableValue = new ArrayList<>();
        BnfLine items;

        JSONArray logicalRef = (JSONArray) jObject.get("LOGICALREF");
        JSONArray bankRef = (JSONArray) jObject.get("BANKREF");
        JSONArray bnAccRef = (JSONArray) jObject.get("BNACCREF");
        JSONArray clientRef = (JSONArray) jObject.get("CLIENTREF");
        JSONArray accountRef = (JSONArray) jObject.get("ACCOUNTREF");
        JSONArray centerRef = (JSONArray) jObject.get("CENTERREF");
        JSONArray bnAccountRef = (JSONArray) jObject.get("BNACCOUNTREF");
        JSONArray bnCenterRef = (JSONArray) jObject.get("BNCENTERREF");
        JSONArray virmanRef = (JSONArray) jObject.get("VIRMANREF");
        JSONArray sourceRef = (JSONArray) jObject.get("SOURCEFREF");
        JSONArray transType = (JSONArray) jObject.get("TRANSTYPE");
        JSONArray date = (JSONArray) jObject.get("DATE_");
        JSONArray department = (JSONArray) jObject.get("DEPARTMENT");
        JSONArray branch = (JSONArray) jObject.get("BRANCH");
        JSONArray sign = (JSONArray) jObject.get("SIGN");
        JSONArray trCode = (JSONArray) jObject.get("TRCODE");
        JSONArray moduleNr = (JSONArray) jObject.get("MODULENR");
        JSONArray lineNr = (JSONArray) jObject.get("LINENR");
        JSONArray specode = (JSONArray) jObject.get("SPECODE");
        JSONArray cyphCode = (JSONArray) jObject.get("CYPHCODE");
        JSONArray tranNo = (JSONArray) jObject.get("TRANNO");
        JSONArray doCode = (JSONArray) jObject.get("DOCODE");
        JSONArray lineExp = (JSONArray) jObject.get("LINEEXP");
        JSONArray accounted = (JSONArray) jObject.get("ACCOUNTED");
        JSONArray trCurr = (JSONArray) jObject.get("TRCURR");
        JSONArray amount = (JSONArray) jObject.get("AMOUNT");
        JSONArray trRate = (JSONArray) jObject.get("TRRATE");
        JSONArray trNet = (JSONArray) jObject.get("TRNET");
        JSONArray reportRate = (JSONArray) jObject.get("REPORTRATE");
        JSONArray reportNet = (JSONArray) jObject.get("REPORTNET");
        JSONArray extenRef = (JSONArray) jObject.get("EXTENREF");
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
        JSONArray cancelled = (JSONArray) jObject.get("CANCELLED");
        JSONArray clBnBranchNo = (JSONArray) jObject.get("CLBNBRANCHNO");
        JSONArray clBnAccountNo = (JSONArray) jObject.get("CLBNACCOUNTNO");
        JSONArray bnTrackingNo = (JSONArray) jObject.get("BNTRACKINGNO");
        JSONArray trnState = (JSONArray) jObject.get("TRNSTATE");
        JSONArray tradingGrp = (JSONArray) jObject.get("TRADINGGRP");
        JSONArray lineExcTyp = (JSONArray) jObject.get("LINEEXCTYP");
        JSONArray discFlag = (JSONArray) jObject.get("DISCFLAG");
        JSONArray discRate = (JSONArray) jObject.get("DISCRATE");
        JSONArray vatRate = (JSONArray) jObject.get("VATRATE");
        JSONArray arCloseAmount = (JSONArray) jObject.get("ARCLOSEAMOUNT");
        JSONArray discAccRef = (JSONArray) jObject.get("DISCACCREF");
        JSONArray discCentRef = (JSONArray) jObject.get("DISCCENREF");
        JSONArray vatrAccRef = (JSONArray) jObject.get("VATRACCREF");
        JSONArray vatrCentRef = (JSONArray) jObject.get("VATRCENREF");
        JSONArray paymentRef = (JSONArray) jObject.get("PAYMENTREF");
        JSONArray bankProcType = (JSONArray) jObject.get("BANKPROCTYPE");
        JSONArray bankProcCode = (JSONArray) jObject.get("BANKPROCCODE");
        JSONArray transDueDate = (JSONArray) jObject.get("TRANSDUEDATE");
        JSONArray siteId = (JSONArray) jObject.get("SITEID");
        JSONArray recStatus = (JSONArray) jObject.get("RECSTATUS");
        JSONArray orgLogicRef = (JSONArray) jObject.get("ORGLOGICREF");
        JSONArray opstat = (JSONArray) jObject.get("OPSTAT");
        JSONArray infdix = (JSONArray) jObject.get("INFIDX");
        JSONArray eximFicheNo = (JSONArray) jObject.get("EXIMFICHENO");
        JSONArray bnTranVatInc = (JSONArray) jObject.get("BNTRANVATINC");
        JSONArray bnTranVatRat = (JSONArray) jObject.get("BNTRANVATRAT");
        JSONArray bnTranVatAccRef = (JSONArray) jObject.get("BNTRANVATACCREF");
        JSONArray bnTranVatCenRef = (JSONArray) jObject.get("BNTRANVATCENREF");
        JSONArray bnTranVatTot = (JSONArray) jObject.get("BNTRANVATTOT");
        JSONArray cheqInfo = (JSONArray) jObject.get("CHEQINFO");
        JSONArray eximInfoRef = (JSONArray) jObject.get("EXIMINFOREF");
        JSONArray eximInfoPar = (JSONArray) jObject.get("EXIMINFOPAR");
        JSONArray excreRef = (JSONArray) jObject.get("EXCREREF");
        JSONArray crCardWzd = (JSONArray) jObject.get("CRCARDWZD");
        JSONArray comsType = (JSONArray) jObject.get("COMSTYPE");
        JSONArray proVisionRef = (JSONArray) jObject.get("PROVISIONREF");
        JSONArray tranGrpLineNo = (JSONArray) jObject.get("TRANGRPLINENO");
        JSONArray projectRef = (JSONArray) jObject.get("PROJECTREF");
        JSONArray tranGrpDate = (JSONArray) jObject.get("TRANGRPDATE");
        JSONArray tranGrpNo = (JSONArray) jObject.get("TRANGRPNO");
        JSONArray bankRefNr = (JSONArray) jObject.get("BANKREFNR");
        JSONArray customDocNr = (JSONArray) jObject.get("CUSTOMDOCNR");
        JSONArray dabLnRef = (JSONArray) jObject.get("DABLNREF");
        JSONArray transRef = (JSONArray) jObject.get("TRANSREF");
        JSONArray affectCollatRl = (JSONArray) jObject.get("AFFECTCOLLATRL");
        JSONArray collatRollRef = (JSONArray) jObject.get("COLLATROLLREF");
        JSONArray collatTrnRef = (JSONArray) jObject.get("COLLATTRNREF");
        JSONArray collatCardRef = (JSONArray) jObject.get("COLLATCARDREF");
        JSONArray grpFirmTrans = (JSONArray) jObject.get("GRPFIRMTRANS");
        JSONArray affectRisk = (JSONArray) jObject.get("AFFECTRISK");
        JSONArray bnCrSource = (JSONArray) jObject.get("BNCRSOURCE");
        JSONArray bnCrRef = (JSONArray) jObject.get("BNCRREF");
        JSONArray bnCrLnType = (JSONArray) jObject.get("BNCRLNTYPE");

        for (int i = 0; i < logicalRef.size(); i++) {
            items = new BnfLine(
                        Integer.parseInt(logicalRef.get(i).toString()), Integer.parseInt(bankRef.get(i).toString()),
                        Integer.parseInt(bnAccRef.get(i).toString()), Integer.parseInt(clientRef.get(i).toString()), 
                        Integer.parseInt(accountRef.get(i).toString()), Integer.parseInt(centerRef.get(i).toString()),
                        Integer.parseInt(bnAccountRef.get(i).toString()), Integer.parseInt(bnCenterRef.get(i).toString()),
                        Integer.parseInt(virmanRef.get(i).toString()), Integer.parseInt(sourceRef.get(i).toString()), 
                        Short.parseShort(transType.get(i).toString()), date.get(i).toString(), 
                        Short.parseShort(department.get(i).toString()), Short.parseShort(branch.get(i).toString()), 
                        Short.parseShort(sign.get(i).toString()), Short.parseShort(trCode.get(i).toString()), 
                        Short.parseShort(moduleNr.get(i).toString()), Short.parseShort(lineNr.get(i).toString()), 
                        specode.get(i).toString(), cyphCode.get(i).toString(), 
                        tranNo.get(i).toString(), doCode.get(i).toString(), 
                        lineExp.get(i).toString(), Short.parseShort(accounted.get(i).toString()), 
                        Short.parseShort(trCurr.get(i).toString()), Float.parseFloat(amount.get(i).toString()), 
                        Float.parseFloat(trRate.get(i).toString()), Float.parseFloat(trNet.get(i).toString()), 
                        Float.parseFloat(reportRate.get(i).toString()), Float.parseFloat(reportNet.get(i).toString()), 
                        Integer.parseInt(extenRef.get(i).toString()), Integer.parseInt(accFicheRef.get(i).toString()), 
                        Short.parseShort(printCnt.get(i).toString()), Short.parseShort(capiblockCreatedby.get(i).toString()), 
                        capiblockCreadeddate.get(i).toString(), Short.parseShort(capiblockCreatedhour.get(i).toString()), 
                        Short.parseShort(capiblockCreatedmin.get(i).toString()), Short.parseShort(capiblockCreatedsec.get(i).toString()), 
                        Short.parseShort(capiblockModifiedby.get(i).toString()), capiblockModifieddate.get(i).toString(), 
                        Short.parseShort(capiblockModifiedhour.get(i).toString()), Short.parseShort(capiblockModifiedmin.get(i).toString()), 
                        Short.parseShort(capiblockModifiedsec.get(i).toString()), Short.parseShort(cancelled.get(i).toString()), 
                        clBnBranchNo.get(i).toString(), clBnAccountNo.get(i).toString(), 
                        bnTrackingNo.get(i).toString(), Short.parseShort(trnState.get(i).toString()), 
                        tradingGrp.get(i).toString(), Short.parseShort(lineExcTyp.get(i).toString()), 
                        Short.parseShort(discFlag.get(i).toString()), Float.parseFloat(discRate.get(i).toString()), 
                        Float.parseFloat(vatRate.get(i).toString()), Float.parseFloat(arCloseAmount.get(i).toString()), 
                        Integer.parseInt(discAccRef.get(i).toString()), Integer.parseInt(discCentRef.get(i).toString()), 
                        Integer.parseInt(vatrAccRef.get(i).toString()), Integer.parseInt(vatrCentRef.get(i).toString()), 
                        Integer.parseInt(paymentRef.get(i).toString()), Short.parseShort(bankProcType.get(i).toString()), 
                        Short.parseShort(bankProcCode.get(i).toString()), transDueDate.get(i).toString(), 
                        Short.parseShort(siteId.get(i).toString()), Short.parseShort(recStatus.get(i).toString()), 
                        Integer.parseInt(orgLogicRef.get(i).toString()), Short.parseShort(opstat.get(i).toString()), 
                        Float.parseFloat(infdix.get(i).toString()), eximFicheNo.get(i).toString(), 
                        Short.parseShort(bnTranVatInc.get(i).toString()), Float.parseFloat(bnTranVatRat.get(i).toString()), 
                        Integer.parseInt(bnTranVatAccRef.get(i).toString()), Integer.parseInt(bnTranVatCenRef.get(i).toString()), 
                        Float.parseFloat(bnTranVatTot.get(i).toString()), cheqInfo.get(i).toString(), 
                        Integer.parseInt(eximInfoRef.get(i).toString()), Float.parseFloat(eximInfoPar.get(i).toString()), 
                        Integer.parseInt(excreRef.get(i).toString()), Short.parseShort(crCardWzd.get(i).toString()), 
                        Short.parseShort(comsType.get(i).toString()), Integer.parseInt(proVisionRef.get(i).toString()), 
                        Short.parseShort(tranGrpLineNo.get(i).toString()), Integer.parseInt(projectRef.get(i).toString()), 
                        tranGrpDate.get(i).toString(), tranGrpNo.get(i).toString(), 
                        bankRefNr.get(i).toString(), customDocNr.get(i).toString(), 
                        Integer.parseInt(dabLnRef.get(i).toString()), Integer.parseInt(transRef.get(i).toString()), 
                        Short.parseShort(affectCollatRl.get(i).toString()), Integer.parseInt(collatRollRef.get(i).toString()), 
                        Integer.parseInt(collatTrnRef.get(i).toString()), Integer.parseInt(collatCardRef.get(i).toString()), 
                        Short.parseShort(grpFirmTrans.get(i).toString()), Short.parseShort(affectRisk.get(i).toString()), 
                        Short.parseShort(bnCrSource.get(i).toString()), Integer.parseInt(bnCrRef.get(i).toString()), 
                        Short.parseShort(bnCrLnType.get(i).toString()));

            tableValue.add(items);
        }
        return tableValue;
    }
}
