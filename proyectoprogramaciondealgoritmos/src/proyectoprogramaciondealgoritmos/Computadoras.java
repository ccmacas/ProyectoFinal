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
public class Computadoras extends Producto{
    //Atributos de la clase Computadoras
    public int ram;
    public  String sistemaO;
    public int Disco;

    public String marca;
    public int procesador;
    //Constructor sin parametros
    public Computadoras() {
    }
    //Constructor q resive parametros
    public Computadoras(int ram, String sistemaO, int Disco, String marca,int procesador, 
                         String nombre, double precioUnit, int cantStock) {
        //LLamamos al constructor de la calse padre
        super(nombre, precioUnit, cantStock);
        //Inicializamos los atriubutos
        this.ram = ram;
        this.sistemaO = sistemaO;
        this.Disco = Disco;
        this.marca = marca;
        this.procesador = procesador;
    }   
    //Metodos especiales  sety get para obetener y establces valores

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public String getSistemaO() {
        return sistemaO;
    }

    public void setSistemaO(String sistemaO) {
        this.sistemaO = sistemaO;
    }

    public int getDisco() {
        return Disco;
    }

    public void setDisco(int Disco) {
        this.Disco = Disco;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getProcesador() {
        return procesador;
    }

    public void setProcesador(int procesador) {
        this.procesador = procesador;
    }
//Metodo toString heredado de la clase padre
    @Override
    public String toString() {
        String ms = super.toString();
        return  ms+"\nMarca :" + marca +"\nProcesador :" +"Core i"+procesador + "\nMemoria ram :" + ram + "gb"+"\nSistema Operativo :" + sistemaO + "\nDisco duro :" + Disco +"gb" ;
    }
    
}
