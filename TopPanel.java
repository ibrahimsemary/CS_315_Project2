import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.*;

/**
 * @category Server and Manager Side
 * @summary Creates the server and manager mode buttons at the top
 */
public class TopPanel extends JPanel{

    TopPanel() { 
        FlowLayout fl = new FlowLayout();
        fl.setHgap(300);   
        setLayout(fl);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        JButton home = new JButton("Server Mode");
        JButton manager = new JButton("Manager Mode");
        home.setPreferredSize(new Dimension(200, 50));
        home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.transactionPanel.setVisible(true);
                Main.checkoutPanel.setVisible(true);
                Main.cardlayout.show(Main.cards, "centralPanel");
            }
    });

        manager.setPreferredSize(new Dimension(200, 50));
        manager.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // 
                    //LoginDOPE.loadlogin();
                    Main.loginDope = new LoginDOPE();
                    Main.cards.add(Main.loginDope, "loginDope");
                    Main.cardlayout.show(Main.cards, "loginDope");
                }
        });

        add(home);
        add(manager);
    }
}
