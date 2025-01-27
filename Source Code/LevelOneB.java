import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;

/**
 * Creates Part B of Level One of the game Wellness@Mac
 * <p> 
 * Time spent: 3 hours
 * 
 * @author M Chan and S Lan 
 * @version 1.0
 */
public class LevelOneB extends JPanel implements ActionListener{
    /**
     * script of the level
     */
    private Script script;
    /**
     * name of the player
     */
    private String pName = "";
    /**
     * frame of the level
     */
    private JFrame frame;
    /**
     * SpringLayout constraints
     */
    private SpringLayout.Constraints nC;
    /**
     * player's score
     */
    private int score = 0;
    /**
     * next button
     */
    private JButton next;
    /**
     * the current text that's displaying on screen
     */
    private String text = "";
    /**
     * frame of the level
     */
    private String speaker = "";
    /**
     * ArrayList of tips that the player chose
     */
    private ArrayList<Boolean> tips;
    /**
     * number of healthy tips the player chose
     */
    private int hPoints;
    
    /**
     * Constructor that creates a new JFrame and adds the JPanel to it
     * 
     * @param player the player's name
     * @param tips ArrayList that holds the tips the player chose
     * @param healthyRecipes the number of healthy recipes the player chose
     */
    public LevelOneB(String player, ArrayList<Boolean> tips, int healthyRecipes) {
        pName = player;
        this.tips = tips;
        hPoints = healthyRecipes;
        frame = new JFrame("Wellness@Mac: Level One");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 700);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(this);
        addNext();
        
        //calculating score
        score += healthyRecipes;
        for (Boolean x : tips) {
            if (x == true)
                score++;
        }
        
        if (score >= 6) { //win scenario
            script = new Script("1W Text", "1W Speaker", "1W Characters", "1W Background", pName);
        }
        else //lose scenario
            script = new Script("1L Text", "1L Speaker", "1L Characters", "1L Background", pName);
        script.assign();
    }
    
    /**
     * Adds the next button to the JPanel
     */    
    public void addNext() {
        //set layout adds the "next" button to the jframe
        SpringLayout spring = new SpringLayout();
        this.setLayout(spring);
        next = new JButton("Next");
        this.add(next);
        next.addActionListener(this);
        nC = spring.getConstraints(next);
        nC.setY(Spring.constant(638));
        nC.setX(Spring.constant(720));
    }
    
    /**Overrides the actionPerformed method of the ActionListener interface.
      * @param ae the ActionEvent
      */
    public void actionPerformed (ActionEvent ae)
    {
        script.assign();
        if (!script.end()) {
            text = script.getText();
            if (script.getText().equals("-")){ //when Monica comments about your tips (in the lose scenario)
                if (tips.get(0) == false) 
                    text = "Your tips aren't really good. For example, coffee does help you keep awake, but it's unhealthy to have it right after waking up.";
                else if (tips.get(1) == false)
                    text = "Your tips aren't really good. For example, snoozing makes you enter deep sleep again, making it even harder to wake up the second time.";
                else if (tips.get(2) == false)
                    text = "Your tips aren't really good. For example, blue light from screen makes you more aware, but also increases stress and anxiety hormones.";
                else
                    text = "Your tips are fine, but some of your recipes are pretty unhealthy...";
                repaint();
                revalidate();
            }
            else {
                repaint();
                revalidate();
            }
        } else {
            writeScores(pName, score);
            frame.dispose();
        }
    }
    
    /**Finds an image and returns it as a BufferedImage
      * @param imageName name of the image to be found (including file extension)
      * @return BufferedImage of the image
      * @exception IOException if image isn't found
      */
    private BufferedImage getImage (String imageName) {
        try {
            return ImageIO.read(new File(imageName));
        } catch (IOException e) {return null;}
    }
    
    /**
     * Overrides paintComponent method of JPanel
     * Draws the graphics of the game
     * @param g Graphics
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //setting the font and creating colours
        Font sFont = new Font("Roboto", 1, 25);
        g.setFont(sFont);
        Color sabrinaText = new Color(255, 69, 0);
        Color monicaText = new Color(0, 114, 47);
        Color yuliaText = new Color(125, 12, 255);
        Color natalieText = new Color(255, 12, 174);
        Color playerText = Color.WHITE;
        
        //BACKGROUND
        g.drawImage(getImage(script.getBG()), 0, 0, this);
        //CHARACTER SPRITES
        if(!script.getCharPic().equals("-"))
            g.drawImage(getImage(script.getCharPic()), 350, 75, this);
        
        //TEXTBOX
        if (!script.getBG().equals("../Resources/Images/Backgrounds/levelOneWinEnd.jpg") && !script.getBG().equals("../Resources/Images/Backgrounds/levelOneLoseEnd.jpg"))
            g.drawImage(getImage("../Resources/Images/textbox.png"), 210, 480, this);
        revalidate();
        
        //SETS TEXT COLOUR BASED ON SPEAKER
        switch (script.getCharName()) {
            case "player": g.setColor(playerText);
            break;
            case "???": case "Sabrina": g.setColor(sabrinaText);
            break;
            case "Monica": g.setColor(monicaText);
            break;
            case "Natalie": g.setColor(natalieText);
            break;
            case "Yulia": g.setColor(yuliaText);
            break;
            default: g.setColor(Color.BLACK);
        }
        
        //SPEAKER NAME
        if (script.getCharName().equals("player")) {
            g.drawString(pName, 285, 514);
        }
        else
            g.drawString(script.getCharName(), 285, 514);
        
        //DIALOGE TEXT
        if (text.length() > 45) { //if dialogue length is greater than 45 characteres
            String text1 = text.substring(0, text.indexOf(" ", (int) text.length()/3));
            String text2 = text.substring(text.indexOf(" ", (int) text.length()/3) + 1, text.indexOf(" ", (int) text.length()/3 * 2));
            String text3 = text.substring(text.indexOf(" ", (int) text.length()/3 * 2) + 1);
            g.drawString(text1, 255, 560);
            g.drawString(text2, 255, 600);
            g.drawString(text3, 255, 640);
        }
        else
            g.drawString(text, 255, 565);
    }
    
    public void writeScores(String n, int s) {
        HighScoreReader reader = new HighScoreReader ("Scores1");
        ArrayList<String> names = reader.getNames();
        ArrayList<Integer> scores = reader.getScores();
        if (scores.size() == 0) {
            names.add(n);
            scores.add(s);
        }
        else {
            for (int x = 0; x < scores.size(); x++) {
                if (s > (int) scores.get(x)) { //is bigger than a certain score, or actually a default
                    scores.add(x, s);
                    names.add(x, n);
                    if (scores.size() > 10) {
                        scores.remove(11);
                        names.remove(11);
                    }
                }
            }
        }
        HighScoreWriter writer = new HighScoreWriter(names, scores, "Scores1");
        writer.write();
    }
}