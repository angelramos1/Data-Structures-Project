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
    private Stack<String> actionHistory; //historial de acciones para deshacer
    
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
        for (int i = 1; i <= capacity; i++) {
            availableSeats.add(new Asiento(level, String.valueOf(i)));
        }
    }
    // método para reservar una cantidad de asientos para un cliente en cierto nivel
    public boolean reserveSeats(Cliente cliente, String level, int quantity) {
        List<Asiento> bookedSeats = new ArrayList<>();
        for (Asiento seat : availableSeats) {
            if (seat.getLevel().equals(level) && !seat.isReserved()) {
                seat.setReserved(true);
                bookedSeats.add(seat);
                if (bookedSeats.size() == quantity) break;
            }
        }

        if (bookedSeats.size() == quantity) {
            availableSeats.removeAll(bookedSeats);
            reservations.put(cliente, bookedSeats);
            actionHistory.push("Reserved " + quantity + " seats for " + cliente.getName());
            return true;
        } else {
            waitlist.add(cliente);
            return false;
        }
    }
    // para cancelar reservaciones y liberar esos asientos
    public boolean cancelReservation(Cliente cliente) {
        if (reservations.containsKey(cliente)) {
            List<Asiento> canceledSeats = reservations.remove(cliente);
            for (Asiento seat : canceledSeats) {
                seat.setReserved(false);
                availableSeats.add(seat);
            }
            actionHistory.push("Canceled reservation for " + cliente.getName());
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
    // Metodo para iterar por la lista de clientes (Lo utilizamos para cancelar reservaciones)
    public Cliente searchClient(String name){
        for (Cliente cliente : reservations.keySet()){
            if(cliente.getName().equals(name)){
                return cliente;
            }
        }
         return null;
        
    }
    
}

