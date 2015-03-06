package com.qq.servlet.demo.thrift.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.Arrays;

public class NTMessageToByteEncoder extends MessageToByteEncoder<byte[]> {

	@Override
	protected void encode(ChannelHandlerContext ctx, byte[] msg, ByteBuf out) throws Exception {
		System.out.println(Arrays.toString(msg));
		out.writeBytes(msg);
		ctx.flush();
	}

}
