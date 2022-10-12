import java.sql.*;

/**
 * @author Pranav
 * 
 * */
public class Database {
    
    public static Statement stmt;
    public static Connection conn;
    public static Boolean done = false;
    // @throws SQLException
    public static void connect() throws SQLException{
        String dbName = "csce315_914_32";
        String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
        conn = DriverManager.getConnection(dbConnectionString, "csce315_914_krishnan", "diversity");
        stmt = conn.createStatement(); 
        System.out.println("Opened database successfully");   
    }
    /**
     * @throws SQLException
     * */
    public static void disconnect() throws SQLException{
        conn.close();
        System.out.println("Disconnected");
    }
    /**
     * @throws SQLException
     * @return ResultSet
     * @params takes an sql query that is given as a string
     */
    
    public static ResultSet executeQuery(String sql) throws SQLException{
        return stmt.executeQuery(sql);
    }
    // @throws SQLException
    public static int executeUpdate(String sql) throws SQLException {
        return stmt.executeUpdate(sql);
    }


}

