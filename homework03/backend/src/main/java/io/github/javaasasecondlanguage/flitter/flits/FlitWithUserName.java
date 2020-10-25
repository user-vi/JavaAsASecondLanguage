package io.github.javaasasecondlanguage.flitter.flits;

public class FlitWithUserName {
    private String userName;
    private String content;

    public FlitWithUserName(String userName, String content) {
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
