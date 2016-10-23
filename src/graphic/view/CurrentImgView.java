package graphic.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	private CurrentImgView() {
		super();
		this.content = new JPanel();
		this.imgPanel = new JPanel();
	}
	
	public static CurrentImgView create(){
		CurrentImgView CIV = new CurrentImgView();
		return CIV;
	}
	
	/**
	 * Methode d'affichage de l'image active centrale
	 * @return
	 */
	public void setImageView() {
		content.setLayout(new BorderLayout());
		content.add(getHeadPanel(), BorderLayout.NORTH);
		content.add(getCenter(), BorderLayout.CENTER);
		content.add(getFootPanel(), BorderLayout.SOUTH);
	}
	public JPanel getImageView(){
		return content;
	}
	
	public void setViewerController(ViewerControler vc){
		super.setViewerController(vc);
	}
	/**
	 * initialisateur de l'image centrale.
	 * @return
	 */
	private JPanel getCenter() {
		pict = new JLabel(new ImageIcon("pict/"+ getCurrentPicture())); 
		imgPanel.setLayout(new BorderLayout());
		imgPanel.add(pict, BorderLayout.CENTER);
		return imgPanel;
	}

	/**
	 * initialisateur des boutons de navigation dans la liste.
	 * @return
	 */
	private JPanel getFootPanel() { // TODO ajouter les listener.
		JPanel foot = new JPanel();
		GridLayout gridLayout = new GridLayout();
		gridLayout.setColumns(2);
		foot.setLayout(gridLayout);

		JButton previousButt = new JButton("Précédent");
		JButton nextButt = new JButton("Next");
		previousButt.addActionListener(this);
		foot.add(previousButt);
		foot.add(nextButt);
		return foot;
	}

	/**
	 * initialisateur du bandeau de visualisation des infos image + bouton modification
	 * @return
	 */
	private JPanel getHeadPanel() { // TODO listener à implanter
		JPanel head = new JPanel();
		head.setLayout(new GridLayout(3, 3));

		Label title = new Label("Titre : ");
		Label desc = new Label("Description : ");
		JButton modifyButt = new JButton("Modifier");
		head.add(title);
		head.add(desc);
		head.add(modifyButt);
		return head;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// previous only for now
		//getPreviousImg();
		super.displaySelectedItem("FlyingSquirrel.jpg");// à remplacer par : //getPreviousImg();
	}

	/**
	 * Methode pour actualiser la vue de l'image.
	 */
	public void refresh(){
		System.out.println("SetPicture");
		pict.setIcon(new ImageIcon("pict/"+ getCurrentPicture())); 
	}
	@Override
	public int getSelectedItemIndex() { // TODO useless?
		// TODO Auto-generated method stub
		return 0;
	}

}
