package com.qq.servlet.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AsyncServlet
 */
@WebServlet(description = "async servlet", urlPatterns = { "/AsyncServlet" }, asyncSupported=true)
public class AsyncServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsyncServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("进入Servlet的时间：" + new Date() + ".<br/>");
        out.flush();

        //在子线程中执行业务调用，并由其负责输出响应，主线程退出
        AsyncContext ctx = request.startAsync();//点火就走的原则
        System.out.println(ctx.getTimeout());
        AsyncListener asyncLitener = new AsyncDispatcherServletListener();
		ctx.addListener(asyncLitener);
        new Thread(new Executor(ctx)).start();

        out.println("结束Servlet的时间：" + new Date() + ".<br/>");
        out.flush();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service(request, response);
	}

	
	
	public class Executor implements Runnable {
	    private AsyncContext ctx = null;
	    public Executor(AsyncContext ctx){
	        this.ctx = ctx;
	    }

	    public void run(){
	        try {
	        	PrintWriter out = ctx.getResponse().getWriter();
	        	for (int i = 0; i < 5; i++) {	
	        		Thread.sleep(1000);
	        		out.println("正在处理业务【"+i+"】<br/>");
	        		out.flush();
				}
	            //等待十秒钟，以模拟业务方法的执行
	            Thread.sleep(3000);
	            out.println("业务处理完毕的时间：" + new Date() + ".");
	            out.flush();
	            ctx.complete();//最终必须调用这个函数告知http请求结束
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	class AsyncDispatcherServletListener implements AsyncListener {

        @Override
        public void onTimeout(AsyncEvent event) throws IOException {
        	System.out.println("Async request did not complete timeout occured");
            handleTimeoutOrError(event, "Request timed out");
        }

        @Override
        public void onComplete(AsyncEvent event) throws IOException {
        	System.out.println("Completed async request");
        }

        @Override
        public void onError(AsyncEvent event) throws IOException {
        	System.out.println("Error in async request"+event.getThrowable());
            handleTimeoutOrError(event, "Error processing " + event.getThrowable().getMessage());
        }

        @Override
        public void onStartAsync(AsyncEvent event) throws IOException {
        	System.out.println("Async Event started..");
        }

        private void handleTimeoutOrError(AsyncEvent event, String message) {
            PrintWriter writer = null;
            try {
                HttpServletResponse response = (HttpServletResponse) event.getAsyncContext().getResponse();
                //HttpServletRequest request = (HttpServletRequest) event.getAsyncContext().getRequest();
                //request.getRequestDispatcher("/app/error.htm").forward(request, response);
                writer = response.getWriter();
                writer.print(message);
                writer.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                event.getAsyncContext().complete();
                if (writer != null) {
                    writer.close();
                }
            }
        }
    }
	
}
