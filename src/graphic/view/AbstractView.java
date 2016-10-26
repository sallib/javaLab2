package graphic.view;

import java.awt.List;
import java.util.Objects;

import javax.swing.JPanel;

import graphic.controler.ViewerControler;
import graphic.model.PictureListModel;

public abstract class AbstractView extends JPanel {

	private ViewerControler vc;
	private final PictureListModel model;
	private final List list;
	
	public AbstractView(PictureListModel model) {
		vc = null;
		this.model = model;
		this.list = new List();
	}


	public PictureListModel getModel() {
		return model; //vc.getModel();
	}

	public String getCurrentPicture() {
		return vc.getCurrentPicture();
	}

	public int getCurrentIndex() {
		return vc.getCurrentIndex();
	}

	public List getList(){
		return list;
	}
	
	
	public void setViewerController(ViewerControler vc) {
		Objects.requireNonNull(vc);
		this.vc = vc;
	}

	public void displaySelectedItem(String item) {
		vc.changeCurrentImg(item);
	}


}
