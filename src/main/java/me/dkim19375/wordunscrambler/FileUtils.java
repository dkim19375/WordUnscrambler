package me.dkim19375.wordunscrambler;

import javafx.scene.control.ProgressBar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class FileUtils {
    public static File copyFromEmbedded(String resource, Class<?> clazz) {
        final File dest = new File(resource);
        final URL from = clazz.getClassLoader().getResource(resource);
        if (from == null) {
            return null;
        }
        try {
            org.apache.commons.io.FileUtils.copyURLToFile(from, dest);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return dest;
    }

    public static Set<String> getStringPerLine(File file) {
        return getStringPerLine(file, null);
    }
    public static Set<String> getStringPerLine(File file, ProgressBar bar) {
        Set<String> words = new HashSet<>();
        if (bar != null) {
            bar.setProgress(0.0);
        }
        final double totalAmount = getLines(file);
        double forEach = 1.0 / (totalAmount == 0 ? 1 : totalAmount);
        final Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return words;
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            words.add(line);
            if (bar != null) {
                bar.setProgress(bar.getProgress() + forEach);
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scanner.close();
        return words;
    }

    public static long getLines(File file) {
        try {
            return Files.lines(file.toPath()).count();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}