import java.io.*;
import java.awt.*;
import javax.swing.*;

/**A class that represents a script of the game and allows other programs to
  * access certain parts of it in a format that can be more easily
  * used
  * <p>
  * Time spent: 1 hour
  * 
  * @author M Chan and S Lan 
  * @version 1.0
  */
public class Script 
{
    /**
     * The name of the player
     */
    private String playerName = "";
    
    /** 
     * Name of the text file that contains the dialogue text for the script
     */
    private String textFile;
    /** 
     * Name of the text file that contains the speaker name for the script
     */
    private String speakerFile; 
    /** 
     * Name of the text file that contains the character image names for the script
     */
    private String characterFile; 
    /** 
     * Name of the text file that contains the background image names for the script
     */
    private String bgFile;
    
    /** 
     * Boolean that represents whether or not the script has ended
     */
    boolean end = false;
    
    /** 
     * String to hold the dialogue text of the script
     */
    private String textString;
    /** 
     * String to hold the character name of the script
     */
    private String charName;
    /** 
     * String to hold the character image name of the script
     */
    private String charPic;
    /** 
     * String to hold the background image nameof the script
     */
    private String bgPic;
    
    /**
     * Integer that represents the current line of the script
     */
    private int line = 1;
    
    /**
     * BufferedReader that reads the dialogue text file
     */
    BufferedReader textReader;
    /**
     * BufferedReader that reads the speaker name text file
     */
    BufferedReader speakerReader;
    /**
     * BufferedReader that reads the character image name text file
     */
    BufferedReader characterReader;
    /**
     * BufferedReader that reads the background image name text file
     */
    BufferedReader bgReader;
    
    /**
     * Constructor for the Script class
     * @param t the name of the text file that holds the dialogue text
     * @param s the name of the text file that holds the speaker names
     * @param c the name of the text file that holds the character image names
     * @param b the name of the text file that holds background image names
     * @param p the name of the player
     */
    public Script(String t, String s, String c, String b, String p) {
        playerName = p;
        textFile = "../Resources/Scripts/" + t + ".txt";
        speakerFile = "../Resources/Scripts/" + s + ".txt";
        characterFile = "../Resources/Scripts/" + c + ".txt";
        bgFile = "../Resources/Scripts/" + b + ".txt";
    }   
    
    /**
     * Creates four new BufferedReader objects that read from the text files
     * Assigns textString, charName, charPic, and bgPic their respective values,
     * based on where the current line is on. The variable line is increased by 1.
     */
    public void assign() {
        try {
            textReader = new BufferedReader(new FileReader(textFile));
            speakerReader = new BufferedReader(new FileReader(speakerFile));
            characterReader = new BufferedReader(new FileReader(characterFile));
            bgReader = new BufferedReader(new FileReader(bgFile));
            
            for (int i = 0; i < line; i++) {
                if (i == line - 1) {
                    textString = textReader.readLine();
                    charName = speakerReader.readLine();
                    charPic = characterReader.readLine();
                    bgPic = bgReader.readLine();
                }
                else {
                    textReader.readLine();
                    speakerReader.readLine();
                    characterReader.readLine();
                    bgReader.readLine();
                }
            }
            textReader.close();
            speakerReader.close();
            characterReader.close();
            bgReader.close();
        }
        catch (IOException e) {
        }
        line++;
    }
    
    /**
     * Returns textString, checks if it's the end, and 
     * replaces any instances of [player] with playerName.
     * 
     * @return textString (with all instances of [player] replaced with playerName)
     */
    public String getText() {
        if (textString.equals("END"))
            end = true;
        else if (textString.indexOf("[player]") > -1){
            String newText = textString.substring(0, textString.indexOf("[player]")) + playerName + textString.substring(textString.indexOf("[player]") + 8);
            return newText;
        }
        return textString;
    }
    
    /**
     * Returns a boolean that represents whether or not the script has ended
     * 
     * @return end, the boolean that represents whether or not the script has ended
     */
    public boolean end() {
        return end;
    }
    
    /**
     * Returns the name of the character who's speaking on the current line of the script
     * 
     * @return the name of the character who's currently speaking
     */
    public String getCharName() {
        return charName;
    }
    
    /**
     * Returns the name of the character image of the current line of the script
     * 
     * @return the name of the image of the current character
     */
    public String getCharPic() {
        return "../Resources/Images/Characters/" + charPic + ".png";
    }
    
    /**
     * Returns the name of the background image of the current line of the script
     * 
     * @return the name of the background of the current background
     */
    public String getBG () {
        return "../Resources/Images/Backgrounds/" + bgPic + ".jpg";
    }
}