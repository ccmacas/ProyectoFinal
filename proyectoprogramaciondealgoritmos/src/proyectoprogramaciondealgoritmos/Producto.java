package proyectoprogramaciondealgoritmos;

public class Producto {

	// Attributos
	private String nombre;
	private double precioUnit;
	private int cantStock;
	
	

	// Constructor vacio
	public Producto() {
	}
        //Constructor q recibe parametros
	public Producto(String nombre, double precioUnit, int cantStock
			) {
		this.nombre = nombre;
		this.precioUnit = precioUnit;
		this.cantStock = cantStock;
		

		
	}

	//Metodos especiales  get and set
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecioUnit() {
		return this.precioUnit;
	}

	public void setPrecioUnit(double precioUnit) {
		this.precioUnit = precioUnit;
	}

	public int getCantStock() {
		return this.cantStock;
	}

	public void setCantStock(int cantStock) {
		this.cantStock = cantStock;
	}

	
// Metodo toString
	@Override
	public String toString() {
		return "\nNombre: " + this.getNombre() + "\n" + "Precio unidad: "
				+ this.getPrecioUnit() + " $ \n" + "En Stock: "
				+ this.getCantStock();
	}

   
}
