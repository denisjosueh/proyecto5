package grupo5;

public class Vehiculo {
//Atributos, todos privados
    private String numPlaca;
    private String marcaVehiculo;
    private String lineaVehiculo;
    private String modeloVehiculo;
    private String colorVehiculo;
    
    //Método constructor
    public Vehiculo(String numPlaca, String marcaVehiculo, String lineaVehiculo, String modeloVehiculo, String colorVehiculo){
        this.numPlaca = numPlaca;
        this.marcaVehiculo = marcaVehiculo;
        this.lineaVehiculo = lineaVehiculo;
        this.modeloVehiculo = modeloVehiculo;
        this.colorVehiculo = colorVehiculo;
    }
    
    //Getters y Setters
    public String getNumPlaca() {
        return numPlaca;
    }

    public void setNumPlaca(String numPlaca) {
        this.numPlaca = numPlaca;
    }

    public String getMarcaVehiculo() {
        return marcaVehiculo;
    }

    public void setMarcaVehiculo(String marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }

    public String getLineaVehiculo() {
        return lineaVehiculo;
    }

    public void setLineaVehiculo(String lineaVehiculo) {
        this.lineaVehiculo = lineaVehiculo;
    }

    public String getModeloVehiculo() {
        return modeloVehiculo;
    }

    public void setModeloVehiculo(String modeloVehiculo) {
        this.modeloVehiculo = modeloVehiculo;
    }

    public String getColorVehiculo() {
        return colorVehiculo;
    }

    public void setColorVehiculo(String colorVehiculo) {
        this.colorVehiculo = colorVehiculo;
    }
    
    //metodo toString, para imprimir todos los datos
    @Override
    public String toString() {
        return "Numero de placa: "+numPlaca
                +"\nMarca: "+marcaVehiculo
                +"\nLinea: "+lineaVehiculo
                +"\nModelo: "+modeloVehiculo
                +"\nColor: "+colorVehiculo;
                }
}
