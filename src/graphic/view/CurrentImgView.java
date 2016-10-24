package graphic.view;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import graphic.controler.ViewerControler;
import graphic.model.Picture;
import graphic.model.PictureListModel;

public class CurrentImgView extends AbstractView implements ActionListener {

	private JLabel pict;
	private final JPanel content;
	private JPanel imgPanel;
	private JButton previousButt;
	private JButton nextButt;
	private Label title;
	private Label desc;

	public CurrentImgView(PictureListModel model) {
		super(model);
		this.content = new JPanel();
		this.imgPanel = new JPanel();
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
		imgPanel.setBorder(BorderFactory.createEtchedBorder());
		ImageIcon img = new ImageIcon("pict/" + getCurrentPicture());
		img.setImage(scale(img.getImage(), 400, 300));
		pict = new JLabel(img);
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

		previousButt = new JButton("Précédent");
		previousButt.setIcon(new ImageIcon("icon/back.png"));
		previousButt.setActionCommand("Previous");// Nom de commande générique
		nextButt = new JButton("Suivant");
		nextButt.setIcon(new ImageIcon("icon/next.png"));
		nextButt.setActionCommand("Next"); // Nom de commande générique
		previousButt.addActionListener(this);
		previousButt.setEnabled(false);
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
		
		Label titleLabel = new Label("Titre : ");
		Label descLabel = new Label("Description : ");
		title = new Label();
		desc = new Label();
		JButton modifyButt = new JButton("Modifier");
		modifyButt.setActionCommand("Modify"); // Nom de commande générique
		modifyButt.addActionListener(this);
		head.add(titleLabel);
		head.add(title);
		head.add(descLabel);
		head.add(desc);
		head.add(modifyButt);
		updateHeaderInfos();
		return head;
	}

	public void updateHeaderInfos(){
		int index = super.getCurrentIndex();
		Picture currentPict = getModel().getFileList().get(index);
		title.setText(currentPict.getTitle());
		desc.setText(currentPict.getDescription());
	}
	
	@Override
	public void actionPerformed(ActionEvent action) {
		int z = super.getCurrentIndex();
		ArrayList<Picture> list = getModel().getFileList();
		String actionCmd = action.getActionCommand();

		// Désactive les boutons suivant / Précédent lorsque nécessaire
		if (z - 1 <= 0) {
			previousButt.setEnabled(false);
		} else if ((z + 1) >= (list.size() - 1)) {
			nextButt.setEnabled(false);
		}

		// Switch selon le bouton activé par l'utilisateur
		switch (actionCmd) {
		case "Previous":
			if (z > 0) {
				super.displaySelectedItem(list.get(z - 1).getPath());
				nextButt.setEnabled(true); // Réactive le bouton next
			}
			break;
		case "Next":
			if (z < list.size() - 1) {
				super.displaySelectedItem(list.get(z + 1).getPath());
				previousButt.setEnabled(true); // Réactive le bouton previous
			}
			break;
		case "Modify":
			System.out.println("Modifier les infos de l'image...");
			int index = super.getCurrentIndex();
			InfosView iv = new InfosView(this);
		default: // Never happen
			break;
		}
		updateHeaderInfos();
	}

	
	public Picture getCIVCurrentPict(){
		int index = super.getCurrentIndex();
		return getModel().getFileList().get(index);
	}
	/**
	 * Methode pour actualiser la vue de l'image.
	 */
	public void refresh() {
		System.out.println("Refresh picture");
		ImageIcon img = new ImageIcon("pict/" + getCurrentPicture());
		img.setImage(scale(img.getImage(), 400, 300));
		pict.setIcon(img);
	}

	/**
	 * Redimensionne une image.
	 * 
	 * @param source
	 *            Image à redimensionner.
	 * @param width
	 *            Largeur de l'image cible.
	 * @param height
	 *            Hauteur de l'image cible.
	 * @return Image redimensionnée.
	 */
	public static Image scale(Image source, int width, int height) {
		/* On crée une nouvelle image aux bonnes dimensions. */
		BufferedImage buf = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		/* On dessine sur le Graphics de l'image bufferisée. */
		Graphics2D g = buf.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(source, 0, 0, width, height, null);
		g.dispose();

		/* On retourne l'image bufferisée, qui est une image. */
		return buf;
	}

}
