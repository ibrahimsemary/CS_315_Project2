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
        inventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.cardlayout.show(Main.cards, "inventoryPanel");
            }
        });

        pHistory.setPreferredSize(new Dimension(200, 50));
        analysis.setPreferredSize(new Dimension(200, 50));
        items.setPreferredSize(new Dimension(200, 50));

        items.addActionListener(new ActionListener() {
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
                }
                catch (Exception x) {System.out.println(x.getMessage());}
                

            }
        });


        add(inventory);
        add(items);
        add(pHistory);
        add(analysis);
        
    }
}
