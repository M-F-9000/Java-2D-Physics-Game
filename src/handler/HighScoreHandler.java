package handler;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
public class HighScoreHandler {

    /**
     * Score storing location
     */
    private final String fileName = "data/scores.txt";


    /**
     * Allows high scores to be saved and read.
     */
    public HighScoreHandler() {
    }

    public void readScores() throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;

        try {
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();

            while (line != null) {
                // Score.txt consists of: name, level 1 score, level 2 score, level 3 score, total score
                String[] tokens = line.split(",");
                String name = tokens[0];
                int score1 = Integer.parseInt(tokens[1]);
                int score2 = Integer.parseInt(tokens[2]);
                int score3 = Integer.parseInt(tokens[3]);
                int finalScore = Integer.parseInt(tokens[4]);
                System.out.println("Name: " + name + "\nLevel 1 score: " + score1
                        + "\nLevel 2 score: " + score2 + "\nLevel 3 score: " + score3+ "\nFinal score: " + finalScore);
                line = reader.readLine();
            }

        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }

    public void writeHighScore(String name, int score1, int score2, int score3, int finalScore) throws IOException {
        boolean append = true;
        FileWriter writer = null;

        try {
            writer = new FileWriter(fileName, append);
            writer.write(name + "," + score1 + "," +
                    score2 + "," + score3 + "," + finalScore + "\n");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
