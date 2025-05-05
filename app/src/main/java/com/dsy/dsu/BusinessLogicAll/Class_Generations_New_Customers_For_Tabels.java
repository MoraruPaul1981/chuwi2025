package com.dsy.dsu.BusinessLogicAll;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dsy.dsu.CnangeServers.PUBLIC_CONTENT;
import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;
import com.dsy.dsu.Hilt.Sqlitehilt.AppModuleSQLlite;

import java.util.Date;

import dagger.hilt.EntryPoints;


public class Class_Generations_New_Customers_For_Tabels {

    Context context;
    ///
    private SQLiteDatabase sqLiteDatabase ;

    public Class_Generations_New_Customers_For_Tabels(Context context) {

        this.context =context;

        // TODO: 16.04.2025
        sqLiteDatabase = EntryPoints.get(context, AppModuleSQLlite.class).getAppModuleSQLlite();
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " sqLiteDatabase " +sqLiteDatabase);
    }


    // TODO: 22.09.2021   ---launch   fio


    // TODO: 26.03.2021 финальная вствка данных новго сотружника

    public Long МетодЗаписиСозданогоСотрудникаВБазуПоТаблицы_ФИО(ContentValues АдаптерДляСозданиеНовогоСотрудаТАблицаФИО, Activity activity) {

        ///todo САМА ВСТВКА ТАБЛИЦА ФИО

        ////
        String ТекущаяТаблицаОбработки = "fio";
//////

        Long РезультатВставкиНовогоТабеляЧерезКонтрейнерТаблицыФИО = 0l;
        try {

            ///TODO  КОНЕЦ сами данные таблица FIO только при вставке данных и ТОЛЬКО

            ///todo САМА ВСТВКА ТАБЛИЦА ФИО


            // TODO: 25.03.2021 вставка фио
           РезультатВставкиНовогоТабеляЧерезКонтрейнерТаблицыФИО = new Class_MODEL_synchronized(activity).
                    ВставкаДанныхЧерезКонтейнерТолькоПриСозданииНовогоСотрудникаУниверсальная(ТекущаяТаблицаОбработки,
                            АдаптерДляСозданиеНовогоСотрудаТАблицаФИО  );



            // TODO: 25.03.2021 успешное вставка в таблицу ФИО


            // TODO: 22.04.2021  srart JOBschedele
            Log.d(this.getClass().getName(), "РезультатВставкиНовогоТабеляЧерезКонтрейнерТаблицыФИО "+РезультатВставкиНовогоТабеляЧерезКонтрейнерТаблицыФИО);



///TODO метод запуска формы после вставки

                    //////


                    ///////////////////////////todo не был создан сотрудник за за ТОГО ЧТО НЕ БЫЛА ВИБРАНА ОРГАНИЗЦИЯ В НАСТРОЙКАХ





            АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.clear();

                // UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника=null;


                //////////////////////////////////
                Log.d(this.getClass().getName(), "РезультатВставкиНовогоТабеляЧерезКонтрейнерТаблицыФИО  " + РезультатВставкиНовогоТабеляЧерезКонтрейнерТаблицыФИО);

                // TODO: 25.03.2021 вставка таблицы ТАБЕЛЬ ПОСЛЕ ТАБЛИЦЫ ФИО




        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(activity).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return  РезультатВставкиНовогоТабеляЧерезКонтрейнерТаблицыФИО;
    }
























    // TODO: 22.09.2021  -----data_tabels




// TODO: 26.03.2021 финальная вствка данных новго сотружника

    public Long МетодЗаписиСозданогоСотрудникаВБазуПоТаблицы_Дата_Табеля(
            ContentValues АдаптерДляСозданиеНовогоСотрудаТАблицаТабель
            , Activity activity,
            int ГодПриВставкеНовогоСотрудника
            , int МЕсяцПриВставкеНовогоСотрудника, Long UUIDgenetarForData_tabels) {


        long РезультатВставкиНовогоТабеляЧерезКонтрейнерТаблицыТабель = 0;
        Integer Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы = 0;
        try{
            String ТекущаяОбрабатываемаяТаблица="data_tabels";
            // TODO: 25.03.2021 вставка табель
            РезультатВставкиНовогоТабеляЧерезКонтрейнерТаблицыТабель = new Class_MODEL_synchronized(activity).
                    ВставкаДанныхЧерезКонтейнерТолькоПриСозданииНовогоСотрудникаУниверсальная(ТекущаяОбрабатываемаяТаблица,
                            АдаптерДляСозданиеНовогоСотрудаТАблицаТабель  );

            Log.d(this.getClass().getName(), "РезультатВставкиНовогоТабеляЧерезКонтрейнерТаблицыТабель "+РезультатВставкиНовогоТабеляЧерезКонтрейнерТаблицыТабель);
            if (РезультатВставкиНовогоТабеляЧерезКонтрейнерТаблицыТабель>0) {
                Class_GRUD_SQL_Operations class_grud_sql_operationsУзнаемСохраненыйРежимРаботыССетью;

                String  РезультатКакойРежимЗаписанвБазеВЫходныеДни=new String();
                // TODO: 24.05.2021 ТРЕТИЙ КОД ЕСЛИ ПОЛЬЗОВАТЕЛЬ ЗАХОДТЕ АВТОМАТИЧЕСКОЙ УСВТУКУ В ВЫХОДЫНЕ ДНИ
                class_grud_sql_operationsУзнаемСохраненыйРежимРаботыССетью=new Class_GRUD_SQL_Operations(activity);

                class_grud_sql_operationsУзнаемСохраненыйРежимРаботыССетью.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","SuccessLogin");
                class_grud_sql_operationsУзнаемСохраненыйРежимРаботыССетью.concurrentHashMapНабор.put("СтолбцыОбработки","mode_weekend");

                // TODO: 12.10.2021  Ссылка Менеджер Потоков
                PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new PUBLIC_CONTENT(activity);
                // TODO: 02.09.2021 exe sql
                SQLiteCursor КурсорУзнаемСохраненыйРежимРаботыССетью= (SQLiteCursor) class_grud_sql_operationsУзнаемСохраненыйРежимРаботыССетью.
                        new GetData(activity).getdata(class_grud_sql_operationsУзнаемСохраненыйРежимРаботыССетью.concurrentHashMapНабор,
                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabase);

                Log.d(this.getClass().getName(), "GetData " +КурсорУзнаемСохраненыйРежимРаботыССетью );
                if (КурсорУзнаемСохраненыйРежимРаботыССетью.getCount() > 0) {
                    КурсорУзнаемСохраненыйРежимРаботыССетью.moveToFirst();
                    РезультатКакойРежимЗаписанвБазеВЫходныеДни = КурсорУзнаемСохраненыйРежимРаботыССетью.getString(0);
                    Log.d(activity.getClass().getName(), " РезультатКакойРежимЗаписанвБазеВЫходныеДни  " + "--" +РезультатКакойРежимЗаписанвБазеВЫходныеДни);/////
                // TODO: 24.05.2021 КОД ДЛЯ АВТОМАТИЧЕСКОГО ВЫСТАВЛЕНИЯ ВЫХОДНЫХ ДНЕЙ В ТАБЕЛЬ
                if (РезультатКакойРежимЗаписанвБазеВЫходныеДни.contentEquals("Включить")) {
                    ContentValues КонтрейнерДляВставкиВВыходныеДниМЕткиВыходные=
                            new Class_MODEL_synchronized(activity). МетодВычисляемВыходныеДниПриСозданииНовогоТабеляАвтоРЕжим(activity,
                            ГодПриВставкеНовогоСотрудника,МЕсяцПриВставкеНовогоСотрудника);
                    Log.w(activity.getClass().getName(), " КонтрейнерДляВставкиВВыходныеДниМЕткиВыходные  " + "--" +КонтрейнерДляВставкиВВыходныеДниМЕткиВыходные);/////
                        // TODO: 25.03.2021 вставка табель
                    Integer          РезультатВставкиВЫходнихДнейЧерезКонтрейнерТаблицыТабель  = new Class_MODEL_synchronized(activity)
                            .ЛокальногоОбновлениеДанныхЧерезКонтейнерУниверсальная(ТекущаяОбрабатываемаяТаблица,
                                КонтрейнерДляВставкиВВыходныеДниМЕткиВыходные,
                                UUIDgenetarForData_tabels,
                                "uuid");

                        Log.d(activity.getClass().getName(), " РезультатВставкиВЫходнихДнейЧерезКонтрейнерТаблицыТабель  " + "--" +РезультатВставкиВЫходнихДнейЧерезКонтрейнерТаблицыТабель);/////

                }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(activity).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РезультатВставкиНовогоТабеляЧерезКонтрейнерТаблицыТабель;
    }











}
