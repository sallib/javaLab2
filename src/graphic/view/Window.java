package graphic.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import graphic.controler.ViewerControler;
import graphic.model.PictureListModel;

public class Window extends JFrame implements java.util.Observer {
	// Instanciation d'un objet JPanel
	private JPanel container = new JPanel();
	private ViewerControler vc;

	public Window(PictureListModel model) {
		this.vc = ViewerControler.create(model);
		this.setTitle("My awesome Canadian pictures");
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		model.addObserver(this);
		
		// Définition de sa couleur de fond
		container.setBackground(Color.ORANGE);
		// On prévient notre JFrame que notre JPanel sera son content pane
		this.setContentPane(container);
		// On définit le layout à utiliser sur le content pane
		this.setLayout(new BorderLayout());
		initImageViewer();
		initListImages();
		initMenu();
		this.setVisible(true);
		
	}

	private void initListImages() {
		this.getContentPane().add(vc.getImageList(), BorderLayout.EAST);
		this.setVisible(true);
	}

	private void initMenu() {
		Menu menu = new Menu();
		this.getContentPane().add(menu.getMenu(), BorderLayout.NORTH);
		this.setVisible(true);
	}


	private void initImageViewer() {
		this.getContentPane().add(vc.getImageView(), BorderLayout.CENTER);
		this.setVisible(true);
	}

	@Override
	public Insets getInsets() {
		return new Insets(10, 10, 10, 10);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}
}