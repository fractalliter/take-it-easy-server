package org.takeiteasy;

import com.sun.net.httpserver.HttpExchange;

public class ServerContext {
    public RequestMethod requestMethod;

    public ServerContext(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
    }

    public void setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
    }

    public void handleRequest(HttpExchange exchange){
        requestMethod.handler(exchange);
    }
}

