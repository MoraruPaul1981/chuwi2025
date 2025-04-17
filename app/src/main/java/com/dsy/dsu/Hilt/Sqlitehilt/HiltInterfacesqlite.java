package com.dsy.dsu.Hilt.Sqlitehilt;


import android.database.sqlite.SQLiteDatabase;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;


@EntryPoint
@InstallIn(SingletonComponent.class)
public interface HiltInterfacesqlite {


    SQLiteDatabase getHiltSqlite( );
}


