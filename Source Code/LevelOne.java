import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;

/**
 * Creates Part A of Level One of the game Wellness@Mac
 * <p> 
 * Time spent: 3 hours
 * 
 * @author M Chan and S Lan 
 * @version 1.0
 */
public class LevelOne extends JPanel implements ActionListener{
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
     * the next button
     */
    private JButton next;
    
    /**
     * Constructor that creates a new JFrame and adds the JPanel to it
     * @param player the player's name
     */
    public LevelOne(String player) {
        pName = player;
        frame = new JFrame("Wellness@Mac: Level One");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 700);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(this);
        addNext();
        script = new Script("1A Text", "1A Speaker", "1A Characters", "1A Background", pName);
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
    
    /**
     * Assigns the next line of the script
     * @param ae ActionEvent
     */
    public void actionPerformed (ActionEvent ae)
    {
        script.assign();
        repaint();
        revalidate();
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
        
        //IF IT'S NOT THE END OF THE SCRIPT
        if (!script.getText().equals("END")) {
            //BACKGROUND
            g.drawImage(getImage(script.getBG()), 0, 0, this);
            //CHARACTER SPRITES
            if(!script.getCharPic().equals("-"))
                g.drawImage(getImage(script.getCharPic()), 350, 75, this);
            
            //TEXTBOX
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
            if (script.getText().length() > 45) { //if dialogue length is greater than 45 characteres
                String text1 = script.getText().substring(0, script.getText().indexOf(" ", (int) script.getText().length()/3));
                String text2 = script.getText().substring(script.getText().indexOf(" ", (int) script.getText().length()/3) + 1, script.getText().indexOf(" ", (int) script.getText().length()/3 * 2));
                String text3 = script.getText().substring(script.getText().indexOf(" ", (int) script.getText().length()/3 * 2) + 1);
                g.drawString(text1, 255, 560);
                g.drawString(text2, 255, 600);
                g.drawString(text3, 255, 640);
            }
            else
                g.drawString(script.getText(), 255, 565);
        }
        else {
            frame.dispose();
            new RecipeScreen(pName);
        }
    }
}