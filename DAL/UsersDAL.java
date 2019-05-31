package DAL;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDAL extends dbConnect {
	
	public boolean getLogin(String userName, String password, String auth) throws SQLException {
		String query = "select * from users where username = '" + userName +"' and passwords = '"+password+"' and auth ='"+auth+"'";
		ResultSet rs = executeQuery(query);
		if (rs != null && rs.next()) return true;
		
		return false;
	}
}
