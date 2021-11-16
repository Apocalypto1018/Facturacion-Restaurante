/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
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
        int opc=0;

        //primer usuario administrador...
        arrayColaboradores.add(new Colaborador("gerente", "1234", "Admin", "Admin", "123456789", 'g'));
        //primer usuario mesero...
        arrayColaboradores.add(new Colaborador("mesero" , "1234","Mesero1", "Mesero1", "123456789", 'm'));

        do {
            
            System.out.print("   Bienvenido al Sistema del Restaurante\n\n");
            System.out.print("*Ingrese 1 para ir al login\n");
            System.out.print("*Ingrese 0 para salir\n->");
            opc=sc.nextInt();
            
            sc.nextLine();
            
            switch(opc){
                case 0:{
                    System.out.println("Saliendo del sistema, Adios!!");
                    salir=true;
                    break;
                }
                case 1:{
                    System.out.print("*Ingrese el usuario\n->");
                    usuario = sc.nextLine();

                    System.out.print("*Ingrese la contrasenia\n->");
                    contrasenia = sc.nextLine();

                    if (confirmarCredencialesGerente(usuario, contrasenia)) {

                        menuGerente();

                    } else if (confirmarCredencialesMesero(usuario, contrasenia)) {

                        menuMesero();

                    } else {

                        System.out.println("*Datos incorrectos, intente de nuevo \n(gerente admin: gerente contrasenia admin: 1234)"
                                + "\n(mesero principal: mesero contrasenia: 1234)");
                    }
                    break;
                }
                default:{
                    System.out.println("Parametro no valido, intente de nuevo");
                    break;
                }
            }

        } while (!salir);

    }

    public static boolean confirmarCredencialesGerente(String usuario, String contrasenia) {

        for (Colaborador actual : arrayColaboradores) {
            if (actual.getUsuario().equals(usuario) && actual.getContrasenia().equals(contrasenia) && actual.getRol()=='g') {
            
                return true;
            }
        }
        return false;
    }

    public static boolean confirmarCredencialesMesero(String usuario, String contrasenia) {

        for (Colaborador actual : arrayColaboradores) {
            if (actual.getUsuario().equals(usuario) && actual.getContrasenia().equals(contrasenia) && actual.getRol()=='m') {
            
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
    
    //ver platillos 
    public static void submenuVerPlatillos(){
      
	for (Platillo actual : arrayPlatillos) {
              
            System.out.println("\n\n*Codigo: " + actual.getCodigo());
            System.out.println("*Nombre: " + actual.getNombre());
            System.out.println("*Descripcion: " + actual.getDescripcion());
            System.out.println("*Precio: " + actual.getPrecio());
          
	}
        
        System.out.print("\n\n");
    }
    
    //funcion factuar
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
        String codigosTotales=" ";
        System.out.println("      FACTURA\n");
        System.out.println("*NIT del cliente: "+nit);
        System.out.println("*Nombre del cliente: "+nombreCliente);
        System.out.println("*Codigos de los platillos:");
        for(int i=0;i<arrayCodigosPedidos.size();i++){
            nCodigo++;
            System.out.println("Codigo "+nCodigo+":"+arrayCodigosPedidos.get(i));
            codigosTotales=codigosTotales+" Codigo "+nCodigo+":"+arrayCodigosPedidos.get(i);
        }
        System.out.println("*Total a pagar: "+totalFacturar);
        System.out.println("*Mesero: "+nombreMesero);
        
        //se llena el archivo con la factura
        
        String texto="      FACTURA:"+"*NIT del cliente:\n "+nit+"   *Nombre del cliente: "
                +nombreCliente+"   *Codigos de los platillos:"+codigosTotales+"   *Total a pagar: "+totalFacturar
                +"   *Mesero: "+nombreMesero;
        
        try {
            File temp = new File(System.getProperty("java.io.tmpdir") + "Factura.txt");

            FileOutputStream flujoSalida = new FileOutputStream(temp);
            FileWriter fw = new FileWriter(temp);

            for(int i=0;i<texto.length();i++){
                flujoSalida.write(texto.charAt(i));
            }
            
            fw.close();
            flujoSalida.close();

            Desktop.getDesktop().open(temp);
           
        }catch (IOException ex) {
             ex.printStackTrace();
        }
        
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
    
    
    //menu gerente
    public static void menuGerente() {

        int opcion = -1;
        do {
            
            System.out.println("1. Clientes.\n2. Colaboradores.\n"
                    + "3. Llenar platillo.\n4. Ver platillos.\n.5fatura.\n"
                    + "0. Cerrar sesion");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    subMenuClienteG();
                    break;

                case 2:
                    subMenuColaboradoresG();
                    break;
                case 3:
                    llenarPlatillo();
                    break;
                            
                case 4:
                    submenuVerPlatillos();
                    break;
                case 5:
                    facturacion();
                    break;
            }
        } while (opcion != 0);

    }
    
    //sub menu de clientes del gerente
    public static void subMenuClienteG() {

        int nit;
        String nombre, apellido, direccion;
        char respuesta=' ';
        int opcionCliente;
        do {
            System.out.println("1. Ingresar cliente.\n2. Eliminar Cliente. \n3. Ver lista de clientes."
                    + "\n0. Regrasar al menu principa.");
            opcionCliente = sc.nextInt();
            switch (opcionCliente) {
                case 0: {
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
                case 2:{
                    System.out.println("Eliminar.");
                    System.out.println("Ingrese Datos del cliente");
                    System.out.println("NIT: ");
                    nit = sc.nextInt();
                    sc.nextLine();

                    int itera = 0;
                    int captura = -1;
                    for (Cliente actual : arrayClientes) {
                        if ((actual.getNIT() == nit)) {
                            captura = itera;
                        }
                        itera++;
                    }
                    if (captura != -1) {
                        System.out.println("Nombre: "+arrayClientes.get(captura).getNombre());
                        System.out.println("Apellido: "+arrayClientes.get(captura).getApellido());
                        System.out.println("Direccion: "+arrayClientes.get(captura).getDireccion());
                        System.out.println("Desea eliminar este cliente? s/n");
                        respuesta=sc.next().charAt(0);
                        arrayClientes.remove(captura);
                        System.out.println("Cliente eliminado exitosamente");
                    }
                    
                    break;
                }
                
                case 3:{
                    for (Cliente actual : arrayClientes) {
              
                        System.out.println("\n\n*NIT: " + actual.getNIT());
                        System.out.println("*Nombre: " + actual.getNombre());
                        System.out.println("*Apellido: " + actual.getApellido());
                        System.out.println("*Direccion: " + actual.getDireccion());

                    }
        
                    System.out.print("\n\n");
                    break;
                }

                default:
                    System.out.println("Opcion no valida");

            }
        } while (opcionCliente != 0);
    }
    
    //sub menu de los colaboradores del gerente
    public static void subMenuColaboradoresG() {
        int opcionColaborador;
        String user, pass, nombre, apellido, telefono, id;
        char respuesta = ' ';
        char rol;
        do {
            System.out.println("1. Ingresar colaborador.\n2. Modificar colaborador"
                    + "\n3. Eliminar colaborador.\n4. Listar colaboradores. \n0. Volver a menu principal");
            opcionColaborador = sc.nextInt();
            sc.nextLine();
            switch (opcionColaborador) {
                case 0:{ 
                    break;
                }
                case 1:
                    System.out.println("Ingresar. ");
                    System.out.println("User: ");
                    user = sc.nextLine();
                    System.out.println("Password: ");
                    pass = sc.nextLine();
                    System.out.println("Nombre: ");
                    nombre = sc.nextLine();
                    System.out.println("Apellido: ");
                    apellido = sc.nextLine();
                    System.out.println("Telefono: ");
                    telefono = sc.nextLine();
                    System.out.println("Rol: g/m");
                    rol = sc.next().charAt(0);
                    sc.nextLine();

                    arrayColaboradores.add(new Colaborador(user, pass, nombre, apellido, telefono, rol));
                     {
                        int itera = 0;
                        int captura = -1;
                        for (Colaborador actual : arrayColaboradores) {
                            if ((actual.getNombre().equalsIgnoreCase(nombre)) && (actual.getApellido().equalsIgnoreCase(apellido))
                                    && (actual.getTelefono().equalsIgnoreCase(telefono))) {
                                captura = itera;
                            }
                            itera++;
                        }

                        if (captura != -1) {
                            System.out.println("Usuario registrado exitosamente");
                            System.out.println("ID: " + arrayColaboradores.get(captura).getID());
                        } else {
                            System.out.println("Error inesperado al registrar usuario");
                        }
                    }
                    break;
                case 2:{
                    System.out.println("Modificar colaborador");
                    System.out.println("Ingrese ID del colaborador");
                    id = sc.nextLine();
                     
                        int itera = 0;
                        int captura = -1;
                        for (Colaborador actual : arrayColaboradores) {
                            if (actual.getID().equals(id)) {
                                captura = itera;
                            }
                            itera++;
                        }
                        if (captura != -1) {
                            modificaColaborador(captura);
                        } else {
                            System.out.println("No existen datos para el ID ingresado");
                        }
                    
                    break;
                }
                case 3:{
                    System.out.println("Eliminar colaborador");
                    System.out.println("Ingrese el id del colaborador: ");
                    id = sc.nextLine();
                     
                        int itera = 0;
                        int captura = -1;
                        for (Colaborador actual : arrayColaboradores) {
                            if (actual.getID().equals(id)) {
                                captura = itera;
                            }
                            itera++;
                        }
                        if (captura != -1) {
                            System.out.println("Nombre: "+arrayColaboradores.get(captura).getNombre());
                            System.out.println("Apellido: "+arrayColaboradores.get(captura).getApellido());
                            System.out.println("Telefono: "+arrayColaboradores.get(captura).getTelefono());
                            System.out.println("Rol: "+arrayColaboradores.get(captura).getRol());
                            System.out.println("Desea eliminar a este colaborador? s/n");
                            respuesta = sc.next().charAt(0);
                            if (respuesta == 's') {
                                arrayColaboradores.remove(captura);
                                System.out.println("Colaborador eliminado exitosamente");
                            } else {
                                break;
                            }
                        } else {
                            System.out.println("No existen datos para el ID ingresado");
                        }
                    
                    break;
                }  
                case 4:{
                    for (Colaborador actual : arrayColaboradores) {
              
                        System.out.println("\n\n*ID: " + actual.getID());
                        System.out.println("*Nombre: " + actual.getNombre());
                        System.out.println("*Apellido: " + actual.getApellido());
                        System.out.println("*Rol: " + actual.getRol());

                    }
        
                    System.out.print("\n\n");
                    break;
                }
                default:
                    System.out.println("Opcion no valida");

            }
        } while (opcionColaborador != 0);
    }
    
    //sub menu para modificar los colaboradores
    public static void modificaColaborador(int index) {
        int opcionModifica = -1;
        String user, pass, nombre, apellido, telefono;
        char rol;
        do {
            System.out.println("1.User.\n2.Password.\n3. Nombre.\n4. Apellido"
                    + "\n5. Telefono.\n6. Rol.\n0. volver.");
            opcionModifica = sc.nextInt();
            sc.nextLine();
            switch (opcionModifica) {
                case 0:
                    System.out.println("Volviendo al menu anterior");
                    break;
                case 1:
                    System.out.println("Nuevo user:");
                    user = sc.nextLine();
                    arrayColaboradores.get(index).setUsuario(user);
                    break;
                case 2:
                    System.out.println("Nuevo password: ");
                    pass = sc.nextLine();
                    arrayColaboradores.get(index).setContrasenia(pass);
                    break;
                case 3:
                    System.out.println("Nuevo nombre: ");
                    nombre = sc.nextLine();
                    arrayColaboradores.get(index).setNombre(nombre);
                    break;
                case 4:
                    System.out.println("Nuevo apellido: ");
                    apellido = sc.nextLine();
                    arrayColaboradores.get(index).setApellido(apellido);
                    break;
                case 5:
                    System.out.println("Nuevo password: ");
                    telefono = sc.nextLine();
                    arrayColaboradores.get(index).setTelefono(telefono);
                    break;
                case 6:
                    System.out.println("Nuevo rol: ");
                    rol = sc.next().charAt(0);
                    arrayColaboradores.get(index).setRol(rol);
                    break;
                default:
                    System.out.println("Ingrese una opcion valida");

            }
        } while (opcionModifica != 0);
    }
}
    
    
    
    
    
    
    
    
    
    
    