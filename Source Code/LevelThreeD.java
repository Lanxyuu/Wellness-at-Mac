import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;

/**
 * Creates Part D of Level Three of the game Wellness@Mac
 * <p> 
 * Time spent: 3 hours
 * 
 * @author M Chan and S Lan 
 * @version 1.0
 */
public class LevelThreeD extends JPanel implements ActionListener{
    /**
     * The script of the text portion of the level
     */
    private TextingScript script;
    /**
     * Name of the player
     */
    private String pName = "";
    /**
     * The JFrame of the level
     */
    private JFrame frame;
    /**
     * The SpringLayout contraints of the layout
     */
    private SpringLayout.Constraints nC;
    /**
     * The SpringLayout of this JPanel
     */
    private SpringLayout spring = new SpringLayout();
    /**
     * Label that displays the text "What should I tell her...?"
     */
    private JLabel label;
    /**
     * The next button
     */
    private JButton next;
    /**
     * Player's score
     */
    private int score = 0;
    
    /**Constructor for the LevelThreeB class 
      * @param player The name of the player
      */
    public LevelThreeD(String player) {
        pName = player;
        frame = new JFrame("Wellness@Mac: Level Three");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 700);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(this);
        this.setLayout(spring);
        implementNext();
        script = new TextingScript("3D Text", "3D Speaker", pName);
        script.assign(); //starting off the new script!
    }

    /**
     * Adds the next button to the JPanel
     */
    public void implementNext() { //add next button to jframe
        next = new JButton("Next");
        this.add(next);
        next.addActionListener(this);
        nC = spring.getConstraints(next);
        nC.setY(Spring.constant(638));
        nC.setX(Spring.constant(720));
    }
    
    /**Overrides the actionPerformed method of the ActionListener interface.
      * Assigns the next line of the script if next was pressed
      * Else select/show choices based on the button
      * 
      * @param ae the ActionEvent
      */
    public void actionPerformed (ActionEvent ae)
    {
        if (ae.getSource() == next) { //clicked the next button
            script.assign();
            if (script.getText().equals("    ")) {
                add(new JLabel(new ImageIcon("../Resources/Images/Backgrounds/levelThreeWinEnd.jpg")));   
                revalidate();
            }
            else {
                repaint(); //write the next stuff
                revalidate();
            }
        }
    }
    
    /**Overrides the paintComponent method of the JPanel class
      *Draws the graphics of the game
      * 
      * @param g Graphics class
      */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //setting the font and creating colours
        String text = "", choice = "", text1 = "", text2 = ""; 
        int mark; //character - location to break the line
        Font sFont = new Font("Roboto", 1, 25); 
        g.setFont(sFont);
        
        //IF IT'S NOT THE END OF THE SCRIPT
        if (!script.getText().equals("END")) {
           
            //TEXT MESSAGE BACKGROUND
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 1100, 700);

            revalidate();
            
            //NORMAL SCRIPT
            //SETS TEXT COLOUR BASED ON SPEAKER
            g.setColor(new Color(0, 114, 47));
                
            //write the text into a variable - continue with normal script
            text = "Monica: " + script.getText();
            
            if (text.length() > 85) { //if dialogue length is greater than 85 characteres
                mark = text.indexOf(" ", 75);  
                text1 = text.substring(0, mark);
                text2 = text.substring(mark);
                g.drawString(text1, 15, 30);
                g.drawString(text2, 110, 60);
            }
            else
                g.drawString(text, 15, 30);
        }
        else {
            writeScores(pName, score);
            frame.dispose();
        }
    }
    
    /**
     * Writes the scores of Level Three
     * @param n name of the player
     * @param s the player's score
     */
     public void writeScores(String n, int s) {
        HighScoreReaderLevel3 reader = new HighScoreReaderLevel3 ("Scores3");
        ArrayList<String>names = reader.getNames();
        names.add(1, n);
        if (names.size() > 10) {
            names.remove(11);
        }
        HighScoreWriterLevel3 writer = new HighScoreWriterLevel3 (names, "Scores3");
        writer.write();
    }
}