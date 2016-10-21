package graphic.controler;

import java.util.ArrayList;

import graphic.model.ModelViewer;
import graphic.view.AbstractView;
import graphic.view.ImageView;
import graphic.view.ImagesList;
import graphic.view.Picture;

public class ViewerControler {

	private final ModelViewer model;
	private final AbstractView view;

	public ViewerControler(AbstractView view, ModelViewer model) {
		this.model = model;
		this.view = view;
	}

	public void control() {
		ArrayList<Picture> fileList = model.getFileList();
		ImagesList il = (ImagesList) this.view;
		int index = il.getSelectedItemIndex();
		
		//TODO : something wrong
		ImageView iv = (ImageView) this.view;
		iv.setPicture(fileList.get(index));
		System.out.println("Changer affichage " + fileList.get(index).getPath());

	}

	public void displaySelectedItem(String selectedItem) {
		ImageView iv = (ImageView) this.view;
	
		control();
		
	}

}
