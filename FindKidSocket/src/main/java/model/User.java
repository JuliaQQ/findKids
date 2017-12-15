package model;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

/**
 * Created by matioyoshitoki on 2017/9/18.
 */
@TableBind(tableName = "user", pkName="id")
public class User extends Model<User>{
    public static final User dao = new User();
}
