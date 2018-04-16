
/**
Hero Interface for each Hero.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HeroInterface extends Frame implements ActionListener
{
    private Label l1;
    private Hero hero;
    private TextArea info;
    private TextField field1;
    private TextField field2;
    private TextField field3;
    private TextField field4;
    private TextField field5;
    private TextField field6;
    private Button b1;

    public HeroInterface(String title, Hero gameHero)
    {
        super(title);
        FlowLayout f1 = new FlowLayout();
        setLayout(f1);
        l1 = new Label("Hero Sheet: " + gameHero.getName(), Label.CENTER);
        l1.setFont(new Font("SansSerif",Font.BOLD,18));
        l1.setBackground(Color.darkGray);
        l1.setForeground(Color.orange);
        setBackground(Color.lightGray);
        this.hero = gameHero;
        field1 = new TextField("Hero Name: " + gameHero.getName());
        field1.setEditable(false);
        field2 = new TextField("Hero Health: " + gameHero.getHealth() + "/" + gameHero.getMaxHealth());
        field2.setEditable(false);
        field3 = new TextField("Hero Level: " + gameHero.getLevel());
        field3.setEditable(false);
        field4 = new TextField("Hero Experience: " + gameHero.getExp() + "/" + gameHero.getMaxExp());
        field4.setEditable(false);
        field5 = new TextField("Hero Gold: " + gameHero.getGold());
        field5.setEditable(false);
        field6 = new TextField("Current Room: " + gameHero.printCurrentRoomName());
        field6.setEditable(false);
        b1 = new Button("Refresh Data");
        
        
        super.setLocation(400,40);              // Sets the location of the GUI when program is run.
        setSize(250, 350);                      // Frame size, width x height

        setVisible(true);  
        add(l1);
        add(field1);
        add(field2);
        add(field3);
        add(field4);
        add(field5);
        add(field6);
        add(b1);
        field2.addActionListener(this);
        field3.addActionListener(this);
        field4.addActionListener(this);
        field5.addActionListener(this);
        field6.addActionListener(this);
        b1.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == b1){
            HeroInterface heroMenu = new HeroInterface("Character Sheet: " + hero.getName(), hero);
            dispose();
        }
    }

}
