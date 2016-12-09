package ru.innopolis.uni.course3.task1;

/**
 * Created by julia on 09.12.2016.
 */
public class Counter {
    private int count;

    public Counter() {
        this.count = 0;
    }

    public int getCount() {
        return count;
    }

    /*public void setCount(int count) {
        this.count = count;
    }*/

    public void incremnt(int inc) {
        this.count += inc;
    }
}
