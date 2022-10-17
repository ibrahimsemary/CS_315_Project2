import java.sql.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.*;

public class InventoryPanel extends JPanel  {    
    protected JTextArea textArea;
    private final static String newline = "\n";
    private static ArrayList<Functions.InventoryItem> inventoryItems;


    public InventoryPanel() throws SQLException {
        super(new GridBagLayout()); 
        /*super(new GridBagLayout());

        textArea = new JTextArea(10, 50);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);*/

        JButton updateInv = new JButton("Update Inventory");
        updateInv.setPreferredSize(new Dimension(200, 50));
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

        //JButton update = new JButton("Update Inventory");


        /*//Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.fill = GridBagConstraints.HORIZONTAL;

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);

        inventoryItems = Functions.getInventoryItems();

        for (Functions.InventoryItem item : inventoryItems) {
            
        }*/

        
 
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
        scrollPane.setPreferredSize(new Dimension(700, 700));
 
        //Add the scroll pane to this panel.
        add(scrollPane);
    }
}
