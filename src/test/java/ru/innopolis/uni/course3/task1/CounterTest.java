package ru.innopolis.uni.course3.task1;

import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * Created on 15.12.2016.
 *
 * @authot Julia Savicheva
 */
public class CounterTest {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    private Counter counter;

    @Before
    public void initialize() {
        this.counter = new Counter();
    }

    @Test
    public void incrementTestInteger() {
        BigInteger six = new BigInteger("6");
        counter.increment(new Integer(6));
        assertTrue("incorrect increment", counter.getCount().equals(six));
    }
    @Test
    public void incrementTestBiginteger() {
        BigInteger six = new BigInteger("6");
        counter.increment(new BigInteger("6"));
        assertTrue("incorrect increment", counter.getCount().equals(six));
    }
}
