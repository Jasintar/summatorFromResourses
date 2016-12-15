package ru.innopolis.uni.course3.task1;

import java.io.IOException;
import java.io.Reader;

/**
 * Created on 15.12.2016.
 *
 * @authot Julia Savicheva
 */
public class ParserNumbers {
    private Reader reader;
    private Counter counter;

    private static String allowedSymbols = "0123456789- \n\t\r";
    private static String separators = " \n\t\r";

    public ParserNumbers(Reader reader, Counter counter) {
//        logger.info(allowedSymbols);
        this.reader = reader;
        this.counter = counter;
    }

    public void parse() throws NumberFormatException, IOException {
        StringBuilder buffer = new StringBuilder();
        Integer number;
        int c;

        while (((c = reader.read()) != -1) && counter.correctProcessing) {
            if (separators.indexOf(c) != -1) {
                if (buffer.length() == 0) {
                    continue;
                }
                number = Integer.valueOf(buffer.toString());

                if (isCorrectNumber(number)) {
                    synchronized (this.counter) {
                        counter.increment(number);
//                        System.out.print(resourseName +  ": ");
                        System.out.println(counter.getCount().toString());

                    }
                }
                buffer.setLength(0);
                buffer.trimToSize();
                continue;
            }
            if (allowedSymbols.indexOf(c) == -1) {
                counter.correctProcessing = false;
//                logger.warn("Incorrect symbol in resource {}: {}", resourseName, (char)c);
//                System.out.println("incorrect symbol in file ".concat(resourseName).concat(": ") + (char)c);
            }
            buffer.append((char) c);
        }
    }

    private boolean isCorrectNumber(Integer number) {
        boolean result = false;
        if ((number > 0) && (number % 2 == 0)) {
            result = true;
        }
        return result;
    }
}
