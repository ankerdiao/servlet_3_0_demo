package com.qq.servlet.demo.netty.sample.http.helloworld;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.ClientCookieEncoder;
import io.netty.handler.codec.http.DefaultCookie;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.QueryStringEncoder;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder.ErrorDataEncoderException;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map.Entry;

public class HttpHelloWorldClient {

	public static void main(String[] args) {
		// Configure the client.
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).handler(new HttpHelloWorldClientIntializer());
			//http://api.weigou.qq.com/wgwdeal/wgCloseDeal.xhtml
			List<Entry<String, String>> headers = formGet(b, "api.weigou.qq.com", 80, "/wgwdeal/wgCloseDeal.xhtml", new URI("http://api.weigou.qq.com/wgwdeal/wgCloseDeal.xhtml"));
			
	        // setup the factory: here using a mixed memory/disk based on size threshold
	        HttpDataFactory factory = new DefaultHttpDataFactory(DefaultHttpDataFactory.MINSIZE); // Disk if MINSIZE exceed
	        
            if (headers == null) {
                factory.cleanAllHttpDatas();
                return;
            }

            // Simple Post form: factory used for big attributes
            List<InterfaceHttpData> bodylist = formPost(b, "localhost", 8080, new URI("http://localhost:8080/servlet_3_0_demo/SyncServlet"), null, factory, headers);
            if (bodylist == null) {
                factory.cleanAllHttpDatas();
                return;
            }

            // Multipart Post form: factory used
//            formPostMultipart(b, host, port, uriFile, factory, headers, bodylist);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			// Shut down executor threads to exit.
            group.shutdownGracefully();
		}
	}
	
	
	
    /**
     * Standard usage of HTTP API in Netty without file Upload (get is not able to achieve File upload due to limitation
     * on request size).
     *
     * @return the list of headers that will be used in every example after
     **/
    private static List<Entry<String, String>> formGet(Bootstrap bootstrap, String host, int port, String get,
            URI uriSimple) throws Exception {
        // Start the connection attempt.
        // No use of HttpPostRequestEncoder since not a POST
        Channel channel = bootstrap.connect(host, port).sync().channel();

        // Prepare the HTTP request.
        QueryStringEncoder encoder = new QueryStringEncoder(get);
        // add Form attribute
        encoder.addParam("getform", "GET");
        encoder.addParam("info", "first value");
        // not the big one since it is not compatible with GET size
        // encoder.addParam("thirdinfo", textArea);
        encoder.addParam("thirdinfo", "third value\r\ntest second line\r\n\r\nnew line\r\n");
        encoder.addParam("Send", "Send");

        URI uriGet;
        try {
            uriGet = new URI(encoder.toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }

        FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, uriGet.toASCIIString());
        HttpHeaders headers = request.headers();
        headers.set(HttpHeaders.Names.HOST, host);
        headers.set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.CLOSE);
        headers.set(HttpHeaders.Names.ACCEPT_ENCODING, HttpHeaders.Values.GZIP.toString() + ',' + HttpHeaders.Values.DEFLATE.toString());

        headers.set(HttpHeaders.Names.ACCEPT_CHARSET, "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
        headers.set(HttpHeaders.Names.ACCEPT_LANGUAGE, "fr");
        headers.set(HttpHeaders.Names.REFERER, uriSimple.toString());
        headers.set(HttpHeaders.Names.USER_AGENT, "Netty Simple Http Client side");
        headers.set(HttpHeaders.Names.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");

        headers.set(HttpHeaders.Names.COOKIE, ClientCookieEncoder.encode(new DefaultCookie("my-cookie", "foo"), new DefaultCookie("another-cookie", "bar")));

        // send request
        List<Entry<String, String>> entries = headers.entries();
        channel.writeAndFlush(request).sync();

        // Wait for the server to close the connection.
        channel.closeFuture().sync();

        return entries;
    }
	
    
    
    /**
     * Standard post without multipart but already support on Factory (memory management)
     *
     * @return the list of HttpData object (attribute and file) to be reused on next post
     */
    static List<InterfaceHttpData> formPost(Bootstrap bootstrap, String host, int port, URI uriSimple,
            File file, HttpDataFactory factory, List<Entry<String, String>> headers) throws Exception {

        // Start the connection attempt
        Channel channel = bootstrap.connect(host, port).sync().channel();

        // Prepare the HTTP request.
        HttpRequest request = new DefaultHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, uriSimple.toASCIIString());

        // Use the PostBody encoder
        HttpPostRequestEncoder bodyRequestEncoder = null;
        try {
            bodyRequestEncoder = new HttpPostRequestEncoder(factory, request, false); // false not multipart
        } catch (NullPointerException e) {
            // should not be since args are not null
            e.printStackTrace();
        } catch (ErrorDataEncoderException e) {
            // test if getMethod is a POST getMethod
            e.printStackTrace();
        }

        // it is legal to add directly header or cookie into the request until finalize
        for (Entry<String, String> entry : headers) {
            request.headers().set(entry.getKey(), entry.getValue());
        }

        // add Form attribute
        try {
            bodyRequestEncoder.addBodyAttribute("getform", "POST");
            bodyRequestEncoder.addBodyAttribute("info", "first value");
            bodyRequestEncoder.addBodyAttribute("secondinfo", "secondvalue kkkkkkkkk");
            bodyRequestEncoder.addBodyAttribute("thirdinfo", textArea);
            bodyRequestEncoder.addBodyAttribute("Send", "Send");
            if(file!=null){
            	bodyRequestEncoder.addBodyFileUpload("myfile", file, "application/x-zip-compressed", false);
            }
        } catch (NullPointerException e) {
            // should not be since not null args
            e.printStackTrace();
        } catch (ErrorDataEncoderException e) {
            // if an encoding error occurs
            e.printStackTrace();
        }

        // finalize request
        try {
            request = bodyRequestEncoder.finalizeRequest();
        } catch (ErrorDataEncoderException e) {
            // if an encoding error occurs
            e.printStackTrace();
        }

        // Create the bodylist to be reused on the last version with Multipart support
        List<InterfaceHttpData> bodylist = bodyRequestEncoder.getBodyListAttributes();

        // send request
        channel.write(request);

        // test if request was chunked and if so, finish the write
        if (bodyRequestEncoder.isChunked()) {
            // could do either request.isChunked()
            // either do it through ChunkedWriteHandler
            channel.writeAndFlush(bodyRequestEncoder).awaitUninterruptibly();
        }  else {
            channel.flush();
        }

        // Do not clear here since we will reuse the InterfaceHttpData on the
        // next request
        // for the example (limit action on client side). Take this as a
        // broadcast of the same
        // request on both Post actions.
        //
        // On standard program, it is clearly recommended to clean all files
        // after each request
        // bodyRequestEncoder.cleanFiles();

        // Wait for the server to close the connection.
        channel.closeFuture().sync();

        return bodylist;
    }

    /**
     * Multipart example
     */
    private static void formPostMultipart(Bootstrap bootstrap, String host, int port, URI uriFile,
            HttpDataFactory factory, List<Entry<String, String>> headers, List<InterfaceHttpData> bodylist)
            throws Exception {

        // Start the connection attempt
        Channel channel = bootstrap.connect(host, port).sync().channel();

        // Prepare the HTTP request.
        HttpRequest request =
                new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, uriFile.toASCIIString());

        // Use the PostBody encoder
        HttpPostRequestEncoder bodyRequestEncoder = null;
        try {
            bodyRequestEncoder = new HttpPostRequestEncoder(factory, request, true); // true => multipart
        } catch (NullPointerException e) {
            // should not be since no null args
            e.printStackTrace();
        } catch (ErrorDataEncoderException e) {
            // test if getMethod is a POST getMethod
            e.printStackTrace();
        }

        // it is legal to add directly header or cookie into the request until finalize
        for (Entry<String, String> entry : headers) {
            request.headers().set(entry.getKey(), entry.getValue());
        }

        // add Form attribute from previous request in formpost()
        try {
            bodyRequestEncoder.setBodyHttpDatas(bodylist);
        } catch (NullPointerException e1) {
            // should not be since previously created
            e1.printStackTrace();
        } catch (ErrorDataEncoderException e1) {
            // again should not be since previously encoded (except if an error
            // occurs previously)
            e1.printStackTrace();
        }

        // finalize request
        try {
            request = bodyRequestEncoder.finalizeRequest();
        } catch (ErrorDataEncoderException e) {
            // if an encoding error occurs
            e.printStackTrace();
        }

        // send request
        channel.write(request);

        // test if request was chunked and if so, finish the write
        if (bodyRequestEncoder.isChunked()) {
            channel.writeAndFlush(bodyRequestEncoder).awaitUninterruptibly();
        } else {
            channel.flush();
        }

        // Now no more use of file representation (and list of HttpData)
        bodyRequestEncoder.cleanFiles();

        // Wait for the server to close the connection.
        channel.closeFuture().sync();
    }
    
 // use to simulate a small TEXTAREA field in a form
    private static final String textArea = "-------------short text----------------";
    
}
