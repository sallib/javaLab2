package graphic.main;

import java.io.File;

import graphic.controler.ViewerControler;
import graphic.model.PictureListModel;
import graphic.view.AbstractView;
import graphic.view.Window;

public class Main {

	public static void main(String[] args) {
		File directory = new File("./pict");
		// Instanciation de notre modèle
		PictureListModel model = PictureListModel.createViewer(directory);
		// Création de notre fenêtre avec le contrôleur en paramètre
		Window window = new Window(model);
		//AbstractView view = new AbstractView(model);
		// Création du contrôleur
		//ViewerControler controler = new ViewerControler(view, model);
		
		// Ajout de la fenêtre comme observer de notre modèle
		//model.addObserver(window);
	}
}