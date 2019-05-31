package DAL;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import com.microsoft.sqlserver.jdbc.*;

public class dbConnect {
	String dbUrl = "jdbc:sqlserver://localhost;databaseName=SQLDBUI;user=sa;password=123456";
    protected Connection conn;
    
    public void dbOpen() throws SQLException {
    	conn = DriverManager.getConnection(dbUrl);
    }
    
    public void dbClose() throws SQLException {
    	conn.close();
    }
    
    public ResultSet executeQuery(String query) throws SQLException {
    	ResultSet rs = null;
    	dbOpen();
    	PreparedStatement ps = conn.prepareStatement(query);
    	rs = ps.executeQuery();
    	//dbClose();
    	return rs;
    }
}
