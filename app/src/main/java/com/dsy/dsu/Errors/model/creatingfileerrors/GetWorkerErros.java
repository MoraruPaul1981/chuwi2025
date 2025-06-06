package com.dsy.dsu.Errors.model.creatingfileerrors;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;
import com.dsy.dsu.Errors.model.interfaces.GetWorkerErrosInterface;

import java.io.File;

public  class GetWorkerErros implements GetWorkerErrosInterface {

    private    Context context;
    private String fileName = "Sous-Avtodor-ERROR.txt";

    private   String patchFileName="SousAvtoFile";

    public GetWorkerErros(Context context) {
        this.context = context;
    }


    @Override
    public void launchCreatingAFileForApp() {
        try {

            File patchFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    File.separator + patchFileName);

            if (!patchFile.isDirectory()) {
                patchFile.setReadable(true,false);
                patchFile.setWritable(true,false);
                patchFile.setExecutable(true,false);
                patchFile.mkdirs();
                // TODO: 10.04.2025
                patchFile.createNewFile();
                // TODO: 10.04.2025
            }
            File file = new File(String.valueOf(patchFile.getAbsoluteFile())+File.separator+fileName);

            if ( ! file.isFile()) {
                file.setReadable(true,false);
                file.setWritable(true,false);
                file.setExecutable(true,false);
                file.createNewFile();



                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  +   " file.isFile() "+file.isFile());
            }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
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