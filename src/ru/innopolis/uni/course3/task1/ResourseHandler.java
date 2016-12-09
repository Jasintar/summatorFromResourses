package ru.innopolis.uni.course3.task1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
        parseFile();
//        while (!this.isInterrupted()) {
//            synchronized(counter) {
//
//            }
//        }
    }

    private void parseFile() {
        int currentLine = 0; // to show in which line is error

        try (FileReader fr = new FileReader(resourseName);
             BufferedReader br = new BufferedReader(fr);) {
            String str = null;

            while ((str=br.readLine()) != null) {
                String[] tokens = str.split(" ");
                for (String token: tokens) {
                    Integer number = Integer.valueOf(token);
                    if (isCorrectNumber(number)) {
                        synchronized (this.counter) {
                            counter.incremnt(number);
                            System.out.print(resourseName +  ": ");
                            System.out.println(counter.getCount());

                        }
                    }
//                    System.out.println(number);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("ioexception");
            e.printStackTrace();
        }
    }

    private boolean isCorrectNumber(int number) {
        boolean result = false;
        if ((number > 0) && (number % 2 == 0)) {
            result = true;
        }
        return result;
    }
/*
    private void parseResourse() {
        try (FileReader fr = new FileReader(resourseName);
             BufferedReader br = new BufferedReader(fr);) {
            String str = null;

            while ((str=br.readLine()) != null) {
                String[] tokens = str.split(" |=");
            }

        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("ioexception");
            e.printStackTrace();
        }
    }
*/

}
