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

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelPromise;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

/**
 * Handles a client-side channel.
 */
@Sharable
public class ClientHandler extends SimpleChannelInboundHandler<String> {

	@Override
	public void write(ChannelHandlerContext ctx, Object msg,
			ChannelPromise promise) throws Exception {
		super.write(ctx, msg, promise);
		System.out.println("write message:"+msg);
	}
	

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
    	Channel ch = ctx.channel();
    	AttributeKey<String> key = AttributeKey.valueOf("ASYNC_CONTEXT");
		Attribute<String> attr = ch.attr(key);
		System.out.println("----------->"+attr.get()+"\t"+msg);
		ChannelFuture future = ch.close();
		future.await(100);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    	cause.printStackTrace();
        ctx.close();
    }
}
