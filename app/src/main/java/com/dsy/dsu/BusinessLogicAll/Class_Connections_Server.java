package com.dsy.dsu.BusinessLogicAll;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.loader.content.AsyncTaskLoader;

import com.dsy.dsu.BusinessLogicAll.CoreBinessLogics.CoreBinessLogics;
import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;
import com.dsy.dsu.Hilt.JbossAdrress.getHiltPortJbossInterface;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.LinkedHashMap;

import javax.net.ssl.SSLSocketFactory;

import dagger.hilt.EntryPoints;


public class Class_Connections_Server  {
    private Context context1;
    private  Class_GRUD_SQL_Operations class_grud_sql_operations=null;
    private SharedPreferences preferences;
    private AsyncTaskLoader<Boolean> asyncTaskLoader;
    public Class_Connections_Server( ) {
        class_grud_sql_operations=new Class_GRUD_SQL_Operations(context1);
    }
    ///////// TODO ПРОВЕРЯЕТ ЕСЛИ ПОДКЛЧБЕНИ В ИНТРЕНТУ










    ///////// TODO ПРОВЕРЯЕТ ЕСЛИ ПОДКЛЧБЕНИ В ИНТРЕНТУ
    public Boolean pingServerJbossSuccessfulOrNot(@NotNull Context context,
                                                  @NotNull SSLSocketFactory getsslSocketFactory2) {
         Boolean результатПрозвонаСокетом = false;
        try {
            LinkedHashMap<Integer,String> getHiltPortJboss=   EntryPoints.get(context, getHiltPortJbossInterface.class).getHiltPortJboss();
            // TODO: 02.04.2024  цикл пинг
                Integer   ИмяПорта =    getHiltPortJboss.entrySet().stream().mapToInt(m->m.getKey()).findAny().getAsInt();
                String     ИмяСервера=       getHiltPortJboss.entrySet().stream().map(m->m.getValue()).findAny().get();
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " ИмяСервера"+ ИмяСервера+" ИмяПорта "+ИмяПорта);


// TODO: 12.01.2024  производим пинг через 3 попытки
                // TODO: 22.12.2022  сама запуска синхронищации из workmanager ОБЩЕГО
                // TODO: 22.12.2022  сама запуска синхронищации из workmanager ОБЩЕГО
                boolean ВыбранныйРежимСети =
                        new GetConnectivityManagerAndroid(context).сonnectivityManageruserselection();

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " lВыбранныйРежимСети" + ВыбранныйРежимСети);

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " lВыбранныйРежимСети" + ВыбранныйРежимСети);


                Long  БуферПолучениеДанныхРЕальныйСтатусРАботыSQLServer= 0l;

                if (ВыбранныйРежимСети==true) {
                    // TODO: 13.01.2025  
                    БуферПолучениеДанныхРЕальныйСтатусРАботыSQLServer = pingingJbossServer(context, getsslSocketFactory2, ИмяПорта, ИмяСервера);
                }


                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                        + " БуферПолучениеДанныхРЕальныйСтатусРАботыSQLServer[0] " +БуферПолучениеДанныхРЕальныйСтатусРАботыSQLServer );

                // TODO: 16.12.2021  положитльеный результат пинга
                if ( БуферПолучениеДанныхРЕальныйСтатусРАботыSQLServer>0) {
                    результатПрозвонаСокетом = true;



                    Log.d(this.getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " ИмяСервера" + ИмяСервера+ "ИмяПорта " +ИмяПорта);

                }else{
                    результатПрозвонаСокетом = false;
                    Log.e(CoreBinessLogics.class.getName(), " ОШИБКА НЕТ СВЯЗИ С СЕВРЕРОМ  результатПрозвонаСокетом[0] " + результатПрозвонаСокетом);
                }

                //todo old code

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                        + " БуферПолучениеДанныхРЕальныйСтатусРАботыSQLServer[0] " +БуферПолучениеДанныхРЕальныйСтатусРАботыSQLServer );

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                    Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return результатПрозвонаСокетом;
    }


    // TODO: 12.01.2024 метод пинга с тремя попытками
    private Long pingingJbossServer(@androidx.annotation.NonNull Context КонтекстКоторыйДляСинхронизации,
                                    @androidx.annotation.NonNull SSLSocketFactory getsslSocketFactory2,
                                    Integer ИмяПорта, String ИмяСервера) {
        // TODO: 12.01.2024
      Long  БуферПолучениеДанныхРЕальныйСтатусРАботыSQLServer =0l ;
        try{
        // TODO: 12.01.2024
            // TODO: 10.11.2022  пинг к сервера
            БуферПолучениеДанныхРЕальныйСтатусРАботыSQLServer =
                    new CoreBinessLogics(КонтекстКоторыйДляСинхронизации).
                            МетодУниверсальногоПинга(new String(), "application/gzip",
                                    "Хотим Получить Статус Реальной Работы SQL SERVER"
                                    ,0l,
                                    0
                                    , ИмяСервера, ИмяПорта, getsslSocketFactory2);//application/gzip

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " ИмяСервера"+ ИмяСервера +" ИмяПорта "+ИмяПорта+
                    " БуферПолучениеДанныхРЕальныйСтатусРАботыSQLServer " + БуферПолучениеДанныхРЕальныйСтатусРАботыSQLServer);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                    Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(КонтекстКоторыйДляСинхронизации).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return   БуферПолучениеДанныхРЕальныйСтатусРАботыSQLServer;
    }



    ///////// TODO ПРОВЕРЯЕТ ЕСЛИ ПОДКЛЧБЕНИ В ИНТРЕНТУ
    public Boolean pingServerJbossSuccessfulOrNot(@NotNull Context context,
                                                  @NotNull SSLSocketFactory getsslSocketFactory2,   @NotNull LinkedHashMap<Integer,String> getHiltPortJboss) {
        Boolean результатПрозвонаСокетом = false;
        try {
            // TODO: 02.04.2024  цикл пинг
            Integer   ИмяПорта =    getHiltPortJboss.entrySet().stream().mapToInt(m->m.getKey()).findAny().getAsInt();
            String     ИмяСервера=       getHiltPortJboss.entrySet().stream().map(m->m.getValue()).findAny().get();
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " ИмяСервера"+ ИмяСервера+" ИмяПорта "+ИмяПорта);


// TODO: 12.01.2024  производим пинг через 3 попытки
            // TODO: 22.12.2022  сама запуска синхронищации из workmanager ОБЩЕГО
            // TODO: 22.12.2022  сама запуска синхронищации из workmanager ОБЩЕГО
            boolean ВыбранныйРежимСети =
                    new GetConnectivityManagerAndroid(context).сonnectivityManageruserselection();

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " lВыбранныйРежимСети" + ВыбранныйРежимСети);

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " lВыбранныйРежимСети" + ВыбранныйРежимСети);


            Long  БуферПолучениеДанныхРЕальныйСтатусРАботыSQLServer= 0l;

            if (ВыбранныйРежимСети==true) {
                // TODO: 13.01.2025
                БуферПолучениеДанныхРЕальныйСтатусРАботыSQLServer = pingingJbossServer(context, getsslSocketFactory2, ИмяПорта, ИмяСервера);
            }


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                    + " БуферПолучениеДанныхРЕальныйСтатусРАботыSQLServer[0] " +БуферПолучениеДанныхРЕальныйСтатусРАботыSQLServer );

            // TODO: 16.12.2021  положитльеный результат пинга
            if ( БуферПолучениеДанныхРЕальныйСтатусРАботыSQLServer>0) {
                результатПрозвонаСокетом = true;



                Log.d(this.getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " ИмяСервера" + ИмяСервера+ "ИмяПорта " +ИмяПорта);

            }else{
                результатПрозвонаСокетом = false;
                Log.e(CoreBinessLogics.class.getName(), " ОШИБКА НЕТ СВЯЗИ С СЕВРЕРОМ  результатПрозвонаСокетом[0] " + результатПрозвонаСокетом);
            }

            //todo old code

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                    + " БуферПолучениеДанныхРЕальныйСтатусРАботыSQLServer[0] " +БуферПолучениеДанныхРЕальныйСтатусРАботыSQLServer );

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                    Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return результатПрозвонаСокетом;
    }
    // TODO: 21.03.2025 end class
}
