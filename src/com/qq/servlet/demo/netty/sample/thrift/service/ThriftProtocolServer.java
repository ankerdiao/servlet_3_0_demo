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
package com.qq.servlet.demo.netty.sample.thrift.service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Simplistic telnet server.
 */
public class ThriftProtocolServer {

    private final int port;

    public ThriftProtocolServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        final EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        final EventLoopGroup workerGroup = new NioEventLoopGroup(3);
        
        startCloseCmdThread(bossGroup, workerGroup);
        
        try {
            final ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(new ThriftProtocolServerChannelHandler());
            Channel channel;
            System.out.println("server is starting....");
            channel = b.bind(port).sync().channel();
            channel.closeFuture().sync();
            System.out.println("server is closing....");
        
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            System.out.println("server is closed");
        }
    }

	private void startCloseCmdThread(final EventLoopGroup bossGroup,
			final EventLoopGroup workerGroup) {
		new Thread(){
        	@Override
        	public void run() {
        		try {
        			byte[] b = new byte[32];
        			String read=null;
        			do{
        				if(read!=null){
        					System.out.println("你的输入是："+read);
        				}
        				int c = System.in.read(b);
        				read = new String(b,0,c);
        			}while(read==null || !read.startsWith("exit"));
                    bossGroup.shutdownGracefully();
                    workerGroup.shutdownGracefully();
                    System.out.println("命令行关闭服务");
				} catch (Exception e) {
					e.printStackTrace();
				}
        	}
        }.start();
	}

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8383;
        }
        new ThriftProtocolServer(port).run();
    }
}
