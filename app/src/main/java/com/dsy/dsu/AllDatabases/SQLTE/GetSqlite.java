package com.dsy.dsu.AllDatabases.SQLTE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;


import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;


public class GetSqlite {
    private Context context;
    @SuppressLint("SuspiciousIndentation")
    public SQLiteDatabase методGetSqlite(@NonNull Context context) {
        // TODO: 13.01.2025
        SQLiteDatabase getSQLites = null;
        try{
              getSQLites=       new GetSQLiteDatabase(context).SqliteDatabase();
                        // TODO: 17.04.2023
                        Log.d(this.getClass().getName(),"\n" + " class FaceAPp "
                                + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                " SQLiteDatabase " +getSQLites);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                    Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  getSQLites;

    }

}
