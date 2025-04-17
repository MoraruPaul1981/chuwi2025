package com.dsy.dsu.Hilt.JbossAdrress;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;

import com.dsy.dsu.Hilt.JbossAdrress.intarfaces.HiltJbossBinessLogicIntarface;
import com.dsy.dsu.Hilt.JbossAdrress.qualifiers.QualifierJbossServer3;
import com.dsy.dsu.Hilt.JbossAdrress.reliesadress.HiltJbossBinessLogic;
import com.dsy.dsu.Hilt.JbossAdrress.reliesadress.HiltJbossBinessLogicSSl;
import com.dsy.dsu.Settings.Model.Model.SLLBenessLogicMode;

import java.util.LinkedHashMap;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;



@Module
@InstallIn(SingletonComponent.class)
@SuppressLint("SuspiciousIndentation")
public class HiltJboss {

    @Provides
    @QualifierJbossServer3
    public  LinkedHashMap<Integer,String> getHiltPortJboss(@ApplicationContext Context context) {
        LinkedHashMap<Integer,String> getJbossPort= new LinkedHashMap();
        try {
            HiltJbossBinessLogicIntarface hiltJbossBinessLogicIntarface = null;
            SharedPreferences     preferencesJboss = context.getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
         String   getMode_ssl=new SLLBenessLogicMode(context).getModeSLL();

            // TODO: 18.03.2024 РЕЛИЗ  сервер  ЫВбор какой сервер будет работа  с SSL  или без него
            if(getMode_ssl.equalsIgnoreCase("https")){
                // TODO: 06.10.2024 SSL enable
                hiltJbossBinessLogicIntarface=new HiltJbossBinessLogicSSl();

                // TODO: 06.10.2024 ответ  сам адрес с чем подкбчаться
                getJbossPort=   hiltJbossBinessLogicIntarface.selectenableforSslrequests(preferencesJboss,context);
            }

            if(getMode_ssl.equalsIgnoreCase("http")){
                // TODO: 06.10.2024 SSL enable
                hiltJbossBinessLogicIntarface=new HiltJbossBinessLogic();

                // TODO: 06.10.2024 ответ  сам адрес с чем подкбчаться
                getJbossPort=   hiltJbossBinessLogicIntarface.selectenableforSslrequests(preferencesJboss,context);
            }

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " getJbossPort " + getJbossPort+ "getMode_ssl " +getMode_ssl);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return getJbossPort;

    }






}


