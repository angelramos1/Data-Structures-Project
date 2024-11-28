//Esta clase representa un cliente que reserva asientos en el sistema.
//Se utiliza los atributos para almacenar información del cliente.
public class Cliente {
    private String name;
    private String email;
    private String phoneNumber;
    private String targetSection;
    private int targetQuantity;
//Constructor 
    public Cliente(String name, String email, String phoneNumber){
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.targetSection = "";
        this.targetQuantity = 0;
    }
//Método para llamar al nombre.
    public String getName(){
        return name;
    }
//Método para llamar el email.
    public String getEmail(){
        return email;
    }
//Método para llamar su teléfono.
    public String getPhoneNumber(){
        return phoneNumber;
    }
//Métodos para escoger la seccion y cantidades deseada(Se usara para el waitlist y cancelar reservaciones)  
public void setTargetSection(String section){
    this.targetSection = section;
}
public String getTargetSection(){
    return targetSection;
}
public int getTargetQuantity() {
    return targetQuantity;
}

public void setTargetQuantity(int quantity) {
    this.targetQuantity = quantity;
}
//Método para devolver en un string la información del cliente.
    public String toString() {
        return "Name: "+ name + ", Email: " + email + ", Phone: " +phoneNumber + ", Target Section: " +targetSection + ", Target Quantity: " + targetQuantity;
    }
}
