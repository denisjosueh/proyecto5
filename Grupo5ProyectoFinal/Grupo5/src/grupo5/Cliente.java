package grupo5;

public class Cliente {

    //Atributos, todos privados
    private String nombre;
    private String apellidos;
    private String cui;
    private String numTelefono;

    //Método constructor
    public Cliente(String nombre, String apellidos, String cui, String numTelefono) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cui = cui;
        this.numTelefono = numTelefono;
    }

    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public String getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }
    
    //Método toString, para imprimir los datos
    @Override
   public String toString(){
     return "Nombre: "+nombre
             +"\nApellidos: "+apellidos
             +"\nCUI: "+cui
             +"\nTelefono: "+numTelefono;

   }
   
    }

