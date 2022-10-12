import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.*;

public class TopPanel extends JPanel{

    TopPanel() { 
        FlowLayout fl = new FlowLayout();
        fl.setHgap(300);   
        setLayout(fl);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        JButton home = new JButton("Home");
        JButton manager = new JButton("Manager Mode");
        home.setPreferredSize(new Dimension(200, 50));
        manager.setPreferredSize(new Dimension(200, 50));
        add(home);
        add(manager);
    }
}
