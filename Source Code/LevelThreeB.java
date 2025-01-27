import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;

/**
 * Creates Part B of Level Three of the game Wellness@Mac
 * <p> 
 * Time spent: 3 hours
 * 
 * @author M Chan and S Lan 
 * @version 1.0
 */
public class LevelThreeB extends JPanel implements ActionListener{
    /**
     * The script of the text portion of the level
     */
    private TextingScript script;
    /**
     * The script for the choices of the level
     */
    private ChoiceScript choices;
    /**
     * The name of the player
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
     * The next button
     */
    private JButton next;
    /**
     * The button that shows bad choices
     */
    private JButton badButton;
    /**
     * The sbutton that shows good choices
     */
    private JButton goodButton;
    /**
     * The button that displays bad choices
     */
    private JButton viewBad;
    /**
     * The button that displays good choices
     */
    private JButton viewGood;
    /**
     * Whether or not the choice selected was the wrong one
     */
    private boolean wrongAnswer = false;
    /**
     * Whether or not a choice was made
     */
    private boolean pickedChoice = false;
    /**
     * Whether or not it's the end
     */
    private boolean end = false;
    /**
     * Whether or not to close the JFrame
     */
    private boolean close = false;
    /**
     * The SpringLayout of this JPanel
     */
    private SpringLayout spring = new SpringLayout();
    /**
     * Label that displays the text "What should I tell her...?"
     */
    private JLabel label;
    
    /**Constructor for the LevelThreeB class 
      * @param player The name of the player
      */
    public LevelThreeB(String player) {
        pName = player;
        frame = new JFrame("Wellness@Mac: Level Three");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 700);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(this);
        this.setLayout(spring);
        implementNext();
        implementChoices();
        script = new TextingScript("3B Text", "3B Speaker", pName);
        choices = new ChoiceScript("3B Bad", "3B Good");
        showChoices(false);
        script.assign(); //starting off the new script!
    }
    
    /**
     * Adds the next button to the JPanel
     */
    public void implementNext() { //add next button to jpanel
        next = new JButton("Next");
        this.add(next);
        next.addActionListener(this);
        nC = spring.getConstraints(next);
        nC.setY(Spring.constant(638));
        nC.setX(Spring.constant(720));
    }
    
    /** 
     * Changes the visibility of the buttons
     * 
     * @param flag determines what to set visibility to
     */
    public void showChoices(boolean flag) {
        badButton.setVisible(flag);
        viewBad.setVisible(flag);
        goodButton.setVisible(flag);
        viewGood.setVisible(flag);
        label.setVisible(flag);
    }
    
    /**
     * Adds choice buttons to JPanel
     */
    public void implementChoices() { //add choice buttons to jframe
        badButton = new JButton("Choose Option 1");
        viewBad = new JButton("View Option 1");
        goodButton = new JButton("Choose Option 2");
        viewGood = new JButton("View Option 2");
        label = new JLabel ("What should I tell her...?");
        
        add(badButton);
        nC = spring.getConstraints(badButton);
        nC.setY(Spring.constant(600)); //depth - row-wise
        nC.setX(Spring.constant(130)); //length - column-wise
        add(viewBad);
        nC = spring.getConstraints(viewBad);
        nC.setY(Spring.constant(600)); //depth - row-wise
        nC.setX(Spring.constant(250)); //length - column-wise
        
        add(goodButton);
        nC = spring.getConstraints(goodButton);
        nC.setY(Spring.constant(600)); //depth - row-wise
        nC.setX(Spring.constant(700)); //length - column-wise
        add(viewGood);
        nC = spring.getConstraints(viewGood);
        nC.setY(Spring.constant(600)); //depth - row-wise
        nC.setX(Spring.constant(820)); //length - column-wise
        
        add(label);
        nC = spring.getConstraints(label);
        nC.setY(Spring.constant(638)); //depth - row-wise
        nC.setX(Spring.constant(475)); //length - column-wise
        
        badButton.addActionListener(this);
        goodButton.addActionListener(this);
        viewBad.addActionListener(this);
        viewGood.addActionListener(this);
    }
    
    /** 
     * Inserts a new line
     * 
     * @param input what to insert
     * @return input 
     */
    public String newLineInserter(String input) {
        int flag = input.indexOf(".");
        if (flag == -1)
            return input;
        return input.substring(0, flag + 1) + "\n" + newLineInserter(input.substring(flag + 1));
    }
    
    /**Overrides the actionPerformed method of the ActionListener interface.
      * Assigns the next line of the script if next was pressed
      * Else select/show choices based on the button
      * @param ae the ActionEvent
      */
    public void actionPerformed (ActionEvent ae)
    {
        if (ae.getSource() == next) { //clicked the next button
            script.assign();
            if (close) {
                frame.dispose();
            }
            else if (end) {
                add(new JLabel(new ImageIcon("../Resources/Images/Backgrounds/levelThreeLoseEnd.jpg")));
                revalidate();
                close = true;
            }
            else {
                repaint(); //write the next stuff
            }
            
            if ((script.getLine() == 2 || script.getLine() == 3 || 
                script.getLine() == 21 || script.getLine() == 26 ||
                script.getLine() == 32 || script.getLine() == 41 ||
                script.getLine() == 43 || script.getLine() == 45) && wrongAnswer == false) { //time to pick dialogue!
                next.setVisible(false); //hide next button
                choices.assign(); //read the next line of the choices
                showChoices(true); //show all the choice buttons!
            }
            revalidate(); //refresh the panel
        }
        else if (ae.getSource() == viewBad) {
            JOptionPane.showMessageDialog(null, newLineInserter(choices.getBadChoice()), "", JOptionPane.INFORMATION_MESSAGE);
            revalidate();
        }
        else if (ae.getSource() == viewGood) {      
            JOptionPane.showMessageDialog(null, newLineInserter(choices.getGoodChoice()), "", JOptionPane.INFORMATION_MESSAGE);            
            revalidate();
        }
        else if (ae.getSource() == badButton) {
            wrongAnswer = true; //screwed up
            pickedChoice = true;
            repaint(); //write what the player texted monica
            revalidate(); //refresh
            showChoices(false);
            next.setVisible(true);
        }
        else if (ae.getSource() == goodButton) {
            pickedChoice = true;
            repaint();
            revalidate();
            showChoices(false);
            next.setVisible(true);
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
        Color monicaText = new Color(0, 114, 47);
        
        //IF IT'S NOT THE END OF THE SCRIPT
        if (!script.getText().equals("END")) {
            
            //TEXT MESSAGE BACKGROUND

            //PICKED CHOICE
            //write the choice onscreen, before normal script
            if (script.getText().equals("Wednesday after school - 11 PM") 
                    || script.getText().equals("Thursday after school - 11 PM")) {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, 1100, 700);
                g.setColor(Color.WHITE);
                g.drawString(script.getText(), 50, 350);
                revalidate();
                return;
            }
            else {
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, 1100, 700);
                revalidate();
            }   
            
            if (pickedChoice) {
                g.setColor(Color.BLACK);
                if (wrongAnswer) {
                    choice = pName + ": " + choices.getBadChoice();
                }
                else {
                    choice = pName + ": " + choices.getGoodChoice();
                }
                if (choice.length() > 85) { //if dialogue length is greater than 85 characteres
                    mark = choice.indexOf(" ", 85);  
                    text1 = choice.substring(0, mark);
                    text2 = choice.substring(mark);
                    g.drawString(text1, 15, 30);
                    g.drawString(text2, 110, 60);
                }
                else
                    g.drawString(choice, 15, 30);
                pickedChoice = false;
                return;
            }
            
            if (wrongAnswer) {
                g.setColor(monicaText);
                text = "Monica: I don�t need more people like you to be so obsessed with me. If you want to be so involved in my life, I�m cutting you out of it. I�m not talking to you anymore.";
                
                mark = text.indexOf(" ", 85);  
                text1 = text.substring(0, mark);
                text2 = text.substring(mark);
                g.drawString(text1, 15, 30);
                g.drawString(text2, 110, 60);
                end = true;
                return;
            }
            else {
                //NORMAL SCRIPT
                //SETS TEXT COLOUR BASED ON SPEAKER
                switch (script.getSpeakerName()) {
                    case "Monica": g.setColor(monicaText);
                    break;
                    default: g.setColor(Color.BLACK); //if monica's not talking, player is
                }
                
                //write the text into a variable - continue with normal script
                if (script.getSpeakerName().equals("player")) {
                    text = pName + ": " + script.getText();
                }
                else {
                    text = "Monica: " + script.getText();
                }
                
                if (text.length() > 85) { //if dialogue length is greater than 85 characteres
                    mark = text.indexOf(" ", 75);
                    System.out.println(text);
                    System.out.println(mark);
                    text1 = text.substring(0, mark);
                    text2 = text.substring(mark);
                    g.drawString(text1, 15, 30);
                    g.drawString(text2, 110, 60); 
                }
                else
                    g.drawString(text, 15, 30);
                return;
            }
        }
        else {
            frame.dispose();
            new LevelThreeC(pName);
        }
    }
}