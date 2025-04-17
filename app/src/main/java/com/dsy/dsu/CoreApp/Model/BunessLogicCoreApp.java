package com.dsy.dsu.CoreApp.Model;

import android.content.Context;
import android.util.Log;

import com.dsy.dsu.BootAndAsync.ViewModelBoot.Model.DowloadUpdatePO.ClassCreateFolderUpdatePO;
import com.dsy.dsu.BusinessLogicAll.CreateFolderBinatySave.ClassCreateFolderBinatyMatrilal;
import com.dsy.dsu.BusinessLogicAll.CreateFolderBinatySave.ClassCreateFolderCommitPays1C;
import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;
import com.dsy.dsu.Errors.model.creatingfileerrors.CreatingAFileForApp;
import com.dsy.dsu.Errors.model.interfaces.GetWorkerErrosInterface;

public class BunessLogicCoreApp {

    private Context context;


    public BunessLogicCoreApp(Context context) {
        this.context = context;
    }


 public      void getBunessLogicCoreApp(){
        try{
          // TODO: 14.08.2023 создаем папку для BinaryFile Save
            ClassCreateFolderBinatyMatrilal classCreateFolderBinatyMatrilal=
                    new ClassCreateFolderBinatyMatrilal(context) ;
            classCreateFolderBinatyMatrilal.МетодCreateFoldersBinaty();

          // TODO: 14.08.2023 создаем папку для BinaryFile CommitPay1C Соласования
            ClassCreateFolderCommitPays1C classCreateFolderCommitPays1C=
                    new ClassCreateFolderCommitPays1C(context) ;
            classCreateFolderCommitPays1C.МетодCreateFoldersBinaty();

          // TODO: 14.08.2023 создаем папку для Обновления ПО
            ClassCreateFolderUpdatePO classCreateFolderUpdatePO=
                    new ClassCreateFolderUpdatePO(context);
            classCreateFolderUpdatePO.МетодCreateFoldersBinaty();

            // TODO: 07.10.2023  create file for ERROR
            GetWorkerErrosInterface getWorkerErros =new CreatingAFileForApp(context);
            getWorkerErros.launchCreatingAFileForApp();


          Log.d(this.getClass().getName(),"\n" + " class CoreApp    " + Thread.currentThread().getStackTrace()[2].getClassName()
                  + "\n" +
                  " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                  " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

      } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

      }

}
