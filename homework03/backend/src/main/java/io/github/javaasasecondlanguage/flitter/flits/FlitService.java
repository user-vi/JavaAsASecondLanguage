package io.github.javaasasecondlanguage.flitter.flits;

import io.github.javaasasecondlanguage.flitter.controller.Result;
import io.github.javaasasecondlanguage.flitter.flits.Flit;
import io.github.javaasasecondlanguage.flitter.flits.cache.FlitCache;
import io.github.javaasasecondlanguage.flitter.flits.FlitRegistrationResult;
import io.github.javaasasecondlanguage.flitter.flits.cache.TopElement;
import io.github.javaasasecondlanguage.flitter.services.UserService;

import java.util.*;

public class FlitService {
    //    private Map<String, String> flits = new HashMap<>();
//    private Map<String, String> flitUser = new HashMap<>();
//    private Map<String, String> userFlit = new HashMap<>();
    private FlitCache topFlits = new FlitCache(10);

    private UserService userService;
    List<FullFlit> allFLits = new ArrayList<>();




    public FlitService(UserService userService) {
        this.userService = userService;
    }

    public FlitRegistrationResult add(String userToken, String content) {
        String userName = userService.getUserByToken(userToken);
        if (userName != null) {
            Flit flit = new Flit(content, userToken);
            topFlits.add(new TopElement(userName, content));
            allFLits.add(new FullFlit(userName, flit));

            return FlitRegistrationResult.SUCCESS;
        } else {
            return FlitRegistrationResult.FAIL;
        }

    }

    public Result discoverFlits() {
        return new Result(topFlits.getTop(), null);
    }


    public Result getByUserName(String username) {
        if (userService.isUserNameRegistered(username)) {
            List<FlitWithUserName> list = new ArrayList<>();
            for (FullFlit flit : allFLits) {
                if (flit.getUserName().equals(username)) {
                    list.add(new FlitWithUserName(username, flit.getFlitContent()));
                }
            }
            return new Result(list, null);
        } else{
            return new Result(null, "User does not registered");
        }

    }

    public void clear() {
        topFlits.clear();
        allFLits.clear();
    }

}









