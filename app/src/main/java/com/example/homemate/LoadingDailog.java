package com.example.homemate;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class LoadingDailog {
    private Activity activity;
    private AlertDialog alertDialog;
    public LoadingDailog(Activity myActivity) {
        activity=myActivity;
    }
    public void startDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        LayoutInflater layoutInflater=activity.getLayoutInflater();
        builder.setView(layoutInflater.inflate(R.layout.custom_dialog,null));
        builder.setCancelable(true);

        alertDialog=builder.create();
        alertDialog.show();
    }
    public void dismissDiaLog(){
        alertDialog.dismiss();
    }
}
