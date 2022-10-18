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

public class RestockReportPanel extends JPanel  {    
    protected JTextArea textArea;
    private final static String newline = "\n";
    private static ArrayList<Functions.InventoryItem> underExcess;

    public RestockReportPanel() throws SQLException {
        super(new GridBagLayout());

        JButton jb = new JButton("GO BACK");
        jb.setPreferredSize(new Dimension(200, 50));
        jb.setBackground(Color.GRAY);
        jb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                //go back
                Main.cardlayout.show(Main.cards, "reportPanel");
            }
        });

        add(jb);  
        
        String[] columnNames = {"id",
                                "name",
                                "quantity"};

        String[] newItem;
        
        underExcess = Functions.underStockedIngredients();

        String[][] data = new String[underExcess.size()][3];

        int i = 0;

        for (Functions.InventoryItem item : underExcess) {
            newItem = new String[3];
            newItem[0] = "" + item.id;
            newItem[1] = item.name;
            newItem[2] = "" + item.quantity;

            data[i] = newItem;
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
    }
}
