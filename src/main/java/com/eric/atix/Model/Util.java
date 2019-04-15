package com.eric.atix.Model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Util {
    public static String getProperty(String property) {
        String prop = "";
        try {
            InputStream in = ClassLoader.getSystemResourceAsStream("Sensitive.conf");
            Properties props = new Properties();
            props.load(in);
            prop = props.getProperty(property);
            in.close();
        } catch (IOException ex) {
            return prop;
        }
        return prop;
    }
}
