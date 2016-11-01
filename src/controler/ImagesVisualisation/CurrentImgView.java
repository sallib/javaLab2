package controler.ImagesVisualisation;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
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
import javax.swing.SwingConstants;

import model.Picture;

/**
 * Vue de l'image courante.
 * Gere l'affichage et la capture des interactions avec l'utilisateur.
 * Affiche l'image courante et ses informations. Permet de naviguer vers l'image suivante/ precedente.
 * Permet d'editer les informations de l'image courante.
 * Renvoie les commandes lancees par l'utilisateur au ViewerController pour repercuter sur
 * toutes les vues l'action realisees.
 *
 */
class CurrentImgView implements ActionListener {

	private JLabel pict;
	private final JPanel content;
	private JPanel imgPanel;
	private JButton previousButt;
	private JButton nextButt;
	private JLabel title;
	private JLabel desc;
	private InterfaceCIV iciv;

	/**
	 * Constructeur de la vue d'affichage de l'image courante et de ses informations.
	 * @param InterfaceCIV  donne la reference vers le ViewerController pour synchroniser les actions de l'utilisateur
	 * pour toutes les vues.
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
			break;
		case "Next":
			iciv.setNextPicture();
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
	 */
	void setDefaultImageView(Picture currentImg) {
		content.setLayout(new BorderLayout());
		content.add(getHeadPanel(), BorderLayout.NORTH);
		content.add(getCenter(currentImg), BorderLayout.CENTER);
		content.add(getFootPanel(), BorderLayout.SOUTH);
	}

	/**
	 * Getter for this JPanel.
	 * @return JPanel contenant la vue.
	 */
	JPanel getImageView() {
		return content;
	}
	
	/**
	 * Mise à jour des informations sur l'image affichee.
	 * @param String titre de l'image mis a jour
	 * @param String description de l'image mis a jour
	 */
	void setTextInfo(String title, String descr){
		this.title.setText(title);
		this.desc.setText(descr);
	}

	/**
	 * Methode pour actualiser la vue de l'image. Actualise aussi la disponibilité des boutons de navigation.
	 * @param String	chemin de l'image courante
	 * @param int 		index de l'image courante dans la liste
	 * @param int 		taille de la liste d'images.
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
	 * @param Picture Image courante a imprimer
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
	 * @return JPanel boutons next/previous.
	 */
	private JPanel getFootPanel() {
		JPanel foot = new JPanel();
		GridLayout gridLayout = new GridLayout();
		gridLayout.setColumns(2);
		foot.setLayout(gridLayout);

		previousButt = new JButton("Précédent");
		previousButt.setIcon(new ImageIcon("icon/back.png")); //Icon du bouton 
		previousButt.setActionCommand("Previous");
		nextButt = new JButton("Suivant");
		nextButt.setIcon(new ImageIcon("icon/next.png")); //Icon du bouton
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
	 * @return  JPanel infos de l'image
	 */
	private JPanel getHeadPanel() {
		JPanel head = new JPanel(new GridLayout(3, 3));
		JPanel headBorderLayout = new JPanel(new BorderLayout());
		JPanel east = new JPanel(new BorderLayout());
		headBorderLayout.setBorder(BorderFactory.createEtchedBorder());
		JLabel titleLabel = new JLabel("Titre : ");
		JLabel descLabel = new JLabel("Description : ");
		title = new JLabel();
		desc = new JLabel();
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		descLabel.setHorizontalAlignment(SwingConstants.CENTER);
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
