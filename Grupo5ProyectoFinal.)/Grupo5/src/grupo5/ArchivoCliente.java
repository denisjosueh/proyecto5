package grupo5;

/***
 * bibliotecas a utilizar
 * se coloca * para poder usar cualquier biblioteca y simplificar codigo*/
import java.io.*;
import java.util.*;

public class ArchivoCliente {
    //Metodo para registrar cliente   
    public void registroCliente(Cliente nuevo) {
        try {
            File f = new File("Clientes.txt");
            FileWriter fw;
            BufferedWriter bw;
            if ((f.exists()) && (f.length() != 0)) {
                fw = new FileWriter(f, true);
                bw = new BufferedWriter(fw);
                bw.newLine();
                bw.write(nuevo.getCui()+ "%" + nuevo.getNombre() + "%" + nuevo.getApellidos() + "%" + nuevo.getNumTelefono());
            } else {
                fw = new FileWriter(f);
                bw = new BufferedWriter(fw);
                bw.write(nuevo.getCui() + "%" + nuevo.getNombre() + "%" + nuevo.getApellidos() + "%" + nuevo.getNumTelefono());
            }
            bw.close();
            fw.close();
            System.out.println("Cliente Registrado");
        } catch (IOException e) {
            System.out.println(e);
        }
    } // fin del método

    //Metodo para consultat cliente
    public void consultarCliente(String nombreB, String apellidoB, String cuiB) {
        try {
            File f = new File("Clientes.txt");
            if (f.exists()) {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String linea;
                boolean encontrado = false;
                while ((linea = br.readLine()) != null) {
                    String consulta[] = linea.split("%");
                    //se deben cumplir todos los campos requeridos por eso se utiliza el operador and(&)
                    if (consulta[0].equalsIgnoreCase(nombreB) && consulta[1].equalsIgnoreCase(apellidoB) && consulta[4].equalsIgnoreCase(cuiB) && consulta[7].equalsIgnoreCase("activo")) {
                        encontrado = true;
                        Cliente cliente = new Cliente(consulta[0], consulta[1], consulta[2], consulta[3]);
                        System.out.println("\n");
                        System.out.println("Cliente Encontrado");
                        System.out.println("__________________");
                        System.out.println(cliente.toString());
                        System.out.println("__________________");
                    }
                }
                if (encontrado == false) {
                    System.out.println("No existe el cliente a buscar o esta inactivo");
                }
            } else {
                System.out.println("Archivo no exixte");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    } //fin del método

    //Método para modificar cliente
    public void modificarCliente(String datoModificar) {
        try {
            File f = new File("Clientes.txt");
            if (f.exists()) {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String linea;
                int numeroLineas = 0;
                while ((linea = br.readLine()) != null) {
                    numeroLineas++;
                }//fin del while                
                String cliente[] = new String[numeroLineas];
                //vamos a inicializar un nuevo buffer
                br.close();//se cierra el archivo
                fr.close();//se cierra el archivo
                br = new BufferedReader(new FileReader(f));
                int i = 0;
                while ((linea = br.readLine()) != null) {
                    cliente[i] = linea; //todos los registros se agregan a cliente
                    i++;
                }//fin del while
                br.close();
                fr.close();
                FileWriter fw = new FileWriter(f);
                BufferedWriter bw = new BufferedWriter(fw);
                boolean eliminado = false;
                boolean primerLinea = true;
                //seccion para recolectar los nuevos datos
                Scanner nuevo = new Scanner(System.in);
                System.out.println("Ingresa el nuevo telefono: ");
                String nuevoTelefono = nuevo.nextLine();

                for (int j = 0; j < cliente.length; j++) { //esta parte sirve para reescribir los datos nuevos por los antiguos
                    String nuevaLinea[] = cliente[j].split("%");
                    if (nuevaLinea[0].equalsIgnoreCase(datoModificar)) {//evaluar nombre                                             
                        bw.newLine();
                        bw.write(nuevaLinea[0] + "%" + nuevaLinea[1] + "%" + nuevaLinea[3] + "%" + nuevaLinea[4] + "%" + nuevaLinea[5] + "%" + nuevoTelefono);
                        eliminado = true;
                        System.out.println("Datos modificados!");
                    } else {
                        if (primerLinea == true) {
                            bw.write(cliente[j]);
                            primerLinea = false;
                        } else {
                            bw.newLine();
                            bw.write(cliente[j]);
                        }
                    }//cierre del else
                }//fin del for
                if (eliminado == false) {
                    System.out.println("No se encontro registro.");
                }
                bw.close();
                fw.close();
                if (numeroLineas == 1 && eliminado == true) {
                    f.delete();
                }
            } else {
                System.out.println("No hay registros para modificar.");
            }//cierre de else
        } catch (IOException e) {
        }
    }//Cierre de modificar

    //Método para mostrar lista de clientes
    public void mostrarClientes() {
         try{
            File f = new File("Clientes.txt");
            if(f.exists()){
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String linea;
                while((linea = br.readLine()) != null){
                    String lista [] = linea.split("%");
                    Cliente cliente = new Cliente(lista[0], lista[1], lista[2], lista[3]);
                    System.out.println(cliente.toString());
                    System.out.println("__________________");
                    System.out.println("\n");
                }
               
            }else{
                System.out.println("No existe el archivo");
            }
        }catch(IOException e){
            System.out.println(e);
        }
    }
    /***
     * Método para validar el departamento
     * y municipio donde se trámito el CUI*/
     public boolean buscar(String buscarCodigoDeptoMuni){
        boolean encontrado = false;
        try {
            File archi= new File("DeptoMuni.txt");
            if (archi.exists()){
                FileReader leerAr = new FileReader(archi);
                BufferedReader br = new BufferedReader(leerAr);
                String linea;                
                
                while((linea = br.readLine())!=null){
                    String [] arregloDeptoMuni = linea.split("%");
                    ///Ejemplo:arregloDeptoMuni[0]=="0101"
                    if (arregloDeptoMuni[0].equalsIgnoreCase(buscarCodigoDeptoMuni)) {
                        encontrado = true;
                        Departamento depart = new Departamento(arregloDeptoMuni[0],arregloDeptoMuni[1]);                        
                        System.out.println(depart.toString());
                        break;
                    }                    
                }
                br.close();
                leerAr.close();
            } else {
                System.out.println("No hay registros.");
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return encontrado;
     }
 }



