import javax.swing.JPanel;

import java.sql.*;
import java.awt.*;
import javax.swing.*;
public class checkoutButton extends JPanel{
    
    checkoutButton() {
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton checkout = new JButton("Checkout");        
        checkout.setPreferredSize(new Dimension(200, 50));
        add(checkout);
    }
}
