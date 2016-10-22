package graphic.view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import graphic.controler.ViewerControler;
import graphic.model.PictureListModel;
import graphic.model.Picture;

public class ImgListView extends AbstractView implements ActionListener {
	private final PictureListModel model;
	private final GridBagLayout gbt = new GridBagLayout();
	private final GridBagConstraints gbc = new GridBagConstraints();
	private final JPanel content = new JPanel();
	private final Label label;
	private final List list;
	private final JButton displayButt;
	private String selectedItem;

	/**
	 * Constructeur par defaut appelee par Window
	 * @param model liste des images préalablement instanciee pour construire la vue initiale.
	 */
	public ImgListView(PictureListModel model) {
		super(model);
		this.model = model;
		this.label = new Label("Liste des images :");
		this.list = new List();
		this.displayButt = new JButton("Afficher");
		displayButt.addActionListener(this);
		content.setLayout(gbt);
	}

	/**
	 * Getter pour l'element actif de la liste.
	 * @return l'index de l'element actif.
	 */
	public int getSelectedItemIndex() {
		return list.getSelectedIndex();
	}

	/**
	 * Constructeur visuel de la liste.
	 * @return l'objet Jpanel permettant de manipuler la liste.
	 */
	public JPanel getImageList() {
		fillFileList();
		selectFirstItem();

		JPanel empty = new JPanel();
		addComponent(label, 0, 0, 1, 1);
		addComponent(list, 2, 0, 1, 4);
		addComponent(empty, 8, 0, 1, 2);
		addComponent(displayButt, 10, 0, 1, 1);
		return content;
	}

	/**
	 * Methode interne pour ajouter les paths de chaque image dans la liste texte affichee.
	 */
	private void fillFileList() {
		model.getFileList().forEach(item -> {
			list.add(item.getPath());
		});
	}

	/**
	 * Methode interne de selection par defaut du premier element de la liste pour le mettre actif. 
	 * Utilisee a l'instanciation.
	 */
	private void selectFirstItem() {
		if (list.getItemCount() > 0) {
			list.select(0);
			selectedItem = list.getItem(0);
		}
	}


	/**
	 * methode de gestion visuel des element de la liste.
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

	/**
	 * Redefinition de l'action a realiser suite a un clic dans la liste.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		selectedItem = list.getSelectedItem();
		ViewerControler controler = new ViewerControler(this, model);
		controler.displaySelectedItem(selectedItem);
	}

}
