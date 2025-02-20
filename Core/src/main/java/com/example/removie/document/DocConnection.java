package com.example.removie.document;

import org.jsoup.nodes.Document;

import java.io.IOException;

public interface DocConnection {

    Document response() throws IOException;
    String getUrl();
}
