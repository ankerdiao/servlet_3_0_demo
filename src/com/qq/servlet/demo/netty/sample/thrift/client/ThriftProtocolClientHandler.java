package com.qq.servlet.demo.netty.sample.thrift.client;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import com.qq.servlet.demo.netty.sample.thrift.protocol.IdGenReq;

public class ThriftProtocolClientHandler extends ChannelHandlerAdapter{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)throws Exception {
		System.out.println(msg.getClass().getName()+"\t"+msg);
		ctx.close();
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		IdGenReq req = new IdGenReq();
		req.setTableName("t_lottery_append_task");
		ctx.writeAndFlush(req);
		ctx.flush();
	}
}
