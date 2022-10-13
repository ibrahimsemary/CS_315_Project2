import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.*;

public class ManagerTopPanel extends JPanel{

    ManagerTopPanel() { 
        setVisible(false);
        FlowLayout fl = new FlowLayout();
        fl.setHgap(300);   
        setLayout(fl);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        JButton server = new JButton("Server Mode");
        JButton inventory = new JButton("Inventory");
        JButton pHistory = new JButton("Purchase History");
        JButton analysis = new JButton("Analysis");
        JButton items = new JButton("Items");
        server.setPreferredSize(new Dimension(200, 50));
        add(server);
        add(inventory);
        add(pHistory);
        add(analysis);
        add(items);
    }
}
