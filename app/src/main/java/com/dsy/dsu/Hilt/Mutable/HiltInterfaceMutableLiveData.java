package com.dsy.dsu.Hilt.Mutable;


import android.content.Intent;

import androidx.lifecycle.MutableLiveData;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;


@EntryPoint
@InstallIn(SingletonComponent.class)
public interface HiltInterfaceMutableLiveData {


    MutableLiveData<Intent> getHiltMutableLiveData( );
}


