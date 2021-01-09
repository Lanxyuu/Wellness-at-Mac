import java.io.*;
import java.awt.*;
import javax.swing.*;
/**
 * This is a special script class that reads dialogue choices
 * @author Madeline Chan
 * @version 1.0
 */

public class ChoiceScript 
{
    /**
     * Name of the bad choices text file
     */
    private String badChoiceFile;
    /**
     * Name of the good choices text file
     */
    private String goodChoiceFile;
    
    /**
     * Variable to store the dialogue of a bad choice
     */
    private String badChoice;
    /**
     * Variable to store the dialogue of a good choice
     */
    private String goodChoice;
    
    /**
     * Current line of the script
     */
    private int line = 1;
    
    /**
     * Buffered Reader to read the bad choice text file
     */
    BufferedReader badChoiceReader;
    /**
     * Buffered Reader to read the good choice text file
     */
    BufferedReader goodChoiceReader;
    
    /**
     * Class constructor that assigns badChoiceFile and goodChoiceFile their respective values
     * @param b name of the bad choice text file
     * @param g name of the good choice text file
     */
    public ChoiceScript(String b, String g) {
        badChoiceFile = "../Resources/Scripts/Choices/" + b + ".txt";
        goodChoiceFile = "../Resources/Scripts/Choices/" + g + ".txt";
    }   
    
    /**
     * Creates two new BufferedReader objects that read from the text files
     * Assigns badChoice and goodChoice their respective values
     * based on where the current line is on. The variable line is increased by 1.
     */
    public void assign() {
        try {
            badChoiceReader = new BufferedReader(new FileReader(badChoiceFile));
            goodChoiceReader = new BufferedReader(new FileReader(goodChoiceFile));
            
            for (int i = 0; i < line; i++) {
                if (i == line - 1) {
                    badChoice = badChoiceReader.readLine();
                    goodChoice = goodChoiceReader.readLine();
                }
                else {
                    badChoiceReader.readLine();
                    goodChoiceReader.readLine();
                }
            }
            badChoiceReader.close();
            goodChoiceReader.close();
        }
        catch (IOException e) {
        }
        line++;
    }
    
    /**
     * Returns badChoice variable (the dialogue of a bad choice)
     * @return badChoice variable (the dialogue of a bad choice)
     */
    public String getBadChoice() {
        return badChoice;
    }
    
    /**
     * Returns goodChoice variable (the dialogue of a good choice)
     * @return the good choice
     */
    public String getGoodChoice() {
        return goodChoice;
    }
}