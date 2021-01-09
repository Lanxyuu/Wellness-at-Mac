import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

/**
 * Creates a box that asks user for their name, before creating 
 * a new Level of the Wellness@Mac game
 * <p>
 * Time spent: 1 hour
 * 
 *@author M Chan and S Lan 
 *@version 1.0
 */

public class NameBox extends JFrame implements ActionListener
{
    /**
     * text field for the player to input their name
     */
    private JTextField nameText;
    /**
     * the SpringLayout of the Jpanel
     */
    private SpringLayout spring;
    /**
     * the JPanel
     */
    private JPanel panel;
    /**
     * the name of the player
     */
    private String playerName;
    /**
     * the level that's to be made after name is entered
     */
    private int level;
    
    /**
     * Class constructor
     * @param l the level that was selected
     */
    public NameBox(int l)
    {
        level = l;
        
        //creating the frame
        this.setVisible(true);
        this.setSize(300, 200);
        this.setTitle("Please enter your name!");
        panel = new JPanel();
        panel.setVisible(true);
        spring = new SpringLayout();
        panel.setSize(350, 160);
        panel.setLayout(spring);
        
        //creating each component
        JLabel nameLabel = new JLabel ("Name: ");
        JButton submitButton = new JButton ("Submit");
        nameText = new JTextField(10);
        
        //setting constraint values for nameLabel
        SpringLayout.Constraints nameC = spring.getConstraints(nameLabel);
        nameC.setY(Spring.constant(5));
        nameC.setX(Spring.constant(10));
        panel.add(nameLabel);
        
        //setting constraint values for nameText
        SpringLayout.Constraints nameTC = spring.getConstraints(nameText);
        nameTC.setY(Spring.constant(5));
        nameTC.setX(Spring.sum(Spring.constant(5), nameC.getConstraint("East")));
        panel.add(nameText);
        
        //setting constraint values for submitButton
        SpringLayout.Constraints submitC = spring.getConstraints(submitButton);
        submitC.setY(Spring.constant(60));
        submitC.setX(Spring.constant(5));
        submitButton.addActionListener(this);
        
        panel.add(submitButton);
        this.add(panel);
        revalidate();
    }
    
    /**
     * Overrides the actionPerformed method of the ActionListener interface
     * Checks nameField for text, if it's suitable, then dispose of this
     * JFrame and create a new level, else create a JDialog and reset the text field
     */
    public void actionPerformed (ActionEvent ae)
    {
        if (nameText.getText().equals("") || nameText.getText().length() > 10) {
            JOptionPane.showMessageDialog(null, "Please enter a valid name under 10 characters!");
            nameText.setText("");
        }
        else {
            playerName = nameText.getText();
            dispose();
            
            if (level == 1) {
                LevelOne oneA = new LevelOne(playerName);
            }
            
            else if (level == 2) {
                LevelTwo two = new LevelTwo(playerName);
            }
            else if (level == 3) {
                LevelThree threeA = new LevelThree(playerName);
            }
        }
    }
}