package com.dsy.dsu.BroadcastRecievers.Bl;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.WorkManagers.binesslogic.CreatePublicWorkManager;
import com.dsy.dsu.BusinessLogicAll.SubClass_Connection_BroadcastReceiver_Sous_Asyns_Glassfish;
import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;

import java.util.Date;

public class getStartingWorkmanagerPublic {


    public void metodRegistraBroadCastFroPublicAsyns(@NonNull Context context) {
        try {
            // TODO: 08.10.2023
            Integer ПубличныйIDДляФрагмента = new SubClass_Connection_BroadcastReceiver_Sous_Asyns_Glassfish()
                    .МетодПолучениеяПубличногоID(context);

            // TODO: 14.12.2023 REPLACE
            new CreatePublicWorkManager(context).getcreatePublicWorkManager(context,ПубличныйIDДляФрагмента );

            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context.getApplicationContext()).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }


    }

}
