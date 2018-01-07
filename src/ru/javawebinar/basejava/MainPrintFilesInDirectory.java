package ru.javawebinar.basejava;

import java.io.File;

public class MainPrintFilesInDirectory {
    public static void main(String[] args) {
        printFilesInDirectory("./src", 1);
        printFilesInDirectory("./test", 1);
    }

    public static void printFilesInDirectory(String directory, int spaces) {
        File dir = new File(directory);
        if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                if (file.isDirectory()) {
                    System.out.println(String.format("%" + spaces + "s", "") + file.getName());
                    printFilesInDirectory(file.getPath(), spaces + 2);
                } else {
                    System.out.println(String.format("%" + spaces + "s", "") + file.getName());
                }
            }
        }
    }
}
