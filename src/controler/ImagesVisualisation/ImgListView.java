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


class ImgListView implements ActionListener {
	private final GridBagLayout gbt;
	private final GridBagConstraints gbc;
	private final JPanel content;
	private final Label label;
	private final List list;
	private final JButton displayButt;
	private InterfaceILV iilv;

	/**
	 * Constructeur par defaut appelee par Window
	 * 
	 * @param model
	 *            liste des images préalablement instanciee pour construire la
	 *            vue initiale.
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

	void refresh(int index) {
		list.select(index);
	}
	
	JPanel getImgList() {
		return content;
	}

	/**
	 * Methode interne pour ajouter les paths de chaque image dans la liste
	 * texte affichee.
	 */
	void fillFileList(ArrayList<Picture> fileList) {
		fileList.forEach(item -> {
			String[] paths = item.getPath().split("/");
			list.add(paths[paths.length-1]);
		});
		selectFirstItem();
	}
	
	void addOneImgToList(String img){
		String[] paths = img.split("/");
		list.add(paths[paths.length-1]);
	}

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
