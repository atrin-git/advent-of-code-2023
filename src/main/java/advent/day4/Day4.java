package advent.day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day4 {

    static final HashMap<Integer, List[]> cards = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/main/java/advent/day4/input.txt"));

        int line = 0;

        while (scanner.hasNextLine()) {
            String allNumbers = scanner.nextLine().split(": ")[1];
            String test = allNumbers.split("\\|")[0];
            List<String> winnerNumbers = Arrays.asList(allNumbers.split("\\|")[0].split(" "));
            List<String> userNumbers = Arrays.asList(allNumbers.split("\\|")[1].split(" "));

            winnerNumbers = winnerNumbers.stream().filter(item -> !item.isBlank())
                    .collect(Collectors.toList());

            userNumbers = userNumbers.stream().filter(item -> !item.isBlank())
                    .collect(Collectors.toList());

            cards.put(line, new List[] {winnerNumbers, userNumbers});
            line++;
        }
        scanner.close();

        //TASK 1

        double sum = 0;

        for (List[] card : cards.values()) {
            int findCount = 0;
            for (int i = 0; i < card[0].size(); i++) {
                if (card[1].contains(card[0].get(i))) {
                    findCount++;
                }
            }
            sum = sum + ((findCount > 0) ? Math.pow(2, findCount - 1) : 0);
        }

        System.out.println("Task 1: " + (int)sum);

        // TASK 2

        HashMap<Integer, HashMap> multyCards = new HashMap<>();

        for (int number: cards.keySet()) {
            HashMap<Integer, List[]> multyCard = new HashMap<>();
            multyCard.put(0, cards.get(number));
            multyCards.put(number, multyCard);
        }

        int iLine = 0;
        while (iLine < multyCards.size()) {
            int findCount = 0;
            HashMap<Integer, List[]> currentCard = multyCards.get(iLine);
            for (int i = 0; i < currentCard.size(); i++) {
                for (int k = 0; k < currentCard.get(i)[1].size(); k++) {
                    if (currentCard.get(i)[0].contains(currentCard.get(i)[1].get(k))) {
                        findCount++;
                    }
                }
                for (int j = 0; j < findCount; j++) {
                    if (!multyCards.containsKey(iLine + j + 1))
                        break;

                    HashMap<Integer, List[]> cardToInsert = multyCards.get(iLine + j + 1);
                    multyCards.get(iLine + j + 1).put(multyCards.get(iLine + j + 1).size(), cardToInsert.get(0));
                }
                findCount = 0;
            }
            iLine++;
        }

        iLine = 0;
        sum = 0;
        while (iLine < multyCards.size()) {
            sum = sum + multyCards.get(iLine).size();
            iLine++;
        }
        System.out.println("Task 2: " + sum);
    }
}

