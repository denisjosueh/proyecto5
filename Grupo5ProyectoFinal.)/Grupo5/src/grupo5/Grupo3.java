package grupo5;

/***
 * Codigo desarrollado con el objetivo de simular
 * el ingreso de datos de clientes, vehiculos */

import java.util.Scanner;
import java.io.*;

public class Grupo3 {

    /***
     * Variables globales, para su uso en cualquier
     * funcion del programa*/
    
    static int opcion;
    static String cui;
    static String numTelefono;
    static String numPlaca;
    static String fecha;
    static String numParqueo;

    public static void main(String[] args) throws IOException {
        /***
         * objetos de tipo Archivo, para poder realizar 
         * cada metodo de los correspondientes archivos segun su tipo*/
        Scanner esc = new Scanner(System.in);
        ArchivoCliente c = new ArchivoCliente();
        ArchivoVehiculo v = new ArchivoVehiculo();

        do {
            menu();
            opcion = esc.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese datos del Cliente");
                    esc.nextLine();
                    System.out.println("Nombre: ");
                    String nombre = esc.nextLine();
                    System.out.println("Apellidos: ");
                    String apellidos = esc.nextLine();
                    validarCui();
                    validarTelefono();
                    Cliente cliente = new Cliente(nombre, apellidos, cui, numTelefono);
                    c.registroCliente(cliente);
                    System.out.println("\n");
                    break;

                case 2:
                    System.out.println("Consulta de Clientes\nSe buscara mediante estos campos: Nombre, Apellidos, CUI");
                    /***
                     * en dado caso este inactivo el cliente, no muestra sus datos*/
                    System.out.println("Ingrese datos requeridos....");
                    esc.nextLine();
                    System.out.println("Nombre: ");
                    String nombreB = esc.nextLine();
                    System.out.println("Apellidos: ");
                    String apellidosB = esc.nextLine();
                    System.out.println("CUI: ");
                    String cuiB = esc.nextLine();
                    c.consultarCliente(nombreB, apellidosB, cuiB);
                    System.out.println("\n");
                    break;

                case 3:
                    ArchivoCliente mCliente = new ArchivoCliente();
                    System.out.println("Modificar Cliente: ");
                    System.out.println("Se buscara Cliente mediante Nombre");
                    esc.nextLine();
                    String datoModificar = esc.nextLine();
                    mCliente.modificarCliente(datoModificar);
                    break;
                case 4:
                    System.out.println("Lista de Clientes");
                    System.out.println("_________________");
                    c.mostrarClientes();
                    break;
                case 5:
                    System.out.println("Ingrese datos del vehiculo");
                    String placa;
                    boolean valida=false;
                    do {
                        System.out.println("Ingrese la placa del Vehículo");
                        placa=esc.nextLine();
                        if (ValidarPlaca(placa)) {
                            System.out.println("*****placa valida*****");
                            valida=true;   
                        }else {
                            System.out.println("La placa es invalida, ingrese de nuevo el numero de placa");
                        }
                    } while (!valida);
                    System.out.println("Marca del Vehiculo: ");
                    String marca = esc.nextLine();
                    System.out.println("Linea del Vehiculo: ");
                    String linea = esc.nextLine();
                    System.out.println("Modelo del Vehiculo: ");
                    String modelo = esc.nextLine();
                    System.out.println("Color del vehiculo: ");
                    String color = esc.nextLine();
                    Vehiculo vehiculo = new Vehiculo(numPlaca, marca, linea, modelo, color);
                    v.registroVehiculo(vehiculo);
                    System.out.println("\n");
                    break;

                case 6:
                    System.out.println("Consulta de vehiculos, mediante No.Placa");
                    System.out.println("Ingrese número de placa");
                    esc.nextLine();
                    String nPlacaB = esc.nextLine();
                    v.consultarVehiculo(nPlacaB);
                    break;

                case 7:
                    ArchivoVehiculo mVehiculo = new ArchivoVehiculo();
                    System.out.println("Modificación de vehiculo");
                    System.out.println("Se buscara mediante Placa: ");
                    esc.nextLine();
                    String datoModificarPlaca = esc.nextLine();
                    mVehiculo.modificarVehiculo(datoModificarPlaca);
                    break;
                case 8:
                    System.out.println("Lista de vehiculos registrados");
                    System.out.println("______________________________");
                    v.mostrarLista();
                    break;
                case 9:
                    System.out.println("Registro de Parqueo");
                    System.out.println("Parqueos Disponibles [155] \n125 para vehiculos \n30 para motocicletas ");
                    insertarParqueo(0);
                    break;
                case 10:
                    System.out.println("Programa Finalizado");
                    break;
                default:
                    System.out.println("Opcion Invalida");
                    break;

            }
        } while (opcion != 10);

    }

    //Menu Principal
    public static void menu() {
        System.out.println("     MENU PRINCIPAL    ");
        System.out.println(" _______________________");
        System.out.println("| 1.  Registrar Cliente  |");
        System.out.println("| 2.  Consultar Cliente  |");
        System.out.println("| 3.  Modificar Cliente  | ");
        System.out.println("| 4.  Lista de Clientes  |");
        System.out.println("| 5.  Registrar Vehiculo |");
        System.out.println("| 6.  Consultar Vehiculo |");
        System.out.println("| 7.  Modificar Vehiculo |");
        System.out.println("| 8.  Lista de Vehiculos |");
        System.out.println("| 9.  Registrar Parqueo  |");
        System.out.println("| 10. Salir              |");
        System.out.println("| Ingrese una opcion     |");
        System.out.println(" _______________________");
    }

    /***
     * Función para validar CUI
     * Lugar de trámite segun ultimos 4 digitos*/
    public static void validarCui() {
        ArchivoCliente dpto = new ArchivoCliente();
        Scanner cadena = new Scanner(System.in);
        boolean valor, valor1, valor2, valor3 = false, valor4;
        do {

            int sumtotal = 0;
            int contador = 0;
            int factor = 9;
            int resultado = 0;
            System.out.println("Ingrese el CUI de la persona valido: ");
            cui = cadena.nextLine();
            //validacion de longitud del cui.  valor1 = true si cumple
            if (cui.length() != 13) {
                System.out.println("La longitud del CUI no es de 13 digitos");
                valor1 = false;
            } else {
                valor1 = true;
            }
            //validacion del contenido de cui cada caracter sea entre 0-9. valor2 = true si cumple 
            contador = 0;
            for (int j = 0; j < cui.length(); j++) {
                switch (cui.charAt(j)) {
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                    case '0':
                        contador++;
                        break;
                }//fin del switch
            }//fin del while
            if (contador == 13) {
                valor2 = true;
            } else {
                valor2 = false;
                System.out.println("El CUI contiene digitos no validos");
            }
            //validacion de codigo Departamento y Municipio dentro archivo DeptoMuni.txt
            //valor3 = true si cumple.
            String cadena2 = "";
            ///0101
            if (valor1) {
                cadena2 = cadena2 + cui.charAt(9) + cui.charAt(10) + cui.charAt(11) + cui.charAt(12);
                if (dpto.buscar(cadena2)) {
                    valor3 = true;
                } else {
                    valor3 = false;
                    System.out.println("El codigo de departamento y municipio NO es valido");
                }
            }
            if (valor1) {
                for (int indice = 0; indice < cui.length() - 5; indice++) {
                    sumtotal = sumtotal + (Integer.parseInt(Character.toString(cui.charAt(indice)))) * factor;
                    factor--;
                }
            }

            resultado = sumtotal % 11;
            resultado = 11 - resultado;
            if (resultado == (Integer.parseInt(Character.toString(cui.charAt(8))))) {
                System.out.println("Cui valido");
                System.out.println("Digito verificador: " + resultado);

                valor4 = true;
            } else if (resultado == 11) {
                System.out.println("Cui valido");
                System.out.println("Digito verificador: 0");
                valor4 = true;
            } else {
                System.out.println("Cui invalido, verifique e intente nuevamente");
                System.out.println(cui.charAt(8));

                valor4 = false;
            }
            //validacion general del CUI
            if ((valor1) && (valor2) && (valor3) && (valor4)) {
                //valor = true;
                valor = true;
            } else {
                valor = false;
            }

        } while (!valor);
    }
   
    // Funcion para validar la placa usando contadores
    public static boolean ValidarPlaca(String placa) {
        placa = placa.toUpperCase(); // Convierte las letras a mayúsculas
        // tipo de placa valida(1 o 2 letras)
        String[] TipoPlaca = {"P", "C", "M", "A", "O", "U", "CC", "CD", "TC", "MI"};
        String[] LetrasInvalidas = {"A", "E", "I", "O", "U", "Ñ"};

        // Buscar el tipo de placa recoriendo el vector y comparando que si sea un tipo valido
        String TipoEncontrado = "";
        for (int i = 0; i < TipoPlaca.length; i++) {
            String Tipo = TipoPlaca[i];
            if (placa.length() >= Tipo.length()) {
                String inicio = placa.substring(0, Tipo.length());
                if (inicio.equals(Tipo)) {
                    TipoEncontrado = Tipo;
                    break;
                }
            }
        }// fin ciclo for

        if (TipoEncontrado.equals("")) {
            return false; // Tipo de placa inválida
        }// Fin if

        // Verificar longitud total de la placa
        if (placa.length() != TipoEncontrado.length() + 6) {
            return false;
        }// Fin if

        // Obtener la parte restante de la placa usando substring que me extrae una parte de una cadena existente
        String restante = placa.substring(TipoEncontrado.length());

        // Validar que los 3 dígitos sean válidos
        int contadorNumeros = 0;// contador que ira sumando su cada uno de los digitos es correcto
        for (int i = 0; i < 3; i++) {
            char numero = restante.charAt(i);
            switch (numero) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    contadorNumeros++;
                    break;
                default:
                    break;
            }// Fin Switch
        }// Fin ciclo for

        // Validar que las 3 letras sean validas sin vocales y tambien se toma en cuenta la letra Ñ ya que tampoco es una letra valida
        int contadorLetras = 0;// contador ira sumando si cada una de las letras cumple con la validacion
        for (int i = 3; i < 6; i++) {
            char letra = restante.charAt(i);
            boolean EsInvalida = false;

            for (int j = 0; j < LetrasInvalidas.length; j++) {
                if (letra == LetrasInvalidas[j].charAt(0)) {
                    EsInvalida = true;// Retorna true si encuentra alguna letra invalida
                    break;
                }// Fin if
            }// Fin ciclo for

            switch (letra) {
                case 'B':
                case 'C':
                case 'D':
                case 'F':
                case 'G':
                case 'H':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                    if (!EsInvalida) {
                        contadorLetras++;
                    }
                    break;
                default:
                    break;
            }// Fin Switch
        }// Fin ciclo flor

        // Verificar que ambos contadores sumen 
        if (contadorNumeros == 3 && contadorLetras == 3) {
            return true;// Retorna true si todas las condiciones se cumplieron
        } else {
            return false;// Retorna false si hay alguna condicion que no se cumplio ya que alguno de los contadores no llego a 3
        }
    }// Fin funcion para validar placa
    
    //Función para validar fecha
    public static boolean validarFecha(String fecha) {
        // Verificar formato dd/mm/yyyy
        if (fecha.length() != 10 || fecha.charAt(2) != '/' || fecha.charAt(5) != '/') {
            return false;
        }

        // Extraer día, mes y año de la cadena
        int dia = Integer.parseInt(fecha.substring(0, 2));
        int mes = Integer.parseInt(fecha.substring(3, 5));
        int anio = Integer.parseInt(fecha.substring(6));

        // Verificar rangos válidos
        if (dia < 1 || dia > 31 || mes < 1 || mes > 12 || anio < 1) {
            return false;
        }

        // Verificar días válidos para cada mes
        int[] diasPorMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (mes == 2 && esBisiesto(anio)) {
            diasPorMes[1] = 29; // Ajustar días de febrero en año bisiesto
        }
        if (dia > diasPorMes[mes - 1]) {
            return false;
        }

        return true;
    }

        //Validar que un año es bisiesto
    public static boolean esBisiesto(int anio) {
        return (anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0);
    }
    
    /***
     * Validar que número de telefono
     * sean solo números*/
    public static void validarTelefono() {
        Scanner entrada = new Scanner(System.in);
        boolean salir = false;
        System.out.println("Ingrese numero de Telefono: \nUnicamente 8 digitos para números en Guatemala");
         numTelefono = entrada.nextLine();

        while (!salir) {
            int contador = 0;
            if (numTelefono.length() == 8) {
                for (int i = 0; i < numTelefono.length(); i++) {
                    switch (numTelefono.charAt(i)) {
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                        case '0':
                            contador++;

                            break;
                    }
                }
            } else {
                System.out.println("Longitud del telefono invalida");
            }
            if (contador == 8) {
                System.out.println("El numero de telefono: " + numTelefono + " es valido");
                salir = true;
            } else {

                System.out.println("El numero de telefono: " + numTelefono + " no es valido");
                System.out.println("Digite nuevamente numero de telefono:");
                numTelefono = entrada.nextLine();
            }
        }
    }

    /*
       * Función que manda a insertar un parqueo
     */
    public static void insertarParqueo(int posicion) throws IOException {
        ArchivoParqueo operaciones = new ArchivoParqueo();
        Parqueo parqueo = leerParqueo();
        operaciones.abrirArchivo("ParqueoDirecto.txt");
        operaciones.nuevoRegistro(posicion, parqueo);
        operaciones.cerrarArchivo();
    }

    /*
	 * Función para registrar un parqueo
     */
    public static Parqueo leerParqueo() {
        Scanner esc = new Scanner(System.in);
        Parqueo parqueo = new Parqueo();

        System.out.println("Fecha: ");
        String fecha = esc.nextLine();
        parqueo.setFecha(fecha);
        System.out.println("Tipo de vehiculo (Moto o Carro)..");
        String tipoVehiculo = esc.nextLine();
        if (tipoVehiculo.equalsIgnoreCase("moto")) {
            System.out.println("No.Placa tipo motocicleta: M ");
            String noPlaca = esc.nextLine();
            parqueo.setNumPlaca(noPlaca);
        } else {
            System.out.println("No.Placa tipo carro: P ");
            String noPlaca = esc.nextLine();
            parqueo.setNumPlaca(noPlaca);
        }

        parqueo.setNumPlaca(numPlaca);
        System.out.println("No.Parqueo: ");
        int nParqueo = esc.nextInt();
                parqueo.setNumParqueo(nParqueo);
                System.out.println("___________");
                System.out.println("\nInformación Registrada...");
                System.out.println(parqueo.toString());
		return parqueo;
	}
}
