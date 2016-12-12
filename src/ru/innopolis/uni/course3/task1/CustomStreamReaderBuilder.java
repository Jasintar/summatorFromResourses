package ru.innopolis.uni.course3.task1;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by julia on 10.12.2016.
 */
public class CustomStreamReaderBuilder extends FileReader {


    public CustomStreamReaderBuilder(String resourseName) throws FileNotFoundException {
//        InputStream reader;
        if (isFilename(resourseName)) {
            System.out.println("its file");
//            super(resourseName);
        }

        super(resourseName);

    }

    private boolean isUrl(String resourseName) {
        Pattern p = Pattern.compile("^(?:([a-z]+):(?:([a-z]*):)?\\/\\/)?(?:([^:@]*)(?::([^:@]*))?@)?((?:[a-z0-9_-]+\\.)+[a-z]{2,}|localhost|(?:(?:[01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}(?:(?:[01]?\\d\\d?|2[0-4]\\d|25[0-5])))(?::(\\d+))?(?:([^:\\?\\#]+))?(?:\\?([^\\#]+))?(?:\\#([^\\s]+))?$");
        Matcher m = p.matcher(resourseName);
        return m.matches();
    }

    private boolean isFilename(String resourseName) {
        Pattern p = Pattern.compile("^[^:<>\\*\\?\\|\"]{3,15}$");
        Matcher m = p.matcher(resourseName);
        return m.matches();
    }
}
