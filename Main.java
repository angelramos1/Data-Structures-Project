import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Mensaje de bienvenida.
        System.out.println("Welcome to the Baseball Reservation System!"); 

        //Opciones para el operador.
      
        Estadio estadio = new Estadio();
        Scanner optionScanner = new Scanner(System.in);
        Scanner sectionScanner = new Scanner(System.in);
        Scanner removeClient = new Scanner(System.in);
        Scanner quantityScanner = new Scanner(System.in);
       
        boolean menu = true;

        while(menu) {
        System.out.println("\n-----MENU------ ");
        System.out.println("1. New reservation.");
        System.out.println("2. Check available seats.");
        System.out.println("3. Check Waiting List.");        
        System.out.println("4. Cancel a reservation.");
        System.out.println("5. Exit the program.");
        System.out.println("Select an option: ");
         int option = optionScanner.nextInt();
            optionScanner.nextLine();

       switch (option){
        case 1 ->{
            //Opcion de hacer reservaciones e informacion del cliente.
            System.out.print("Customer name: ");
            String nombre = optionScanner.nextLine();
            System.out.print("\nEmail: ");
            String email = optionScanner.nextLine();
            System.out.print("\nPhone number: ");
            String telefono = optionScanner.nextLine();
            Cliente cliente = new Cliente(nombre, email, telefono);

            System.out.print("Select a Section: ");
            System.out.print("\n1. Field Level ------($300)");
            System.out.print("\n2. Main Level -------($120)");
            System.out.print("\n3. Grandstand Level -($45)");
            System.out.print("\nEnter the section: ");
            String sectionScanned = sectionScanner.nextLine();
            System.out.print("\n Enter number of seats: ");
            int quantity = quantityScanner.nextInt();
            boolean success = estadio.reserveSeats(cliente, sectionScanned, quantity);
            if (success) {
                System.out.println("Reservation has been made for " + cliente.getName() + ", " + quantity + " seats in " + sectionScanned + ".");
            } else {
                // Add to waitlist if no seats available
                estadio.addToWaitList(cliente);
                System.out.println("No available seats in " + sectionScanned + ". " +
                                   cliente.getName() + " has been added to the waitlist.");
            }
        }
        case 2 ->{
            //Utiliza el metodo que muestra los asientos disponibles en cada field.
            estadio.showAvailableSeats();
       }
       case 3 ->{
            estadio.showWaitlist();
       }
        case 4 ->{
            //Utiliza el metodo creado para cancelar reservaciones e indica si no hay una reservacion con ese cliente.
            System.out.print("\nName of the client: ");
            String nombre = removeClient.nextLine();
            Cliente cliente = estadio.searchClient(nombre);
            if (cliente != null){
                estadio.cancelReservation(cliente);
                System.out.println("Reservation for " + cliente.getName() + " has been cancelled.");
            }
            else{
                System.out.println("There is no reservation for this client.");
            }


        }
       
        case 5 -> {
            //Se sale del sistema y se cierran los scanners.
            System.out.println("Good bye!");
            menu = false;
            sectionScanner.close();
            optionScanner.close();
            removeClient.close();
            quantityScanner.close();
           
        }
        default -> 
        System.out.println("Invalid option.");
    }
        if (menu) {
        System.out.println("\nWould you like to:");
        System.out.println("1. Return to the menu.");
        System.out.println("2. Undo the last action.");
        System.out.print("Enter your choice: ");
        int undoOption = optionScanner.nextInt();
        optionScanner.nextLine();
        
        if (undoOption == 2) {
            estadio.Undo();
            System.out.println("The last action has been undone.");
        }   
    }
    
    }
}
}
