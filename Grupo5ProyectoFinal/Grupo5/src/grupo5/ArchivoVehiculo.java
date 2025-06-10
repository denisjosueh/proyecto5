package grupo5;

//Biblioteca a ultilizar, para manejar Archivos
import java.io.*;
import java.util.Scanner;

public class ArchivoVehiculo {

    //Método para registrar vehiculo
    public void registroVehiculo(Vehiculo nuevo) {
        try {
            File f = new File("vehiculo.txt");
            FileWriter fw;
            BufferedWriter bw;
            if ((f.exists()) && (f.length()) != 0) {
                fw = new FileWriter(f, true);
                bw = new BufferedWriter(fw);
                bw.newLine();
                bw.write(nuevo.getNumPlaca() + "%" + nuevo.getMarcaVehiculo() + "%" + nuevo.getLineaVehiculo() + "%" + nuevo.getModeloVehiculo() + "%" + nuevo.getColorVehiculo());
            } else {
                fw = new FileWriter(f);
                bw = new BufferedWriter(fw);
                bw.write(nuevo.getNumPlaca() + "%" + nuevo.getMarcaVehiculo() + "%" + nuevo.getLineaVehiculo() + "%" + nuevo.getModeloVehiculo() + "%" + nuevo.getColorVehiculo());
            }
            bw.close();
            fw.close();
            System.out.println("Vehiculo Registrado");
        } catch (IOException e) {
            System.out.println(e);
        }
    } // fin del método
    
    //Método para mostrar lista de vehiculos
    public void mostrarLista(){
        try{
            File f = new File("vehiculo.txt");
            if(f.exists()){
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String linea;
                while((linea = br.readLine()) != null){
                    String lista [] = linea.split("%");
                    Vehiculo vehiculo = new Vehiculo(lista[0], lista[1], lista[2], lista[3], lista[4]);
                    System.out.println(vehiculo.toString());
                    System.out.println("__________________");
                    System.out.println("\n");
                }
               
            }else{
                System.out.println("No existe el archivo");
            }
        }catch(IOException e){
            System.out.println(e);
        }
    } //fin del método
    
    //Método para buscar vehiculo por placa
    public void consultarVehiculo(String numPlaca){
        try{
            File f = new File("vehiculo.txt");
            if(f.exists()){
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String linea;
                boolean encontrado = false;
                while((linea = br.readLine()) != null){
                    String lista [] = linea.split("%");
                    /***
                     * se deben cumplir ambas condiciones
                     * placa previamente registrada
                     * que este ativo el vehiculo*/
                    if(lista[0].equalsIgnoreCase(numPlaca) && (lista[6].equalsIgnoreCase("activo"))){
                        encontrado = true;
                        Vehiculo vehiculo = new Vehiculo(lista[0], lista[1], lista[2], lista[3], lista[4]);
                        System.out.println("\n");
                        System.out.println("Vehiulo encontrado");
                        System.out.println("__________________");
                        System.out.println(vehiculo.toString());
                        System.out.println("__________________");
                    }
                }
                if(encontrado == false){
                    System.out.println("No se encontraron resultados");
                    System.out.println("Vehiculo inactivo o inexistente");
                }
            }else{
                System.out.println("El archivo no existe");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    //Método para modificar vehiculo
    public void modificarVehiculo(String datoModificar) {
        try {
            File f = new File("vehiculo.txt");
            if (f.exists()) {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String linea;
                int numeroLineas = 0;
                while ((linea = br.readLine()) != null) {
                    numeroLineas++;
                }//fin del while                
                String vehiculo[] = new String[numeroLineas];
                //vamos a inicializar un nuevo buffer
                br.close();//se cierra el archivo
                fr.close();//se cierra el archivo
                br = new BufferedReader(new FileReader(f));
                int i = 0;
                while ((linea = br.readLine()) != null) {
                    vehiculo[i] = linea; //todos los registros se agregan a contacto
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
                System.out.println("Ingresa la nueva marca: ");
                String nuevaMarca = nuevo.nextLine();
                System.out.println("Ingresa la nueva linea: ");
                String nLinea = nuevo.nextLine();
                System.out.println("Ingresa el nuevo modelo: ");
                String nuevoModelo = nuevo.nextLine();
                System.out.println("Ingresa el nuevo color: ");
                String nuevoColor = nuevo.nextLine();


                for (int j = 0; j < vehiculo.length; j++) { //esta parte sirve para reescribir
                    String nuevaLinea[] = vehiculo[j].split("%");
                    if (nuevaLinea[0].equalsIgnoreCase(datoModificar)) {//evaluar placa                                                
                        bw.newLine();
                        bw.write(nuevaLinea[0] + "%" + nuevaMarca + "%" + nLinea + "%" + nuevoModelo + "%" + nuevoColor);
                        eliminado = true;
                        System.out.println("Datos modificados!");
                    } else {
                        if (primerLinea == true) {
                            bw.write(vehiculo[j]);
                            primerLinea = false;
                        } else {
                            bw.newLine();
                            bw.write(vehiculo[j]);
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
    }
}
