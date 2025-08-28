import java.util.Scanner;

public class PrimalityTester {
	
	
    public PrimalityTester() {
        
    }

    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); 
        System.out.print("Enter an integer greater than 1: "); 
        
        if (input.hasNextInt()) {
        	
            int number = input.nextInt(); 

            
            if (number < 2) {
                System.out.println("invalid input");
            } 
            
            else if (isPrime(number)) {
                System.out.println("prime");
            } 
            
            else {
                System.out.println("composite");
            }
        } 
        
        else {
            System.out.println("invalid input"); 
        }

        input.close(); 
    }

    public static boolean isPrime(int n) {
        if (n < 2) {
            return false; 
        }
        for (int i = 2; i <= Math.sqrt(n); i++) { 
            if (n % i == 0) {
                return false; 
            }
        }
        return true; 
    }
}
