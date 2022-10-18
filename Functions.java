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
    static void processTransaction(double cost, ArrayList<Item> items) throws SQLException{
        
        ResultSet res = Database.executeQuery("SELECT MAX(transactionid) FROM transactions;");
        res.next();
        int t_id = Integer.parseInt(res.getString("max")) +1;
        Database.executeUpdate("INSERT INTO transactions VALUES (" + t_id +", CURRENT_DATE, " + cost + ");");
        ResultSet res1 = Database.executeQuery("SELECT MAX(indexid) FROM transactionitems;");
        res1.next();
        int i_id = Integer.parseInt(res1.getString("max")) +1;

        for(Item item : items){
            Database.executeUpdate("INSERT INTO transactionitems VALUES ("+i_id+", "+t_id+", "+item.id+");");
            i_id ++;
        }

        decrementInventory(t_id);

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

    public static class Transaction{
        public int id;
        public String date;
        public double cost;

        public Transaction(int id, String date, double cost){
            this.id = id;
            this.date = date;
            this.cost = cost;
        }
    }

    /**
     * @return
     * @throws SQLException
     */
    public static ArrayList<Item>getItems() throws SQLException{
       
        ArrayList<Item> items = new ArrayList<Item>();
        ResultSet res = Database.executeQuery("SELECT id,name,cost,type FROM items;");
        while(res.next()){
            Integer tempID= Integer.parseInt(res.getString("id"));
            String tempName = res.getString("name");
            Double tempCost = Double.parseDouble(res.getString("cost"));
            String type = res.getString("type");
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
     * @param name
     * @param type
     * @param cost
     * @throws SQLException
     */
    public static void addItem(String name, String type, double cost) throws SQLException{
        ResultSet res = Database.executeQuery("SELECT MAX(id) FROM items;");
        res.next();
        int id = Integer.parseInt(res.getString("max")) +1;
        Database.executeUpdate("INSERT INTO items VALUES ("+id+", '"+name+"', '"+type+"', "+cost+", 0);");
    }
    /*
    public static void addItem(String name, String type, double cost, ArrayList<String> items) throws SQLException{
        addItem(name, type, cost);
        ResultSet res;
        for(String item : items){
            res = Database.executeQuery("SELECT * FROM inventory WHERE itemname = '" + item + "';");
            if(res.next()){
                int id = Integer.parseInt(res.getString("itemid"));
                // "INSERT INTO ingredientslist VALUES ("
            } else {
                ;
            }
        }
    }*/

    /**
     * 
     * @param name
     * @param newCost
     * @throws SQLException
     */

    public static void updateItem(String name, double newCost) throws SQLException{
        Database.executeUpdate("UPDATE items SET cost = "+newCost+" WHERE name = '"+name +"';");
    }

    public static void addBatch(String name, int amt, String expDate) throws SQLException{
        ResultSet res = Database.executeQuery("SELECT MAX(batchid) FROM batch;");
        res.next();
        int batchid = Integer.parseInt(res.getString("max")) +1;
        ResultSet res1 = Database.executeQuery("SELECT itemid FROM inventory WHERE itemname = '"+name+"';");
        res1.next();
        int itemid = Integer.parseInt(res1.getString("itemid"));
        Database.executeUpdate("INSERT INTO batch VALUES ("+batchid+", '"+expDate+"', "+amt+", "+itemid+");");
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
    
    public static double processCombo(String combo_name, ArrayList<Item> items, double totalCost){
        int entrees = 0;
        int sides = 0;
        //get num of everything
        for(Item item: items){
            if(item.type.equals("entree")){
                entrees += 1;
            }
            else if(item.type.equals("side")){
                sides += 1;
            }
            else if(item.name.equals("bowl")){
                entrees -= 1;
                sides -=1 ;
            }
            else if(item.name.equals("plate")){
                entrees -= 2;
                sides -=1 ;
            }
            else if(item.name.equals("bigger plate")){
                entrees -= 3;
                sides -=1 ;
            }
        }
        //get cost of all entrees and one side subtract it + then add cost of bowl + add to bowl
        if(combo_name.equals("bowl")){
            if(entrees >= 1 && sides >= 1){
                totalCost += 7.50;
                totalCost -= comboHelper(1, 1, items);

            }
            else{
                return -1;
            }
        }
        else if(combo_name.equals("plate")){
            if(entrees >= 2 && sides >= 1){
                //do stuff
                totalCost += 9.00;
                totalCost -= comboHelper(2, 1, items);
            }
            else{
                return -1;
            }
        }
        else if(combo_name.equals("bigger plate")){
            if(entrees >= 3 && sides >= 1){
                //do stuff
                totalCost += 10.50;
                totalCost -= comboHelper(3, 1, items);

            }
            else{
                return -1;
            }
        }
        return totalCost;
    }

    public static double comboHelper(int num_entrees, int num_sides, ArrayList<Item> items){
        //get num of entrees
        double to_return = 0;
        for(Item item: items){
            if(item.type.equals("entree") && num_entrees > 0){
                to_return += item.cost;
                num_entrees--;
            }
            else if(item.type.equals("side") && num_sides > 0){
                to_return += item.cost;
                num_sides--;
            }
            if(num_sides == 0 && num_entrees == 0){
                break;
            }
        }
        return to_return;
    }

    public static void decrementInventory(int transactionid) throws SQLException{
        //Database.executeUpdate("DROP VIEW view1 CASCADE;");
        Database.executeUpdate("CREATE OR REPLACE VIEW view1 AS SELECT transactionid, id, name FROM transactionitems NATURAL JOIN items WHERE transactionid ="+transactionid+";");
        ResultSet res = Database.executeQuery("select itemid from items NATURAL JOIN ingredientslist NATURAL JOIN inventory NATURAL JOIN view1;");
        String id;
        ArrayList<String> updates = new ArrayList<>();
        while(res.next()){
            id = res.getString("itemid");
            updates.add("UPDATE inventory SET totalquantity = totalquantity -1 WHERE itemid ="+id+";");
            //Database.executeUpdate("UPDATE inventory SET totalquantity = totalquantity -1 WHERE itemid ="+id+";");
        }
        for( String str : updates){
            Database.executeUpdate(str);
        }
    }
    
    public static ArrayList<Transaction> getTransactions(String startDate, String endDate) throws SQLException{
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        String q = "SELECT * FROM transactions WHERE '"+ startDate +"' <= transactiondate AND transactiondate <= '"+ endDate +"' ORDER BY transactiondate DESC;";
        ResultSet res = Database.executeQuery(q);
        //System.out.println(q);      
        while(res.next()){
            Transaction t = new Transaction(Integer.parseInt(res.getString("transactionid")), 
                                            res.getString("transactiondate"), 
                                            Double.parseDouble(res.getString("totalcost")));
            transactions.add(t);
        }      

        return transactions;
    }

    public static ArrayList<ArrayList<String>> getTopItems(String startDate, String endDate) throws SQLException{
        ArrayList<ArrayList<String>> items = new ArrayList<ArrayList<String>>();
        String q1 = "CREATE OR REPLACE VIEW view11 AS SELECT indexid, transactionitems.transactionid, id FROM transactionitems LEFT JOIN transactions on transactionitems.transactionid = transactions.transactionid WHERE transactiondate >= '"+startDate+"' AND transactiondate <= '"+endDate+"';";
        String q2 = "SELECT name, count(*) amountOrdered from view11 NATURAL JOIN items GROUP BY (id, name) ORDER BY amountOrdered DESC;";
        Database.executeUpdate(q1);
        ResultSet res = Database.executeQuery(q2);
        while(res.next()){
            ArrayList<String> temp = new ArrayList<String>();
            temp.add(res.getString("name"));
            temp.add(res.getString("amountOrdered"));
            items.add(temp);
        }

        return items;
    }

    public static ArrayList<InventoryItem> underStockedIngredients() throws SQLException{
        ArrayList<InventoryItem> to_return = new ArrayList<>();
        ResultSet res = Database.executeQuery("SELECT * FROM INVENTORY WHERE INVENTORY.TOTALQUANTITY < INVENTORY.MINIMUMAMOUNT;");
        while(res.next()){
            Integer quantity = Integer.parseInt(res.getString("totalquantity"));
            Integer id = Integer.parseInt(res.getString("itemid"));
            String name = res.getString("itemname");
            to_return.add(new InventoryItem(id, name, quantity));
        }
        return to_return;
    }
}
