import java.sql.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import java.awt.event.*;

public class MenuItemPanel extends JPanel  {    
    protected JTextArea textArea;
    private final static String newline = "\n";
    private static ArrayList<Functions.Item> menuItems;

    public MenuItemPanel() throws SQLException {
        super(new GridBagLayout());

        JButton newItem = new JButton("New Item");
        JButton updateItem = new JButton("Update Item");

        newItem.setPreferredSize(new Dimension(200, 50));
        updateItem.setPreferredSize(new Dimension(200, 50));

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
                    Main.menuItemPanel.revalidate();
                    Main.cardlayout.show(Main.cards, "menuItemPanel");
                }
                catch (Exception x) {System.out.println(x.getMessage());}
                

            }
        });

        updateItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String itemName =  JOptionPane.showInputDialog("Update Item:");
                String itemType = JOptionPane.showInputDialog("Update Item type(Entree, Side, Extra)");
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
                        Main.menuItemPanel.revalidate();
                        Main.cardlayout.show(Main.cards, "menuItemPanel");
                    }
                
                catch (Exception x) {System.out.println(x.getMessage());}
                

            }
        });

        add(newItem);
        add(updateItem);

        JButton jb = new JButton("GO BACK");
        jb.setPreferredSize(new Dimension(200, 50));
        jb.setBackground(Color.GRAY);
        jb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                //go back
                Main.cardlayout.show(Main.cards, "managerPanel");
            }
        });
        add(jb);        
 
        String[] columnNames = {"id",
                                "name",
                                "cost",
                                "type"};

        String[] displayItem;
        
        menuItems = Functions.getItems();

        String[][] data = new String[menuItems.size()][4];

        int i = 0;

        for (Functions.Item item : menuItems) {
            displayItem = new String[4];
            displayItem[0] = "" + item.id;
            displayItem[1] = item.name;
            displayItem[2] = "" + item.cost;
            displayItem[3] = item.type;

            data[i] = displayItem;
            i++;
        }

        final JTable table = new JTable(data, columnNames);
        table.setRowHeight(30);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
 
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 500));
 
        //Add the scroll pane to this panel.
        add(scrollPane);
    }
}
