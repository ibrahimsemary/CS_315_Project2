import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
// Compile: javac *.java
// Run: java -cp ".;postgresql-42.2.8.jar" Main

public class Main {

    static JFrame frame;
    
    static void processTransaction(double cost, int[] items) throws SQLException{
        
        ResultSet res = Database.executeQuery("SELECT MAX(transactionid) FROM transactions");
        System.out.println(res);
        // INSERT INTO transactions VALUES (id, curDate, cost)
        for(int itemid : items){
            // INSERT INTO transactionsitems (t_id, itemid)
        }

    }

    public static void main(String args[]) {
        try {
            Database.connect();
            frame = new JFrame();
            
            frame.setTitle("Panda Express");
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    try {Database.disconnect();}
                    catch(SQLException f) {
                        f.printStackTrace();
                        System.err.println(f.getClass().getName()+": "+f.getMessage());
                        System.exit(0);
                    }
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
            });
            frame.add(new TransactionPane(), BorderLayout.EAST);
            frame.add(new checkoutButton(), BorderLayout.SOUTH);
            frame.add(new topPanel(), BorderLayout.NORTH);
            // frame.pack();
            frame.setVisible(true);

            int[] a = {};
            processTransaction(10,a);

        }
        catch(SQLException e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        
        


    }

}