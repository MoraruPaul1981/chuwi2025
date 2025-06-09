package com.dsy.dsu.Hilt.PublicId;


import android.content.Context;
import android.util.Log;

import com.dsy.dsu.BusinessLogicPublic.GetPublicID.GetttingPublicID;
import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;




@Module
@InstallIn(SingletonComponent.class)
public class ModulePublicId {


    @QualifierPublicId
    @Provides
    public Integer getHiltPublicId(@ApplicationContext Context context) {
        Integer ПубличныйID = 0;
        try {
            ПубличныйID = new GetttingPublicID().getttingPublicID(context);
            // TODO: 29.01.2024
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ПубличныйID " + ПубличныйID);


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ПубличныйID;


    }
}
