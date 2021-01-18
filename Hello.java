import java.io.IOException;
import java.util.Scanner;

public class Hello {
      
      Scanner s = new Scanner(System.in);
      short d;
      Way w = new Way();
      Ai ai = new Ai();
      boolean begining = true;

      void coolPrinting(String s){
              String str = new String(s);
              for(int i = 0; i < str.length(); i++){
                    if(Character.isSpaceChar(str.charAt(i))){
				try
			{
   				 Thread.sleep(ai.getRandomInt(100,300));
			}
			catch(InterruptedException ex)
			{
				 Thread.currentThread().interrupt();
			}
			System.out.print(str.charAt(i));
			}
			else{
			try
			{
   				 Thread.sleep(ai.getRandomInt(50,100));
			}
			catch(InterruptedException ex)
			{
				 Thread.currentThread().interrupt();
			}
			System.out.print(str.charAt(i));
			}
		}
	}

      void hello(){

if(begining){

           System.out.println(" "); 
           coolPrinting("Welcome to the XO game");
           System.out.println(" "); 
		try
		{
 		   Thread.sleep(1600);
		}
		catch(InterruptedException ex)
		{
 		   Thread.currentThread().interrupt();
		}
           System.out.println(" ");
           coolPrinting("Choose dimention of the field...");
           System.out.println(" ");
           try
		{
 		   Thread.sleep(700);
		}
		catch(InterruptedException ex)
		{
 		   Thread.currentThread().interrupt();
		}
           System.out.println(" ");
           coolPrinting("3, 4, 5 or 6?");
	   try
		{
 		   Thread.sleep(400);
		}
		catch(InterruptedException ex)
		{
 		   Thread.currentThread().interrupt();
		}
	   System.out.print("             ");
           coolPrinting("(To exit enter 'exit')");
           System.out.println(" "); 
}
	   System.out.println(" ");               
		if(s.hasNextShort()){
	           d = s.nextShort();
			if(d >2 & d<7){
        	             System.out.println(" ");
           	             w.way(d);
                        }else {
			     System.out.println(" ");
			     System.out.println(" Incorrect dimention. Try 3,4,5 or 6 : ");
			     System.out.println(" ");
			     begining = false;
			     hello();
			}
                }else if(s.nextLine().equals("exit")){
			System.out.println(" ");
			System.out.println("Bye . . . ");
		}else {
			System.out.println(" ");
			System.out.println("Wrong command. Please try numbers   =) ");
			System.out.println(" ");
			begining = false;
			hello();
		}
      }
}