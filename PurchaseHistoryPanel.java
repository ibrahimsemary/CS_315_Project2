import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.awt.event.*;
import java.util.Date;

/**
 * @category Manager Side
 * @summary Creates panel to show purchase history between the passed dates. Includes
 * a button to change the dates being shown.
 */
public class PurchaseHistoryPanel extends JPanel  {    
    protected JTextArea textArea;
    private final static String newline = "\n";
    private static ArrayList<ArrayList<String>> transactions;

    /**
     * 
     * @param start Beginning date for purchase history
     * @param end End date for purchase history
     * @throws SQLException
     */
    public PurchaseHistoryPanel(String start, String end) throws SQLException {
        super(new GridBagLayout());

        JButton jb = new JButton("GO BACK");
        JButton changeDates = new JButton("Change Dates");
        jb.setPreferredSize(new Dimension(200, 50));
        jb.setBackground(Color.GRAY);
        jb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                //go back
                Main.cardlayout.show(Main.cards, "reportPanel");
            }
        });

        changeDates.setPreferredSize(new Dimension(200,50));
        changeDates.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newStart =  JOptionPane.showInputDialog("Start Date");
                String newEnd = JOptionPane.showInputDialog("End Date");

                try {
                        PurchaseHistoryPanel newPHPanel = new PurchaseHistoryPanel(newStart, newEnd);
                        Main.purchaseHistoryPanel = newPHPanel;
                        Main.cards.add(newPHPanel, "purchaseHistoryPanel");
                        Main.purchaseHistoryPanel.revalidate();
                        Main.cardlayout.show(Main.cards, "purchaseHistoryPanel");
                    }
                
                catch (Exception x) {System.out.println(x.getMessage());}
            }
        });

        add(changeDates);
        add(jb);  
        
        JLabel wrongDate = new JLabel("One or both of the selected dates is out of range");

        try {    
            String[] columnNames = {"Name",
                                    "Amount Ordered"};

            String[] displayItem;

            transactions = Functions.getTopItems(start, end);
            String[][] data = new String[transactions.size()][2];
            int i = 0;
            for (ArrayList<String> item : transactions) {
                displayItem = new String[2];
                displayItem[0] = item.get(0);
                displayItem[1] = "" + item.get(1);

                data[i] = displayItem;
                i++;
            }
            final JTable table = new JTable(data, columnNames);
            table.setRowHeight(30);
            table.setPreferredScrollableViewportSize(table.getPreferredSize());

            //Create the scroll pane and add the table to it.
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(500, 500));
            //Add the scroll pane to this panel.
            add(scrollPane);
        } catch (Exception x) {
            wrongDate.setPreferredSize(new Dimension(400, 100));
            add(wrongDate);
            System.out.print(x.getMessage());
        }
    }
}
