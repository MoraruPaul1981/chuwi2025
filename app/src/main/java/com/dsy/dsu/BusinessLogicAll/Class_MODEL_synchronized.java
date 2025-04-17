package com.dsy.dsu.BusinessLogicAll;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.AllDatabases.SQLTE.GetSQLiteDatabase;
import com.dsy.dsu.BusinessLogicAll.DownloadsJBOSS.BunessLogicDownloadBufferReader.DownloadReader;
import com.dsy.dsu.BusinessLogicAll.DownloadsJBOSS.BunessLogicDownloadBufferReader.GetBinessLogicDownloadReader;
import com.dsy.dsu.BusinessLogicAll.DownloadsJBOSS.BunessLogicDownloadByte.DownloadByte;
import com.dsy.dsu.BusinessLogicAll.DownloadsJBOSS.BunessLogicDownloadByte.GetBinessLogicDownloadByte;
import com.dsy.dsu.BusinessLogicAll.DownloadsJBOSS.BunessLogicDownloadFiles.DownloadFiles;
import com.dsy.dsu.BusinessLogicAll.DownloadsJBOSS.BunessLogicDownloadFiles.GetBinessLogicDownloadFiles;
import com.dsy.dsu.BusinessLogicAll.DATE.Class_Generation_Data;

import com.dsy.dsu.BusinessLogicAll.DeviceName.ModulegetDeviceName;
import com.dsy.dsu.BusinessLogicAll.GetPublicID.GetPublicID;
import com.dsy.dsu.CnangeServers.PUBLIC_CONTENT;
import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;
import com.dsy.dsu.Hilt.JbossAdrress.getHiltPortJbossInterface;
import com.dsy.dsu.Hilt.OkhhtpBuilder.GetAsyncOkHttpClientBuilder;
import com.google.common.io.ByteSource;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.crypto.NoSuchPaddingException;
import javax.net.ssl.SSLSocketFactory;

import dagger.hilt.EntryPoints;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

///////Универсальный Класс Обмена Данными  Два Стачичных Метода и Плюс Сттичный Курсор
 public class Class_MODEL_synchronized extends GetSQLiteDatabase {
  public     Context context;
    private PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =null;
    private Class_MODEL_synchronized ссылка_MODELsynchronized = null;
    private String ПубличноеЛогин =      new String();
    private  String ПубличноеПароль =   new String();


    private SQLiteDatabase sqLiteDatabase ;

    private SharedPreferences preferencesJboss;


    public Class_MODEL_synchronized(  @NotNull Context context) {
        super(context);
       this. context=context;
        //TODO контроль потоков
        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT(context);
        sqLiteDatabase=    GetSQLiteDatabase.SqliteDatabase();
        // TODO: 06.10.2024

        preferencesJboss = context.getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
    }


    //todo #GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET




    // TODO: 04.08.2021 HEAD HEAD

    ///МЕТОД ПОЛУЧЕНИЕ ДАННЫХ С СЕРВЕРА
    public StringBuffer МетодУниверсальныйСервернаяВерсияДанныхДанныесСервера(String ИмяТаблицы,
                                                                              String Тип,
                                                                              String JobForServer,
                                                                              Long Версия,
                                                                              Integer ID,
                                                                              String ИмяСервера,
                                                                              Integer ИмяПорта,
                                                                              SSLSocketFactory getsslSocketFactory2) {

        final StringBuffer[] БуферСамиДанныеОтСервера = {new StringBuffer()};
        try {
            String enableSSl = preferencesJboss.getString("enablesll","http");
            String СтрокаСвязиСсервером =enableSSl+"://"+ИмяСервера+":"+ИмяПорта+"/"+new PUBLIC_CONTENT(context).getСсылкаНаРежимСервераТабель();;
            СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
            Log.d(this.getClass().getName(), "   СтрокаСвязиСсервером "+  СтрокаСвязиСсервером);
            String Params = "?" + "NameTable= " + ИмяТаблицы.trim() +
                    "&" + "JobForServer=" + JobForServer.trim() + ""
                    + "&" + "IdUser=" + ID + ""
                    + "&" + "VersionData=" + Версия + "";
            СтрокаСвязиСсервером=   СтрокаСвязиСсервером + Params;
            СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
            URL Adress = new URL(СтрокаСвязиСсервером);
            Log.d(this.getClass().getName(), " СтрокаСвязиСсервером " + СтрокаСвязиСсервером);




            OkHttpClient.Builder builderokhtttp=   new GetAsyncOkHttpClientBuilder(context,getsslSocketFactory2).GetAsyncOkHttpClientBuilder(enableSSl);

            OkHttpClient okHttpClientДанныеОтСервера = builderokhtttp.addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Class_GRUD_SQL_Operations grudSqlOperations= new Class_GRUD_SQL_Operations(context);
                            grudSqlOperations.concurrentHashMapНабор.put("СамFreeSQLКОд",
                                    " SELECT success_users,success_login  FROM successlogin  ORDER BY date_update DESC ;");
                            PUBLIC_CONTENT  classEngineSQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT (context);
                            SQLiteCursor            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО= null;
                            try {
                                Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО = (SQLiteCursor) grudSqlOperations.
                                        new GetаFreeData(context).getfreedata(grudSqlOperations.
                                                concurrentHashMapНабор,
                                        classEngineSQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabase);
                            } catch (ExecutionException e) {
                                throw new RuntimeException(e);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                            if(Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount()>0){
                                Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();
                                ПубличноеЛогин =         Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(0).trim();
                                ПубличноеПароль =           Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(1).trim();
                            }
                            Log.d(this.getClass().getName(), "  PUBLIC_CONTENT.ПубличноеИмяПользовательДлСервлета  " + ПубличноеЛогин +
                                    " PUBLIC_CONTENT.ПубличноеПарольДлСервлета " + ПубличноеПароль);

                            // TODO: 18.02.2025 get name Device
                            String ANDROID_ID =new ModulegetDeviceName().getDeviceName(context);
                            
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                            Log.d(this.getClass().getName(), "  ПубличноеЛогин " + ПубличноеЛогин + " ПубличноеПароль " + ПубличноеПароль+" ANDROID_ID "+ANDROID_ID);
                            
                            Request originalRequest = chain.request();
                            Request.Builder builder = originalRequest.newBuilder()
                                    .header("Content-Type", Тип + " ;charset=UTF-8")
                                    .header("Accept-Encoding", "gzip,*,compress,identity,deflate,zstd,br")
                                    .header("Connection", "Keep-Alive")
                                    .header("Accept-Language", "ru-RU")
                                    .header("identifier", ПубличноеЛогин)
                                    .header("p_identifier", ПубличноеПароль)
                                    .header("id_device_androis", ANDROID_ID);
                            Request newRequest = builder.build();
                            return chain.proceed(newRequest);
                        }
                    }).connectTimeout(5, TimeUnit.SECONDS)
                    .writeTimeout(2, TimeUnit.MINUTES)
                    .readTimeout(2, TimeUnit.MINUTES)
                    .build();
            ///  MediaType JSON = MediaType.parse("application/json; charset=utf-16");
            Request requestGET = new Request.Builder().get().url(Adress).build();
            Log.d(this.getClass().getName(), "  request  " + requestGET);
            // TODO  Call callGET = client.newCall(requestGET);
            Dispatcher dispatcherДанныеОтСервера = okHttpClientДанныеОтСервера.dispatcher();
            okHttpClientДанныеОтСервера.newCall(requestGET).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Log.e(this.getClass().getName(), "  ERROR call  " + call + "  e" + e.toString());
                    Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + e.getMessage());
                    new RecordNewErros(context).recordnewerror(e.toString(), Class_MODEL_synchronized.class.getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    // TODO: 28.12.2024
                    // TODO: 31.05.2022
                    dispatcherДанныеОтСервера.executorService().shutdown();
                    //TODO закрываем п отоки
                }
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    try{
                    if (response.isSuccessful()) {
                        String  ПришедшегоПотока =    response.header("stream_size");
                        ПришедшегоПотока =     Optional.ofNullable(ПришедшегоПотока).map(String::valueOf).orElse("0");
                        Long РазмерПришедшегоПотока = Long.parseLong(ПришедшегоПотока  );
                        // TODO: 29.09.2023
                        Integer КакаяКодировка = Integer.parseInt(   Optional.ofNullable(response.header("getcharsets")).map(String::new).orElse("0"));
                        Boolean ФлагgZIPOutputStream =Boolean.parseBoolean (  Optional.ofNullable(response.header("GZIPOutputStream")).map(String::new).orElse("false"));
                        if (РазмерПришедшегоПотока>0l) {
                            // TODO: 07.10.2023  gzip
                            // TODO: 07.10.2023  gzip

                            // TODO: 07.04.2025  получаем STEAM  от сервера и обрабоатываем его для READER
                            DownloadReader downloadReader=new DownloadReader();
                            // TODO: 07.04.2025 обрабоатываем пршедщий файл
                            // TODO: 07.04.2025 обрабоатываем пршедщий файл
                            БуферСамиДанныеОтСервера[0]=downloadReader.downloadReader(context, new GetBinessLogicDownloadReader(),
                                    response.body().bytes()) ;
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "     БуферСамиДанныеОтСервера[0] " +    БуферСамиДанныеОтСервера[0]
                                    +  " РазмерПришедшегоПотока " + РазмерПришедшегоПотока);



/*

                            InputStream inputStreamОтПинга = new GZIPInputStream(response.body().source().inputStream(),2048);////4096
                            // TODO: 07.10.2023 end
                            BufferedReader РидерОтСервераМетодаGET;//
                            if (КакаяКодировка==8) {
                                РидерОтСервераМетодаGET = new BufferedReader(new InputStreamReader(inputStreamОтПинга, StandardCharsets.UTF_8));
                            } else {
                                РидерОтСервераМетодаGET = new BufferedReader(new InputStreamReader(inputStreamОтПинга, StandardCharsets.UTF_16));
                            }
                                БуферСамиДанныеОтСервера[0] = РидерОтСервераМетодаGET.lines().collect(StringBuffer::new, (sb, i) -> sb.append(i),
                                        StringBuffer::append);
                                Log.d(this.getClass().getName(), "БуферСамиДанныеОтСервера " + БуферСамиДанныеОтСервера[0] +  " РазмерПришедшегоПотока " +РазмерПришедшегоПотока);*/


                        }



                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "   БуферСамиДанныеОтСервера[0] " +  БуферСамиДанныеОтСервера[0]);

                        // TODO: 28.12.2024 closeting
                        response.close();
                        // TODO: 31.05.2022
                        dispatcherДанныеОтСервера.executorService().shutdown();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(context).recordnewerror(e.toString(),
                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                }
            });
            //TODO
            dispatcherДанныеОтСервера.executorService().awaitTermination(1, TimeUnit.DAYS);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            String ОшибкаТекущегоМетода = e.toString();
            if (!ОшибкаТекущегоМетода.toString().trim().trim().matches("(.*)java.io.EOFException(.*)") &&
                    !ОшибкаТекущегоМетода.toString().trim().trim().matches("(.*)java.net.sockettimeoutexception(.*)")
                    &&
                    !ОшибкаТекущегоМетода.toString().trim().matches("(.*)SocketTimeout(.*)")) {
                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + ОшибкаТекущегоМетода + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + ОшибкаТекущегоМетода.toString());
                new RecordNewErros(context).recordnewerror(e.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        //// todo get ASYNtASK
        return БуферСамиДанныеОтСервера[0];

    }

    // TODO: 06.09.2023  пришли данные байтовые от сервера
    ///МЕТОД ПОЛУЧЕНИЕ ДАННЫХ С СЕРВЕРА
    public   byte[]     методGetByteFromServerAsync(String ИмяТаблицы,
                                                                              String Тип,
                                                                              String JobForServer,
                                                                              Long Версия,
                                                                              Integer ID,
                                                                              String ИмяСервера,
                                                                              Integer ИмяПорта,
                                                         SSLSocketFactory getsslSocketFactory2) {

        AtomicReference<byte[]>  inputStreamJaksonByte = new AtomicReference();
        try {
            String enableSSl = preferencesJboss.getString("enablesll","http");
            String СтрокаСвязиСсервером =enableSSl+"://"+ИмяСервера+":"+ИмяПорта+"/"+new PUBLIC_CONTENT(context).getСсылкаНаРежимСервераТабель();;
            СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
            Log.d(this.getClass().getName(), "   СтрокаСвязиСсервером "+  СтрокаСвязиСсервером);
            String Params = "?" + "NameTable= " + ИмяТаблицы.trim() +
                    "&" + "JobForServer=" + JobForServer.trim() + ""
                    + "&" + "IdUser=" + ID + ""
                    + "&" + "VersionData=" + Версия + "";
            СтрокаСвязиСсервером=   СтрокаСвязиСсервером + Params;
            СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
            URL Adress = new URL(СтрокаСвязиСсервером);
            Log.d(this.getClass().getName(), " СтрокаСвязиСсервером " + СтрокаСвязиСсервером);





            OkHttpClient.Builder builderokhtttp=   new GetAsyncOkHttpClientBuilder(context,getsslSocketFactory2).GetAsyncOkHttpClientBuilder(enableSSl);


            OkHttpClient okHttpClientДанныеОтСервера = builderokhtttp.addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Class_GRUD_SQL_Operations grudSqlOperations= new Class_GRUD_SQL_Operations(context);
                            grudSqlOperations.concurrentHashMapНабор.put("СамFreeSQLКОд",
                                    " SELECT success_users,success_login  FROM successlogin  ORDER BY date_update DESC ;");
                            PUBLIC_CONTENT  classEngineSQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT (context);
                            SQLiteCursor            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО= null;
                            try {
                                Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО = (SQLiteCursor) grudSqlOperations.
                                        new GetаFreeData(context).getfreedata(grudSqlOperations.
                                                concurrentHashMapНабор,
                                        classEngineSQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabase);
                            } catch (ExecutionException e) {
                                throw new RuntimeException(e);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                            if(Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount()>0){
                                Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();
                                ПубличноеЛогин =         Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(0).trim();
                                ПубличноеПароль =           Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(1).trim();
                            }
                            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.close();
                            Log.d(this.getClass().getName(), "  PUBLIC_CONTENT.ПубличноеИмяПользовательДлСервлета  " + ПубличноеЛогин +
                                    " PUBLIC_CONTENT.ПубличноеПарольДлСервлета " + ПубличноеПароль);
                            // TODO: 18.02.2025 get name Device
                            String ANDROID_ID =new ModulegetDeviceName().getDeviceName(context);
                            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
                            Log.d(this.getClass().getName(), "  ПубличноеЛогин " + ПубличноеЛогин + " ПубличноеПароль " + ПубличноеПароль+" ANDROID_ID "+ANDROID_ID);
                            Request originalRequest = chain.request();
                            Request.Builder builder = originalRequest.newBuilder()
                                    .header("Content-Type", Тип + " ;charset=UTF-8")
                                    .header("Accept-Encoding", "gzip,*,compress,identity,deflate,zstd,br")
                                    .header("Connection", "Keep-Alive")
                                    .header("Accept-Language", "ru-RU")
                                    .header("identifier", ПубличноеЛогин)
                                    .header("p_identifier", ПубличноеПароль)
                                    .header("id_device_androis", ANDROID_ID);
                            Request newRequest = builder.build();
                            return chain.proceed(newRequest);
                        }
                    }).connectTimeout(5, TimeUnit.SECONDS)
                    .writeTimeout(2, TimeUnit.MINUTES)
                    .readTimeout(2, TimeUnit.MINUTES)
                    .build();
            ///  MediaType JSON = MediaType.parse("application/json; charset=utf-16");
            Request requestGET = new Request.Builder().get().url(Adress).build();
            Log.d(this.getClass().getName(), "  request  " + requestGET);
            // TODO  Call callGET = client.newCall(requestGET);
            Dispatcher dispatcherДанныеОтСервера = okHttpClientДанныеОтСервера.dispatcher();
            okHttpClientДанныеОтСервера.newCall(requestGET).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Log.e(this.getClass().getName(), "  ERROR call  " + call + "  e" + e.toString());
                    Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + e.getMessage());
                    new RecordNewErros(context).recordnewerror(e.toString(), Class_MODEL_synchronized.class.getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    // TODO: 31.05.2022
                    dispatcherДанныеОтСервера.executorService().shutdown();
                    //TODO закрываем п отоки
                }
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    try{
                        if (response.isSuccessful()) {
                            String  ПришедшегоПотока =    response.header("stream_size");
                            ПришедшегоПотока =     Optional.ofNullable(ПришедшегоПотока).map(String::valueOf).orElse("0");
                            Long РазмерПришедшегоПотока = Long.parseLong(ПришедшегоПотока  );
                            // TODO: 29.09.2023
                            Integer КакаяКодировка = Integer.parseInt(   Optional.ofNullable(response.header("getcharsets")).map(String::new).orElse("0"));
                            Boolean ФлагgZIPOutputStream =Boolean.parseBoolean (  Optional.ofNullable(response.header("GZIPOutputStream")).map(String::new).orElse("false"));
                            if (РазмерПришедшегоПотока>0l) {
                                // TODO: 07.10.2023  gzip
                          /*      byte[] asByteBuffer=    response.body().source().readByteArray();
                                // TODO: 25.09.2024
                                inputStreamJaksonByte.set(new GZIPInputStream(ByteSource.wrap(asByteBuffer).openBufferedStream(),2048)); ;//4096*/

                                // TODO: 07.04.2025  получаем STEAM  от сервера и обрабоатываем его для READER
                                DownloadByte downloadByte=new DownloadByte();
                                // TODO: 07.04.2025 обрабоатываем пршедщий файл
                                inputStreamJaksonByte.set(downloadByte.downloadByte(context, new GetBinessLogicDownloadByte(), response.body().bytes())); ;

                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+" inputStreamJaksonByte.get() " +inputStreamJaksonByte.get());
                            }
                            // TODO: 28.12.2024 closeting
                            response.close();
                            // TODO: 31.05.2022
                            dispatcherДанныеОтСервера.executorService().shutdown();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(context).recordnewerror(e.toString(),
                                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
            });
            //TODO
            dispatcherДанныеОтСервера.executorService().awaitTermination(1, TimeUnit.DAYS);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            String ОшибкаТекущегоМетода = e.toString();
            if (!ОшибкаТекущегоМетода.toString().trim().matches("(.*)java.io.EOFException(.*)") &&
                    !ОшибкаТекущегоМетода.toString().trim().matches("(.*)java.net.sockettimeoutexception(.*)")
                    &&
                    !ОшибкаТекущегоМетода.toString().trim().matches("(.*)SocketTimeout(.*)")) {
                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + ОшибкаТекущегоМетода + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + ОшибкаТекущегоМетода.toString());
                new RecordNewErros(context).recordnewerror(e.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        //// todo get ASYNtASK
        return   inputStreamJaksonByte.get();

    }















    //todo #GET     //#GET  только для ПИНГА     //#GET  только для ПИНГА  //#GET  только для ПИНГА //#GET  только для ПИНГА //#GET  только для ПИНГА //#GET  только для ПИНГА //#GET  только для ПИНГА //#GET  только для ПИНГА
    ///МЕТОД ПОЛУЧЕНИЕ ДАННЫХ С СЕРВЕРА
    @SuppressLint("Range")
    public Long МетодУниверсальногоПинга(String NameTable,
                                            String Тип ,
                                            String JobForServer,
                                            Long VersionData,
                                            Integer IdUser,
                                            String ИмяСервера,
                                            Integer ИмяПорта,
                                         @NotNull SSLSocketFactory getsslSocketFactory2)  {
        final Long[] РазмерПришедшегоПотока = {0l};
        try {
            String enableSSl = preferencesJboss.getString("enablesll","http");
            StringBuffer БуферРезультатПингасСервером = null;
            String СтрокаСвязиСсервером = enableSSl+"://" + ИмяСервера + ":" + ИмяПорта + "/"+ new PUBLIC_CONTENT(context).getСсылкаНаРежимСервераRuntime();
            СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
            Log.d(this.getClass().getName(), "   СтрокаСвязиСсервером " + СтрокаСвязиСсервером);
            String Params  = "?" + "NameTable= " + NameTable.trim() +
                    "&" + "JobForServer=" + JobForServer.trim() + ""+
                    "&" + "IdUser=" + IdUser + ""
                    + "&" + "VersionData=" + VersionData + "";
            Log.d(this.getClass().getName(), " Params" + Params);
            СтрокаСвязиСсервером = СтрокаСвязиСсервером + Params;
            СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
            URL Adress = new URL(СтрокаСвязиСсервером);
            Log.d(this.getClass().getName(), " СтрокаСвязиСсервером " + СтрокаСвязиСсервером);
            // TODO: 11.03.2023 новый тест коде

            // TODO: 25.12.2024 Какой тип okhhtp подключить


            OkHttpClient.Builder builderokhtttp=   new GetAsyncOkHttpClientBuilder(context,getsslSocketFactory2).GetAsyncOkHttpClientBuilder(enableSSl);

            // TODO: 15.12.2023 end test
            OkHttpClient okHttpClientПинг = builderokhtttp.addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
                            Class_GRUD_SQL_Operations grudSqlOperations = new Class_GRUD_SQL_Operations(context);
                            grudSqlOperations.concurrentHashMapНабор.put("СамFreeSQLКОд",
                                    " SELECT success_users,success_login  FROM successlogin  ORDER BY date_update DESC ;");
                            // TODO: 12.10.2021  Ссылка Менеджер Потоков
                            PUBLIC_CONTENT publicContent = new PUBLIC_CONTENT(context);
                            SQLiteCursor Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО = null;
                            try {
                                Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО = (SQLiteCursor) grudSqlOperations.
                                        new GetаFreeData(context).getfreedata(grudSqlOperations.
                                                concurrentHashMapНабор,
                                        publicContent.МенеджерПотоков,sqLiteDatabase);
                            } catch (ExecutionException e) {
                                throw new RuntimeException(e);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount() > 0) {
                                Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();
                                ПубличноеЛогин = Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(0).trim();
                                ПубличноеПароль = Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(1).trim();
                            }
                            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.close();

                            // TODO: 18.02.2025 get name Device
                            String ANDROID_ID =new ModulegetDeviceName().getDeviceName(context);
                            Log.d(this.getClass().getName(), "  PUBLIC_CONTENT.ПубличноеИмяПользовательДлСервлета  " + ПубличноеЛогин +
                                    " PUBLIC_CONTENT.ПубличноеПарольДлСервлета " + ПубличноеПароль);
                            Request originalRequest = chain.request();
                            Request.Builder builder = originalRequest.newBuilder()
                                    .header("Content-Type", Тип + " ;charset=UTF-8")
                                    .header("Accept-Encoding", "gzip,*,compress,identity,deflate,zstd,br")
                                    .header("Connection", "Keep-Alive")
                                    .header("Accept-Language", "ru-RU")
                                    .header("identifier", ПубличноеЛогин)
                                    .header("p_identifier", ПубличноеПароль)
                                    .header("id_device_androis", ANDROID_ID);
                            Request newRequest = builder.build();
                            return chain.proceed(newRequest);
                        }
                    }).connectTimeout(5, TimeUnit.SECONDS)
                    .writeTimeout(2,TimeUnit.MINUTES)
                    .readTimeout(2, TimeUnit.MINUTES)
                    .build();
            ///  MediaType JSON = MediaType.parse("application/json; charset=utf-16");
            Request requestGET = new Request.Builder().get().url(Adress).build();
            Log.d(this.getClass().getName(), "  request  " + requestGET);
            // TODO  Call callGET = client.newCall(requestGET);
            Dispatcher  dispatcherПинг = okHttpClientПинг.dispatcher();
            okHttpClientПинг.newCall(requestGET).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Log.e(this.getClass().getName(), "  ERROR call  " + call + "  e" + e.toString());
                    Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + e.getMessage());
                    new RecordNewErros(context).recordnewerror(e.toString(), Class_MODEL_synchronized.class.getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    // TODO: 31.05.2022
                    dispatcherПинг.executorService().shutdown();
                    //TODO закрываем п отоки
                }
                @SuppressLint("SuspiciousIndentation")
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    try{
                    if (response.isSuccessful()) {
                        String  ПришедшегоПотока =    response.header("stream_size");
                        ПришедшегоПотока =     Optional.ofNullable(ПришедшегоПотока).map(String::valueOf).orElse("0");
                        РазмерПришедшегоПотока[0] = Long.parseLong(ПришедшегоПотока  );
                        // TODO: 29.09.2023

                        Integer КакаяКодировка = Integer.parseInt(   Optional.ofNullable(response.header("getcharsets")).map(String::new).orElse("0"));
                        Boolean ФлагgZIPOutputStream =Boolean.parseBoolean (  Optional.ofNullable(response.header("GZIPOutputStream")).map(String::new).orElse("false"));
                        StringBuffer БуферРезультатПингасСервером = null;
                        if (РазмерПришедшегоПотока[0] >0l) {


                            // TODO: 07.04.2025  получаем STEAM  от сервера и обрабоатываем его для READER
                            DownloadReader downloadReader=new DownloadReader();
                            // TODO: 07.04.2025 обрабоатываем пршедщий файл
                            StringBuffer   РидерОтСервераМетодаGET=downloadReader.downloadReader(context, new GetBinessLogicDownloadReader(),
                                    response.body().bytes()) ;
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " РидерОтСервераМетодаGET " +РидерОтСервераМетодаGET
                                    +  " РазмерПришедшегоПотока[0] " + РазмерПришедшегоПотока[0]);



                        }
                        Log.d(this.getClass().getName(), "БуферРезультатПингасСервером " + БуферРезультатПингасСервером +  " РазмерПришедшегоПотока[0] " + РазмерПришедшегоПотока[0]);
                    }

                        // TODO: 28.12.2024 closeting
                        response.close();
                        // TODO: 31.05.2022
                        dispatcherПинг.executorService().shutdown();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(context).recordnewerror(e.toString(),
                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                }
            });
            dispatcherПинг.executorService().awaitTermination(1,TimeUnit.DAYS);

            Log.i(context.getClass().getName(), "БуферРезультатПингасСервером" + БуферРезультатПингасСервером);
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
            String ОшибкаТекущегоМетода = ex.toString();
            if (!ОшибкаТекущегоМетода.toString().trim().matches("(.*)java.io.EOFException(.*)") &&
                    !ОшибкаТекущегоМетода.toString().trim().matches("(.*)java.net.sockettimeoutexception(.*)")
                    &&
                    !ОшибкаТекущегоМетода.toString().trim().matches("(.*)SocketTimeout(.*)")) {

                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + ОшибкаТекущегоМетода + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + ОшибкаТекущегоМетода.toString());
                new RecordNewErros(context).recordnewerror(ex.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        return РазмерПришедшегоПотока[0];
    }


////////////////////////////////16.15  новый метод ПИНГА














































///todo #POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST


    ///////метод ОТПРАВКИ ДАННЫХ НА СЕРВЕР
    public StringBuffer методSendByteToAsync(@NonNull    byte[] ГенерацияJSONОтAndroid,
                                             @NonNull Integer ID,
                                             @NonNull String Таблица,
                                             @NonNull  String JobForServer,
                                             @NonNull   String ИмяСервера,
                                             @NonNull  Integer ИмяПорта,
                                             @NonNull SSLSocketFactory getsslSocketFactory2)  {

        final StringBuffer[] БуферCallsBackОтСеврера = {new StringBuffer()};
                try {
                    String enableSSl = preferencesJboss.getString("enablesll","http");
                    // TODO: 12.03.2023  метод POST()
                    String СтрокаСвязиСсервером =enableSSl+"://"+ИмяСервера+":"+ИмяПорта+"/"+new PUBLIC_CONTENT(context).getСсылкаНаРежимСервераТабель();
                    СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
                    String Params = "?" + "NameTable=" + Таблица.trim() + "&"
                            + "IdUser=" + ID +
                            "&" + "JobForServer=" + JobForServer.trim() + "";
                    СтрокаСвязиСсервером = СтрокаСвязиСсервером + Params;
                    СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
                    URL Adress = new URL(СтрокаСвязиСсервером);
                    Log.d(this.getClass().getName(), " Adress  " + Adress);


                    OkHttpClient.Builder builderokhtttp=   new GetAsyncOkHttpClientBuilder(context,getsslSocketFactory2).GetAsyncOkHttpClientBuilder(enableSSl);

                    OkHttpClient okHttpClientОтправкиДанныхНаСервер =builderokhtttp.addInterceptor(new Interceptor() {
                                @Override
                                public Response intercept(Chain chain) throws IOException {
                                    // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
                                    Class_GRUD_SQL_Operations grudSqlOperations = new Class_GRUD_SQL_Operations(context);
                                    grudSqlOperations.concurrentHashMapНабор.put("СамFreeSQLКОд",
                                            " SELECT success_users,success_login  FROM successlogin  ORDER BY date_update DESC ;");
                                    // TODO: 12.10.2021  Ссылка Менеджер Потоков
                                    PUBLIC_CONTENT publicContent = new PUBLIC_CONTENT(context);
                                    SQLiteCursor Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО = null;
                                    try {
                                        Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО = (SQLiteCursor) grudSqlOperations.
                                                new GetаFreeData(context).getfreedata(grudSqlOperations.
                                                        concurrentHashMapНабор,
                                                publicContent.МенеджерПотоков, sqLiteDatabase);
                                    } catch (ExecutionException e) {
                                        throw new RuntimeException(e);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                    if (Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount() > 0) {
                                        Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();
                                        ПубличноеЛогин = Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(0).trim();
                                        ПубличноеПароль = Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(1).trim();
                                    }
                                    // TODO: 18.02.2025 get name Device
                                    String ANDROID_ID =new ModulegetDeviceName().getDeviceName(context);
                                    Log.d(this.getClass().getName(), "  PUBLIC_CONTENT.ПубличноеИмяПользовательДлСервлета  " + ПубличноеЛогин +
                                            " PUBLIC_CONTENT.ПубличноеПарольДлСервлета " + ПубличноеПароль);
                                    Request originalRequest = chain.request();
                                    Request.Builder builder = originalRequest.newBuilder()
                                            .header("Content-Type", "application/octet-stream ;charset=UTF-8")
                                            .header("Accept-Encoding", "gzip,*,compress,identity,deflate,zstd,br")
                                            .header("Connection", "Keep-Alive")
                                            .header("Accept-Language", "ru-RU")
                                            .header("identifier", ПубличноеЛогин)
                                            .header("p_identifier", ПубличноеПароль)
                                            .header("id_device_androis", ANDROID_ID);
                                    Request newRequest = builder.build();
                                    return chain.proceed(newRequest);
                                }
                            }).connectTimeout(5, TimeUnit.SECONDS)
                            .writeTimeout(2, TimeUnit.MINUTES)
                            .readTimeout(2, TimeUnit.MINUTES)
                            .build();
                    ///  MediaType JSON = MediaType.parse("application/json; charset=utf-16");

                    Log.i(context.getClass().getName(), "ГенерацияJSONОтAndroid.toString()" + ГенерацияJSONОтAndroid.toString());
                    // MediaType JSON = MediaType.parse("application/json; charset=utf-16");
                      MediaType JSON = MediaType.parse("application/octet-stream; charset=utf-8");





                   // RequestBody body = RequestBody.create(JSON, СгенерированыйФайлJSONДляОтправкиНаСервер.toString());

                    RequestBody requestBody = new RequestBody() {
                        @Override
                        public MediaType contentType() {
                            try{
                                Log.d(this.getClass().getName(), " requestBody ");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                            return JSON;
                        }

                        @Override
                        public void writeTo(BufferedSink sink) throws IOException {
                            // TODO: 21.09.2023 SEND BITY FROM SERVEER
                            try (GZIPOutputStream gzipOutputStream =       new GZIPOutputStream(sink.outputStream(),2048,true );){///4096
                                // TODO: 07.10.2023  wreting to server..
                                gzipOutputStream.write(ГенерацияJSONОтAndroid);
                                gzipOutputStream.finish();
                                gzipOutputStream.close();
                                Log.d(this.getClass().getName(), " requestBody  ГенерацияJSONОтAndroid.toString() "+ГенерацияJSONОтAndroid.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        }
                    };
                    Request requestPost = new Request.Builder().post(requestBody).url(Adress).build();
                    Log.d(this.getClass().getName(), "  requestPost  " + requestPost);
                    // TODO  Call callGET = client.newCall(requestGET);
                    Dispatcher  dispatcherCallsBackСервера = okHttpClientОтправкиДанныхНаСервер.dispatcher();
                    okHttpClientОтправкиДанныхНаСервер.newCall(requestPost).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            Log.e(this.getClass().getName(), "  ERROR call  " + call + "  e" + e.toString());
                            Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + e.getMessage());
                            new RecordNewErros(context).recordnewerror(e.toString(), Class_MODEL_synchronized.class.getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            // TODO: 31.05.2022
                            dispatcherCallsBackСервера.executorService().shutdown();
                            //TODO закрываем п отоки
                        }
                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            try{
                            if (response.isSuccessful()) {
                                String  ПришедшегоПотока =    response.header("stream_size");
                                ПришедшегоПотока =     Optional.ofNullable(ПришедшегоПотока).map(String::valueOf).orElse("0");
                                Long РазмерПришедшегоПотока = Long.parseLong(ПришедшегоПотока  );
                                Integer КакаяКодировка = Integer.parseInt(   Optional.ofNullable(response.header("getcharsets")).map(String::new).orElse("0"));
                                Boolean ФлагgZIPOutputStream =Boolean.parseBoolean (  Optional.ofNullable(response.header("GZIPOutputStream")).map(String::new).orElse("false"));
                                if (РазмерПришедшегоПотока>0l) {
                                    // TODO: 07.10.2023
                                    // TODO: 07.04.2025  получаем STEAM  от сервера и обрабоатываем его для READER
                                    DownloadReader downloadReader=new DownloadReader();
                                    // TODO: 07.04.2025 обрабоатываем пршедщий файл
                                    // TODO: 07.04.2025 обрабоатываем пршедщий файл
                                    БуферCallsBackОтСеврера[0] =downloadReader.downloadReader(context, new GetBinessLogicDownloadReader(),
                                            response.body().bytes()) ;
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " БуферCallsBackОтСеврера[0]  " +БуферCallsBackОтСеврера[0]
                                            +  " РазмерПришедшегоПотока" + РазмерПришедшегоПотока);

                                        Log.d(this.getClass().getName(), " БуферCallsBackОтСеврера[0] " +  БуферCallsBackОтСеврера[0] +
                                                " РазмерПришедшегоПотока " +РазмерПришедшегоПотока);
                                }

                                Log.d(this.getClass().getName(), " БуферCallsBackОтСеврера[0] " +  БуферCallsBackОтСеврера[0] +  " РазмерПришедшегоПотока " +РазмерПришедшегоПотока);

                                // TODO: 28.12.2024 closeting
                                response.close();
                                // TODO: 31.05.2022
                                dispatcherCallsBackСервера.executorService().shutdown();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(e.toString(),
                                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        }
                    });
                    dispatcherCallsBackСервера.executorService().awaitTermination(1, TimeUnit.DAYS);

                    Log.i(context.getClass().getName(), "БуферCallsBackОтСеврера" + БуферCallsBackОтСеврера[0]);
                    // TODO: 12.03.2023  тест код конец
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                    String ОшибкаТекущегоМетода=new String();
                    if (!ОшибкаТекущегоМетода.toString().trim().matches("(.*)java.io.EOFException(.*)") &&
                            !ОшибкаТекущегоМетода.toString().trim().matches("(.*)java.net.sockettimeoutexception(.*)") &&
                            !ОшибкаТекущегоМетода.toString().trim().matches("(.*)SocketTimeout(.*)")) {
                        Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + ex + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(context).recordnewerror(ex.toString(), Class_MODEL_synchronized.class.getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
        return БуферCallsBackОтСеврера[0];
    }

    ////--- TODO ТУТ НАХОДЯТЬСЯ КОНТЕРЙНЕРЫ ДЛЯ ВСТАВКИ И ОБНОВЛЕНИИ ДАННЫХ  ЧЕРЕЗ КОНТЕЙНЕРЫ


    ///////// TODO УНИВЕРСАЛЬНЫЙ МЕТОД ВСТАВКИ ДАННЫХ
    protected Long ВставкаДанныхЧерезКонтейнерУниверсальная(String ТаблицаКудаВставляем,
                                                            ContentValues КонтейнерДляВставки,
                                                            String ИмяТаблицыОтАндройда_Локальноая,
                                                            String ПолученнаяДатаДляПониманияДатуСейчасВставляетьИлиНет,
                                                            boolean ФлагОбновлятьДатуВерсииДанных,
                                                            int ДляСинхронизацииОбщееКоличествоСколькоСтрочекJSON,
                                                            boolean СинхронизациюВизуализировать,
                                                            Context КонтекстСинхроДляКонтроллера,
                                                            @NotNull CompletionService МенеджерПотоков,
                                                            @NotNull SQLiteDatabase getБазаДанныхДЛяОперацийВнутри,
                                                            Integer ИндексТекущейОперацииJSONДляВизуальнойОбработки)
            throws ExecutionException, InterruptedException, TimeoutException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        ///////////////////////////////////////////////////////////////
        ///////ПОПЫТКА ПОДКЛЮЧЧЕНИЕ К ИНТРЕНТУ
        Long Результат_ВставкиДанных = 0l;
        //
        Integer Результат_ПриписиИзменнийВерсииДанных = 0;
        //
        Class_GRUD_SQL_Operations class_grud_sql_operationsВставка;
        //
        class_grud_sql_operationsВставка = new Class_GRUD_SQL_Operations(context);
///

/////
        try {
            //

                try {

                    // TODO: 06.09.2021 параметры для вствки
                    class_grud_sql_operationsВставка.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаВставляем);
                    ////



                    // TODO: 06.09.2021 контейнер для вставки
                    class_grud_sql_operationsВставка.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляВставки);


                    ///TODO РЕЗУЛЬТА изменения версии данных
                    Результат_ВставкиДанных= (Long)  class_grud_sql_operationsВставка.
                            new InsertData(context).insertdata(class_grud_sql_operationsВставка.concurrentHashMapНабор,
                            КонтейнерДляВставки,
                            МенеджерПотоков,getБазаДанныхДЛяОперацийВнутри);

                    Log.d(this.getClass().getName(), "Результат_ВставкиДанных   " + Результат_ВставкиДанных);
//////////////////////todo old



                    //////
                    if (Результат_ВставкиДанных > 0) {
                        ////успешная вставка данных
                        ///TODO ПЕРОВЕ ТРАНЗАКЦИЯ ВСТАВКИ ДАННЫХ
                        ///TODO ПЕРВАЯ ТРАНЗАКЦИЯ



                        ///
                        КонтейнерДляВставки.clear();
                        ////
                        Log.d(this.getClass().getName(), " Результат_ВставкиДанных   " + Результат_ВставкиДанных + "   PUBLIC_CONTENT.СколькоСтрочекJSONПоКонкретнойТаблице "
                                +              ИндексТекущейОперацииJSONДляВизуальнойОбработки  );

                        //метод анализируем стоит ли вставлять дату сейчас в таблицу модификацию версия данных локального
                        ////////вторая часть операции после успешной вствки изменяем данные на дабоавляем дату в таблицу модификаци  клиент
                        //////TODO ВАЖВАНО ПЕРВЫЙ ЗАПУСК БАЗЫ ВСЕГОДА FALSE ДЛЯ ПОНИМАНИЯ ЕСЛИ ЭТО НУЛЕВОЙ ЗАПУСК ТО НЕ НАДО В ТАБЛИЦУК МОДИФИКАФЕН КЛИЕНТ ВСТАЯЛТЬ ДАТУ СЕЙЧАС

                    }



                    /////
                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    ////// начало запись в файл

                }

            ///////////TODO визуализация даных по строчно из НИЖНЕГО ПОТОКА ПОДМИНИМАЕМСЯ НА ВЕРХ ОПЕРЦИЯ ВСТАВКА

            // TODO: 07.09.2021 ССЫЛКА НА КЛАСС КОТОРЫЙ ЗАНИМАЕТЬСЯ ОТПОБРАЖЕНИЕМ ВИЗУАЛЬНЫЙ СИЕНХОООНИАХЦИИ   ВСТАВКА


  /*          //
        new Class_Visible_Processing_Async(contextСозданиеБАзы).ГенерируемПРОЦЕНТЫДляAsync(
                    ДляСинхронизацииОбщееКоличествоСколькоСтрочекJSON,
                    СинхронизациюВизуализировать,
                    (Activity) ActivityДляСинхронизацииОбмена,
                ИндексТекущейОперацииJSONДляВизуальнойОбработки,
                СколькоСтрочекJSON,ФиналПроценты);
*/








            //////
        } catch (Exception e) {///////ошибки
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), Class_MODEL_synchronized.class.getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ////// начало запись в файл

        }
        ////TODO метод вставки
        return Результат_ВставкиДанных;
    }


    ///////// TODO УНИВЕРСАЛЬНЫЙ МЕТОД ВСТАВКИ ДАННЫХ
    protected Long ВставкаДанныхЧерезКонтейнерУниверсальнаяЧерезContentResolver(String ТаблицаКудаВставляем,
                                                                                ContentValues КонтейнерДляВставки,
                                                                                String ИмяТаблицыОтАндройда_Локальноая,
                                                                                String ПолученнаяДатаДляПониманияДатуСейчасВставляетьИлиНет,
                                                                                boolean ФлагОбновлятьДатуВерсииДанных,
                                                                                int ДляСинхронизацииОбщееКоличествоСколькоСтрочекJSON,
                                                                                boolean СинхронизациюВизуализировать,
                                                                                Context context,
                                                                                @NotNull CompletionService МенеджерПотоков,
                                                                                @NotNull SQLiteDatabase getБазаДанныхДЛяОперацийВнутри,
                                                                                Integer СколькоСтрочекJSON,
                                                                                Integer ИндексТекущейОперацииJSONДляВизуальнойОбработки)
            throws ExecutionException, InterruptedException, TimeoutException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        ///////////////////////////////////////////////////////////////
        ///////ПОПЫТКА ПОДКЛЮЧЧЕНИЕ К ИНТРЕНТУ
        Long Результат_ВставкиДанных = 0l;
        //
        Integer Результат_ПриписиИзменнийВерсииДанных = 0;
        //
        Class_GRUD_SQL_Operations class_grud_sql_operationsВставка;
        //
        class_grud_sql_operationsВставка = new Class_GRUD_SQL_Operations(this.context);
///

/////
        try {
            //

            try {

                // TODO: 06.09.2021 параметры для вствки
                class_grud_sql_operationsВставка.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаВставляем);
                ////



                // TODO: 06.09.2021 контейнер для вставки
                // class_grud_sql_operationsВставка.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляВставки);


                ///TODO РЕЗУЛЬТА изменения версии данных
                Результат_ВставкиДанных= (Long)  class_grud_sql_operationsВставка.
                        new InsertData(this.context).insertdata(class_grud_sql_operationsВставка.concurrentHashMapНабор,
                        КонтейнерДляВставки,
                        МенеджерПотоков,getБазаДанныхДЛяОперацийВнутри);

                Log.d(this.getClass().getName(), "Результат_ВставкиДанных   " + Результат_ВставкиДанных);
//////////////////////todo old



                //////
                if (Результат_ВставкиДанных > 0) {
                    ////успешная вставка данных
                    ///TODO ПЕРОВЕ ТРАНЗАКЦИЯ ВСТАВКИ ДАННЫХ
                    ///TODO ПЕРВАЯ ТРАНЗАКЦИЯ



                    ///
                    КонтейнерДляВставки.clear();
                    ////
                    Log.d(this.getClass().getName(), " Результат_ВставкиДанных   " + Результат_ВставкиДанных + "   PUBLIC_CONTENT.СколькоСтрочекJSONПоКонкретнойТаблице "
                            +              ИндексТекущейОперацииJSONДляВизуальнойОбработки  +  "  СколькоСтрочекJSON " +СколькоСтрочекJSON);

                    //метод анализируем стоит ли вставлять дату сейчас в таблицу модификацию версия данных локального
                    ////////вторая часть операции после успешной вствки изменяем данные на дабоавляем дату в таблицу модификаци  клиент
                    //////TODO ВАЖВАНО ПЕРВЫЙ ЗАПУСК БАЗЫ ВСЕГОДА FALSE ДЛЯ ПОНИМАНИЯ ЕСЛИ ЭТО НУЛЕВОЙ ЗАПУСК ТО НЕ НАДО В ТАБЛИЦУК МОДИФИКАФЕН КЛИЕНТ ВСТАЯЛТЬ ДАТУ СЕЙЧАС

                }



                /////
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(this.context).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                ////// начало запись в файл

            }

            ///////////TODO визуализация даных по строчно из НИЖНЕГО ПОТОКА ПОДМИНИМАЕМСЯ НА ВЕРХ ОПЕРЦИЯ ВСТАВКА

            // TODO: 07.09.2021 ССЫЛКА НА КЛАСС КОТОРЫЙ ЗАНИМАЕТЬСЯ ОТПОБРАЖЕНИЕМ ВИЗУАЛЬНЫЙ СИЕНХОООНИАХЦИИ   ВСТАВКА


  /*          //
        new Class_Visible_Processing_Async(contextСозданиеБАзы).ГенерируемПРОЦЕНТЫДляAsync(
                    ДляСинхронизацииОбщееКоличествоСколькоСтрочекJSON,
                    СинхронизациюВизуализировать,
                    (Activity) ActivityДляСинхронизацииОбмена,
                ИндексТекущейОперацииJSONДляВизуальнойОбработки,
                СколькоСтрочекJSON,ФиналПроценты);
*/








            //////
        } catch (Exception e) {///////ошибки
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(this.context).recordnewerror(e.toString(), Class_MODEL_synchronized.class.getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ////// начало запись в файл

        }
        ////TODO метод вставки
        return Результат_ВставкиДанных;
    }

    //////TODO метод пребразует цифры из цикла в проценты


    /////////TODO  ОБНОВЛЕНИЕ КОНТЕЙНЕР ВСТВКИ ДАННЫХ УНИВЕРСАЛЬНЫЙ
    protected Integer ОбновлениеДанныхЧерезКонтейнерУниверсальная(String ТаблицаКудаОбновляем,
                                                                  ContentValues КонтейнерДляОбновления,
                                                                  String UUIDДляСостыковПриОбновления,
                                                                  int ДляСинхронизацииОбщееКоличествоСколькоСтрочекJSON,
                                                                  boolean СинхронизациюВизуализировать,
                                                                  Context КонтекстСинхроДляКонтроллера,
                                                                  String ИндификаторЧерезЧегоОбнолвяемсяUUIDИлиID,
                                                                  CompletionService МенеджерПотоков,
                                                                  SQLiteDatabase getБазаДанныхДЛяОперацийВнутри,
                                                                  Integer СколькоСтрочекJSON,
                                                                  Integer ИндексТекущейОперацииJSONДляВизуальнойОбработки)
            throws ExecutionException, InterruptedException, TimeoutException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        Integer Результат_ОбновлениеДанных = 0;
        int Результат_ПриписиИзменнийВерсииДанных = 0;
        Class_GRUD_SQL_Operations class_grud_sql_operationsОбновление;
        try {
            class_grud_sql_operationsОбновление=new Class_GRUD_SQL_Operations(context);
            class_grud_sql_operationsОбновление.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаОбновляем);
            class_grud_sql_operationsОбновление.concurrentHashMapНабор.put("Флаг_ЧерезКакоеПолеОбновлением",ИндификаторЧерезЧегоОбнолвяемсяUUIDИлиID);
            class_grud_sql_operationsОбновление.concurrentHashMapНабор.put("ЗначениеФлагОбновления",UUIDДляСостыковПриОбновления);
            class_grud_sql_operationsОбновление.concurrentHashMapНабор.put("ЗнакФлагОбновления","=");
            class_grud_sql_operationsОбновление.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляОбновления);
                    ///TODO РЕЗУЛЬТАТ ОБНОВЛЕНИЕ ДАННЫХ
            Результат_ОбновлениеДанных= (Integer)  class_grud_sql_operationsОбновление.
                    new UpdateData(context).updatedata(class_grud_sql_operationsОбновление.concurrentHashMapНабор,
                    class_grud_sql_operationsОбновление.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                    МенеджерПотоков,getБазаДанныхДЛяОперацийВнутри);
            Log.d(this.getClass().getName(), "Результат_ОбновлениеДанных   " + Результат_ОбновлениеДанных);
                        if (Результат_ОбновлениеДанных > 0) {
                            Log.d(this.getClass().getName(), " Результат_ВставкиДанных   "
                                    + Результат_ОбновлениеДанных + "   PUBLIC_CONTENT.СколькоСтрочекJSONПоКонкретнойТаблице " + ИндексТекущейОперацииJSONДляВизуальнойОбработки+
                                     "  СколькоСтрочекJSON " +СколькоСтрочекJSON);
                        }
            Log.d(this.getClass().getName(), "ИндексТекущейОперацииJSONДляВизуальнойОбработки   " +ИндексТекущейОперацииJSONДляВизуальнойОбработки+ " СколькоСтрочекJSON " +СколькоСтрочекJSON);
        } catch (Exception e) {///////ошибки
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), Class_MODEL_synchronized.class.getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return Результат_ОбновлениеДанных;
    }




    ///-------TODO ДАННЫЕЙ МЕТОД ОБНОЛДЯЕТЬ ДАННЫЕ ТОЛЬКО ВЕРСИИ ДАННЫХ ИЗМЕНЯЕТЬ ПРОТО  ДАТЫ В MODIFICATION CLIENT



    ///////// TODO  КОНЕЦ УНИВЕРСАЛЬНЫЙ МЕТОД ВСТАВКИ ДАННЫХ






    // TODO: 11.08.2021  запись изменения ТОЛЬКО ДЛЯ ЧАТА СПИМИНЕНИЕМ ВЕРСИИ ДАННЫХ


    ///////// TODO  КОНЕЦ УНИВЕРСАЛЬНЫЙ МЕТОД ВСТАВКИ ДАННЫХ


    /////////TODO КОНТЕЙНЕР ЛОКАЛЬНОГО ОБНОВЛЕНИЯ  ДАННЫХ УНИВЕРСАЛЬНЫЙ
    public Integer ЛокальногоОбновлениеДанныхЧерезКонтейнерУниверсальная(String ТаблицаКудаОбновляем,
                                                                        @NonNull ContentValues КонтейнерДляЛокальногоОбновления,
                                                                         Long UUIDДляСостыковПриОбновления,
                                                                         String ФлагЧерезЧегоОбновляетьсяIDилиUUID) throws ExecutionException, InterruptedException, TimeoutException {
        //////////////////////////////////////////////////
        ///////ПОПЫТКА ПОДКЛЮЧЧЕНИЕ К ИНТРЕНТУ

        Integer Результат_ЛокальногоОбновлениеДанных = 0;


        System.out.println(" ОбновлениеДанныхЧерезКонтейнерУниверсальная ");

        String ОшибкаТекущегоМетода;


        Class_GRUD_SQL_Operations class_grud_sql_operationsЛокальноеОбновление;

            try {



                    class_grud_sql_operationsЛокальноеОбновление=new Class_GRUD_SQL_Operations(context);


                    // TODO: 06.09.2021 ПАРАМЕТРЫ ДЛЯ ЛОКАЛЬНОГО ОБНОВЛЕНИЯ

                    class_grud_sql_operationsЛокальноеОбновление.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаОбновляем);
                    ///

                    class_grud_sql_operationsЛокальноеОбновление.concurrentHashMapНабор.put("Флаг_ЧерезКакоеПолеОбновлением",ФлагЧерезЧегоОбновляетьсяIDилиUUID);
                    /////

                    ///

                    class_grud_sql_operationsЛокальноеОбновление.concurrentHashMapНабор.put("ЗначениеФлагОбновления",UUIDДляСостыковПриОбновления);

//

                    class_grud_sql_operationsЛокальноеОбновление.
                            concurrentHashMapНабор.put("ЗнакФлагОбновления","=");

                    // TODO: 06.09.2021  КОНТЕЙНЕР ДЛЯ ЛОКАЛЬНОГОМ ОБНОВЛЕНИЯ

                    class_grud_sql_operationsЛокальноеОбновление.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляЛокальногоОбновления);


                            ///TODO РЕЗУЛЬТАТ ВСТАВКИ ДАННЫХ



                    Результат_ЛокальногоОбновлениеДанных= (Integer) class_grud_sql_operationsЛокальноеОбновление.
                            new UpdateData(context).updatedata(class_grud_sql_operationsЛокальноеОбновление.concurrentHashMapНабор,
                            class_grud_sql_operationsЛокальноеОбновление.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,
                            sqLiteDatabase);


                    Log.d(this.getClass().getName(), "Результат_ЛокальногоОбновлениеДанных   " + Результат_ЛокальногоОбновлениеДанных);


                    if(Результат_ЛокальногоОбновлениеДанных==null){
                        ///
                        Результат_ЛокальногоОбновлениеДанных=0;
                    }





                    if (Результат_ЛокальногоОбновлениеДанных > 0) {
                        ///todo первое сохранение транзакции
                        //ССылкаНаСозданнуюБазу.
                        Log.d(this.getClass().getName(), "Результат_ЛокальногоОбновлениеДанных   " +Результат_ЛокальногоОбновлениеДанных);
                    }

                    Log.d(this.getClass().getName(), "Результат_ЛокальногоОбновлениеДанных   " +Результат_ЛокальногоОбновлениеДанных);


                    ///


            } catch (Exception e) {///////ошибки
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                ОшибкаТекущегоМетода = e.toString();
                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

            }


        //   publishProgress(Результат_ПриписиИзменнийВерсииДанных);

        return Результат_ЛокальногоОбновлениеДанных;
    }
    //////метод записывает данные о времени в таблицу модификации ерсии данных


    /////////КОНТЕЙНЕР ВСТВКИ ДАННЫХ УНИВЕРСАЛЬНЫЙ
    public Long ВставкаДанныхЧерезКонтейнерТолькоПриСозданииНовогоСотрудникаУниверсальная(String ТаблицаКудаВставляем, ContentValues КонтейнерДляВставкиНовогоСотрудника)  {


        Long Результат_ВставкиДанныхТолькоДляСотрудникаНового = 0l;
        Integer Результат_ПриписиИзменнийВерсииДанных = 0;
        Class_GRUD_SQL_Operations class_grud_sql_operationsВставкаСотрудника;
            try {
                Long ВытаскиваемПОвышенуюВерисюДанныхВнутриПоля_CurrenTable=КонтейнерДляВставкиНовогоСотрудника.getAsLong("current_table");
                    class_grud_sql_operationsВставкаСотрудника=new Class_GRUD_SQL_Operations(context);
                    /////TODO параменты для вставки
                    class_grud_sql_operationsВставкаСотрудника.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаВставляем);
                    /////TODO контейнер для вставки
                    class_grud_sql_operationsВставкаСотрудника.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляВставкиНовогоСотрудника);
                    ///TODO РЕЗУЛЬТА изменения версии данных
                    Результат_ВставкиДанныхТолькоДляСотрудникаНового= (Long)  class_grud_sql_operationsВставкаСотрудника.
                            new InsertData(context).insertdata(class_grud_sql_operationsВставкаСотрудника.concurrentHashMapНабор,
                            class_grud_sql_operationsВставкаСотрудника.contentValuesДляSQLBuilder_Для_GRUD_Операций ,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,
                            sqLiteDatabase);
                    Log.d(this.getClass().getName(), " Результат_ВставкиДанныхТолькоДляСотрудникаНового  "+Результат_ВставкиДанныхТолькоДляСотрудникаНового);
                    if(Результат_ВставкиДанныхТолькоДляСотрудникаНового==null){
                        Результат_ВставкиДанныхТолькоДляСотрудникаНового=0l;
                    }
            } catch (Exception e) {///////ошибки
                e.printStackTrace();
                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        return Результат_ВставкиДанныхТолькоДляСотрудникаНового;
    }


    // TODO: 10.07.2021  для чата


















  public Long ВставкаДанныхЧерезКонтейнерТолькоПриСозданииНСообщенияДЛЯЧата(String ТаблицаКудаВставляем, ContentValues КонтейнерДляВставкиДляЧата,
                                                                            String ИмяТаблицыОтАндройда_Локальноая,
                                                                            String ПолученнаяДатаДляПониманияДатуСейчасВставляетьИлиНет,
                                                                            boolean ФлагОбновлятьДатуВерсииДанных) throws ExecutionException,
            InterruptedException, TimeoutException {
        ///////////////////////////////////////////////////////////////////////////
        ///////ПОПЫТКА ПОДКЛЮЧЧЕНИЕ К ИНТРЕНТУ

      Long Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата = 0l;
        /////
        Integer Результат_ПриписиИзменнийВерсииДанных = 0;

        System.out.println(" Вставка Данных Через Контейнер Универсальная");

        ////TOdo начинаем таранзакццию
      Class_GRUD_SQL_Operations class_grud_sql_operationsВставкаЧата;



            try {
                try{

                    ////
                    class_grud_sql_operationsВставкаЧата=new Class_GRUD_SQL_Operations(context);

                    // TODO: 06.09.2021 ПАРАМЕТРЫ ДЛЯ ВСТАВКИ ДАННЫХ ЧАТА

                    class_grud_sql_operationsВставкаЧата.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаВставляем);



                    // TODO: 06.09.2021 КОНТЕЙНЕР ДЛЯ ВСТАВКИ ДАННЫХ ЧАТА


                    class_grud_sql_operationsВставкаЧата.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляВставкиДляЧата);

                    // TODO: 12.10.2021  Ссылка Менеджер Потоков



                    // TODO: 06.09.2021 САМА ВСТАВКА ДЛЯ ЧАТА ЧАТА

                    ///TODO РЕЗУЛЬТА изменения версии данных
                    Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата= (Long)  class_grud_sql_operationsВставкаЧата.
                            new InsertData(context).insertdata(class_grud_sql_operationsВставкаЧата.concurrentHashMapНабор,
                            class_grud_sql_operationsВставкаЧата.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,
                            sqLiteDatabase);
//

                    Log.d(this.getClass().getName(), "Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата   " + Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата);

         /*                  ВставкиДанных=new    ();
                       ВставкиДанных.setTables(ТаблицаКудаВставляем);


                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            Результат_ВставкиДанныхТолькоДляСотрудникаНового =             ВставкиДанных.insert(ССылкаНаСозданнуюБазу, КонтейнерДляВставки);
                        }else {

                            Результат_ВставкиДанныхТолькоДляСотрудникаНового =     ССылкаНаСозданнуюБазу.insert(ТаблицаКудаВставляем,null,КонтейнерДляВставки);

                        }

*/

                    //////////
                    if (Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата > 0) {
                        ////успешная вставка данных
                        ///TODO ПЕРОВЕ ТРАНЗАКЦИЯ ВСТАВКИ ДАННЫХ

                        Log.d(this.getClass().getName(), " Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата   " + Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата);
                    }
                    ///
                } catch (Exception e) {///////ошибки
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(context).recordnewerror(e.toString(), Class_MODEL_synchronized.class.getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

                }
                //////



        /*        //метод анализируем стоит ли вставлять дату сейчас в таблицу модификацию версия данных локального
                ////////вторая часть операции после успешной вствки изменяем данные на дабоавляем дату в таблицу модификаци  клиент
                //////ДЛЯ ПОНИМАНИЯ ЕСЛИ ЭТО НУЛЕВОЙ ЗАПУСК ТО НЕ НАДО В ТАБЛИЦУК МОДИФИКАФЕН КЛИЕНТ ВСТАЯЛТЬ ДАТУ СЕЙЧАС
                if (ФлагОбновлятьДатуВерсииДанных == true && Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата > 0) {/////ПРИ УКАЗАНОЙ ДАТЕ ПОДТВЕРЖДАТЬ ВРЕМЯ НЕ НАДО
                    /////запускаем втроую транзакцию


                    ///todo ДАННЫЙ КОД ИЗМЕНЯЕТ ВЕРИСЮ ДАННЫХ

                    ///ПослеУспешнойОперациии записать в табблицу версии данных на клиенте
                    // TODO: 03.09.2021  получение ПО НОВОМУ ДВИЖКУ
                    Class_GRUD_SQL_Operations  classGrudSqlOperationsВставкиДанныхПриСозданииНСообщенияДЛЯЧата;
                    // TODO: 30.08.2021    КОД ОБНОВЛЕНИЕ   ДАННЫХ   ЧЕРЕЗ
                    //////
                    classGrudSqlOperationsВставкиДанныхПриСозданииНСообщенияДЛЯЧата=new Class_GRUD_SQL_Operations(contextСозданиеБАзы);
                    ///
                    classGrudSqlOperationsВставкиДанныхПриСозданииНСообщенияДЛЯЧата. concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы",ИмяТаблицыОтАндройда_Локальноая);
                    ///
                    classGrudSqlOperationsВставкиДанныхПриСозданииНСообщенияДЛЯЧата. concurrentHashMapНабор.put("ФлагТипИзменениеВерсииДанныхЛокальнаяСервернаяИлиОба","Локальное");
                    ///

                    ///TODO РЕЗУЛЬТА изменения версии данных
                       Результат_ПриписиИзменнийВерсииДанных= (Integer) classGrudSqlOperationsВставкиДанныхПриСозданииНСообщенияДЛЯЧата.
                            new ChangesVesionData(contextСозданиеБАзы).changesvesiondata(classGrudSqlOperationsВставкиДанныхПриСозданииНСообщенияДЛЯЧата. concurrentHashMapНабор);
//

                    ////
                    if(Результат_ПриписиИзменнийВерсииДанных==null){
                        ////
                        Результат_ПриписиИзменнийВерсииДанных=0;
                    }
                    // TODO: 03.09.2021



                    ///todo  конец  ДАННЫЙ КОД ИЗМЕНЯЕТ ВЕРИСЮ ДАННЫХ

                    if (Результат_ПриписиИзменнийВерсииДанных > 0) {

                        ///TODO ПЕРОВЕ ТРАНЗАКЦИЯ ВСТАВКИ ДАННЫХ
                        Log.w(this.getClass().getName(), "  Результат_ПриписиИзменнийВерсииДанных  ПОСЛЕ ОПЕРАЦИИ ПОВЫШАЕМ ВЕРИСЮ ДАННЫХ....   " +Результат_ПриписиИзменнийВерсииДанных );
                    }else{

                        Log.e(this.getClass().getName(), " ОШибка  Результат_ПриписиИзменнийВерсииДанных  ПОСЛЕ ОПЕРАЦИИ ПОВЫШАЕМ ВЕРИСЮ ДАННЫХ....   " +Результат_ПриписиИзменнийВерсииДанных );
                    }


                }*/


            } catch (Exception e) {///////ошибки
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                ///

            }

        //// todo get ASYNtASK
        return Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата;
    }











    // TODO: 10.07.2021  для чата


    /////////КОНТЕЙНЕР ВСТВКИ ДАННЫХ УНИВЕРСАЛЬНЫЙ
    public Long ВставкаДанныхЧерезКонтейнерОрганизацияДляТекущегоСотрудникаУниверсальная(String ТаблицаКудаВставляем, ContentValues КонтейнерДляВставкиОрганизацияДляТекущегоСотрудника,
                                                                                         String ИмяТаблицыОтАндройда_Локальноая,
                                                                                         String ПолученнаяДатаДляПониманияДатуСейчасВставляетьИлиНет,
                                                                                         boolean ФлагОбновлятьДатуВерсииДанных, int ПубличныйIDДляорганизацции,
                                                                                         String ДатаДляОбновлениеОргназации) throws ExecutionException,
            InterruptedException, TimeoutException {
        ///////////////////////////////////////////////////////////////////////////
        ///////ПОПЫТКА ПОДКЛЮЧЧЕНИЕ К ИНТРЕНТУ

          Long Результат_ОбновленияДанныхОрганизация = 0l;
        ///
        Long Результат_ВставкиДанныхОрганизация = 0l;
        ///
        int Результат_ПриписиИзменнийВерсииДанных = 0;

        Class_GRUD_SQL_Operations class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации;

            try {
                //

                    class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации=new Class_GRUD_SQL_Operations(context);

                    // TODO: 06.09.2021 ПАРАРМЕТРЫ ДЛЯ ВСТАВКИ НОВОЙ ОРГАНИЗАЦИИ


                    class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаВставляем);

                    //
                    class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.concurrentHashMapНабор.put("Флаг_ЧерезКакоеПолеОбновлением","user_update");


                    //
                    class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.concurrentHashMapНабор.put("ЗначениеФлагОбновления",ПубличныйIDДляорганизацции);


//

                class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.
                        concurrentHashMapНабор.put("ЗнакФлагОбновления","=");
                    // TODO: 06.09.2021 КОНТЕЙНЕР ДЛЯ ВСТАВКИ НОВОЙ ОРГАНИЗАЦИИ

                    class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляВставкиОрганизацияДляТекущегоСотрудника);


                    // TODO: 06.09.2021 САМА ОПРАЦЙИЯ ОБНОВЛЕНИЯ ПЕРЫЙ ШАГ ЕСЛИ ВЫЙДЕТ, ТО ПОСЛЕДНИЙ

                // TODO: 12.10.2021  Ссылка Менеджер Потоков




                    ///TODO РЕЗУЛЬТА изменения версии данных
                    Результат_ОбновленияДанныхОрганизация= (Long)  class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.
                            new UpdateData(context).updatedata(class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.concurrentHashMapНабор,
                            class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,
                            sqLiteDatabase);

                Log.d(this.getClass().getName(), "Результат_ОбновленияДанныхОрганизация   " + Результат_ОбновленияДанныхОрганизация);

           /*                ПриписиИзменнийВерсииДанных=new    ();
                       ПриписиИзменнийВерсииДанных.setTables(ТаблицаКудаВставляем);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        Результат_ВставкиДанныхОрганизация  =             ПриписиИзменнийВерсииДанных.
                                update(ССылкаНаСозданнуюБазу,КонтейнерДляВставки, "user_update=?",
                                        new String[]{String.valueOf(ПубличныйIDДляорганизацции)});
                    }*/


                    ///TODO ПЕРОВЕ ТРАНЗАКЦИЯ ВСТАВКИ ДАННЫХ
                    Log.d(this.getClass().getName(), " Результат_ОбновленияДанныхОрганизация   " + Результат_ОбновленияДанныхОрганизация);


                    ///////TODO  ЕСЛИ ОПЕРАЦИЯ ОБНОВЛЕНИЯ НЕ ПРОШЛА ТО НИЖЕ ПРОИЗВОДИМ ВСТАВКИ УЖЕ ДАННЫХ -- ЭТО НУЖНО КОГДА ПЕРВЫЙ ЗАПЦУСК ПРОГРАММЫ И НЕТ ЫВООБЩЕ ДАННЫХ В БАЗЕ И ПРОИЗВХОДИТ РАЗНИЦА ВСТАВКА ИЛИ ОБНОВЛЕНИЕ
                    if (Результат_ОбновленияДанныхОрганизация > 0) {
                        ////успешная вставка данных
                        ///TODO ПЕРОВЕ ТРАНЗАКЦИЯ ВСТАВКИ ДАННЫХ
                        Log.d(this.getClass().getName(), " Результат_ВставкиДанныхОрганизация   " + Результат_ВставкиДанныхОрганизация);

                    } else {




                        ///TODO ВТОРАЯ ЧАТЬ ВСТАВКИ НОВОЙ ОРГАНИЗАЦИИ ПРОИХВОДИМ УЖЕ ВСТАВКУ ПОСЛЕ НЕ УДАЧНОГО ОБНВЛЕНИЯ





                        class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации=new Class_GRUD_SQL_Operations(context);

                        // TODO: 06.09.2021 ПАРАРМЕТРЫ ДЛЯ ВСТАВКИ НОВОЙ ОРГАНИЗАЦИИ


                        class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаВставляем);




                        Результат_ВставкиДанныхОрганизация= (Long)  class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.
                                new InsertData(context).insertdata(class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.concurrentHashMapНабор,
                                class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.contentValuesДляSQLBuilder_Для_GRUD_Операций ,
                                Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,
                                sqLiteDatabase);

                        Log.d(this.getClass().getName(), "Результат_ВставкиДанныхОрганизация   " + Результат_ВставкиДанныхОрганизация);
/*
                               ВставкиДанных=new    ();
                           ВставкиДанных.setTables(ТаблицаКудаВставляем);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            Результат_ВставкиДанныхОрганизация =             ВставкиДанных.insert(ССылкаНаСозданнуюБазу, КонтейнерДляВставки);
                        }
*/

                        ///TODO ПЕРОВЕ ТРАНЗАКЦИЯ ВСТАВКИ ДАННЫХ
                        Log.d(this.getClass().getName(), " Результат_ВставкиДанныхОрганизация   " + Результат_ВставкиДанныхОрганизация);
                    }


                    if (Результат_ВставкиДанныхОрганизация > 0) {

                        ///TODO ПЕРОВЕ ТРАНЗАКЦИЯ ВСТАВКИ ДАННЫХ


                        Log.d(this.getClass().getName(), " Результат_ВставкиДанныхОрганизация   " + Результат_ВставкиДанныхОрганизация);

                    }


                //////
            } catch (Exception e) {///////ошибки
                e.printStackTrace();
                ///метод запись ошибок в таблицу

                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                //////

            }

        return Результат_ВставкиДанныхОрганизация;
    }




















// TODO: 10.07.2021  только для чата






    /////////КОНТЕЙНЕР ВСТВКИ ДАННЫХ УНИВЕРСАЛЬНЫЙ
    public Integer wewillsetupanewPublicidaftersuccessfulsynchronizationSettingsTabels(
            @NonNull String ИмяТаблицы,
            @NonNull   ContentValues КонтейнерДляВставкиПубличныйID,
            @NonNull Integer PublicID )
            throws ExecutionException,
            InterruptedException, TimeoutException {
        // TODO: 08.10.2024
        Integer   UpdatingPublicID=0;
        try {
// TODO: 08.10.2024 Update PUBLIC ID AFTER SYNnc
            Uri uri = Uri.parse("content://com.dsy.dsu.providerforsystemtables/" + ИмяТаблицы + "");
            // TODO: 08.10.2024 Дополнительное добавление данных
            ContentResolver contentProviderNewPubicID = context.getContentResolver();
            КонтейнерДляВставкиПубличныйID.put("publicid",PublicID);
            // TODO: 09.10.2024 task for current poeration
            КонтейнерДляВставкиПубличныйID.put("currenttaskforthecontentprovider","firststartapp");

            // TODO: 08.10.2024 Находим если такой  Пользователь
          Long getuuidLocal=  new GetPublicID().gettingSettingTableVersion(context," SELECT user_update FROM "+ИмяТаблицы+"  ",ИмяТаблицы);
            // TODO: 12.04.2023 UPDATER PUBLIC ID
          if(getuuidLocal>0 ){
              // TODO: 12.04.2023 UPDATER PUBLIC ID
                 UpdatingPublicID=  contentProviderNewPubicID.update(uri, КонтейнерДляВставкиПубличныйID,null,null);
              Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                      " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                      " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ "  UpdatingPublicID " +UpdatingPublicID);


              // TODO: 08.10.2024 UNSERT PUBLIC ID
          }else {
              // TODO: 12.04.2023 INSERT PUBLIC ID
              Uri insertData = contentProviderNewPubicID.insert(uri, КонтейнерДляВставкиПубличныйID);
              if (insertData != null) {
                  String InsertingPublicID = Optional.ofNullable(insertData).map(Emmeter -> Emmeter.toString().replace("content://", "")).get();
                  UpdatingPublicID=Integer.parseInt(InsertingPublicID);
                  Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                          " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                          " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "  InsertingPublicID " + InsertingPublicID);
              }
          }


            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " getuuidLocal " +getuuidLocal);

        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return UpdatingPublicID;
    }







    /////////КОНТЕЙНЕР ВСТВКИ ДАННЫХ УНИВЕРСАЛЬНЫЙ
    public Integer wewillsetupanewPublicidaftersuccessfulsynchronizationSuccessLogin(
            @NonNull String ИмяТаблицы,
            @NonNull   ContentValues КонтейнерДляВставкиПубличныйID,
            @NonNull Integer PublicID )
            throws ExecutionException,
            InterruptedException, TimeoutException {
        // TODO: 08.10.2024
        Integer   UpdatingPublicID=0;
        try {
// TODO: 08.10.2024 Update PUBLIC ID AFTER SYNnc
            Uri uri = Uri.parse("content://com.dsy.dsu.providerforsystemtables/" + ИмяТаблицы + "");
            // TODO: 08.10.2024 Дополнительное добавление данных
            ContentResolver contentProviderNewPubicID = context.getContentResolver();
            // TODO: 08.10.2024
            КонтейнерДляВставкиПубличныйID.put("publicid",PublicID);
            
            // TODO: 08.10.2024 Находим если такой  Пользователь
            Long getuuidLocal=  new GetPublicID().gettingSettingTableVersion(context," SELECT id FROM "+ИмяТаблицы+"  ",ИмяТаблицы);
            // TODO: 08.10.2024  
            КонтейнерДляВставкиПубличныйID.put("getuuidLocal",getuuidLocal);
            // TODO: 12.04.2023 UPDATER PUBLIC ID
            if(getuuidLocal>0 ){
                // TODO: 12.04.2023 UPDATER PUBLIC ID
                UpdatingPublicID=  contentProviderNewPubicID.update(uri, КонтейнерДляВставкиПубличныйID,null,null);
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ "  UpdatingPublicID " +UpdatingPublicID);


                // TODO: 08.10.2024 UNSERT PUBLIC ID
            }else {
                // TODO: 12.04.2023 INSERT PUBLIC ID
                Uri insertData = contentProviderNewPubicID.insert(uri, КонтейнерДляВставкиПубличныйID);
                if (insertData != null) {
                    String InsertingPublicID = Optional.ofNullable(insertData).map(Emmeter -> Emmeter.toString().replace("content://", "")).get();
                    UpdatingPublicID=Integer.parseInt(InsertingPublicID);
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "  InsertingPublicID " + InsertingPublicID);
                }
            }


            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " getuuidLocal " +getuuidLocal);

        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return UpdatingPublicID;
    }































    /////////TODO  ОБНОВЛЕНИЕ КОНТЕЙНЕР ВСТВКИ ДАННЫХ УНИВЕРСАЛЬНЫЙ
    Long ОбновлениеДанныхЧерезКонтейнерТолькоПриСозданииСУниверсальная(String ТаблицаКудаОбновляем,
                                                                                      ContentValues КонтейнерДляОбновленияСозданииНовогоСотрудника,
                                                                                      String UUIDДляСостыковПриОбновления)
            throws ExecutionException,
            InterruptedException, TimeoutException {
        /////////////////////////////////////////////////////////
        ///////ПОПЫТКА ПОДКЛЮЧЧЕНИЕ К ИНТРЕНТУ

        long Результат_ОбновлениеДанныхОбновлениеСозданииНового = 0;
        ///////
        int Результат_ПриписиИзменнийВерсииДанных = 0;


        System.out.println(" ОбновлениеДанныхЧерезКонтейнерУниверсальная ");
//
Class_GRUD_SQL_Operations class_grud_sql_operationsОбвовлениеСозданииНовогоСотрудника;
            try {
                ///
                class_grud_sql_operationsОбвовлениеСозданииНовогоСотрудника=new Class_GRUD_SQL_Operations(context);


// TODO: 06.09.2021  ПАРАМЕНТЫ ДЛЯ ОБНОВЛЕНИЯ

                class_grud_sql_operationsОбвовлениеСозданииНовогоСотрудника.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаОбновляем);
                ///
                class_grud_sql_operationsОбвовлениеСозданииНовогоСотрудника.concurrentHashMapНабор.put("Флаг_ЧерезКакоеПолеОбновлением","uuid");
                ///
                class_grud_sql_operationsОбвовлениеСозданииНовогоСотрудника.concurrentHashMapНабор.put("ЗначениеФлагОбновления",UUIDДляСостыковПриОбновления);
                ///
//

                class_grud_sql_operationsОбвовлениеСозданииНовогоСотрудника.
                        concurrentHashMapНабор.put("ЗнакФлагОбновления","=");
                // TODO: 06.09.2021  КОНТЕ  НЕР ДЛЯ ОБНОВЛЕНИЯ

                class_grud_sql_operationsОбвовлениеСозданииНовогоСотрудника.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляОбновленияСозданииНовогоСотрудника);


                // TODO: 12.10.2021  Ссылка Менеджер Потоков


                // TODO: 06.09.2021 CАМО ОБНОВЛЕНИЕ


                Результат_ОбновлениеДанныхОбновлениеСозданииНового= (Long)  class_grud_sql_operationsОбвовлениеСозданииНовогоСотрудника.
                        new UpdateData(context).updatedata(class_grud_sql_operationsОбвовлениеСозданииНовогоСотрудника.concurrentHashMapНабор,
                        class_grud_sql_operationsОбвовлениеСозданииНовогоСотрудника.contentValuesДляSQLBuilder_Для_GRUD_Операций ,
                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,
                        sqLiteDatabase);

                Log.d(this.getClass().getName(), "Результат_ОбновлениеДанныхОбновлениеСозданииНового   " + Результат_ОбновлениеДанныхОбновлениеСозданииНового);
/*
                           ПриписиИзменнийВерсииДанных=new    ();
                       ПриписиИзменнийВерсииДанных.setTables(ТаблицаКудаОбновляем);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        Результат_ОбновлениеДанныхОбновлениеСозданииНового  =             ПриписиИзменнийВерсииДанных.
                                update(contextСозданиеБАзы, КонтейнерДляОбновления, "uuid= ?",
                                        new String[]{UUIDДляСостыковПриОбновления});
                    }else{

                        Результат_ОбновлениеДанныхОбновлениеСозданииНового  =        ССылкаНаСозданнуюБазу.update(ТаблицаКудаОбновляем, КонтейнерДляОбновления, "uuid= ?",
                                new String[]{UUIDДляСостыковПриОбновления});

                    }*/


                    Log.d(this.getClass().getName(), "   Результат_ОбновлениеДанныхОбновлениеСозданииНового "+Результат_ОбновлениеДанныхОбновлениеСозданииНового);




                    ///////
                    if (Результат_ОбновлениеДанныхОбновлениеСозданииНового > 0) {
                        ///TODO ПЕРВАЯ ТРАНЗАКЦИЯ
                        // ССылкаНаСозданнуюБазу.

                        Log.d(this.getClass().getName(), "   Результат_ОбновлениеДанныхОбновлениеСозданииНового "+Результат_ОбновлениеДанныхОбновлениеСозданииНового);
                    }


            } catch (Exception e) {///////ошибки
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            ////
        return Результат_ОбновлениеДанныхОбновлениеСозданииНового;
    }
































    ///////// todo КОНТЕЙНЕР ВСТВКИ ДАННЫХ УНИВЕРСАЛЬНЫЙ ТОЛЬКО ДЛЯ ЗАПИСИ ОШИБКИ
    Long ВставкаДанныхЧерезКонтейнерУниверсальнаяТолькоДляЗаписиОшибки(String ТаблицаКудаВставляем,
                                                                       ContentValues КонтейнерДляВставкиЗаписиОшибки)
            throws ExecutionException, InterruptedException, TimeoutException {
        ////////////////////////////////////////////////////////////////////////

        long Результат_ВставкиДанныхДляЗаписиОшибки = 0;
        ///
        int Результат_ПриписиИзменнийВерсииДанных = 0;


        System.out.println(" ВставкаДанныхЧерезКонтейнерУниверсальная");

        Class_GRUD_SQL_Operations class_grud_sql_operationsДляВставкиОшибок=new Class_GRUD_SQL_Operations(context);


            try {
                // TODO: 06.09.2021  ПАРАСМЕТИРЫ ВСТАВКИ ОШИБКИ

                class_grud_sql_operationsДляВставкиОшибок.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаВставляем);


                // TODO: 06.09.2021  КОНТЕЙНЕР ВСТАВКИ ОШИБКИ
                class_grud_sql_operationsДляВставкиОшибок.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляВставкиЗаписиОшибки);


                // TODO: 06.09.2021 сама операция ВСТАВКИ ОШИБКИ
                // TODO: 12.10.2021  Ссылка Менеджер Потоков



                ///TODO РЕЗУЛЬТА изменения версии данных
                Результат_ВставкиДанныхДляЗаписиОшибки= (Long)  class_grud_sql_operationsДляВставкиОшибок.
                        new InsertData(context).insertdata(class_grud_sql_operationsДляВставкиОшибок.concurrentHashMapНабор,
                        class_grud_sql_operationsДляВставкиОшибок.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,
                        sqLiteDatabase);

                Log.d(this.getClass().getName(), "Результат_ВставкиДанныхДляЗаписиОшибки   " + Результат_ВставкиДанныхДляЗаписиОшибки);
              /*      ///////

                           ВставкиДанных=new    ();
                       ВставкиДанных.setTables(ТаблицаКудаВставляем);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        Результат_ВставкиДанныхДляЗаписиОшибки =             ВставкиДанных.insert(ССылкаНаСозданнуюБазу, КонтейнерДляВставки);
                    }

*/

                    if (Результат_ВставкиДанныхДляЗаписиОшибки > 0) {
                        // ССылкаНаСозданнуюБазу.

                        Log.d(this.getClass().getName(), " Результат_ВставкиДанныхДляЗаписиОшибки  " + Результат_ВставкиДанныхДляЗаписиОшибки);

                    }
                //////
                /////НАЗВАНИЕ ПОТОКА
                Log.i(this.getClass().getName(), "НАЗВАНИЕ ПОТОКА В aSYNSTASK " + Thread.currentThread().getName().toUpperCase());
                /////
            } catch (Exception e) {///////ошибки
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        return Результат_ВставкиДанныхДляЗаписиОшибки;
    }


    /////////TODO КОНТЕЙНЕР УДАЛЕНИЕ СОТРУДНИКА ИЗ ТАБЕЛЯ  ДАННЫХ УНИВЕРСАЛЬНЫЙ
    public Integer УдалениеДанныхЧерезКонтейнерУниверсальная(String ТаблицаОткудаУдлаяемЗапись,
                                                             String ЧерезКакоеПолеУдлаяемФлаг,
                                                             Long UUIDДляСостыковПриОбновления,
                                                             String ПолеКудаИзменятьСтатус,
                                                             String ЗапоЗначенияУсменыСтатуса) throws ExecutionException,
            InterruptedException, TimeoutException {
        Integer Результат_УдалениеДанных = 0;
Class_GRUD_SQL_Operations classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная;
            try {
                //
                Log.w(this.getClass().getName(), "РЕЗУЛЬТАТ УДАЛДЕНИЕ ОДНОГО СОТРУДНИКА ПолеКудаИзменятьСтатус  "
                        + ПолеКудаИзменятьСтатус + " ЗапоЗначенияУсменыСтатуса " + ЗапоЗначенияУсменыСтатуса);
                classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная = new Class_GRUD_SQL_Operations(context);
                classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная.
                        concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы", ТаблицаОткудаУдлаяемЗапись);
                classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная.
                        concurrentHashMapНабор.put("Флаг_ЧерезКакоеПолеОбновлением",ЧерезКакоеПолеУдлаяемФлаг);
                classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная
                        .concurrentHashMapНабор.put("ЗначениеФлагОбновления",UUIDДляСостыковПриОбновления);
                classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная.
                        concurrentHashMapНабор.put("ЗнакФлагОбновления","="); //или =   или <   >
                classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная.contentValuesДляSQLBuilder_Для_GRUD_Операций
                        .put(ПолеКудаИзменятьСтатус, ЗапоЗначенияУсменыСтатуса);//todo status_write   status_send
                ///
                Log.w(this.getClass().getName(), "РЕЗУЛЬТАТ УДАЛДЕНИЕ ОДНОГО СОТРУДНИКА РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудника  "
                        + " ТаблицаОткудаУдлаяемЗапись " + ТаблицаОткудаУдлаяемЗапись);
                // TODO: 30.01.2022

                // TODO: 19.11.2022 ПОДНИМАЕМ ВЕРИСЮ ДАННЫХ
                Long РезультатУвеличинаяВерсияДАныхЧата = new VersionCurentTable(context).upVersionCurentTable(ТаблицаОткудаУдлаяемЗапись );
                Log.d(this.getClass().getName(), " РезультатУвеличинаяВерсияДАныхЧата  " + РезультатУвеличинаяВерсияДАныхЧата);

                classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная.contentValuesДляSQLBuilder_Для_GRUD_Операций.put("current_table",
                        РезультатУвеличинаяВерсияДАныхЧата);
                ///TODO РЕЗУЛЬТА ОБНОВЛЕНИЯ
                Результат_УдалениеДанных = (Integer) classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная.
                        new UpdateData(context).updatedata(classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная.
                                concurrentHashMapНабор,
                        classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков, sqLiteDatabase);
                Log.d(this.getClass().getName(), "Результат_УдалениеДанных " + Результат_УдалениеДанных);
                } catch (Exception e) {///////ошибки
                    e.printStackTrace();
                    Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(context).recordnewerror(e.toString(), Class_MODEL_synchronized.class.getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
        return Результат_УдалениеДанных;
    }






    /////TODO ЛОКАЛЬНАЯ ОБНОВЛЕНИЕ ВНУТРИ ТАБЕЛЯ
    public Long МетодЛокальноеОбновлениеВТабеле(ContentValues КонтейнерЗаполненияДаннымиПриЛокальномОбновлении,
                                                String ПолучениеЗначениеСтолбикUUID,
                                                Context КонтексДляЛокальногоОбновления,
                                                String таблицаДляЛокальногоОбонвления) throws InterruptedException, ExecutionException, TimeoutException {
        Integer результатОбновлениеЧерезКонтрейнер = 0;
        try {
            ///////TODO САМ ВЫЗОВ МЕТОДА ОБНОВЛЕНИЕ ЛОКАЛЬНОГО обновление uuid
            результатОбновлениеЧерезКонтрейнер = ЛокальногоОбновлениеДанныхЧерезКонтейнерУниверсальная(таблицаДляЛокальногоОбонвления,
                    КонтейнерЗаполненияДаннымиПриЛокальномОбновлении,
                    Long.parseLong(ПолучениеЗначениеСтолбикUUID), "uuid");
            Log.d(this.getClass().getName(),
                    "  результатОбновлениеЧерезКонтрейнер[0] " + результатОбновлениеЧерезКонтрейнер);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return Long.parseLong(String.valueOf(результатОбновлениеЧерезКонтрейнер));//5,TimeUnit.SECONDS

    }




















    /////////TODO КОНТЕЙНЕР УДАЛЕНИЕ СОТРУДНИКА ИЗ ТАБЕЛЯ  ДАННЫХ УНИВЕРСАЛЬНЫЙ
    public Integer УдалениеТолькоПустогоТабеляЧерезКонтейнерУниверсальная(String ТаблицаОткудаУдлаяемЗапись,
                                                                          String ЧерезКакоеПолеУдлаяемФлаг,
                                                                          Long UUIDДляСостыковПриОбновления)
            throws ExecutionException,
            InterruptedException, TimeoutException {
        Integer Результат_ОбновлениеДанных = 0;
        Integer Результат_ПриписиИзменнийВерсииДанных = 0;
        // TODO: 03.09.2021  получение ПО НОВОМУ ДВИЖКУ
        Class_GRUD_SQL_Operations  classGrudSqlOperationsДляУдаленияСотрудника;
        // TODO: 30.08.2021    КОД ОБНОВЛЕНИЕ   ДАННЫХ   ЧЕРЕ
            try {
                // TODO: 03.09.2021  получение ПО НОВОМУ ДВИЖКУ
                classGrudSqlOperationsДляУдаленияСотрудника=new Class_GRUD_SQL_Operations(context);
                classGrudSqlOperationsДляУдаленияСотрудника.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы",ТаблицаОткудаУдлаяемЗапись);
                classGrudSqlOperationsДляУдаленияСотрудника.concurrentHashMapНабор.put("Флаг_ЧерезКакоеПолеСнаДанных",ЧерезКакоеПолеУдлаяемФлаг);
                classGrudSqlOperationsДляУдаленияСотрудника.concurrentHashMapНабор.put("ЗначениеФлагСнаДанных",UUIDДляСостыковПриОбновления);
                // TODO: 06.09.2021  КОНТЕЙНЕР ДЛЯ УДАЛЕНИЯ
                ContentValues АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная=new ContentValues();
                АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная.put("status_send", "Удаленная");///ПОКА НЕ ОТКЛЮЧИЛИ
                String СгенерированованныйДатаВремениСейчаcДляУдаления=     new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();
                АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная.put("date_update", СгенерированованныйДатаВремениСейчаcДляУдаления);///ПОКА НЕ ОТКЛЮЧИЛИ
                Class_GRUD_SQL_Operations        class_grud_sql_operationsПовышаемВерсиюДанныхПриСозданеииИзШаблонаСотрудника=new Class_GRUD_SQL_Operations(context);


                // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
                Long РезультатУвеличинаяВерсияПриудалениеСотрудника = new VersionCurentTable(context).upVersionCurentTable(ТаблицаОткудаУдлаяемЗапись );
                Log.d(this.getClass().getName(), " РезультатУвеличинаяВерсияПриудалениеСотрудника  " + РезультатУвеличинаяВерсияПриудалениеСотрудника);

                //TODO  конец курант ча
                АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная.put("current_table", РезультатУвеличинаяВерсияПриудалениеСотрудника);
                classGrudSqlOperationsДляУдаленияСотрудника.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная);
                Log.d(this.getClass().getName(), "UUIDДляСостыковПриОбновления   " +UUIDДляСостыковПриОбновления );
                    if (UUIDДляСостыковПриОбновления > 0) {
                        Результат_ОбновлениеДанных= (Integer)  classGrudSqlOperationsДляУдаленияСотрудника.
                                new SleepData(context).sleepdata(classGrudSqlOperationsДляУдаленияСотрудника.concurrentHashMapНабор,
                                classGrudSqlOperationsДляУдаленияСотрудника.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                                Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabase);
                        Log.d(this.getClass().getName(), "Результат_ОбновлениеДанных   " + Результат_ОбновлениеДанных);
                    }
                    Log.d(this.getClass().getName(), " Результат_ОбновлениеДанных   " + Результат_ОбновлениеДанных);
            } catch (Exception e) {///////ошибки
                e.printStackTrace();
                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e.toString() + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        return Результат_ОбновлениеДанных;
    }
























    /////////TODO КОНТЕЙНЕР УДАЛЕНИЕ СОТРУДНИКА ИЗ ТАБЕЛЯ  ДАННЫХ УНИВЕРСАЛЬНЫЙ
    public Integer УдалениеТолькоШАблонЧерезКонтейнерУниверсальная(String ТаблицаОткудаУдлаяемЗапись,
                                                                   String ЧерезКакоеПолеУдлаяемФлаг,
                                                                   String UUIDДляСостыковПриОбновления) throws ExecutionException,
            InterruptedException, TimeoutException {
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////ПОПЫТКА ПОДКЛЮЧЧЕНИЕ К ИНТРЕНТУ

        Integer Результат_УдалениеТолькоШАблон = 0;
        ////////
        Integer Результат_ПриписиИзменнийВерсииДанных = 0;

        System.out.println(" УдалениеДанныхЧерезКонтейнерУниверсальная ");

        ///todo начинаем транзакцию

            try {
                ///
                /////
                System.out.println(" УдалениеДанныхЧерезКонтейнерУниверсальная ");

                // TODO: 03.09.2021  получение ПО НОВОМУ ДВИЖКУ
                Class_GRUD_SQL_Operations  classGrudSqlOperationsДляУдаленияСотрудника;
                // TODO: 30.08.2021    КОД ОБНОВЛЕНИЕ   ДАННЫХ   ЧЕРЕЗ
                //////

                    // TODO: 03.09.2021  получение ПО НОВОМУ ДВИЖКУ
                    classGrudSqlOperationsДляУдаленияСотрудника=new Class_GRUD_SQL_Operations(context);


                    ///todo ПРИ УДАЛЕНИ И СОТРУДНИКА ОЧИЩАЕМ ПОЛЯ ЧТОБЫ НЕБЛОЫ СВЯКЗКИ С ТАБЕЛЕМ

                    // TODO: 06.09.2021  ПАРАМЕТРЫ ДЛЯ УДАЛЕНИЯ

                    classGrudSqlOperationsДляУдаленияСотрудника.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы",ТаблицаОткудаУдлаяемЗапись);
                    /////////
                    classGrudSqlOperationsДляУдаленияСотрудника.concurrentHashMapНабор.put("Флаг_ЧерезКакоеПолеОбновлением",ЧерезКакоеПолеУдлаяемФлаг);
                    /////////
                    classGrudSqlOperationsДляУдаленияСотрудника.concurrentHashMapНабор.put("ЗначениеФлагОбновления",UUIDДляСостыковПриОбновления);
                    /////////

                classGrudSqlOperationsДляУдаленияСотрудника.concurrentHashMapНабор.put("ЗнакФлагОбновления","=");
                /////////



                    // TODO: 06.09.2021  КОНТЕЙНЕР ДЛЯ УДАЛЕНИЯ
                    ContentValues АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная=new ContentValues();
                    //
                classGrudSqlOperationsДляУдаленияСотрудника.contentValuesДляSQLBuilder_Для_GRUD_Операций.put("status_send", "Удаленная");///ПОКА НЕ ОТКЛЮЧИЛИ
                    //////
                    ////TODO ДАТА
                    String СгенерированованныйДатаВремениСейчаcДляУдаления=     new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();

                    АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная.put("date_update", СгенерированованныйДатаВремениСейчаcДляУдаления);///ПОКА НЕ ОТКЛЮЧИЛИ
                    ///
               // АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная.putNull("id");///ПОКА НЕ ОТКЛЮЧИЛИ


                // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
                Long РезультатУвеличинаяВерсияДАныхЧата = new VersionCurentTable(context).upVersionCurentTable(ТаблицаОткудаУдлаяемЗапись);
                Log.d(this.getClass().getName(), " РезультатУвеличинаяВерсияДАныхЧата  " + РезультатУвеличинаяВерсияДАныхЧата);

                //TODO  конец курант ча
                АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная.put("current_table", РезультатУвеличинаяВерсияДАныхЧата);

                ///

                    classGrudSqlOperationsДляУдаленияСотрудника.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная);


                    Log.d(this.getClass().getName(), "UUIDДляСостыковПриОбновления   " +UUIDДляСостыковПриОбновления  + " РезультатУвеличинаяВерсияДАныхЧата " +РезультатУвеличинаяВерсияДАныхЧата );


                    if (Long.parseLong(UUIDДляСостыковПриОбновления) > 0) {


                        // TODO: 06.09.2021 сама операция обновления через новый движок  удаление пустого табеля
                        // TODO: 12.10.2021  Ссылка Менеджер Потоков



                        ///TODO РЕЗУЛЬТА изменения версии данных
                        Результат_УдалениеТолькоШАблон= (Integer)   classGrudSqlOperationsДляУдаленияСотрудника.
                                new UpdateData(context).updatedata(classGrudSqlOperationsДляУдаленияСотрудника.concurrentHashMapНабор,
                                classGrudSqlOperationsДляУдаленияСотрудника.contentValuesДляSQLBuilder_Для_GRUD_Операций ,
                                Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabase);


                        Log.d(this.getClass().getName(), "Результат_УдалениеТолькоШАблон   " + Результат_УдалениеТолькоШАблон);
//////////////////////////////////////////////////////////////////////

                        if(Результат_УдалениеТолькоШАблон==null){

                            //
                            Результат_УдалениеТолькоШАблон=0;
                        }



             /*                  ПриписиИзменнийВерсииДанных=new    ();
                           ПриписиИзменнийВерсииДанных.setTables(ТаблицаОткудаУдлаяемЗапись);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            Результат_ОбновлениеДанных  =             ПриписиИзменнийВерсииДанных.
                                    update(ССылкаНаСозданнуюБазу,АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная,
                                            ЧерезКакоеПолеУдлаяемФлаг + "= ?", new String[]{String.valueOf(UUIDДляСостыковПриОбновления)});
                        }
*/

                    }
                    Log.d(this.getClass().getName(), " Результат_УдалениеТолькоШАблон   " + Результат_УдалениеТолькоШАблон);
                } catch (Exception e) {///////ошибки
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e.toString() + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        return Результат_УдалениеТолькоШАблон;
    }






///todo записываем выбраную  ОРГАНИЗАЦИЮ В БАЗУ

    @SuppressLint("SuspiciousIndentation")
    public Integer МетодКоторыйЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobile(String ПередаваемыйРежимИнтрентета,
                                                                               Context КонтекстWIFI,
                                                                               String Таблица,
                                                                               String Поля) {
/////todo КОД ЗАПОЛЕНЕИЯ ДАННЫМИ В СПИНЕР ЦФО ДЕПАРТАМЕНТ МЕСЯЦ
        Integer РезультатОбновлениеЧерезКонтрейнер = 0;
        Class_GRUD_SQL_Operations class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil;
        long PublicId=0l;
                try {
                    Log.d(this.getClass().getName(), " ПередаваемыйРежимИнтрентета  " + ПередаваемыйРежимИнтрентета);
                    ////TODO ОБНУЛЯЕМ КАКУЮ ОРГАНИЗАЦИЮ ВЫБРАЛИ ОЧИЩАЕМ ВСТАВЛЕМ ППАРАМЕТР WIF-FI
                    class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil=new Class_GRUD_SQL_Operations(context);

                    // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
                    Class_GRUD_SQL_Operations class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ= new Class_GRUD_SQL_Operations(context);
                    class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.concurrentHashMapНабор.put("СамFreeSQLКОд",
                            " SELECT id  FROM successlogin  ORDER BY date_update DESC ;");
                    // TODO: 12.10.2021  Ссылка Менеджер Потоков
                    PUBLIC_CONTENT  Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT (context);
                    SQLiteCursor            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО=
                            (SQLiteCursor) class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.
                            new GetаFreeData(context).getfreedata(class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.concurrentHashMapНабор,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabase);
                    if(Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount()>0){
                        Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();
                      PublicId =         Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getInt(0);
                        Log.d(this.getClass().getName(), " PublicId  " + PublicId);
                    }
                    // TODO: 06.09.2021 ВТОРОЕ ДЕЙСТИВЕ ПОСЛЕ ОЧИСТКИ ВСТАВЛЯЕМ  ПОЛУЧЕНЫЙ СТАТУС   ДЛЯ WIFI MOBILE
                    class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil=new Class_GRUD_SQL_Operations(context);
                    // TODO: 06.09.2021  ПАРАМЕТРЫ ДЛЯ ПЕРВОГО ДЕЙСТИЯ ОЧИЩЕНИЯ
                    class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы",Таблица);
                    class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil.concurrentHashMapНабор.put("Флаг_ЧерезКакоеПолеОбновлением","id");
                    class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil.concurrentHashMapНабор.put("ЗначениеФлагОбновления", PublicId);
                    class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil.
                            concurrentHashMapНабор.put("ЗнакФлагОбновления","="); //или =   или <   >
                    // TODO: 06.09.2021  КОНТЕРЙНЕР ДЛЯ ПЕРВОГО ДЕЙСТИЯ ОЧИЩЕНИЯ
                    ContentValues ВставляемВБАзуВыбранныйРежимИнтренета=new ContentValues();
            ВставляемВБАзуВыбранныйРежимИнтренета = new ContentValues();
                    ВставляемВБАзуВыбранныйРежимИнтренета.put("id", PublicId);
                    ВставляемВБАзуВыбранныйРежимИнтренета.put(Поля, ПередаваемыйРежимИнтрентета);
                    ////TODO ДАТА
                    String СгенерированованныйДатаДляДаннойОперации=     new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();
                    ВставляемВБАзуВыбранныйРежимИнтренета.put("date_update", СгенерированованныйДатаДляДаннойОперации);
                    class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil.
                            contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(ВставляемВБАзуВыбранныйРежимИнтренета);
                    // TODO: 06.09.2021  САМА ОПАРАЦИИЯ ОБНОВЛЕНИЯ СТАТУ WIFI ИЛИ MOBILE
                    // TODO: 12.10.2021  Ссылка Менеджер Потоков
                    ///TODO РЕЗУЛЬТАТ ОБНОВЛЕНИЕ ДАННЫХ
                    РезультатОбновлениеЧерезКонтрейнер= (Integer)  class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil.
                            new UpdateData(context).updatedata(class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil.concurrentHashMapНабор,
                            class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil.contentValuesДляSQLBuilder_Для_GRUD_Операций ,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabase);
                    Log.d(this.getClass().getName(), "РезультатОбновлениеЧерезКонтрейнер   " + РезультатОбновлениеЧерезКонтрейнер);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
        return РезультатОбновлениеЧерезКонтрейнер;
    }
























    ////TODO КОТОТРЫЙ УЗНАЕТ ИЗ БАЗЫ КАКОЙ РЕЖИМ РАБОТЫ ИНТРЕНТА WIFI AND MOBILE
    public String МетодПолучениеИмяСистемыДляСменыПользователя(Context КонтекстДляРежимаИнтрента) {
        //
        String ИмяУспешноВошедегоПользователья = new String();
        ///

        SQLiteCursor Курсор_ПолучениеИмяСистемы = null;
        //
        Class_GRUD_SQL_Operations class_grud_sql_operationsПолучениеИмяСистемы;
        ////
        try {

            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

            ///
            class_grud_sql_operationsПолучениеИмяСистемы=new Class_GRUD_SQL_Operations(context);

            ///
            class_grud_sql_operationsПолучениеИмяСистемы.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","SuccessLogin");
            ///////
            class_grud_sql_operationsПолучениеИмяСистемы.concurrentHashMapНабор.put("СтолбцыОбработки","success_users");
            //
            /*        class_grud_sql_operations. concurrentHashMapНабор.put("ФорматПосика","uuid=?    AND status_send !=? AND month_tabels=? AND  year_tabels =? AND fio IS NOT NULL ");
                    ///"_id > ?   AND _id< ?"
                    //////
                    class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска1",finalПолученныйUUID);
                    ///
                    class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска2","Удаленная");
                    ///
                    class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);
                    //
                    class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 .......
*/
            ////TODO другие поля

            ///classGrudSqlOperations. concurrentHashMapНабор.put("ПоляГрупировки",null);
            ////
            //class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеГрупировки",null);
            ////
            class_grud_sql_operationsПолучениеИмяСистемы.concurrentHashMapНабор.put("УсловиеСортировки","date_update");
            ////
           /// class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеЛимита","1");
            ////
            // TODO: 12.10.2021  Ссылка Менеджер Потоков


            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ

            Курсор_ПолучениеИмяСистемы= (SQLiteCursor)  class_grud_sql_operationsПолучениеИмяСистемы.
                    new GetData(context).getdata(class_grud_sql_operationsПолучениеИмяСистемы.concurrentHashMapНабор,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabase);

            Log.d(this.getClass().getName(), "GetData "  +Курсор_ПолучениеИмяСистемы);


/*

            Cursor Курсор_ЗагружаетДанныеПриСозданииТабеля = new Class_MODEL_synchronized(contextСозданиеБАзы).КурсорУниверсальныйДляБазыДанных("SuccessLogin", new String[]
                            {"success_users"}, null,
                    null, null, null, "date_update", null);///"SELECT name  FROM MODIFITATION_Client WHERE name=?",НазваниеТаблицНаСервере
*/

            //////todo ПОЛУЧЕНИЕ ДАННЫХ
            if (Курсор_ПолучениеИмяСистемы.getCount() > 0) {
                //////
                Курсор_ПолучениеИмяСистемы.moveToFirst();

                //
                ИмяУспешноВошедегоПользователья = Курсор_ПолучениеИмяСистемы.getString(0);
                /////
                Log.d(this.getClass().getName(), "ИмяУспешноВошедегоПользователья  " + ИмяУспешноВошедегоПользователья);

            }

            ///поймать ошибку
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ///////
        }
      return  ИмяУспешноВошедегоПользователья;

    }





















    //////todo метод ДЛЯ ТАБЕЛЯ  ЗАГРУЖАЕТ СОТРУДНИКОВ В КОНТЕРТНЫЙ ТАБЕЛЬ
    Cursor МетодЗагружаетСотрудниковListViewТабеля(int IDЧьиДанныеДляСотрудников, Long полученнаяUUIDНазванияОрганизации, String finalУниверсальноеИмяТабеля, Context контекстLIstView,
                                                   int МЕсяцДляКурсораТабелей, int ГодДляКурсораТабелей, String ЦифровоеИмяНовгоТабеля) {
        ////


        SQLiteCursor Курсор_ДляЗагрузкиСотрудниковНепостредственнов = null;

         Class_GRUD_SQL_Operations class_grud_sql_operationsСотрудниковListViewТабел;

        try {
            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

            class_grud_sql_operationsСотрудниковListViewТабел=new Class_GRUD_SQL_Operations(контекстLIstView);

            ///
            class_grud_sql_operationsСотрудниковListViewТабел.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","viewtabel");
            ///////
            class_grud_sql_operationsСотрудниковListViewТабел.concurrentHashMapНабор.put("СтолбцыОбработки","name,uuid,BirthDate,snils,_id,status_carried_out");
            //
            ///////
            class_grud_sql_operationsСотрудниковListViewТабел.concurrentHashMapНабор.put("ФорматПосика","user_update= ?  AND  month_tabels=?  AND year_tabels=?" +
                    " AND nametabel=? AND organizations=? AND status_send!=?  AND nametabel_typename=? AND name IS NOT NULL");
            ////
            // TODO: 06.09.2021  значнеия для where

            class_grud_sql_operationsСотрудниковListViewТабел.concurrentHashMapНабор.put("УсловиеПоиска1",IDЧьиДанныеДляСотрудников);

            class_grud_sql_operationsСотрудниковListViewТабел.concurrentHashMapНабор.put("УсловиеПоиска2",МЕсяцДляКурсораТабелей);

            class_grud_sql_operationsСотрудниковListViewТабел.concurrentHashMapНабор.put("УсловиеПоиска3",ГодДляКурсораТабелей);

            class_grud_sql_operationsСотрудниковListViewТабел.concurrentHashMapНабор.put("УсловиеПоиска4", finalУниверсальноеИмяТабеля.trim());

            class_grud_sql_operationsСотрудниковListViewТабел.concurrentHashMapНабор.put("УсловиеПоиска5",полученнаяUUIDНазванияОрганизации);

            class_grud_sql_operationsСотрудниковListViewТабел.concurrentHashMapНабор.put("УсловиеПоиска6","Удаленная");

            class_grud_sql_operationsСотрудниковListViewТабел.concurrentHashMapНабор.put("УсловиеПоиска7",ЦифровоеИмяНовгоТабеля);

            // TODO: 06.09.2021 УСЛОВИЕ ДЛЯ СОРТИРОВКИ

            class_grud_sql_operationsСотрудниковListViewТабел.concurrentHashMapНабор.put("УсловиеСортировки","date_update DESC");

            ////
            // TODO: 12.10.2021  Ссылка Менеджер Потоков

            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ


            Курсор_ДляЗагрузкиСотрудниковНепостредственнов= (SQLiteCursor)  class_grud_sql_operationsСотрудниковListViewТабел.
                    new GetData(контекстLIstView).getdata(class_grud_sql_operationsСотрудниковListViewТабел.concurrentHashMapНабор,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabase);

            Log.d(this.getClass().getName(), "GetData "  );

            ////


/*
            Курсор_ДляЗагрузкиСотрудниковНепостредственнов = new Class_MODEL_synchronized(контекстLIstView).КурсорУниверсальныйДляБазыДанных("viewtabel",
                    new String[]{"name,uuid,BirthDate,snils,_id,status_carried_out"},//     new String[]{"name,id,uuid,BirthDate,snils},
                    " user_update= ?  AND  month_tabels=?  AND year_tabels=? AND nametabel=? AND organizations=? AND status_send!=?  AND nametabel_typename=? AND name IS NOT NULL",//AND status_send IS NULL//"Удаленная" //AND status_send!=?" /AND status_send IS NULL AND  name IS NOT NULL AND fio IS NOT NULL
                    new String[]{String.valueOf(IDЧьиДанныеДляСотрудников), String.valueOf(МЕсяцДляКурсораТабелей), String.valueOf(ГодДляКурсораТабелей),
                            finalУниверсальноеИмяТабеля.trim(), String.valueOf(полученнаяUUIDНазванияОрганизации), "Удаленная", String.valueOf(ЦифровоеИмяНовгоТабеля)}, null, null, "date_update DESC", null);



*/




            //////todo полученный
            if (Курсор_ДляЗагрузкиСотрудниковНепостредственнов.getCount() > 0) {
                ////////
                Курсор_ДляЗагрузкиСотрудниковНепостредственнов.moveToFirst();




                Log.i(this.getClass().getName(), " Курсор_ДляЗагрузкиСотрудниковНепостредственновListView.getCount() " + Курсор_ДляЗагрузкиСотрудниковНепостредственнов.getCount());
            }

            /////////
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        // new String[] { filter+"%" }, // new String[] {"%"+ filter+ "%" }, n
        //todo КУРСОР ЧЕРЕЗ ПОИСК LIKE


        return Курсор_ДляЗагрузкиСотрудниковНепостредственнов;

    }












// TODO: 12.03.2021 Метод который получает данные при возврате из ШАБЛОНОВ

/*    //////todo метод ДЛЯ ТАБЕЛЯ  ЗАГРУЖАЕТ СОТРУДНИКОВ В КОНТЕРТНЫЙ ТАБЕЛЬ
    Cursor МетодЗагружаетСотрудниковListViewТабеляПриВозвратеИЗШаблона(Context контекстLIstView, String ЦифровоеИмяНовгоТабеля, int месяцДляПермещенияПоТабелю, int годДляПермещенияПоТабелю) {
        ////




// TODO: 07.05.2021  ГЛАВНЫЙ КУРСОР СОГРУЗКИ СОТУРДНИКОВ В ТАБЕЛЬ

        Cursor Курсор_ДляЗагрузкиСотрудниковНепостредственновИзШаблона = null;
        try {
            Курсор_ДляЗагрузкиСотрудниковНепостредственновИзШаблона = new Class_MODEL_synchronized(контекстLIstView).КурсорУниверсальныйДляБазыДанных("viewtabel",
                    new String[]{"*"},//     new String[]{"name,id,uuid,BirthDate,snils},
                    "status_send!=?  AND cfo=? AND fio !=?  AND month_tabels=? AND  year_tabels =?  AND fio IS NOT NULL AND name IS NOT NULL",//  nametabel_typename  AND nametabel IS NOT NULL",//AND status_send IS NULL//"Удаленная" //AND status_send!=?" /AND status_send IS NULL AND  name IS NOT NULL AND fio IS NOT NULL
                    new String[]{"Удаленная", String.valueOf(ЦифровоеИмяНовгоТабеля), "", String.valueOf(месяцДляПермещенияПоТабелю), String.valueOf(годДляПермещенияПоТабелю)},
                    "name", null, "name", null);

            // TODO: 07.05.2021  данный курсор с датой показывает какой сотрудника изменили такой и сверху
*//*
            Курсор_ДляЗагрузкиСотрудниковНепостредственновИзШаблона = new Class_MODEL_synchronized(контекстLIstView).КурсорУниверсальныйДляБазыДанных("viewtabel",
                    new String[]{"*"},//     new String[]{"name,id,uuid,BirthDate,snils},
                    "status_send!=?  AND nametabel_typename=? AND uuid !=? AND uuid IS NOT NULL AND name IS NOT NULL",// AND nametabel IS NOT NULL",//AND status_send IS NULL//"Удаленная" //AND status_send!=?" /AND status_send IS NULL AND  name IS NOT NULL AND fio IS NOT NULL
                    new String[]{ "Удаленная",String.valueOf(ЦифровоеИмяНовгоТабеля),""},
                    "name", null, "date_update DESC", null);*//*

            //////
            if (Курсор_ДляЗагрузкиСотрудниковНепостредственновИзШаблона.getCount() > 0) {
                Курсор_ДляЗагрузкиСотрудниковНепостредственновИзШаблона.moveToFirst();
            }

            /////////
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        // new String[] { filter+"%" }, // new String[] {"%"+ filter+ "%" }, n
        //todo КУРСОР ЧЕРЕЗ ПОИСК LIKE
        Log.i(this.getClass().getName(), " Курсор_ДляЗагрузкиСотрудниковНепостредственновИзШаблонаListView.getCount() " + Курсор_ДляЗагрузкиСотрудниковНепостредственновИзШаблона.getCount());

        return Курсор_ДляЗагрузкиСотрудниковНепостредственновИзШаблона;

    }*/

//TODO МЕТОД ЗАГРУЗНИ НОВОГО СОТРУДНИКА
    public Cursor МетодДанныеДЛяСпинераТАбеля() {
                    SQLiteCursor            КурсорДляСпинераСамиМЕсяцы = null;
                    try {
                        Class_GRUD_SQL_Operations    class_grud_sql_operationsЗначенияНовгоСотрудник=new Class_GRUD_SQL_Operations(context);
                            class_grud_sql_operationsЗначенияНовгоСотрудник.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","tabel");
                            class_grud_sql_operationsЗначенияНовгоСотрудник.concurrentHashMapНабор.put("СтолбцыОбработки","month_tabels,year_tabels,cfo,uuid");
                            class_grud_sql_operationsЗначенияНовгоСотрудник.concurrentHashMapНабор.put("ФорматПосика","status_send!=?  " +
                                    " AND month_tabels IS NOT NULL  AND year_tabels IS NOT NULL");
                            class_grud_sql_operationsЗначенияНовгоСотрудник.concurrentHashMapНабор.put("УсловиеПоиска1","Удаленная");
                            class_grud_sql_operationsЗначенияНовгоСотрудник.concurrentHashMapНабор.put("ПоляГрупировки","month_tabels,year_tabels");
                            class_grud_sql_operationsЗначенияНовгоСотрудник.concurrentHashMapНабор.put("УсловиеСортировки","year_tabels DESC " +
                                    ",month_tabels DESC " );
                            class_grud_sql_operationsЗначенияНовгоСотрудник.concurrentHashMapНабор.put("УсловиеЛимита","6");


                            КурсорДляСпинераСамиМЕсяцы= (SQLiteCursor)  class_grud_sql_operationsЗначенияНовгоСотрудник.new GetData(context).getdata(class_grud_sql_operationsЗначенияНовгоСотрудник.
                                            concurrentHashMapНабор,
                                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabase);

                            Log.d(this.getClass().getName(), "КурсорДляСпинераСамиМЕсяцы " +КурсорДляСпинераСамиМЕсяцы );

                } catch (Exception e) {
                        e.printStackTrace();
                        ///метод запись ошибок в таблицу
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
        return КурсорДляСпинераСамиМЕсяцы;
    }

















//TODO МЕТОД ЗАГРУЗНИ НОВОГО шаблона

    public Cursor МетодЗагружаетЗначенияШаблонов(int полученнаяUUIDОрганизациидДляКурсораСпинераДаты, Context КонтекстДЛяСотрудника) {
     /*   Cursor asyncTaskLoader= (Cursor) new AsyncTaskLoader(КонтекстДЛяСотрудника) {
            @Override
            public Object loadInBackground() {*/

        SQLiteCursor Курсор_ЗагружаетАрайдистЗначенийНовогоШаблонаВнутри = null;
        ///
        Class_GRUD_SQL_Operations class_grud_sql_operationsЗначенияШаблонов;
        try {
// TODO: 06.09.2021

            class_grud_sql_operationsЗначенияШаблонов=new Class_GRUD_SQL_Operations(context);


            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

            ///
            class_grud_sql_operationsЗначенияШаблонов.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","Templates");
            ///////
            class_grud_sql_operationsЗначенияШаблонов.concurrentHashMapНабор.put("СтолбцыОбработки","*");
            //
            class_grud_sql_operationsЗначенияШаблонов.concurrentHashMapНабор.put("ФорматПосика","user_update=?");
            ///"_id > ?   AND _id< ?"
            //////
            class_grud_sql_operationsЗначенияШаблонов.concurrentHashMapНабор.put("УсловиеПоиска1",полученнаяUUIDОрганизациидДляКурсораСпинераДаты);
            ///
             /*       concurrentHashMapНабор. concurrentHashMapНабор.put("УсловиеПоиска2","12");
                    //
                    concurrentHashMapНабор. concurrentHashMapНабор.put("УсловиеПоиска3","13");////УсловиеПоискаv4,........УсловиеПоискаv5 .......*/

            ////TODO другие поля

            //class_grud_sql_operationsЗначенияШаблонов. concurrentHashMapНабор.put("ПоляГрупировки","month_tabels,year_tabels");
            ////
            ///  concurrentHashMapНабор. concurrentHashMapНабор.put("УсловиеГрупировки","date_update DESC");
            ////
            class_grud_sql_operationsЗначенияШаблонов.concurrentHashMapНабор.put("УсловиеСортировки","date_update DESC");
            ////
            /// class_grud_sql_operationsЗначенияНовгоСотрудник. concurrentHashMapНабор.put("УсловиеЛимита","1");
            ////
            // TODO: 12.10.2021  Ссылка Менеджер Потоков


            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ


            Курсор_ЗагружаетАрайдистЗначенийНовогоШаблонаВнутри= (SQLiteCursor)  class_grud_sql_operationsЗначенияШаблонов.
                    new GetData(context).getdata(class_grud_sql_operationsЗначенияШаблонов.concurrentHashMapНабор,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabase);

            Log.d(this.getClass().getName(), "GetData " +Курсор_ЗагружаетАрайдистЗначенийНовогоШаблонаВнутри );


        /*    // TODO: 06.09.2021  old
            Курсор_ЗагружаетАрайдистЗначенийНовогоШаблонаВнутри = new Class_MODEL_synchronized(КонтекстДЛяСотрудника).КурсорУниверсальныйДляБазыДанных("Templates", new String[]
                            {"*"}, "user_update=?",
                    new String[]{String.valueOf(полученнаяUUIDОрганизациидДляКурсораСпинераДаты)},
                    null, null, "date_update DESC", null);
            ////
*/

            ///
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return Курсор_ЗагружаетАрайдистЗначенийНовогоШаблонаВнутри;
    }


    //todo загружет уже готовые созданные табеля
    public SQLiteCursor МетодЗагружетУжеготовыеТабеля(Context КонтекстДляЗагружемыхТАбелей,
                                                      Long UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников,
                                                      int месяцДляПермещенияПоТабелю,
                                                      int годДляПермещенияПоТабелю) {


        //////TODO ГЛАВНЫЙ КУРСОР ДЛЯ НЕПОСРЕДТСВЕНОГО ЗАГРУЗКИ СОТРУДНИКА
        ////
        SQLiteCursor Курсор_ЗагружаемТабеляСозданныйВнутрений = null;
        //////
        Class_GRUD_SQL_Operations class_grud_sql_operationsУжеготовыеТабеля;
        try {

            class_grud_sql_operationsУжеготовыеТабеля = new Class_GRUD_SQL_Operations(context);


                /*    Курсор_ЗагружаетНазваниеТабеляНАОснованииСФО = new Class_MODEL_synchronized(КонтекстДляРежимаИнтрента).КурсорУниверсальныйДляБазыДанных("cfo", new String[]
                                    {"name"}, "id=?",
                            new String[]{String.valueOf(ТекущееСФО)}, null, null, "date_update DESC", "1");///"SELECT name  FROM MODIFITATION_Client WHERE name=?",НазваниеТаблицНаСервере
                    // TODO: 02.09.2021
*/


            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

            ///
            class_grud_sql_operationsУжеготовыеТабеля.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","viewtabel");
            ///////
            class_grud_sql_operationsУжеготовыеТабеля.concurrentHashMapНабор.put("СтолбцыОбработки","*");
            //
            class_grud_sql_operationsУжеготовыеТабеля.concurrentHashMapНабор.put("ФорматПосика"," uuid=?    " +
                    "AND status_send !=?" +
                    " AND month_tabels=?" +
                    " AND  year_tabels =? " +
                    "AND fio IS NOT NULL");
            ///"_id > ?   AND _id< ?"
            //////
            class_grud_sql_operationsУжеготовыеТабеля.concurrentHashMapНабор.put("УсловиеПоиска1",UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников);
            ///
            class_grud_sql_operationsУжеготовыеТабеля.concurrentHashMapНабор.put("УсловиеПоиска2","Удаленная");
            //
            class_grud_sql_operationsУжеготовыеТабеля.concurrentHashMapНабор.put("УсловиеПоиска3",месяцДляПермещенияПоТабелю);
            //
            class_grud_sql_operationsУжеготовыеТабеля.concurrentHashMapНабор.put("УсловиеПоиска4",годДляПермещенияПоТабелю);
            ///
             /*       concurrentHashMapНабор. concurrentHashMapНабор.put("УсловиеПоиска2","12");
                    //
                    concurrentHashMapНабор. concurrentHashMapНабор.put("УсловиеПоиска3","13");////УсловиеПоискаv4,........УсловиеПоискаv5 .......*/

            ////TODO другие поля

            /////classGrudSqlOperations. concurrentHashMapНабор.put("ПоляГрупировки",null);
            ////
            ///  concurrentHashMapНабор. concurrentHashMapНабор.put("УсловиеГрупировки","date_update DESC");
            ////
        class_grud_sql_operationsУжеготовыеТабеля.concurrentHashMapНабор.put("УсловиеСортировки","uuid");//date_update
            ////
          class_grud_sql_operationsУжеготовыеТабеля.concurrentHashMapНабор.put("УсловиеЛимита","1");
            ////

            // TODO: 12.10.2021  Ссылка Менеджер Потоков



            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ


            Курсор_ЗагружаемТабеляСозданныйВнутрений= (SQLiteCursor)  class_grud_sql_operationsУжеготовыеТабеля.
                    new GetData(context).getdata(class_grud_sql_operationsУжеготовыеТабеля.concurrentHashMapНабор,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabase);

            Log.d(this.getClass().getName(), "GetData " +Курсор_ЗагружаемТабеляСозданныйВнутрений );
            ////
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

///TODO ЗАПУСКАЕМ  ПуллПамяти
        return Курсор_ЗагружаемТабеляСозданныйВнутрений;

    }










    //todo загружет уже готовые созданные табеля
    public SQLiteCursor МетодЗагружетУжеготовыеТабеляПриСмещенииДанныхСкроллПоДАнным(Context КонтекстДляЗагружемыхТАбелей,
                                                                                     int ЦифровоеИмяНовгоТабеля,
                                                                                     int месяцДляПермещенияПоТабелю,
                                                                                     int годДляПермещенияПоТабелю) {
        /*Cursor asyncTaskLoaderЗагружаемТабеляСозданный = (Cursor) new AsyncTaskLoader(КонтекстДляЗагружемыхТАбелей) {
            @Override
            public Object loadInBackground() {*/
//////TODO ГЛАВНЫЙ КУРСОР ДЛЯ НЕПОСРЕДТСВЕНОГО ЗАГРУЗКИ СОТРУДНИКА
        ////

        SQLiteCursor Курсор_ЗагружаемТабеляСозданныйВнутрений = null;
        ///
        Class_GRUD_SQL_Operations class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю;
        try {


            class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю=new Class_GRUD_SQL_Operations(context);




            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

            ///
            class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","viewtabel");
            ///////
            class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю.concurrentHashMapНабор.put("СтолбцыОбработки","*");
            //
            class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю.concurrentHashMapНабор.put("ФорматПосика","cfo=?  " +
                    "AND status_send !=? " +
                    " AND month_tabels=?  " +
                    " AND  year_tabels=?" +
                    " AND fio IS NOT NULL");
            ///"_id > ?   AND _id< ?"
            //////
            class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю.concurrentHashMapНабор.put("УсловиеПоиска1",ЦифровоеИмяНовгоТабеля);
            ///
            class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю.concurrentHashMapНабор.put("УсловиеПоиска2","Удаленная");
            //
            class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю.concurrentHashMapНабор.put("УсловиеПоиска3",месяцДляПермещенияПоТабелю);
            //
            class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю.concurrentHashMapНабор.put("УсловиеПоиска4",годДляПермещенияПоТабелю);
            ///
             /*       concurrentHashMapНабор. concurrentHashMapНабор.put("УсловиеПоиска2","12");
                    //
                    concurrentHashMapНабор. concurrentHashMapНабор.put("УсловиеПоиска3","13");////УсловиеПоискаv4,........УсловиеПоискаv5 .......*/

            ////TODO другие поля

            /////classGrudSqlOperations. concurrentHashMapНабор.put("ПоляГрупировки",null);
            ////
            ///  concurrentHashMapНабор. concurrentHashMapНабор.put("УсловиеГрупировки","fio");
            ////
            class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю.concurrentHashMapНабор.put("УсловиеСортировки","uuid");
            ////
     ////  class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю. concurrentHashMapНабор.put("УсловиеЛимита","1");
            ////


            // TODO: 12.10.2021  Ссылка Менеджер Потоков



            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ


            Курсор_ЗагружаемТабеляСозданныйВнутрений= (SQLiteCursor)  class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю.
                    new GetData(context).getdata(class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю.concurrentHashMapНабор,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabase);

            Log.d(this.getClass().getName(), "GetData " +Курсор_ЗагружаемТабеляСозданныйВнутрений );


            ///////todo\
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

///TODO ЗАПУСКАЕМ  ПуллПамяти
        return Курсор_ЗагружаемТабеляСозданныйВнутрений;

    }














    //todo загружет уже готовые созданные табеля
    public SQLiteCursor МетодЗагружетУжеготовыеТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек(Context КонтекстДляЗагружемыхТАбелей,
                                                                                               int ЦифровоеИмяНовгоТабеля,
                                                                                               int месяцДляПермещенияПоТабелю,
                                                                                               int годДляПермещенияПоТабелю) {

        SQLiteCursor Курсор_ЗагружаемТабеляСозданный_ПервыйКурсорКоторыйСамЗагружаетьсяКогадМыЗаходимНААктивти = null;
        Class_GRUD_SQL_Operations class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти;
        try {
            class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти=new Class_GRUD_SQL_Operations(context);
            class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","viewtabel");
            class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти.concurrentHashMapНабор.put("СтолбцыОбработки","*");
            class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти.concurrentHashMapНабор.put("ФорматПосика",
                    "cfo=? " +
                    "AND status_send !=? AND" +
                    " month_tabels=? AND" +
                    "  year_tabels=? " +
                            "AND fio IS NOT NULL");
            ///"_id > ?   AND _id< ?"
            class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти.concurrentHashMapНабор.put("УсловиеПоиска1",ЦифровоеИмяНовгоТабеля);
            class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти.concurrentHashMapНабор.put("УсловиеПоиска2","Удаленная");
            class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти.concurrentHashMapНабор.put("УсловиеПоиска3",месяцДляПермещенияПоТабелю);
            class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти.concurrentHashMapНабор.put("УсловиеПоиска4",годДляПермещенияПоТабелю);
            class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти.concurrentHashMapНабор.put("УсловиеСортировки","date_update ");
            Курсор_ЗагружаемТабеляСозданный_ПервыйКурсорКоторыйСамЗагружаетьсяКогадМыЗаходимНААктивти= (SQLiteCursor)
                    class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти.
                    new GetData(context).getdata(class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти.
                                    concurrentHashMapНабор,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabase);
            Log.d(this.getClass().getName(), "GetData "+Курсор_ЗагружаемТабеляСозданный_ПервыйКурсорКоторыйСамЗагружаетьсяКогадМыЗаходимНААктивти  );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return Курсор_ЗагружаемТабеляСозданный_ПервыйКурсорКоторыйСамЗагружаетьсяКогадМыЗаходимНААктивти;
    }









































    // TODO: 09.04.2021 Метод Обновление Получение ПО с Сервера
    // TODO: 09.04.2021 Метод Обновление Получение ПО с Сервера
    public synchronized File МетодЗагрузкиОбновлениеПОсСервера(@NonNull String АдресЗагрузки,
                                                  @NonNull Context context,
                                                  @NonNull String ИмяСервера,
                                                  @NonNull Integer ИмяПорта,
                                                  @NonNull String ЗаданиеЗагрузки,
                                                  @NonNull String ИмяФайлаЗагрузки ,
                                                  @NonNull String ВозвращяемыйТип,
                                                  @NonNull SSLSocketFactory getsslSocketFactory2) {
        // TODO: 24.09.2024
         AtomicReference<File>  getFileAPKandJson=new AtomicReference<>();
        try {
            String enableSSl = preferencesJboss.getString("enablesll","http");

            String PatchDeleteJsonAnalitic="SousAvtoFile/UpdatePO";
            String СтрокаСвязиСсервером =enableSSl+"://"+ИмяСервера+":"+ИмяПорта+"/";;
            СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
            СтрокаСвязиСсервером = СтрокаСвязиСсервером + АдресЗагрузки; /////"dsu1.glassfish/update_android_dsu1/output-metadata.json";
            СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
            URL    Adress = new URL(СтрокаСвязиСсервером);


            OkHttpClient.Builder builderokhtttp=   new GetAsyncOkHttpClientBuilder(context,getsslSocketFactory2).GetAsyncOkHttpClientBuilder(enableSSl);

            OkHttpClient okHttpClientЗагрузкаНовогоПО = builderokhtttp.addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
                            Class_GRUD_SQL_Operations grudSqlOperations = new Class_GRUD_SQL_Operations(context);
                            grudSqlOperations.concurrentHashMapНабор.put("СамFreeSQLКОд",
                                    " SELECT success_users,success_login  FROM successlogin  ORDER BY date_update DESC ;");
                            // TODO: 12.10.2021  Ссылка Менеджер Потоков
                            PUBLIC_CONTENT publicContent = new PUBLIC_CONTENT(context);
                            SQLiteCursor Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО = null;
                            try {
                                Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО = (SQLiteCursor) grudSqlOperations.
                                        new GetаFreeData(context).getfreedata(grudSqlOperations.
                                                concurrentHashMapНабор,
                                        publicContent.МенеджерПотоков, sqLiteDatabase);
                            } catch (ExecutionException e) {
                                throw new RuntimeException(e);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount() > 0) {
                                Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();
                                ПубличноеЛогин = Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(0).trim();
                                ПубличноеПароль = Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(1).trim();
                            }
                            // TODO: 18.02.2025 get name Device
                            String ANDROID_ID =new ModulegetDeviceName().getDeviceName(context);
                            Log.d(this.getClass().getName(), "  PUBLIC_CONTENT.ПубличноеИмяПользовательДлСервлета  " + ПубличноеЛогин +
                                    " PUBLIC_CONTENT.ПубличноеПарольДлСервлета " + ПубличноеПароль);
                            Request originalRequest = chain.request();
                            Request.Builder builder = originalRequest.newBuilder()
                                    .header("Content-Type", ВозвращяемыйТип)
                                    .header("Accept-Encoding", "gzip,*,compress,identity,deflate,zstd,br")
                                    .header("Connection", "Keep-Alive")
                                    .header("Accept-Language", "ru-RU")
                                    .header("identifier", ПубличноеЛогин)
                                    .header("p_identifier", ПубличноеПароль)
                                    .header("task_downlonupdatepo", ЗаданиеЗагрузки)
                                    .header("id_device_androis", ANDROID_ID);
                            Request newRequest = builder.build();
                            return chain.proceed(newRequest);
                        }
                    }).connectTimeout(5, TimeUnit.SECONDS)
                    .writeTimeout(2, TimeUnit.MINUTES)
                    .readTimeout(2, TimeUnit.MINUTES)
                    .build();
            ///  MediaType JSON = MediaType.parse("application/json; charset=utf-16");
            Request requestGET = new Request.Builder().get().url(Adress).build();
            Log.d(this.getClass().getName(), "  request  " + requestGET);
            Dispatcher  dispatcherЗагрузкаПО = okHttpClientЗагрузкаНовогоПО.dispatcher();
            okHttpClientЗагрузкаНовогоПО.newCall(requestGET).enqueue(new Callback() {



                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    try{
                        Log.e(this.getClass().getName(), "  ERROR call  " + call + "  e" + e.toString());
                        Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + e.getMessage());
                        new RecordNewErros(context).recordnewerror(e.toString(), Class_MODEL_synchronized.class.getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        // TODO: 31.05.2022
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + ex + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(context).recordnewerror(ex.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    dispatcherЗагрузкаПО.executorService().shutdown();
                    //TODO закрываем п отоки
                }
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    try{
                        if (response.isSuccessful()) {
                            String  ПришедшегоПотока =    response.header("stream_size");
                            ПришедшегоПотока =     Optional.ofNullable(ПришедшегоПотока).map(String::valueOf).orElse("0");
                            Long РазмерПришедшегоПотока = Long.parseLong(ПришедшегоПотока  );
                            Integer КакаяКодировка = Integer.parseInt(   Optional.ofNullable(response.header("getcharsets")).map(String::new).orElse("0"));
                            Boolean ФлагgZIPOutputStream =Boolean.parseBoolean (  Optional.ofNullable(response.header("GZIPOutputStream")).map(String::new).orElse("false"));

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ФлагgZIPOutputStream " +ФлагgZIPOutputStream);

                            // TODO: 06.05.2023  если ПОТОК ЕСТЬ СОДЕРЖИВАЕМ ПАРСИМ
                            if(РазмерПришедшегоПотока>0){

                                // TODO: 07.04.2025  получаем STEAM  от сервера и обрабоатываем его для получение файла
                                DownloadFiles getBinessLogicDwonloadFiles=new DownloadFiles();
                                // TODO: 07.04.2025 обрабоатываем пршедщий файл
                                getFileAPKandJson.set(getBinessLogicDwonloadFiles.downloadFiles(context,
                                        new GetBinessLogicDownloadFiles(),
                                        response.body().bytes() ,ИмяФайлаЗагрузки)) ;

                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() +" getFileAPKandJson.get() " +getFileAPKandJson.get());

                                // TODO: 20.03.2023 ответ от сервреа если нет цифры значит не и файла
                            }
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }

                        // TODO: 28.12.2024 closeting
                        response.close();
                        // TODO: 06.05.2023 exit
                        dispatcherЗагрузкаПО.executorService().shutdown();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(context).recordnewerror(e.toString(),
                                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }

                }
            });
            dispatcherЗагрузкаПО.executorService().awaitTermination(1,TimeUnit.DAYS);
            // TODO: 07.04.2025  
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() +" getFileAPKandJson.get() " +getFileAPKandJson.get());
            // TODO: 13.03.2023  конец загрузки файла по новому FILE
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
            String  ОшибкаТекущегоМетода = ex.toString();
            if (!ОшибкаТекущегоМетода.toString().trim().matches("(.*)java.io.EOFException(.*)") &&
                    !ОшибкаТекущегоМетода.toString().trim().matches("(.*)java.net.sockettimeoutexception(.*)")
                    &&
                    !ОшибкаТекущегоМетода.toString().trim().matches("(.*)SocketTimeout(.*)") &&
                    !ОшибкаТекущегоМетода.toString().trim().matches("(.*)java.net.ConnectException(.*)")
                    && !ОшибкаТекущегоМетода.toString().trim().matches("(.*)FileNotFoundException(.*)"))  {
                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + ОшибкаТекущегоМетода + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + ОшибкаТекущегоМетода.toString());
                new RecordNewErros(this.context).recordnewerror(ex.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

            }
        }
        return   getFileAPKandJson.get();

    }












    ////TODO ДАННЫЙ МЕТОД ВЫЧИСЛЯЕТ НУЖНО ЛИ ЗАПОЛЯНТЬ ВЫХОДНИЕ ДНИ БУКВОЙ Б
    public ContentValues МетодВычисляемВыходныеДниПриСозданииНовогоТабеляАвтоРЕжим(@NonNull Context КонтекстДляРежимаИнтрента,
                                                                                   @NonNull Integer Месяц ,
                                                                                    @NonNull Integer Год ) {

        ContentValues РезультатВычисленияВыходныхДней = new ContentValues();

        try {


// create a Calendar for the 1st of the required month
            ///
            Log.d(КонтекстДляРежимаИнтрента.getClass().getName(), " Год  " + "--" + Год + " Месяц " + Месяц);/////



            Calendar calendar1 = Calendar.getInstance(new Locale("ru"));
            //TODO

      //   calendar.set(Calendar.DAY_OF_MONTH,calendar.);
            YearMonth yearMonthObject = YearMonth.of(Год, Месяц);

            int daysInMonth = yearMonthObject.lengthOfMonth()+1; //28

            SimpleDateFormat СозданияВычисляемВыходные=null;
            // TODO: 29.04.2022  int ИндексДней
            int ИндексДней;

            ///////TODO сам цикл который заполняет месяцами
            for ( ИндексДней=1;ИндексДней<daysInMonth;ИндексДней++) {


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    //
                    СозданияВычисляемВыходные = new SimpleDateFormat("yyyy-MM-dd", new Locale("rus"));

                } else {

                    СозданияВычисляемВыходные = new java.text.SimpleDateFormat("yyyy-MM-dd", new Locale("rus"));

                }


                Date   ДатаПосикаВыходныеДней       = СозданияВычисляемВыходные.parse (Год +"-"+Месяц+"-"+ИндексДней );

                // Then get the day of week from the Date based on specific locale.

            // TODO: 29.01.2022  отдельно только название дня
            String РезультатДатыДляКонктетногоТабеляТолькоЗанвание = new SimpleDateFormat("EEE", new Locale("ru")).format(ДатаПосикаВыходныеДней );


                if (РезультатДатыДляКонктетногоТабеляТолькоЗанвание.equalsIgnoreCase("сб")  ||
                        РезультатДатыДляКонктетногоТабеляТолькоЗанвание.equalsIgnoreCase("вс")) {
                    System.out.println("выходные дни при созадни тбалея полльзователь разрешмил авторежим" + ИндексДней);

                    String ОбьединяемДеньсЦифровдЛЯвСТАВКИ = "d" + String.valueOf(ИндексДней);

                    РезультатВычисленияВыходныхДней.put(ОбьединяемДеньсЦифровдЛЯвСТАВКИ, "В");
                }

            }
// stop when we reach the launch of the next month


            Log.d(this.getClass().getName(), "   РезультатВычисленияВыходныхДней  " + РезультатВычисленияВыходныхДней.valueSet() + " daysInMonth " + daysInMonth);


            ///поймать ошибку
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ///////
        }

        return РезультатВычисленияВыходныхДней;
    }













    ////TODO КОТОТРЫЙ УЗНАЕТ ИЗ БАЗЫ КАКОЙ РЕЖИМ РАБОТЫ ИНТРЕНТА WIFI AND MOBILE
    public String МетодПолучениеНазваниеТабеляНаОснованииСФО(Context КонтекстДляРежимаИнтрента, Integer ТекущееСФО) throws InterruptedException {
        //
                String ПолученоеНазваниеТабеляНаОснованииСФО = null;
                SQLiteCursor Курсор_ЗагружаетНазваниеТабеляНАОснованииСФО = null;
                Class_GRUD_SQL_Operations concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийпОЛУЧЕНИЯнАЗВАНИЕСФО;
                try {
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийпОЛУЧЕНИЯнАЗВАНИЕСФО=new Class_GRUD_SQL_Operations(КонтекстДляРежимаИнтрента);
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийпОЛУЧЕНИЯнАЗВАНИЕСФО.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","cfo");
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийпОЛУЧЕНИЯнАЗВАНИЕСФО.concurrentHashMapНабор.put("СтолбцыОбработки","name");
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийпОЛУЧЕНИЯнАЗВАНИЕСФО.concurrentHashMapНабор.put("ФорматПосика","_id = ?");
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийпОЛУЧЕНИЯнАЗВАНИЕСФО.concurrentHashMapНабор.put("УсловиеПоиска1",ТекущееСФО);
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийпОЛУЧЕНИЯнАЗВАНИЕСФО.concurrentHashMapНабор.put("УсловиеЛимита","1");
                    Курсор_ЗагружаетНазваниеТабеляНАОснованииСФО= (SQLiteCursor)  concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийпОЛУЧЕНИЯнАЗВАНИЕСФО.
                            new GetData(КонтекстДляРежимаИнтрента).getdata(concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийпОЛУЧЕНИЯнАЗВАНИЕСФО.concurrentHashMapНабор,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabase);
                    Log.d(this.getClass().getName(), "GetData "  +Курсор_ЗагружаетНазваниеТабеляНАОснованииСФО);
                    if (Курсор_ЗагружаетНазваниеТабеляНАОснованииСФО.getCount() > 0) {
                        Курсор_ЗагружаетНазваниеТабеляНАОснованииСФО.moveToFirst();
                        ПолученоеНазваниеТабеляНаОснованииСФО = Курсор_ЗагружаетНазваниеТабеляНАОснованииСФО.getString(0).trim();
                        Log.d(this.getClass().getName(), " ПолученоеНазваниеТабеляНаОснованииСФО  " + ПолученоеНазваниеТабеляНаОснованииСФО);
                    }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ПолученоеНазваниеТабеляНаОснованииСФО;
    }




















    // TODO: 15.06.2021 метод вычислчет дни недели  в потоке для отправки и принятии d1,d2,d3

    boolean МетодКоторыйВычисляетЕслиДНИвПотоке(String ПараметрИмяТаблицыОтАндройдаPostВнутриДляПоиска, JSONObject БуферСтолбикиДляВставкиВнутриВнутриДляПосика) {


        boolean ЕслиТакойДень = false;
        try{
        ////
        StringBuffer БУферИзJSONВБУФЕРАНАЛИЗА = new StringBuffer(БуферСтолбикиДляВставкиВнутриВнутриДляПосика.toString());

        ArrayList<String> АрайЛистДниТабеля = new ArrayList<String>(Arrays.asList("d1", "d2", "d3", "d4", "d5", "d6", "d7", "d8", "d9", "d10", "d11", "d12", "d13", "d14"
                , "d15", "d16", "d17", "d18", "d19", "d20", "d21", "d22", "d23", "d24", "d25", "d26", "d27", "d28", "d29", "d30", "d31"));


            //////////
            String ЗначенияИБуфераДляПосикаДней = БУферИзJSONВБУФЕРАНАЛИЗА.toString();


            // TODO: 15.06.2021

            for (String КлючИзАрайЛиста : АрайЛистДниТабеля) {

                System.out.println(КлючИзАрайЛиста);

                //
                ЕслиТакойДень = ЗначенияИБуфераДляПосикаДней.contains(КлючИзАрайЛиста);
                ///TODO ЕСЛИ СТРАБОТАЛО ТО ВЫХОДИМ ИЗ ЦИКЛА
                if (ЕслиТакойДень == true) {

                    System.out.println("   ЕслиТакойДень " + ЕслиТакойДень);
                    /////////////
                    break;

                }


            }


            ///
    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        // TODO: 01.09.2021 метод вызова
        new RecordNewErros(context.getApplicationContext()).recordnewerror(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return ЕслиТакойДень;
    }

// TODO: 11.08.2021  ПОЛУЧЕНИЕ ЛОКАЛЬНОЙ ВЕРИСИ ДАНННЫХ ЧАТА
    // TODO: 10.08.2021  получение УВЕЛИЧИНОЙ ВЕРСИИ ДАННЫХ ДЛЯ ЧАТА
    protected Long МетодПолученияЛокальнойВерсииДаныхЧатаДляОтправкиЕгоНАСервер(String Текущаятаблицы, String ТекущаяяКолонкаТаблицы, Context context, String ИмяТаблицыОтАндройда_Локальноая) {
        Long ЗначениеДляПовышениеВерсии = 1l;
        SQLiteCursor Курсор_КоторыйПолучаетМаксимальюнуВерсиюДанных = null;
        Class_GRUD_SQL_Operations class_grud_sql_operationsВерсииДаныхЧатаДляОтправкиЕгоНАСервер;
        try{
            class_grud_sql_operationsВерсииДаныхЧатаДляОтправкиЕгоНАСервер=new Class_GRUD_SQL_Operations(this.context);
            class_grud_sql_operationsВерсииДаныхЧатаДляОтправкиЕгоНАСервер.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы",Текущаятаблицы);
            class_grud_sql_operationsВерсииДаныхЧатаДляОтправкиЕгоНАСервер.concurrentHashMapНабор.put("СтолбцыОбработки",ТекущаяяКолонкаТаблицы);
            class_grud_sql_operationsВерсииДаныхЧатаДляОтправкиЕгоНАСервер.concurrentHashMapНабор.put("ФорматПосика","name=? ");
            class_grud_sql_operationsВерсииДаныхЧатаДляОтправкиЕгоНАСервер.concurrentHashMapНабор.put("УсловиеПоиска1",ИмяТаблицыОтАндройда_Локальноая);

            Курсор_КоторыйПолучаетМаксимальюнуВерсиюДанных= (SQLiteCursor)  class_grud_sql_operationsВерсииДаныхЧатаДляОтправкиЕгоНАСервер.
                    new GetData(this.context).getdata(class_grud_sql_operationsВерсииДаныхЧатаДляОтправкиЕгоНАСервер.concurrentHashMapНабор,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabase);

            Log.d(this.getClass().getName(), "GetData " +Курсор_КоторыйПолучаетМаксимальюнуВерсиюДанных );
            if(Курсор_КоторыйПолучаетМаксимальюнуВерсиюДанных.getCount()>0){
                Курсор_КоторыйПолучаетМаксимальюнуВерсиюДанных.moveToFirst();
                ЗначениеДляПовышениеВерсии=Курсор_КоторыйПолучаетМаксимальюнуВерсиюДанных.getLong(0);
                Log.d(this.getClass().getName(), "ЗначениеДляПовышениеВерсии "+ЗначениеДляПовышениеВерсии);
            }
            Log.d(this.getClass().getName(), "ЗначениеДляПовышениеВерсии "+ЗначениеДляПовышениеВерсии);
            Курсор_КоторыйПолучаетМаксимальюнуВерсиюДанных.close();
            Log.d(this.getClass().getName(), " сработала ...  создание таблицы Data_Chat TRIGGER"+ЗначениеДляПовышениеВерсии);
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }


        // TODO: 06.09.2021 результат 
        return  ЗначениеДляПовышениеВерсии;
    }


    public Integer МетодПосчётаЧасовПоСотруднику(Cursor курсор_ЗагружаемТабеляСозданный) {
        Integer СуммаЧасов = 0;
        try{
            if (курсор_ЗагружаемТабеляСозданный.getCount()>0) {
                for (int ИндексДляИзмененияДней = 1; ИндексДляИзмененияДней < 32; ИндексДляИзмененияДней++) {
                    int ИндексЧассыСотрудника = курсор_ЗагружаемТабеляСозданный.getColumnIndex("d" + ИндексДляИзмененияДней);
                    if (  курсор_ЗагружаемТабеляСозданный.getType(ИндексЧассыСотрудника)==Cursor.FIELD_TYPE_INTEGER) {
                        int ЧассыСотрудника = курсор_ЗагружаемТабеляСозданный.getInt(ИндексЧассыСотрудника);
                        СуммаЧасов = СуммаЧасов + ЧассыСотрудника;
                        Log.d(this.getClass().getName(), "    СуммаЧасов " + СуммаЧасов);
                    }
                }
            }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " СуммаЧасов "+СуммаЧасов );
        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return СуммаЧасов;
    }







    ///todo являеться ли весь текст числом
    public boolean МетодОпределениеВселиЦифрыВстроке(String ВселиЦифрыВтексе) {
        boolean Результат = false;
        try {
            Long.parseLong(ВселиЦифрыВтексе);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }



    ////TODO САМ МЕТОД АУНТИФИКАЦИИ С СЕРВЕРОМ
    public Integer методАвторизацииЛогинИПаполь(@NonNull Context context,
                                                     @NonNull SharedPreferences preferences,
                                                     @NonNull String ПубличноеЛогин,
                                                     @NonNull String ПубличноеПароль,
                                                @NotNull SSLSocketFactory getsslSocketFactory2) {


        final Integer[] БуферПубличныйIDОтСервера = {0};
        try {
            String enableSSl = preferencesJboss.getString("enablesll","http");

            LinkedHashMap<Integer,String> getHiltPortJboss=   EntryPoints.get(context, getHiltPortJbossInterface.class).getHiltPortJboss();

            // TODO: 02.04.2024  Адресс и Порт Сервера Jboss
            String   ИмяСерверИзХранилица = getHiltPortJboss.values().stream().map(m->String.valueOf(m)).findFirst().get();
            Integer    ПортСерверИзХранилица = getHiltPortJboss.keySet().stream().mapToInt(m->m).findFirst().getAsInt();



            String ИмменоКакойСерверПодкючения =enableSSl+"://"+ИмяСерверИзХранилица+":"+ПортСерверИзХранилица+"/";
          String  СтрокаСвязиСсервером = ИмменоКакойСерверПодкючения +new PUBLIC_CONTENT(context).getСсылкаНаРежимСервераТабель()+ "?"
                     + "JobForServer=" + "Хотим Получить ID для Генерации  UUID" + ""+
                  "&" + "IdUser=" + ПубличноеЛогин + "";
            СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
            Log.d(this.getClass().getName(), " СтрокаСвязиСсервером " +СтрокаСвязиСсервером);
            URL Adress = new URL(СтрокаСвязиСсервером); //
            Log.d(this.getClass().getName(),  "СтрокаСвязиСсервером "+СтрокаСвязиСсервером+
                    "ПубличноеПароль "+ПубличноеПароль+
                    "СтрокаСвязиСсервером "+СтрокаСвязиСсервером
                    + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                    " Класс  :" + Thread.currentThread().getStackTrace()[2].getClassName());

            // TODO: 11.03.2023  текст код
            if (ПубличноеЛогин.length()>0 && ПубличноеПароль.length()>0 && СтрокаСвязиСсервером.length()>0) {

                OkHttpClient.Builder builderokhtttp=   new GetAsyncOkHttpClientBuilder(context,getsslSocketFactory2).GetAsyncOkHttpClientBuilder(enableSSl);
                // TODO: 05.03.2025
                OkHttpClient okHttpClientИмяиПароль =builderokhtttp.addInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                // TODO: 18.02.2025 get name Device
                                String ANDROID_ID =new ModulegetDeviceName().getDeviceName(context);
                                // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
                                Log.d(this.getClass().getName(), "  ПубличноеЛогин " + ПубличноеЛогин + " ПубличноеПароль " + ПубличноеПароль);
                                Request originalRequest = chain.request();
                                Request.Builder builder = originalRequest.newBuilder()
                                        .header("Content-Type", "application/octet-stream ;charset=UTF-8")
                                        .header("Accept-Encoding", "gzip,*,compress,identity,deflate,zstd,br")
                                        .header("Connection", "Keep-Alive")
                                        .header("Accept-Language", "ru-RU")
                                        .header("identifier", ПубличноеЛогин)
                                        .header("p_identifier", ПубличноеПароль)
                                        .header("id_device_androis", ANDROID_ID);
                                Request newRequest = builder.build();
                                return chain.proceed(newRequest);
                            }
                        }).connectTimeout(5, TimeUnit.SECONDS)
                        .writeTimeout(2, TimeUnit.MINUTES)
                        .readTimeout(2, TimeUnit.MINUTES)
                        .build();
                ///  MediaType JSON = MediaType.parse("application/json; charset=utf-16");
                Request requestGET = new Request.Builder().get().url(Adress).build();
                Log.d(this.getClass().getName(), "  request  " + requestGET);
                // TODO  Call callGET = client.newCall(requestGET);
                Dispatcher dispatcherПроверкаЛогиниПароль = okHttpClientИмяиПароль.dispatcher();
                okHttpClientИмяиПароль.newCall(requestGET).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        Log.e(this.getClass().getName(), "  ERROR call  " + call + "  e" + e.toString());
                        БуферПубличныйIDОтСервера[0]=0;
                        Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + e.getMessage());
                        new RecordNewErros(context).recordnewerror(e.toString(), Class_MODEL_synchronized.class.getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        // TODO: 31.05.2022
                        dispatcherПроверкаЛогиниПароль.executorService().shutdown();

                        //TODO закрываем п отоки
                    }
                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        try{
                            if (response.isSuccessful()) {

                            String  ПришедшегоПотока =    response.header("stream_size");
                                ПришедшегоПотока =     Optional.ofNullable(ПришедшегоПотока).map(String::valueOf).orElse("0");
                                Long РазмерПришедшегоПотока = Long.parseLong(ПришедшегоПотока  );

                                Integer КакаяКодировка = Integer.parseInt(   Optional.ofNullable(response.header("getcharsets")).map(String::new).orElse("0"));
                                Boolean ФлагgZIPOutputStream =Boolean.parseBoolean (  Optional.ofNullable(response.header("GZIPOutputStream")).map(String::new).orElse("false"));
                                if (РазмерПришедшегоПотока>0) {
                                    // TODO: 07.10.2023  gzip
                                    // TODO: 07.04.2025  получаем STEAM  от сервера и обрабоатываем его для READER
                                    DownloadReader downloadReader=new DownloadReader();
                                    // TODO: 07.04.2025 обрабоатываем пршедщий файл
                                    // TODO: 07.04.2025 обрабоатываем пршедщий файл
                                    StringBuffer   БуферПубличныйID=downloadReader.downloadReader(context, new GetBinessLogicDownloadReader(),
                                            response.body().bytes()) ;
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " БуферПубличныйID " +БуферПубличныйID
                                            +  " РазмерПришедшегоПотока " + РазмерПришедшегоПотока);
                                    // TODO: 31.05.2022
                                    String  БуферПубличный = БуферПубличныйID.toString().trim();

                                        if(     БуферПубличный.trim().matches("(.*)Server Running...... Don't Login(.*)")==true){
                                            БуферПубличный="-1";
                                            БуферПубличныйIDОтСервера[0] =Integer.parseInt(БуферПубличный);
                                        }else {
                                            БуферПубличный=   БуферПубличный .replaceAll("[^0-9]", "")
                                                    .replaceAll("]","")
                                                    .replaceAll("","");

                                            boolean isNumeric = БуферПубличный.chars().allMatch( Character::isDigit );
                                            if (isNumeric) {
                                                БуферПубличныйIDОтСервера[0] =Integer.parseInt(БуферПубличный);
                                            }

                                        }
                                    Log.d(this.getClass().getName(), "БуферПубличныйIDОтСервера "
                                            + БуферПубличныйIDОтСервера[0] +  " РазмерПришедшегоПотока " +РазмерПришедшегоПотока +
                                            " БуферПубличный " +БуферПубличный);
                                    // TODO: 31.05.2022
                                }

                            }
                            // TODO: 28.12.2024 closeting
                            response.close();
                            // TODO: 28.12.2024
                            dispatcherПроверкаЛогиниПароль.executorService().shutdown();
                            Log.i(this.getClass().getName(),  " java.security.cert.X509Certificate  "+
                                    Thread.currentThread().getStackTrace()[2].getMethodName()+
                                    " время " +new Date().toLocaleString() );
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                });
                dispatcherПроверкаЛогиниПароль.executorService().awaitTermination(1,TimeUnit.DAYS);
                dispatcherПроверкаЛогиниПароль.cancelAll();
            }
            Log.i(this.getClass().getName(),  " java.security.cert.X509Certificate  "+
                    Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " время " +new Date().toLocaleString()  );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return БуферПубличныйIDОтСервера[0];
    }
}















