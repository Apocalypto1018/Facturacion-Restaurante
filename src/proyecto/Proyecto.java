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

    private static ArrayList<Colaborador> arrayColaboradores = new ArrayList<Colaborador>();
    private static ArrayList<Cliente> arrayClientes = new ArrayList<Cliente>();
    private static ArrayList<Platillo> arrayPlatillo = new ArrayList<Platillo>();
    private static Scanner sc = new Scanner(System.in);

    private static boolean salir = false;
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

        do {

            System.out.print("*Ingrese el usuario\n->");
            usuario = sc.nextLine();

            System.out.print("*Ingrese la contrasenia\n->");
            contrasenia = sc.nextLine();

            if (confirmarCredencialesGerente(usuario, contrasenia)) {

                menuGerente();

            } else if (confirmarCredencialesMesero(usuario, contrasenia)) {

                menuMesero();

            } else {

                System.out.println("*Datos incorrectos, intente de nuevo (usuario admin: gerente contrasenia admin: 1234)");
            }

        } while (!salir);

    }

    public static boolean confirmarCredencialesGerente(String usuario, String contrasenia) {

        for (int i = 0; i < arrayColaboradores.size(); i++) {
            if (arrayColaboradores.get(i).getUsuario().equals(usuario) && arrayColaboradores.get(i).getContrasenia().equals(contrasenia)
                    && arrayColaboradores.get(i).getRol() == 'g') {
                return true;
            }
        }
        return false;
    }

    public static boolean confirmarCredencialesMesero(String usuario, String contrasenia) {

        for (int i = 0; i < arrayColaboradores.size(); i++) {
            if (arrayColaboradores.get(i).getUsuario().equals(usuario) && arrayColaboradores.get(i).getContrasenia().equals(contrasenia)
                    && arrayColaboradores.get(i).getRol() == 'm') {
                return true;
            }
        }
        return false;
    }
    
    //menu mesero
    public static void menuMesero() {
        int opcion=0;
        
        do{
            System.out.println("1. Clientes");
            System.out.println("2. Ver Platillos");
            System.out.println("3. Facturacion");
            System.out.print("0. Salir\n->");
            opcion=sc.nextInt();
            
            sc.nextLine();
            
            switch(opcion){
                case 0:{
                    System.out.println("Volviendo al menu de login");
                    break;
                }
                
                case 1:{
                    submenuCliente();
                    break;
                }
                
                case 2:{
                    submenuVerPlatillos();
                }
            }
            
        }while(opcion!=0);
        
    }
    
    //submenu cliente de mesero
    public static void submenuCliente(){
        
        int opcion=0;
        int nit;
        String nombre, apellido, direccion;
        
        do{
            System.out.println("1. Ingresar Cliente");
            System.out.print("0. Volver\n->");
            opcion=sc.nextInt();
            
            sc.nextLine();
            
            switch(opcion){
                case 0:{
                    System.out.println("Volviendo al menu de Mesero");
                    break;
                }
                
                case 1:{
                    System.out.println("Ingresar. ");
                    System.out.println("Ingrese Datos del cliente");
                    System.out.println("NIT: ");
                    nit = sc.nextInt();
                    
                    sc.nextLine();
                    
                    System.out.println("Nombre: ");
                    nombre = sc.nextLine();
                    System.out.println("Apellido: ");
                    apellido = sc.nextLine();
                    System.out.println("Direccion: ");
                    direccion = sc.nextLine();
                    arrayClientes.add(new Cliente(nit, nombre, apellido, direccion));
                    break;
                }
                
            }
            
        }while(opcion!=0);
    }
    
    //ver platillos de mesero
    public static void submenuVerPlatillos(){
      
	for (Platillo actual : arrayPlatillo) {
              
            System.out.println("\n\n*Codigo: " + actual.getCodigo());
            System.out.println("*Nombre: " + actual.getNombre());
            System.out.println("*Descripcion: " + actual.getDescripcion());
            System.out.println("*Descripcion: " + actual.getPrecio());
          
	}
  
    }

    
    
    
    
    
    
    
    
    
    
    
    public static void menuGerente() {

        int opcion = 0;
        int nit;
        char rol;
        String nombre, apellido, direccion, telefono;

        do {
            System.out.println("1. Clientes.\n2. Colaboradores.\n "
                    + "3. Carta de platillos.\n4. Facturacion.\n"
                    + "5. Cerrar sesion.");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    int opcionCliente;
                    do {
                        System.out.println("1. Ingresar cliente.\n 2. Eliminar Cliente"
                                + "\n3. Regrasar al menu principa.");
                        opcionCliente = sc.nextInt();
                        switch (opcionCliente) {
                            case 0:{
                                System.out.println("Volviendo al menu de Gerente");
                                break;
                            }
                            case 1:
                                System.out.println("Ingresar. ");
                                System.out.println("Ingrese Datos del cliente");
                                System.out.println("NIT: ");
                                nit = sc.nextInt();
                                
                                sc.nextLine();
                                
                                System.out.println("Nombre: ");
                                nombre = sc.nextLine();
                                System.out.println("Apellido: ");
                                apellido = sc.nextLine();
                                System.out.println("Direccion: ");
                                direccion = sc.nextLine();
                                arrayClientes.add(new Cliente(nit, nombre, apellido, direccion));
                                break;
                            case 2:
                                System.out.println("Eliminar.");
                                System.out.println("Ingrese Datos del cliente");
                                System.out.println("NIT: ");
                                nit = sc.nextInt();
                                
                                sc.nextLine();
                                
                                System.out.println("Nombre: ");
                                nombre = sc.nextLine();
                                System.out.println("Apellido: ");
                                apellido = sc.nextLine();
                                System.out.println("Direccion: ");
                                direccion = sc.nextLine();
                                 {
                                    int itera = 0;
                                    int captura = -1;
                                    for (Cliente actual : arrayClientes) {
                                        if ((actual.getNIT() == nit) && (actual.getNombre().equalsIgnoreCase(nombre))
                                                && (actual.getApellido().equalsIgnoreCase(apellido))
                                                && (actual.getDireccion().equalsIgnoreCase(direccion))) {
                                            captura = itera;
                                        }
                                        itera++;
                                    }
                                    if (captura != -1) {
                                        arrayClientes.remove(captura);
                                    }
                                }
                                break;
                           
                            default:
                                System.out.println("Opcion no valida");

                        }
                    } while (opcion != 0);
                    break;

                case 2:
                    int opcionColaborador;
                    do {
                        System.out.println("1. Ingresar colaborador.\n 2. Modificar colaborador"
                                + "\n3. Eliminar colaborador.\n Volver a menu principal");
                        opcionColaborador = sc.nextInt();
                        switch (opcionColaborador) {
                            case 1:
                                System.out.println("Ingresar. ");
                                System.out.println("Nombre: ");
                                nombre = sc.nextLine();
                                System.out.println("Apellido: ");
                                apellido = sc.nextLine();
                                System.out.println("Telefono: ");
                                telefono = sc.nextLine();
                                System.out.println("Rol: g/m");
                                rol = sc.next().charAt(0);
                                arrayColaboradores.add(new Colaborador(nombre, apellido, telefono, rol));
                                 {
                                    int itera = 0;
                                    int captura = -1;
                                    for (Colaborador actual : arrayColaboradores) {
                                        if ((actual.getNombre().equalsIgnoreCase(nombre))
                                                && (actual.getApellido().equalsIgnoreCase(apellido))
                                                && (actual.getTelefono().equalsIgnoreCase(telefono))) {
                                            captura = itera;
                                        }
                                        itera++;
                                    }
                                    if (captura != -1) {
                                        arrayClientes.remove(captura);
                                    }
                                }

                                break;
                            case 2:
                                System.out.println("Eliminar.");
                                System.out.println("Ingrese Datos del cliente");
                                System.out.println("NIT: ");
                                nit = sc.nextInt();
                                System.out.println("Nombre: ");
                                nombre = sc.nextLine();
                                System.out.println("Apellido: ");
                                apellido = sc.nextLine();
                                System.out.println("Direccion: ");
                                direccion = sc.nextLine();
                                int itera = 0;
                                int captura = -1;
                                for (Cliente actual : arrayClientes) {
                                    if ((actual.getNIT() == nit) && (actual.getNombre().equalsIgnoreCase(nombre))
                                            && (actual.getApellido().equalsIgnoreCase(apellido))
                                            && (actual.getDireccion().equalsIgnoreCase(direccion))) {
                                        captura = itera;
                                    }
                                    itera++;
                                }
                                if (captura != -1) {
                                    arrayClientes.remove(captura);
                                }
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Opcion no valida");

                        }
                    } while (opcion != 3);
                    break;
            }
        } while (opcion != 4);

    }

}
