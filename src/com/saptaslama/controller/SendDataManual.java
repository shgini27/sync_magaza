/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saptaslama.controller;

import com.saptaslama.controller.sender.cards.BnCardSender;
import com.saptaslama.controller.sender.cards.ClCardSender;
import com.saptaslama.controller.sender.cards.ItemSender;
import com.saptaslama.controller.sender.cards.KsCardSender;
import com.saptaslama.controller.sender.cards.SlsManSender;
import com.saptaslama.controller.sender.cards.SrvCardSender;
import com.saptaslama.controller.sender.cards.UnitSender;
import com.saptaslama.controller.sender.sales.BnFicheSender;
import com.saptaslama.controller.sender.sales.ClFicheSender;
import com.saptaslama.controller.sender.sales.InvoiceSender;
import com.saptaslama.controller.sender.sales.KsLineSender;
import com.saptaslama.controller.sender.sales.StFicheSender;
import com.saptaslama.view.SyncMagazaView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Shagy
 */
public class SendDataManual {

    //send UNITSET
    private UnitSender unitSend;

    //send ITEMS
    private ItemSender itemSend;

    //send SLSMAN
    private SlsManSender slssend;

    //send CLCARD
    private ClCardSender clSend;

    //send SRVCARD
    private SrvCardSender srvSend;

    //Send BankCard
    private BnCardSender bnSend;

    //send KSCARD
    private KsCardSender ksSend;

    //send BNFICHE
    private BnFicheSender bnFicheSend;

    //send CLFICHE
    private ClFicheSender clFicheSend;

    //send INVOICE
    private InvoiceSender invoiceSend;

    //send KSLINES
    private KsLineSender ksLineSend;

    //send STFICHE
    private StFicheSender stFicheSend;

    public boolean sendData(int source) {
        Date d = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        System.err.println("TODAY: " + date.format(d.getTime()));

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        System.err.println("WEEK BEFORE: " + date.format(cal.getTime()));

        try {
            //send UNITSET
            unitSend = new UnitSender();
            unitSend.init();
            unitSend = null;

            //send ITEMS
            itemSend = new ItemSender();
            itemSend.init();
            itemSend = null;

            //send SLSMAN
            slssend = new SlsManSender();
            slssend.init();
            slssend = null;

            //send CLCARD
            clSend = new ClCardSender();
            clSend.init();
            clSend = null;

            //send SRVCARD
            srvSend = new SrvCardSender();
            srvSend.init();
            srvSend = null;

            //Send BankCard
            bnSend = new BnCardSender();
            bnSend.init();
            bnSend = null;

            //send KSCARD
            ksSend = new KsCardSender();
            ksSend.init();
            ksSend = null;

            //send BNFICHE
            bnFicheSend = new BnFicheSender();
            bnFicheSend.init();
            bnFicheSend = null;

            //send CLFICHE
            clFicheSend = new ClFicheSender();
            clFicheSend.init();
            clFicheSend = null;

            //send INVOICE
            invoiceSend = new InvoiceSender();
            invoiceSend.init();
            invoiceSend = null;

            //send KSLINES
            ksLineSend = new KsLineSender();
            ksLineSend.init();
            ksLineSend = null;

            //send STFICHE
            stFicheSend = new StFicheSender();
            stFicheSend.init();
            stFicheSend = null;

            System.err.println("DATA HAS BEEN SEND SUCCESSFULLY!");
            if (source == 1) {
                SyncMagazaView.setText("sent", 4);
            }

            return true;
        } catch (Exception e) {
            System.err.println("DATA SEND FAILED!" + e.getMessage());
            return false;
        }
    }
}
