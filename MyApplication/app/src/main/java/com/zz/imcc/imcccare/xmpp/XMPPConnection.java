package com.zz.imcc.imcccare.xmpp;

import com.zz.imcc.Myapplication;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import java.io.IOException;

/**
 * Created by Administrator on 2017/3/12.
 */

public class XMPPConnection {

    public XMPPTCPConnection con;
    private boolean target;
    private ConnectionIm connectionIm;

    private XmppConnecionListener xmppConnecionListener = new XmppConnecionListener();



    public XMPPConnection(ConnectionIm connectionIm){
        this.connectionIm = connectionIm;
    }

    public XMPPTCPConnection getConnection(){
        return con;
    }

    //初始化XMPPTCPConnection 登录
    public void init(String username,String password) throws IOException, XMPPException, SmackException {
        XMPPTCPConnectionConfiguration.Builder config = XMPPTCPConnectionConfiguration.builder();
        config.setHost(Myapplication.HOST);              //设置openfire主机IP
        config.setServiceName(Myapplication.SERVICENAME);         //设置openfire服务器名称
        config.setPort(5222);                   //设置端口号：默认5222
        config.setUsernameAndPassword(username,password);    //设置用户名与密码
        config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);      //禁用SSL连接
        config.setSendPresence(true);
        config.setDebuggerEnabled(true);
        con = new XMPPTCPConnection(config.build());
        con.connect();
        target = con.isConnected();
        if(target){
            con.addConnectionListener(xmppConnecionListener);
            con.login();
            connectionIm.loginture();
            System.out.println("zhao-----XMPP 服务器连接成功");
        }else{
            System.out.println("zhao--------XMPP 服务器连接失败");
        }
    }


}
