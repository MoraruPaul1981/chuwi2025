package com.dsy.dsu.Settings.View;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.dsy.dsu.BusinessLogicAll.Class_GRUD_SQL_Operations;
import com.dsy.dsu.BusinessLogicAll.DATE.Class_Generation_Data;
import com.dsy.dsu.Dashboard.Model.LaunchActivityDiaologSettings;
import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;
import com.dsy.dsu.BusinessLogicAll.Class_MODEL_synchronized;
import com.dsy.dsu.CnangeServers.PUBLIC_CONTENT;
import com.dsy.dsu.R;
import com.dsy.dsu.Settings.Model.ChangeSSLForSettings;
import com.google.android.material.button.MaterialButton;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


//вывод данных на Автивити
@AndroidEntryPoint
public class MainActivity_Settings extends AppCompatActivity {

     private      Map<String, String> ХэшДанныеИзБазыДляЗАполенияСпинеровыОрганизация = Collections.synchronizedMap(new LinkedHashMap<String, String>());


     @Inject
      SQLiteDatabase sqLiteDatabase ;
    private Spinner СпинерВыборОрганизации;
    private     Cursor Курсор_СамиДанные_Logins=null;
            private int ЕстьСтроки;
    private MaterialButton imageViewСтрелкаВнутриНастроек,КнопкаСохранениеОрганизации;

    private       Switch СвичДляWIFI ,switchАвтоЗаполенияВТАбелеВыходных,switchСкрытыеПоляПолучениеМатериалов,switchsslcomunications;
    private    Context context;
    private      TextView textViewИмяПрограммы;
    private   TextView textViewВерсияПрограммы;
    private   TextView textViewТекущийПользователь;
    private    TextView textViewВремяПоследнееСинхронизации;
    private     int ПубличныйIDДляорганизацции=0;
    private     String ДатаДляОбновлениеОргназации;
    private SharedPreferences preferences;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    // TODO: 12.10.2021  Ссылка Менеджер Потоков
    PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Log.d(this.getClass().getName(), "Запущен.... метод  onCreate в классе MainActivity_Settings  ; ");
                super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_wifi);
            getSupportActionBar().hide(); ///скрывать тул бар

            preferences = getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);

            fragmentManager =  getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();


            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new  PUBLIC_CONTENT(getApplicationContext());
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            /////todo данная настрока запрещает при запуке активти подскаваать клавиатуре вверх на компонеты eedittext
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            getSupportActionBar().hide(); ///скрывать тул бар
            context =this;
            // TODO: 16.04.2025

            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " sqLiteDatabase " +sqLiteDatabase);



            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
            imageViewСтрелкаВнутриНастроек = (MaterialButton) findViewById(R.id.imageViewСтрелкаВнутриНастроек);

            textViewВерсияПрограммы=(TextView) findViewById(R.id.textViewВерсияПрограммы);

            Log.d(this.getClass().getName(), "  textViewВерсияПрограммы " + textViewВерсияПрограммы.getText());
            textViewТекущийПользователь  =(TextView) findViewById(R.id.textViewТекущийПользователь);
            String ПолученыйТекущееИмяПользователя=new Class_MODEL_synchronized(getApplicationContext()).МетодПолучениеИмяСистемыДляСменыПользователя(getApplicationContext());
            Log.d(this.getClass().getName(), "  ПолученыйТекущееИмяПользователя  "+ПолученыйТекущееИмяПользователя);
            textViewТекущийПользователь.setText("Пользователь: "+ПолученыйТекущееИмяПользователя.toUpperCase());
            textViewВремяПоследнееСинхронизации =(TextView) findViewById(R.id.textViewВремяПоследнееСинхронизации);
            СвичДляWIFI= (Switch) findViewById(R.id.switchWIFI);
            switchАвтоЗаполенияВТАбелеВыходных= (Switch) findViewById(R.id.switchАвтоЗаполенияВТАбелеВыходных);
            switchСкрытыеПоляПолучениеМатериалов= (Switch) findViewById(R.id.switchСкрытыеПоляПолучениеМатериалов);

            switchsslcomunications= (Switch) findViewById(R.id.switchsslcomunications);


            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        try {
            МетодСозданиеКодBACK();
            МетодОбработкиСвичаДляWIFI();
            МетодОбработкиСвичаАвтоматическогоДобавлениямМеткуВыходныхДней();
            методВычисляетПоследнуюДатуСинхронищацииССервром();
            МетодОбработкиСкрытыхматериалов();
            settheCurrentVersionoftheProgramVersion( );

            // TODO: 08.10.2024 SSL
            ChangeSSLForSettings sslForSettings=    new ChangeSSLForSettings(switchsslcomunications,getApplicationContext());

            sslForSettings .changeSwitcSllSimple();
            sslForSettings .changeSwitcSllSimpleLister();


            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    private void МетодОбработкиСкрытыхматериалов() {
        try {
       Boolean     ФлагДляСкрытыхМатериалов = preferences.getBoolean("ФлагДляСкрытыхМатериалов",false);
            //////////TODO КАК РЕЖИМ РАБОТЫ ИНТРЕНТА И  ПЕРЕОПРЕДЕЛЯЕМ ВИЗУАЛЬНО
            if(ФлагДляСкрытыхМатериалов){
                switchСкрытыеПоляПолучениеМатериалов.setChecked(true);
                switchСкрытыеПоляПолучениеМатериалов.setText("Материалы (Открытые)");///Оба Mobile/Wifi
            }else {
                switchСкрытыеПоляПолучениеМатериалов.setChecked(false);
                switchСкрытыеПоляПолучениеМатериалов.setText("Материалы (Скрытые)");

            }

            switchСкрытыеПоляПолучениеМатериалов.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    SharedPreferences.Editor editor = preferences.edit();
                    if (isChecked) {
                        MainActivity_Settings.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                switchСкрытыеПоляПолучениеМатериалов.setText("Материалы (Открытые)");
                                editor.putBoolean("ФлагДляСкрытыхМатериалов", true);
                            }});
                    } else {
                        MainActivity_Settings.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                switchСкрытыеПоляПолучениеМатериалов.setText("Материалы (Скрытые)");
                                editor.putBoolean("ФлагДляСкрытыхМатериалов", false);
                            }});
                    }
                    editor.apply();
                }
            });
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


// TODO: 02.06.2021 метод которыу вычислет и заполянет ДАТУПОСЛЕДНЕЙ СИНХРОНИЗАЦИИ С СЕРВЕРОМ

    protected void методВычисляетПоследнуюДатуСинхронищацииССервром() {


        SQLiteCursor  Курсор_ВытаскиваемПоследнуюДатуСинхрониазииССерором=null;

Class_GRUD_SQL_Operations class_grud_sql_operationsВычисляетПоследнуюДатуСинхронищацииССервром=new Class_GRUD_SQL_Operations(getApplicationContext());
 try{

  String ПоследнаяДата=null;


     // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
     ///
     class_grud_sql_operationsВычисляетПоследнуюДатуСинхронищацииССервром.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","MODIFITATION_Client");
     ///////
             class_grud_sql_operationsВычисляетПоследнуюДатуСинхронищацииССервром.concurrentHashMapНабор.put("СтолбцыОбработки","versionserveraandroid");
             //
             class_grud_sql_operationsВычисляетПоследнуюДатуСинхронищацииССервром.
                     concurrentHashMapНабор.put("ФорматПосика","versionserveraandroid  = (SELECT MAX(versionserveraandroid) FROM MODIFITATION_Client)  AND versionserveraandroid IS NOT NULL ");

             Курсор_ВытаскиваемПоследнуюДатуСинхрониазииССерором= (SQLiteCursor)  new Class_GRUD_SQL_Operations(getApplicationContext()).
                     new GetData(getApplicationContext()).getdata(class_grud_sql_operationsВычисляетПоследнуюДатуСинхронищацииССервром.concurrentHashMapНабор,
                     Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabase);

             Log.d(this.getClass().getName(), "GetData "  +Курсор_ВытаскиваемПоследнуюДатуСинхрониазииССерором);
     ////TODO   результат
            if ( Курсор_ВытаскиваемПоследнуюДатуСинхрониазииССерором.getCount()>0) {
                Курсор_ВытаскиваемПоследнуюДатуСинхрониазииССерором.moveToFirst();////
                ПоследнаяДата  =Курсор_ВытаскиваемПоследнуюДатуСинхрониазииССерором.getString(0);
                Log.d(this.getClass().getName(), "ПоследнаяДата"+ПоследнаяДата);
             }
     if (ПоследнаяДата!=null) {
         textViewВремяПоследнееСинхронизации.setText("Успешный обмен : Дата "+ПоследнаяДата);
     }


     ///////
    } catch (Exception e) {
        //  Block of code to handle errors
        e.printStackTrace();
     Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
             + Thread.currentThread().getStackTrace()[2].getLineNumber());
     new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
             Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    
    
    






    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    protected void onDestroy() {
        super.onDestroy();




    }












































    private void МетодОбработкиСвичаДляWIFI() throws InterruptedException {
        try{
        //TODO флажек WIFI MObile /// =  МетодПолучениеЗначенияРежимаРаботыИнтернетаWifiИлиInternet(КонтекстКоторыйДляСинхронизации);
            Class_GRUD_SQL_Operations concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийДляОпределенияРЕжимаРаботыСетиВЫборWIFIИЛИMOBILE;
            String  РезультатКакойРежимРаботыССетьюВыборWifiИлиMobile=new String();
            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийДляОпределенияРЕжимаРаботыСетиВЫборWIFIИЛИMOBILE=new Class_GRUD_SQL_Operations(getApplicationContext());
            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийДляОпределенияРЕжимаРаботыСетиВЫборWIFIИЛИMOBILE.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","SuccessLogin");
            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийДляОпределенияРЕжимаРаботыСетиВЫборWIFIИЛИMOBILE.concurrentHashMapНабор.put("СтолбцыОбработки","mode_connection");
            SQLiteCursor КурсорУзнаемСохраненыйРежимРаботыССетьюВЫборWIFIИЛИMObile=null;
            КурсорУзнаемСохраненыйРежимРаботыССетьюВЫборWIFIИЛИMObile= (SQLiteCursor)  concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийДляОпределенияРЕжимаРаботыСетиВЫборWIFIИЛИMOBILE.
                    new GetData(getApplicationContext()).getdata(concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийДляОпределенияРЕжимаРаботыСетиВЫборWIFIИЛИMOBILE.concurrentHashMapНабор,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabase);
            Log.d(this.getClass().getName(), "GetData " +КурсорУзнаемСохраненыйРежимРаботыССетьюВЫборWIFIИЛИMObile );
            if (КурсорУзнаемСохраненыйРежимРаботыССетьюВЫборWIFIИЛИMObile.getCount() > 0) {
                КурсорУзнаемСохраненыйРежимРаботыССетьюВЫборWIFIИЛИMObile.moveToFirst();
                РезультатКакойРежимРаботыССетьюВыборWifiИлиMobile = КурсорУзнаемСохраненыйРежимРаботыССетьюВЫборWIFIИЛИMObile.getString(0);
                Log.d(getApplicationContext().getClass().getName(), " РезультатКакойРежимРаботыССетьюВыборWifiИлиMobile  " + "--" +РезультатКакойРежимРаботыССетьюВыборWifiИлиMobile);/////
            }
        Log.d(this.getClass().getName(), " РезультатКакойРежимРаботыССетьюВыборWifiИлиMobile : "+ РезультатКакойРежимРаботыССетьюВыборWifiИлиMobile);
            final Toast[] aa = new Toast[1];
            final ImageView[] cc = new ImageView[1];
            MainActivity_Settings.this.runOnUiThread(new Runnable() {
                                                     @Override
                                                     public void run() {
                                                          aa[0] = Toast.makeText(getBaseContext(), "OPEN", Toast.LENGTH_SHORT);
                                                         cc[0] = new ImageView(getBaseContext());
                                                     }});
        //////////TODO КАК РЕЖИМ РАБОТЫ ИНТРЕНТА И  ПЕРЕОПРЕДЕЛЯЕМ ВИЗУАЛЬНО
        switch (РезультатКакойРежимРаботыССетьюВыборWifiИлиMobile.trim()){
            case"Mobile":
                this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        СвичДляWIFI.setChecked(true);
                        СвичДляWIFI.setText("Mobile/Wifi (Сеть)");///Оба Mobile/Wifi
                      //  cc[0].setImageResource(R.drawable.icon_dsu1_for_dont_wifi);
                        aa[0].setView(cc[0]);
                        aa[0].show();
                    }
                });
                break;
                ///TODO WIFI ТОЛЬКО
            case"WIFI":
                this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                СвичДляWIFI.setChecked(false);
                СвичДляWIFI.setText("WIFI");
             //   cc[0].setImageResource(R.drawable.icon_dsu1_for__wifi);
                aa[0].setView(cc[0]);
                aa[0].show();
                    }
                });
                break;
        }
        СвичДляWIFI.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //////TODO ЕСЛИ РЕЖИМ TRUE  MOBILE ВПИСЫВАЕМ КАК В БАЗУ MOBILE
                if (isChecked) {
                    // The toggle is enabled mobile
         Integer РезультатЗаписиНовогоРЕжима=    new Class_MODEL_synchronized(getApplicationContext())
                 .МетодКоторыйЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobile("Mobile", getApplicationContext()
                     ,"SuccessLogin","mode_connection" );
                    Log.d(this.getClass().getName(), "РезультатЗаписиНовогоРЕжима " +РезультатЗаписиНовогоРЕжима );
                    MainActivity_Settings.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            СвичДляWIFI.setText("Mobile/Wifi (Сеть)");
                            Toast aa = Toast.makeText(getBaseContext(), "OPEN", Toast.LENGTH_SHORT);
                            ImageView cc = new ImageView(getBaseContext());
                            cc.setImageResource(R.drawable.icon_dsu1_for_dont_wifi);
                            aa.setView(cc);
                            aa.show();
                        }});
                } else {
                    // The toggle is disabled
                    Integer РезультатЗаписиНовогоРЕжима=            new Class_MODEL_synchronized(getApplicationContext()).МетодКоторыйЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobile("WIFI", getApplicationContext()
                            ,"SuccessLogin","mode_connection" );
                    Log.d(this.getClass().getName(), "РезультатЗаписиНовогоРЕжима " +РезультатЗаписиНовогоРЕжима );
                    ///TODO принудительно устанвливаем редим работы синхронизации
                    MainActivity_Settings.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            СвичДляWIFI.setText("Wifi");
                            Toast aa = Toast.makeText(getBaseContext(), "OPEN", Toast.LENGTH_SHORT);
                            ImageView cc = new ImageView(getBaseContext());
                            cc.setImageResource(R.drawable.icon_dsu1_for__wifi);
                            aa.setView(cc);
                            aa.show();
                        }});
                }
            }
        });
    } catch (Exception e) {
        e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    private void МетодОбработкиСвичаАвтоматическогоДобавлениямМеткуВыходныхДней() throws InterruptedException {
        try{
            //TODO флажек WIFI MObile /// =  МетодПолучениеЗначенияРежимаРаботыИнтернетаWifiИлиInternet(КонтекстКоторыйДляСинхронизации);
          String РезультатКакойРежимЗаписанвБазеВЫходныеДни = new String();

        /*    РезультатКакойРежимЗаписанвБазеВЫходныеДни =  new Class_MODEL_synchronized(getApplicationContext()).
                    МетодПолучениеЗначенияРежимаРаботыИнтернетаWifiИлиInternet(getApplicationContext() ,"SuccessLogin","mode_weekend");*/
            Class_GRUD_SQL_Operations concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийДляПолучениеСтатусаВключенРЕжимВыходныхДней;


            // TODO: 24.05.2021 ТРЕТИЙ КОД ЕСЛИ ПОЛЬЗОВАТЕЛЬ ЗАХОДТЕ АВТОМАТИЧЕСКОЙ УСВТУКУ В ВЫХОДЫНЕ ДНИ

            ////
            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийДляПолучениеСтатусаВключенРЕжимВыходныхДней=new Class_GRUD_SQL_Operations(getApplicationContext());

            ///
            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийДляПолучениеСтатусаВключенРЕжимВыходныхДней.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","SuccessLogin");
            ///////
            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийДляПолучениеСтатусаВключенРЕжимВыходныхДней.concurrentHashMapНабор.put("СтолбцыОбработки","mode_weekend");

            // TODO: 02.09.2021 exe sql
            SQLiteCursor КурсорУзнаемСохраненыйРежимАрботыВыходныхДней=null;

            КурсорУзнаемСохраненыйРежимАрботыВыходныхДней= (SQLiteCursor) concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийДляПолучениеСтатусаВключенРЕжимВыходныхДней.
                    new GetData(getApplicationContext()).getdata(concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийДляПолучениеСтатусаВключенРЕжимВыходныхДней.
                            concurrentHashMapНабор,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabase);

            Log.d(this.getClass().getName(), "GetData " +КурсорУзнаемСохраненыйРежимАрботыВыходныхДней );


            //////
            if (КурсорУзнаемСохраненыйРежимАрботыВыходныхДней.getCount() > 0) {

                КурсорУзнаемСохраненыйРежимАрботыВыходныхДней.moveToFirst();

                РезультатКакойРежимЗаписанвБазеВЫходныеДни = КурсорУзнаемСохраненыйРежимАрботыВыходныхДней.getString(0).trim();

                ///
                Log.d(getApplicationContext().getClass().getName(), " РезультатКакойРежимЗаписанвБазеВЫходныеДни  " + "--" +РезультатКакойРежимЗаписанвБазеВЫходныеДни);/////


            }






            Log.d(this.getClass().getName(), " РезультатКакойРежимЗаписанвБазеВЫходныеДни : "+ РезультатКакойРежимЗаписанвБазеВЫходныеДни);
            ////
            final Toast[] aa = new Toast[1];
            final ImageView[] cc = new ImageView[1];
            MainActivity_Settings.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    aa[0] = Toast.makeText(getBaseContext(), "OPEN", Toast.LENGTH_SHORT);
                    cc[0] = new ImageView(getBaseContext());
                }});


            //////////TODO КАК РЕЖИМ РАБОТЫ ИНТРЕНТА И  ПЕРЕОПРЕДЕЛЯЕМ ВИЗУАЛЬНО
            switch (РезультатКакойРежимЗаписанвБазеВЫходныеДни){
                case"Включить":
                    this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            switchАвтоЗаполенияВТАбелеВыходных.setChecked(true);
                            switchАвтоЗаполенияВТАбелеВыходных.setText("Вкл (Выходные)");
                            //  cc[0].setImageResource(R.drawable.icon_dsu1_for_dont_wifi);
                            aa[0].setView(cc[0]);
                            aa[0].show();

                        }
                    });
                    break;


                ///TODO WIFI ТОЛЬКО
                case"Выключить":
                    this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            switchАвтоЗаполенияВТАбелеВыходных.setChecked(false);
                            switchАвтоЗаполенияВТАбелеВыходных.setText("Выкл (Выходные)");
                            //   cc[0].setImageResource(R.drawable.icon_dsu1_for__wifi);
                            aa[0].setView(cc[0]);
                            aa[0].show();
                        }
                    });
                    break;

            }


//////todo при нНАЖАТИИ  НА КНОПКУ СОХРАНИТЬ НАСТРОЙКИ ЗАПИСЫВАЕТ ОРГАНИЗАЦИЮ В БАЗУ

            switchАвтоЗаполенияВТАбелеВыходных.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    //////TODO ЕСЛИ РЕЖИМ TRUE  MOBILE ВПИСЫВАЕМ КАК В БАЗУ MOBILE
                    if (isChecked) {
                        // The toggle is enabled mobile
                        new Class_MODEL_synchronized(getApplicationContext()).МетодКоторыйЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobile("Включить", getApplicationContext()
                                ,"SuccessLogin","mode_weekend");

                        ///TODO принудительно устанвливаем редим работы синхронизации
                        MainActivity_Settings.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                switchАвтоЗаполенияВТАбелеВыходных.setText("Вкл (Выходные)");
                                Toast aa = Toast.makeText(getBaseContext(), "OPEN", Toast.LENGTH_SHORT);
                                ImageView cc = new ImageView(getBaseContext());
                                cc.setImageResource(R.drawable.icon_dsu1_add_organisazio_success);
                                aa.setView(cc);
                                aa.show();
                            }});



                        //////TODO ЕСЛИ РЕЖИМ TRUE  MOBILE ВПИСЫВАЕМ КАК В БАЗУ ТОЛЬКО WIFI
                    } else {
                        // The toggle is disabled
                        new Class_MODEL_synchronized(getApplicationContext()).МетодКоторыйЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobile("Выключить", getApplicationContext()
                                ,"SuccessLogin","mode_weekend");

                        ///TODO принудительно устанвливаем редим работы синхронизации
                        MainActivity_Settings.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                switchАвтоЗаполенияВТАбелеВыходных.setText("Выкл (Выходные)");
                                Toast aa = Toast.makeText(getBaseContext(), "OPEN", Toast.LENGTH_SHORT);
                                ImageView cc = new ImageView(getBaseContext());
                                cc.setImageResource(R.drawable.icon_dsu1_off_swihc_bolyny_error);
                                aa.setView(cc);
                                aa.show();
                            }});
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }



















//todo СОХРАНЕНИЕ ВЫБРАНОЙ ОРГАНИЗАЦИИ


            private int МетодЗаписиПолученойОрганизацииВТАблицу(String ПолученоеНазваниеИзСпинераДляХэша) {

                final long[] РезультатВставкиНовогоСотрудникарезКонтрейнер = {0};
        try {


            Object ПолученныйUUIDИзХэшаОрганизации = null;
            for (Map.Entry<String, String> ХэшПосикаОрганизация : ХэшДанныеИзБазыДляЗАполенияСпинеровыОрганизация.entrySet()) {

                if (ХэшПосикаОрганизация .getValue().trim().equalsIgnoreCase(ПолученоеНазваниеИзСпинераДляХэша)) {

                    Log.d(this.getClass().getName(), "ХэшПосикаСоздаваемогоТабеля.getKey() " + ХэшПосикаОрганизация .getKey()

                            + " ХэшПосикаСоздаваемогоТабеля.getValue() "+ ХэшПосикаОрганизация .getValue());
                    //////финальное значение от сфо

                    ПолученныйUUIDИзХэшаОрганизации =ХэшПосикаОрганизация.getKey();

                    int ПолученныйUUIDИзХэшаОрганизацииФинал=Integer.parseInt(ПолученныйUUIDИзХэшаОрганизации.toString());


                    /////todo записываем получени UUID
                    if ( ПолученныйUUIDИзХэшаОрганизацииФинал>0) {
                        /////

                        final ContentValues[] АдаптерВставкиВыбраноеОрганизации = {new ContentValues()};


                         АдаптерВставкиВыбраноеОрганизации[0] = МетодЗаполенияДаннымиПриВставкеОрганизации(ПолученныйUUIDИзХэшаОрганизацииФинал);



///TODO ВСТАВКА НОВГО ТАБЕЛЯ В ТАБЛИЦУ

                        РезультатВставкиНовогоСотрудникарезКонтрейнер[0] = new Class_MODEL_synchronized(getApplicationContext()).
                                ВставкаДанныхЧерезКонтейнерОрганизацияДляТекущегоСотрудникаУниверсальная("settings_tabels",
                                        АдаптерВставкиВыбраноеОрганизации[0], "settings_tabels",
                                        "",
                                        true,
                                        ПубличныйIDДляорганизацции,ДатаДляОбновлениеОргназации);

                        Log.d(this.getClass().getName(), " РезультатВставкиНовогоСотрудникарезКонтрейнер " + РезультатВставкиНовогоСотрудникарезКонтрейнер[0]);









                                Log.d(this.getClass().getName(), "записали названиеорганизацуии в базу " + new Date()  +
                                        "               РезультатВставкиНовогоСотрудникарезКонтрейнер[0] "+              РезультатВставкиНовогоСотрудникарезКонтрейнер[0]);








                        // TODO: 19.03.2021 конец вставки организаии
                        Toast aa = Toast.makeText(getBaseContext(), "OPEN", Toast.LENGTH_SHORT);
                        ImageView cc = new ImageView(getBaseContext());
                        /////TODO РЕЗУЛЬТАТ ВСТАВКИ ОРГАНИЗАЦИИ НА АКТИЫТИ НАСТРОЙКИ
                        if (  РезультатВставкиНовогоСотрудникарезКонтрейнер[0] >0){
                            //      cc.setImageResource(R.drawable.icon_dsu1_add_organisazio_success);//icon_dsu1_synchronisazia_dsu1_success
                        }else{
                            cc.setImageResource(R.drawable.icon_dsu1_add_organisazio_error);//icon_dsu1_synchronisazia_dsu1_success
                            Toast.makeText(getApplicationContext(), "ошибка организация не записалась !!!" , Toast.LENGTH_LONG).show();
                            ////
                            aa.setView(cc);
                            aa.show();
                        }
                        ////////////////////
/////TODO результат вставки организации в систему
                    }
                    ///TODO как заполнили сразу выходим
                    break;
                }
            }
            ////TODO если не пустой
        } catch (Exception e) {
                e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
return (int) РезультатВставкиНовогоСотрудникарезКонтрейнер[0];
    }








    @NotNull
    protected ContentValues МетодЗаполенияДаннымиПриВставкеОрганизации(
            int полученныйUUIDИзХэшаОрганизацииФинал) {
        /////////
        ContentValues АдаптерВставкиВыбраноеОрганизации = new ContentValues();////контрейнер для нового табеля
        //TODO вставка в контейнер

        //////
        ПубличныйIDДляорганизацции=0;

        /////
        Class_GRUD_SQL_Operations class_grud_sql_operationsЗаполенияДаннымиПриВставкеОрганизации;

   try{

        ///////

        АдаптерВставкиВыбраноеОрганизации.put("organizations", String.valueOf(полученныйUUIDИзХэшаОрганизацииФинал));

// TODO: 28.03.2021 id search puvlic


            class_grud_sql_operationsЗаполенияДаннымиПриВставкеОрганизации=new Class_GRUD_SQL_Operations(getApplicationContext());

       // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ



       ///
       class_grud_sql_operationsЗаполенияДаннымиПриВставкеОрганизации.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","SuccessLogin");
       ///////
       class_grud_sql_operationsЗаполенияДаннымиПриВставкеОрганизации.concurrentHashMapНабор.put("СтолбцыОбработки","id");
       //
       class_grud_sql_operationsЗаполенияДаннымиПриВставкеОрганизации.concurrentHashMapНабор.put("ФорматПосика","id IS NOT NULL");
                    ///"_id > ?   AND _id< ?"
                    //////
               /*     class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска1",finalПолученныйUUID);
                    ///
                    class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска2","Удаленная");
                    ///
                    class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);
                    //
                    class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 .......
*/
       ////TODO другие поля

       ///classGrudSqlOperations. concurrentHashMapНабор.put("ПоляГрупировки",null);
       ////
       //class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеГрупировки",null);
       ////
       class_grud_sql_operationsЗаполенияДаннымиПриВставкеОрганизации.concurrentHashMapНабор.put("УсловиеСортировки","date_update");
       ////
       class_grud_sql_operationsЗаполенияДаннымиПриВставкеОрганизации.concurrentHashMapНабор.put("УсловиеЛимита","1");
       ////

       // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ

       SQLiteCursor Курсор_ИщемПУбличныйIDКогдаегоНетВстатике=null;

       ///
       Курсор_ИщемПУбличныйIDКогдаегоНетВстатике= (SQLiteCursor)  new Class_GRUD_SQL_Operations(getApplicationContext()).
               new GetData(getApplicationContext()).getdata(class_grud_sql_operationsЗаполенияДаннымиПриВставкеОрганизации.concurrentHashMapНабор,
               Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabase);

       Log.d(this.getClass().getName(), "GetData "  +Курсор_ИщемПУбличныйIDКогдаегоНетВстатике);



/*


            // TODO: 07.09.2021  _______________old
            Курсор_ИщемПУбличныйIDКогдаегоНетВстатике =
                    new Class_MODEL_synchronized(getApplicationContext()).КурсорУниверсальныйДляБазыДанных("SuccessLogin",
                            new String[]{"id"}, " id IS NOT NULL", null, null, null, "date_update", "1");//
*/

        // TODO: 07.09.2021  РЕЗУЛЬТАТы 
        /////////
        if(Курсор_ИщемПУбличныйIDКогдаегоНетВстатике.getCount()>0){
            //////////
            Курсор_ИщемПУбличныйIDКогдаегоНетВстатике.moveToFirst();
            ////
            Log.d(this.getClass().getName(), " Курсор_ИщемПУбличныйIDКогдаегоНетВстатике " + Курсор_ИщемПУбличныйIDКогдаегоНетВстатике.getCount());

            ПубличныйIDДляорганизацции =Курсор_ИщемПУбличныйIDКогдаегоНетВстатике.getInt(0);
            
        }

        Log.d(this.getClass().getName(), "   ПубличныйIDДляорганизацции" +
                ПубличныйIDДляорганизацции);



// TODO: 07.09.2021 заполенение данными
        АдаптерВставкиВыбраноеОрганизации.put("user_update", String.valueOf(ПубличныйIDДляорганизацции));







        // TODO: 07.09.2021  ВТОРОЕ ДЕЙСТИЕ ВТОРОЙ КУРСОР

       /////


       // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

       ///

       class_grud_sql_operationsЗаполенияДаннымиПриВставкеОрганизации=new Class_GRUD_SQL_Operations(getApplicationContext());

       ///
       class_grud_sql_operationsЗаполенияДаннымиПриВставкеОрганизации.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","settings_tabels");
       ///////
       class_grud_sql_operationsЗаполенияДаннымиПриВставкеОрганизации.concurrentHashMapНабор.put("СтолбцыОбработки","uuid");
       //
       class_grud_sql_operationsЗаполенияДаннымиПриВставкеОрганизации.concurrentHashMapНабор.put("ФорматПосика","user_update=? ");
                    ///"_id > ?   AND _id< ?"
                    //////
       class_grud_sql_operationsЗаполенияДаннымиПриВставкеОрганизации.concurrentHashMapНабор.put("УсловиеПоиска1",ПубличныйIDДляорганизацции);
                    ///
             /*       class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска2","Удаленная");
                    ///
                    class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);
                    //
                    class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 .......
*/
       ////TODO другие поля*/

       ///classGrudSqlOperations. concurrentHashMapНабор.put("ПоляГрупировки",null);
       ////
       //class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеГрупировки",null);
       ////
       class_grud_sql_operationsЗаполенияДаннымиПриВставкеОрганизации.concurrentHashMapНабор.put("УсловиеСортировки","date_update");
       ////
       class_grud_sql_operationsЗаполенияДаннымиПриВставкеОрганизации.concurrentHashMapНабор.put("УсловиеЛимита","1");
       ////
       // TODO: 20.04.2021 определяем ели UUID или нет
       SQLiteCursor     Курсор_УзнаемЕслиUUIDВТАблицеОрганизация=null;
       Курсор_УзнаемЕслиUUIDВТАблицеОрганизация= (SQLiteCursor)  new Class_GRUD_SQL_Operations(getApplicationContext()).
               new GetData(getApplicationContext()).getdata(class_grud_sql_operationsЗаполенияДаннымиПриВставкеОрганизации.concurrentHashMapНабор,
               Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabase);
       Log.d(this.getClass().getName(), "GetData "  +Курсор_УзнаемЕслиUUIDВТАблицеОрганизация);
        int UUIDдлянастроуки = 0;
            // TODO: 07.09.2021  РЕЗУЛЬТАТ
            if (Курсор_УзнаемЕслиUUIDВТАблицеОрганизация!=null){

                if (Курсор_УзнаемЕслиUUIDВТАблицеОрганизация.getCount()>0){

                    Курсор_УзнаемЕслиUUIDВТАблицеОрганизация.moveToFirst();

                    int IndexUUID=Курсор_УзнаемЕслиUUIDВТАблицеОрганизация.getColumnIndex("uuid");

                    UUIDдлянастроуки=Курсор_УзнаемЕслиUUIDВТАблицеОрганизация.getInt(IndexUUID);


                    if(UUIDдлянастроуки==0){
                        Random random=new Random();
                        UUIDдлянастроуки=random.nextInt(100000);
                    }

                }else{
                    Random random=new Random();
                    UUIDдлянастроуки=random.nextInt(100000);
                }
            }
       АдаптерВставкиВыбраноеОрганизации.put("uuid", UUIDдлянастроуки);
        // TODO: 28.03.2021 date for update orgazition
        ДатаДляОбновлениеОргназации=null;
    String СгенерированованныйДата=     new Class_Generation_Data(getApplicationContext()).ГлавнаяДатаИВремяОперацийСБазойДанных();
        ДатаДляОбновлениеОргназации=          СгенерированованныйДата;
       Log.d(this.getClass().getName(), "ДатаДляОбновлениеОргназации"+ДатаДляОбновлениеОргназации);
        АдаптерВставкиВыбраноеОрганизации.put("date_update", ДатаДляОбновлениеОргназации);

    } catch (Exception e) {
        //  Block of code to handle errors
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return АдаптерВставкиВыбраноеОрганизации;
    }

    /////todo метод создание BACK
    private void МетодСозданиеКодBACK() {
        imageViewСтрелкаВнутриНастроек.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO Запусукаем Фргамент НАстройки  dashbord
                new LaunchActivityDiaologSettings(fragmentManager,context).launchADashboardSettings();

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");





            }

        });
    }























    //// TODO МЕТОД ЗАПОЛЕНИЯ ДАННЫМИ ИЗ БАЗЫ В СПИНЕР НАЗВАНИЯ ОРГАНИЗАЦИЙ

   protected ArrayList<String> МетодЗаполненияНазваниеОрганизации(String ИмяТаблицыДляСпинера,
                                                         String СтолбикДляЗагурзкиВСпинер) {
        ///
        Log.d(this.getClass().getName()," МетодЗаполениеяСпинераДаннымиИзБазы() ");
        //////////////s
      SQLiteCursor Курсор_ЗагружаетДанныеПриСозданииТабеля = null;
        /////
        ArrayList<String> АрайЛИстДанныеИзБазыДляЗАполенияСпинеровы= new ArrayList <String>(); ////ГЛАВНЫЙ СПИСОК ТАБЛИЦ ДЛЯ  ОБМЕНАМИ ДАННЫМИ ИЗ НЕГО БУДЕТ БРАТЬСЯ СПИСКО ТАБЛИЦ


       Class_GRUD_SQL_Operations class_grud_sql_operationsЗаполненияНазваниеОрганизации=new Class_GRUD_SQL_Operations(getApplicationContext());

        try{
/////todo КОД ЗАПОЛЕНЕИЯ ДАННЫМИ В СПИНЕР ЦФО ДЕПАРТАМЕНТ МЕСЯЦ

            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

            ///
            class_grud_sql_operationsЗаполненияНазваниеОрганизации.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы",ИмяТаблицыДляСпинера);
            ///////
            class_grud_sql_operationsЗаполненияНазваниеОрганизации.concurrentHashMapНабор.put("СтолбцыОбработки",СтолбикДляЗагурзкиВСпинер);
            //
            /*        class_grud_sql_operations. concurrentHashMapНабор.put("ФорматПосика","uuid=?    AND status_send !=? AND month_tabels=? AND  year_tabels =? AND fio IS NOT NULL ");
                    ///"_id > ?   AND _id< ?"
                    //////
                    class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска1",finalПолученныйUUID);
                    ///
                    class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска2","Удаленная");
                    ///
                    class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);
                    //
                    class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 .......
*/
            ////TODO другие поля

            ///classGrudSqlOperations. concurrentHashMapНабор.put("ПоляГрупировки",null);
            ////
            //class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеГрупировки",null);
            ////
   /*         class_grud_sql_operationsПолучениеИмяСистемы. concurrentHashMapНабор.put("УсловиеСортировки","date_update");
            ////
            /// class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеЛимита","1");*/
            ////

            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ

            Курсор_ЗагружаетДанныеПриСозданииТабеля=null;

            /////////
            Курсор_ЗагружаетДанныеПриСозданииТабеля= (SQLiteCursor)  new Class_GRUD_SQL_Operations(getApplicationContext()).
                    new GetData(getApplicationContext()).getdata(class_grud_sql_operationsЗаполненияНазваниеОрганизации.concurrentHashMapНабор,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabase);

            Log.d(this.getClass().getName(), "GetData "  +Курсор_ЗагружаетДанныеПриСозданииТабеля);




    /*        // TODO: 07.09.2021       _______________old

            Курсор_ЗагружаетДанныеПриСозданииТабеля =  new Class_MODEL_synchronized(getApplicationContext()).КурсорУниверсальныйДляБазыДанных(ИмяТаблицыДляСпинера, new String[]
                                        {СтолбикДляЗагурзкиВСпинер}, null,
                                null, null, null,null, null);///"SELECT name  FROM MODIFITATION_Client WHERE name=?",НазваниеТаблицНаСервере

*/


            if (Курсор_ЗагружаетДанныеПриСозданииТабеля.getCount()>0) {
                ////
                МетодЗаполенияАктивтиНастройки(Курсор_ЗагружаетДанныеПриСозданииТабеля, АрайЛИстДанныеИзБазыДляЗАполенияСпинеровы);
            }

            Log.d(this.getClass().getName(), " asyncTaskLoaderЗагружаетДанныеПриСозданииТабеля[0].getCount() " +  Курсор_ЗагружаетДанныеПриСозданииТабеля.getCount());
///

            ///поймать ошибку
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            ///////
        }
////ВОЗВРЯЩАЕМ  МетодЗаполненияДляТабеляСпинераДаннымиИзБазы
        return АрайЛИстДанныеИзБазыДляЗАполенияСпинеровы;
    }


















    @SuppressLint("SuspiciousIndentation")
    protected void МетодЗаполенияАктивтиНастройки(Cursor Курсор_ЗагружаетДанныеПриСозданииТабеля,
                                                  ArrayList<String> арайЛИстДанныеИзБазыДляЗАполенияСпинеровы) {

        try{
        /////УДАЛЯЕМ ИЗ ПАМЯТИ  ОТРАБОТАННЫЙ АСИНАТСК
        /////
        String ЗначениеДляЗаполенияСпинера= "";

////////
        if ( Курсор_ЗагружаетДанныеПриСозданииТабеля.getCount()>0) {/////ЗАГРУЖАЕМ ДАННЫЕ ИЗ ТАБЛИЦЫ CFO ДЛЯ СПИНЕРА И СОЗДАНИЯ ТАБЕЛЯ
            /////
            Курсор_ЗагружаетДанныеПриСозданииТабеля.moveToFirst();

            Log.d(this.getClass().getName(), " Курсор_ЗагружаетДанныеПриСозданииТабеля " + Курсор_ЗагружаетДанныеПриСозданииТабеля.getCount());

            ////TODO ДАННЫЕ ПЕРВЫМ СТАВИТЬСЯ ПЕРЕД ВСЕМ МАСИВОМ ЗНАЧЕНИЯ
            арайЛИстДанныеИзБазыДляЗАполенияСпинеровы.add("") ;
            //TODO
            ХэшДанныеИзБазыДляЗАполенияСпинеровыОрганизация.clear();


            ////сам цикл заполения спинеров
          do{

              /////

                ///////вытаскиваем данные из базы столбкик ЗАПОЛЕНИЕ ХЕША
                //TODO UUID  ВСЛУЧАЕ ЕЛСИ ID ПУСТОЙ ТО ЗАПОЛЯЕМ ПОЛЯ UUID

                    int ИндексДляСохранениеОрганизацииUUID= Курсор_ЗагружаетДанныеПриСозданииТабеля.getColumnIndex("id");

          Long СамДляСохранениеОрганизацииUUID = Курсор_ЗагружаетДанныеПриСозданииТабеля.getLong(ИндексДляСохранениеОрганизацииUUID);

              int ИндексДляСохранениеОрганизацииИмени= Курсор_ЗагружаетДанныеПриСозданииТабеля.getColumnIndex("name");

              ЗначениеДляЗаполенияСпинера  = Курсор_ЗагружаетДанныеПриСозданииТабеля.getString(ИндексДляСохранениеОрганизацииИмени);

                Log.d(this.getClass().getName(), " СамДляСохранениеОрганизацииUUID " + СамДляСохранениеОрганизацииUUID +" ЗначениеДляЗаполенияСпинера "  +ЗначениеДляЗаполенияСпинера);

//////TODO САМО ЗАПОЛНЕНИЕ НАЗВАНИЕМ ОРГАНИЗАЦИИ В AERRAYLIST  И HASPMAP
                if (СамДляСохранениеОрганизацииUUID>0 && ЗначениеДляЗаполенияСпинера!=null) {
///ЗАПОЛЯНЕМ ААРАЛИСТ
                    ////// todo для ЗАПОЛЕНИЕ АРАЙЛИСТА ЗАПОЛЕНИЕ ПЕРВОЕ И ВАЖНОЕ АРАЛИЛСИАТ
                    арайЛИстДанныеИзБазыДляЗАполенияСпинеровы.add(ЗначениеДляЗаполенияСпинера); /////ЗАПОЛЯНЕМ АРАЙЛИСТА ДЛЯ ОТОБРАЖЕНИЯ НАЗВАНИЕ ТАБЕЛЯ В АКТИВТИ
///ЗАПОЛЯНЕМ ХЭ
                    ////// TODO ДЛЯ ЗАПОЛЕНИЯ ХЭШМАПА ЗАПОЛЕЯНИМ ХЭШМАП ВЗАВИСИМОСТИ ОТ НАЗВАНИЯ ТАБЛИЦЫ
                    ХэшДанныеИзБазыДляЗАполенияСпинеровыОрганизация.put(String.valueOf(СамДляСохранениеОрганизацииUUID), ЗначениеДляЗаполенияСпинера);////ЗАПОЛЯЕМ  ДОПОЛНИТЕЛЬНО  ХЭШМАП ДЛЯ РАБОТЫ ВСТМВКИ В БАЗУ ДОПОЛНИТЕЛЬНО
             //TODO ПОСЛЕ УСТАВКИ  ДЕЛАЕМ NULL
                    СамДляСохранениеОрганизацииUUID=0l ;
                    /////////
                    ЗначениеДляЗаполенияСпинера=null;

                }

            }while (Курсор_ЗагружаетДанныеПриСозданииТабеля.moveToNext());

            Log.d(this.getClass().getName(), "  АрайЛИстДанныеИзБазыДляЗАполенияСпинеровы.size()  " + арайЛИстДанныеИзБазыДляЗАполенияСпинеровы.size()
                    + "  ХэшДанныеИзБазыДляЗАполенияСпинеровыОрганизация.size() " +ХэшДанныеИзБазыДляЗАполенияСпинеровыОрганизация.size());


            // TODO: 07.09.2021 close
            Курсор_ЗагружаетДанныеПриСозданииТабеля.close();


            ///todo Метод который узнает какой ВыбранаОрганизация самим пользователь СОРТИРУЕМ ЕГО ПО ЦИФРЕ 1 КТО ВЫБРАЛ ТОГО И СТАВИМ

            МетодКоторыйСортируетАрайЛистПоУсловияКакаяОрганизацияУжеВыбрана(арайЛИстДанныеИзБазыДляЗАполенияСпинеровы);


        }

        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
             // TODO: 01.09.2021 метод вызова
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
          this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }



















    protected void МетодКоторыйСортируетАрайЛистПоУсловияКакаяОрганизацияУжеВыбрана(ArrayList<String> арайЛИстДанныеИзБазыДляЗАполенияСпинеровы) {

        Cursor Курсор_ИщемВыбраннуюОрганизацию = null;
        ///
        Class_GRUD_SQL_Operations class_grud_sql_operationsКоторыйСортируетАрайЛистПоУсловияКакаяОрганизацияУжеВыбрана=new Class_GRUD_SQL_Operations(getApplicationContext());

        try{

//todo ИЩЕМ САМУ ОРГАНИЗАЦИИ

            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

            ///
            class_grud_sql_operationsКоторыйСортируетАрайЛистПоУсловияКакаяОрганизацияУжеВыбрана.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","organization");
            ///////
            class_grud_sql_operationsКоторыйСортируетАрайЛистПоУсловияКакаяОрганизацияУжеВыбрана.concurrentHashMapНабор.put("СтолбцыОбработки","name,chosen_organization");
            //
            class_grud_sql_operationsКоторыйСортируетАрайЛистПоУсловияКакаяОрганизацияУжеВыбрана.concurrentHashMapНабор.put("ФорматПосика","chosen_organization=? ");
                    ///"_id > ?   AND _id< ?"
                    //////
            class_grud_sql_operationsКоторыйСортируетАрайЛистПоУсловияКакаяОрганизацияУжеВыбрана.concurrentHashMapНабор.put("УсловиеПоиска1","1");
                    ///
              /*      class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска2","Удаленная");
                    ///
                    class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);
                    //
                    class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 .......

            ////TODO другие поля*/

            ///classGrudSqlOperations. concurrentHashMapНабор.put("ПоляГрупировки",null);
            ////
            //class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеГрупировки",null);
            ////
/*            class_grud_sql_operationsПолучениеИмяСистемы. concurrentHashMapНабор.put("УсловиеСортировки","date_update");*/
            ////
            /// class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеЛимита","1");
            ////

            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ

            Курсор_ИщемВыбраннуюОрганизацию=null;

            /////

            Курсор_ИщемВыбраннуюОрганизацию= (SQLiteCursor)  new Class_GRUD_SQL_Operations(getApplicationContext()).
                    new GetData(getApplicationContext()).getdata(class_grud_sql_operationsКоторыйСортируетАрайЛистПоУсловияКакаяОрганизацияУжеВыбрана.concurrentHashMapНабор,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabase);

            Log.d(this.getClass().getName(), "GetData "  +Курсор_ИщемВыбраннуюОрганизацию);


/*

            // TODO: 07.09.2021     _______________old

                        ////TODO ИЩЕМ ОРГАНИЗАЦИЮ КОТРОУЮ ВЫБРАЛ СОТРУДНИК УЖЕ ЗАХОДИЛ И ВЫБРАЛ НА АКТИВТИ цифра один ставитсья всегда каторую выбрали
            Курсор_ИщемВыбраннуюОрганизацию =  new Class_MODEL_synchronized(getApplicationContext()).КурсорУниверсальныйДляБазыДанных("organization", new String[]
                                {"name","chosen_organization"}, "chosen_organization=?",new String[] {"1"}, null, null,null, null);///"SELECT name  FROM MODIFITATION_Client WHERE name=?",НазваниеТаблицНаСервере

*/


        ///TODO САМА СОРТИРОВКА ПО ЦИФРЕ 1 НУ КТО ВЫБРАЛ ОРГАНИЗАЦИЮ ТОГО ПЕРВОГО И СТАВИМ

        if (Курсор_ИщемВыбраннуюОрганизацию.getCount()>0) {
            /////
            Log.d(this.getClass().getName(), "  Курсор_ИщемВыбраннуюОрганизацию  " +Курсор_ИщемВыбраннуюОрганизацию.getCount());

            ////
            Курсор_ИщемВыбраннуюОрганизацию.moveToFirst();
            ///

            String ОрганизацияРанееВыбранаяСотрудником = Курсор_ИщемВыбраннуюОрганизацию.getString(0);

            ///////
            String НомерОрганизацияРанееВыбранаяСотрудником= Курсор_ИщемВыбраннуюОрганизацию.getString(1);


            /////TODO СОБВСТВЕННО СОРТИРУЕМ ПО НАЙДЕНОМУ КЛЮЧУ

        int ИщемСвойМесяцПорядок= арайЛИстДанныеИзБазыДляЗАполенияСпинеровы.indexOf(ОрганизацияРанееВыбранаяСотрудником);

        /////TODo данной командой ставим месяц котроый наш всегда в начало Арайлиста SORT()
        Collections.swap(арайЛИстДанныеИзБазыДляЗАполенияСпинеровы,0,ИщемСвойМесяцПорядок);

        Log.d(this.getClass().getName(), "  АрайЛИстДанныеИзБазыДляЗАполенияСпинеровы  " + арайЛИстДанныеИзБазыДляЗАполенияСпинеровы.toString());

        ////TODO попытка удили предыдущего места ЛИШНЕЕ МЕСТО КОТОРЕ ОСВОБОДИЛОСЬ ПОСЛЕ КАК МЫ СОРТИРОВАЛИ
            арайЛИстДанныеИзБазыДляЗАполенияСпинеровы.remove( ИщемСвойМесяцПорядок);

        }

            //TODO КУРСОР ЗАКРЫВАЕМ
            Курсор_ИщемВыбраннуюОрганизацию.close();

    } catch (Exception e) {
        //  Block of code to handle errors
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        ///////
    }
    }











    protected void settheCurrentVersionoftheProgramVersion( ) {


        try{
            // TODO: 24.09.2024   Локальная Версия Программернр Обеспечения табель
            PackageInfo pInfo = context. getPackageManager().getPackageInfo(context. getPackageName(), 0);
            String version = pInfo.versionName;//Version Name
            Integer ЛокальнаяВерсияПО = pInfo.versionCode;
            textViewВерсияПрограммы.setText("Версия ПО"+": "+ЛокальнаяВерсияПО.toString());
            // TODO: 03.10.2023
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    " ЛокальнаяВерсияПО "+ЛокальнаяВерсияПО);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            ///////
        }
    }









    //TODO END CLASS
}/// конец  public class MainActivity_New_Tabely extends AppCompatActivity

