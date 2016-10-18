package ulaval_ex2;

public class TestListeSandy {
	public static void main(String[] args)  {
		Liste l1 = new Liste(1);
		// l1 = l1.ajouterEnTete(2);
		// l1 = l1.ajouterEnTete(3);
		Liste ll2 = l1.ajouterEnTete(2);
		Liste ll3 = ll2.ajouterEnTete(2);
		System.out.println("Longueur : " + ll3.longueur()); // 0
		System.out.println("Affiche : " + ll3); //
		
		Liste l2 = ll3.ajouterEnQueue(4);
		System.out.println("Longueur : " + l2); // 0
		System.out.println("Affiche : " + l2); //
		System.out.println("Affiche : " + ll3); //

	}
}
