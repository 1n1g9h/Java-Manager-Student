package DAL;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.*;

public class SubjectDAL extends dbConnect {
	public ArrayList<Subject> getSubject() throws SQLException {
		String query = "select * from Subjects";
		ResultSet rs = executeQuery(query);
		ArrayList<Subject> subjectList = new ArrayList<>();
		if (rs != null) {
			while (rs.next()) {
				Subject sj = new Subject();
				sj.setSubjectid(rs.getString("subjectid"));
				sj.setSubjectname(rs.getString("subjectname"));
				sj.setCredit(rs.getString("credit"));
				subjectList.add(sj);
			}
		}
		dbClose();
		return subjectList;
	}
		public boolean insertSubject(String subjectid, String subjectname, String credit) throws SQLException {

	        String major = "insert into Subjects values (?, ?,?)";
	        dbOpen();
	        PreparedStatement psData = conn.prepareStatement(major);
	        psData.setString(1, subjectid);
	        psData.setString(2, subjectname);
	        psData.setString(3, credit);
	        int result = psData.executeUpdate();
	        dbClose();
	        return result > 0;

	    }
		public boolean updateSubject(String subjectid, String subjectname, String credit,String id) throws SQLException {

	        String updateMajor = "update Subjects set subjectid = ? ,subjectname = ?,credit = ? where subjectid = '"+id+"'";

	        dbOpen();

	        PreparedStatement psData = conn.prepareStatement(updateMajor);
	        psData.setString(1, subjectid);
	        psData.setString(2, subjectname);
	        psData.setString(3,credit);
	        //psData.setString(3, majorid);
	        int result = psData.executeUpdate();
	        dbClose();
	        return result > 0;

	    }
		public boolean removeSubject(String id) throws SQLException {

	        String removeSubject = "delete from Subjects where subjectid = '"+id+"'" ;

	        return executeUpdate(removeSubject);

	    }
		private boolean executeUpdate(String removeSubject) {
			// TODO Auto-generated method stub
			return false;
		}
		
}

