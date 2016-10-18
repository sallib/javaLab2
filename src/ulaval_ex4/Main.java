package ulaval_ex4;

public class Main {

}

interface Truc {
public int methodeA();
public void methodeB();
public int methodeC();
}
class Chose implements Truc {
public int methodeA() {
return 2;
}
public void methodeB() {
System.out.println(methodeA());
}
public int methodeC(){
	return 1;
}
}
class Exercice5 {
public static void main(String[] arg) {
Chose b = new Chose();
b.methodeB();
}
}
