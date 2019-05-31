package BUS;

import java.sql.SQLException;
import java.util.ArrayList;

import DAL.MenuAdminDAL;
import DTO.MenuAdmin;

public class MenuAdminBUS {
		MenuAdminDAL dal = new MenuAdminDAL();
	
		public ArrayList<DTO.MenuAdmin> getUsers() throws SQLException {
			return dal.getUsers();

		}
		 public boolean insertUser(String userName, String password,String auth) throws SQLException {

	        return dal.insertUser(userName,password,auth);

	    }
	
}
