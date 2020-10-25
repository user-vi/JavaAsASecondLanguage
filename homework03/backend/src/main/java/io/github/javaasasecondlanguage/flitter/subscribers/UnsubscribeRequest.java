package io.github.javaasasecondlanguage.flitter.subscribers;

public class UnsubscribeRequest {

    private String subscriberToken;
    private String publisherName;

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
