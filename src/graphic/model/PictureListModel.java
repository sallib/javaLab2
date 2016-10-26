package graphic.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class PictureListModel {
	private final ArrayList<Picture> fileList;

	/**
	 * Constructeur par defaut.
	 */
	private PictureListModel() {
		this.fileList = new ArrayList<>();
	}

	/**
	 * createur de l'objet PictureListModel qui gère les objets Pictures sous
	 * forme d'ArrayList.
	 * 
	 * @param directory
	 *            le dossier par défaut contenant des images.
	 * @return l'objet PictureListModel
	 */
	public static PictureListModel createViewer(File directory) {
		PictureListModel viewer = new PictureListModel();
		viewer.loadPictures(directory);
		return viewer;
	}

	/**
	 * Methode pour remplir la liste des images stockees dans le repertoire par
	 * defaut.
	 * 
	 * @param directory
	 *            repertoire par defaut.
	 */
	private void loadPictures(File directory) {
		Objects.requireNonNull(directory);
		Arrays.asList(directory.list()).forEach(f -> {
			fileList.add(new Picture(directory.getPath() + "/"+ f));
		});
	}

	
	//TODO : suppress
	private void affiche(){
		fileList.forEach( t  -> {
			System.out.print(t.getTitle() + " ; ");
		});
		System.out.println("");
	}
	/**
	 * Getter pour la liste d'image.
	 * 
	 * @return
	 */
	public ArrayList<Picture> getFileList() {
		affiche(); //TODO suppress
		return this.fileList;
	}

	public ArrayList<String> getAllPaths() {
		ArrayList<String> allPaths = new ArrayList<>();
		for (Picture p : fileList) {
			allPaths.add(p.getPath());
		}
		return allPaths;
	}

	public void addPicture(String path) {
		
		fileList.add(new Picture(path));
	}
}
