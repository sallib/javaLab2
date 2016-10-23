package graphic.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import graphic.controler.ViewerControler;
import graphic.model.Picture;
import graphic.model.PictureListModel;

public class CurrentImgView extends AbstractView implements ActionListener {

	private JLabel pict;
	private final JPanel content;
	private JPanel imgPanel;

	private CurrentImgView(PictureListModel model) {
		super(model);
		this.content = new JPanel();
		this.imgPanel = new JPanel();
	}

	public static CurrentImgView create(PictureListModel model) {
		CurrentImgView CIV = new CurrentImgView(model);
		return CIV;
	}

	/**
	 * Methode d'affichage de l'image active centrale
	 * 
	 * @return
	 */
	public void setImageView() {
		content.setLayout(new BorderLayout());
		content.add(getHeadPanel(), BorderLayout.NORTH);
		content.add(getCenter(), BorderLayout.CENTER);
		content.add(getFootPanel(), BorderLayout.SOUTH);
	}

	public JPanel getImageView() {
		return content;
	}

	public void setViewerController(ViewerControler vc) {
		super.setViewerController(vc);
	}

	/**
	 * initialisateur de l'image centrale.
	 * 
	 * @return
	 */
	private JPanel getCenter() {
		pict = new JLabel(new ImageIcon("pict/" + getCurrentPicture()));
		imgPanel.setLayout(new BorderLayout());
		imgPanel.add(pict, BorderLayout.CENTER);
		return imgPanel;
	}

	/**
	 * initialisateur des boutons de navigation dans la liste.
	 * 
	 * @return
	 */
	private JPanel getFootPanel() { // TODO ajouter les listener.
		JPanel foot = new JPanel();
		GridLayout gridLayout = new GridLayout();
		gridLayout.setColumns(2);
		foot.setLayout(gridLayout);

		JButton previousButt = new JButton("Précédent");
		previousButt.setActionCommand("Previous");// Nom de commande générique
		JButton nextButt = new JButton("Suivant");
		nextButt.setActionCommand("Next"); // Nom de commande générique
		previousButt.addActionListener(this);
		nextButt.addActionListener(this);
		foot.add(previousButt);
		foot.add(nextButt);
		return foot;
	}

	/**
	 * initialisateur du bandeau de visualisation des infos image + bouton
	 * modification
	 * 
	 * @return
	 */
	private JPanel getHeadPanel() { // TODO listener à implanter
		JPanel head = new JPanel();
		head.setLayout(new GridLayout(3, 3));

		Label title = new Label("Titre : ");
		Label desc = new Label("Description : ");
		JButton modifyButt = new JButton("Modifier");
		modifyButt.setActionCommand("Modify"); // Nom de commande générique
		modifyButt.addActionListener(this);
		head.add(title);
		head.add(desc);
		head.add(modifyButt);
		return head;
	}

	@Override
	public void actionPerformed(ActionEvent action) {
		int z = super.getCurrentIndex();
		ArrayList<Picture> list = getModel().getFileList();
		String actionCmd = action.getActionCommand();
		switch (actionCmd) {
		case "Previous":
			if (z > 0) {
				super.displaySelectedItem(list.get(z -1).getPath());
			}else{
				//début de la liste : on repart à la fin
				super.displaySelectedItem(list.get(list.size()-1).getPath()); 
			}
			break;
		case "Next":
			if (z < list.size()-1) {
				super.displaySelectedItem(list.get(z + 1).getPath());
			}else{
				//fin de la liste : on repart du début
				super.displaySelectedItem(list.get(0).getPath()); 
			}
			break;
		case "Modify":
			System.out.println("Modifier les infos de l'image...");
		default: // Never happen
			break;
		}
	}

	/**
	 * Methode pour actualiser la vue de l'image.
	 */
	public void refresh() {
		System.out.println("Refresh picture");
		pict.setIcon(new ImageIcon("pict/" + getCurrentPicture()));
	}

}
