package controler.ihm;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

/**
 * Vue de la fenetre d'application : affiche la fenetre.
 */
class WindowView extends JFrame  {
	private JPanel container = new JPanel();
	
	private WindowView() {
		this.setTitle("My awesome Canadian pictures");
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// On prévient notre JFrame que notre JPanel sera son content pane
		this.setContentPane(container);
		// On définit le layout à utiliser sur le content pane
		this.setLayout(new BorderLayout());
		this.setVisible(true);
	}
	
	static WindowView create(JPanel imgList,JPanel imgView,JMenuBar menu){
		WindowView w = new WindowView();
		w.getContentPane().add(imgView, BorderLayout.CENTER);
		w.getContentPane().add(imgList, BorderLayout.EAST);
		w.getContentPane().add(menu, BorderLayout.NORTH);
		w.setVisible(true);
		return w;
	}

	void close(){
		this.setVisible(false);
		this.dispose();
	}

}