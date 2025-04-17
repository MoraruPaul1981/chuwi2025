package com.dsy.dsu.Hilt.RoomHilt;


import android.database.sqlite.SQLiteDatabase;

import com.dsy.dsu.AllDatabases.ROOM.ROOMDatabase;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;


@EntryPoint
@InstallIn(SingletonComponent.class)
public interface HiltInterfaceRoom {


    ROOMDatabase getHiltRoom( );
}


