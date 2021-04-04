package project.startaidea4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ResetDB {

	public void resetTable(List<Tool> tools) throws Exception {
		String driver = null;
		Connection c = null;
		Statement statement = null;
		PreparedStatement ps = null;
		String url;
		try {
			c = ConnectionHelper.getConnection();
            Statement s = c.createStatement();
			String query = "TRUNCATE TABLE tools";
			s.execute(query);
			for (Tool tool : tools) {

				ps = (PreparedStatement) c.prepareStatement("INSERT INTO tools (title,link,description,tags)"
						+ "VALUES (?,?,?,?)",
						new String[] { "id" });
				ps.setString(1, tool.getTitle());
				ps.setString(2, tool.getLink());
				ps.setString(3, tool.getDescription());	
				ps.setString(4, tool.getTags().toString());
				ps.executeUpdate();
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void recoverData() {
		
		ToolDAO dao = new ToolDAO();
		dao.remove(1);
		dao.remove(2);
		//dao.remove(3);
		
		Tool notion = new Tool();
		notion.setTitle("Notion");
		notion.setLink("https://notion.so");
		notion.setDescription("All in one tool to organize teams and ideas. "
				+ "Write, plan, collaborate, and get organized. ");
		ArrayList<String> notions = new ArrayList();
		notions.add(0, "organization");
		notions.add(1, "planning");
		notions.add(2, "collaboration");
		notions.add(3, "writing");
		notions.add(4, "calendar");
		notion.setTags(notions);
		dao.create(notion);
	
		Tool server = new Tool();
		server.setTitle("json-server");
		server.setLink("https://github.com/typicode/json-server");
		server.setDescription("Fake REST API based on a json schema. Useful for mocking and creating "
				+ "APIs for front-end devs to consume in coding challenges.");
		ArrayList<String> serverTags = new ArrayList();
		serverTags.add(0, "api");
		serverTags.add(1, "json");
		serverTags.add(2, "schema");
		serverTags.add(3, "node");
		serverTags.add(4, "github");
		serverTags.add(5, "rest");
		server.setTags(serverTags);
		dao.create(server);
		
		Tool fastify = new Tool();
		fastify.setTitle("fastify");
		fastify.setLink("https://www.fastify.io/");
		fastify.setDescription("Extremely fast and simple, low-overhead web framework for "
				+ "NodeJS. Supports HTTP2.");
		ArrayList<String> fastifyTags = new ArrayList();
		fastifyTags.add(0, "api");
		fastifyTags.add(1, "json");
		fastifyTags.add(2, "schema");
		fastifyTags.add(3, "node");
		fastifyTags.add(4, "github");
		fastifyTags.add(5, "rest");
		fastify.setTags(fastifyTags);
		dao.create(fastify);		
	}
}
