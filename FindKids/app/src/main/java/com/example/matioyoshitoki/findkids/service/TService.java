package com.example.matioyoshitoki.findkids.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.matioyoshitoki.findkids.constraints.Keys;
import com.example.matioyoshitoki.findkids.socket.MinaClientHandler;


import com.example.matioyoshitoki.findkids.Tools.AllConts;
import com.example.matioyoshitoki.findkids.Tools.Tools;

/**
 * Created by matioyoshitoki on 17/2/20.
 */
public class TService extends Service {
    /**
     * 创建Handler对象，作为进程传递postDelayed之用
     */
    private Handler objHandler = new Handler();
    private int intCounter = 0;
    private static final String TAG = "TService";
    AMapLocationClient mLocationClient = null;
    private String keyPws = "hslenxjahslenxj)hslenxj@hslenxj!hslenxj,hslenxj/hsle7xja";
    private int index = 0;

    private JSONArray data = new JSONArray();
    private String phone_num;
//    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

//    private NotificationManager notificationManager;

    public TService(String phone_num){
        Log.i("初始化","哈哈哈");
        this.phone_num = phone_num;
    }
//    private Runnable mTasks = new Runnable() {
//        public void run() {
//            intCounter++;
//
//            Log.i("HIPPO", "Counter:" + Integer.toString(intCounter));
//
//            objHandler.postDelayed(mTasks, 1000);
//        }
//    };

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i("onStart","可不可以？");
    }

    public void onCreate() {
        Log.i(TAG, "============> TService.onCreate");
//        (new Thread(mTasks)).start();
        System.out.println("这种可不可以啊？");


        //声明定位回调监听器
        AMapLocationListener mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                String lat = ""+aMapLocation.getLatitude();
                String lo = ""+aMapLocation.getLongitude();
                String spd = ""+aMapLocation.getSpeed();
                String time = Tools.getNowDateString("YYYYMMddHHmmss");
                JSONObject tmp = new JSONObject();

                tmp.put(Keys.LAT,lat);
                tmp.put(Keys.LONG,lo);
                tmp.put(Keys.TIME,time);
                tmp.put(Keys.SPEED,spd);

                data.add(tmp);
                if (index < 10) {
                    Log.i("序列号", index + ":"+tmp.toString());
                    index++;
                }else {

                    JSONObject result = new JSONObject();

                    result.put(Keys.TYPE,AllConts.TYPESENDLOCATION);
                    result.put(Keys.DATA,data);
                    result.put(Keys.PHONENUMBER, phone_num);

                    MinaClientHandler.localBuff = result.toString();//new String(Tools.encrypt(jsb.toString().getBytes(),keyPws));
                    data = new JSONArray();
                    index = 0;
                }
            }
        };



        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);

        //声明AMapLocationClientOption对象
        AMapLocationClientOption mLocationOption = null;
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();

        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        mLocationOption.setInterval(1000);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();

        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("服务出现", "2333");
        return super.onStartCommand(intent, flags, startId);

    }

    public IBinder onBind(Intent intent) {
        Log.i(TAG, "============> TService.onBind");
        return null;
    }
//
//    public class LocalBinder extends Binder {
//        public TService getService() {
//            return TService.this;
//        }
//    }
//
//    public boolean onUnbind(Intent intent) {
//        Log.i(TAG, "============> TService.onUnbind");
//        return false;
//    }
//
//    public void onRebind(Intent intent) {
//        Log.i(TAG, "============> TService.onRebind");
//    }
//
    public void onDestroy() {
        Log.i(TAG, "============> TService.onDestroy");
        mLocationClient.stopLocation();
//        objHandler.removeCallbacks(mTasks);
        super.onDestroy();
    }

//    private void showNotification() {
////        Notification notification = new Notification(R.drawable.icon,
////                "SERVICE START", System.currentTimeMillis());
//
//        Intent intent = new Intent(this, LoginActivity.class);
//        intent.putExtra("FLG", 1);
//        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
//                intent, 0);
//
//
////        notification.setLatestEventInfo(this, "SERVICE", "SERVICE START",
////                contentIntent);
////        notificationManager.notify(R.string.service_start, notification);
//    }
}
