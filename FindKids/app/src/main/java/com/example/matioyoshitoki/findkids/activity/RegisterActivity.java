package com.example.matioyoshitoki.findkids.activity;

import android.app.Dialog;
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

import com.example.matioyoshitoki.findkids.R;
import com.example.matioyoshitoki.findkids.Tools.AccountValidatorUtil;
import com.example.matioyoshitoki.findkids.Tools.Dialog_loading;
import com.example.matioyoshitoki.findkids.http.GetVcode_Thread;
import com.example.matioyoshitoki.findkids.Tools.Timer_changeButton;

import com.example.matioyoshitoki.findkids.Tools.Tools;
import com.example.matioyoshitoki.findkids.http.Register_Thread;

/**
 * Created by matioyoshitoki on 2017/9/28.
 */
public class RegisterActivity extends AppCompatActivity {

    Button back ;
    Button getVcode_btn ;
    Button regist ;
    EditText phone_num ;
    EditText vcode ;
    EditText password ;

    private SharedPreferences sPref ;

    Dialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);
        setContentView(R.layout.register);
        Tools.translucentStatusBar(this, true);

        sPref = PreferenceManager.getDefaultSharedPreferences(this);

        back = (Button)findViewById(R.id.register_back);
        getVcode_btn = (Button)findViewById(R.id.register_send_vcode);
        regist = (Button)findViewById(R.id.regist_btn);
        phone_num = (EditText)findViewById(R.id.regist_phone);
        vcode = (EditText)findViewById(R.id.register_vcode);
        password = (EditText)findViewById(R.id.regist_pwd);

        back.setOnClickListener(back_listener);
        getVcode_btn.setOnClickListener(getVcode_listener);

        regist.setOnClickListener(register_listener);


    }

    Button.OnClickListener back_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent main_view = new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(main_view);
            RegisterActivity.this.finish();
        }
    };

    Button.OnClickListener getVcode_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (phone_num.getText().toString().isEmpty()){
                Toast toast = Toast.makeText(RegisterActivity.this, "手机号不能为空", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
            if (AccountValidatorUtil.isMobile(phone_num.getText().toString())){
                GetVcode_Thread getVcode_thread = new GetVcode_Thread(phone_num.getText().toString());
                getVcode_thread.start();
                Timer_changeButton timer_changeButton = new Timer_changeButton(30,mHandler);
                timer_changeButton.start();
                getVcode_btn.setOnClickListener(no_use);
            }else {
                Toast toast = Toast.makeText(RegisterActivity.this, "手机号格式错误", Toast.LENGTH_SHORT);
                toast.show();
            }


        }
    };

    Button.OnClickListener no_use = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    Button.OnClickListener register_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (phone_num.getText().toString().isEmpty()){
                Toast toast = Toast.makeText(RegisterActivity.this, "手机号不能为空", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
            if (password.getText().toString().isEmpty()){
                Toast toast = Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
            if (vcode.getText().toString().isEmpty()){
                Toast toast = Toast.makeText(RegisterActivity.this, "验证码不能为空", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
            if (AccountValidatorUtil.isMobile(phone_num.getText().toString())){
                Register_Thread register_thread = new Register_Thread(phone_num.getText().toString(),password.getText().toString(),vcode.getText().toString(),mHandler);
                register_thread.start();
            }else {
                Toast toast = Toast.makeText(RegisterActivity.this, "手机号格式错误", Toast.LENGTH_SHORT);
                toast.show();
            }
            dialog = Dialog_loading.createLoadingDialog(RegisterActivity.this, "注册中...");


        }
    };

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    //完成主界面更新,拿到数据
                    int data = (int)msg.obj;
                    Log.i("","(" + data + "s)后重试");
                    getVcode_btn.setText("(" + data + "s)后重试");
//                    getVcode_btn.setTextColor(0xffffff);
                    if (data==0){
//                        getVcode_btn.setEnabled(true);
                        getVcode_btn.setOnClickListener(getVcode_listener);
                        getVcode_btn.setText("发送验证码");
                    }

                    break;
                case 1:
                    dialog.cancel();

                    sPref.edit().putString("session_id", (String)msg.obj).commit();
                    Toast toast = Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                    RegisterActivity.this.finish();
                default:
                    break;
            }
        }

    };



}
