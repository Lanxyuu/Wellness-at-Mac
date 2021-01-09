import java.io.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Reads the high score of the Wellness@Mac game from a text file
 * @author Madeline Chan
 * @version 1.0
 */
public class HighScoreReader {
    /**
     * Holds the names of the players
     */
    private ArrayList<String> names;
    /**
     * Holds the scores of the players
     */
    private ArrayList<Integer> scores;
    /**
     * The text file that the scores are read from
     */
    private String file;
    /**
     * Reads the text file
     */
    private BufferedReader input;
    
    /**
     * Class constructor that assigns a file name and 
     * instantiates names and scores
     * @param f the name of the file to be read from
     */
    public HighScoreReader(String f) {
        file = "../Resources/Scores/" + f + ".txt";
        names = new ArrayList<String>();
        scores = new ArrayList<Integer>();
    }
    
    /**
     * Assigns each variable the next corresponding line in the text files
     */
    public void read() {
        String line = "";
        int counter = 0;
        try {
            input = new BufferedReader(new FileReader(file));
            while (line != null) {
                line = input.readLine();
                counter++;
                if (!(line.equals("arglebargle") && (counter % 2 == 1)) || !(line.equals("69") && (counter % 2 == 0))) {//not a default
                    if (counter % 2 == 1)
                        names.add(line);
                    else if (counter % 2 == 0)
                        scores.add(Integer.parseInt(line));
                }
            }
            input.close();
        }
        catch (IOException e) {
        }
    }
    
     /**
      * Returns an ArrayList containing the players' names
      * @return ArrayList containing the players' names
      */
    public ArrayList<String> getNames() {
        return names;
    }
    
    /**
      * Returns an ArrayList containing the players' scores
      * @return ArrayList containing the players' scores
      */
    public ArrayList<Integer> getScores() {
        return scores;
    }
}