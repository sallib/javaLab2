package graphic.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observer;

import graphic.view.Picture;

public class ModelViewer extends AbstractModel {
	private final ArrayList<Picture> fileList = new ArrayList<>();
	private final File directory;

	private ModelViewer(File directory) {
		this.directory = directory;
	}

	public static ModelViewer createViewer(File directory) {
		ModelViewer viewer = new ModelViewer(directory);
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
