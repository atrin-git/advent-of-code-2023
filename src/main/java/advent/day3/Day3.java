package advent.day3;

import advent.day2.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Day3 {

    static final HashMap<String, Integer> gears = new HashMap<>();

    static int sum = 0;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/main/java/advent/day3/input.txt"));

        char[][] text = new char[140][];
        int line = 0;

        while (scanner.hasNextLine()) {
            text[line] = scanner.nextLine().toCharArray();
            line++;
        }
        scanner.close();

        for (int lineNumber = 0; lineNumber < text.length; lineNumber++) {
            char[] textLine = text[lineNumber];
            int startIndex = -1;
            int endIndex = -1;

            StringBuilder currentNumber = new StringBuilder();

            for (int i = 0; i < textLine.length; i++) {
                if (Character.isDigit(textLine[i])) {
                    if (startIndex == -1) {
                        startIndex = i;
                    }
                    if (i == (textLine.length - 1)) {
                        endIndex = i;
                    }
                    currentNumber.append(textLine[i]);
                }
                if (!Character.isDigit(textLine[i]) && startIndex >= 0) {
                    endIndex = i - 1;
                }
                if (endIndex > 0) {
                    int numToCalc = Integer.parseInt(currentNumber.toString());
                    findSymbols(text, lineNumber, startIndex, endIndex, numToCalc);
                    startIndex = -1;
                    endIndex = -1;
                    currentNumber.delete(0, currentNumber.length());
                }
            }
        }
        System.out.println(sum);
    }

    private static boolean findSymbols(char[][] text, int lineNumber, int startIndex, int endIndex, int currentNumber) {
        // строка до
        if (lineNumber > 0) {
            for (int i = (startIndex > 0 ? startIndex - 1 : 0);
                 i <= (endIndex < text[lineNumber].length - 1 ? endIndex + 1 : endIndex);
                 i++) {
                if (!Character.isDigit(text[lineNumber - 1][i]) && text[lineNumber - 1][i] != '.') {
                    if (text[lineNumber - 1][i] == '*') {
                        setGear(lineNumber - 1, i, currentNumber);
                    }
                    return true;
                }
            }
        }

        // текущая строка
        if (startIndex > 0) {
            if (!Character.isDigit(text[lineNumber][startIndex - 1]) && text[lineNumber][startIndex - 1] != '.') {
                if (text[lineNumber][startIndex - 1] == '*') {
                    setGear(lineNumber, startIndex - 1, currentNumber);
                }
                return true;
            }
        }
        if (endIndex < text[lineNumber].length - 1) {
            if (!Character.isDigit(text[lineNumber][endIndex + 1]) && text[lineNumber][endIndex + 1] != '.') {
                if (text[lineNumber][endIndex + 1] == '*') {
                    setGear(lineNumber, endIndex + 1, currentNumber);
                }
                return true;
            }
        }

        // строка после
        if (lineNumber < text.length - 1) {
            for (int i = (startIndex > 0 ? startIndex - 1 : 0);
                 i <= (endIndex < text[lineNumber + 1].length - 1 ? endIndex + 1 : endIndex);
                 i++) {
                if (!Character.isDigit(text[lineNumber + 1][i]) && text[lineNumber + 1][i] != '.') {
                    if (text[lineNumber + 1][i] == '*') {
                        setGear(lineNumber + 1, i, currentNumber);
                    }
                    return true;
                }
            }
        }

        return false;
    }

    static private void setGear(int lineNumber, int index, int currentNumber) {
        String key = "" + lineNumber + "_" + index;
        if (gears.containsKey(key)) {
            gears.put(key, gears.get(key) * currentNumber);
            sum = sum + gears.get(key);
        }
        else {
            gears.put(key, currentNumber);
        }
    }
}

