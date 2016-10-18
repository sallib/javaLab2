package ulaval_ex1;

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
		System.out.println("head"+ head);
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
}
