package io.github.javaasasecondlanguage.flitter;

import io.github.javaasasecondlanguage.flitter.flits.FlitService;
import io.github.javaasasecondlanguage.flitter.services.UserService;
import io.github.javaasasecondlanguage.flitter.subscribers.SubscribeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public FlitService getFlitService(UserService userService, SubscribeService subscribeService){
        return new FlitService(userService, subscribeService);
    }

    @Bean
    public UserService getUserService(){
        return new UserService();
    }

    @Bean
    public SubscribeService subscribeService(UserService userService){
        return new SubscribeService(userService);
    }

}
