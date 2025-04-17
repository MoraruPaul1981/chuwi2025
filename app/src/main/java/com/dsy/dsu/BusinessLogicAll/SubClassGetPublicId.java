package com.dsy.dsu.BusinessLogicAll;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.BusinessLogicAll.DATE.SubClassCursorLoader;
import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;

public class SubClassGetPublicId {
    public Integer ПубличныйID(@NonNull Context context){
        Integer  ПубличноеID=0;
        try{
        // TODO: 09.04.2023  курсор самим создаваемых табеляПОСИК ДАННЫХ ЧЕРЕЗ UUID
        Bundle bundleListTabels=new Bundle();
        bundleListTabels.putString("СамЗапрос","   SELECT Suc.publicid  FROM successlogin  AS Suc ORDER BY Suc.date_update DESC  LIMIT 1 ; ");
        bundleListTabels.putStringArray("УсловияВыборки" ,new String[]{ });
        bundleListTabels.putString("Таблица","successlogin");
        Cursor КурсорПубличноеID=      (Cursor)    new SubClassCursorLoader(). CursorLoaders(context, bundleListTabels);
        Log.d(this.getClass().getName(), "КурсорПубличноеID "+КурсорПубличноеID  );
        if ((КурсорПубличноеID.getCount()>0)){
            ПубличноеID =КурсорПубличноеID.getInt(0);
        }
            //////////20.15
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " ПубличноеID  " +ПубличноеID);
        } catch (Exception e) {
        e.fillInStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return  ПубличноеID;
    }


}
class SubClassGetPublicFIO {
    public Integer ПубличныйFIO(@NonNull Context context){
        Integer  ПубличноеID=0;
        try{
            // TODO: 09.04.2023  курсор самим создаваемых табеляПОСИК ДАННЫХ ЧЕРЕЗ UUID
            Bundle bundleListTabels=new Bundle();
            bundleListTabels.putString("СамЗапрос","   SELECT fio  FROM successlogin  ORDER BY date_update DESC  LIMIT 1 ; ");
            bundleListTabels.putStringArray("УсловияВыборки" ,new String[]{ });
            bundleListTabels.putString("Таблица","successlogin");
            Cursor КурсорПубличноеID=      (Cursor)    new SubClassCursorLoader(). CursorLoaders(context, bundleListTabels);
            Log.d(this.getClass().getName(), "КурсорПубличноеID "+КурсорПубличноеID  );
            if ((КурсорПубличноеID.getCount()>0)){
                ПубличноеID =КурсорПубличноеID.getInt(0);
            }
            //////////20.15
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " ПубличноеID  " +ПубличноеID);
        } catch (Exception e) {
            e.fillInStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  ПубличноеID;
    }


}