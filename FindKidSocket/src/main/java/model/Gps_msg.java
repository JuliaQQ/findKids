package model;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

/**
 * Created by matioyoshitoki on 2017/11/27.
 */
@TableBind(tableName = "Gps_msg", pkName="phone_num")
public class Gps_msg extends Model<Gps_msg>{
    public static final Gps_msg dao = new Gps_msg();
}
