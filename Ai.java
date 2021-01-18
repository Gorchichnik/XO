import java.util.ArrayList;
import java.util.Iterator;

public class Ai {

	Integer[][] pr;
        String[][] arr;
        int dim,max;
        short x,y;
        ArrayList<Integer> xs = new ArrayList<Integer>();
	ArrayList<Integer> ys = new ArrayList<Integer>();
	void defineMatrix(short d, String[][] a){
		pr = new Integer[d][d];
		arr = new String[d][d];
                arr = a;		 
                 dim = d;
	}

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

        void aiturn(){
		makeMatrix();
             
		  
                findmax();
		int z = getRandomInt(0, xs.size());

		int a = xs.get(z);
		int b = ys.get(z);
                
		x = (short) a;
		y = (short) b;

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
        

	void makeMatrix(){
		matrixNull();
		int s1,s2,s;
		
		
		for(int i = 0; i < dim; i++){
			for(int j = 0; j < dim; j++){
				s = 0;
                                s1 = 0;
                                s2 = 0;


				if(arr[i][j] != "X" | arr[i][j] != "O"){
					
					for(int n = i; n >=0; n--){
						if(arr[n][j] == "X" & n!=i){
							s1++;
						}
						if(arr[n][j] == "O" & s1!=0  & n!=i){
							s1=0;
						}
					}


					for(int m = i; m < dim; m++){
						if(arr[m][j] == "X" & m!=i){
							s1++;
						}
 						if(arr[m][j] == "O" & s1!=0  & m!=i){
							s1=0;
						}
					}
					
                                        if(s1==0){
						for(int n = i; n >=0; n--){
							if(arr[n][j] == "O"  & n!=i){
								s1++;
							}							
						}
						for(int m = i; m < dim; m++){
							if(arr[m][j] == "X"  & m!=i){
								s1++;
							}							
						}
					}




					if(s1!=0){
						s1 = (int)Math.pow(s1,s1);
					}
					pr[i][j] = pr[i][j] + s1;


					for(int n = j; n >=0; n--){
						if(arr[i][n] == "X"  & n!=j){
							s2++;
						}
						if(arr[i][n] == "O" & s2!=0   & n!=j){
							s2=0;
						}
					}
					for(int m = j; m < dim; m++){
						if(arr[i][m] == "X"  & m!=j){
							s2++;
						}
 						if(arr[i][m] == "O" & s2!=0  & m!=j){
							s2=0;
						}
					}
			

                                        if(s2==0){
						for(int n = j; n >=0; n--){
							if(arr[i][n] == "O" & n!=j){
								s2++;
							}					
						}
						for(int m = j; m < dim; m++){
							if(arr[i][m] == "O"  & m!=j){
								s2++;
							} 							
						}						
					}


					if(s2!=0){
						s2 = (int)Math.pow(s2,s2);
					}
					pr[i][j] = pr[i][j] + s2;



				        if(i == j){
					for(int n = i; n >=0; n--){
						if(arr[n][n] == "X"   & n!=i){
							s++;
						}
						if(arr[n][n] == "O" & s!=0 & n!=i){
							s=0;
						}
					}

					for(int m = i; m < dim; m++){
						if(arr[m][m] == "X"   & m!=i){
							s++;
						}
 						if(arr[m][m] == "O" & s!=0 & m!=i){
							s=0;
						}
					}


					if(s == 0){
						for(int n = i; n >=0; n--){
							if(arr[n][n] == "O" & n!=i){
								s++;
							}	
						}

						for(int m = i; m < dim; m++){
							if(arr[m][m] == "O"   & m!=i){
								s++;
							} 							
						}
					
					}
                                        if(s!=0){
						pr[i][i] = pr[i][i] + (int)Math.pow(s,s);
					}

				}	
                                s = 0;
				if(i + j == dim - 1){
					for(int n = i; n >=0; n--){
						if(arr[n][dim - 1 - n] == "X"   & n!=i){
							s++;
						}
						if(arr[n][dim - 1 - n] == "O" & s!=0  & n!=i){
							s=0;
						}
					}
					for(int m = i; m < dim; m++){
						if(arr[m][dim - 1 -m] == "X"  & m!=i){
							s++;
						}
 						if(arr[m][dim - 1 -m] == "O" & s!=0  & m!=i){
							s=0;
						}
					}
					if(s == 0){
						for(int n = i; n >=0; n--){
							if(arr[n][dim - 1 -n] == "O"  & n!=i){
								s++;
							}	
						}
						for(int m = i; m < dim; m++){
							if(arr[m][dim - 1 -m] == "O"  & m!=i){
								s++;
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