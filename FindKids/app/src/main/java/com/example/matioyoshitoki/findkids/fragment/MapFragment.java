package com.example.matioyoshitoki.findkids.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.PolylineOptions;
import com.example.matioyoshitoki.findkids.R;
import com.example.matioyoshitoki.findkids.Tools.All_Handle;
import com.example.matioyoshitoki.findkids.constraints.Keys;



/**
 * Created by matioyoshitoki on 2017/11/7.
 */
@SuppressLint("ValidFragment")
public class MapFragment extends Fragment {
    private String context;
    private MapView mapView;

    public  MapFragment(String context){
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment,container,false);
        mapView = (MapView)view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        AMap aMap = mapView.getMap();
        All_Handle.send_location = mHandler;
        aMap.setTrafficEnabled(true);// 显示实时交通状况
        //地图模式可选类型：MAP_TYPE_NORMAL,MAP_TYPE_SATELLITE,MAP_TYPE_NIGHT
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);// 卫星地图模式
        //mTextView = (TextView)getActivity().findViewById(R.id.map_content);
//        mTextView.setText(context);
        return view;
    }

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    AMap aMap = mapView.getMap();
                    JSONObject res = (JSONObject)msg.obj;
                    JSONArray data = res.getJSONArray(Keys.DATA);
                    PolylineOptions polylineOptions = new PolylineOptions();
                    for (int i=0;i<data.size();i++){
                        polylineOptions.add(new LatLng(Float.valueOf(data.getJSONObject(i).getString(Keys.LAT)),Float.valueOf(data.getJSONObject(i).getString(Keys.LONG))));
                    }
                    polylineOptions.geodesic(true).color(Color.RED);
                    aMap.addPolyline(polylineOptions);
                    break;
                case 1:

                    break;
                case 2:

                    break;
                default:
                    break;
            }
        }

    };

}


