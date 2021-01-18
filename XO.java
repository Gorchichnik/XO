import java.io.IOException;

public class XO {
    public static void main(String... arg) throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	Hello g = new Hello();
            g.hello();
    }
}