package model;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

/**
 * Created by matioyoshitoki on 2017/11/27.
 */
@TableBind(tableName = "gps_receive")
public class Gps_receive  extends Model<Gps_receive>{
    public static final Gps_receive dao = new Gps_receive();
}
