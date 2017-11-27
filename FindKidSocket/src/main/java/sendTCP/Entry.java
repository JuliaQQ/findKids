package sendTCP;

import config.Config;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class Entry {

//	 
	 public static void main(String[] args) {
//		 try {
//			 new Config().initAll();
//		 } catch (IOException e) {
//			 e.printStackTrace();
//		 }
		 IoAcceptor acceptor=new NioSocketAcceptor();
		 acceptor.getSessionConfig().setReadBufferSize(16);
		 acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,10);//读写通道在10秒内无任何操作进入空闲状态
		 acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),LineDelimiter.WINDOWS.getValue(),LineDelimiter.WINDOWS.getValue())));//以换行符为标识的数据
		//3.注册iohandler到ioservice
		acceptor.setHandler(new MyServerIoHandler());
		//4绑定套接字
		try {
			acceptor.bind(new InetSocketAddress(10000));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }


}
