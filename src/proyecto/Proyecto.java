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
    private static ArrayList<Platillo> arrayPlatillos = new ArrayList<Platillo>();
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
            System.out.println("4. llenar Platillos");
            System.out.print("0. Para cerrar sesion\n->");
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
                    break;
                }
                
                case 3:{
                    facturacion();
                    break;
                }
                
                case 4:{
                    llenarPlatillo();
                    break;
                }
                
                default:{
                    System.out.println("Parametro no valido, intente de nuevo");
                    break;
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
                
                default:{
                    System.out.println("Parametro no valido, intente de nuevo");
                    break;
                }
                
            }
            
        }while(opcion!=0);
    }
    
    //ver platillos de mesero
    public static void submenuVerPlatillos(){
      
	for (Platillo actual : arrayPlatillos) {
              
            System.out.println("\n\n*Codigo: " + actual.getCodigo());
            System.out.println("*Nombre: " + actual.getNombre());
            System.out.println("*Descripcion: " + actual.getDescripcion());
            System.out.println("*Precio: " + actual.getPrecio());
          
	}
        
        System.out.print("\n\n");
    }
    
    //funcion factuar de mesero
    public static void facturacion(){
        ArrayList<String> arrayCodigosPedidos = new ArrayList<>();

        int nit=0;
        int itera=0;
        int captura = -1;
        float totalFacturar=0;
        
        int masPedidos=0;
        
        int nCodigo=0;

        String nombreMesero;
        String nombreCliente;
        String codigo;
        
        //buscar el NIT del cliente a facturar
        System.out.print("*Ingrese el NIT del cliente\n->");
        nit=sc.nextInt();

        sc.nextLine();
       
        for (Cliente actual : arrayClientes) {
            if ((actual.getNIT() == nit)) {
                captura = itera;
            }
            itera++;
        }
        
        if(captura!=-1){
            nombreCliente=arrayClientes.get(captura).getNombre();
            
        }else{
            System.out.println("Cliente no registrado (aparecera en la factura como CF)");
            nombreCliente="CF";
            nit=0;
        }
        
        //pedir los codigos de los platillos para facturar
        do{
            System.out.println("*Ingrese 1 para registrar nuevo codigo de pedido en la factura");
            System.out.print("*Ingrese 0 para terminar de registrar codigos en la factura\n->");
            masPedidos=sc.nextInt();
            
            sc.nextLine();
            
            switch(masPedidos){
                case 0:{
                    System.out.println("Continuando con la factura...");
                    break;
                }
                
                case 1:{
                    System.out.print("*Ingrese el codigo del platillo\n->");
                    codigo=sc.nextLine();
                    
                    for (Platillo actual : arrayPlatillos) {
                        if (actual.getCodigo().equals(codigo)) {
                            captura = itera;
                        }
                        itera++;
                    }

                    if(captura!=-1){
                        arrayCodigosPedidos.add(codigo); //se guarda el codigo del platillo
                        
                        totalFacturar+=arrayPlatillos.get(captura).getPrecio(); //se acumula el precio del platillo
                        captura=-1;
                        itera=0;
                    }else{
                        System.out.println("Platillo no registrado");
                        captura=-1;
                        itera=0;
                    }  
                    break;
                }   
                
                default:{
                    System.out.println("Parametro no valido, intente de nuevo");
                    break;
                }
                
            }
            
        }while(masPedidos!=0);
        
        //pedir nombre del mesero
        do{
            System.out.print("*Ingrese el Nombre del mesero a asociar en la factura\n->");
            nombreMesero=sc.nextLine();

            for (Colaborador actual : arrayColaboradores) {
                if (actual.getNombre().equals(nombreMesero)) {
                    captura = itera;
                }
                itera++;
            }

            if(captura==-1){
                System.out.println("El nombre del mesero a asociar no aparece, intente otra ves");
            }
        }while(captura==-1);
        
        //mostrar factura en consola
        System.out.println("      FACTURA\n");
        System.out.println("*NIT del cliente: "+nit);
        System.out.println("*Nombre del cliente: "+nombreCliente);
        System.out.println("*Codigos de los platillos:");
        for(int i=0;i<arrayCodigosPedidos.size();i++){
            nCodigo++;
            System.out.println("Codigo "+nCodigo+":"+arrayCodigosPedidos.get(i));
        }
        System.out.println("*Total a pagar: "+totalFacturar);
        System.out.println("*Mesero: "+nombreMesero);
        
    }
    
    //funcion que llena los platillos
    public static void llenarPlatillo(){
        String nombre;
        String descripcion;
        float precio=0;

        System.out.println("Ingrese Datos del Platillo");
        
        System.out.print("Nombre:\n->");
        nombre = sc.nextLine();

        System.out.print("Descripcion:\n->");
        descripcion = sc.nextLine();
        
        System.out.print("Precio:\n->");
        precio = sc.nextFloat();
        
        sc.nextLine();
        
        arrayPlatillos.add(new Platillo(nombre, descripcion, precio));
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
