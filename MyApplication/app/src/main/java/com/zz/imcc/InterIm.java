package com.zz.imcc;

import android.content.Context;
import android.content.Intent;

import com.zz.imcc.imcccare.xmpp.ConnectionIm;
import com.zz.imcc.imcccare.xmpp.XMPPConnection;
import com.zz.imcc.ui.login.LoginIm;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;

import java.io.IOException;

/**
 * Created by Administrator on 2017/3/12.
 */

public class InterIm implements ConnectionIm{
    private InterIm interIm;
    private Context context;

    private LoginIm loginIm;

    public InterIm(Context context){
        this.context = context;
    }

    public void login(){
        XMPPConnection xmppConnection = new XMPPConnection(this);
        String username = Myapplication.username;
        String password = Myapplication.password;
        System.out.println("zhao-username----------"+username);
        System.out.println("zhao-password----------"+password);

        try {
            xmppConnection.init(username,password);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMPPException e) {
            e.printStackTrace();
        } catch (SmackException e) {
            e.printStackTrace();
        }
    }

    //登录成功发广播
    @Override
    public void loginture() {
        System.out.println("zhao-----99-----");
        Intent intent = new Intent();
        intent.setAction("Loginture");
        context.sendBroadcast(intent);
    }
}
