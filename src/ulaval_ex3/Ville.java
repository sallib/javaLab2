package ulaval_ex3;

import java.util.ArrayList;
import java.util.Objects;

public class Ville {
	private final String name;

	public Ville(String name){
		Objects.requireNonNull(name);
		this.name = name;
	}
	
	public boolean estJoignable(Ville destination) {
		for (Liste l : Map.map) {
				if (l.contient(destination.getNom()) && l.contient(this.getNom())) {
					return true;
				
			}
		}
		return false;
	}
	
	public String getNom() {
		return name;
	}
}
