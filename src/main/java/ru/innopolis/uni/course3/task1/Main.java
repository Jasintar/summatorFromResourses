package ru.innopolis.uni.course3.task1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 09.12.2016.
 * @authot Julia Savicheva
 */
public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    private static Counter counter = new Counter();

    public static void main(String[] args) {
        logger.warn("message");

        for (String resourse: args) {
            Thread t = new ResourseHandler(resourse, counter);
            t.start();
        }
        logger.info("Message");
        System.out.println(counter.getCount());
    }
}
