package com.qq.servlet.demo.netty.sample.thrift.service;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;

import java.util.Map;

import com.qq.servlet.demo.netty.sample.thrift.coder.ThriftProtocolDecoder;
import com.qq.servlet.demo.netty.sample.thrift.coder.ThriftProtocolEncoder;

public class ThriftProtocolServerChannelHandler extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		Map<ChannelOption<?>, Object> options = ch.config().getOptions();
		System.out.println(options);
		ch.pipeline().addLast("decoder", new ThriftProtocolDecoder());
		ch.pipeline().addLast("encoder", new ThriftProtocolEncoder());
		ch.pipeline().addLast("handler", new ThriftProtocolServerHandler());
	}

}
