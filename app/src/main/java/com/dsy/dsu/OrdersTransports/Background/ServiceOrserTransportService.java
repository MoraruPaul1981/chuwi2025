package com.dsy.dsu.OrdersTransports.Background;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.BinderThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.dsy.dsu.BusinessLogicAll.VersionCurentTable;
import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;
import com.dsy.dsu.BusinessLogicAll.DATE.Class_Generation_Data;
import com.dsy.dsu.BusinessLogicAll.DATE.SubClassCursorLoader;
import com.dsy.dsu.Hilt.Sqlitehilt.AppModuleSQLlite;
import com.google.firebase.annotations.concurrent.Background;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import dagger.hilt.EntryPoints;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ServiceOrserTransportService extends IntentService {

    private  SubClassOrderTransport subClassOrderTransport;

   protected LocalBinderOrderTransport localBinderOrderTransport= new LocalBinderOrderTransport();
    public ServiceOrserTransportService() {

        super("ServiceOrserTransportService");

    }

    @Override
    public void onCreate() {
        super.onCreate();
        // TODO: 16.04.2025
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() );
        subClassOrderTransport=new SubClassOrderTransport();
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  getApplicationContext().getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSelf();
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  getApplicationContext().getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        try{
            LocalBinderOrderTransport.setCallingWorkSourceUid(new Random().nextInt());
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  getApplicationContext().getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
    }
        return localBinderOrderTransport;

    }



    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  getApplicationContext().getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  getApplicationContext().getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        super.onRebind(intent);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  getApplicationContext().getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        Log.d(newBase.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  newBase.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try{
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  getApplicationContext().getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
    }
    }
    public class LocalBinderOrderTransport extends Binder {
        public ServiceOrserTransportService getService() {
            try {
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
            return ServiceOrserTransportService.this;
        }


        @Background
        @BinderThread
        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
           try{
               Log.d(getApplicationContext().getClass().getName(), "\n"
                       + " время: " + new Date() + "\n+" +
                       " Класс в процессе... " + this.getClass().getName() + "\n" +
                       " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +  " mapBoundService " +
                       " reply " +reply  + " data " +data );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
            return    true;
        }




        // TODO: 04.05.2023 Главный метод Службы Заказы Транспота
        @BinderThread
        @Background
        public  Integer методВиндингУдалениеЗаказа(@NonNull  Long UUIDДляУдалениеRow  ){
            Integer РезультатаУдалениеRow=0;
            try{
                SubClassDeleteУдаланиеRow subClassDeleteУдаланиеRow=new SubClassDeleteУдаланиеRow();
                 РезультатаУдалениеRow=         subClassDeleteУдаланиеRow.методУдалениеВыбранойRow(UUIDДляУдалениеRow);
                Log.d(getApplicationContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +  " mapBoundService " +
                        " mapRetry " +РезультатаУдалениеRow + " Thread 1   "
                        + Thread.currentThread().getName()+ " Thread 2"
                        + Thread.getAllStackTraces().values().toString() );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
            return  РезультатаУдалениеRow;
        }






        // TODO: 04.05.2023 Главный метод Службы Заказы Транспота
        @BinderThread
        @Background
        public  Map<String,Object> методГлавныйTraffic(@NonNull  HashMap<String,String> dataMap  ){
            Map<String,Object>  mapRetry= new HashMap<>();
            try{
                     Cursor cursor=   subClassOrderTransport.new SubClassGetCursor().методGetCursor(dataMap);
                        // TODO: 04.05.2023  ответ Курсором Из Службы
                        mapRetry.put("replyget1",cursor);
                Log.d(getApplicationContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +  " mapBoundService " +
                        " mapRetry " +mapRetry + " Thread 1   "
                        + Thread.currentThread().getName()+ " Thread 2"
                        + Thread.getAllStackTraces().values().toString() );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
            return  mapRetry;
        }
        // TODO: 04.05.2023 Главный метод Службы Заказы Транспота
        @BinderThread
        @Background
        public  Map<String,Object> методГлавныйNeworderTranportTraffic(@NonNull  HashMap<String,String> dataMap  ){
            Map<String,Object>  mapRetry= new HashMap<>();
            try{
                Cursor cursor=   subClassOrderTransport.new SubClassGetCursor().методGetNewOrderCursor( dataMap);
                // TODO: 04.05.2023  ответ Курсором Из Службы
                mapRetry.put("replyget1",cursor);
                Log.d(getApplicationContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +  " mapBoundService " +
                        " mapRetry " +mapRetry + " Thread 1   "
                        + Thread.currentThread().getName()+ " Thread 2"
                        + Thread.getAllStackTraces().values().toString() );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
            return  mapRetry;
        }
        // TODO: 04.05.2023 Главный метод Службы Заказы Транспота LIKE
        @BinderThread
        @Background
        public  Map<String,Object> методГлавныйLikeNeworderTranportTraffic(@NonNull  HashMap<String,String> dataMap  ){
            Map<String,Object>  mapRetry= new HashMap<>();
            try{
                Cursor cursor=   subClassOrderTransport.new SubClassGetCursor().методGetLikeNewOrderCursor( dataMap);
                // TODO: 04.05.2023  ответ Курсором Из Службы
                mapRetry.put("replyget1",cursor);
                Log.d(getApplicationContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +  " mapBoundService " +
                        " mapRetry " +mapRetry + " Thread 1   "
                        + Thread.currentThread().getName()+ " Thread 2"
                        + Thread.getAllStackTraces().values().toString() );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
            return  mapRetry;
        }

        // TODO: 24.05.2023 Grpuo By New order Trarnsport
        @BinderThread
        @Background
        public  Map<String,Object> методГлавныйGrpuopByOrderTrasport(@NonNull  HashMap<String,String> dataMap  ){
            Map<String,Object>  mapRetry= new HashMap<>();
            try{
                Cursor cursor=   subClassOrderTransport.new SubClassGetCursor().методGetGroupByOrderCursor( dataMap);
                // TODO: 04.05.2023  ответ Курсором Из Службы
                mapRetry.put("replyget1",cursor);
                Log.d(getApplicationContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +  " mapBoundService " +
                        " mapRetry " +mapRetry + " Thread 1   "
                        + Thread.currentThread().getName()+ " Thread 2"
                        + Thread.getAllStackTraces().values().toString() );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
            return  mapRetry;
        }
        // TODO: 01.06.2023  метод получение трех значений день месяц год
        public LinkedHashMap<String,String> методGetТриЗначениеГодМесяцДень() {
            LinkedHashMap<String,String> linkedHashMapДеньМесяцГод=new LinkedHashMap<>();
            try{
                Calendar myCalмесяц = new GregorianCalendar();
                Calendar myCalдень = new GregorianCalendar();
                SimpleDateFormat simpleDateFormatмесяц=    new SimpleDateFormat("MM", new Locale("ru"));
                SimpleDateFormat simpleDateFormatдень=    new SimpleDateFormat("dd", new Locale("ru"));
               String Dateмесяц=      simpleDateFormatмесяц.format(myCalмесяц.getTime());
               String Dateдень=       simpleDateFormatдень.format(myCalдень.getTime());
                Integer Год = myCalмесяц.get(Calendar.YEAR);
                // TODO: 01.06.2023  Заполяем
                linkedHashMapДеньМесяцГод.put("День", Dateдень);
                linkedHashMapДеньМесяцГод.put("Месяц",  Dateмесяц);
                linkedHashMapДеньМесяцГод.put("Год",Год.toString());
                Log.d(getApplicationContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                        " linkedHashMapДеньМесяцГод " +linkedHashMapДеньМесяцГод);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  linkedHashMapДеньМесяцГод;
        }
    }








































    // TODO: 25.04.2023  КЛАСС РАБОТЫ ЗАКАЗА ТРАНСПОРТА
   protected class SubClassOrderTransport{

        // TODO: 03.05.2023 GEt Cursor
      protected   class SubClassGetCursor{
            Cursor методGetCursor(@NonNull   HashMap<String,String>  mapBoundService){
                Cursor cursor = null;
                try{
               String    СамЗапрос = mapBoundService.get("1").trim();
                String УсловияВыборки=    mapBoundService.get("2").trim();
                String ФильтрУсловияВыборки1= Optional.ofNullable(mapBoundService.get("3")) .orElse("") ;
                String ФильтрУсловияВыборки2=   Optional.ofNullable( mapBoundService.get("4")).orElse("") ;
                String Таблица=    mapBoundService.get("5").trim();
                    Bundle bundleЗаказТранспорт=new Bundle();
                    bundleЗаказТранспорт.putString("СамЗапрос", СамЗапрос + " "+ УсловияВыборки);
                    bundleЗаказТранспорт.putStringArray("УсловияВыборки" ,new String[]{ФильтрУсловияВыборки1,ФильтрУсловияВыборки2});
                    bundleЗаказТранспорт.putString("Таблица",Таблица);
                    cursor=      (Cursor)    new SubClassCursorLoader(). CursorLoaders(getApplicationContext(), bundleЗаказТранспорт);

                Log.d(getApplicationContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "mapBoundService " +mapBoundService+
                         "cursor " +cursor);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
                return  cursor;
            }

            Cursor методGetNewOrderCursor(@NonNull   HashMap<String,String>  mapBoundService){
                Cursor cursor = null;
                try{
                    String    СамЗапрос = mapBoundService.get("1").trim();
                    String УсловияВыборки=    mapBoundService.get("2").trim();
                    String ФильтрУсловияВыборки1= Optional.ofNullable(mapBoundService.get("3")) .orElse("") ;
                    String Таблица=    mapBoundService.get("4").trim();
                    Bundle bundleЗаказТранспорт=new Bundle();
                    bundleЗаказТранспорт.putString("СамЗапрос", СамЗапрос + " "+ УсловияВыборки);
                    bundleЗаказТранспорт.putStringArray("УсловияВыборки" ,new String[]{ФильтрУсловияВыборки1});
                    bundleЗаказТранспорт.putString("Таблица",Таблица);
                    cursor=      (Cursor)    new SubClassCursorLoader(). CursorLoaders(getApplicationContext(), bundleЗаказТранспорт);

                    Log.d(getApplicationContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "mapBoundService " +mapBoundService+
                            "cursor " +cursor);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
                }
                return  cursor;
            }
            // TODO: 15.05.2023 LIKE
            Cursor методGetLikeNewOrderCursor(@NonNull   HashMap<String,String>  mapBoundService){
                Cursor cursor = null;
                try{
                                 String Таблица=    mapBoundService.get("Таблица").trim();
                                Bundle data=new Bundle();
                                String  ТекущийLike=mapBoundService.get("ТекущийLike").trim();
                                String  ТекущийСтолбик=mapBoundService.get("ТекущейСтолбик").trim();
                                Log.w(this.getClass().getName(), "   Таблица  " +Таблица);
                                Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + Таблица.trim() + "");
                                ContentResolver resolver = getApplicationContext().getContentResolver();
                              //  data.putString("selection"," fullname  LIKE  ? AND fullname!=? ");
                                data.putString("selection"," "+ТекущийСтолбик+"  LIKE  ? AND "+ТекущийСтолбик+"!=?  ");
                                data.putStringArray("selectionArgs",new String[]{"%"+ТекущийLike+"%",""});
                                data.putString(   "sortOrder",ТекущийСтолбик);
                    // TODO: 16.05.2023
                    cursor = resolver.query(uri,new String[]{"*"},data,null);// TODO: 13.10.2022 ,"Удаленная"
                    Log.d(getApplicationContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "mapBoundService " +mapBoundService+
                            "cursor " +cursor);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
                }
                return  cursor;
            }
            // TODO: 24.05.2023  Group By New Order Trasport
            Cursor методGetGroupByOrderCursor(@NonNull   HashMap<String,String>  mapBoundService){
                Cursor cursor = null;
                try{
                    String    СамЗапрос = mapBoundService.get("1").trim();
                    String УсловияWhere=    mapBoundService.get("2").trim();
                    String УсловияGroupBy= mapBoundService.get("3").trim();
                    String УсловияHaving=    mapBoundService.get("4").trim();
                    String Таблица=    mapBoundService.get("5").trim();
                    String OrderBy=    mapBoundService.get("6").trim();
                    Bundle bundleЗаказТранспорт=new Bundle();
                    bundleЗаказТранспорт.putString("СамЗапрос", СамЗапрос + " "+ УсловияWhere + " "+УсловияGroupBy +  " " +   УсловияHaving+" "+OrderBy+" " );
                    bundleЗаказТранспорт.putStringArray("УсловияВыборки" ,new String[]{ });
                    bundleЗаказТранспорт.putString("Таблица",Таблица);
                    cursor=      (Cursor)    new SubClassCursorLoader(). CursorForGetgropuByLoaders(getApplicationContext(), bundleЗаказТранспорт);

                    Log.d(getApplicationContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "mapBoundService " +mapBoundService+
                            "cursor " +cursor);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
                }
                return  cursor;
            }
        }
        // TODO: 25.04.2023 END CLASS   SubClassOrderTransport  // TODO: 25.04.2023 END CLASS   SubClassOrderTransport  // TODO: 25.04.2023 END CLASS   SubClassOrderTransport  // TODO: 25.04.2023 END CLASS   SubClassOrderTransport
        // TODO: 25.04.2023 END CLASS   SubClassOrderTransport   // TODO: 25.04.2023 END CLASS   SubClassOrderTransport  // TODO: 25.04.2023 END CLASS   SubClassOrderTransport  // TODO: 25.04.2023 END CLASS   SubClassOrderTransport
    }



    // TODO: 15.06.2023  Удаление Выбраного СТрочки ЗАказа
    class SubClassDeleteУдаланиеRow{
        Integer методУдалениеВыбранойRow(@NonNull  Long UUIDДляУдалениеRow){
            Integer РезультатаУдалениеRow=0;
            try{
                String ТаблицаОбработки="order_tc";
                Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecurrentoperations/" +ТаблицаОбработки + "");
                ContentValues contentValuesУданиеЗаказаТраспорта=new ContentValues();
                contentValuesУданиеЗаказаТраспорта.put("status",5);
                String Дата =     new Class_Generation_Data(getApplicationContext()).ГлавнаяДатаИВремяОперацийСБазойДанныхДОП();
                contentValuesУданиеЗаказаТраспорта.put("date_update", Дата);
                Long Версия = new VersionCurentTable(getApplicationContext()).upVersionCurentTable(    ТаблицаОбработки);
                contentValuesУданиеЗаказаТраспорта.put("current_table", Версия);
                // TODO: 12.04.2023 удаление ЗАказа Траспрта
                ContentResolver contentResolver=getApplicationContext().getContentResolver();
                РезультатаУдалениеRow= contentResolver.update(uri, contentValuesУданиеЗаказаТраспорта,"uuid=?",new String[]{String.valueOf(UUIDДляУдалениеRow)});
                // TODO: 15.06.2023
                Log.d(getApplicationContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                        " UUIDДляУдалениеRow" +UUIDДляУдалениеRow+ "\n"+" РЕЗУЛЬТАТ РезультатаУдалениеRow  " +  РезультатаУдалениеRow);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getApplicationContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  РезультатаУдалениеRow;

        }
    }


}