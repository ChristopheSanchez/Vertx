package com.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;

public class VertxClient extends AbstractVerticle {

	public void start() throws Exception {
		NetClient client = vertx.createNetClient();
		
		client.connect(10000, "localhost",  new Handler<AsyncResult<NetSocket>>() {
			public void handle(AsyncResult<NetSocket> res) {
				NetSocket socket = res.result();
				if (res.succeeded()) {
					  System.out.println("Connecté au serveur!");
					  socket.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
						  		+ " <name>Vertx</name>" + " <description>Demo project for vertx</description>");
				} else {
					  System.out.println("Failed to connect: " + res.cause().getMessage());
				}
				socket.handler(new Handler<Buffer>() {
                    @Override
                    public void handle(Buffer buffer) {
                        System.out.println(buffer.getString(0, buffer.length()));
                    }
                });
			}
		});
		
		client.close();
	}

}
