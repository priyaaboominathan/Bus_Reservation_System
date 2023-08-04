package busres;
import java.sql.*;


public class DBcon {
    public static Connection getCon() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/BusRes";    //local host-name, 3306 -id, database name = jdbc
        String username = "root";
        String password = "Success";
        Connection con = DriverManager.getConnection(url,username,password);
        return con;
    }
}
