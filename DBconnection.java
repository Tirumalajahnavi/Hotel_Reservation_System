package hotel;
import java.sql.*;
public class DBconnection {
    static Connection connection() throws Exception
    {
        Class.forName("oracle.jdbc.OracleDriver");
        String url="jdbc:oracle:thin:@localhost:1521:XE";
        String username="system";
        String password="root";
        return DriverManager.getConnection(url,username,password);
    }
}