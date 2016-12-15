package ru.innopolis.uni.course3.task1;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 15.12.2016.
 *
 * @authot Julia Savicheva
 */
public class StreamReader extends Reader implements Closeable, AutoCloseable{

    private String resourceName;
    private BufferedReader reader;

    public StreamReader(String resourceName) {
        try {

            if (isUrl(resourceName)) {
                URL url = new URL(resourceName);
                this.reader = new BufferedReader(new InputStreamReader(url.openStream()));
            } else if (isFilename(resourceName)) {
                this.reader = new BufferedReader(new FileReader(resourceName));
            } else {
//                не могу открыть ресурс {} на чтение
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }

    public String getResourceName() {
        return this.resourceName;
    }

    public int read() throws IOException {
        return reader.read();
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return reader.read(cbuf, off, len);
    }

    public BufferedReader getReader() {
        return this.reader;
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
}
