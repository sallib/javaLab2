package controler.ihm;

import java.util.Objects;

import controler.ImagesVisualisation.ViewerControler;
import model.Picture;
import model.PictureListModel;

public class ApplicationController implements InterfaceInfoView,InterfaceMenuView {
	private MenuView menu;
	private WindowView window;
	private static InfosView infos;
	private ViewerControler vc;
	
	public ApplicationController(PictureListModel model){
		Objects.requireNonNull(model);
		vc = ViewerControler.create(model);
		menu = new MenuView(this);
		window = WindowView.create(vc.getImageList(),vc.getImageView(),menu.getMenu());
		infos = new InfosView(this);
	}

	@Override
	public void updateHeaderInfos() {
		vc.updateHeaderInfos();
	}
	
	public static void activateInfoView(Picture current){
		infos.init(current);
	}

	@Override
	public void addPicture(String newImg) {
		vc.addPicture(newImg);
	}

	@Override
	public void closeWindow() {
		window.close();
	}
}
