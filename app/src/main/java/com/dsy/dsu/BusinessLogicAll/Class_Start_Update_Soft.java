package com.dsy.dsu.BusinessLogicAll;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.dsy.dsu.CnangeServers.BinessLogicPublicContent;

public class Class_Start_Update_Soft {

    Context context;
    // TODO: 12.11.2021

    Activity activity;
    BinessLogicPublicContent binessLogicPublicContentПО;

    public Class_Start_Update_Soft(Context context, Activity activity) {

        this.context = context;

        this.activity = activity;


        binessLogicPublicContentПО = new BinessLogicPublicContent(context);

        Log.d(this.getClass().getName(), " context " + context + "\n" +
                "  activity " + activity);

    }















}
