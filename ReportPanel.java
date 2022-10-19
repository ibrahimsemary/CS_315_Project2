import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.xml.crypto.dsig.spec.HMACParameterSpec;

import java.awt.event.*;
import java.sql.*;

/**
 * @category Manager Side
 * @summary Shows buttons to take user to sales report, excess report, restock report, and paired items
 * @see {@link PurchaseHistoryPanel}, {@link ExcessReportPanel}, {@link RestockReportPanel} 
 */
public class ReportPanel extends JPanel{
    
    ReportPanel() {
        JButton salesReport = new JButton("Sales Report");
        JButton excessReport = new JButton("Excess Report");
        JButton restockReport = new JButton("Restock Report");
        JButton popularPaired = new JButton("Popular Paired Items");
        JButton jb = new JButton("GO BACK");
        jb.setPreferredSize(new Dimension(200, 50));
        jb.setBackground(Color.GRAY);
        jb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                //go back
                Main.cardlayout.show(Main.cards, "managerPanel");
            }
        });

        salesReport.setPreferredSize(new Dimension(200, 50));
        excessReport.setPreferredSize(new Dimension(200, 50));
        restockReport.setPreferredSize(new Dimension(200, 50));
        popularPaired.setPreferredSize(new Dimension(200, 50));


        salesReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    String start = JOptionPane.showInputDialog("Start Date");
                    String end = JOptionPane.showInputDialog("End Date");
                    if (end == "") {end = "1/1/2500";}
                    Main.purchaseHistoryPanel = new PurchaseHistoryPanel(start, end);
                    Main.cards.add(Main.purchaseHistoryPanel, "purchaseHistoryPanel");
                    Main.cardlayout.show(Main.cards, "purchaseHistoryPanel");
                }
                catch(Exception x) {System.out.println(x.getMessage());}
            }
        });

        restockReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.restockReportPanel = new RestockReportPanel();
                    Main.cards.add(Main.restockReportPanel, "restockReportPanel");
                    Main.cardlayout.show(Main.cards, "restockReportPanel");
                } catch (Exception x) {
                    System.out.println(x.getMessage());
                }

            }
        });

        add(salesReport);
        add(excessReport);
        add(restockReport);
        add(popularPaired);
        add(jb);
    }


}
