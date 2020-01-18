package com.censored.lucas;

import java.io.*;

public class Censor extends PrintWriter {

    String [] censorTerms;

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


    @Override
    public PrintWriter printf(String format, Object... args) {
        for (String censorTerm : censorTerms) {
            for (int i = 0; i < args.length ; i++) {
                if (censorTerm.equals(args[i])) {
                    args[i] = "*";
                }
            }
        }
        return super.printf(format, args);
    }

    public void setCensorTerms(String [] censorTerms) {
        this.censorTerms = censorTerms;
    }
}
