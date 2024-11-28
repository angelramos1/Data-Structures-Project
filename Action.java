import java.util.List;
// Clase para los métodos de Undo, para que se haga más fácil manejar las acciones y los clientes haciendolas.
public class Action {
        private String actionType; 
        private Cliente cliente;   
        private List<Asiento> targetSeats;
    
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
