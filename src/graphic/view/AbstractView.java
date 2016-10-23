package graphic.view;

import java.util.Objects;

import javax.swing.JPanel;

import graphic.controler.ViewerControler;
import graphic.model.PictureListModel;

public abstract class AbstractView extends JPanel {

	private ViewerControler vc;
	private final PictureListModel model;

	public AbstractView(PictureListModel model) {
		vc = null;
		this.model = model;
	}

	// public abstract int getSelectedItemIndex();// TODO check that.

	public PictureListModel getModel() {
		return model; //vc.getModel();
	}

	public String getCurrentPicture() {
		return vc.getCurrentPicture();
	}

	public int getCurrentIndex() {
		return vc.getCurrentIndex();
	}

	public void setViewerController(ViewerControler vc) {
		Objects.requireNonNull(vc);
		this.vc = vc;
	}

	public void displaySelectedItem(String item) {
		vc.changeCurrentImg(item);
	}

	public ViewerControler getControler(){
		return vc;
	}
}
