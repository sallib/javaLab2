package graphic.view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Menu extends JPanel {

	public JMenuBar getMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("Fichier");
		JMenu infos = new JMenu("Infos");
		JMenuItem file_add = new JMenuItem("Ajouter");
		JMenuItem file_quit = new JMenuItem("Quitter");
		JMenuItem info_more = new JMenuItem("A propos");

		file.add(file_add);
		file.add(file_quit);
		infos.add(info_more);

		menuBar.add(file);
		menuBar.add(infos);
		return menuBar;
	}
}
