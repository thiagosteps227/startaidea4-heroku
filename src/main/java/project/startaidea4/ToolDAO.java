package project.startaidea4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ToolDAO {
	
	public static List<Tool> findAll() {
        List<Tool> list = new ArrayList<Tool>();
        Connection c = null;
    	String sql = "SELECT * FROM tools ORDER BY id";
        try {
            c = ConnectionHelper.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return list;
    }
	
	public List<Tool> findByTag(String tags){
    	List<Tool> list = new ArrayList<Tool>();
    	
    	Connection c= null;
    	String sql = "SELECT * FROM tools AS e WHERE UPPER(tags) LIKE ?"
    			+ "ORDER BY id";
    	try {
    		c = ConnectionHelper.getConnection();
    		PreparedStatement ps = (PreparedStatement) c.prepareStatement(sql);
    		ps.setString(1, "%"+tags.toUpperCase()+"%");
    		ResultSet rs = ps.executeQuery();
    		while(rs.next()) {
    			list.add(processRow(rs));
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    		throw new RuntimeException(e);
    	} finally {
    	ConnectionHelper.close(c);
    	}
    	return list;
    }
	
	public static Tool create(Tool tool) {
    	Connection c= null;
    	java.sql.PreparedStatement ps = null;
    	try {
    		
    		c = ConnectionHelper.getConnection();
    		ps = c.prepareStatement("INSERT INTO tools "
    				+ "(title,link,description,tags)"
    				+ "VALUES(?,?,?,?)", 
    			new String[] { "id"});
    		ps.setString(1, tool.getTitle());
    		ps.setString(2, tool.getLink());
    		ps.setString(3, tool.getDescription());
    		ps.setString(4, tool.getTags().toString());   
    		ps.executeUpdate();
    		ResultSet rs = ps.getGeneratedKeys();
    		rs.next();
    		int id = rs.getInt(1);
    		tool.setId(id);
    	} catch (Exception e){
    		e.printStackTrace();
    		throw new RuntimeException(e);
    	} finally {
    		ConnectionHelper.close(c);
    	}
    	
    	return tool;
    }
	
	  public boolean remove(int id) {
	    	Connection c= null;
	    	try {
	    		c = ConnectionHelper.getConnection();
	    		PreparedStatement ps = (PreparedStatement) c.prepareStatement("DELETE FROM tools WHERE id =?");
	    		ps.setInt(1, id);
	    		int count = ps.executeUpdate();
				return count == 1;
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    		throw new RuntimeException (e);
	    	} finally {
	    		ConnectionHelper.close(c);
	    	}
	    }
	
	protected static Tool processRow(ResultSet rs) throws SQLException {
        Tool tool = new Tool();
        tool.setId(rs.getInt("id"));
        tool.setTitle(rs.getString("title"));
        tool.setLink(rs.getString("link"));
        tool.setDescription(rs.getString("description"));
        String tags = rs.getString("tags");
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(tags.split(",")));
        tool.setTags(list);
        
        return tool;
    }

}
