import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
 
public class RechteckTest {
    
   private static Rechteck myRechteck;
    
   @BeforeClass
   public static void create() {
      // Test-Objekt erschaffen mit den Testwerten (L�nge: 10 und Breite: 20)
      myRechteck = new Rechteck(10, 20);
      System.out.println("Start!");
   }
    
   @Before
   public void vor() {
      // Diese Methode wird vor jedem Testfall ausgef�hrt
      System.out.println("vor Test");
   }
    
   @Test
   public void derTest1() {
      // Testfall 1: Pr�fung ob Umfangsberechnung stimmt
      System.out.println("Test1");
      Assert.assertTrue(60 == myRechteck.berechneUmfang());      
   }
   
   @Test
   public void dernegativTest1() {
      // Testfall 1: Pr�fung ob Umfangsberechnung stimmt
      System.out.println("Test1");
      Assert.assertFalse(80 == myRechteck.berechneUmfang());      
   }
    
   @Test
   public void derTest2() {
      // Testfall 2: Pr�fung ob Fl�cheninhaltsberechnung stimmt
      System.out.println("Test2");
      Assert.assertNotNull(myRechteck.berechneInhalt());   
   }
    
   @After
   public void nach() {
      // Diese Methode wird nach jedem Testfall ausgef�hrt z.B. um einen bestimmten Zustand zu erreichen
      System.out.println("nach Test");
   }
    
   @AfterClass
   public static void delete() {
      // Diese Methode wird am Ende der Test-Klasse ausgef�hrt z.B. zum aufr�umen oder l�schen von R�ckst�nden
      System.out.println("Test Ende!");
   }
}