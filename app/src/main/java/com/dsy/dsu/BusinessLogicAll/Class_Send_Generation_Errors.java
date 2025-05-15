package com.dsy.dsu.BusinessLogicAll;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.util.Log;


import com.dsy.dsu.CnangeServers.BinessLogicPublicContent;

import java.util.Date;
import java.util.concurrent.ExecutionException;

public class Class_Send_Generation_Errors {
    Context context;
    Activity activity;
    //

    public Class_Send_Generation_Errors(Context context, String СамаОшибка, Activity activityВнутри) {
        this.context =context;
        activity=   activityВнутри;
        // TODO: 16.04.2025
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() );

        StringBuffer БуферОшибкаПриПодключениекСерверуДляАунтификацииПользователяПриВходе=new StringBuffer(СамаОшибка);



        // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
        Class_GRUD_SQL_Operations class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ= new Class_GRUD_SQL_Operations(this.context);
        ///
        class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.concurrentHashMapНабор.put("СамFreeSQLКОд",
                " SELECT id  FROM successlogin  ORDER BY date_update DESC ;");

        Integer   ПубличноеIDПолученныйИзСервлетаДляUUID=0;

        // TODO: 12.10.2021  Ссылка Менеджер Потоков

        BinessLogicPublicContent Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new BinessLogicPublicContent(this.context);


        ///////
        SQLiteCursor Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО= null;
        try {
            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО = (SQLiteCursor) class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.
                    new GetаFreeData(this.context).getfreedata(class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.
                            concurrentHashMapНабор,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabase);
        } catch (ExecutionException executionException) {
            executionException.printStackTrace();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }


        if(Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount()>0){
            ////
            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();

            /////
            ПубличноеIDПолученныйИзСервлетаДляUUID=         Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getInt(0);
///


            Log.d(this.getClass().getName(), " ID  " + ПубличноеIDПолученныйИзСервлетаДляUUID);


        }





    }
    //функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО ВСЕЙ ПРОГРАММЕ
    // TODO: 21.10.2021


}
