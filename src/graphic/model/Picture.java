package graphic.view;

public class Picture {

	private String title;
	private String description;
	private String path;

	public Picture() {
	}

	public Picture(String path) {
		this.path = path;
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
