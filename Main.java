import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Mensaje de bienvenida.
        System.out.println("Welcome to the Baseball Reservation System!"); 

        //Opciones para el operador.
        System.out.println("-----MENU------ ");
        System.out.println("1. New reservation.");
        System.out.println("2. Check a reservation.");        
        System.out.println("3. Cancel a reservation.");
        System.out.println("4. Exit the program.");
        System.out.println("Select an option: ");
        Estadio estadio = new Estadio();
        Scanner optionScanner = new Scanner(System.in);
        Scanner sectionScanner = new Scanner(System.in);
        Scanner removeClient = new Scanner(System.in);
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
            System.out.print("\n1. Field Level Cost: $300");
            System.out.print("\n2. Main Level Cost: $120");
            System.out.print("\n3. Grandstand Level Cost: $45");
            System.out.print("\nEnter the section: ");
            String sectionScanned = sectionScanner.nextLine();
            estadio.reserveSeats(cliente, sectionScanned, option);
           System.out.print("You reservation has been made!");
        }
        case 2 ->{
            //Utiliza el metodo que muestra los asientos disponibles en cada field.
            estadio.showAvailableSeats();
       }
        case 3 ->{
            //Utiliza el metodo creado para cancelar reservaciones e indica si no hay una reservacion con ese cliente.
            System.out.print("\nName of the client: ");
            String nombre = removeClient.nextLine();
            Cliente cliente = estadio.searchClient(nombre);
            if (cliente != null){
                estadio.cancelReservation(cliente);
            }
            else{
                System.out.println("There is no reservation for this client.");
            }


        }
        case 4 -> {
            //Se sale del sistema y se cierran los scanners.
            System.out.println("Good bye!");
            sectionScanner.close();
            optionScanner.close();
            removeClient.close();
        }
        default -> 
        System.out.println("Invalid option.");
    }
    }
}
