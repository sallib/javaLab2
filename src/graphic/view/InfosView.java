package graphic.view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import graphic.controler.ViewerControler;
import graphic.model.Picture;
import graphic.model.PictureListModel;

public class InfosView extends JFrame implements ActionListener {

	private JPanel container = new JPanel();
	private final GridBagLayout gbt;
	private final GridBagConstraints gbc;
	private final PictureListModel model;
	private final ViewerControler vc;
	private JTextField titleEdit;
	private JTextArea descEdit;
	private Picture currentPict;
	
	public InfosView(PictureListModel model, ViewerControler vc) {
		this.model = model;
		this.vc = vc;
		this.setTitle("Modifier Informations");
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.gbt = new GridBagLayout();
		this.gbc = new GridBagConstraints();
		init();
		this.setContentPane(container);
		this.setVisible(true);
	}

	private void init() {
		//Récupération du titre et de la description de l'image courante
		int z = vc.getCurrentIndex();
		currentPict = model.getFileList().get(z);
		String title = currentPict.getTitle();
		String description = currentPict.getDescription();
		
		JPanel empty = new JPanel();
		JLabel titleLabel = new JLabel("Titre");
		titleEdit = new JTextField(title, 20);
		JLabel descLabel = new JLabel("Description");
		descEdit = new JTextArea(description, 3, 20);
		JButton valid = new JButton("Valider");
		valid.setActionCommand("Valider");
		valid.addActionListener(this);
		JButton cancel = new JButton("Annuler");
		cancel.setActionCommand("Cancel");
		cancel.addActionListener(this);
		container.setLayout(this.gbt);

		addComponent(titleLabel, 1, 2, 1, 1);
		addComponent(titleEdit, 1, 3, 2, 1);
		addComponent(empty, 3, 1, 3, 2);
		addComponent(descLabel, 5, 2, 1, 1);
		addComponent(descEdit, 5, 3, 2, 2);
		addComponent(empty, 8, 3, 3, 2);
		addComponent(valid, 12, 3, 1, 1);
		addComponent(cancel, 12, 4, 1, 1);

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
		container.add(c);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		switch (action) {
		case "Valider":
			//Modification du titre et description de l'image courante
			currentPict.setTitle(titleEdit.getText());
			currentPict.setDescription(descEdit.getText());
			//Quitter
			this.setVisible(false);
			this.dispose();
			break;
		case "Cancel":
			this.setVisible(false);
			this.dispose();
			break;
		default:
			break;
		}

	}

	@Override
	public Insets getInsets() {
		return new Insets(5, 5, 5, 5);
	}
}
