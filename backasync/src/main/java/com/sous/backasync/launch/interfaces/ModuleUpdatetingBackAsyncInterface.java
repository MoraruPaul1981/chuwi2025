package com.sous.backasync.launch.interfaces;

import android.annotation.SuppressLint;
import android.content.ContentValues;

import androidx.annotation.NonNull;

public interface ModuleUpdatetingBackAsyncInterface {

    // TODO: 28.01.2025

       final String getNameProvider="com.sous.backasync.provider";


    @SuppressLint("NewApi")
    Integer getModuleUpdate(@NonNull String Таблица, @NonNull  ContentValues contentValuesModuleBack ,  @NonNull String selection,  @NonNull String[] selectionArgs);

    @SuppressLint("NewApi")
    Integer getModuleUpdate(@NonNull String Таблица,@NonNull ContentValues contentValuesModuleBackAsync);
}
