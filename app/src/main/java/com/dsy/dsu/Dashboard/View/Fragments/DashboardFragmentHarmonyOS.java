package com.dsy.dsu.Dashboard.View.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dsy.dsu.CallNavigarlaout.CallNavigarlaout;
import com.dsy.dsu.Errors.WriteErrorForAll.RecordNewErros;
import com.dsy.dsu.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * AHilt simple {@link Fragment} subclass.
 * Use the {@link DashboardFragmentHarmonyOS#newInstance} factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
public class DashboardFragmentHarmonyOS extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match

    private  BuniccessLogicFra4gmentDashboard buniccessLogicFra4gmentDashboard;
    private MaterialCardView materialcardview_dashboard=null;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private          View ViewDashboart=null;

    DrawerLayout navigator_dashboord_ubuntu;
    RelativeLayout relativelayout_dashboard;

    private Handler handlerDashBord;
    public DashboardFragmentHarmonyOS() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DashboardFragmentHarmonyOS newInstance( ) {
        DashboardFragmentHarmonyOS fragment = new DashboardFragmentHarmonyOS();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
        buniccessLogicFra4gmentDashboard=new BuniccessLogicFra4gmentDashboard();
            // TODO: 17.08.2023 inizial message
            buniccessLogicFra4gmentDashboard.  МетодИнициализацияMessager();
            //  setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_Material_Dialog_Alert);//Theme_Dialog
       // setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog
      //  setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);//Theme_Dialog
       // setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog
      //  setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Dialog_Alert);//Theme_Dialog
       // setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);//Theme_Dialog
       // setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);//Theme_Dialog
        //setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Light_Panel);//Theme_Dialog
      //  setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_InputMethod);//Theme_Dialog
       // setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Light_Panel);//Theme_Dialog
       // setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Panel);//Theme_Dialog
       // setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Settings);//Theme_Dialog
        //setStyle(DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_DeviceDefault_Wallpaper_NoTitleBar);//Theme_Dialog
       // setStyle(DialogFragment.STYLE_NO_FRAME | DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Wallpaper_NoTitleBar);//Theme_Dialog
      //  setStyle(  DialogFragment.STYLE_NO_FRAME | DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_DeviceDefault_Wallpaper_NoTitleBar);//Theme_Dialog
    //    setStyle(   DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_DeviceDefault_Wallpaper_NoTitleBar);//Theme_Dialog
      //  setStyle(   DialogFragment.STYLE_NO_INPUT ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog
       // setStyle(   DialogFragment.STYLE_NO_FRAME ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Overscan);//Theme_Dialog
        setStyle(   DialogFragment.STYLE_NO_FRAME ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Overscan);//Theme_Dialog
            //setCancelable(false);
        // TODO: 15.08.2023
        Log.d(this.getClass().getName(),"\n" + " class "
                + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(getContext().getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        try{
        fragmentManager.setFragmentResult(     "CallBackDashborndFragment",new Bundle());

        Log.d(this.getClass().getName(),"\n" + " class "
                + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(getContext().getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=null;
        try{
       /*     ViewDashboart= inflater.inflate(R.layout.simple_dashbord_fragment_grey, container, false);
            ViewDashboart= inflater.inflate(R.layout.simple_dashbord_fragment_grey, container, false);
            ViewDashboart= inflater.inflate(R.layout.simple_dashbord_fragment_grey, container, false);*/
           // ViewDashboart= inflater.inflate(R.layout.simple_dashbord_fragment_grey, container, false);
           // ViewDashboart= inflater.inflate(R.layout.simple_dashbord_fragment_blue, container, false);
            view= inflater.inflate(R.layout.simple_dashbord_fragment_grey, container, false);
           // view= inflater.inflate(R.layout.simple_dashbord_fragment_green, container, false);
            // TODO: 21.06.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  view; //TODO inflater.inflate(R.layout.activity_main__tabel_four_colums, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try{
            super.onViewCreated(view, savedInstanceState);
            fragmentManager = getActivity(). getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();

            ViewDashboart=view;
            DrawerLayout      drawerLayout_dashdord                  = (DrawerLayout) view.findViewById(R.id.drawerLayout_dashdord); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            navigator_dashboord_ubuntu = (DrawerLayout) drawerLayout_dashdord.findViewById(R.id.navigator_dashboord_ubuntu); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            // TODO: 17.08.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }





    @Override
    public void onStart() {
        super.onStart();
        try{

       buniccessLogicFra4gmentDashboard.методНастройкиВнешнегоВида();





          new CallNavigarlaout(navigator_dashboord_ubuntu,handlerDashBord,getContext()).методНастройкиБоковойпанели();


        // TODO: 20.07.2023
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(getContext().getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    // TODO: 15.08.2023  Бизнес ЛОгика Bunecees Logic Dashboard Fragment

    class BuniccessLogicFra4gmentDashboard{


        private void методНастройкиВнешнегоВида() {
            try{


                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(    getDialog().getWindow().getAttributes());
                layoutParams.gravity = Gravity.CENTER;
                layoutParams.dimAmount=0.0f;

                getDialog().getWindow().setAttributes(layoutParams);
                getDialog().getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

                // TODO: 20.07.2023
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        // TODO: 17.08.2023  Класс Для Компонента Табель


        class BunicessLogicTabel{
            MaterialTextView materialtextview_dashborn_tabel;
/*            void методНАстройкиДизайнаТабеля( ){
                materialtextview_dashborn_tabel=(MaterialTextView)      ViewDashboart.findViewById(R.id.materialtextview_dashborn_tabel);

                Spannable wordtoSpan = new SpannableString(materialtextview_dashborn_tabel.getText().toString());

                wordtoSpan.setSpan(new ForegroundColorSpan(Color.BLUE), 0, materialtextview_dashborn_tabel.getText().toString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                materialtextview_dashborn_tabel.setText(wordtoSpan);



            }*/



        }











        private void МетодИнициализацияMessager() {
            try{

                handlerDashBord=new Handler(Looper.getMainLooper()){


                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                    }

                    @Override
                    public void dispatchMessage(@NonNull Message msg) {
                        super.dispatchMessage(msg);

                        try {
                            Bundle bundleCallsBackAsynsService=msg.getData();
                            Log.d(this.getClass().getName(), "\n" + " class " +
                                    Thread.currentThread().getStackTrace()[2].getClassName()
                                    + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " msg.what " +msg.what  + "bundleCallsBackAsynsService "+bundleCallsBackAsynsService);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }

                    }
                };

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }













    }//TODO end Buniceess Lofic for Activity



}