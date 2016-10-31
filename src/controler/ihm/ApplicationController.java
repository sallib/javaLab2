package controler.ihm;

import java.util.Objects;

import controler.ImagesVisualisation.ViewerControler;
import model.Picture;
import model.PictureListModel;

/**
 * Instancie les vues des différents blocs et synchronise les interactions entre chaque vues grace aux interfaces.
 *
 */
public class ApplicationController implements InterfaceInfoView,InterfaceMenuView {
	private MenuView menu;
	private WindowView window;
	private static InfosView infos;
	private ViewerControler vc;
	
	/**
	 * Constructeur de l'application.
	 * @param PictureListModel	liste d'images par defaut a charger.
	 */
	public ApplicationController(PictureListModel model){
		Objects.requireNonNull(model);
		vc = ViewerControler.create(model);
		menu = new MenuView(this);
		window = WindowView.create(vc.getImageList(),vc.getImageView(),menu.getMenu());
		infos = new InfosView(this);
	}

	/**
	 * Transmet la consigne de rafraichissement visuelle des attributs d'images à l'ecran au ViewerController.
	 */
	@Override
	public void updateHeaderInfos() {
		vc.updateHeaderInfos();
	}
	
	/**
	 * Affiche la fenetre de modification des attributs de l'image courante.
	 * @param current
	 */
	public static void activateInfoView(Picture current){
		infos.init(current);
	}

	/**
	 * Transmet la consigne d'ajouter l'image en parametre a la structure ViewerController.
	 */
	@Override
	public void addPicture(String newImg) {
		vc.addPicture(newImg);
	}

	/**
	 * Transmet la consigne de fermeture de l'application a la vue Window.
	 */
	@Override
	public void closeWindow() {
		window.close();
	}
}
