import javax.swing.JPanel;

import java.sql.*;
import java.awt.*;
import javax.swing.*;
public class checkoutButton extends JPanel{
    
    checkoutButton() {
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton checkout = new JButton("Checkout");        
        checkout.setPreferredSize(new Dimension(100, 50));
        checkout.setBackground(Color.GREEN);

        JButton clear = new JButton("CLEAR");
        clear.setPreferredSize(new Dimension(100,50));
        clear.setBackground(Color.RED);

        add(clear);
        add(checkout);
    }
}
