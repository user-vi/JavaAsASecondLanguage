package io.github.javaasasecondlanguage.flitter.services;

import io.github.javaasasecondlanguage.flitter.user.Flit;

import java.lang.reflect.Array;
import java.util.*;

public class FlitService {
    private Map<String, String> flits = new HashMap<>();
    private Map<String, String> flitUser = new HashMap<>();
    private Map<String, String> userFlit = new HashMap<>();
    private UserService userService ;
    ArrayList<String> flitsArray;

    public FlitService() {
    }

    public FlitService(UserService userService) {
        this.userService = userService;
    }

    public void add(String userToken, String content) {
        Flit flit = new Flit(content);
        String flitToken = flit.getFlitToken();
        flitsArray.add(flitToken);

        flits.put(flitToken, content);
        flitUser.put(flitToken, userToken);

        String userName = userService.getUserByToken(userToken); //переделать
        userFlit.put(userName, content);
    }

    public Map<String, String> discoverFlits() {
        Map<String, String> result = new HashMap<>();
        for (int i = 1; i < 11; i++) {
            // last token
            Integer lastIndex = flitsArray.size() - i;
            String lastFlitToken = flitsArray.get(lastIndex);
            if (lastFlitToken == null) {
                break;
            }

            //last massage
            String lastFlitContent = flits.get(lastFlitToken);

            // user of massage
            String userToken = flits.get(lastFlitToken);
            String userName = userService.getUserByToken(userToken);

            //put in map
            result.put(userName, lastFlitContent);
        }
        return result;

    }

//    Set<String> userFlitList (String userName) {
//
//    }


}









