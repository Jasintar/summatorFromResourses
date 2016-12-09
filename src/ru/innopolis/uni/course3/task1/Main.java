package ru.innopolis.uni.course3.task1;

/**
 * Created by julia on 09.12.2016.
 */
public class Main {
    public static Counter counter = new Counter();

    public static void main(String[] args) {
        for (String resourse: args) {
            Thread t = new ResourseHandler(resourse, counter);
            t.start();
        }
    }
}
