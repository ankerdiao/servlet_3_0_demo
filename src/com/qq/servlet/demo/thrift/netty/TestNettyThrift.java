//package com.qq.servlet.demo.thrift.netty;
//
//import java.net.InetSocketAddress;
//import java.util.concurrent.Executors;
//
//import org.apache.thrift.protocol.TBinaryProtocol;
//import org.apache.thrift.protocol.TProtocol;
//import org.apache.thrift.transport.TTransport;
//import org.jboss.netty.bootstrap.ClientBootstrap;
//import org.jboss.netty.channel.Channel;
//import org.jboss.netty.channel.ChannelFactory;
//import org.jboss.netty.channel.ChannelPipelineFactory;
//import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
//
//import se.cgbystrom.netty.thrift.TNettyTransport;
//import se.cgbystrom.netty.thrift.ThriftClientHandler;
//import se.cgbystrom.netty.thrift.ThriftPipelineFactory;
//
//
//
//public class TestNettyThrift {
//
//	public static void main(String[] args) {
//			 // create the client bootstrap
//			 ChannelFactory factory = new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool(), 3);
//			 ClientBootstrap bootstrap = new ClientBootstrap(factory);
//			 
//			 // create the netty handlers, and connect to the server
//			 ThriftClientHandler handler = new ThriftClientHandler();
//			 
//			 // comment the following line and uncomment the next to use an Http Client. Needs an http-enabled server.
//			 ChannelPipelineFactory factory = new ThriftPipelineFactory(handler);
//			 // ChannelPipelineFactory factory = new ThriftHttpClientPipelineFactory(handler, "127.0.0.1", 8080);
//			 
//			 bootstrap.setPipelineFactory(factory);
//			 Channel channel = bootstrap.connect(
//			         new InetSocketAddress("localhost", 8080)).awaitUninterruptibly()
//			         .getChannel();
//			 
//			 // create your thrift client and use it as normal
//			 TTransport transport = new TNettyTransport(channel, handler);
//			 try {
//			     TProtocol protocol = new TBinaryProtocol(transport);
//			     user-generated.thrift.Client client = new user-generated.thrift.Client(protocol);
//			     client.ping();
//			 } catch (Exception exc) {
//			     exc.printStackTrace();
//			 }
//			 
//			 // Release the netty resources
//			 channel.close().awaitUninterruptibly();
//			 bootstrap.releaseExternalResources();
//			 }
//			 
//	}
//}
