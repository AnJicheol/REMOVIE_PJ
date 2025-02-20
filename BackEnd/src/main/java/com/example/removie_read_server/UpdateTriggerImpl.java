package com.example.removie_read_server;


import com.example.removie_read_server.service.manager.ReMovieManager;
import com.example.removie_read_server.service.fcm.FCMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class UpdateTriggerImpl implements UpdateTrigger {
    private final CacheManager cacheManager;
    private final ReMovieManager reMovieManager;
    private final FCMService fcmService;

    @Autowired
    public UpdateTriggerImpl(CacheManager cacheManager, ReMovieManager reMovieManager, FCMService fcmService) {
        this.cacheManager = cacheManager;
        this.reMovieManager = reMovieManager;
        this.fcmService = fcmService;
    }

    @Override
    public void updateProcess(){
        sendFCM();
        clearCache();
    }

    @CacheEvict(value = "*", allEntries = true)
    public void clearCache(){
        cacheManager.getCacheNames().forEach(cacheName -> {
            Cache cache = cacheManager.getCache(cacheName);
            if (cache != null) {
                cache.clear();
            }
        });
    }

    private void sendFCM(){
        fcmService.sendNotification(reMovieManager.latestRemovie());
    }
}
