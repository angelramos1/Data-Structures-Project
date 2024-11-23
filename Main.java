import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Baseball Reservation System!"); //mensaje de bienvenida

        System.out.println("Please choose an option: ");
        System.out.println("1. New reservation.");
        System.out.println("2. Check a reservation.");        
        System.out.println("3. Cancel a reservation.");
        
        Scanner optionScanner = new Scanner(System.in);
        int option = optionScanner.nextInt();
        optionScanner.nextLine();
        
        if (option==1){          
        }
        else if(option==2){
        }
        else{            
        }
    }
}

