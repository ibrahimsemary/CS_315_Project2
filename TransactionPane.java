import java.sql.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.*;

public class TransactionPane extends JPanel{
    TransactionPane() {
        // implicit call to super()
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Checkout");
        title.setFont(new Font(title.getFont().getName(), title.getFont().getStyle(), 50));
        title.setBorder(new LineBorder(Color.BLACK));
        add(title);
        
    }

    public void display(String name, String cost) {
        JLabel itemDisplay = new JLabel(name + "     $" + cost);
        itemDisplay.setFont(new Font(itemDisplay.getFont().getName(), itemDisplay.getFont().getStyle(), 15));
        add(itemDisplay);
    }
}
