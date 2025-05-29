package com.dsy.dsu.JbossAdress.JbossHilt.intarfaces;


import com.dsy.dsu.JbossAdress.JbossHilt.intarfaces.QualifierJbossServer3;

import java.util.LinkedHashMap;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@EntryPoint
@InstallIn(SingletonComponent.class)
public interface getHiltPortJbossInterface {
    @QualifierJbossServer3
    LinkedHashMap<Integer,String> getHiltPortJboss();
}
