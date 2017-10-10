package com.example.matioyoshitoki.findkids.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.matioyoshitoki.findkids.socket.MinaClientHandler;
import com.example.matioyoshitoki.findkids.R;
import com.example.matioyoshitoki.findkids.service.TService;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;

import tools.Tools;


public class testService extends AppCompatActivity {

    private Button login_btn;
    private EditText phone_num ;
    private EditText password ;
    private Button register ;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        Tools.translucentStatusBar(this, true);

        login_btn = (Button)findViewById(R.id.login_btn);
        phone_num = (EditText)findViewById(R.id.login_phoneNum);
        password = (EditText)findViewById(R.id.login_pwd);
        login_btn = (Button)findViewById(R.id.login_btn);

        register = (Button)findViewById(R.id.regist);
        register.setOnClickListener(register_listener);

//        Intent intent = new Intent(testService.this, FloatWindowService.class);
//        ctx = testService.this;
//        startService(intent);




        login_btn.setOnClickListener(Listener01);
        (new Thread(networkTask)).start();


        //声明AMapLocationClient类对象

//        Log.i("","这是什么？");

    }


    Button.OnClickListener Listener01 = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            //login
            String phone = phone_num.getText().toString();
            String pwd = password.getText().toString();


        }

    };


    Button.OnClickListener register_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent register_activity = new Intent(testService.this,RegisterActivity.class);
            startActivity(register_activity);
        }
    };


    private void startService() {
        Intent i = new Intent(testService.this, TService.class);

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startService(i);
        Log.i("形式紧急", "服务启动");
    }

    private void stopService() {
        Intent i = new Intent(testService.this, TService.class);

        this.stopService(i);
        Log.i("形式紧急", "服务关闭");
    }




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
