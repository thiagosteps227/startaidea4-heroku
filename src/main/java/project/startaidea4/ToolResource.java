package project.startaidea4;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ToolResource {
	
	ToolDAO dao = new ToolDAO();

	@GetMapping("/tools")
	//http://localhost:3000/api/tools
	public List<Tool> findAll() {
		return dao.findAll();
	}

	@GetMapping("/tools?tag={tag}")
	//http://localhost:3000/api/tools?tag=node
	public List <Tool>findByTag(@PathVariable(value = "tag") String tag){
		return dao.findByTag(tag);
		}
	
	@PostMapping("/tools")
	//http://localhost:3000/api/tools
	public Tool create(@RequestBody Tool tool) {
			return dao.create(tool);
	}
	
	@DeleteMapping("/tools/{id}")
	//http://localhost:3000/api/tools/id
	public ResponseEntity<Tool> remove(@PathVariable("id") int id) {
		dao.remove(id);
		return ResponseEntity.noContent().build();
	}
}
