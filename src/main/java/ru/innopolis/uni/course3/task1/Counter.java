package ru.innopolis.uni.course3.task1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

/**
 * Counter for numbers
 * Created on 09.12.2016.
 * @author Julia Savicheva
 */
public class Counter {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(Counter.class);
    /**
     * Current Counter value
     */
    private BigInteger count;

    /**
     * flag, that is true while all threads processing correctly
     * false, when happens incorrect processing
     */
    static volatile boolean correctProcessing;

    /**
     * Counter class constructor
     */
    public Counter() {
        this.count = BigInteger.ZERO;
        correctProcessing = true;
    }

    /**
     * @return current state of counter
     */
    public BigInteger getCount() {
        return count;
    }

    /**
     * Adds inc to Counter
     * @param inc value that will be added to counter
     */
    public void increment(BigInteger inc) {
        this.count = this.count.add(inc);
    }

    public void increment(Integer inc) {
        this.count = this.count.add(BigInteger.valueOf(inc));
    }
}
