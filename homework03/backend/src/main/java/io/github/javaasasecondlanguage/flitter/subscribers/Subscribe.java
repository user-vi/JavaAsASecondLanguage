package io.github.javaasasecondlanguage.flitter.subscribers;

public class Subscribe {
    private String subscribeToken;
    private String publisherToken;

    public Subscribe(String subscribeToken, String publisherToken) {
        this.subscribeToken = subscribeToken;
        this.publisherToken = publisherToken;
    }

    public String getSubscribeToken() {
        return subscribeToken;
    }

    public String getPublisherToken() {
        return publisherToken;
    }
}
