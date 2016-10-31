package controler.ImagesVisualisation;

import java.util.ArrayList;
import java.util.Objects;

import javax.swing.JPanel;

import controler.ihm.ApplicationController;
import model.Picture;
import model.PictureListModel;

public class ViewerControler implements InterfaceCIV,InterfaceILV {

	private final CurrentImgView CIV;
	private final ImgListView ILV;
	private final PictureListModel model;
	private String selectedItem;
	private ArrayList<String> fileListPath;// TODO voir si possibilité de supprimer

	private ViewerControler(PictureListModel model) {
		this.model = model;
		this.CIV = new CurrentImgView(this);
		this.ILV = new ImgListView(this);
	}

	/**
	 * factoryMethod fini.
	 * 
	 * @param model
	 * @return
	 */
	public static ViewerControler create(PictureListModel model) {
		Objects.requireNonNull(model);
		ViewerControler vc = new ViewerControler(model);
		vc.ILV.fillFileList(vc.model.getFileList());
		vc.selectedItem = vc.model.getFileList().get(0).getPath();
		vc.fileListPath = vc.model.getAllPaths();
		vc.CIV.setDefaultImageView(vc.getCurrentPict());
		vc.updateHeaderInfos();
		return vc;
	}
	
	public void updateHeaderInfos() {
		CIV.setTextInfo(getCurrentPict().getTitle(), getCurrentPict().getDescription());
	}
	
	@Override
	public void openInfoView(){
		ApplicationController.activateInfoView(getCurrentPict());
	}
	
	public void addPicture(String newImg){
		Objects.requireNonNull(newImg);
		model.addPicture(newImg);
		fileListPath.add(newImg);
		ILV.addOneImgToList(newImg);
		changeCurrentImg(newImg);
	}

	@Override
	public void setPreviousPicture() {
		changeCurrentImg(fileListPath.get((getCurrentIndex() - 1) < 0 ? 
				0 : getCurrentIndex() - 1));
	}

	@Override
	public void setNextPicture() {
		changeCurrentImg(fileListPath.get((getCurrentIndex() + 1) > fileListPath.size()-1 ? 
				fileListPath.size()-1 : getCurrentIndex() + 1 ));
	}

	@Override
	public void setCurrentPicture(int index) {
		changeCurrentImg(fileListPath.get((index >= 0 && index < fileListPath.size())?
				index : 0));
	}
	
	public JPanel getImageList() {
		return ILV.getImgList();
	}

	public JPanel getImageView() {
		return CIV.getImageView();
	}

	private PictureListModel getModel() {
		return model;
	}

	private Picture getCurrentPict(){
		return getModel().getFileList().get(getCurrentIndex()); // TODO refactor possible en stockant un champs de classe Picture current
	}

	private void changeCurrentImg(String item) {
		selectedItem = item;
		updateHeaderInfos();
		CIV.refresh(item,getCurrentIndex(),fileListPath.size());
		ILV.refresh(getCurrentIndex());
	}

	private int getCurrentIndex() {
		return fileListPath.indexOf(selectedItem);
	}
}
