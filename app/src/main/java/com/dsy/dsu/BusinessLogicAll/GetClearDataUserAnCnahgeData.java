package com.dsy.dsu.BusinessLogicAll;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.dsy.dsu.BusinessLogicAll.SharedPreferences.GetSharedPreferences;
import com.dsy.dsu.BusinessLogicAll.WorkerTables.GetWorkerAndSystemTables;
import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;
import com.dsy.dsu.Passwords.View.MainActivityPasswords;
import com.sous.backasync.launch.ModuleDeleting;
import com.sous.backasync.launch.ModuleUpdating;


import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GetClearDataUserAnCnahgeData {
    private Context context;

    // TODO: 24.02.2022
    public GetClearDataUserAnCnahgeData(Context context) {
        this.context = context;
        // TODO: 16.04.2025
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }


    // TODO: 24.04.2023 Метод Семны ДАнных Пользователя
    public Integer changeTableWorkerUsers(Context context,
                                          Activity activity,
                                          ProgressDialog progressDialogДляУдалениеТаблиц) {
        ArrayList<Integer>   РезультатWorkerСменыДанных=new ArrayList<>();
              try {
                  CopyOnWriteArrayList<String> ИменаТаблицыWorker=    new GetWorkerAndSystemTables().getWorkerTablesALl(context);

                  Observable.fromIterable(ИменаТаблицыWorker)
                          .subscribeOn(Schedulers.single())
                          .observeOn(AndroidSchedulers.mainThread())
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
                          .concatMap(i -> Observable.just(i).delay(50, TimeUnit.MILLISECONDS))
                          .doOnNext(new Consumer<String>() {
                              @Override
                              public void accept(String текущаяТаблицаДляУдваления) throws Throwable {

                                  // TODO: 29.05.2025  Очистка Рабочих Таблиц
                                  Integer РезультатУдалениеДанных=
                                          методСменыДанныхПользователя( текущаяТаблицаДляУдваления.toString().trim(),
                                                  context);

                                  РезультатWorkerСменыДанных.add(РезультатУдалениеДанных);

                                  Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                          " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                          " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                          + " РезультатWorkerСменыДанных " +РезультатWorkerСменыДанных);


                                  // TODO: 29.05.2025  Очистка Системных Таблиц
                          Integer ИменаТаблицыSystem=        changeTableSystemUsers(context);



                                  Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                          " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                          " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                          + " ИменаТаблицыSystem " +ИменаТаблицыSystem);
                              }
                          }).doAfterNext(new Consumer<String>() {
                              @Override
                              public void accept(String string) throws Throwable {
                                  // TODO: 24.04.2023 Конец Цикла
                                  activity.runOnUiThread(()->{
                                      progressDialogДляУдалениеТаблиц.setMessage("Удаление рабочих таблиц ..."+string + " ("+РезультатWorkerСменыДанных.size()+")");
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
                                      progressDialogДляУдалениеТаблиц.dismiss();
                                      progressDialogДляУдалениеТаблиц.cancel();

                                      wretingNewVaueSuccess(РезультатWorkerСменыДанных.size());

                                      методПослеСменыДанныхЗапускаемСНАчала(activity);
                                  });



                                  Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                          " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                          " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                              }
                          }).subscribe();

          } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new RecordNewErros(context).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());

            }
        return   РезультатWorkerСменыДанных.size();
    }

    // TODO: 24.04.2023 Метод Семны ДАнных Пользователя2
    public Integer changeTableWorkerUsers(@NonNull  Context context,@NonNull  Activity activity) {
        ArrayList<Integer>   РезультатWorkerСменыДанных=new ArrayList<>();
        try {
            CopyOnWriteArrayList<String> ИменаТаблицыWorker=    new GetWorkerAndSystemTables().getWorkerTablesALl(context);

            Observable.fromIterable(ИменаТаблицыWorker)
                    .subscribeOn(Schedulers.single())
                    .observeOn(AndroidSchedulers.mainThread())
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
                    .concatMap(i -> Observable.just(i).delay(50, TimeUnit.MILLISECONDS))
                    .doOnNext(new Consumer<String>() {
                        @Override
                        public void accept(String текущаяТаблицаДляУдваления) throws Throwable {

                            // TODO: 29.05.2025  Очистка Рабочих Таблиц
                            Integer РезультатУдалениеДанных=
                                    методСменыДанныхПользователя( текущаяТаблицаДляУдваления.toString().trim(),
                                            context);

                            РезультатWorkerСменыДанных.add(РезультатУдалениеДанных);

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " РезультатWorkerСменыДанных " +РезультатWorkerСменыДанных);


                            // TODO: 29.05.2025  Очистка Системных Таблиц
                            Integer ИменаТаблицыSystem=        changeTableSystemUsers(context);




                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " ИменаТаблицыSystem " +ИменаТаблицыSystem);
                        }
                    }) .doOnComplete(new Action() {
                        @Override
                        public void run() throws Throwable {
                            // TODO: 19.02.2025
                            activity.runOnUiThread(()-> {

                                wretingNewVaueSuccess(РезультатWorkerСменыДанных.size());

                            });

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                        }
                    }).subscribe();

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }
        return   РезультатWorkerСменыДанных.size();
    }










































    // TODO: 24.04.2023 Метод Семны ДАнных Пользователя
    private Integer changeTableSystemUsers(Context context) {
        ArrayList<Integer>   РезультатСменыДанныхSystem=new ArrayList<>();
        try {
            CopyOnWriteArrayList<String> ИменаТаблицыSystem=    new GetWorkerAndSystemTables().getSystemTablesALl(context);

            Observable.fromIterable(ИменаТаблицыSystem)
                    .concatMap(i -> Observable.just(i).delay(50, TimeUnit.MILLISECONDS))
                    .doOnNext(new Consumer<String>() {
                        @Override
                        public void accept(String текущаяСистемнаяТаблица) throws Throwable {
                            // TODO: 09.09.2021 DELETE УДАЛЕНИЕ ТАБЛИЦ ПЕРЕД УМЕНЫ ПОЛЬЗОВАТЕЛЯ
                            Integer РезультатУдалениеДанных=
                                    методСменыДанныхMODIFITATION_Client( текущаяСистемнаяТаблица, context);

                            Log.d(this.getClass().getName(), "РезультатУдалениеДанных " + РезультатУдалениеДанных+ " текущаяСистемнаяТаблица "
                                    +текущаяСистемнаяТаблица);
                            // TODO: 09.09.2021  действие второе добалянеим дату
                            РезультатСменыДанныхSystem.add(РезультатУдалениеДанных);

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                        }
                    }).doOnError(new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            throwable.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " +throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            // TODO: 01.09.2021 метод вызова
                            new RecordNewErros(context).recordnewerror(throwable.toString(),
                                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }).blockingSubscribe();

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }
        return   РезультатСменыДанныхSystem.size();
    }



    // TODO: 24.02.2022

 public void методПослеСменыДанныхЗапускаемСНАчала(@NonNull Activity activity) {
        try {
                   Toast.makeText(activity, " Успешное смена данных !!! "    , Toast.LENGTH_SHORT).show();;
                    /////TODO ЗАПУСКАМ ОБНОЛВЕНИЕ ДАННЫХ С СЕРВЕРА ПЕРЕРД ЗАПУСКОМ ПРИЛОЖЕНИЯ ВСЕ ПРИЛОЖЕНИЯ ДСУ-1
            Intent Интент_ЗапускаетPasswords=new Intent();
            Интент_ЗапускаетPasswords.setClass(activity, MainActivityPasswords.class);
            Интент_ЗапускаетPasswords.setFlags( Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            Bundle bundle=new Bundle();
            Интент_ЗапускаетPasswords.putExtras(bundle);
            Интент_ЗапускаетPasswords.  addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Интент_ЗапускаетPasswords.  addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Интент_ЗапускаетPasswords.setAction("MainActivityPasswords.class");
            activity.startActivity(Интент_ЗапускаетPasswords);//tso
            // TODO: 11.04.2025 exit
            activity.finishAffinity();
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
            Log.d(this.getClass().getName(), "  ИмяТаблицы "+ИмяТаблицы+"" );
            // TODO: 14.05.2025
            ModuleDeleting moduleDeleting = new ModuleDeleting(context);
            // TODO: 03.02.2025 update new back
            СменаДанных=    moduleDeleting.getModuleDelete(ИмяТаблицы ,null,null);
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
    public Integer методОчисткаТаблицыSuccesslogin(String ИмяТаблицы, Context context) {
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
    protected Integer методСменыДанныхMODIFITATION_Client(String ТекущееИмяТаблицы, Context context) {
//
        Integer РезультатОбновлениеОчисткиТаблиц = 0;
        try {

            String getTableRoot= "MODIFITATION_Client";
            ModuleUpdating moduleUpdating = new ModuleUpdating(context);
            ContentValues contentValuesСменаДанных=new ContentValues();
            contentValuesСменаДанных.put("localversionandroid", "2000-01-10 00:00:00");
            contentValuesСменаДанных.put("versionserveraandroid", "2000-01-10 00:00:00");

            contentValuesСменаДанных.put("localversionandroid_version", 0);
            contentValuesСменаДанных.put("versionserveraandroid_version", 0);

            // TODO: 03.02.2025 update new back
            РезультатОбновлениеОчисткиТаблиц=
                    moduleUpdating.getModuleSystemUpdate(getTableRoot,contentValuesСменаДанных,"name=?",new String[]{ТекущееИмяТаблицы});
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " РезультатОбновлениеОчисткиТаблиц "+РезультатОбновлениеОчисткиТаблиц );
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РезультатОбновлениеОчисткиТаблиц;
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