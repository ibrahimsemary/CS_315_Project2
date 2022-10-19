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
 * @category ManagerSide
 * @summary Creates table showing all items that sold less than 10% of their stock over a given timeframe
 */
public class ExcessReportPanel extends JPanel  {    
    protected JTextArea textArea;
    private final static String newline = "\n";
    private static ArrayList<Functions.excessIngredient> excessIng;

    /**
     * @throws SQLException
     */
    public ExcessReportPanel(String date) throws SQLException {
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
                                "quantity",
                                "amount sold"};

        String[] newItem;
        
        excessIng = Functions.excessReport(date);

        String[][] data = new String[excessIng.size()][4];

        int i = 0;

        for (Functions.excessIngredient item : excessIng) {
            newItem = new String[4];
            newItem[0] = "" + item.id;
            newItem[1] = item.name;
            newItem[2] = "" + item.totalAmount;
            newItem[3] = "" + item.amountSold;

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
