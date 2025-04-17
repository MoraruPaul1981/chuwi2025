package com.dsy.dsu.Hilt.JbossAdrress;


import com.dsy.dsu.Hilt.JbossAdrress.qualifiers.QualifierJbossServer3;

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
