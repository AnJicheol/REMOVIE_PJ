package com.example.removie.document;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class GETConnection implements DocConnection {
    private final Connection connection;

    public GETConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Document response() throws IOException {
        return connection.get();
    }

    @Override
    public String getUrl() {
        return connection.request().url().toString();
    }

    public static GETConnection of(Connection connection){
        return new GETConnection(connection);
    }
}
