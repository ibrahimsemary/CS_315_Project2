import javax.swing.JPanel;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
/**
 * @category Server Side
 * @summary Creates the checkout and clear buttons 
 */
public class checkoutButton extends JPanel{
    
    checkoutButton() throws SQLException {
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton checkout = new JButton("Checkout");        
        checkout.setPreferredSize(new Dimension(100, 50));
        checkout.setBackground(Color.GREEN);
        checkout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    TransactionPane.checkout();
                    InventoryPanel yy = new InventoryPanel();
                    Main.inventoryPanel = yy;
                    Main.cards.add(yy, "inventoryPanel");
                    TransactionPane.clear();
                }
                catch(Exception x) {
                    System.out.println(x.getMessage());
                }
            }
        });

        JButton clear = new JButton("CLEAR");
        clear.setPreferredSize(new Dimension(100,50));
        clear.setBackground(Color.RED);
        clear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                TransactionPane.clear();
            }
        });

        add(clear);
        add(checkout);
    }
}
