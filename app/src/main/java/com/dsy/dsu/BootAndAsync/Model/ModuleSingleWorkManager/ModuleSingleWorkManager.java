package com.dsy.dsu.BootAndAsync.Model.ModuleSingleWorkManager;

import android.content.Context;
import android.util.Log;


import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;
import com.dsy.dsu.WorkManagers.binesslogic.CreateSingleWorkManager;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;


@Module
@InstallIn(SingletonComponent.class)
@Named
public class ModuleSingleWorkManager {

    Context context;

    public  @Inject ModuleSingleWorkManager(@ApplicationContext Context context) {

        this.context = context;
    }



    public void startingSingleWorkManger() {
        try {
            // TODO: 03.03.2025 Запускем Синхрнонизацию через Singlw Work manager
            new CreateSingleWorkManager(context).getcreateSingleWorkManager( "BootService");

            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n");

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }



}
