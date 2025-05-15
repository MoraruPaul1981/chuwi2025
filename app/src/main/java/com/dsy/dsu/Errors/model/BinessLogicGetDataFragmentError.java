package com.dsy.dsu.Errors.model;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentManager;

import com.dsy.dsu.BusinessLogicAll.AnalysisUserAuthenticated.GetAnalysisUserAuthenticated;
import com.dsy.dsu.BusinessLogicAll.Class_Sendiing_Errors;
import com.dsy.dsu.Dashboard.Model.LaunchActivityDiaologSettings;
import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;
import com.dsy.dsu.Errors.model.interfaces.GettingExistingErrorsInterface;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.IOUtils;
import com.sous.backasync.launch.ModuleQuety;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.function.Consumer;

import io.reactivex.rxjava3.core.Flowable;

final public class BinessLogicGetDataFragmentError {

    private Context context;
    private  ModuleQuety moduleQuety;

    
    
    
    public BinessLogicGetDataFragmentError(@NonNull  Context context,
                                           @NonNull  ModuleQuety moduleQuety) {
        this.moduleQuety = moduleQuety;
        this.context = context;
        // TODO: 17.04.2023
        Log.d(this.getClass().getName(),"\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

    }


    // TODO: 07.04.2025 launch Get ERRORS
  public StringBuffer getDataFragmentError (@NonNull GettingExistingErrorsInterface gettingExistingErrorsInterface){
       StringBuffer getDataFragmentError=new StringBuffer();
       try{

       getDataFragmentError=  gettingExistingErrorsInterface.gettingExistingErrors(context,  moduleQuety);


       // TODO: 17.04.2023
       Log.d(this.getClass().getName(),"\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
               " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
               " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " getDataFragmentError " +getDataFragmentError);
   } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        // TODO: 01.09.2021 метод вызова
        new RecordNewErros(context).recordnewerror(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

      return  getDataFragmentError;
   }




    // TODO: 24.03.2025 end class
}

