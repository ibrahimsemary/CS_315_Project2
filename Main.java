import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;
// Compile: javac *.java
// Run: java -cp ".;postgresql-42.2.8.jar" Main

public class Main {

    static JFrame serverFrame;
    static JFrame managerFrame;
    static CentralPanel centralPanel;
    static TransactionPane transactionPanel;
    static checkoutButton checkoutPanel;
    static TopPanel topPanel;
    static ItemPanel entreePanel;
    static ItemPanel sidesPanel;
    static ItemPanel extrasPanel;
    static ItemPanel combosPanel;
    static CardLayout cardlayout;
    static JPanel cards;
    static CostLabel cLabel;

    public static void main(String args[]) {
        try {
            Database.connect();
            makeServer();
            makeManager();
        }
        catch(SQLException e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }

    public static void makeServer() throws SQLException {
            serverFrame = new JFrame();
            cardlayout = new CardLayout();
            cards = new JPanel(cardlayout);
            serverFrame.setTitle("Panda Express");
            serverFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            serverFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            serverFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    try {Database.disconnect();}
                    catch(SQLException f) {
                        f.printStackTrace();
                        System.err.println(f.getClass().getName()+": "+f.getMessage());
                        System.exit(0);
                    }
                    serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
            });
            transactionPanel = new TransactionPane();
            centralPanel = new CentralPanel();
            checkoutPanel = new checkoutButton();
            topPanel = new TopPanel();
            entreePanel = new ItemPanel("entree");
            sidesPanel = new ItemPanel("side");
            extrasPanel = new ItemPanel("extra");
            combosPanel = new ItemPanel("combo");
            cLabel = new CostLabel(0); 
           // cards.add(transactionPanel, "transactionPanel");
            cards.add(centralPanel, "centralPanel");
            //cards.add(topPanel, "topPanel");
            cards.add(entreePanel, "entreePanel");
            cards.add(sidesPanel, "sidesPanel");
            cards.add(extrasPanel, "extrasPanel");
            cards.add(combosPanel, "combosPanel");

            serverFrame.add(transactionPanel, BorderLayout.EAST);
            serverFrame.add(checkoutPanel, BorderLayout.SOUTH);
            serverFrame.add(topPanel, BorderLayout.NORTH);
            //frame.add(entreePanel, BorderLayout.CENTER);
            // frame.add(sidesPanel, BorderLayout.CENTER);
            // frame.add(extrasPanel, BorderLayout.CENTER);
            //frame.add(centralPanel, BorderLayout.CENTER);

            serverFrame.add(cards, BorderLayout.CENTER);
            cardlayout.show(cards, "transactionPanel");
            serverFrame.setVisible(true);
        //     entreePanel.setVisible(false);
        //      try {TimeUnit.SECONDS.sleep(10);}
        //  catch(InterruptedException e) {System.out.println("sd");}
        //  centralPanel.setVisible(false);
        //  entreePanel.setVisible(true);
        //  entreePanel.revalidate();
        // c.setVisible(false);
            // frame.pack();
    }

    public static void makeManager() {
        managerFrame = new JFrame();
        
    }

}