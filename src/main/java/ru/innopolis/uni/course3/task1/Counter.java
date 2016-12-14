package ru.innopolis.uni.course3.task1;

import java.math.BigInteger;

/**
 * Counter for numbers
 * Created on 09.12.2016.
 * @author Julia Savicheva
 */
public class Counter {
    /**
     * Current Counter value
     */
    private BigInteger count;

    /**
     * Counter class constructor
     */
    public Counter() {
        this.count = BigInteger.ZERO;
    }

    /**
     * @return current state of counter
     */
    public String getCount() {
        return count.toString();
    }

    /**
     * @param inc value that will be added to counter
     */
    public void increment(BigInteger inc) {
        this.count = this.count.add(inc);
    }
}
