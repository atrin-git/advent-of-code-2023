package advent.day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) {

        // PREPARE Data

        Game[][] games = new Game[100][];
        try {
            Scanner scanner = new Scanner(new File("src/main/java/advent/day2/input.txt"));
            int lineNumber = 0;

            while (scanner.hasNextLine()) {
                String[] textLine = scanner.nextLine().split(":")[1].split(";");
                int setNumber = 0;
                games[lineNumber] = new Game[textLine.length];
                for (String set: textLine) {
                    Game game = new Game(set);
                    games[lineNumber][setNumber] = game;
                    setNumber++;
                }
                lineNumber++;
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (games[0].length == 0)
            return;

        // TASK 1

        // Settings
        int needRed = 12;
        int needGreen = 13;
        int needBlue = 14;

        boolean isGamable = true;
        int sum = 0;

        for (int i = 0; i < games.length; i++) {
            for (int j = 0; j < games[i].length; j++) {
                if (games[i][j].getRedCount() > needRed
                        || games[i][j].getGreenCount() > needGreen
                        || games[i][j].getBlueCount() > needBlue) {
                    isGamable = false;
                    break;
                }
            }
            if (isGamable)
                sum = sum + (i + 1);
            isGamable = true;
        }

        System.out.println("task 1: " + sum);

        // TASK 2

        sum = 0;

        for (int i = 0; i < games.length; i++) {
            int maxRed = 1, maxGreen = 1, maxBlue = 1;
            for (int j = 0; j < games[i].length; j++) {
                if (games[i][j].getRedCount() > maxRed)
                    maxRed = games[i][j].getRedCount();
                if (games[i][j].getGreenCount() > maxGreen)
                    maxGreen = games[i][j].getGreenCount();
                if (games[i][j].getBlueCount() > maxBlue)
                    maxBlue = games[i][j].getBlueCount();
            }
            int multy = maxRed * maxGreen * maxBlue;
            sum = sum + multy;
        }

        System.out.println("task 2: " + sum);
    }
}

