package grupo5;

public class Parqueo {
    
    //Atributos, todos privados, a excepcion de la constante 
    public static final int TAMANIO = 200;
    private int numParqueo;
    private String numPlaca;
    private String fecha;
    
    //Método constructor vacio, para instanciar obejetos vacios
    public Parqueo(){
        numParqueo = 0;
        numPlaca = "";
        fecha = "";
    }

    //Método constructor con parámetros
    public Parqueo(int numParqueo, String numPlaca, String fecha) {
        this.numParqueo = numParqueo;
        this.numPlaca = numPlaca;
        this.fecha = fecha;
    }

    //Getters y Setters
    public int getNumParqueo() {
        return numParqueo;
    }

    public void setNumParqueo(int numParqueo) {
        this.numParqueo = numParqueo;
    }

    public String getNumPlaca() {
        return numPlaca;
    }

    public void setNumPlaca(String numPlaca) {
        this.numPlaca = numPlaca;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    //Método toString, para imprimir datos
    @Override
    public String toString(){
        return "Fecha: "+fecha
                +"\nNo. Placa: "+numPlaca
                +"\nNo. Parqueo: "+numParqueo;
    }
    
}
