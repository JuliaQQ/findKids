package sendTCP;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import constraints.Errors;
import constraints.FindKids;
import constraints.Keys;
import model.Gps_tmp;
import model.Protect;
import model.User;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import org.apache.log4j.Logger;

import java.util.List;


public class MyServerIoHandler implements IoHandler {

	//	@SuppressWarnings("unused")
	private Logger logs= Logger.getLogger(getClass());

	public void exceptionCaught(IoSession arg0, Throwable arg1) throws Exception {
		// TODO Auto-generated method stub

	}

	public void inputClosed(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	public void messageReceived(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(message.toString());
		String msg = message.toString();
		JSONObject msg_json = JSONObject.parseObject(msg);
		String phone_num = msg_json.getString(Keys.PHONENUMBER);
		String type = msg_json.getString(Keys.TYPE);
		Gps_tmp gps_tmp = new Gps_tmp();
//
//
		switch (type){
			case FindKids.TYPEBREATH:
				Protect protect = new Protect();
				String sql = "select * from protect where protector=?";
				List<Protect> protects = protect.find(sql,phone_num);
				sql = "select * from gps_tmp where phone_num = ";
				for (Protect p:protects){
					sql += (p.getStr(Keys.BE_PROTECTED) +" AND phone_num=");
				}
				sql = sql.substring(0,(sql.length()-" AND phone_num=".length()));
				List<Gps_tmp> gps_tmps = gps_tmp.find(sql);
				JSONObject result = new JSONObject();
				JSONArray content = new JSONArray();
				for (Gps_tmp gps_tmp1:gps_tmps){

					JSONObject every_data = new JSONObject();
					JSONArray data ;
					boolean tmp = false;
					for (Object object:content){
						JSONObject jsonObject = (JSONObject)object;
						tmp |= gps_tmp1.getStr(Keys.PHONENUMBER).equals(jsonObject.getString(Keys.PHONENUMBER));
						if (gps_tmp1.getStr(Keys.PHONENUMBER).equals(jsonObject.getString(Keys.PHONENUMBER))){
							data = jsonObject.getJSONArray(Keys.DATA);
							JSONObject buff = new JSONObject();
							buff.put(Keys.LAT,gps_tmp1.getStr(Keys.LAT));
							buff.put(Keys.LONG,gps_tmp1.getStr("lo"));
							buff.put(Keys.SPEED,gps_tmp1.getStr(Keys.SPEED));
							buff.put(Keys.TIME,gps_tmp1.getStr(Keys.TIME));
							data.add(buff);
							jsonObject.replace(Keys.DATA,data);
						}
					}
					if (!tmp) {
						every_data.put(Keys.PHONENUMBER, gps_tmp1.getStr(Keys.PHONENUMBER));
						data = new JSONArray();
						JSONObject buff = new JSONObject();
						buff.put(Keys.LAT, gps_tmp1.getStr(Keys.LAT));
						buff.put(Keys.LONG, gps_tmp1.getStr("lo"));
						buff.put(Keys.SPEED, gps_tmp1.getStr(Keys.SPEED));
						buff.put(Keys.TIME, gps_tmp1.getStr(Keys.TIME));
						data.add(buff);
						every_data.put(Keys.DATA, data);
						content.add(every_data);
					}

				}
				result.put(Keys.DATA,content);
				result.put(Keys.TYPE,FindKids.TYPEBREATH);
				result.put(Keys.STATUS, Errors.SUCCESS);
				session.write(result.toJSONString());
				break;
			case FindKids.TYPESENDLOCATION:
				JSONArray data = msg_json.getJSONArray(Keys.DATA);
				String group_id = msg_json.getString(Keys.GROUP_ID);
				for (Object object:data){
					JSONObject jsonObject = (JSONObject)object;
					String lat = jsonObject.getString(Keys.LAT);
					String lo = jsonObject.getString(Keys.LONG);
					String speed = jsonObject.getString(Keys.SPEED);
					String time = jsonObject.getString(Keys.TIME);
					gps_tmp.set(Keys.GROUP_ID,group_id);
					gps_tmp.set(Keys.LAT,lat);
					gps_tmp.set(Keys.LONG,lo);
					gps_tmp.set(Keys.SPEED,speed);
					gps_tmp.set(Keys.TIME,time);
					gps_tmp.save();
				}
				break;
			default:
				break;
		}

////		User user = User.dao.findFirst("select * from user where id = ?",23055);
//		session.write(user.getStr("userName"));
		return ;
	}

	public void messageSent(IoSession arg0, Object arg1) throws Exception {
		// TODO Auto-generated method stub

	}

	public void sessionClosed(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	public void sessionCreated(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("连接成功"+arg0.getId());
	}

	public void sessionIdle(IoSession arg0, IdleStatus arg1) throws Exception {
		// TODO Auto-generated method stub

	}

	public void sessionOpened(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub

	}


}
