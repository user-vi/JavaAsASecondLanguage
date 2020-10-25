package io.github.javaasasecondlanguage.flitter.subscribers;

public class Subscribe {
    private String subscribeToken;
    private String publisherName;

    public Subscribe(String subscribeToken, String publisherToken) {
        this.subscribeToken = subscribeToken;
        this.publisherName = publisherToken;
    }

    public String getSubscribeToken() {
        return subscribeToken;
    }

    public String getPublisherName() {
        return publisherName;
    }
}
