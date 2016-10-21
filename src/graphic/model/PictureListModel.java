package graphic.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observer;

public class PictureListModel extends AbstractModel {
	private final ArrayList<Picture> fileList = new ArrayList<>();
	private final File directory;

	private PictureListModel(File directory) {
		this.directory = directory;
	}

	public static PictureListModel createViewer(File directory) {
		PictureListModel viewer = new PictureListModel(directory);
		viewer.loadPictures();
		return viewer;
	}

	private void loadPictures() {
		Arrays.asList(this.directory.list()).forEach(f -> {
			fileList.add(new Picture(f));
		});
	}

	public ArrayList<Picture> getFileList() {
		return this.fileList;
	}

	@Override
	public void addObserver(Observer obs) {
		// TODO Auto-generated method stub

	}
}
