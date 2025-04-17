package com.dsy.dsu.BusinessLogicAll.CalculateChasov;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;
import com.dsy.dsu.BusinessLogicAll.DATE.SubClassCursorLoader;

import java.util.concurrent.ExecutionException;

public class SubClasssCuclulatesHours {

private Context context;

    public SubClasssCuclulatesHours(Context context) {
        this.context = context;
    }

    //TODO Метод ПОДЧСЧЕТА ЧАСОВ ПО ВСЕМ ТАБЕЛЯМ СРАЗУ
    private Integer  методПодстчетЧасовФИО(Long finalПолученныйUUID)
            throws InterruptedException, ExecutionException {
        Integer     ЧасыСотрудникаТекущегоТабеля=0; //TODO РЕзультат
        try {
            Bundle bundleПосикФИО=new Bundle();
            bundleПосикФИО.putString("СамЗапрос","  SELECT *  FROM  vietabel WHERE   uuid=?" +
                    "  AND fio IS NOT NULL   ORDER BY fio  LIMIT   1  ");
            bundleПосикФИО.putStringArray("УсловияВыборки" ,new String[]{String.valueOf(finalПолученныйUUID)});
            bundleПосикФИО.putString("Таблица","fio");
            Cursor КурсорПосчётЧасов=      (Cursor)    new SubClassCursorLoader(). CursorLoaders(context, bundleПосикФИО);
            //TODO Считаем Сумму часов по всем табелям
            ЧасыСотрудникаТекущегоТабеля = МетодПосчётаЧасовПоСотрудникуДляЗагрузкиСотрудников(КурсорПосчётЧасов);
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ЧасыСотрудникаТекущегоТабеля " +ЧасыСотрудникаТекущегоТабеля);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ЧасыСотрудникаТекущегоТабеля;

    }

    ////////////////////
    protected int МетодПосчётаЧасовПоСотрудникуДляЗагрузкиСотрудников(Cursor курсор_ЗагружаемТабеляСозданный) {
        int СуммаЧасов = 0;
        try{
            do {
                for (int ИндексДляИзмененияДней = 1; ИндексДляИзмененияДней < 32; ИндексДляИзмененияДней++) {
                    int ЧассыСотрудника = курсор_ЗагружаемТабеляСозданный.getInt(курсор_ЗагружаемТабеляСозданный.getColumnIndex("d" + ИндексДляИзмененияДней));
                    СуммаЧасов = СуммаЧасов + ЧассыСотрудника;
                    Log.d(this.getClass().getName(), "    СуммаЧасов " + СуммаЧасов);
                }///TODO END FOR  ПО СТОЛБЦАМ БЕЖИМ
            } while (курсор_ЗагружаемТабеляСозданный.moveToNext());
            курсор_ЗагружаемТабеляСозданный.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return СуммаЧасов;
    }


}
