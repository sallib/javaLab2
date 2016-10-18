package ulaval_ex1;

import java.util.LinkedList;

public class TestListe {
	public static void main(String[] args) {
		// 1- liste vide.
		Liste testVide = new Liste();
		System.out.println("test 1:\n vide:"+	testVide.estVide() + " | tete()"+ testVide.tete() +" | queue()"+
				testVide.queue() +" | longueur()"+ testVide.longueur());
				
		System.out.println(testVide.toString());
		// 1.1 liste vide avec ajout d'élément.
		Liste a  = testVide.ajouterEnTete(7);
		System.out.println(a.toString());
		System.out.println("test 1.1:\n vide:"+	a.estVide() + " | tete()"+ a.tete() +" | queue()"+
				a.queue() +" | longueur()"+ a.longueur());
		System.out.println(a.toString());
		Liste b = a.ajouterEnTete("youpi");
		System.out.println("test 1.2:\n vide:"+	b.estVide() + " | tete()"+ b.tete() +" | queue()"+
				b.queue() +" | longueur()"+ b.longueur());
		System.out.println(b.toString());
		
		Liste testObject = new Liste("toto");
		System.out.println("test 2:\n vide:"+	testObject.estVide() + " | tete()"+ testObject.tete() +" | queue()"+
				testObject.queue() +" | longueur()"+ testObject.longueur());
		System.out.println(testObject.toString());
	}
}
