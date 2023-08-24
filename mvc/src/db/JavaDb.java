package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class JavaDb {
    static Connection conn;
    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost/java";
    static String uname="root";
    static String pass = "";
    
    public static Connection getConnection() throws Exception{
        if(conn==null){
            conn = DriverManager.getConnection(url, uname, pass);
        }
        return conn;
    }
}
