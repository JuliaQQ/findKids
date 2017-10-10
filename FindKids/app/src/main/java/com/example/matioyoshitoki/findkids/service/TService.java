package com.example.matioyoshitoki.findkids.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.matioyoshitoki.findkids.socket.MinaClientHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import tools.AllConts;
import tools.Tools;

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
    JSONArray jsa = new JSONArray();
    private int index = 0;
//    private String buffLati = "";
//    private String buffLong = "";
//    private String buffSpeed = "";
//    private String buffTime = "";
    private String buffKeys = "";
    private String buffValues = "";
//    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

//    private NotificationManager notificationManager;

    public TService(){
        Log.i("初始化","哈哈哈");
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
                if (index < 10) {
//                    Log.i("蓄力中",""+index);

                    String lat = ""+aMapLocation.getLatitude();
                    String lo = ""+aMapLocation.getLongitude();
                    String spd = ""+aMapLocation.getSpeed();
                    buffKeys = "18969899383"+"0"+"20160227124621";//Tools.getNowDateString("YYYYMMddHHmmss")
                    Log.i("keys==========>",""+buffKeys);
                    buffValues = lat+"|"+lo+"|"+spd;

                    JSONObject jsb = new JSONObject();
                    try {
                        jsb.put(buffKeys,buffValues);
                        Log.i("数据", jsb.toString() + "");
                        jsa.add(jsb.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    Log.i("序列号", index + ":"+jsb.toString());
                    index++;
                }else {
                    buffKeys = "18969899383"+"0"+"20160227124621";
                    buffValues = aMapLocation.getLatitude()+"|"+aMapLocation.getLongitude()+"|"+aMapLocation.getSpeed();
                    JSONObject js = new JSONObject();
                    try {
                        js.put(buffKeys,buffValues);
                        jsa.add(js.toString());
                        Log.i("数据", js.toString() + "");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    JSONObject jsb = new JSONObject();
                    try {
                        jsb.put(AllConts.KEYTYPE,AllConts.TYPESENDDAN);
                        jsb.put(AllConts.KEYCODE,Tools.calCC(jsa.toString()));
                        jsb.put(AllConts.KEYCONTENT,jsa.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    Log.i("数据处理完毕",""+jsb.toString());
                    MinaClientHandler.localBuff = jsb.toString();//new String(Tools.encrypt(jsb.toString().getBytes(),keyPws));

                    index = 0;
                    buffKeys = "";
                    buffValues = "";
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
//        Intent intent = new Intent(this, testService.class);
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
