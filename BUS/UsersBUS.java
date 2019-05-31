package BUS;

import java.sql.SQLException;
import DAL.UsersDAL;

public class UsersBUS {

	UsersDAL dal = new UsersDAL();
	
	public boolean getLogin(String userName, String password,String auth) throws SQLException {
		return dal.getLogin(userName, password,auth);
	}
}
