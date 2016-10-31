package model;

import java.util.Objects;

/**
 * Modele de donnees pour stocker une image.
 *
 */
public class Picture {

	private String title;
	private String description;
	private String path;

	/**
	 * constructeur de l'image.
	 * @param path url de l'image
	 */
	public Picture(String path) {
		Objects.requireNonNull(path);
		this.path = path;
		this.title = path.split("/")[path.split("/").length-1];
		this.description = "pas de description";

	}
	/**
	 * getter de l'url
	 * @return String
	 */
	public String getPath() {
		return path;
	}
	/**
	 * getter du titre de l'image
	 * @return String
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * setter du titre
	 */
	public void setTitle(String title) {
		Objects.requireNonNull(title);
		this.title = title;
	}

	/**
	 * getter de la description
	 * @return String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * setter de la description
	 * @return String
	 */
	public void setDescription(String description) {
		Objects.requireNonNull(description);
		this.description = description;
	}


}
