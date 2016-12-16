package ru.innopolis.uni.course3.task1;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created on 16.12.2016.
 *
 * @authot Julia Savicheva
 */
public class StreamReaderTest {
    private StreamReader streamReader;

    @Test(expected = IOException.class)
    public void constructorExceptionTest() throws IOException {
        streamReader = new StreamReader("oiuytre");
    }

    @Test(expected = IOException.class)
    public void constructorFileNotFoundTest() throws IOException {
        streamReader = new StreamReader("oiuytre.txt");
    }

    @Test
    public void readTest() throws IOException {
        streamReader = mock(StreamReader.class);
        when(streamReader.read()).thenReturn((int) 'c');
        assertEquals(streamReader.read(), (int) 'c');
    }
}
