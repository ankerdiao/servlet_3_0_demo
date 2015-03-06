package com.qq.servlet.demo.thrift;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.TProcessor;

import com.qq.servlet.demo.netty.sample.thrift.protocol.IdGenService;

public class DemoService {

	public static void main(String[] args) {
		
		TMultiplexedProcessor processor = new TMultiplexedProcessor();
		processor.registerProcessor(IdGenService.class.getSimpleName(), new IdGenService.Processor(new IdGenServiceHandler()));
//		TProcessor processor = null;
		
//		processor.process(iprot, oprot);
		 
	}
}
