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
					s1 = forXO(j, 0, i, "X", s1, 1);

					if(sense){
						s1 = forXO(j, i, dim - 1, "X", s1, 1);	
					}
                                        if(s1==0 & sense){
						s1 = forXO(j, 0, i, "O", s1, 1);
						if(sense){
							s1 = forXO(j, i, dim - 1, "O", s1, 1);	
						}
					}

					if(s1!=0){
						pr[i][j] += (int)Math.pow(s1,s1);
					}

					sense = true;

					s2 = forXO(i, 0, j, "X", s2, 2);
					if(sense){
						s2 = forXO( i, j, dim - 1, "X", s2, 2);		
					}
                                        if(s2==0 & sense){
						s2 = forXO(i, 0, j, "O", s2, 2);
						if(sense){
							s2 = forXO( i, j, dim - 1, "O", s2, 2);		
						}
					}

					if(s2!=0){
						pr[i][j] += (int)Math.pow(s2,s2);
					}
					
					sense = true;

				      	if(i == j){
						s = forXO(i, 0, i, "X", s, 3);
						if(sense){
							s = forXO(i, i, dim - 1, "X", s, 3);
						}
                                       		if(s==0 & sense){
							s = forXO(i, 0, i, "O", s, 3);
							if(sense){
								s = forXO(i, i, dim - 1, "O", s, 3);	
							}
						}

                                       		if(s!=0){
							pr[i][i] += (int)Math.pow(s,s);
						}

					}	

                               		s = 0;
					sense = true;

					if(i + j == dim - 1){
						s = forXO(dim - i - 1, 0, i, "X", s, 4);
						if(sense){
							s = forXO(dim - i - 1, i, dim - 1, "X", s, 4);	
						}
                                      		if(s==0 & sense){
							s = forXO(dim - i - 1, 0, i, "O", s, 4);
							if(sense){
								s = forXO(dim - i - 1, i, dim - 1, "O", s, 4);	
							}
						}

						if(s!=0){
							pr[i][j] += (int)Math.pow(s,s);
						}
					}				
				}		
			}
		}
	}

	int forXO(int fix, int from, int to, String check, int sum, int way){
		switch(way){

		case 1:
			for(int n = from; n <= to; n++){
				if(arr[n][fix] == check ){
				sum++;
				}
				if(arr[n][fix] != check & arr[n][fix] != "_" & sum!=0 ){
					sum=0;
					sense = false;
					break;
				}
			}
		break;

		case 2: 
			for(int n = from; n <= to; n++){
				if(arr[fix][n] == check ){
				sum++;
				}
				if(arr[fix][n] != check & arr[fix][n] != "_" & sum!=0 ){
					sum=0;
					sense = false;
					break;
				}
			}
		break;

		case 3: 
			for(int n = from; n <= to; n++){
				if(arr[n][n] == check ){
				sum++;
				}
				if(arr[n][n] != check & arr[n][n] != "_" & sum!=0 ){
					sum=0;
					sense = false;
					break;
				}
			}
		break;

		case 4: 
			for(int n = from; n <= to; n++){
				if(arr[n][dim - 1 -n] == check ){
				sum++;
				}
				if(arr[n][dim - 1 -n] != check & arr[n][dim - 1 -n] != "_" & sum!=0 ){
					sum=0;
					sense = false;
					break;
				}
			}
		break;
		}
		return sum;
	}
}