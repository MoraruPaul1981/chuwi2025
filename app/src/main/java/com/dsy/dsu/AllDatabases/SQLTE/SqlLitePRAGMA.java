package com.dsy.dsu.AllDatabases.SQLTE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;

public class SqlLitePRAGMA {

    private Context context;

    public SqlLitePRAGMA(@NonNull Context context) {
        this.context = context;
    }

    @SuppressLint("SuspiciousIndentation")
    public void launchsqlLitePRAGMA(@NonNull  SQLiteDatabase ССылкаНаСозданнуюБазу ) {
        // TODO: 13.01.2025
        try{
      SQLiteStatement stackTraceElementPRAGMA=
              ССылкаНаСозданнуюБазу.compileStatement("PRAGMA synchronous = FULL");
          int updatessynch=      stackTraceElementPRAGMA.executeUpdateDelete();

            // TODO: 17.04.2023
            stackTraceElementPRAGMA=
                    ССылкаНаСозданнуюБазу.compileStatement("PRAGMA journal_mode =  MEMORY");//DELETE | TRUNCATE | PERSIST | MEMORY | WAL | OFF

            int updatesjournal=      stackTraceElementPRAGMA.executeUpdateDelete();

            // TODO: 17.04.2023
            Log.d(this.getClass().getName(),"\n" + " class FaceAPp "
                    + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " updatesjournal "+updatesjournal+ "\n"+
                    " updatessynch "+updatessynch);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                    Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }


    }



}
