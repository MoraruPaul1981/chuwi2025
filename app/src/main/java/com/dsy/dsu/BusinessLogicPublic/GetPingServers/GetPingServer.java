package com.dsy.dsu.BusinessLogicPublic.GetPingServers;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;

import javax.net.ssl.SSLSocketFactory;

public interface GetPingServer {


      Boolean pingServerJbossSuccessfulOrNot(@NotNull SSLSocketFactory getsslSocketFactory2,LinkedHashMap<Integer,String> getHiltPortJboss) ;





    Long pingingJbossServer(@androidx.annotation.NonNull SSLSocketFactory getsslSocketFactory2,
                            Integer ИмяПорта, String ИмяСервера);


}
