package com.qq.servlet.demo.netty.sample.thrift.service;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import com.qq.servlet.demo.netty.sample.thrift.protocol.IdGenReq;

public class ThriftProtocolServerHandler extends ChannelHandlerAdapter{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)throws Exception {
		System.out.println(msg.getClass().getName()+"\t"+msg);
		IdGenReq req = new IdGenReq();
		req.setTableName("fuwuduan-t_lottery_append_taskf-fuwu");
		ctx.write(req);
		ctx.flush();
	}
}
