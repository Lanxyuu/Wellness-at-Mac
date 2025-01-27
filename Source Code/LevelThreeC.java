import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import javax.swing.JOptionPane;

/**
 * Creates Part C of Level Three of the game Wellness@Mac
 * <p> 
 * Time spent: 3 hours
 * 
 * @author M Chan and S Lan 
 * @version 1.0
 */
public class LevelThreeC extends JPanel implements ActionListener{
    /**
     * The script of the text portion of the level
     */
    private Script script;
    /**
     * The name of the player
     */
    private String pName = "";
    /**
     * The current text that displays on the screen
     */
    private String text = "";
    /**
     * The JFrame of the level
     */
    private JFrame frame;
    /**
     * The SpringLayout of the level
     */
    private SpringLayout.Constraints nC;
    /**
     * Number of choices already made
     */
    private int choicesMade = 0;
    /**
     * The SpringLayout of this level
     */
    private SpringLayout spring;
    /**
     * The next button
     */
    private JButton next;
    /**
     * The first choice button
     */
    private JButton button1;
    /**
     * The second choice button
     */
    private JButton button2;
    /**
     * The first view button
     */
    private JButton view1;
    /**
     * The second view button
     */
    private JButton view2;
    
    /**Constructor for the LevelThreeB class 
      * @param player The name of the player
      */
    public LevelThreeC(String player) {
        repaint();
        pName = player;
        frame = new JFrame("Wellness@Mac: Level Three");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setSize(1100, 700);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(this);
        
        addNext();
        implementChoices();
        showChoices(false);
        
        script = new Script("3C Text", "3C Speaker", "3C Characters", "3C Background", pName);
        script.assign();
        text = script.getText();
    }
    
    /**
     * Adds the next button to the JPanel
     */
    public void addNext() {
        //set layout adds the "next" button to the jframe
        spring = new SpringLayout();
        this.setLayout(spring);
        next = new JButton("Next");
        this.add(next);
        next.addActionListener(this);
        nC = spring.getConstraints(next);
        nC.setY(Spring.constant(638));
        nC.setX(Spring.constant(720));
    }
    
    /**
     * Adds the choice buttons to the JPanel
     */
    public void implementChoices() { //add choice buttons to jpanel
        button1 = new JButton("Choose Option 1");
        button1.setSize(5, 5);
        view1 = new JButton("View Option 1");
        view1.setSize(5, 5);
        button2 = new JButton("Choose Option 2");
        button2.setSize(5, 5);
        view2 = new JButton("View Option 2");
        view2.setSize(5, 5);
        
        this.add(button1);
        nC = spring.getConstraints(button1);
        nC.setY(Spring.constant(300)); //depth - row-wise
        nC.setX(Spring.constant(130)); //length - column-wise
        this.add(view1);
        nC = spring.getConstraints(view1);
        nC.setY(Spring.constant(300)); //depth - row-wise
        nC.setX(Spring.constant(250)); //length - column-wise
        
        this.add(button2);
        nC = spring.getConstraints(button2);
        nC.setY(Spring.constant(300)); //depth - row-wise
        nC.setX(Spring.constant(700)); //length - column-wise
        this.add(view2);
        nC = spring.getConstraints(view2);
        nC.setY(Spring.constant(300)); //depth - row-wise
        nC.setX(Spring.constant(820)); //length - column-wise
        
        button1.addActionListener(this);
        button2.addActionListener(this);
        view2.addActionListener(this);
        view1.addActionListener(this);
    }
    
    /**
     * changes visibility of the buttons
     * 
     * @param flag determines what to set visibility to
     */
    public void showChoices(boolean flag) {
        button1.setVisible(flag);
        view1.setVisible(flag);
        button2.setVisible(flag);
        view2.setVisible(flag);
    }
    
    /**Overrides the actionPerformed method of the ActionListener interface.
      * Assigns the next line of the script if next was pressed
      * Else select/show choices based on the button
      * 
      * @param ae the ActionEvent
      */
    public void actionPerformed (ActionEvent ae)
    {
        String thisChoice = "", thatChoice = "";
        
        //assigning thisChoice and thatChoice based on which part it's on
        switch (choicesMade) {
            case 0: thisChoice = "Tell him everything about why you�re leaving him, and insult him to let him know he's a jerk. Go out with a bang!"; //bad
            thatChoice = "You don�t have to tell him much. You can just tell him that it's not working out - and give a few reasons why, if you want."; //good
            break;
            case 1: thisChoice = "You don�t have to respond, because you don�t owe him anything. You can delete and block all of his contacts, if you'd like."; //good
            thatChoice = "You should contact him back to clear anything up. Even though he abused you, you still owe him full closure."; //bad
            break;
            case 2: thisChoice = "Just remind him that you can call 9-1-1 - you can record the phone call or screenshot your texts, as evidence."; //goood
            thatChoice = "Just give me his number, and I can threaten him. Don't get the authorities involved, because it's not their business."; //bad
            break;
        }
        
        if (ae.getActionCommand().equals("Next")) {
            if (!button2.isVisible()) {
                script.assign();
                text = script.getText();
                if (text.equals("(What should I say...?)")) {
                    showChoices(true);
                    next.setVisible(false);
                } 
                repaint();
                revalidate();
            }
        }
         else if (ae.getActionCommand().equals("View Option 1")) {
            JOptionPane.showMessageDialog(null, thisChoice);
        } else if (ae.getActionCommand().equals("View Option 2")) {
            JOptionPane.showMessageDialog(null, thatChoice);
        } else if (ae.getActionCommand().equals("Choose Option 1")) {
            switch (choicesMade) {
                case 0: 
                    text = "Tell him everything about why you�re leaving him, and insult him to let him know he's a jerk. Go out with a bang!";
                    script = new Script("3L Text", "3L Speaker", "3L Characters", "3L Background", pName);
                    script.assign();
                break;
                case 1: 
                    text = "You don�t have to respond, because you don�t owe him anything. You can delete and block all of his contacts, if you'd like.";
                break;
                case 2:
                    text = "Just remind him that you can call 9-1-1 - you can record the phone call or screenshot your texts, as evidence.";
                break;
            }
            choicesMade++;
            repaint();
            revalidate();
            showChoices(false);
            next.setVisible(true);
        } else if (ae.getActionCommand().equals("Choose Option 2")) {
            switch (choicesMade) {
                case 0: text = "You don�t have to tell him much. You can just tell him that it's not working out - and give a few reasons why, if you want.";
                break;
                case 1: 
                    text = "You should contact him back to clear anything up. Even though he abused you, you still owe him full closure.";
                    script = new Script("3L Text", "3L Speaker", "3L Characters", "3L Background", pName);
                    script.assign();
                break;
                case 2: 
                    text = "Just give me his number, and I can threaten him. Don't get the authorities involved, because it's not their business.";
                    script = new Script("3L Text", "3L Speaker", "3L Characters", "3L Background", pName);
                    script.assign();
                break;
            }
            choicesMade++;
            repaint();
            revalidate();
            showChoices(false);
            next.setVisible(true);
        }
    }
    
    /** Finds a file and returns it as a BufferedImage
      * @param imageName name of the file
      * @return a BufferedImage
      */
    private BufferedImage getImage (String imageName) {
        try {
            return ImageIO.read(new File(imageName));
        } catch (IOException e) {return null;}
    }
    
    /**Overrides the paintComponent method of the JPanel class
      *Draws the graphics of the game
      * 
      * @param g Graphics class
      */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //setting the font and creating colours
        Font sFont = new Font("Roboto", 1, 25);
        g.setFont(sFont);
        Color monicaText = new Color(0, 114, 47);
        
        //IF IT'S NOT THE END OF THE SCRIPT
        if (!script.end()) {
            //BACKGROUND
            g.drawImage(getImage(script.getBG()), 0, 0, this);
            //CHARACTER SPRITES
            if(!script.getCharPic().equals("-"))
                g.drawImage(getImage(script.getCharPic()), 350, 75, this);
            
            //TEXTBOX
            if (!script.getBG().equals("../Resources/Images/Backgrounds/levelThreeLoseEnd.jpg"))
                g.drawImage(getImage("../Resources/Images/textbox.png"), 210, 480, this);
            revalidate();
            
            //SETS TEXT COLOUR BASED ON SPEAKER
            switch (script.getCharName()) {
                case "Monica": g.setColor(monicaText);
                break;
                default: g.setColor(Color.WHITE);
            }
            
            //SPEAKER NAME
            if (script.getCharName().equals("player")) {
                g.drawString(pName, 285, 514);
            }
            else
                g.drawString(script.getCharName(), 285, 514);
            
            //DIALOGE TEXT
            if (text.length() > 40) { //if dialogue length is greater than 45 characteres
                String text1 = text.substring(0, text.indexOf(" ", (int) text.length()/3));
                String text2 = text.substring(text.indexOf(" ", (int) text.length()/3) + 1, text.indexOf(" ", (int) text.length()/3 * 2));
                String text3 = text.substring(text.indexOf(" ", (int) text.length()/3 * 2) + 1);
                g.drawString(text1, 255, 560);
                g.drawString(text2, 255, 600);
                g.drawString(text3, 255, 640);
            }
            else
                g.drawString(script.getText(), 255, 565);
        } 
        else {
            frame.dispose();
            new LevelThreeD(pName);
        }
    }
}
