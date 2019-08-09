package com.example.matioyoshitoki.findkids.Tools;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by matioyoshitoki on 2017/10/16.
 */
public class Timer_changeButton extends Thread {

    int time = 0;
    Handler mHandler;

    public Timer_changeButton(int time,Handler mHandler){
        this.time = time;
        this.mHandler = mHandler;
    }

    @Override
    public void run() {
        super.run();
        while (time!=0){
            //耗时操作，完成之后发送消息给Handler，完成UI更新；
//            mHandler.sendEmptyMessage(0);

            //需要数据传递，用下面方法；
            Message msg =new Message();
            msg.what = 0;
            msg.obj = time;//可以是基本类型，可以是对象，可以是List、map等；
            mHandler.sendMessage(msg);
            Log.i("","(" + time + "s)后重试");
            time--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Message msg =new Message();
        msg.what = 0;
        msg.obj = time;//可以是基本类型，可以是对象，可以是List、map等；
        mHandler.sendMessage(msg);
        Log.i("", "(" + time + "s)后重试");
    }


}
