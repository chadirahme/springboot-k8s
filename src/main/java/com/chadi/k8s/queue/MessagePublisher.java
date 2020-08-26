package com.chadi.k8s.queue;

public interface MessagePublisher {

    void publish(final String message);
}