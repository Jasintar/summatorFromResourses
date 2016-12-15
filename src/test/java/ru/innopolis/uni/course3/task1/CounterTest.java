package ru.innopolis.uni.course3.task1;

import org.junit.*
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 15.12.2016.
 *
 * @authot Julia Savicheva
 */
public class CounterTest {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    private CounterTest counter;

    @Before
    public void initialize() {
        this.counter = new CounterTest();
    }

    @Test
    public void incrementTest() {
//        this.counter.increment(5);
        Assert.assertTrue(counter.);
    }
}
