package com.dsy.dsu.WorkManagers;

import static android.content.Context.ACTIVITY_SERVICE;
import static android.content.Context.NOTIFICATION_SERVICE;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.Person;
import androidx.core.graphics.drawable.IconCompat;
import androidx.work.Data;
import androidx.work.WorkInfo;
import androidx.work.Worker;
import androidx.work.WorkerParameters;


import com.dsy.dsu.BusinessLogicAll.Class_GRUD_SQL_Operations;
import com.dsy.dsu.BusinessLogicAll.VersionCurentTable;
import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;
import com.dsy.dsu.CnangeServers.PUBLIC_CONTENT;
import com.dsy.dsu.BusinessLogicAll.SubClass_Starting_Tasks_ЗапускДЛяЗадач;
import com.dsy.dsu.Hilt.Sqlitehilt.AppModuleSQLlite;
import com.dsy.dsu.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dagger.hilt.EntryPoints;


public class MyWork_Notifocations_Уведомления_Для_Задачи extends Worker {
    private  Context context;
    private  String ИмяСлужбыУведомленияДля_Задачи = "WorkManager NOtofocationForTasks";
    private  WorkerParameters workerParams;
    private   NotificationManager mNotificationManagerДляЧАТА = null;
    private   WorkInfo ИнформацияОЗапущенойСлужбе_Уведомления_Одноразовая;
    private  Integer ОбщееКоличествоНЕпрочитанныхСтрок = 0;
    private NotificationCompat.Builder builder_Для_Задачи = null;
    private SQLiteDatabase sqLiteDatabase ;
    private Class_GRUD_SQL_Operations class_grud_sql_operationsIDпользоввателяДляСлужб;
    private  SimpleDateFormat ФоорматДат ;
    private    int     ID_ТаблицаУвендомлений;
    private   Intent ИнтентДляЗапускаСлужбыПолсеАнализа;
    private   Boolean РезультатНужноЗапускатьУведомленияИлиНет=false;
    private String PROCESS_ID_УведомленияПлановая="12";
    private   PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =null;
    private  ArrayList БуферСамиУведомленияЛинкСамиУведомления;
    private  NotificationCompat.MessagingStyle messagingStyleДля_ОбщихУведомлений;
    private Person.Builder person;
    private SubClass_Starting_Tasks_ЗапускДЛяЗадач subClassStartingTasksЗапускДЛяЗадач;
    private  Long UUIDРочитаногоЗаданиеДляКотрогоДалееБудетПроизведенаСменаСтсусаНАОзнакомленный=0l;
    private     Data myDataОтветОбщегоУведомления;

    public MyWork_Notifocations_Уведомления_Для_Задачи(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
        workerParams = workerParams;
        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new PUBLIC_CONTENT(context);
        messagingStyleДля_ОбщихУведомлений = new NotificationCompat.MessagingStyle(getApplicationContext().getResources().getString(R.string.action_settings)).setConversationTitle("Задачи");
        Log.i(this.context.getClass().getName(),
                " messagingStyleДля_ОбщихУведомлений " + "\n" + messagingStyleДля_ОбщихУведомлений.getMessages());
        subClassStartingTasksЗапускДЛяЗадач = new SubClass_Starting_Tasks_ЗапускДЛяЗадач(getApplicationContext());
    }

    @SuppressLint("RestrictedApi")
    @NonNull
    @Override
    public Executor getBackgroundExecutor() {
        Log.i(context.getClass().getName(),
                "public Executor getBackgroundExecutor() {");
        return  Executors.newSingleThreadExecutor();
    }
    @Override
    public void onStopped() {
        super.onStopped();
        Log.i(this.getClass().getName(), " onStopped ()  ");
    }
    // TODO: 17.11.2021  ГЛАВНЫЙ МЕТОД КЛАССА WORK MANEGER  ДЛЯ УВЕЛОДОМЛЕНИЯ ТОЛЬКО ДЛЯ ЧАТА
    @NonNull
    @Override
    public Result doWork() {
        Boolean ФинальныйФлагЛюбогоЗапущеногоАктивти = false;
        try {
            // TODO: 16.04.2025
            sqLiteDatabase = EntryPoints.get(context, AppModuleSQLlite.class).getAppModuleSQLlite();
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " sqLiteDatabase " +sqLiteDatabase);
            ActivityManager ЗапущенныйПроуессыДляУведомленийЧата = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
            if (ЗапущенныйПроуессыДляУведомленийЧата!=null) {
                List<ActivityManager.AppTask> КоличествоЗапущенныйПроуессыДляЧата = ЗапущенныйПроуессыДляУведомленийЧата.getAppTasks();
            if (КоличествоЗапущенныйПроуессыДляЧата.size() > 0) {
                Log.i(context.getClass().getName(), "ЗАПУСК    ВНУТРИ метода         " +
                        "public Result doWork()  MyWork_Notifocations_Уведомления  внутри WORK MANAGER  КоличествоЗапущенныйПроуессыДляЧата " + "\n"
                        + КоличествоЗапущенныйПроуессыДляЧата.size());
                for (ActivityManager.AppTask ТекущаяАктивти : КоличествоЗапущенныйПроуессыДляЧата) {
                    String АктивностьЕслиЕстьTOPДляЧата = null;
                    if (ТекущаяАктивти!=null) {
                        Log.i(context.getClass().getName(), "ЗАПУСК    ВНУТРИ метода         " +
                                "ТекущаяАктивти.getTaskInfo().numActivities  " + "\n"
                                + ТекущаяАктивти.getTaskInfo().numActivities);
                        // TODO: 20.02.2022
                        if (ТекущаяАктивти.getTaskInfo().numActivities>0) {
                            // TODO: 20.02.2022
                            АктивностьЕслиЕстьTOPДляЧата = ТекущаяАктивти.getTaskInfo().topActivity.getClassName().toString();
                        }
                        Log.i(context.getClass().getName(), "ТекущаяАктивти " + ТекущаяАктивти +
                                " АктивностьЕслиЕстьTOPДляЧата  " + АктивностьЕслиЕстьTOPДляЧата +
                                "ТекущаяАктивти.getTaskInfo().numActivities  " + "\n"
                                + ТекущаяАктивти.getTaskInfo().numActivities);
                    }
                    if (АктивностьЕслиЕстьTOPДляЧата!=null) {
                        // TODO: 06.12.2021
                        switch (АктивностьЕслиЕстьTOPДляЧата) {
                            case "com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Visible_Async":
                            case "com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Face_Start":
                            case "com.dsy.dsu.For_Code_Settings_DSU1.MainActivityPasswords":
                                break;
                            // TODO: 01.12.2021 САМ ЗАПУСК WORK MANAGER  СИНХРОНИАЗЦИИ ПРИ ВКЛЮЧЕННОЙ АКТИВТИ
                            default:
                                Log.d(this.getClass().getName(), "ЗАПУСК СЛУЖБА ВНУТРИ startService   Вещятеля BroadcastReceiver  Service_Notificatios_Уведомления_ОбновлениеПО  ДЛЯ ЧАТА " + new Date() +
                                        "\n" + " Build.BRAND " + Build.BRAND.toString() + "\n");
                                ///////todo код запуска уведомлений для чата
                                МетодЗапускаСлужбыУведомленияДляЧата();
                                ///////todo код запуска уведомлений для чата
                                Log.i(context.getClass().getName(), "Метод ВНУТРИ РАБОТА... С АКТИВТИ ДЕЙСТВУЩИМ ЧАТА ОТРАБОТАЛ ВНУТРИ метода ЗАПУСКАЕМ БЕЗ activity      " +
                                        "   public Result doWork()  MyWork_Notifocations_Уведомления  внутри WORK MANAGER "
                                        + new Date() +
                                        " WorkManager Synchronizasiy_Data  " + " РАБОТАЮЩИЙ ПРОЦЕСС  КоличествоЗапущенныйПроуессыДляЧата.size()"
                                        + КоличествоЗапущенныйПроуессыДляЧата.size() + "\n" +
                                        "   действуещее TOP активти АктивностьЕслиЕстьTOPДляЧата " + АктивностьЕслиЕстьTOPДляЧата);
                                ///////todo  КОНЕЦ  код запуска уведомлений для чата
                                break;
                            // TODO: 24.11.2021
                        }
                    }else{
                        // TODO: 03.12.2021 ПРОСТО ФОНОВАЯ ЗАДАЧА  РЕЗКО НЕ СТАЛО АКТИВТИ
                        МетодЗапускаетУведомленияКогдаВообщенетНиОдногоАктивтиNULL(ФинальныйФлагЛюбогоЗапущеногоАктивти);
                        Log.i(context.getClass().getName(), "ЗАПУСК    ВНУТРИ метода         public Result doWork()  MyWork_Notifocations_Уведомления  внутри WORK MANAGER Зарускает когда Активти РАВНО )" + "\n"
                                + КоличествоЗапущенныйПроуессыДляЧата);
                    }
                }
            } else {
                МетодЗапускаетУведомленияКогдаВообщенетНиОдногоАктивтиNULL(ФинальныйФлагЛюбогоЗапущеногоАктивти);
                Log.i(context.getClass().getName(), "ЗАПУСК    ВНУТРИ метода         public Result doWork()  MyWork_Notifocations_Уведомления  внутри WORK MANAGER Зарускает когда Активти РАВНО )" + "\n"
                        + КоличествоЗапущенныйПроуессыДляЧата);
            }
        }else{
            // TODO: 03.12.2021 ПРОСТО ФОНОВАЯ ЗАДАЧА
            МетодЗапускаетУведомленияКогдаВообщенетНиОдногоАктивтиNULL(ФинальныйФлагЛюбогоЗапущеногоАктивти);
                Log.i(context.getClass().getName(), "ЗАПУСК    ВНУТРИ метода         public Result doWork()  MyWork_Notifocations_Уведомления  внутри WORK MANAGER Зарускает когда Активти воОБЩЕ НЕТ null" );
        }
            // TODO: 11.05.2021 ЗПУСКАЕМ СЛУЖБУ через брдкастер синхронизхации и уведомления
            Log.i(context.getClass().getName(), "ОбщееКоличествоНЕпрочитанныхСтрок" + ОбщееКоличествоНЕпрочитанныхСтрок + " РезультатНужноЗапускатьУведомленияИлиНет "+ РезультатНужноЗапускатьУведомленияИлиНет);
            // TODO: 25.02.2022 send datas
            myDataОтветОбщегоУведомления = new Data.Builder()
                    .putBoolean("ОтветПослеВыполения_MyWork_Notifocations_Уведомления_Общая",
                            РезультатНужноЗапускатьУведомленияИлиНет)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(), " Стоп СЛУЖБА Service_Notificatios_Уведомления_ОбновлениеПО  ДЛЯ ЧАТА " +
                    "MyWork_Notifocations_Уведомления_Для_Задачи ошибка  Exception e в классе MyWork_Notifocations_Уведомления_Для_Задачи " + e.toString() + "\n" +
                    " ФинальныйФлагЛюбогоЗапущеногоАктивти " + ФинальныйФлагЛюбогоЗапущеногоАктивти);
            Log.e(getApplicationContext().getClass().getName(), " Ошибка  MyWork_Notifocations_Уведомления ЧАТ   public Result doWork()    ДЛЯ ЧАТА ");
        }
            return Result.retry();
            // TODO: 03.01.2022
    }

















    // TODO: 16.12.2021 Метод ЗАпускает в фоне Уведомелния когда вообще нет нет ни одного Активти NULL

    private void МетодЗапускаетУведомленияКогдаВообщенетНиОдногоАктивтиNULL(Boolean ФинальныйФлагЛюбогоЗапущеногоАктивти) {
        try{

                          Log.d(this.getClass().getName(), "ЗАПУСК СЛУЖБА ВНУТРИ startService   Вещятеля BroadcastReceiver  Service_Notificatios_Уведомления_ОбновлениеПО  ДЛЯ ЧАТА " + new Date() +
                                  "\n" + " Build.BRAND " + Build.BRAND.toString() + "\n" );

                          ///////todo код запуска уведомлений для чата


                          ///////todo код запуска уведомлений для чата

                          МетодЗапускаСлужбыУведомленияДляЧата();

                          ///////todo код запуска уведомлений для чата

                          Log.i(context.getClass().getName(), "Метод ВНУТРИ ЧИСТО ФОНОВАЯ ЗАДАЧА  ЧАТА ОТРАБОТАЛ ВНУТРИ метода ЗАПУСКАЕМ БЕЗ activity      " +
                                  "   public Result doWork()  MyWork_Notifocations_Уведомления  внутри WORK MANAGER "
                                  + new Date() +
                                  " WorkManager Synchronizasiy_Data  " );

                          ///////todo  КОНЕЦ  код запуска уведомлений для чата




                          //TODO когда запускам уведомления чата  КОГДА НЕТ АКТИВИТИ И ПРОСТО ЗАПУСКАЕМ


                          //////////
                      } catch (Exception e) {
                      e.printStackTrace();
                      ///метод запись ошибок в таблицу
                      Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                              " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                      // TODO: 01.09.2021 метод вызова
                      new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                              this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                              Thread.currentThread().getStackTrace()[2].getLineNumber());

                      Log.e(context.getClass().getName(), " Стоп СЛУЖБА Service_Notificatios_Уведомления_ОбновлениеПО  ДЛЯ ЧАТА " +
                              "MyWork_Notifocations_Уведомления_Для_Задачи ошибка  Exception e в классе MyWork_Notifocations_Уведомления_Для_Задачи " + e.toString() + "\n" +
                              " ФинальныйФлагЛюбогоЗапущеногоАктивти " + ФинальныйФлагЛюбогоЗапущеногоАктивти);
                      Log.e(getApplicationContext().getClass().getName(), " Ошибка  MyWork_Notifocations_Уведомления ЧАТ   public Result doWork()    ДЛЯ ЧАТА ");
                  }
    }


    // TODO: 17.11.2021










    private void МетодЗапускаСлужбыУведомленияДляЧата() {


        String ЛокальныйФлагУбратьУведомленияСлужбу=new String();

        // TODO: 15.11.2021


        try{

            Log.i(getApplicationContext().getClass().getName(), "Запуск метода МетодЗапускаСлужбыУведомления СЛУЖБА СЛУЖБАService_Notifications ДЛЯ ЧАТА ДЛЯ ЧАТА  "+new Date());

            try {
                ФоорматДат = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", new Locale("ru"));//"yyyy-MM-dd HH:mm:ss.SSS"//"yyyy-MM-dd'T'HH:mm:ss'Z'"
            } catch (Exception e) {
                e.printStackTrace();
                // TODO: 02.08.2021
                ФоорматДат = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("ru"));//"yyyy-MM-dd HH:mm:ss.SSS"//"yyyy-MM-dd'T'HH:mm:ss'Z'"
            }
            ФоорматДат.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));


// TODO: 01.04.2021 данное условие запускает соаздание уведомления с начала своего ниже код только реагирует на нажатие кнопки удалить уведомление и саму служюу отстановить кнопка ЗАКРЫТЬ






                    /////////TODO запуск нновую нотификашенс устанолвка
                    МетодЗарускаСозданиеУведомлений();




                    Log.d(getApplicationContext().getClass().getName(), " Запуск по Расписанию СЛУЖБА  Информирования  BroadcastReceiver или  FaceApp " + "  --" + new Date());




            // TODO: 06.04.2021 Определяем рабоает ли Служба КОД ПРОВЕРЯТЕТНЕ ЗАПУЩЕНАЛИ СЛУЖЬБА И ЕСЛИНЕ ЗАПУЩЕНА ТОНЕ НАДО ЕЕ УДАЛЯТЬ ИЗ ПАМЯТИ



            // TODO: 06.04.2021 ПРИНИМАЕМ РЕШЕНИЕ О ЗАКРЫТИЕ СЛУЖБЫ ЧЕРЕ З КНОПКУ







            ///////
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            //TODO ПЕРЕД СОЗДАНИЕМ НОВОГО СООБЕЩНИЯ ОБНУЛЯЕМ ПРДЫДУЩЕЕ

        }

    }


    /////////////////

    private void МетодЗарускаСозданиеУведомлений() {

        try{


            try{


                РезультатНужноЗапускатьУведомленияИлиНет=false;

                ///
                Log.d(getApplicationContext().getClass().getName(), " Внутри МетодЗарускаСозданиеУведомлений"
                        + "--" + РезультатНужноЗапускатьУведомленияИлиНет);/////


                // TODO: 17.11.2021  ВЫЧИСЛЯЕМ ВООБЩЕ ЕСТЬ СТРОЧКИ В ЧАТЕ НЕ ПРОЧИТАННЫЕ КОТОРЫЕ НАЖДО ПРОЧИТАТЬ -- ВЕРНЁТ TRUE


                РезультатНужноЗапускатьУведомленияИлиНет=МетодВычисляемСтоитСоздаватьИЗапускатьСлужбуНапоминаний();






                ///
                Log.d(getApplicationContext().getClass().getName(), " Внутри Future Результат НужноЗапускать Уведомления Или Нет  СЛУЖБА РезультатНужноЗапускатьУведомленияИлиНет"
                        + "--" + РезультатНужноЗапускатьУведомленияИлиНет);/////

                String ФлагПолучаемИзНутриПрограммы = null;//=// notificationIntentДляУведомлений .getStringExtra("Флаг");

                if(ФлагПолучаемИзНутриПрограммы==null){


                    ФлагПолучаемИзНутриПрограммы=new String();
                }


                ///
                Log.d(getApplicationContext().getClass().getName(), " Определили Результат НужноЗапускать Уведомления Или Нет  СЛУЖБА"
                        + "--" + РезультатНужноЗапускатьУведомленияИлиНет+  " ФлагПолучаемИзНутриПрограммы " +ФлагПолучаемИзНутриПрограммы+ "\n"
                        + "ОбщееКоличествоНЕпрочитанныхСтрок   "+ОбщееКоличествоНЕпрочитанныхСтрок);/////


// TODO: 17.11.2021  ЕСЛИ TRUE ТО НАЧИНАЕМ ЗАПУСКАЕМ УВЕДОМЛЕНИЯ



                if (РезультатНужноЗапускатьУведомленияИлиНет==true ) {


                    //////TODO МЕТОД КОТОРЫЙ ЗАПУСКАЕТ УВЕДОМЛЕНИЯ ПОСЛЕ АНАЛИЗА ДАТ


                    МетодКоторыйЗапускаетУвеломленияПослеАнализа(ИнтентДляЗапускаСлужбыПолсеАнализа, РезультатНужноЗапускатьУведомленияИлиНет, ФлагПолучаемИзНутриПрограммы);//  //ФлагКтоЗапустилСлужбу

                    Log.d(this.getClass().getName(), "ЗАПУСК ПОСЛЕ АНАЛИЗА ДАТ ЗАПУСКАЕМ УВЕДОМЛЕНИЯ  СЛУЖБА  Синхронизация   " + " ВРЕМЯ " + new Date()
                            + "\n" + " РезультатНужноЗапускатьУведомленияИлиНет " + РезультатНужноЗапускатьУведомленияИлиНет);








                }





                ///


                Log.d(getApplicationContext().getClass().getName(), " Определили Результат НужноЗапускать Уведомления Или Нет  СЛУЖБА"
                        + "--" + РезультатНужноЗапускатьУведомленияИлиНет+  " ФлагПолучаемИзНутриПрограммы " +ФлагПолучаемИзНутриПрограммы);/////


                ///////
            } catch (Exception e) {
                //  Block of code to handle errors
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());



                //TODO ПЕРЕД СОЗДАНИЕМ НОВОГО СООБЕЩНИЯ ОБНУЛЯЕМ ПРДЫДУЩЕЕ

                NotificationManager notificationManager = (NotificationManager)
                        getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

                notificationManager.cancel(Integer.parseInt(PROCESS_ID_УведомленияПлановая));
            }







//
            Log.d(this.getClass().getName(), "AsyncРезультатНужноЗапускатьУведомленияИлиНет " );

 /*       //////TODO МЕТОД КОТОРЫЙ ЗАПУСКАЕТ УВЕДОМЛЕНИЯ ПОСЛЕ АНАЛИЗА ДАТ
            МетодКоторыйЗапускаетУвеломленияПослеАнализа(intent, РезультатНужноЗапускатьУведомленияИлиНет);*/


        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();

            //TODO ПЕРЕД СОЗДАНИЕМ НОВОГО СООБЕЩНИЯ ОБНУЛЯЕМ ПРДЫДУЩЕЕ

            NotificationManager notificationManager = (NotificationManager)
                    getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

            notificationManager.cancel(Integer.parseInt(PROCESS_ID_УведомленияПлановая));
            // /.cancelAll(); //   mNotificationManagerДляЧАТА.cancelAll();
            //   mNotificationManagerДляЧАТА=null;
            //  builder=null;
            Log.d(getApplicationContext().getClass().getName(), " Стоп СЛУЖБА СЛУЖБАService_Notifications  ДЛЯ ЧАТА onDestroy() Exception ");

        }



    }





    private void МетодКоторыйЗапускаетУвеломленияПослеАнализа(Intent intent, boolean результатНужноЗапускатьУведомленияИлиНет, String ФлагКтоЗапустилСлужбу) {
        ///TODO ЕСЛИ TRUE ТО ЗАПУСКАЕМ УВЕДОМЛЕНИЯ ТОЛЬКО КОГДА  TRUE


        Log.d(this.getClass().getName(), "Результат Нужно Запускать Уведомления Или Нет СЛУЖБА  true and false :: " +
                результатНужноЗапускатьУведомленияИлиНет);


        /////TODO ЗАПУСКАЕМ И СОЗДАЕМ СЕРВИС УВЕДОМЛЕНИЯ

        ///
        Log.d(getApplicationContext().getClass().getName(), " ФлагКтоЗапустилСлужбу " + ФлагКтоЗапустилСлужбу);





            МетодНотификайшенДЛяОбщейСлужбыУведомления(ФлагКтоЗапустилСлужбу);


        ///
        Log.d(getApplicationContext().getClass().getName(), " ФлагКтоЗапустилСлужбу " + ФлагКтоЗапустилСлужбу);



    }

/////////TODO запуск нновую нотификашенс устанолвка


    private void МетодНотификайшенДЛяОбщейСлужбыУведомления(String ФлагКтоЗапустилСлужбу) {
        try {

            Log.d(getApplicationContext().getClass().getName(), " Создание Уведомлеения СЛУЖБА СЛУЖБА Service_Notificatios_Уведомления_ОбновлениеПО ");


            builder_Для_Задачи = null;


            // TODO: 03.03.2022 определяем кода для отложеного запуска службы смены статсу условия задачи выполнить
            PendingIntent ЗапускКОдаЧтоПОльзовательВыполнить = subClassStartingTasksЗапускДЛяЗадач.
                    МетодЗапускаСменыСтатусаВыполнилСлужбыЧерезPendingIntent(PROCESS_ID_УведомленияПлановая, ИмяСлужбыУведомленияДля_Задачи,
                            person.build().getUri(),
                            2, "Выполнил 100 %", "ЗапускаемИзмененияСатусазадачиВыполнил");





            // TODO: 03.03.2022 определяем кода для отложеного запуска службы смены статсу условия задачи  отказ
            PendingIntent ЗапускКОдаЧтоПОльзовательОтказЗаданием = subClassStartingTasksЗапускДЛяЗадач.
                    МетодЗапускаСменыСтатусаОтказСлужбыЧерезPendingIntent(PROCESS_ID_УведомленияПлановая, ИмяСлужбыУведомленияДля_Задачи,
                            person.build().getUri(),
                            1, "Отказ", "ЗапсукаемОтказИзмененияСтатусаВзадаче");


            // TODO: 03.03.2022 ВЬТОРОЙ МЕТОД ДЛЯ ЗАДАНИЕ ПЕРЕХОД ИЗ УВЕДОМЛЕНИЯ В ЗАДАНИЕ
            PendingIntent ЗапускПриКликеКодаИзЗаданияКогдаНадоПерейтисУведомленияНаЗАдачние = subClassStartingTasksЗапускДЛяЗадач.
                    МетодПриКликеЗапускаЗаданияИзСамогоУведомленияПереход(PROCESS_ID_УведомленияПлановая, ИмяСлужбыУведомленияДля_Задачи,
                            person.build().getUri(),
                            0, "", "ИзУведомленияЗадачаПереходимВАктивтиЗадача");


            ///////TODO запускаем смены стануса задачи черезе PendingIntent
            Log.d(getApplicationContext().getClass().getName(), "PROCESS_ID_УведомленияПлановая  ПЕРЕЙТИ " + PROCESS_ID_УведомленияПлановая +
                    " ИмяСлужбыУведомленияДляЧата " + ИмяСлужбыУведомленияДля_Задачи + " person.build().getUri() " + person.build().getUri());


            ///////TODO запускаем смены стануса задачи черезе PendingIntent
            Log.d(getApplicationContext().getClass().getName(), "PROCESS_ID_УведомленияПлановая  ПЕРЕЙТИ " + PROCESS_ID_УведомленияПлановая +
                    " ИмяСлужбыУведомленияДляЧата " + ИмяСлужбыУведомленияДля_Задачи + " person.build().getUri() " + person.build().getUri());


            // TODO: 27.03.2022 ДЛЯ ЗАДАЧА
            NotificationManager notificationManager = (NotificationManager)
                    getApplicationContext().getSystemService(NOTIFICATION_SERVICE);





            // TODO: 21.12.2021 В КОДЕ НИЖЕ МЫ ОПРЕДЕЛЯЕМ ЗАПУСКАТЬ НАМ КОД ИЛИ НЕТ

            Boolean СтатустУведомленияДляУведомленияЧАТА=false;


            StatusBarNotification[] statusBarNotificationУведомленияЧата=      notificationManager.getActiveNotifications();
            // TODO: 21.12.2021
            for(StatusBarNotification statusBarNotification1: statusBarNotificationУведомленияЧата) {

                // TODO: 21.12.2021

                if (statusBarNotification1.getId() == Integer.parseInt(PROCESS_ID_УведомленияПлановая)) {

                    // TODO: 21.12.2021
                    СтатустУведомленияДляУведомленияЧАТА = statusBarNotification1.isClearable();
                    // TODO: 21.12.2021

                    Log.d(this.getClass().getName(), " СтатустУведомленияДляУведомленияЧАТА" + СтатустУведомленияДляУведомленияЧАТА);


                }


            }


            Log.d(this.getClass().getName(), "СтатустУведомленияДляУведомленияЧАТА " +СтатустУведомленияДляУведомленияЧАТА+
                    " БуферСамиУведомленияЛинкСамиУведомления " +БуферСамиУведомленияЛинкСамиУведомления );


            // TODO: 21.11.2021 НЕПОСТРЕДСТВЕННО СОЗДАНИЕ УВЕДОМЛЕНИЯ ДЛЯ ЧАТА СОЗДАНИЕ И ЗАПОЛЕНИЕ


               // if (БуферСамиУведомленияЛинкСамиУведомления.size()>0  && СтатустУведомленияДляУведомленияЧАТА==false ) {/// && СтатустУведомленияДляУведомленияЧАТА==false
                if (БуферСамиУведомленияЛинкСамиУведомления.size()>0 ) {/// && СтатустУведомленияДляУведомленияЧАТА==false


                    Log.d(this.getClass().getName(), "bigText " +БуферСамиУведомленияЛинкСамиУведомления+ " БуферСамиУведомленияЛинкСамиУведомления.toString() "
                            +БуферСамиУведомленияЛинкСамиУведомления.toString()+"\n"+
                            "ЗАПУСК......НОВОЕ СООБЩЕНИЕ   bigText " +БуферСамиУведомленияЛинкСамиУведомления+"\n"+
                            "  время ::" + new Date());




                    //TODO ПЕРЕД СОЗДАНИЕМ НОВОГО СООБЕЩНИЯ ОБНУЛЯЕМ ПРДЫДУЩЕЕ


                    notificationManager.cancel(Integer.parseInt(PROCESS_ID_УведомленияПлановая));


                    ///notificationManager.cancelAll();



                    onStopped();


                    Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
// Vibrate for 500 milliseconds

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v2.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        v2.vibrate(200);
                    }



// TODO: 21.11.2021  само созданеи уведомления

       /*             Drawable icon = null;
                    icon = getResources().getDrawable(R.drawable.icon_dsu1_for_fragment1_chat2);
                    icon.setBounds(10, 0, 90, 85);*/


         /*           // TODO: 03.02.2022  УВЕДОМЛЕНИЯ ДЛЯ ОБШЕЙ СИНХРОНИАЗЦИИ
                    NotificationCompat.MessagingStyle.Message messagingStyleДля_ОбщихУведомлений =
                            new NotificationCompat.MessagingStyle.Message("Всем привет!", System.currentTimeMillis(), "Ivan");*/

               /*     NotificationCompat.MessagingStyle.Message messagingStyle2 =
                            new NotificationCompat.MessagingStyle.Message("  zЯЯЯ  Всем привет!", System.currentTimeMillis(), (Person3) null);*/


                    PendingIntent ЗапускКОдаЧтоПОльзовательОзнаомленсЗаданиемss = null;


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        ///"@mipmap/icon_main_tabel_four" ////.setSmallIcon(R.drawable.ic_notifications_black_24dp)
                        builder_Для_Задачи = new NotificationCompat.Builder(getApplicationContext(), PROCESS_ID_УведомленияПлановая)
                                /////
                                .setContentText(БуферСамиУведомленияЛинкСамиУведомления.toString())                 // .setContentText("http://developer.alexanderklimov.ru/android/")
                                .setSmallIcon(R.drawable.ic_notifications_black_24dp)////builder.setSmallIcon(R.drawable.ic_launcher_background);//R.mipmap.ic_launcher   ///R.drawable.ic_notifications_black_24dp
                                .setPriority(NotificationCompat.PRIORITY_MAX)
                                .setColor(Color.BLUE)
                                .setColorized(true)
                                //.setContentTitle("Задание на выполнение")
                                .setSmallIcon(R.drawable.icon_dsu1_for_fragment1_chat2)
                                .setGroup("SousAndroid")
                                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                                        R.drawable.ic_notifications_black_24dp)) // большая картинка
                                //.setTicker("Последнее китайское предупреждение!") // до Lollipop
                                .setVibrate(new long[]{0, 250, 100, 250})
                                .setShowWhen(true)
                                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                                .setCategory(Notification.CATEGORY_MESSAGE)
                                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE | Notification.FLAG_AUTO_CANCEL)
                                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                                .setStyle(new NotificationCompat.BigTextStyle().bigText(БуферСамиУведомленияЛинкСамиУведомления
                                        .toString())).setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)
                                .setStyle(messagingStyleДля_ОбщихУведомлений).setColor(Color.parseColor(("#FAEBD" + new Random().nextInt(1))))
                                .setGroupSummary(true)
                                .setColor(Color.GREEN)
                                .addAction(android.R.drawable.ic_btn_speak_now, "Выполнить", ЗапускКОдаЧтоПОльзовательВыполнить)
                                .addAction(android.R.drawable.ic_delete, "Отказ", ЗапускКОдаЧтоПОльзовательОтказЗаданием)
                                .setAutoCancel(false)
                                .setWhen(System.currentTimeMillis()) // автоматически закрыть уведомление после нажатия////.setStyle(new NotificationCompat.BigTextStyle().bigText(bigText) ).setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
                                .setContentIntent(ЗапускПриКликеКодаИзЗаданияКогдаНадоПерейтисУведомленияНаЗАдачние);
                        ////TODO три кнопки действия PUSH-сообщений
                        /// .setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL)  .addAction(android.R.drawable.ic_delete, "Выполнил/на", ЗапускКОдаЧтоПОльзовательОзнаомленсЗаданиемss)


                    } else {
                        builder_Для_Задачи =
                                new NotificationCompat.Builder(getApplicationContext(), PROCESS_ID_УведомленияПлановая)
                                        //
                                        .setContentText(БуферСамиУведомленияЛинкСамиУведомления.toString())                 // .setContentText("http://developer.alexanderklimov.ru/android/")
                                        .setSmallIcon(R.drawable.ic_notifications_black_24dp)////builder.setSmallIcon(R.drawable.ic_launcher_background);//R.mipmap.ic_launcher   ///R.drawable.ic_notifications_black_24dp
                                        .setPriority(NotificationCompat.PRIORITY_MAX)
                                        .setColor(Color.BLUE)
                                        .setColorized(true)
                                        //  .setContentTitle("Задание на выполнение")
                                        .setSmallIcon(R.drawable.icon_dsu1_for_fragment1_chat2)
                                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                                                R.drawable.ic_notifications_black_24dp)) // большая картинка
                                        //.setTicker("Последнее китайское предупреждение!") // до Lollipop
                                        .setVibrate(new long[]{0, 250, 100, 250})
                                        .setShowWhen(true)
                                        .setGroup("SousAndroid")
                                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                                        .setCategory(Notification.CATEGORY_MESSAGE)
                                        .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE | Notification.FLAG_AUTO_CANCEL)
                                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                                        /* .setStyle(new NotificationCompat.BigTextStyle().bigText(БуферСамиУведомленияЛинкСамиУведомления.toString())
                                         ).setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)*/
                                        .setStyle(messagingStyleДля_ОбщихУведомлений).setColor(Color.parseColor(("#FAEBD" + new Random().nextInt(1))))
                                        .setGroupSummary(true)
                                        .setColor(Color.GREEN)
                                        .addAction(android.R.drawable.ic_btn_speak_now, "Выполнить", ЗапускКОдаЧтоПОльзовательВыполнить)
                                        .addAction(android.R.drawable.ic_delete, "Отказ", ЗапускКОдаЧтоПОльзовательОтказЗаданием)
                                        .setAutoCancel(false)
                                        .setWhen(System.currentTimeMillis()) // автоматически закрыть уведомление после нажатия////.setStyle(new NotificationCompat.BigTextStyle().bigText(bigText) ).setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
                                        .setContentIntent(ЗапускПриКликеКодаИзЗаданияКогдаНадоПерейтисУведомленияНаЗАдачние);// TODO: 27.03.2022    .addAction(android.R.drawable.ic_delete, "Выполнил/на", ЗапускКОдаЧтоПОльзовательОзнаомленсЗаданиемss)

                        // автоматически закрыть уведомление после нажатия
                        // .setContentIntent(ЗапускЗакрытия);
                        ////TODO три кнопки действия PUSH-сообщений



                    }



            // TODO: 27.11.2021 САМ ЗАПУСК УВЕДОМЛЕНИЯ

            mNotificationManagerДляЧАТА = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

            // TODO: 17.11.2021  launch
            // === Removed some obsoletes
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(
                        PROCESS_ID_УведомленияПлановая,
                        "Channel human readable title",
                        NotificationManager.IMPORTANCE_HIGH);
                mNotificationManagerДляЧАТА.createNotificationChannel(channel);
                builder_Для_Задачи.setChannelId(String.valueOf(PROCESS_ID_УведомленияПлановая));
                channel.setDescription("Увеомление для версии выше API 25");
                // TODO: 18.11.2021  дополнительые настройки

                builder_Для_Задачи.build().flags |= Notification.FLAG_FOREGROUND_SERVICE;
                ///TODO Запускаем увидомления
                builder_Для_Задачи.build().flags |= Notification.FLAG_AUTO_CANCEL;
                // startForeground(Integer.parseInt(PROCESS_ID_УведомленияПлановая),builder.build());//builder.build()
                ///TODO Запускаем увидомления
                builder_Для_Задачи.build().flags |= Notification.FLAG_SHOW_LIGHTS;
                ///TODO Запускаем увидомления
                builder_Для_Задачи.build().flags |= Notification.FLAG_INSISTENT;

                // TODO: 02.12.2021
                builder_Для_Задачи.setNumber(БуферСамиУведомленияЛинкСамиУведомления.size());


                ///TODO Запускаем увидомления
                builder_Для_Задачи.build().flags |=Intent.FLAG_ACTIVITY_CLEAR_TASK;
                ///TODO Запускаем увидомления
                builder_Для_Задачи.build().flags |=  Intent.FLAG_ACTIVITY_NEW_TASK;


                // TODO: 20.06.2022 световая индикация\

                builder_Для_Задачи.build().ledARGB = Color.RED;
                builder_Для_Задачи.build().ledOffMS = 0;
                builder_Для_Задачи.build().ledOnMS = 1;
                builder_Для_Задачи.build().flags =  Notification.FLAG_SHOW_LIGHTS;
                builder_Для_Задачи.build().flags = Notification.FLAG_ONGOING_EVENT;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    mNotificationManagerДляЧАТА.getBubblePreference();
                }

                ///TODO Запускаем увидомления
                // TODO: 02.12.2021  сам запуск уведомления
                mNotificationManagerДляЧАТА.notify(Integer.parseInt(PROCESS_ID_УведомленияПлановая), builder_Для_Задачи.build());////   mNotificationManagerДляЧАТА.notify(Integer.parseInt(PROCESS_ID_УведомленияПлановая), builder.build());
                ///TODO закрытие увидомления
                //mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();

   /*             Log.i(this.getClass().getName(), "ЗАПУСК СЛУЖБА  УВЕДОМЛЕНИЯ ДЛЯ ЧАТА САМО ПОЯВЛЕНИЕ  ВАРИАНТ -1  startForeground(Integer.parseInt(PROCESS_ID_УведомленияПлановая),builder.build());//builder.build()" + new Date()
                        + "\n" + " PROCESS_ID_УведомленияПлановая " + PROCESS_ID_УведомленияПлановая+"\n"
                        +  "  САМИ СООБЩЕНИЯ : "+БуферСамиУведомленияЛинкСамиУведомления.toString());
*/



              //    startForeground(Integer.parseInt(PROCESS_ID_УведомленияПлановая),builder.build());//builder.build()

            }else{
                ///TODO Запускаем увидомления

                // TODO: 27.11.2021 САМ ЗАПУСК УВЕДОМЛЕНИЯ

                //mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                builder_Для_Задачи.build().flags |= Notification.FLAG_FOREGROUND_SERVICE;
                ///TODO Запускаем увидомления
                ///TODO Запускаем увидомления
                builder_Для_Задачи.build().flags |= Notification.FLAG_AUTO_CANCEL;
                ///TODO Запускаем увидомления
                builder_Для_Задачи.build().flags |= Notification.FLAG_SHOW_LIGHTS;
                ///TODO Запускаем увидомления
                builder_Для_Задачи.build().flags |= Notification.FLAG_INSISTENT;
                ///TODO Запускаем увидомления
                builder_Для_Задачи.build().flags |=Intent.FLAG_ACTIVITY_CLEAR_TASK;
                ///TODO Запускаем увидомления
                builder_Для_Задачи.build().flags |=  Intent.FLAG_ACTIVITY_NEW_TASK;
                // TODO: 02.12.2021
                builder_Для_Задачи.setNumber(БуферСамиУведомленияЛинкСамиУведомления.size());
                // TODO: 20.06.2022 световая индикация\

                builder_Для_Задачи.build().ledARGB = Color.RED;
                builder_Для_Задачи.build().ledOffMS = 0;
                builder_Для_Задачи.build().ledOnMS = 1;
                builder_Для_Задачи.build().flags = Notification.FLAG_SHOW_LIGHTS;
                builder_Для_Задачи.build().flags = Notification.FLAG_ONGOING_EVENT;


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    mNotificationManagerДляЧАТА.getBubblePreference();
                }

                // TODO: 02.12.2021  сам запуск уведомления

                mNotificationManagerДляЧАТА.notify(Integer.parseInt(PROCESS_ID_УведомленияПлановая), builder_Для_Задачи.build());////   mNotificationManagerДляЧАТА.notify(Integer.parseInt(PROCESS_ID_УведомленияПлановая), builder.build());
                ///TODO закрытие увидомления

           //     startForeground(Integer.parseInt(PROCESS_ID_УведомленияПлановая),builder.build());//builder.build()
/*

                Log.i(this.getClass().getName(), "ЗАПУСК СЛУЖБА  УВЕДОМЛЕНИЯ ДЛЯ ЧАТА САМО ПОЯВЛЕНИЕ  ВАРИАНТ -2  startForeground(Integer.parseInt(PROCESS_ID_УведомленияПлановая),builder.build());/" + new Date()
                        + "\n" + " PROCESS_ID_УведомленияПлановая " + PROCESS_ID_УведомленияПлановая+"\n"
                        +  "  САМИ СООБЩЕНИЯ : "+БуферСамиУведомленияЛинкСамиУведомления.toString());*/
            }
            /// startForeground(Integer.parseInt(PROCESS_ID_УведомленияПлановая),builder.build());//builder.build()

// TODO: 20.06.2022  метод смены статуса уведосления что его показывали

                    МетодСтатусаЗадачиЧтоУведомлениеУжеПоказывали();

                    Log.i(this.getClass().getName(), " смена статуса уведомления за " );




                }else{
                    Log.i(this.getClass().getName(), " ОБЩЕЕ УВЕДОМЛЕНИЯ НЕТ ДАГЫХ ЧТО ЗАПОЛНИТЬ УСЛОВИЯ ПУСТОЙ СЛУЖБЫ БуферСамиУведомленияЛинкСамиУведомления  " +БуферСамиУведомленияЛинкСамиУведомления.toString());
                }



            //////



        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();

            //TODO ПЕРЕД СОЗДАНИЕМ НОВОГО СООБЕЩНИЯ ОБНУЛЯЕМ ПРДЫДУЩЕЕ
            Log.d(getApplicationContext().getClass().getName(), " Стоп СЛУЖБА СЛУЖБАService_Notifications ДЛЯ ЧАТА  onDestroy() Exception ");

        }







    }

    private void МетодСтатусаЗадачиЧтоУведомлениеУжеПоказывали() throws ExecutionException, InterruptedException {
        //////TODO ПОСЛЕ ПОЯЛВЛЕНИЯ УВЕДОМЛЕНИЯ ЗАПИСЫАЕМ В В ТАБЛИЦУ СТАТУСА ЧТОДРННОЕ ЦУВЕДОМДЕНИЯ УЖЕ ПОКАЗЫВАЕМ
        try{

            Class_GRUD_SQL_Operations  class_grud_sql_operationsПослеУдаленияДобавляемДатуВерсии=new Class_GRUD_SQL_Operations(getApplicationContext());

            String ТаблицаОбрабокаиПриСменсатусаУведомленияЧтоЕгоУжекПоказхывали="data_notification";

        ContentValues contentValuesСменаСтатусаЗадачиЧтоЕЕЕжуПоказывали = new ContentValues();
        ///
        contentValuesСменаСтатусаЗадачиЧтоЕЕЕжуПоказывали.put("alreadyshownnotifications", 1);

            // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
            Long РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудникаПервая=
                    new VersionCurentTable(getApplicationContext()).upVersionCurentTable(    ТаблицаОбрабокаиПриСменсатусаУведомленияЧтоЕгоУжекПоказхывали);
            Log.d(this.getClass().getName(), " РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудникаПервая  " + РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудникаПервая);


            contentValuesСменаСтатусаЗадачиЧтоЕЕЕжуПоказывали.put("current_table", РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудникаПервая);
        // TODO: 06.09.2021  ПАРАМЕНТЫ ДЛЯ ОБНОВЛЕНИЯ
        class_grud_sql_operationsПослеУдаленияДобавляемДатуВерсии.concurrentHashMapНабор
                .put("НазваниеОбрабоатываемойТаблицы",ТаблицаОбрабокаиПриСменсатусаУведомленияЧтоЕгоУжекПоказхывали);
        //
        class_grud_sql_operationsПослеУдаленияДобавляемДатуВерсии.concurrentHashMapНабор.put("Флаг_ЧерезКакоеПолеОбновлением","uuid");
        ///
        class_grud_sql_operationsПослеУдаленияДобавляемДатуВерсии.concurrentHashMapНабор
                .put("ЗначениеФлагОбновления",UUIDРочитаногоЗаданиеДляКотрогоДалееБудетПроизведенаСменаСтсусаНАОзнакомленный);
        ///
        class_grud_sql_operationsПослеУдаленияДобавляемДатуВерсии.
                concurrentHashMapНабор.put("ЗнакФлагОбновления","=");
        ////TODO КОНТЕЙНЕР ДЛЯ ОБНОВЛЕНИЯ
        class_grud_sql_operationsПослеУдаленияДобавляемДатуВерсии.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(contentValuesСменаСтатусаЗадачиЧтоЕЕЕжуПоказывали);


        ///TODO РЕЗУЛЬТАТ ОБНОВЛЕНИЕ ДАННЫх уведомления заадачи
        Integer  ОбновляемСтатусЗадачиУведомленияЧТоЕЕУжеПоказывали= (Integer)  class_grud_sql_operationsПослеУдаленияДобавляемДатуВерсии.
                new UpdateData(getApplicationContext()).updatedata(class_grud_sql_operationsПослеУдаленияДобавляемДатуВерсии.concurrentHashMapНабор,
                class_grud_sql_operationsПослеУдаленияДобавляемДатуВерсии.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                new PUBLIC_CONTENT(getApplicationContext()).МенеджерПотоков,   sqLiteDatabase);

        Log.d(this.getClass().getName(), " ОбновляемСтатусЗадачиУведомленияЧТоЕЕУжеПоказывали  " +ОбновляемСтатусЗадачиУведомленияЧТоЕЕУжеПоказывали
                +" UUIDРочитаногоЗаданиеДляКотрогоДалееБудетПроизведенаСменаСтсусаНАОзнакомленный "+UUIDРочитаногоЗаданиеДляКотрогоДалееБудетПроизведенаСменаСтсусаНАОзнакомленный);



            if (ОбновляемСтатусЗадачиУведомленияЧТоЕЕУжеПоказывали > 0) {

                Integer РезультатПослеВставкиДанныхУвеличиваемВерсиюДанных =
                        МетодПослеУспешнойЗаписиЗначенияВТаблицуПоднимаемВерсиюДанных(class_grud_sql_operationsПослеУдаленияДобавляемДатуВерсии,
                                sqLiteDatabase,
                                РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудникаПервая, ТаблицаОбрабокаиПриСменсатусаУведомленияЧтоЕгоУжекПоказхывали);
                // TODO: 21.03.2022
                // TODO: 21.03.2022
                Log.d(this.getClass().getName(),
                        " ТаблицаОбработки " + ТаблицаОбрабокаиПриСменсатусаУведомленияЧтоЕгоУжекПоказхывали
                                + " РезультатПослеВставкиДанныхУвеличиваемВерсиюДанных " + РезультатПослеВставкиДанныхУвеличиваемВерсиюДанных);
            }



    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }



    }



    private Integer МетодПослеУспешнойЗаписиЗначенияВТаблицуПоднимаемВерсиюДанных
            (Class_GRUD_SQL_Operations classGrudSqlOperationsДляОперацииСозданеиНовойЗадачи,
             SQLiteDatabase sqLiteDatabaseДляНовгоЗадания,
             Long РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудника, String таблицаОбработкиПослеУспешнойВсатвкиНовойЗадачи) throws ExecutionException, InterruptedException {

        // TODO: 21.03.2022
        Integer Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы = 0;


        // TODO: 21.03.2022
        try {
            Log.d(getApplicationContext().getClass().getName(), "таблицаОбработкиПослеУспешнойВсатвкиНовойЗадачи "
                    + таблицаОбработкиПослеУспешнойВсатвкиНовойЗадачи);


            classGrudSqlOperationsДляОперацииСозданеиНовойЗадачи.
                    concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы",
                            таблицаОбработкиПослеУспешнойВсатвкиНовойЗадачи);
            ///
            classGrudSqlOperationsДляОперацииСозданеиНовойЗадачи.
                    concurrentHashMapНабор.put("ФлагТипИзменениеВерсииДанныхЛокальнаяСервернаяИлиОба",
                            "Локальное");///  "ЛокальныйСерверныйОба"    ПОСЛЕ КАК ПРИШЛИ ВНЕШНИЕ ДАННЫЕ
            ///
            ///
            classGrudSqlOperationsДляОперацииСозданеиНовойЗадачи.
                    concurrentHashMapНабор.put(" " +
                                    "ПередоваемоеЗначенияДляТаблицы_MODIFITATION_Client_КотороеНадоЗаписать",
                            РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудника);///  "ЛокальныйСерверныйОба"    ПОСЛЕ КАК ПРИШЛИ ВНЕШНИЕ ДАННЫЕ
            ///


            ///TODO РЕЗУЛЬТА изменения версии данных
            Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы =
                    (Integer) classGrudSqlOperationsДляОперацииСозданеиНовойЗадачи.
                            new ChangesVesionData(getApplicationContext()).
                            changesvesiondata(classGrudSqlOperationsДляОперацииСозданеиНовойЗадачи.
                                            concurrentHashMapНабор,
                                    new PUBLIC_CONTENT(getApplicationContext()).МенеджерПотоков
                                    , sqLiteDatabaseДляНовгоЗадания);
//
            Log.d(getApplicationContext().getClass().getName(), "Результат_ПриписиИзменнийВерсииДанныхВФонеПриСменеОрганизации "
                    + Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы);

            // TODO: 21.03.2022
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
        }

        // TODO: 21.03.2022
        return Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы;
    }








    ///TODO метод определяем стоит запускать и создвать службу напоминаний или нет

    private boolean МетодВычисляемСтоитСоздаватьИЗапускатьСлужбуНапоминаний() {
        // final boolean[] РезультатВнутриЗапускатьУведомленияИлиНет = {false};
        try {



            ////

            Log.d(this.getClass().getName(), "  СЛУЖБА..... ДЛЯ проверяем нужно ли создвать и запускать службу нпоминаний работа с датами");

            /////todo если не изместен локальная версия являеться null перед проверкой то еще раз применяем выясняем локальную версиюПО для проверки

            long РезультатОбновлениеЛокальнойВерсииПО = 0;
            /////////
            /////TODO когда локальное ПО версию не нашли вытаемся ее увидить в базе

            int ПолучениеПравДляТаблицыПрава=0;

            Integer ПубличноеIDПолученныйИзСервлетаДляUUID=0;

            // TODO: 26.05.2021  перед обработкой уведомлений функция удаления Уведомлениц Сообщений не относящиеся к Пользователю
            ///
            Class_GRUD_SQL_Operations   class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете=new Class_GRUD_SQL_Operations(getApplicationContext());
            // TODO: 03.11.2021
            PUBLIC_CONTENT public_contentменеджер=new PUBLIC_CONTENT(getApplicationContext());

            ///
            class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете.
                    concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","SuccessLogin");
            ///////
            class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете.
                    concurrentHashMapНабор.put("СтолбцыОбработки","id");
            //

            ////
            class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете.
                    concurrentHashMapНабор.put("УсловиеЛимита","1");



            ////
            SQLiteCursor Курсор_ВычисляемПУбличныйID=null;
            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ

            Курсор_ВычисляемПУбличныйID= (SQLiteCursor)
                    class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете.
                    new GetData(getApplicationContext()).getdata(class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете.
                            concurrentHashMapНабор,public_contentменеджер.МенеджерПотоков,   sqLiteDatabase );

            Log.d(this.getClass().getName(), "GetData "+Курсор_ВычисляемПУбличныйID  );


            /////
            if (Курсор_ВычисляемПУбличныйID.getCount() > 0) {
                //////////
                Курсор_ВычисляемПУбличныйID.moveToFirst();

                ПубличноеIDПолученныйИзСервлетаДляUUID=Курсор_ВычисляемПУбличныйID.getInt(0);

                // TODO: 03.11.2021



                Log.w(this.getClass().getName(), "  ID " + ПубличноеIDПолученныйИзСервлетаДляUUID);


            }

            // TODO: 02.03.2022

            if(ПубличноеIDПолученныйИзСервлетаДляUUID==null){
                // TODO: 02.03.2022
                ПубличноеIDПолученныйИзСервлетаДляUUID=0;

            }





            // TODO: 26.05.2021 данные по высичлению ID



            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

            ///
            class_grud_sql_operationsIDпользоввателяДляСлужб=new Class_GRUD_SQL_Operations(getApplicationContext());

            ///
            class_grud_sql_operationsIDпользоввателяДляСлужб.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","view_tasks");//old для другой уведомления data_chat
            ///////
            class_grud_sql_operationsIDпользоввателяДляСлужб.concurrentHashMapНабор.put("СтолбцыОбработки","*");
            //
            class_grud_sql_operationsIDпользоввателяДляСлужб.concurrentHashMapНабор.put("ФорматПосика","status_write=?  AND id_user=?   AND alreadyshownnotifications<>? " +
                    " AND message IS NOT NULL  ");
            ///"_id > ?   AND _id< ?"


            //////
            class_grud_sql_operationsIDпользоввателяДляСлужб.concurrentHashMapНабор.put("УсловиеПоиска1",0);
            //
            class_grud_sql_operationsIDпользоввателяДляСлужб.concurrentHashMapНабор.put("УсловиеПоиска2",  ПубличноеIDПолученныйИзСервлетаДляUUID);


            // TODO: 02.03.2022

            class_grud_sql_operationsIDпользоввателяДляСлужб.concurrentHashMapНабор.put("УсловиеСортировки","date_update DESC");
            ////
          // class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНабор.put("УсловиеЛимита","1");
            ////
            class_grud_sql_operationsIDпользоввателяДляСлужб.concurrentHashMapНабор.put("УсловиеЛимита","1");
            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ

            //
            class_grud_sql_operationsIDпользоввателяДляСлужб.concurrentHashMapНабор.put("УсловиеПоиска3",  4);
            ///
            SQLiteCursor   Курсор_ДляСлужбыУведомлений_ТолькоДляЧата=null;


            Курсор_ДляСлужбыУведомлений_ТолькоДляЧата= (SQLiteCursor)  class_grud_sql_operationsIDпользоввателяДляСлужб.
                    new GetData(getApplicationContext()).getdata(class_grud_sql_operationsIDпользоввателяДляСлужб.concurrentHashMapНабор,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,   sqLiteDatabase );


            ////////

            Log.d(this.getClass().getName(), "Курсор_ДляСлужбыУведомлений_ТолькоДляЧата "  +Курсор_ДляСлужбыУведомлений_ТолькоДляЧата);





            // TODO: 26.05.2021 данные по высичлению ID

            SQLiteCursor Курсор_ДляСлужбыУведомлений_ТолькоДляЧатаТОлькоКоличествоЗадач = МетодПолучениеТОлЬКоКурсораДЛяПолучнеиеКоличетсовЗадач(ПубличноеIDПолученныйИзСервлетаДляUUID);



            ////////

            Log.d(this.getClass().getName(), "Курсор_ДляСлужбыУведомлений_ТолькоДляЧатаТОлькоКоличествоЗадач "  +Курсор_ДляСлужбыУведомлений_ТолькоДляЧатаТОлькоКоличествоЗадач);




/*
            // TODO: 09.09.2021   ____old
             Курсор_IDпользоввателяДляСлужб=
                                    CREATE_DATABASE.ССылкаНаСозданнуюБазу.rawQuery("SELECT *  From notification", null);

*/


            ОбщееКоличествоНЕпрочитанныхСтрок=0;


            // TODO: 09.09.2021  resltat

            if ( Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getCount()>0) {
                //////

                Log.d(this.getClass().getName(), "Курсор_ДляСлужбыУведомлений_ТолькоДляЧат количествро сттрочек  :::  "
                        +Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getCount());


                Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.moveToFirst();
                // TODO: 02.03.2022

                Курсор_ДляСлужбыУведомлений_ТолькоДляЧатаТОлькоКоличествоЗадач.moveToFirst();


                РезультатНужноЗапускатьУведомленияИлиНет=true;

                ОбщееКоличествоНЕпрочитанныхСтрок=       Курсор_ДляСлужбыУведомлений_ТолькоДляЧатаТОлькоКоличествоЗадач.getCount();

                Log.w(this.getClass().getName(), "  ОбщееКоличествоНЕпрочитанныхСтрок  " +ОбщееКоличествоНЕпрочитанныхСтрок);
                // TODO: 19.11.2021


                // TODO: 19.11.2021
                if(ОбщееКоличествоНЕпрочитанныхСтрок>=1){
                    // TODO: 19.11.2021
                    ОбщееКоличествоНЕпрочитанныхСтрок=ОбщееКоличествоНЕпрочитанныхСтрок-1;
                    // TODO: 19.11.2021

                    Log.w(this.getClass().getName(), "  ОбщееКоличествоНЕпрочитанныхСтрок  " +ОбщееКоличествоНЕпрочитанныхСтрок);
                }


                // TODO: 20.05.2021  продолжение уведомления определяем даты после того как получили права на сотрудника

                МетодПолучениеДатыПослеПолучениеПравСотрудникаДля_ОбщихУведомления(Курсор_ДляСлужбыУведомлений_ТолькоДляЧата);




            } else {
                Log.w(this.getClass().getName(), "  СЛУЖБА...  НЕТ СООБЩЕНИ ДЛЯ ОТОБРАЖЕНИЯ В ЧАТЕ НЕТ ПРОПУСКАЕМ ХОЛОСТОЙ ХОД СЛУЖБЫ УВЕДОМЛЕНИЯ ЧАТ " +ПолучениеПравДляТаблицыПрава);
                // TODO: 15.11.2021
                РезультатНужноЗапускатьУведомленияИлиНет=false;
            }

        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РезультатНужноЗапускатьУведомленияИлиНет ;
    }


    // TODO: 02.03.2022

    private SQLiteCursor МетодПолучениеТОлЬКоКурсораДЛяПолучнеиеКоличетсовЗадач(Integer ПубличноеIDПолученныйИзСервлетаДляUUID) throws ExecutionException, InterruptedException {
        // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

        SQLiteCursor   Курсор_ДляСлужбыУведомлений_ТолькоДляЧатаТОлькоКоличествоЗадач=null;
     try{
        ///
        class_grud_sql_operationsIDпользоввателяДляСлужб=new Class_GRUD_SQL_Operations(getApplicationContext());

        ///
        class_grud_sql_operationsIDпользоввателяДляСлужб.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","view_tasks");//old для другой уведомления data_chat
        ///////
        class_grud_sql_operationsIDпользоввателяДляСлужб.concurrentHashMapНабор.put("СтолбцыОбработки","*");
        //
        class_grud_sql_operationsIDпользоввателяДляСлужб.concurrentHashMapНабор.put("ФорматПосика","status_write=?  AND id_user=?   AND alreadyshownnotifications<>? " +
                " AND message IS NOT NULL  ");
        ///"_id > ?   AND _id< ?"


        //////
        class_grud_sql_operationsIDпользоввателяДляСлужб.concurrentHashMapНабор.put("УсловиеПоиска1",0);
        //
        class_grud_sql_operationsIDпользоввателяДляСлужб.concurrentHashMapНабор.put("УсловиеПоиска2", ПубличноеIDПолученныйИзСервлетаДляUUID);

         //
         class_grud_sql_operationsIDпользоввателяДляСлужб.concurrentHashMapНабор.put("УсловиеПоиска3",  4);
        // TODO: 02.03.2022

        class_grud_sql_operationsIDпользоввателяДляСлужб.concurrentHashMapНабор.put("УсловиеСортировки","date_update DESC");
        ////
        // class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНабор.put("УсловиеЛимита","1");
        ////
        //class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНабор.put("УсловиеЛимита","1");
        // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
        ///



        Курсор_ДляСлужбыУведомлений_ТолькоДляЧатаТОлькоКоличествоЗадач= (SQLiteCursor)  class_grud_sql_operationsIDпользоввателяДляСлужб.
                new GetData(getApplicationContext()).getdata(class_grud_sql_operationsIDпользоввателяДляСлужб.concurrentHashMapНабор,
                Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,   sqLiteDatabase );


        ////////

        Log.d(this.getClass().getName(), "Курсор_ДляСлужбыУведомлений_ТолькоДляЧатаТОлькоКоличествоЗадач "  +Курсор_ДляСлужбыУведомлений_ТолькоДляЧатаТОлькоКоличествоЗадач);


    } catch (Exception e)

    {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
    }


        return Курсор_ДляСлужбыУведомлений_ТолькоДляЧатаТОлькоКоличествоЗадач;
    }


    // TODO: 20.05.2021  продолжение уведомления определяем даты после того как получили права на сотрудника



    private void МетодПолучениеДатыПослеПолучениеПравСотрудникаДля_ОбщихУведомления(SQLiteCursor  Курсор_ДляСлужбыУведомлений_ТолькоДляЧата) throws ParseException {
        // TODO: 20.05.2021 действие вьторое

        try{

            Log.d(this.getClass().getName(), "   Курсор_ДляСлужбыУведомлений_ТолькоДляЧата "+Курсор_ДляСлужбыУведомлений_ТолькоДляЧата);


            ////TODO launch do
            //TODO перед созданеим

            БуферСамиУведомленияЛинкСамиУведомления=new ArrayList();

            do {
                ////



                // TODO: 03.02.2022
                person=new Person.Builder();


                /////
                /////
                Integer ИндексКтоНаписалСообщениеID  = Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getColumnIndex("user_update");///id_user
                /////
                Integer КтоНаписалСообщениеID  = Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getInt(ИндексКтоНаписалСообщениеID);
                //
                String КтоНаписалСообщениеФИО=new String();

                Log.d(this.getClass().getName(), "КтоНаписалСообщениеID " + КтоНаписалСообщениеID);

                // TODO: 07.02.2022 вытаскием текущий uuid для того чтобы далее установить сатутсс о ознакомлен

                /////
                Integer ИндексКтоНаписалСообщениеUUID  = Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getColumnIndex("uuid");///id_user
                /////
                Long КтоНаписалСообщениеUUID  = Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getLong(ИндексКтоНаписалСообщениеUUID);

                Log.d(this.getClass().getName(), "КтоНаписалСообщениеUUID " + КтоНаписалСообщениеUUID);

                // TODO: 16.11.2021 find FIO
                ///
                class_grud_sql_operationsIDпользоввателяДляСлужб=new Class_GRUD_SQL_Operations(getApplicationContext());

                ///
                class_grud_sql_operationsIDпользоввателяДляСлужб.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","chat_users");
                ///////
                class_grud_sql_operationsIDпользоввателяДляСлужб.concurrentHashMapНабор.put("СтолбцыОбработки","name");
                //
                class_grud_sql_operationsIDпользоввателяДляСлужб.concurrentHashMapНабор.put("ФорматПосика","_id=?   AND name IS NOT NULL ");
                ///"_id > ?   AND _id< ?"
                //////
                class_grud_sql_operationsIDпользоввателяДляСлужб.concurrentHashMapНабор.put("УсловиеПоиска1",КтоНаписалСообщениеID);

    /*            class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНабор.put("УсловиеСортировки","date_update DESC");
                ////
                class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНабор.put("УсловиеЛимита","5");*/

                // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
                ///
                SQLiteCursor   Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал=null;


                Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал= (SQLiteCursor)  class_grud_sql_operationsIDпользоввателяДляСлужб.
                        new GetData(getApplicationContext()).getdata(class_grud_sql_operationsIDпользоввателяДляСлужб.concurrentHashMapНабор,
                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,   sqLiteDatabase );


                ////////

                Log.d(this.getClass().getName(), "Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал "  +Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал);

                if (Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал.getCount()>0) {
                    //TODO

                    Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал.moveToFirst();
                    КтоНаписалСообщениеФИО=      Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал.getString(0).trim();

                  //  КтоНаписалСообщениеФИО=      Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал.getString(0).trim();
                }

                ////////

                Log.d(this.getClass().getName(), "КтоНаписалСообщениеФИО "  +КтоНаписалСообщениеФИО);









                // TODO: 15.11.2021
                Integer ИНдексПРочитаногоСообщенияUUID= Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getColumnIndex("uuid");

               UUIDРочитаногоЗаданиеДляКотрогоДалееБудетПроизведенаСменаСтсусаНАОзнакомленный= Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getLong(ИНдексПРочитаногоСообщенияUUID);


                Log.d(this.getClass().getName(), "UUIDРочитаногоЗаданиеДляКотрогоДалееБудетПроизведенаСменаСтсусаНАОзнакомленный "  +UUIDРочитаногоЗаданиеДляКотрогоДалееБудетПроизведенаСменаСтсусаНАОзнакомленный);





              Integer ИНдексПРочитаногоСообщения = Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getColumnIndex("date_update");//TODO OLD date_start

                String ВремНеПРочитаногоСообщения= Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getString(ИНдексПРочитаногоСообщения);


                Log.d(this.getClass().getName(), " ВремНеПРочитаногоСообщения  " + ВремНеПРочитаногоСообщения);
                // TODO: 15.11.2021

                SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", new Locale("ru"));//yyyy-MM-dd HH:mm:ss.SSS  ///TODO yyyy-MM-dd HH:mm
                //

                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));

                Date date = null;
                date = simpleDateFormat.parse(ВремНеПРочитаногоСообщения);

                simpleDateFormat.applyPattern(" dd EEEE yyyy HH:mm");//dd-MM-yyyy  // EEEE yyyy HH:mm"

                String ФиналВремНеПРочитаногоСообщения = simpleDateFormat.format(date);

                Log.d(this.getClass().getName(), "ФиналВремНеПРочитаногоСообщения " + ФиналВремНеПРочитаногоСообщения+"\n");

                ///TODO заполенения ДАННЫХ УВЕДОМЛЕНИЯ ОДНОРАЗОВАГО ДЛЯ ЧАТА
                // TODO: 03.02.2022

      /*          // TODO: 03.02.2022
                БуферСамиУведомленияЛинкСлужебнаИнформация.add("\n"+"автор:"+КтоНаписалСообщениеФИО);
                // TODO: 03.02.2022
                // TODO: 03.02.2022
                БуферСамиУведомленияЛинкСлужебнаИнформация.add("количество сообщений: +("+ОбщееКоличествоНЕпрочитанныхСтрок+")");*/
                // TODO: 03.02.2022
                //////todoСамиУведомленияВЧатеНеПрочитанный.matches("[а-яА-Я]")///[m-nM-N]

                // TODO: 04.02.2022
                // TODO: 15.11.2021
                Integer ИндексСтатусПрочтенияПриВыданнйоЗадании=   Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getColumnIndex("status_write");

               Integer СамСтатусПрочтенияПриВыданнйоЗадании= Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getInt(ИндексСтатусПрочтенияПриВыданнйоЗадании);



                Log.d(this.getClass().getName(), " СамСтатусПрочтенияПриВыданнйоЗадании  " + СамСтатусПрочтенияПриВыданнйоЗадании);



                // TODO: 15.11.2021
                Integer ИндексЗаданияID=   Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getColumnIndex("head_message");// todo old id


                String СамСтатусIDЗадание;

                 СамСтатусIDЗадание= Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getString(ИндексЗаданияID);



                Log.d(this.getClass().getName(), " СамСтатусIDЗадание  " + СамСтатусIDЗадание);





                String СтатусДошлоПользователюЗадача;

                // TODO: 04.02.2022
                if (СамСтатусПрочтенияПриВыданнйоЗадании==0) {

                    СтатусДошлоПользователюЗадача="доставлено";

                    Log.d(this.getClass().getName(), " СамСтатусПрочтенияПриВыданнйоЗадании  " + СамСтатусПрочтенияПриВыданнйоЗадании);
                } else {
                    СтатусДошлоПользователюЗадача="уже отработано";

                    Log.d(this.getClass().getName(), " СамСтатусПрочтенияПриВыданнйоЗадании  " + СамСтатусПрочтенияПриВыданнйоЗадании);
                }


                // TODO: 16.11.2021  само сообщение

                // TODO: 15.11.2021
                Integer ИндексСамогоЗадании=   Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getColumnIndex("message");
                /////
                String СамиУведомленияВЧатеНеПрочитанный = Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getString(ИндексСамогоЗадании).trim();


                Log.d(this.getClass().getName(), " СамиУведомленияВЧатеНеПрочитанный  " + СамиУведомленияВЧатеНеПрочитанный+
                        " СамСтатусIDЗадание " +СамСтатусIDЗадание);



                // TODO: 15.11.2021
                Integer ИндексУжеПоказывалиУведомленияЗадании=   Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getColumnIndex("alreadyshownnotifications");
                /////
                Integer СтатусзадачиУжеПоказывалиТекущееУведомления = Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getInt(ИндексУжеПоказывалиУведомленияЗадании);


                Log.d(this.getClass().getName(), " СамиУведомленияВЧатеНеПрочитанный  " + СамиУведомленияВЧатеНеПрочитанный+
                        " СамСтатусIDЗадание " +СамСтатусIDЗадание + "  СтатусзадачиУжеПоказывалиТекущееУведомления " +СтатусзадачиУжеПоказывалиТекущееУведомления);



                // TODO: 15.11.2021 узнаеv статус задачи и передаем ее
                Integer ИндексУжеУведомленияСтатусЕЕ=   Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getColumnIndex("status_write");
                /////
                Integer СтатусзадачиТекущееУведомленияСтатус= Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getInt(ИндексУжеУведомленияСтатусЕЕ);

                Log.d(this.getClass().getName(), " СтатусзадачиТекущееУведомленияСтатус  " + СтатусзадачиТекущееУведомленияСтатус);
                String СтатусПоказывмПользователю =new String();

                switch (СтатусзадачиТекущееУведомленияСтатус){

                    case 0:
                        СтатусПоказывмПользователю="В процессе...";
                        break;
                    case 1:
                        СтатусПоказывмПользователю="Отказ";
                        break;
                    case 2:
                        СтатусПоказывмПользователю="Выполнена";
                        break;
                    case 3:
                        СтатусПоказывмПользователю="Отмененная";
                        break;
                }







// TODO: 20.06.2022  заполенияе тольк если данное уведомления задачиеще не показывали
                if (СтатусзадачиУжеПоказывалиТекущееУведомления==0 && СтатусзадачиТекущееУведомленияСтатус==0) {
                    // TODO: 20.06.2022
                    БуферСамиУведомленияЛинкСамиУведомления.add("заголовок:"+" "+СамСтатусIDЗадание+"\n"+
                            "задание: "+" "+СамиУведомленияВЧатеНеПрочитанный +"\n"+
                            "создано: ("+ФиналВремНеПРочитаногоСообщения+")" +"\n"+
                            "cтатус: ("+СтатусПоказывмПользователю+")"+"\n");
                }

                // TODO: 02.03.2022




                МетодПослеЗапоелнияВЦиклеЗадачЗаполянем(КтоНаписалСообщениеФИО, UUIDРочитаногоЗаданиеДляКотрогоДалееБудетПроизведенаСменаСтсусаНАОзнакомленный);

                Log.d(this.getClass().getName(), " БуферСамиУведомленияЛинкСамиУведомления  " + БуферСамиУведомленияЛинкСамиУведомления.toString());



                // TODO: 19.11.2021  после обработки одно записи выходим
                //todo while
            } while (Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.moveToNext());







            // TODO: 19.11.2021 после обработки добавляем

            // БуферСамиУведомленияЛинкСамиУведомления.append(" +").append("(").append(ОбщееКоличествоНЕпрочитанныхСтрок) .append(")").append(" сообщений.") ;
            // TODO: 19.11.2021
            Log.d(this.getClass().getName(), "СамиУведомления " + БуферСамиУведомленияЛинкСамиУведомления.toString() +
                    " БуферСамиУведомленияЛинкСамиУведомления " + БуферСамиУведомленияЛинкСамиУведомления.size()+
                    "   БуферСамиУведомленияЛинкСамиУведомления " + БуферСамиУведомленияЛинкСамиУведомления.size());


            ////TODO закрываем курсор

            if (Курсор_ДляСлужбыУведомлений_ТолькоДляЧата!=null) {

                if (!Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.isClosed()) {

                    Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.close();
                }
            }


            // TODO: 19.05.2021  ошибки
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            //TODO ПЕРЕД СОЗДАНИЕМ НОВОГО СООБЕЩНИЯ ОБНУЛЯЕМ ПРДЫДУЩЕЕ

            NotificationManager notificationManager = (NotificationManager)
                    getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

            notificationManager.cancel(Integer.parseInt(PROCESS_ID_УведомленияПлановая));
        }




    }

    private void МетодПослеЗапоелнияВЦиклеЗадачЗаполянем(String КтоНаписалСообщениеФИО,
                                                         Long UUIDРочитаногоЗаданиеДляКотрогоДалееБудетПроизведенаСменаСтсусаНАОзнакомленный) {

        // TODO: 02.03.2022


        try{
            Log.d(this.getClass().getName(),"   onOpen БАЗА  ДАННЫХ   ДСУ-1 настройки базы КтоНаписалСообщениеФИО "+КтоНаписалСообщениеФИО+
                    " UUIDРочитаногоЗаданиеДляКотрогоДалееБудетПроизведенаСменаСтсусаНАОзнакомленный "+UUIDРочитаногоЗаданиеДляКотрогоДалееБудетПроизведенаСменаСтсусаНАОзнакомленный);
            // TODO: 02.03.2022

        String СамиУведомленияВЧатеНеПрочитанный;
        // TODO: 04.02.2022

        СамиУведомленияВЧатеНеПрочитанный=null;

        СамиУведомленияВЧатеНеПрочитанный=БуферСамиУведомленияЛинкСамиУведомления.toString().replace("[","");

        СамиУведомленияВЧатеНеПрочитанный=СамиУведомленияВЧатеНеПрочитанный.replace("]","");


        // TODO: 07.02.2022 заполения сообщения для ОБШЕЙ УВЕДОМЛЕНИЯ

        // TODO: 07.02.2022
        person.setKey(СамиУведомленияВЧатеНеПрочитанный);
        // TODO: 07.02.2022
        person.setBot(true);

        person.setName(КтоНаписалСообщениеФИО);
        // TODO: 07.02.2022 \
        // person.setIcon( IconCompat.createWithResource(Контекст, R.drawable.icon_dsu1_for_fragment1_chat2));
            // TODO: 07.02.2022

            person.setIcon(IconCompat.createWithResource(context, R.drawable.icon_dsu1_for_fragment1_chat2_for_public));

            // TODO: 07.02.2022устанавливаем записываем текущий UUID для того чтобы потом перердатьего для Озванкомления

            HashMap<Integer, Long> hashMapХэшДляЗапоминиялUUID = new HashMap();
            // TODO: 07.02.2022
            hashMapХэшДляЗапоминиялUUID.put(new Random().nextInt(), UUIDРочитаногоЗаданиеДляКотрогоДалееБудетПроизведенаСменаСтсусаНАОзнакомленный);

            person.setUri(String.valueOf(UUIDРочитаногоЗаданиеДляКотрогоДалееБудетПроизведенаСменаСтсусаНАОзнакомленный));
            //  person .setUri(String.valueOf(hashMapХэшДляЗапоминиялUUID));
            // TODO: 07.02.2022  person
            person.build();


            // TODO: 03.02.2022  УВЕДОМЛЕНИЯ ДЛЯ ОБШЕЙ СИНХРОНИАЗЦИИ
            messagingStyleДля_ОбщихУведомлений.addMessage(person.build().getKey(), System.currentTimeMillis(), "от:" + person.build().getName()
                    + " +" +
                    "(" + ОбщееКоличествоНЕпрочитанныхСтрок + ")");


        Log.d(this.getClass().getName(), "СамиУведомленияВЧатеНеПрочитанный "
                + СамиУведомленияВЧатеНеПрочитанный +" БуферСамиУведомленияЛинкСамиУведомления.size() "
                +БуферСамиУведомленияЛинкСамиУведомления.size()+ " messagingStyleДля_ОбщихУведомлений " +messagingStyleДля_ОбщихУведомлений+ "  СамиУведомленияВЧатеНеПрочитанный " +СамиУведомленияВЧатеНеПрочитанный);




    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        // TODO: 01.09.2021 метод вызова
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());

    }
    }


    // TODO: 17.11.2021  end classs worl manager

    }






























