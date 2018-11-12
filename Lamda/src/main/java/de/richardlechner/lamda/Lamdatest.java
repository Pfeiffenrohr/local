package de.richardlechner.lamda;


import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.function.Consumer;

public class Lamdatest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Lamdatest lamda = new Lamdatest();
   lamda.lamda();
       

    }

    public void lamda ()
    {
        List <String> myList = Arrays.asList("element1","element2","element3");
        
        Vector vec = new Vector();
        vec.add("a");
        vec.add(("b"));
        
        vec.forEach(elem -> System.out.println(elem)
                
                );
       /* myList.forEach(new Consumer<String>() {
            
            public void accept(String element) {
               System.out.println(element);
            }
         });*/
        
       /* myList.forEach((element) -> 
        {
        element = element + "...";    
        System.out.println(element) ; 
        });*/
    }
}
