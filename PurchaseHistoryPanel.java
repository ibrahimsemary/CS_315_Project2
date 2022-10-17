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

public class PurchaseHistoryPanel extends JPanel  {    
    protected JTextArea textArea;
    private final static String newline = "\n";
    private static ArrayList<Functions.Transaction> transactions;

    public PurchaseHistoryPanel() throws SQLException {
        super(new GridBagLayout());

        JButton jb = new JButton("GO BACK");
        jb.setPreferredSize(new Dimension(200, 50));
        jb.setBackground(Color.GRAY);
        jb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                //go back
                Main.cardlayout.show(Main.cards, "managerPanel");
            }
        });
        add(jb);  
        
        JLabel wrongDate = new JLabel("One or both of the selected dates is out of range");
 
        try {    String[] columnNames = {"date",
                                    "id",
                                    "cost"};

            String[] displayItem;

            Date today = new Date();
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            String now = df.format(today);

            transactions = Functions.getTransactions("10-6-2022", "10-12-2022");
            String[][] data = new String[transactions.size()][3];
            int i = 0;
            for (Functions.Transaction item : transactions) {
                displayItem = new String[3];
                displayItem[0] = item.date;
                displayItem[1] = "" + item.id;
                displayItem[2] = "" + item.cost;

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
        }
    }
}
