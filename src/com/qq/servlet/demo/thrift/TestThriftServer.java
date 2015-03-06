package com.qq.servlet.demo.thrift;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

import com.qq.servlet.demo.netty.sample.thrift.protocol.IdGenService;

public class TestThriftServer {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws TTransportException {
		System.out.println(IdGenService.class.getSimpleName());
		TMultiplexedProcessor processor = new TMultiplexedProcessor();
		processor.registerProcessor(IdGenService.class.getSimpleName(), new IdGenService.Processor(new IdGenServiceHandler()));
		
		TServerTransport transport = new TServerSocket(9063);
		
		TThreadPoolServer.Args serverArgs = new TThreadPoolServer.Args(transport);
		
		serverArgs.processor(processor);
		
		TThreadPoolServer server = new TThreadPoolServer(serverArgs);
		
		System.out.println("服务已启动");
		server.serve();
		System.out.println("服务已结束");
	}
}
