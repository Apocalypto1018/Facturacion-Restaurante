/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author
 */
public class Proyecto {
    
    private static ArrayList<Colaborador> arrayColaboradores = new ArrayList<>();
    
    private static Scanner sc= new Scanner(System.in);
    
    private static boolean salir=false;
    private static String usuario;
    private static String contrasenia;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //primer usuario administrador...
        arrayColaboradores.add(new Colaborador("Admin", "Admin", "123456789", 'g'));
        //primer usuario mesero...
        arrayColaboradores.add(new Colaborador("Mesero1", "Mesero1", "123456789", 'm'));
        
        do{
            
            System.out.print("*Ingrese el usuario\n->");
            usuario=sc.nextLine();
            contrasenia=sc.nextLine(); 

            System.out.print("*Ingrese la contrasenia\n->");
            contrasenia=sc.nextLine(); 
             
            
            if(confirmarCredencialesGerente(usuario, contrasenia)){
                
                menuGerente();
                
            }else if(confirmarCredencialesMesero(usuario, contrasenia)){
                
                menuMesero();
                
            }else{
                
                System.out.println("*Datos incorrectos, intente de nuevo (usuario admin: gerente contrasenia admin: 1234)");
            }
            
        }while(!salir);
        
    }
    
    public static boolean confirmarCredencialesGerente(String usuario, String contrasenia){
        
        for(int i=0;i<arrayColaboradores.size();i++){
            if(arrayColaboradores.get(i).getUsuario().equals(usuario) && arrayColaboradores.get(i).getContrasenia().equals(contrasenia) &&
                    arrayColaboradores.get(i).getRol()=='g'){
                return true;
            }
        }
	return false;
    }
    
    public static boolean confirmarCredencialesMesero(String usuario, String contrasenia){
        
        for(int i=0;i<arrayColaboradores.size();i++){
            if(arrayColaboradores.get(i).getUsuario().equals(usuario) && arrayColaboradores.get(i).getContrasenia().equals(contrasenia) && 
                    arrayColaboradores.get(i).getRol()=='m'){
                return true;
            }
        }
	return false;
    }
    
    public static void menuMesero(){
        System.out.println("dentroMesero");
    }
    
    public static void menuGerente(){
        System.out.println("dentroGerente");
    }
    
}
