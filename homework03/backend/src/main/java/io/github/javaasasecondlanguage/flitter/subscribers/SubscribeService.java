package io.github.javaasasecondlanguage.flitter.subscribers;

import io.github.javaasasecondlanguage.flitter.controller.Result;
import io.github.javaasasecondlanguage.flitter.services.UserService;

import java.util.ArrayList;
import java.util.List;

public class SubscribeService {

    private UserService userService;
    private List<Subscribe> subscribesList = new ArrayList<>();

    public SubscribeService(UserService userService) {
        this.userService = userService;
    }

    public Result subscribe(String subscribeToken, String publisherName) {
        if (userService.isUserTokenRegistered(subscribeToken) && userService.isUserNameRegistered(publisherName)) {
            subscribesList.add(new Subscribe(subscribeToken, publisherName));
            return new Result(null, null);
        } else {
            return new Result(null, "User not found");
        }
    }

    public Result unsubscribe(String subscribeToken, String publisherName) {
        if (userService.isUserTokenRegistered(subscribeToken) && userService.isUserNameRegistered(publisherName)) {
            subscribesList.removeIf(subscribe ->
                    subscribe.getSubscribeToken().equals(subscribeToken) &&
                            subscribe.getPublisherName().equals(publisherName)
                );
            return new Result(null, null);
        } else {
            return new Result(null, "User not found");
        }
    }

    public Result publisers(String userToken) {
        if (userService.isUserTokenRegistered(userToken)) {
            List<String> publishersOfCurrentSubscriber = new ArrayList<>();
            for (Subscribe subscribe : subscribesList) {
                if (subscribe.getSubscribeToken().equals(userToken)) {
                    publishersOfCurrentSubscriber.add(subscribe.getPublisherName());
                }
            }
            return new Result(publishersOfCurrentSubscriber, null);
        } else {
            return new Result(null, "User does not registered");
        }
    }

    public Result subscribers(String publisherToken) {
        if (userService.isUserTokenRegistered(publisherToken)) {
            List<String> publishersOfCurrentSubscriber = new ArrayList<>();
            String publisherName = userService.getUserByToken(publisherToken);
            for (Subscribe subscribe : subscribesList) {
                if (subscribe.getPublisherName().equals(publisherName)) {
                    String publisher = userService.getUserByToken(subscribe.getSubscribeToken());
                    publishersOfCurrentSubscriber.add(publisher);
                }
            }
            return new Result(publishersOfCurrentSubscriber, null);
        } else {
            return new Result(null, "User does not registered");
        }
    }

    public void clear() {
        subscribesList.clear();
    }
}
