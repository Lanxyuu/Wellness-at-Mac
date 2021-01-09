import java.io.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Writes the high scores for level 3 of the Wellness@Mac game
 * @author Madeline Chan
 * @version 1.0
 */
public class HighScoreWriterLevel3 {
    /**
     * Holds the names of the players
     */
    private ArrayList<String> names;
    /**
     * Name of the text file to be written to
     */
    private String file;
    /**
     * PrintWriter to write to files
     */
    private PrintWriter output;
    
    /**
     * Class constructor that assigns a file name and 
     * instantiates names
     * @param n ArrayList that contains the names
     * @param f String that contains the name of the text file
     */
    public HighScoreWriterLevel3(ArrayList<String> n, String f) {
        file = "../Resources/Scores/" + f + ".txt";
        names = n;
    }
    
    /**
     * Assigns each variable the next corresponding line in the text files
     */
    public void write() {
        try {
            output = new PrintWriter(new FileWriter(file));
            for (int x = 0; x < names.size(); x++) {
                output.println(names.get(x));
            }
            if (names.size() < 10) {
                for (int x = names.size(); x < 10; x++) {
                    output.println("arglebargle");
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
            }
            input.close();
        }
        catch (IOException e) {
        }
    }        
}