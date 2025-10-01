package Database_Banking;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class bankingDatabase {
    private static final String url = "jdbc:mysql://localhost:3306/Section17_004?createDatabaseIfNotExist=true&useSSL=false";
    private static final String user = "root";
    private static final String password = "123456$";

    public static Connection getConnection() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}