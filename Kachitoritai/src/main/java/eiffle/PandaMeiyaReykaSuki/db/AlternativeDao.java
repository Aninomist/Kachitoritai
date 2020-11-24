package eiffle.PandaMeiyaReykaSuki.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import eiffle.PandaMeiyaReykaSuki.model.Alternative;

public class AlternativeDao {

	java.sql.Connection conn;
	
	final String tblName = "Alternative"; 
	
	public AlternativeDao() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
	
	public boolean createAlternatives(int numAlt, String choiceID) throws Exception {
		try {
			for (int i = 0; i < numAlt; i++) {
				String altID = UUID.randomUUID().toString();
				PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tblName +
						" (altID, choiceID, isFinalAlternative) values (?,?,FALSE)");
				ps.setString(1, altID);
				ps.setString(2, choiceID);
				ps.execute();
			}
			return true;
			
		} catch(Exception e) {
			throw new Exception("Falied to create Alternatives: " + e.getMessage());
		}
	}
	
	public List<Alternative> getAlternatives(String choiceID) throws Exception {
		List<Alternative> allAlts = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE choiceID = ?");
			ps.setString(1, choiceID);
			ResultSet resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				Alternative alt = generateAlt(resultSet);
				allAlts.add(alt);
			}
			
			return allAlts;
			
		} catch(Exception e) {
			throw new Exception("Falied to create Alternatives: " + e.getMessage());
		}
	}
	
	private Alternative generateAlt(ResultSet result) throws Exception {
		return new Alternative(
				result.getString("altID"),
				result.getString("choiceID"),
				result.getBoolean("isFinalAlternative"));
	}
}