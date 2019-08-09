package tools;

import java.io.IOException;

/**
 * Created by matioyoshitoki on 2017/9/18.
 */
public class OpenSSLTools {
    public static void openssl(String file_name,String out_file_name){

        try {
            Runtime.getRuntime().exec("openssl enc -des3 -in "+file_name+" -out "+out_file_name+" -pass pass:"+file_name);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
