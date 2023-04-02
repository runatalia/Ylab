package io.ylab.intensive.lesson04.filesort;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Validator {
    private File file;

    public Validator(File file) {
        this.file = file;
    }

    public boolean isSorted() {
        try {
            Scanner scanner = new Scanner(new FileInputStream(this.file));

            boolean var6;
            label42: {
                boolean var10;
                try {
                    long current;
                    for(long prev = Long.MAX_VALUE; scanner.hasNextLong(); prev = current) {
                        current = scanner.nextLong();
                        if (current > prev) {
                            var6 = false;
                            break label42;
                        }
                    }

                    var10 = true;
                } catch (Throwable var8) {
                    try {
                        scanner.close();
                    } catch (Throwable var7) {
                        var8.addSuppressed(var7);
                    }

                    throw var8;
                }

                scanner.close();
                return var10;
            }

            scanner.close();
            return var6;
        } catch (IOException var9) {
            var9.printStackTrace();
            return false;
        }
    }
}

