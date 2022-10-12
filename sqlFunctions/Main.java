import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Main{

    static void processTransaction(double cost, int[] items) throws SQLException{
        
        ResultSet res = Database.executeQuery("SELECT MAX(transactionid) FROM transactions;");
        res.next();
        int t_id = Integer.parseInt(res.getString("max")) +1;
        Database.executeUpdate("INSERT INTO test_transaction VALUES (" + t_id +", CURRENT_DATE, " + cost + ");");
        for(int itemid : items){
            // INSERT INTO transactionsitems (t_id, itemid)
        }

    }

    public static void main(String[] args){

        int[] a = {};
        try{
            Database.connect();
            processTransaction(10, a);
            Database.disconnect();
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }


}