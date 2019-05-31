package BUS;

import java.sql.SQLException;
import java.util.ArrayList;

import DAL.MajorDAL;
import DAL.SubjectDAL;
import DTO.Major;
import DTO.Subject;

public class SubjectBUS {
	SubjectDAL dal = new SubjectDAL();
	
	public ArrayList<Subject> getSubject() throws SQLException {
		return dal.getSubject();
	}
	public boolean insertSubject(String subjectid,String subjectname,String credit) throws SQLException {

        return dal.insertSubject(subjectid, subjectname, credit);

    }
	public boolean updateSubject(String subjectid,String subjectname,String credit,String id) throws SQLException {

        return dal.updateSubject(subjectid, subjectname, credit,id);

    }
	public boolean removeSubject(String id) throws SQLException {

        return dal.removeSubject(id);

    }

}
