package com.qq.servlet.demo.netty.sample.myprotocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import org.apache.thrift.TBase;

import com.qq.servlet.demo.netty.sample.myprotocol.bo.ProtocolTools;

public class MyProtocolDecoder extends LengthFieldBasedFrameDecoder {

	public MyProtocolDecoder() {
        super(10485760, 0, 4, 0, 4);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected TBase decode(ChannelHandlerContext ctx, ByteBuf in)throws Exception {
        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        if (frame == null) {
            return null;
        }
        
        int bytes = frame.readableBytes();
        byte[] bf = new byte[bytes];
        frame.markReaderIndex();
        frame.readBytes(bf);
        
        //读取类名包含的长度
        byte[] cl = new byte[4];
        System.arraycopy(bf, 0, cl, 0, cl.length);
        
        //根据类名的长度，读取类的名称
        byte[] cn = new byte[byte2Length(cl)];
        System.arraycopy(bf, cl.length, cn, 0, cn.length);
        String className = new String(cn);
        
        //在整个数据中，出去类名长度和类名成，剩下的即为类的实体内容
        byte[] body = new byte[bf.length-cl.length-cn.length];
        System.arraycopy(bf, cl.length+cn.length, body, 0, body.length);
        
        Class<? extends TBase> name = (Class<? extends TBase>) Class.forName(className);
        
        TBase newInstance = name.newInstance();
        ProtocolTools.unSerialize(body, newInstance);
        
		return newInstance;
	}
	
	
	/**
	 * 根据四个字节，转为int表示
	 * @param bb
	 * @return
	 */
	private int byte2Length(byte[] bb) {
		int length = ((bb[0] & 0xFF) << 24) | ((bb[1] & 0xFF) << 16)
				| ((bb[2] & 0xFF) << 8) | (bb[3] & 0xFF);
		return length;
	}
	
	/**
	 * 将网络字节序的数据流转换成一int型数值(从偏移位置开始转换4个字节)
	 * 
	 * @param bytes:
	 *            相应的字节流
	 * @param offset:字节流中开始转换的偏移位置
	 * @return 返回一个整型数值
	 */
	public static int ntohl(byte[] bytes, int offset) {
		int length1 = ((((int) bytes[offset + 0]) << 24) & 0xff000000);
		int length2 = ((((int) bytes[offset + 1]) << 16) & 0x00ff0000);
		int length3 = ((((int) bytes[offset + 2]) << 8) & 0x0000ff00);
		int length4 = ((((int) bytes[offset + 3]) << 0) & 0x000000ff);

		return length1 + length2 + length3 + length4;
	}

	/**
	 * 将一个整型数值转换成网络字节序的字节流(4 个字节长)
	 * 
	 * @param value:待转换的整型数值
	 * @return 返回转换所网络字节序的字节流
	 */
	public static byte[] htonl(int value) {
		byte[] bytes = new byte[4];

		bytes[3] = (byte) (value & 0x000000ff);
		bytes[2] = (byte) ((value & 0x0000ff00) >> 8);
		bytes[1] = (byte) ((value & 0x00ff0000) >> 16);
		bytes[0] = (byte) ((value & 0xff000000) >> 24);

		return bytes;
	}
	
}

