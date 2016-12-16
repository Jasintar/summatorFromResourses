package ru.innopolis.uni.course3.task1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 15.12.2016.
 *
 * @authot Julia Savicheva
 */
public class StreamReader extends Reader implements Closeable, AutoCloseable{
    private static Logger logger = LoggerFactory.getLogger(StreamReader.class);

    private BufferedReader reader;

    /**
     * Constructor of StreamReader
     * @param resourceName name of resource
     * @throws IOException
     */
    public StreamReader(String resourceName) throws IOException {
        switch (getResourceType(resourceName)){
            case "url":
                URL url = new URL(resourceName);
                this.reader = new BufferedReader(new InputStreamReader(url.openStream()));
                break;
            case "file":
                this.reader = new BufferedReader(new FileReader(resourceName));
                break;
            default:
                logger.warn("Cannot open resource {}.", resourceName);
                throw new IOException("Cannot open resource ".concat(resourceName));
        }
    }

    /**
     * Overrided close method
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        reader.close();
    }

    /**
     * @return next byte
     * @throws IOException
     */
    public int read() throws IOException {
        return reader.read();
    }

    /**
     * Overrided method of Reader
     * @param cbuf
     * @param off
     * @param len
     * @return
     * @throws IOException
     */
    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return reader.read(cbuf, off, len);
    }

    private String getResourceType(String resourceName) {
        String result = "unknown";
        Pattern pUrl = Pattern.compile("^(ht|f)tp(s?)\\:\\/\\/[0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*(:(0-9)*\u200C\u200B)*(\\/?)([a-zA-Z0-9\\-\u200C\u200B\\.\\?\\,\\'\\/\\\\\\+&amp;%\u200C\u200B\\$#_]*)?$");
        Matcher mUrl = pUrl.matcher(resourceName);

        Pattern pFile = Pattern.compile("^([A-Za-z]:)*[^:<>\\*\\?\\|\"]+.txt$");
        Matcher mFile = pFile.matcher(resourceName);
        if (mUrl.matches()) {
            result = "url";
        }
        if (mFile.matches()) {
            result = "file";
        }
        return result;
    }
}
