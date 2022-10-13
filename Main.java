import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;
// Compile: javac *.java
// Run: java -cp ".;postgresql-42.2.8.jar" Main

public class Main {

    static JFrame frame;
    static CentralPanel centralPanel;
    static TransactionPane transactionPanel;
    static checkoutButton checkoutPanel;
    static TopPanel topPanel;
    static ItemPanel entreePanel;
    static ItemPanel sidesPanel;
    static ItemPanel extrasPanel;
    static CardLayout cardlayout;
    static JPanel cards;
    static CostLabel cLabel;

    public static void main(String args[]) {
        makeServer();
    }

    public static void makeServer() {
        try {
            Database.connect();
            frame = new JFrame();
            cardlayout = new CardLayout();
            cards = new JPanel(cardlayout);
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
            transactionPanel = new TransactionPane();
            centralPanel = new CentralPanel();
            checkoutPanel = new checkoutButton();
            topPanel = new TopPanel();
            entreePanel = new ItemPanel("entree");
            sidesPanel = new ItemPanel("side");
            extrasPanel = new ItemPanel("extra");
            cLabel = new CostLabel(0); 
           // cards.add(transactionPanel, "transactionPanel");
            cards.add(centralPanel, "centralPanel");
            //cards.add(topPanel, "topPanel");
            cards.add(entreePanel, "entreePanel");
            cards.add(sidesPanel, "sidesPanel");
            cards.add(extrasPanel, "extrasPanel");

            frame.add(transactionPanel, BorderLayout.EAST);
            frame.add(checkoutPanel, BorderLayout.SOUTH);
            frame.add(topPanel, BorderLayout.NORTH);
            //frame.add(entreePanel, BorderLayout.CENTER);
            // frame.add(sidesPanel, BorderLayout.CENTER);
            // frame.add(extrasPanel, BorderLayout.CENTER);
            //frame.add(centralPanel, BorderLayout.CENTER);

            frame.add(cards, BorderLayout.CENTER);
            cardlayout.show(cards, "transactionPanel");
            frame.setVisible(true);
        //     entreePanel.setVisible(false);
        //      try {TimeUnit.SECONDS.sleep(10);}
        //  catch(InterruptedException e) {System.out.println("sd");}
        //  centralPanel.setVisible(false);
        //  entreePanel.setVisible(true);
        //  entreePanel.revalidate();
        // c.setVisible(false);
            // frame.pack();

            


        }
        catch(SQLException e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        
        


    }

}