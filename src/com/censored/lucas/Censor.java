package com.censored.lucas;

import java.io.*;

public class Censor extends PrintWriter {


    public Censor(Writer out) {
        super(out);
    }

    public Censor(Writer out, boolean autoFlush) {
        super(out, autoFlush);
    }

    public Censor(OutputStream out) {
        super(out);
    }

    public Censor(OutputStream out, boolean autoFlush) {
        super(out, autoFlush);
    }

    public Censor(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    public Censor(String fileName, String csn) throws FileNotFoundException, UnsupportedEncodingException {
        super(fileName, csn);
    }

    public Censor(File file) throws FileNotFoundException {
        super(file);
    }

    public Censor(File file, String csn) throws FileNotFoundException, UnsupportedEncodingException {
        super(file, csn);
    }

    public String setCensorTerms(String[] censorTerms) {
        for (String aux : censorTerms) {
            return aux;
        }
        return "";
    }
}
