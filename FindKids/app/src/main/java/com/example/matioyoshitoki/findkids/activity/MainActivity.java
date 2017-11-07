package com.example.matioyoshitoki.findkids.activity;

import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.matioyoshitoki.findkids.R;
import com.example.matioyoshitoki.findkids.Tools.Dialog_loading;
import com.example.matioyoshitoki.findkids.Tools.Tools;
import com.example.matioyoshitoki.findkids.constraints.Keys;
import com.example.matioyoshitoki.findkids.fragment.MapFragment;
import com.example.matioyoshitoki.findkids.http.Logout_Thread;

/**
 * Created by matioyoshitoki on 2017/10/24.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView topBar;
    private TextView tabDeal;

    private FrameLayout ly_content;

    private MapFragment f1,f2;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_activity);
        Tools.translucentStatusBar(this, true);

        bindView();

    }

    //UI组件初始化与事件绑定
    private void bindView() {
        topBar = (TextView)this.findViewById(R.id.map);
        tabDeal = (TextView)this.findViewById(R.id.personal);
        ly_content = (FrameLayout) findViewById(R.id.fragment_container);

        tabDeal.setOnClickListener(this);
        topBar.setOnClickListener(this);

    }

    //重置所有文本的选中状态
    public void selected(){
        tabDeal.setSelected(false);
        topBar.setSelected(false);
    }

    //隐藏所有Fragment
    public void hideAllFragment(FragmentTransaction transaction){
        if(f1!=null){
            transaction.hide(f1);
        }
        if(f2!=null){
            transaction.hide(f2);
        }
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch(v.getId()){
            case R.id.map:
                selected();
                Log.i("按了","map");
                tabDeal.setSelected(true);
                if(f1==null){
                    f1 = new MapFragment("第一个Fragment");
                    transaction.add(R.id.fragment_container,f1);
//                    transaction.replace(R.id.main_back,f1);
                }else{
                    transaction.show(f1);
//                    transaction.replace(R.id.main_back,f1);
                }
                break;

            case R.id.personal:
                Log.i("按了","personal");
                selected();
                topBar.setSelected(true);
                if(f2==null){
                    f2 = new MapFragment("第二个Fragment");
                    transaction.add(R.id.fragment_container,f2);
//                    transaction.show(f2);
//                    transaction.replace(R.id.main_back, f2);
//
                }else{
//                    transaction.replace(R.id.main_back,f2);
                    transaction.show(f2);
                }
                break;
        }

        transaction.commit();
    }
}
