package controler.ImagesVisualisation;

import java.util.ArrayList;
import java.util.Objects;

import javax.swing.JPanel;

import controler.ihm.ApplicationController;
import model.Picture;
import model.PictureListModel;

/**
 * Controler pour la gestion de l'affichage des images.
 * Interagit avec les vues CurrentImageView et ImageListView pour synchroniser leurs affichages.
 * Modifie le modele PictureListModel au besoin.
 *
 */
public class ViewerControler implements InterfaceCIV,InterfaceILV {

	private final CurrentImgView CIV;
	private final ImgListView ILV;
	private final PictureListModel model;
	private String selectedItem;
	private ArrayList<String> fileListPath;

	/**
	 * Constructeur du controler.
	 * @param model la liste par defaut a stocker et afficher a l'ouverture du programme.
	 */
	private ViewerControler(PictureListModel model) {
		this.model = model;
		this.CIV = new CurrentImgView(this);
		this.ILV = new ImgListView(this);
	}

	/**
	 * factoryMethod pour instancier correctement chacune des vues.
	 * 
	 * @param model la liste par defaut a stocker et afficher a l'ouverture du programme.
	 * @return l'objet controler instancie.
	 */
	public static ViewerControler create(PictureListModel model) {
		Objects.requireNonNull(model);
		ViewerControler vc = new ViewerControler(model);
		vc.ILV.fillFileList(vc.model.getFileList());
		vc.selectedItem = vc.model.getFileList().get(0).getPath();
		vc.fileListPath = vc.model.getAllPaths();
		vc.CIV.setDefaultImageView(vc.getCurrentPict());
		vc.updateHeaderInfos();
		return vc;
	}
	
	/**
	 * methode pour commander l'actualisation de l'affichage des informations de l'image courante a CurrentImageView.
	 */
	public void updateHeaderInfos() {
		CIV.setTextInfo(getCurrentPict().getTitle(), getCurrentPict().getDescription());
	}
	
	/**
	 * Suite a la requete de l'utilisateur capturee par CurrentImageView, le controleur remonte la demande d'ouverture
	 * de la fenetre d'edition.
	 */
	@Override
	public void openInfoView(){
		ApplicationController.activateInfoView(getCurrentPict());
	}
	
	/**
	 * methode d'ajout de l'image passee en parametre dans chacune des vues et dans le modele.
	 * @param newImg l'image a inserer
	 */
	public void addPicture(String newImg){
		Objects.requireNonNull(newImg);
		model.addPicture(newImg);
		fileListPath.add(newImg);
		ILV.addOneImgToList(newImg);
		changeCurrentImg(newImg);
	}

	/**
	 * commande pour passer a l'image precedente en synchronisant les vues.
	 */
	@Override
	public void setPreviousPicture() {
		changeCurrentImg(fileListPath.get((getCurrentIndex() - 1) < 0 ? 
				0 : getCurrentIndex() - 1));
	}

	/**
	 * commande pour passer a l'image suivante en synchronisant les vues.
	 */
	@Override
	public void setNextPicture() {
		changeCurrentImg(fileListPath.get((getCurrentIndex() + 1) > fileListPath.size()-1 ? 
				fileListPath.size()-1 : getCurrentIndex() + 1 ));
	}

	/**
	 * commande pour passer a l'image passee en parametre en synchronisant les vues.
	 * @param index image courante desiree.
	 */
	@Override
	public void setCurrentPicture(int index) {
		changeCurrentImg(fileListPath.get((index >= 0 && index < fileListPath.size())?
				index : 0));
	}
	
	/**
	 * retourne la vue a afficher pour ImageListView.
	 * @return JPanel
	 */
	public JPanel getImageList() {
		return ILV.getImgList();
	}

	/**
	 * retourne la vue a afficher pour CurrentImageView.
	 * @return JPanel
	 */
	public JPanel getImageView() {
		return CIV.getImageView();
	}

	/**
	 * retourne le modele des images.
	 * @return
	 */
	private PictureListModel getModel() {
		return model;
	}

	/**
	 * Methode d'accee a l'image courante.
	 * @return l'image courante.
	 */
	private Picture getCurrentPict(){
		return getModel().getFileList().get(getCurrentIndex()); 
	}

	/**
	 * Methode pour demander le rafraichissement des vues suite a l'actualisation de la vue courante.
	 * @param item
	 */
	private void changeCurrentImg(String item) {
		selectedItem = item;
		updateHeaderInfos();
		CIV.refresh(item,getCurrentIndex(),fileListPath.size());
		ILV.refresh(getCurrentIndex());
	}

	/**
	 * Methode d'accee a l'index courant.
	 * @return int
	 */
	private int getCurrentIndex() {
		return fileListPath.indexOf(selectedItem);
	}
}
