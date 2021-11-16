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
public final class Platillo {
    
    private final String codigo;
    private String nombre;
    private String descripcion;
    private float precio;
    
    public Platillo(String Nombre, String Descripcion, float Precio){
        
        this.codigo=generarID();
        
        this.nombre=Nombre;
        this.descripcion=Descripcion;
        this.precio=Precio;
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
   
    public void setDescripcion(String Descripcion){
        this.descripcion=Descripcion;
    }
    
    public void setPrecio(Float Precio){
        this.precio=Precio;
    }
    
    //getters
    public String getCodigo(){
        return this.codigo;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public String getDescripcion(){
        return this.descripcion;
    }
    
    public float getPrecio(){
        return this.precio;
    }
    
    
}
