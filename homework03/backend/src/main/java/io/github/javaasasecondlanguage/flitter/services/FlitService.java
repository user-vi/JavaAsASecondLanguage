package io.github.javaasasecondlanguage.flitter.services;

import io.github.javaasasecondlanguage.flitter.controller.Result;
import io.github.javaasasecondlanguage.flitter.flits.Flit;
import io.github.javaasasecondlanguage.flitter.flits.cache.FlitCache;
import io.github.javaasasecondlanguage.flitter.flits.FlitRegistrationResult;
import io.github.javaasasecondlanguage.flitter.flits.cache.TopElement;

import java.util.*;

public class FlitService {
    //    private Map<String, String> flits = new HashMap<>();
//    private Map<String, String> flitUser = new HashMap<>();
//    private Map<String, String> userFlit = new HashMap<>();
    private FlitCache topFlits = new FlitCache(10);
    private UserService userService;
    List<String> flitsArray = new ArrayList<>();
    private Map<String, List<Flit>> userFlit = new HashMap<>();

    public FlitService() {
    }

    public FlitService(UserService userService) {
        this.userService = userService;
    }

    public FlitRegistrationResult add(String userToken, String content) {
        String userName = userService.getUserByToken(userToken);
        if (userName != null) {
            Flit flit = new Flit(content, userToken);
            topFlits.add(new TopElement(userName, content));

//            List<Flit> flitsOfUser = userFlit.get(userToken);
//            if ()
//            if (flitsOfUser != null ){
//                flitsOfUser.add(flit);
//            }else{
//                userFlit.put(userToken, Arrays.asList(flit));
//            }
//            flits.put(flitToken, content);
//            flitUser.put(flitToken, userToken);
//
//            userFlit.put(userName, content);
            return FlitRegistrationResult.SUCCESS;
        } else {
            return FlitRegistrationResult.FAIL;
        }

    }

    public Result discoverFlits() {
        return new Result(topFlits.getTop(), null);
    }

//    Set<String> userFlitList (String userName) {
//
//    }

    public void clear() {
        topFlits.clear();
        userFlit.clear();
    }


}









