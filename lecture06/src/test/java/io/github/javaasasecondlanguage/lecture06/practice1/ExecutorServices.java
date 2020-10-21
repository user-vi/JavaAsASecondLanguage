package io.github.javaasasecondlanguage.lecture06.practice1;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServices {
    @Test
    void executorService() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        Future<Boolean> success = executor.submit(() -> {
            System.out.println("Hello from [" + Thread.currentThread().getName() + "]");
            Thread.sleep(10000);
            return true;
        });
        Boolean result = success.get();
        System.out.println("Main thread [" + Thread.currentThread().getName() + "] received result " + result);
    }

    /**
     * Get latest topic
     * https://hacker-news.firebaseio.com/v0/maxitem.json
     *
     * Get item data
     * https://hacker-news.firebaseio.com/v0/item/126809.json
     */
    @Test
    void getLatestHackerNewsTopic(){
        throw new RuntimeException("Not Implemented");
    }
}
