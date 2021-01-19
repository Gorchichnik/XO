import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class Play {

      Ai ai = new Ai();
      Field f = new Field();
      String[][] arr;
      short x,y,dim;
      short num1 = 0;

      ArrayList<Integer> xs = new ArrayList<Integer>();
      ArrayList<Integer> ys = new ArrayList<Integer>();
      ArrayList<Integer> xt = new ArrayList<Integer>();
	
      void game(short d){
                 arr = new String[d][d];

                 for(int x=0; x<=d-1; x++){
			System.out.print("\r\n");
                    for(int y=0; y<=d-1; y++){
                       arr[x][y] = "_";
                       System.out.print(" [_] "); 
                    }
                    System.out.print("\r\n");
                 }            
                 System.out.print("\r\n");

                 ai.defineMatrix(d,arr);
                 dim=d;
                 createwincomb();
                 play();                   
           }

      void createwincomb(){
              int s = 0;
              int f = 0;
              int k = 0;
              int h = 0;
 
              for(int i = 1; i<=dim; i++){
                  s = s + i;
                  f = f + (int)Math.pow(10,i-1);
                  k = k + (int)Math.pow(10,i-1)*i;
                  h = h + (int)Math.pow(10,i-1)*(dim - i + 1);
              }

              for(int j = 1; j<=dim; j++){
                  xt.add((int)(s*Math.pow(10,j-1)));
                  xt.add((int)(f*j));
              }

              xt.add(k);
              xt.add(h);                          
      }  

      void game1(){        
		 yes = false;     
                   
                 if(num1>=dim & bruteforce(xs, -1, xs.size(), 0, 0) ){
                        System.out.println("You win!"); 
                 }else if((double)num1>=((double)(dim*dim))/2){
                        System.out.println("Dead heat!"); 
                 }else{
                        aiturn();
			yes = false;
                        if(num1>=dim & bruteforce(ys, -1, ys.size(), 0, 0)){
                               System.out.println("You loose!"); 
                        } else{
                               play();
                        }
                 }                                
      }
	boolean yes;
	
        boolean bruteforce(ArrayList<Integer> xr, int i, int size , int num , int s){
		num += 1;

		for(int j = i + 1; j <= size - dim + num - 1; j++){
			if(num == dim){
				s += xr.get(j);
				if(xt.contains(s)){
					yes = true;
//					System.out.println(" true ");
				}
//				System.out.println(" s = " + s);
					
			}else{
				s += xr.get(j);
				yes = bruteforce(xr, j , size, num, s);				
			}
			s -= xr.get(j);
			
		}
//		if(yes){System.out.println(" true ");}
		return yes;
	}

      void play(){ 
            System.out.println("Please enter coordinates of your turn :       (To exit enter 'exit')");
            System.out.print("\r\n");

            Scanner sc = new Scanner(System.in);

            try{
		if (sc.hasNextShort()){
               		x = sc.nextShort();
              		y = sc.nextShort();

	               if( arr[x-1][y-1] != "X" & arr[x-1][y-1] != "O"){
        	           arr[x-1][y-1] = "X";                   
                	   num1++;
	                   xs.add((int)(y*Math.pow(10,x-1)));
	
        	           System.out.println(" ");
                	   System.out.println("Your move : ");
	                   System.out.println(" ");

	                   f.viewarr(arr);
	
        	           System.out.print("\r\n");

	                   game1();
                
		        } else {
			   System.out.println();
 	 	 	   System.out.println("This cell is not empty!");
                    	   System.out.println();

                           play();
                	}
		}else if (sc.hasNextLine()){
			if(sc.nextLine().equals("exit")){
				System.out.print("\r\n");
				System.out.println("Bye...");
				System.out.print("\r\n");
			}else{
				System.out.println("Incorrect pass. Please try again.");
		               System.out.print("\r\n");

		               play();
			}
		}
            } catch (Exception e) {             
               System.out.println("Incorrect pass. Please try again.");
               System.out.print("\r\n");

               play();
            }
            
      }
      
      void aiturn(){		
                    ai.humanX(x,y);
                    ai.aiturn();

                    x = ai.getX();
	            y = ai.getY();   
                    
                    arr[x][y] = "O";

                    ys.add((int)((y+1)*Math.pow(10,x)));

                    System.out.println("Computer's turn : ");
                    System.out.print("\r\n");
                    f.viewarr(arr);
                    System.out.print("\r\n");		                     
     }
}