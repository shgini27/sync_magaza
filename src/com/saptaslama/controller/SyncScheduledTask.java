/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saptaslama.controller;

import com.saptaslama.view.SyncMagazaView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

/**
 *
 * @author Shagy
 */
public class SyncScheduledTask extends TimerTask{

    @Override
    public void run() {
    boolean flag;
        SyncMagazaView.setText("syncing...", 1);
        
        Date d = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.err.println("TODAY: " + date.format(d.getTime()));

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        System.err.println("WEEK BEFORE: " + date.format(cal.getTime()));
        
        SyncDataManual sync = new SyncDataManual();
        flag = sync.syncData(0);
        
        if(flag){
            SyncMagazaView.setText(date.format(d.getTime()), 3);
            System.err.println("SYNC MAGAZA COUNTER: " + CompanyConstants.SYNC_COUNTER++);
        }else{
            System.err.println("DIDN'T SYNC MAGAZA COUNTER: " + CompanyConstants.SYNC_COUNTER);
        }
    }
    
}
