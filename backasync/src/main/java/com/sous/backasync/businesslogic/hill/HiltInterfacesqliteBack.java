package com.sous.backasync.businesslogic.hill;


import android.database.sqlite.SQLiteDatabase;

import com.sous.backasync.hilt.QualifiertEndBack;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;



@EntryPoint
@InstallIn(SingletonComponent.class)
public interface HiltInterfacesqliteBack {

    SQLiteDatabase getHiltSqliteBAck( );
}
