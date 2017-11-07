package com.example.matioyoshitoki.findkids.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.matioyoshitoki.findkids.Tools.Dialog_loading;
import com.example.matioyoshitoki.findkids.constraints.Keys;
import com.example.matioyoshitoki.findkids.http.AutoLogin_Thread;
import com.example.matioyoshitoki.findkids.http.Login_Thread;
import com.example.matioyoshitoki.findkids.socket.MinaClientHandler;
import com.example.matioyoshitoki.findkids.R;
import com.example.matioyoshitoki.findkids.service.TService;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;


import java.net.InetSocketAddress;

import com.example.matioyoshitoki.findkids.Tools.Tools;


public class LoginActivity extends AppCompatActivity {

    private Button login_btn;
    private EditText phone_num ;
    private EditText password ;
    private Button register ;

    private SharedPreferences sPref ;

    private Dialog dialog;
    private int flg;
    private Intent tsIntent;
    public static Context ctx = null;

    /**
     * 网络操作相关的子线程
     */
    Runnable networkTask = new Runnable() {

        @Override
        public void run() {

            Log.i("测试信息","socket启动");
            // TODO
            // 在这里进行 http request.网络请求相关操作
            NioSocketConnector connector = new NioSocketConnector();

            //创建接受数据的过滤器
            DefaultIoFilterChainBuilder chain = connector.getFilterChain();

            //设定这个过滤器将一行一行(/r/n)的读取数据
            chain.addLast("myChin", new ProtocolCodecFilter(new TextLineCodecFactory()));

            //客户端的消息处理器：一个SamplMinaServerHander对象
            connector.setHandler(new MinaClientHandler());

            //连接到服务器：
            ConnectFuture cf = connector.connect(new InetSocketAddress("47.95.115.33",10000));

            //Wait for the connection attempt to be finished.
            cf.awaitUninterruptibly();

            if (cf==null){
                Log.i("提示","请检查网络情况");
            }else {
                cf.getSession().getCloseFuture().awaitUninterruptibly();
            }

            connector.dispose();
        }
    };

    /*
    * 目前登录功能存在bug，bug为服务端无法正确更新session导致自动登录出错
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        Tools.translucentStatusBar(this, true);
        sPref = PreferenceManager.getDefaultSharedPreferences(this);

        if (!sPref.getString(Keys.SESSIONID,"").equals("")){

            AutoLogin_Thread autoLogin_thread = new AutoLogin_Thread(sPref.getString(Keys.SESSIONID,""),mHandler);
            autoLogin_thread.start();
            dialog = Dialog_loading.createLoadingDialog(LoginActivity.this, "登录中...");
        }



        login_btn = (Button)findViewById(R.id.login_btn);
        phone_num = (EditText)findViewById(R.id.login_phoneNum);
        password = (EditText)findViewById(R.id.login_pwd);
        login_btn = (Button)findViewById(R.id.login_btn);

        register = (Button)findViewById(R.id.regist);
        register.setOnClickListener(register_listener);

//        Intent intent = new Intent(LoginActivity.this, FloatWindowService.class);
//        ctx = LoginActivity.this;
//        startService(intent);




        System.out.println("测测测测实"+sPref.getString(Keys.SESSIONID, ""));
        login_btn.setOnClickListener(login_listener);
        (new Thread(networkTask)).start();


        //声明AMapLocationClient类对象

//        Log.i("","这是什么？");

    }


    Button.OnClickListener login_listener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            //login
            String phone = phone_num.getText().toString();
            String pwd = password.getText().toString();
            Login_Thread login_thread = new Login_Thread(phone,pwd,mHandler);
            login_thread.start();
            dialog = Dialog_loading.createLoadingDialog(LoginActivity.this, "登录中...");
        }

    };



    Button.OnClickListener register_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent register_activity = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(register_activity);
        }
    };


    private void startService() {
        Intent i = new Intent(LoginActivity.this, TService.class);

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startService(i);
        Log.i("形式紧急", "服务启动");
    }

    private void stopService() {
        Intent i = new Intent(LoginActivity.this, TService.class);

        this.stopService(i);
        Log.i("形式紧急", "服务关闭");
    }

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    dialog.cancel();
                    JSONObject result = (JSONObject)msg.obj;
                    Log.i("保存session_id",result.getString(Keys.SESSIONID));
                    sPref.edit().putString(Keys.SESSIONID, result.getString(Keys.SESSIONID)).commit();
                    sPref.edit().putString(Keys.USERNAME, result.getString(Keys.USERNAME)).commit();
                    sPref.edit().putString(Keys.USERAUTHORITY, result.getString(Keys.USERAUTHORITY)).commit();
                    sPref.edit().putString(Keys.USERICON, result.getString(Keys.USERICON)).commit();
                    sPref.edit().putString(Keys.USERLEVEL, result.getString(Keys.USERLEVEL)).commit();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                    break;
                case 2:
                    Log.i("失败","失败中～～～～");
                    dialog.cancel();
                    Toast toast = Toast.makeText(LoginActivity.this, (String)msg.obj, Toast.LENGTH_SHORT);
                    toast.show();
                    break;
                default:
                    break;
            }
        }

    };





//    public static void DisplayToast(Context context , String str , int time){
//        if (time == 1){
//            //短时间显示Toast
//            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
//        }else if (time == 2){
//            //长时间显示Toast
//            Toast.makeText(context, str, Toast.LENGTH_LONG).show();
//        }
//    }
}
