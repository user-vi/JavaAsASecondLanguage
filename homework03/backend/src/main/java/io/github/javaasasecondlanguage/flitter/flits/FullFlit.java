package io.github.javaasasecondlanguage.flitter.flits;

public class FullFlit extends Flit{
    private String userName;

    public FullFlit(String flitContent, String userToken, String userName) {
        super(flitContent, userToken);
        this.userName = userName;
    }

    public FullFlit(String userName, Flit flit) {
        this(flit.getFlitContent(), flit.getUserToken(), userName);
    }

    public String getUserName() {
        return userName;
    }
}
