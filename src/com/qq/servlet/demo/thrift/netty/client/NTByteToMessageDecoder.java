package com.qq.servlet.demo.thrift.netty.client;

import java.util.Arrays;
import java.util.List;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TMemoryBuffer;

import com.qq.servlet.demo.netty.sample.thrift.protocol.IdGenReq;
import com.qq.servlet.demo.netty.sample.thrift.protocol.IdGenResp;
import com.qq.servlet.demo.netty.sample.thrift.protocol.IdGenService;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class NTByteToMessageDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		super.setSingleDecode(true);
		
		int i = in.readableBytes();
		System.out.println(i);
		
		if(i<=0){
			return;
		}
		
		byte[] bb = new byte[i];
		in.readBytes(bb);
		
//		byte[] bs = in.array();
		System.out.println(Arrays.toString(bb));
		
		IdGenReq req=new IdGenReq();
		TMemoryBuffer tran = new TMemoryBuffer(1024);
		tran.write(bb);
		TProtocol pro = new TBinaryProtocol(tran);
		TMultiplexedProtocol multi = new TMultiplexedProtocol(pro, IdGenService.class.getSimpleName()); 
		IdGenService.Client cli = new IdGenService.Client(multi);
		cli.send_idGen(req);
		IdGenResp resp = cli.recv_idGen();
		System.out.println(resp);
		
		out.add(resp);
		
	}

}
