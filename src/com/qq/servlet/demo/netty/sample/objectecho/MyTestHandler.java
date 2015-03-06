package com.qq.servlet.demo.netty.sample.objectecho;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;

import java.net.SocketAddress;

public class MyTestHandler extends ChannelHandlerAdapter {

    // Not using volatile because it's used only for a sanity check.
    boolean added;

    /**
     * Return {@code true} if the implementation is {@link Sharable} and so can be added
     * to different {@link ChannelPipeline}s.
     */
    public boolean isSharable() {
    	System.out.println(Thread.currentThread().getName()+"\t isSharable");
        return getClass().isAnnotationPresent(Sharable.class);
    }

    /**
     * Do nothing by default, sub-classes may override this method.
     */
    
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // NOOP
    	System.out.println(Thread.currentThread().getName()+"\t public void handlerAdded(ChannelHandlerContext ctx) throws Exception");
    }

    /**
     * Do nothing by default, sub-classes may override this method.
     */
    
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    	System.out.println(Thread.currentThread().getName()+"\t public void handlerRemoved(ChannelHandlerContext ctx) throws Exception");
        // NOOP
    }

    /**
     * Calls {@link ChannelHandlerContext#fireExceptionCaught(Throwable)} to forward
     * to the next {@link ChannelHandler} in the {@link ChannelPipeline}.
     *
     * Sub-classes may override this method to change behavior.
     */
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    	System.out.println(Thread.currentThread().getName()+"\t public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception ");
//        ctx.fireExceptionCaught(cause);
    }

    /**
     * Calls {@link ChannelHandlerContext#fireChannelRegistered()} to forward
     * to the next {@link ChannelHandler} in the {@link ChannelPipeline}.
     *
     * Sub-classes may override this method to change behavior.
     */
    
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
    	System.out.println(Thread.currentThread().getName()+"\t public void channelRegistered(ChannelHandlerContext ctx) throws Exception");
//        ctx.fireChannelRegistered();
    }

    /**
     * Calls {@link ChannelHandlerContext#fireChannelActive()} to forward
     * to the next {@link ChannelHandler} in the {@link ChannelPipeline}.
     *
     * Sub-classes may override this method to change behavior.
     */
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	System.out.println(Thread.currentThread().getName()+"\t public void channelActive(ChannelHandlerContext ctx) throws Exception");
//        ctx.fireChannelActive();
    }

    /**
     * Calls {@link ChannelHandlerContext#fireChannelInactive()} to forward
     * to the next {@link ChannelHandler} in the {@link ChannelPipeline}.
     *
     * Sub-classes may override this method to change behavior.
     */
    
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    	System.out.println(Thread.currentThread().getName()+"\t public void channelInactive(ChannelHandlerContext ctx) throws Exception");
//        ctx.fireChannelInactive();
    }

    /**
     * Calls {@link ChannelHandlerContext#fireChannelRead(Object)} to forward
     * to the next {@link ChannelHandler} in the {@link ChannelPipeline}.
     *
     * Sub-classes may override this method to change behavior.
     */
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    	System.out.println(Thread.currentThread().getName()+"\t public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception");
//        ctx.fireChannelRead(msg);
    }

    /**
     * Calls {@link ChannelHandlerContext#fireChannelReadComplete()} to forward
     * to the next {@link ChannelHandler} in the {@link ChannelPipeline}.
     *
     * Sub-classes may override this method to change behavior.
     */
    
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    	System.out.println(Thread.currentThread().getName()+"\t public void channelReadComplete(ChannelHandlerContext ctx) throws Exception");
//        ctx.fireChannelReadComplete();
    }

    /**
     * Calls {@link ChannelHandlerContext#fireUserEventTriggered(Object)} to forward
     * to the next {@link ChannelHandler} in the {@link ChannelPipeline}.
     *
     * Sub-classes may override this method to change behavior.
     */
    
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    	System.out.println(Thread.currentThread().getName()+"\t public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception");
//        ctx.fireUserEventTriggered(evt);
    }

    /**
     * Calls {@link ChannelHandlerContext#fireChannelWritabilityChanged()} to forward
     * to the next {@link ChannelHandler} in the {@link ChannelPipeline}.
     *
     * Sub-classes may override this method to change behavior.
     */
    
    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
    	System.out.println(Thread.currentThread().getName()+"\t public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception");
//        ctx.fireChannelWritabilityChanged();
    }

    /**
     * Calls {@link ChannelHandlerContext#bind(java.net.SocketAddress, ChannelPromise)} to forward
     * to the next {@link ChannelHandler} in the {@link ChannelPipeline}.
     *
     * Sub-classes may override this method to change behavior.
     */
    
    @Override
    public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
    	System.out.println(Thread.currentThread().getName()+"\t public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception");
//        ctx.bind(localAddress, promise);
    }

    /**
     * Calls {@link ChannelHandlerContext#connect(SocketAddress, SocketAddress, ChannelPromise)} to forward
     * to the next {@link ChannelHandler} in the {@link ChannelPipeline}.
     *
     * Sub-classes may override this method to change behavior.
     */
    
    @Override
    public void connect(ChannelHandlerContext ctx,SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
//        ctx.connect(remoteAddress, localAddress, promise);
        System.out.println(Thread.currentThread().getName()+"\t public void connect(ChannelHandlerContext ctx,SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception ");
    }

    /**
     * Calls {@link ChannelHandlerContext#disconnect(ChannelPromise)} to forward
     * to the next {@link ChannelHandler} in the {@link ChannelPipeline}.
     *
     * Sub-classes may override this method to change behavior.
     */
    
    @Override
    public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
    	System.out.println(Thread.currentThread().getName()+"\t public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception");
//        ctx.disconnect(promise);
    }

    /**
     * Calls {@link ChannelHandlerContext#close(ChannelPromise)} to forward
     * to the next {@link ChannelHandler} in the {@link ChannelPipeline}.
     *
     * Sub-classes may override this method to change behavior.
     */
    
    @Override
    public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
    	System.out.println(Thread.currentThread().getName()+"\t public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception");
//        ctx.close(promise);
    }

    /**
     * Calls {@link ChannelHandlerContext#read()} to forward
     * to the next {@link ChannelHandler} in the {@link ChannelPipeline}.
     *
     * Sub-classes may override this method to change behavior.
     */
    
    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
    	System.out.println(Thread.currentThread().getName()+"\t public void read(ChannelHandlerContext ctx) throws Exception ");
//        ctx.read();
    }

    /**
     * Calls {@link ChannelHandlerContext#write(Object)} to forward
     * to the next {@link ChannelHandler} in the {@link ChannelPipeline}.
     *
     * Sub-classes may override this method to change behavior.
     */
    
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
    	System.out.println(Thread.currentThread().getName()+"\t public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {");
//        ctx.write(msg, promise);
    }

    /**
     * Calls {@link ChannelHandlerContext#flush()} to forward
     * to the next {@link ChannelHandler} in the {@link ChannelPipeline}.
     *
     * Sub-classes may override this method to change behavior.
     */
    
    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
    	System.out.println(Thread.currentThread().getName()+"\t public void flush(ChannelHandlerContext ctx) throws Exception");
//        ctx.flush();
    }

}
