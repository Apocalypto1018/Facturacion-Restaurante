/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

/**
 *
 * @author
 */
public class Cliente {
    
    private int NIT;
    private String nombre;
    private String apellido;
    private String direccion;
    
    public Cliente(int nit, String Nombre, String Apellido, String Direccion){
        this.NIT=nit;
        this.nombre=Nombre;
        this.apellido=Apellido;   
        this.direccion=Direccion;
    }
    
    //setters
    public void setNIT(int nit){
        this.NIT=nit;
    }
    
    public void setNombre(String Nombre){
        this.nombre=Nombre;
    }
    
    public void setApellido(String Apellido){
        this.apellido=Apellido;   
    }
    
    public void setDireccion(String Direccion){
        this.direccion=Direccion;
    }
    
    //getters
    public int getNIT(){
        return this.NIT;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public String getApellido(){
        return this.apellido;
    }
    
    public String getDireccion(){
        return this.direccion;
    }
}
