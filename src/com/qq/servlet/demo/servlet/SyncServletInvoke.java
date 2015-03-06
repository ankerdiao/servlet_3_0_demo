package com.qq.servlet.demo.servlet;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qq.servlet.demo.netty.telnet.TelnetClientInitializer;

/**
 * Servlet implementation class AsyncServletInvoke
 */
@WebServlet(urlPatterns={"/syncServletInvoke"})
public class SyncServletInvoke extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
       
	private EventLoopGroup group = new NioEventLoopGroup(1);
	private Bootstrap bootstrap = new Bootstrap();



	public void init(ServletConfig config) throws ServletException {
        bootstrap.group(group);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(new TelnetClientInitializer());
	}


	public void destroy() {
		Future<?> future = group.shutdownGracefully();
		try {
			future.await(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	int allcount=0;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		allcount++;
		System.out.println("------------service["+allcount+"]-------------");
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("进入Servlet的时间：" + new Date() + ".<br/>");
        out.flush();
        
        request.setAttribute("beginTime", System.currentTimeMillis());
        
//        try {
//			Channel channel = bootstrap.connect("localhost", 8081).sync().channel();
//			channel.writeAndFlush("ddddddddddddddddddddddddddddddddddddddd");
//			
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		Socket socket = new Socket();
		SocketAddress endpoint = new InetSocketAddress("127.0.0.1", 8081);
		socket.connect(endpoint);
		socket.setSoTimeout(30000);
		socket.getOutputStream().write("ddddddddddddddddddddddddddddddddddddddd\n".getBytes());
		socket.getOutputStream().flush();
		byte[] b=new byte[1024];
		int read = socket.getInputStream().read(b);
		byte[] d=new byte[read];
		System.arraycopy(b, 0, d, 0, read);
		socket.close();
		
        out.println("结束Servlet的时间：" + new Date() + ".<br/>"+new String(d));
        out.flush();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

}
