/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogramaciondealgoritmos;

/**
 *
 * @author Usuario
 */
public class Cliente extends Personal{
    //Atributo de clase cliente
    public String tipo;
    //COnstructo que recibe parametros
    public Cliente() {
    }
    // constructoq recibe parametros
    public Cliente( String cedula, String nombre, String apellido, String telefono,  String correo, String numCelular) {
        super(cedula, nombre, apellido, telefono, correo, numCelular);
      
    }
// metodos especiales set y get para obtener 
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    // Metdo toString que herda de la clase padre
    @Override
    public String mostrarDatos(){
        String ms=super.mostrarDatos();
        return ms+tipo;
    }
}
