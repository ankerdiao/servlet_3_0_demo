package com.qq.servlet.demo.netty.sample.thrift.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

import com.qq.servlet.demo.netty.sample.thrift.coder.ThriftProtocolDecoder;
import com.qq.servlet.demo.netty.sample.thrift.coder.ThriftProtocolEncoder;

public class ThriftProtocolClientChannelHandler extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast("encoder", new ThriftProtocolEncoder());
		ch.pipeline().addLast("decoder", new ThriftProtocolDecoder());
		ch.pipeline().addLast("handler", new ThriftProtocolClientHandler());
	}

	

}
