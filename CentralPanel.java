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
                    setVisible(false);
                    Main.entreePanel.setVisible(true);
                }
                catch(Exception x) {
                System.out.println(x.getMessage());
                }
            } 
            } );

    }


}