//Clase Estadio para administrar los asientos disponibles, las reservaciones de los clientes y la disponibilidad de asientos.
import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;

public class Estadio {
    private Set<Asiento> availableSeats; //asientos disponibles
    private Map<Cliente, List<Asiento>> reservations; // mapa de clientes y sus asientos
    private Queue<Cliente> waitlist; //lista de espera 
    private Stack<Action> actionHistory; //historial de acciones para deshacer
    
    //constructor
    public Estadio() {
        availableSeats = new HashSet<>();
        reservations = new HashMap<>();
        waitlist = new LinkedList<>();
        actionHistory = new Stack<>();

        // se crean los asientos para cada nivel
        initializeSeats("Field Level", 500);
        initializeSeats("Main Level", 1000);
        initializeSeats("Grandstand Level", 2000);
    }
    //se inicializan los asientos en el nivel específico
    private void initializeSeats(String level, int capacity) {
        for (int i =1;i<=capacity;i++) {
            availableSeats.add(new Asiento(level, String.valueOf(i)));
        }
    }
    // método para reservar una cantidad de asientos para un cliente en cierto nivel
    public boolean reserveSeats(Cliente cliente, String level, int quantity) {
        List<Asiento> bookedSeats=new ArrayList<>();
        for (Asiento seat:availableSeats) {
            if (seat.getLevel().equals(level) && !seat.isReserved()) {
                seat.setReserved(true);
                bookedSeats.add(seat);
                if (bookedSeats.size()==quantity) break;
            }
        }

        if (bookedSeats.size()== quantity) {
            availableSeats.removeAll(bookedSeats);
            reservations.put(cliente,bookedSeats);
            actionHistory.push(new Action("Reserved",cliente,bookedSeats));
            return true;
        } else {
            addToWaitList(cliente,level,quantity);
            return false;
        }
    }
    // para cancelar reservaciones y liberar esos asientos
    public boolean cancelReservation(Cliente cliente) {
        if (reservations.containsKey(cliente)) {
            List<Asiento> canceledSeats =reservations.remove(cliente);
            for (Asiento seat:canceledSeats) {
                seat.setReserved(false);
                availableSeats.add(seat);
            }
            actionHistory.push(new Action("Canceled",cliente,canceledSeats));
            if (!waitlist.isEmpty()) {
                Queue<Cliente> remainingWaitlist= new LinkedList<>();
                boolean seatsReassigned= false;
    
                for (Cliente nextClient:waitlist) {
                    String desiredSection =nextClient.getTargetSection();
                    int desiredQuantity= nextClient.getTargetQuantity();
    
                    // verifica si los asientos liberados cumplen con los requisitos del cliente
                    List<Asiento> reassignedSeats = new ArrayList<>();
                    for (Asiento seat:canceledSeats) {
                        if (seat.getLevel().equals(desiredSection) && reassignedSeats.size() < desiredQuantity) {
                            seat.setReserved(true);
                            reassignedSeats.add(seat);
                        }
                    }
    
                    if (reassignedSeats.size()==desiredQuantity) {
                        // Quitar los asientos asignados
                        canceledSeats.removeAll(reassignedSeats); 
                        availableSeats.removeAll(reassignedSeats);
                        reservations.put(nextClient, reassignedSeats);
                        actionHistory.push(new Action("Reserved", nextClient, reassignedSeats));
                        System.out.println("Assigned " + desiredQuantity + " seats in " + desiredSection + 
                                           " to " + nextClient.getName() + " from the waitlist.");
                        seatsReassigned = true;
                    } else {
                        // Cliente se quedan en la lista de espera.
                        remainingWaitlist.add(nextClient); 
                    }
                }
    
                waitlist.clear();
                // Se agregaron los clientes no asignados a la lista de espera de nuevo.
                waitlist.addAll(remainingWaitlist); 
    
                if (!seatsReassigned) {
                    System.out.println("No clients in the waitlist matched the available seats.");
                }
            }
            return true;
        }
        return false;
    }
    // para enseñar los asientos disponibles
    public void showAvailableSeats() {
        Map<String, Integer> seatCount = new HashMap<>();
        for (Asiento seat : availableSeats) {
            seatCount.put(seat.getLevel(), seatCount.getOrDefault(seat.getLevel(), 0) + 1);
        }

        System.out.println("Available Seats:");
        seatCount.forEach((level, count) -> System.out.println(level + ": " + count + " seats available"));
    }
    // para enseñar los clientes en el waitList
    public void showWaitlist() {
        System.out.println("Waitlist:");
        for (Cliente cliente : waitlist) {
            System.out.println(cliente);
        }
    }
    
    public void addToWaitList(Cliente cliente,String section,int quantity){
        if(!waitlist.contains(cliente)){
            cliente.setTargetSection(section);
            cliente.setTargetQuantity(quantity);
            waitlist.add(cliente);
        }
        else{
            System.out.println(cliente.getName()+ " is on the wait list.");
        }
        
    }

    // Metodo para iterar por la lista de clientes (Lo utilizamos para cancelar reservaciones)
    public Cliente searchClient(String name){
        for (Cliente cliente:reservations.keySet()){
            if(cliente.getName().equals(name)){
                return cliente;
            }
        }
         return null;
        
    }
    // Metodo para borrar la ultima accion.
    public boolean Undo(){
        if (actionHistory.isEmpty()) {
            System.out.println("No actions to undo.");
            return false;
        }
    
        Action lastAction =actionHistory.pop();
        Cliente cliente= lastAction.getCliente();
        List<Asiento> affectedSeats = lastAction.getTargetSeats();
    
        if (lastAction.getActionType().equals("Reserved")) {
            for (Asiento seat:affectedSeats) {
                seat.setReserved(false);
                availableSeats.add(seat);
            }
            // Quita los clientes de su reservacion
            reservations.remove(cliente); 
            System.out.println("Reservation for " + cliente.getName() + " has been undone.");
    
            // Agrega los asientos al cliente en la lista de espera
            if (!waitlist.isEmpty()) {
                Cliente nextClient = waitlist.poll();
                reserveSeats(nextClient, affectedSeats.get(0).getLevel(), affectedSeats.size());
                System.out.println("Seats reassigned to the next client in waitlist: " + nextClient.getName());
            }
        } else if (lastAction.getActionType().equals("Canceled")) {
            // Deshace la cancelacion de reserva.
            for (Asiento seat:affectedSeats) {
                seat.setReserved(true);
                availableSeats.remove(seat);
            }
            //Devuelve la reservacion borrada.
            reservations.put(cliente, affectedSeats); 
            System.out.println("Cancellation for "+ cliente.getName() + " has been undone.");
        }
        return true;
    
    }
    
}

