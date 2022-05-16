package com.example.demo.util;

import java.io.IOException;
import java.io.InputStream;

public class InputStreamConsumer extends Thread {

    private InputStream is;
    private IOException exp;
    private StringBuilder output;

    public InputStreamConsumer(InputStream is) {
        this.is = is;
    }

    @Override
    public void run() {
        int in = -1;
        output = new StringBuilder(64);
        try {
            while ((in = is.read()) != -1) {
                output.append((char) in);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            exp = ex;
        }
    }

    public StringBuilder getOutput() {
        return output;
    }

    public IOException getException() {
        return exp;
    }
}
