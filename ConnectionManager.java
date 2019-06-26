import java.sql.*;
import javax.swing.JOptionPane;
public class ConnectionManager {
    private static String url = "jdbc:mysql://127.0.0.1:3306/library";    
    private static String driverName = "com.mysql.jdbc.Driver";   
    private static String username = "root";   
    private static String password = "";
    private static Connection con;

    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Failed to create the database connection."); 
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Driver not found.", "Error!", JOptionPane.ERROR_MESSAGE); 
        }
        return con;
    }
}