package com.dsy.dsu.BusinessLogicAll;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.dsy.dsu.CnangeServers.PUBLIC_CONTENT;

public class Class_Start_Update_Soft {

    Context context;
    // TODO: 12.11.2021

    Activity activity;
    PUBLIC_CONTENT public_contentПО;

    public Class_Start_Update_Soft(Context context, Activity activity) {

        this.context = context;

        this.activity = activity;


        public_contentПО = new PUBLIC_CONTENT(context);

        Log.d(this.getClass().getName(), " context " + context + "\n" +
                "  activity " + activity);

    }















}
