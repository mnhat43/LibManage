package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public interface DatabaseInfo{
    String url = "jdbc:postgresql://localhost:5432/LibraryManage";
    String user = "postgres";
    String password = "nhatdcbn2003";
}
