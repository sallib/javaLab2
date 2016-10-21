package graphic.view;

import java.util.ArrayList;

import javax.swing.JPanel;

import graphic.model.AbstractModel;

public abstract class AbstractView extends JPanel {

	private final AbstractModel model;
	private ArrayList<String> fileList = new ArrayList<>();
	
	public AbstractView(AbstractModel model) {
		this.model = model;
	}
	
	public ArrayList<String> getFileList(){
		return fileList;
	}
	
	
	
}
