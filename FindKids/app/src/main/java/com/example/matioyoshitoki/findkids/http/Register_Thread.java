package com.example.matioyoshitoki.findkids.http;

import com.example.matioyoshitoki.findkids.constraints.Keys;

import java.util.ArrayList;

/**
 * Created by matioyoshitoki on 2017/10/17.
 */
public class Register_Thread extends Thread{
    String phone_num;
    String password ;
    String vcode ;


    public Register_Thread(String phone_num,String password,String vcode){
        this.phone_num = phone_num;
        this.password = password;
        this.vcode = vcode;
    }

    @Override
    public void run() {
        super.run();
        ArrayList<String> ps = new ArrayList<>();
        ps.add(Keys.PHONENUMBER+ "=" + phone_num);
        ps.add(Keys.PASSWORD+"="+password);
        ps.add(Keys.VCODE+"="+vcode);
        GetAndPost.executeHttpPost("http://47.95.115.33:8080/reg", ps);
    }
}
