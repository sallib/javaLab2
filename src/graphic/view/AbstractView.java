package graphic.view;

import java.util.ArrayList;
import java.util.Objects;

import javax.swing.JPanel;

import graphic.controler.ViewerControler;
import graphic.model.PictureListModel;


public abstract class AbstractView extends JPanel {
	
	private ViewerControler vc;
	
	public AbstractView() {
		vc = null;
	}
	
/*	public ArrayList<String> getFileList(){ // TODO à traiter
		return fileList;
	}*/
	public abstract int getSelectedItemIndex();// TODO check that.
	
	public PictureListModel getModel(){
		return vc.getModel();
	}
	
	public String getCurrentPicture(){
		return vc.getCurrentPicture();
	}
	
	public int getCurrentIndex(){
		return vc.getCurrentIndex();
	}
	
	public void setViewerController(ViewerControler vc){
		Objects.requireNonNull(vc);
		this.vc = vc;
	}
	
	public void displaySelectedItem(String path){
		vc.changeCurrentImg(path);
	}
	
	
	
}
