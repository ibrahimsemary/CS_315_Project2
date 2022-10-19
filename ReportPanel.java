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
                    if (start == null) {return;}
                    else if (start.length() < 1) {start = "1/1/1000";}
                    String end = JOptionPane.showInputDialog("End Date");
                    if (end == null) {return;}
                    else if (end.length() < 1) {end = "1/1/2500";}
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

        excessReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String start = JOptionPane.showInputDialog("Date to view excess from:");
                    if (start == null) {return;}
                    else if (start.length() < 1) {start = "1/1/1000";}
                    Main.excessReportPanel = new ExcessReportPanel(start);
                    Main.cards.add(Main.excessReportPanel, "excessReportPanel");
                    Main.cardlayout.show(Main.cards, "excessReportPanel");
                } catch (Exception x) {
                    System.out.println(x.getMessage());
                }
            }
        });

        popularPaired.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    String start = JOptionPane.showInputDialog("Start Date");
                    if (start == null) {return;}
                    else if (start.length() < 1) {start = "1/1/1000";}
                    String end = JOptionPane.showInputDialog("End Date");
                    if (end == null) {return;}
                    else if (end.length() < 1) {end = "1/1/2500";}
                    Main.pairsPanel = new PairsPanel(start, end);
                    Main.cards.add(Main.pairsPanel, "pairsPanel");
                    Main.cardlayout.show(Main.cards, "pairsPanel");
                }
                catch(Exception x) {System.out.println(x.getMessage());}
            }
        });

        add(salesReport);
        add(excessReport);
        add(restockReport);
        add(popularPaired);
        add(jb);
    }


}
