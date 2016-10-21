package graphic.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import graphic.model.AbstractModel;
import graphic.model.Picture;

public class CurrentImgView extends AbstractView {

	private JLabel pict; 
	

	public CurrentImgView(AbstractModel model) {
		super(model);
	}
	
	public JPanel getImageView() {
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());

		content.add(getHeadPanel(), BorderLayout.NORTH);
		content.add(getCenter(), BorderLayout.CENTER);
		content.add(getFootPanel(), BorderLayout.SOUTH);

		return content;
	}

	public void setPicture(Picture picture){
		System.out.println("SetPicture");
		pict.setIcon(new ImageIcon("pict/"+picture.getPath()));
	}
	
	private JPanel getCenter() {
		JPanel panel = new JPanel();
		pict = new JLabel(new ImageIcon("pict/RedSquirrel.jpg"));
		panel.setLayout(new BorderLayout());
		panel.add(pict, BorderLayout.CENTER);
		return panel;
	}

	private JPanel getFootPanel() {
		JPanel foot = new JPanel();
		GridLayout gridLayout = new GridLayout();
		gridLayout.setColumns(2);
		foot.setLayout(gridLayout);

		JButton previousButt = new JButton("Précédent");
		JButton nextButt = new JButton("Next");
		foot.add(previousButt);
		foot.add(nextButt);
		return foot;
	}

	private JPanel getHeadPanel() {
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

}
