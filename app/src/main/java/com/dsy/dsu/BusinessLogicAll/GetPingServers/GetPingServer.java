package com.dsy.dsu.BusinessLogicAll.GetPingServers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;

import javax.net.ssl.SSLSocketFactory;

public interface GetPingServer {


      Boolean pingServerJbossSuccessfulOrNot(@NotNull SSLSocketFactory getsslSocketFactory2
              ,@NotNull SQLiteDatabase sqLiteDatabase) ;





    Long pingingJbossServer(@androidx.annotation.NonNull SSLSocketFactory getsslSocketFactory2,
                            Integer ИмяПорта, String ИмяСервера,
                            @NotNull SQLiteDatabase sqLiteDatabase );


}
