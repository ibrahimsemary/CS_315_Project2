import java.sql.*;


public class Database {
    
    public static Statement stmt;
    public static Connection conn;
    public static Boolean done = false;
    public static void connect() throws SQLException{
        String dbName = "csce315_914_32";
        String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
        conn = DriverManager.getConnection(dbConnectionString, "csce315_914_krishnan", "diversity");
        stmt = conn.createStatement(); 
        System.out.println("Opened database successfully");   
    }

    public static void disconnect() throws SQLException{
        conn.close();
        System.out.println("Disconnected");
    }

    public static ResultSet executeQuery(String sql) throws SQLException{
        return stmt.executeQuery(sql);
    }
    public static int executeUpdate(String sql) throws SQLException {
        return stmt.executeUpdate(sql);
    }


}

