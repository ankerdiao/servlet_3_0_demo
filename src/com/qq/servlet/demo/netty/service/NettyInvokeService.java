package com.qq.servlet.demo.netty.service;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;

import com.qq.servlet.demo.netty.telnet.TelnetClientInitializer;

public class NettyInvokeService {

	private EventLoopGroup group = new NioEventLoopGroup(20);
	private Bootstrap bootstrap = new Bootstrap();
	
	
	
	public void init() {
         bootstrap.group(group);
         bootstrap.channel(NioSocketChannel.class);
         bootstrap.handler(new TelnetClientInitializer());
	}
	
	public void invoke(final String host, final int port, final AsyncContext ctx) throws InterruptedException{
		Channel channel = bootstrap.connect(host, port).sync().channel();
		
		HttpServletRequest request = (HttpServletRequest) ctx.getRequest();
		AttributeKey<AsyncContext> key=AttributeKey.valueOf("ASYNC_CONTEXT");
		Attribute<AsyncContext> attr = channel.attr(key);
		attr.set(ctx);
		
		String msg = request.getQueryString();
		
		ChannelFuture lastWriteFuture = channel.writeAndFlush(msg+" is sent by servlet from tomcat \t\n");
		
		// Wait until all messages are flushed before closing the channel.
		if (lastWriteFuture != null) {
			lastWriteFuture.sync();
		}
	}
	
	public void destory() throws InterruptedException{
		Future<?> future = group.shutdownGracefully();
		future.await(20000);
	}
}
