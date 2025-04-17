package com.dsy.dsu.Dashboard.Model.endingasynsdashboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.Dashboard.View.MainActivity_Dashboard;
import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;
import com.dsy.dsu.Passwords.MainActivityPasswords;

import java.util.Date;

public class LaunchMainAppAfterSyncing {

private Activity activity;

    public LaunchMainAppAfterSyncing(Activity activity) {
        this.activity = activity;
    }

    public void appAfterSyncingDashboard( ){
        try {
            Intent Интент_ЗапускаетDashboard=new Intent();
            Интент_ЗапускаетDashboard.setClass(activity, MainActivity_Dashboard.class);
             Интент_ЗапускаетDashboard.setFlags(  Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            Bundle bundle=new Bundle();
            Интент_ЗапускаетDashboard.putExtras(bundle);
            Интент_ЗапускаетDashboard.  addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Интент_ЗапускаетDashboard.  addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Интент_ЗапускаетDashboard.setAction("MainActivity_Dashboard.class");
            activity.startActivity(Интент_ЗапускаетDashboard);//tso
            // TODO: 11.04.2025  
            activity.finishAffinity();

            Log.d(this.getClass().getName(),"\n"
                    + " bremy: " + new Date()+"\n+"
                    + "  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(activity.getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }





    public void appAfterSyncingPassword( ) {
        try{
            Intent Интент_ЗапускаетPasswords=new Intent();
            Интент_ЗапускаетPasswords.setClass(activity, MainActivityPasswords.class);
            Интент_ЗапускаетPasswords.setFlags( Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            Bundle bundle=new Bundle();
            Интент_ЗапускаетPasswords.putExtras(bundle);
            Интент_ЗапускаетPasswords.  addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Интент_ЗапускаетPasswords.  addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Интент_ЗапускаетPasswords.setAction("MainActivityPasswords.class");
            activity.startActivity(Интент_ЗапускаетPasswords);//tso
            // TODO: 11.04.2025 exit
            activity.finishAffinity();

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    " localBinderAsync "+ "\n" );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(activity.getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    // TODO: 25.09.2024  end class
}
