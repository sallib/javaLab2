package graphic.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InfosView extends JFrame implements ActionListener {

	private JPanel container = new JPanel();
	private final GridBagLayout gbt;
	private final GridBagConstraints gbc;
	
	public InfosView() {
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
		JPanel empty = new JPanel();
		JLabel title = new JLabel("Titre");
		JTextField titleEdit = new JTextField("No title",20);

		JLabel desc = new JLabel("Description");
		JTextArea descEdit = new JTextArea("No description", 3, 20);		
		JButton valid = new JButton("Valider");
		valid.setActionCommand("Valider");
		valid.addActionListener(this);
		JButton exit = new JButton("Quitter");
		exit.setActionCommand("Exit");
		exit.addActionListener(this);
		container.setLayout(this.gbt);
		
		addComponent(title, 1, 2,1, 1);
		addComponent(titleEdit, 1, 3,2, 1);
		addComponent(empty, 3, 1, 3, 2);
		addComponent(desc, 5, 2, 1, 1);
		addComponent(descEdit, 5, 3, 2,2);
		addComponent(empty, 8, 3, 3, 2);
		addComponent(valid, 12, 3, 1, 1);
		addComponent(exit, 12, 4, 1, 1);
		
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
			break;
		case "Exit":
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
