package com.qq.servlet.demo.servlet;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import javax.servlet.AsyncContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebListener;

@WebListener
public class Work implements ServletContextListener {
	
	private static final BlockingQueue<AsyncContext> readQueue = new LinkedBlockingQueue<AsyncContext>();
	private static final BlockingQueue<AsyncContext> writeQueue = new PriorityBlockingQueue<AsyncContext>(500);
	
	private volatile Thread thread;

	public static void addWrite(AsyncContext c) {
		writeQueue.add(c);
	}

	public static void addRead(AsyncContext c) {
		readQueue.add(c);
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						AsyncContext context;
						while ((context = readQueue.poll()) != null) {
							Thread.sleep(20000);
							try {
								ServletResponse response = context
										.getResponse();
								response.setContentType("text/plain");
								PrintWriter out = response.getWriter();
								out.printf("Thread %s completed the task",
										Thread.currentThread().getName());
								out.flush();
								System.out.println(context.getRequest() + " "
										+ System.currentTimeMillis());
							} catch (Exception e) {
								throw new RuntimeException(e.getMessage(), e);
							} finally {
								context.complete();
							}
						}
					} catch (InterruptedException e) {
						return;
					}
				}
			}
		});
		thread.start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		thread.interrupt();
	}
}