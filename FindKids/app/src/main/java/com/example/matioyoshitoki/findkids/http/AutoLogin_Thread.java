package com.example.matioyoshitoki.findkids.http;

import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSONObject;
import com.example.matioyoshitoki.findkids.Tools.AllConts;
import com.example.matioyoshitoki.findkids.constraints.Keys;

import java.util.ArrayList;

/**
 * Created by matioyoshitoki on 2017/10/24.
 */
public class AutoLogin_Thread extends Thread {
    Handler mHandler;
    String session_id ;


    public AutoLogin_Thread(String session_id,Handler mHandler){
        this.mHandler = mHandler;
        this.session_id = session_id;
    }

    @Override
    public void run() {
        super.run();
        ArrayList<String> ps = new ArrayList<>();
        ps.add(Keys.SESSIONID+ "=" + session_id);
        JSONObject result = JSONObject.parseObject(GetAndPost.executeHttpPost("http://47.95.115.33:8080/autoLog", ps));

        System.out.println(result);
        switch (result.getString(Keys.STATUS)){
            case AllConts.SUCCESS :
                Message msg = new Message();
                msg.what = 1;
                result.put(Keys.SESSIONID,session_id);
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
