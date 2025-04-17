package com.dsy.dsu.Dashboard.View;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import com.dsy.dsu.BootAndAsync.Model.DowloadUpdatePO.DownLoadPO;


import com.dsy.dsu.BootAndAsync.Model.EventsBus.MessageEvensBusNetworkStatuses;
import com.dsy.dsu.BootAndAsync.Model.EventsBus.MessageEvensBusUpdatePO;
import com.dsy.dsu.BootAndAsync.Model.BinesslogicActivityBoot.GetComponentActivityBootService;

import com.dsy.dsu.BroadcastRecievers.Bl.RegisterBroadcastForWorkManager;

import com.dsy.dsu.Dashboard.Model.bl_launchFragmentSettingsandDashbord.LaunchActivityDashboard;
import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;

import com.dsy.dsu.FirebaseAndOneSignal.OneSignal.StartigOneSignal.GetStartingRegistraziyOneSIgnalAndFireBase;
import com.dsy.dsu.Hilt.getSSLSocketFactory2.QualifiergetsslSocketFactory2;
import com.dsy.dsu.R;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Date;

import javax.inject.Inject;
import javax.net.ssl.SSLSocketFactory;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity_Dashboard extends AppCompatActivity {

    @Inject
    SQLiteDatabase sqlite;

    @Inject
    RegisterBroadcastForWorkManager registerBroadcastForWorkManager;

    @Inject
    GetStartingRegistraziyOneSIgnalAndFireBase getStartingRegistraziyOneSIgnalAndFireBase;

    private   Activity activity;

    private  BuniccessLogicaActivityDashboard buniccessLogicaActivityDashboard;


    public static final int ALL_PERSSION_CODE=1;
    private FragmentManager fragmentManager;

    private SharedPreferences preferences;






    @Inject
    @QualifiergetsslSocketFactory2
    SSLSocketFactory getsslSocketFactory2;

    protected GetComponentActivityBootService blInnerMainActivityBootAndAsync;
    // TODO: 03.11.2022 FaceApp
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_dashboard);
            FragmentContainerView fragmentContainerView=   (FragmentContainerView) findViewById(R.id.linearLayout_root_activity_dashboard);
            activity = this;
            getSupportActionBar().hide();
            fragmentManager =  getSupportFragmentManager();
            preferences=   getApplicationContext() .getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
            // TODO: 04.10.2023 разрешения для всего


            registeEventBusFirst();


            // TODO: 15.08.2023 Начинается Пользовательский КОд
            buniccessLogicaActivityDashboard=new BuniccessLogicaActivityDashboard();
            // TODO: 29.09.2023 Статус Повтороной Синхрониазции
            buniccessLogicaActivityDashboard.  методЗаписываемПовторныйЭтапСинхрогниазции( );

            buniccessLogicaActivityDashboard.  strartigWorkManger();
            buniccessLogicaActivityDashboard.  strartigOneSignal();
            // TODO: 28.09.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
        }
    }




    @Override
    protected void onStart() {
        super.onStart();
        try {
            LaunchActivityDashboard launchActivityDashboard=new LaunchActivityDashboard( fragmentManager,getApplicationContext());
            // TODO: 27.03.2024 в зависомсти кто вызвает
                launchActivityDashboard.     launchADashboardFragment();

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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        try{

            unregisterEventBusFirst();
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
        }
    }

    // TODO: 15.08.2023 НачинаетсяБизнеЛОгика Активтив Dashboard
    // TODO: 15.08.2023 НачинаетсяБизнеЛОгика Активтив Dashboard
    // TODO: 15.08.2023 НачинаетсяБизнеЛОгика Активтив Dashboard
    // TODO: 15.08.2023 НачинаетсяБизнеЛОгика Активтив Dashboard
    // TODO: 15.08.2023 НачинаетсяБизнеЛОгика Активтив Dashboard
    // TODO: 15.08.2023 НачинаетсяБизнеЛОгика Активтив Dashboard

    public class BuniccessLogicaActivityDashboard {
        private void strartigWorkManger() {
            try{

                registerBroadcastForWorkManager.statingPublicWorkMAnager(getApplicationContext());

                // TODO: 25.03.2023
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        private void strartigOneSignal() {
            try{

                getStartingRegistraziyOneSIgnalAndFireBase.getStartingRegistraziyOneSignalFirebase();

                // TODO: 25.03.2023
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        private void методЗаписываемПовторныйЭтапСинхрогниазции( ) {
            try {
                // TODO: 02.08.2023 БИЗНЕС КОД
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("РежимЗапускаСинхронизации", "ПовторныйЗапускСинхронизации");
                editor.apply();
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







        // TODO: 20.03.2025  статусы сети
        public void getEventBusNetworkStatuses(@NonNull MessageEvensBusNetworkStatuses messageEvensBusNetworkStatuses,
                                               @NonNull Context context){
            try{
                Bundle bundleGetOtServicePrograssBar =(Bundle)         messageEvensBusNetworkStatuses.mess.getExtras();
                String Статус=   bundleGetOtServicePrograssBar.getString("Статус");


                if(Статус.contains(    "ServerJbosOff")){
                    Toast.makeText(context,     "Сервер выкл.!!!"    , Toast.LENGTH_LONG).show();

                }

                // TODO: 26.12.2022  конец основгого кода
                Log.d(context.getClass().getName(), "\n" + " class "
                        + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " Статус " +Статус);


            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }





        public void getEventBusUpdatePo(@NonNull MessageEvensBusUpdatePO messageEvensBusUpdatePO  ){

            try{
                Bundle bundleGetOtServiceUpdatePO =(Bundle)         messageEvensBusUpdatePO.mess.getExtras();
                String Статус=   bundleGetOtServiceUpdatePO.getString("Статус");
                Integer СервернаяВерсия=   bundleGetOtServiceUpdatePO.getInt("СервернаяВерсия");


                if(Статус.contains(    "LastVersionUpdatePO")){
                    Toast.makeText(getApplicationContext(),     "Последняя версия ПО !!! "+"("+СервернаяВерсия+")"    , Toast.LENGTH_LONG).show();

                }




                if(Статус.contains(    "UpdateProcessorPO")){
                    //Toast.makeText(getApplicationContext(),     "Идет Обновление ПО..."    , Toast.LENGTH_LONG).show();

                    Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    v2.vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE));

                    // TODO: 22.01.2024
                    DownLoadPO downLoadPO=new DownLoadPO(activity,getApplicationContext(),СервернаяВерсия,getsslSocketFactory2);

                    downLoadPO.МетодСообщениеАнализПО( );
                    // TODO: 26.12.2022  конец основгого кода
                    Log.d(getApplicationContext().getClass().getName(), "\n" + " class "
                            + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                }



                // TODO: 26.12.2022  конец основгого кода
                Log.d(getApplicationContext().getClass().getName(), "\n" + " class "
                        + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }









//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic
    }//TODO END BUNIVEESS Logic //TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic

    private void registeEventBusFirst() {

        if (  !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                + "   starting... onRestart" + " starting... onRestart");
    }

    private void unregisterEventBusFirst() {
        if (  EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                + "   starting... onRestart" + " starting... onRestart");
    }


    // TODO: 23.01.2024 EventBus for Async
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void EventMessageEvensBusAyns(MessageEvensBusNetworkStatuses messageEvensBusNetworkStatuses){
        try{

                    buniccessLogicaActivityDashboard .getEventBusNetworkStatuses(messageEvensBusNetworkStatuses,getApplicationContext());

            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + "   starting... onRestart" + " starting... onRestart");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    // TODO: 23.01.2024 EventBus for Update PO
    @Subscribe (threadMode = ThreadMode.MAIN)
    public void EventMessageEvensBusUpdatePO(MessageEvensBusUpdatePO messageEvensBusUpdatePO){
        try{


            buniccessLogicaActivityDashboard.   getEventBusUpdatePo(messageEvensBusUpdatePO);

            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + "   starting... onRestart" + " starting... onRestart");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }


    }

    // TODO: 20.03.2025 end class
}


