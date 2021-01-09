import java.io.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Writes the high scores of the Wellness@Mac game
 * @author Madeline Chan
 * @version 1.0
 */
public class HighScoreWriter {
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
     * Prints the scores
     */
    private PrintWriter output;
    
    /**
     * Class constructor that assigns a file name and 
     * instantiates names and scores
     * @param n ArrayList that contains the names
     * @param s ArrayList that contains the scores
     * @param f String that contains the name of the text file
     */
    public HighScoreWriter(ArrayList<String> n, ArrayList<Integer> s, String f) {
        file = "../Resources/Scores/" + f + ".txt";
        names = n;
        scores = s;
    }
    
    /**
     * Assigns each variable the next corresponding line in the text files
     */
    public void write() {
        try {
            output = new PrintWriter(new FileWriter(file));
            for (int x = 0; x < names.size(); x++) {
                output.println(names.get(x));
                output.println(scores.get(x));
            }
            if (names.size() < 10) {
                for (int x = names.size(); x < 10; x++) {
                    output.println("arglebargle");
                    output.println("69");
                }
                
            }
            output.close();
        }
        catch (IOException e) {
        }
    }
        
    /**
     * Clears the text file
     */
    public void clear() {
        try {
            PrintWriter input = new PrintWriter(new FileWriter(file));
            for (int x = 0; x < 10; x++) {
                input.println("arglebargle");
                input.println("69");
            }
            input.close();
        }
        catch (IOException e) {
        }
    }
}