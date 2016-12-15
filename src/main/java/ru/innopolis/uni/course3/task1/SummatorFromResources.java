package ru.innopolis.uni.course3.task1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 14.12.2016.
 *
 * @authot Julia Savicheva
 */
public class SummatorFromResources {
    private static Logger logger = LoggerFactory.getLogger(SummatorFromResources.class);

    private static final Counter counter = new Counter();
    String[] resources;

    public SummatorFromResources(String[] resources) {
        this.resources = resources;
    }

    public void sumAll() {
        for (String resource: resources) {
            Thread t = new ResourseHandler(resource, counter);
            t.start();
        }
    }
}
