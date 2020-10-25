package io.github.javaasasecondlanguage.flitter.subscribers;

import io.github.javaasasecondlanguage.flitter.controller.Result;
import io.github.javaasasecondlanguage.flitter.services.UserService;

import java.util.ArrayList;
import java.util.List;

public class SubscribeService {

    private UserService userService;
    private  List<Subscribe> subscribesList = new ArrayList<>();

    public SubscribeService(UserService userService) {
        this.userService = userService;
    }

    public Result subscribe(String subscribeToken, String publisherToken){
        if (userService.isUserTokenRegistered(subscribeToken) && userService.isUserTokenRegistered(publisherToken)) {
            subscribesList.add(new Subscribe(subscribeToken, publisherToken));
            return new Result(null, null);
        } else{
            return new Result(null, "User does not registered");
        }
    }

    public Result list(String subscriberToken) {
        if (userService.isUserTokenRegistered(subscriberToken)) {
            List<String> publishersOfCurrentSubscriber = new ArrayList<>();
            for (Subscribe subscribe: subscribesList){
                if (subscribe.getSubscribeToken() ==  subscriberToken){
                    String publisher = userService.getUserByToken(subscribe.getPublisherToken());
                    publishersOfCurrentSubscriber.add(publisher);
                }
            }
            return new Result(publishersOfCurrentSubscriber, null);
        } else{
            return new Result(null, "User does not registered");
        }
    }

    public void clear(){
        subscribesList.clear();
    }
}
