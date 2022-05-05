package com.hw12;

import java.util.HashMap;

public class RomanParser {
    public int romanToInt(String s) {
        var parseArray = s.toCharArray();
        var romanArray = this.getRomanNumberList();

        int solution = 0;
        boolean isLastItem;

        for (int i = 0; i < parseArray.length; i++) {
            isLastItem = i + 1 < parseArray.length;

            if (
                (parseArray[i] == 'I' && isLastItem && parseArray[i + 1] == 'V')
                || (parseArray[i] == 'I' && isLastItem && parseArray[i + 1] == 'X')
            ) {
                solution += Integer.parseInt(romanArray.get(parseArray[i + 1]).toString()) - 1;
                i++;
                continue;
            }

            if (
                (parseArray[i] == 'X' && isLastItem && parseArray[i + 1] == 'L')
                || (parseArray[i] == 'X' && isLastItem && parseArray[i + 1] == 'C')
            ) {
                solution += Integer.parseInt(romanArray.get(parseArray[i + 1]).toString()) - 10;
                i++;
                continue;
            }

            if (
                (parseArray[i] == 'C' && isLastItem && parseArray[i + 1] == 'D')
                || (parseArray[i] == 'C' && isLastItem && parseArray[i + 1] == 'M')
            ) {
                solution += Integer.parseInt(romanArray.get(parseArray[i + 1]).toString()) - 100;
                i++;
                continue;
            }

            solution += Integer.parseInt(romanArray.get(parseArray[i]).toString());
        }

        return solution;
    }

    private HashMap<Character, Integer> getRomanNumberList() {
        var arr = new HashMap<Character, Integer>();
        arr.put('I', 1);
        arr.put('V', 5);
        arr.put('X', 10);
        arr.put('L', 50);
        arr.put('C', 100);
        arr.put('D', 500);
        arr.put('M', 1000);

        return arr;
    }
}