package model;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

/**
 * Created by matioyoshitoki on 2017/11/27.
 */
@TableBind(tableName = "gps_tmp", pkName="phone_num")
public class Gps_tmp extends Model<Gps_tmp>{
    public static final Gps_tmp dao = new Gps_tmp();
}
