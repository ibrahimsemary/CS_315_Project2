import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.*;
import java.sql.*;

public class ItemPanel extends JPanel{
    
    ItemPanel(String item) throws SQLException{
        //setVisible(false);
        String sql = "SELECT * FROM ITEMS WHERE type = '" + item + "';";
        ResultSet rs = Database.executeQuery(sql);
        String name;
        while (rs.next()) {
            name = rs.getString("name");
            JButton jb = new JButton(name);
            jb.setPreferredSize(new Dimension(200, 100));
            add(jb);
        }
        revalidate();
    }

}
