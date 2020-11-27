package io.github.javaasasecondlanguage.lecture09.practice2;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ObjectLayout {
    public static void guess() throws Exception {
        System.out.println(VM.current().details());
        int[] intArray = new int[1000];
        Integer[] integerArray = new Integer[1000];
        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();
        HashSet<Integer> hashSet = new HashSet<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        ConcurrentHashMap<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();

        for (int i = 0; i < 1000; i++) {
            Integer io = i;
            intArray[i] = io;
            integerArray[i] = io;
            arrayList.add(io);
            linkedList.add(io);
            hashSet.add(io);
            hashMap.put(i, i);
            concurrentHashMap.put(i, i);
        }

        arrayList.trimToSize();

        System.out.println("====================================================================");
        System.out.println(">> new Integer(10)");
        System.out.println(GraphLayout.parseInstance(new Integer(10)).toPrintable());
        System.out.println("====================================================================");
        System.out.println(">> new Long(10)");
        System.out.println(GraphLayout.parseInstance(new Long(10)).toPrintable());
        System.out.println("====================================================================");
        System.out.println(">> new int[1000]");
        System.out.println(GraphLayout.parseInstance((Object) intArray).toFootprint());
        System.out.println("====================================================================");
        System.out.println(">> new Integer[1000]");
        System.out.println(GraphLayout.parseInstance((Object) integerArray).toFootprint());
        System.out.println("====================================================================");
        System.out.println(">> new ArrayList<Integer>(1000)");
        System.out.println(GraphLayout.parseInstance(arrayList).toFootprint());
        System.out.println("====================================================================");
        System.out.println(">> new LinkedList<Integer>(1000)");
        System.out.println(GraphLayout.parseInstance(linkedList).toFootprint());
        System.out.println("====================================================================");
        System.out.println(">> new HashSet<Integer>(1000)");
        System.out.println(GraphLayout.parseInstance(hashSet).toFootprint());
        System.out.println("====================================================================");
        System.out.println(">> new HashMap<Integer>(1000)");
        System.out.println(GraphLayout.parseInstance(hashMap).toFootprint());
        System.out.println("====================================================================");
        System.out.println(">> new ConcurrentHashMap<Integer>(1000)");
        System.out.println(GraphLayout.parseInstance(concurrentHashMap).toFootprint());
        System.out.println("====================================================================");
        System.out.println(">> new ArrayList<Integer>(1000).stream()");
        System.out.println(GraphLayout.parseInstance(arrayList.stream()).toFootprint());
        System.out.println("====================================================================");
        System.out.println(">> new String(\"Hello, World!\")");
        System.out.println(GraphLayout.parseInstance("Hello, World!").toPrintable());
        System.out.println("====================================================================");
        System.out.println(">> new Object()");
        System.out.println(GraphLayout.parseInstance(new Object()).toPrintable());
        System.out.println("====================================================================");
        System.out.println(">> new RegularClass()");
        System.out.println(GraphLayout.parseInstance(new RegularClass()).toPrintable());
        System.out.println("====================================================================");
        System.out.println(">> new Record(5)");
        System.out.println(GraphLayout.parseInstance(new Record(5)).toPrintable());
        System.out.println("====================================================================");
    }

    /**
     * Try same with -XX:-UseCompressedOops - header layout will change on 64 arch because class reference will become bigger
     * @throws Exception
     */
    public static void internals() throws Exception {
        System.out.println("========== Layout of Object instance ==========");
        Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());

        System.out.println("========== Initial layout of RegularClass instance ==========");
        RegularClass instance = new RegularClass();
        System.out.println(ClassLayout.parseInstance(instance).toPrintable());

        System.out.println("========== Identity hashcode - instantiated lazily ==========");
        System.identityHashCode(instance);
        System.out.println(ClassLayout.parseInstance(instance).toPrintable());
        for(int i=0;i<10000000;i++){
            new Object();
        }

        System.out.println("========== After several GCs - keep tracking of number of survivals ==========");
        System.out.println(ClassLayout.parseInstance(instance).toPrintable());

        System.out.println("========== After locking on object - the layout changes t========== ");
        synchronized (instance){
            System.out.println(ClassLayout.parseInstance(instance).toPrintable());
        }
    }

    static class RegularClass{}

    static record Record(int x){}
}
