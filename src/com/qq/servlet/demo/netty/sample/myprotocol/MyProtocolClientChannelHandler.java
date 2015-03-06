package com.qq.servlet.demo.netty.sample.myprotocol;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

import com.qq.servlet.demo.netty.sample.objectecho.MyProtocolClientHandler;

public class MyProtocolClientChannelHandler extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast("encoder", new MyprotocolEncoder());
		ch.pipeline().addLast("decoder", new MyProtocolDecoder());
		ch.pipeline().addLast("handler", new MyProtocolClientHandler());
	}

	

}
