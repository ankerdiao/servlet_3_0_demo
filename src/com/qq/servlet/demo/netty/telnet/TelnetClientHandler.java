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
package com.qq.servlet.demo.netty.telnet;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handles a client-side channel.
 */
@Sharable
public class TelnetClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
    	Channel ch = ctx.channel();
    	AttributeKey<AsyncContext> key=AttributeKey.valueOf("ASYNC_CONTEXT");
		Attribute<AsyncContext> attr = ch.attr(key);
		AsyncContext context = attr.get();
		
		if(context!=null){
			HttpServletRequest request = (HttpServletRequest) context.getRequest();
			HttpServletResponse response = (HttpServletResponse) context.getResponse();
			Object object = request.getAttribute("beginTime");
			long bt = Long.parseLong(object.toString());
			PrintWriter writer = response.getWriter();
			writer.println("invoke api result: \t");
			long time = System.currentTimeMillis()-bt;
//			writer.println("进入服务器到出来服务器的时间花费："+time);
			writer.flush();
			//调用这个servlet上下文，完成http的会话请求
			System.err.println(context+"\t"+request.getAttribute("REQUEST_ID"));
			context.complete();
		}else{
			System.out.println(context+"----------->"+msg);
		}
		ChannelFuture future = ch.close();
		future.await(100);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    	cause.printStackTrace();
        ctx.close();
    }
}
