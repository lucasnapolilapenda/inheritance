package com.censored.lucas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Locale;

public class TestCensor {

    public static void main(String[] args) {
        try {
            PrintWriter pw = new PrintWriter("regular.txt");
            writeData(pw);

            String[] censorTerms = {"Mark Branson", "1356", "1268", "Google"};
            CensoredPrintWriter cpw = new CensoredPrintWriter("censored.txt");
            cpw.setCensorTerms(censorTerms);
            writeData(cpw);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void writeData(PrintWriter pw) {
        pw.println("-- Year end company report --");

        Calendar c1 = Calendar.getInstance();
        pw.format("Generated on: %tc / ", c1);
        pw.format(Locale.CHINA, "%tc", c1);
        pw.println();

        pw.printf("Transfer from account %d to %d in the amount $1,000,000", 1356, 1268);
        pw.println(1356);

        Calendar c2 = Calendar.getInstance();
        c2.set(2019, 3, 12, 10, 13, 35);
        pw.printf("Transfer took place on %tc / ", c2);
        pw.printf(Locale.CHINA, "%tc", c2);
        pw.println();

        pw.println("Director Mark Branson announced his resignation");

        pw.append("Our client ");
        pw.append('G');
        pw.write('o');
        pw.print('o');
        pw.print(new char[] {'g', 'l', 'e'});
        pw.append(" had a positive response");

        pw.close();
    }


}