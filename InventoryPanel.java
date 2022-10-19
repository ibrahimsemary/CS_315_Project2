import java.sql.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.*;

/**
 * @category Manager Side
 * @summary Creates a table containing all inventory item ids, names, and current amounts. Also contains
 * function to update the quantity of an item.
 */
public class InventoryPanel extends JPanel  {    
    protected JTextArea textArea;
    private final static String newline = "\n";
    private static ArrayList<Functions.InventoryItem> inventoryItems;

    /**
     * @throws SQLException
     */
    public InventoryPanel() throws SQLException {
        super(new GridBagLayout());

        JButton updateInv = new JButton("Update Inventory");
        updateInv.setPreferredSize(new Dimension(200, 50));
        updateInv.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    String itemName =  JOptionPane.showInputDialog("Ingredient name:");
                    String itemType = "1-1-1111";//JOptionPane.showInputDialog("expDate");
                    String itemCost = JOptionPane.showInputDialog("Amount");
                    Integer xx = Integer.parseInt(itemCost);

                    Functions.addBatch(itemName, xx, itemType);
                    InventoryPanel yy = new InventoryPanel();
                    Main.inventoryPanel = yy;
                    Main.cards.add(yy, "inventoryPanel");
                    Main.inventoryPanel.revalidate();
                    Main.cardlayout.show(Main.cards, "inventoryPanel");

                }
                catch(Exception x) {
                    System.out.println(x.getMessage());
                }
            }
        });

        JButton jb = new JButton("GO BACK");
        jb.setPreferredSize(new Dimension(200, 50));
        jb.setBackground(Color.GRAY);
        jb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                //go back
                Main.cardlayout.show(Main.cards, "managerPanel");
            }
        });

        add(updateInv);
        add(jb);

        String[] columnNames = {"id",
                                "name",
                                "quantity"};

        String[] newItem;
        
        inventoryItems = Functions.getInventoryItems();

        String[][] data = new String[inventoryItems.size()][3];

        int i = 0;

        for (Functions.InventoryItem item : inventoryItems) {
            newItem = new String[3];
            newItem[0] = "" + item.id;
            newItem[1] = item.name;
            newItem[2] = "" + item.quantity;

            data[i] = newItem;
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
