package de.richardlechner.lamda;

public class Lamddatest2 {
	public static void main (String[] args) {
		Mathe addieren = (int a, int b) -> a+b;
		Mathe mult = (a, b) -> {return a*b;};
		Gibaus gibaus2 =() -> 2;
		
	    System.out.println(addieren.fkt(2, 5));
	    System.out.println(mult.fkt(2, 5));
	    System.out.println(gibaus2.gib());
	    
	}
interface Mathe {
	int fkt(int a, int b);
}

interface Gibaus{
	int gib ();
}
}
