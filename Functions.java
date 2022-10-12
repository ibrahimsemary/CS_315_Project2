import java.sql.*;
import java.awt.*;
import java.util.ArrayList; // import the ArrayList class

import javax.print.DocFlavor.STRING;
import javax.swing.*;
import java.awt.event.*;

public class Functions{

        /**
     * @param cost              cost of the transaction
     * @param items             list of itemid for items purchased
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
     * @param itemId            itemid of item being added
     * @param expiryDate        date item will expire
     * @param Quantity          number of items in shipment
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
     * @param username          username of user trying to log in
     * @param password          password of user trying to log in
     * @return boolean          returns if successful login
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

    public static class Triplet<T1,T2,T3> {
        public T1 first;
        public T2 second;
        public T3 third;

        public Triplet(T1 t1, T2 t2, T3 t3){
            this.first = t1;
            this.second = t2;
            this.third = t3;
        }
    }

    /**
     * @return
     * @throws SQLException
     */
    public static ArrayList<Triplet<Integer, String, Double>>getItems() throws SQLException{
       
        ArrayList<Triplet<Integer, String, Double>> items = new ArrayList<Triplet<Integer, String, Double>>();
        ResultSet res = Database.executeQuery("SELECT * FROM items;");
        res.next();
        while(res.next()){
            Integer tempID= Integer.parseInt(res.getString("id"));
            String tempName = res.getString("name");
            Double tempCost = Double.parseDouble(res.getString("cost"));
            Triplet<Integer, String, Double> temp = new Triplet<Integer,String,Double>(tempID, tempName, tempCost);
            items.add(temp);
        }
        return items;
    }

    /**
     * 
     * @return
     * @throws SQLException
     */
    public static ArrayList<Triplet<Integer, String, Double>>getEntrees() throws SQLException{
       
        ArrayList<Triplet<Integer, String, Double>> items = new ArrayList<Triplet<Integer, String, Double>>();
        ResultSet res = Database.executeQuery("SELECT * FROM items where type = 'entree';");
        res.next();
        while(res.next()){
            Integer tempID= Integer.parseInt(res.getString("id"));
            String tempName = res.getString("name");
            Double tempCost = Double.parseDouble(res.getString("cost"));
            Triplet<Integer, String, Double> temp = new Triplet<Integer,String,Double>(tempID, tempName, tempCost);
            items.add(temp);
        }
        return items;
    }
    /**
     * 
     * @return
     * @throws SQLException
     */
    public static ArrayList<Triplet<Integer, String, Double>>getSides() throws SQLException{
       
        ArrayList<Triplet<Integer, String, Double>> items = new ArrayList<Triplet<Integer, String, Double>>();
        ResultSet res = Database.executeQuery("SELECT * FROM items where type = 'side';");
        res.next();
        while(res.next()){
            Integer tempID= Integer.parseInt(res.getString("id"));
            String tempName = res.getString("name");
            Double tempCost = Double.parseDouble(res.getString("cost"));
            Triplet<Integer, String, Double> temp = new Triplet<Integer,String,Double>(tempID, tempName, tempCost);
            items.add(temp);
        }
        return items;
    }
    

}