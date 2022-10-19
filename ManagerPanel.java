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


        JButton remove = new JButton("Remove Item");
        remove.setPreferredSize(new Dimension(200,50));
        remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                String item = JOptionPane.showInputDialog("Input name of item to remove");
                String itemType = JOptionPane.showInputDialog("Input type of item to remove");
                Boolean onMenu = Functions.hideItem(item);

                if (!onMenu) {
                    JOptionPane.showMessageDialog(null, "Item is not on menu");
                    return;
                }
                if (!itemType.equals("entree") && !itemType.equals("side") && !itemType.equals("extra")) {
                    JOptionPane.showMessageDialog(null, "Invalid type");
                    return;
                }
                
                ItemPanel xx = new ItemPanel(itemType);
                    if (itemType.equals("entree")) {
                        Main.entreePanel = xx;
                        Main.cards.add(xx, "entreePanel");
                    }
                    else if (itemType.equals("side")) {
                        Main.sidesPanel = xx;
                        Main.cards.add(xx, "sidesPanel");
                    }
                    else if(itemType.equals("extra")){
                        Main.extrasPanel = xx;
                        Main.cards.add(xx, "extrasPanel");
                    }

                JOptionPane.showMessageDialog(null, "Item removed");
                } catch (Exception x) {
                    System.out.println(x.getMessage());
                }
            }
        });

        add(inventory);
        add(menuItem);
        add(report);
        //add(analysis);
        add(remove);
        
        
    }
}
