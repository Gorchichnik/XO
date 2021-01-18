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

		makeMatrix(); // initialisation of the probability Matrix
                     
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

	void makeMatrix(){

		matrixNull(); 
		int s1,s2,s; // values of horizontal, vertical and diagonal probabilities  
		boolean sense = true; // does it make sense if horizontal, vertical or diagonal has "X" and "O" at the same time?
 		
		
		for(int i = 0; i < dim; i++){
			for(int j = 0; j < dim; j++){
				s = 0;
                                s1 = 0;
                                s2 = 0;

				if(arr[i][j] != "X" & arr[i][j] != "O"){					
					for(int n = 0; n <= i; n++){
						if(arr[n][j] == "X" & n!=i){
							s1++;
						}
						if(arr[n][j] == "O" & s1!=0 & n!=i){
							s1=0;
							sense = false;
							break;
						}
					}

					if(sense){
						for(int m = i; m < dim; m++){
							if(arr[m][j] == "X" & m!=i){
								s1++;
							}
 							if(arr[m][j] == "O" & s1!=0  & m!=i){
								s1=0;
								sense = false;
								break;
							}
						}
					}
	
                                        if(s1==0 & sense){
						for(int n = 0; n <=i; n++){
							if(arr[n][j] == "O"  & n!=i){
								s1++;
							}	
							if(arr[n][j] == "X" & s1!=0  & n!=i){
								s1=0;
								sense = false;
								break;
							}						
						}
						if(sense){
							for(int m = i; m < dim; m++){
								if(arr[m][j] == "O"  & m!=i){
									s1++;
								}	
								if(arr[m][j] == "X" & s1!=0  & m!=i){
									s1=0;
									break;
								}						
							}
						}
					}

					if(s1!=0){
						s1 = (int)Math.pow(s1,s1);
						pr[i][j] = pr[i][j] + s1;
					}
					
					sense = true;

					for(int n = 0; n <=j; n++){
						if(arr[i][n] == "X"  & n!=j){
							s2++;
						}
						if(arr[i][n] == "O" & s2!=0   & n!=j){
							s2=0;
							sense = false;
							break;							
						}
					}

					if(sense){
						for(int m = j; m < dim; m++){
							if(arr[i][m] == "X"  & m!=j){
								s2++;
							}
 							if(arr[i][m] == "O" & s2!=0  & m!=j){
								s2=0;
								sense = false;
								break;							
							}
						}
					}
			
                                        if(s2==0 & sense){
						for(int n = 0; n <=j; n++){
							if(arr[i][n] == "O" & n!=j){
								s2++;
							}	
							if(arr[i][n] == "X" & s2!=0   & n!=j){
								s2=0;
								sense = false;
								break;				
							}				
						}
						if(sense){
							for(int m = j; m < dim; m++){
								if(arr[i][m] == "O"  & m!=j){
									s2++;
								} 	
								if(arr[i][m] == "X" & s2!=0   & m!=j){
									s2=0;
									break;
								}						
							}
						}						
					}
			
					if(s2!=0){
						s2 = (int)Math.pow(s2,s2);
						pr[i][j] = pr[i][j] + s2;
					}
					
					sense = true;
				      	if(i == j){
						for(int n = i; n >=0; n--){
							if(arr[n][n] == "X"   & n!=i){
								s++;
							}
							if(arr[n][n] == "O" & s!=0 & n!=i){
								s=0;
								sense = false;
								break;	
							}
						}

						if(sense){
							for(int m = i; m < dim; m++){
								if(arr[m][m] == "X"   & m!=i){
									s++;
								}
 								if(arr[m][m] == "O" & s!=0 & m!=i){
									s=0;
									sense = false;
									break;
								}
							}
						}

						if(s == 0 & sense){
							for(int n = i; n >=0; n--){
								if(arr[n][n] == "O" & n!=i){
									s++;
								}	
								if(arr[n][n] == "X" & s!=0 & n!=i){
									s=0;
									sense = false;
									break;	
								}
							}

							for(int m = i; m < dim; m++){
								if(arr[m][m] == "O"   & m!=i){
									s++;
								} 		
								if(arr[m][m] == "X" & s!=0 & m!=i){
									s=0;
									sense = false;
									break;	
								}					
							}
					
						}

                                       		if(s!=0){
							pr[i][i] = pr[i][i] + (int)Math.pow(s,s);
						}

					}	

                               		s = 0;
					sense = true;

					if(i + j == dim - 1){
						for(int n = i; n >=0; n--){
							if(arr[n][dim - 1 - n] == "X"   & n!=i){
								s++;
							}
							if(arr[n][dim - 1 - n] == "O" & s!=0  & n!=i){
								s=0;
								sense = false;
								break;
							}
						}

						for(int m = i; m < dim; m++){
							if(arr[m][dim - 1 -m] == "X"  & m!=i){
								s++;
							}
 							if(arr[m][dim - 1 -m] == "O" & s!=0  & m!=i){
								s=0;
								sense = false;
								break;
							}
						}

						if(s == 0){
							for(int n = i; n >=0; n--){
								if(arr[n][dim - 1 -n] == "O"  & n!=i){
									s++;
								}	
								if(arr[n][dim - 1 - n] == "X" & s!=0  & n!=i){
									s=0;
									sense = false;
									break;
								}
							}
							for(int m = i; m < dim; m++){
								if(arr[m][dim - 1 -m] == "O"  & m!=i){
									s++;
								} 	
								if(arr[m][dim - 1 - m] == "X" & s!=0  & m!=i){
									s=0;
									sense = false;
									break;
								}						
							}						
						}

						if(s!=0){
							pr[i][j] = pr[i][j] + (int)Math.pow(s,s);
						}
					}					
				}else{
					pr[i][j] = 0;
				}		
			}
		}
	}
}