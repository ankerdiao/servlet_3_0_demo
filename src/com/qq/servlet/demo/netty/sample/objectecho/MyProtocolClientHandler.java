package com.qq.servlet.demo.netty.sample.objectecho;

import com.qq.servlet.demo.netty.sample.myprotocol.bo.IssueDo;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class MyProtocolClientHandler extends ChannelHandlerAdapter{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)throws Exception {
		System.out.println(msg.getClass().getName()+"\t"+msg);
		ctx.close();
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		IssueDo issueDo = new IssueDo();
		
		issueDo.compoundEndTime=System.currentTimeMillis();
		issueDo.drawResult="99999999999999999999999999999999999999999999999999999999";
		
		ctx.writeAndFlush(issueDo);
		ctx.flush();
	}
}
