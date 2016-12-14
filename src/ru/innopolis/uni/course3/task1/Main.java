package ru.innopolis.uni.course3.task1;

/**
 * Created on 09.12.2016.
 * @authot Julia Savicheva
 */
public class Main {
    private static Counter counter = new Counter();

    public static void main(String[] args) {
        for (String resourse: args) {
            Thread t = new ResourseHandler(resourse, counter);
            t.start();
        }
        System.out.println(counter.getCount());
    }
}
