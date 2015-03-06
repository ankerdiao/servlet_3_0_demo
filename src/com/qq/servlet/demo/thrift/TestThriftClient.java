package com.qq.servlet.demo.thrift;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Arrays;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMessage;
import org.apache.thrift.protocol.TMessageType;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TMemoryBuffer;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.qq.servlet.demo.netty.sample.myprotocol.bo.ProtocolTools;
import com.qq.servlet.demo.netty.sample.thrift.protocol.IdGenBatchReq;
import com.qq.servlet.demo.netty.sample.thrift.protocol.IdGenReq;
import com.qq.servlet.demo.netty.sample.thrift.protocol.IdGenResp;
import com.qq.servlet.demo.netty.sample.thrift.protocol.IdGenService;
import com.qq.servlet.demo.netty.sample.thrift.protocol.IdGenService.Processor.idGen;

public class TestThriftClient {


	
	public static void main(String[] args) throws TException {
		test();
		
//		byte[] bs=new byte[]{12, 0, 0, 10, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 2, 0, 0, 0, 0, 0, 0, 39, 15, 11, 0, 3, 0, 0, 0, 13, 97, 100, 102, 97, 115, 102, 97, 115, 100, 102, 97, 115, 100, 0, 0};
//		IdGenResp resp = new IdGenResp();
//		ProtocolTools.unSerialize(bs, resp);;
//		System.out.println(resp);
//		
//		IdGenResp resp1 = new IdGenResp();
//		resp1.setErrCode(9999);
//		resp1.setErrMsg("adfasfasdfasd");
//		
//		byte[] serialize = ProtocolTools.serialize(resp1);
//		
//		System.out.println(Arrays.toString(serialize));
//		
		
//		yuanshi();
		
	}

	/*
-128, 1, 0, 1, 	----版本号
0, 0, 0, 18, 	----方法字符串的长度
73, 100, 71, 101, 110, 83, 101, 114, 118, 105, 99, 101, 58, 105, 100, 71, 101, 110, 	--方法字符串
0, 0, 0, 1, 	----seqid
12, 			----TField.type
0, 1, 			----TField.id
11, 0, 1, 0, 0, 0, 21, 116, 95, 108, 111, 116, 116, 101, 114, 121, 95, 97, 112, 112, 101, 110, 100, 95, 116, 97, 115, 107, 8, 0, 3, 0, 0, 0, 0, 0, 	----strut body
0				----TField.stop


11, 0, 1, 0, 0, 0, 21, 116, 95, 108, 111, 116, 116, 101, 114, 121, 95, 97, 112, 112, 101, 110, 100, 95, 116, 97, 115, 107, 8, 0, 3, 0, 0, 0, 0, 0	----strut body
	 * */
	
	private static void test() throws TException {
		TMemoryBuffer transportr = new TMemoryBuffer(1024);
		TProtocol protocol = new TBinaryProtocol(transportr);
		TMultiplexedProtocol multiProtocol3 = new TMultiplexedProtocol(protocol,IdGenService.class.getSimpleName()); 
		IdGenService.Client aoClient = new IdGenService.Client(multiProtocol3);
		
		IdGenReq req=new IdGenReq();
		req.setTableName("t_lottery_append_task");
		req.setSource("testjava");
		
		aoClient.send_idGen(req);
		
		int length = transportr.length();
		byte[] buf = new byte[length];
		transportr.read(buf, 0, length);

		System.out.println(Arrays.toString(buf));
//		System.out.println(new String(buf));
		

		byte[] bs = ProtocolTools.serialize(req);
		System.out.println(Arrays.toString(bs));
//		System.out.println(new String(bs));
		
		try {
			Socket socket = new Socket();
			SocketAddress endpoint = new InetSocketAddress("127.0.0.1", 9063);
			socket.connect(endpoint);
			socket.setSoTimeout(30000);
			socket.getOutputStream().write(buf);
			socket.getOutputStream().flush();
			byte[] b=new byte[1024];
			int read = socket.getInputStream().read(b);
			byte[] d=new byte[read];
			System.arraycopy(b, 0, d, 0, read);
			System.out.println();
			System.out.println(Arrays.toString(d));
//			System.out.println(new String(d));
			
			socket.close();
			
			
			
			TMemoryBuffer tran = new TMemoryBuffer(1024);
			tran.write(d);
			TProtocol pro = new TBinaryProtocol(tran);
			TMultiplexedProtocol multi = new TMultiplexedProtocol(pro, IdGenService.class.getSimpleName()); 
			IdGenService.Client cli = new IdGenService.Client(multi);
			cli.send_idGen(req);
			IdGenResp resp = cli.recv_idGen();
			System.out.println(resp);
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		
	}

	static void yuanshi() throws TTransportException, TException {
		TTransport transport = null;
		IdGenService.Client aoClient = null;
//		transport = new TSocket("203.195.182.110", 9063);
		transport = new TSocket("127.0.0.1", 9063);
		transport.open();
		TBinaryProtocol protocol = new TBinaryProtocol(transport);
		TMultiplexedProtocol multiProtocol3 = new TMultiplexedProtocol(protocol, IdGenService.class.getSimpleName()); 
		aoClient = new IdGenService.Client(multiProtocol3);
		
		
		IdGenReq req=new IdGenReq();
		req.setTableName("t_lottery_append_task");
		IdGenResp idGen = aoClient.idGen(req);
		System.out.println(idGen);
		
		
		if(transport != null){
			transport.close();
		}
	}
}
