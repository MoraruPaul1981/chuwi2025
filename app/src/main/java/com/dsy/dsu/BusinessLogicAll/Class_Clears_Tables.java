package com.dsy.dsu.BusinessLogicAll;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.dsy.dsu.BusinessLogicAll.DATE.Class_Generation_Data;

import com.dsy.dsu.BusinessLogicAll.SharedPreferences.GetSharedPreferences;
import com.dsy.dsu.CnangeServers.PUBLIC_CONTENT;
import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;
import com.dsy.dsu.Hilt.Sqlitehilt.AppModuleSQLlite;
import com.dsy.dsu.Passwords.MainActivityPasswords;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.crypto.NoSuchPaddingException;

import dagger.hilt.EntryPoints;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Class_Clears_Tables {
    Context context;
      Handler handlerУдалениеТаблицПринудительно;

    private SQLiteDatabase sqLiteDatabase ;

    private  ProgressDialog progressDialogДляУдалениеТаблиц;
    // TODO: 24.02.2022
    public Class_Clears_Tables(Context context, Handler handlerУдалениеТаблицПринудительно, ProgressDialog progressDialogДляУдалениеТаблиц) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        this.context = context;
        // TODO: 16.04.2025
        sqLiteDatabase = EntryPoints.get(context, AppModuleSQLlite.class).getAppModuleSQLlite();
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " sqLiteDatabase " +sqLiteDatabase);
        this.handlerУдалениеТаблицПринудительно = handlerУдалениеТаблицПринудительно;
        this.progressDialogДляУдалениеТаблиц=progressDialogДляУдалениеТаблиц;
    }


    // TODO: 24.04.2023 Метод Семны ДАнных Пользователя
    public Integer методСменаДанныхПользователя(Context context,
                                                CompletionService МенеджерПотоковВнутрений,
                                                Activity activity)
            throws ExecutionException, InterruptedException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {

        CopyOnWriteArrayList<String> ИменаТаблицыОтАндройда=    new PUBLIC_CONTENT(context).getWorkerTablesALl(context);
        ИменаТаблицыОтАндройда.add("successlogin");
        ИменаТаблицыОтАндройда.add("settings_tabels");
        ИменаТаблицыОтАндройда.add("errordsu1");
        ArrayList<Integer>   РезультатСменыДанных=new ArrayList<>();
        // TODO: 24.02.2022
                Log.d(this.getClass().getName()," ИменаТаблицыОтАндройда   "+ИменаТаблицыОтАндройда);
              try {

                  Observable.fromIterable(ИменаТаблицыОтАндройда)
                          .subscribeOn(Schedulers.single())
                          .filter(e->!e.equalsIgnoreCase("fio"))
                          .filter(e->!e.equalsIgnoreCase("cfo"))
                          .filter(e->!e.equalsIgnoreCase("organization"))
                          .filter(e->!e.equalsIgnoreCase("depatment"))
                          .filter(e->!e.equalsIgnoreCase("region"))
                          .filter(e->!e.equalsIgnoreCase("nomen_vesov"))
                          .filter(e->!e.equalsIgnoreCase("type_materials"))
                          .filter(e->!e.equalsIgnoreCase("track"))
                          .filter(e->!e.equalsIgnoreCase("company"))
                          .filter(e->!e.equalsIgnoreCase("prof"))
                          .filter(e->!e.equalsIgnoreCase("type_materials"))
                          .filter(e->!e.equalsIgnoreCase("nomen_vesov"))
                          .filter(e->!e.equalsIgnoreCase("view_onesignal"))
                          .filter(e->!e.equalsIgnoreCase("vid_tc"))
                          .concatMap(i -> Observable.just(i).delay(500, TimeUnit.MILLISECONDS))
                          .doOnNext(new Consumer<String>() {
                              @Override
                              public void accept(String текущаяТаблицаДляУдваления) throws Throwable {
                                  // TODO: 09.09.2021 DELETE УДАЛЕНИЕ ТАБЛИЦ ПЕРЕД УМЕНЫ ПОЛЬЗОВАТЕЛЯ
                                  Integer РезультатУдалениеДанных=
                                          методСменыДанныхПользователя( текущаяТаблицаДляУдваления.toString().trim(),
                                                  context);
                                  Log.d(this.getClass().getName(), "РезультатУдалениеДанных " + РезультатУдалениеДанных+ " текущаяТаблицаДляУдваления "
                                          +текущаяТаблицаДляУдваления);
                                  // TODO: 09.09.2021  действие второе добалянеим дату
                                  РезультатСменыДанных.add(РезультатУдалениеДанных);

                                  Integer РезультатУдалениеMODIFITATION_Client=
                                          методСменыДанныхMODIFITATION_Client( текущаяТаблицаДляУдваления.toString().trim(),
                                                  context);

                                  Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                          " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                          " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                              }
                          }).doAfterNext(new Consumer<String>() {
                              @Override
                              public void accept(String string) throws Throwable {
                                  // TODO: 24.04.2023 Конец Цикла
                                  activity.runOnUiThread(()->{
                                      progressDialogДляУдалениеТаблиц.setMessage("Удаление таблицы ..."+string + " ("+РезультатСменыДанных.size()+")");
                                  });

                                  Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                          " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                          " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                              }
                          }).doOnComplete(new Action() {
                              @Override
                              public void run() throws Throwable {
                                  // TODO: 19.02.2025
                                  activity.runOnUiThread(()->{
                                      if (РезультатСменыДанных.size()>0) {
                                          методПослеСменыДанныхЗапускаемСНАчала(activity);
                                      }
                                      progressDialogДляУдалениеТаблиц.dismiss();
                                      progressDialogДляУдалениеТаблиц.cancel();
                                  });

                                      wretingNewVaueSuccess(РезультатСменыДанных.size());

                                  Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                          " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                          " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                              }
                          }).subscribeOn(Schedulers.single())
                          .observeOn(AndroidSchedulers.mainThread())
                          .subscribe();

          } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new RecordNewErros(context).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());

            }
        return   РезультатСменыДанных.size();
    }


    // TODO: 24.02.2022

 public void методПослеСменыДанныхЗапускаемСНАчала(@NonNull Activity activity) {
        Intent Интент_Меню=new Intent();
        try {
                   Toast.makeText(activity, " Успешное смена данных !!! "    , Toast.LENGTH_SHORT).show();;
                    /////TODO ЗАПУСКАМ ОБНОЛВЕНИЕ ДАННЫХ С СЕРВЕРА ПЕРЕРД ЗАПУСКОМ ПРИЛОЖЕНИЯ ВСЕ ПРИЛОЖЕНИЯ ДСУ-1
                    Интент_Меню.setClass(activity, MainActivityPasswords.class); //MainActivity_Visible_Async //MainActivity_Face_App_OLd
                    Интент_Меню.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP  );//////FLAG_ACTIVITY_SINGLE_TOP
                    activity. startActivity(Интент_Меню);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread()
                    .getStackTrace()[2].getMethodName() + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(activity).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }



    // TODO: 09.09.2021 delete data for tabels
    protected Integer методСменыДанныхПользователя(String ИмяТаблицы, Context context) {
//
        Integer СменаДанных = 0;
        try {
            Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecurrentoperations/"+ИмяТаблицы+"");
            Log.d(this.getClass().getName(), "  ИмяТаблицы "+ИмяТаблицы+"" );

            ContentResolver contentResolver=context.getContentResolver();
            СменаДанных=  contentResolver.delete(uri,null,null);
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    ///
                    Log.d(context.getClass().getName(), " РезультатУдалениеОчисткиТаблиц" + "--" + СменаДанных));/////
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return СменаДанных;
    }


    // TODO: 09.09.2021 delete ТАБЛИЦЫ successlogin
    public Integer методОчисткаТаблицыSuccesslogin(String ИмяТаблицы, Context context) throws ExecutionException, InterruptedException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
//
        Integer СменаДанных = 0;
        try {
            Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecurrentoperations/"+ИмяТаблицы+"");
            Log.d(this.getClass().getName(), "  ИмяТаблицы "+ИмяТаблицы+"" );

            ContentResolver contentResolver=context.getContentResolver();
            СменаДанных=  contentResolver.delete(uri,null,null);

            if (СменаДанных>0) {
                context.getContentResolver().notifyChange(uri, null);
            }


            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " СменаДанных " +СменаДанных );

        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return СменаДанных;
    }

    // TODO: 09.09.2021 delete data for tabels
    protected Integer методСменыДанныхMODIFITATION_Client(String ИмяТаблицы, Context context) {
//
        Integer РезультатУдалениеОчисткиТаблиц = 0;
        try {
            Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecurrentoperations/MODIFITATION_Client ");
            Log.d(this.getClass().getName(), "  ИмяТаблицы MODIFITATION_Client  " );
            ContentValues contentValuesСменаДанных=new ContentValues();
            String Дата =     new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанныхДОП();


            contentValuesСменаДанных.put("localversionandroid", "1901-01-10 00:00:00");
            contentValuesСменаДанных.put("versionserveraandroid", "1901-01-10 00:00:00");

            contentValuesСменаДанных.put("localversionandroid_version", 0);
            contentValuesСменаДанных.put("versionserveraandroid_version", 0);


            ContentResolver contentResolver=context.getContentResolver();
          Integer  СменаДанных=  contentResolver.update(uri, contentValuesСменаДанных,"name=?",new String[]{String.valueOf(ИмяТаблицы)});
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
            ///
            Log.d(context.getClass().getName(), " РезультатУдалениеОчисткиТаблиц" + "--" + РезультатУдалениеОчисткиТаблиц));/////
        } catch (SQLException e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РезультатУдалениеОчисткиТаблиц;
    }





    // TODO: 09.09.2021  метод добалвние в таблице даты

    Integer МетодПослеУдаленияДобавляемДатуВерсииИОбнуляемВерсиюДАнныхвТаблицеMODIFITATION_Client(String ИмяТаблицы, CompletionService МенеджерПотоковВнутрений) {
        Integer ДобавлениеДатыПослеУдалниеТаблиц = 0;
        try {
            ContentValues contentValuesОчисткаТаблицДобавлениеДат = new ContentValues();
            contentValuesОчисткаТаблицДобавлениеДат.put("localversionandroid", "1900-01-10 00:00:00");
            contentValuesОчисткаТаблицДобавлениеДат.put("versionserveraandroid", "1900-01-10 00:00:00");
            contentValuesОчисткаТаблицДобавлениеДат.put("localversionandroid_version", 0);
            contentValuesОчисткаТаблицДобавлениеДат.put("versionserveraandroid_version", 0);
            Class_GRUD_SQL_Operations  class_grud_sql_operationsПослеУдаленияДобавляемДатуВерсии=new Class_GRUD_SQL_Operations(context);
            class_grud_sql_operationsПослеУдаленияДобавляемДатуВерсии.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","MODIFITATION_Client");
            class_grud_sql_operationsПослеУдаленияДобавляемДатуВерсии.concurrentHashMapНабор.put("Флаг_ЧерезКакоеПолеОбновлением","name");
            class_grud_sql_operationsПослеУдаленияДобавляемДатуВерсии.concurrentHashMapНабор.put("ЗначениеФлагОбновления",ИмяТаблицы);
            class_grud_sql_operationsПослеУдаленияДобавляемДатуВерсии.
                    concurrentHashMapНабор.put("ЗнакФлагОбновления","=");
            ////TODO КОНТЕЙНЕР ДЛЯ ОБНОВЛЕНИЯ
            class_grud_sql_operationsПослеУдаленияДобавляемДатуВерсии.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(contentValuesОчисткаТаблицДобавлениеДат);
            ///TODO РЕЗУЛЬТАТ ОБНОВЛЕНИЕ ДАННЫХ
            ДобавлениеДатыПослеУдалниеТаблиц= (Integer)  class_grud_sql_operationsПослеУдаленияДобавляемДатуВерсии.
                    new UpdateData(context).updatedata(class_grud_sql_operationsПослеУдаленияДобавляемДатуВерсии.concurrentHashMapНабор,
                    class_grud_sql_operationsПослеУдаленияДобавляемДатуВерсии.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                    МенеджерПотоковВнутрений,
                    sqLiteDatabase);
            Log.d(this.getClass().getName(), " сработала ...  обнуление версии в MODIFITATION_Client для таблицы " + ИмяТаблицы+
                    " ДобавлениеДатыПослеУдалниеТаблиц  " +ДобавлениеДатыПослеУдалниеТаблиц);

        } catch (SQLException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        ///////
        return ДобавлениеДатыПослеУдалниеТаблиц;
    }

    void wretingNewVaueSuccess(Integer РезультатОчистикТАблицИДобалениеДаты) {
        try {
            if (РезультатОчистикТАблицИДобалениеДаты >0) {

                new GetSharedPreferences(context).writinganewvaluePreferences();
            }
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                    + "\n" +" РезультатОчистикТАблицИДобалениеДаты " +РезультатОчистикТАблицИДобалениеДаты
            );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }
}