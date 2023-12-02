package advent.day2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Game {
    @Setter @Getter
    private Integer redCount = 0, greenCount = 0, blueCount = 0;

    public Game(String gameSet) {
        String[] set = gameSet.split(",");

        for (String cube: set) {
            cube = cube.trim();
            switch (cube.split(" ")[1].trim()) {
                case "red":
                    this.redCount = Integer.parseInt(cube.split(" ")[0].trim());
                    break;
                case "blue":
                    this.blueCount = Integer.parseInt(cube.split(" ")[0].trim());
                    break;
                case "green":
                    this.greenCount = Integer.parseInt(cube.split(" ")[0].trim());
                    break;
                default:
                    break;
            }
        }
    }

}
