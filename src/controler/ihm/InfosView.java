package controler.ihm;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Picture;

/**
 * Vue de la fenetre de modification des attributs de l'image courante : 
 * affiche et capte les actions de l'utilisateur.
 * Transmet les interactions de modification des attributs a la classe Application
 * via l'InterfaceInfoView.
 */
class InfosView extends JFrame implements ActionListener {

	private JPanel container = new JPanel();
	private final GridBagLayout gbt;
	private final GridBagConstraints gbc;
	private JTextField titleEdit;
	private JTextArea descEdit;
	private Picture currentPicture;
	private InterfaceInfoView iiv;

	/**
	 * constructeur de la fenêtre infosView en attente d'être appelée. 
	 * Tant qu'elle n'est pas appelé par la méthode init(Picture p), currentPicture reste null.
	 * Par défaut n'est pas visible.
	 */
	InfosView(InterfaceInfoView iiv) {
		this.iiv = iiv;
		this.currentPicture = null;
		this.setTitle("Modifier Informations");
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.gbt = new GridBagLayout();
		this.gbc = new GridBagConstraints();
		this.setContentPane(container);
		this.setVisible(false);
	}

	@Override
	public Insets getInsets() {
		return new Insets(5, 5, 5, 5);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		switch (action) {
		case "Valider":
			// Modification du titre et description de l'image courante
			currentPicture.setTitle(titleEdit.getText());
			currentPicture.setDescription(descEdit.getText());
			iiv.updateHeaderInfos();
			// Quitter
			close();
		case "Cancel":
			close();
			break;
		default:
			break;
		}

	}
	
	/**
	 * Initialise la fenetre avec les composants
	 */
	 void init(Picture currentPicture) {
		Objects.requireNonNull(currentPicture);
		this.currentPicture = currentPicture;
		this.setVisible(true);

		String title = currentPicture.getTitle();
		String description = currentPicture.getDescription();

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
	 * methode de gestion visuelle des elements de la liste.
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

	private void close() {
		this.setVisible(false);
		this.dispose();
	}
}
