package com.dsy.dsu.Dashboard.Model.bl_launchFragmentSettingsandDashbord;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dsy.dsu.Dashboard.View.Fragments.DashboardFragmentMaterialDesign;
import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;
import com.dsy.dsu.Services.ServiceUpdatePoОбновлениеПО;

import org.jetbrains.annotations.NotNull;

public class LaunchActivityDashboard {

    FragmentManager fragmentManager;
    Context context;

    public LaunchActivityDashboard(  FragmentManager fragmentManager, Context context) {
        this.fragmentManager = fragmentManager;
        this.context = context;
    }


  public   void launchADashboardFragment() {
        try {
            // TODO Запусукаем Фргамент DdshBoard
            DashboardFragmentMaterialDesign dashboardFragmentMaterialDesign = DashboardFragmentMaterialDesign.newInstance();
            Bundle data = new Bundle();
            FragmentTransaction   fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.addToBackStack(null);
            dashboardFragmentMaterialDesign.setArguments(data);
           fragmentTransaction.setPrimaryNavigationFragment(dashboardFragmentMaterialDesign);
            dashboardFragmentMaterialDesign.setEnterTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
             fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            dashboardFragmentMaterialDesign.show(fragmentManager, "DashboardFragmentMaterialDesign");
            fragmentTransaction.commit();

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }

    public   void launchADashboardFragment(     @NotNull  ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО localBinderОбновлениеПО) {
        try {
            // TODO Запусукаем Фргамент DdshBoard
            DashboardFragmentMaterialDesign dashboardFragmentMaterialDesign = DashboardFragmentMaterialDesign.newInstance();
            Bundle data = new Bundle();
            FragmentTransaction   fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.addToBackStack(null);
            fragmentManager.popBackStack();
            dashboardFragmentMaterialDesign.setArguments(data);
            fragmentTransaction.setPrimaryNavigationFragment(dashboardFragmentMaterialDesign);
            dashboardFragmentMaterialDesign.setEnterTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            dashboardFragmentMaterialDesign.show(fragmentManager, "DashboardFragmentMaterialDesign");


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }




}
