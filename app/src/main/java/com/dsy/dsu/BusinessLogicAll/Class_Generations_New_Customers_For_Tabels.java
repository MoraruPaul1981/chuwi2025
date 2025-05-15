package com.dsy.dsu.BusinessLogicAll;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.util.Log;

import com.dsy.dsu.BusinessLogicAll.CoreBinessLogics.CoreBinessLogics;
import com.dsy.dsu.CnangeServers.BinessLogicPublicContent;
import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;

import java.util.Date;


public class Class_Generations_New_Customers_For_Tabels {

    Context context;
    ///


    public Class_Generations_New_Customers_For_Tabels(Context context) {
        this.context =context;
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  );
    }


    // TODO: 22.09.2021   ---launch   fio


    // TODO: 26.03.2021 финальная вствка данных новго сотружника

    public Integer МетодЗаписиСозданогоСотрудникаВБазуПоТаблицы_ФИО(ContentValues АдаптерДляСозданиеНовогоСотрудаТАблицаФИО,
                                                                    Activity activity) {
        ///todo САМА ВСТВКА ТАБЛИЦА ФИО
        Integer getcreatingAnewEmployee = 0;
        try {
            String ТекущаяТаблицаОбработки = "fio";


            // TODO: 25.03.2021 вставка фио
            getcreatingAnewEmployee = new CoreBinessLogics(activity,sqLiteDatabase).
                    ВставкаДанныхЧерезКонтейнерТолькоПриСозданииНовогоСотрудникаУниверсальная(ТекущаяТаблицаОбработки,
                            АдаптерДляСозданиеНовогоСотрудаТАблицаФИО  );



            // TODO: 30.01.2022 Сообщеам Observer что изменилибьс данные в адаптере AdapterCursor
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " getcreatingAnewEmployee"
                    +getcreatingAnewEmployee);



        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(activity).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return  getcreatingAnewEmployee;
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
            РезультатВставкиНовогоТабеляЧерезКонтрейнерТаблицыТабель = new CoreBinessLogics(activity,sqLiteDatabase).
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
                BinessLogicPublicContent Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new BinessLogicPublicContent(activity);
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
                            new CoreBinessLogics(activity,sqLiteDatabase). МетодВычисляемВыходныеДниПриСозданииНовогоТабеляАвтоРЕжим(activity,
                            ГодПриВставкеНовогоСотрудника,МЕсяцПриВставкеНовогоСотрудника);
                    Log.w(activity.getClass().getName(), " КонтрейнерДляВставкиВВыходныеДниМЕткиВыходные  " + "--"
                            +КонтрейнерДляВставкиВВыходныеДниМЕткиВыходные);/////
                        // TODO: 25.03.2021 вставка табель
                    Integer          РезультатВставкиВЫходнихДнейЧерезКонтрейнерТаблицыТабель  = new CoreBinessLogics(activity,sqLiteDatabase)
                            .ЛокальногоОбновлениеДанныхЧерезКонтейнерУниверсальная(ТекущаяОбрабатываемаяТаблица,
                                КонтрейнерДляВставкиВВыходныеДниМЕткиВыходные,
                                UUIDgenetarForData_tabels,
                                "uuid");

                        Log.d(activity.getClass().getName(), " РезультатВставкиВЫходнихДнейЧерезКонтрейнерТаблицыТабель  " + "--"
                                +РезультатВставкиВЫходнихДнейЧерезКонтрейнерТаблицыТабель);/////

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
