import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.*;
import java.sql.*;

public class ItemPanel extends JPanel{
    
    ItemPanel(String item) throws SQLException{
        setVisible(false);
        String sql = "SELECT * FROM ITEMS WHERE type = '" + item + "';";
        ResultSet rs = Database.executeQuery(sql);
        String name, id;
        while (rs.next()) {
            name = rs.getString("name");
            id = rs.getString("id");
            JButton jb = new JButton(name);
            jb.setPreferredSize(new Dimension(200, 100));
            jb.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    // add to transaction
                    // Main.arrayname.add(id);
                    // Add to transaction
                    // go back
                }
            });
            add(jb);
        }

        name = "back";
        JButton jb = new JButton(name);
        jb.setPreferredSize(new Dimension(200, 100));
        jb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                //go back
            }
        });
        revalidate();
    }

}
