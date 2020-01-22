package com.censored.lucas;

import java.io.*;
import java.util.Locale;

public class CensoredPrintWriter extends PrintWriter {

    public String [] censorTerms;
    public String client;
    public Boolean positionCensored = true;

    @Override
    public PrintWriter printf(String format, Object... args) {
        String xCensored = format;
        String [] censor = getCensorTerms();
        String secret = "****";

        if (!getPositionCensored()) {
            for (int i = 0; i < args.length; i++) {
                for (int i1 = 0; i1 < censor.length; i1++) {
                    if (args[i].toString().equals(censor[i1])) {
                        args[i] = secret;
                    }
                }
            }

            for (String censorTerm : censorTerms) {
                if (xCensored.contains(censorTerm.replace("\"", ""))) {
                    xCensored = xCensored.replaceAll(censorTerm.replace("\"", ""), "****");
                }
            }
            xCensored = xCensored.replaceAll("%d", "%s");
            return super.format(xCensored, args);
        }

        if (getPositionCensored()) {
            String [] arg2 = {"****"};
            xCensored = xCensored.replaceAll("%d", "****");
            super.print(xCensored);
        }

        return null;
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
        setClient(getClient() + d);
    }

    @Override
    public void print(char c) {
        setClient(getClient() + c);
    }

    @Override
    public void print(char[] s) {
        String add = "";
        for (int i = 0; i < s.length; i++) {
            add += String.valueOf(s[i]);
        }
        setClient(getClient()+add);
        clientValidator();
    }

    @Override
    public PrintWriter append(CharSequence csq) {
        String csqString = csq.toString();
        for (String censorTerm : censorTerms) {
            if (csqString.contains(censorTerm.replace("\"",""))) {
                csqString = csqString.replaceAll(censorTerm.replace("\"", ""), "****");
            }
        }
        return super.append(csqString);
    }



    public void clientValidator () {

        for (String censorTerm : censorTerms) {
            if (getClient().contains(censorTerm.replace("\"",""))) {
                append("****");
                setClient("");
            }
        }
        append(getClient());
        setClient("");
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

    public CensoredPrintWriter(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    public Boolean getPositionCensored() {
        return positionCensored;
    }





}
