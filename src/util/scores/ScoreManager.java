package util.scores;

import java.io.IOException;
import java.nio.file.*;

public class ScoreManager {
    private final Path SCORE_PATH = Paths.get("src/util/scores/scores.txt");
    private String[] scoreTemp;
    private final int[] scorePoints;
    private final String[] scoreNames;

    public ScoreManager() {
        this.scoreTemp = null;
        this.scorePoints = new int[5];
        this.scoreNames = new String[5];
    }

    public void openScoresFile() {
        if (!Files.exists(this.SCORE_PATH)) {
            try {
                Files.createFile(this.SCORE_PATH);
            } catch (IOException error) {
                System.out.print("Scores were not loaded correctly.\n" +
                                 "Please, try again.\n");
            }
        }
    }

    public String[] readScores() {
        try {
            this.scoreTemp = Files.readString(this.SCORE_PATH).split("\n");
        } catch (IOException error) {
            System.out.print("Error: The scores file could not be read properly.\n");
        }
        return this.scoreTemp;
    }

    public void recalculateScores(String playerName, int newScore) {
        for (int i = 0; i < this.scoreTemp.length; i++) {
            String scoreString = this.scoreTemp[i].substring(this.scoreTemp[i].lastIndexOf("-") + 1, this.scoreTemp[i].length()).trim();
            String scoreName = this.scoreTemp[i].substring(0, this.scoreTemp[i].lastIndexOf("-") - 1).trim();
            this.scorePoints[i] = Integer.parseInt(scoreString);
            this.scoreNames[i] = scoreName;

        }

        for (int i = 0; i < this.scorePoints.length; i++) {
            if (newScore > this.scorePoints[i]) {
                if (i != 4) {
                    for (int k = this.scorePoints.length - 2; i <= k; k--) {
                        this.scorePoints[k + 1] = this.scorePoints[k];
                        this.scoreNames[k + 1] = this.scoreNames[k];
                    }
                }
                this.scorePoints[i] = newScore;
                this.scoreNames[i] = playerName;
                break;
            }
        }


        writeScores("", "rewrite");
        for (int i = this.scoreTemp.length - 1; 0 <= i; i--) {
            String scoreLine = this.scoreNames[4 - i] + " - " + this.scorePoints[4 - i] + "\n";
            this.scoreTemp[i] = scoreLine;
            this.writeScores(scoreLine, "append");
        }
    }

    public void writeScores(String scoreLine, String writingMethod) {
        try {
            if (writingMethod.equals("append")) {
                Files.writeString(this.SCORE_PATH, scoreLine, StandardOpenOption.APPEND);
            } else if (writingMethod.equals("rewrite")) {
                Files.writeString(this.SCORE_PATH, scoreLine);
            }
        } catch (IOException error) {
            System.out.print("Error: The file could be changed properly\n");
        }
    }
}
