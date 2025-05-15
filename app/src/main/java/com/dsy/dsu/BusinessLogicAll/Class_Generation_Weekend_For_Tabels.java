package com.dsy.dsu.BusinessLogicAll;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.util.Log;

import com.dsy.dsu.BusinessLogicAll.CoreBinessLogics.CoreBinessLogics;
import com.dsy.dsu.CnangeServers.BinessLogicPublicContent;
import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;

import java.util.Date;


public class Class_Generation_Weekend_For_Tabels {
    Context context;
    public Class_Generation_Weekend_For_Tabels(Context context) {
        this.context =context;
        // TODO: 16.04.2025
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() );
    }


    /////////////////////////////
    // TODO: 24.05.2021 метод автоматической вставки выходных дней
    public Integer МетодТретийАвтоматическаяВставкаВыходныхДней(Long UUIDGeneratorINset, int ГодПриВставкеНовогоСотрудника, int МЕсяцПриВставкеНовогоСотрудника) {
        // TODO: 24.05.2021 КОД ДЛЯ АВТОМАТИЧЕСКОГО ВЫСТАВЛЕНИЯ ВЫХОДНЫХ ДНЕЙ В ТАБЕЛЬ
        Integer РезультатВставкиВЫходнихДнейЧерезКонтрейнерТаблицыТабель = 0;
        try {
            Class_GRUD_SQL_Operations concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийВставкиВыходныхДней;
            String  РезультатКакойРежимЗаписанвБазеВЫходныеДни=new String();
            // TODO: 24.05.2021 ТРЕТИЙ КОД ЕСЛИ ПОЛЬЗОВАТЕЛЬ ЗАХОДТЕ АВТОМАТИЧЕСКОЙ УСВТУКУ В ВЫХОДЫНЕ ДНИ
            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийВставкиВыходныхДней=new Class_GRUD_SQL_Operations(context);
            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийВставкиВыходныхДней.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","SuccessLogin");
            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийВставкиВыходныхДней.concurrentHashMapНабор.put("СтолбцыОбработки","mode_weekend");
            // TODO: 12.10.2021  Ссылка Менеджер Потоков
            BinessLogicPublicContent Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new BinessLogicPublicContent(context);
            // TODO: 02.09.2021 exe sql
            SQLiteCursor КурсорУзнаемСохраненыйРежимРаботыССетью= (SQLiteCursor)  concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийВставкиВыходныхДней.
                    new GetData(context).getdata(concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийВставкиВыходныхДней.
                            concurrentHashMapНабор,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabase);
            Log.d(this.getClass().getName(), "GetData " +КурсорУзнаемСохраненыйРежимРаботыССетью );
            //////
            if (КурсорУзнаемСохраненыйРежимРаботыССетью.getCount() > 0) {
                КурсорУзнаемСохраненыйРежимРаботыССетью.moveToFirst();
                РезультатКакойРежимЗаписанвБазеВЫходныеДни = КурсорУзнаемСохраненыйРежимРаботыССетью.getString(0);
                ///
                Log.d(context.getClass().getName(), " РезультатКакойРежимЗаписанвБазеВЫходныеДни  " + "--" +РезультатКакойРежимЗаписанвБазеВЫходныеДни);/////
            }
            Log.d(this.getClass().getName()," РезультатКакойРежимЗаписанвБазеВЫходныеДни " + РезультатКакойРежимЗаписанвБазеВЫходныеДни);
            // TODO: 24.05.2021 КОД ДЛЯ АВТОМАТИЧЕСКОГО ВЫСТАВЛЕНИЯ ВЫХОДНЫХ ДНЕЙ В ТАБЕЛЬ
            if (РезультатКакойРежимЗаписанвБазеВЫходныеДни.contentEquals("Включить")) {
                // TODO: 24.05.2021 вычисляем дни
                ContentValues КонтрейнерДляВставкиВВыходныеДниМЕткиВыходные = new CoreBinessLogics(context)
                        .МетодВычисляемВыходныеДниПриСозданииНовогоТабеляАвтоРЕжим(context,
                                МЕсяцПриВставкеНовогоСотрудника , ГодПриВставкеНовогоСотрудника);
                // TODO: 24.05.2021 сама вставка  выходних дней
                Log.d(this.getClass().getName()," КонтрейнерДляВставкиВВыходныеДниМЕткиВыходные " + КонтрейнерДляВставкиВВыходныеДниМЕткиВыходные);

                // TODO: 25.03.2021 вставка табель
                РезультатВставкиВЫходнихДнейЧерезКонтрейнерТаблицыТабель = new CoreBinessLogics(context)
                        .ЛокальногоОбновлениеДанныхЧерезКонтейнерУниверсальная("data_tabels",
                                КонтрейнерДляВставкиВВыходныеДниМЕткиВыходные,
                                UUIDGeneratorINset,
                                "uuid");
                Log.d(this.getClass().getName()," РезультатВставкиВЫходнихДнейЧерезКонтрейнерТаблицыТабель " + РезультатВставкиВЫходнихДнейЧерезКонтрейнерТаблицыТабель);
                //////
                if (РезультатВставкиВЫходнихДнейЧерезКонтрейнерТаблицыТабель > 0) {
                    //todo ВО ВРЕМЯ СИНХРОНИЗХАЦИИ В ФОНЕ ИЗМЕНЯЕМ ДАТЫ ТАБЛИЦ ТОЛЬКО ПО ТАБЛИЦАМ КОГДА ИДЕМ
                    long Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицыТабель = 0;
                    long Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицыФИОФИНАЛ = 0;
                    // TODO: 03.09.2021 дата дял ТЕКУЩЕЙ ОПЕРАЦИИ
                }
                КонтрейнерДляВставкиВВыходныеДниМЕткиВыходные.clear();
            }
            //////
        } catch (Exception e) {///////ошибки
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(CoreBinessLogics.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return  РезультатВставкиВЫходнихДнейЧерезКонтрейнерТаблицыТабель;
    }

    //функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО ВСЕЙ ПРОГРАММЕ

}
