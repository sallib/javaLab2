package ulaval_ex2;

public class Test {

	public static void main(String[] args) {
		Liste test = new Liste();
		Liste testeQueueVide = test.ajouterEnQueue("okay");
		System.out.println(testeQueueVide);
		Liste testAdd = test.ajouterEnTete("tata");
		Liste testAdd2 = testAdd.ajouterEnTete("toto");
		System.out.println("testAdd2"+ testAdd2);
		System.out.println("contient" + testAdd2.contient("toto"));
		System.out.println("contient" + testAdd2.contient("tata"));
		Liste testAdd3 = testAdd.ajouterEnTete(3);
		System.out.println("contient" + testAdd3.contient(3));
		String chaine = "blop";
		Liste testAdd4 = testAdd.ajouterEnTete(chaine);
		System.out.println("contient" + testAdd4.contient(chaine));
		Liste testAddQueue = test.ajouterEnQueue("fin");
		System.out.println("testAddQueue: " + testAddQueue);
		System.out.println("test: " + test);
		System.out.println("testAdd: " + testAdd);
		
		Liste plein = new Liste(1);
		Liste plein2 = plein.ajouterEnQueue("top");
		System.out.println(plein2);
		
		Liste toQueue = testAdd3.ajouterEnQueue("final");
		System.out.println("toqueue:"+ toQueue);
		System.out.println("testAdd3:"+ testAdd3);
		
		Liste concat = testAdd2.concat(testAdd3);
		System.out.println("liste1 Add2"+ testAdd2);
		System.out.println("liste1 Add3"+ testAdd3);
		System.out.println("concat"+ concat);
		
		Liste invers = concat.inverse();
		System.out.println("inverse: source"+ concat);
		System.out.println("inverse: dest"+ invers);
		
		Liste copie = invers.copie();
		System.out.println("copie"+ copie);
	}

}
