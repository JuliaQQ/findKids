package com.example.matioyoshitoki.findkids.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.matioyoshitoki.findkids.R;

import tools.Tools;

/**
 * Created by matioyoshitoki on 2017/9/28.
 */
public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        Tools.translucentStatusBar(this,true);
    }
}
