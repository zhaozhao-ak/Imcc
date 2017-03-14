package com.zz.imcc.imcccare.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.zz.imcc.imcccare.xmpp.XMPPConnection;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;

import java.io.IOException;

/**
 * Created by Administrator on 2017/3/14.
 */

public class ImService extends Service {

    private int loginNum = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }


    public int longin(final String username, final String password){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //初始化登录
                    XMPPConnection.init(username,password);
                    XMPPTCPConnection con = XMPPConnection.getConnection();
                    con.login();
                    loginNum = 1;
                } catch (IOException e) {
                    System.out.println("zhao-----11-----"+e);
                    e.printStackTrace();
                } catch (XMPPException e) {
                    loginNum = 2;
                    System.out.println("zhao-----登录失败，密码错误-----"+e);
                    e.printStackTrace();
                } catch (SmackException e) {
                    System.out.println("zhao-----33-----"+e);
                    e.printStackTrace();
                }
            }
        }).start();
        return loginNum;
    }

    public class MyBinder extends Binder {
        public ImService getService() {
            return ImService.this;
        }
    }
}
