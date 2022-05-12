package com.hw15;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FileManager {
    public void parser(String filePath) {
        try {
            var fileReader = new FileReader(filePath);
            var bufferedReader = new BufferedReader(fileReader);

            bufferedReader.lines()
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .map(String::toLowerCase)
                .map(s -> s
                    .replace(",", "")
                    .replace(".", "")
                    .replace("?", "")
                    .replace("!", "")
                    .replace(":", "")
                    .replace(";", "")
                    .replace("-", "")
                    .replace("‑", "")
                    .replace("_", "")
                    .replace("'", "")
                    .replace("\"", "")
                    .replace("’", "")
                    .replace("«", "")
                    .replace("»", "")
                    .replace("…", "")
                    .replace("[", "")
                    .replace("]", "")
                    .replace("(", "")
                    .replace(")", "")
                    .replace("{", "")
                    .replace("}", "")
                    .replace("1", "")
                    .replace("2", "")
                    .replace("3", "")
                    .replace("4", "")
                    .replace("5", "")
                    .replace("6", "")
                    .replace("7", "")
                    .replace("8", "")
                    .replace("9", "")
                    .replace("0", "")
                    .replace("русс", "")
                    .replace("рассия", "")
                )
                .filter(s -> s.length() > 3)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(System.out::println);
            ;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}