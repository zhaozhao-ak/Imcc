package com.zz.imcc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.zz.imcc.imcccare.service.ImService;
import com.zz.imcc.ui.login.LoginIm;
import com.zz.imcc.utils.LoginNum;
import com.zz.imcc.utils.SaveUtil;

/**
 * Created by Administrator on 2017/3/12.
 */

public class InterIm{
    private InterIm interIm;
    private Context context;

    private LoginIm loginIm;
    private OutIm outIm;
    private ServiceConnection mSc;
    private  ImService imService;

    public InterIm(final Context context, OutIm outIm){
        this.context = context;
        this.outIm = outIm;
        mSc = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                imService = ((ImService.MyBinder)iBinder).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
        Intent service = new Intent(context,ImService.class);
        context.bindService(service, mSc, Context.BIND_AUTO_CREATE);
    }

    public void login(){
        String username = SaveUtil.getData(context,"username");
        String password = SaveUtil.getData(context,"password");

        System.out.println("im-----getData----username-----"+username);
        System.out.println("im-----getData----password-----"+password);

        int num = imService.longin(username,password);

        System.out.println("im-----getData----num-----"+num);

        if (num == LoginNum.LOGINSUCCESS){
            outIm.loginsucceed();
        }else {
            outIm.logindefeated();
        }
    }
}
