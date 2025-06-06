package com.dsy.dsu.Passwords.View;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.multidex.BuildConfig;


import com.dsy.dsu.BootAndAsync.View.MainActivityBootAndAsync;

import com.dsy.dsu.BusinessLogicAll.CoreBinessLogic.CoreBinessLogics;
import com.dsy.dsu.BusinessLogicAll.GetClearDataUserAnCnahgeData;
import com.dsy.dsu.BusinessLogicAll.GetPingServers.GetPingServerJboss;

import com.dsy.dsu.BusinessLogicAll.CreateFolderBinatySave.ClassDeleteErrorFile;
import com.dsy.dsu.BusinessLogicAll.GetConnectivityManagerAndroid;
import com.dsy.dsu.BusinessLogicAll.Permissions.GrandPermissions;
import com.dsy.dsu.JbossAdress.JbossContext;
import com.dsy.dsu.CoreApp.Model.BunessLogicCoreApp;
import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;

import com.dsy.dsu.BusinessLogicAll.SubClassWriterPUBLICIDtoDatabase;

import com.dsy.dsu.JbossAdress.JbossHilt.intarfaces.QualifierPortJboss;
import com.dsy.dsu.Hilt.getSSLSocketFactory2.QualifiergetsslSocketFactory2;
import com.dsy.dsu.R;

import com.dsy.dsu.Settings.Model.GetSettingTableSaves;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.jakewharton.rxbinding4.view.RxView;


import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.net.ssl.SSLSocketFactory;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.Unit;

@AndroidEntryPoint
public class MainActivityPasswords extends AppCompatActivity {
    ////todo аунтификация
    private int ПодсчетПолощительиОтрцательРезультатов = 0; ////подсчитываем количество негативныйх попыток долеее 5 послываем программу в спячку
    private Button КнопкаВходавСистему;///КНОПКА ДЛЯ ВХОДЯ В СИСТЕМУ
    private ProgressBar ПрогрессБарДляВходаСистему;///КНОПКА ДЛЯ ВХОДЯ В СИСТЕМУ
    private TextInputEditText ИмяДляВходаСистему, ПарольДляВходаСистему;///КНОПКА ДЛЯ ВХОДЯ В СИСТЕМУ
    private Configuration config;
    private Activity activity;



    private String ПубличноеЛогин = new String();
    private String ПубличноеПароль = new String();
    private SharedPreferences preferences;
    private String ОшибкиПришлиПослеПингаОтСервера = null;
    private Message message;
    public static final int ALL_PERSSION_CODE=1;
    public static final int CAMERA_PERSSION_CODE=2;



    @Inject
    @QualifiergetsslSocketFactory2
    SSLSocketFactory getsslSocketFactory2;


    @Inject
    @QualifierPortJboss
    public LinkedHashMap<Integer,String> getHiltPortJboss;

    private  String getstepAsync;
    //////////////////////TODO SERVICE


    ////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main__authentication);

            preferences = getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);

            activity = this;
            ((Activity) getApplicationContext()).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            ((Activity) getApplicationContext()).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

            getBunbleSendForActivityPassword();


            // TODO: 04.10.2023 разрешения для всего
            GrandPermissions grandPermissions=   new GrandPermissions(this );
            grandPermissions.checkPermissions();
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());

// TODO   запускам бизнес логику CoreApp
            new BunessLogicCoreApp(getApplicationContext()).getBunessLogicCoreApp();


            методHideКлавиатура();

            getSupportActionBar().hide(); ///скрывать тул бар

            // TODO: 29.09.2023 клавиатура

            Log.d(this.getClass().getName(), "   ");
            Locale locale = new Locale("rus");
            Locale.setDefault(locale);
            config =
                    getBaseContext().getResources().getConfiguration();
            config.setLocale(locale);
            createConfigurationContext(config);


            // TODO: 19.12.2023 удаляем файл ошибоок
            ClassDeleteErrorFile classDeleteErrorFile=new ClassDeleteErrorFile(getApplicationContext());
            classDeleteErrorFile.МетодDeleteFolders();

// TODO: 19.12.2023 создаем  файл ошибоок



            КнопкаВходавСистему = (Button) findViewById(R.id.КнопкаВходаВПриложение);/////кнопка входа на сервер
            КнопкаВходавСистему.setVisibility(View.VISIBLE);
            ПрогрессБарДляВходаСистему = (ProgressBar) findViewById(R.id.progressBarДляWIFI); ////програссбар при аунтификации при входе в системму
            ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);// по умолчанию прогресс бар делаем не видеым
            ИмяДляВходаСистему = (TextInputEditText) findViewById(R.id.ИмяДляВходавПрограмму); ////програссбар при аунтификации при входе в системму
            ПарольДляВходаСистему = (TextInputEditText) findViewById(R.id.ПарольДляВходавПрограмму); ////програссбар при аунтификации при входе в системму


            // TODO: 02.08.2023 БИЗНЕС КОД
            методЗаписываемПервыйЭтапСинхрогниазции(getstepAsync);

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");



            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            // TODO: 05.06.2025



        } catch (Exception e) {
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
        }
    }

    private void getBunbleSendForActivityPassword() {
        try{
        Intent intent =  getIntent();
        Bundle bungleforPaaasword =intent.getExtras();
        // TODO: 10.04.2023
        if (bungleforPaaasword !=null) {
              getstepAsync = bungleforPaaasword.getString("stepAsync");
        }

        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +" getstepAsync " +getstepAsync);
    } catch (Exception e) {
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //////TODO  данный код срабатывает когда произошда ошивка в базе

    }



    @Override
    protected void onStart() {
        super.onStart();
        try{
            МетодПодготовкиДляАунтификации(); ////МЕТОД ПРЕДВАРИТЕЛЬНОГО ПОДГОТОВКИ К АУНТИФИКАЦИИ ПОЛЬЗОВАТЛЕЯ

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

        } catch (Exception e) {
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }



    ////////TODO КОТОРЫЙ НАЧИНАЕМ ТОЛЬКО ЕСЛИ ЕСТЬ ИМЯ И ПАРОЛЬ НАЧИНАЕТЬСЯ ТОЛЬКО С НЯЖАТИЕ КНОПКИ ВХОД
    private void МетодПодготовкиДляАунтификации() {
        try {

            // TODO: 29.09.2023 Класс Сохранение и Аунтификации
            ClassSavePassword classSavePassword=new ClassSavePassword();

            classSavePassword. metodSavePassword();

            Log.d(this.getClass().getName(), " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                    " Класс  :" + Thread.currentThread().getStackTrace()[2].getClassName());
                            // TODO: 25.04.2023  end async pool
        } catch (Exception e) {
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }








    private void методGetПарольОбработка(View v) {
        try{
        //TODO запукаем метод Афторизаиция по ЛОГИНУ И ПАРОЛЮ
            Integer  getPublicIDotJboss =
                    new CoreBinessLogics(getApplicationContext())
                            .методАвторизацииЛогинИПаполь( ПубличноеЛогин, ПубличноеПароль,getsslSocketFactory2);
        Log.d(this.getClass().getName(), " getPublicIDotJboss " + getPublicIDotJboss);

        // TODO: 24.08.2023 УСПЕШНЫЙ КОД ЛОГИРОВАНИЕ И ПАРОЛЬ
        switch (getPublicIDotJboss){
            case 0:
                МетодВизуальногоОтображениеРаботыКоннекта("Сервер выкл !!!", v);
                break;
            case -1:
                //TODO ПОСЛЕ ПИНГА ВИЗУАЛИЗАЦИЯ
                МетодВизуальногоОтображениеРаботыКоннекта("Логин и пароль не правильные !!!", v);
                break;
            // TODO: 15.09.2023  успещнно SUCCEESS
            default:
                // TODO: 15.09.2023  Успешно  ПЕРЕХОД В САМУ ПРОГРАММУ

                if (getPublicIDotJboss>0) {
                    ClassAfterForfardFaseApp classAfterForfardFaseApp=new ClassAfterForfardFaseApp();

                    classAfterForfardFaseApp.ClassAfterForfardFaseApp( getPublicIDotJboss, v);
                }

                Log.d(this.getClass().getName(), " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                        " Класс  :" + Thread.currentThread().getStackTrace()[2].getClassName()+ " getPublicIDotJboss " +getPublicIDotJboss);

                break;
        }
            Log.d(this.getClass().getName(), " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                    " Класс  :" + Thread.currentThread().getStackTrace()[2].getClassName()+ " getPublicIDotJboss " +getPublicIDotJboss);
    } catch (Exception e) {
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }
    
    
    
    
    



    private  Integer   методЗаписьВSuccesLogin(Integer ПолученинныйПубличныйID, View v) {
        Integer ЗаписьВSuccesLogin=0;
        //// todo после успешного получение имени и пароля записываем их в базу ЗАПУСК МЕТОДА ВСТАВКИ ИМЕНИ И ПАРОЛЯ ПРИ АУНТИФИКАЦИИ БОЛЕЕ 7 ДНЕЙ
        try {
            //todo ЗАПИСЬ ="SUCCENLOGIN";
            ЗаписьВSuccesLogin =
                    new SubClassWriterPUBLICIDtoDatabase().recordaftersyncSuccesLogin(getApplicationContext(), ПолученинныйПубличныйID, ПубличноеЛогин, ПубличноеПароль);
            // TODO: 10.09.2021  РЕЗУЛЬТАТ ЗАПИСИ СОТРУДНИКА ЗАПИСИ В БАЗУ
            Log.d(this.getClass().getName(), " БуферПолученнниеДанныхПолученияIDотСервера " +
                    " УСПЕШАЯ ЗАПИСЬ ПУБЛИЧНОГО id SUCCEESS !!!!  " +
                    "ТАБЛИЦА settings_tabels  РезультатЗаписиНовгоIDБАзуВТаблицеНАСТРОЕКПОЛЬЗОВТЕЛЯ_ДЛяЗАПИСИВТаблицу_settings_tabels "
                    + ЗаписьВSuccesLogin);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ЗаписьВSuccesLogin;

    }


    private  Integer   методЗаписьВSettingTabel(Integer ПолученинныйПубличныйID, View v) {
        Integer ЗаписьВSettingTabel=0;
        try {
                /// TODO: 22.02.2022 ЗАПИСЬ ="settings_tabels";
                ЗаписьВSettingTabel =new GetSettingTableSaves().getWritingPasswordSetingTable(getApplicationContext(),ПолученинныйПубличныйID);

                // TODO: 10.09.2021  РЕЗУЛЬТАТ ЗАПИСИ СОТРУДНИКА ЗАПИСИ В БАЗУ

            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " ЗаписьВSettingTabel " +ЗаписьВSettingTabel);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ЗаписьВSettingTabel;

    }


    private void exitForActivityPassword(@NonNull Integer PublicID) {
        try {
            // TODO: 01.12.2022 записываем режим синъронизации
                Uri uri = Uri.parse("package:" + BuildConfig.APPLICATION_ID);
                Intent IntentStartFaceApp = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, uri);
                IntentStartFaceApp.putExtra("ID", PublicID);
                IntentStartFaceApp.putExtra("ПубличноеИмяПользовательДлСервлета", ПубличноеЛогин);
                IntentStartFaceApp.putExtra("ПубличноеПарольДлСервлета", ПубличноеПароль);
                IntentStartFaceApp.setClass(getApplicationContext(), MainActivityBootAndAsync.class);
                IntentStartFaceApp.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(IntentStartFaceApp);
               // TODO: 11.04.2025 exit
               finish();
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " PublicID " +PublicID);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    private void savePublicIdSharedPreferences(@NonNull Integer ПубличноеID) {
        try {
            // TODO: 02.08.2023 БИЗНЕС КОД
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("ПубличноеID", ПубличноеID);
            editor.apply();
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " ПубличноеID " +ПубличноеID);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    private void методЗаписываемПервыйЭтапСинхрогниазции( @NonNull String stepAsync) {
        try {
            // TODO: 02.08.2023 БИЗНЕС КОД
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("РежимЗапускаСинхронизации",stepAsync );///TODO "СамыйПервыйЗапускСинхронизации"   ,  //TODO "ПовторныйЗапускСинхронизации"
            editor.commit();
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }






    ///todo метод визуализацци успешных и не успешных аунтифиуаци пользоватле
    private void МетодВизуальногоОтображениеРаботыКоннекта(String СтатусДляПользователя, @NonNull View v) {
        runOnUiThread(new Runnable() {
            public void run() {
                Log.d(this.getClass().getName(), " handlerВизуализацияАунтификации ");
                try {
                    методHideКлавиатура();
                    Drawable icon;
                    ПодсчетПолощительиОтрцательРезультатов++;
                    // TODO: 29.09.2023 Програсс бар
                    методПрограссБАр();

                    Snackbar snackbar = Snackbar.make(v, " " + СтатусДляПользователя + " (" + ПодсчетПолощительиОтрцательРезультатов + ") ", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    Log.d(this.getClass().getName(), " ОшибкаПриПодключениекСерверуДляАунтификацииПользователяПриВходе ");
                    if (ПодсчетПолощительиОтрцательРезультатов > 4) {////ПОПЫТКИ НЕ УДАЧНОГО ВХОДА В ПРОГРАММУ СВЫШЕ 5  СООБШАЕМ ПОЛЬЗОВАТЛЮ ЧТО ЕГО ИММ ЯИ ИЛИ ПАРОЛЬ НЕ ПРАВИЛЬНЫЙ И ПРИЛОЖЕНИЕ ОПРАЫЛЕМ В СОН
                        ПодсчетПолощительиОтрцательРезультатов = 0;
                        ПрогрессБарДляВходаСистему.setVisibility(View.VISIBLE);// при нажатии делаем видимый програсссбар
                        Snackbar.make(v, " Сон на 10 секунд.....", Snackbar.LENGTH_LONG).show();
                        КнопкаВходавСистему.setEnabled(false);
                        КнопкаВходавСистему.setClickable(false);
                        ПрогрессБарДляВходаСистему.postDelayed(() -> {
                            ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);
                            КнопкаВходавСистему.setEnabled(true);
                            КнопкаВходавСистему.setClickable(true);
                            КнопкаВходавСистему.setBackgroundColor(Color.parseColor("#00ACC1"));
                        }, 10000);// по умолчанию прогресс бар делаем не видеым

                        // TODO: 13.10.2021
                    }
                } catch (Exception e) {
                    ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);// при нажатии делаем видимый програсссбар
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }
        });
    }

    private void методHideКлавиатура() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void методПрограссБАр() {
        try{
        ПрогрессБарДляВходаСистему.setIndeterminate(false);
        ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);
        ПрогрессБарДляВходаСистему.refreshDrawableState();
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        // TODO: 01.09.2021 метод вызова
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }







    // TODO: 29.09.2023  Класс Сохранение Пароля
    class ClassSavePassword{
        // TODO: 29.09.2023  пароль
      private   Disposable disposableSave;
    @SuppressLint("CheckResult")
    void metodSavePassword(){
        // TODO: 29.09.2023 Save and Ayntifization
  try{
        RxView.clicks(  КнопкаВходавСистему)
                .throttleFirst(3,TimeUnit.SECONDS)
                .filter(s -> !s.toString().isEmpty())
                .map(new Function<Unit, Object>() {
                    @Override
                    public Object apply(Unit unit) throws Throwable {
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                        return    КнопкаВходавСистему;
                    }
                })
                .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        throwable.printStackTrace();
                        Log.e(getApplicationContext().getClass().getName(),
                                "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getApplicationContext()).recordnewerror(throwable.toString(),
                                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                })
                .onErrorComplete(new Predicate<Throwable>() {
                    @Override
                    public boolean test(Throwable throwable) throws Throwable {
                        throwable.printStackTrace();
                        Log.e(getApplicationContext().getClass().getName(),
                                "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getApplicationContext()).recordnewerror(throwable.toString(),
                                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                        return false;
                    }
                })
                .subscribe( GetPasswordServer-> {
                    ///todo revboot

                    Completable.fromSupplier(()->GetPasswordServers())
                            .subscribeOn(Schedulers.single())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new CompletableObserver() {
                                @Override
                                public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                                    // TODO: 29.09.2023 Запустился
                                    ПрогрессБарДляВходаСистему.setVisibility(View.VISIBLE);// при нажатии делаем видимый програсссбар
                                    ПрогрессБарДляВходаСистему.refreshDrawableState();
                                    ПрогрессБарДляВходаСистему.forceLayout();
                                }

                                @Override
                                public void onComplete() {
                                    Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                        v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                                    } else {
                                        //deprecated in API 26
                                        v2.vibrate(50);
                                    }
                                    // TODO: 29.09.2023 Програсс бар
                                    методПрограссБАр();
                                }

                                @Override
                                public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            });

                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + GetPasswordServer );


                });
    } catch (Exception e) {
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        }

    }


    Boolean GetPasswordServers(){
        Boolean ФлагЕслиРАзрешенияКамераИлиНет=false;
        try{
// TODO: 29.09.2023 ВТОРОЙ Observavle самой операции аунтификации и сохрание пароля

            ПубличноеЛогин = ИмяДляВходаСистему.getText().toString().trim();///получаем из формы имя для того чтобы постучаться на сервер
            Log.d(getPackageName().getClass().getName(), "ПубличноеИмяПользовательДлСервлета " + ПубличноеЛогин);

            ПубличноеПароль = ПарольДляВходаСистему.getText().toString().trim();///////получаем из формы пароль для того чтобы постучаться на сервер
            Log.d(getPackageName().getClass().getName(), "ПубличноеПарольДлСервлета " + ПубличноеПароль);

            // TODO: 29.09.2023 пароль и логин
            if (ПубличноеЛогин.length() > 3 && ПубличноеПароль.length() > 3) {
                // TODO: 22.12.2022  сама запуска синхронищации из workmanager ОБЩЕГО
                boolean ВыбранныйРежимСети =
                        new GetConnectivityManagerAndroid(getApplicationContext()).сonnectivityManageruserselection();

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " lВыбранныйРежимСети" + ВыбранныйРежимСети);



                if (ВыбранныйРежимСети == true) {
                    // TODO: 16.12.2021 НЕПОСРЕДСТВЕННЫЙ ПИНГ СИСТЕНМ ИНТРЕНАТ НА НАЛИЧЕНИ СВАЗИ С БАЗОЙ SQL SERVER
                    Boolean   СтатусРаботыСервера =
                            new GetPingServerJboss(getApplicationContext()). pingServerJbossSuccessfulOrNot( getsslSocketFactory2 ,getHiltPortJboss);

                    // TODO: 07.10.2023 пинг сервера//
                    if (СтатусРаботыСервера == true) {
                            // TODO: 15.09.2023 ОБРАБОТКА ПАРОЛИ
                            методGetПарольОбработка(КнопкаВходавСистему);
                            Log.d(this.getClass().getName(), " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                                    " Класс  :" + Thread.currentThread().getStackTrace()[2].getClassName() +
                                    "  СтатусРаботыСервера " +СтатусРаботыСервера + " ФлагЕслиРАзрешенияКамераИлиНет " +ФлагЕслиРАзрешенияКамераИлиНет);

                        // TODO: 15.09.2023 end password
                    } else {
                        МетодВизуальногоОтображениеРаботыКоннекта("Сервер выкл !!!", КнопкаВходавСистему);
                    }
                } else {
                    МетодВизуальногоОтображениеРаботыКоннекта("Интернет выкл !!!",КнопкаВходавСистему);
                }
            } else {
                МетодВизуальногоОтображениеРаботыКоннекта(" Повторите Логин и пароль ", КнопкаВходавСистему);
            }

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + ФлагЕслиРАзрешенияКамераИлиНет );
    } catch (Exception e) {
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return  ФлагЕслиРАзрешенияКамераИлиНет;
    }






    // TODO: 29.09.2023 Классс после УСПЕШНО уантификайции переходимм в саму программу  faceapp

    class  ClassAfterForfardFaseApp{
        // TODO: 29.09.2023 метод перехода после успепешно аунификации и переход в саму программа
     void       ClassAfterForfardFaseApp(@NonNull   Integer PublicID,@NonNull View v){
         try{

             Integer ЗаписьВSuccesLogin=       методЗаписьВSuccesLogin(PublicID, v);//Integer ПолученинныйПубличныйID
         // TODO: 29.09.2023 SuccesLogin Первый этап
             if (  ЗаписьВSuccesLogin>0  ) {

                 Log.d(this.getClass().getName(), " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                         " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                         " Класс  :" + Thread.currentThread().getStackTrace()[2].getClassName()   + " ЗаписьВSuccesLogin " +ЗаписьВSuccesLogin);





                 Integer ЗаписьВSettingTabel=       методЗаписьВSettingTabel(PublicID, v);//Integer ПолученинныйПубличныйID
                 // TODO: 29.09.2023 SettingTabel  Второй Этап
                 if (ЗаписьВSettingTabel>0) {//TODO после  успешного получение от сервер Public ID для текущего проекта

                     savePublicIdSharedPreferences(PublicID);

                     exitForActivityPassword(PublicID);
                 }
                 Log.d(this.getClass().getName(), " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                         " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                         " Класс  :" + Thread.currentThread().getStackTrace()[2].getClassName()   + " ЗаписьВSettingTabel " +ЗаписьВSettingTabel);
             }else {

                 Toast.makeText(getApplicationContext(), "Ошибка в системных таблицах !!!", Toast.LENGTH_SHORT).show();
             }
         Log.d(this.getClass().getName(), " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                 " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                 " Класс  :" + Thread.currentThread().getStackTrace()[2].getClassName()   + " ЗаписьВSuccesLogin " +ЗаписьВSuccesLogin);

     } catch (Exception e) {
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        }

    }



    // TODO: 23.01.2024 EventBus for Update PO

}








