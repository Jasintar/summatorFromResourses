package ru.innopolis.uni.course3.task1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that handle resourse in thread
 * Created on 09.12.2016.
 * @author Julia Savicheva
 */
public class ResourseHandler extends Thread implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(ResourseHandler.class);

    private String resourseName;
    private final Counter counter;

    private static String allowedSymbols = "0123456789- ";

    /**
     * ResourseHandler constructor
     * @param resourceName name of file or URL
     * @param counter new numbers adds to Counter
     */
    public ResourseHandler(String resourceName, Counter counter) {
        this.resourseName = resourceName;
        this.counter = counter;
    }

    /**
     * Parse resourse, and adds numbers to counter
     */
    @Override
    public void run() {
        System.out.println(resourseName);
        parseFile();
    }

    private void parseFile() {
        BufferedReader bufferedReader = null;
        try {
            if (isUrl(resourseName)) {
                URL url = new URL(resourseName);
                bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            } else if (isFilename(resourseName)) {
                bufferedReader = new BufferedReader(new FileReader(resourseName));
            }
            parseReader(bufferedReader);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean isCorrectNumber(Integer number) {
        boolean result = false;
        if ((number > 0) && (number % 2 == 0)) {
            result = true;
        }
        return result;
    }

    private boolean isUrl(String resourseName) {
        Pattern p = Pattern.compile("^(ht|f)tp(s?)\\:\\/\\/[0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*(:(0-9)*\u200C\u200B)*(\\/?)([a-zA-Z0-9\\-\u200C\u200B\\.\\?\\,\\'\\/\\\\\\+&amp;%\u200C\u200B\\$#_]*)?$");
        Matcher m = p.matcher(resourseName);
        return m.matches();
    }

    private boolean isFilename(String resourseName) {
        Pattern p = Pattern.compile("^([A-Za-z]:)*[^:<>\\*\\?\\|\"]+.txt$");
        Matcher m = p.matcher(resourseName);
        return m.matches();
    }

    private void parseReader(BufferedReader reader) throws NumberFormatException, IOException {
        StringBuilder buffer = new StringBuilder();
        Integer number;
        int c;

        while (((c = reader.read()) != -1) && counter.correctProcessing) {
            if (c == ' ') {
                number = Integer.valueOf(buffer.toString());


                if (isCorrectNumber(number)) {
                    synchronized (this.counter) {
                        counter.increment(number);
                        System.out.print(resourseName +  ": ");
                        System.out.println(counter.getCount().toString());

                    }
                }
                buffer.setLength(0);
                buffer.trimToSize();
                continue;
            }
            if (allowedSymbols.indexOf(c) == -1) {
                counter.correctProcessing = false;
                logger.warn("Incorrect symbol in resource {}: {}", resourseName, (char)c);
//                System.out.println("incorrect symbol in file ".concat(resourseName).concat(": ") + (char)c);


            }
            buffer.append((char) c);
        }
    }

}
