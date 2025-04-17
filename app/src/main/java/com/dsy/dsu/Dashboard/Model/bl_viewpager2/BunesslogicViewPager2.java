package com.dsy.dsu.Dashboard.Model.bl_viewpager2;

import static androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_SETTLING;

import android.content.Context;
import android.util.Log;

import androidx.viewpager2.widget.ViewPager2;

import com.dsy.dsu.Dashboard.View.Fragments.DashboardFragmentMaterialDesign;
import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;

public class BunesslogicViewPager2 {
   private ViewPager2 pagerdachbord;
    private Context getcontext;
    private  DemoCollectionAdapter demoCollectionAdapter;
    private  DashboardFragmentMaterialDesign dashboardFragmentMaterialDesign;
    public BunesslogicViewPager2(ViewPager2 pagerdachbord, Context getcontext, DashboardFragmentMaterialDesign dashboardFragmentMaterialDesign) {
        this.pagerdachbord = pagerdachbord;
        this.getcontext = getcontext;
        this.dashboardFragmentMaterialDesign = dashboardFragmentMaterialDesign;
    }

   public void getBunesslogicViewPager2(){
        try{
            demoCollectionAdapter = new DemoCollectionAdapter(dashboardFragmentMaterialDesign);
            dashboardFragmentMaterialDesign.pagerdachbord.setAdapter(demoCollectionAdapter);
            dashboardFragmentMaterialDesign.pagerdachbord.setScrollContainer(true);
            dashboardFragmentMaterialDesign.pagerdachbord.animate().start();
            dashboardFragmentMaterialDesign.pagerdachbord.setPageTransformer(new ZoomOutPageTransformerDashBoard());
            dashboardFragmentMaterialDesign.pagerdachbord.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                }

                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);


                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    super.onPageScrollStateChanged(state);
                    if(state==SCROLL_STATE_SETTLING){
                    /*    dashboardFragmentMaterialDesign.pagerdachbord.getHandler().postDelayed(()->{
                            dashboardFragmentMaterialDesign.pagerdachbord.setCurrentItem( 0,true);
                        },5000);*/
                    }
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                }
            });
        // TODO: 21.06.2023
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getcontext).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


}
