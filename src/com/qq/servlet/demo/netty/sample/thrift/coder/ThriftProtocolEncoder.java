package com.qq.servlet.demo.netty.sample.thrift.coder;

import org.apache.thrift.TBase;

import com.qq.servlet.demo.netty.sample.myprotocol.bo.ProtocolTools;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

@SuppressWarnings("rawtypes")
public class ThriftProtocolEncoder extends MessageToByteEncoder<TBase>{

	private static final byte[] LENGTH_PLACEHOLDER = new byte[4];
	
	@Override
	protected void encode(ChannelHandlerContext ctx, TBase msg, ByteBuf out)throws Exception {

	    int startIdx = out.writerIndex();
        
        out.writeBytes(LENGTH_PLACEHOLDER);
        
        //写类名的长度和类名的具体内容
        String className = msg.getClass().getName();
        byte[] bs = className.getBytes();
        //添加类名长度，用四个字节表示
        out.writeBytes(LENGTH_PLACEHOLDER);
        //添加类名的内容
        out.writeBytes(bs);
        //覆盖写如类名长度
        out.setInt(startIdx+4, bs.length);
        
        //添加对象body的内容
        byte[] bytes = ProtocolTools.serialize(msg);
        out.writeBytes(bytes);
        
        int endIdx = out.writerIndex();

        out.setInt(startIdx, endIdx - startIdx - 4);
	}


}
