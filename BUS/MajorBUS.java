package BUS;

import java.sql.SQLException;
import java.util.ArrayList;

import DAL.MajorDAL;
import DTO.Major;

public class MajorBUS {

	MajorDAL dal = new MajorDAL();
	
	public ArrayList<Major> getMajor() throws SQLException {
		return dal.getMajor();
	}
	public boolean insertMajor(String majorid,String majorname) throws SQLException {

        return dal.insertMajor(majorid,majorname);

    }
	public boolean updateMajor(String majorid,String majorname) throws SQLException {
    
            return dal.updateMajor(majorid, majorname);

    }
}
