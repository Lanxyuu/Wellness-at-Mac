import java.io.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Reads the high score of Level Three of the Wellness@Mac game from a text file
 * @author Madeline Chan
 * @version 1.0
 */
public class HighScoreReaderLevel3 {
    /**
     * Holds the names of the players
     */
    private ArrayList<String> names;
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
     * instantiates names
     * @param f the name of the file to be read from
     */
    public HighScoreReaderLevel3(String f) {
        file = "../Resources/Scores/" + f + ".txt";
        names = new ArrayList<String>();
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
                if (!(line.equals("arglebargle"))) {//not a default
                    names.add(line);
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
}