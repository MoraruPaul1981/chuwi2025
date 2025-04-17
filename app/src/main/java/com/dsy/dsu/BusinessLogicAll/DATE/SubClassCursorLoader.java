package com.dsy.dsu.BusinessLogicAll.DATE;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.loader.content.CursorLoader;

import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;


public class SubClassCursorLoader {
    // TODO: 25.11.2022 новы метод получение данных для всех
    public Cursor CursorLoaders(@NonNull Context context, @NonNull Bundle bundle) throws  SQLException {
        Cursor cursor=null;
        CursorLoader cursorLoader=null;
        try{
            cursorLoader=new CursorLoader(context);
            String[] УсловияВыборки=      bundle.getStringArray("УсловияВыборки");
            String  СамЗапрос=      bundle.getString("СамЗапрос");
            String  Таблица=      bundle.getString("Таблица");
            Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecurrentoperations/" + Таблица + "");
            cursorLoader.setUri(uri);
            cursorLoader.setSelection(СамЗапрос);
            cursorLoader.setSelectionArgs(УсловияВыборки);//МесяцПростоАнализа
            cursor=    cursorLoader.loadInBackground();
            if (cursor.getCount() > 0 && cursor!=null) {
                cursor.moveToFirst();
                Log.d(this.getClass().getName(), "cursor.getCount() "
                        + cursor.getCount());
                cursorLoader.reset();
            }
        } catch ( Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }finally {
            cursorLoader.commitContentChanged();
        }
        return  cursor;
    }
    // TODO: 25.11.2022 новы метод получение данных для всех
    public Cursor CursorDontSelectionLoaders(@NonNull Context context, @NonNull Bundle bundle) throws  SQLException {
        Cursor cursor=null;
        CursorLoader cursorLoader=null;
        try{
            cursorLoader=new CursorLoader(context);
            //String[] УсловияВыборки=      bundle.getStringArray("УсловияВыборки");
            String  СамЗапрос=      bundle.getString("СамЗапрос");
            String  Таблица=      bundle.getString("Таблица");
            Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecurrentoperations/" + Таблица + "");
            cursorLoader.setUri(uri);
            cursorLoader.setSelection(СамЗапрос);
            //cursorLoader.setSelectionArgs(УсловияВыборки);//МесяцПростоАнализа
            cursor=    cursorLoader.loadInBackground();
            if (cursor.getCount() > 0 && cursor!=null) {
                cursor.moveToFirst();
                Log.d(this.getClass().getName(), "cursor.getCount() "
                        + cursor.getCount());
                cursorLoader.reset();
            }
        } catch ( Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }finally {
            cursorLoader.commitContentChanged();
        }
        return  cursor;
    }
    public Cursor ExeruteSSQLoaders(@NonNull Context context, @NonNull Bundle bundle) throws  SQLException {
        Cursor cursor=null;
        CursorLoader cursorLoader=null;
        try{
            cursorLoader=new CursorLoader(context);
            String[] УсловияВыборки=      bundle.getStringArray("УсловияВыборки");
            String  СамЗапрос=      bundle.getString("СамЗапрос");
            String  Таблица=      bundle.getString("Таблица");
            Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecurrentoperations/" + Таблица + "");
            cursorLoader.setUri(uri);
            cursorLoader.setSelection(СамЗапрос);
            cursorLoader.setSelectionArgs(УсловияВыборки);//МесяцПростоАнализа
            cursor=    cursorLoader.loadInBackground();
            if (cursor.getCount() > 0 && cursor!=null) {
                cursor.moveToFirst();
                Log.d(this.getClass().getName(), "cursor.getCount() "
                        + cursor.getCount());
                cursorLoader.reset();
            }
        } catch ( Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }finally {
            cursorLoader.commitContentChanged();
        }
        return  cursor;
    }
    // TODO: 24.05.2023  FOR GROUP BY

    // TODO: 25.11.2022 новы метод получение данных для всех
    public Cursor CursorForGetgropuByLoaders(@NonNull Context context, @NonNull Bundle bundle) throws  SQLException {
        Cursor cursor=null;
        CursorLoader cursorLoader=null;
        try{
            cursorLoader=new CursorLoader(context);
           /* String[] УсловияВыборки=      bundle.getStringArray("УсловияВыборки");*/
            String  СамЗапрос=      bundle.getString("СамЗапрос");
            String  Таблица=      bundle.getString("Таблица");
            Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecurrentoperations/" + Таблица + "");
            cursorLoader.setUri(uri);
            cursorLoader.setSelection(СамЗапрос);
         /*   cursorLoader.setSelectionArgs(УсловияВыборки);//МесяцПростоАнализа*/
            cursor=    cursorLoader.loadInBackground();
            if (cursor.getCount() > 0 && cursor!=null) {
                cursor.moveToFirst();
                Log.d(this.getClass().getName(), "cursor.getCount() "
                        + cursor.getCount());
                cursorLoader.reset();
            }
        } catch ( Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }finally {
            cursorLoader.commitContentChanged();
        }
        return  cursor;
    }
}