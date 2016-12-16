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

    /**
     * Constructor of multy-thread Summator
     * @param resources resources list
     */
    public SummatorFromResources(String[] resources) {
        this.resources = resources;
    }

    /**
     * calculate all numbers, each resource calculates in own thread
     */
    public void sumAll() {
        for (String resource: resources) {
            Thread t = new ResourceHandler(resource, counter);
            t.start();
        }
    }
}
