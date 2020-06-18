package com.api.auctionhouseapi.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadInputFile {

    public String readFromInputFile(String fileName) throws IOException {
        String fileContaints = "";
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        BufferedReader br = new BufferedReader(new FileReader(file));
        while (true) {
            String currentLine = br.readLine();
            if (currentLine == null)
                break;
            fileContaints = fileContaints + currentLine + "\n";
        }
        return fileContaints.substring(0, fileContaints.length() - 1);
    }
}
