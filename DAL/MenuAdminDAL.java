package DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;
import java.util.*;
import DTO.*;

public class MenuAdminDAL extends dbConnect{
	public ArrayList<MenuAdmin> getUsers() throws SQLException {
		String query = "select * from Users";
		ResultSet rs = executeQuery(query);
		ArrayList<MenuAdmin> AdminList = new ArrayList<>();
		if (rs != null) {
			while (rs.next()) {
				MenuAdmin admin = new MenuAdmin();
				admin.setUserid(rs.getInt("userid"));
				admin.setUsername(rs.getString("username"));
				admin.setPasswords(rs.getString("passwords"));
				admin.setAuth(rs.getString("auth"));
				AdminList.add(admin);
			}
		}
		dbClose();
		return AdminList;
	}
	public boolean insertUser(String userName, String password, String auth) throws SQLException {

        String users = "insert into users values (?, ?, ?)";
        dbOpen();
        PreparedStatement psData = conn.prepareStatement(users);
        psData.setString(1, userName);
        psData.setString(2, password);
        psData.setString(3, auth);
        int result = psData.executeUpdate();
        dbClose();
        return result > 0;

    }

}
