package controler.ImagesVisualisation;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Picture;

/**
 * Vue pour la liste des images.
 * Gere l'affichage et la capture des interactions avec l'utilisateur.
 * Affiche la liste des images et permet leur affichage via le bouton d'affichage.
 * Renvoie les commandes lancees par l'utilisateur au ViewerController pour repercuter sur
 * toutes les vues l'action realisees.
 *
 */
class ImgListView implements ActionListener {
	private final GridBagLayout gbt;
	private final GridBagConstraints gbc;
	private final JPanel content;
	private final Label label;
	private final List list;
	private final JButton displayButt;
	private InterfaceILV iilv;

	/**
	 *  Constructeur de la vue d'affichage de l'image courante et de ses informations.
	 * @param InterfaceILV  donne la reference vers le ViewerController pour synchroniser les actions de l'utilisateur
	 * pour toutes les vues.
	 */
	ImgListView(InterfaceILV iilv) {
		Objects.requireNonNull(iilv);
		this.iilv = iilv;
		this.gbt = new GridBagLayout();
		this.content = new JPanel();
		this.gbc = new GridBagConstraints();
		this.label = new Label("Liste des images :");
		this.list = new List();
		this.displayButt = new JButton("Afficher");
		init();
	}
	
	/**
	 * Action lorsque l'utilisateur clique sur le bouton Afficher
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int z = list.getSelectedIndex();
		iilv.setCurrentPicture(z);
	}

	/**
	 * Selectionne l'element de la liste precise par l'index en parametre.
	 * @param index de l'element.
	 */
	void refresh(int index) {
		list.select(index);
	}
	
	/**
	 * getter de la liste pour le controller
	 * @return la liste des images sous forme d'url.
	 */
	JPanel getImgList() {
		return content;
	}

	/**
	 * Methode d'initialisation pour ajouter les paths de chaque image dans la liste
	 * texte affichee.
	 * @param ArrayList<Picture> la liste des images a afficher sous forme de texte.
	 */
	void fillFileList(ArrayList<Picture> fileList) {
		fileList.forEach(item -> {
			String[] paths = item.getPath().split("/");
			list.add(paths[paths.length-1]);
		});
		selectFirstItem();
	}
	
	/**
	 * methode d'ajout manuel d'image dans la liste.
	 * @param img l'url de l'image.
	 */
	void addOneImgToList(String img){
		String[] paths = img.split("/");
		list.add(paths[paths.length-1]);
	}

	/**
	 * Methode d'ajout d'un actionListener sur le bouton d'affichage.
	 */
	private void init(){
		content.setBorder(BorderFactory.createEtchedBorder());
		this.setImageList();
		this.displayButt.addActionListener(this);
		this.content.setLayout(this.gbt);
	}

	/**
	 * Constructeur visuel de la liste.
	 * 
	 * @return l'objet Jpanel permettant de manipuler la liste.
	 */
	private void setImageList() {
		JPanel empty = new JPanel();
		addComponent(label, 0, 0, 1, 1);
		addComponent(list, 2, 0, 1, 4);
		addComponent(empty, 8, 0, 1, 2);
		addComponent(displayButt, 10, 0, 1, 1);
	}

	/**
	 * Methode interne de selection par defaut du premier element de la liste
	 * pour le mettre actif. Utilisee a l'instanciation.
	 */
	private void selectFirstItem() {
		if (list.getItemCount() > 0) {
			list.select(0);
		}
	}

	/**
	 * methode de gestion visuel des element de la liste.
	 * 
	 * @param c
	 * @param ligne
	 * @param colonne
	 * @param largeur
	 * @param hauteur
	 */
	private void addComponent(Component c, int ligne, int colonne, int largeur, int hauteur) {
		gbc.gridx = colonne;
		gbc.gridy = ligne;
		gbc.gridwidth = largeur;
		gbc.gridheight = hauteur;
		gbt.setConstraints(c, gbc);
		content.add(c);
	}
}
