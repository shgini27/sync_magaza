/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saptaslama.controller;

import com.saptaslama.controller.receiver.BnCardReceiver;
import com.saptaslama.controller.receiver.BnFicheReceiver;
import com.saptaslama.controller.receiver.ClCardReceiver;
import com.saptaslama.controller.receiver.InvoiceReceiver;
import com.saptaslama.controller.receiver.ItemsReceiver;
import com.saptaslama.controller.receiver.KsCardReceiver;
import com.saptaslama.controller.receiver.KsLinesReceiver;
import com.saptaslama.controller.receiver.SlsManReceiver;
import com.saptaslama.controller.receiver.SrvCardReceiver;
import com.saptaslama.controller.receiver.StFicheReceiver;
import com.saptaslama.controller.receiver.UnitReceiver;
import com.saptaslama.controller.sync.SyncBankCard;
import com.saptaslama.controller.sync.SyncBnFiche;
import com.saptaslama.controller.sync.SyncClCard;
import com.saptaslama.controller.sync.SyncInvoice;
import com.saptaslama.controller.sync.SyncItems;
import com.saptaslama.controller.sync.SyncKsCard;
import com.saptaslama.controller.sync.SyncKsLine;
import com.saptaslama.controller.sync.SyncSlsMan;
import com.saptaslama.controller.sync.SyncSrvCard;
import com.saptaslama.controller.sync.SyncStFiche;
import com.saptaslama.controller.sync.SyncUnit;
import com.saptaslama.view.SyncMagazaView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Shagy
 */
public class SyncDataManual {

    private UnitReceiver unitReceiver;
    private SyncUnit syncUnit;

    private ItemsReceiver itemReceiver;
    private SyncItems syncItems;

    private SlsManReceiver slsReceiver;
    private SyncSlsMan syncSlsMan;

    private ClCardReceiver clCardReceiver;
    private SyncClCard syncClCard;

    private SrvCardReceiver srvCardReceiver;
    private SyncSrvCard syncSrvCard;

    private BnCardReceiver bnCardReceiver;
    private SyncBankCard syncBnCard;

    private KsCardReceiver ksCardReceiver;
    private SyncKsCard syncKsCard;

    private BnFicheReceiver bnFicheReceive;
    private SyncBnFiche syncBnFiche;

    private InvoiceReceiver invoiceReceive;
    private SyncInvoice syncInvoice;

    private KsLinesReceiver ksLineReceive;
    private SyncKsLine syncKsLine;

    private StFicheReceiver stFicheReceive;
    private SyncStFiche syncStFiche;

    public boolean syncData(int source) {
        Date d = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        System.err.println("TODAY: " + date.format(d.getTime()));

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        System.err.println("WEEK BEFORE: " + date.format(cal.getTime()));

        try {
            //Sync UNITSET
            unitReceiver = new UnitReceiver();
            syncUnit = new SyncUnit(unitReceiver);
            syncUnit.init();
            unitReceiver = null;
            syncUnit = null;

            //Sync ITEMS
            itemReceiver = new ItemsReceiver();
            syncItems = new SyncItems(itemReceiver);
            syncItems.init();
            syncItems = null;
            itemReceiver = null;

            //Sync SLSMAN
            slsReceiver = new SlsManReceiver();
            syncSlsMan = new SyncSlsMan(slsReceiver);
            syncSlsMan.init();
            slsReceiver = null;
            syncSlsMan = null;

            //Sync CLCARD
            clCardReceiver = new ClCardReceiver();
            syncClCard = new SyncClCard(clCardReceiver);
            syncClCard.init();
            clCardReceiver = null;
            syncClCard = null;

            //Sync SRVCARD
            srvCardReceiver = new SrvCardReceiver();
            syncSrvCard = new SyncSrvCard(srvCardReceiver);
            syncSrvCard.init();
            srvCardReceiver = null;
            syncSrvCard = null;

            //Sync BNCARD
            bnCardReceiver = new BnCardReceiver();
            syncBnCard = new SyncBankCard(bnCardReceiver);
            syncBnCard.init();
            bnCardReceiver = null;
            syncBnCard = null;

            //Sync KSCARD
            ksCardReceiver = new KsCardReceiver();
            syncKsCard = new SyncKsCard(ksCardReceiver);
            syncKsCard.init();
            ksCardReceiver = null;
            syncKsCard = null;

            //Sync BNFICHE
            bnFicheReceive = new BnFicheReceiver();
            syncBnFiche = new SyncBnFiche(bnFicheReceive);
            syncBnFiche.init();
            bnFicheReceive = null;
            syncBnFiche = null;

            //Sync INVOICE
            invoiceReceive = new InvoiceReceiver();
            syncInvoice = new SyncInvoice(invoiceReceive);
            syncInvoice.init();
            invoiceReceive = null;
            syncInvoice = null;

            //Sync KSLINES
            ksLineReceive = new KsLinesReceiver();
            syncKsLine = new SyncKsLine(ksLineReceive);
            syncKsLine.init();
            ksLineReceive = null;
            syncKsLine = null;

            //Sync STFICHE
            stFicheReceive = new StFicheReceiver();
            syncStFiche = new SyncStFiche(stFicheReceive);
            syncStFiche.init();
            stFicheReceive = null;
            syncStFiche = null;

            System.err.println("SYNC HAS BEEN DONE SUCCESSFULLY!");
            if (source == 1) {
                SyncMagazaView.setText("updated", 4);
            }

            return true;
        } catch (Exception e) {
            System.err.println("SYNC IS FAILED!" + e.getMessage());
            return false;
        }
    }
}
