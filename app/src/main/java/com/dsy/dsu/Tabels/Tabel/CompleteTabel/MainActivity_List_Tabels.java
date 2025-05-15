package com.dsy.dsu.Tabels.Tabel.CompleteTabel;

import static java.util.Locale.setDefault;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.dsy.dsu.BusinessLogicAll.CoreBinessLogics.CoreBinessLogics;
import com.dsy.dsu.BusinessLogicAll.Class_GRUD_SQL_Operations;
import com.dsy.dsu.CnangeServers.BinessLogicPublicContent;
import com.dsy.dsu.Dashboard.Model.bl_launchFragmentSettingsandDashbord.LaunchActivityDashboard;
import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;
import com.dsy.dsu.BusinessLogicAll.DATE.SubClassCursorLoader;
import com.dsy.dsu.Services.Service_For_Public;
import com.dsy.dsu.R;
import com.dsy.dsu.Tabels.Peoples.MainActivity_List_Peoples;
import com.dsy.dsu.Tabels.Tabel.New.MainActivity_New_Tabely;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;


import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;




@AndroidEntryPoint
public class MainActivity_List_Tabels extends AppCompatActivity  {
    private  Spinner СпинерВыборДату;/////спинеры для создание табеляСпинерТабельДепратамент

    private  GridView gridViewAllTabes;
    private   ProgressDialog progressDialogДляУдаления;

    private    Configuration config;

    private     String ПолученныйГодДляНовогоТабеля= "";
    private   String ФинальнаяМЕсяцДляНовогоТабеля= "";

    private   Context context;

    private   Button backFaceApp;


    private  TextView textViewКоличествоТабелей;
    private  FloatingActionButton КруглаяКнопкаСамТабель;
    private  Activity activity;
    private   Integer   ПубличноеIDПолученныйИзСервлетаДляUUID=0;
    private Class_GRUD_SQL_Operations class_grud_sql_operationsДляАктивтиТабель ;
    private BinessLogicPublicContent Class_Engine_SQLГдеНаходитьсяМенеджерПотоков ;
    private Long MainParentUUIDFromTabel =0l;
    private SharedPreferences sharedPreferencesХранилище;
    private  Animation     animation;
    private  Animation     animationvibr1;
    private   int МЕсяцТабелей;
    private  int ГодТабелей;
    private   int DigitalNameCFOFromTabel;
    private  String ИмесяцвИГодСразу;
    private  int Position;
    private String FullNameCFO;
    private  LinkedList< String> МассивДляВыбораВСпинерДатаArray=new  LinkedList< String>();
    private  LinkedList< Long> МассивДляВыбораВСпинореMainUUID=new  LinkedList< Long>();
    private  Message message;
    private      SubClassCursorLoader subClassCursorLoader;
    private    DatePickerDialog ДатаДляКалендаря;


    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private    SimpleCursorAdapter simpleCursorAdapterAllTAbels;

    private   Cursor Курсор_ДанныеСпиннера;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
        super.onCreate(savedInstanceState);
           // setContentView(R.layout.activity_main__historytabely);
            setContentView(R.layout.activity_main__historytabely_materialview);
            activity=this;
            context =this;
            getSupportActionBar().hide(); ///скрывать тул бар
            subClassCursorLoader=      new SubClassCursorLoader();
            class_grud_sql_operationsДляАктивтиТабель      = new Class_GRUD_SQL_Operations(getApplicationContext());
            // TODO: 16.04.2025
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  );


            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new BinessLogicPublicContent(getApplicationContext());
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        /////todo данная настрока запрещает при запуке активти подскаваать клавиатуре вверх на компонеты eedittext
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            gridViewAllTabes = (GridView) findViewById(R.id.gridViewAllTabes); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА


            fragmentManager =   getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();

        //todo кнопка назад
        backFaceApp = findViewById(R.id.КонопкаНазадСтрелкаВсеТабеля);
        textViewКоличествоТабелей= findViewById(R.id.textViewКоличествоТабелей);
        СпинерВыборДату=(Spinner) findViewById(R.id.СпинерТабельМесяцИсториииТабелей);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        Locale locale = new Locale("rus");
        setDefault(locale );
        config =
                getBaseContext().getResources().getConfiguration();
        config.setLocale(locale);
        createConfigurationContext(config);
            animationvibr1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_singletable2);
               animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_row_tabellist);
         КруглаяКнопкаСамТабель = findViewById(R.id.КруглаяКнопкаСамТабель);//////КНОПКА СОЗДАНИЕ НОВГО ТАБЕЛЯ ИЗ ИСТОРИИ ВТОРОЙ ШАГ СОЗДАНИЯ ТАБЕЛЯ СНАЧАЛА ИСТРОИЯ ПОТОМ НА БАЗЕ ЕГО СОЗЗДАНИЕ

            // TODO: 14.10.2022 настйрока хранилища
            sharedPreferencesХранилище=   getApplicationContext().getSharedPreferences("sharedPreferencesХранилище",
                    Context.MODE_MULTI_PROCESS);
            SharedPreferences.Editor editor = sharedPreferencesХранилище.edit();
            editor.putString( "sharedPreferencesХранилищеkey", "sharedPreferencesХранилищеvalue" );
            editor.apply();

            // TODO: 06.11.2022 методы после создание
            //TODO МЕТОД ПОЛУЧЕНИЕ ДАННЫХ ДЛЯ ДАННОГО АКВТИВИ
            МетодBackПеременныеFromListPeoples();
            МетодКруглаяКнопка();
            МетодНазадBACKНААктивти();
            МетодMessage();

            Курсор_ДанныеСпиннера=    методGetDataSimpleCursorAdapter(); /////МЕТОД ЗАГРУЗКИ СОЗДАННЫХ ТАБЕЛЕЙ ИЗ БАЗж

            методЗаполенениеДатаСпинер( );
            ////todo заполение спинера
            МетодДанныеСпинераДаты( );

            // TODO: 09.04.2023  set Позиция после инициализации Scinner
            методМассивДляВыбораВСпинерДата();


            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " Курсор_ДанныеСпиннера" +Курсор_ДанныеСпиннера);
        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }}


    @Override
    protected void onStart() {
        super.onStart();
        try{

            gridViewAllTabes.refreshDrawableState();
            gridViewAllTabes.requestLayout();
            gridViewAllTabes.startAnimation(animation);
            СпинерВыборДату.startAnimation(animation);
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }











    private void МетодКруглаяКнопка() {
        КруглаяКнопкаСамТабель.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO СОЗДАНИЕ НОВОГО ТАБЕЛЯ
                try{
                    Log.d(this.getClass().getName()," создание нового сотрудника " );
                    ///TODO создание нового ТАБЕЛЯ
                    МетодСозданиеДиалогаКалендаряДаты();////ЗПАСУКАЕМ МЕТОД КОГДА НАДО ВЫБРВТЬ ДАТУ С КАЛЕНДАРКА
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }}
        });
    }

    private void МетодНазадBACKНААктивти() {
        backFaceApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Log.d(this.getClass().getName(), " кликнем для созданни новго сотрдника при нажатии  ");
                    ///todo код которыц возврящет предыдущий актвитики кнопка back

                    LaunchActivityDashboard launchActivityDashboard=new LaunchActivityDashboard( fragmentManager,getApplicationContext());
                    // TODO: 27.03.2024 в зависомсти кто вызвает
                    launchActivityDashboard.     launchADashboardFragment();


                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    // TODO: 01.09.2021 метод вызова
                    new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                            this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }


        });
    }

    // TODO: 09.04.2023  получем данные для ТАбелей
    void МетодBackПеременныеFromListPeoples() {
        try{
        Intent Интент_Back_MAinActivity_List_peole = getIntent();
            Bundle bundleДЛяListTabels=Интент_Back_MAinActivity_List_peole.getExtras();
            if (bundleДЛяListTabels!=null) {
                MainParentUUIDFromTabel =      bundleДЛяListTabels.getLong("MainParentUUID", 0l);
                Position=   bundleДЛяListTabels.getInt("Position", 0);
                ГодТабелей=  bundleДЛяListTabels.getInt("ГодТабелей", 0);
                МЕсяцТабелей=  bundleДЛяListTabels.getInt("МЕсяцТабелей",0);
                DigitalNameCFOFromTabel = bundleДЛяListTabels.getInt("DigitalNameCFO", 0);
                FullNameCFO=    bundleДЛяListTabels.getString("FullNameCFO", "" );
                ИмесяцвИГодСразу= bundleДЛяListTabels.getString("ИмесяцвИГодСразу", "" );
            }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " bundleДЛяListTabels " +bundleДЛяListTabels);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
        // TODO: 01.09.2021 метод вызова
       this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }





    private void методКоличествоТабелей(@NonNull  Cursor    Курсор_Main_ListTabelsFinal) {
        try{
        if (   СпинерВыборДату.getCount()>0) {
            textViewКоличествоТабелей.setText(" ("+СпинерВыборДату.getCount()+")"+" ("+Курсор_Main_ListTabelsFinal.getCount()+")");
        } else {
            textViewКоличествоТабелей.setText("("+"0"+")");
        }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    ///todo  конец метода удаления третий обработчки нажатия
    ///////МЕТОД СОЗДАННИЕ СПИНЕРА

    ///todo сообщение
    @SuppressLint("SuspiciousIndentation")
    @UiThread


    private Cursor МетодПолучениеДанныхДляИхУдаления(@NonNull Context context ,@NonNull Long СамоЗначениеUUID) {
        Cursor cursor=null;
        try{
            Bundle bundle=new Bundle();
            bundle.putString("СамЗапрос","  SELECT uuid FROM  data_tabels  WHERE uuid_tabel=?     AND status_send!=?");
            bundle.putStringArray("УсловияВыборки" ,new String[]{String.valueOf(СамоЗначениеUUID),"Удаленная"});
            bundle.putString("Таблица","data_tabels");
            Intent intent=new Intent("ДляУдаление");
            intent.putExtras(bundle);
            Service_For_Public  service_for_public=new Service_For_Public();
        cursor=  service_for_public.МетодПолучениеДанныхЧерезCursorLoader(context,intent);
            Log.d(this.getClass().getName(), " cursor " + cursor);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return cursor;
    }

    private void методМассивДляВыбораВСпинерДата() {
        try {
            ИмесяцвИГодСразу = СпинерВыборДату.getSelectedItem().toString();
                if (ИмесяцвИГодСразу !=null) {
                 Integer ИндексНахождение=   МассивДляВыбораВСпинерДатаArray.indexOf(ИмесяцвИГодСразу);
                    Log.d(  getApplicationContext().getClass().getName(), " ИндексНахождение "+ИндексНахождение);
                    if (ИндексНахождение>=0) {
                        Collections.swap(МассивДляВыбораВСпинерДатаArray,0,ИндексНахождение);
                    }
                }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " МассивДляВыбораВСпинерДатаArray " +МассивДляВыбораВСпинерДатаArray+"  FullNameCFO  " +FullNameCFO);
        } catch (Exception e) {
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
       this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        /////МЕТОД ЗАГРУКЗИ КОЛИЧЕСТВО ТАБЕЛЕЙ ИЗ БАЗЫ\




    }

    private void МетодДанныеСпинераДаты(  ) {
        try{
            ArrayAdapter<String>           АдаптерДляСпинераДата = new ArrayAdapter<String>(this,
                    R.layout.simple_for_create_new_assintionmaterila_spinner_main, МассивДляВыбораВСпинерДатаArray);
            АдаптерДляСпинераДата.setDropDownViewResource(R.layout.simple_for_create_new_assintionmaterila_spinner);
        СпинерВыборДату.setAdapter(АдаптерДляСпинераДата);
        СпинерВыборДату.setSelected(true);
        СпинерВыборДату.setSaveEnabled(true);
        СпинерВыборДату.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (view!=null) {
                    if ((TextView) parent.getChildAt(0) != null) {
                        ИмесяцвИГодСразу = Optional.ofNullable(String.valueOf(((TextView)
                             parent.getChildAt(0)).getText())).map(String::new).orElse(" "); /////ОПРЕДЕЛЯЕМ ТЕКУЩЕЕ ЗНАЧЕНИЕ ВНУТИРИ СПЕНИРА
                        //////TODO линия снизу самих табелей ЦВЕТ
                        if (! ИмесяцвИГодСразу.equalsIgnoreCase("Не создано") ) {

                            ((TextView) parent.getChildAt(0)).setTextSize(16);
                            ((TextView) parent.getChildAt(0)).startAnimation(animation);
                            ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                            ((TextView) parent.getChildAt(0)).setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                            ((TextView) parent.getChildAt(0)).setTypeface(((TextView) parent.getChildAt(0)).getTypeface(), Typeface.BOLD);//////ВЫДЕЛЕМ ЖИРНЫМ ЦВЕТОМ ДАТЫ
                            ((TextView) parent.getChildAt(0)).setText(ИмесяцвИГодСразу);//// ЗАПИСЫВАЕМ ЗНАЧЕНИЕ В СПИПЕР
                            СпинерВыборДату.startAnimation(animation);
                            TextView textViewspiner=(TextView)   СпинерВыборДату.getSelectedView();
                                textViewspiner.setTextColor(Color.BLACK);

                            MainParentUUIDFromTabel =(Long)       МассивДляВыбораВСпинореMainUUID.get(position);


                            Bundle bundleДЛяСпинераДаты=new Bundle();
                            bundleДЛяСпинераДаты.putLong("MainParentUUID", MainParentUUIDFromTabel);
                            bundleДЛяСпинераДаты.putInt("Position", position);
                            bundleДЛяСпинераДаты.putString("ИмесяцвИГодСразу", ИмесяцвИГодСразу.trim());
                            bundleДЛяСпинераДаты.putInt("ГодТабелей",ГодТабелей );
                            bundleДЛяСпинераДаты.putInt("МЕсяцТабелей",МЕсяцТабелей);
                            bundleДЛяСпинераДаты.putInt("DigitalNameCFO", DigitalNameCFOFromTabel);
                            // TODO: 19.04.2023  add bungle
                            textViewspiner.setTag(bundleДЛяСпинераДаты);


                            Cursor Курсор_MainСамиДанныеТекущегоТАбеля = методGetИзСпинераВнутри();


                            if (Курсор_MainСамиДанныеТекущегоТАбеля!=null &&  Курсор_MainСамиДанныеТекущегоТАбеля.getCount()>0 ) {

                                // TODO: 23.08.2023  ГЛАВНЫЙ МЕТОД ЗАПОЛЕНИЯ ЭКРАНА SIMPLECURSOR  ДАННЫМИ
                                методзаполненияSimplrCursor(Курсор_MainСамиДанныеТекущегоТАбеля);
                                // TODO: 19.04.2023  показываем количемтво табеленй
                                методКоличествоТабелей(Курсор_MainСамиДанныеТекущегоТАбеля );

                            } else {
                                // TODO: 19.04.2023  Когда ДАННЫХ НЕТ
                                методDontGetData();
                            }

                            Log.d(this.getClass().getName(), " КакойКонтекст" + ИмесяцвИГодСразу +
                                    " ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре " + ИмесяцвИГодСразу+
                                    " МассивДляВыбораВСпинерДатаArray " +МассивДляВыбораВСпинерДатаArray  +
                                    " МассивДляВыбораВСпинореMainUUID " + МассивДляВыбораВСпинореMainUUID+
                                    "  ((TextView) parent.getChildAt(0)) " +((TextView) parent.getChildAt(0)).getTag()
                                    + " MainParentUUID " + MainParentUUIDFromTabel );

                        }else {
                            // TODO: 19.04.2023  Когда ДАННЫХ НЕТ
                            методDontGetData( );
                        }
                        Log.d(this.getClass().getName(), " КакойКонтекст" + ИмесяцвИГодСразу +
                                " ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре " + ИмесяцвИГодСразу+
                                " МассивДляВыбораВСпинерДатаArray " +МассивДляВыбораВСпинерДатаArray  +
                                " МассивДляВыбораВСпинореMainUUID " + МассивДляВыбораВСпинореMainUUID+
                                "  ((TextView) parent.getChildAt(0)) " +((TextView) parent.getChildAt(0)).getTag()  + " MainParentUUID " + MainParentUUIDFromTabel);
                    }
                }
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(this.getClass().getName(), "  FullNameCFO  " + FullNameCFO);
            }
        });
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    @SuppressLint({"Range", "SuspiciousIndentation"})
    private Cursor методGetИзСпинераВнутри() {
        Cursor    Курсор_MainСамиДанныеТекущегоТАбеля=null;
        try {
        Cursor Курсор_Main_ListTabelFinding=    методGetDataSimpleCursorAdapter(MainParentUUIDFromTabel);
        МЕсяцТабелей =Курсор_Main_ListTabelFinding.getInt(Курсор_Main_ListTabelFinding.getColumnIndex("month_tabels"));
        ГодТабелей   =Курсор_Main_ListTabelFinding.getInt(Курсор_Main_ListTabelFinding.getColumnIndex("year_tabels"));
        DigitalNameCFOFromTabel =Курсор_Main_ListTabelFinding.getInt(Курсор_Main_ListTabelFinding.getColumnIndex("cfo"));
        // TODO: 19.04.2023 даннные
        Курсор_Main_ListTabelFinding.close();
        // TODO: 09.04.2023  Главный Треитий Последние Получение Данных Для Конктерного Месяца И Года
        Курсор_MainСамиДанныеТекущегоТАбеля=    методGetDataSimpleCursorAdapter(МЕсяцТабелей,ГодТабелей);
            // TODO: 17.04.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  +
                     "  Курсор_MainСамиДанныеТекущегоТАбеля " +Курсор_MainСамиДанныеТекущегоТАбеля);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return Курсор_MainСамиДанныеТекущегоТАбеля;
    }


    void методDontGetData(  ){
        try{
                TextView textViewСпинерДАты = (TextView) СпинерВыборДату.getSelectedView();
                СпинерВыборДату.setSelection(0, true);
                СпинерВыборДату.refreshDrawableState();
                СпинерВыборДату.requestLayout();
                // TODO: 19.04.2023  когад нет данныхх
                методDontCursorSimplrCursor();


            Log.d(this.getClass().getName(), " КакойКонтекст" + ИмесяцвИГодСразу +
                    " ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре " + ИмесяцвИГодСразу+
                    " МассивДляВыбораВСпинерДатаArray " +МассивДляВыбораВСпинерДатаArray  +
                    " МассивДляВыбораВСпинореMainUUID " + МассивДляВыбораВСпинореMainUUID+
                    "  ((TextView) parent.getChildAt(0)) "+ " MainParentUUID " + MainParentUUIDFromTabel);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    ////TODO Метод Преоразует цифру в  названия месяца
    private String МетодДляПреоразванияЦифрыВНазванияМесяца(Cursor Курсор_ВЫводимМаксимальнуюДатуДляСпинера) {
        String  МаксимальнаяМесяцДляСпинера;
        String МаксимальнаяГодДляСпинера;
        String  МаксимальнаяНазваниеДляСпинера;
        String ПолученыеМесяцНеОбработанный = null;
        try{
            МаксимальнаяМесяцДляСпинера =Курсор_ВЫводимМаксимальнуюДатуДляСпинера.getString(0);
            Log.i(this.getClass().getName(), "МаксимальнаяМесяцДляСпинера[0] " +   МаксимальнаяМесяцДляСпинера);
            МаксимальнаяГодДляСпинера =Курсор_ВЫводимМаксимальнуюДатуДляСпинера.getString(1);
            Log.i(this.getClass().getName(), "МаксимальнаяГодДляСпинера[0] " + МаксимальнаяГодДляСпинера);
            МаксимальнаяНазваниеДляСпинера=Курсор_ВЫводимМаксимальнуюДатуДляСпинера.getString(2);
            Log.i(this.getClass().getName(), " МаксимальнаяНазваниеДляСпинера[0] " + МаксимальнаяНазваниеДляСпинера);
            DateFormat df = new SimpleDateFormat("MM/yyyy");
            Date date = df.parse(МаксимальнаяМесяцДляСпинера+"/"+МаксимальнаяГодДляСпинера);
            System.out.println(date); // Sat Jan 02 00:00:00 GMT 2010
                /*   SimpleDateFormat f = new SimpleDateFormat("MMM", new Locale("ru"));
                    SimpleDateFormat f1 = new SimpleDateFormat("LLL", new Locale("ru"));
                    SimpleDateFormat f2 = new SimpleDateFormat("MMMM", new Locale("ru"));*/
            SimpleDateFormat ПреобразованиеЦифраВНАзваниемесяца = new SimpleDateFormat("LLLL  yyyy", new Locale("ru"));
            ПолученыеМесяцНеОбработанный=  ПреобразованиеЦифраВНАзваниемесяца.format(date);
            System.out.println(ПолученыеМесяцНеОбработанный);
            ПолученыеМесяцНеОбработанный=(ПолученыеМесяцНеОбработанный.substring(0,1).toUpperCase()+
                    ПолученыеМесяцНеОбработанный.substring(1).toLowerCase());
            Log.i(this.getClass().getName(), " ПолученыеМесяцНеОбработанный " + ПолученыеМесяцНеОбработанный);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
       this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ПолученыеМесяцНеОбработанный;
    }

    private Cursor методGetDataSimpleCursorAdapter()  {
        Cursor Курсор_ДанныеСпиннера = null;
        try{
            // TODO: 09.04.2023  курсор самим создаваемых табеляПОСИК ДАННЫХ ЧЕРЕЗ UUID
            Bundle bundleListTabels=new Bundle();
            bundleListTabels.putString("СамЗапрос","  SELECT * FROM  tabel WHERE status_send!=?  " +
                    "  AND month_tabels IS NOT NULL " +
                    " AND year_tabels IS NOT NULL" +  "" +
                    " GROUP BY month_tabels, year_tabels HAVING count(year_tabels )>0  "+
                    " ORDER BY year_tabels DESC ,month_tabels DESC LIMIT 6  ");
            bundleListTabels.putStringArray("УсловияВыборки" ,new String[]{String.valueOf("Удаленная")});
            bundleListTabels.putString("Таблица","tabel");
            Курсор_ДанныеСпиннера=      (Cursor)    new SubClassCursorLoader(). CursorLoaders(context, bundleListTabels);
            Log.d(this.getClass().getName(), "Курсор_ДанныеСпиннера "+Курсор_ДанныеСпиннера  );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                   new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return Курсор_ДанныеСпиннера;
    }
    private Cursor методGetDataSimpleCursorAdapter(@NonNull Long  MainParentUUID )  {
        Cursor Курсор_Main_ListTabels = null;
        try{
            // TODO: 09.04.2023  курсор самим создаваемых табеляПОСИК ДАННЫХ ЧЕРЕЗ UUID
            Bundle bundleListTabels=new Bundle();
            bundleListTabels.putString("СамЗапрос","  SELECT * FROM  tabel WHERE status_send!=? AND uuid=? " +
                    "  AND month_tabels IS NOT NULL " +
                    " AND year_tabels IS NOT NULL" +
                    " ORDER BY year_tabels DESC ,month_tabels DESC LIMIT 6  ");
            bundleListTabels.putStringArray("УсловияВыборки"
                    ,new String[]{String.valueOf("Удаленная"),String.valueOf(MainParentUUID)});
            bundleListTabels.putString("Таблица","tabel");
            Курсор_Main_ListTabels=      (Cursor)    subClassCursorLoader. CursorLoaders(context, bundleListTabels);
            Log.d(this.getClass().getName(), "GetData "+Курсор_Main_ListTabels  );

            Log.d(this.getClass().getName(), " Курсор_Main_ListTabels.getCount() " +    Курсор_Main_ListTabels.getCount());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return Курсор_Main_ListTabels;
    }

    private Cursor методGetDataSimpleCursorAdapter(@NonNull Integer  МЕсяцТабелей, @NonNull Integer  ГодТабелей )  {
        Cursor Курсор_Main_ListTabels = null;
        try{
            // TODO: 09.04.2023  курсор самим создаваемых табеляПОСИК ДАННЫХ ЧЕРЕЗ UUID
            Bundle bundleListTabels=new Bundle();
            bundleListTabels.putString("СамЗапрос","  SELECT * FROM tabel" +
                    " WHERE status_send!=? AND month_tabels =?  AND year_tabels =? " +
                    " ORDER BY year_tabels DESC ,month_tabels DESC LIMIT 6  ");
            bundleListTabels.putStringArray("УсловияВыборки" ,new String[]{String.valueOf("Удаленная"),String.valueOf(МЕсяцТабелей),String.valueOf(ГодТабелей)});
            bundleListTabels.putString("Таблица","tabel");
            Курсор_Main_ListTabels=      (Cursor)    subClassCursorLoader. CursorLoaders(context, bundleListTabels);
            Log.d(this.getClass().getName(), "GetData "+Курсор_Main_ListTabels  );

            Log.d(this.getClass().getName(), " Курсор_Main_ListTabels.getCount() " +    Курсор_Main_ListTabels.getCount());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return Курсор_Main_ListTabels;
    }



    @SuppressLint("Range")
    private void методзаполненияSimplrCursor(Cursor Курсор_Main_ListTabels) {
        try {
            SubClassCursorLoader subClassCursorLoader1ПОсикНазваниеЦФО=      new SubClassCursorLoader();
            simpleCursorAdapterAllTAbels =
                    new SimpleCursorAdapter(getApplicationContext(), R.layout.list_item_all_customer_tabel3,
                    Курсор_Main_ListTabels, new String[]{"_id","cfo"}, new int[]{android.R.id.text1,android.R.id.text2},
                          0);  ///name
            SimpleCursorAdapter.ViewBinder binding = new SimpleCursorAdapter.ViewBinder() {

                @Override
                public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                    try{
                        switch (view.getId()) {
                            case android.R.id.text2:
                                MainParentUUIDFromTabel = cursor.getLong(cursor.getColumnIndex("uuid")); //TODO ЗАПРОС К ТАБЛИЦЕ TABEL
                                DigitalNameCFOFromTabel = cursor.getInt(cursor.getColumnIndex("cfo"));//TODO ЗАПРОС К ТАБЛИЦЕ TABEL


                                FullNameCFO = getingNameCurrentZFOWithUUID(subClassCursorLoader1ПОсикНазваниеЦФО);

                          if( FullNameCFO.equalsIgnoreCase("Нет ЦФО !!!")  )  {

                              FullNameCFO = getingNameCurrentZFOWithID(subClassCursorLoader1ПОсикНазваниеЦФО);
                          }

                                // TODO: 19.06.2023 close

                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                        " DigitalNameCFO " + DigitalNameCFOFromTabel + " FullNameCFO "+FullNameCFO);


                                МЕсяцТабелей = cursor.getInt(cursor.getColumnIndex("month_tabels"));
                                ГодТабелей= cursor.getInt(cursor.getColumnIndex("year_tabels"));
                                // TODO: 15.12.2022  Давные Bundle
                                Bundle bundleДЛяListTabels=new Bundle();
                                bundleДЛяListTabels.putLong("MainParentUUID", MainParentUUIDFromTabel);
                                bundleДЛяListTabels.putInt("Position", cursor.getPosition());
                                bundleДЛяListTabels.putInt("ГодТабелей",ГодТабелей );
                                bundleДЛяListTabels.putInt("МЕсяцТабелей",  МЕсяцТабелей);
                                bundleДЛяListTabels.putInt("DigitalNameCFO", DigitalNameCFOFromTabel);
                                bundleДЛяListTabels.putString("FullNameCFO", FullNameCFO.trim());
                                bundleДЛяListTabels.putString("ИмесяцвИГодСразу", ИмесяцвИГодСразу.trim());

                                // TODO: 09.04.2023  ВставлЯем Данные
                                ((MaterialTextView) view).setTag(bundleДЛяListTabels);
                                if (FullNameCFO!=null && FullNameCFO.length()>0) {
                                    ((MaterialTextView) view).setText(FullNameCFO.trim());
                                }else{
                                    ((MaterialTextView) view).setText("Нет ЦФО !!!");
                                }
                                ((MaterialTextView) view).setTextSize(15l);
                                ((MaterialTextView) view).startAnimation(animationvibr1);
                                // TODO: 18.04.2023  Внешниц вид

                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                        " DigitalNameCFO " + DigitalNameCFOFromTabel + " MainParentUUID "+ MainParentUUIDFromTabel);
                                return true;



                            case android.R.id.text1:
                                //Drawable icon2 = getResources().getDrawable(   R.drawable.icon_alltabels1);
                                Drawable icon2 = getResources().getDrawable(   R.drawable.icon_newlisttabel);
                                ((ImageView) view).setImageDrawable(icon2);
                                ((ImageView) view).setImageResource(R.drawable.icon_newlisttabel);

                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " MainParentUUID "+ MainParentUUIDFromTabel);
                                return true;

                        }

                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + " MainParentUUID "+ MainParentUUIDFromTabel);
                        return false;
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    return false;
                }


            };
            simpleCursorAdapterAllTAbels.setViewBinder(binding);
            gridViewAllTabes.setAdapter(simpleCursorAdapterAllTAbels);
            simpleCursorAdapterAllTAbels.notifyDataSetChanged();
            gridViewAllTabes.refreshDrawableState();
            gridViewAllTabes.requestLayout();
            // TODO: 19.04.2023 слушаелти
            // TODO: 18.04.2023 Слушаиель Клика
            методПоGridView( );
            // TODO: 18.04.2023 Слушатель Удалание
            методУдалениеТабеля( );


    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    @SuppressLint("Range")
    @NonNull
    private String getingNameCurrentZFOWithUUID(@NonNull  SubClassCursorLoader subClassCursorLoader1ПОсикНазваниеЦФО) {
        try{
        // TODO: 18.04.2023 Название ЦФО
        Bundle bundleНазваниеЦФО=new Bundle();
        bundleНазваниеЦФО.putString("СамЗапрос","  SELECT * FROM  cfo WHERE    uuid=? ");
        bundleНазваниеЦФО.putStringArray("УсловияВыборки" ,new String[]{  String.valueOf(DigitalNameCFOFromTabel)});
        bundleНазваниеЦФО.putString("Таблица","cfo");
        // TODO: 07.06.2023 вытаскиваем названеи ЦФО
        Cursor КурсорПОискНазваниеЦФО = (Cursor)    subClassCursorLoader1ПОсикНазваниеЦФО. CursorLoaders(context, bundleНазваниеЦФО);//TODO ЗАПРОС К ТАБЛИЦЕ CFO

        // TODO: 09.10.2024  Получаем Название ЦФО  для отображения
        if (КурсорПОискНазваниеЦФО.getCount()>0) {
            FullNameCFO=КурсорПОискНазваниеЦФО.getString(КурсорПОискНазваниеЦФО.getColumnIndex("name")).trim();
            // TODO: 07.10.2024
            if ( FullNameCFO.equalsIgnoreCase("Тестовое")) {
                FullNameCFO="Нет ЦФО !!!";
            }
        }else {
            FullNameCFO="Нет ЦФО !!!";
        }
        КурсорПОискНазваниеЦФО.close();
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


        return FullNameCFO;
    }


    @SuppressLint("Range")
    @NonNull
    private String getingNameCurrentZFOWithID(@NonNull  SubClassCursorLoader subClassCursorLoader1ПОсикНазваниеЦФО) {
        try{
            // TODO: 18.04.2023 Название ЦФО
            Bundle bundleНазваниеЦФО=new Bundle();
            bundleНазваниеЦФО.putString("СамЗапрос","  SELECT * FROM  cfo WHERE    _id=? ");
            bundleНазваниеЦФО.putStringArray("УсловияВыборки" ,new String[]{  String.valueOf(DigitalNameCFOFromTabel)});
            bundleНазваниеЦФО.putString("Таблица","cfo");
            // TODO: 07.06.2023 вытаскиваем названеи ЦФО
            Cursor КурсорПОискНазваниеЦФО = (Cursor)    subClassCursorLoader1ПОсикНазваниеЦФО. CursorLoaders(context, bundleНазваниеЦФО);//TODO ЗАПРОС К ТАБЛИЦЕ CFO

            // TODO: 09.10.2024  Получаем Название ЦФО  для отображения
            if (КурсорПОискНазваниеЦФО.getCount()>0) {
                FullNameCFO=КурсорПОискНазваниеЦФО.getString(КурсорПОискНазваниеЦФО.getColumnIndex("name")).trim();
                // TODO: 07.10.2024
                if ( FullNameCFO.equalsIgnoreCase("Тестовое")) {
                    FullNameCFO="Нет ЦФО !!!";
                }
            }else {
                FullNameCFO="Нет ЦФО !!!";
            }
            КурсорПОискНазваниеЦФО.close();
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


        return FullNameCFO;
    }




    private void методУдалениеТабеля( ) {
        gridViewAllTabes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                try{
                    MaterialTextView materialTextView=(MaterialTextView)        view.findViewById(android.R.id.text2);
                    materialTextView.setBackgroundColor(Color.GRAY);

                    message.getTarget().postDelayed(()->{
                        Bundle bundleДЛяListTabels=(Bundle)           materialTextView.getTag();
                        Long    MainParentUUID=      bundleДЛяListTabels.getLong("MainParentUUID");
                        String    FullNameCFO=      bundleДЛяListTabels.getString("FullNameCFO");
                        ///todo Удаление
                        МетодУдалениеТАбеляСообщениеПередЭтим(MainParentUUID, FullNameCFO,view);
                            },200);

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
                return true;
            }
        });

    }

    private void методDontCursorSimplrCursor( ) {
        try {
            ArrayList<HashMap<String, Object>> ЛистНетданных= new ArrayList<HashMap<String, Object>> ();
            HashMap<String, Object> map = new HashMap<>();
            map.put("allimage", " dont");
            ЛистНетданных.add(map);
            SimpleAdapter АдаптерКогдаНетданных = new SimpleAdapter(getApplicationContext(),
                    ЛистНетданных,
                    R.layout.list_item_all_customer_tabel4dont,
                    new String[]{ "allimage"},
                    new int[]{android.R.id.text1});

            SimpleAdapter.ViewBinder БиндингКогдаНетДАнных = new SimpleAdapter.ViewBinder() {
                @Override
                public boolean setViewValue(View view, Object data, String textRepresentation) {
                        try{
                            switch (view.getId()) {
                                case android.R.id.text1:
                                    // TODO: 09.04.2023  ВставлЯем Данные

                                    RelativeLayout relativeLayout=view.findViewById(android.R.id.text1);
                                    CheckedTextView checkedTextView=(CheckedTextView) relativeLayout.getChildAt(0);
                                    checkedTextView.setHeight(1200);
                                    checkedTextView.setTextSize(18l);
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" + " MainParentUUID " + MainParentUUIDFromTabel);
                                    return true;
                            }
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" + " MainParentUUID "+ MainParentUUIDFromTabel);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        return false;
                    }
                };
                АдаптерКогдаНетданных.setViewBinder(БиндингКогдаНетДАнных);
                gridViewAllTabes.setAdapter(АдаптерКогдаНетданных);
                АдаптерКогдаНетданных.notifyDataSetChanged();
                gridViewAllTabes.refreshDrawableState();
                gridViewAllTabes.requestLayout();
                // TODO: 19.04.2023 слушаелти

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }




    private void методПоGridView( ) {
        gridViewAllTabes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try{

                    MaterialTextView materialTextView=(MaterialTextView)        view.findViewById(android.R.id.text2);
                    materialTextView.setBackgroundColor(Color.GRAY);
                    message.getTarget().postDelayed(()->{
                        // TODO: 09.04.2023  перехеод после клика Items
                        МетодПереходMainActivity_List_Peoples(materialTextView);
                    },100);

/////TODO одинатрный клик для загрузки в этот табель всех сотрудников
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
        });
    }
    /////TODO метод запуска кода при однократорм нажатии просто загузка сотрудников табель
    private void МетодПереходMainActivity_List_Peoples(@NonNull  MaterialTextView textView) {
        try{
            Intent    ИнтентпереходВMainActivityList_Peoples=new Intent(getApplicationContext(), MainActivity_List_Peoples.class);
            Bundle bundleИзMAinActivbity_List_Tabels=(Bundle) textView.getTag();
            ИнтентпереходВMainActivityList_Peoples.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ИнтентпереходВMainActivityList_Peoples      .putExtras(bundleИзMAinActivbity_List_Tabels);
            startActivity(ИнтентпереходВMainActivityList_Peoples);
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " bundleИзMAinActivbity_List_Tabels "+bundleИзMAinActivbity_List_Tabels);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    //////TODO вычисляем максимальную дату для СПИНЕРА ДЛЯ ВАДАПТЕРА AAARYADAPTER

    SQLiteCursor МетодКоторыйПоказываетМаксимальнуюДатуИзмененияДляСпинера() throws ExecutionException, InterruptedException {
         SQLiteCursor  Курсор_КоторыйЗагружаетГотовыеТабеляМаксимальнаяДатаДляСпинера = null;
                try{
                    // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
                    String ТаблицаНазваниеОбработки="tabel";
                    class_grud_sql_operationsДляАктивтиТабель= new Class_GRUD_SQL_Operations(getApplicationContext());
                    class_grud_sql_operationsДляАктивтиТабель.
                            concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы",ТаблицаНазваниеОбработки);
                    class_grud_sql_operationsДляАктивтиТабель.
                            concurrentHashMapНабор.put("СтолбцыОбработки","month_tabels,year_tabels");
                    class_grud_sql_operationsДляАктивтиТабель.
                            concurrentHashMapНабор.put("ФорматПосика"," status_send!=?");
                    class_grud_sql_operationsДляАктивтиТабель.concurrentHashMapНабор.put("УсловиеПоиска1","Удаленная");
                    class_grud_sql_operationsДляАктивтиТабель.concurrentHashMapНабор.put("УсловиеСортировки","current_table ");//DESC
                    ////
                    BinessLogicPublicContent Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new BinessLogicPublicContent(getApplicationContext());
                    Курсор_КоторыйЗагружаетГотовыеТабеляМаксимальнаяДатаДляСпинера= (SQLiteCursor) class_grud_sql_operationsДляАктивтиТабель.
                            new GetData(getApplicationContext()).getdata(class_grud_sql_operationsДляАктивтиТабель.
                                    concurrentHashMapНабор,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,  );
                    Log.d(this.getClass().getName(), "GetData " +Курсор_КоторыйЗагружаетГотовыеТабеляМаксимальнаяДатаДляСпинера );
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    // TODO: 01.09.2021 метод вызова
                    new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
        return Курсор_КоторыйЗагружаетГотовыеТабеляМаксимальнаяДатаДляСпинера;
    }
     void МетодКогдаДанныхСамихТабелйНет( ) {
        try{

        } catch (Exception e) {
            e.printStackTrace();

            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }































    /////МЕТОД СОЗДАНИЕ ДАТЫ И КАЛЕНДАРЯ
    /////МЕТОД СОЗДАНИЕ ДАТЫ И КАЛЕНДАРЯ
    /////МЕТОД СОЗДАНИЕ ДАТЫ И КАЛЕНДАРЯ
    /////МЕТОД СОЗДАНИЕ ДАТЫ И КАЛЕНДАРЯ

    private void МетодСозданиеДиалогаКалендаряДаты() {///////метод создание календяря даты
/////TODO тут визуализикуеться КАЛЕНДАРЬ
        try {
            Calendar newDate = Calendar.getInstance();
            class  getDatePicker extends DatePickerDialog{
                public getDatePicker(@NonNull Context context, int themeResId) {
                    super(context, themeResId);
                    setButton(BUTTON_POSITIVE, ("Ok"), this);
                    setButton(BUTTON_NEGATIVE, ("Закрыть"), this);
                }
                public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    newDate.set(year, monthOfYear, dayOfMonth);
                }

            }
            ДатаДляКалендаря=new getDatePicker(this,  android.R.style.Widget_Material_DatePicker);//TODO : android.R.style.Widget_Material_DatePicker
            ДатаДляКалендаря.setTitle("Календарь");
            ДатаДляКалендаря.setCancelable(false);
            ДатаДляКалендаря.setCanceledOnTouchOutside(false);


            WindowManager.LayoutParams params = ДатаДляКалендаря.getWindow().getAttributes();
            ДатаДляКалендаря.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);
            ДатаДляКалендаря.getWindow().setGravity(Gravity.CENTER);
            ДатаДляКалендаря.getWindow().setAttributes(params);


         /*   Drawable drawabledown=context.getDrawable(R.drawable.style_for_calendar1);
            ДатаДляКалендаря.getWindow().setBackgroundDrawable(drawabledown);*/
            //todo:
            if (!ДатаДляКалендаря.isShowing()) {
                ДатаДляКалендаря.show();
            }


            // TODO: 18.03.2024  создаем кнопки ок и cancel
            getBUTTON_POSITIVE();
            getBUTTON_NEGATIVE();


            Log.d(this.getClass().getName(), " ИмесяцвИГодСразу " + ИмесяцвИГодСразу);
        ////
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }



    private void getBUTTON_NEGATIVE() {
        ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_NEGATIVE).setVisibility(View.VISIBLE);
        ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);//Color.parseColor("#03DAC6")
        ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_NEGATIVE).setText("Закрыть");

        ДатаДляКалендаря.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                        ДатаДляКалендаря.dismiss();
                        ДатаДляКалендаря.cancel();
                        // TODO: 17.04.2023
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                " DayOfMonth " +ДатаДляКалендаря.getDatePicker().getYear() );

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
                });
    }

    private void getBUTTON_POSITIVE() {
        ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_POSITIVE).setVisibility(View.VISIBLE);
        ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);//Color.parseColor("#03DAC6")
        ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_POSITIVE).setText("Ok");

        ДатаДляКалендаря.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try{

                        int DayOfMonth=    ДатаДляКалендаря.getDatePicker().getDayOfMonth();
                        int Month=  ДатаДляКалендаря.getDatePicker().getMonth()+1;
                        int Year=     ДатаДляКалендаря.getDatePicker().getYear();





                        // TODO: 18.03.2024  создание новой даты
                        setDateTimeForNeTabel( DayOfMonth,Month,Year);



                            if (ИмесяцвИГодСразу != null && СпинерВыборДату !=null) {
                                // TODO: 03.10.2024


                                setNewNameMotchCurretntabel();

                                ДатаДляКалендаря.dismiss();

                                ДатаДляКалендаря.cancel();

                                }  else {
                                Toast.makeText(getApplicationContext(), " Нет месяца для создание Табеля !!! ", Toast.LENGTH_LONG).show();
                                // TODO: 18.03.2024
                            }





                        // TODO: 17.04.2023
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                " DayOfMonth " +ДатаДляКалендаря.getDatePicker().getYear()  );


                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
                });
    }

    private void setDateTimeForNeTabel(@NonNull int DayOfMonth,@NonNull int  МесяцИзКолендаря,@NonNull  int Year) {
        try {
            Date ПрасингДаты = new Date();
            if (FullNameCFO != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    ПрасингДаты = new android.icu.text.SimpleDateFormat("dd-MM-yyyy", new Locale("ru")).parse(DayOfMonth+"-"+МесяцИзКолендаря+"-"+Year);
                } else {
                    ПрасингДаты = new SimpleDateFormat("dd-MM-yyyy", new Locale("ru")).parse(DayOfMonth+"-"+МесяцИзКолендаря+"-"+Year);
                }

                ИмесяцвИГодСразу = МетодПереводаНазваниеМесяцаСАнглискогоНаРУсский(ПрасингДаты);

                Log.d(this.getClass().getName(), " ИмесяцвИГодСразу " + ИмесяцвИГодСразу);

            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }





    ////TODO СОЗДАНИЯ КАЛЕНДАРЯ С ПОЛУЧЕННЫМИ УЖЕ ДАННЫМИ
    private void setNewNameMotchCurretntabel() throws ParseException {
        try{
        Log.d(this.getClass().getName()," ИмесяцвИГодСразу " +ИмесяцвИГодСразу);
        StringBuffer МЕсяцСЗакглавнойБуквы =new StringBuffer(ИмесяцвИГодСразу.toLowerCase());

        ИмесяцвИГодСразу= МЕсяцСЗакглавнойБуквы.substring(0, 1).toUpperCase() + МЕсяцСЗакглавнойБуквы .substring(1).toLowerCase().trim();
        Log.d(this.getClass().getName()," МЕсяцСЗакглавнойБуквы " +ИмесяцвИГодСразу);

            int НовыйМесяц = МетодПолучениниеНовогоМесяцДляЗАписивОднуКолонку(ФинальнаяМЕсяцДляНовогоТабеля);
            int НовыйГод = МетодПолучениниеНовыйГодДляЗАписивОднуКолонку(ПолученныйГодДляНовогоТабеля);
            ///TODO  ПОСЛЕ ВСТАКИ ПЕРЕХОДИМ НА АКТИВТИ С ВЫБОРО И СОЗДАНИЕМ САМОГО ТАБЕЛЯ НОВОГО
            Intent Интент_НовыйТабель = new Intent(getApplicationContext(), MainActivity_New_Tabely.class);
            Bundle     bundleСозданиеНовогоТабеля=new Bundle();
            if (НовыйГод>0 && НовыйМесяц>0 ) {
                bundleСозданиеНовогоТабеля.putString("ИмесяцвИГодСразу", ИмесяцвИГодСразу);
                bundleСозданиеНовогоТабеля.putInt("ГодТабелей", НовыйГод);
                bundleСозданиеНовогоТабеля.putInt("МЕсяцТабелей", НовыйМесяц);
                Интент_НовыйТабель.putExtras(bundleСозданиеНовогоТабеля);
            }
            Интент_НовыйТабель.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (bundleСозданиеНовогоТабеля.size()>0) {
                startActivity(Интент_НовыйТабель);
            }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " bundleСозданиеНовогоТабеля "+bundleСозданиеНовогоТабеля + " НовыйМесяц " +НовыйМесяц+ " НовыйГод " +НовыйГод);
        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

        }






    //TODO метод получени месяа для записи в одну колонку

    private int  МетодПолучениниеМесяцДляЗАписивОднуКолонку(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
        System.out.println( " " + ДатаКоторуюНадоПеревестиИзТекставЦифру + " " +ДатаКоторуюНадоПеревестиИзТекставЦифру);
        int month=0;
        try{
        SimpleDateFormat formatмесяц = new SimpleDateFormat("LLLL  yyyy");
        Date date = formatмесяц .parse(ДатаКоторуюНадоПеревестиИзТекставЦифру);
        Calendar calendar = Calendar.getInstance(new Locale("ru"));
        calendar.setTime(date);
        Calendar calendar2 = new GregorianCalendar();
        calendar.setTime(date );
        month = calendar.get(Calendar.MONTH) + 1;



    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        ///


    }


        return   month;
    }

    //TODO метод получени месяа для записи в одну колонку

    private int  МетодПолучениниеГОдДляЗАписивОднуКолонку(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
        System.out.println( "ДатаКоторуюНадоПеревестиИзТекставЦифру " +ДатаКоторуюНадоПеревестиИзТекставЦифру);
        int year=0;
        try{
        SimpleDateFormat formatгод = new SimpleDateFormat("LLLL  yyyy");
        Date date = formatгод.parse(ДатаКоторуюНадоПеревестиИзТекставЦифру);
        Calendar calendar = Calendar.getInstance(new Locale("ru"));
        calendar.setTime(date);
            year  = calendar.get(Calendar.YEAR);


    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        ///


    }
        return   year ;
    }

////TODO МЕТОД ТОЛЬКО ДЛЯ ВСТВКИ НОВОГО МЕСЯЦА и ГодТабелей НОВЫЙ

















    private int  МетодПолучениниеНовогоМесяцДляЗАписивОднуКолонку(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
        System.out.println( " " + ДатаКоторуюНадоПеревестиИзТекставЦифру + " " +ДатаКоторуюНадоПеревестиИзТекставЦифру);
        int month=0;
        try{
        SimpleDateFormat formatмесяц = new SimpleDateFormat("LLLL");
        Date date = formatмесяц .parse(ДатаКоторуюНадоПеревестиИзТекставЦифру);
        Calendar calendar = Calendar.getInstance(new Locale("ru"));
        calendar.setTime(date);
        month = calendar.get(Calendar.MONTH) + 1;


    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        ///


    }
        return   month;
    }

    private int  МетодПолучениниеНовыйГодДляЗАписивОднуКолонку(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
        System.out.println( "ДатаКоторуюНадоПеревестиИзТекставЦифру " +ДатаКоторуюНадоПеревестиИзТекставЦифру);

        int year=0;
        try{
        SimpleDateFormat formatгод = new SimpleDateFormat("yyyy");
        Date date = formatгод.parse(ДатаКоторуюНадоПеревестиИзТекставЦифру);
        Calendar calendar = Calendar.getInstance(new Locale("ru"));
        calendar.setTime(date);
        Calendar calendar2 = new GregorianCalendar();
        calendar.setTime(date );
      year = calendar.get(Calendar.YEAR);


    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        ///


    }
        return   year ;
    }





    //TODO метод получени года для записи в одну колонку

    private String МетодПолучениниеГодаДляЗАписивОднуКолонку(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
        //
        String Год = null;
try{
        int ИщемЕслиПробелВДате=ДатаКоторуюНадоПеревестиИзТекставЦифру.indexOf(" ");

        StringBuffer ИщемГод=new StringBuffer(ДатаКоторуюНадоПеревестиИзТекставЦифру);

         Год=  ИщемГод.substring(ИщемЕслиПробелВДате,ИщемГод.length());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy", new Locale("ru"));

        Date date = formatter.parse(Год);

        System.out.println(date);

        System.out.println(formatter.format(date));


    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return   Год;
    }







    @SuppressLint("Range")
    private void методЗаполенениеДатаСпинер( )
            throws ExecutionException, InterruptedException, ParseException {
        try {
            МассивДляВыбораВСпинерДатаArray.clear();
            МассивДляВыбораВСпинореMainUUID.clear();
            // TODO: 19.04.2023 close cursor
            if (Курсор_ДанныеСпиннера!=null && Курсор_ДанныеСпиннера.getCount()>0) {
                do{
                  Integer      Месяц = Курсор_ДанныеСпиннера.getInt(Курсор_ДанныеСпиннера.getColumnIndex("month_tabels")) ;
                    Integer     Год =Курсор_ДанныеСпиннера.getInt(Курсор_ДанныеСпиннера.getColumnIndex(	"year_tabels")) ;
                    Long     ЗнаениеИзБазыНовыеТабеляUUID = Курсор_ДанныеСпиннера.getLong(Курсор_ДанныеСпиннера.getColumnIndex("uuid"));
                    String     ЗнаениеИзБазыНовыеТабеляНазваниеТабеля = null;
                    int     ЗнаениеИзБазыНовыеТабеляIDСфо = Курсор_ДанныеСпиннера.getInt(Курсор_ДанныеСпиннера.getColumnIndex("cfo"));
                    Log.d(this.getClass().getName()," ЗнаениеИзБазыНовыеТабеляНазваниеТабеля " +ЗнаениеИзБазыНовыеТабеляНазваниеТабеля);


                    ////todo ПРЕОБРАЗОВАЫВЕМ ЦИФРВЫ В ДАТУ ВВИДЕТ ТЕКСТА ИБЛЬ АВГУСТ 2020 2021
                    SimpleDateFormat ПереводимЦифруВТЕкстМЕсяца = new SimpleDateFormat("mm", new Locale("rus") );
                    Date ДатаДляПолученияМесяцаСловом = ПереводимЦифруВТЕкстМЕсяца.parse(Месяц.toString());
                    String ПреобразованоеИмяМесяца= ПереводимЦифруВТЕкстМЕсяца.format( ДатаДляПолученияМесяцаСловом );
                    Log.d(this.getClass().getName()," ПреобразованоеИмяМесяца " +ПреобразованоеИмяМесяца);
                    SimpleDateFormat formatмесяц = new SimpleDateFormat("MMyyyy", new Locale("ru"));
                    Date date = formatмесяц.parse(ПреобразованоеИмяМесяца+Год);
                    Calendar calendar = Calendar.getInstance(new Locale("ru"));
                    calendar.setTime(date);
                    System.out.println(calendar.get(Calendar.YEAR));
                    System.out.println(calendar.get(Calendar.MONTH)+1);
                    System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
                    System.out.println(new SimpleDateFormat("LLLL").format(calendar.getTime()));
                    ПреобразованоеИмяМесяца=new SimpleDateFormat("LLLL").format(calendar.getTime());
                    StringBuffer stringBuffer=new StringBuffer(ПреобразованоеИмяМесяца);
                    ПреобразованоеИмяМесяца=stringBuffer.substring(0,1).toUpperCase()+stringBuffer.substring(1,stringBuffer.length()).toLowerCase();
                    String ФиналВставкаМЕсяцаИгода = "";
                    ФиналВставкаМЕсяцаИгода=ПреобразованоеИмяМесяца+ "  "+Год;
                    Log.d(this.getClass().getName()," ФиналВставкаМЕсяцаИгода "+ФиналВставкаМЕсяцаИгода);
                    ///todo заполяем Название СФО
                 МассивДляВыбораВСпинерДатаArray.add(ФиналВставкаМЕсяцаИгода.trim());
                    ///todo заполяем Название UUID
                    МассивДляВыбораВСпинореMainUUID.add(ЗнаениеИзБазыНовыеТабеляUUID);


                }while (Курсор_ДанныеСпиннера.moveToNext());
// TODO: 23.08.2023
                Курсор_ДанныеСпиннера.moveToFirst();
            }else {
                     МассивДляВыбораВСпинерДатаArray.add("Не создано");

            }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " МассивДляВыбораВСпинерДатаArray " +МассивДляВыбораВСпинерДатаArray +
                    " МассивДляВыбораВСпинореMainUUID " +МассивДляВыбораВСпинореMainUUID);

        } catch (Exception e) {///////ошибки
            e.printStackTrace();
            Log.e(CoreBinessLogics.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }



    ////ВТОРАЯ ФУНКЦИЯ  ДАТЫ НА РУСКИЙ ЯЗЫК МЕСЯЦ
    //функция получающая время операции

    public String МетодПереводаНазваниеМесяцаСАнглискогоНаРУсский(Date ПрасингДаты) {
        SimpleDateFormat sdfmt = null;
        SimpleDateFormat sdfmtГод = null;
        String ДатаДляСпинераИМеняемАнглискиеНаРУскиеНазваниеМесяцев;
        String ФинальнаяДатаДЛяОпределенияНовыйЭтоМесяцИлиНЕт;
        sdfmt = new SimpleDateFormat("LLLL", new Locale("ru"));
        ФинальнаяМЕсяцДляНовогоТабеля=sdfmt.format(ПрасингДаты);
        Log.d(this.getClass().getName(),"  ФинальнаяМЕсяцДляНовогоТабеля  "+ ФинальнаяМЕсяцДляНовогоТабеля);

        ///////МЕНЯЕМ АГЛИСКИЕ НА РУСКОЕ НАЗВАНИЕМЕСЯЦА
        ДатаДляСпинераИМеняемАнглискиеНаРУскиеНазваниеМесяцев=sdfmt.format(ПрасингДаты);////ПЕРВОНОЧАЛЬНОЕ ВИД МЕСЯЦ НА АНГЛИСКОМ
        ДатаДляСпинераИМеняемАнглискиеНаРУскиеНазваниеМесяцев= ДатаДляСпинераИМеняемАнглискиеНаРУскиеНазваниеМесяцев;
        Log.d(this.getClass().getName(),"  ДатаДляСпинераИМеняемАнглискиеНаРУскиеНазваниеМесяцев  "+ДатаДляСпинераИМеняемАнглискиеНаРУскиеНазваниеМесяцев);
        ///////Добалявем год
        sdfmtГод = new SimpleDateFormat("yyyy", new Locale("ru"));
        ПолученныйГодДляНовогоТабеля=sdfmtГод.format(ПрасингДаты);
        System.out.println("  операции время :  " + sdfmtГод.format(ПрасингДаты));
        ФинальнаяДатаДЛяОпределенияНовыйЭтоМесяцИлиНЕт=ФинальнаяМЕсяцДляНовогоТабеля+"  "+ПолученныйГодДляНовогоТабеля;
        System.out.println("  ФинальнаяДатаДЛяОпределенияНовыйЭтоМесяцИлиНЕт  " + ФинальнаяДатаДЛяОпределенияНовыйЭтоМесяцИлиНЕт);
////////свич пробегаеться по названиям месяцев и перерделываем их с аглиского на русский
        return  ФинальнаяДатаДЛяОпределенияНовыйЭтоМесяцИлиНЕт;
    }











    ///todo сообщение на активти создание новго сотрудника спрашиваем нужно ли создать
    @UiThread
    protected void СообщениеСпрашиваемПользователяЧтоОнТОчноХочетьСоздатьНовыйТабель(String ШабкаДиалога, final String СообщениеДиалога, boolean статус) {
        ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ
        try{
//////сам вид
            final AlertDialog alertDialog = new MaterialAlertDialogBuilder(this)
                    .setTitle(ШабкаДиалога)
                    .setMessage(СообщениеДиалога)
                    .setPositiveButton("Да", null)
                    .setNegativeButton("Нет", null)
                    .setIcon(R.drawable.icon_dsu1_new_tabel1)
                    .show();
/////////кнопка
            final Button MessageBoxUpdateСоздатьТабель = alertDialog .getButton(AlertDialog.BUTTON_POSITIVE);
            MessageBoxUpdateСоздатьТабель.setOnClickListener(new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) {
                    //удаляем с экрана Диалог
                    alertDialog .dismiss();
                    Log.d(this.getClass().getName()," создание нового сотрудника " );


                    ///TODO создание нового ТАБЕЛЯ
                    МетодСозданиеДиалогаКалендаряДаты();////ЗПАСУКАЕМ МЕТОД КОГДА НАДО ВЫБРВТЬ ДАТУ С КАЛЕНДАРКА


                }
            });

/////////кнопка
            final Button MessageBoxUpdateЗАкрытьСозданиеТабеля = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            MessageBoxUpdateЗАкрытьСозданиеТабеля.setOnClickListener(new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) {
                    //удаляем с экрана Диалог
                    alertDialog .dismiss();
///запуск метода обновления через DIALOGBOX
                }
            });
            /////
            //TODO шаблоны



        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
       this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }










































































    void МетодУдалениеТАбеляСообщениеПередЭтим(  @NonNull Long СамUUIDТабеля,
                                               @NonNull String НазваниеУдаляемогоТАбеля,
                                                 @NonNull  View v) {

        try{
            Long СамUUIDТабеляКакLONG= Long.valueOf(СамUUIDТабеля);
            Boolean ФлагВыясняемПроведенныйТабельИлиНет = false;
            SQLiteCursor Курсор_ИщемПроведенЛиТАбельИлиНЕт = null;
            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
            class_grud_sql_operationsДляАктивтиТабель= new Class_GRUD_SQL_Operations(getApplicationContext());
            class_grud_sql_operationsДляАктивтиТабель.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","data_tabels");
            class_grud_sql_operationsДляАктивтиТабель.concurrentHashMapНабор.put("СтолбцыОбработки","*");
            class_grud_sql_operationsДляАктивтиТабель.concurrentHashMapНабор.put("ФорматПосика","uuid_tabel=?");
            class_grud_sql_operationsДляАктивтиТабель.concurrentHashMapНабор.put("УсловиеПоиска1",СамUUIDТабеляКакLONG.toString());
            class_grud_sql_operationsДляАктивтиТабель.concurrentHashMapНабор.put("УсловиеЛимита",1);
            class_grud_sql_operationsДляАктивтиТабель.concurrentHashMapНабор.put("УсловиеСортировки","date_update DESC");
            // TODO: 12.10.2021  Ссылка Менеджер Потоков
            BinessLogicPublicContent Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new BinessLogicPublicContent(getApplicationContext());
            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
            Курсор_ИщемПроведенЛиТАбельИлиНЕт= (SQLiteCursor) class_grud_sql_operationsДляАктивтиТабель.
                    new GetData(getApplicationContext()).getdata(class_grud_sql_operationsДляАктивтиТабель.concurrentHashMapНабор,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков, sqLiteDatabase);
            Log.d(this.getClass().getName(), "GetData " +Курсор_ИщемПроведенЛиТАбельИлиНЕт );
            /////////
            if(Курсор_ИщемПроведенЛиТАбельИлиНЕт.getCount()>0){
                Курсор_ИщемПроведенЛиТАбельИлиНЕт.moveToFirst();
                Log.d(this.getClass().getName(), " Курсор_ИщемПУбличныйIDКогдаегоНетВстатике " + Курсор_ИщемПроведенЛиТАбельИлиНЕт.getCount());
                int ИндексКурсор_ИщемПУбличныйIDКогдаегоНетВстатике= Курсор_ИщемПроведенЛиТАбельИлиНЕт.getColumnIndex("status_carried_out");
                ФлагВыясняемПроведенныйТабельИлиНет =Boolean.parseBoolean( Курсор_ИщемПроведенЛиТАбельИлиНЕт.getString(ИндексКурсор_ИщемПУбличныйIDКогдаегоНетВстатике));
                Log.d(this.getClass().getName(), " ИндексКурсор_ИщемПУбличныйIDКогдаегоНетВстатике " + ИндексКурсор_ИщемПУбличныйIDКогдаегоНетВстатике+
                        "  ФлагВыясняемПроведенныйТабельИлиНет " +ФлагВыясняемПроведенныйТабельИлиНет);
            }
            Курсор_ИщемПроведенЛиТАбельИлиНЕт.close();

            // TODO: 16.09.2021  продолжаем удаление табеля табелья ЕСЛИ СТАРУС ТАБЕЛЬЯ НЕ ПРОВЕДЕННЫЙ
            if (ФлагВыясняемПроведенныйТабельИлиНет==false) {//todo МОЖНО УДАЛИТЬ ПОТОМУ ЧТО ЕЩЕ НЕ ПРЕДЕНЕ

                СообщениеВыборУдлалянияТабеляИзБазы(НазваниеУдаляемогоТАбеля,СамUUIDТабеля) ;

            }else if (ФлагВыясняемПроведенныйТабельИлиНет==true){

            Snackbar.make(v, "В Табеле присутстуют проведенный табель !!! ( удалить нельзя )",Snackbar.LENGTH_LONG).setAction("Action",null).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }


    }
//todo  конеч сообщение предупреждения удлаения табеля





















    ///todo сообщение
    @UiThread
    protected void СообщениеВыборУдлалянияТабеляИзБазы(String Сообщение, Long СамоЗначениеUUID) {
        ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ
        try {
//////сам вид
            final AlertDialog alertDialog = new MaterialAlertDialogBuilder(this)
                    .setTitle("Удаление Табеля...")
                    .setMessage(Сообщение)
                    .setPositiveButton("Да", null)
                    .setNegativeButton("Нет", null)
                    .setIcon(R.drawable.icon_dsu1_delete_customer)
                    .show();
/////////кнопка
            final Button MessageBoxУдалениеСотрудникаИзТабеля = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            MessageBoxУдалениеСотрудникаИзТабеля .setOnClickListener(new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) {
                    //удаляем с экрана Диалог
                    alertDialog.dismiss();
                    Log.d(this.getClass().getName(), "  ФИНАЛ создание нового сотрудника " + "ИндификаторUUID " + " СамоЗначениеUUID " + СамоЗначениеUUID+"  "+ FullNameCFO);
                    if (СамоЗначениеUUID>0) {
                        // TODO: 15.02.2023 получаем даннеы для удаления
                        Cursor cursorДляУдалениея=    МетодПолучениеДанныхДляИхУдаления(getApplicationContext(),СамоЗначениеUUID);
                        // TODO: 15.02.2023  само удаление по двум таблицам
                        МетодУдалениеСамогоТабеляИлиСотрудников(СамоЗначениеUUID,"data_tabels",cursorДляУдалениея);
                        Log.d(this.getClass().getName(), "  ФИНАЛ создание нового сотрудника " + "cursorДляУдалениея ");
                    }


                }
            });
            /////////кнопка
            final Button MessageBoxУдалениеСотрудникаИзТабеляОтмена = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            MessageBoxУдалениеСотрудникаИзТабеляОтмена.setOnClickListener(new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) {
                    //удаляем с экрана Диалог
                    alertDialog.dismiss();
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







    //todo метод удаление сотрудника из табеля
    private void МетодУдалениеСамогоТабеляИлиСотрудников(@NonNull Long СамоЗначениеUUID,
                                                         @NonNull  String ИзКакойТаблицыУдалять,
                                                         @NonNull Cursor cursor) {
        ArrayList<Integer> УдалениеintegerArrayList=new ArrayList<>();
        try{
            Log.d(this.getClass().getName()," ДляУдалениеUUID " +ИзКакойТаблицыУдалять);
            progressDialogДляУдаления = new ProgressDialog(activity);
            progressDialogДляУдаления.setTitle("Удаление Табеля");
            progressDialogДляУдаления.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialogДляУдаления.setProgress(0);
            progressDialogДляУдаления.setCanceledOnTouchOutside(false);
            progressDialogДляУдаления.setMessage("Удаление...");
            progressDialogДляУдаления.show();
            Integer СтрочкиОбработки=cursor.getCount();
                        Observable.range(0,СтрочкиОбработки)
                                    .subscribeOn(Schedulers.single())
                                    .zipWith(Observable.interval(300, TimeUnit.MILLISECONDS), new BiFunction<Object, Long, Object>() {
                                        @Override
                                        public Object apply(Object o, Long aLong) throws Throwable {
                                            Log.d(this.getClass().getName(), " o " + o+ " aLong " +aLong);
                                            return aLong;
                                        }
                                    }).doOnNext(new Consumer<Object>() {
                                    @Override
                                    public void accept(Object o) throws Throwable {
                                        // TODO: 22.11.2022  первая часть
                                    Long    ДляУдалениеUUID=     cursor.getLong(0);
                                   Integer     Удаление = new CoreBinessLogics(getApplicationContext()).УдалениеТолькоПустогоТабеляЧерезКонтейнерУниверсальная(ИзКакойТаблицыУдалять,
                                                    "uuid", ДляУдалениеUUID);
                                            Log.d(this.getClass().getName(), " ДляУдалениеUUID " + ДляУдалениеUUID);
                                        if (Удаление>0) {
                                            УдалениеintegerArrayList.add(Удаление);
                                        }
                                    }
                                })
                                .doAfterNext(new Consumer<Object>() {
                                    @Override
                                    public void accept(Object o) throws Throwable {
                                        cursor.moveToNext();
                                        context.getMainExecutor().execute(()->{
                                            progressDialogДляУдаления.setMessage("Удалание..."+УдалениеintegerArrayList.size()+"("+СтрочкиОбработки+")");
                                        });
                                    }
                                })
                               .subscribeOn(AndroidSchedulers.mainThread())
                                    .doOnComplete(new Action() {
                                        @Override
                                        public void run() throws Throwable {
                                            Log.d(this.getClass().getName(), " УдалениеintegerArrayList.size() " +УдалениеintegerArrayList.size());
                                            МетодУдалениеСамогоТабеляИлиСотрудников(СамоЗначениеUUID,"tabel");
                                        }
                                    })
                                    .doOnError(new Consumer<Throwable>() {
                                        @Override
                                        public void accept(Throwable throwable) throws Throwable {
                                            Log.d(this.getClass().getName(), " doOnError  МетодУдалениеСамогоТабеля  throwable " +throwable.getMessage());
                                            ///метод запись ошибок в таблицу
                                            Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                            new RecordNewErros(getApplicationContext()).recordnewerror(throwable.toString(), this.getClass().getName(),
                                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        }
                                    })
                                    .onErrorComplete(new Predicate<Throwable>() {
                                        @Override
                                        public boolean test(Throwable throwable) throws Throwable {
                                            Log.d(this.getClass().getName(), " onErrorComplete  МетодУдалениеСамогоТабеля  throwable " +throwable.getMessage());
                                            ///метод запись ошибок в таблицу
                                            Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                            new RecordNewErros(getApplicationContext()).recordnewerror(throwable.toString(), this.getClass().getName(),
                                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                            return false;
                                        }
                                    }).subscribe();;



        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        };
    }




    //todo ВТОРОЙ МЕТОД УДАЛЕНИЕ ДЛЯ ВЕРХНЕНЙ ТАБЛИЦЫ ТАБЕЛЬ
    private void МетодУдалениеСамогоТабеляИлиСотрудников(@NotNull  Long ДляУдалениеUUID,
                                                         @NonNull  String ИзКакойТаблицыУдалять) {
        ArrayList<Integer> УдалениеintegerArrayList=new ArrayList<>();
        try{
            Log.d(this.getClass().getName()," ДляУдалениеUUID " +ДляУдалениеUUID);
            Observable.range(0,1)
                    .subscribeOn(Schedulers.single())
                    .zipWith(Observable.interval(300, TimeUnit.MILLISECONDS), new BiFunction<Object, Long, Object>() {
                        @Override
                        public Object apply(Object o, Long aLong) throws Throwable {
                            Log.d(this.getClass().getName(), " o " + o+ " aLong " +aLong);
                            return aLong;
                        }
                    }).doOnNext(new Consumer<Object>() {
                        @Override
                        public void accept(Object o) throws Throwable {
                            // TODO: 22.11.2022  первая часть
                            Integer     Удаление = new CoreBinessLogics(getApplicationContext()).УдалениеТолькоПустогоТабеляЧерезКонтейнерУниверсальная(ИзКакойТаблицыУдалять,
                                    "uuid", ДляУдалениеUUID);
                            Log.d(this.getClass().getName(), " ДляУдалениеUUID " + ДляУдалениеUUID);
                            if (Удаление>0) {
                                УдалениеintegerArrayList.add(Удаление);
                            }
                        }
                    })
                    .doAfterNext(new Consumer<Object>() {
                        @Override
                        public void accept(Object o) throws Throwable {
                            context.getMainExecutor().execute(()->{
                                progressDialogДляУдаления.setMessage("Удалание..."+УдалениеintegerArrayList.size()+"("+УдалениеintegerArrayList.size()+")");
                            });

                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete(new Action() {
                        @Override
                        public void run() throws Throwable {
                            Log.d(this.getClass().getName(), " УдалениеintegerArrayList.size() " +УдалениеintegerArrayList.size());
                            if ( УдалениеintegerArrayList.size()>0) {
                                // TODO: 07.10.2022  СИНХронизация
                                // TODO: 15.02.2023
                                progressDialogДляУдаления.dismiss();
                                progressDialogДляУдаления.cancel();;

                                // TODO: 24.08.2023  метод перегрузки reeboot данных
                                методRebootGataTabel();

                                onStart();
                            }
                        }
                    })

                    .doOnError(new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            Log.d(this.getClass().getName(), " doOnError  МетодУдалениеСамогоТабеля  throwable " +throwable.getMessage());
                            ///метод запись ошибок в таблицу
                            Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getApplicationContext()).recordnewerror(throwable.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    })
                    .onErrorComplete(new Predicate<Throwable>() {
                        @Override
                        public boolean test(Throwable throwable) throws Throwable {
                            Log.d(this.getClass().getName(), " onErrorComplete  МетодУдалениеСамогоТабеля  throwable " +throwable.getMessage());
                            ///метод запись ошибок в таблицу
                            Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getApplicationContext()).recordnewerror(throwable.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            return false;
                        }
                    }).subscribe();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        };
    }

    private void методRebootGataTabel() {
        try{
        if (simpleCursorAdapterAllTAbels!=null) {
            /*Cursor Курсор_MainСамиДанныеТекущегоТАбеля  =     simpleCursorAdapterAllTAbels.getCursor();
           Курсор_MainСамиДанныеТекущегоТАбеля .requery();*/

            // TODO: 24.08.2023

            Курсор_ДанныеСпиннера=    методGetDataSimpleCursorAdapter(); /////МЕТОД ЗАГРУЗКИ СОЗДАННЫХ ТАБЕЛЕЙ ИЗ БАЗж
          //  Курсор_ДанныеСпиннера.requery(); /////МЕТОД ЗАГРУЗКИ СОЗДАННЫХ ТАБЕЛЕЙ ИЗ БАЗж

            методЗаполенениеДатаСпинер( );
            ////todo заполение спинера
            МетодДанныеСпинераДаты( );

            // TODO: 09.04.2023  set Позиция после инициализации Scinner
            методМассивДляВыбораВСпинерДата();



           // Cursor Курсор_MainСамиДанныеТекущегоТАбеля = методGetИзСпинераВнутри();


        /*    if (Курсор_MainСамиДанныеТекущегоТАбеля!=null &&  Курсор_MainСамиДанныеТекущегоТАбеля.getCount()>0 ) {
                // TODO: 23.08.2023  ГЛАВНЫЙ МЕТОД ЗАПОЛЕНИЯ ЭКРАНА SIMPLECURSOR  ДАННЫМИ
                методзаполненияSimplrCursor(Курсор_MainСамиДанныеТекущегоТАбеля);
                // TODO: 19.04.2023  показываем количемтво табеленй
                методКоличествоТабелей(Курсор_MainСамиДанныеТекущегоТАбеля );

            } else {
                // TODO: 19.04.2023  Когда ДАННЫХ НЕТ
                методDontGetData();
            }*/
        }
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    };
    }




    private void МетодMessage() {
        try {
                message=Message.obtain(new Handler(Looper.myLooper()),()->{
                    Bundle bundle=   message.getData();
                    Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+
                            Thread.currentThread().getStackTrace()[2].getMethodName()+
                            " время " +new Date().toLocaleString() + " message " +message );
                    Log.i(this.getClass().getName(), "bundle " +bundle);
                    //message.recycle();
                });
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " message " +message);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
        }

    }

}