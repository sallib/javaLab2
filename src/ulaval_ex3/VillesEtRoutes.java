package ulaval_ex3;

public class VillesEtRoutes {
	public static void main(String args[])
	{
	Ville quebec = new Ville("Quebec");
	Ville montreal = new Ville("Montreal");
	Ville drummondville = new Ville("Drummondville");
	Ville sherbrooke = new Ville("Sherbrooke");
	Route a40 = new Route("A40", quebec, montreal);
	Route a20 = new Route("A20", quebec, drummondville);
	System.out.println("Drummondville -> Montreal ?: " +
	drummondville.estJoignable(montreal));
	System.out.println("Quebec -> Sherbrooke ?: " +
	quebec.estJoignable(sherbrooke));
	}
}
