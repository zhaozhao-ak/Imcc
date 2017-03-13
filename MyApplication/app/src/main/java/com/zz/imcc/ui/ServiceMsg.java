package com.zz.imcc.ui;

import android.content.Context;

import com.zz.imcc.InterIm;
import com.zz.imcc.OutIm;
import com.zz.imcc.ui.login.LoginIm;

/**
 * Created by Administrator on 2017/3/13.
 */

public class ServiceMsg implements OutIm {

    private Context context;
    private LoginIm loginIm;
    private InterIm interIm;
    public ServiceMsg(Context context, LoginIm loginIm){
        this.context = context;
        this.loginIm = loginIm;
        this.interIm = new InterIm(context,this);
    }

    @Override
    public void loginsucceed() {
        loginIm.loginture();
    }

    @Override
    public void logindefeated() {
        loginIm.loginerror();
    }

    public void login(){
        interIm.login();
    }
}
