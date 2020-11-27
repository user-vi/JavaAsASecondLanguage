package io.github.javaasasecondlanguage.lecture09.practice1;

import java.util.ArrayList;
import java.util.List;

public class GarbageGenerator {
    static final int MEGABYTE_IN_BYTES = 1024 * 1024;
    static final int ITERATION_COUNT = 1024 * 10;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Starting generating garbage");

        //List<byte[]> garbageBin = new ArrayList<>();
        for (int i = 0; i < ITERATION_COUNT; i++) {
            System.out.println(i);
            byte[] array = new byte[MEGABYTE_IN_BYTES];
            //garbageBin.add(array);
        }

        System.out.println("Finished");
    }
}
