package com.dsy.dsu.BusinessLogicAll.GetPublicID;


import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@EntryPoint
@InstallIn(SingletonComponent.class)
public interface HiltInterfacesPublicID {

    @QualifierPublicID
    Integer getPublicIDAllApp();
}
