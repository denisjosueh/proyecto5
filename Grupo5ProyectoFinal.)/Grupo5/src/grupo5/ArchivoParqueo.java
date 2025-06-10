package grupo5;

//Biblioteca a utilizar, para el manejo de archivos en este caso direto
import java.io.*;

public class ArchivoParqueo {

    public RandomAccessFile archivo;

    /*
	 * Se abre el archivo recibiendo un nombre
     */
    public void abrirArchivo(String nombreArchivo) throws IOException {
        archivo = new RandomAccessFile(nombreArchivo, "rw");
    }

    /*
	 * Se cierra el archivo
     */
    public void cerrarArchivo() throws IOException {
        if (archivo != null) {
            archivo.close();
        }
    }

    /*
	 * Se lee un registro para llenar un objeto parqueo
	 * a partir de una posición dada
     */
    public Parqueo obtenerRegistro(int posicion) throws IllegalArgumentException, NumberFormatException, IOException {
        Parqueo parqueo = new Parqueo();
        if (posicion < 1 || posicion > 155) {
            throw new IllegalArgumentException("Fuera de rango");
        }
        // buscar registro apropiado en el archivo
        archivo.seek((posicion) * Parqueo.TAMANIO);
        parqueo.setFecha(leerCadena(archivo));
        parqueo.setNumPlaca(leerCadena(archivo));
        parqueo.setNumParqueo(archivo.readInt());

        return parqueo;

    }

    /*
	 *
	 *asegurarse que la cadena sea de la longitud apropiada
	 *remplazando los caracteres que falten de la longitud con
	 *espacios en blanco
     */
    private String leerCadena(RandomAccessFile archivo) throws IOException {
        char nombre[] = new char[50], temp;
        for (int cuenta = 0; cuenta < nombre.length; cuenta++) {
            temp = archivo.readChar();
            nombre[cuenta] = temp;
        }

        return new String(nombre).replace('\0', ' ');
    }

    /*
	 * Almacena un parqueo en la posición dada
     */
    public void nuevoRegistro(int posicion, Parqueo parqueo) throws IllegalArgumentException, IOException {
        // buscar registro apropiado en el archivo
        archivo.seek((posicion) * Parqueo.TAMANIO);
        archivo.writeInt(parqueo.getNumParqueo());
        escribirCadena(archivo, parqueo.getFecha());
        escribirCadena(archivo, parqueo.getNumPlaca());
    }

    /*
	 * Escribe una cadena de caracteres de máximo 50 caracteres
     */
    private void escribirCadena(RandomAccessFile archivo, String cadena) throws IOException {
        StringBuffer bufer = null;
        if (cadena != null) {
            bufer = new StringBuffer(cadena);
        } else {
            bufer = new StringBuffer(50);
        }
        bufer.setLength(50);
        archivo.writeChars(bufer.toString());
    }

}
