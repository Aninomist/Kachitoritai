package eiffle.PandaMeiyaReykaSuki.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import eiffle.PandaMeiyaReykaSuki.model.Choice;



public class ChoiceDAO {


	java.sql.Connection conn;
	
	final String tblName = "Choice"; 
	
	public ChoiceDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
	
	public boolean existChoice(String choiceID) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ChoiceID = ?;");
			ps.setString(1, choiceID);
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				return true;
	        }
			
			return false;
			
		} catch (Exception e) {
			System.out.println("exception caught in existChoice");
			throw new Exception("Falied to checking if ChoiceID exist: " + e.getMessage());
		}

	}
	
	public boolean addChoice(Choice choice) throws Exception {
		try {
			//System.out.println("prep statement 1 where choise = ");
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ChoiceID = ?;");
			ps.setString(1, choice.choiceID);
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				System.out.println("System generated a duplicate UUID!");
				return false;
	        }
			
			
			System.out.println("prep statement 2 to insert");
			ps = conn.prepareStatement("INSERT INTO " + tblName
					+ " (choiceID, description, numberOfAlternative, dateCreated, userLimit, userRegistered, completed) values(?,?,?,?,?,?,FALSE);");
			ps.setString(1,  choice.choiceID);
			ps.setString(2,  choice.description);
            ps.setInt(3,  choice.numAlt);
            ps.setString(4,  choice.dateCreated);
            ps.setInt(5,  choice.limitMember);
            ps.setInt(6, 0);
            System.out.println("etatement 2 execute:" + ps);
            ps.execute();
            return true;	 
            
		} catch (Exception e) {
			System.out.println("exception caught in addChoice");
			throw new Exception("Falied to insert Choice: " + e.getMessage());
		}

	}
	
	public List<Choice> getAllChoices() throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName +";");
			ResultSet resultSet = ps.executeQuery();
			
			List<Choice> listOfChoices = new ArrayList<>();
			
			while (resultSet.next() ) {
				Choice choice = constructChoice(resultSet);
				listOfChoices.add(choice);
			}
			
			return listOfChoices;

		} catch (Exception e) {
			System.out.println("exception caught in getAllChoices");
			throw new Exception("Falied to get getAllChoices: " + e.getMessage());
		}
	}
	
	public boolean getChoice(String choiceID) throws Exception {
		//TODO
		return false;
	}
	
	
	private Choice constructChoice(ResultSet result) throws Exception {
		return new Choice(
				result.getString("choiceID"),
				result.getString("description"),
				result.getInt("numberOfAlternative"),
				result.getString("dateCreated"),
				result.getInt("userLimit"),
				result.getInt("userRegistered"),
				result.getBoolean("completed"));
	}

	public String getChoiceDescription(String choiceID) throws Exception{
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ChoiceID = ?;");
			ps.setString(1, choiceID);
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				return resultSet.getString("description");
	        }
			
			return resultSet.getString("description");
			
		} catch (Exception e) {
			System.out.println("exception caught in existChoice");
			throw new Exception("Falied to checking if ChoiceID exist: " + e.getMessage());
		}

	}

	public boolean checkComplete(String choiceID) throws Exception{
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ChoiceID = ?;");
			ps.setString(1, choiceID);
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				return resultSet.getBoolean("completed");
	        }
			
			return resultSet.getBoolean("completed");
			
		} catch (Exception e) {
			System.out.println("exception caught in existChoice");
			throw new Exception("Falied to check if choice Completed: " + e.getMessage());
		}
	}
}
