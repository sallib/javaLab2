package ulaval_ex3;

import java.util.Objects;

public class Route implements Map{
	private final String name;
	
	public Route(String name, Ville source, Ville dest){
		Objects.requireNonNull(name);
		Objects.requireNonNull(source);
		Objects.requireNonNull(dest);
		this.name = name;
		ajouterChemin(source, dest);
	}
	
	public void ajouterChemin(Ville v1, Ville v2) {
		boolean in = false;
		Liste tmp;
		for (int i = 0; i < map.size(); i++) {
			if (map.get(i).contient(v1.getNom())) {
				if (map.get(i).contient(v2.getNom())) {
					// Do nothing => la route existe déja
				}
				tmp = map.get(i);
				tmp = tmp.ajouterEnQueue(v2.getNom());
				map.set(i, tmp);
				in = true;
			} else if (map.get(i).contient(v2.getNom())) {
				tmp = map.get(i);
				tmp = tmp.ajouterEnTete(v1.getNom());
				map.set(i, tmp);
				in = true;
			}
		}

		if (!in) {
			Liste newWay = new Liste(v1.getNom()).ajouterEnQueue(v2.getNom());
			Map.map.add(newWay);
		}
	}
}
