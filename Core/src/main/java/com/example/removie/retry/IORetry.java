package com.example.removie.retry;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

@Retryable(
        retryFor = { SocketTimeoutException.class,
                ConnectException.class,
                AmazonServiceException.class,
                SdkClientException.class },
        maxAttempts = 5,
        backoff = @Backoff(delay = 2000, multiplier = 2, maxDelay = 15000)
)
public @interface IORetry {
}
