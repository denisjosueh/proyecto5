package grupo5;

/**
 *
 * @author Grupo5 Ingenieria en Sistemas UMG Boca del Monte
 */
public class Departamento {
    //Atributos, todos privados
    private String codigoDepto;
    private String nombreDepto;
    
    //Constructor con parámetros

    public Departamento(String codigoDepto, String nombreDepto) {
        this.codigoDepto = codigoDepto;
        this.nombreDepto = nombreDepto;
    }
    //Getters y Setters

    public String getCodigoDepto() {
        return codigoDepto;
    }

    public void setCodigoDepto(String codigoDepto) {
        this.codigoDepto = codigoDepto;
    }

    public String getNombreDepto() {
        return nombreDepto;
    }

    public void setNombreDepto(String nombreDepto) {
        this.nombreDepto = nombreDepto;
    }
    //Método toString 
    @Override
    public String toString(){
        return "Codigo del Departamento: "+codigoDepto
                +"\nNombre del Municipio: "+nombreDepto;
    }
}
