//Esta clase representa un cliente que reserva asientos en el sistema.
//Se utiliza los atributos para almacenar información del cliente.
public class Cliente {
    private String name;
    private String email;
    private String phoneNumber;
//Constructor 
    public Cliente(String name, String email, String phoneNumber){
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
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
//Método para devolver en un string la información del cliente.
    public String toString() {
        return "Name: " + name + ", Email: " + email + ", Phone: " + phoneNumber;
    }
}
