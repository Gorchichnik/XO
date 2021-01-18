import java.util.Scanner;

public class Way {
      
      Play f = new Play();

      void way(short d){
            
           switch(d) {
                 case 3:
                    
                    f.game((short) 3);
                    break;
                 case 4:
                    
                    f.game((short) 4);
                    break;
                 case 5:
                    
                    f.game((short) 5);
                    break;
                 case 6:
                    
                    f.game((short) 6);
                    break;
                 default:
                    System.out.println("Incorrect command. Please pass the wright number.");
                    Scanner s = new Scanner(System.in);
                    short d1 = s.nextShort();
                    way(d1);
           }
           
      }
}