package ru.innopolis.uni.course3.task1;

import java.io.*;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that handle resourse in thread
 * Created by julia on 09.12.2016.
 * @author Julia Savicheva
 */
public class ResourseHandler extends Thread implements Runnable {
    private String resourseName;
    private final Counter counter;


    public ResourseHandler(String resourseName, Counter counter) {
        this.resourseName = resourseName;
        this.counter = counter;
    }

    @Override
    public void run() {
        System.out.println(resourseName);
        parseFile();
    }

    private void parseFile() {
        if (isUrl(resourseName)) {
            try {
                URL url = new URL(resourseName);
                try (InputStreamReader fr = new InputStreamReader(url.openStream());
                     BufferedReader br = new BufferedReader(fr)){
                    parseReader(br);
                } catch (FileNotFoundException e) {
                    System.out.println(resourseName.concat(" file not found!"));
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {

                e.printStackTrace();
            }
        } else if (isFilename(resourseName)) {
            try (FileReader fr = new FileReader(resourseName);
                 BufferedReader br = new BufferedReader(fr)) {
                parseReader(br);

            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("ioexception");
                e.printStackTrace();
            }
        } else {
            System.out.println("Cannot handle resourse ".concat(resourseName));
        }
    }

    private boolean isCorrectNumber(BigInteger number) {
        boolean result = false;
        if ((number.compareTo(BigInteger.ZERO) == 1) && (number.remainder(new BigInteger("2")).equals(BigInteger.ZERO))) {
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

    private void parseReader(BufferedReader reader) throws IOException{
        StringBuilder buffer = new StringBuilder();
        int c;

        while ((c = reader.read()) != -1) {
            if (c == ' ') {
                BigInteger number = new BigInteger(buffer.toString());

                if (isCorrectNumber(number)) {
                    synchronized (this.counter) {
                        counter.increment(number);
//                        System.out.print(resourseName +  ": ");
                        System.out.println(counter.getCount());

                    }
                }
                buffer.setLength(0);
                buffer.trimToSize();
                continue;
            }
            buffer.append((char) c);
        }
    }

}
