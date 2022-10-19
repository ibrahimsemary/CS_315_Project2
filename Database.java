import java.sql.*;

/**
 * @category SQL handler
 * {@summary Handler to connect/disconnect/execute database commands}
 */
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

    /**
     * {@summary Closes the database connection}
     * @throws SQLException
     */
    public static void disconnect() throws SQLException{
        conn.close();
        System.out.println("Disconnected");
    }
    /**
     * @summary Executes a sql command that returns output
     * @param sql Command to run
     * @return Returns a resultset with the query results
     * @throws SQLException
     */
    public static ResultSet executeQuery(String sql) throws SQLException{
        return stmt.executeQuery(sql);
    }
    /**
     * @summary Executes a sql command that does not return output
     * @param sql Command to run
     * 
     * @throws SQLException
     */
    public static int executeUpdate(String sql) throws SQLException {
        return stmt.executeUpdate(sql);
    }


}

