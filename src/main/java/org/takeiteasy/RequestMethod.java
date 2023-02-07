package org.takeiteasy;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public enum RequestMethod {
    GET{
        @Override
        public void handler(HttpExchange exchange){
            try {
                String response = "This is the GET response";
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    },
    POST{
        @Override
        public void handler(HttpExchange exchange){
            try {
                String response = "This is the POST response";
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    },
    PUT{
        @Override
        public void handler(HttpExchange exchange){
            try {
                String response = "This is the PUT response";
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    },
    DELETE{
        @Override
        public void handler(HttpExchange exchange){
            try {
                String response = "This is the DELETE response";
                exchange.sendResponseHeaders(404, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    },
    NOT_SUCH_A_METHOD{
        @Override
        public void handler(HttpExchange exchange){
            try {
                String response = "No such a method";
                exchange.sendResponseHeaders(403, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    };

    public abstract void handler(HttpExchange exchange);
}

