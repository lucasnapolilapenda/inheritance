package com.censored.lucas;

import java.io.*;
import java.util.Locale;

public class CensoredPrintWriter extends PrintWriter {

    String [] censorTerms;


    public CensoredPrintWriter(Writer out) {
        super(out);
    }

    public CensoredPrintWriter(Writer out, boolean autoFlush) {
        super(out, autoFlush);
    }

    public CensoredPrintWriter(OutputStream out) {
        super(out);
    }

    public CensoredPrintWriter(OutputStream out, boolean autoFlush) {
        super(out, autoFlush);
    }

    public CensoredPrintWriter(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    public CensoredPrintWriter(String fileName, String csn) throws FileNotFoundException, UnsupportedEncodingException {
        super(fileName, csn);
    }

    public CensoredPrintWriter(File file) throws FileNotFoundException {
        super(file);
    }

    public CensoredPrintWriter(File file, String csn) throws FileNotFoundException, UnsupportedEncodingException {
        super(file, csn);
    }


    @Override
    public PrintWriter printf(String format, Object... args) {
        String xCensored = format;
        String [] censor = getCensorTerms();
        String secret = "****";

        for (int i = 0; i < args.length; i++) {
            for (int i1 = 0; i1 < censor.length; i1++) {
                if (args[i].toString().equals(censor[i1])) {
                    args[i] = secret;
                }
            }
        }

        for (String censorTerm : censorTerms) {
            if (xCensored.contains(censorTerm.replace("\"",""))) {
                xCensored = xCensored.replaceAll(censorTerm.replace("\"", ""), "****");
            }
        }
        xCensored = xCensored.replaceAll("%d","%s");
        return super.format(xCensored, args);
    }


    @Override
    public void println(String x) {
        String xCensored = x;
        synchronized (lock) {
            for (String censorTerm : censorTerms) {
                if (x.contains(censorTerm.replace("\"", ""))) {
                    xCensored = xCensored.replaceAll(censorTerm.replace("\"", ""), "****");
                }
            }
        }
        super.print(xCensored);
        super.println();
    }


    @Override
    public PrintWriter format(Locale l, String format, Object... args) {
        return super.format(l, format, args);
    }

    public String[] getCensorTerms() { return censorTerms; }

    public void setCensorTerms(String [] censorTerms) {
        this.censorTerms = censorTerms;
    }
}
