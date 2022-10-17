import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.*;
import java.sql.SQLException;

public class ManagerPanel extends JPanel{

    ManagerPanel() throws SQLException { 
        
        
        JButton inventory = new JButton("Inventory");
        JButton pHistory = new JButton("Purchase History");
        JButton analysis = new JButton("Analysis");
        JButton menuItem = new JButton("Items");
        JButton report = new JButton("Report");

        inventory.setPreferredSize(new Dimension(200, 50));
        pHistory.setPreferredSize(new Dimension(200, 50));
        analysis.setPreferredSize(new Dimension(200, 50));
        menuItem.setPreferredSize(new Dimension(200, 50));
        report.setPreferredSize(new Dimension(200, 50));

        inventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.cardlayout.show(Main.cards, "inventoryPanel");
            }
        });

        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.cardlayout.show(Main.cards, "menuItemPanel");
            }
        });

        pHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String start = JOptionPane.showInputDialog("Start Date");
                    String end = JOptionPane.showInputDialog("End Date");
                    Main.purchaseHistoryPanel = new PurchaseHistoryPanel(start, end);
                    Main.cards.add(Main.purchaseHistoryPanel, "purchaseHistoryPanel");
                    Main.cardlayout.show(Main.cards, "purchaseHistoryPanel");
                } catch (Exception x) {
                    System.out.println(x.getMessage());
                }
            }
        });

        add(inventory);
        add(menuItem);
        add(pHistory);
        add(analysis);
        
    }
}
