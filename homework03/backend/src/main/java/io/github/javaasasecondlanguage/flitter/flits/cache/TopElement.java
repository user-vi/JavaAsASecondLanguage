package io.github.javaasasecondlanguage.flitter.flits.cache;

public class TopElement {
    private String userName;
    private String content;

    public TopElement() {
    }

    public TopElement(String userName, String content) {
        this.userName = userName;
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public String getContent() {
        return content;
    }
}
