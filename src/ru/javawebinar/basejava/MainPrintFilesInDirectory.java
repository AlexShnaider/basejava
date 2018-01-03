package ru.javawebinar.basejava;

import java.io.File;

public class MainPrintFilesInDirectory {
    public static void main(String[] args) {
        printFilesInDirectory("./src");
        printFilesInDirectory("./test");
    }

    public static void printFilesInDirectory(String directory) {
        File dir = new File(directory);
        if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                if (file.isDirectory()) {
                    printFilesInDirectory(file.getPath());
                } else {
                    System.out.println(file.getName());
                }
            }
        }
    }
}
