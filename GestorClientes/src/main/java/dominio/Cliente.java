package dominio;

import java.io.Serializable;

public class Cliente implements Serializable{ //Los java bean tienen implements Serializable
    
    //Definimos las propiedades privadas - Mapeo con nuestra tabla Cliente
    private int idCliente; //id_cliente
    private String nombre; // nombre
    private String apellidos;// apellidos
    private String email;//email
    private String telefono;//telefono
    private double saldo;//saldo
    
    //Constructores

    public Cliente(int idCliente) {
        this.idCliente = idCliente;
    }

    
    public Cliente(String nombre, String apellidos, String email, String telefono, double saldo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.saldo = saldo;
    }

    public Cliente(int idCliente, String nombre, String apellidos, String email, String telefono, double saldo) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.saldo = saldo;
    }
    
    

    public Cliente() {
    }
    
    
    //Getter and Setter

    public int getIdCliente() {
        return idCliente;
    }

//    public void setIdCliente(int idCliente) { Se setea a traves de la base de datos
//        this.idCliente = idCliente;
//    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    //toString

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente{idCliente=").append(idCliente);
        sb.append(", nombre=").append(nombre);
        sb.append(", apellidos=").append(apellidos);
        sb.append(", email=").append(email);
        sb.append(", telefono=").append(telefono);
        sb.append(", saldo=").append(saldo);
        sb.append('}');
        return sb.toString();
    }

    
    
    
    
    
}
