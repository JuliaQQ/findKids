package com.example.matioyoshitoki.findkids.http;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSONObject;
import com.example.matioyoshitoki.findkids.Tools.AllConts;
import com.example.matioyoshitoki.findkids.constraints.Keys;


import java.util.ArrayList;

/**
 * Created by matioyoshitoki on 2017/10/23.
 */
public class Login_Thread extends Thread {
    String phone_num;
    String pwd ;
    Handler mHandler;


    public Login_Thread(String phone_num,String pwd,Handler mHandler){
        this.phone_num = phone_num;
        this.pwd = pwd;
        this.mHandler = mHandler;
    }

    @Override
    public void run() {
        super.run();
        ArrayList<String> ps = new ArrayList<>();
        ps.add(Keys.PHONENUMBER+ "=" + phone_num);
        ps.add(Keys.PASSWORD + "=" + pwd);
        JSONObject result = JSONObject.parseObject(GetAndPost.executeHttpPost("http://47.95.115.33:8080/log", ps));
        System.out.println(result);
        switch (result.getString(Keys.STATUS)){
            case AllConts.SUCCESS :
                Message msg = new Message();
                msg.what = 1;
                msg.obj = result;
                mHandler.sendMessage(msg);
                break;
            case AllConts.FAILD:
                Message msg_faild = new Message();
                msg_faild.what = 2;
                msg_faild.obj = result.getString(Keys.INFO);
                break;
        }
    }
}
