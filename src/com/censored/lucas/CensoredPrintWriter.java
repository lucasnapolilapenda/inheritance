package com.censored.lucas;

import java.io.*;
import java.util.Locale;

public class CensoredPrintWriter extends PrintWriter {

    public String [] censorTerms;


    public String client;


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

    @Override
    public PrintWriter append(char c) {
        setClient("" + c);
        return null;
    }

    @Override
    public void write(int c) {
        char d = (char) c;
        setClient(client + d);
    }

    @Override
    public void print(char c) {
        setClient(client + c);
    }

    @Override
    public void print(char[] s) {
        String add = "";
        for (int i = 0; i < s.length; i++) {
            add += String.valueOf(s[i]);
        }
        client = client + add;
        clientValidator();

    }

    public void clientValidator () {

        for (String censorTerm : censorTerms) {
            if (client.contains(censorTerm.replace("\"",""))) {
                append("****");
                client = "";
            }
        }
        append(client);
        client = "";
    }

    public String[] getCensorTerms() { return censorTerms; }

    public void setCensorTerms(String [] censorTerms) {
        this.censorTerms = censorTerms;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }


}
