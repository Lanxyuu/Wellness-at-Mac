import java.io.*;
import java.awt.*;
import javax.swing.*;

/**
 * Special script for the texting parts of Level Three of Wellness@Mac
 * @author M Chan and S Lan 
 * @version 1.0
 */
public class TextingScript 
{
    /**
     * name of player
     */
    private String playerName = "";
    
    /**
     * name of the text file for the text dialogue
     */
    private String textFile;
    /**
     * name of the text for the texter name
     */
    private String speakerFile; 
    
    /**
     * if the texting has ended
     */
    private boolean end = false;
    
    /**
     * stores what's being texted
     */
    private String textString;
    
    /**
     * stores the name of the speaker
     */
    private String speakerName;
    
    /**
     * current line of the script
     */
    private int line = 1;
    
    /**
     * reads the text file for text
     */
    BufferedReader textReader;
    /**
     * reads the text file for the speaker
     */
    BufferedReader speakerReader;
    
    /**
     * Class constructor that instantiates playerName, textFile, and speakerFile
     * @param t name of text file for for texts
     * @param s name of text file for the speaker
     * @param p player name
     */
    public TextingScript(String t, String s, String p) {
        playerName = p;
        textFile = "../Resources/Scripts/" + t + ".txt";
        speakerFile = "../Resources/Scripts/" + s + ".txt";
    }   
    
    /**
     * Assigns the next line of the script
     */
    public void assign() {
        try {
            textReader = new BufferedReader(new FileReader(textFile));
            speakerReader = new BufferedReader(new FileReader(speakerFile));
            
            for (int i = 0; i < line; i++) {
                if (i == line - 1) {
                    textString = textReader.readLine();
                    speakerName = speakerReader.readLine();
                }
                else {
                    textReader.readLine();
                    speakerReader.readLine();
                }
            }
            textReader.close();
            speakerReader.close();
        }
        catch (IOException e) {
        }
        line++;
    }
    
    /**
     * Gets the text content of the current line
     * @return textString
     */
    public String getText() {
        if (textString.equals("END"))
            end = true;
        else if (textString.indexOf("[player]") > -1){ //fills in [player] to read username
            String newText = textString.substring(0, textString.indexOf("[player]")) + playerName + textString.substring(textString.indexOf("[player]") + 8);
            return newText;
        }
        return textString;
    }
    
    /**
     * Whether or not the script has ended
     * @return end
     */
    public boolean end() {
        return end;
    }
    
    /**
     * Gets the speaker name
     * @return speakerName
     */
    public String getSpeakerName() {
        return speakerName;
    }
       
    /**
     * Gets the current line of the script before it was incremented
     * @return line - 1
     */
    public int getLine() { //the current line, before it was incremented
        return line - 1;
    }
}