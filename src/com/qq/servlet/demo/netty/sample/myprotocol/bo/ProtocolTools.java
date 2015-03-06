package com.qq.servlet.demo.netty.sample.myprotocol.bo;

import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TMemoryBuffer;
import org.apache.thrift.transport.TTransportException;

/**
 * thrift协议序列化工具
 * @author ankerdiao
 * 2014年8月5日
 */
@SuppressWarnings("rawtypes")
public class ProtocolTools{

	private static final TBinaryProtocol.Factory factory = new TBinaryProtocol.Factory();
//	private static final int size = 102400;

	/**
	 * 把字节流序列化为对象
	 * 协议：TBinaryProtocol
	 * @param bs
	 * @param reqr
	 * @return 
	 * @throws TTransportException
	 * @throws TException
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@SuppressWarnings({"unchecked" })
	public static <T extends TBase> T unSerialize(byte[] bs, Class<T> tr) throws TTransportException, TException, InstantiationException, IllegalAccessException {
		TBase base = tr.newInstance();
		unSerialize(bs, base);
		T t = (T) base;
		return t;
	}
	
	/**
	 * 把字节流序列化为对象
	 * 协议：TBinaryProtocol
	 * @param bs
	 * @param reqr
	 * @throws TTransportException
	 * @throws TException
	 */
	public static void unSerialize(byte[] bs, TBase reqr) throws TTransportException, TException {
		TMemoryBuffer transportr = new TMemoryBuffer(reqr.toString().length());
		TProtocol tProtocolr = factory.getProtocol(transportr);
		transportr.write(bs);
		reqr.read(tProtocolr);
	}

	/**
	 * 把对象反序列化为字节流
	 * 协议：TBinaryProtocol
	 * @param reqw
	 * @return
	 * @throws TException
	 */
	public static byte[] serialize(TBase reqw) throws TException {
		TMemoryBuffer transport = new TMemoryBuffer(reqw.toString().length());
		TProtocol tProtocol = factory.getProtocol(transport);
		reqw.write(tProtocol);
		int length = transport.length();
		byte[] buf = new byte[length];
		transport.read(buf , 0, length);
		return buf;
	}
}
