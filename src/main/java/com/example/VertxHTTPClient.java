package com.example;

import org.omg.CORBA.Request;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpClientResponse;


public class VertxHTTPClient extends AbstractVerticle {
	
	private HttpClient httpClient = null;
	private HttpClientRequest req = null;
	
	public void start() throws Exception {
		httpClient = vertx.createHttpClient();
		String xml = "test";
		httpClient.getNow(9999, "localhost", xml, new Handler<HttpClientResponse>() {
			
		    @Override
		    public void handle(HttpClientResponse httpClientResponse) {
		    	
		        System.out.println("Response received");
		        httpClientResponse.bodyHandler(new Handler<Buffer>() {
		            public void handle(Buffer data) {
		              System.out.println(data);
		            }
		        });
		    }
		});
	}
}
