package com.dsy.dsu.BootAndAsync.Model.Service.bl_service_boot;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;


import com.dsy.dsu.BootAndAsync.Model.DowloadUpdatePO.CallBackBusUpdatePO;
import com.dsy.dsu.BootAndAsync.Model.EventsBus.MessageEvensBusAppAfterSyncing;
import com.dsy.dsu.BootAndAsync.Model.EventsBus.MessageEvensBusNetworkStatuses;
import com.dsy.dsu.BootAndAsync.Model.EventsBus.MessageEvensBusUpdatePO;
import com.dsy.dsu.BusinessLogicAll.AnalysisUserAuthenticated.GetAnalysisUserAuthenticated;
import com.dsy.dsu.BusinessLogicAll.Class_Connections_Server;
import com.dsy.dsu.Dashboard.Model.endingasynsdashboard.LaunchMainAppAfterSyncing;

import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;
import com.dsy.dsu.Services.ServiceUpdatePoОбновлениеПО;
import com.dsy.dsu.Services.Service_For_Remote_Async_Binary;

import org.greenrobot.eventbus.EventBus;

import java.util.Date;
import java.util.LinkedHashMap;

import javax.inject.Inject;
import javax.net.ssl.SSLSocketFactory;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import io.reactivex.rxjava3.core.Maybe;

@Module
@InstallIn(SingletonComponent.class)
@SuppressLint("Range")
public class BinessLogicIntentServiceBoot {

    public   Service_For_Remote_Async_Binary.LocalBinderAsync localBinderAsync;//TODO нова\
    public    ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО localBinderОбновлениеПО;//TODO нова

    private  Integer permissibledaysofwork=240;

    public  @Inject BinessLogicIntentServiceBoot(@ApplicationContext Context contextBounding) {
        //TODO сомо имя json
        try{
        // TODO: 14.08.2023 методЗапукска Синхрониазйиии
           МетодБиндингаОбновлениеПО(  contextBounding);

            // TODO: 14.08.2023 методЗапукска Синхрониазйиии
            МетодБиндингаRemoteAsync(contextBounding);

        Log.d(contextBounding.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(contextBounding).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }





    public void lanchUpdatePO(@NonNull LinkedHashMap<Integer,String> getHiltPortJboss,
                              @NonNull Context context) {
        try {
            // TODO: 14.08.2023 вызов кода ПОльзовательский

            // TODO: 14.08.2023 Проверяем версси ПО с серврной и локальной
        Maybe maybelanchUpdatePO=    Maybe.fromCallable(()->{
                        // TODO: 22.01.2024 true запускаем Анализ По
                        Integer    versionServicePO=        getVersionServicePO(getHiltPortJboss,context);
                        Integer    versionLocalPO=        getVersionLocalPO(getHiltPortJboss,context);
                        Log.d(context.getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                + "\n"+"versionServicePO"+versionServicePO + " versionLocalPO "+versionLocalPO);
                        if (versionServicePO>versionLocalPO) {
                            return versionServicePO;
                        } else {
                            return null;
                        }
                    }).doOnSuccess(versionServicePO->{
                        // TODO: 30.03.2025 Запускаем ПО
                        // TODO: 22.01.2024 запускаю обновление ПО
                        startingUpdatePOComplete(versionServicePO,context );

                        // TODO: 03.10.2023
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                    }).doOnComplete(()->{
                        // TODO: 30.03.2025 Сообщаем ЧТо версия Уже есть
            Integer    versionServicePO=        getVersionServicePO(getHiltPortJboss,context);

                        getLastVersionUpdatePO(context,versionServicePO);

                        Log.d(context.getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n");

                    });

            maybelanchUpdatePO .blockingSubscribe();


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

    @SuppressLint("SuspiciousIndentation")
    public Long lanchAsync(@NonNull Context context) {
        Long  getcompleteAsync=0l;
        try {
            // TODO: 14.08.2023 вызов кода ПОльзовательский
               getcompleteAsync =   completeAsync(   context);

            // TODO: 03.10.2023
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"   + " getcompleteAsync " +getcompleteAsync);

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
        return   getcompleteAsync;
    }

    public void lanchUpdatePOAndAsync(@NonNull LinkedHashMap<Integer,String> getHiltPortJboss,
                                      @NonNull Context context) {
        try {
            // TODO: 14.08.2023 Проверяем версси ПО с серврной и локальной
       Maybe maybelanchUpdatePOAndAsync=    Maybe.fromCallable(()->{
               // TODO: 22.01.2024 true запускаем Анализ По
               Integer    versionServicePO=        getVersionServicePO(getHiltPortJboss,context);
               Integer    versionLocalPO=        getVersionLocalPO(getHiltPortJboss,context);
               Log.d(context.getClass().getName(), "\n"
                       + " время: " + new Date() + "\n+" +
                       " Класс в процессе... " + this.getClass().getName() + "\n" +
                       " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                       + "\n"+"versionServicePO"+versionServicePO + " versionLocalPO "+versionLocalPO);
               if (versionServicePO>versionLocalPO) {
                   return versionServicePO;
               } else {
                   return null;
               }
            }).doOnSuccess(versionServicePO->{
               // TODO: 30.03.2025 Запускаем ПО
               // TODO: 22.01.2024 запускаю обновление ПО
               startingUpdatePOComplete(versionServicePO,context );

               // TODO: 03.10.2023
               Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                       " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                       " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
            }).doOnComplete(()->{
               // TODO: 30.03.2025 Запускаем СИНХРОНИЗАЦИЮ
               Long getcompleteAsync =   lanchAsync(   context  );
                   // TODO: 31.03.2025

                   // TODO: 31.03.2025 нет логина  и пароля переводим программу на Активити Password
                   afterUpdatePOandAsynclaunchActivity(context);

               // TODO: 03.10.2023
               Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                       " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                       " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " getcompleteAsync " +getcompleteAsync);

               Log.d(context.getClass().getName(), "\n"
                       + " время: " + new Date() + "\n+" +
                       " Класс в процессе... " + this.getClass().getName() + "\n" +
                       " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n");

            });
            // TODO: 31.03.2025
            maybelanchUpdatePOAndAsync.blockingSubscribe();


            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n");//
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }




    // TODO: 31.03.2025  после обновлени и /или синхрониазци запцскаем нужную активти
    public void afterUpdatePOandAsynclaunchActivity(@NonNull Context context) {
        try {
     Boolean UserAuthenticated=       new GetAnalysisUserAuthenticated(context).analysisUserAuthenticated(240);
          Intent intentLaunchMainAppAfterSyncing=new Intent();
            intentLaunchMainAppAfterSyncing.setAction("LaunchMainAppAfterSyncing");
            Bundle setbundleLaunchMainAppAfterSyncing=new Bundle();
            setbundleLaunchMainAppAfterSyncing.putBoolean("launchMainAppAfterSyncing",  UserAuthenticated);///"В процесс"
            intentLaunchMainAppAfterSyncing.putExtras(setbundleLaunchMainAppAfterSyncing);
// TODO: 11.04.2025  Запускаем Основное Приложение после как прошда Синхронизация
            EventBus.getDefault().post(new MessageEvensBusAppAfterSyncing(intentLaunchMainAppAfterSyncing));

            // TODO: 28.04.2023
            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " userHasReceivedAccesstoDashBordorPasswordisNeeded " +UserAuthenticated);
    } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }

    }


    public void getDontNetwork(@NonNull Context context) {
        try{
            Intent intentComunicationsBusAyns=new Intent();
            Bundle bundleComunications=new Bundle();


                intentComunicationsBusAyns.setAction("EventBusAnsyc");
                bundleComunications.putString("Статус",  "ServerJbosOff");///"В процесс"
                intentComunicationsBusAyns.putExtras(bundleComunications);

                EventBus.getDefault().post(new MessageEvensBusNetworkStatuses(intentComunicationsBusAyns));
                // TODO: 22.01.2024 просто сеть рабоатет  переделаем програсс бару

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    " localBinderAsync "+ "\n" );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }




    public void getLastVersionUpdatePO(@NonNull Context context,@NonNull  Integer    СервернаяВерсия) {
        try{
            Intent intentComunicationsBusAyns=new Intent();
            Bundle bundleComunications=new Bundle();


            intentComunicationsBusAyns.setAction("EventBusAnsyc");
            bundleComunications.putString("Статус",  "LastVersionUpdatePO");///"В процесс"
            bundleComunications.putInt("СервернаяВерсия",  СервернаяВерсия);///"В процесс"
            intentComunicationsBusAyns.putExtras(bundleComunications);

            EventBus.getDefault().post(new MessageEvensBusNetworkStatuses(intentComunicationsBusAyns));
            // TODO: 22.01.2024 просто сеть рабоатет  переделаем програсс бару

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    " localBinderAsync "+ "\n" );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }









































        private void startingUpdatePOComplete(@NonNull Integer СервернаяВерсия, @NonNull Context context) {
                     try{
                         Intent intentComunicationsUpdatePO=new Intent();
                         Bundle bundleComunications=new Bundle();

                         intentComunicationsUpdatePO.setAction("EventBusUpdatePO");
                         bundleComunications.putString("Статус",  "UpdateProcessorPO");///"В процесс"
                         bundleComunications.putInt("СервернаяВерсия",  СервернаяВерсия);///"В процесс"
                         intentComunicationsUpdatePO.putExtras(bundleComunications);

                         EventBus.getDefault().post(new MessageEvensBusUpdatePO(intentComunicationsUpdatePO));

                         Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                 " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                 " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                 " localBinderAsync "+ "\n" );
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(context).recordnewerror(e.toString(),
                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
        }





        // TODO: 20.03.2025  слушатель после завершения синхронизации и обновление по 
        private void DontUpdatePOComplete(@NonNull Integer СервернаяВерсия, @NonNull Context context,@NonNull String getWhoLaunched) {
            try{
                Intent intentComunicationsUpdatePO=new Intent();
                intentComunicationsUpdatePO.setAction("EventBusUpdatePO");
                // TODO: 20.03.2025
                Bundle bundleComunications=new Bundle();

                PackageInfo pInfo = context. getPackageManager().getPackageInfo(context. getPackageName(), 0);
                Integer ЛокальнаяВерсияПО = pInfo.versionCode;//Version Code

                if(СервернаяВерсия==0){
                    bundleComunications.putInt("СервернаяВерсия",  ЛокальнаяВерсияПО);///"В процесс"
                }else {
                    bundleComunications.putInt("СервернаяВерсия",  СервернаяВерсия);///"В процесс"
                }
                bundleComunications.putString("Статус",    "LastVersionUpdatePO");///"В процесс"
                intentComunicationsUpdatePO.putExtras(bundleComunications);

                // TODO: 20.03.2025
           new CallBackBusUpdatePO(context).callbackEvensBusUpdatePO(intentComunicationsUpdatePO);

                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                            " localBinderAsync "+ "\n" );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        }



    // TODO: 19.01.2024   /////// МЕТОД КОГДА ЗАХОДИЛ ПОСЛЬДНИЙ РАЗ ПОЛЬЗОВАТЛЬ




    ///////todo ФИНАЛЬНЫЙ МЕТОД КТО ВХОДИЛ ДО 7 ДНЕЙ ИЛИ ПОСЫЛАЕМ НА АУНТИФИКАЦИЮ
    Integer getVersionServicePO(@NonNull LinkedHashMap<Integer,String> getHiltPortJboss, @NonNull Context context) {
        Integer СервернаяВерсия=0;
        try {
            СервернаяВерсия = localBinderОбновлениеПО.getService().МетодГлавныйОбновленияПОДоAsync(true,
                    context,getHiltPortJboss );

            Log.i(this.getClass().getName(), " Атоманически установкаОбновление ПО " +
                    Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString());
            Log.i(this.getClass().getName(), "R.id.item_async_updatepo  "
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString() +
                    "СервернаяВерсия " + СервернаяВерсия);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return  СервернаяВерсия;
    }

    Integer getVersionLocalPO(@NonNull LinkedHashMap<Integer,String> getHiltPortJboss, @NonNull Context context) {
        Integer ЛокальнаяВерсияПО=0;
        try {
            // TODO: 24.09.2024   Локальная Версия Программернр Обеспечения табель
            PackageInfo    pInfo = context. getPackageManager().getPackageInfo(context. getPackageName(), 0);
            String version = pInfo.versionName;//Version Name
              ЛокальнаяВерсияПО = pInfo.versionCode;
            Log.i(this.getClass().getName(), "R.id.item_async_updatepo  "
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString() +
                    "СервернаяВерсия " + ЛокальнаяВерсияПО);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return  ЛокальнаяВерсияПО;
    }




    Long completeAsync( @NonNull Context context  ){
        // TODO: 28.03.2025
        Long completeAsync=0l;
        try{
            // TODO: 03.10.2023

            completeAsync=  localBinderAsync.getService().metodStartingSync(context);

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " completeAsync " +completeAsync );

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  completeAsync;
    }




























    @SuppressLint("SuspiciousIndentation")
    public Boolean МетодПингаКСереруЗапущенЛиСерерИлиНет( @NonNull SSLSocketFactory getsslSocketFactory2, @NonNull Context context) {
        Boolean СтатусРаботыСервера =false;
        try {
            // TODO: 16.12.2021 НЕПОСРЕДСТВЕННЫЙ ПИНГ СИСТЕНМ ИНТРЕНАТ НА НАЛИЧЕНИ СВАЗИ С БАЗОЙ SQL SERVER
              СтатусРаботыСервера = new Class_Connections_Server(). pingServerJbossSuccessfulOrNot(context,getsslSocketFactory2);

   Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +  "  СтатусРаботыСервера " +СтатусРаботыСервера);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return СтатусРаботыСервера;
    }


























































    @SuppressLint("NewApi")
    public void МетодБиндингаRemoteAsync(  @NonNull  Context contextBounding  ) {
        try {
            // TODO: 28.04.2023  запускаем Гланвную Синхрониазцию

                //  Intent intentОбноразоваяСинхронизациия = new Intent(context, Service_For_Remote_Async.class);
                Intent intentAsync = new Intent(contextBounding, Service_For_Remote_Async_Binary.class);
                intentAsync.setAction("com.StartingAsyncMainBackgroud");
                ServiceConnection connectionAsync = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        try {
                            if (service.isBinderAlive()) {
                                // TODO: 29.09.2023
                                 localBinderAsync = (Service_For_Remote_Async_Binary.LocalBinderAsync) service;

                                // TODO: 25.03.2023
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                                        + " localBinderAsync " +localBinderAsync);

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(contextBounding).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }


                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        localBinderAsync = null;
                        Log.d(contextBounding.getClass().getName().toString(), "\n"
                                + "onServiceConnected  одноразовая  messengerActivity  ");
                    }

                    @Override
                    public void onBindingDied(ComponentName name) {
                        ServiceConnection.super.onBindingDied(name);
                    }

                    @Override
                    public void onNullBinding(ComponentName name) {
                        ServiceConnection.super.onNullBinding(name);
                    }
                };

            contextBounding. bindService(intentAsync ,connectionAsync ,Context.BIND_AUTO_CREATE);


            // TODO: 28.04.2023
            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(contextBounding).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
        }

    }





    @SuppressLint("NewApi")
    public void МетодБиндингаОбновлениеПО( @NonNull  Context contextBounding ) {
        try {
            ServiceConnection   connectionОбновлениеПО = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    try {
                        if (service.isBinderAlive()) {

                            // TODO: 28.07.2023  Update
                            localBinderОбновлениеПО = (ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО) service;

                            // TODO: 25.03.2023
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

                        }

                        Log.d(contextBounding.getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"
                                + "localBinderОбновлениеПО " + localBinderОбновлениеПО);

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(contextBounding).recordnewerror(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }


                @Override
                public void onServiceDisconnected(ComponentName name) {
                    try {
                        localBinderОбновлениеПО = null;
                        Log.i(contextBounding.getClass().getName(), "    onServiceDisconnected  binder.isBinderAlive()");
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(contextBounding).recordnewerror(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
            };
            Intent intentЗапускСлужбыОбновлениеПО = new Intent(contextBounding, ServiceUpdatePoОбновлениеПО.class);
            intentЗапускСлужбыОбновлениеПО.setAction("com.ServiceUpdatePoОбновлениеПО");

            contextBounding. bindService(intentЗапускСлужбыОбновлениеПО,connectionОбновлениеПО,Context.BIND_AUTO_CREATE  );
            // TODO: 28.04.2023
            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(contextBounding).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
        }

    }


    // TODO: 19.01.2024 END CLASS
}