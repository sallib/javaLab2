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
	private JPanel empty;
	private JLabel titleLabel;
	private JLabel descLabel;
	private JButton valid;
	private JButton cancel;

	/**
	 * constructeur de la fenêtre infosView en attente d'être appelée. 
	 * Tant qu'elle n'est pas appelé par la méthode init(Picture p), currentPicture reste null.
	 * Par défaut n'est pas visible.
	 * @param InterfaceInfoView reference vers Application pour remonter les commandes donnees par l'utilisateur.
	 */
	InfosView(InterfaceInfoView iiv) {
		this.iiv = iiv;
		this.currentPicture = null;
		this.setTitle("Modifier Informations");
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setAlwaysOnTop(true);
		this.gbt = new GridBagLayout();
		this.gbc = new GridBagConstraints();
		this.setContentPane(container);

		empty = new JPanel();
		titleLabel = new JLabel("Titre");
		
		descLabel = new JLabel("Description");
		
		valid = new JButton("Valider");
		valid.setActionCommand("Valider");
		valid.addActionListener(this);
		cancel = new JButton("Annuler");
		cancel.setActionCommand("Cancel");
		cancel.addActionListener(this);
		container.setLayout(this.gbt);

		addComponent(titleLabel, 1, 2, 1, 1);
		titleEdit = new JTextField(null, 20);
		addComponent(empty, 3, 1, 3, 2);
		addComponent(descLabel, 5, 2, 1, 1);
		descEdit = new JTextArea(null, 3, 20);
		addComponent(empty, 8, 3, 3, 2);
		addComponent(valid, 12, 3, 1, 1);
		addComponent(cancel, 12, 4, 1, 1);
		addComponent(titleEdit, 1, 3, 2, 1);
		addComponent(descEdit, 5, 3, 2, 2);
		this.setVisible(false);
	}

	/**
	 * defini la zone active de la fenetre
	 */
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
			System.out.println("validate running:"+titleEdit.getText() +" "+ descEdit.getText());
			System.out.println("validater champs: "+ this.currentPicture.getTitle() +" "+ this.currentPicture.getDescription());
			iiv.updateHeaderInfos();
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
		String title = currentPicture.getTitle();
		String description = currentPicture.getDescription();
		
		titleEdit.setText(title);
		descEdit.setText(description);
		
		this.setVisible(true);
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

	/**
	 * Ferme la fenetre
	 */
	private void close() {
		System.out.println("close infosview");
		this.setVisible(false);
		this.dispose();
	}
}
