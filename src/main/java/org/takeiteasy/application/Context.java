package org.takeiteasy.application;

import com.sun.net.httpserver.HttpExchange;

public interface Context {
    void handleRequest(HttpExchange exchange);
}
