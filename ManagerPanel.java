import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.*;
import java.sql.SQLException;

/**
 * @category Manager Side
 * @summary Creates buttons to take user to inventory, items, or reports
 * @see {@link InventoryPanel}, {@link MenuItemPanel}, {@link ReportPanel}
 */
public class ManagerPanel extends JPanel{

    /**
     * @throws SQLException
     */
    ManagerPanel() throws SQLException { 
        
        
        JButton inventory = new JButton("Inventory");
        // JButton analysis = new JButton("Analysis");
        JButton menuItem = new JButton("Items");
        JButton report = new JButton("Report");

        inventory.setPreferredSize(new Dimension(200, 50));
       // analysis.setPreferredSize(new Dimension(200, 50));
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



        report.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    Main.cardlayout.show(Main.cards, "reportPanel");

                }
                catch(Exception x) {System.out.println(x.getMessage());}
            }
        });

        add(inventory);
        add(menuItem);
        add(report);
        add(analysis);
        
        
    }
}
