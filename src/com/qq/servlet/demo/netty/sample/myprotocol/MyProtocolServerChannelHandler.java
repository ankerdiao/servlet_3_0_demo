package com.qq.servlet.demo.netty.sample.myprotocol;

import java.util.Map;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;

public class MyProtocolServerChannelHandler extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		Map<ChannelOption<?>, Object> options = ch.config().getOptions();
		System.out.println(options);
		ch.pipeline().addLast("decoder", new MyProtocolDecoder());
		ch.pipeline().addLast("encoder", new MyprotocolEncoder());
		ch.pipeline().addLast("handler", new MyProtocolServerHandler());
	}

}
