package graphic.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import graphic.model.PictureListModel;

public class Menu extends AbstractView implements ActionListener {
	private final Window window;
	private final JMenuBar menuBar;
	private final JMenu file;
	private final JMenu infos;
	private final JMenuItem file_add;
	private final JMenuItem file_quit;
	private final JMenuItem info_more;
	
	public Menu(PictureListModel model, Window window) {
		super(model);
		this.window = window;
		menuBar = new JMenuBar();
		file = new JMenu("Fichier");
		infos = new JMenu("Infos");
		file_add = new JMenuItem("Ajouter");
		file_quit = new JMenuItem("Quitter");
		info_more = new JMenuItem("A propos");
	}

	public JMenuBar getMenu() {
		info_more.addActionListener(this);
		info_more.setActionCommand("info");
		file_add.addActionListener(this);
		file_add.setActionCommand("file");
		file_quit.addActionListener(this);
		file_quit.setActionCommand("quit");
		file.add(file_add);
		file.add(file_quit);
		infos.add(info_more);
		menuBar.add(file);
		menuBar.add(infos);
		return menuBar;
	}

	private void chooseFile() {
		JFileChooser chooser = new JFileChooser(new File("."));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG; JPEG; GIF; PNG", "jpeg", "jpg", "png",
				"gif");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String newImg = chooser.getSelectedFile().getPath();
			
			System.out.println("You chose to open this file: " + newImg);
			//TODO : Il faut ajouter le nouvel élément a la List de l'interface
			// Et sélextionner l'item pour pouvoir afficher les informations titre / desc
		//	displaySelectedItem(newImg);  -> fait tout planter car VC est null à ce moment la. s
			getModel().addPicture(newImg);
		
			// TODO : L'ajout ne se passe pas bien !!!
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case "info":
			String infos = "Interface développé par Jonathan Garnier et Sandy Allibert\nUniversité Laval\nOctobre 2016";
			JOptionPane.showMessageDialog(null, infos, "Information", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "file":
			chooseFile();
			break;
		case "quit":
			window.close();
			break;
		}

	}
}
