package com.example.matioyoshitoki.findkids.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.matioyoshitoki.findkids.R;
import com.example.matioyoshitoki.findkids.service.TService;
import com.example.matioyoshitoki.findkids.activity.LoginActivity;

import java.lang.reflect.Field;

/**
 * Created by matioyoshitoki on 17/2/22.
 */
public class FloatWindowSmallView extends LinearLayout {

    public static int viewWidth;
    public static int viewHeight;
    private static int statusBarHeight;
    private WindowManager windowManager;
    private WindowManager.LayoutParams mParams;
    private float xInScreen;
    private float yInScreen;
    private float xDownInScreen;
    private float yDownInScreen;
    private float xInView;
    private float yInView;

    @SuppressLint("NewApi")
    public FloatWindowSmallView(Context context) {
        super(context);
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        LayoutInflater.from(context).inflate(R.layout.float_window_small, this);
        ImageView view = (ImageView)findViewById(R.id.small_window_layout);
        view.setBackground(getResources().getDrawable(R.drawable.lwz,null));
        viewWidth = view.getLayoutParams().width;
        viewHeight = view.getLayoutParams().height;

    }


//    @Override
//    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
//        Intent i = new Intent(LoginActivity.ctx,TService.class);
//        LoginActivity.ctx.startService(i);
//        return super.onKeyLongPress(keyCode, event);
//    }
    long downTime = 0;
    long upTime = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 手指按下时记录必要数据,纵坐标的值都需要减去状态栏高度
                xInView = event.getX();
                yInView = event.getY();
                xDownInScreen = event.getRawX();
                yDownInScreen = event.getRawY() - getStatusBarHeight();
                xInScreen = event.getRawX();
                yInScreen = event.getRawY() - getStatusBarHeight();
                downTime = event.getDownTime();
                break;
            case MotionEvent.ACTION_MOVE:
                xInScreen = event.getRawX();
                yInScreen = event.getRawY() - getStatusBarHeight();
                // 手指移动的时候更新小悬浮窗的位置
                updateViewPosition();
                break;
            case MotionEvent.ACTION_UP:
                // 如果手指离开屏幕时，xDownInScreen和xInScreen相等，且yDownInScreen和yInScreen相等，则视为触发了单击事件。
                upTime = event.getEventTime();
                Log.i("时间变化",""+downTime+":"+upTime);
                if (upTime-downTime > 1000){
                    Intent i = new Intent(LoginActivity.ctx,TService.class);
                    LoginActivity.ctx.startService(i);
                    downTime = 0;
                    upTime = 0;
                }

                break;
            case MotionEvent.ACTION_BUTTON_PRESS:
                long time = event.getEventTime();
                if (time>1000){
                    Intent i = new Intent(LoginActivity.ctx,TService.class);
                    LoginActivity.ctx.startService(i);
                }else {
                    Log.i("时间不够",""+time);
                }
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * 将小悬浮窗的参数传入，用于更新小悬浮窗的位置。
     *
     * @param params 小悬浮窗的参数
     */
    public void setParams(WindowManager.LayoutParams params) {
        mParams = params;
    }

    /**
     * 更新小悬浮窗在屏幕中的位置。
     */
    private void updateViewPosition() {
        mParams.x = (int) (xInScreen - xInView);
        mParams.y = (int) (yInScreen - yInView);
        windowManager.updateViewLayout(this, mParams);
    }

    /**
     * 打开大悬浮窗，同时关闭小悬浮窗。
     */
    private void openBigWindow() {
        MyWindowManager.createBigWindow(getContext());
        MyWindowManager.removeSmallWindow(getContext());
    }

    /**
     * 用于获取状态栏的高度。
     *
     * @return 返回状态栏高度的像素值。
     */
    private int getStatusBarHeight() {
        if (statusBarHeight == 0) {
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object o = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = (Integer) field.get(o);
                statusBarHeight = getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusBarHeight;
    }
}