package controler.ImagesVisualisation;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Picture;

class CurrentImgView implements ActionListener {

	private JLabel pict;
	private final JPanel content;
	private JPanel imgPanel;
	private JButton previousButt;
	private JButton nextButt;
	private Label title;
	private Label desc;
	private InterfaceCIV iciv;

	/**
	 * Class constructor
	 * @param iciv ViewerController reference for specific methods calls.
	 */
	CurrentImgView(InterfaceCIV iciv) {
		Objects.requireNonNull(iciv);
		this.iciv = iciv;
		this.content = new JPanel();
		this.imgPanel = new JPanel();
	}
	
	@Override
	public void actionPerformed(ActionEvent action) {
		String actionCmd = action.getActionCommand();
		switch (actionCmd) {
		case "Previous":
				iciv.setPreviousPicture();
				nextButt.setEnabled(true); // Réactive le bouton next TODO à supprimer ?
			break;
		case "Next":
			iciv.setNextPicture();
				previousButt.setEnabled(true); // Réactive le bouton previous TODO à supprimer ?
			break;
		case "Modify":
			iciv.openInfoView();
		default: // Never happen
			break;
		}
	}

	/**
	 * Methode d'affichage de l'image active centrale
	 * @param Picture current image to print.
	 * @return
	 */
	void setDefaultImageView(Picture currentImg) {
		content.setLayout(new BorderLayout());
		content.add(getHeadPanel(), BorderLayout.NORTH);
		content.add(getCenter(currentImg), BorderLayout.CENTER);
		content.add(getFootPanel(), BorderLayout.SOUTH);
	}

	/**
	 * Getter for this JPanel.
	 * @return
	 */
	JPanel getImageView() {
		return content;
	}
	
	/**
	 * Mise à jour des informations sur l'image affichee.
	 * @param String image's title updated.
	 * @param String image's description updated.
	 */
	void setTextInfo(String title, String descr){
		this.title.setText(title);
		this.desc.setText(descr);
	}

	/**
	 * Methode pour actualiser la vue de l'image. Actualise aussi la disponibilité des boutons de navigation.
	 * @param String	path of the current picture
	 * @param int 		index of the current picture in the list.
	 * @param int 		size of the picture's list.
	 */
	void refresh(String currentPicture,int index,int size) {
		ImageIcon img = new ImageIcon(currentPicture);
		img.setImage(scale(img.getImage(), 400, 300));
		pict.setIcon(img);
		if (index == 0) {
			previousButt.setEnabled(false);
			nextButt.setEnabled(true);
		} else if (index  == size - 1) {
			previousButt.setEnabled(true);
			nextButt.setEnabled(false);
		}else{
			previousButt.setEnabled(true);
			nextButt.setEnabled(true);
		}
	}

	/**
	 * initialisateur de l'image centrale.
	 * @param Picture current picture to print.
	 * @return
	 */
	private JPanel getCenter(Picture currentImg) {
		imgPanel.setBorder(BorderFactory.createEtchedBorder());
		ImageIcon img = new ImageIcon(currentImg.getPath());
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
	private JPanel getFootPanel() {
		JPanel foot = new JPanel();
		GridLayout gridLayout = new GridLayout();
		gridLayout.setColumns(2);
		foot.setLayout(gridLayout);

		previousButt = new JButton("Précédent");
		previousButt.setIcon(new ImageIcon("icon/back.png"));
		previousButt.setActionCommand("Previous");
		nextButt = new JButton("Suivant");
		nextButt.setIcon(new ImageIcon("icon/next.png"));
		nextButt.setActionCommand("Next");
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
		JPanel head = new JPanel(new GridLayout(3, 3));
		JPanel headBorderLayout = new JPanel(new BorderLayout());
		JPanel east = new JPanel(new BorderLayout());
		headBorderLayout.setBorder(BorderFactory.createEtchedBorder());
		Label titleLabel = new Label("Titre : ");
		Label descLabel = new Label("Description : ");
		title = new Label();
		desc = new Label();
		JButton modifyButt = new JButton("Modifier");
		modifyButt.setActionCommand("Modify");
		modifyButt.addActionListener(this);
		head.add(titleLabel);
		head.add(title);
		head.add(descLabel);
		head.add(desc);
		headBorderLayout.add(head, BorderLayout.CENTER);
		east.add(new JPanel(), BorderLayout.NORTH);
		east.add(modifyButt, BorderLayout.CENTER);
		east.add(new JPanel(), BorderLayout.SOUTH);
		headBorderLayout.add(east, BorderLayout.EAST);
		return headBorderLayout;
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
	private static Image scale(Image source, int width, int height) {
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
