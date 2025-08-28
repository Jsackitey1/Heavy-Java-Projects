import java.util.Scanner;

public class LineLength {
   
    public LineLength() {
       
    }

 
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); 
        System.out.print("Enter a line of text: ");
        String len = input.nextLine();          

        
        
        int linelength = len.length();
        
        String charact= (linelength > 1)? "characters":"character" ;
      
        System.out.println("That line is " + linelength +" "+ charact+ " long.");

    }
}
