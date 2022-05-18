package com.hw15;

import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FileManager {
    public void fileParser(Path filePath) {
        try (
            var fr = new BufferedReader(new FileReader(filePath.toString()));
            var fw = new BufferedWriter(new FileWriter(this.buildResultPath(filePath)))
        ) {
            List<Map.Entry<String, Long>> list = fr.lines()
                .flatMap(s -> Arrays.stream(s.split("[^А-Яа-я]+")))
                .map(String::toLowerCase)
                .filter(s -> s.length() > 3)
                .filter(s -> !s.contains("русс"))
                .filter(s -> !s.contains("рассия"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .toList()
            ;

            var iterator = list.iterator();

            while (iterator.hasNext()) {
                fw.write(String.valueOf(iterator.next()) + '\n');
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String buildResultPath(Path path) {
        var filePath = path.toString();

        return filePath.substring(0, filePath.indexOf('.')) + "_result.txt";
    }
}