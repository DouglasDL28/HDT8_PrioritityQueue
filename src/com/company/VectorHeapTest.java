package com.company;

// Es importante notar que las únicas funciones que se utilizan del VectorHeap son las que implementa PriorityQueue.
public class VectorHeapTest {
    VectorHeap<Integer> test = new VectorHeap<>();

    @org.junit.Test
    public void remove() {
        test.add(1);
        test.add(2);

        System.out.println(test.remove());
    }

    @org.junit.Test
    public void getFirst() {
        test.add(1);
        test.add(2);

        System.out.println(test.getFirst());
    }

    @org.junit.Test
    public void isEmpty() {
        System.out.println(test.isEmpty());

        test.add(1);

        System.out.println(test.isEmpty());
    }

    @org.junit.Test
    public void size() {
        test.add(1);
        test.add(2);

        System.out.println(test.size());
    }

    @org.junit.Test
    public void clear() {
        test.add(1);
        test.add(2);
        System.out.println(test.size());

        test.clear();

        System.out.println(test.size());
    }
}