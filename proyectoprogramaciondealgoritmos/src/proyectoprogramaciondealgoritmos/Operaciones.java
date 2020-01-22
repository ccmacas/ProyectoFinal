package proyectoprogramaciondealgoritmos;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Operaciones {


//Metodo para registar un cliente que recibe como parametro un arrayList de tipo Personal
    public void registrarCliente(ArrayList<Personal> personal) {
        //Solicitamos los datos que necesitamos Mediante JOptinePane para llenar los parametros del constructor
        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del nuevo empleado");
        String apellido = JOptionPane.showInputDialog(null, "Ingrese el apellido :");
        String cedula = JOptionPane.showInputDialog(null, "Ingrese el numero de cedula:");
        String correo = JOptionPane.showInputDialog(null, "Ingrese el correo:");
        String telefono = JOptionPane.showInputDialog(null, "Ingrese el número de telefono"); 
        String celular = JOptionPane.showInputDialog(null, "Ingrese el numero de celular");
        //Añadimos a nuestro arralyst un cliente nuevo con los parametros  que se pidieron anteriormente
        personal.add(new Cliente( cedula, nombre, apellido, telefono, correo, celular));
    }
//Metodo para mostrar los clientes registrados  que recibe como parametro un arrayList de tipo Personal

    public void mostrarClientes(ArrayList<Personal> personal) {
        /*Creamos una variable de tipo String para almacenar todo los datos en 
        una sola cadena*/
        String clientes = "";
        /*Con un if verificamos si nuestro Arraylist tenga datos,si es que tiene valores 
        haga lo que esta dentro del if */
        if (!personal.isEmpty()) {
            /*Con un for normal recorremos todo el Arraylist hasta que i sea menor tamaño del ArrayList,
            para obtener el tamño usamos un metodo de la clase Arraylist que es el .size() q es un metodo 
            que devuelve un valor entero*/
            for (int i = 0; i < personal.size(); i++) {
                /*A nuestra variable cliente le almancenamos los valores que obtine
                del arraylist en la posicion i del metodo mostrarDatos*/
                clientes += (i + 1) + personal.get(i).mostrarDatos() + "\n";
            }//Se sale del ciclo for
            //Se presenta con JOptinePane.ShowMessageDialog con la variable cliente que contiene todo los datos en una sola cadena
            JOptionPane.showMessageDialog(null, clientes);
        } else {//en caso de que nuestra lista esete basia
            //Se prsenta un mensaje de Error con JOptinePane
            JOptionPane.showMessageDialog(null, "No hay Clientes Registrados", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
//Metodo para registar un cliente que recibe como parametro un arrayList de tipo Personal

    public void registarNuevoTrabajador(ArrayList<Personal> personal) {
        //Solicitamos los datos que necesitamos para llenar a nuestro constructo
        //Mediante JOptinePane para llenar los parametros del constructor
        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del nuevo empleado");
        String apellido = JOptionPane.showInputDialog(null, "Ingrese el apellido :");
        String cedula = JOptionPane.showInputDialog(null, "Ingrese el numero de cedula:");
        String correo = JOptionPane.showInputDialog(null, "Ingrese el correo:");
        String telefono = JOptionPane.showInputDialog(null, "Ingrese el número de telefono");
        String celular = JOptionPane.showInputDialog(null, "Ingrese el numero de celular");
        String cargo = JOptionPane.showInputDialog(null, "Ingrese el cargo");
        //Como los JoptinePane devuelven valores de tipo String 
        //Realizamos la conversion para pasar el valor de sueldo a double
        double sueldo = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el sueldo"));
        //Añadimos a nuestro arralyst un empleado  nuevo con los parametros  que se pidieron anteriormente
        personal.add(new Empleado(sueldo, cargo, cedula, nombre, apellido, telefono, correo, celular));
    }
// metodo para mostrar el nombre del personal registrado

    public void mostrarPersonal(ArrayList<Personal> personal) {
        // Creamos un variable tipo String  para almacanar todos los datos en una sola cadena
        String ms = "";
        //Comprobamos si hay valores en el arreglo
        if (!personal.isEmpty()) {
            /*Para saber del tamaño del arralyst usamos uno de los metodos que vienen con la clase que es 
            metodo .size() con esto obtendremos el tamaño*/
            //Con un ciclo for rcorremos todo nuestro arreglo mienstras q i sea menor al tamaño del arreglo
            for (int i = 0; i < personal.size(); i++) {
                //Por cada interracion  de ciclo for almacenamos en nuestra variable ms todos los valores
                //que se encuentren en la posicion i que llama al metodo mostrarDatos.
                ms += (i + 1) + personal.get(i).mostrarDatos() + "\n";
                /*para  saber la posicion que se encuentra usamos los metodos que vienen con la clasea arraylyst en este
                caso usamo el metodo .get() que nos devuelve un valor entero*/
            }
            //Con un JOPtionPane presentamos todo los valores que almacenamos en nuestra  variable ms
            JOptionPane.showMessageDialog(null, ms);
        } else {//Es casso de que nuestra arrayList este vacio presentamos 
            //Con un  JOPtinePane  mensaje de advertencia dicienedo que no hay trabajadores registrados
            JOptionPane.showMessageDialog(null, "No hay Trabajadores Registrados", "Error", JOptionPane.WARNING_MESSAGE);
        }

    }

//Metodo  para mostar los productos
    public void mostarProductos(ArrayList<Producto> productos) {
        // Creamos un variable tipo String  para almacanar todos los datos en una sola cadena
        String ms = "";
        //Con un ciclo for  mejorado rcorremos todo nuestro ArrayList hacia instancia a la clase producto
        for (Producto producto : productos) {
            //Por cada interracion  de ciclo for almacenamos en nuestra variable ms todos los valores en el .toString 
            ms = ms + "\n-----------o----------\n" + producto.toString();
        }
        // Presentamos con JOptinePane los valores guardados en la variable ms
        JOptionPane.showMessageDialog(null, ms);

    }
//Metodo  para mostar los productos

    //Metodo para vender un producto q resive como parametro un arrayList y 2 numero enteros  uno qu es la opcion q eleligio el usuario
    // y el otro q resive la cantidad deseada del producto
    public boolean venderProducto(ArrayList<Producto> productos, int respuesta, int cantidadP) {
        // Creamos una variable tipo producto y le asignamos el valor del una posicion del arreglo
        Producto catalogo = productos.get(respuesta);
        //Usamos un if para controla el producto en la  posicion que ingreso el usuario  este disponible

        /*con un if anidado verifiamos q si nuestra variable de tipo producto este instanciando a la clase computadora
             y que  la canitidad en stock del  producto seleccionado sea mayor igual q la cantida pedida */
        if (catalogo instanceof Computadoras && productos.get(respuesta).getCantStock() >= cantidadP) {
            //Si se cumple establece la nueva cantidad de stock
            productos.get(respuesta).setCantStock(productos.get(respuesta).getCantStock() - cantidadP);
            
         
            JOptionPane.showMessageDialog(null, "Venta Realizada con Exito");
            //devolvemos el valor de la caja + el precio

            // conun else  preguntamos la si la cantidad de producto es menor de la cantidad mayor de la cantidad Pedida
            return true;
        } else {// en caso de q no  haya la suficiente cantidad de producto 
            JOptionPane.showMessageDialog(null, "No hay cantidad suficiente del produto");
            return false;
        }

    }
   
//Metodo modificar que recibe parametros un arraylist, respueta del usuario, y una varialbe tipo catalogo

    public double Modificar(ArrayList<Producto> productos, int respuesta ) {
        //Declaramos variables tipo
        int n;
        int opcion;
        int mo;
        Producto catalogo = productos.get(respuesta);
        //Decalaramos  arreglos de tipo double 
        double preciosD[] = {160, 200, 260};
        double precioRam[] = {130, 250, 450};
        double precioProce[] = {200, 350, 450};
        double presActual = 0;
        //Preguntamos si desea modificar alguna parte
        mo = Integer.parseInt(JOptionPane.showInputDialog(null, "Desea Modificar alguna parte del equipo:\n 1.Si\n2.No"));
        // Usamos un if para ver si se mofica alguna parte
        if (mo == 1) {
            n = Integer.parseInt(JOptionPane.showInputDialog("Qué desea Modificar \n1.Disco Duro\n 2.Memoria Ram\n 3.Procesador"));
            //Usamos un swicth para saber que opcion a cogido el usuario
            switch (n) {
                //el primer caso prsenta los tipos de disco qu ofrece
                case 1: {
                    opcion = Integer.parseInt(JOptionPane.showInputDialog("1.500 GB  $160 \n2.1000 GB  $200\n3. 2000 GB $260"));
                    presActual = (productos.get(respuesta).getPrecioUnit() + preciosD[opcion - 1]);
                    //switch  comparamos que disco desea cambiar
                    switch (((Computadoras) catalogo).getDisco()) {
                        case 1000:
                            presActual = presActual - 200;
                            productos.get(respuesta).setPrecioUnit(presActual);

                            break;
                        case 500:
                            presActual = presActual - 160;
                            productos.get(respuesta).setPrecioUnit(presActual);

                            break;
                        case 2000:
                            presActual = presActual - 260;
                            productos.get(respuesta).setPrecioUnit(presActual);

                            break;
                        default:
                            break;
                    }
                    break;

                }

                case 2: {
                    //el segundo  caso prsenta los tipos de memoria  que ofrece
                    opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "1.2GB  $130\n2.4GB  $250 \n3.8Gb $450"));
                    presActual = productos.get(respuesta).getPrecioUnit() + precioRam[opcion - 1];
                    productos.get(respuesta).setPrecioUnit(presActual);
                    //switch  comparamos que tipos de memoria ram ofrece desea cambiar
                    switch (((Computadoras) catalogo).getRam()) {
                        case 4:
                            presActual = presActual - 260;
                           

                            break;
                        case 8:
                            presActual = presActual - 450;
                       

                            break;
                        case 2:
                            presActual = presActual - 130;
                            

                            break;
                        default:
                            break;
                    }

                    break;
                }
                case 3:
                    //el tercer  caso prsenta los tipos de procesador q ofrece que ofrece
                    opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "1.Core i3  $200\n2.Core i5  $350\n3.Core i7  $450"));
                    presActual = productos.get(respuesta).getPrecioUnit() + precioProce[opcion - 1];

                    //switch  comparamos que tipos de procesadores ofrece que  desea cambiar
                    switch (((Computadoras) catalogo).getProcesador()) {
                        case 3:
                            presActual = presActual - 200;
                            
                            break;
                        case 5:
                            presActual = presActual - 350;                           

                            break;
                        case 7:
                            presActual = presActual - 450;

                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;

            }
            return presActual;
        } else {//en caso que no se cupla la condicion
            return productos.get(respuesta).getPrecioUnit();
        }
    }

    //metodo para REGISTRARproduectos
    public void registrarProductos(ArrayList<Producto> catalogo) {
        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del producto");
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad del producto"));
        double precio = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el precio del producto"));

        int ram = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la memoria Ram en GB"));
        String sistemaO = JOptionPane.showInputDialog(null, "Ingrese el Sistema Operativo");
        int Disco = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el tamaño del disco en GB"));
        String marca = JOptionPane.showInputDialog(null, "Ingrse la marca del computador");
        int procesador = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el procesador Core i "));
        catalogo.add(new Computadoras(ram, sistemaO, Disco, marca, procesador, nombre, precio, cantidad));

    }

}
