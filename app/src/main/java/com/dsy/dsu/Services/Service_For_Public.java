package com.dsy.dsu.Services;
import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.ProgressDialog;
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
import android.widget.Toast;

import androidx.annotation.BinderThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.dsy.dsu.BusinessLogicAll.Class_GRUD_SQL_Operations;
import com.dsy.dsu.BusinessLogicAll.VersionCurentTable;
import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;
import com.dsy.dsu.BusinessLogicAll.Class_Generation_UUID;
import com.dsy.dsu.BusinessLogicAll.Class_Generation_Weekend_For_Tabels;
import com.dsy.dsu.BusinessLogicAll.DATE.Class_Generation_Data;
import com.dsy.dsu.BusinessLogicAll.DATE.SubClassCursorLoader;
import com.dsy.dsu.Tabels.Tabel.CompleteTabel.MainActivity_List_Tabels;

import org.reactivestreams.Subscription;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */


@AndroidEntryPoint
public class Service_For_Public extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    private String ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре;
    public LocalBinderОбщий localBinderОбщий = new LocalBinderОбщий();
    protected         SibClassApplyFromBackPeriodof_ЗаполененияТабеляИзПрошлогоМесяца sibClassApplyFromBackPeriodof_заполененияТабеляИзПрошлогоМесяца;
    private Context context;
    private  Intent intentОтActivityListPeoples;

    public Service_For_Public() {
        super("Service_For_Public");
    }


    @Inject
    public   SQLiteDatabase getSqlLiteCoreApp;
    @Override
    public void onCreate() {
        super.onCreate();
        try{
            // TODO: 16.04.2025
          //  sqLiteDatabase = EntryPoints.get(context, HiltInterfacesqlite.class).getHiltSqlite();
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        sibClassApplyFromBackPeriodof_заполененияТабеляИзПрошлогоМесяца=new SibClassApplyFromBackPeriodof_ЗаполененияТабеляИзПрошлогоМесяца();

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
    }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        try{
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        //   return super.onBind(intent);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
    }
        return localBinderОбщий;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        Log.d(newBase.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  newBase.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        this.context=newBase;
        super.attachBaseContext(newBase);
    }

    @SuppressLint("WrongThread")
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " УДАЛЕНИЕ СТАТУСА Удаленная !!!!!" +"\n"+
                " УДАЛЕНИЕ СТАТУСА Удаленная !!!!! " +"\n"+
                " УДАЛЕНИЕ СТАТУСА Удаленная !!!!!   Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        this.context=getApplicationContext();
        МетодГлавныйPublicPO(getApplicationContext(),intent,null);
// TODO: 30.06.2022 сама не постредствено запуск метода
    }

    public class LocalBinderОбщий extends Binder {
        public Service_For_Public getService() {
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            return Service_For_Public.this;
        }

        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
          try{
            data = Parcel.obtain();
            reply = Parcel.obtain();
            data.writeString("fffff");
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
            return    reply.readBoolean();
        }
    }
    @BinderThread
    public Integer МетодГлавныйPublicPO(@NonNull Context context, @NonNull Intent intent,@NonNull ProgressDialog progressDialog) {
        Integer РезультатОперации=0;
        try{
            if( this.context==null){
                this.context=context;
            }
            this.intentОтActivityListPeoples=intent;
            switch (intent.getAction()) {
                case "ЗапускЗаполенеияИзПрошлыхМесяцев":
                    // TODO: 28.09.2022 Запуск Само Заполенеия Данных из Прошлого Месяца
                sibClassApplyFromBackPeriodof_заполененияТабеляИзПрошлогоМесяца.МетодЗапускЗаполенеияИзПрошлыхМесяцев(context, intent,progressDialog);
                    Log.w(this.getClass().getName(), "   intent.getAction()  " + intent.getAction());
                    break;
                // TODO: 25.09.2022 удаление статуса удаленных строк
                case "ЗапускУдалениеСтатусаУдаленияСтрок":
                  new SubClassFromУдалениеСтатусУдаленный(context).МетодУдаленияСтатусаУдаленных(intent);
                    Log.w(this.getClass().getName(), "   intent.getAction()  " + intent.getAction());
                    break;
                default:
                    break;
            }

            stopSelf();

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
        return РезультатОперации;
    }
// TODO: 15.02.2023
public Cursor МетодПолучениеДанныхЧерезCursorLoader(@NonNull Context context, @NonNull Intent intent) {
    Cursor cursor=null;
    try{
        if( this.context==null){
            this.context=context;
        }
        Log.w(this.getClass().getName(), "   МетодПолучениеДанныхЧерезCursorLoader  " + context);
        if (intent.getAction().contentEquals("ДляУдаление")) {
            cursor=      (Cursor)     new SubClassCursorLoader(). CursorLoaders(context, intent.getExtras());
        }
        Log.w(this.getClass().getName(), "   cursor  " + cursor);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
    }
    return cursor;
}

    // TODO: 28.09.2022  класс заполнения из прошлых месяцев

    class SibClassApplyFromBackPeriodof_ЗаполененияТабеляИзПрошлогоМесяца {

        private   Integer  ГодТабелейИзТабеля;
        private   Integer   МЕсяцТабелейИзТабеля;
        private      Long     MainParentUUID;
        Integer   DigitalNameCFO;


        private void МетодЗапускЗаполенеияИзПрошлыхМесяцев(@NonNull Context context, @NonNull Intent intent,@NonNull ProgressDialog progressDialog) {
            try {
                final AtomicInteger atomicIntegerBeforeMothCopyTabel = new AtomicInteger(0);
                    // TODO: 22.09.2025
                    String getCurrentTabel="viewtabel";
                            //TODO ВЫЧИСЛЯЕМ ДАННЫЕ КОТОРЫЕ НА ВСТАВИТЬ
                    Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecurrentoperations/" + getCurrentTabel + "");
                    ContentResolver contentResolver=context.getContentResolver();
                            // TODO: 24.02.2025 внтрений
                            final Cursor[] Курсор_ВытаскиваемПоследнийМесяцТабеля = {null};
                            Flowable.range(1,12)
                                    .filter(f->f.intValue()<МЕсяцТабелейИзТабеля)
                                    //.filter(f->f.intValue()<9)
                            .sorted(Collections.reverseOrder()).delay(1000,TimeUnit.MILLISECONDS)
                            .onBackpressureBuffer().doOnNext(new Consumer<Integer>() {
                                        @Override
                                        public void accept(Integer getmonthagofordatasearch) throws Throwable {
                                            Курсор_ВытаскиваемПоследнийМесяцТабеля[0] =      contentResolver.query(uri,new String[]{},
                                                    new String("  SELECT * FROM  "+getCurrentTabel+" WHERE year_tabels=?  AND month_tabels=?  AND cfo=?  AND status_send!=?  ORDER BY date_update DESC    "),
                                                    new String[]{String.valueOf(ГодТабелейИзТабеля),
                                                            String.valueOf( getmonthagofordatasearch),String.valueOf(DigitalNameCFO),"Удаленная"},null);

                                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                    + " getmonthagofordatasearch  " +getmonthagofordatasearch + " Курсор_ВытаскиваемПоследнийМесяцТабеля[0] " +Курсор_ВытаскиваемПоследнийМесяцТабеля[0]);

                                        }
                                    })
                            .takeWhile(new Predicate<Integer>() {
                                @Override
                                public boolean test(Integer getmonthagofordatasearch) throws Throwable {
                                    // TODO: 24.02.2025
                                    Integer  getmonthagofordatasearchtakeWhile=0;
                                    if (Курсор_ВытаскиваемПоследнийМесяцТабеля[0]!=null) {
                                        getmonthagofordatasearchtakeWhile=   Курсор_ВытаскиваемПоследнийМесяцТабеля[0].getCount();
                                    }
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                            + " getmonthagofordatasearchtakeWhile  " +getmonthagofordatasearchtakeWhile);
                                    if (getmonthagofordatasearchtakeWhile>0) {
                                        return false; //TODO false  это продолжение обработуки
                                    } else {
                                        return true;
                                    }
                                }
                            }).doOnError(new Consumer<Throwable>() {
                                        @Override
                                        public void accept(Throwable throwable) throws Throwable {
                                            throwable.printStackTrace();
                                            Log.e(this.getClass().getName(), "Ошибка " +throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                            new RecordNewErros(context).recordnewerror(throwable.toString(), this.getClass().getName(),
                                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        }
                                    })
                                    .doOnComplete(new Action() {
                                                @Override
                                                public void run() throws Throwable {

                                                    if (Курсор_ВытаскиваемПоследнийМесяцТабеля[0]!=null) {
                                                        if (Курсор_ВытаскиваемПоследнийМесяцТабеля[0].getCount()>0) {
                                                            // TODO: 16.02.2023 сама вставка
                                                            atomicIntegerBeforeMothCopyTabel.set( copyDataTabelwithNewTabel(context, ГодТабелейИзТабеля, МЕсяцТабелейИзТабеля,
                                                                    Курсор_ВытаскиваемПоследнийМесяцТабеля[0],
                                                                    progressDialog,MainParentUUID));
                                                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                                    + " atomicIntegerBeforeMothCopyTabel  " + atomicIntegerBeforeMothCopyTabel.get());


                                                        }
                                                    }


// TODO: 24.02.2025

                                                    // TODO: 21.09.2023
                                                    if (             atomicIntegerBeforeMothCopyTabel.get() >0) {
                                                        // TODO: 21.04.2023 после операции возврящемся на Activity List Peoples
                                                        МетодПереходMainActivity_List_Peoples(intentОтActivityListPeoples);

                                                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                                + "  atomicIntegerBeforeMothCopyTabel.get() " +            atomicIntegerBeforeMothCopyTabel.get() );
                                                    }else{
                                                        // TODO: 24.02.2025
                                                        context.getMainExecutor().execute(()->{
                                                            Toast.makeText(context, "Табель не скопирован!!!", Toast.LENGTH_SHORT).show();
                                                        });

                                                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                                + "  atomicIntegerBeforeMothCopyTabel.get() " + atomicIntegerBeforeMothCopyTabel.get());
                                                    }

                                                    progressDialog.dismiss();
                                                    progressDialog.cancel();

                                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                                }
                                            })
                                    .doOnSubscribe(new Consumer<Subscription>() {
                                        @Override
                                        public void accept(Subscription subscription) throws Throwable {
                                            // TODO: 24.02.2025
                                            Bundle bundleПолучаемДанных =(Bundle)  intent.getExtras();
                                            MainParentUUID=    bundleПолучаемДанных.getLong("MainParentUUID", 0l);
                                            DigitalNameCFO=   bundleПолучаемДанных.getInt("DigitalNameCFO", 0);
                                            ГодТабелейИзТабеля= getYear();
                                            //ГодТабелейИзТабеля=  bundleПолучаемДанных.getInt("ГодТабелей", 0);
                                            //    МЕсяцТабелейИзТабеля=  bundleПолучаемДанных.getInt("МЕсяцТабелей",0);
                                            МЕсяцТабелейИзТабеля=     getMoth();

                                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                    + " DigitalNameCFO " +DigitalNameCFO  + "МЕсяцТабелейИзТабеля " +МЕсяцТабелейИзТабеля +" ГодТабелейИзТабеля " +ГодТабелейИзТабеля);
                                        }
                                    }).subscribeOn(Schedulers.single()).observeOn(AndroidSchedulers.mainThread())
                                    .subscribe();


                            //todo  конец
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");


                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        private Integer getMoth() {
            LocalDate today = LocalDate.now();
            МЕсяцТабелейИзТабеля = today.getMonthValue();
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " intentОтActivityListPeoples " +intentОтActivityListPeoples.getExtras());
            return  МЕсяцТабелейИзТабеля;
        }


        private Integer getYear() {
            LocalDate today = LocalDate.now();
            ГодТабелейИзТабеля = today.getYear();
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " intentОтActivityListPeoples " +intentОтActivityListPeoples.getExtras());
            return  ГодТабелейИзТабеля;
        }

        /////TODO метод запуска кода при однократорм нажатии просто загузка сотрудников табель
        private void МетодПереходMainActivity_List_Peoples( @NonNull Intent intentОтActivityListPeoples) {
            try{
                Intent    ИнтентпереходВMainActivityList_Peoples=new Intent(getApplicationContext(), MainActivity_List_Tabels.class);
                ИнтентпереходВMainActivityList_Peoples      .putExtras(intentОтActivityListPeoples.getExtras());
                ИнтентпереходВMainActivityList_Peoples.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(ИнтентпереходВMainActivityList_Peoples);
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " intentОтActivityListPeoples " +intentОтActivityListPeoples.getExtras());
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }





        private Integer copyDataTabelwithNewTabel(@NonNull Context context,
                                                  @NonNull  Integer ГодНазадДляЗаполнени,
                                                  @NonNull   Integer МесяцИзПрошлогоМесяца,
                                                  @NonNull    Cursor Курсор_ВытаскиваемПоследнийМесяцТабеля,
                                                  @NonNull ProgressDialog progressDialog,
                                                  @NonNull Long MainParentUUID) {
            ArrayList<Integer> integerArrayListВствавкаИзПрошлогоМесяц = new ArrayList<>();;
            final Disposable[] disposable1 = new Disposable[1];
            try {
                // TODO: 21.09.2023  Сама ыВсатвка
                Observable.range(0,Курсор_ВытаскиваемПоследнийМесяцТабеля.getCount())
                        .concatMap(i -> Observable.just(i).delay(250, TimeUnit.MILLISECONDS))
                        .blockingSubscribe(new Observer<Integer>() {
                            @Override
                            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                                Курсор_ВытаскиваемПоследнийМесяцТабеля.moveToLast();
                                context.getMainExecutor().execute(() -> {
                                    progressDialog.setMax(Курсор_ВытаскиваемПоследнийМесяцТабеля.getCount());
                                });
                                disposable1[0] =d;
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                            }

                            @Override
                            public void onNext(@io.reactivex.rxjava3.annotations.NonNull Integer integer) {
                                // TODO: 23.09.2022 ВСТАВЛЯЕМ ДАННЫЕ ВО ВТОРУЮ ТАБЛИЦЫ ДАТА_ТАБЕЛЬ
                                Integer РезультатВставкиВНИжнуюТаюблицу = МетодВставкивТаблицуДата_Табель(context,
                                        Курсор_ВытаскиваемПоследнийМесяцТабеля, MainParentUUID, ГодНазадДляЗаполнени, МесяцИзПрошлогоМесяца);

                                // TODO: 21.09.2023
                                Курсор_ВытаскиваемПоследнийМесяцТабеля.moveToPrevious();
                                Log.d(this.getClass().getName(), " Вторая Таблиуа Из Прошлого МЕссяца РезультатВставкиВНИжнуюТаюблицу"
                                        + РезультатВставкиВНИжнуюТаюблицу);
                                if (РезультатВставкиВНИжнуюТаюблицу > 0) {
                                    integerArrayListВствавкаИзПрошлогоМесяц.add(РезультатВставкиВНИжнуюТаюблицу);
                                    getApplicationContext().getMainExecutor().execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            progressDialog.setProgress(integerArrayListВствавкаИзПрошлогоМесяц.size());
                                            progressDialog.setMessage("в процессе");
                                        }
                                    });


                                }
                            }


                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
                            }

                            @Override
                            public void onComplete() {

                                getApplicationContext().getMainExecutor().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressDialog.dismiss();
                                        progressDialog.cancel();

                                    }
                                });
                                // TODO: 21.09.2023  cliser
                                Курсор_ВытаскиваемПоследнийМесяцТабеля.close();
                                disposable1[0].dispose();
                            }
                        });
                // TODO: 23.09.2022 ВСТАВЛЯЕМ ДАННЫЕ ВО ВТОРУЮ ТАБЛИЦЫ ДАТА_ТАБЕЛЬ

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
            return  integerArrayListВствавкаИзПрошлогоМесяц.size();
        }


        @SuppressLint("Range")
        private Integer МетодВставкивТаблицуДата_Табель(@NonNull Context context,
                                                        @NonNull Cursor Курсор_ВытаскиваемПоследнийМесяцТабеля
                , @NonNull Long ParentUUID,
                                                        @NonNull Integer ПолученаяДатаТолькоГод,
                                                        @NonNull Integer  МесяцИзПрошлогоМесяца) {
            String ответОперцииВставки = null;
            try {
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " ПолученаяДатаТолькоГод  " + ПолученаяДатаТолькоГод + "  МесяцИзПрошлогоМесяца " + МесяцИзПрошлогоМесяца + " Курсор_ВытаскиваемПоследнийМесяцТабеля " +Курсор_ВытаскиваемПоследнийМесяцТабеля);
                Class_GRUD_SQL_Operations       class_grud_sql_operationЗаполнениеИзПрошлогоМесяца = new Class_GRUD_SQL_Operations(getApplicationContext());
                String НазваниеОбрабоатываемойТаблицы = "data_tabels";
                ContentValues contentValuesДляДатаТабель = new ContentValues();
                int ИндексСтолбикаДляЗаполненияФИО = Курсор_ВытаскиваемПоследнийМесяцТабеля.getColumnIndex("fio");
                contentValuesДляДатаТабель.put("fio", Курсор_ВытаскиваемПоследнийМесяцТабеля.getLong(ИндексСтолбикаДляЗаполненияФИО));
                int ИндексFIOuser_update = Курсор_ВытаскиваемПоследнийМесяцТабеля.getColumnIndex("user_update");
                contentValuesДляДатаТабель.put("user_update", Курсор_ВытаскиваемПоследнийМесяцТабеля.getInt(ИндексFIOuser_update));


              @SuppressLint("Range") Integer Профессия     = Курсор_ВытаскиваемПоследнийМесяцТабеля.getInt(Курсор_ВытаскиваемПоследнийМесяцТабеля.getColumnIndex("dt_prof"));

                if (Профессия==0) {
                    Профессия =  Курсор_ВытаскиваемПоследнийМесяцТабеля.getInt(Курсор_ВытаскиваемПоследнийМесяцТабеля.getColumnIndex("fio_prof"));
                }

                contentValuesДляДатаТабель.put("prof", Профессия);


                String СгенерированованныйДатаДляДаннойОперации = new Class_Generation_Data(getApplicationContext()).ГлавнаяДатаИВремяОперацийСБазойДанных();
                contentValuesДляДатаТабель.put("date_update", СгенерированованныйДатаДляДаннойОперации);
                // TODO: 23.09.2022 сама вставка в таблиц ТАБЕЛЬ  #1
                Long ДляНовойЗаписиUUID = (Long) new Class_Generation_UUID(getApplicationContext()).МетодГенерацииUUID();
                contentValuesДляДатаТабель.put("uuid", ДляНовойЗаписиUUID);;
                contentValuesДляДатаТабель.put("uuid_tabel", ParentUUID);
                contentValuesДляДатаТабель.put("status_send", " ");
                contentValuesДляДатаТабель.put("status_carried_out", "False");
               // contentValuesДляДатаТабель.putNull("_id");
                // TODO: 22.09.2022 дополнительные параменты ДатаТабель
                // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
                Long РезультатУвеличиваемВерсияДатаТАбель =
                        new VersionCurentTable(getApplicationContext())
                                .upVersionCurentTable(    НазваниеОбрабоатываемойТаблицы );
                Log.d(this.getClass().getName(), " РезультатУвеличиваемВерсияДатаТАбель  " + РезультатУвеличиваемВерсияДатаТАбель);
                contentValuesДляДатаТабель.put("current_table", РезультатУвеличиваемВерсияДатаТАбель);
                Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecurrentoperations/" + НазваниеОбрабоатываемойТаблицы + "");
                ContentResolver resolver = context.getContentResolver();
                Uri insertData = resolver.insert(uri, contentValuesДляДатаТабель);
                if (insertData!=null) {
                    ответОперцииВставки = Optional.ofNullable(insertData).map(Emmeter -> Emmeter.toString().replace("content://", "")).get();
                    if (     Integer.parseInt(ответОперцииВставки)>0) {
                        Integer РезультатВставкаВыходныхДНей=
                                new Class_Generation_Weekend_For_Tabels(getApplicationContext())
                                        .МетодТретийАвтоматическаяВставкаВыходныхДней(ДляНовойЗаписиUUID,ПолученаяДатаТолькоГод,МесяцИзПрошлогоМесяца );
                        Log.d(this.getClass().getName(), "   РезультатВставкаВыходныхДНей  "+  РезультатВставкаВыходныхДНей);
                        // TODO: 21.09.2023
                    }
                }else {
                    ответОперцииВставки="0";
                }
                Log.d(this.getClass().getName(), "insertData   " + insertData + "  ответОперцииВставки " + ответОперцииВставки);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
            return Integer.parseInt(ответОперцииВставки);
        }

        private Integer МетодВставкивТаблицуTaбель(@NonNull Context context,
                                                        @NonNull Cursor Курсор_ВытаскиваемПоследнийМесяцТабеля,
                                                        @NonNull Integer ГодНазадДляЗаполнени,
                                                        @NonNull Integer  МесяцИзПрошлогоМесяца,
                                                   @NonNull Integer   СФОУжеСозданогоТАбеля,
                                                   @NonNull Long ParentUUID) {
            String ответОперцииВставкиТабель = null;
            try {

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " ГодНазадДляЗаполнени  " + ГодНазадДляЗаполнени + "  МесяцИзПрошлогоМесяца "
                        + МесяцИзПрошлогоМесяца + " СФОУжеСозданогоТАбеля " +СФОУжеСозданогоТАбеля);
                Class_GRUD_SQL_Operations       class_grud_sql_operationЗаполнениеИзПрошлогоМесяца = new Class_GRUD_SQL_Operations(getApplicationContext());
                String НазваниеОбрабоатываемойТаблицы = "tabel";
                ContentValues contentValuesДляТабель = new ContentValues();
                int ИндексFIOuser_update = Курсор_ВытаскиваемПоследнийМесяцТабеля.getColumnIndex("user_update");
                contentValuesДляТабель.put("user_update", Курсор_ВытаскиваемПоследнийМесяцТабеля.getInt(ИндексFIOuser_update));
                String СгенерированованныйДатаДляДаннойОперации = new Class_Generation_Data(getApplicationContext()).ГлавнаяДатаИВремяОперацийСБазойДанных();
                contentValuesДляТабель.put("date_update", СгенерированованныйДатаДляДаннойОперации);
                contentValuesДляТабель.put("uuid", ParentUUID);
                contentValuesДляТабель.put("cfo",СФОУжеСозданогоТАбеля);
                contentValuesДляТабель.put("month_tabels", МесяцИзПрошлогоМесяца);
                contentValuesДляТабель.put("year_tabels",ГодНазадДляЗаполнени);
                contentValuesДляТабель.put("status_send", " ");
               // contentValuesДляТабель.putNull("_id");
                // TODO: 22.09.2022 дополнительные параменты ДатаТабель
                // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
                Long РезультатУвеличиваемВерсияДатаТАбель =
                        new VersionCurentTable(getApplicationContext()).upVersionCurentTable(    НазваниеОбрабоатываемойТаблицы );
                Log.d(this.getClass().getName(), " РезультатУвеличиваемВерсияДатаТАбель  " + РезультатУвеличиваемВерсияДатаТАбель);
                contentValuesДляТабель.put("current_table", РезультатУвеличиваемВерсияДатаТАбель);
                // TODO: 21.09.2023 TABEL
                Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecurrentoperations/" + НазваниеОбрабоатываемойТаблицы + "");
                ContentResolver resolver = context.getContentResolver();
                Uri insertData = resolver.insert(uri, contentValuesДляТабель);
                ответОперцииВставкиТабель = Optional.ofNullable(insertData).map(Emmeter -> Emmeter.toString().replace("content://", "")).get();
                Log.d(this.getClass().getName(), "ответОперцииВставкиТабель   " + ответОперцииВставкиТабель );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
            return Integer.parseInt(ответОперцииВставкиТабель);
        }


    }

    // TODO: 25.09.2022 класс удаление статуса удаленных записей
    class SubClassFromУдалениеСтатусУдаленный {
        Context context;
        public SubClassFromУдалениеСтатусУдаленный(Context context) {
            this.context=context;
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

        }

        // TODO: 25.09.2022 запуск метода
        public void МетодУдаленияСтатусаУдаленных(@NonNull Intent intent){
            try{
                Stream<String> streamУдалениеСтатусаУдаленный=Stream.of("data_tabels","tabel","get_materials_data" );
                streamУдалениеСтатусаУдаленный.forEach(Таблица->{
                    Uri  uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + Таблица + "");
                    ContentResolver resolver = context.getContentResolver();
                    Integer  УдалениеДанныхСоСтатусомУдаленная=   resolver.delete(uri,"status_send=?  ",new String[]{"1СУдалено" });

                    Integer    УдалениеДанныхСоСтатусомУдаленнаяТабель=   resolver.delete(uri,"status_send=?  ",new String[]{"Удаленная" });

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                             " УдалениеДанныхСоСтатусомУдаленная "+ УдалениеДанныхСоСтатусомУдаленная);
                });

                Stream<String> streamУдалениеСтатусаУдаленныйВтрой=Stream.of( "order_tc");
                streamУдалениеСтатусаУдаленныйВтрой.forEach(Таблица->{
                    Uri  uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + Таблица + "");
                    ContentResolver resolver = context.getContentResolver();
                    Integer  УдалениеДанныхСоСтатусомУдаленная=   resolver.delete(uri,"  status=?",new String[]{ "6"});
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                            " УдалениеДанныхСоСтатусомУдаленная "+ УдалениеДанныхСоСтатусомУдаленная);
                });


            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
        }
        // TODO: 20.03.2023  метод смены статуса при удаление на СЕРВРЕРЕ





    }
}