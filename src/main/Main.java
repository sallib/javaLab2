package main;

import java.io.File;

import controler.ihm.ApplicationController;
import model.PictureListModel;

public class Main {

	public static void main(String[] args) {
		File directory = new File("./pict");
		// Instanciation de notre modèle
		PictureListModel model = PictureListModel.createViewer(directory);
		// Création de notre fenêtre avec le contrôleur en paramètre
		 new ApplicationController(model);
	}
}