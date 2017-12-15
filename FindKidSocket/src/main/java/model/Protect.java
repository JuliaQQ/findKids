package model;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

/**
 * Created by matioyoshitoki on 2017/11/28.
 */
@TableBind(tableName = "protect")
public class Protect extends Model<Protect> {
    public static final Protect dao = new Protect();
}
