//Clase Asiento para representar cada asiento en el lugar con atributos como sección, fila y número de asiento. 
//Se utiliza los atributos para almacenar información del asiento.
public class Asiento {
    private boolean reserved;
    private String level;
    private String numberOfSeat;

//Constructor 
    public Asiento(String level, String numberOfSeat){
        this.level = level;
        this.numberOfSeat = numberOfSeat;
        this.reserved = false;
    }
//Método para llamar a reserved.
    public boolean isReserved(){
        return reserved;
    }
//Método para llamar al level.
    public String getLevel(){
        return level;
    }
//Método para llamar al numberOfSeat.
    public String getNumberOfSeat(){
        return numberOfSeat;
    }
//Setter
    public void setReserved(boolean reserved){
        this.reserved = reserved;
    }
    
}
