import java.util.Scanner;
import java.io.IOException;

public class Field {           
      int dim;
	
      
      
      void viewarr(String[][] arr) throws IOException, InterruptedException {
		 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                 System.out.println(" ");
	         System.out.println(" ");
                 System.out.println("       THE XO GAME "); 
		 System.out.println(" ");
                 dim = arr.length-1;
                 for(int x=0; x<=dim; x++){
			System.out.print("\r\n");
			System.out.print("     ");
                    for(int y=0; y<=dim; y++){                     
                       System.out.print(" ["+arr[x][y]+"] "); 
                    }
                    System.out.print("\r\n");
                 }   
      }       

}