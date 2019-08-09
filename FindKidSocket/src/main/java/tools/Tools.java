package tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class Tools {
	//获取指定格式的当前日期字符串
	public static String getNowDateString(){
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");
		return sdf.format(now);
	}
	
	//获取指定格式的当前日期字符串
		public static String getNowDateString(String format){
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(now);
		}
	
	//获取指定格式的当前日期字符串
		@SuppressWarnings("static-access")
	public static String getEndDateString(){
		
			Date now = new Date();
		    Calendar calendar = new GregorianCalendar(); 
		    calendar.setTime(now); 
		    calendar.add(calendar.DATE,15);//把日期往后增加一天.整数往后推,负数往前移动 
		    now=calendar.getTime();   //这个时间就是日期往后推一天的结果 
			SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");
			return sdf.format(now);
	}
	@SuppressWarnings("static-access")
	public static String getEndDateString(String format,int day){
			
			Date now = new Date();
		    Calendar calendar = new GregorianCalendar(); 
		    calendar.setTime(now); 
		    calendar.add(calendar.DATE,day);//把日期往后增加一天.整数往后推,负数往前移动 
		    now=calendar.getTime();   //这个时间就是日期往后推一天的结果 
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(now);
	}

	//自动补齐0
	public static void addZero(String target,int length){
		while (target.length() < length){
			target = "0"+target;
		}
	}

	//生成随机数字和字母,  
    protected static String getStringRandom(int length) {  
          
        String val = "";  
        Random random = new Random();  
          
        //参数length，表示生成几位随机数  
        for(int i = 0; i < length; i++) {  
              
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
    }


    
  //生成随机数字  
    protected static String getRandom(int length) {  
          
        String val = "";  
        Random random = new Random();  
          
        //参数length，表示生成几位随机数  
        for(int i = 0; i < length; i++) {  
 
            val += String.valueOf(random.nextInt(10));  
        }
 
        return val;  
    }


    
	 public final static String MD5(String s) {
	        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       

	        try {
	            byte[] btInput = s.getBytes();
	            // 获得MD5摘要算法的 MessageDigest 对象
	            MessageDigest mdInst = MessageDigest.getInstance("MD5");
	            // 使用指定的字节更新摘要
	            mdInst.update(btInput);
	            // 获得密文
	            byte[] md = mdInst.digest();
	            // 把密文转换成十六进制的字符串形式
	            int j = md.length;
	            char str[] = new char[j * 2];
	            int k = 0;
	            for (int i = 0; i < j; i++) {
	                byte byte0 = md[i];
	                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
	                str[k++] = hexDigits[byte0 & 0xf];
	            }
	            return new String(str);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	 }
	 
	 

}
