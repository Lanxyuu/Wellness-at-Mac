/**
 * The WellnessAtMACDriver is the driver class that executes
 * the game, Wellness@MAC: Healthy Living Simulator. It 
 * creates the main menu: allowing access to the game levels,
 * game rules and instructions, Help menu and FAQ, and High 
 * Scores table.
 * <p>
 * Time spent: since 1 pm - 1:45pm
 * 
 * @author M Chan and S Lan 
 * @version 4 28.05.18
 */ 
import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class WellnessAtMACDriver extends JFrame implements ActionListener{
    private JLabel current, titleScreen, faqScreen, aboutGameScreen, instructionsScreen, creditsScreen, scores1Screen, scores2Screen, scores3Screen;
    private ArrayList<String> names1, names2, names3;
    private ArrayList<Integer> leaderboard1, leaderboard2;
    private HighScoreReader reader1, reader2;
    private HighScoreReaderLevel3 reader3;
    private HighScoreWriter reseter;
    
    public WellnessAtMACDriver() {
        super("Wellness@MAC: Healthy Living Simulator!");
        setSize(1100,700);
        JMenuBar menus = new JMenuBar();
        
        /** Menus for the menubar */
        JMenu help = new JMenu("Help"); 
        JMenu levels = new JMenu("Play!");
        JMenu about = new JMenu("About");
        JMenu highScores = new JMenu("High Scores");
        
        /** Menu items for the menus */
        JMenuItem quit = new JMenuItem("Quit");
        JMenuItem faq = new JMenuItem("How to Use + FAQ");       
        JMenuItem level1 = new JMenuItem("Level 1");  
        JMenuItem level2 = new JMenuItem("Level 2");
        JMenuItem level3 = new JMenuItem("Level 3");
        JMenuItem aboutGame = new JMenuItem("What is Wellness@MAC?");
        JMenuItem instructions = new JMenuItem("Instructions + Game Rules");
        JMenuItem credits = new JMenuItem("Credits");
        JMenuItem scores1 = new JMenuItem("Level 1 Scores");
        JMenuItem scores2 = new JMenuItem("Level 2 Scores");
        JMenuItem scores3 = new JMenuItem("Level 3 Scores");
        JMenuItem clear1 = new JMenuItem("Clear Level 1 Scores");
        JMenuItem clear2 = new JMenuItem("Clear Level 2 Scores");
        JMenuItem clear3 = new JMenuItem("Clear Level 3 Scores");
        
        /** Registering the menu items as action listeners */
        quit.addActionListener(this);
        faq.addActionListener(this);
        level1.addActionListener(this);
        level2.addActionListener(this);
        level3.addActionListener(this);
        aboutGame.addActionListener(this);
        instructions.addActionListener(this);
        credits.addActionListener(this);
        scores1.addActionListener(this);
        scores2.addActionListener(this);
        scores3.addActionListener(this);
        clear1.addActionListener(this);
        clear2.addActionListener(this);
        clear3.addActionListener(this);
        
        /** Adding the menu items to their menus */
        help.add(quit);
        help.add(faq);
        levels.add(level1);
        levels.add(level2);
        levels.add(level3);
        about.add(aboutGame);
        about.add(instructions);
        about.add(credits);
        highScores.add(scores1);
        highScores.add(scores2);
        highScores.add(scores3);
        
        /** Adding the menus to the menu bar */
        menus.add(help);
        menus.add(levels);
        menus.add(about);
        menus.add(highScores);
        
        setJMenuBar(menus);
        
        /** Instantiating the different screens */
        titleScreen = new JLabel(new ImageIcon("../Resources/Images/Backgrounds/titleScreen.jpg"));
        faqScreen = new JLabel(new ImageIcon("../Resources/Images/Backgrounds/faq.jpg"));
        aboutGameScreen = new JLabel(new ImageIcon("../Resources/Images/Backgrounds/aboutGameScreen.jpg"));
        instructionsScreen = new JLabel(new ImageIcon("../Resources/Images/Backgrounds/instructions.jpg"));        
        creditsScreen = new JLabel(new ImageIcon("../Resources/Images/Backgrounds/credits.jpg"));        
        scores1Screen = new JLabel(new ImageIcon("../Resources/Images/Backgrounds/level1.jpg"));        
        scores2Screen = new JLabel(new ImageIcon("../Resources/Images/Backgrounds/level2.jpg"));        
        scores3Screen = new JLabel(new ImageIcon("../Resources/Images/Backgrounds/level3.jpg"));        
        
        add(titleScreen);
        current = titleScreen;
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);  
        
        /**Initially reading stuff from files*/
        reader1 = new HighScoreReader("Scores1");
        names1 = reader1.getNames();
        leaderboard1 = reader1.getScores();
        reader2 = new HighScoreReader("Scores2");
        names2 = reader2.getNames();
        leaderboard2 = reader2.getScores();
        reader3 = new HighScoreReaderLevel3("Scores3");
        names3 = reader3.getNames();
    }
    
    public void actionPerformed (ActionEvent ae) {
        if (ae.getActionCommand().equals ("Quit")) {
            dispose();
        }
        else if (ae.getActionCommand().equals("How to Use + FAQ")) {
            remove(current);
            add(faqScreen);
            current = faqScreen;
        }
        else if (ae.getActionCommand().equals("What is Wellness@MAC?")) {
            remove(current);
            add(aboutGameScreen);
            current = aboutGameScreen;
        }
        else if (ae.getActionCommand().equals("Instructions + Game Rules")) {
            remove(current);
            add(instructionsScreen);
            current = instructionsScreen;
        }
        else if (ae.getActionCommand().equals("Credits")) {
            remove(current);
            add(creditsScreen);
            current = creditsScreen;
        }
        else if (ae.getActionCommand().equals("Level 1 Scores")) {
            remove(current);
            add(scores1Screen);
            current = scores1Screen;
            
            names1 = reader1.getNames();
            leaderboard1 = reader1.getScores();
            add(new Drawing1());
        }
        else if (ae.getActionCommand().equals("Level 2 Scores")) {
            remove(current);
            add(scores2Screen);
            current = scores2Screen;
            
            names2 = reader2.getNames();
            leaderboard2 = reader2.getScores();
            add(new Drawing2());
        }
        else if (ae.getActionCommand().equals("Level 3 Scores")) {
            remove(current);
            add(scores3Screen);
            current = scores3Screen;
            
            names3 = reader3.getNames();
            add(new Drawing3());
        }
        else if (ae.getActionCommand().equals("Level 1")) {
            new NameBox(1);
        }        
        else if (ae.getActionCommand().equals("Level 2")) {
            new NameBox(2);
        }        
        else if (ae.getActionCommand().equals("Level 3")) {
            new NameBox(3);
        } 
        else if (ae.getActionCommand().equals("Clear Level 1 Scores")) {
            reseter = new HighScoreWriter (names1, leaderboard1, "Scores1");
            reseter.clear();
        }
        else if (ae.getActionCommand().equals("Clear Level 2 Scores")) {
            HighScoreWriter reseter = new HighScoreWriter (names2, leaderboard1, "Scores2");
            reseter.clear();
        }
        else if (ae.getActionCommand().equals("Clear Level 3 Scores")) {
            HighScoreWriterLevel3 reseter = new HighScoreWriterLevel3 (names3, "Scores3");
            reseter.clear();
        }
        revalidate();
    }
    
    class Drawing1 extends JComponent {
        public void paint (Graphics g) {
            Font myFont = new Font ("Roboto", 1, 25);
            for (int x = 0; x < names1.size(); x++) {
                g.drawString(names1.get(x), 150, 200 + x*30);
                g.drawString(names1.get(x), 700, 200 + x*30);
            }
        }
    }
    
    class Drawing2 extends JComponent {
        public void paint (Graphics g) {
            Font myFont = new Font ("Roboto", 1, 25);
            for (int x = 0; x < names2.size(); x++) {
                g.drawString(names2.get(x), 150, 200 + x*30);
                g.drawString(names2.get(x), 700 ,200 + x*30);
            }
        }
    }
    
    class Drawing3 extends JComponent {
        public void paint (Graphics g) {
            Font myFont = new Font ("Roboto", 1, 25);
            for (int x = 0; x < names3.size(); x++) {
                g.drawString(names3.get(x), 150, 200 + x*30);
                g.drawString("WIN! :-)", 700 ,200 + x*30);
            }
        }
    }
    
    public static void main(String[] args) { 
        new WellnessAtMACDriver();
    }
}
