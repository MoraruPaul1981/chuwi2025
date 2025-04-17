package com.dsy.dsu.BusinessLogicAll.DownloadsJBOSS.BunessLogicDownloadFiles;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;
import com.google.common.io.ByteStreams;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.zip.GZIPInputStream;

public class GetBinessLogicDownloadFiles implements  GetBinessLogicDwonloadFilesInterface {
    /**
     * @param context
     * @param
     * @return
     */
    @Override
    public File GetBinessLogicDwonloadFiles(@NotNull Context context,@NotNull byte[] getbytejboss,@NotNull String ИмяФайлаЗагрузки) {
        // TODO: 07.04.2025
        File  getNewFile=null;
  try{
      String PatchDeleteJsonAnalitic="SousAvtoFile/UpdatePO";

      File ПутькФайлу = null;
      if (Build.VERSION.SDK_INT >= 30) {
          ПутькФайлу = context.getExternalFilesDir( Environment.DIRECTORY_DOWNLOADS+ File.separator + PatchDeleteJsonAnalitic);
      } else {
          ПутькФайлу = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS+ File.separator + PatchDeleteJsonAnalitic);
      }

      if (!ПутькФайлу.isDirectory()) {
          ПутькФайлу.mkdirs();
          ПутькФайлу.getParentFile().mkdirs();
      }
      // TODO: 12.02.2025 удаление
      // TODO: 12.02.2025  второе удаление файла самого

      File СамкФайлу = null;
      if (Build.VERSION.SDK_INT >= 30) {
          СамкФайлу = context.getExternalFilesDir( Environment.DIRECTORY_DOWNLOADS+ File.separator + PatchDeleteJsonAnalitic+File.separator + ИмяФайлаЗагрузки);
      } else {
          СамкФайлу = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS+ File.separator + PatchDeleteJsonAnalitic+File.separator + ИмяФайлаЗагрузки);
      }

      if (СамкФайлу.exists()) {
          СамкФайлу.delete();
      }
      Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
              " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
              " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());



        getNewFile=new File(String.valueOf(СамкФайлу)) ;
      getNewFile.setReadable(true);
      getNewFile.setWritable(true);
      getNewFile.setExecutable(true);
      getNewFile.getParentFile().mkdirs();

      // TODO: 24.09.2024
      Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
              " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
              " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " getNewFile " +  getNewFile);

      // TODO: 20.03.2023 само создание файла
      if ( getNewFile.createNewFile()) {

          try (ByteArrayInputStream bin = new ByteArrayInputStream(getbytejboss);
               GZIPInputStream gzipper = new GZIPInputStream(bin))
          {
              // Not sure where to go here

       /*       byte[] buffer = new byte[2048];
              ByteArrayOutputStream out = new ByteArrayOutputStream();
              int len;
              while ((len = gzipper.read(buffer)) > 0) {
                  out.write(buffer, 0, len);
              }

              gzipper.close();
              out.flush();
              out.close();*/
              //out.toByteArray();
              ByteArrayOutputStream out = new ByteArrayOutputStream(2048);
             ByteStreams.copy(gzipper , out);

              gzipper.close();
              out.flush();
              out.close();

              try (FileOutputStream outputStream = new FileOutputStream(getNewFile)) {
                  outputStream.write(out.toByteArray());
              }

          }
          Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                  " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                  " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " getBinessLogicDwonloadFiles.length() " +getNewFile.length());
      } else {
          Log.e(context.getClass().getName(), "Ошибка ERRO FILE DONT NEW FILE  getNewFile" + getNewFile);
      }

      Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
              " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
              " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " getBinessLogicDwonloadFiles " +getNewFile);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

        return getNewFile;
    }
}
