package ru.innopolis.uni.course3.task1;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

/**
 * Created on 14.12.2016.
 *
 * @authot Julia Savicheva
 */
public class MailAppender extends AppenderSkeleton {

    @Override
    protected void append(LoggingEvent event) {
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println(event.getMessage());
        System.out.println("++++++++++++++++++++++++++++++++++");
    }

    @Override
    public void close() {

    }

    @Override
    public boolean requiresLayout() {
        return false;
    }
}
