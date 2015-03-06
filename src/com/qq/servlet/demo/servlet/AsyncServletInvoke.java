package com.qq.servlet.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.AsyncContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qq.servlet.demo.netty.service.NettyInvokeService;

/**
 * Servlet implementation class AsyncServletInvoke
 */
@WebServlet(urlPatterns={"/AsyncServletInvoke"}, asyncSupported=true)
public class AsyncServletInvoke extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	NettyInvokeService nettyInvokeService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsyncServletInvoke() {
        super();
        nettyInvokeService = new NettyInvokeService();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		nettyInvokeService.init();
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			nettyInvokeService.destory();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	int allcount=0;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		allcount++;
		System.out.println("------------service["+allcount+"]-------------");
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("进入Servlet的时间：" + new Date() + ".<br/>");
        out.flush();
        
        request.setAttribute("beginTime", System.currentTimeMillis());
        request.setAttribute("REQUEST_ID", ""+allcount);
        
        AsyncContext context = request.startAsync();//把同步转异步后的上下文
        context.setTimeout(1000*60);
        try {
			nettyInvokeService.invoke("localhost", 8081, context);
		} catch (InterruptedException e) {
			e.printStackTrace();
			out.println(e.getMessage());
			out.flush();
			context.complete();
		}
        
        out.println("结束Servlet的时间：" + new Date() + ".<br/>");
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
