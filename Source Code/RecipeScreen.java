import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import javax.swing.JOptionPane;

/**
 * Creates a screen that allows the player to choose between a
 * list of 10 recipes and 3 pairs of morning tips (5 healthy recipes
 * and 1 healthy tip in each pair); used for Wellness@Mac Level One
 * <p>
 * Time spent: 5 hours
 * 
 * @author M Chan and S Lan 
 * @version 1.0
 */
public class RecipeScreen extends JPanel implements ActionListener{
    /**
     * The points accumlated from choosing a healthy recipe
     */
    private int recipePoints = 0;
    /**
     * The points accumlated from choosing a healthy tip
     */
    private int tipPoints = 0;
    /**
     * The y-coordinate of the red arrow
     */
    private int arrY = 80;
    /**
     * Holds the numbers of the recipes that were chosen
     */
    private ArrayList<Integer> recipeNumbers = new ArrayList<Integer>(0);
    /**
     * Holds booleans that represent whether or not the tip chosen was healthy
     */
    private ArrayList<Boolean> healthyTip = new ArrayList<Boolean>(0);
    /**
     * Accepts input for the user to choose their recipes/tips
     */
    private JTextField recipeField;
    /**
     * JFrame that this JPanel is added to
     */
    private JFrame recipeFrame;
    /**
     * The name of the player
     */
    private String playerName;
    /**
     * Whether or not it's time to choose recipes or tips
     */
    private boolean recipeChoice = true;
    
    /**
     * Class constructor which creates a JFrame and adds this JPanel to it
     * 
     * @param player the name of the player
     */
    public RecipeScreen (String player) {
        playerName = player;
        //CREATING JFRAME
        recipeFrame = new JFrame("Choose your recipes!");
        recipeFrame.setSize(1100, 700);
        recipeFrame.setResizable(false);
        recipeFrame.setVisible(true);
        recipeFrame.add(this); 
        
        //SUBMIT BUTTON
        SpringLayout spring = new SpringLayout();
        this.setLayout(spring);
        JButton submitButton = new JButton("Submit");
        this.add(submitButton);
        submitButton.addActionListener(this);
        SpringLayout.Constraints nC = spring.getConstraints(submitButton);
        nC.setY(Spring.constant(197));
        nC.setX(Spring.constant(675));
        
        //TEXTFIELD
        recipeField = new JTextField(5);
        this.add(recipeField);
        recipeField.addActionListener(this);
        SpringLayout.Constraints nRF = spring.getConstraints(recipeField);
        nRF.setY(Spring.constant(200));
        nRF.setX(Spring.constant(600));
        
        revalidate();
    }
    
    /**
     * Overrides the paintComponent method of the JPanel class and
     * draws the graphics of this part of the level
     * 
     *@param g Graphics
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Font iFont = new Font("Roboto", 1, 20);
        g.setFont(iFont);
        g.setColor(Color.BLACK);
        
        if (recipeChoice) {
            g.drawImage(getImage("../Resources/Images/Backgrounds/recipeScreen.jpg"), 0, 0, this);
            g.drawString("It's time to choose your recipes!", 25, 25);
            g.drawString("Enter the corresponding number into the text field, and press submit!", 350, 25);
            g.drawString("You can't undo any selections,", 560, 260);
            g.drawString("so choose carefully!", 645, 280);
            g.drawString("Remember, try to choose", 600, 310);
            g.drawString("the healthy ones!", 680, 330);
            g.drawString("The game will continue after", 580, 360);
            g.drawString("five recipes are chosen.", 620, 380);
            
            if (recipeNumbers.contains(1)) {
                g.drawLine(210, 120, 610, 120);
            } if (recipeNumbers.contains(2)) {
                g.drawLine(210, 155, 550, 155);
            } if (recipeNumbers.contains(3)) {
                g.drawLine(210, 190, 450, 190);
            } if (recipeNumbers.contains(4)) {
                g.drawLine(210, 225, 425, 225);
            } if (recipeNumbers.contains(5)) {
                g.drawLine(210, 260, 420, 260);
            } if (recipeNumbers.contains(6)) {
                g.drawLine(210, 295, 515, 295);
            } if (recipeNumbers.contains(7)) {
                g.drawLine(210, 330, 515, 330);
            } if (recipeNumbers.contains(8)) {
                g.drawLine(210, 365, 435, 365);
            } if (recipeNumbers.contains(9)) {
                g.drawLine(210, 400, 435, 400);
            } if (recipeNumbers.contains(10)) {
                g.drawLine(210, 435, 530, 435);
            }
        } else {
            g.drawImage(getImage("../Resources/Images/Backgrounds/tipScreen.jpg"), 0, 0, this);
            g.drawImage(getImage("../Resources/Images/arrow.png"), 110, arrY, this);
            g.setColor(Color.BLACK);
            if (healthyTip.size() != 0) {
                if (healthyTip.size() == 1) {
                    if (healthyTip.get(0) == true) { //FIRST OPTION
                        g.drawLine(210, 120, 600, 120); //A1
                    }
                    else if (healthyTip.get(0) == false)
                        g.drawLine(210, 155, 600, 155); //A2
                }
                if (healthyTip.size() == 2) { //SECOND OPTION
                    if (healthyTip.get(0) == true) {
                        g.drawLine(210, 120, 600, 120); //A1
                    }
                    else if (healthyTip.get(0) == false)
                        g.drawLine(210, 155, 600, 155); //A2
                    if (healthyTip.get(1) == true) {
                        g.drawLine(210, 225, 570, 225); //B1
                    }
                    else if (healthyTip.get(1) == false) { //B2 
                        g.drawLine(210, 260, 700, 260);
                        g.drawLine(210, 300, 700, 300);
                    }
                } if (healthyTip.size() == 3) { //THIRD OPTION
                    if (healthyTip.get(0) == true) {
                        g.drawLine(210, 120, 500, 120); //A1
                    }
                    else if (healthyTip.get(0) == false)
                        g.drawLine(210, 150, 500, 150); //A2
                    if (healthyTip.get(1) == true) {
                        g.drawLine(210, 180, 500, 180); //B1
                    }
                    else if (healthyTip.get(1) == false) 
                        g.drawLine(210, 210, 500, 210); //B2
                    if (healthyTip.get(2) == true) { //2
                        g.drawLine(210, 270, 500, 270); //C2
                    }
                    else if (healthyTip.get(2) == false)
                        g.drawLine(210, 240, 210, 240); //C1
                }
            }
            repaint();
            revalidate();
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
     * Overrides the actionPerformed method of the ActionListener interface that
     * registers chosen recipes/tips and adds them to the arraylist
     * Crosses out the chosen items and calculates score.
     */
    public void actionPerformed(ActionEvent ae) {
        boolean invalid = false;
        String number = recipeField.getText();
        int num = 0;
        //CHOOSING RECIPES
        if (recipeChoice) {
            try {
                num = Integer.parseInt(number);
                if (num < 1 || num > 10) {
                    JOptionPane.showMessageDialog(null, "Please enter a number between 1 to 10!");
                    recipeField.setText("");
                    invalid = true;
                } else if (recipeNumbers.contains(num)) {
                    JOptionPane.showMessageDialog(null, "You already chose that!");
                    recipeField.setText("");
                    invalid = true;
                }
            }
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a number between 1 to 10!");
                recipeField.setText("");
                invalid = true;
            }
            if (!invalid) {
                if (num == 1 || num == 2 || num == 6 || num == 9 || num == 10){
                    recipePoints++;
                }
                recipeNumbers.add(num);
                recipeField.setText("");
                
                repaint();
                revalidate();
                
                if (recipeNumbers.size() == 5) {
                    recipeFrame.setTitle("Choose your tips!");
                    recipeChoice = false;
                    repaint();
                    revalidate();
                }
            }
        } else { //CHOOSING TIPS
            try {
                num = Integer.parseInt(number);
                if (num < 1 || num > 2) {
                    JOptionPane.showMessageDialog(null, "Please enter a number between 1 to 2!");
                    recipeField.setText("");
                    invalid = true;
                }
            }
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a number between 1 to 10!");
                recipeField.setText("");
                invalid = true;
            }
            if (!invalid) {
                if (healthyTip.size() == 0) {
                    arrY += 125;
                    if (num == 1) {
                        tipPoints++;
                        healthyTip.add(true);
                    }
                    else
                        healthyTip.add(false);
                } else if (healthyTip.size() == 1) {
                    arrY += 160;
                    if (num == 1) {
                        tipPoints++;
                        healthyTip.add(true);
                    }
                    else
                        healthyTip.add(false);
                } 
                else if (healthyTip.size() == 2) {
                    if (num == 2) {
                        tipPoints++;
                        healthyTip.add(true);
                    }
                    else
                        healthyTip.add(false);
                    recipeFrame.dispose();
                    new LevelOneB(playerName, healthyTip, recipePoints);
                }
                recipeField.setText("");
                repaint();
                revalidate();
            }
        }
    }
}