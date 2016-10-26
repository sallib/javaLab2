package graphic.model;



public class Picture {

	private String title;
	private String description;
	private String path;

	public Picture() {
	}

	public Picture(String path) {
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
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
