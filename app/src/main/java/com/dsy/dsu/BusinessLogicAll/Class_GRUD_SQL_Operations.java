package com.dsy.dsu.BusinessLogicAll;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.loader.content.AsyncTaskLoader;

import com.dsy.dsu.AllDatabases.SQLTE.GetSqlite;
import com.dsy.dsu.BusinessLogicAll.DATE.Class_Generation_Data;
import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;




///////TODO КЛАСС ВСЕ ОПЕРАЦИИ ВСТАВКИ УДАЛЕНИЕ ОБНОВЛЕНИЯ ВЫБОРКА ДАННЫХ В ОДНОМ МЕСТЕ
public class Class_GRUD_SQL_Operations extends GetSqlite {
    public AsyncTaskLoader asyncTaskLoaderАунтификацияПользователя = null;
    protected Stream Стрим=null;
    public Context context;
    public    Map<String,Object> concurrentHashMapНабор =
            Collections.synchronizedMap(new LinkedHashMap<String,Object>());
    public Callable<Object> ЛистДляGRUDопераций = null;
  public  ContentValues contentValuesДляSQLBuilder_Для_GRUD_Операций  = new ContentValues();
    SQLiteQueryBuilder    SQLBuilder_Для_GRUD_Операций =null;
    List <String> subQueriesОбьединенныйЗапросыUNION;

    public Class_GRUD_SQL_Operations(Context context) {
        this.context = context;
        this.context =context;
        subQueriesОбьединенныйЗапросыUNION=Collections.synchronizedList(new ArrayList());
    }
    // TODO: 22.09.2022

    //TODO КЛАСС ПОЛУЧЕНИЕ ДАННЫХ
    public class  GetData extends Class_GRUD_SQL_Operations {
        Context context;
        public GetData(@NotNull Context context) {
            super(context);
            this.context=context;
            //todo ПОДКЛЮЯЕМ КЛАСС ВЫШЕСТОЯЩИЙ ДЛЯ РАБОТЫ ОПЕРАЦИЙ grud
            /**
             *
             * @param context
             */
         ///   Create_Database_СсылкаНАБазовыйКласс=new CREATE_DATABASE(context);
            ////
            ///
            /**
             *
             */
            SQLBuilder_Для_GRUD_Операций =new SQLiteQueryBuilder    ();
        }
        // TODO: 30.08.2021  НИЖЕ  УКАЗАНЫ ВСЕ МЕТОДЫ ПОЛУЧЕНИЕ ОБНОВЛЕНИЯ ВСТАВКИ УДАЛЕНИЕ ДАННЫХ
        // TODO: 27.08.2021 МЕТОД  ПОЛУЧЕНИЯ ДАННЫХ
        public Object getdata(Map<String, Object> concurrentHashMap, CompletionService МенеджерПотоков, SQLiteDatabase getБазаДанныхДЛяОперацийВнутри) throws ExecutionException, InterruptedException {
            Object Getdata=null;
        ЛистДляGRUDопераций=new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    Object getdata=null;
                    try {
                        // TODO: 27.08.2021 парарменты
                        String НазваниеОбрабоатываемойТаблицы=null;
                        String    СтолбцыОбработки=null;
                        String ФорматПосика=null;
                        String УсловиеПоиска[]=new String[50];
                        String ПоляГрупировки=null;
                        String УсловиеГрупировки=null;
                        String УсловиеСортировки=null;
                        String УсловиеЛимита=null;
                        Boolean ФлагНепотораяемостиСтрок=false;
                        String ПодЗапросНомер1=null;
                        String ПодЗапросНомер2=null;
                        String ПодЗапросНомер3=null;
                        String ПодЗапросНомер4=null;
                        String ПодЗапросНомер5=null;
                        String ПодЗапросНомер6=null;
                        String ПодЗапросНомер7=null;
                        String ПодЗапросНомер8=null;
                        String ПодЗапросНомер9=null;
                        //TODO ЦИКЛ ПО ПАРАМЕТРАМ
                        if (concurrentHashMap!=null && getБазаДанныхДЛяОперацийВнутри!=null ) {
                            Log.w(context.getClass().getName(), "concurrentHashMap " + concurrentHashMap.values()
                                    + " getБазаДанныхДЛяОперацийВнутри" +getБазаДанныхДЛяОперацийВнутри);
                            for (Object КлючconcurrentHashMap : concurrentHashMap.keySet()) {
                                Object ЗначениеconcurrentHashMap = concurrentHashMap.get(КлючconcurrentHashMap);
                                Log.w(context.getClass().getName(), "concurrentHashMap.toString() " + concurrentHashMap.toString());
                                // TODO: 27.08.2021  присваевываем значения полям для получение данных
                                switch (КлючconcurrentHashMap.toString().trim()){
                                    case "НазваниеОбрабоатываемойТаблицы" :
                                        НазваниеОбрабоатываемойТаблицы=ЗначениеconcurrentHashMap.toString().trim();
                                        break;
                                    case "СтолбцыОбработки" :
                                        СтолбцыОбработки=ЗначениеconcurrentHashMap.toString().trim();;
                                        break;
                                    case "ФорматПосика" :
                                        ФорматПосика=ЗначениеconcurrentHashMap.toString().trim();
                                        break;
                                    case "УсловиеПоиска1" :
                                        УсловиеПоиска[0]=ЗначениеconcurrentHashMap.toString().trim();
                                        break;
                                    case "УсловиеПоиска2" :
                                        УсловиеПоиска[1]=ЗначениеconcurrentHashMap.toString().trim();
                                        break;
                                    case "УсловиеПоиска3" :
                                        УсловиеПоиска[2]=ЗначениеconcurrentHashMap.toString().trim();
                                        break;
                                    case "УсловиеПоиска4" :
                                        УсловиеПоиска[3]=ЗначениеconcurrentHashMap.toString().trim();
                                        break;
                                    case "УсловиеПоиска5" :
                                        УсловиеПоиска[4]=ЗначениеconcurrentHashMap.toString().trim();
                                        break;
                                    case "УсловиеПоиска6" :
                                        УсловиеПоиска[5]=ЗначениеconcurrentHashMap.toString().trim();
                                        break;
                                    case "УсловиеПоиска7" :
                                        УсловиеПоиска[6]=ЗначениеconcurrentHashMap.toString().trim();
                                        break;
                                    case "УсловиеПоиска8" :
                                        УсловиеПоиска[7]=ЗначениеconcurrentHashMap.toString().trim();
                                        break;
                                    case "УсловиеПоиска9" :
                                        УсловиеПоиска[8]=ЗначениеconcurrentHashMap.toString().trim();
                                        break;
                                    case "УсловиеПоиска10" :
                                        УсловиеПоиска[9]=ЗначениеconcurrentHashMap.toString().trim();
                                        break;
                                    case "УсловиеПоиска11" :
                                        УсловиеПоиска[10]=ЗначениеconcurrentHashMap.toString().trim();
                                        break;
                                    case "УсловиеПоиска12" :
                                        УсловиеПоиска[11]=ЗначениеconcurrentHashMap.toString().trim();
                                        break;
                                    case "УсловиеПоиска13" :
                                        УсловиеПоиска[12]=ЗначениеconcurrentHashMap.toString().trim();
                                        break;
                                    case "УсловиеПоиска14" :
                                        УсловиеПоиска[13]=ЗначениеconcurrentHashMap.toString().trim();
                                        break;
                                    case "УсловиеПоиска15" :
                                        УсловиеПоиска[14]=ЗначениеconcurrentHashMap.toString().trim();
                                        break;
                                    case "УсловиеПоиска16" :
                                        УсловиеПоиска[15]=ЗначениеconcurrentHashMap.toString().trim();
                                        break;
                                    case "УсловиеПоиска17" :
                                        УсловиеПоиска[16]=ЗначениеconcurrentHashMap.toString().trim();
                                        break;
                                    case "УсловиеПоиска18" :
                                        УсловиеПоиска[17]=ЗначениеconcurrentHashMap.toString().trim();
                                        break;
                                    case "ПоляГрупировки" :
                                        ПоляГрупировки=ЗначениеconcurrentHashMap.toString().trim();
                                        break;
                                    case "УсловиеГрупировки" :
                                        УсловиеГрупировки=ЗначениеconcurrentHashMap.toString().trim();
                                        break;
                                    case "УсловиеСортировки" :
                                        УсловиеСортировки=ЗначениеconcurrentHashMap.toString().trim();
                                        break;
                                    case "УсловиеЛимита" :
                                        УсловиеЛимита=ЗначениеconcurrentHashMap.toString().trim();
                                        break;
                                    case "ФлагНепотораяемостиСтрок" :
                                        ФлагНепотораяемостиСтрок=  (Boolean) ЗначениеconcurrentHashMap;
                                        break;
                                    case "ПодЗапросНомер1" :
                                        ПодЗапросНомер1=ЗначениеconcurrentHashMap.toString().trim();
                                        // TODO: 31.01.2022  add union
                                        subQueriesОбьединенныйЗапросыUNION.add(ПодЗапросНомер1);
                                        break;
                                    case "ПодЗапросНомер2" :
                                        ПодЗапросНомер2=ЗначениеconcurrentHashMap.toString().trim();
                                        // TODO: 31.01.2022  add union
                                        subQueriesОбьединенныйЗапросыUNION.add(ПодЗапросНомер2);
                                        break;
                                    case "ПодЗапросНомер3" :
                                        ПодЗапросНомер3=ЗначениеconcurrentHashMap.toString().trim();
                                        // TODO: 31.01.2022  add union
                                        subQueriesОбьединенныйЗапросыUNION.add(ПодЗапросНомер3);
                                        break;
                                    case "ПодЗапросНомер4" :
                                        ПодЗапросНомер4=ЗначениеconcurrentHashMap.toString().trim();
                                        // TODO: 31.01.2022  add union
                                        subQueriesОбьединенныйЗапросыUNION.add(ПодЗапросНомер4);
                                        break;
                                    case "ПодЗапросНомер5" :
                                        ПодЗапросНомер5=ЗначениеconcurrentHashMap.toString().trim();
                                        // TODO: 31.01.2022  add union
                                        subQueriesОбьединенныйЗапросыUNION.add(ПодЗапросНомер5);
                                        break;
                                    case "ПодЗапросНомер6" :
                                        ПодЗапросНомер6=ЗначениеconcurrentHashMap.toString().trim();
                                        // TODO: 31.01.2022  add union
                                        subQueriesОбьединенныйЗапросыUNION.add(ПодЗапросНомер6);
                                        break;
                                        case "ПодЗапросНомер7" :
                                        ПодЗапросНомер7=ЗначениеconcurrentHashMap.toString().trim();
                                        // TODO: 31.01.2022  add union
                                        subQueriesОбьединенныйЗапросыUNION.add(ПодЗапросНомер7);
                                        break;
                                    case "ПодЗапросНомер8" :
                                        ПодЗапросНомер8=ЗначениеconcurrentHashMap.toString().trim();
                                        // TODO: 31.01.2022  add union
                                        subQueriesОбьединенныйЗапросыUNION.add(ПодЗапросНомер8);
                                        break;
                                    case "ПодЗапросНомер9" :
                                        ПодЗапросНомер9=ЗначениеconcurrentHashMap.toString().trim();
                                        // TODO: 31.01.2022  add union
                                        subQueriesОбьединенныйЗапросыUNION.add(ПодЗапросНомер9);
                                        break;


                                }
                            }
                        }else {
                            Log.w(context.getClass().getName(), " getБазаДанныхДЛяОперацийВнутри  "+
                                    getБазаДанныхДЛяОперацийВнутри );
                        }
                        // TODO: 27.08.2021  проверка параметров чтоюбы небыло NULL
                        LinkedHashMap<Integer,Object> concurrentHashMapТолькоДляЗбораЗаполенныхПараметровУсловияФильтра=new LinkedHashMap<Integer,Object> ();
                        // TODO: 30.08.2021   цикл упаковываем ппарметры в массик для ЗАПРОСА
                        for (int i=0;i<УсловиеПоиска.length ; i++ ) {
                            Log.d(context.getClass().getName(), "УсловиеПоиска[i] " + УсловиеПоиска[i]);
                            if (УсловиеПоиска[i] != null) {
                                concurrentHashMapТолькоДляЗбораЗаполенныхПараметровУсловияФильтра.put(new Random().nextInt(),УсловиеПоиска[i]);
                                Log.w(context.getClass().getName(), " concurrentHashMapТолькоДляЗбораЗаполенныхПараметровУсловияФильтра.values().toString()  "
                                        + concurrentHashMapТолькоДляЗбораЗаполенныхПараметровУсловияФильтра.values().toString());
                            }
                        }
                        // TODO: 31.08.2021
                        String[] ПолученныеПараметрыДляУсловияПосикаФинал=null;
                        if (concurrentHashMapТолькоДляЗбораЗаполенныхПараметровУсловияФильтра.size()>0) {
                            ПолученныеПараметрыДляУсловияПосикаФинал= concurrentHashMapТолькоДляЗбораЗаполенныхПараметровУсловияФильтра.values().
                                    toArray(new String[ concurrentHashMapТолькоДляЗбораЗаполенныхПараметровУсловияФильтра.size()]);
                        }
                        Log.w(context.getClass().getName(), " ПолученныеПараметрыДляУсловияПосикаФинал  "
                                + ПолученныеПараметрыДляУсловияПосикаФинал);
                        SQLBuilder_Для_GRUD_Операций.setTables(НазваниеОбрабоатываемойТаблицы);
                        if (ФорматПосика!=null) {
                            SQLBuilder_Для_GRUD_Операций.appendWhere(ФорматПосика.toLowerCase());
                        }
                        if (ФлагНепотораяемостиСтрок!=null) {
                            SQLBuilder_Для_GRUD_Операций.setDistinct(ФлагНепотораяемостиСтрок);
                        }
                        Log.w(this.getClass().getName(), "ПодЗапросНомер1   "
                                + ПодЗапросНомер1 + " ПодЗапросНомер2 " +ПодЗапросНомер2);
                        if (ПодЗапросНомер1==null && ПодЗапросНомер2==null) {
                            getdata =
                                    SQLBuilder_Для_GRUD_Операций.query(getБазаДанныхДЛяОперацийВнутри,new String[]{СтолбцыОбработки},
                                            null,ПолученныеПараметрыДляУсловияПосикаФинал
                                            ,ПоляГрупировки, УсловиеГрупировки, УсловиеСортировки,УсловиеЛимита);//ФорматПосика выше
                            Log.w(this.getClass().getName(), "   РЕЗУЛЬТАТ GetData  ПОЛУЧЕНИЕ  ДАННЫХ    КОЛ СТРОЧКЕ:  "+ ((SQLiteCursor) getdata).getCount());
                        } else {
                            String[] subQueries = subQueriesОбьединенныйЗапросыUNION.toArray(new String[0]);
                            Log.w(this.getClass().getName(), "   РЕЗУЛЬТАТ GetData  ПОЛУЧЕНИЕ  ДАННЫХ  SUB UNION   КОЛ СТРОЧКЕ: UNION  subQueries   "+ subQueries);
                            String query =    SQLBuilder_Для_GRUD_Операций.buildUnionQuery(subQueries, null ,null);
                            query=query.replace("ALL", " ");
                            Log.w(this.getClass().getName(), "   РЕЗУЛЬТАТ GetData  ПОЛУЧЕНИЕ  ДАННЫХ  SUB UNION   КОЛ СТРОЧКЕ: UNION  subQueries   "+ subQueries);
                            getdata =   getБазаДанныхДЛяОперацийВнутри.rawQuery(query, null);
                            Log.w(this.getClass().getName(), "   РЕЗУЛЬТАТ GetData  ПОЛУЧЕНИЕ  ДАННЫХ  SUB UNION   КОЛ СТРОЧКЕ:  "+ ((SQLiteCursor) getdata).getCount());
                        }
                        Log.w(this.getClass().getName(), " ЕДИНСТВЕННЫЙ         ПОЛУЧЕНИЯ ДАННЫХ   " + getdata);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    return getdata;
                }
            };
            //TODO конец выполения кода через Callble  , отправляем его в главный менеджер пОТОКОВ
            Getdata=     new ClassRuntimeExeGRUDOpertions(context).МетодЗапускаОперацийGRUD_exe(ЛистДляGRUDопераций,МенеджерПотоков);
            Log.d(this.getClass().getName(), "   рЕЗУЛЬТАТ МЕТОДА ПОЛУЧЕНИЕ ДАННЫХ ЧЕРЕЗ ОДИН МЕТДЖЕР ПОТОКОВ  Getdata "+Getdata);
            return Getdata;
        }
    }
// TODO: 17.09.2021  END GETDATA
    //TODO КЛАСС Вставки ДАННЫХ
    public class InsertData extends Class_GRUD_SQL_Operations {
Context context;
        public InsertData(@NotNull Context context) {
            super(context);
            this.context=context;
            /**
             *
             * @param context
             */
            /// Create_Database_СсылкаНАБазовыйКласс=new CREATE_DATABASE(context);

            /**
             *
             */
            SQLBuilder_Для_GRUD_Операций =new SQLiteQueryBuilder    ();

            //////////

        }


        // TODO: 27.08.2021 МЕТОД  ВСТАВКИ ДАННЫХ
        public Object insertdata(Map<String, Object> concurrentHashMap, ContentValues contentValuesВставкаДанных,
                                 CompletionService МенеджерПотоков,
                                 SQLiteDatabase getБазаДанныхДЛяОперацийВнутри) throws ExecutionException, InterruptedException {
            Object InsertData = null;
            try {
                ЛистДляGRUDопераций = new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        Object insertData=null;
                        String  НазваниеОбрабоатываемойТаблицы=null;
                        //TODO ЦИКЛ ПО ПАРАМЕТРАМ
                        for (Object КлючconcurrentHashMap : concurrentHashMap.keySet()) {
                            Object ЗначениеconcurrentHashMap = concurrentHashMap.get(КлючconcurrentHashMap);
                            Log.w(context.getClass().getName(), "concurrentHashMap.toString() " + concurrentHashMap.toString());
                            switch (КлючconcurrentHashMap.toString().trim()){
                                case "НазваниеОбрабоатываемойТаблицы" :
                                    НазваниеОбрабоатываемойТаблицы=ЗначениеconcurrentHashMap.toString().trim();
                                    break;
                            }
                        }
                        Log.w(this.getClass().getName(), "   НазваниеОбрабоатываемойТаблицы   "+НазваниеОбрабоатываемойТаблицы);
                        SQLBuilder_Для_GRUD_Операций.setTables(НазваниеОбрабоатываемойТаблицы.toLowerCase());
                        /////TODO операция ВСТАВКИ
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            ////////
                            insertData =    SQLBuilder_Для_GRUD_Операций.insert( getБазаДанныхДЛяОперацийВнутри, contentValuesВставкаДанных);
                        } else {
                            /////TODO операция ВСТАВКИ
                            insertData =getБазаДанныхДЛяОперацийВнутри.insert(НазваниеОбрабоатываемойТаблицы, null, contentValuesВставкаДанных);
                        }
                        Log.w(context.getClass().getName(), " РЕЗУЛЬТАТ insertData  ВСТАВКИ ЗНАЧЕНИЯ  " +  insertData.toString() 
                                + " результат вставки Integer.parseInt(insertData.toString()) " +Integer.parseInt(insertData.toString()));
                        return insertData;
                    }
                };
                InsertData=     new ClassRuntimeExeGRUDOpertions(context).МетодЗапускаОперацийGRUD_exe(ЛистДляGRUDопераций,МенеджерПотоков);
                Log.w(context.getClass().getName(), " РЕЗУЛЬТАТ InsertData  ВСТАВКИ ЗНАЧЕНИЯ  " +  InsertData.toString() );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return InsertData;
        }

    }

    //TODO КЛАСС Вставки ЧЕРЕЗ КОНТЕЙНЕР ДАННЫХ
    class  InsertDataContentResolver extends Class_GRUD_SQL_Operations {

        public InsertDataContentResolver(@NotNull Context context) {
            super(context);
            /**
             *
             * @param context
             */
            /// Create_Database_СсылкаНАБазовыйКласс=new CREATE_DATABASE(context);

            /**
             *
             */
           // SQLBuilder_Для_GRUD_Операций =new SQLiteQueryBuilder    ();

            //////////

        }



        // TODO: 27.08.2021 МЕТОД  ВСТАВКИ ДАННЫХ
        Object insertdataContentResolver(Map<String,Object> concurrentHashMap,
                                         ContentValues[] contentValuesВставкаДанных ,
                          CompletionService МенеджерПотоков,
                          SQLiteDatabase getБазаДанныхДЛяОперацийВнутри) throws ExecutionException, InterruptedException {
            Object InsertData=null;
            try {
                ЛистДляGRUDопераций = new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        Object insertData=null;
                        String  НазваниеОбрабоатываемойТаблицы=null;
                        for (Object КлючconcurrentHashMap : concurrentHashMap.keySet()) {
                            Object ЗначениеconcurrentHashMap = concurrentHashMap.get(КлючconcurrentHashMap);
                            Log.w(context.getClass().getName(), "concurrentHashMap.toString() " + concurrentHashMap.toString());
                            switch (КлючconcurrentHashMap.toString().trim()){
                                case "НазваниеОбрабоатываемойТаблицы" :
                                    НазваниеОбрабоатываемойТаблицы=ЗначениеconcurrentHashMap.toString().trim();
                                    break;
                            }
                        }
                        Log.w(this.getClass().getName(), "   НазваниеОбрабоатываемойТаблицы   "+НазваниеОбрабоатываемойТаблицы);

                  //      Uri uri = Uri.parse("content://MyContentProviderDatabase/" +НазваниеОбрабоатываемойТаблицы + "");
                        Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" +НазваниеОбрабоатываемойТаблицы + "");
                        ContentResolver resolver = context.getContentResolver();
                        insertData=   resolver.bulkInsert(uri, contentValuesВставкаДанных);
                        Log.w(context.getClass().getName(), " РЕЗУЛЬТАТ insertData  ВСТАВКИ ЗНАЧЕНИЯ  " +  insertData.toString() );
                        return insertData;
                    }
                };
                //TODO конец выполения кода через Callble  , отправляем его в главный менеджер пОТОКОВ
                InsertData=     new ClassRuntimeExeGRUDOpertions(context).МетодЗапускаОперацийGRUD_exe(ЛистДляGRUDопераций,МенеджерПотоков);
                Log.w(context.getClass().getName(), " РЕЗУЛЬТАТ InsertData  ВСТАВКИ ЗНАЧЕНИЯ  " +  InsertData.toString() );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return InsertData;
        }

    }












    //TODO КЛАСС Обновление ДАННЫХ
    public class  UpdateData extends Class_GRUD_SQL_Operations {
Context context;
        public UpdateData(@NotNull Context context) {
            super(context);
            this.context=context;
            /**
            /**
             *
             * @param context
             */
          //  Create_Database_СсылкаНАБазовыйКласс=new CREATE_DATABASE(context);
            /**
             *
             */
            SQLBuilder_Для_GRUD_Операций =new SQLiteQueryBuilder    ();

            //////////

        }


        // TODO: 27.08.2021 МЕТОД  ПОЛУЧЕНИЯ ДАННЫХ
        public Object updatedata(Map<String, Object> concurrentHashMap, ContentValues contentValuesДляОбновленияДАнных, CompletionService МенеджерПотоков, SQLiteDatabase getБазаДанныхДЛяОперацийВнутри) throws ExecutionException, InterruptedException {
            ///////////
            Object Updatedata=null;
            //////// TODO запуск менеджера потоков


// TODO: 28.06.2022  блокировка операции ОБНОВЛЕНИЕ

            
            try {
                ///TODO тело самого кода List    ////
                ЛистДляGRUDопераций= new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        /////
                        Object updatedata=null;
                        ///
                        // TODO: 30.08.2021  параметры
                        String  НазваниеОбрабоатываемойТаблицы=null;
                        /////
                        String  Флаг_ЧерезКакоеПолеОбновлением=null;

                        //
                        String  ЗначениеФлагОбновления=null;

                        //
                        String ЗнакФлагОбновления=null;


                        Log.w(context.getClass().getName(), "concurrentHashMap " + concurrentHashMap.values() +
                                "  contentValuesДляОбновленияДАнных " +contentValuesДляОбновленияДАнных.valueSet());

                        //TODO ЦИКЛ ПО ПАРАМЕТРАМ
                        for (Object КлючconcurrentHashMap : concurrentHashMap.keySet()) {

                            Object ЗначениеconcurrentHashMap = concurrentHashMap.get(КлючconcurrentHashMap);
                            //

                            Log.w(context.getClass().getName(), "concurrentHashMap.toString() " + concurrentHashMap.toString());

                            // TODO: 27.08.2021  присваевываем значения полям для получение данных

                            switch (КлючconcurrentHashMap.toString().trim()){

                                //
                                case "НазваниеОбрабоатываемойТаблицы" :
                                    //////
                                    НазваниеОбрабоатываемойТаблицы=ЗначениеconcurrentHashMap.toString().trim();

                                    // TODO: 27.08.2021  конец присвоение параментов
                                    break;
                                ///

                                //
                                case "Флаг_ЧерезКакоеПолеОбновлением" :
                                    //////
                                    Флаг_ЧерезКакоеПолеОбновлением=ЗначениеconcurrentHashMap.toString();

                                    // TODO: 27.08.2021  конец присвоение параментов
                                    break;
                                ///

                                //
                                case "ЗначениеФлагОбновления" :
                                    //////
                                    ЗначениеФлагОбновления=ЗначениеconcurrentHashMap.toString();

                                    // TODO: 27.08.2021  конец присвоение параментов
                                    break;
                                ///
                                //
                                case "ЗнакФлагОбновления" :
                                    //////
                                    ЗнакФлагОбновления=ЗначениеconcurrentHashMap.toString();

                                    // TODO: 27.08.2021  конец присвоение параментов
                                    break;
                                ///




                                ///
///
                            }

                        }

                        Log.w(this.getClass().getName(), "   НазваниеОбрабоатываемойТаблицы   "+НазваниеОбрабоатываемойТаблицы);

                        //////TODO конец параменты

                        // TODO: 30.08.2021  ОРМИРУЕМ КОРКАТ БУДЩЕЙ ВСТАВКИ ДАННЫХ

                        //


                        ////////////
                        SQLBuilder_Для_GRUD_Операций.setTables(НазваниеОбрабоатываемойТаблицы);



                        // TODO: 19.11.2021 srart transation

                        if (!getБазаДанныхДЛяОперацийВнутри.inTransaction()) {
                            getБазаДанныхДЛяОперацийВнутри.beginTransaction();
                        }

                        /////////////
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

                            //////////TODO само обновление
                            updatedata =             SQLBuilder_Для_GRUD_Операций.
                                    update(getБазаДанныхДЛяОперацийВнутри,contentValuesДляОбновленияДАнных, Флаг_ЧерезКакоеПолеОбновлением +  ""+ЗнакФлагОбновления+ "?",
                                            new String[]{ЗначениеФлагОбновления});
                        }else{

                            updatedata =           getБазаДанныхДЛяОперацийВнутри
                                    .update(НазваниеОбрабоатываемойТаблицы,contentValuesДляОбновленияДАнных,Флаг_ЧерезКакоеПолеОбновлением +  ""+ЗнакФлагОбновления+ "?", new String[]{ЗначениеФлагОбновления});
                        }

                        /////TODO РЕЗУЛЬТАТ операция Обновление


                        Log.d(context.getClass().getName(), " updatedata " + updatedata.toString() + "результат обвноления  Integer.parseInt(updatedata.toString() "+Integer.parseInt(updatedata.toString()));
                        ////////
                        //////
                        if (getБазаДанныхДЛяОперацийВнутри.inTransaction()) {
                            getБазаДанныхДЛяОперацийВнутри.setTransactionSuccessful();

                            getБазаДанныхДЛяОперацийВнутри.endTransaction();
                        }

                        // TODO: 29.10.2021  clear in memory sqlite




                        return updatedata;
                    }
                };


                //TODO конец выполения кода через Callble  , отправляем его в главный менеджер пОТОКОВ

                Updatedata=     new ClassRuntimeExeGRUDOpertions(context).МетодЗапускаОперацийGRUD_exe(ЛистДляGRUDопераций,МенеджерПотоков);

                ///

                /////
                ///
                Log.w(context.getClass().getName(), " РЕЗУЛЬТАТ Updatedata  ОБНОВЛЕНИЕ ЗНАЧЕНИЯ  " +  Updatedata.toString() );


// TODO: 29.10.2021 ВЫКЛЮЧАЕМ БАЗУ


                /////

            } catch (Exception e) {
                ///////TODO error
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());

                new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                /////
            }
            //////// TODO конец  менеджера потоков

            return Updatedata;
        }

    }


    //TODO КЛАСС СОН SLEEP ДАННЫХ
    public class SleepData extends Class_GRUD_SQL_Operations {
        Context context;
        public SleepData(@NotNull Context context) {
            super(context);
            this.context=context;
            SQLBuilder_Для_GRUD_Операций = new SQLiteQueryBuilder();
        }
        // TODO: 27.08.2021 МЕТОД  ПОЛУЧЕНИЯ ДАННЫХ
        public Object sleepdata(Map<String, Object> concurrentHashMap, ContentValues contentValuesДляСнаДанных, CompletionService МенеджерПотоков, SQLiteDatabase getБазаДанныхДЛяОперацийВнутри) throws ExecutionException, InterruptedException {
            Object Sleepdata = null;
            try {
                ЛистДляGRUDопераций = new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        Object sleepdata=null;
                        String  НазваниеОбрабоатываемойТаблицы=null;
                        String  Флаг_ЧерезКакоеПолеСнаДанных=null;
                        String  ЗначениеФлагСнаДанных=null;
                        Log.w(context.getClass().getName(), "concurrentHashMap " + concurrentHashMap.values() +
                                "  contentValuesДляСнаДанных " +contentValuesДляСнаДанных.valueSet());
                        //TODO ЦИКЛ ПО ПАРАМЕТРАМ
                        for (Object КлючconcurrentHashMap : concurrentHashMap.keySet()) {

                            Object ЗначениеconcurrentHashMap = concurrentHashMap.get(КлючconcurrentHashMap);
                            Log.w(context.getClass().getName(), "concurrentHashMap.toString() " + concurrentHashMap.toString());
                            // TODO: 27.08.2021  присваевываем значения полям для получение данных
                            switch (КлючconcurrentHashMap.toString().trim()){
                                case "НазваниеОбрабоатываемойТаблицы" :
                                    НазваниеОбрабоатываемойТаблицы=ЗначениеconcurrentHashMap.toString().trim();
                                    // TODO: 27.08.2021  конец присвоение параментов
                                    break;
                                case "Флаг_ЧерезКакоеПолеСнаДанных" :
                                    Флаг_ЧерезКакоеПолеСнаДанных=ЗначениеconcurrentHashMap.toString();
                                    break;
                                case "ЗначениеФлагСнаДанных" :
                                    ЗначениеФлагСнаДанных=ЗначениеconcurrentHashMap.toString();
                                    // TODO: 27.08.2021  конец присвоение параментов
                                    break;

                            }

                        }
                        Log.w(this.getClass().getName(), "   НазваниеОбрабоатываемойТаблицы   "+НазваниеОбрабоатываемойТаблицы);
                        SQLBuilder_Для_GRUD_Операций.setTables(НазваниеОбрабоатываемойТаблицы);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            //////////TODO само обновление
                            sleepdata =             SQLBuilder_Для_GRUD_Операций.
                                    update(getБазаДанныхДЛяОперацийВнутри,contentValuesДляСнаДанных, Флаг_ЧерезКакоеПолеСнаДанных + "= ?",
                                            new String[]{ЗначениеФлагСнаДанных});
                        }else{

                            sleepdata =           getБазаДанныхДЛяОперацийВнутри
                                    .update(НазваниеОбрабоатываемойТаблицы,contentValuesДляСнаДанных,Флаг_ЧерезКакоеПолеСнаДанных + "= ?", new String[]{ЗначениеФлагСнаДанных});
                        }
                        Log.w(this.getClass().getName(), "   рЕЗУЛЬТАТ МЕТОДА ВСТАВКИ ДАННЫХ  УСПЕШНО  deletedata "+sleepdata);
                        return sleepdata;
                    }
                };
                Sleepdata=   new ClassRuntimeExeGRUDOpertions(context).МетодЗапускаОперацийGRUD_exe(ЛистДляGRUDопераций,МенеджерПотоков);
                Log.w(context.getClass().getName(), " РЕЗУЛЬТАТ sleepdata  СНА ДАННЫХ  ЗНАЧЕНИЯ  " +  Sleepdata.toString() );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return Sleepdata;
        }
    }
    //TODO КЛАСС Удаление ДАННЫХ
    public class  DeleteData extends Class_GRUD_SQL_Operations {
        Context context;

        public DeleteData(@NotNull Context context) {
            super(context);
            this.context = context;
            /**
             *
             * @param context
             */
            ///Create_Database_СсылкаНАБазовыйКласс=new CREATE_DATABASE(context);
            /**
             *
             */
            SQLBuilder_Для_GRUD_Операций = new SQLiteQueryBuilder();


        }

        // TODO: 27.08.2021 МЕТОД  ПОЛУЧЕНИЯ ДАННЫХ
        public Object deletedata(Map<String, Object> concurrentHashMap
                , CompletionService МенеджерПотоков,
                                 SQLiteDatabase getБазаДанныхДЛяОперацийВнутри) throws ExecutionException, InterruptedException {
            /////
            Object Deletedata = null;
            //////// TODO запуск менеджера потоков

            try {
                ///TODO тело самого кода List    ////
                //////// TODO запуск менеджера потоков
                ///TODO тело самого кода List    ////
                ЛистДляGRUDопераций = new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        /////
                        Object deletedata = null;
                        ///
                        // TODO: 30.08.2021  параметры
                        String НазваниеОбрабоатываемойТаблицы = null;
                        /////
                        String Флаг_ЧерезКакоеПолеУдаление = null;

                        //
                        String ЗначениеФлагУдаление = null;

                        //
                        String ЗначениеФлагУдалениеВторое = null;
                        //
                        String ЗнакФлагУдаление = null;

                        String СамFreeSQLКОд = null;


                        Log.w(context.getClass().getName(), "concurrentHashMap " + concurrentHashMap.values());

                        //TODO ЦИКЛ ПО ПАРАМЕТРАМ
                        for (Object КлючconcurrentHashMap : concurrentHashMap.keySet()) {

                            Object ЗначениеconcurrentHashMap = concurrentHashMap.get(КлючconcurrentHashMap);
                            //

                            Log.w(context.getClass().getName(), "concurrentHashMap.toString() " + concurrentHashMap.toString());

                            // TODO: 27.08.2021  присваевываем значения полям для получение данных

                            switch (КлючconcurrentHashMap.toString().trim()) {

                                //
                                case "НазваниеОбрабоатываемойТаблицы":
                                    //////
                                    НазваниеОбрабоатываемойТаблицы = ЗначениеconcurrentHashMap.toString().trim();

                                    // TODO: 27.08.2021  конец присвоение параментов
                                    break;
                                ///

                                //
                                case "Флаг_ЧерезКакоеПолеУдаление":
                                    //////
                                    Флаг_ЧерезКакоеПолеУдаление = ЗначениеconcurrentHashMap.toString();

                                    // TODO: 27.08.2021  конец присвоение параментов
                                    break;
                                ///


                                //
                                case "ЗначениеФлагУдаление":
                                    //////
                                    ЗначениеФлагУдаление = ЗначениеconcurrentHashMap.toString();

                                    // TODO: 27.08.2021  конец присвоение параментов
                                    break;
                                ///


                                //
                                case "ЗначениеФлагУдалениеВторой":
                                    //////
                                    ЗначениеФлагУдалениеВторое = ЗначениеconcurrentHashMap.toString();

                                    // TODO: 27.08.2021  конец присвоение параментов
                                    break;
                                ///


                                //
                                case "ЗнакФлагУдаление":
                                    //////
                                    ЗнакФлагУдаление = ЗначениеconcurrentHashMap.toString();

                                    // TODO: 27.08.2021  конец присвоение параментов
                                    break;
                                ///


                                //
                                case "СамFreeSQLКОд":
                                    //////
                                    СамFreeSQLКОд = ЗначениеconcurrentHashMap.toString();

                                    // TODO: 27.08.2021  конец присвоение параментов
                                    break;
                                ///


                                ///
///
                            }

                        }

                        Log.w(this.getClass().getName(), "   НазваниеОбрабоатываемойТаблицы   " + НазваниеОбрабоатываемойТаблицы);

                        //////TODO конец параменты

                        // TODO: 30.08.2021  ОРМИРУЕМ КОРКАТ БУДЩЕЙ ВСТАВКИ ДАННЫХ

                        //


                        ////////////
                        SQLBuilder_Для_GRUD_Операций.setTables(НазваниеОбрабоатываемойТаблицы);

                        // TODO: 29.12.2021
                        SQLBuilder_Для_GRUD_Операций.appendWhere(Флаг_ЧерезКакоеПолеУдаление);

                        /////TODO операция ВСТАВКИ.rawQuery(СамFreeSQLКОд, null);


                        int РезультатУдаления = 0;


                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {

                         /*   РезультатУдаления = SQLBuilder_Для_GRUD_Операций
                                    .delete(getБазаДанныхДЛяОперацийВнутри,null,null);*/
                   /*         РезультатУдаления =  getБазаДанныхДЛяОперацийВнутри
                                    .delete(НазваниеОбрабоатываемойТаблицы,null, null);*/
                            РезультатУдаления = SQLBuilder_Для_GRUD_Операций.
                                    delete(getБазаДанныхДЛяОперацийВнутри, null,
                                            new String[]{ЗначениеФлагУдаление, ЗначениеФлагУдалениеВторое});
                            ///
                            ///
                            Log.d(context.getClass().getName(), " РезультатУдаления" + "--" + РезультатУдаления);/////
                        } else {

                            РезультатУдаления = getБазаДанныхДЛяОперацийВнутри
                                    .delete(НазваниеОбрабоатываемойТаблицы, Флаг_ЧерезКакоеПолеУдаление,
                                            new String[]{ЗначениеФлагУдаление, ЗначениеФлагУдалениеВторое});

                            ///
                            Log.d(context.getClass().getName(), " РезультатУдаления" + "--" + РезультатУдаления);/////
                        }

                        Log.d(this.getClass().getName(), "РезультатУдаления " + РезультатУдаления);


                        //////



            /*            /////////////
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

                            //////////TODO само обновление
                            deletedata =             SQLBuilder_Для_GRUD_Операций.
                                    delete(getБазаДанныхДЛяОперацийВнутри, null,
                                            new String[]{ЗначениеФлагУдаление,ЗначениеФлагУдалениеВторое});
                        }else{

                            deletedata =           getБазаДанныхДЛяОперацийВнутри
                                    .delete(НазваниеОбрабоатываемойТаблицы,Флаг_ЧерезКакоеПолеУдаление,
                                            new String[]{ЗначениеФлагУдаление,ЗначениеФлагУдалениеВторое});


                            Log.w(this.getClass().getName(), "   deletedata   "+deletedata);

                            //
                 *//*           deletedata =           Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазуORM()
                                    .delete(НазваниеОбрабоатываемойТаблицы,Флаг_ЧерезКакоеПолеУдаление + ""+ЗнакФлагУдаление+ "?", new String[]{ЗначениеФлагУдаление});*//*
                        }*/

                        /////TODO РЕЗУЛЬТАТ операция Обновление
                        //////


                        ////////

                        return РезультатУдаления;
                    }
                };


                //TODO конец выполения кода через Callble  , отправляем его в главный менеджер пОТОКОВ

                Deletedata = new ClassRuntimeExeGRUDOpertions(context).МетодЗапускаОперацийGRUD_exe(ЛистДляGRUDопераций, МенеджерПотоков);

                ///
                ///
                Log.w(context.getClass().getName(), " РЕЗУЛЬТАТ deletedata  УДАЛЕНИЕ ДАННЫХ  ЗНАЧЕНИЯ  " + Deletedata.toString());
                /////

                /////

                ///
                Log.d(context.getClass().getName(), " deletedata " + Deletedata);
                /////

// TODO: 29.10.2021 ВЫКЛЮЧАЕМ БАЗУ


            } catch (Exception e) {
                ///////TODO error
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());

                new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                /////
            }

            return Deletedata;
        }
    }
// TODO: 22.02.2022 данный метод НУЖЕН ТОЛЬЕО ДЛЯ УДАЛЕНИЕ ТАБЛДИЦ ПОЛНОСТЬЮ


        // TODO: 27.08.2021 МЕТОД  ПОЛУЧЕНИЯ ДАННЫХ
        Object deletedataAlltable(Map<String,Object> concurrentHashMap
                ,CompletionService МенеджерПотоков,
                          SQLiteDatabase getБазаДанныхДЛяОперацийВнутри) throws ExecutionException, InterruptedException {
            /////
            Object deletedataAlltable=null;
            //////// TODO запуск менеджера потоков

            try {
                ///TODO тело самого кода List    ////
                //////// TODO запуск менеджера потоков
                ///TODO тело самого кода List    ////
                ЛистДляGRUDопераций=new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        /////
                        SQLiteQueryBuilder       SQLBuilder_Для_GRUD_Операций =new SQLiteQueryBuilder    ();

                        Object deletedata=null;
                        ///
                        // TODO: 30.08.2021  параметры
                        String  НазваниеОбрабоатываемойТаблицы=null;
                        /////
                        String  Флаг_ЧерезКакоеПолеУдаление=null;

                        //
                        String  ЗначениеФлагУдаление=null;

                        //
                        String  ЗначениеФлагУдалениеВторое=null;
                        //
                        String ЗнакФлагУдаление=null;

                        String   СамFreeSQLКОд=null;


                        Log.w(context.getClass().getName(), "concurrentHashMap " + concurrentHashMap.values());

                        //TODO ЦИКЛ ПО ПАРАМЕТРАМ
                        for (Object КлючconcurrentHashMap : concurrentHashMap.keySet()) {

                            Object ЗначениеconcurrentHashMap = concurrentHashMap.get(КлючconcurrentHashMap);
                            //

                            Log.w(context.getClass().getName(), "concurrentHashMap.toString() " + concurrentHashMap.toString());

                            // TODO: 27.08.2021  присваевываем значения полям для получение данных

                            switch (КлючconcurrentHashMap.toString().trim()){

                                //
                                case "НазваниеОбрабоатываемойТаблицы" :
                                    //////
                                    НазваниеОбрабоатываемойТаблицы=ЗначениеconcurrentHashMap.toString().trim();

                                    // TODO: 27.08.2021  конец присвоение параментов
                                    break;
                                ///

                                //
                                case "Флаг_ЧерезКакоеПолеУдаление" :
                                    //////
                                    Флаг_ЧерезКакоеПолеУдаление=ЗначениеconcurrentHashMap.toString();

                                    // TODO: 27.08.2021  конец присвоение параментов
                                    break;
                                ///


                                //
                                case "ЗначениеФлагУдаление" :
                                    //////
                                    ЗначениеФлагУдаление=ЗначениеconcurrentHashMap.toString();

                                    // TODO: 27.08.2021  конец присвоение параментов
                                    break;
                                ///


                                //
                                case "ЗначениеФлагУдалениеВторой" :
                                    //////
                                    ЗначениеФлагУдалениеВторое=ЗначениеconcurrentHashMap.toString();

                                    // TODO: 27.08.2021  конец присвоение параментов
                                    break;
                                ///


                                //
                                case "ЗнакФлагУдаление" :
                                    //////
                                    ЗнакФлагУдаление=ЗначениеconcurrentHashMap.toString();

                                    // TODO: 27.08.2021  конец присвоение параментов
                                    break;
                                ///


                                //
                                case "СамFreeSQLКОд" :
                                    //////
                                    СамFreeSQLКОд=ЗначениеconcurrentHashMap.toString();

                                    // TODO: 27.08.2021  конец присвоение параментов
                                    break;
                                ///





                                ///
///
                            }

                        }

                        Log.w(this.getClass().getName(), "   НазваниеОбрабоатываемойТаблицы   "+НазваниеОбрабоатываемойТаблицы);

                        //////TODO конец параменты

                        // TODO: 30.08.2021  ОРМИРУЕМ КОРКАТ БУДЩЕЙ ВСТАВКИ ДАННЫХ

                        //


                        ////////////
                        SQLBuilder_Для_GRUD_Операций.setTables(НазваниеОбрабоатываемойТаблицы);

                        // TODO: 29.12.2021

                        /////TODO операция ВСТАВКИ.rawQuery(СамFreeSQLКОд, null);

                        int РезультатУдаленияВсейТаблицы= 0;


                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {

                         /*   РезультатУдаления = SQLBuilder_Для_GRUD_Операций
                                    .delete(getБазаДанныхДЛяОперацийВнутри,null,null);*/
                   /*         РезультатУдаления =  getБазаДанныхДЛяОперацийВнутри
                                    .delete(НазваниеОбрабоатываемойТаблицы,null, null);*/
                            РезультатУдаленияВсейТаблицы = SQLBuilder_Для_GRUD_Операций
                                    .delete(getБазаДанныхДЛяОперацийВнутри,null,null);
                            ///
                            ///
                            Log.d(context.getClass().getName(), " РезультатУдаленияВсейТаблицы" + "--" + РезультатУдаленияВсейТаблицы);/////
                        }else{

                            РезультатУдаленияВсейТаблицы =  getБазаДанныхДЛяОперацийВнутри
                                    .delete(НазваниеОбрабоатываемойТаблицы,null, null);

                            ///
                            Log.d(context.getClass().getName(), " РезультатУдаления" + "--" + РезультатУдаленияВсейТаблицы);/////
                        }

                        Log.d(this.getClass().getName(), "РезультатУдаления "+РезультатУдаленияВсейТаблицы );

            /*            /////////////
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

                            //////////TODO само обновление
                            deletedata =             SQLBuilder_Для_GRUD_Операций.
                                    delete(getБазаДанныхДЛяОперацийВнутри, null,
                                            new String[]{ЗначениеФлагУдаление,ЗначениеФлагУдалениеВторое});
                        }else{

                            deletedata =           getБазаДанныхДЛяОперацийВнутри
                                    .delete(НазваниеОбрабоатываемойТаблицы,Флаг_ЧерезКакоеПолеУдаление,
                                            new String[]{ЗначениеФлагУдаление,ЗначениеФлагУдалениеВторое});


                            Log.w(this.getClass().getName(), "   deletedata   "+deletedata);

                            //
                 *//*           deletedata =           Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазуORM()
                                    .delete(НазваниеОбрабоатываемойТаблицы,Флаг_ЧерезКакоеПолеУдаление + ""+ЗнакФлагУдаление+ "?", new String[]{ЗначениеФлагУдаление});*//*
                        }*/

                        /////TODO РЕЗУЛЬТАТ операция Обновление
                        //////


                        ////////

                        return РезультатУдаленияВсейТаблицы;
                    }
                };


                //TODO конец выполения кода через Callble  , отправляем его в главный менеджер пОТОКОВ

                deletedataAlltable=     new ClassRuntimeExeGRUDOpertions(context).МетодЗапускаОперацийGRUD_exe(ЛистДляGRUDопераций,МенеджерПотоков);

                ///
                ///
                Log.w(context.getClass().getName(), " РЕЗУЛЬТАТ deletedata  УДАЛЕНИЕ ДАННЫХ  ЗНАЧЕНИЯ  " +  deletedataAlltable.toString() );
                /////

                /////

                ///
                Log.d(context.getClass().getName(), " deletedata " + deletedataAlltable);
                /////

// TODO: 29.10.2021 ВЫКЛЮЧАЕМ БАЗУ


            } catch (Exception e) {
                ///////TODO error
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());

                new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                /////
            }

            return deletedataAlltable;
        }
    //TODO КЛАСС Вставки ДАННЫХ
    public class  ChangesVesionData extends Class_GRUD_SQL_Operations {
        Context context;

        public ChangesVesionData(@NotNull Context context) {
            super(context);
            this.context = context;
            SQLBuilder_Для_GRUD_Операций = new SQLiteQueryBuilder();
        }
        // TODO: 27.08.2021 МЕТОД  ИЗМЕНЕНИЯ ПОВЫШЕНИЯ ВЕРСИИ ДЛЯ  ТЕКУЩЕЙ ТАБЛИЦЫ ТАБЛИЦЫ
        public Object changesvesiondata(Map<String, Object> concurrentHashMap,
                                        CompletionService МенеджерПотоков,
                                        SQLiteDatabase getБазаДанныхДЛяОперацийВнутри) throws ExecutionException, InterruptedException {
            ///////
            Object Changesvesiondata = null;
            //////// TODO запуск менеджера потоков

            /////

            try {
                ///TODO тело самого кода List    ////
                ЛистДляGRUDопераций = new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        /////
                        Object changesvesiondata = null;
                        String НазваниеОбрабоатываемойТаблицы = null;
                        String ФлагТипИзменениеВерсииДанныхЛокальнаяСервернаяИлиОба = null;
                        String ПередоваемоеЗначенияДляТаблицы_MODIFITATION_Client_КотороеНадоЗаписать = null;
                        Log.w(context.getClass().getName(), "concurrentHashMap " + concurrentHashMap.values());
                        //TODO ЦИКЛ ПО ПАРАМЕТРАМ
                        for (Object КлючconcurrentHashMap : concurrentHashMap.keySet()) {

                            Object ЗначениеconcurrentHashMap = concurrentHashMap.get(КлючconcurrentHashMap);
                            //

                            Log.w(context.getClass().getName(), "concurrentHashMap.toString() " + concurrentHashMap.toString());

                            // TODO: 27.08.2021  присваевываем значения полям для получение данных

                            switch (КлючconcurrentHashMap.toString().trim()) {

                                case "НазваниеОбрабоатываемойТаблицы":
                                    //////
                                    НазваниеОбрабоатываемойТаблицы = ЗначениеconcurrentHashMap.toString().trim();

                                    // TODO: 27.08.2021  конец присвоение параментов
                                    break;

                                ///
                                case "ФлагТипИзменениеВерсииДанныхЛокальнаяСервернаяИлиОба":
                                    //////
                                    ФлагТипИзменениеВерсииДанныхЛокальнаяСервернаяИлиОба = ЗначениеconcurrentHashMap.toString().trim();

                                    // TODO: 27.08.2021  конец присвоение параментов
                                    break;

                                ///

                                ///
                                case "ПередоваемоеЗначенияДляТаблицы_MODIFITATION_Client_КотороеНадоЗаписать":
                                    //////
                                    ПередоваемоеЗначенияДляТаблицы_MODIFITATION_Client_КотороеНадоЗаписать = ЗначениеconcurrentHashMap.toString().trim();

                                    // TODO: 27.08.2021  конец присвоение параментов
                                    break;

                                ///


                            }

                        }

                        // TODO: 23.09.2021  почле цикла
                        Log.d(this.getClass().getName(), "  ПередоваемоеЗначенияДляТаблицы_MODIFITATION_Client_КотороеНадоЗаписать"
                                + ПередоваемоеЗначенияДляТаблицы_MODIFITATION_Client_КотороеНадоЗаписать);

                        String СгенерированованныйДата = null;
                        //
                        String ПолеНаОснованииКотрогоУвеличиваемВерсиюДанных = null;


                        ///
                        Long РезультатУвеличинаяВерсияДАных = 0L;

                        //////
                        СгенерированованныйДата = new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();

                        Log.d(this.getClass().getName(), "   ЗАПУСК ФОНОВОЙ СИНХРОНИЗАЦИИИ С mYwORK_sYNCHRONIZACI  СЛУЖБА  WorkManager Synchronizasiy_Data  СгенерированованныйДата " + СгенерированованныйДата);

// TODO: 30.08.2021  ЗАПОЛЕНЕНИ ПОЛУЧЕННЫМИ ДАННЫМИ НОВОЙ ДАТОЙ И НОВОЙ ВЕРИСЕЙ


                        switch (ФлагТипИзменениеВерсииДанныхЛокальнаяСервернаяИлиОба) {


                            ///////////// ПЕРВОЕ ЛОКАЛЬНОЕ
                            case "Локальное":
                                /////////
                                contentValuesДляSQLBuilder_Для_GRUD_Операций.put("localversionandroid", СгенерированованныйДата);
                                /////////
                                ПолеНаОснованииКотрогоУвеличиваемВерсиюДанных = "localversionandroid_version";
                                ///////////////////--old ВЕРСИЯ ПОВЫШЕНИЯ ДАННЫХ

                              /*  РезультатУвеличинаяВерсияДАных=
                                        МетодПолученияУвеличинойВесрииДанныхДляТекущейТолькоДляСистемнойТаблицы_MODIFITATION_Client(
                                                НазваниеОбрабоатываемойТаблицы.toLowerCase(),"localversionandroid_version",contextСозданиеБАзы
                                                ,getБазаДанныхДЛяОперацийВнутри);*/

                                // TODO: 13.01.2022
                                Log.w(this.getClass().getName(), "  ПередоваемоеЗначенияДляТаблицы_MODIFITATION_Client_КотороеНадоЗаписать"
                                        + ПередоваемоеЗначенияДляТаблицы_MODIFITATION_Client_КотороеНадоЗаписать);

                                ///
                                contentValuesДляSQLBuilder_Для_GRUD_Операций.put(ПолеНаОснованииКотрогоУвеличиваемВерсиюДанных,
                                        ПередоваемоеЗначенияДляТаблицы_MODIFITATION_Client_КотороеНадоЗаписать);
                                //
                                break;


                            ///////////// ВТОРОЕ

                            case "Серверный":
                                /////////
                                contentValuesДляSQLBuilder_Для_GRUD_Операций.put("versionserveraandroid", СгенерированованныйДата);
                                //
                                /////////
                                ПолеНаОснованииКотрогоУвеличиваемВерсиюДанных = "versionserveraandroid_version";
                                ////

/*

                                    РезультатУвеличинаяВерсияДАных=
                                            МетодПолученияУвеличинойВесрииДанныхДляТекущейТолькоДляСистемнойТаблицы_MODIFITATION_Client(
                                                    НазваниеОбрабоатываемойТаблицы.toLowerCase(),"localversionandroid_version",contextСозданиеБАзы
                                                    ,getБазаДанныхДЛяОперацийВнутри);
*/

                                // TODO: 13.01.2022
                                Log.w(this.getClass().getName(), "  ПередоваемоеЗначенияДляТаблицы_MODIFITATION_Client_КотороеНадоЗаписать"
                                        + ПередоваемоеЗначенияДляТаблицы_MODIFITATION_Client_КотороеНадоЗаписать);

                                contentValuesДляSQLBuilder_Для_GRUD_Операций.put(ПолеНаОснованииКотрогоУвеличиваемВерсиюДанных,
                                        ПередоваемоеЗначенияДляТаблицы_MODIFITATION_Client_КотороеНадоЗаписать);
                                //

                                break;


                            ///////////// Третье

                            case "ЛокальныйСерверныйОба":

                                //TODO ПОДНИМАЕМ ЛОКАЛЬНУЮ ВЕРИСЮ ДАННЫХ  ТУТ В ОБА ПОДНИМЕМ И  ЛОКАЛЬНУЮ И СЕРВРНУЮ ТАК КАК ВЫБРАНО СРУЗА ОБРА РЕЖИМА


                                /////////
                                contentValuesДляSQLBuilder_Для_GRUD_Операций.put("localversionandroid", СгенерированованныйДата);
                                /////////
                                ПолеНаОснованииКотрогоУвеличиваемВерсиюДанных = "localversionandroid_version";
                                ///////////////////--old ВЕРСИЯ ПОВЫШЕНИЯ ДАННЫХ


/*
                                РезультатУвеличинаяВерсияДАных=
                                        МетодПолученияУвеличинойВесрииДанныхДляТекущейТолькоДляСистемнойТаблицы_MODIFITATION_Client(
                                                НазваниеОбрабоатываемойТаблицы.toLowerCase(),"localversionandroid_version",contextСозданиеБАзы
                                                ,getБазаДанныхДЛяОперацийВнутри);*/

                                // TODO: 13.01.2022
                                Log.w(this.getClass().getName(), "  ПередоваемоеЗначенияДляТаблицы_MODIFITATION_Client_КотороеНадоЗаписать"
                                        + ПередоваемоеЗначенияДляТаблицы_MODIFITATION_Client_КотороеНадоЗаписать);

                                ///
                                contentValuesДляSQLBuilder_Для_GRUD_Операций.put(ПолеНаОснованииКотрогоУвеличиваемВерсиюДанных,
                                        ПередоваемоеЗначенияДляТаблицы_MODIFITATION_Client_КотороеНадоЗаписать);


                                /////////TODO ВТОРАЯ ОПЕРАЦИЯ ПОДИНМАЕТ СЕРВЕРНУЮ ВЕРИСЮ
                                contentValuesДляSQLBuilder_Для_GRUD_Операций.put("versionserveraandroid", СгенерированованныйДата);
                                //
                                /////////
                                ПолеНаОснованииКотрогоУвеличиваемВерсиюДанных = "versionserveraandroid_version";
                                ////
               /*                 РезультатУвеличинаяВерсияДАных=
                                        МетодПолученияУвеличинойВесрииДанныхДляТекущейТолькоДляСистемнойТаблицы_MODIFITATION_Client(
                                                НазваниеОбрабоатываемойТаблицы.toLowerCase(),"localversionandroid_version",contextСозданиеБАзы
                                                ,getБазаДанныхДЛяОперацийВнутри);*/

                                // TODO: 13.01.2022
                                Log.w(this.getClass().getName(), "  ПередоваемоеЗначенияДляТаблицы_MODIFITATION_Client_КотороеНадоЗаписать" +
                                        ПередоваемоеЗначенияДляТаблицы_MODIFITATION_Client_КотороеНадоЗаписать);

                                contentValuesДляSQLBuilder_Для_GRUD_Операций.put(ПолеНаОснованииКотрогоУвеличиваемВерсиюДанных,
                                        ПередоваемоеЗначенияДляТаблицы_MODIFITATION_Client_КотороеНадоЗаписать);
                                //

                                break;


                            default:
                                throw new NullPointerException("Не выбран не одтн режим");


                        }


// TODO: 30.08.2021  СОЗДАНИЕ ТРИГЕРА


                        ////

                        /////
                        String ТаблицаОбработки = "MODIFITATION_Client";

                        SQLBuilder_Для_GRUD_Операций.setTables(ТаблицаОбработки.toLowerCase());


                        // TODO: 19.11.2021 srart transation
                        if (!getБазаДанныхДЛяОперацийВнутри.inTransaction()) {

                            getБазаДанныхДЛяОперацийВнутри.beginTransaction();
                        }

                        /////////////
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            ////////
                            changesvesiondata = SQLBuilder_Для_GRUD_Операций.
                                    update(getБазаДанныхДЛяОперацийВнутри,
                                            contentValuesДляSQLBuilder_Для_GRUD_Операций, "name=?", new String[]{НазваниеОбрабоатываемойТаблицы.toLowerCase()});
                        } else {
                            ///////////////

                            changesvesiondata = getБазаДанныхДЛяОперацийВнутри
                                    .update(ТаблицаОбработки, contentValuesДляSQLBuilder_Для_GRUD_Операций, "name=?", new String[]{НазваниеОбрабоатываемойТаблицы.toLowerCase()});
                        }

                        // TODO: 22.11.2021  ПОСЛЕ УСПЕШНОЙ ОПЕРАЦИИ ПОДТВЕРЖДАЕМ ТРАНЗАУЙИЮ

                        // TODO: 19.11.2021 srart transation
                        if (getБазаДанныхДЛяОперацийВнутри.inTransaction()) {

                            getБазаДанныхДЛяОперацийВнутри.setTransactionSuccessful();

                            getБазаДанныхДЛяОперацийВнутри.endTransaction();
                        }


                        // TODO: 19.11.2021 close transaction

                        //
                        Log.d(this.getClass().getName(), "  СОЗДАН ТРИГЕР ДЛЯ ТАБЛИЦЫ  tabels  update     " + СгенерированованныйДата);


                        Log.d(this.getClass().getName(), "РезультатИзменениеВерсииДанных " + changesvesiondata +
                                " результат изменения данных Integer.parseInt(changesvesiondata.toString()) " + Integer.parseInt(changesvesiondata.toString()));


                        return changesvesiondata;
                    }
                };


                //TODO конец выполения кода через Callble  , отправляем его в главный менеджер пОТОКОВ

                Changesvesiondata = new ClassRuntimeExeGRUDOpertions(context).МетодЗапускаОперацийGRUD_exe(ЛистДляGRUDопераций, МенеджерПотоков);

                ///
                ///
                Log.w(context.getClass().getName(), " РЕЗУЛЬТАТ changesvesiondata  ИЗМЕНЕНИЯ ДАННЫХ  ЗНАЧЕНИЯ  " + Changesvesiondata.toString());
                /////


// TODO: 29.10.2021 ВЫКЛЮЧАЕМ БАЗУ


            } catch (Exception e) {
                ///////TODO error
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());

                new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                /////
            }
            //////// TODO конец  менеджера потоков

            return Changesvesiondata;
        }
///// todo конец первой повышени  данных


    }

    //TODO КЛАСС ПОЛУЧЕНИЕ ДАННЫХ Free
    public class  GetаFreeData extends Class_GRUD_SQL_Operations {
        Context context;
        public GetаFreeData(@NotNull Context context) {
            super(context);
            this.context=context;
            SQLBuilder_Для_GRUD_Операций =new SQLiteQueryBuilder    ();
        }
        // TODO: 30.08.2021  НИЖЕ  УКАЗАНЫ ВСЕ МЕТОДЫ ПОЛУЧЕНИЕ ОБНОВЛЕНИЯ ВСТАВКИ УДАЛЕНИЕ ДАННЫХ
        // TODO: 27.08.2021 МЕТОД  ПОЛУЧЕНИЯ ДАННЫХ
        public Object getfreedata(Map<String, Object> concurrentHashMap, CompletionService МенеджерПотоков, SQLiteDatabase getБазаДанныхДЛяОперацийВнутри) throws ExecutionException, InterruptedException {
            ///////
            Object GetFreedata=null;
            //////// TODO запуск менеджера потоков
            // TODO: 29.08.2021 СОЗДАЕМ ЗАДАЧУ ДЛЯ ВЫПОЛЕНИЯ ЧЕРЕЗ CALLABLE
            ЛистДляGRUDопераций=new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    Object getfreedata=null;
                    try {
                        // TODO: 27.08.2021 парарменты
                        String НазваниеОбрабоатываемойТаблицы=null;
                        //
                        String    СамFreeSQLКОд=null;
                        Log.w(context.getClass().getName(), "concurrentHashMap " + concurrentHashMap.values());
                        //TODO ЦИКЛ ПО ПАРАМЕТРАМ
                        for (Object КлючconcurrentHashMap : concurrentHashMap.keySet()) {
                            Object ЗначениеconcurrentHashMap = concurrentHashMap.get(КлючconcurrentHashMap);
                            Log.w(context.getClass().getName(), "concurrentHashMap.toString() " + concurrentHashMap.toString());
                            // TODO: 27.08.2021  присваевываем значения полям для получение данных
                            switch (КлючconcurrentHashMap.toString().trim()){
                                case "НазваниеОбрабоатываемойТаблицы" :
                                    //////
                                    НазваниеОбрабоатываемойТаблицы=ЗначениеconcurrentHashMap.toString();
                                    break;
                                ///////
                                case "СамFreeSQLКОд" :
                                    СамFreeSQLКОд=ЗначениеconcurrentHashMap.toString();
                                    break;
                            }

                        }
                        // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
                        if (getБазаДанныхДЛяОперацийВнутри!=null) {
                            getfreedata= (SQLiteCursor) getБазаДанныхДЛяОперацийВнутри.rawQuery(СамFreeSQLКОд, null);
                        }
                        Log.d(this.getClass().getName(), "getfreedata "+getfreedata  );
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    return getfreedata;
                }
            };
            GetFreedata=     new ClassRuntimeExeGRUDOpertions(context).МетодЗапускаОперацийGRUD_exe(ЛистДляGRUDопераций,МенеджерПотоков);
            Log.d(this.getClass().getName(), "   рЕЗУЛЬТАТ МЕТОДА ПОЛУЧЕНИЕ ДАННЫХ ЧЕРЕЗ ОДИН МЕТДЖЕР ПОТОКОВ  GetFreedata "+GetFreedata);
            return GetFreedata;
        }
    }
// TODO: 17.09.2021  END GETDATA



    // TODO: 28.08.2021 гдавный метод выполения операций EXEEXEEXEEXEEXEEXEEXEEXEEXEEXEEXEEXEEXEEXEEXEEXEEXEEXEEXEEXEEXEEXEEXEEXEEXEEXEEXEEXE
  public   class ClassRuntimeExeGRUDOpertions extends Class_GRUD_SQL_Operations {
        //////
        Context context;

        public ClassRuntimeExeGRUDOpertions(@NotNull Context context) {
            super(context);
            this.context = context;
        }

        // TODO: 27.08.2021  МЕТОД ИСПОЛЕНИЯ В ПОТОКЕ
        public Object МетодЗапускаОперацийGRUD_exe(Callable<Object> ЗадачаКоторуюнадоВыполнить, CompletionService МенеджерПотоков)
                throws ExecutionException, InterruptedException {
            Object ФинальныйПолученныйРезультатОперацийGRUD = null;
            try {
                // TODO: 28.08.2021  ЗАПУСКАЕМ ЗАДАЧУ
                МенеджерПотоков.submit(ЗадачаКоторуюнадоВыполнить);
                Log.w(context.getClass().getName(), "ФИНАЛ   МЕНЕДЖЕРА ПОТОКОВ " +
                        " GRUD-ОПЕРАЦИЙ  EXE add Callable  ЗадачаКоторуюнадоВыполнить " + ЗадачаКоторуюнадоВыполнить);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                МенеджерПотоков.take().cancel(false);
            } finally {
                try {
                    ФинальныйПолученныйРезультатОперацийGRUD = (Object) МенеджерПотоков.take().get();
                    Log.w(context.getClass().getName(),
                            " ФИНАЛ   .GET()  МЕНЕДЖЕРА ПОТОКОВ  GRUD-ОПЕРАЦИЙ     "
                                    + ФинальныйПолученныйРезультатОперацийGRUD + "\n" +
                                    "  .GET() размер ответа  " + ФинальныйПолученныйРезультатОперацийGRUD.toString().length());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(context).recordnewerror(e.toString(),
                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                    МенеджерПотоков.take().cancel(false);
                }
            }
            return ФинальныйПолученныйРезультатОперацийGRUD;
        }




    }
}

    //TODO   конец КЛАСС Удаление ДАННЫХ



// TODO: 12.08.2021  метода повышает ВЕРСИЮ ДАННЫ Х ПОСЛЕ УСПЕШНОЙ СИНХРОНИАЗЦИИ ТАБЛИЦЫ
























