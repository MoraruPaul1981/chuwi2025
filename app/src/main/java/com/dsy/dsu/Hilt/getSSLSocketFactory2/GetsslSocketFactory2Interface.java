package com.dsy.dsu.Hilt.getSSLSocketFactory2;


import javax.net.ssl.SSLSocketFactory;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;


@EntryPoint
@InstallIn(SingletonComponent.class)
public interface GetsslSocketFactory2Interface {

    @QualifiergetsslSocketFactory2
    SSLSocketFactory getsslSocketFactory2( );
}


