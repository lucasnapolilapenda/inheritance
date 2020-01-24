package com.censored.lucas;

import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.Locale;

public class CensoredPrintWriter extends PrintWriter {

    public String [] censorTerms;
    public String client;
    public Boolean positionCensored = false;

    // Override Methods
    @Override
    public PrintWriter printf(String format, Object... args) {
        String xCensored = format;
        String [] censor = getCensorTerms();
        if (!getPositionCensored()) {
            for (int i = 0; i < args.length; i++) {
                for (int i1 = 0; i1 < censor.length; i1++) {
                    if (args[i].toString().equals(censor[i1])) {
                        System.out.println("Changing by * :"+ args[i]);
                        args[i] = starCreator(censor[i1].replace("\"", ""));
                    }
                }
            }

            for (String censorTerm : censorTerms) {
                if (xCensored.contains(censorTerm.replace("\"", ""))) {
                    xCensored = xCensored.replaceAll(censorTerm.replace("\"", ""), starCreator(xCensored));
                }
            }
            xCensored = xCensored.replaceAll("%d", "%s");
            return super.format(xCensored, args);
        }

        if (getPositionCensored() && xCensored.contains("%d")) {
            xCensored = xCensored.replaceAll("%d", "****");
            Arrays.stream(args).forEach((e->System.out.println("Changing by * :"+ e)));
            super.print(xCensored);
        } else { return super.format(xCensored, args);}

        return null;
    }

    @Override
    public void println(String x) {
        String secret = "";
        String xCensored = x;
        synchronized (lock) {
            for (String censorTerm : censorTerms) {
                if (x.contains(censorTerm.replace("\"", ""))) {
                    xCensored = xCensored.replaceAll(censorTerm.replace("\"", ""), "secret");
                    secret = starCreator(censorTerm.replace("\"", ""));
                    System.out.println("Changing by * :" + censorTerm.replace("\"", ""));
                }
            }
        }
        xCensored = xCensored.replaceAll("secret", secret);
        super.print(xCensored);
        super.println();
    }

    @Override
    public void println(int x) {
        String y = Integer.toString(x);
        println(y);
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
                csqString = csqString.replaceAll(censorTerm.replace("\"", ""), starCreator(csqString));
            }
        }
        return super.append(csqString);
    }

    // Local Methods

    public void clientValidator () {

        for (String censorTerm : censorTerms) {
            if (getClient().contains(censorTerm.replace("\"",""))) {
                append(starCreator(getClient()));
                System.out.println("Changing by * :" + getClient());
                setClient("");
            }
        }
        append(getClient());
        setClient("");
    }

    public String starCreator (String x) {
        String y = "";
        for (char c : x.toCharArray()) {
            c = '*';
            y = y + c;
        }
        return y;
    }

    // constructors

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

