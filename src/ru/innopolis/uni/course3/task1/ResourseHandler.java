package ru.innopolis.uni.course3.task1;

/**
 * Created by julia on 09.12.2016.
 */
public class ResourseHandler extends Thread implements Runnable {
    private String resourseName;
    private Counter counter;

    public ResourseHandler(String resourseName, Counter counter) {
        this.resourseName = resourseName;
        this.counter = counter;
    }

    @Override
    public void run() {
        System.out.println(resourseName);
//        while (!this.isInterrupted()) {
//            synchronized(counter) {
//
//            }
//        }
    }

}
