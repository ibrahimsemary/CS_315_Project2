import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.*;

public class ManagerPanel extends JPanel{

    ManagerPanel() { 
        
        
        JButton inventory = new JButton("Inventory");
        JButton updateInv = new JButton("Update Inventory");
        JButton pHistory = new JButton("Purchase History");
        JButton analysis = new JButton("Analysis");
        JButton newItem = new JButton("New Item");
        JButton updateItem = new JButton("Update Item");
        JButton menuItem = new JButton("Items");
        inventory.setPreferredSize(new Dimension(200, 50));
        pHistory.setPreferredSize(new Dimension(200, 50));
        analysis.setPreferredSize(new Dimension(200, 50));
        newItem.setPreferredSize(new Dimension(200, 50));
        updateItem.setPreferredSize(new Dimension(200, 50));
        updateInv.setPreferredSize(new Dimension(200, 50));
        menuItem.setPreferredSize(new Dimension(200, 50));
        inventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.cardlayout.show(Main.cards, "inventoryPanel");
            }
        });

        newItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String itemName =  JOptionPane.showInputDialog("New Item to add:");
                String itemType = JOptionPane.showInputDialog("New Item type(Entree, Side, Extra)");
                String itemCost = JOptionPane.showInputDialog("New Item cost");
                Double cc = Double.parseDouble(itemCost);
                itemType = itemType.toLowerCase();
                if (!itemType.equals("entree") && !itemType.equals("side") && !itemType.equals("extra")){
                    JOptionPane.showMessageDialog(null,itemType + " type does not exist!");
                    return;
                }
                try {
                    Functions.addItem(itemName, itemType, cc);
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
                    MenuItemPanel dd = new MenuItemPanel();
                    Main.menuItemPanel = dd;
                    Main.cards.add(dd, "menuItemPanel");
                }
                catch (Exception x) {System.out.println(x.getMessage());}
                

            }
        });

        updateItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String itemName =  JOptionPane.showInputDialog("Update Item:");
                String itemType = JOptionPane.showInputDialog("New Item type(Entree, Side, Extra)");
                String itemCost = JOptionPane.showInputDialog("Item cost");
                Double cc = Double.parseDouble(itemCost);
                itemName = itemName.toLowerCase();
                itemType = itemType.toLowerCase();
                if (!itemType.equals("entree") && !itemType.equals("side") && !itemType.equals("extra")){
                    JOptionPane.showMessageDialog(null,itemType + " type does not exist!");
                    return;
                }
                try {
                        
                        Functions.updateItem(itemName, cc);
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
                        MenuItemPanel dd = new MenuItemPanel();
                        Main.menuItemPanel = dd;
                        Main.cards.add(dd, "menuItemPanel");
                    }
                
                catch (Exception x) {System.out.println(x.getMessage());}
                

            }
        });

        updateInv.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    String itemName =  JOptionPane.showInputDialog("Ingredient name:");
                    String itemType = JOptionPane.showInputDialog("expDate");
                    String itemCost = JOptionPane.showInputDialog("Amount");
                    Integer xx = Integer.parseInt(itemCost);

                    Functions.addBatch(itemName, xx, itemType);
                    InventoryPanel yy = new InventoryPanel();
                    Main.inventoryPanel = yy;
                    Main.cards.add(yy, "inventoryPanel");

                }
                catch(Exception x) {
                    System.out.println(x.getMessage());
                }
            }
        });

        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.cardlayout.show(Main.cards, "menuItemPanel");
            }
        });



        add(inventory);
        add(updateInv);
        add(menuItem);
        add(newItem);
        add(updateItem);
        add(pHistory);
        add(analysis);
        
    }
}
