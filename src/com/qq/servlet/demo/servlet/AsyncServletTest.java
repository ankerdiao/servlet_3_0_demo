package com.qq.servlet.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(asyncSupported=true, value = "/AsyncServletTest", loadOnStartup=4)
public class AsyncServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AsyncListener listener = new AsyncListenerTest();
	ThreadPoolExecutor pool = new ThreadPoolExecutor(150, 300, 3, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1000));
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
//		System.out.println("新的请求进来了\t"+new Date()+"\t"+req);
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println("进入Servlet的时间：" + new Date() + ".");
		out.flush();

		// 在子线程中执行业务调用，并由其负责输出响应，主线程退出
		AsyncContext ctx = req.startAsync();
		ctx.setTimeout(60000);

		ctx.addListener(listener);
		pool.execute(new RunCtx(ctx));
		

		out.println("结束Servlet的时间：" + new Date() + ".");
		out.flush();
	}
}



class RunCtx implements Runnable{

	private AsyncContext ctx;

	public RunCtx(AsyncContext ctx) {
		this.ctx = ctx;
	}

	@Override
	public void run() {
		try {
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
			
//			HttpServletRequest request = (HttpServletRequest) ctx.getRequest();
			HttpServletResponse response = (HttpServletResponse) ctx.getResponse();
			
			response.getWriter().write(new String(d));
//			response.getOutputStream().flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ctx.complete();
		}
	}
	
}



class AsyncListenerTest implements AsyncListener{

	@Override
	public void onComplete(AsyncEvent arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(AsyncEvent ev) throws IOException {
		try {
			ev.getAsyncContext().complete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onStartAsync(AsyncEvent arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTimeout(AsyncEvent arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
}


class Executor implements Runnable {
	private AsyncContext ctx = null;

	public Executor(AsyncContext ctx) {
		this.ctx = ctx;
	}

	public void run() {
		try {
			System.out.println("业务线程处理"+Thread.currentThread().getName());
			// 等待十秒钟，以模拟业务方法的执行
			Thread.sleep(60000);
			PrintWriter out = ctx.getResponse().getWriter();
			out.println("业务处理完毕的时间：" + new Date() + ".");
			out.flush();
			ctx.complete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}