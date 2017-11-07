package com.example.matioyoshitoki.findkids.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.matioyoshitoki.findkids.R;

/**
 * Created by matioyoshitoki on 2017/11/7.
 */
@SuppressLint("ValidFragment")
public class MapFragment extends Fragment {
    private String context;
    private TextView mTextView;

    public  MapFragment(String context){
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment,container,false);
        mTextView = (TextView)view.findViewById(R.id.map_content);
        //mTextView = (TextView)getActivity().findViewById(R.id.map_content);
        mTextView.setText(context);
        return view;
    }
}


