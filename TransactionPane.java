import java.sql.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.*;

public class TransactionPane extends JPanel{
    public static ArrayList<Functions.Item> itemIds;
    public double cost;
    public static JLabel totalCost;

    TransactionPane() {
        // implicit call to super()
        itemIds = new ArrayList<Functions.Item>();
        cost = 0.0;
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Checkout");
        title.setFont(new Font(title.getFont().getName(), title.getFont().getStyle(), 50));
        title.setBorder(new LineBorder(Color.BLACK));
        
        totalCost = new JLabel("Total: ");
        totalCost.setFont(new Font(title.getFont().getName(), title.getFont().getStyle(), 20));

        add(totalCost);
        add(title, 0);
    }

    public void display(String name) {
        JLabel itemDisplay = new JLabel(name);
        itemDisplay.setFont(new Font(itemDisplay.getFont().getName(), itemDisplay.getFont().getStyle(), 15));
        add(itemDisplay);
    }

    public static void clear() {
        TransactionPane newPane = new TransactionPane();
        Main.serverFrame.remove(Main.transactionPanel);
        Main.transactionPanel = newPane;
        Main.serverFrame.add(newPane, BorderLayout.EAST);
        Main.serverFrame.revalidate();
        itemIds.clear();
    }

    public static void checkout() throws SQLException {
        Functions.processTransaction(Main.transactionPanel.cost, itemIds);
    }
}
