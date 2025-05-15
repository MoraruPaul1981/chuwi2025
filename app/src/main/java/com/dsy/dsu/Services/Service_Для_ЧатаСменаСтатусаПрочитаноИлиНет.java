package com.dsy.dsu.Services;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Context;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.dsy.dsu.BusinessLogicAll.CoreBinessLogics.CoreBinessLogics;
import com.dsy.dsu.BusinessLogicAll.Class_GRUD_SQL_Operations;
import com.dsy.dsu.BusinessLogicAll.DATE.Class_Generation_Data;
import com.dsy.dsu.BusinessLogicAll.VersionCurentTable;
import com.dsy.dsu.CnangeServers.BinessLogicPublicContent;
import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;
import com.dsy.dsu.BusinessLogicAll.Class_Generation_UUID;
import com.google.android.material.button.MaterialButton;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeoutException;

import javax.crypto.NoSuchPaddingException;
import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class Service_Для_ЧатаСменаСтатусаПрочитаноИлиНет extends IntentService {

    // Binder given to clients
    public LocalBinderДляЧата binderЧАТ = new LocalBinderДляЧата();
    // Random number generator
    private Context context;

    private Class_GRUD_SQL_Operations    class_grud_sql_operations ;


    @Inject
    public   SQLiteDatabase sqLiteDatabase;
    private CoreBinessLogics modelДляФрагментаДляОперацииЗаписиНовгоСтатусаПрочитанного ;
    private BinessLogicPublicContent Class_Engine_SQLГдеНаходитьсяМенеджерПотоков ;
    private RecordNewErros recordNewErros;


    public Service_Для_ЧатаСменаСтатусаПрочитаноИлиНет() {
        super("Service_Для_ЧатаСменаСтатусаПрочитаноИлиНет");
    }

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinderДляЧата extends Binder {
        public Service_Для_ЧатаСменаСтатусаПрочитаноИлиНет getService() {
            // Return this instance of LocalService so clients can call public methods
            return Service_Для_ЧатаСменаСтатусаПрочитаноИлиНет.this;
        }
    }


    @Override
    public IBinder onBind(@NonNull Intent intent) {
        Log.i(getApplicationContext().getClass().getName(), "     public IBinder onBind(@NonNull Intent intent) { " + new Date()+"\n"+
                " Thread.currentThread().getName()  " +Thread.currentThread().getName());//todo super.onBind(intent)
        return  binderЧАТ ;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        System.out.println("     public static void enqueueWork(Context context, Intent work) {" + new Date()+"\n"+
                " Thread.currentThread().getName()  " +Thread.currentThread().getName());
    }


   @Override
    public void onCreate() {
        super.onCreate();
       // TODO: 16.04.2025
       //sqLiteDatabase = EntryPoints.get(context, HiltInterfacesqlite.class).getHiltSqlite();
       Log.d(context.getClass().getName(), "\n"
               + " время: " + new Date() + "\n+" +
               " Класс в процессе... " + this.getClass().getName() + "\n" +
               " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " sqLiteDatabase " +sqLiteDatabase);
        Log.i(getApplicationContext().getClass().getName(), " public class Service_Для_ЧатаСменаСтатусаПрочитаноИлиНет extends JobIntentService { " + new Date()+"\n"+
                " Thread.currentThread().getName()  " +Thread.currentThread().getName());
    }



    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(getApplicationContext().getClass().getName(), "    public boolean onUnbind(Intent intent) { " + new Date()+"\n"+
                " Thread.currentThread().getName()  " +Thread.currentThread().getName());
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {

    try{

    Log.i(getApplicationContext().getClass().getName(), "  Service_Для_ЧатаСменаСтатусаПрочитаноИлиНет   public void onDestroy() { " + new Date()+"\n"+
            " Thread.currentThread().getName()  " +Thread.currentThread().getName());

    super.onDestroy();

    stopSelf();
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i(getApplicationContext().getClass().getName(), "     public void onRebind(Intent intent) { " + new Date()+"\n"+
                " Thread.currentThread().getName()  " +Thread.currentThread().getName());
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
    }

    @Override
    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        super.dump(fd, writer, args);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        Log.i(getApplicationContext().getClass().getName(), "  attachBaseContext      protected void attachBaseContext(Context newBase) {  " + new Date()+"\n"+
                " Thread.currentThread().getName()  " +Thread.currentThread().getName());
        this.context=newBase;
    }

// TODO: 15.07.2022  пользовательский код
    /** method for clients */
    public Integer МетодВнутриБиндингСлужбыСменаСтатусаВЧатеПрочитаноИлиНет(@NonNull Intent intent,@NonNull Context context) {
// TODO: 18.06.2022
        Integer результатСменыСтатусаВЧатеПрочитаноИлиНет = 0;
        try{
            this.context=context;

            Bundle bundleДАнныеПришлиСЧатаДляСменыСтатуса= intent.getExtras();
            // TODO: 18.06.2022
            String ЗамоЗначениеТекущегоСообщения=   bundleДАнныеПришлиСЧатаДляСменыСтатуса.getString("ЗамоЗначениеТекущегоСообщения");

            Long РезультатВставкиНовогоФлагаЧтоТекущееСообщенеиОтДруговоПользоватлеяБылоПрочитанно=
                    bundleДАнныеПришлиСЧатаДляСменыСтатуса.getLong("РезультатВставкиНовогоФлагаЧтоТекущееСообщенеиОтДруговоПользоватлеяБылоПрочитанно");


            Log.d(this.getClass().getName(), "  bundleДАнныеПришлиСЧатаДляСменыСтатуса " + bundleДАнныеПришлиСЧатаДляСменыСтатуса + "" +
                    " РезультатВставкиНовогоФлагаЧтоТекущееСообщенеиОтДруговоПользоватлеяБылоПрочитанно "+РезультатВставкиНовогоФлагаЧтоТекущееСообщенеиОтДруговоПользоватлеяБылоПрочитанно);
            // TODO: 18.06.2022  сама смена статуса через службы биндинга

            результатСменыСтатусаВЧатеПрочитаноИлиНет =
                    МетодЗаписиСтатусаДляТекущегоПользователя(ЗамоЗначениеТекущегоСообщения ,
                            РезультатВставкиНовогоФлагаЧтоТекущееСообщенеиОтДруговоПользоватлеяБылоПрочитанно,context);


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        Log.i(context.getClass().getName(), "        public Boolean МетодВнутриБиндингСлужбыСменаСтатуса() {" + new Date()+"\n"+
                " Thread.currentThread().getName()  " +Thread.currentThread().getName()+ " intent " +intent);
        return результатСменыСтатусаВЧатеПрочитаноИлиНет;
    }
    

    // TODO: Rename actions, choose action names that describe tasks that this
    // TODO: 18.06.2022  сама перенесенный метод для смены статуча

    @SuppressLint("SuspiciousIndentation")
    Integer МетодЗаписиСтатусаДляТекущегоПользователя(
          @NonNull String СамоЗначенияИндифкатора,
           @NonNull Long ПолученныйUUIDТекущейСтрочкиКоторуюПрочитали,
           @NonNull Context context) {

        Integer РезультатОбновленияСтатусЧатаКакПрочитанный = 0;
        Long РезультатУвеличинаяВерсияДАныхЧата = 0L;
        String ТаблицаОбработкиВнутриЧтатаПриУвеличсенииВерсииДаннвъКоглаПрочинаноСообещния = "data_chat";

        try {
           class_grud_sql_operations = new Class_GRUD_SQL_Operations(context);
         modelДляФрагментаДляОперацииЗаписиНовгоСтатусаПрочитанного = new CoreBinessLogics(context);
             Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new BinessLogicPublicContent(context);
            recordNewErros =new RecordNewErros(context);

            // TODO: 15.07.2022

            ContentValues contentValuesОбновленниВТАблицеКакПрочитанныйМеняемСтатусЗаписисВчатеПостлеПросмотра = new ContentValues();
            contentValuesОбновленниВТАблицеКакПрочитанныйМеняемСтатусЗаписисВчатеПостлеПросмотра.put("status_write", 1);

            // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
          РезультатУвеличинаяВерсияДАныхЧата =
                    new VersionCurentTable(context).upVersionCurentTable(    ТаблицаОбработкиВнутриЧтатаПриУвеличсенииВерсииДаннвъКоглаПрочинаноСообещния);
            Log.d(this.getClass().getName(), " РезультатУвеличинаяВерсияДАныхЧата  " + РезультатУвеличинаяВерсияДАныхЧата);

            contentValuesОбновленниВТАблицеКакПрочитанныйМеняемСтатусЗаписисВчатеПостлеПросмотра.put("current_table", РезультатУвеличинаяВерсияДАныхЧата);
            РезультатОбновленияСтатусЧатаКакПрочитанный = 0;
            РезультатОбновленияСтатусЧатаКакПрочитанный = modelДляФрагментаДляОперацииЗаписиНовгоСтатусаПрочитанного.
                    ЛокальногоОбновлениеДанныхЧерезКонтейнерУниверсальная(ТаблицаОбработкиВнутриЧтатаПриУвеличсенииВерсииДаннвъКоглаПрочинаноСообещния,
                            contentValuesОбновленниВТАблицеКакПрочитанныйМеняемСтатусЗаписисВчатеПостлеПросмотра,
                            ПолученныйUUIDТекущейСтрочкиКоторуюПрочитали, СамоЗначенияИндифкатора);
            // TODO: 18.06.2022 повышаем верисю после успегной смены статус
            Log.d(this.getClass().getName(), "  РезультатОбновленияСтатусЧатаКакПрочитанный"
                    + РезультатОбновленияСтатусЧатаКакПрочитанный+ " РезультатОбновленияСтатусЧатаКакПрочитанный " +РезультатОбновленияСтатусЧатаКакПрочитанный);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            recordNewErros.recordnewerror(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        if (РезультатОбновленияСтатусЧатаКакПрочитанный > 0) {
            РезультатОбновленияСтатусЧатаКакПрочитанный = Integer.parseInt(String.valueOf(РезультатУвеличинаяВерсияДАныхЧата));
        }
        return РезультатОбновленияСтатусЧатаКакПрочитанный;   // TODO: 05.07.2021 вставка новго сообщения в деве таблоицы Code_For_Chats_КодДля_Чата and DATA_Chat
    }


    // TODO: 15.07.2022  ПОЛУЧЕНИЕ ДАННЫХ ДЛЯ ЧАТА


  public   SQLiteCursor МетодГенерацияКурсораДляЧата(@NonNull Long ПолученыйУжеСуществующийUUIDИзПерепискиДляЧата,@NonNull Context context) throws ExecutionException, InterruptedException {
        SQLiteCursor          КурсорДанныеДлязаписиичтнияЧата=null;
        try {
            this.context=context;
            class_grud_sql_operations = new Class_GRUD_SQL_Operations(context);
            modelДляФрагментаДляОперацииЗаписиНовгоСтатусаПрочитанного = new CoreBinessLogics(context);
            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new BinessLogicPublicContent(context);
            recordNewErros =new RecordNewErros(context);

            Log.i(context.getClass().getName(),
                    " ПолученыйУжеСуществующийUUIDИзПерепискиДляЧата    " +ПолученыйУжеСуществующийUUIDИзПерепискиДляЧата);//todo super.onBind(intent)

            if (ПолученыйУжеСуществующийUUIDИзПерепискиДляЧата > 0) {
                class_grud_sql_operations.
                        concurrentHashMapНабор.put("ПодЗапросНомер1",

                                " SELECT   * FROM data_chat  WHERE  chat_uuid  =" + ПолученыйУжеСуществующийUUIDИзПерепискиДляЧата +
                                        "  AND   message IS NOT NULL    " +
                                        " ORDER BY   date_update     ASC, id   ASC  ");// current_table    ///   date_update current_table     ASC
                   /* " SELECT   * FROM data_chat  WHERE  chat_uuid  =" + ПолученыйУжеСуществующийUUIDИзПерепискиДляЧата +
                            "  AND   message IS NOT NULL    " +
                            " ORDER BY    date_update     ASC   ");// current_table    ///   date_update*/
                // TODO: 19.06.2022  ГЛАВНЫЙ КУРСОР ЧАТ

                КурсорДанныеДлязаписиичтнияЧата = (SQLiteCursor) class_grud_sql_operations.
                        new GetData(context).getdata(class_grud_sql_operations.
                                concurrentHashMapНабор,
                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков
                        ,    sqLiteDatabase);
                if (КурсорДанныеДлязаписиичтнияЧата != null) {
                    if (КурсорДанныеДлязаписиичтнияЧата.getCount() > 0) {
                        КурсорДанныеДлязаписиичтнияЧата.moveToFirst();
                    }
                    Log.i(context.getClass().getName(),
                            " void МетодПолучениеДанныхдляФрагментаЧитатьиПисатьЧат() throws ExecutionException, InterruptedException {" +
                                    "    КурсорДанныеДлязаписиичтнияЧата    " +КурсорДанныеДлязаписиичтнияЧата);//todo super.onBind(intent)
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            recordNewErros.recordnewerror(e.toString(),
                    context.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  КурсорДанныеДлязаписиичтнияЧата;
    }


// TODO: 15.07.2022 метод создание нового сообщения только в одну таблицу
private Long МетодЗаписиНовогоСообщенияТольковТаблицу_CHAT_КогдаЕщеМеждуПользователямиНетПереписки(
        @NonNull Integer ПубличныйIDДляФрагмента,
        @NonNull Long НовыйUUIDДляОбеихТаблицЧАТиДАТАЧАТдляПоляPARENT_UUID
,@NonNull Context context
,@NonNull Integer IDСкемПереписываюсь)
        throws ExecutionException,
        InterruptedException,
        TimeoutException,
        NoSuchPaddingException,
        NoSuchAlgorithmException,
        InvalidKeyException {
    final Long[] РезультатВставки_НовойЗаписиРодительскуюТаблицыЧАТ = {0l};
    final    String ПерваяТаблицыОбработкиТаблицаЧат = "chats";


    try {
        this.context=context;
        class_grud_sql_operations = new Class_GRUD_SQL_Operations(context);
        modelДляФрагментаДляОперацииЗаписиНовгоСтатусаПрочитанного = new CoreBinessLogics(context);
        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new BinessLogicPublicContent(context);
        recordNewErros =new RecordNewErros(context);


        LinkedBlockingQueue<Integer> linkedBlockingQueueДляЗаписиСразуДвестрокиЧатОДляКого = new LinkedBlockingQueue();
    linkedBlockingQueueДляЗаписиСразуДвестрокиЧатОДляКого.offer(ПубличныйIDДляФрагмента);  //todo Я ---
    linkedBlockingQueueДляЗаписиСразуДвестрокиЧатОДляКого.offer(Integer.parseInt(String.valueOf(IDСкемПереписываюсь)));//TODO КОМУ ПИШЕМ



    // TODO: 15.07.2022 сама операция вставки
    linkedBlockingQueueДляЗаписиСразуДвестрокиЧатОДляКого.forEach((ТекущееЗначениеДляЗаписиВЦиклеВДваСтлбика) -> {

   try{

            ContentValues contentValuesЗаписьНовогоСообщения_ТаблицаЧат = new ContentValues();
            String СгенерированованныйДатаДляФрагмента = new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();
            contentValuesЗаписьНовогоСообщения_ТаблицаЧат.put("date_update", СгенерированованныйДатаДляФрагмента);
            contentValuesЗаписьНовогоСообщения_ТаблицаЧат.put("user_update", ПубличныйIDДляФрагмента);
            contentValuesЗаписьНовогоСообщения_ТаблицаЧат.put("id_user", Integer.parseInt(ТекущееЗначениеДляЗаписиВЦиклеВДваСтлбика.toString()));////бышвий user_for     ПолученыйIDДляЧата
           // contentValuesЗаписьНовогоСообщения_ТаблицаЧат.putNull("_id");////бышвий user_for

       Long   ЛокльныйUUIDТОлькоДЛЯОднойТаблицыChats =
                    (Long) new Class_Generation_UUID(context).МетодГенерацииUUID();
            ЛокльныйUUIDТОлькоДЛЯОднойТаблицыChats = ЛокльныйUUIDТОлькоДЛЯОднойТаблицыChats + new Random().nextInt(10);

            contentValuesЗаписьНовогоСообщения_ТаблицаЧат.put("uuid", ЛокльныйUUIDТОлькоДЛЯОднойТаблицыChats);// TODO Локальный Сгенерированый UUID

            contentValuesЗаписьНовогоСообщения_ТаблицаЧат.put("uuid_parent", НовыйUUIDДляОбеихТаблицЧАТиДАТАЧАТдляПоляPARENT_UUID);// TODO Общий UUID

       // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
       Long РезультатУвеличинаяВерсияДАныхЧата=
               new VersionCurentTable(context).upVersionCurentTable(    ПерваяТаблицыОбработкиТаблицаЧат);
       Log.d(this.getClass().getName(), " РезультатУвеличинаяВерсияДАныхЧата  " + РезультатУвеличинаяВерсияДАныхЧата);


       // TODO: 18.11.2022
            contentValuesЗаписьНовогоСообщения_ТаблицаЧат.put("current_table", РезультатУвеличинаяВерсияДАныхЧата);
            РезультатВставки_НовойЗаписиРодительскуюТаблицыЧАТ[0] = new CoreBinessLogics(context)
                    .ВставкаДанныхЧерезКонтейнерТолькоПриСозданииНСообщенияДЛЯЧата(ПерваяТаблицыОбработкиТаблицаЧат,
                            contentValuesЗаписьНовогоСообщения_ТаблицаЧат, ПерваяТаблицыОбработкиТаблицаЧат, "",
                            true);
                contentValuesЗаписьНовогоСообщения_ТаблицаЧат.clear();
            Object вЫКИДИВАЕМоТРАБТАННЫЙэЛЕМЕНТ = linkedBlockingQueueДляЗаписиСразуДвестрокиЧатОДляКого.take().longValue();

    } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            recordNewErros.recordnewerror(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    });

        Log.w(context.getClass().getName(),  " РезультатВставки_НовойЗаписиРодительскуюТаблицыЧАТ[0] " +РезультатВставки_НовойЗаписиРодительскуюТаблицыЧАТ[0]);

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        recordNewErros.recordnewerror(e.toString(),
                this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

    return РезультатВставки_НовойЗаписиРодительскуюТаблицыЧАТ[0];

    // TODO: 15.07.2022 второй метод создания новго сообщения только для Таблицы DATA_CHAT когда между участниками уже есть переписка  

}



    private Long МетодСозданиеНовогоСообщениявТаблицы_DATA_CHATS_КогдаМеждуУчастникамиУжеБылаПереписка(
            @NonNull Integer ПубличныйIDДляФрагмента,
            @NonNull Long НовыйUUIDДляОбеихТаблицЧАТиДАТАЧАТдляПоляPARENT_UUID
    , @NonNull String СамоСообщенияНовоеДляЧата
    ,@NonNull Context context) {

        Long РезультатВставкиНовогоСообщениявТАблицы_Data_CHATS= 0l;

        try {
            this.context=context;
            class_grud_sql_operations = new Class_GRUD_SQL_Operations(context);
            modelДляФрагментаДляОперацииЗаписиНовгоСтатусаПрочитанного = new CoreBinessLogics(context);
            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new BinessLogicPublicContent(context);
            recordNewErros =new RecordNewErros(context);

            final String ТаблицаВторойОбработкиДляТаблицыДата_Табеля = "data_chat";

            Log.d(this.getClass().getName(), "  СамоСообщенияНовоеДляЧата "
                    + СамоСообщенияНовоеДляЧата);
            if (НовыйUUIDДляОбеихТаблицЧАТиДАТАЧАТдляПоляPARENT_UUID > 0) {

                class_grud_sql_operations = new Class_GRUD_SQL_Operations(context);
                ContentValues contentValuesЗаписьНовогоСообщения_ТаблицыDATA_CHAT = new ContentValues();
                String СгенерированованныйДатаДляДаннойОперации = new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();
                contentValuesЗаписьНовогоСообщения_ТаблицыDATA_CHAT.put("date_update", СгенерированованныйДатаДляДаннойОперации);
                contentValuesЗаписьНовогоСообщения_ТаблицыDATA_CHAT.put("user_update", ПубличныйIDДляФрагмента);
                contentValuesЗаписьНовогоСообщения_ТаблицыDATA_CHAT.put("chat_uuid", НовыйUUIDДляОбеихТаблицЧАТиДАТАЧАТдляПоляPARENT_UUID);
                Long ЛокальныйUUIDДляТаблицыДатаЧатВтораяТаблица = (Long) new Class_Generation_UUID(context).МетодГенерацииUUID();
                contentValuesЗаписьНовогоСообщения_ТаблицыDATA_CHAT.put("uuid", ЛокальныйUUIDДляТаблицыДатаЧатВтораяТаблица);

                // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
                Long РезультатУвеличинаяВерсияДАныхДатЧата=
                        new VersionCurentTable(context).upVersionCurentTable(    ТаблицаВторойОбработкиДляТаблицыДата_Табеля);
                Log.d(this.getClass().getName(), " РезультатУвеличинаяВерсияДАныхДатЧата  " + РезультатУвеличинаяВерсияДАныхДатЧата);

                // TODO: 18.11.2022
                contentValuesЗаписьНовогоСообщения_ТаблицыDATA_CHAT.put("current_table", РезультатУвеличинаяВерсияДАныхДатЧата);
                if (СамоСообщенияНовоеДляЧата.length() > 0) {
                    contentValuesЗаписьНовогоСообщения_ТаблицыDATA_CHAT.put("message", СамоСообщенияНовоеДляЧата.trim());
                    РезультатВставкиНовогоСообщениявТАблицы_Data_CHATS = new CoreBinessLogics(context).
                            ВставкаДанныхЧерезКонтейнерТолькоПриСозданииНСообщенияДЛЯЧата(ТаблицаВторойОбработкиДляТаблицыДата_Табеля,
                                    contentValuesЗаписьНовогоСообщения_ТаблицыDATA_CHAT, ТаблицаВторойОбработкиДляТаблицыДата_Табеля, "",
                                    true);
                    Log.d(this.getClass().getName(), "  РезультатВставки_ТолькоВДочернуюТаблицуТакаКакВСтрашойТАблицуУжеЕстьПереписка "
                            + РезультатВставкиНовогоСообщениявТАблицы_Data_CHATS);

                    contentValuesЗаписьНовогоСообщения_ТаблицыDATA_CHAT.clear();
                }
            }
            Log.w(context.getClass().getName(),  " РезультатВставкиНовогоСообщениявТАблицы_Data_CHATS " +РезультатВставкиНовогоСообщениявТАблицы_Data_CHATS);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            recordNewErros.recordnewerror(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РезультатВставкиНовогоСообщениявТАблицы_Data_CHATS;
    }





    // TODO: 15.07.2022  метод который  ВЫЧИСЛЯЕТ КЕМ БЫЛ НАПИСАНОЕ ТЕКУЩЕЕ СООБЩЕНИЕ

    String МетодКемБЫлоНАписаноСообщение(@NonNull  Integer ПолученноеФИОКемБылоНаписаноСообщение,@NonNull Context context) {
        String КтопанисалСообщениеФИО = new String();

        try {
            this.context=context;
            class_grud_sql_operations = new Class_GRUD_SQL_Operations(context);
            modelДляФрагментаДляОперацииЗаписиНовгоСтатусаПрочитанного = new CoreBinessLogics(context);
            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new BinessLogicPublicContent(context);
            recordNewErros =new RecordNewErros(context);


            // TODO: 15.07.2022
            class_grud_sql_operations.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы", "fio");
            class_grud_sql_operations.concurrentHashMapНабор.put("СтолбцыОбработки", "name");
            class_grud_sql_operations.concurrentHashMapНабор.put("ФорматПосика", "user_update = ? ");
            class_grud_sql_operations.concurrentHashMapНабор.put("УсловиеПоиска1", ПолученноеФИОКемБылоНаписаноСообщение);
            class_grud_sql_operations.concurrentHashMapНабор.put("УсловиеЛимита", "1");

            // TODO: 15.07.2022 ПОЛУЧАЕМ ФИО
            SQLiteCursor   Курсор_соЗначениемФИО = (SQLiteCursor) class_grud_sql_operations.
                    new GetData(context).getdata(class_grud_sql_operations.concurrentHashMapНабор,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,   sqLiteDatabase);
            if (Курсор_соЗначениемФИО.getCount() > 0) {
                Курсор_соЗначениемФИО.moveToFirst();
                КтопанисалСообщениеФИО = Курсор_соЗначениемФИО.getString(0).trim();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            recordNewErros.recordnewerror(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return КтопанисалСообщениеФИО;
    }


    // TODO: 15.07.2022 метод  определяет что сообщение Протитано Мной Или НЕт 


    Boolean МетодПолучаемНаТекущуюЗаписьПрочитанноеСообщениеИлиНЕТ( @NonNull Long ТекущийUUDДляАнализаПрочитаноИлиНет, @NonNull Context context,   @NonNull Integer ПубличныйIDДляФрагмента) {

        Boolean ПолученныйРезультаЗаписьЖирнаяИлиНет = false;


        Log.w(context.getClass().getName(),  " ТекущийUUDДляАнализаПрочитаноИлиНет " +ТекущийUUDДляАнализаПрочитаноИлиНет + " ПубличныйIDДляФрагмента " +ПубличныйIDДляФрагмента);

        try {
            this.context=context;
            class_grud_sql_operations = new Class_GRUD_SQL_Operations(context);
            modelДляФрагментаДляОперацииЗаписиНовгоСтатусаПрочитанного = new CoreBinessLogics(context);
            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new BinessLogicPublicContent(context);
            recordNewErros =new RecordNewErros(context);

            
            class_grud_sql_operations.
                    concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы", "data_chat");
            class_grud_sql_operations.
                    concurrentHashMapНабор.put("СтолбцыОбработки", "status_write");
            class_grud_sql_operations.
                    concurrentHashMapНабор.put("ФорматПосика", "uuid=?  AND status_write =? AND user_update!=?");
            class_grud_sql_operations.
                    concurrentHashMapНабор.put("УсловиеПоиска1", ТекущийUUDДляАнализаПрочитаноИлиНет);
            class_grud_sql_operations.
                    concurrentHashMapНабор.put("УсловиеПоиска2", 0);
            class_grud_sql_operations.
                    concurrentHashMapНабор.put("УсловиеПоиска3", ПубличныйIDДляФрагмента);
            class_grud_sql_operations.
                    concurrentHashMapНабор.put("УсловиеЛимита", 1);

            // TODO: 15.07.2022  Получаем Сообщения Я Уже Протичал или НЕт
            SQLiteCursor     КурсорПрочиталСообщениеДанноеИлиНет =
                    (SQLiteCursor) class_grud_sql_operations.
                            new GetData(context).getdata(class_grud_sql_operations.
                                    concurrentHashMapНабор,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков
                            ,    sqLiteDatabase);

            if (КурсорПрочиталСообщениеДанноеИлиНет.getCount() > 0) {
                ПолученныйРезультаЗаписьЖирнаяИлиНет = true;
            } else {
                ПолученныйРезультаЗаписьЖирнаяИлиНет = false;
            }

            Log.w(context.getClass().getName(),  " ПолученныйРезультаЗаписьЖирнаяИлиНет " + ПолученныйРезультаЗаписьЖирнаяИлиНет);


            КурсорПрочиталСообщениеДанноеИлиНет.close();

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            recordNewErros.recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ПолученныйРезультаЗаписьЖирнаяИлиНет = false;
        }

        return ПолученныйРезультаЗаписьЖирнаяИлиНет;
    }


    // TODO: 15.07.2022  метод смены статуса wtire статуса

    Integer МетодЗаписиСтатусаДляТекущегоПользователя(@NonNull Long ПолученныйUUIDТекущейСтрочкиКоторуюПрочитали,
                                                      @NonNull String СамоЗначенияИндифкатора,
                                                      @NonNull Context context) {

        Integer РезультатОбновленияСтатусЧатаКакПрочитанный = 0;


        try {
            this.context=context;
            class_grud_sql_operations = new Class_GRUD_SQL_Operations(context);
            modelДляФрагментаДляОперацииЗаписиНовгоСтатусаПрочитанного = new CoreBinessLogics(context);
            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new BinessLogicPublicContent(context);
            recordNewErros =new RecordNewErros(context);

            // TODO: 15.07.2022  смены статуса

         final    String ТаблицаОбработкиВнутриЧтатаПриУвеличсенииВерсииДаннвъКоглаПрочинаноСообещния = "data_chat";

            ContentValues contentValuesОбновленниВТАблицеКакПрочитанныйМеняемСтатусЗаписисВчатеПостлеПросмотра = new ContentValues();
            contentValuesОбновленниВТАблицеКакПрочитанныйМеняемСтатусЗаписисВчатеПостлеПросмотра.put("status_write", 1);


            // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
            Long РезультатУвеличинаяВерсияДАныхЧата=
                    new VersionCurentTable(context).upVersionCurentTable(ТаблицаОбработкиВнутриЧтатаПриУвеличсенииВерсииДаннвъКоглаПрочинаноСообещния);
            Log.d(this.getClass().getName(), " РезультатУвеличинаяВерсияДАныхЧата  " + РезультатУвеличинаяВерсияДАныхЧата);

// TODO: 18.11.2022
            contentValuesОбновленниВТАблицеКакПрочитанныйМеняемСтатусЗаписисВчатеПостлеПросмотра.put("current_table", РезультатУвеличинаяВерсияДАныхЧата);

            РезультатОбновленияСтатусЧатаКакПрочитанный = modelДляФрагментаДляОперацииЗаписиНовгоСтатусаПрочитанного.
                    ЛокальногоОбновлениеДанныхЧерезКонтейнерУниверсальная(ТаблицаОбработкиВнутриЧтатаПриУвеличсенииВерсииДаннвъКоглаПрочинаноСообещния,
                            contentValuesОбновленниВТАблицеКакПрочитанныйМеняемСтатусЗаписисВчатеПостлеПросмотра,
                            ПолученныйUUIDТекущейСтрочкиКоторуюПрочитали, СамоЗначенияИндифкатора);


            Log.w(context.getClass().getName(),  " РезультатОбновленияСтатусЧатаКакПрочитанный " + РезультатОбновленияСтатусЧатаКакПрочитанный);


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            recordNewErros.recordnewerror(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return РезультатОбновленияСтатусЧатаКакПрочитанный;   // TODO: 05.07.2021 вставка новго сообщения в деве таблоицы Code_For_Chats_КодДля_Чата and DATA_Chat
    }


    // TODO: 15.07.2022 ДАТА  СООБЩЕНИЯ ЧАТА

    // TODO: 01.07.2022  метод получание ФИО И ДАТА НОВОГО СООБЩЕНИЯ

    private StringBuffer МетодДляНовгоСообщенияДату(@NonNull Cursor cursor, @NonNull Context context) {
        StringBuffer БуферПолученойФИОиДатаСообещния = new StringBuffer();
        try {

            this.context=context;
            class_grud_sql_operations = new Class_GRUD_SQL_Operations(context);
            modelДляФрагментаДляОперацииЗаписиНовгоСтатусаПрочитанного = new CoreBinessLogics(context);
            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new BinessLogicPublicContent(context);
            recordNewErros =new RecordNewErros(context);

            // TODO: 15.07.2022
            String ФиналДата = null;
            String ФиналДатаДлиннная = null;

            
            
            Integer ИндексДатаСообщенийВсехСВыбраннымСотрудником = cursor.getColumnIndex("date_update");
            String ПолученноеДатыСообщенияСообщения = cursor.getString(ИндексДатаСообщенийВсехСВыбраннымСотрудником).trim();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", new Locale("ru"));
            Date date = null;
            try {
                date = dateFormat.parse(ПолученноеДатыСообщенияСообщения);
            } catch (ParseException e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                recordNewErros.recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());

                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("ru"));
                date = dateFormat.parse(ПолученноеДатыСообщенияСообщения);
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm", new Locale("ru"));
            //simpleDateFormat.applyPattern("HH:mm");//dd-MM-yyyy//// EEEE yyyy HH:mm  /////  dd MMMM yyyy HH:mm
            simpleDateFormat.applyPattern("dd-MMM-yyyy HH:mm");//dd-MM-yyyy//// EEEE yyyy HH:mm  /////  dd MMMM yyyy HH:mm
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
            ФиналДата = simpleDateFormat.format(date);

            // TODO: 01.07.2022  мое сообщение
            БуферПолученойФИОиДатаСообещния.append(ФиналДата);///ПолученыйФИОIDДляЧата


            Log.w(context.getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    +"\n"+ " БуферПолученойФИОиДатаСообещния " +БуферПолученойФИОиДатаСообещния + " ФиналДата "+ФиналДата);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            recordNewErros.recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return БуферПолученойФИОиДатаСообещния;
    }




    // TODO: 01.07.2022  метод получание ФИО И ДАТА НОВОГО СООБЩЕНИЯ

    private StringBuffer МетодДляНовгоСообщенияГЕнерируетФИО(@NonNull Cursor cursor,@NonNull Context context, @NonNull Integer ПубличныйIDДляФрагмента) {

        String ФиналДата = null;
        String ФиналДатаДлиннная = null;
        StringBuffer БуферПолученойФИОиДатаСообещния = new StringBuffer();

        try {
            this.context=context;
            class_grud_sql_operations = new Class_GRUD_SQL_Operations(context);
            modelДляФрагментаДляОперацииЗаписиНовгоСтатусаПрочитанного = new CoreBinessLogics(context);
            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new BinessLogicPublicContent(context);
            recordNewErros =new RecordNewErros(context);



            Integer ИндексДатаСообщенийВсехСВыбраннымСотрудником = cursor.getColumnIndex("date_update");
            String ПолученноеДатыСообщенияСообщения = cursor.getString(ИндексДатаСообщенийВсехСВыбраннымСотрудником).trim();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", new Locale("ru"));
            Date date = null;
            try {
                date = dateFormat.parse(ПолученноеДатыСообщенияСообщения);
            } catch (ParseException e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                recordNewErros.recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());

                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("ru"));
                date = dateFormat.parse(ПолученноеДатыСообщенияСообщения);
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm", new Locale("ru"));
            simpleDateFormat.applyPattern("dd-MMM-yyyy HH:mm");//dd-MM-yyyy//// EEEE yyyy HH:mm  /////  dd MMMM yyyy HH:mm
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
            ФиналДата = simpleDateFormat.format(date);
            int ИндексКемБылоНаписаноСообщение = cursor.getColumnIndex("user_update");
            String ФиоКтоНАписалСообщение = new String();
            
            Integer ПолученноеФИОКемБылоНаписаноСообщениеДляПосикаФИО = cursor.getInt(ИндексКемБылоНаписаноСообщение);
            // TODO: 18.07.2022  кто написал ФИО сообщение
            
            ФиоКтоНАписалСообщение = МетодКемБЫлоНАписаноСообщение(ПолученноеФИОКемБылоНаписаноСообщениеДляПосикаФИО,context);
            int ИндексКтоНаписалСообщениеСотрудникомДляtext2 = cursor.getColumnIndex("user_update");
            int ПолученноеКтоНаписалДляtext2 = cursor.getInt(ИндексКтоНаписалСообщениеСотрудникомДляtext2);
            if (ПолученноеКтоНаписалДляtext2 == ПубличныйIDДляФрагмента) {
                // TODO: 01.07.2022  мое сообщение
                БуферПолученойФИОиДатаСообещния.append(ФиналДата);///ПолученыйФИОIDДляЧата


                Log.w(context.getClass().getName(), "\n"
                        + " время: " + new Date()+"\n+" +
                        " Класс в процессе... " +  this.getClass().getName()+"\n"+
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                        +"\n"+ " БуферПолученойФИОиДатаСообещния " +БуферПолученойФИОиДатаСообещния);


            } else {
                // TODO: 01.07.2022  мен написали
                БуферПолученойФИОиДатаСообещния.append(ФиоКтоНАписалСообщение.toLowerCase().trim())
                        .append("  ").append("(").append(ФиналДата).append(")");
                //((MaterialButton) view).setText(БуферПолученойФИОиДатаСообещния.toString());///ПолученыйФИОIDДляЧата

                Log.w(context.getClass().getName(), "\n"
                        + " время: " + new Date()+"\n+" +
                        " Класс в процессе... " +  this.getClass().getName()+"\n"+
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                        +"\n"+ " БуферПолученойФИОиДатаСообещния " +БуферПолученойФИОиДатаСообещния);



            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            recordNewErros.recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return БуферПолученойФИОиДатаСообещния;
    }




    private void МетодСамоСообщениеМоё(MaterialButton view, Cursor cursor, String ПолученноеТелоСообщения, int ПолученноеКтоНаписал,@NonNull  Context context) {
        try {
           // StringBuffer БуферПолученныеДанныеИзБывшегоText2=      МетодКоторыйГенерируетФИОиДатаСообщения(cursor);
/*
            Log.w(context.getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+"\n"+ " ПолученноеТелоСообщения " +ПолученноеТелоСообщения+
                    " БуферПолученныеДанныеИзБывшегоText2 "+БуферПолученныеДанныеИзБывшегоText2.toString());*/


       //     StringBuffer БуферПолучаемДатуДляСоственныхСообщенийСоздалЯ=          МетодКоторыйГенерируетТОлькоДату(cursor);

/*            StringBuffer БуферМоихСообзщений=new StringBuffer();
            БуферМоихСообзщений
                    .append("\n")
                    .append("\n")
                    .append("  "+ПолученноеТелоСообщения)
                    .append("\n")
                    .append("\n")
                    .append("(")
                    .append(БуферПолучаемДатуДляСоственныхСообщенийСоздалЯ.toString().trim())
                    .append(")").append("\n");

            view.setText(БуферМоихСообзщений.toString().trim());*/
            // TODO: 18.06.2022
            //   view.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS|InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            recordNewErros.recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    // TODO: 18.07.2022 код от чата но пока только перенесенный ьез обработки --


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

// TODO: 15.07.2022  конец класса службы для чата только

}

