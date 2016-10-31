package model;

import java.util.Objects;

public class Picture {

	private String title;
	private String description;
	private String path;

	public Picture(String path) {
		Objects.requireNonNull(path);
		this.path = path;
		this.title = path.split("/")[path.split("/").length-1];
		this.description = "pas de description";
		
	}

	public String getPath() {
		return path;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		Objects.requireNonNull(title);
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		Objects.requireNonNull(description);
		this.description = description;
	}
	
	
}
