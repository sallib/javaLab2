package graphic.model;

import java.util.ArrayList;
import java.util.Observer;

import graphic.observer.Observable;

public abstract class AbstractModel implements Observable { // UNUSED
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	
	public void addObserver(java.util.Observer obs) {
		this.listObserver.add(obs);
	}
	public ArrayList<String> getFileList;
	
	

}
