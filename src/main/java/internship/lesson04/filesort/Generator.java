package io.ylab.intensive.lesson04.filesort;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Generator {
    public Generator() {
    }

    public File generate(String name, int count) throws IOException {
        Random random = new Random();
        File file = new File(name);
        PrintWriter pw = new PrintWriter(file);

        try {
            int i = 0;

            while(true) {
                if (i >= count) {
                    pw.flush();
                    break;
                }

                pw.println(random.nextLong());
                ++i;
            }
        } catch (Throwable var9) {
            try {
                pw.close();
            } catch (Throwable var8) {
                var9.addSuppressed(var8);
            }

            throw var9;
        }

        pw.close();
        return file;
    }
}

