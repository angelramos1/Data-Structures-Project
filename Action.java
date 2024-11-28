import java.util.List;

public class Action {
        private String actionType; // Reserved or Canceled
        private Cliente cliente;   // Client associated with the action
        private List<Asiento> targetSeats; // Seats involved in the action
    
        public Action(String actionType, Cliente cliente, List<Asiento> targetSeats) {
            this.actionType = actionType;
            this.cliente = cliente;
            this.targetSeats = targetSeats;
        }
    
        public String getActionType() {
            return actionType;
        }
    
        public Cliente getCliente() {
            return cliente;
        }
    
        public List<Asiento> getTargetSeats() {
            return targetSeats;
        }
    }  
