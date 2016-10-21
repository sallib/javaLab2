package graphic.view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import graphic.controler.ViewerControler;
import graphic.model.ModelViewer;

public class ImagesList extends AbstractView implements ActionListener {
	private final ModelViewer model;
	private final GridBagLayout gbt = new GridBagLayout();
	private final GridBagConstraints gbc = new GridBagConstraints();
	private final JPanel content = new JPanel();
	private ArrayList<Picture> fileList = new ArrayList<>();
	private final Label label;
	private final List list;
	private final JButton displayButt;
	private String selectedItem;

	public ImagesList(ModelViewer model) {
		super(model);
		this.model = model;
		this.label = new Label("Liste des images :");
		this.list = new List();
		this.displayButt = new JButton("Afficher");
		displayButt.addActionListener(this);
		content.setLayout(gbt);
	}

	private void fillFileList() {
		fileList = model.getFileList();
		fileList.forEach(item -> {
			list.add(item.getPath());
		});
	}

	private void selectFirstItem() {
		if (list.getItemCount() > 0) {
			list.select(0);
			selectedItem = list.getItem(0);
		}
	}

	public int getSelectedItemIndex() {
		return list.getSelectedIndex();
	}

	public JPanel getImageList() {
		fillFileList();
		selectFirstItem();

		JPanel empty = new JPanel();
		addComponent(label, 0, 0, 1, 1);
		addComponent(list, 2, 0, 1, 4);
		addComponent(empty, 8, 0, 1, 2);
		addComponent(displayButt, 10, 0, 1, 1);
		return content;
	}

	private void addComponent(Component c, int ligne, int colonne, int largeur, int hauteur) {
		gbc.gridx = colonne;
		gbc.gridy = ligne;
		gbc.gridwidth = largeur;
		gbc.gridheight = hauteur;
		gbt.setConstraints(c, gbc);
		content.add(c);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		selectedItem = list.getSelectedItem();
		ViewerControler controler = new ViewerControler(this, model);
		controler.displaySelectedItem(selectedItem);
	}

}
