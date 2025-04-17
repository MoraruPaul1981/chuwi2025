package com.dsy.dsu.BusinessLogicAll;

import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;

import java.util.concurrent.CompletionService;

import javax.annotation.Nonnull;

// TODO: 10.02.2022  данный по д класс ПОВТОРНО ПРОВЕРЯЕТ НЕ ПОЯВИЛЬСЯ ЛИ МЕЖДУ УЧАСНИКАМИ ЧАТА ПУБЛИЧНЫЙ UUID ПРОЦЕСЕ ПЕРЕПИСКИ
public class SubClass_RetryGEtRowInChatsКлассПроверемЕщеРАзПоявилосЛИПуббличныйUUIDМеждуУчасникамиЧата {


    public Long МетодПовторноПроверетНеПовилосьЛиМеждеУчаникамиперепискиПубличныйUUID(
           @NonNull Context context,
         @Nonnull Long ПолученыйIDДляЧата,
           @Nonnull    Integer ПубличныйIDДляФрагмента,
           @Nonnull     CompletionService completionServiceНОваяЗадача,
           @Nonnull      SQLiteDatabase sqLiteDatabaseНоваяЗадача) {

        Long РезультатПроверикПолучлиЛИUUIDМеждуУчасникамиПереписки = 0l;

        try {

            // TODO: 05.07.2021 после успешной вставки новой записи обновляем UI

            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

            Class_GRUD_SQL_Operations class_grud_sql_operations_ДляПовторногоПоискаПуцбличногоUUIDМеждуУчасникамиперпискиЧатаТекущего = new Class_GRUD_SQL_Operations(context);
            ///
//                class_grud_sql_operations_ДанныеДляСообщенийЧатаТретьяЧасть.
//                        concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","viewchat");
//                ///////
//                class_grud_sql_operations_ДанныеДляСообщенийЧатаТретьяЧасть.
//                        concurrentHashMapНабор.put("СтолбцыОбработки","*");
//                //
//      /*          class_grud_sql_operations_ДанныеДляСообщенийЧата. concurrentHashMapНабор.put("ФорматПосика","uuid=?    " +
//                        "AND status_send !=? AND month_tabels=? AND  year_tabels =? AND fio IS NOT NULL ");
//                    ///"_id > ?   AND _id< ?"*/
//                    //////
//
//                class_grud_sql_operations_ДанныеДляСообщенийЧатаТретьяЧасть.
//                        concurrentHashMapНабор.put("ФлагНепотораяемостиСтрок",true);
//                    ///
//            /*      class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска2","Удаленная");
//                    ///
//                    class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);//ФлагНепотораяемостиСтрок
//                    //
//                    class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 .......
//*/
//                ////TODO другие поля*/
//
//                class_grud_sql_operations_ДанныеДляСообщенийЧатаТретьяЧасть.
//                        concurrentHashMapНабор.put("ПоляГрупировки","id_user");
//                ////
//                class_grud_sql_operations_ДанныеДляСообщенийЧатаТретьяЧасть.
//                        concurrentHashMapНабор.put("УсловиеГрупировки"," COUNT(*) >= 1");
//                ////
//                class_grud_sql_operations_ДанныеДляСообщенийЧатаТретьяЧасть.
//                        concurrentHashMapНабор.put("УсловиеСортировки","date_update DESC");
//                ////
//               /// class_grud_sql_operationsMODEL. concurrentHashMapНабор.put("УсловиеЛимита","1");
            ////

            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
            // class_grud_sql_operations_ДанныеДляСообщенийЧатаТретьяЧасть. concurrentHashMapНабор.put("УсловиеПоиска1",111);
            ///
            class_grud_sql_operations_ДляПовторногоПоискаПуцбличногоUUIDМеждуУчасникамиперпискиЧатаТекущего.concurrentHashMapНабор.put("СамFreeSQLКОд",///viewchat

         /*               " SELECT  * FROM viewchat  WHERE     id_user   =" + ID +
                                " ORDER BY  date_update  ASC ;");
*/
                    " SELECT uuid_parent FROM chats   WHERE id_user= " + ПолученыйIDДляЧата + "  AND  user_update=" + ПубличныйIDДляФрагмента + " ORDER BY  date_update  ASC ;");

            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ  вторая операиця
            //////
            SQLiteCursor КурсорДанныеИщемНЕПоявильсяЛиПубличныйUUIDМеджуУчасникамиПереписки = null;

            ////////
            КурсорДанныеИщемНЕПоявильсяЛиПубличныйUUIDМеджуУчасникамиПереписки = (SQLiteCursor) class_grud_sql_operations_ДляПовторногоПоискаПуцбличногоUUIDМеждуУчасникамиперпискиЧатаТекущего.
                    new GetаFreeData(context)
                    .getfreedata(class_grud_sql_operations_ДляПовторногоПоискаПуцбличногоUUIDМеждуУчасникамиперпискиЧатаТекущего
                                    .concurrentHashMapНабор,
                            completionServiceНОваяЗадача,
                            sqLiteDatabaseНоваяЗадача);


            if (КурсорДанныеИщемНЕПоявильсяЛиПубличныйUUIDМеджуУчасникамиПереписки.getCount() > 0) {

                // TODO: 10.02.2022
                КурсорДанныеИщемНЕПоявильсяЛиПубличныйUUIDМеджуУчасникамиПереписки.moveToFirst();


                РезультатПроверикПолучлиЛИUUIDМеждуУчасникамиПереписки = КурсорДанныеИщемНЕПоявильсяЛиПубличныйUUIDМеджуУчасникамиПереписки.getLong(0);
                // TODO: 10.02.2022
                Log.d(this.getClass().getName(), " повторно РезультатПроверикПолучлиЛИUUIDМеждуУчасникамиПереписки " + РезультатПроверикПолучлиЛИUUIDМеждуУчасникамиПереписки + "\n");
            }
            // TODO: 21.03.2022

            КурсорДанныеИщемНЕПоявильсяЛиПубличныйUUIDМеджуУчасникамиПереписки.close();

        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ///


        }


        // TODO: 10.02.2022
        Log.d(this.getClass().getName(), " повторно РезультатПроверикПолучлиЛИUUIDМеждуУчасникамиПереписки " + РезультатПроверикПолучлиЛИUUIDМеждуУчасникамиПереписки + "\n");

        return РезультатПроверикПолучлиЛИUUIDМеждуУчасникамиПереписки;
    }
    // TODO: 21.03.2022  для задачи  проверяем если межуд участиника переписка


    public Long МетодПовторноПроверетНеПовилосьДЛЯЗАДАЧПубличныйUUID(Context context

            , Integer ПолученыйIDДляЧата,
                                                                     Integer   ПубличныйIDДляФрагмента,
                                                                     CompletionService completionServiceНОваяЗадача,
                                                                     SQLiteDatabase sqLiteDatabaseНоваяЗадача) {

        Long РезультатПроверикПолучлиЛИUUIDМеждуУчасникамиПереписки = 0l;

        try {

            // TODO: 05.07.2021 после успешной вставки новой записи обновляем UI

            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

            Class_GRUD_SQL_Operations class_grud_sql_operations_ДляПовторногоПоискаПуцбличногоUUIDМеждуУчасникамиперпискиЧатаТекущего = new Class_GRUD_SQL_Operations(context);
            ///
//                class_grud_sql_operations_ДанныеДляСообщенийЧатаТретьяЧасть.
//                        concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","viewchat");
//                ///////
//                class_grud_sql_operations_ДанныеДляСообщенийЧатаТретьяЧасть.
//                        concurrentHashMapНабор.put("СтолбцыОбработки","*");
//                //
//      /*          class_grud_sql_operations_ДанныеДляСообщенийЧата. concurrentHashMapНабор.put("ФорматПосика","uuid=?    " +
//                        "AND status_send !=? AND month_tabels=? AND  year_tabels =? AND fio IS NOT NULL ");
//                    ///"_id > ?   AND _id< ?"*/
//                    //////
//
//                class_grud_sql_operations_ДанныеДляСообщенийЧатаТретьяЧасть.
//                        concurrentHashMapНабор.put("ФлагНепотораяемостиСтрок",true);
//                    ///
//            /*      class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска2","Удаленная");
//                    ///
//                    class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);//ФлагНепотораяемостиСтрок
//                    //
//                    class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 .......
//*/
//                ////TODO другие поля*/
//
//                class_grud_sql_operations_ДанныеДляСообщенийЧатаТретьяЧасть.
//                        concurrentHashMapНабор.put("ПоляГрупировки","id_user");
//                ////
//                class_grud_sql_operations_ДанныеДляСообщенийЧатаТретьяЧасть.
//                        concurrentHashMapНабор.put("УсловиеГрупировки"," COUNT(*) >= 1");
//                ////
//                class_grud_sql_operations_ДанныеДляСообщенийЧатаТретьяЧасть.
//                        concurrentHashMapНабор.put("УсловиеСортировки","date_update DESC");
//                ////
//               /// class_grud_sql_operationsMODEL. concurrentHashMapНабор.put("УсловиеЛимита","1");
            ////

            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
            // class_grud_sql_operations_ДанныеДляСообщенийЧатаТретьяЧасть. concurrentHashMapНабор.put("УсловиеПоиска1",111);
            ///
            class_grud_sql_operations_ДляПовторногоПоискаПуцбличногоUUIDМеждуУчасникамиперпискиЧатаТекущего.concurrentHashMapНабор.put("СамFreeSQLКОд",///viewchat

         /*               " SELECT  * FROM viewchat  WHERE     id_user   =" + ID +
                                " ORDER BY  date_update  ASC ;");
*/
                    " SELECT uuid FROM notifications   WHERE id_user= " + ПолученыйIDДляЧата + "  AND  user_update = " + ПубличныйIDДляФрагмента + " ORDER BY  date_update  ASC ;");

            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ  вторая операиця
            //////
            SQLiteCursor КурсорДанныеИщемЕслиУжеUUIDМенждуУчастикамиНовойЗАДАЧ_Task = null;

            ////////
            КурсорДанныеИщемЕслиУжеUUIDМенждуУчастикамиНовойЗАДАЧ_Task = (SQLiteCursor) class_grud_sql_operations_ДляПовторногоПоискаПуцбличногоUUIDМеждуУчасникамиперпискиЧатаТекущего.
                    new GetаFreeData(context)
                    .getfreedata(class_grud_sql_operations_ДляПовторногоПоискаПуцбличногоUUIDМеждуУчасникамиперпискиЧатаТекущего
                                    .concurrentHashMapНабор,
                            completionServiceНОваяЗадача,
                            sqLiteDatabaseНоваяЗадача);


            if (КурсорДанныеИщемЕслиУжеUUIDМенждуУчастикамиНовойЗАДАЧ_Task.getCount() > 0) {

                // TODO: 10.02.2022
                КурсорДанныеИщемЕслиУжеUUIDМенждуУчастикамиНовойЗАДАЧ_Task.moveToFirst();


                РезультатПроверикПолучлиЛИUUIDМеждуУчасникамиПереписки = КурсорДанныеИщемЕслиУжеUUIDМенждуУчастикамиНовойЗАДАЧ_Task.getLong(0);
                // TODO: 10.02.2022
                Log.d(this.getClass().getName(), " повторно РезультатПроверикПолучлиЛИUUIDМеждуУчасникамиПереписки " + РезультатПроверикПолучлиЛИUUIDМеждуУчасникамиПереписки + "\n");
            }
            // TODO: 21.03.2022

            КурсорДанныеИщемЕслиУжеUUIDМенждуУчастикамиНовойЗАДАЧ_Task.close();

        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ///
        }


        // TODO: 10.02.2022
        Log.d(this.getClass().getName(), " повторно РезультатПроверикПолучлиЛИUUIDМеждуУчасникамиПереписки " + РезультатПроверикПолучлиЛИUUIDМеждуУчасникамиПереписки + "\n");

        return РезультатПроверикПолучлиЛИUUIDМеждуУчасникамиПереписки;
    }
}