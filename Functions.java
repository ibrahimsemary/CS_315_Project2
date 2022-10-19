import java.sql.*;
import java.awt.*;
import java.util.ArrayList; // import the ArrayList class
import java.util.Map;
import java.util.HashMap;
import javax.print.DocFlavor.STRING;
import javax.swing.*;
import java.awt.event.*;

public class Functions{

    public static class excessIngredient{
        public Integer id;
        public String name;
        public Integer totalAmount;
        public Integer amountSold;

        public excessIngredient(Integer t1, String t2, Integer t3, Integer t4){
            this.id = t1;
            this.name = t2;
            this.totalAmount= t3;
            this.amountSold = t4;
        }
    }

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
    public static int addItem(String name, String type, double cost) throws SQLException{
        ResultSet res = Database.executeQuery("SELECT MAX(id) FROM items;");
        res.next();
        int id = Integer.parseInt(res.getString("max")) +1;
        Database.executeUpdate("INSERT INTO items VALUES ("+id+", '"+name+"', '"+type+"', "+cost+", 0);");
        return id;
    }
    
    public static void addItem(String name, String type, double cost, ArrayList<String> items) throws SQLException{
        int id;
        id = addItem(name, type, cost);
        ResultSet res1 = Database.executeQuery("SELECT MAX(indexid) from ingredientslist;");
        res1.next();
        int indexid = Integer.parseInt(res1.getString("max")) +1;
        ResultSet res;
        int i=0;
        while(i < items.size()){
            res = Database.executeQuery("SELECT * FROM inventory WHERE itemname = '" + items.get(i) + "';");
            if(res.next()){
                // System.out.println(items.get(i) +" in inventory");
                int itemid = Integer.parseInt(res.getString("itemid"));
                //System.out.println("INSERT INTO ingredientslist VALUES ("+indexid+", "+ id +", " + itemid +");");
                Database.executeUpdate("INSERT INTO ingredientslist VALUES ("+indexid+" ,"+ id +", " + itemid +");");
                indexid++;
                i++;
            } else {
                // System.out.println("Item not in inventory");
                res1 = Database.executeQuery("SELECT MAX(itemid) FROM inventory;");
                res1.next();
                int newitemid = Integer.parseInt(res1.getString("max")) +1;
                // System.out.println("INSERT INTO inventory VALUES ("+newitemid+", '"+ items.get(i) +"', 0 , 100);");
                Database.executeUpdate("INSERT INTO inventory VALUES ("+newitemid+", '"+ items.get(i) +"',0 , 100);");
            }
        }
    }

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

    public static ArrayList<ArrayList<String>> getTopItems(String fromDate) throws SQLException{
        ArrayList<ArrayList<String>> items = new ArrayList<ArrayList<String>>();
        String q1 = "CREATE OR REPLACE VIEW view11 AS SELECT indexid, transactionitems.transactionid, id FROM transactionitems LEFT JOIN transactions on transactionitems.transactionid = transactions.transactionid WHERE transactiondate >= '"+fromDate+"';";
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

    static ArrayList<excessIngredient> excessReport(String date) throws SQLException{
        String q1 = "CREATE OR REPLACE VIEW view50 AS SELECT indexid, transactionitems.transactionid, id FROM transactionitems LEFT JOIN transactions on transactionitems.transactionid = transactions.transactionid WHERE transactiondate >= '"+date+"';";
        String q2 = "SELECT id, name, count(*) amountOrdered from view50 NATURAL JOIN items GROUP BY (id, name) ORDER BY amountOrdered DESC;";
        Database.executeUpdate(q1);
        ResultSet res = Database.executeQuery(q2);

        ArrayList<String> names = new ArrayList<>();
        ArrayList<Integer> amounts = new ArrayList<>();

        ArrayList<excessIngredient> to_return = new ArrayList<>();


        while(res.next()){
            String name = res.getString("name");
            names.add(name);
            int amountOrdered = Integer.parseInt(res.getString("amountordered"));
            amounts.add(amountOrdered);
        }
        Map<String, Integer> myMap = new HashMap<>();
        for(int i = 0; i < names.size(); i++){
            res = Database.executeQuery("SELECT itemname FROM items NATURAL JOIN ingredientslist NATURAL JOIN inventory WHERE name = '" + names.get(i) + "';");
            while(res.next()){
                String ingredientName = res.getString("itemname");
                if(myMap.containsKey(ingredientName)){
                    myMap.put(ingredientName, myMap.get(ingredientName)+amounts.get(i));
                }
                else{
                    myMap.put(ingredientName, amounts.get(i));
                }
            }
        }

        for(String name: myMap.keySet()){
            res = Database.executeQuery("SELECT * FROM INVENTORY WHERE ITEMNAME = '" + name + "'");
            res.next();
            Integer quantity = Integer.parseInt(res.getString("totalQuantity"));
            Integer tempId = Integer.parseInt(res.getString("itemid"));

            Integer temp = myMap.get(name);
            int total = quantity + temp;
            if(total/ temp > 10){
                to_return.add(new excessIngredient(tempId, name, total , temp));
            }
        }
        return to_return;
    }

    public static class pairCount implements Comparable<pairCount>{

        int first;
        int second;
        int count;

        public pairCount(int first, int second, int count){
            this.first = first;
            this.second = second;
            this.count = count;
        }

        @Override
        public int compareTo(pairCount p2){
            
            if(this.count < p2.count)
                return 1;
            return -1;
        }
    }

    public static ArrayList<ArrayList<String>> getPairs(String startDate, String endDate) throws SQLException{

        ArrayList<ArrayList<String>> pairs = new ArrayList<ArrayList<String>>();
        Database.executeUpdate("CREATE OR REPLACE VIEW view44 AS select transactionid, indexid, id from transactionitems NATURAL JOIN transactions WHERE '"+startDate +"' <= transactiondate and transactiondate <= '" + endDate +"';");
        ResultSet res1 = Database.executeQuery("SELECT MAX(id) FROM items;");
        res1.next();
        int maxid = Integer.parseInt(res1.getString("max")) +1;

        int[][] counts = new int[maxid][maxid];
        
        ResultSet res = Database.executeQuery("SELECT a.id id1, b.id id2 FROM view44 a JOIN view44 b on a.transactionid = b.transactionid WHERE a.id != b.id;");
        while(res.next()){
            int id1 = Integer.parseInt(res.getString("id1")) / 2;
            int id2 = Integer.parseInt(res.getString("id2")) / 2;
            if(id1 > id2){
                counts[id2][id1]++;
            } else {
                counts[id1][id2]++;
            }
        }

        ArrayList<pairCount> counts1 = new ArrayList<>();
        for(int i=0; i<maxid; i++){
            for(int j=0; j<maxid; j++){
                if(counts[i][j] > 0){
                    counts1.add(new pairCount(i,j, counts[i][j]));
                }
            }
        }
        
        counts1.sort( ((o1, o2) -> o1.compareTo(o2)) );
        
        String[] names = new String[100];
        for(int i=0; i<counts1.size(); i++){
            ArrayList<String> temp = new ArrayList<>();
            String name;
            if(names[counts1.get(i).first] == null){
                ResultSet res2 = Database.executeQuery("SELECT name FROM items WHERE id = " + counts1.get(i).first);
                res2.next();
                name = res2.getString("name");
                names[counts1.get(i).first] = name;
                temp.add(name);
            } else {
                temp.add(names[counts1.get(i).first]);
            }
            if(names[counts1.get(i).second] == null){
                ResultSet res2 = Database.executeQuery("SELECT name FROM items WHERE id = " + counts1.get(i).second);
                res2.next();
                name = res2.getString("name");
                names[counts1.get(i).second] = name;
                temp.add(name);
            } else {
                temp.add(names[counts1.get(i).second]);
            }
            temp.add(""+counts1.get(i).count);
            //System.out.println(temp.get(0) + " " + temp.get(1) + " "+ temp.get(2));
            pairs.add(temp);
        }

        return pairs;
    }
}
