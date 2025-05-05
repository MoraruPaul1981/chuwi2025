package com.dsy.dsu.BusinessLogicAll;

import static java.util.Calendar.getInstance;

import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dsy.dsu.CnangeServers.PUBLIC_CONTENT;
import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;
import com.dsy.dsu.Hilt.Sqlitehilt.AppModuleSQLlite;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import dagger.hilt.EntryPoints;

public class Class_Generation_UUID {
 private    Context context;
    private  Integer ПубличныйID =0;

    private SQLiteDatabase sqLiteDatabase ;
    public Class_Generation_UUID(Context context) {
        this.context = context;
///////TODO
        // TODO: 16.04.2025
        sqLiteDatabase = EntryPoints.get(context, AppModuleSQLlite.class).getAppModuleSQLlite();
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " sqLiteDatabase " +sqLiteDatabase);
    }
    public Long МетодГенерацииUUID() {
        Long UUID = 0l;
        try {
            if (context!=null) {
                // TODO ГЕНЕРАЦИЯ UUID ВВИДЕ ЦИФРЫ
                //"yyyyMMddHHmmssSSS" //"EEEEE MMMMM yyyy HH:mm:ss.SSSSSSZ"
                Date Дата = getInstance().getTime();
                // DateFormat dateFormat = new SimpleDateFormat("yyyyddMMHHmmssSS", new Locale("ru"));
                DateFormat dateFormat = new SimpleDateFormat("yyddMMHHmmssS", new Locale("ru"));
                dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
                String СгенерированоДатаДляUUIDШагПервый = dateFormat.format(Дата);
                Long СгенерированоДатаВТипеLong = Long.parseLong(СгенерированоДатаДляUUIDШагПервый);

                //todo гененируем если есть публичный id
                // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
                Class_GRUD_SQL_Operations class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ= new Class_GRUD_SQL_Operations(context);
                class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.concurrentHashMapНабор.put("СамFreeSQLКОд",
                        " SELECT id  FROM successlogin  ORDER BY date_update DESC ;");
                // TODO: 12.10.2021  Ссылка Менеджер Потоков
                PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new PUBLIC_CONTENT(context);

                SQLiteCursor            Курсор_Получаемsuccesslogin= (SQLiteCursor) class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.
                        new GetаFreeData(context).getfreedata(class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.concurrentHashMapНабор,
                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabase);

                if(Курсор_Получаемsuccesslogin.getCount()>0){
                    Курсор_Получаемsuccesslogin.moveToFirst();
                   ПубличныйID =         Курсор_Получаемsuccesslogin.getInt(0);
                    Log.d(this.getClass().getName(), " ID  " + ПубличныйID);
                }

                // TODO: 24.03.2021 ЕслиВубличногоНЕтТоНАходим ЕГо
                if ( ПубличныйID >0) {
                    String  ПубличноеIDПолученныйИзСервлетаДляUUIDПромежуточный=String.valueOf(ПубличныйID);
                    String  СгенерированоДатаВТипеLongПромежуточный=String.valueOf(СгенерированоДатаВТипеLong);

                    // TODO: 02.08.2023  генерируем  UUID
                    String UUIDПромкжеточный = ПубличноеIDПолученныйИзСервлетаДляUUIDПромежуточный+СгенерированоДатаВТипеLongПромежуточный;
                    BigInteger UUIDПромежуточныйBig= new BigInteger(UUIDПромкжеточный);
                    UUID=        UUIDПромежуточныйBig.longValue();
                    Log.w(this.getClass().getName(), "   UUIDФинал " + UUID);
                }
                Курсор_Получаемsuccesslogin.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        // TODO: 06.09.2021  новый UUID
        return UUID;
    }

}
