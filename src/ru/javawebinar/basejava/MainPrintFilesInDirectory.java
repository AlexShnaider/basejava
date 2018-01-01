package ru.javawebinar.basejava;

import java.io.File;

public class MainPrintFilesInDirectory {
    public static void main(String[] args) {
        printFilesInDirectory("./src");
        printFilesInDirectory("./test");
    }

    public static void printFilesInDirectory(String directory) {
        File dir = new File(directory);
        for (String file : dir.list()) {
            String possibleDirPath = directory + '/' + file;
            if (new File(possibleDirPath).isDirectory()) {
                printFilesInDirectory(possibleDirPath);
            } else {
                System.out.println(file);
            }
        }
    }
}
