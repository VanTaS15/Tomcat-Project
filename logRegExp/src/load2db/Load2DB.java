package load2db;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Load2DB {

    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            String msg = "The com.mysql.jdbc.Driver is missing\n"
                    + "install and rerun the application";
            System.out.println(msg);
            System.exit(1);
        }
int i=1;
        // connect to db
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb?useUnicode=true&characterEncoding=UTF-8", "root", "zoot");
            System.out.println("Inserting records into the table...");
            stmt = con.createStatement();
            
            String sql = "INSERT INTO test VALUES ('"+i+"','100', 'Foo', 'Bar', '18')";
            i=i+1;
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            String msg = "Error Connecting to Database:\n" + e.getMessage();
            System.out.println(msg);
            try {
                if (stmt != null)
                    stmt.close();
                if (con != null) 
                    con.close();
            } catch (SQLException se) {
                System.out.println("SQLException: " + se.getMessage());
            }
        }
    }
}


