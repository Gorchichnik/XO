import java.util.Scanner;

public class Field {           
      int dim;
      
      void viewarr(String[][] arr){
                 dim = arr.length-1;
                 for(int x=0; x<=dim; x++){
			System.out.print("\r\n");
                    for(int y=0; y<=dim; y++){                     
                       System.out.print(" ["+arr[x][y]+"] "); 
                    }
                    System.out.print("\r\n");
                 }   
      }    

      void viewarr(Integer[][] arr){
                 dim = arr.length-1;
                 for(int x=0; x<=dim; x++){
			System.out.print("\r\n");
                    for(int y=0; y<=dim; y++){                     
                       System.out.print(" ["+arr[x][y]+"] "); 
                    }
                    System.out.print("\r\n");
                 }   
      }    

}