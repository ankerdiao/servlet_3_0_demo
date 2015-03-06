package com.qq.servlet.demo.thrift;

import org.apache.thrift.TException;

import com.qq.servlet.demo.netty.sample.thrift.protocol.IdGenBatchReq;
import com.qq.servlet.demo.netty.sample.thrift.protocol.IdGenBatchResp;
import com.qq.servlet.demo.netty.sample.thrift.protocol.IdGenReq;
import com.qq.servlet.demo.netty.sample.thrift.protocol.IdGenResp;
import com.qq.servlet.demo.netty.sample.thrift.protocol.IdGenService.Iface;

public class IdGenServiceHandler implements Iface {

	@Override
	public IdGenResp idGen(IdGenReq req) throws TException {
		IdGenResp resp = new IdGenResp();
		resp.setErrCode(9999);
		resp.setErrMsg("adfasfasdfasd");
		System.out.println(req);
		return resp;
	}

	@Override
	public IdGenBatchResp idGenBatch(IdGenBatchReq req) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

}
