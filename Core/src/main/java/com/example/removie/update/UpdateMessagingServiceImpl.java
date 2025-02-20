package com.example.removie.update;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


/**
 * 모든 프로세스 종료 후 백엔드 메시지를 보내는 서비스입니다.
 * 해당 신호를 받은 백엔드 서버는 캐시를 초기화합니다, 해당 메시지가 실패하여도
 * 백엔드 서버에 캐시는 자동으로 소멸됩니다.
 *
 * @author An_Jicheol
 * @version 1.0
 */

@Service
public class UpdateMessagingServiceImpl implements UpdateMessagingService{
    private static final Logger logger = LoggerFactory.getLogger(UpdateMessagingServiceImpl.class);

    private final WebClient webClient;

    @Value("${backend.url}")
    private String BACKEND_URL;

    @Autowired
    public UpdateMessagingServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(BACKEND_URL).build();
    }

    public void sendMessage() {
        webClient.post()
                .retrieve()
                .toBodilessEntity()
                .doOnSuccess(response -> {
                    if (response.getStatusCode() == HttpStatus.NON_AUTHORITATIVE_INFORMATION) {
                        logger.info("메시지 전송 완료");
                    } else {
                        logger.error("메시지 전송 실패, 응답 코드: {}", response.getStatusCode());
                    }
                })
                .doOnError(error -> logger.error("메시지 전송 중 오류 발생", error))
                .block();
    }
}
