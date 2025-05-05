package com.dsy.dsu.Tabels.Peoples;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import com.dsy.dsu.BusinessLogicAll.Class_GRUD_SQL_Operations;
import com.dsy.dsu.BusinessLogicAll.DATE.Class_Generation_Data;
import com.dsy.dsu.BusinessLogicAll.VersionCurentTable;
import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;
import com.dsy.dsu.BusinessLogicAll.Class_Generation_UUID;
import com.dsy.dsu.BusinessLogicAll.Class_Generations_New_Customers_For_Tabels;
import com.dsy.dsu.CnangeServers.PUBLIC_CONTENT;
import com.dsy.dsu.BusinessLogicAll.SubClassGetPublicId;
import com.dsy.dsu.Hilt.Sqlitehilt.AppModuleSQLlite;
import com.dsy.dsu.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


import dagger.hilt.EntryPoints;
import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;



@AndroidEntryPoint
public class MainActivity_New_People extends AppCompatActivity implements DatePickerDialog.OnDateSetListener  {
    ////todo переменные для новго сотрдуника при создание на  активтик
    private Button КнопкаСозданиеНовогоСотрудника;
    private EditText ЗначениеФИОСозданиеСотрудника,  ЗначениеСНИЛССозданиеСотрудника;
    private TextView  ЗначениеДеньРожденияСозданиеСотрудника;
    private   String FullNameCFO;
    private  Long MainParentUUID;

    private  Long CurrenrsСhildUUID;
    private  Integer Position;
    private  Integer ГодТабелей;

    private  Integer МЕсяцТабелей;
    private String ИмесяцвИГодСразу;

    private  Configuration config;



    private Button КнопкаНазад;
    private   int DigitalNameCFO;
    private  LinkedHashMap<String,Integer> ЛистДляАдаптераСпинерОрганизацияСамоЗначениеIDДляЗаписи;
    private   Activity activity;

    private SQLiteDatabase sqLiteDatabase ;
    private Context КонтекстДляАктивтиСозданиеНовогоСотрудника;
    private  Spinner СпинерВыборОрганизацииПриСозданииНовогоСотрудника;/////спинеры для создание табеля
    private    String ПолученноеТекущееЗначениеСпинераОрганизация;
    private long РезультатВставкиНовогоТабеляЧерезКонтрейнерТаблицыФИО;
    private   int Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицыФИО;


    private PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =null;
    private   ProgressDialog progressDialog;
    private      ConstraintLayout constraintLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_create_new_customers);
        //TODO  ОЧИЩАЕМ ПАМТЬ
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        try{

       constraintLayout = (ConstraintLayout)  findViewById(R.id.constraintLayout);

        Log.d(this.getClass().getName(), " constraintLayout   "+constraintLayout);
///////TODO
            // TODO: 16.04.2025
            sqLiteDatabase = EntryPoints.get(getApplicationContext(), AppModuleSQLlite.class).getAppModuleSQLlite();
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " sqLiteDatabase " +sqLiteDatabase);

        activity=this;
        ////
            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT(getApplicationContext());
        /////todo данная настрока запрещает при запуке активти подскаваать клавиатуре вверх на компонеты eedittext
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
     //   getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION  );
        getSupportActionBar().hide(); ///скрывать тул бар
        /////todo данная настрока запрещает при запуке активти подскаваать клавиатуре вверх на компонеты eedittext
        КонтекстДляАктивтиСозданиеНовогоСотрудника=this;
        КнопкаСозданиеНовогоСотрудника = findViewById(R.id.КнопкаСозданиеНовогоТабеля);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        /////
        Log.d(this.getClass().getName(), "   ");
        // Locale locale = Locale.ROOT;
        Locale locale = new Locale("rus");
        Locale.setDefault(locale);
        config =
                getBaseContext().getResources().getConfiguration();
        config.setLocale(locale);
        createConfigurationContext(config);
        ///TODO разное

////TODO ИНИЗАЛИЗАУМЯ ОБЬЕКТОВ НА АКТИВИТИ
        ЗначениеФИОСозданиеСотрудника = findViewById(R.id.ЗначениеЦФОПриСозданииНовогоТабеля);
        ЗначениеДеньРожденияСозданиеСотрудника = findViewById(R.id.ЗначениеДепартаментаПриСоздаенииНовогоТабеля);
        ЗначениеСНИЛССозданиеСотрудника = findViewById(R.id.ЗначениеДатаСоздаваемогоТабеля);
        ЗначениеСНИЛССозданиеСотрудника = findViewById(R.id.ЗначениеДатаСоздаваемогоТабеля);
        //todo кнопка назад
        КнопкаНазад= findViewById(R.id.imageViewСтрелкаНазадНовыйСотрудник);
                СпинерВыборОрганизацииПриСозданииНовогоСотрудника= findViewById(R.id.значениеИзСпинераОрганизацияДляНовогоСотрудника);
            // TODO: 17.04.2023 Переменные из других Активти
            //todo пришили данные из преедедущего активти с названием табеля сСАМО ИМЯ И ЕГО UUID
            методGetVaeribaleCustomers();
// TODO: 17.04.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
//todo настройки
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        ///////
    }

    }

    @Override
    protected void onStart() {
        super.onStart();
        try{
            МетодСозданиеСпинеровОрганизации(Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков);
            МетодЗапускаКодаПоСозданиюНовогоСотрудникаДляДвухТаблицФиоиДатаТабеля();
            МетодПолучениеДатыРожденияЧерезКалендарь();
            МетодВозврещениеНаПредыдущуюАктивтиBACK();
            ///
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ///////
        }
    }













    protected void МетодСозданиеСпинеровОрганизации(CompletionService МенеджерПотоковВнутри) {
        
        // TODO: 24.03.2021 ЕслиВубличногоНЕтТоНАходим ЕГо
        
        ArrayList<String> ЛистДляАдаптераСпинерОрганизация = new ArrayList<>();


         ЛистДляАдаптераСпинерОрганизацияСамоЗначениеIDДляЗаписи = new LinkedHashMap<>();

        SQLiteCursor    Курсор_ИщемВсеОрганизации=null;
        //
        Class_GRUD_SQL_Operations class_grud_sql_operationsСозданиеСпинеровОрганизации;

        try{
            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
            ///
            class_grud_sql_operationsСозданиеСпинеровОрганизации=new Class_GRUD_SQL_Operations(getApplicationContext());

            ///
            class_grud_sql_operationsСозданиеСпинеровОрганизации.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","organization");
            ///////
            class_grud_sql_operationsСозданиеСпинеровОрганизации.concurrentHashMapНабор.put("СтолбцыОбработки","*");
            //
            class_grud_sql_operationsСозданиеСпинеровОрганизации.concurrentHashMapНабор.put("ФорматПосика"," name IS NOT NULL");
                    ///"_id > ?   AND _id< ?"
                    //////
/*
                    class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска1",finalПолученныйUUID);
                    ///
                    class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска2","Удаленная");
                    ///
                    class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);
                    //
                    class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 ....                  class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска1",finalПолученныйUUID);
*/
            ////TODO другие поля

            ///classGrudSqlOperations. concurrentHashMapНабор.put("ПоляГрупировки",null);
            ////
            //class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеГрупировки",null);
            ////
          //  class_grud_sql_operationsПолучениеИмяСистемы. concurrentHashMapНабор.put("УсловиеСортировки","date_update");
            ////
            /// class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеЛимита","1");
            ////

            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ

            Курсор_ИщемВсеОрганизации= (SQLiteCursor)  class_grud_sql_operationsСозданиеСпинеровОрганизации.
                    new GetData(getApplicationContext()).getdata(class_grud_sql_operationsСозданиеСпинеровОрганизации.concurrentHashMapНабор,
                    МенеджерПотоковВнутри,sqLiteDatabase);
            ////////////

            Log.d(this.getClass().getName(), "GetData " +Курсор_ИщемВсеОрганизации );

/*


// TODO: 07.09.2021    _old
                Курсор_ИщемВсеОрганизации =
                        new Class_MODEL_synchronized(getApplicationContext()).КурсорУниверсальныйДляБазыДанных("organization",
                                new String[]{"*"}, " name IS NOT NULL", null, null, null, null, null);//


*/

            // TODO: 07.09.2021  полученный результат

                if(Курсор_ИщемВсеОрганизации.getCount()>0){


                    Курсор_ИщемВсеОрганизации.moveToFirst();

                    ЛистДляАдаптераСпинерОрганизация=new ArrayList<>();

                 ЛистДляАдаптераСпинерОрганизация.add("") ;


// TODO: 07.09.2021 _old по данным
                    do{

                        Log.d(this.getClass().getName(), " Курсор_ИщемПУбличныйIDКогдаегоНетВстатике " + Курсор_ИщемВсеОрганизации.getCount());


                        int ПолощениеСамаОрганизация=Курсор_ИщемВсеОрганизации.getColumnIndex("name");

                        String          СамаОрганизация =Курсор_ИщемВсеОрганизации.getString(ПолощениеСамаОрганизация);

                        Log.d(this.getClass().getName(), "  СамаОрганизация" +  СамаОрганизация);

                        ЛистДляАдаптераСпинерОрганизация.add(СамаОрганизация) ;

                        // TODO: 02.11.2021   ВтораяЧасть ПолученияID ДЛЯВставка



                        int ПолощениеСамаОрганизацияIDДЛяЗаписи=Курсор_ИщемВсеОрганизации.getColumnIndex("id");

                        Integer         СамаОрганизацияIDЗаписи =Курсор_ИщемВсеОрганизации.getInt(ПолощениеСамаОрганизацияIDДЛяЗаписи);

                        Log.d(this.getClass().getName(), "  СамаОрганизацияIDЗаписи" +  СамаОрганизацияIDЗаписи);


                        ЛистДляАдаптераСпинерОрганизацияСамоЗначениеIDДляЗаписи.put(СамаОрганизация,СамаОрганизацияIDЗаписи);


                        Log.d(this.getClass().getName(), "  ЛистДляАдаптераСпинерОрганизацияСамоЗначениеIDДляЗаписи" +  ЛистДляАдаптераСпинерОрганизацияСамоЗначениеIDДляЗаписи.values());

                    } while (Курсор_ИщемВсеОрганизации.moveToNext());
                    ////

                }
            // TODO: 07.09.2021 exit
            Курсор_ИщемВсеОрганизации.close();





// Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> АдаптерДляСпинераОрганизация = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1,
                ЛистДляАдаптераСпинерОрганизация);
        // Определяем разметку для использования при выборе элемента
        АдаптерДляСпинераОрганизация.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        СпинерВыборОрганизацииПриСозданииНовогоСотрудника.setAdapter(АдаптерДляСпинераОрганизация);

        //
        СпинерВыборОрганизацииПриСозданииНовогоСотрудника.setHorizontalScrollBarEnabled(true);
        ////что быврали
        СпинерВыборОрганизацииПриСозданииНовогоСотрудника.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //////СПИНЕР ДЕПАРТАМЕНТ
                if (position>0) {///ставим ограничкния если выбрано не 0 позиция то запонимаеним

                    ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                    ((TextView) parent.getChildAt(0)).setTextSize(12);
                    ((TextView) parent.getChildAt(0)).setTextSize(12);
                    ((TextView) parent.getChildAt(0)).setTypeface(Typeface.DEFAULT_BOLD);
                    ((TextView) parent.getChildAt(0)).setHint("Выберете Организацию".toUpperCase(Locale.ROOT));
                    ((TextView) parent.getChildAt(0)).setHintTextColor(Color.parseColor("#675757"));
                    ((TextView) parent.getChildAt(0)).setBackgroundResource(R.drawable.textlines);
                    ((TextView) parent.getChildAt(0)).setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

                    ПолученноеТекущееЗначениеСпинераОрганизация = parent.getItemAtPosition(position).toString();

                    Log.d(this.getClass().getName(), "ПолученноеТекущееЗначениеСпинераОрганизация " + ПолученноеТекущееЗначениеСпинераОрганизация);
                        /*Toast toast = Toast.makeText(getApplicationContext(),
                                "Ваш выбор Раздел : " + ПолученноеЗначениеИзСпинераРаздел + " " + position, Toast.LENGTH_SHORT);
                        toast.show();*/

                }else if (position==0){
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                    ((TextView) parent.getChildAt(0)).setBackgroundResource(R.drawable.textlines);
                    ((TextView) parent.getChildAt(0)).setTextSize(12);
                    ((TextView) parent.getChildAt(0)).setTypeface(Typeface.DEFAULT_BOLD);
                    ((TextView) parent.getChildAt(0)).setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                    ((TextView) parent.getChildAt(0)).setHint("Выберете Организацию".toUpperCase(Locale.ROOT));
                    ((TextView) parent.getChildAt(0)).setHintTextColor(Color.parseColor("#675757"));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

            ///////
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }















    @Override
    protected void onDestroy() {
        super.onDestroy();

        //////TODO  данный код срабатывает когда произошда ошивка в базе

    }






    @Override
    protected void onPause() {
        super.onPause();
        try {

          ///  МетодЗапускаЛокальнойСинхронизации();
            ///////
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                   new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    protected void методGetVaeribaleCustomers() {
        try{
            Intent ИнтентActivityNewCustomer = getIntent();
           Bundle bundleActivityNewCustomer =      ИнтентActivityNewCustomer.getExtras();
            if (bundleActivityNewCustomer!=null) {
                MainParentUUID=    bundleActivityNewCustomer.getLong("MainParentUUID", 0);
                Position=    bundleActivityNewCustomer.getInt("Position", 0);
                ГодТабелей=  bundleActivityNewCustomer.getInt("ГодТабелей", 0);
                МЕсяцТабелей=  bundleActivityNewCustomer.getInt("МЕсяцТабелей",0);
                DigitalNameCFO=   bundleActivityNewCustomer.getInt("DigitalNameCFO", 0);
                FullNameCFO=  bundleActivityNewCustomer.getString("FullNameCFO", "");
                ИмесяцвИГодСразу= bundleActivityNewCustomer.getString("ИмесяцвИГодСразу", "");
                CurrenrsСhildUUID= bundleActivityNewCustomer.getLong("CurrenrsСhildUUID", 0l);
            }
// TODO: 17.04.2023  //////////20.15
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " bundleActivityNewCustomer " +bundleActivityNewCustomer);
        } catch (Exception e) {
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }









///todo метод который возврящаем с текущего активити на предыдущий
///todo метод получение ДАТЫ РОЖДЕНИЯ ИЗ  КАЛЕНЛАРЯ
private void МетодВозврещениеНаПредыдущуюАктивтиBACK() {
    КнопкаНазад.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(this.getClass().getName(), " кликнем для созданни новго сотрдника при нажатии  ");
            ///todo код которыц возврящет предыдущий актвитики кнопка back
            //TODO ПОСЛЕ УСПЕШНОЙ СОЗДАНИЕ НОВОГО СОТРУДНИКА ПЕРЕХОДИМ В ТАБЕЛЯ

            методBackActivityListPeoples();
            //////

        }
    });
}





///todo метод получение ДАТЫ РОЖДЕНИЯ ИЗ  КАЛЕНЛАРЯ
    private void МетодПолучениеДатыРожденияЧерезКалендарь() {
        ЗначениеДеньРожденияСозданиеСотрудника.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(this.getClass().getName(), " кликнем для созданни новго сотрдника при нажатии  ");
                МетодВытаскиваемИзКалендаряДиалогаКалендаряДаты();
            }
        });
    }
    ///поймать ошибку
    private void МетодВытаскиваемИзКалендаряДиалогаКалендаряДаты() {///////метод создание календяря даты
/////TODO тут визуализикуеться КАЛЕНДАРЬ
        DatePickerDialog ДатаДляКалендаря=new DatePickerDialog(this, (DatePickerDialog.OnDateSetListener) this,
                GregorianCalendar.getInstance().get(Calendar.YEAR),
                GregorianCalendar.getInstance().get(Calendar.MONTH),
                GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH));
        ДатаДляКалендаря.setIcon(R.drawable.icon_dsu1_new_customer7 );
        ДатаДляКалендаря.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        StringBuffer БуферПолученаяДатаРожденияСотрудника=new StringBuffer();
        //todo make offset
        switch (month){
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                month=month+1;
                break;
                /////todo яныварь
            case 0:
                month=month+1;
                break;
        }
//todo выравниваем длину месяца
        String МесяцФинал=String.valueOf(month);
        if (МесяцФинал.length()==1) {
            МесяцФинал = "0" + МесяцФинал;
        }
        ////todo  выравниваем длину день
        String ДеньФинал=String.valueOf(dayOfMonth);
        if (ДеньФинал.length()==1) {
            ДеньФинал = "0" + ДеньФинал;
        }
        БуферПолученаяДатаРожденияСотрудника.append(ДеньФинал).append(".").append(МесяцФинал).append(".").append(year);
        Log.d(this.getClass().getName(), " stringBuffer  "+ БуферПолученаяДатаРожденияСотрудника.toString());
        //TODO ЗАПОЛНЯЕМ
        ЗначениеДеньРожденияСозданиеСотрудника.setText(БуферПолученаяДатаРожденияСотрудника.toString());
    }














    ///todo данный метод начальный для создание нового сотрудника с кнопки
       void МетодЗапускаКодаПоСозданиюНовогоСотрудникаДляДвухТаблицФиоиДатаТабеля()  throws  InterruptedException{
           final Long[] РезультатВставкиDataTabels = {0l};
           try{
            КнопкаСозданиеНовогоСотрудника.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(this.getClass().getName(), " ЗначениеФИОСозданиеСотрудника  "+ ЗначениеФИОСозданиеСотрудника+
                            " ЗначениеДеньРожденияСозданиеСотрудника  " + ЗначениеДеньРожденияСозданиеСотрудника +
                            " ЗначениеСНИЛССозданиеСотрудника  " +ЗначениеСНИЛССозданиеСотрудника);
                    // TODO: 09.08.2022 создаем новго сотрудника
                    int ТекущаяПозиция=СпинерВыборОрганизацииПриСозданииНовогоСотрудника.getSelectedItemPosition();
                    Integer ПубличноеID=    new SubClassGetPublicId().ПубличныйID(getApplicationContext());
                    ПолученноеТекущееЗначениеСпинераОрганизация=( СпинерВыборОрганизацииПриСозданииНовогоСотрудника.getItemAtPosition(ТекущаяПозиция).toString());
                    ReentrantLock reentrantLock=new ReentrantLock();
                    Condition condition= reentrantLock.newCondition();
                    Log.d(this.getClass().getName(), " ПолученноеТекущееЗначениеСпинераОрганизация  "+ ПолученноеТекущееЗначениеСпинераОрганизация);


                    if (ЗначениеФИОСозданиеСотрудника.length() > 4
                            && ЗначениеДеньРожденияСозданиеСотрудника.length() > 4
                            && ЗначениеСНИЛССозданиеСотрудника.length()==11 &&
                            ТекущаяПозиция!=0 &&
                            СпинерВыборОрганизацииПриСозданииНовогоСотрудника.getItemAtPosition(ТекущаяПозиция).toString()!=null &&
                            СпинерВыборОрганизацииПриСозданииНовогоСотрудника.getItemAtPosition(ТекущаяПозиция).toString()!="") {
                        // TODO: 17.04.2023 наинаем встаавку новаого сотрудинка Single

                        Completable.fromAction(new Action() {
                                    @Override
                                    public void run() throws Throwable {
                                        Long РезультатВставкивТаблицуФИО = 0l;
                                        final Long  РезультатВставкивТаблицуDataTabels = 0l;
/////TODO перед созданием определяем не пустые ли значения
                                        //TODO внешний вид
                                        activity.runOnUiThread(()->{
                                            progressDialog=new ProgressDialog(activity  );
                                            progressDialog.setCancelable(false);
                                            progressDialog.setTitle("Создание сотрудника");
                                            progressDialog.setMessage("в процессе...");
                                            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                                            progressDialog.setMax(1);
                                            progressDialog.setProgress(0);
                                            progressDialog.setIndeterminate(true);
                                            progressDialog.show();
                                            constraintLayout.setClickable(false);
                                        });

                                        reentrantLock.lock();
                                        Long   UUIDGenetetorNewCustoner= (Long) new Class_Generation_UUID(getApplicationContext()).МетодГенерацииUUID();
                                        // TODO: 23.09.2021  получение из даты месяц и год
                                        Log.d(this.getClass().getName(), " ИмесяцвИГодСразу  " + ИмесяцвИГодСразу);
                                        // TODO: 22.09.2021 обработка ТАБЛИЦА ФИО

                                        РезультатВставкивТаблицуФИО = new Class_Generator_New_Customer_In_Table_Fio().методВставкиВТАблицуФИО(ТекущаяПозиция,UUIDGenetetorNewCustoner,ПубличноеID);
                                        // TODO: 22.09.2021 ПОСЛЕ ДВУХ ОБРАБОТКАХ  ФИО И ДАТА_ТАБЕЛЬ ПЕРЕРХОДИМ НА ДРГОЕ АКТИВТИ
                                        Log.d(this.getClass().getName(), " РезультатВставкивТаблицуФИО  " + РезультатВставкивТаблицуФИО);

                                        if (РезультатВставкивТаблицуФИО >0) {
                                            condition.await(200, TimeUnit.MILLISECONDS);
                                            condition.signal();
                                        }

                                        if (РезультатВставкивТаблицуФИО >0) {
                                            // TODO: 22.09.2021  ТАБЛИЦА ДАТА_ТАБЕЛЯ
                                            РезультатВставкиDataTabels[0] = new Class_Generator_New_Customer_In_Table_Data_Tables().
                                                    методСозданиеНовогоСотрудникаDataTabels(UUIDGenetetorNewCustoner,
                                                            МЕсяцТабелей,
                                                            ГодТабелей,ПубличноеID);
                                            // TODO: 22.09.2021 ПОСЛЕ ДВУХ ОБРАБОТКАХ  ФИО И ДАТА_ТАБЕЛЬ ПЕРЕРХОДИМ НА ДРГОЕ АКТИВТИ
                                            Log.d(this.getClass().getName(), " РезультатВставкивТаблицуDataTabels  "+РезультатВставкивТаблицуDataTabels);
                                            if (  РезультатВставкиDataTabels[0] >0) {
                                                condition.await(200, TimeUnit.MILLISECONDS);
                                                condition.signal();
                                            }else {
                                                getApplicationContext().getMainExecutor().execute(()->{
                                                    Snackbar.make(v, "Сотрунидник не был создан !!! ", Snackbar.LENGTH_LONG).show();
                                                    Log.d(this.getClass().getName(), " РезультатВставкиDataTabels[0]  "+РезультатВставкиDataTabels[0]);
                                                });
                                            }

                                        }else {
                                            getApplicationContext().getMainExecutor().execute(()->{
                                                Snackbar.make(v, "Сотрунидник не был создан !!!", Snackbar.LENGTH_LONG).show();
                                                Log.d(this.getClass().getName(), " РезультатВставкиDataTabels[0]  "+РезультатВставкиDataTabels[0]);
                                            });
                                        }
                                        Log.d(this.getClass().getName(), " РезультатВставкиDataTabels[0]  "+РезультатВставкиDataTabels[0]);

                                        reentrantLock.unlock();

                                    }
                                })
                                .subscribeOn(Schedulers.single())
                                .observeOn(AndroidSchedulers.mainThread()).
                                doOnComplete(new Action() {
                                    @Override
                                    public void run() throws Throwable {
                                        constraintLayout.forceLayout();
                                        progressDialog.setProgress(1);

                                        if (РезультатВставкиDataTabels[0]>0) {
                                            // TODO: 17.04.2023 переходим на обратно в активити выбор сотрудников
                                            if (progressDialog!=null) {
                                                progressDialog.setIndeterminate(false);
                                                progressDialog.dismiss();
                                                constraintLayout.setClickable(true);
                                            }

                                            методBackActivityListPeoples();
                                        }
                                        // TODO: 17.04.2023  //////////20.15
                                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                    }
                                })
                                .doOnError(new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Throwable {
                                        ///метод запись ошибок в таблицу
                                        Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        new RecordNewErros(getApplicationContext()).recordnewerror(throwable.toString(), this.getClass().getName(),
                                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    }
                                }).subscribe();

                    } else {
                        activity.runOnUiThread(()->{
                            Snackbar.make(v, "Заполните данные (СНИЛС от 11 знаков) ", Snackbar.LENGTH_LONG).show();
                            Log.i(this.getClass().getName(), " Не все поля заполены (снилс от 10 знаков) ");
                        });

                    }
                }
            });
       } catch (Exception e) {
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

// TODO: 22.09.2021  ОБРАБОТКА ТАБЛИЦЫ ДАТА_ТАБЕЛЯ
    @NotNull
    private Cursor МетодПроверяетПустойЛиТабельПервыйЗапускТабеляЧтоДелатьОбновлятьИлиВставлять()
            throws ExecutionException, InterruptedException, TimeoutException {
        //
        Class_GRUD_SQL_Operations class_grud_sql_operationsПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем;
        SQLiteCursor Курсор_КоторыйПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем =null;
        try{


        //todo табель еще есть м ыв уже сущетсвещющеуй табель не всталяем  а обнолвяем
////TODO КУРСОР ПРОВЕЯЕТ ПЕРВЫЙ ЭТО ЗАПУСК ИЛИ НЕТ

            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

            ///
            class_grud_sql_operationsПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем=
                    new Class_GRUD_SQL_Operations(getApplicationContext());

            ///
            class_grud_sql_operationsПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем.
                    concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","tabels");
            ///////
            class_grud_sql_operationsПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем.
                    concurrentHashMapНабор.put("СтолбцыОбработки","fio");
            //
            class_grud_sql_operationsПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем.
                    concurrentHashMapНабор.put("ФорматПосика","uuid=? ");
                    ///"_id > ?   AND _id< ?"
                    //////
            class_grud_sql_operationsПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем.
                    concurrentHashMapНабор.put("УсловиеПоиска1",MainParentUUID);
                    ///
        /*            class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска2","Удаленная");
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
      /*      class_grud_sql_operationsПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем.
                    concurrentHashMapНабор.put("УсловиеСортировки","date_update");*/
            ////
            /// class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеЛимита","1");
            ////

            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ

            //
       Курсор_КоторыйПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем =null;

   /////
            Курсор_КоторыйПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем
                    = (SQLiteCursor)  class_grud_sql_operationsПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем.
                    new GetData(getApplicationContext()).getdata(class_grud_sql_operationsПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем.
                            concurrentHashMapНабор,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков
                    ,sqLiteDatabase);

            Log.d(this.getClass().getName(), "GetData "  +Курсор_КоторыйПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем);




/*

            // TODO: 07.09.2021   _old
 Курсор_КоторыйПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем =
                new Class_MODEL_synchronized(this).КурсорУниверсальныйДляБазыДанных("tabels",
                        new String[]{"fio"}, "uuid=?", new String[]{УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель}, null, null, null, null);//"SuccessLogin", "date_update","id=","1",null,null,null,null
        ///TODO УДАЛЕМ ПАМЯТЬ*/


//todo определяем есть uuid в строчке или нет
        Log.d(this.getClass().getName(), "Курсор_КоторыйПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем " +
                Курсор_КоторыйПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем.getCount());
        /////s



    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

    }

        return Курсор_КоторыйПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем;
    }

    ////////todo































    //TODO ПОСЛЕ УСПЕШНОЙ СОЗДАНИЕ НОВОГО СОТРУДНИКА ВОЗВРАЩАЕМСЯ ОБРАТНО В ТАБЕЛЬ КУДА ДОБАВЛЯЛИ СОТРУЛНИКА


    private void методBackActivityListPeoples() {
        try{
            Intent ИнтентBackActivityListPeoples = new Intent();
            ИнтентBackActivityListPeoples.setClass(this, MainActivity_List_Peoples.class);
            Bundle dataBackActivityListPeoples=new Bundle();
            dataBackActivityListPeoples.putLong("MainParentUUID", MainParentUUID);
            dataBackActivityListPeoples.putInt("Position",    Position);
            dataBackActivityListPeoples.putInt("ГодТабелей",     ГодТабелей);
            dataBackActivityListPeoples.putInt("МЕсяцТабелей", МЕсяцТабелей);
            dataBackActivityListPeoples.putInt("DigitalNameCFO",  DigitalNameCFO);
            dataBackActivityListPeoples.putString("FullNameCFO", FullNameCFO);
            dataBackActivityListPeoples.putString("ИмесяцвИГодСразу",    ИмесяцвИГодСразу);
            dataBackActivityListPeoples.putLong("CurrenrsСhildUUID",  CurrenrsСhildUUID);
            ИнтентBackActivityListPeoples.putExtras(dataBackActivityListPeoples);
            startActivity(ИнтентBackActivityListPeoples);
        // TODO: 17.04.2023  //////////20.15
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }





    ///todo сообщение на активти создание новго сотрудника спрашиваем нужно ли создать
    @UiThread
    protected void СообщениеСообщаетОСоздаенииНовогоСотрудника(String ШабкаДиалога, final String СообщениеДиалога, boolean статус) {
        ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ
        int ФлагЗнака;
        if (статус) {
            ФлагЗнака = R.drawable.icon_dsu1_new_customer_success;
        } else {
            ФлагЗнака = R.drawable.icon_dsu1_new_customer_error;
        }

        try {
//////сам вид
            final AlertDialog alertDialog = new MaterialAlertDialogBuilder(КонтекстДляАктивтиСозданиеНовогоСотрудника)
                    .setTitle(ШабкаДиалога)
                    .setMessage(СообщениеДиалога)
                    .setPositiveButton("ОК", null)
                    .setIcon(ФлагЗнака)
                    .show();
/////////кнопка
            final Button MessageBoxUpdateСоздатьТабель = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            MessageBoxUpdateСоздатьТабель.setOnClickListener(new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) {
                    //удаляем с экрана Диалог
                    alertDialog.dismiss();
                    Log.d(this.getClass().getName(), "  ФИНАЛ создание нового сотрудника ");

                    if (статус) {
                        ///todo после как мы либо создали новогосо остружника или обновли его в табел то обнуляем



                        //todo обнуляем ПОСЛЕ ВСТАВКИ НОВГО СОТРУДНИКА

                        ЗначениеФИОСозданиеСотрудника=null;

                        ЗначениеДеньРожденияСозданиеСотрудника=null;

                        ЗначениеСНИЛССозданиеСотрудника=null;

///TODO метод запуска формы после вставки
                        //TODO ПОСЛЕ УСПЕШНОЙ СОЗДАНИЕ НОВОГО СОТРУДНИКА ПЕРЕХОДИМ В ТАБЕЛЯ



                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    /////////todo проверика подключение к wi fi












































    // TODO: 22.09.2021   KLASS GENERATOR NEW CUSTOMERS  IN TABLE FIO


    class  Class_Generator_New_Customer_In_Table_Fio   {
        /////////
        public Class_Generator_New_Customer_In_Table_Fio() {


        }

        // TODO: 22.09.2021   обработка ТАБЛИЦЫ ФИО



        // TODO: 22.09.2021  ЗАПИСЬ НОВОГО СОТДУНИКА ПЕРВОЕ  ДЕЙСТИЕ ЗАПИСЬВ ТАБЛИЦУ ФИО



      protected Long методВставкиВТАблицуФИО(@NotNull  int ТекущееЗначение,
                                             @NotNull Long   UUIDGenetetorNewCustoner
                                             ,@NotNull Integer ПубличноеID) throws InterruptedException {

            Long РезультаВставкиВТАблицуФИО=0l;
            try {
                ContentValues АдаптерДляСозданиеНовогоСотрудаТАблицаФИО = new ContentValues();////контрейнер для нового табеля
                String НазваниеФИО=ЗначениеФИОСозданиеСотрудника.getText().toString();
                String ЗначениеДеньРождения=ЗначениеДеньРожденияСозданиеСотрудника.getText().toString();
                Object  ПолученныйСНИЛСНовогоСотрудникаПереход=ЗначениеСНИЛССозданиеСотрудника.getText().toString().replaceAll("[^0-9]","").trim();
                Long ПолученныйСНИЛСНовогоСотрудника=Long.parseLong(ПолученныйСНИЛСНовогоСотрудникаПереход.toString());
                АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("name",НазваниеФИО);
        Integer ЕслиПробел=        НазваниеФИО.indexOf(" ");
                Log.w(getApplicationContext().getClass().getName(), "    ЕслиПробел    " +ЕслиПробел);
                if (ЕслиПробел>=0) {
                    int КоличествоПробелов = НазваниеФИО.length() - НазваниеФИО.replace(" ", "").length();
                    Log.w(getApplicationContext().getClass().getName(), "    occurrencesCount    " +КоличествоПробелов);
                    String s1[]=НазваниеФИО.split("\\s+");
                    if (КоличествоПробелов==1) {
                        if (s1[0]!=null) {
                            АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("f",s1[0]);
                        }
                        if (s1[1]!=null) {
                            АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("n",s1[1]);
                        }

                    }else {

                        if (КоличествоПробелов>=2) {
                            /////
                            if (s1[0]!=null) {
                                АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("f",s1[0]);
                            }
                            if (s1[1]!=null) {
                                АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("n",s1[1]);
                            }

                            if (s1[2]!=null) {
                                АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("o",s1[2]);
                            }
                        }
                    }
                }
                Log.d(this.getClass().getName(), "  АдаптерДляСозданиеНовогоСотрудаТАблицаФИО " + АдаптерДляСозданиеНовогоСотрудаТАблицаФИО);
                Long РезультатВычисляемВреисюДанных =
                        new VersionCurentTable(getApplicationContext()).upVersionCurentTable(    "fio"  );
                АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("current_table",РезультатВычисляемВреисюДанных);
                АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("BirthDate",ЗначениеДеньРождения);
                АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("snils",ПолученныйСНИЛСНовогоСотрудника);
                // TODO: 01.11.2021  ПРОВЛДИМ АНАЛИЗ НЕТ ЛИ СЛУЧАЙНО СОЗДАВАЕМОГО СОТРУДНИКА В ТАБЛИЦЕ ФИО
                Class_GRUD_SQL_Operations class_grud_sql_operationsИщемВТАблицеФИОНЕтЛИСлучайноТАковожеСотрудника=new Class_GRUD_SQL_Operations( getApplicationContext());
                class_grud_sql_operationsИщемВТАблицеФИОНЕтЛИСлучайноТАковожеСотрудника.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы", "fio");
                class_grud_sql_operationsИщемВТАблицеФИОНЕтЛИСлучайноТАковожеСотрудника.concurrentHashMapНабор.put("СтолбцыОбработки", "snils");
                class_grud_sql_operationsИщемВТАблицеФИОНЕтЛИСлучайноТАковожеСотрудника.concurrentHashMapНабор.put("ФорматПосика",
                        " snils=?  ");
                ///"_id > ?   AND _id< ?"
                //////
                class_grud_sql_operationsИщемВТАблицеФИОНЕтЛИСлучайноТАковожеСотрудника.concurrentHashMapНабор.put("УсловиеПоиска1",ПолученныйСНИЛСНовогоСотрудника);
                class_grud_sql_operationsИщемВТАблицеФИОНЕтЛИСлучайноТАковожеСотрудника.concurrentHashMapНабор.put("УсловиеСортировки", "date_update DESC");
                class_grud_sql_operationsИщемВТАблицеФИОНЕтЛИСлучайноТАковожеСотрудника.concurrentHashMapНабор.put("УсловиеЛимита", "1");
                SQLiteCursor     Курсор_ИщемЕслиТАкойСнилсУже = (SQLiteCursor) class_grud_sql_operationsИщемВТАблицеФИОНЕтЛИСлучайноТАковожеСотрудника.
                        new GetData(getApplicationContext()).getdata(class_grud_sql_operationsИщемВТАблицеФИОНЕтЛИСлучайноТАковожеСотрудника.concurrentHashMapНабор,
                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков, sqLiteDatabase);
                Log.d(this.getClass().getName(), "Курсор_ИщемЕслиТАкойСнилсУже " + Курсор_ИщемЕслиТАкойСнилсУже);
                // TODO: 01.11.2021
                if( Курсор_ИщемЕслиТАкойСнилсУже.getCount()==0){
                АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("uuid",UUIDGenetetorNewCustoner);
                String ДатаПРиСозданииНовогоСотрудника=null;
                String СгенерированованныйДатаДляДаннойОперации=     new Class_Generation_Data(getApplicationContext()).ГлавнаяДатаИВремяОперацийСБазойДанных();
                ДатаПРиСозданииНовогоСотрудника = СгенерированованныйДатаДляДаннойОперации;
                Log.d(this.getClass().getName(), " ДатаПРиСозданииНовогоСотрудника" + ДатаПРиСозданииНовогоСотрудника);
                АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("date_update",ДатаПРиСозданииНовогоСотрудника);

                if (DigitalNameCFO >0) {
                Integer ПолученныйIDОрганизации=  (Integer)  ЛистДляАдаптераСпинерОрганизацияСамоЗначениеIDДляЗаписи.get(ПолученноеТекущееЗначениеСпинераОрганизация);
                    Log.d(this.getClass().getName(), "ПолученныйIDОрганизации "+ПолученныйIDОрганизации   + " ТекущееЗначение " +ТекущееЗначение);
                    АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("current_organization",ПолученныйIDОрганизации); ///1
                    // TODO: 22.04.2021  srart JOBschedele
                    Log.d(this.getClass().getName(), "ПолученноеТекущееЗначениеСпинераОрганизация "+ПолученноеТекущееЗначениеСпинераОрганизация +
                            "  ПолученныйIDОрганизации " +ПолученныйIDОрганизации);
                        АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("user_update", ПубличноеID);
                    final int[] ТекущуюОрганизацию = {0};
                    final Cursor[] Курсор_ИщемТекущуюОрганизациюКоторуюВыбраСОтрудник = {null};
                    Log.d(this.getClass().getName(),"ТекущуюОрганизацию[0] " + ТекущуюОрганизацию[0] );
                    //// TODO  СамаВставка нового сотрудника в новый табель
                }else{
                    Log.e(this.getClass().getName(), " нет данных из предцдуещго табеля");
                }
                // TODO: 23.09.2021  повышаем верисю таблицы фио
                // TODO: 08.092021  метод после заполения данными
                РезультаВставкиВТАблицуФИО=       new Class_Generations_New_Customers_For_Tabels(getApplicationContext()).
                        МетодЗаписиСозданогоСотрудникаВБазуПоТаблицы_ФИО(АдаптерДляСозданиеНовогоСотрудаТАблицаФИО,activity);
                Log.w(this.getClass().getName(), " РезультаВставкиВТАблицуФИО  "+РезультаВставкиВТАблицуФИО);
                }else{
                    Toast.makeText(getApplicationContext(), "Ошибка данный сотрудник уже есть в таблице СНИЛС : "+ПолученныйСНИЛСНовогоСотрудника , Toast.LENGTH_LONG).show();
                }
//TODO ОКОНЧИАЕМ ВСТАВКУ ДАННЫХ
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  РезультаВставкиВТАблицуФИО;

        }












        // TODO: 22.09.2021  МЕТОД НЕПОСТРЕДВСТЕННОЙ ЗАПИСИ ДАННЫХ В ТАБЛИЦУ ФИО




    }

// TODO: 22.09.2021








































































    // TODO: 22.09.2021   KLASS GENERATOR NEW CUSTOMERS  IN TABLE data_tabels
    // TODO: 22.09.2021   KLASS GENERATOR NEW CUSTOMERS  IN TABLE data_tabels
    // TODO: 22.09.2021   KLASS GENERATOR NEW CUSTOMERS  IN TABLE data_tabels


    class   Class_Generator_New_Customer_In_Table_Data_Tables   {
        ///////


        public Class_Generator_New_Customer_In_Table_Data_Tables() {


        }

    // TODO: 22.09.2021  ТАБЛИЦА ДАТА_ТАБЕЛЬ







    // TODO: 22.09.2021  ЗАПИСЬ НОВОГО СОТДУНИКА ВТОРОЕ ДЕЙСТИЕ ЗАПИСЬВ ТАБЛИЦУ ДАТА_ТАБЕЛЯ





    //TODO метод записи нового сотрудника в базу
   protected Long методСозданиеНовогоСотрудникаDataTabels(@NotNull  Long UUIDGenetetorNewCustoner,
                                                          int ГодПриВставкеНовогоСотрудника,
                                                          int  МЕсяцПриВставкеНовогоСотрудника,
                                                          @NotNull Integer ПубличноеID) throws InterruptedException {


        Long РезультатВставкиВтаблицу_Дата_табеля=0l;
        try {
            ContentValues  АдаптерДляСозданиеНовогоСотрудаТАблицаТабель=new ContentValues();
            АдаптерДляСозданиеНовогоСотрудаТАблицаТабель.put("fio",UUIDGenetetorNewCustoner);
            АдаптерДляСозданиеНовогоСотрудаТАблицаТабель.put("uuid_tabel",MainParentUUID);
        //    АдаптерДляСозданиеНовогоСотрудаТАблицаТабель.putNull("_id");
            АдаптерДляСозданиеНовогоСотрудаТАблицаТабель.put("status_carried_out", "False");
            Long РезультатПовышаемВреисюДанныхТаблицы_Дата_Табеля =
                    new VersionCurentTable(getApplicationContext()).upVersionCurentTable(    "data_tabels" );
            // TODO: 23.09.2021  повышаем верисю таблицы фио
            АдаптерДляСозданиеНовогоСотрудаТАблицаТабель.put("current_table",РезультатПовышаемВреисюДанныхТаблицы_Дата_Табеля);


            String СгенерированованныйДатаДляДаннойОперации=     new Class_Generation_Data(getApplicationContext()).ГлавнаяДатаИВремяОперацийСБазойДанных();
            String      ДатаПРиСозданииНовогоСотрудника = СгенерированованныйДатаДляДаннойОперации;
            Log.d(this.getClass().getName(), " ДатаПРиСозданииНовогоСотрудника" + ДатаПРиСозданииНовогоСотрудника);
            АдаптерДляСозданиеНовогоСотрудаТАблицаТабель.put("date_update",ДатаПРиСозданииНовогоСотрудника);
            // TODO: 23.09.2021
            Log.d(this.getClass().getName(), " ДатаПРиСозданииНовогоСотрудника" + ДатаПРиСозданииНовогоСотрудника);

          Long UUidGeneratorDataTabels =  (Long)  new Class_Generation_UUID(getApplicationContext()).МетодГенерацииUUID();
            АдаптерДляСозданиеНовогоСотрудаТАблицаТабель.put("uuid",UUidGeneratorDataTabels);
                // TODO: 24.03.2021 ЕслиВубличногоНЕтТоНАходим ЕГо

                final int[] ТекущуюОрганизацию = {0};
                final Cursor[] Курсор_ИщемТекущуюОрганизациюКоторуюВыбраСОтрудник = {null};
            // TODO: 23.09.2021  повышаем верисю таблицы фио
            АдаптерДляСозданиеНовогоСотрудаТАблицаТабель.put("status_send", " ");
            АдаптерДляСозданиеНовогоСотрудаТАблицаТабель.put("user_update", ПубличноеID);
            Log.d(this.getClass().getName(),"ТекущуюОрганизацию[0] " + ТекущуюОрганизацию[0] );
            // TODO: 08.09.2021  метод после заполения данными
            РезультатВставкиВтаблицу_Дата_табеля=   new Class_Generations_New_Customers_For_Tabels(getApplicationContext()).
                    МетодЗаписиСозданогоСотрудникаВБазуПоТаблицы_Дата_Табеля(
                    АдаптерДляСозданиеНовогоСотрудаТАблицаТабель
                    ,activity,
                    ГодПриВставкеНовогоСотрудника,
                    МЕсяцПриВставкеНовогоСотрудника, UUidGeneratorDataTabels);/////TODO НОВЫЙ НУЖЕН НЕ ДЛЯ ВСТАВКИ А ДЛЯ ВТОРОГО ДЕЙСТВИЯ ЗАПОЛЕНИЯ ВЫХОДНЫМИ ЕСЛИ НВ НАСТРОЙКАХ ЕСТЬ КАКАЯ ФУЕНКЦИЯ И ОНА ВКЛЮЧЕН

            Log.e(this.getClass().getName(), " РезультатВставкиВтаблицу_Дата_табеля" + РезультатВставкиВтаблицу_Дата_табеля);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  РезультатВставкиВтаблицу_Дата_табеля;

    }

    // TODO: 22.09.2021   метод записи дата_табельс



    }

}