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

    private static String allowedSymbols = "0123456789- \n\t\r";
    private static String separators = " \n\t\r";

    /**
     * ParserNumbers constructor
     * @param reader откуда будут читаться числа
     */
    public ParserNumbers(Reader reader) {
        this.reader = reader;
    }

    /**
     * Returns next number from Reader
     * @return next number
     * @throws IOException if incorrect symbol found
     * @throws NumberFormatException if number cannot be Integer
     */
    public Integer getNextNumber() throws IOException, NumberFormatException {
        StringBuilder buffer = new StringBuilder();
        Integer number = null;
        int c;

        while (((c = reader.read()) != -1)) {
            if (separators.indexOf(c) != -1) {
                if (buffer.length() == 0) {
                    continue;
                }
                try {
                    number = Integer.valueOf(buffer.toString());
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Incorrect number ".concat(buffer.toString()));
                }


                buffer.setLength(0);
                buffer.trimToSize();
                break;
            }
            if (allowedSymbols.indexOf(c) == -1) {
                throw new IOException("Incorrect symbol " + (char) c);
            }
            buffer.append((char) c);
        }
        return number;
    }
}
