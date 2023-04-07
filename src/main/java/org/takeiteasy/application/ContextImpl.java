package org.takeiteasy.application;

import com.sun.net.httpserver.HttpExchange;

public class ContextImpl implements Context {
    public Handler handler;

    public ContextImpl(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void handleRequest(HttpExchange exchange) {
        handler.handler(exchange);
    }
}

