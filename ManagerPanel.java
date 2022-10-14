import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.*;

public class ManagerPanel extends JPanel{

    ManagerPanel() { 
        
        
        JButton inventory = new JButton("Inventory");
        JButton pHistory = new JButton("Purchase History");
        JButton analysis = new JButton("Analysis");
        JButton items = new JButton("Items");
        inventory.setPreferredSize(new Dimension(200, 50));
        pHistory.setPreferredSize(new Dimension(200, 50));
        analysis.setPreferredSize(new Dimension(200, 50));
        items.setPreferredSize(new Dimension(200, 50));
        add(inventory);
        add(pHistory);
        add(analysis);
        add(items);
    }
}
