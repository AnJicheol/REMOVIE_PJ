package com.example.removie_read_server.service.fcm;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FCMServiceImpl implements FCMService {
    private static final Logger logger = LoggerFactory.getLogger(FCMServiceImpl.class);
    private static final String FCM_TOPIC = "REMOVIE_WISHLIST";

    public void sendNotification(List<String> movieCodeList) {
        Message message = Message.builder()
                .setTopic(FCM_TOPIC)
                .putData("movieCodes", String.join(",", movieCodeList))
                .build();


        try {
            String response = FirebaseMessaging.getInstance().send(message);
            logger.info("FCM 메시지 전송 성공: {}", response);
        } catch (FirebaseMessagingException e) {
            logger.error("FCM 메시지 전송 실패: {}", e.getMessage(), e);
        }
    }
}