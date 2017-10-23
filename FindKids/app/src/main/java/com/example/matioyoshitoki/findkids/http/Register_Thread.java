package com.example.matioyoshitoki.findkids.http;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.example.matioyoshitoki.findkids.Tools.AllConts;
import com.example.matioyoshitoki.findkids.constraints.Keys;

import java.util.ArrayList;

/**
 * Created by matioyoshitoki on 2017/10/17.
 */
public class Register_Thread extends Thread{
    String phone_num;
    String password ;
    String vcode ;
    private SharedPreferences sPref;
    Handler mHandler ;


    public Register_Thread(String phone_num,String password,String vcode,Handler mHandler){
        this.phone_num = phone_num;
        this.password = password;
        this.vcode = vcode;
        this.mHandler = mHandler;
    }

    @Override
    public void run() {
        super.run();
        ArrayList<String> ps = new ArrayList<>();
        ps.add(Keys.PHONENUMBER+ "=" + phone_num);
        ps.add(Keys.PASSWORD + "=" + password);
        ps.add(Keys.VCODE + "=" + vcode);
        JSONObject result = JSONObject.parseObject(GetAndPost.executeHttpPost("http://47.95.115.33:8080/reg", ps));
        if (result.getString(Keys.STATUS).equals(AllConts.SUCCESS)){
            sPref.edit().putString("session_id",result.getString(Keys.SESSIONID));
            mHandler.sendEmptyMessage(1);
        }else {
            mHandler.sendEmptyMessage(1);
        }

    }
}
