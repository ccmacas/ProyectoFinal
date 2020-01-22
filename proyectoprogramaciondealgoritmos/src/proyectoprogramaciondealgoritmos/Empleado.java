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
public  class Empleado extends Personal {
    //Atriubutos de la clase empleado
    public double salario;
    public String cargo;
//Constructo Vacio
    public Empleado() {
    }
//Cosntructor q resive parametros
    public Empleado(double salario, String cargo, String cedula, String nombre, String apellido, String telefono, String correo, String numCelular) {
        super(cedula, nombre, apellido, telefono, correo, numCelular);
        this.salario = salario;
        this.cargo = cargo;
    }
//Metodos especiale set y get para obetenr y establcer 
    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

   
    public double Salario() {
        return getSalario();
    }
    @Override
    //Metodo que hereda de la clase padre 
     public String mostrarDatos(){
        String ms=super.mostrarDatos();
        return ms+" "+getCargo()+" "+getSalario();
    }
}
