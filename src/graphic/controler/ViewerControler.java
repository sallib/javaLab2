package graphic.controler;

import java.util.ArrayList;
import java.util.Objects;

import javax.swing.JPanel;

import graphic.model.PictureListModel;
import graphic.view.CurrentImgView;
import graphic.view.ImgListView;

public class ViewerControler {

	private final CurrentImgView CIV;
	private final ImgListView ILV;
	private final PictureListModel model;
	private String selectedItem;
	private ArrayList<String> fileListPath;

	private ViewerControler(PictureListModel model) {
		this.model = model;
		this.CIV = CurrentImgView.create();
		this.ILV = ImgListView.create();
	}
	
	/**
	 * factoryMethod fini.
	 * @param model
	 * @return
	 */
	public static ViewerControler create(PictureListModel model){
		Objects.requireNonNull(model);
		ViewerControler vc = new ViewerControler(model);
		vc.setLinkInAbstractView();
		vc.ILV.fillFileList();
		vc.selectedItem = vc.model.getFileList().get(0).getPath();
		vc.fileListPath = vc.model.getAllPaths();
		vc.CIV.setImageView();
		return vc;
	}
	
	public PictureListModel getModel(){
		return model;
	}
	
	private void setLinkInAbstractView(){
		CIV.setViewerController(this);
		ILV.setViewerController(this);
	}
	
	public JPanel getImageList(){
		return ILV.getImgList();
	}
	
	public JPanel getImageView(){
		return CIV.getImageView();
	}

	public void control() {
		/*ArrayList<Picture> fileList = model.getFileList();
		int index = this.view.getSelectedItemIndex();*/

		// TODO : something wrong
		//ImageView iv = (ImageView) this.view;
		//iv.setPicture(fileList.get(index));
		//System.out.println("Changer affichage " + fileList.get(index).getPath());
		System.out.println("Changer affichage " + selectedItem);

	}

	public void displaySelectedItem(String selectedItem) {
		// ImageView iv = (ImageView) this.view;
		System.out.println("Afficher l'image " + selectedItem); // TODO quand appelé rafraichit les 2 classes.
		control();

	}
	
	public void changeCurrentImg(String path){
		selectedItem = path;
		CIV.refresh();
		ILV.refresh();
	}
	
	public String getCurrentPicture(){
		System.out.println("getCurrentPicture:"+selectedItem);
		return selectedItem;
	}
	
	public int getCurrentIndex(){
		return fileListPath.indexOf(selectedItem);
	}

}
