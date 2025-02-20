package com.example.removie_read_server.service.fcm;

import java.util.List;

public interface FCMService {
    void sendNotification(List<String> movieCodeList);
}
