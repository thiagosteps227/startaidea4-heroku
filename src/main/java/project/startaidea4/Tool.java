package project.startaidea4;

import java.util.ArrayList;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class Tool {
	
	private int id;
	private String title;
	private String link;
	private String description;  
	private ArrayList<String> tags;
	
	public Tool() {

	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
