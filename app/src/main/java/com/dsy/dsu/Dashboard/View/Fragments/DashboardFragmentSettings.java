package com.dsy.dsu.Dashboard.View.Fragments;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.multidex.BuildConfig;

import com.dsy.dsu.BootAndAsync.Model.EventsBus.MessageEvensBusUpdatePO;
import com.dsy.dsu.BootAndAsync.Model.Service.bl_service_boot.StartServiceBootAndAsync;
import com.dsy.dsu.BootAndAsync.Model.BinesslogicActivityBoot.GetComponentActivityBootService;


import com.dsy.dsu.BusinessLogicAll.CoreBinessLogic.CoreBinessLogics;
import com.dsy.dsu.BusinessLogicAll.Class_Clears_Tables;
import com.dsy.dsu.BusinessLogicAll.GetPingServers.GetPingServerJboss;
import com.dsy.dsu.Dashboard.Model.bl_launchFragmentSettingsandDashbord.LaunchActivityDashboard;
import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;

import com.dsy.dsu.Errors.model.BinessLogicLaunchFragmenrError;
import com.dsy.dsu.Hilt.JbossAdrress.qualifiers.QualifierJbossServer3;
import com.dsy.dsu.Hilt.getSSLSocketFactory2.QualifiergetsslSocketFactory2;
import com.dsy.dsu.Services.ServiceUpdatesPO;
import com.dsy.dsu.Tabels.Templates.MainActivity_New_Templates;
import com.dsy.dsu.Settings.View.MainActivity_Settings;
import com.dsy.dsu.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.jakewharton.rxbinding4.view.RxView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.net.ssl.SSLSocketFactory;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.subjects.Subject;
import kotlin.Unit;

/**
 * AHilt simple {@link Fragment} subclass.
 * Use the {@link DashboardFragmentSettings#newInstance} factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
public class DashboardFragmentSettings extends  DialogFragment {
    // TODO: Rename parameter arguments, choose names that match

    private  ClassBiznesLogikaSettings classBiznesLogikaSettings;


    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private AlertDialog DialogBox=null;
    private  Handler handlerAsync;
    private MaterialButton КнопкаоСистеме, КнопкаПользователи ,  КнопкаОбменДанными, КнопкаОбновление,КнопкаОшибки,КнопкаШаблоны;
    private Animation  animation6;
    private TextView TextViewLogo;
    private LifecycleOwner lifecycleOwner;
    private AppCompatImageButton bottonBack;

    private ServiceUpdatesPO.localBinderОбновлениеПО localBinderОбновлениеПО;//TODO новаЯ

    private Subject<ServiceUpdatesPO.localBinderОбновлениеПО> UpdatePublish;


    private   ServiceConnection  connectionОбновлениеПО;




    GetComponentActivityBootService blInnerMainActivityBootAndAsync;
    @Inject
    StartServiceBootAndAsync startServiceBootAndAsync;


    @Inject
    @QualifiergetsslSocketFactory2
    SSLSocketFactory getsslSocketFactory2;



    @Inject
    @QualifierJbossServer3
    public LinkedHashMap<Integer,String> getHiltPortJboss;



    // TODO: Rename and change types and number of parameters
    public static DashboardFragmentSettings newInstance( ) {
        DashboardFragmentSettings fragment = new DashboardFragmentSettings();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            lifecycleOwner=getActivity();

            fragmentManager = getActivity(). getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            // TODO: 22.08.2023  настйроки анимацуии
            animation6 = AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row9);
            
            classBiznesLogikaSettings=new ClassBiznesLogikaSettings();
            // TODO: 17.08.2023 inizial message
            classBiznesLogikaSettings.  МетодИнициализацияHandler();


            // TODO: 27.12.2024



// TODO: 27.12.2024 Инициализирукм Конструктор Класса для запуска Обновление ПО
            blInnerMainActivityBootAndAsync=new GetComponentActivityBootService(getsslSocketFactory2,getActivity(),getContext() ,lifecycleOwner);

            //  setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_Material_Dialog_Alert);//Theme_Dialog
       // setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog
      //  setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);//Theme_Dialog
       // setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog
      //  setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Dialog_Alert);//Theme_Dialog
       // setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);//Theme_Dialog
       // setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);//Theme_Dialog
        //setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Light_Panel);//Theme_Dialog
      //  setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_InputMethod);//Theme_Dialog
       // setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Light_Panel);//Theme_Dialog
       // setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Panel);//Theme_Dialog
       // setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Settings);//Theme_Dialog
        //setStyle(DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_DeviceDefault_Wallpaper_NoTitleBar);//Theme_Dialog
       // setStyle(DialogFragment.STYLE_NO_FRAME | DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Wallpaper_NoTitleBar);//Theme_Dialog
      //  setStyle(  DialogFragment.STYLE_NO_FRAME | DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_DeviceDefault_Wallpaper_NoTitleBar);//Theme_Dialog
    //    setStyle(   DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_DeviceDefault_Wallpaper_NoTitleBar);//Theme_Dialog
      //  setStyle(   DialogFragment.STYLE_NO_INPUT ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog
       // setStyle(   DialogFragment.STYLE_NO_FRAME ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Overscan);//Theme_Dialog
       // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog
            //setCancelable(false);
           // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog  Без Часов
        //    setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);//Theme_Dialog
           // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_DarkActionBar);//Theme_Dialog
          //  setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_DialogWhenLarge_NoActionBar);//Theme_Dialog   с часами сверху
            // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog  Без Часов
          /*    setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_DialogWhenLarge_NoActionBar);//Theme_Dialog   с часами сверху

            setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_Black_NoTitleBar );*/
            setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_Material_Light_NoActionBar );
  /*          setCancelable(false);*/
            setShowsDialog(true);
        // TODO: 15.08.2023
        Log.d(this.getClass().getName(),"\n" + " class "
                + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(getContext().getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=null;
        try{
       /*     ViewDashboart= inflater.inflate(R.layout.simple_dashbord_fragment_grey, container, false);
            ViewDashboart= inflater.inflate(R.layout.simple_dashbord_fragment_grey, container, false);
            ViewDashboart= inflater.inflate(R.layout.simple_dashbord_fragment_grey, container, false);*/
           // ViewDashboart= inflater.inflate(R.layout.simple_dashbord_fragment_grey, container, false);
           // ViewDashboart= inflater.inflate(R.layout.simple_dashbord_fragment_blue, container, false);
          //  view= inflater.inflate(R.layout.simple_dashbord_fragment_grey, container, false);
            view= inflater.inflate(R.layout.simple_dashbord_fragment_grey_materialdisign_4, container, false);
         // TODO: 19.02.2025
            MaterialCardView  materialcardview_settings         = (MaterialCardView) view.findViewById(R.id.materialcardview_settings); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            КнопкаоСистеме   = (MaterialButton) materialcardview_settings.findViewById(R.id.КнопкаоСистеме); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            КнопкаПользователи         = (MaterialButton) materialcardview_settings.findViewById(R.id.КнопкаПользователи); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            КнопкаОбменДанными         = (MaterialButton) materialcardview_settings.findViewById(R.id.КнопкаОбменДанными); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            КнопкаОбновление = (MaterialButton) materialcardview_settings.findViewById(R.id.Кнопкаобновление); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            КнопкаОшибки          = (MaterialButton) materialcardview_settings.findViewById(R.id.КнопкаОшибки); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            КнопкаШаблоны          = (MaterialButton) materialcardview_settings.findViewById(R.id.КнопкаШаблоны); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            TextViewLogo      = (TextView) materialcardview_settings.findViewById(R.id.TextViewLogo); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            bottonBack = (AppCompatImageButton) materialcardview_settings.findViewById(R.id.imageButton_back_in_settings); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА

           // view= inflater.inflate(R.layout.simple_dashbord_fragment_green, container, false);
            // TODO: 21.06.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  view; //TODO inflater.inflate(R.layout.activity_main__tabel_four_colums, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try{
            super.onViewCreated(view, savedInstanceState);

            // TODO: 22.08.2023 код после ИНИЦИАЛИЗАЦИИ КНОПОК
            classBiznesLogikaSettings.методНастройкиВнешнегоВида();

            // TODO: 22.08.2023  дейстия кнопок TASK
            classBiznesLogikaSettings.new ClassAllTaskButtons().new SubClassoSystem().методТаскоСистемы();

            classBiznesLogikaSettings.new ClassAllTaskButtons().new SubClassAsyncVisual().ЗапускСинхрониазцииИзФрагмента() ;

            classBiznesLogikaSettings.new ClassAllTaskButtons().new SubClassChangeDataUsers().методСменыДанныхПользователя(); ;


            classBiznesLogikaSettings.new ClassAllTaskButtons().new ClassUpdatePO().методОбновлениеПО(); ;

            classBiznesLogikaSettings.new ClassAllTaskButtons().new ClassTaskError( ).методtaskError() ;

            classBiznesLogikaSettings.new ClassAllTaskButtons().new ClassTaskShablony( ) .методtaskChablony() ;


            // TODO: 10.03.2025  биндинг обновление ПО
            new BindingSoftwareUpdatePO().getbindingSoftwareUpdatePO();

            // TODO: 17.08.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }





    @Override
    public void onStart() {
        super.onStart();
        try{
       classBiznesLogikaSettings.new ClassAnimatilBackButton().методToSettingsFragment();
        // TODO: 20.07.2023
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " localBinderОбновлениеПО " +localBinderОбновлениеПО  );
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(getContext().getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }




    @Override
    public void onStop() {
        super.onStop();
        try{
            try{

                // TODO: 17.08.2023
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
            }
            // TODO: 17.08.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
    }
    }





    // TODO: 23.01.2024 EventBus for Update PO
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void EventMessageEvensBusUpdatePO(MessageEvensBusUpdatePO messageEvensBusUpdatePO){
        try{


            blInnerMainActivityBootAndAsync   .getEventBusUpdatePo(messageEvensBusUpdatePO);

            Log.d(getContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + "   starting... onRestart" + " starting... onRestart");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }


    }


    // TODO: 15.08.2023  Бизнес Логика Фрагмета Настройки
    class ClassBiznesLogikaSettings{
        // TODO: 15.08.2023 тест код для 1С
        private void методНастройкиВнешнегоВида() {
            try{

                // TODO: 21.08.2023  ЛОГОТИП
                TextViewLogo.startAnimation(animation6);
                TextViewLogo.refreshDrawableState();
                // TODO: 20.07.2023
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }







        private void МетодИнициализацияHandler() {
            try{
                handlerAsync=new Handler(Looper.getMainLooper()){


                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                    }

                    @Override
                    public void dispatchMessage(@NonNull Message msg) {
                        super.dispatchMessage(msg);
                        try {
                            Bundle bundleCallsBackAsynsService=msg.getData();
                            switch (msg.what){
                                case 20:// процеессе

                                    final Object ТекущаяВерсияПрограммы = BuildConfig.VERSION_CODE;
                                    Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());

                                    Toast toast = Toast.makeText(getContext(), "Последняя версия ПО !!! " +"("+ЛокальнаяВерсияПОСравнение.toString()+")", Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.BOTTOM |Gravity.CENTER_HORIZONTAL, 50, 0);
                                    toast.show();



                                    Log.d(this.getClass().getName(), "\n" + " class " +
                                            Thread.currentThread().getStackTrace()[2].getClassName()
                                            + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                    break;

                                case 34:// процеессе

                                    Toast toast3 = Toast.makeText(getContext(), "Нет связи c Cервер ПО !!!", Toast.LENGTH_LONG);
                                    toast3.setGravity(Gravity.BOTTOM, 0, 40);
                                    toast3.show();

                                    Log.d(this.getClass().getName(), "\n" + " class " +
                                            Thread.currentThread().getStackTrace()[2].getClassName()
                                            + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                    break;
                            }


                            Log.d(this.getClass().getName(), "\n" + " class " +
                                    Thread.currentThread().getStackTrace()[2].getClassName()
                                    + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }


                    }
                };

                Log.i(getContext().getClass().getName(),  " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }









        // TODO: 22.08.2023  классс который отвечает за Переход на Фрагмент НАстройки
        class ClassAnimatilBackButton{
            void методToSettingsFragment(){
                bottonBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            // TODO Запусукаем  Фрагмент Главный Экран
                            LaunchActivityDashboard launchActivityDashboard=new LaunchActivityDashboard( fragmentManager,getContext());
                            // TODO: 27.03.2024 в зависомсти кто вызвает
                            launchActivityDashboard.     launchADashboardFragment(localBinderОбновлениеПО);

                                // TODO: 01.08.2023
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                });


            }


        }


        // TODO: 22.08.2023 Класс для всех Тask Кнопок     // TODO: 22.08.2023 Класс для всех Тask Кнопок
        // TODO: 22.08.2023 Класс для всех Тask Кнопок    // TODO: 22.08.2023 Класс для всех Тask Кнопок
        class ClassAllTaskButtons{
            // TODO: 22.08.2023  класс для Таск О Системы
            class SubClassoSystem {
                void методТаскоСистемы ( ){
                    КнопкаоСистеме.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try{
                                Intent Интент_Меню = new Intent(getContext(), MainActivity_Settings.class);
                                Интент_Меню.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK  );
                                startActivity(Интент_Меню);

                                Log.i(getContext().getClass().getName(),  " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        }
                    });


                }

            }














            // TODO: 22.08.2023  Сласс для Запуска Синхрониазциии  из Фрагнмета Настройки


            class SubClassAsyncVisual{
                private void ЗапускСинхрониазцииИзФрагмента() {


                    RxView.clicks(  КнопкаОбменДанными)
                            .throttleFirst(3, TimeUnit.SECONDS)
                            .filter(s -> !s.toString().isEmpty())
                            .map(new Function<Unit, Object>() {
                                @Override
                                public Object apply(Unit unit) throws Throwable {
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                                    return    КнопкаОбменДанными;
                                }
                            })
                            .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Throwable {
                                    throwable.printStackTrace();
                                    Log.e(getContext().getClass().getName(),
                                            "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new RecordNewErros(getContext()).recordnewerror(throwable.toString(),
                                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            })
                            .onErrorComplete(new Predicate<Throwable>() {
                                @Override
                                public boolean test(Throwable throwable) throws Throwable {
                                    throwable.printStackTrace();
                                    Log.e(getContext().getClass().getName(),
                                            "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new RecordNewErros(getContext()).recordnewerror(throwable.toString(),
                                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    return false;
                                }
                            })
                            .subscribe( GetNameSingleAsync1c-> {

                                // TODO: 24.01.2024  запуска синхрониахции из фрагмента настройкит 
                                startingPoUpdateFromFragmentSettings();

                            });


                }

                private void startingPoUpdateFromFragmentSettings() {
                    try{
                    ///todo revboot
                    ProgressDialog  progressDialogДляСинхронизации = new ProgressDialog(getActivity());

                        // TODO: 22.12.2022  сама запуска синхронищации из workmanager ОБЩЕГО
                    /*    boolean ВыбранныйРежимСети =
                                new GetConnectivityManagerAndroid(getContext()).сonnectivityManageruserselection();
                        if (ВыбранныйРежимСети == true) {*/
                            handlerAsync.post(() -> {
                                progressDialogДляСинхронизации.setTitle("Обмен данными");
                                progressDialogДляСинхронизации.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                progressDialogДляСинхронизации.setProgress(0);
                                progressDialogДляСинхронизации.setCanceledOnTouchOutside(false);
                                progressDialogДляСинхронизации.setMessage("В процессе ....");
                                if (!progressDialogДляСинхронизации.isShowing()) {
                                    progressDialogДляСинхронизации.show();
                                }
                            });

                            // TODO: 10.07.2023  запуск Синхрониаию
                            startServiceBootAndAsync.startServiceBootAndAsync("lanchAsync");
                            // TODO: 26.06.2022
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");



                   /*     } else {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast toast = Toast.makeText(getContext(), "Сервер выкл. !!!", Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.BOTTOM, 0, 40);
                                    toast.show();
                                }
                            });
                        }*/
                        // TODO: 14.12.2023

                        handlerAsync.postDelayed(() -> {
                            progressDialogДляСинхронизации.dismiss();
                            progressDialogДляСинхронизации.cancel();
                        }, 3000);



                    } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

        }
                }


                //TODO end SubClassAsyncVisual
            }//TODO end SubClassAsyncVisual //TODO end SubClassAsyncVisual


            // TODO: 22.08.2023  Класс для смены Данных Пользователя на Другие
            class SubClassChangeDataUsers{
                @UiThread
                protected void методСменыДанныхПользователя() {
                    КнопкаПользователи.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                // TODO: 16.12.2021 НЕПОСРЕДСТВЕННЫЙ ПИНГ СИСТЕНМ ИНТРЕНАТ НА НАЛИЧЕНИ СВАЗИ С БАЗОЙ SQL SERVER
                         Boolean   СтатусРаботыСервера =
                                        new GetPingServerJboss(getContext()).
                                                pingServerJbossSuccessfulOrNot(getsslSocketFactory2,getHiltPortJboss);

                                if (СтатусРаботыСервера == true) {
                                    String ПолученыйТекущееИмяПользователя = new CoreBinessLogics(getContext())
                                            .МетодПолучениеИмяСистемыДляСменыПользователя(getActivity());

                                MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(getActivity())
                                                .setTitle("Смена данных")
                                                .setMessage("Данные будут удалены" + "\n"
                                                        + " (текущий пользователь : ) " + ПолученыйТекущееИмяПользователя.toUpperCase())
                                                 .setIcon(R.drawable.icon_dsu1_web_success)
                                             .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent Интент_Меню = new Intent();
                                        try {
                                            // TODO: 24.04.2023  запуск смены Пользоватедя Данные
                                            ProgressDialog prograssbarСменаДанныхПользователя;
                                            prograssbarСменаДанныхПользователя = new ProgressDialog(getActivity());
                                            prograssbarСменаДанныхПользователя.setTitle("Смена данных");
                                            prograssbarСменаДанныхПользователя.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                            prograssbarСменаДанныхПользователя.setProgress(0);
                                            prograssbarСменаДанныхПользователя.setCanceledOnTouchOutside(false);
                                            prograssbarСменаДанныхПользователя.setMessage("в процессе...");
                                            prograssbarСменаДанныхПользователя.show();

                                            try{





                                                Class_Clears_Tables class_clears_tables=     new Class_Clears_Tables(getActivity(),
                                                        handlerAsync,
                                                        prograssbarСменаДанныхПользователя);

                                                  class_clears_tables.методСменаДанныхПользователя(getContext(),getActivity() );



                                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");


                                            } catch (Exception e) {
                                                e.printStackTrace();
                                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                                                        + " Линия  :"
                                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                                            }
                                            Log.d(this.getClass().getName(), "\n" + " class " +
                                                    Thread.currentThread().getStackTrace()[2].getClassName()
                                                    + "\n" +
                                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                                                    + " Линия  :"
                                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        }

                                    }
                                })
                                        .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                dialog.cancel();
                                                dialog.dismiss();
                                                Log.d(this.getClass().getName(), "\n" + " class " +
                                                        Thread.currentThread().getStackTrace()[2].getClassName()
                                                        + "\n" +
                                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                            }
                                        })
                                        .setIcon(R.drawable.icon_change_user1);

// TODO: 22.08.2023
                                if(    DialogBox==null ){
                                    DialogBox=  materialAlertDialogBuilder.show();
                                }else {
                                    if(!DialogBox.isShowing()){
                                        DialogBox=  materialAlertDialogBuilder.show();
                                    }
                                }

                                } else {
                                    Toast.makeText(getContext(), "Для смены данных, нужно подключение к серверу !!! "
                                            + "\n", Toast.LENGTH_LONG).show();
                                }

                                Log.d(this.getClass().getName(), "\n" + " class " +
                                        Thread.currentThread().getStackTrace()[2].getClassName()
                                        + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new RecordNewErros(getActivity()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }

                        }
                    });


                }



//TODO END CLASS SubClassChangeDataUsers//TODO END CLASS SubClassChangeDataUsers//TODO END CLASS SubClassChangeDataUsers
            }//TODO END CLASS SubClassChangeDataUsers//TODO END CLASS SubClassChangeDataUsers


            // TODO: 23.08.2023 \ Class EntityMaterialBinary Обновление ПО
            class  ClassUpdatePO{
           void методОбновлениеПО(){
               try{
               RxView.clicks(КнопкаОбновление)
                       .throttleFirst(3, TimeUnit.SECONDS)
                       .filter(s -> !s.toString().isEmpty())
                       .map(new Function<Unit, Object>() {
                           @Override
                           public Object apply(Unit unit) throws Throwable {
                               Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                       " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                       " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                               return    КнопкаОбновление;
                           }
                       })
                       .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                           @Override
                           public void accept(Throwable throwable) throws Throwable {
                               throwable.printStackTrace();
                               Log.e(getContext().getClass().getName(),
                                       "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                               " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                               new RecordNewErros(getContext()).recordnewerror(throwable.toString(),
                                       this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                       Thread.currentThread().getStackTrace()[2].getLineNumber());
                           }
                       })
                       .onErrorComplete(new Predicate<Throwable>() {
                           @Override
                           public boolean test(Throwable throwable) throws Throwable {
                               throwable.printStackTrace();
                               Log.e(getContext().getClass().getName(),
                                       "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                               " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                               new RecordNewErros(getContext()).recordnewerror(throwable.toString(),
                                       this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                       Thread.currentThread().getStackTrace()[2].getLineNumber());
                               return false;
                           }
                       })
                       .subscribe( GetNameSingleAsync1c-> {



// TODO: 10.07.2023  запуск обновление ПО

// TODO: 10.07.2023  запуск обновление ПО
                               startServiceBootAndAsync.startServiceBootAndAsync("lanchUpdatePO");

                               Log.i(this.getClass().getName(), " Из меню установкаОбновление ПО "
                                       + Thread.currentThread().getStackTrace()[2].getMethodName()
                                       + " время " + new Date().toLocaleString());


                       });


           } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                            + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }


           }
                //TODO END   class  ClassUpdatePO
            }//TODO END   class  ClassUpdatePO


            // TODO: 23.08.2023  КЛАСС EntityMaterialBinary Ошибки
            class ClassTaskError{
                // TODO: 23.08.2023  metod error
                void  методtaskError(){
                    КнопкаОшибки.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {

                                // TODO Запусукаем  Фрагмент Главный Экран
                                BinessLogicLaunchFragmenrError binessLogicLaunchFragmenrError =new BinessLogicLaunchFragmenrError( fragmentManager,getContext());
                                // TODO: 27.03.2024 в зависомсти кто вызвает
                                binessLogicLaunchFragmenrError.     launchErrorFragment(localBinderОбновлениеПО);

                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        }
                    });


                }
//TODO END  class ClassTaskError
            }//TODO END  class ClassTaskError



// TODO: 23.08.2023 КЛАСС ШАБЛОНЫ TASK
 class ClassTaskShablony{
    // TODO: 23.08.2023  metod task Shablony
    void методtaskChablony(){
        КнопкаШаблоны.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent Интент_BackВозвращаемАктивти = new Intent();
                    Интент_BackВозвращаемАктивти.setClass(getContext(), MainActivity_New_Templates.class); //
                    Интент_BackВозвращаемАктивти.setAction("FromFragmentSettings.class");
                    Интент_BackВозвращаемАктивти.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK   );
                    Интент_BackВозвращаемАктивти.putExtra("ЗапускШаблоновFaceAppБлокировкаКнопкиДа", true);
                     startActivity(Интент_BackВозвращаемАктивти);
                    Log.d(this.getClass().getName(), "" +
                            "                     case R.id.шабоны:");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }
        });
    }


}


// TODO: 04.10.2023  binder class
          //TODO end class ClassAllTaskButtons
        }//TODO end class ClassAllTaskButtons

    }//TODO end Buniceess Lofic for Activity




class BindingSoftwareUpdatePO{


    // TODO: 03.10.2023  метод когда не биндинга
     void getbindingSoftwareUpdatePO(         ) {
        try {
            Boolean asBoolenОбновлениеПО = null;
                connectionОбновлениеПО = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        try {
                            if (service.isBinderAlive()) {
                                // TODO: 28.07.2023  Update
                                localBinderОбновлениеПО = (ServiceUpdatesPO.localBinderОбновлениеПО) service;
                            }

                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"
                                    + "localBinderОбновлениеПО " + localBinderОбновлениеПО);

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }


                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        try {
                            localBinderОбновлениеПО = null;
                            Log.i(getContext().getClass().getName(), "    onServiceDisconnected  binder.isBinderAlive()");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                };
                Intent intentЗапускСлужбыОбновлениеПО = new Intent(getContext(), ServiceUpdatesPO.class);
                intentЗапускСлужбыОбновлениеПО.setAction("com.ServiceUpdatesPO");
                asBoolenОбновлениеПО = getContext().bindService(intentЗапускСлужбыОбновлениеПО, connectionОбновлениеПО, Context.BIND_AUTO_CREATE);
            // TODO: 28.04.2023
            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " asBoolenОбновлениеПО " + asBoolenОбновлениеПО);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
        }

    }




}

// TODO: 10.03.2025  END
}