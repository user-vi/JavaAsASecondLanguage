package io.github.javaasasecondlanguage.lecture06.practice1;

import org.junit.jupiter.api.Test;

public class Threads {
    @Test
    void runnable() throws InterruptedException {
        var t = new Thread(() ->
        {
            System.out.println("Hello from [" + Thread.currentThread().getName() + "]");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
        System.out.println("Main thread [" + Thread.currentThread().getName() + "] before join");
        t.join();
        System.out.println("Main thread [" + Thread.currentThread().getName() + "] after join");
    }
}
