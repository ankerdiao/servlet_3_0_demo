package com.qq.servlet.demo.netty.sample.myprotocol;

import com.qq.servlet.demo.netty.sample.myprotocol.bo.IssueDo;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class MyProtocolServerHandler extends ChannelHandlerAdapter{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)throws Exception {
		System.out.println(msg.getClass().getName()+"\t"+msg);
		IssueDo issueDo = new IssueDo();
		issueDo.setExtInfo("ddddddddddddddddddddddddddddddddddddddddddddddddddddd");
		ctx.write(issueDo);
		ctx.flush();
	}
}
