import java.sql.*;
import java.awt.*;

import javax.print.DocFlavor.STRING;
import javax.swing.*;
import java.awt.event.*;


public class Main{
    /**
     * @param cost
     * @param items
     * @throws SQLException
     */
    static void processTransaction(double cost, int[] items) throws SQLException{
        
        ResultSet res = Database.executeQuery("SELECT MAX(transactionid) FROM test_transaction;");
        res.next();
        int t_id = Integer.parseInt(res.getString("max")) +1;
        Database.executeUpdate("INSERT INTO test_transaction VALUES (" + t_id +", CURRENT_DATE, " + cost + ");");
        for(int itemid : items){
            //Database.executeUpdate("INSERT INTO transactionitems_test VALUES ("+t_id+", "+itemid+");");
        }

    }

    /**
     * @param itemId
     * @param expiryDate
     * @param Quantity
     * @throws SQLException
     */
    //expiry date should probably be changed to the date orderde or something
    static void processBatches(int itemId, String expiryDate, int Quantity)throws SQLException{
        //get the max batch id and add one to it - this is going to be the new primary key
        //then do the query to put it in the DB
        ResultSet res = Database.executeQuery("SELECT MAX(batchid) FROM batch;");
        res.next();
        int batchId = Integer.parseInt(res.getString("max")) + 1;
        //Database.executeUpdate("INSERT INTO batch VALUES(" + batchId + "," + itemId + "," + expiryDate + "," + Quantity + ");");
    }
    /**
     * @param username
     * @param password
     * @return boolean
     * @throws SQLException
     */
    static boolean login(String username, String password) throws SQLException{
        ResultSet res = Database.executeQuery("SELECT * FROM USERS;");
        res.next();
        while(res.next()){
            String tempName = res.getString("name");
            String tempPassword = res.getString("password");
            String tempRole = res.getString("Role");
            if(tempName.equals(username) && password.equals(tempPassword)&& tempRole.equals("manager")){
                return true;
                
            }
            res.next();
        }
        return false;
    }


    public static void main(String[] args){

        int[] a = {1,2};
        try{
            Database.connect();
            // password test
            // System.out.println(login("miketyson", "password"));
            // System.out.println(login("ibrahim", "haram"));


            Database.disconnect();
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }


}