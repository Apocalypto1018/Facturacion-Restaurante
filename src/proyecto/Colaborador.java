/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.util.UUID;

/**
 *
 * @author 
 */
public final class Colaborador {
    
    private final String id;
    private String nombre;
    private String apellido;
    private String telefono;
    private char rol;
    
    private String passWord;
    private String usuario;

    public Colaborador(String Nombre, String Apellido, String Telefono, char Rol){
        
        this.id=generarID();
        
        this.nombre=Nombre;
        this.apellido=Apellido;
        this.telefono=Telefono;
        
        this.rol=darRol(Rol);
        
    }
    
    //funcion que da el rol, el usuario y la password
    public char darRol(char Rol){
        
        if(Rol=='g'){
            usuario="gerente";
            passWord="1234";
        }else{
            usuario="mesero";
            passWord="1234";
        }
        
        return Rol;
    }
    
    //funcion que genero un id unico
    public String generarID(){
            
        UUID uniqueKey = UUID.randomUUID();

        return filter(String.valueOf(uniqueKey));
    }
    
    //funcion que toma los 3 primeros digitos del id generado
    public String filter(String word) {

        StringBuilder builder = new StringBuilder();

        String palabras[] = word.split(" ");

        for (String palabra : palabras) {

            if (palabra.length() >= 3) {
               builder.append(palabra.substring(0, 3));
            } else {
               builder.append(palabra);
            }
        }
      return builder.toString();
    }
    
    //setters
    public void setNombre(String Nombre){
        this.nombre=Nombre;
    }
    
    public void setApellido(String Apellido){
        this.apellido=Apellido;
    }
    
    public void setTelefono(String Telefono){
        this.telefono=Telefono;
    }
    
    public void setRol(char Rol){
        this.rol=Rol;
    }

    
    //getters
    public String getID(){
        return this.id;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public String getApellido(){
        return this.apellido;
    }
    
    public String getTelefono(){
        return this.telefono;
    }
    
    public char getRol(){
        return this.rol;
    }
    
    public String getUsuario(){
        return this.usuario;
    }
    
    public String getContrasenia(){
        return this.passWord;
    }
    
}
