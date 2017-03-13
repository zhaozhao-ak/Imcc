package com.zz.imcc;

import android.content.Context;

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
    private OutIm outIm;

    public InterIm(Context context,OutIm outIm){
        this.context = context;
        this.outIm = outIm;
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
            System.out.println("zhao-----11-----"+e);
            e.printStackTrace();
        } catch (XMPPException e) {
            outIm.logindefeated();
            System.out.println("zhao-----登录失败，密码错误-----"+e);
            e.printStackTrace();
        } catch (SmackException e) {
            System.out.println("zhao-----33-----"+e);
            e.printStackTrace();
        }
    }

    //登录成功发广播
    @Override
    public void loginture() {
        System.out.println("zhao-----99-----");
        outIm.loginsucceed();
    }
}
