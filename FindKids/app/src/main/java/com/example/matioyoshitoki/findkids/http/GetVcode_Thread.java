package com.example.matioyoshitoki.findkids.http;

import android.util.Log;

import com.example.matioyoshitoki.findkids.constraints.Keys;
import com.example.matioyoshitoki.findkids.http.GetAndPost;

import java.util.ArrayList;

/**
 * Created by matioyoshitoki on 2017/10/16.
 */
public class GetVcode_Thread extends Thread{

    String phone_num;


    public GetVcode_Thread(String phone_num){
        this.phone_num = phone_num;
    }

    @Override
    public void run() {
        super.run();
        ArrayList<String> ps = new ArrayList<>();
        ps.add(Keys.PHONENUMBER+ "=" + phone_num);
        GetAndPost.executeHttpPost("http://47.95.115.33:8080/getvcode", ps);
    }
}
