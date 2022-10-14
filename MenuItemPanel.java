import java.sql.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.*;

public class MenuItemPanel extends JPanel  {    
    protected JTextArea textArea;
    private final static String newline = "\n";
    private static ArrayList<Functions.Item> menuItems;

    public MenuItemPanel() throws SQLException {
        super(new GridLayout(1,0)); 
        /*super(new GridBagLayout());

        textArea = new JTextArea(10, 50);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);*/

        JButton jb = new JButton("GO BACK");
        jb.setPreferredSize(new Dimension(200, 100));
        jb.setBackground(Color.GRAY);
        jb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                //go back
                Main.cardlayout.show(Main.cards, "managerPanel");
            }
        });
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
                                "cost",
                                "type"};

        String[] newItem;
        
        menuItems = Functions.getItems();

        String[][] data = new String[menuItems.size()][3];

        int i = 0;

        for (Functions.Item item : menuItems) {
            newItem = new String[4];
            newItem[0] = "" + item.id;
            newItem[1] = item.name;
            newItem[2] = "" + item.cost;
            newItem[3] = item.type;

            data[i] = newItem;
            i++;
        }


 
        final JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
 
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
 
        //Add the scroll pane to this panel.
        add(scrollPane);
    }
}
