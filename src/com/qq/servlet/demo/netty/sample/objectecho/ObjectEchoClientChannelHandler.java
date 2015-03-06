package com.qq.servlet.demo.netty.sample.objectecho;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class ObjectEchoClientChannelHandler extends ChannelInitializer<SocketChannel>{

	@Override
	public void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast("encoder",new ObjectEncoder());
		pipeline.addLast("decoder",new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
		pipeline.addLast("serverHandler",new ObjectEchoClientHandler());
	}
}
