package com.example.removie.document.kobis;

import com.example.removie.document.DocConnect;
import com.example.removie.document.DocConnection;
import com.example.removie.kobis.exception.DocIOException;
import com.example.removie.kobis.exception.DocResponseNullException;
import com.example.removie.retry.IORetry;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class KOBISDocConnect implements DocConnect {
    private final Logger logger = LoggerFactory.getLogger(KOBISDocConnect.class);


    @IORetry
    public Document responseDoc(DocConnection docConnection){
        try {
            Document document = docConnection.response();
            validateDocument(document);
            return document;

        } catch (IOException e) {
            throw new DocIOException("영화 리스트 페이지 리스폰 실패", e);
        }
    }

    private void validateDocument(Document document){
        if(document.html().isEmpty()){

            logger.error("페이지 변경 가능성 있음 - Document 비어 있음.");
            throw new DocResponseNullException("MovieInfoList Document 비어 있음");
        }
    }
}
