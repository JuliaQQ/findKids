package com.example.matioyoshitoki.findkids;

import android.util.Log;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * Created by matioyoshitoki on 17/2/23.
 */
public class MinaClientHandler extends IoHandlerAdapter {

    public static String localBuff = "";

    // 当客户端连接进入时
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        while (true){
//            System.out.println("incomming 客户端: " + session.getRemoteAddress());

            if (!localBuff.isEmpty()){
                Log.i("发送数据",""+localBuff);
                session.write(localBuff);
                localBuff = "";
            }else{
                Log.i("发送数据","TEST BOOM BOOM BOOM");
                session.write("TEST BOOM BOOM BOOM");
            }
            Thread.sleep(10*1000);
        }

    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        System.out.println("客户端发送信息异常....");
    }

    // 当客户端发送消息到达时
    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {

        System.out.println("服务器返回的数据：" + message.toString());
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("客户端与服务端断开连接.....");
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        // TODO Auto-generated method stub
        System.out
                .println("one Client Connection" + session.getRemoteAddress());
        session.write("我来了······");
    }

}