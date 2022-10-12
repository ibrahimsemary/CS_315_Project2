import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.*;

public class topPanel extends JPanel{

    topPanel() { 
        FlowLayout fl = new FlowLayout();
        fl.setHgap(300);   
        setLayout(fl);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        JButton home = new JButton("Home");
        JButton manager = new JButton("Manager Mode");
        home.setPreferredSize(new Dimension(200, 50));
        home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //function to open default window
            }
        });
        
        manager.setPreferredSize(new Dimension(200, 50));
        manager.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //function to open manager window
            }
        });

        add(home);
        add(manager);
    }
}
