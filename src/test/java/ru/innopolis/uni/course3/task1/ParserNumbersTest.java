package ru.innopolis.uni.course3.task1;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created on 16.12.2016.
 *
 * @authot Julia Savicheva
 */
public class ParserNumbersTest {

    ParserNumbers parser;

    @Test(expected = IOException.class)
    public void incorrectSymbolTest() throws IOException {
        StreamReader streamReader = mock(StreamReader.class);
        when(streamReader.read()).thenReturn((int) 'c');
        parser = new ParserNumbers(streamReader);
        Integer i = parser.getNextNumber();

    }

    @Test
    public void twoOrMoreSeparatorsTest() throws IOException {
        StreamReader streamReader = mock(StreamReader.class);
        when(streamReader.read()).thenReturn((int) ' ').thenReturn((int) ' ').thenReturn((int) '1').thenReturn((int) '1').thenReturn((int) ' ');
        parser = new ParserNumbers(streamReader);
        Integer i = parser.getNextNumber();
        assertEquals((long) i, (long) 11);
    }

    @Test(expected = NumberFormatException.class)
    public void bigNumberTest() throws IOException {
        StreamReader streamReader = mock(StreamReader.class);
        when(streamReader.read()).thenReturn((int) '9').thenReturn((int) '9').thenReturn((int) '1').thenReturn((int) '1').thenReturn((int) '9').thenReturn((int) '9').thenReturn((int) '9').thenReturn((int) '9').thenReturn((int) '9').thenReturn((int) '9').thenReturn((int) '9').thenReturn((int) '9').thenReturn((int) '9').thenReturn((int) '9').thenReturn((int) '9').thenReturn((int) ' ');
        parser = new ParserNumbers(streamReader);
        Integer i = parser.getNextNumber();
    }
}
