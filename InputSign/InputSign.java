import java.util.Scanner;

public class InputSign {
	
    public InputSign() {
    }

    public static void main(String[] args) {
    	
        Scanner input = new Scanner(System.in); 
        System.out.print("Enter an integer: ");   
        
        int userInput = input.nextInt();          

        if (userInput > 0) {
            System.out.println("positive");
        } else if (userInput < 0) {
            System.out.println("negative");
        } else {
            System.out.println("zero");
        }

    }
}
