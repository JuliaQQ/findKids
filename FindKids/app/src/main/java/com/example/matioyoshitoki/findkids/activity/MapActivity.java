package com.example.matioyoshitoki.findkids.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.matioyoshitoki.findkids.R;
import com.example.matioyoshitoki.findkids.Tools.Dialog_loading;
import com.example.matioyoshitoki.findkids.Tools.Tools;
import com.example.matioyoshitoki.findkids.constraints.Keys;
import com.example.matioyoshitoki.findkids.http.Logout_Thread;

/**
 * Created by matioyoshitoki on 2017/10/24.
 */
public class MapActivity extends AppCompatActivity {

    Button logout_btn ;
    private SharedPreferences sPref ;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);
        Tools.translucentStatusBar(this, true);

        sPref = PreferenceManager.getDefaultSharedPreferences(this);

        logout_btn = (Button)findViewById(R.id.logout_btn);
        logout_btn.setOnClickListener(logout_listener);


    }

    View.OnClickListener logout_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Logout_Thread logout_thread = new Logout_Thread(sPref.getString(Keys.SESSIONID,""),mHandler);
            logout_thread.start();
            dialog = Dialog_loading.createLoadingDialog(MapActivity.this, "登出中...");
        }
    };

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    dialog.cancel();
                    sPref.edit().remove(Keys.SESSIONID).commit();
                    Intent intent = new Intent(MapActivity.this,LoginActivity.class);
                    startActivity(intent);
                    MapActivity.this.finish();
                    break;
                case 2:
                    dialog.cancel();
                    Toast toast = Toast.makeText(MapActivity.this, (String)msg.obj, Toast.LENGTH_SHORT);
                    toast.show();
                    break;
                default:
                    break;
            }
        }

    };

}
