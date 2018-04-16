
/**
Main Interface class.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class MainInterface extends Frame implements ActionListener
{
    private Label l1;
    private Button b1;
    private Button b2;
    private Button b3;
    private Hero hero1;
    private Hero hero2;
    private Hero hero3;
    ArrayList<Integer> set = new ArrayList<Integer>();
    public MainInterface(String title, Hero gameHero1, Hero gameHero2, Hero gameHero3)
    {
        super(title);
        FlowLayout f1 = new FlowLayout();
        setLayout(f1);
        l1 = new Label("Heroes and Monsters Adventure:", Label.CENTER);
        l1.setFont(new Font("SansSerif",Font.BOLD,18));
        l1.setBackground(Color.darkGray);
        l1.setForeground(Color.orange);
        setBackground(Color.lightGray);
        b1 = new Button("View " + gameHero1.getName());
        b2 = new Button("View " + gameHero2.getName());
        b3 = new Button("View " + gameHero3.getName());
        this.hero1 = gameHero1;
        this.hero2 = gameHero2;
        this.hero3 = gameHero3;
        super.setLocation(400,40);              // Sets the location of the GUI when program is run.
        setSize(320, 100);                      // Frame size, width x height
        setVisible(true);  
        add(l1);
        add(b1);
        add(b2);
        add(b3);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if (set.contains(1) && set.contains(2) && set.contains(3)){
            dispose();
        }
        
        if (e.getSource() == b1 && !set.contains(1)){ // Avoid multiple windows
            set.add(1); // Checks whether user clicked this button.
            HeroInterface heroMenu = new HeroInterface("Character Sheet: " + hero1.getName(), hero1);
        }
        else if (e.getSource() == b2 && !set.contains(2)){
            set.add(2);
            HeroInterface heroMenu = new HeroInterface("Character Sheet: " + hero1.getName(), hero2);
        }
        else if (e.getSource() == b3 && !set.contains(3)){
            set.add(3);
            HeroInterface heroMenu = new HeroInterface("Character Sheet: " + hero1.getName(), hero3);
        }

    }
}
