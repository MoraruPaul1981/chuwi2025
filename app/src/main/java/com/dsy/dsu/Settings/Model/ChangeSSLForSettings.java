package com.dsy.dsu.Settings.Model;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;

import com.dsy.dsu.BusinessLogicAll.GetPublicID.GetPublicID;
import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;

import java.util.Date;

public class ChangeSSLForSettings {

    Switch  switchsslcomunications;

    Context context;

    public ChangeSSLForSettings(Switch switchsslcomunications, Context context) {
        this.switchsslcomunications = switchsslcomunications;
        this.context = context;
    }


    public  void changeSwitcSllSimple(){
  try {
      String getMode_ssl=new String();
      //SELECT

      getMode_ssl=new com.dsy.dsu.Settings.Model.Model.SLLBenessLogicMode(context).getModeSLL();

      Log.d(context.getClass().getName(), "\n"
              + " время: " + new Date()+"\n+" +
              " Класс в процессе... " +  this.getClass().getName()+"\n"+
              " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " getMode_ssl " +getMode_ssl);

          if(getMode_ssl.equalsIgnoreCase("http")) {
              switchsslcomunications.setChecked(false);
              switchsslcomunications.setText("Обычный (Сервер)");
             }else {
              // TODO: 09.10.2024
              if(getMode_ssl.equalsIgnoreCase("https")) {
                  switchsslcomunications.setChecked(true);
                  switchsslcomunications.setText("Защищенный (Сервер)");
              }
            }
      // TODO: 09.10.2024
      switchsslcomunications.refreshDrawableState();
      switchsslcomunications.requestLayout();
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+" getMode_ssl "+getMode_ssl);

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

    }



    public  void changeSwitcSllSimpleLister(){
        try {

            switchsslcomunications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                   if (switchsslcomunications.isChecked()) {

                       recordingShiftdSSLconnectionMode("https");

                       Log.d(context.getClass().getName(), "\n"
                               + " время: " + new Date()+"\n+" +
                               " Класс в процессе... " +  this.getClass().getName()+"\n"+
                               " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                    }else {

                       recordingShiftdSSLconnectionMode("http");

                       Log.d(context.getClass().getName(), "\n"
                               + " время: " + new Date()+"\n+" +
                               " Класс в процессе... " +  this.getClass().getName()+"\n"+
                               " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                   }



                    // TODO: 08.10.2024 после выполения   переопределяем внешний ВИД Активити
                    changeSwitcSllSimple();

                    Log.d(context.getClass().getName(), "\n"
                            + " время: " + new Date()+"\n+" +
                            " Класс в процессе... " +  this.getClass().getName()+"\n"+
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                }
            });


            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }







      Integer recordingShiftdSSLconnectionMode(@NonNull String getchangeMode){
          // TODO: 09.10.2024
          Integer recordingShiftdSSLconnectionMode=0;
          try{

              String ИмяТаблицы= "successlogin";
              ContentValues contentValuesChangeModeSLL=new ContentValues();
              Uri uri = Uri.parse("content://com.dsy.dsu.providerforsystemtables/successlogin");
              // TODO: 08.10.2024 Дополнительное добавление данных
              ContentResolver contentProviderNewPubicID = context.getContentResolver();
              contentValuesChangeModeSLL.put("mode_ssl",getchangeMode);
              contentValuesChangeModeSLL.put("currenttaskforthecontentprovider","mode_ssl");

              // TODO: 08.10.2024 Находим если такой  Пользователь
              Long getuuidLocal=  new GetPublicID( ).gettingSettingTableVersion(context," SELECT publicid FROM "+ИмяТаблицы+"  ",ИмяТаблицы);
              // TODO: 09.10.2024
              contentValuesChangeModeSLL.put("publicid",getuuidLocal);
              // TODO: 12.04.2023 UPDATER PUBLIC ID
              if(getuuidLocal>0 ){

                  // TODO: 12.04.2023 UPDATER model_ssl
                  recordingShiftdSSLconnectionMode=  contentProviderNewPubicID.update(uri, contentValuesChangeModeSLL,null,null);

                  Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                          " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                          " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ "  recordingShiftdSSLconnectionMode " +recordingShiftdSSLconnectionMode);

                  // TODO: 08.10.2024 UNSERT PUBLIC ID
              }
              Log.d(context.getClass().getName(), "\n"
                  + " время: " + new Date()+"\n+" +
                  " Класс в процессе... " +  this.getClass().getName()+"\n"+
                  " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());

      } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return  recordingShiftdSSLconnectionMode;
      }




    // TODO: 09.10.2024


}
