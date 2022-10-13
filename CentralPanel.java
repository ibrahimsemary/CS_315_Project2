import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.*;
import java.sql.SQLException;

public class CentralPanel extends JPanel{
    
    CentralPanel() {

        JButton entree = new JButton("Entrees"); // entree
        JButton sides = new JButton("Sides"); // side
        JButton combos = new JButton("Combos"); //meal
        JButton extras = new JButton("Extras"); // extra

        int w = 200;
        int h = 200;

        entree.setPreferredSize(new Dimension(w, h));
        sides.setPreferredSize(new Dimension(w, h));
        combos.setPreferredSize(new Dimension(w, h));
        extras.setPreferredSize(new Dimension(w, h));

        add(entree);
        add(sides);
        add(combos);
        add(extras);
        entree.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                try {
                    Main.cardlayout.show(Main.cards, "entreePanel");
                }
                catch(Exception x) {
                System.out.println(x.getMessage());
                }
            } 
            } );

            sides.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                try {
                    Main.cardlayout.show(Main.cards, "sidesPanel");
                }
                catch(Exception x) {
                System.out.println(x.getMessage());
                }
            } 
            } );
        
            extras.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                try {
                    Main.cardlayout.show(Main.cards, "extrasPanel");
                }
                catch(Exception x) {
                System.out.println(x.getMessage());
                }
            } 
            } );

            combos.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        Main.cardlayout.show(Main.cards, "combosPanel");
                    }
                    catch(Exception x) {
                        System.out.println(x.getMessage());
                    }
                }
            });
        
    }


}