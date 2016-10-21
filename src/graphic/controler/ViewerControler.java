package graphic.controler;

import java.util.ArrayList;

import graphic.model.PictureListModel;
import graphic.model.Picture;
import graphic.view.AbstractView;
import graphic.view.ImgListView;

public class ViewerControler {

	private final PictureListModel model;
	private final AbstractView view;

	public ViewerControler(AbstractView view, PictureListModel model) {
		this.model = model;
		this.view = view;
	}

	public void control() {
		ArrayList<Picture> fileList = model.getFileList();
		ImgListView il = (ImgListView) this.view;
		int index = il.getSelectedItemIndex();

		// TODO : something wrong
		//ImageView iv = (ImageView) this.view;
		//iv.setPicture(fileList.get(index));
		System.out.println("Changer affichage " + fileList.get(index).getPath());

	}

	public void displaySelectedItem(String selectedItem) {
		// ImageView iv = (ImageView) this.view;
		System.out.println("Afficher l'image " + selectedItem);
		control();

	}

}
