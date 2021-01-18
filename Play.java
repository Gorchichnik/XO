import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class Play {
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
                    for(int y=0; y<=d-1; y++){
                       arr[x][y] = "_";
                       System.out.print("[_]"); 
                    }
                    System.out.print("\r\n");
                 }            
                 System.out.print("\r\n");
                 System.out.println("d = "+d);
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
                 if(num1>=dim & win(xs)){
                        System.out.println("You win!"); 
                 }else if((double)num1>=((double)(dim*dim))/2){
                        System.out.println("Dead heat!"); 
                 }else{
                        aiturn();
                        if(num1>=dim & win(ys)){
                               System.out.println("You loose!"); 
                        } else{
                               play();
                        }
                 }                                
      }

      boolean win(ArrayList<Integer> xr){
             boolean w = false;
             int s;
             switch(dim) {
             case 3:
                for(int i = 0; i <= xr.size()-dim; i++){
                    for(int j = i+1; j<= xr.size()-dim+1; j++){
                           for(int k = j+1; k<= xr.size()-dim+2; k++){
                                  s = xr.get(i)+xr.get(j)+xr.get(k);
                                  
                                  if(xt.contains(s)){
                                        w = true;
                                  }
                           }
                    }
                }
                break;
             case 4:
                for(int i = 0; i <= xr.size()-dim; i++){
                    for(int j = i+1; j<= xr.size()-dim+1; j++){
                           for(int k = j+1; k<= xr.size()-dim+2; k++){
                                for(int b = k+1; b<= xr.size()-dim+3; b++){
                                  s = xr.get(i)+xr.get(j)+xr.get(k)+xr.get(b);
                                  
                                  if(xt.contains(s)){
                                        w = true;
                                  }
                                }
                           }
                    }
                }
             
             break;
             case 5:
                for(int i = 0; i <= xr.size()-dim; i++){
                    for(int j = i+1; j<= xr.size()-dim+1; j++){
                           for(int k = j+1; k<= xr.size()-dim+2; k++){
                                for(int b = k+1; b<= xr.size()-dim+3; b++){
               				for(int a = b+1; a<= xr.size()-dim+4; a++){
                                  s = xr.get(i)+xr.get(j)+xr.get(k)+xr.get(b)+xr.get(a);
                                  
                                  if(xt.contains(s)){
                                        w = true;
                                  }
                                        }
                                }
                           }
                    }
                }
             
             break;
             case 6:
                for(int i = 0; i <= xr.size()-dim; i++){
                    for(int j = i+1; j<= xr.size()-dim+1; j++){
                           for(int k = j+1; k<= xr.size()-dim+2; k++){
                                for(int b = k+1; b<= xr.size()-dim+3; b++){
               				for(int a = b+1; a<= xr.size()-dim+4; a++){
                                               for(int c = a+1; c<= xr.size()-dim+5; c++){
                                  s = xr.get(i)+xr.get(j)+xr.get(k)+xr.get(b)+xr.get(a)+xr.get(c);
                                  
                                  if(xt.contains(s)){
                                        w = true;
                                  }
                                               }
                                        }
                                }
                           }
                    }
                }
             
             break;
}
             return w;
      }


      void play(){ 
            System.out.println("PLease enter coordinates of your turn");
            System.out.print("\r\n");
            Scanner sc = new Scanner(System.in);
            try{
               x = sc.nextShort();
               y = sc.nextShort();
               if( arr[x-1][y-1] != "X" & arr[x-1][y-1] != "O"){
                   arr[x-1][y-1] = "X";
                   num1++;
                   xs.add((int)(y*Math.pow(10,x-1)));
                   
                   System.out.println("Your turn");
                   System.out.println(" ");
                   f.viewarr(arr);
                   System.out.print("\r\n");
                   game1();
                
	        } else {
 	 	    System.out.println("This cell is not empty!");
                    System.out.println();
                    play();
                }
            } catch (Exception e) {
               System.out.println("Incorrect dimention. Please try again.");
               System.out.print("\r\n");
               play();
            }
            
      }
      
      void aiturn(){
                x = getRandomShort((short)1,dim);
                y = getRandomShort((short)1,dim);
                if(arr[x-1][y-1] == "O" | arr[x-1][y-1] == "X"){
                    aiturn();
                } else {
                    arr[x-1][y-1] = "O";
                    ys.add((int)(y*Math.pow(10,x-1)));
                    System.out.println("Computer turn");
                    System.out.print("\r\n");
                    f.viewarr(arr);
                    System.out.print("\r\n");
                } 
            
}

     short getRandomShort(short a, short b){          
           short s = (short)Math.round(Math.random()*(double)(b-a)+(double)a);
           return s;
}
}