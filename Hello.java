import java.io.IOException;
import java.util.Scanner;

public class Hello {
      
      Scanner s = new Scanner(System.in);
      short d;
      Way w = new Way();

      void hello(){

	  

           System.out.println(" "); 
           System.out.println("Welcome to the XO game");
           System.out.println(" ");
           System.out.println("Choose dimention of the field...");
           System.out.println(" ");
           
           System.out.println("3, 4, 5 or 6?");
           System.out.println(" ");
           d = s.nextShort();
           System.out.println(" ");
           w.way(d);
           
      }
}