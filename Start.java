import java.io.IOException;
 
public class CLS {    
    public static void clear() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	        
    }
}