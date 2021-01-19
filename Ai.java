import java.util.ArrayList;
import java.util.Iterator;

// Artifitial Intelegence
public class Ai {

	Integer[][] pr;  // probability
        String[][] arr;  // "X" & "O" Array
        int dim, max;    // dimention of game field & maximal probability
        short x,y;       // "O" output coordinates 
        ArrayList<Integer> xs = new ArrayList<Integer>();     //ArrayList of 'x' coordinates of maximal probabilities 
	ArrayList<Integer> ys = new ArrayList<Integer>();     //ArrayList of 'y' coordinates of maximal probabilities

	// difinition of arrays in the begining...

	void defineMatrix(short d, String[][] a){
		pr = new Integer[d][d];
		arr = new String[d][d];
                arr = a;		 
                dim = d;
	}

	// 'X' setting after the 'X' coordinates is passed 

	void humanX(short x, short y){		
		arr[x-1][y-1] = "X";                            
	}	

	short getX(){
		return x;
	}	

	short getY(){
		return y;
	}

	int getRandomInt(int a, int b){          
           int s = (int)Math.round(Math.random()*(double)(b-a)+(double)a);
           return s;
	}

	//main method to do AI move

        void aiturn(){

		makeMatrixEvo(); // initialisation of the probability Matrix
                     
                findmax(); // finding of the maximal points in probability Matrix

		int index = getRandomInt(0, xs.size() - 1); // finding randomly one of the maximal points

		//finding its coordinates

		int temp1 = xs.get(index);
		int temp2 = ys.get(index);

		x = (short) temp1;
		y = (short) temp2;

		xs.clear();
		ys.clear();

		arr[x][y] = "O";
		
	}

        void findmax(){
		max = 0;
		for(int i = 0; i < dim; i++){
			for(int j = 0; j < dim; j++){
			 	if(pr[i][j] > max){
					max = pr[i][j];
					xs.clear();
					ys.clear();
				}
				if(pr[i][j] == max){
					xs.add(i);
					ys.add(j);
				}
			}
		}

	}

	void matrixNull(){
		for(int i=0; i < dim; i++){
			for(int j=0; j < dim; j++){
				pr[i][j] = 0;
			}
		}
	}
        
	//Next method is "the heart" of AI algorithm . Can't explain )



	boolean sense; // does it make sense if horizontal, vertical or diagonal has "X" and "O" at the same time?

	void makeMatrixEvo(){
		matrixNull(); 
		int s1,s2,s; // values of horizontal, vertical and diagonal probabilities  
		
		int way;     //  horizontal is 1, vertical is 2, diagonals are 3 & 4;
		
		for(int i = 0; i < dim; i++){
			for(int j = 0; j < dim; j++){
				s = 0;
                                s1 = 0;
                                s2 = 0;

				if(arr[i][j] != "X" & arr[i][j] != "O"){
					sense = true;	

					pr[i][j] += soSlovVovana2(j, i, s1, 1);	
					sense = true;

					pr[i][j] += soSlovVovana2(i, j, s2, 2);
					sense = true;

				      	if(i == j){
						pr[i][j] += soSlovVovana2(i, i, s, 3);
					}	

                               		s = 0;
					sense = true;

					if(i + j == dim - 1){
						pr[i][j] += soSlovVovana2(dim - i - 1, i , s, 4);	
					}				
				}		
			}
		}
	}

	int soSlovVovana2(int j, int i, int s, int d){
                int ret = 0;
		s = forXO(j, 0, i, "X", s, d);

		if(sense){
			s = forXO(j, i, dim - 1, "X", s, d);	
		}
                if(s==0 & sense){
			s = forXO(j, 0, i, "O", s, d);
			if(sense){
				s = forXO(j, i, dim - 1, "O", s, d);	
	  		}
		}

		if(s!=0){
			ret += (int)Math.pow(s,s);
		}

		return ret;
	}

	boolean br;

	int soSlovVovana(int n, int m, String check, int sum){
		if(arr[n][m] == check ){
			sum++;
		}
		if(arr[n][m] != check & arr[n][m] != "_" & sum!=0 ){
			sum=0;
			sense = false;
			br = true;
		}
 		return sum;
	}

	int forXO(int fix, int from, int to, String check, int sum, int way){
		br = false;
		
		switch(way){

		case 1:		
			for(int n = from; n <= to; n++){			
				sum = soSlovVovana(n, fix, check, sum);
				if(br){
					break;
				}
			}
		break;

		case 2: 
			for(int n = from; n <= to; n++){
				sum = soSlovVovana(fix, n, check, sum);
				if(br){
					break;
				}
			}
		break;

		case 3: 
			for(int n = from; n <= to; n++){
				sum = soSlovVovana(n, n, check, sum);
				if(br){
					break;
				}
			}
		break;

		case 4: 
			for(int n = from; n <= to; n++){
				sum = soSlovVovana(n, dim - n -1, check, sum);
				if(br){
					break;
				}
			}
		break;
		}
		return sum;
	}
}