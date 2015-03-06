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
package com.qq.servlet.demo.netty.telnet.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.qq.servlet.demo.netty.telnet.TelnetClientInitializer;

/**
 * Simplistic telnet client.
 */
public class TelnetClient {

    private final String host;
    private final int port;

    public TelnetClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception {
    	EventLoopGroup group = new NioEventLoopGroup(2);
        try {
            final Bootstrap b = new Bootstrap();
            b.group(group);
            b.channel(NioSocketChannel.class);
            b.handler(new ClientInitializer());

            
            for (int t = 0; t < 10; t++) {
            	for (int i = 0; i < 50; i++) {
            		new Thread(){
            			int j=0;
            			public void run() {
            				try {
            					test(b, j++);
            				} catch (InterruptedException e) {
            					e.printStackTrace();
            				}
            			};
            		}.start();
            	}
			}
            
            System.in.read();
        } finally {
            group.shutdownGracefully();
        }
    }

	private void test(Bootstrap b, int i) throws InterruptedException {
		// Start the connection attempt.
		Channel ch = b.connect(host, port).sync().channel();
		
		String body = "["+i+"]aaaaaaa"+Thread.currentThread().getName()+"aaaaa";
		
		AttributeKey<String> key = AttributeKey.valueOf("ASYNC_CONTEXT");
		Attribute<String> attr = ch.attr(key);
		String andSet = attr.getAndSet(Thread.currentThread().getName());
		System.out.println(andSet);
		
		ChannelFuture lastWriteFuture = ch.writeAndFlush( body+ "\r\n");
		// Wait until all messages are flushed before closing the channel.
		if (lastWriteFuture != null) {
			lastWriteFuture.sync();
		}
	}

    public static void main(String[] args) throws Exception {
        new TelnetClient("localhost", 8081).run();
    }
}
