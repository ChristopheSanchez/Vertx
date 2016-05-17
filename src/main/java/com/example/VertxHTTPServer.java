package com.example;

import java.util.Map;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;

public class VertxHTTPServer extends AbstractVerticle {
	
	private HttpServer httpServer = null;
	
	public void start() throws Exception {
		httpServer = vertx.createHttpServer();
		System.out.println("Lancement du serveur");
		
		httpServer.requestHandler(new Handler<HttpServerRequest>() {
			@Override
			public void handle(HttpServerRequest request) {
				System.out.println("Connexion entrante !!");
	            
				System.out.println("Got request: " + request.uri());
				
				for (Map.Entry<String, String> entry : request.headers()) {
	                System.out.println("1- " + entry.getKey() + " : " + entry.getValue());
	              }
	            request.response().headers().set("Content-Type", "text/html; charset=UTF-8");
	            request.response().setChunked(true);
	            request.response().write("<html><body><h1>Hello from vert.x!</h1>" + "</body></html>", "UTF-8").end();
	        }
	    });
		
		httpServer.listen(9999);
	}
}
