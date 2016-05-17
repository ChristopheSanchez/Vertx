package com.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.NetSocket;

import java.awt.TextArea;

import io.vertx.*;

public class VertxApplication extends AbstractVerticle {

	public void start() throws Exception {

        System.out.println("Start of Hello World");

        /*vertx.createHttpServer() 
            .requestHandler(req -> {
            	System.out.println("Hello World !!");
            	
            	req.response().putHeader("content-type", "text/html")
            		.end("<html><head><title>Vertx Application</title></head><body><h2>Hello World, Bonjour !!</h2>"
            				+ "<br/> <textarea "
            				+ "name=\"contenu\" "
            				+ "cols=\"45\" "
            				+ "rows=\"7\" "
            				+ "onfocus=\"if(this.value == this.defaultValue) this.value = '';\""
            				+ "	onblur=\"if(this.value.length > 40){alert('La longueur maximale aurotisée est de 40 caractères.'); this.value = this.defaultValue;} if(this.value == '') this.value = this.defaultValue;\""
            				+ ">XML Message</textarea>"
            				+ "<br/><br/><input type=\"button\" name=\"ValidateButton\" value=\"Valider\" onclick=\"\" "
            				+ ""
            				+ "</body></html>");
            	TextArea f1 = new TextArea();
            	String v1 = f1.getText();
            	System.out.println(v1);
            }) 
            .listen(8080);*/
        
        NetServer server = vertx.createNetServer();
        
        server.connectHandler(new Handler<NetSocket>() {
        	
        	public void handle(NetSocket socket) {
              	System.out.println("Nouvelle connection sur le serveur");
              	//socket.upgradeToSsl(null);
              	socket.handler(new Handler<Buffer>() {
               		public void handle(Buffer buffer) {
                        System.out.println("incoming data: " + buffer.length());
                        System.out.println(buffer.getString(0, buffer.length()));
                        if (buffer.length()>20) {
                        	socket.write("Données reçues !!");
                        } else socket.write("Données non complètes"); 
                    }
				});
        	}
        });	
        
        server.listen(10000);
        
        server.close();
        
   }
}
