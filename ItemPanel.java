import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.function.Function;

public class ItemPanel extends JPanel{
    
    ItemPanel(String item) throws SQLException{
        setVisible(false);
        ArrayList<Functions.Item> items = Functions.getItems();
        for(Functions.Item my_item: items){
            if (!my_item.type.equals(item)) continue;
            JButton jb = new JButton(my_item.name);
            jb.setPreferredSize(new Dimension(200, 100));
            jb.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    // add to transaction
                    // Main.arrayname.add(id);
                    // Add to transaction
                    // go back
                    if(item == "combo") {
                        
                    }
                    Main.transactionPanel.display(my_item.name);
                    Main.transactionPanel.cost += my_item.cost;
                    Main.transactionPanel.itemIds.add(my_item);
                    Main.cardlayout.show(Main.cards, "centralPanel");
                }
            });
            add(jb);
        }

        JButton jb = new JButton("GO BACK");
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
