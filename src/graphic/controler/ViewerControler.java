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
		this.CIV = new CurrentImgView(model);
		this.ILV = new ImgListView(model);
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
	
	public JPanel getImageList(PictureListModel model){
		return ILV.getImgList();
	}
	
	public JPanel getImageView(){
		return CIV.getImageView();
	}

	
	public void changeCurrentImg(String path){
		System.out.println("Change Current Img");
		selectedItem = path;
		CIV.refresh();
		ILV.refresh();
	}
	
	public String getCurrentPicture(){
		return selectedItem;
	}
	
	public int getCurrentIndex(){
		return fileListPath.indexOf(selectedItem);
	}

}
