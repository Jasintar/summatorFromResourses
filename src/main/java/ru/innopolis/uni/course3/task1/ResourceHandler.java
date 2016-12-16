package ru.innopolis.uni.course3.task1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created on 16.12.2016.
 *
 * @authot Julia Savicheva
 */
public class ResourceHandler extends Thread {
    private static Logger logger = LoggerFactory.getLogger(ResourceHandler.class);

    private String resourseName;
    private final Counter counter;
    private StreamReader reader;
    private ParserNumbers parser;

    /**
     * ResourceHandler constructor
     * @param resourceName name of file or URL
     * @param counter new numbers adds to Counter
     */
    public ResourceHandler(String resourceName, Counter counter) {
        this.resourseName = resourceName;
        this.counter = counter;
        try {
            reader = new StreamReader(resourceName);
            parser = new ParserNumbers(reader);
        } catch (MalformedURLException e) {
            logger.warn("Incorrect URL {} cannot readed", resourceName);
            this.counter.correctProcessing = false;
        } catch (IOException e) {
            logger.warn("{} in resource {}", e.getMessage(), resourceName);
            this.counter.correctProcessing = false;
        }

    }

    /**
     * Parse resourse, and adds valid numbers to counter
     */
    @Override
    public void run() {
        parseResource();
    }

    private void parseResource() {
        logger.info("Starts handling of resource {}", resourseName);
        Integer number;
        try {
            while (counter.correctProcessing && (number = parser.getNextNumber()) != null) {
                if (isCorrectNumber(number)) {
                    synchronized (this.counter) {
                        logger.info("Added number {} from resource {}", number, resourseName);
                        counter.increment(number);
                        logger.info("Current sum: {}", counter.getCount());
                    }
                }
            }
        } catch (NumberFormatException e) {
            logger.warn("{} in resource {}", e.getMessage(), resourseName);
        } catch (IOException e) {
            counter.correctProcessing = false;
            logger.warn("{} in resource {}", e.getMessage(), resourseName);
        }
        logger.info("Resource {} handeled", resourseName);
    }

    private boolean isCorrectNumber(Integer number) {
        boolean result = false;
        if ((number > 0) && (number % 2 == 0)) {
            result = true;
        }
        return result;
    }

}
