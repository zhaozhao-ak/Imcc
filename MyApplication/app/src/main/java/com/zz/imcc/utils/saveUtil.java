package com.zz.imcc.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/3/14.
 */

public class SaveUtil {


    public static void saveData(Context context, String name, String msg){

        SharedPreferences sp = context.getSharedPreferences("sp_util", Context.MODE_PRIVATE);
        sp.edit()
                .putString(name, msg)
                .commit();
    }

    public static String getData(Context context,String name){
        SharedPreferences sp = context.getSharedPreferences("sp_util", Context.MODE_PRIVATE);
        String msg = sp.getString(name, null);
        return msg;
    }

}
