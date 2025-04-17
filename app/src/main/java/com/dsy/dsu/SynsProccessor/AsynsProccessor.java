package com.dsy.dsu.SynsProccessor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.AllDatabases.SQLTE.GetSQLiteDatabase;
import com.dsy.dsu.BusinessLogicAll.GetPublicID.GetPublicID;
import com.dsy.dsu.BusinessLogicAll.Class_MODEL_synchronized;
import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;
import com.dsy.dsu.Hilt.JbossAdrress.getHiltPortJbossInterface;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;

import javax.net.ssl.SSLSocketFactory;

import dagger.hilt.EntryPoints;

public class AsynsProccessor extends Class_MODEL_synchronized {
    // TODO: 28.07.2022  переменые
    public Context context;






    public SQLiteDatabase sqLiteDatabase ;
    public SharedPreferences preferences;
    public Integer  ПубличныйIDДляФрагмента=0;

    public  String КлючДляFirebaseNotification = "2a1819db-60c8-4ca3-a752-1b6cd9cadfa1";
    public   ObjectMapper jsonGenerator;

    public SSLSocketFactory getsslSocketFactory2;



    public Integer getHiltPublicId;

    // TODO: 28.07.2022
    public AsynsProccessor(@NotNull Context context,@NonNull  ObjectMapper jsonGenerator,
                           @NotNull  SSLSocketFactory getsslSocketFactory2,
                           @NonNull   Integer getHiltPublicId) {
        super(context);
        this.context=context;
        this.   sqLiteDatabase=    GetSQLiteDatabase.SqliteDatabase();
        this.jsonGenerator=    jsonGenerator;
        this.getsslSocketFactory2=    getsslSocketFactory2;
        this.getHiltPublicId=    getHiltPublicId;
        // TODO: 12.04.2024  
        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                + " getHiltPublicId " + getHiltPublicId+"sqLiteDatabase" + sqLiteDatabase);


    }




// TODO: 19.08.2021  ДАННЫЙ МЕТОД ЗАПУСКАЕТ СИНХРОНИЗЦИ ДЛЯ ЧАТА

// TODO: 08.09.2021 НИЖЕ НАЧИНАЮТЬСЯ МЕТОДЫ САМОЙ ФОНОВОЙ СИНХРОНИАЗЦИИ ВНИМАНИЕ !!!!!!!
// TODO: 08.09.2021 НИЖЕ НАЧИНАЮТЬСЯ МЕТОДЫ САМОЙ ФОНОВОЙ СИНХРОНИАЗЦИИ ВНИМАНИЕ !!!!!!!
// TODO: 08.09.2021 НИЖЕ НАЧИНАЮТЬСЯ МЕТОДЫ САМОЙ ФОНОВОЙ СИНХРОНИАЗЦИИ ВНИМАНИЕ !!!!!!!
    // TODO: 08.09.2021 НИЖЕ НАЧИНАЮТЬСЯ МЕТОДЫ САМОЙ ФОНОВОЙ СИНХРОНИАЗЦИИ ВНИМАНИЕ !!!!!!!
// TODO: 08.09.2021 НИЖЕ НАЧИНАЮТЬСЯ МЕТОДЫ САМОЙ ФОНОВОЙ СИНХРОНИАЗЦИИ ВНИМАНИЕ !!!!!!!
    // TODO: 08.09.2021 НИЖЕ НАЧИНАЮТЬСЯ МЕТОДЫ САМОЙ ФОНОВОЙ СИНХРОНИАЗЦИИ ВНИМАНИЕ !!!!!!!










































//////ПЕРВЫЙ МЕТОД ОБМЕНА ДАННЫМИ С СЕРВЕРОМ МЕТОД GET

    public Long МетодНачалоСихронизациивФоне(@NotNull Context context)   {
        Long результатСинхрониазции=0l;
        try {
            ////САМАЯ ПЕРВАЯ КОМАНДА НАЧАЛА ОБМНЕНА ДАННЫМИ///// TODO ГЛАВНЫЙ МЕТОД ОБМЕНА ДАНЫМИ  НА АКТИВИТИ FACE_APP
            Integer getPublicID = new GetPublicID().getPublicIDAllApp(context);
            //TODO
            if (getPublicID > 0) {

                результатСинхрониазции = МетодПолучениеСпискаТаблицДляОбменаДанными(getPublicID);
            }


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " результатСинхрониазции " + результатСинхрониазции+ " getPublicID "+getPublicID);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(this.context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return    результатСинхрониазции;
    }








































    ////////////МЕТОД ПОЛУЧЕНИЕ  ВЕРСИИ ДАННЫХ
    Long МетодПолучениеСпискаТаблицДляОбменаДанными(@NonNull Integer   getPublicID)
            throws JSONException, InterruptedException, ExecutionException, TimeoutException {///второй метод получаем версию данных на СЕРВЕР ЧТОБЫ СОПОЧТАВИТЬ ДАТЫ

        Long РезультатСинхронизации=0l;
        try {
            Log.d(this.getClass().getName(), "   ID  getPublicID" +   getPublicID);
            preferences=  context .getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
            LinkedHashMap<Integer,String> getHiltPortJboss=   EntryPoints.get(context, getHiltPortJbossInterface.class).getHiltPortJboss();

// TODO: 02.04.2024  Адресс и Порт Сервера Jboss 
            String   ИмяСерверИзХранилица = getHiltPortJboss.values().stream().map(m->String.valueOf(m)).findFirst().get();
            Integer    ПортСерверИзХранилица = getHiltPortJboss.keySet().stream().mapToInt(m->m).findFirst().getAsInt();


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ИмяСерверИзХранилица " + ИмяСерверИзХранилица+
                    " ПортСерверИзХранилица " +ПортСерверИзХранилица );


            // TODO: 10.11.2022 Получение Список Таблиц
            StringBuffer       BufferGetVersionData = МетодУниверсальныйСервернаяВерсияДанныхДанныесСервера(
                    "view_alltablesversion",
                    "application/gzip",
                    "Хотим Получить Версию Данных Сервера",///"Хотим Получить Версию Данных Сервера" б //TODO "Хотим Получить Статус Блокировки Пользователя по ID"
                    0l,
                    getPublicID,
                    ИмяСерверИзХранилица ,
                    ПортСерверИзХранилица,getsslSocketFactory2);

            Log.d(this.getClass().getName(), " BufferGetVersionData.toString().toCharArray().length "
                    + BufferGetVersionData.toString().toCharArray().length);
            // TODO: 03.09.2021
            if (BufferGetVersionData != null) {
                if (BufferGetVersionData.toString().toCharArray().length > 3
                        && ! BufferGetVersionData.toString().trim().matches("(.*)Server Running...... Don't Login(.*)")) {
                    Log.d(this.getClass().getName(), "  getHiltPublicId  " + getHiltPublicId +
                            " BufferGetVersionData " + BufferGetVersionData.toString());


                    //TODO Таблицы ОТ  Сервера
                    CopyOnWriteArrayList<ConcurrentHashMap<String, String>> getBufferFromJbossServerAllTables =
                            jsonGenerator.readValue(BufferGetVersionData.toString(),
                            new TypeReference<CopyOnWriteArrayList<ConcurrentHashMap<String, String>>>() {
                            });
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " getBufferFromJbossServerAllTables " +getBufferFromJbossServerAllTables);


                    //TODO Таблицы ОТ  Андройда
                 //   CopyOnWriteArrayList<String>      getMainTabelAllAndroid = new PUBLIC_CONTENT(context).getWorkerTablesALl(context);

                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"   + " getBufferFromJbossServerAllTables " +getBufferFromJbossServerAllTables);

// TODO: 25.09.2024  Запускаем ГЛАВНУЮ ОБРБАБОТКУ по таблицам
                    РезультатСинхронизации   = МетодГлавныхЦиклТаблицДляСинхронизации(getPublicID,getBufferFromJbossServerAllTables);


                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " getBufferFromJbossServerAllTables.size() " + getBufferFromJbossServerAllTables.size());


                }
            }else {
                Log.i(this.getClass().getName(), " НЕт данных с сервера  BufferGetVersionData " + BufferGetVersionData );

                Log.i(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " НЕт данных с сервера  BufferGetVersionData " + BufferGetVersionData);


            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РезультатСинхронизации;
    }



// TODO: 10.09.2021  запускаем метод обработки по таблицам

































    
    
    
    
    
    
    


















































    // TODO: 04.11.2021  метод ПОСЫЛАЕМ ТОЛЬКО NULL В ПОЛЕ ID  НА СЕРВЕР


























    @SuppressLint("SuspiciousIndentation")
    Long МетодГлавныхЦиклТаблицДляСинхронизации(@NonNull Integer PublicID,
                                                @NonNull    CopyOnWriteArrayList<ConcurrentHashMap<String, String>> getBufferFromJbossServerAllTables) {//КонтекстСинхроДляКонтроллера
        // TODO: 07.04.2024
       AtomicLong ResultatSync =new AtomicLong(0l);
        try {
            Log.i(this.getClass().getName(), " PublicID "
                    +  PublicID.toString()
                    + " getBufferFromJbossServerAllTables "
                    +  getBufferFromJbossServerAllTables.toString());


            LinkedHashMap<Integer,String> getHiltPortJboss=    EntryPoints.get(context, getHiltPortJbossInterface.class).getHiltPortJboss();
            /*
//TODO :  Реальная Работа
 */

// TODO: 21.08.2023 ГЛАВНЫЙ ЦИКЛ СИХРОНИАЗЦИИ
            // TODO: 21.08.2023  только  Параллено
            ResultatSync.set(       new ProccesorparallelSynch( context,
                    jsonGenerator,
                    getsslSocketFactory2,
                    getBufferFromJbossServerAllTables,
                    PublicID,getHiltPortJboss).startingAsyncParallels());

            // TODO: 08.04.2024
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                    + "\n" + "parallel"+" ResultatSync.get() " + ResultatSync.get());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                    Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber()  );
        }
        return  ResultatSync.get();
    }




}