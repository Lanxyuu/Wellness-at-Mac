import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import javax.swing.JOptionPane;

/**
 * This program creates a JFrame and adds a JPanel to it to create the screen
 * for Level Two of the Wellness@Mac game.
 * <p>
 * Time spent: 10 hours
 * 
 * @author M Chan and S Lan 
 * @version 1.0
 */
public class LevelTwo extends JPanel implements ActionListener{
    /**
     * The script of the level
     */
    private Script script;
    /**
     * The name of the player
     */
    private String pName = "";
    /**
     * The current text that is displayed
     */
    private String text = "";
    /**
     * The frame that this JPanel is added to
     */
    private JFrame frame;
    /**
     * The constraints of the SpringLayout of this level
     */
    private SpringLayout.Constraints nC;
    /**
     * The current score of the player
     */
    private int score = 0;
    /**
     * The "Next" button
     */
    private JButton next;
    /**
     * The SpringLayout of the JPanel
     */
    private SpringLayout spring;
    /**
     * The "Choose Option 1" button
     */
    private JButton badButton;
    /**
     * The "View Option 1" button
     */
    private JButton goodButton;
    /**
     * The "Choose Option 2" button
     */
    private JButton viewBad;
    /**
     * The "View Option 2" button
     */
    private JButton viewGood;
    /**
     * Number of choices made
     */
    private int choicesMade = 0;
    /**
     * Class constructor that creates a new JFrame and adds this to it
     * 
     * @param player name of the player
     */
    public LevelTwo(String player) {
        repaint();
        pName = player;
        frame = new JFrame("Wellness@Mac: Level Two");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setSize(1100, 700);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(this);
        
        addNext();
        implementChoices();
        showChoices(false);
        
        script = new Script("2A Text", "2A Speaker", "2A Characters", "2A Background", pName);
        script.assign();
        text = script.getText();
    }
    
    /**
     * Adds the "Next" button (a JButton) to the JPanel
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
    public void implementChoices() { //add choice buttons to jframe
        badButton = new JButton("Choose Option 1");
        badButton.setSize(5, 5);
        viewBad = new JButton("View Option 1");
        viewBad.setSize(5, 5);
        goodButton = new JButton("Choose Option 2");
        goodButton.setSize(5, 5);
        viewGood = new JButton("View Option 2");
        viewGood.setSize(5, 5);
        
        this.add(badButton);
        nC = spring.getConstraints(badButton);
        nC.setY(Spring.constant(300)); //depth - row-wise
        nC.setX(Spring.constant(130)); //length - column-wise
        this.add(viewBad);
        nC = spring.getConstraints(viewBad);
        nC.setY(Spring.constant(300)); //depth - row-wise
        nC.setX(Spring.constant(250)); //length - column-wise
        
        this.add(goodButton);
        nC = spring.getConstraints(goodButton);
        nC.setY(Spring.constant(300)); //depth - row-wise
        nC.setX(Spring.constant(700)); //length - column-wise
        this.add(viewGood);
        nC = spring.getConstraints(viewGood);
        nC.setY(Spring.constant(300)); //depth - row-wise
        nC.setX(Spring.constant(820)); //length - column-wise
        
        badButton.addActionListener(this);
        goodButton.addActionListener(this);
        viewGood.addActionListener(this);
        viewBad.addActionListener(this);
    }
    
    /** 
     * Changes the visibility of the buttons
     * 
     * @param flag Sets visibility to true or false based on it
     */
    public void showChoices(boolean flag) {
        badButton.setVisible(flag);
        viewBad.setVisible(flag);
        goodButton.setVisible(flag);
        viewGood.setVisible(flag);
    }
    
    /**
     * Changes the "Choose Option 1" and "Choose Option 2" buttons to
     * "Agree to plan an outing for Sabrina" and "Say that Sabrina should be focusing on her work", 
     * and hides the other two buttons.
     */
    public void showAgreeChoice() {
        badButton.setText("Agree to plan an outing for Sabrina.");
        goodButton.setText("Say that Sabrina should be focusing on her work.");
        badButton.setVisible(true);
        goodButton.setVisible(true);
        viewBad.setVisible(false);
        viewGood.setVisible(false);
    }
    
    /**Overrides the actionPerformed method of the ActionListener interface.
      * Assigns the Strings thisChoice and thatChoice based on number of choices
      * already made. If "Next" was pressed: checks whether or not the current line of the script requires
      * the player to make choices or decisions (signified by "(What should I say...?)" or
      * "(What should I do...?)" and makes the buttons visible. Also checks if the current
      * line changes based on points (signified by "-") and assigns the appropriate String
      * to text. Else, assigns the next line to the script and repaints and revalidates the
      * JPanel. If any other button was pressed, allows player to view options, or select them,
      * based on the button
      * 
      * @param ae the ActionEvent
      */
    public void actionPerformed (ActionEvent ae)
    {
        String thisChoice = "", thatChoice = "";
        
        //assigning thisChoice and thatChoice based on which part it's on
        switch (choicesMade) {
            case 0: thisChoice = "I don�t think continuing to push yourself for them like this is good for your health.";
            thatChoice = "You can�t manage them properly. It�s having an effect on your health.";
            break;
            case 1: thisChoice = "But I�m your friend, so I know what�s best for you. Just take a break for a few days, trust me.";
            thatChoice = "We all still need breaks sometimes, though. Everyone will understand if you take a break for just a few days!";
            break;
            case 2: thisChoice = "Well, I can tell when you�re doing something you shouldn�t be doing. Can�t you just listen to my advice?";
            thatChoice = "But I care about you. I believe that continuing what you're doing might have a serious effect on your health.";
            break;
            case 3: thisChoice = "That's great! I'm really glad that you're doing this, Sabrina.";
            thatChoice = "Okay, good. This is good for you, trust me.";
            break;
            case 4: thatChoice = "It'll help you keep track of your assignments. Trust me, you need one...";
            thisChoice = "They�re really useful for helping you stay organized. Why don�t you try it out?";
            break;
            case 5: thisChoice = "It might not be too bad to at least give it a try, right? It might be really beneficial for you!";
            thatChoice = "Well, you�ll have to fix your bad habits one way or another. Just follow my advice.";
            break;
        }
        
        if (ae.getActionCommand().equals("Next")) {
            if (!goodButton.isVisible()) {
                script.assign();
                text = script.getText();
                if (text.equals("(What should I say...?)")) {
                    showChoices(true);
                    next.setVisible(false);
                } 
                else if (text.equals("(What should I do...?)")) {
                    showAgreeChoice();
                    next.setVisible(false);
                } 
                else if (text.equals("-")) {
                    if (score <= 2)
                        text = "Yeah... Thanks, " + pName + ". I hope I'll feel better soon, I guess...";
                    else
                        text = "Yeah, I guess I was being a bit stubborn. Thanks, " + pName + "! I feel better already.";
                    repaint();
                    next.setVisible(true);
                    revalidate();
                } 
                else {
                    showChoices(false);
                    next.setVisible(true);
                    repaint();
                    revalidate();
                }
            }
        }
        else if (ae.getActionCommand().equals("View Option 1")) {
            JOptionPane.showMessageDialog(null, thisChoice);
        } 
        else if (ae.getActionCommand().equals("View Option 2")) {
            JOptionPane.showMessageDialog(null, thatChoice);
        } 
        else if (ae.getActionCommand().equals("Choose Option 1")) {
            switch (choicesMade) {
                case 0: text = "I don�t think continuing to push yourself for them like this is good for your health.";
                score++;
                break;
                case 1: text = "But I'm your friend, so I know what's best for you. Just take a break for a few days, trust me";
                break;
                case 2: text = "Well, I can tell when you're doing something that you shouldn't be doing. Can't you just listen to my advice?.";
                break;
                case 3: text = "That's great! I'm really glad that you're doing this, Sabrina.";
                score++;
                break;
                case 4: text = "They help you keep track of your assignments. Trust me, you need one.";
                break;
                case 5: text = "It might not be too bad to at least give it a try, right? It might be really beneficial for you!";
                score++;
                if (score < 4) {
                    script = new Script("2L Text", "2L Speaker", "2L Characters", "2L Background", pName);
                    script.assign();
                }
                break;
            }
            choicesMade++;
            repaint();
            revalidate();
            showChoices(false);
            next.setVisible(true);
        } 
        else if (ae.getActionCommand().equals("Choose Option 2")) {
            switch (choicesMade) {
                case 0: text = "You can�t manage them properly. It�s having an effect on your health.";
                break;
                case 1: text = "We all need breaks sometimes, though. Everyone will understand if you take a break for just a few days.";
                score++;
                break;
                case 2: text = "But I care about you. I believe that continuing what you're doing might have a serious effect on your health.";
                score++;
                break;
                case 3: text = "Okay, good. I know this is good for you, trust me.";
                break;
                case 4: text = "They�re really useful for helping you stay organized. Why don�t you try it out?";
                score++;
                break;
                case 5: text = "Well, you�ll have to fix your bad habits one way or another. Just follow my advice.";
                if (score < 4) {
                    script = new Script("2L Text", "2L Speaker", "2L Characters", "2L Background", pName);
                    script.assign();
                    /*repaint();
                    revalidate();
                    showChoices(false);*/
                }
                break;
            }
            choicesMade++;
            repaint();
            revalidate();
            showChoices(false);
            next.setVisible(true);
        } else if (ae.getActionCommand().equals("Agree to plan an outing for Sabrina.")) {
            repaint();
            revalidate();
            showChoices(false);
            next.setVisible(true);
        }
        else if (ae.getActionCommand().equals("Say that Sabrina should be focusing on her work.")) {
            script = new Script("2Dis Text", "2Dis Speaker", "2Dis Characters", "2Dis Background", pName);
            script.assign();
            /*repaint();
            revalidate();*/
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
        Color sabrinaText = new Color(255, 69, 0);
        Color monicaText = new Color(0, 114, 47);
        Color yuliaText = new Color(125, 12, 255);
        Color natalieText = new Color(255, 12, 174);
        Color playerText = Color.WHITE;
        
        //IF IT'S NOT THE END OF THE SCRIPT
        if (!script.end()) {
            //BACKGROUND
            g.drawImage(getImage(script.getBG()), 0, 0, this);
            //CHARACTER SPRITES
            if(!script.getCharPic().equals("-"))
                g.drawImage(getImage(script.getCharPic()), 350, 75, this);
            
            //TEXTBOX
            if (!script.getBG().equals("../Resources/Images/Backgrounds/levelTwoWinEnd.jpg") && !script.getBG().equals("../Resources/Images/Backgrounds/levelTwoLoseEnd.jpg"))
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
            if (text.length() > 40) { //if dialogue length is greater than 40 characteres
                String text1 = text.substring(0, text.indexOf(" ", (int) text.length()/3));
                String text2 = text.substring(text.indexOf(" ", (int) text.length()/3) + 1, text.indexOf(" ", (int) text.length()/3 * 2));
                String text3 = text.substring(text.indexOf(" ", (int) text.length()/3 * 2) + 1);
                g.drawString(text1, 255, 560);
                g.drawString(text2, 255, 600);
                g.drawString(text3, 255, 640);
            }
            else
                g.drawString(script.getText(), 255, 565);
        } else {
            writeScores(pName, score);
            frame.dispose();
        }
    }
    
    /**
     * Writes the scores of this level into a text file
     * 
     * @param n the name
     * @param s the score
     */
    public void writeScores(String n, int s) {
        HighScoreReader reader = new HighScoreReader ("Scores2");
        ArrayList<String>names = reader.getNames();
        ArrayList<Integer>scores = reader.getScores();
        if (scores.size() == 0) {
            names.add(n);
            scores.add(s);
        }
        else {
            for (int x = 0; x < scores.size(); x++) {
                if (s > (int)scores.get(x)) { //is bigger than a certain score, or actually a default
                    scores.add(x, s);
                    names.add(x, n);
                    if (scores.size() > 10) {
                        scores.remove(11);
                        names.remove(11);
                    }
                }
            }
        }
        HighScoreWriter writer = new HighScoreWriter(names, scores, "Scores2");
        writer.write();
    }
}
