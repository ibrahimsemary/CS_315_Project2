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

    public static class Item{
        public int id;
        public String name;
        public double cost;
        public String type;

        public Item(int id, String name, double cost){
            this.id = id;
            this.name = name;
            this.cost = cost;
        }

        public Item(int id, String name, double cost, String type){
            this.id = id;
            this.name = name;
            this.cost = cost;
            this.type = type;
        }
    }

    public static class InventoryItem{
        public int id;
        public String name;
        public int quantity;

        public InventoryItem(int id, String name, int quantity){
            this.id = id;
            this.name = name;
            this.quantity = quantity;
        }
    }

    /**
     * @return
     * @throws SQLException
     */
    public static ArrayList<Item>getItems() throws SQLException{
       
        ArrayList<Item> items = new ArrayList<Item>();
        ResultSet res = Database.executeQuery("SELECT id,name,cost,type FROM items;");
        res.next();
        while(res.next()){
            Integer tempID= Integer.parseInt(res.getString("id"));
            String tempName = res.getString("name");
            Double tempCost = Double.parseDouble(res.getString("cost"));
            String type = res.getString("cost");
            Item temp = new Item(tempID, tempName, tempCost, type);
            items.add(temp);
        }
        return items;
    }

    /**
     * 
     * @return
     * @throws SQLException
     */
    public static ArrayList<InventoryItem>getInventoryItems() throws SQLException{
        ArrayList<InventoryItem> items = new ArrayList<InventoryItem>();
        ResultSet res = Database.executeQuery("SELECT itemid, itemname, totalquantity FROM inventory;");
        res.next();
        while(res.next()){
            Integer tempID= Integer.parseInt(res.getString("itemid"));
            String tempName = res.getString("itemname");
            int tempQuantity = Integer.parseInt(res.getString("totalquantity"));
            InventoryItem temp = new InventoryItem(tempID, tempName, tempQuantity);
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

    public static double processCombo(String combo_name, ArrayList<String> items){
        
        return 2.0;
    }
    

}