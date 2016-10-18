package ulaval_ex3;

import java.util.Objects;

public class Liste {
	private Object elemTete;
	private Liste queue;

	public Liste(){
		this.elemTete = null;
		this.queue = null;
	}

	public Liste(Object o){
		Objects.requireNonNull(o);
		this.queue = null;
		this.elemTete = o;
	}

	public boolean estVide(){
		return (this.elemTete  == null && this.queue == null);
	}

	public Liste ajouterEnTete(Object o){
		if(this.estVide()){
			Liste head = new Liste(o);
			return head;
		}
		Liste head = new Liste(o);
		head.queue = this;
		return head;
	}

	public Object tete(){
		return this.elemTete;
	}

	public Liste queue(){
		return this.queue;
	}

	public int longueur(){
		Liste curseur = this;
		int i=0;
		while(curseur != null){
			i++;
			curseur = curseur.queue;
		}
		return i;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Liste:");
		Liste curseur = this;
		for(int i=0; i< this.longueur();i++){
			sb.append(Objects.toString(curseur.elemTete))
			.append(", ");
			curseur = curseur.queue;
		}
		return sb.toString();
	}
	// EX2 :
	
	public boolean contient(Object o){
		Objects.requireNonNull(o);
		// utilisation d'une liste servant de curseur pour pouvoir itérer sur la liste contenu dans this.
		Liste curseur = this; 
		for(int i=0; i< this.longueur();i++){
			if(curseur.tete().equals(o)){
				return true;
			}
			curseur = curseur.queue();
		}
		return false;
	}

	public Liste ajouterEnQueue(Object o){
		Objects.requireNonNull(o);
		if(this.estVide()){
			Liste newListe = new Liste(o);
			return newListe;
		}
		Liste parcours = this;
		Liste newListe = new Liste(this.tete());
		Liste firtNewList = newListe;
		for(int i=0; i< this.longueur()-1;i++){
			parcours = parcours.queue();
			newListe.queue = new Liste(parcours.tete());
			newListe = newListe.queue();
		}
		newListe.queue = new Liste(o);
		newListe = firtNewList;
		return newListe;
	}
	
	public Liste concat(Liste l2){ // TODO en attente de confirmation du prof
		Objects.requireNonNull(l2);
		Liste newListe = new Liste(this.tete());
		Liste parcours = this;
		Liste firstNewListe = newListe;
		// première liste puis seconde liste.
		for(int i=0;i<this.longueur()-1;i++){
			parcours = parcours.queue();
			newListe.queue = new Liste(parcours.tete());
			newListe = newListe.queue();
		}
		if(l2.estVide()){
			newListe = firstNewListe;
			return newListe;
		}
		parcours = l2;
		for(int j=0;j<l2.longueur();j++){
			newListe.queue = new Liste(parcours.tete());
			newListe = newListe.queue();
			parcours = parcours.queue();
		}
		newListe = firstNewListe;
		return newListe;
	}
	
	public Liste inverse(){
		Liste invers = new Liste(this.tete());
		Liste courant = this;
		for(int i =0;i<this.longueur()-1;i++){
			courant = courant.queue();
			invers = invers.ajouterEnTete(courant.tete());
		}
		return invers;
	}
	
	public Liste copie(){
		Liste newListe = new Liste(this.tete());
		Liste curseur = this;
		Liste firstNewList = newListe;
		for(int i=0;i<this.longueur()-1;i++){
			curseur = curseur.queue();
			newListe.queue = new Liste(curseur.tete());
			newListe = newListe.queue();
		}
		newListe = firstNewList;
		return newListe;
	}
}
