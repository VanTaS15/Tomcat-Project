package logregexp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.regex.*;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class LogRegExp1 {

    public static void main(String argv[]) {
        FileReader myFile = null;
        BufferedReader buff = null;
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
       // String logEntryPattern = "^([\\d.]+) (\\S+) (\\S+) (\\S+) (\\S+) (\\S+) (\\S+) ([\\d]+) [a-zA-Z0-9_ ]*(\\S+) [-]?[ ]?\\[([\\w:/]+\\s[+\\-]\\d{4})\\] \\\"(.+?)\\\" (\\d{3}) (\\S+) (\\d)+ (\\S+) \"(.+?)\\\" \"(.+?)\\\"";
//String logEntryPattern="^([\\d.]+) (\\S+) (\\S+) (\\S+) (\\S+) (\\S+) (\\S+) ([\\d]+) [a-zA-Z0-9_ ]*(\\S+) [-]?[ ]?\\[([\\w:/]+\\s[+\\-]\\d{4})\\] \\\"(.+?)\\\" (\\d{3}) (\\S+) ([\\d]+) (\\S+) \"(.+?)\\\" \"(.+?)\\\"";
     //String logEntryPattern="^([\\d.]+|[\\d:]+) (\\S+) (\\S+) (\\S+) (\\S+) (\\S+) (\\S+) ([\\d]+) [a-zA-Z0-9_ ]*(\\S+) [-]?[ ]?\\[([\\w:/] +\\s[+\\-]\\d{4})\\] \\\"(.+?)\\\" (\\d{3}) (\\S+) ([\\d]+) (\\S+) \"(.+?)\\\" \"(.+?)\\\" ";
     
String logEntryPattern="^([\\d.]+|[\\d:]+) (\\S+) (\\S+) (\\S+) (\\S+) (\\S+) (\\S+) ([\\d]+) [a-zA-Z0-9_ ]*(\\S+) [-]?[ ]?\\[([\\w:/]+\\s[+\\-]\\d{4})\\] \\\"(.+?)\\\" (\\d{3}) (\\S+) (\\d)+ (\\S+) \"(.+?)\\\" \"(.+?)\\\"";  
     

     System.out.println("Using RE Pattern:");
        System.out.println(logEntryPattern);

        Pattern p = Pattern.compile(logEntryPattern);

        try {
            myFile = new FileReader("e3775_access_log2016-05-24.log");
            buff = new BufferedReader(myFile);

            while (true) {
                String line = buff.readLine();
                if (line == null) {
                    break;
                }

                Matcher matcher = p.matcher(line);
                System.out.println("groups: " + matcher.groupCount());
                if (!matcher.matches()) {
                    System.err.println(line + matcher.toString());
                    return;
                }

                System.out.println("%a Remote IP Address     : " + matcher.group(1));
                System.out.println("%A Local IP Address      : " + matcher.group(2));
                System.out.println("%b Bytes sent            : " + matcher.group(3));
                System.out.println("%B Bytes sent            : " + matcher.group(4));
                System.out.println("%h Remote host name or IP: " + matcher.group(5));
                System.out.println("%H Request Protocol      : " + matcher.group(7));
                System.out.println("%I Rem logic username  - : " + matcher.group(6));
                System.out.println("%m Requ Method           : " + matcher.group(7));
                System.out.println("%p Local Port            : " + matcher.group(8));
                System.out.println("%q Query String          : " + matcher.group(9));
                System.out.println("%r First Requ Line       : " + matcher.group(11));
                System.out.println("%s Status                : " + matcher.group(12));
                System.out.println("%t Date and Time         : " + matcher.group(10));                

                System.out.println("%r First Line or Requ    : " + matcher.group(11));

                System.out.println("%U requested URL         : " + matcher.group(13));
                System.out.println("%D time to process (ms)  : " + matcher.group(14));
                System.out.println("%S Session ID            : " + matcher.group(15));
                System.out.println("Agent                    : " + matcher.group(16));
                System.out.println("Referrer                 : " + matcher.group(17));                     
                
                try {
                    
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb?useUnicode=true&characterEncoding=UTF-8", "root", "zoot");
            System.out.println("Inserting records into the table...");
            stmt = con.createStatement();

            //String sql = "INSERT INTO logfile VALUES (0,'matcher.group(1)', 'matcher.group(2)', 'matcher.group(3)',' matcher.group(4)','matcher.group(5)','matcher.group(6)','matcher.group(7)','matcher.group(8)','matcher.group(9)','matcher.group(10)','matcher.group(11)','matcher.group(12)','matcher.group(13)','matcher.group(14)','matcher.group(15)','matcher.group(16)','matcher.group(17)')";
            String sql = "INSERT INTO logfile VALUES (0,'"+matcher.group(1)+"','"+matcher.group(2)+"', '"+matcher.group(3)+"','"+matcher.group(4)+"','"+matcher.group(5)+"','"+matcher.group(6)+"','"+matcher.group(7)+"','"+matcher.group(8)+"','"+matcher.group(9)+"','"+matcher.group(10)+"','"+matcher.group(11)+"','"+matcher.group(12)+"','"+matcher.group(13)+"','"+matcher.group(14)+"','"+matcher.group(15)+"','"+matcher.group(16)+"','"+matcher.group(17)+"')";
           
//String sql = "INSERT INTO test VALUES (0,'"+matcher.group(1)+"', '"+matcher.group(2)+"', '"+matcher.group(3)+"','"+matcher.group(4)+"','"+matcher.group(5)+"','"+matcher.group(6)+"','"+matcher.group(7)+"')";
           System.out.println(matcher.group(11));
           
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
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                buff.close();
                myFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
