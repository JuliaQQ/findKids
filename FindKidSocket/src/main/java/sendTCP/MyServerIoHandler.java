package sendTCP;

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
//		int length = Integer.valueOf(msg.substring(0,4));
//		msg = msg.substring(4);
//		int code = Integer.valueOf(msg.substring(0,4));
//		User user = new User();
//		List<User> users = user.find("select * from user where id = ?",10);
//
//
//		switch (code){
//			case 4010:
//				String contract_id = msg.substring(0,50);
//				msg = msg.substring(50);
//				float contract_amount = Float.valueOf(msg.substring(0,12));
//				msg = msg.substring(12);
//				String identity_code = msg.substring(0,18);
//				msg = msg.substring(18);
//				String sig = msg.substring(0,1);
//				msg = msg.substring(1);
//				String remark = msg.substring(0,30);
//
//				break;
//			case 4011:
//				String file_name = msg.substring(0,30);
//				break;
//			default:break;
//		}
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
