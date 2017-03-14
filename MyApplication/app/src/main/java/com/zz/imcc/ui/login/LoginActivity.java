package com.zz.imcc.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zz.imcc.R;
import com.zz.imcc.ui.ServiceMsg;
import com.zz.imcc.ui.main.MainActivity;
import com.zz.imcc.utils.SaveUtil;
import com.zz.imcc.utils.ToastUtil;


/**
 * 登录界面
 */
public class LoginActivity extends AppCompatActivity implements LoginIm{

    private EditText nameEText, pwdEText;
    private Button loginButton;
    private ServiceMsg serviceMsg;
    private Context context;


    // 主机IP
    public static final String HOST = "192.168.1.105";
    // 对应的端口号
    public static final int PORT = 5222;
    //对应的主机名称
    public static final String SERVICENAME = "mvncsctbsgqlqc7";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        serviceMsg = new ServiceMsg(context,this);
        //初始化View
        initView();

        //绑定监听器
        initListener();
    }

    /**
     * 初始化View
     */
    private void initView() {
        nameEText = (EditText) findViewById(R.id.et_name);
        pwdEText = (EditText) findViewById(R.id.et_pwd);
        loginButton = (Button) findViewById(R.id.bt_login);

    }

    /**
     * 绑定监听器
     */
    private void initListener() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取出用户名和密码
                final String username = nameEText.getText().toString().trim();
                final String password = pwdEText.getText().toString().trim();
                //判断用户名与密码是否为空

                if (TextUtils.isEmpty(username)||TextUtils.isEmpty(password)) {
                    nameEText.setError("用户名或者密码不能为空");
                    return;
                }else {
                    SaveUtil.saveData(context,"username",username);
                    SaveUtil.saveData(context,"password",password);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            serviceMsg.login();
                        }
                    }).start();
                }
            }
        });
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    ToastUtil.showToastSafe(context,"登录成功");
                    Intent main = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(main);
                    break;
                case 2:
                    ToastUtil.showToastSafe(context,"密码错误，请重新登录");
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    public void loginture() {
        Message msg = new Message();
        msg.what = 1;
        handler.sendMessage(msg);

    }

    @Override
    public void loginerror() {
        Message msg = new Message();
        msg.what = 2;
        handler.sendMessage(msg);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
