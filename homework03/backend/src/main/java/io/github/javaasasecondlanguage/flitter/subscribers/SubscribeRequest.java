package io.github.javaasasecondlanguage.flitter.subscribers;

import org.springframework.web.bind.annotation.RequestBody;

public class SubscribeRequest {

    private String subscriberToken;
    private String publisherName;

    public SubscribeRequest() {
    }

    public void setSubscriberToken(String subscriberToken) {
        this.subscriberToken = subscriberToken;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getSubscriberToken() {
        return subscriberToken;
    }

    public String getPublisherName() {
        return publisherName;
    }
}
