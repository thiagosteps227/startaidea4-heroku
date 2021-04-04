package project.startaidea4;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class Startaidea4ApplicationTests {

	ToolResource toolResource;
	
	@BeforeEach
	void setUp() throws Exception{
		toolResource = new ToolResource();
		ResetDB reset = new ResetDB();
		List<Tool> tools = new ArrayList<Tool>();
		Tool tool = new Tool();
		tool.setTitle("test");
		tool.setLink("www.test.com");
		tool.setDescription("description");
		ArrayList<String> toolTags = new ArrayList();
		toolTags.add(0, "tag0");
		toolTags.add(1, "tag1");
		tool.setTags(toolTags);
		tools.add(tool);
		
		tool = new Tool();
		tool.setTitle("test2");
		tool.setLink("https://www.test2.com");
		tool.setDescription("description2");
		ArrayList<String> toolTags2 = new ArrayList();
		toolTags2.add(0, "tag2");
		toolTags2.add(1, "tag3");
		tool.setTags(toolTags2);
		tools.add(tool);
		reset.resetTable(tools);
	}
	
	@Test
	void contextLoads() {
	}

	@Test
	void testGetAllTools() {
		List<Tool> list = toolResource.findAll();
		assertEquals(2, list.size());
		Tool tool = list.get(0);
		assertEquals("test", tool.getTitle());
		tool = list.get(1);
		assertEquals("test2", tool.getTitle());
		assertEquals("https://www.test2.com", tool.getLink());
		assertEquals("description2", tool.getDescription());
		
	}
	
	@Test
	void testFindByTag() {
		List<Tool> list = toolResource.findByTag("tag0");
		assertEquals("test", list.get(0).getTitle());
	}
	
	@Test
	void testCreateTool() {
		Tool tool3 = new Tool();
		tool3 .setTitle("test3");
		tool3 .setLink("https://www.test2.com");
		tool3 .setDescription("description2");
		ArrayList<String> toolTags3 = new ArrayList();
		toolTags3.add(0, "tag4");
		toolTags3.add(1, "tag5");
		tool3.setTags(toolTags3);
		toolResource.create(tool3);
		assertEquals("test3", tool3.getTitle());
	}
	
	@Test
	void testRemoveTool() {
		List<Tool> list = new ArrayList();
		Tool tool3 = new Tool();
		tool3 .setTitle("test3");
		tool3 .setLink("https://www.test2.com");
		tool3 .setDescription("description2");
		ArrayList<String> toolTags3 = new ArrayList();
		toolTags3.add(0, "tag4");
		toolTags3.add(1, "tag5");
		tool3.setTags(toolTags3);
		toolResource.create(tool3);
		ResponseEntity<Tool> responseEntity = toolResource.remove(tool3.getId());
		assertNull(responseEntity.getBody());
	}
	
	@AfterAll
	public static void backToNormal() {
		
		ResetDB recover = new ResetDB();
		recover.recoverData();
	}
	
}
