import java.sql.*;
import java.awt.*;
im
import javax.print.DocFlavor.STRING;
import javax.swing.*;
import java.awt.event.*;

public class Main{

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

    static void processTransaction(double cost, int[] items) throws SQLException{
        
        ResultSet res = Database.executeQuery("SELECT MAX(transactionid) FROM test_transaction;");
        res.next();
        int t_id = Integer.parseInt(res.getString("max")) +1;
        Database.executeUpdate("INSERT INTO test_transaction VALUES (" + t_id +", CURRENT_DATE, " + cost + ");");
        for(int itemid : items){
            Database.executeUpdate("INSERT INTO transactionitems_test VALUES ("+t_id+", "+itemid+");");
        }

    }

    /**
     * 
     * @param itemid
     * @param newCost
     */
    public static void updateItem(int itemid, double newCost) throws SQLException{
        Database.executeUpdate("UPDATE items SET cost = "+newCost+" WHERE itemid = "+itemid +";");
    }

    /**
     * 
     * @param trip
     * @param newCost
     * @throws SQLException
     */
    public static void updateItem(Triplet<Integer,String,Double> trip, double newCost) throws SQLException{
        Database.executeUpdate("UPDATE items SET cost = "+newCost+" WHERE itemid = "+trip.first +";");
    }

    public static void addItem(String name, String type, double cost) throws SQLException{
        ResultSet res = Database.executeQuery("SELECT MAX(id) FROM items;");
        res.next();
        int id = Integer.parseInt(res.getString("max")) +1;
        Database.executeUpdate("INSERT INTO items VALUES ("+id+", "+name+", "+type+", "+cost+", 0);");
    }


    //expiry date should probably be changed to the date orderde or something
    static void processBatches(int itemId, String expiryDate, int Quantity)throws SQLException{
        //get the max batch id and add one to it - this is going to be the new primary key
        //then do the query to put it in the DB
        ResultSet res = Database.executeQuery("SELECT MAX(batchid) FROM batch;");
        res.next();
        int batchId = Integer.parseInt(res.getString("max")) + 1;
        //Database.executeUpdate("INSERT INTO batch VALUES(" + batchId + "," + itemId + "," + expiryDate + "," + Quantity + ");");
    }

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
            System.out.println(login("miketyson", "password"));
            System.out.println(login("ibrahim", "haram"));
            Database.disconnect();
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }


}