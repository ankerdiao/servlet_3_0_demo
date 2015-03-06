/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.qq.servlet.demo.thrift.netty.client;

import java.util.Arrays;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TMemoryBuffer;

import com.qq.servlet.demo.netty.sample.thrift.protocol.IdGenReq;
import com.qq.servlet.demo.netty.sample.thrift.protocol.IdGenService;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

/**
 * Simplistic telnet client.
 */
public class NettyThriftClient {

    private final String host;
    private final int port;

    public NettyThriftClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception {
    	EventLoopGroup group = new NioEventLoopGroup(2);
        try {
            final Bootstrap b = new Bootstrap();
            b.group(group);
            b.channel(NioSocketChannel.class);
            b.handler(new NettyThriftClientInitializer());

            

    		// Start the connection attempt.
            ChannelFuture future = b.connect(host, port);
            ChannelFuture sync = future.sync();
    		Channel ch = sync.channel();
    		
    		
    		AttributeKey<String> key = AttributeKey.valueOf("ASYNC_CONTEXT");
    		Attribute<String> attr = ch.attr(key);
    		String andSet = attr.getAndSet(Thread.currentThread().getName());
    		
    		
    		TMemoryBuffer transportr = new TMemoryBuffer(1024);
    		TProtocol protocol = new TBinaryProtocol(transportr);
    		TMultiplexedProtocol multiProtocol3 = new TMultiplexedProtocol(protocol,IdGenService.class.getSimpleName()); 
    		IdGenService.Client aoClient = new IdGenService.Client(multiProtocol3);
    		IdGenReq req=new IdGenReq();
    		req.setTableName("t_lottery_append_task-----------");
    		req.setSource(andSet);
    		aoClient.send_idGen(req);
    		int length = transportr.length();
    		byte[] buf = new byte[length];
    		transportr.read(buf, 0, length);
    		System.out.println(Arrays.toString(buf));
    		
    		
    		
    		ChannelFuture lastWriteFuture = ch.writeAndFlush(buf);
    		// Wait until all messages are flushed before closing the channel.
    		if (lastWriteFuture != null) {
    			lastWriteFuture.sync();
    		}
            
            
            System.in.read();
        } finally {
            group.shutdownGracefully();
        }
    }


    public static void main(String[] args) throws Exception {
        new NettyThriftClient("localhost", 9063).run();
    }
}
