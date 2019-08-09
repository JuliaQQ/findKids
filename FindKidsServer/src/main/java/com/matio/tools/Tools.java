package com.matio.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

/**
 * Created by matioyoshitoki on 2017/8/3.
 */
public class Tools {
    public static String getSomeDayBefore(String now,int days) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        calendar.setTime(df.parse(now));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - days);
        return df.format(calendar.getTime());
    }

    public static String getOneDayBefore(String now) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        calendar.setTime(df.parse(now));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
        return df.format(calendar.getTime());
    }

    public static String getOneDayLater(String now) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        calendar.setTime(df.parse(now));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
        return df.format(calendar.getTime());
    }

    /**
     * 生成sessionid
     * @author chenke
     * @return sessionid
     */
    public static String generateSessionId(int length){
        String uuid = UUID.randomUUID().toString();
        String sessionId = uuid.replace("-", "").toUpperCase();

        return sessionId.substring(0,length);
    }
}
