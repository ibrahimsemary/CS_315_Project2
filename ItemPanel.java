import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class ItemPanel extends JPanel{
    
    ItemPanel(String item) throws SQLException{
        setVisible(false);
        String sql = "SELECT * FROM ITEMS WHERE type = '" + item + "';";
        ResultSet rs = Database.executeQuery(sql);
        String name, id, cost;
        while (rs.next()) {
            name = rs.getString("name");
            id = rs.getString("id");
            cost = rs.getString("cost");
            final String innername = name;
            final String innerid = id;
            final String innercost = cost;
            JButton jb = new JButton(name);
            jb.setPreferredSize(new Dimension(200, 100));
            jb.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    // add to transaction
                    // Main.arrayname.add(id);
                    // Add to transaction
                    // go back
                    Main.transactionPanel.display(innername, innercost);
                    Main.transactionPanel.itemIds.add(innerid);
                    Main.cardlayout.show(Main.cards, "centralPanel");
                }
            });
            add(jb);
        }

        name = "GO BACK";
        JButton jb = new JButton(name);
        jb.setPreferredSize(new Dimension(200, 100));
        jb.setBackground(Color.GRAY);
        jb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                //go back
                Main.cardlayout.show(Main.cards, "centralPanel");
            }
        });
        add(jb);
        revalidate();
    }

}
