package DAL;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.*;

public class MajorDAL extends dbConnect {

	public ArrayList<Major> getMajor() throws SQLException {
		String query = "select * from Major";
		ResultSet rs = executeQuery(query);
		ArrayList<Major> majorList = new ArrayList<>();
		if (rs != null) {
			while (rs.next()) {
				Major mj = new Major();
				mj.setMajorid(rs.getString("majorid"));
				mj.setMajorname(rs.getString("majorname"));
				majorList.add(mj);
			}
		}
		dbClose();
		return majorList;
	}
	public boolean insertMajor(String majorid, String majorname) throws SQLException {

        String major = "insert into Major values (?, ?)";
        dbOpen();
        PreparedStatement psData = conn.prepareStatement(major);
        psData.setString(1, majorid);
        psData.setString(2, majorname);
        int result = psData.executeUpdate();
        dbClose();
        return result > 0;

    }
    public boolean updateMajor(String majorid, String majorname) throws SQLException {

        String updateMajor = "update Major set majorid = ? ,majorname = ?";

        dbOpen();

        PreparedStatement psData = conn.prepareStatement(updateMajor);
        psData.setString(1, majorid);
        psData.setString(2, majorname);
        //psData.setString(3, majorid);
        int result = psData.executeUpdate();
        dbClose();
        return result > 0;

    }
}
