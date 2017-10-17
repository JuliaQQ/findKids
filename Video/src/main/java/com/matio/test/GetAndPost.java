package com.matio.test;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by matioyoshitoki on 2017/10/10.
 */
public class GetAndPost {

    public static void main(String[]args){
        ArrayList<String> sss = new ArrayList<>();
        sss.add("phone_num=18969899383");
        sss.add("user_pwd=123456");
        sss.add("vcode=1234");
        System.out.println(GetAndPost.executeHttpPost("http://47.95.115.33:8080/reg",sss));
    }

    public static String executeHttpGet(String url_str,ArrayList<String> ps) {
        String result = null;
        URL url = null;
        HttpURLConnection connection = null;
        InputStreamReader in = null;
        try {
            if (ps.size()>0) {
                url_str += "?" + ps.get(0)+"&";
                if (ps.size()>1) {
                    for (int i = 1; i < ps.size(); i++) {
                        url_str += ps.get(i)+"&";
                    }
                }
            }
            url_str = url_str.substring(0,url_str.length()-1);
            url = new URL(url_str);
            connection = (HttpURLConnection) url.openConnection();
            in = new InputStreamReader(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(in);
            StringBuffer strBuffer = new StringBuffer();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                strBuffer.append(line);
            }
            result = strBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return result;
    }

    public static String executeHttpPost(String url_str,ArrayList<String> ps) {
        String result = null;
        URL url = null;
        HttpURLConnection connection = null;
        InputStreamReader in = null;
        try {
            url = new URL(url_str);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Charset", "utf-8");
            DataOutputStream dop = new DataOutputStream(connection.getOutputStream());
            String buff = "?";
            for (int i=0;i<ps.size()-1;i++){
                dop.writeBytes(ps.get(i)+"&");
            }
            dop.writeBytes(ps.get(ps.size() - 1));
            dop.flush();
            dop.close();

            System.out.println(connection.getResponseCode());
            if(connection.getResponseCode() == 200){

                in = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(in);
                StringBuffer strBuffer = new StringBuffer();
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    strBuffer.append(line);
                }
                result = strBuffer.toString();

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return result;
    }

}
