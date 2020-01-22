package proyectoprogramaciondealgoritmos;

import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class Facturar extends javax.swing.JFrame {

    //CREAMOS UN VARIABLE DE TIPO STRING PARA SABER EN QUE DIRECCION QUEREMOS O TENEMOS GUARDADOS NUESTROA ARCHIVOS
    String Carpeta = "";
    //CREMOS UNA VARIBLE  DE TIPO DE DEFAULTABLEMODEL PARA PODER EDITA EL MODELO DE NUESTRA TABLA 1
    public static DefaultTableModel modelo;
    //CREAMOS DE VARIABLE DE TIPO DOUBLE PARA PODER CALCULAR EL TOTAL,SUBTOTAL Y EL IVA A PAGAR
    static double total;
    double sub_total;
    double iva;
    //CREAMOS UN OBEJETO DE TIPO OP
    Operaciones op = new Operaciones();
    //CREMOS DOS ARRAYLISTS DEL TIPO PERSONAL Y PRODUCTO
    public static ArrayList<Personal> PersonalEmp = new ArrayList<Personal>();
    public static ArrayList<Producto> catalogo = new ArrayList();
    //CREMOS UNA VARIBLE  DE TIPO DE DEFAULTABLEMODEL PARA PODER EDITA EL MODELO DE NUESTRA TABLA 2
    DefaultTableModel m;

    //USAMOS UN METODO CREAR REGISTROS 
    private void crearRegistros() {
        Formatter archivoClientes;
        //ENCERRAMOS UN TRY-CATH PARA CAPTURA CUALQUIER EERO QUE SE PUEDA PRODUCIR
        try {
            //CON LA AYUDA DE NUESTRA VARIABLE FORMAT CREAMOS LOS ARCHVISO
            archivoClientes = new Formatter("Registro_Cliente.csv");
            //MEDIANTE UN FOR RECORREMOS NUESTROS ARRAYLIST 
            for (int i = 0; i < PersonalEmp.size(); i++) {
                Personal pers = PersonalEmp.get(i);
                if (pers instanceof Cliente) {
                    //ESCRIBIMOS EN NUESTRO ARCHIVO CON LOS PARAMETROS QUE TIENE NUESTRO ARRAYLIST
                    archivoClientes.format("%s,%s,%s,%s,%s,%s\r\n", PersonalEmp.get(i).getNombre(), PersonalEmp.get(i).getApellido(),
                            PersonalEmp.get(i).getCedula(), PersonalEmp.get(i).getCorreo(), PersonalEmp.get(i).getTelefono(),
                            PersonalEmp.get(i).getNumCelular());
                }
            }
            JOptionPane.showMessageDialog(null, " Registrado Correctamente");
            //CERRAMOS NUESTRO ARCHIVO
            archivoClientes.close();

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se Encontro el registro", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
//METODO CARGARREGISTROS PARA MOSTRAR EL PESRSONAL , CLIENTES Y PRODUCTOS  QUE HEMOS REGISTRADO

    private void cargarRegistros() {
        //USAMOS UNA VARIABLE TIPO SCANNER
        Scanner cargaArchivo;
        //CREAMOS VARIABLES PARA RECIBIR LOS VALORES
        String linea, nombre, apellido, cedula, telefono, correo, celular;
        //ENCERRAMOS EN UN TRY-CATCH PARA CAPTURA CUALQUIER POSIBLE ERROR
        try {
            //cON LA AYUDA DE NUESTRA VARIABLE DE TIPO SCANNER LEEMOS EL ARCHIVO
            cargaArchivo = new Scanner(new File("Registro_Cliente.csv"));
            //USAMOS UN WHULE PARA RECORRER Y LERR TDO LO QUE ESTA ESCRTIO EN NUESTRO ARCHIVO
            while (cargaArchivo.hasNext()) {

                linea = cargaArchivo.nextLine();
                String[] tokens = linea.split(";");
                nombre = tokens[0];
                apellido = tokens[1];
                cedula = tokens[2];
                correo = tokens[3];
                telefono = tokens[4];
                celular = tokens[5];
                // LE AÑADIMOS A NUESTRO ARRAYLYST NUESTRO NUEVO CLIENTE
                PersonalEmp.add(new Cliente(cedula, nombre, apellido, telefono, correo, celular));

            }
            //CERRAMOS NUESTRO ARCHIVO
            cargaArchivo.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error archivo no encontrado", "Archivo no encontrado", JOptionPane.ERROR_MESSAGE);
        }
    }
//mETODO PARA GUARDAR LA VENTAS REALIZADAS

    public void guardarVentas(double caja) {
        //cREAMOS UNA VARIALE TIPO FORMATTER
        Formatter archivoVentas;
        //ENCERRAMOS UN TRY-CATH PARA CAPTURA CUALQUIER EERO QUE SE PUEDA PRODUCIR
        try {
            //CON LA AYUDA DE NUESTRA VARIABLE FORMAT CREAMOS LOS ARCHVISO
            archivoVentas = new Formatter("Valor_Caja.csv");
            //ESCRIBIMOS EN NUESTRO ARCHIVO CON LOS DATOS QUEREMOS GUARDAR
            archivoVentas.format("%.2f\n,", caja);
            //CERRAMOS NUESTRO ARCHIVO
            archivoVentas.close();

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo guardar la venta", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
//METODO PARA LLENAR NUESTRA TABLAS

    private void llenarTablaCliente() {
        //CRESMOA UN OBJETO DE TIPO DEFAULTTABLEMODEL PARA PODER DARLE UN MODELO A NUESTRA TABLAcLIENTES
        DefaultTableModel dtm = new DefaultTableModel();
        //AÑADIMOS A NUESTRA TABLA LAS COLUMNAS CON SU RESPECTIVO NOBRE
        dtm.addColumn("Nombre");
        dtm.addColumn("Apellido");
        dtm.addColumn("Cedula");
        dtm.addColumn("Correo");
        dtm.addColumn("Telefono");
        //CREAMOS UNS VARIABLE DE TIPO SCANNER 
        Scanner cargaArchivo;
        //cREAMOS UN ARREGLO CON DE TAMAÑO DEL NUEMO DE COLUMAS
        Object fila[] = new Object[dtm.getColumnCount()];
        String linea;
        //ENCERRAMOS UN TRY-CATH PARA CAPTURA CUALQUIER EERO QUE SE PUEDA PRODUCIR
        try {
            //CON LA AYUDA DE NUESZRA VARIABLDE TIPO SCANNER LEEMOS EL ARCHIVO
            cargaArchivo = new Scanner(new File("Registro_Cliente.csv"));
            //USAMOS UN WHULE PARA RECORRER Y LERR TDO LO QUE ESTA ESCRTIO EN NUESTRO ARCHIVO
            while (cargaArchivo.hasNext()) {
                linea = cargaArchivo.nextLine();
                String[] tokens = linea.split(";");
                //aÑADIMOS A  NUESTRA FILAS LOS VALORES OBTENIDO DEL ARCHIVO
                fila[0] = tokens[0];
                fila[1] = tokens[1];
                fila[2] = tokens[2];
                fila[3] = tokens[3];
                fila[4] = tokens[5];

                dtm.addRow(fila);
            }
            //ESTABLECMOS EL MODELO DE NUESTRA TABLA
            tablaCliente.setModel(dtm);
            cargaArchivo.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error al leer archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
//mETOD PARA CARGAR LOS PRODEUCTOS

    private void cargarProductos() {

        //CREAMOS VARIABLES PARA RECIBIR LOS VALORES
        String linea, nombre;;
        int cantidad, ram, disco, procesador;
        double precioU;
        String sistemaO, marca;
        //USAMOS UNA VARIABLE TIPO SCANNER
        Scanner cargaArchivo;
        //ENCERRAMOS EN UN TRY-CATCH PARA CAPTURA CUALQUIER POSIBLE ERROR
        try {
            //cON LA AYUDA DE NUESTRA VARIABLE DE TIPO SCANNER LEEMOS EL ARCHIVO
            cargaArchivo = new Scanner(new File("Registro_Productos.csv"));
            //USAMOS UN WHULE PARA RECORRER Y LERR TDO LO QUE ESTA ESCRTIO EN NUESTRO ARCHIVO
            while (cargaArchivo.hasNext()) {
                linea = cargaArchivo.nextLine();
                String[] tokens = linea.split(";");
                nombre = tokens[0];
                precioU = Double.parseDouble(tokens[1].replace(",", "."));
                cantidad = Integer.parseInt(tokens[2]);
                ram = Integer.parseInt(tokens[3]);
                sistemaO = tokens[4];
                disco = Integer.parseInt(tokens[5]);
                marca = tokens[6];
                procesador = Integer.parseInt(tokens[7]);
                // LE AÑADIMOS A NUESTRO ARRAYLYST NUESTRO NUEVQ COMPUTADORA O PRODUCTO
                catalogo.add(new Computadoras(ram, sistemaO, disco, marca, procesador, nombre, precioU, cantidad));

            }
            //CERRAMOS NUESTRO ARCHIVO
            cargaArchivo.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Facturar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
//METODO PARA LLENAR NUESTRA TABLAS

    private void llenarTabla() {
        //CRESMOA UN OBJETO DE TIPO DEFAULTTABLEMODEL PARA PODER DARLE UN MODELO A NUESTRA TABLAcLIENTES
        DefaultTableModel dtm = new DefaultTableModel();
        //AÑADIMOS A NUESTRA TABLA LAS COLUMNAS CON SU RESPECTIVO NOBRE
        dtm.addColumn("Nombre Producto");
        dtm.addColumn("Precio");
        dtm.addColumn("Cantidad");
        dtm.addColumn("Ram gb");
        dtm.addColumn("Sistema Operativo");
        dtm.addColumn("Disco gb");
        dtm.addColumn("Marca");
        dtm.addColumn("Procesador Core i");
        //cREAMOS UN ARREGLO CON DE TAMAÑO DEL NUMERO DE COLUMNAS
        Object fila[] = new Object[dtm.getColumnCount()];
        //USAMOS UN FOR PARA RECORRER TODO NUESTRO ARRAYLIST
        for (int i = 0; i < catalogo.size(); i++) {
            fila[0] = catalogo.get(i).getNombre();
            fila[1] = catalogo.get(i).getPrecioUnit();
            fila[2] = catalogo.get(i).getCantStock();
            Producto prod = catalogo.get(i);
            if (prod instanceof Computadoras) {
                fila[3] = ((Computadoras) prod).getRam();
                fila[4] = ((Computadoras) prod).getSistemaO();
                fila[5] = ((Computadoras) prod).getDisco();
                fila[6] = ((Computadoras) prod).getMarca();
                fila[7] = ((Computadoras) prod).getProcesador();
                //Y PARA ASIGNARLOS A LOS VALORES EN CADA DILA DE LA TABLA
            }
            //AÑADIMOS NUESTRAS FILAS
            dtm.addRow(fila);
        }
        //ESTABLECMOS EL MODELO DE LA TABLA
        tablaProducto.setModel(dtm);
    }

    public Facturar() {

        initComponents();
        //lLAMALOS A NUESTRO METODO 
        cargarRegistros();
        cargarProductos();
        llenarTablaCliente();
        llenarTabla();
        //iNICIALIAZMOS NUESTRA VARIBLE DE TIPO DOUBLE
        total = 0;
        sub_total = 0;
        iva = 0;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Cliente = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        txtNomb = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaCliente = new javax.swing.JTable();
        btnRegistrar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        Producto = new javax.swing.JDialog();
        jLabel16 = new javax.swing.JLabel();
        txtCant = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaProducto = new javax.swing.JTable();
        btnAgregarP = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        lblFondo1 = new javax.swing.JLabel();
        Facturacion = new javax.swing.JDialog();
        Recibo = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtClfa = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablRecibo = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtTelFac = new javax.swing.JTextField();
        txtCeFac = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtSub_Total = new javax.swing.JTextField();
        txtIVa12 = new javax.swing.JTextField();
        txtTotalf = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel30 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtDirFac = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        lbltelefono = new javax.swing.JLabel();
        lblRuc = new javax.swing.JLabel();
        txtClienteA = new javax.swing.JTextField();
        txtDir = new javax.swing.JTextField();
        txtRuc = new javax.swing.JTextField();
        btnBuscarClientes = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnBuscarProductos = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtClienteN = new javax.swing.JTextField();
        txtTelef = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaFactura = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtSub_total = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtIva = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        btnGenerarFactura = new javax.swing.JButton();
        lblFondo = new javax.swing.JLabel();

        Cliente.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CLIENTES REGISTRADOS");
        Cliente.getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 320, 34));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("APELLIDO :");
        Cliente.getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 133, 99, 34));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("NOMBRE :");
        Cliente.getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 83, 84, 34));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Ruc o Cedula .");
        Cliente.getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 181, 99, 34));
        Cliente.getContentPane().add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 134, 272, 34));
        Cliente.getContentPane().add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 182, 272, 34));
        Cliente.getContentPane().add(txtNomb, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 84, 272, 34));

        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("CORREO :");
        Cliente.getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, 113, 34));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel2.setOpaque(false);

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("TELEFONO :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(440, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(80, Short.MAX_VALUE))
        );

        Cliente.getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 860, 180));

        tablaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClienteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaCliente);

        Cliente.getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 860, 270));

        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        Cliente.getContentPane().add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 560, -1, 30));

        btnAgregar.setText("AGREGAR");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        Cliente.getContentPane().add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 560, -1, 30));

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoprogramaciondealgoritmos/Imagenes/fondo.jpg"))); // NOI18N
        Cliente.getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 900, 630));

        Producto.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("CANTIDAD :");
        Producto.getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 560, 100, 34));
        Producto.getContentPane().add(txtCant, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 560, 50, 33));

        tablaProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tablaProducto);
        if (tablaProducto.getColumnModel().getColumnCount() > 0) {
            tablaProducto.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        Producto.getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 920, 400));

        btnAgregarP.setText("AGREGAR");
        btnAgregarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPActionPerformed(evt);
            }
        });
        Producto.getContentPane().add(btnAgregarP, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 560, -1, 30));

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("PRODUCTOS DISPONIBLES");
        Producto.getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 360, 34));

        lblFondo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFondo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoprogramaciondealgoritmos/Imagenes/fondo.jpg"))); // NOI18N
        Producto.getContentPane().add(lblFondo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 620));

        Facturacion.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Facturacion.setTitle("Factura");

        Recibo.setBackground(new java.awt.Color(255, 255, 255));
        Recibo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        Recibo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel17.setText("Correo  :");
        Recibo.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 100, 29));

        jPanel5.setBackground(new java.awt.Color(0, 255, 255));

        jLabel19.setBackground(new java.awt.Color(0, 204, 255));
        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel19.setText("Factura Nro 0001");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        Recibo.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, -1, 60));

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setOpaque(false);

        jLabel21.setBackground(new java.awt.Color(0, 204, 255));
        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel21.setText("SIN-NOMBRE");

        jLabel22.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel22.setText("Calle S/N entre Calle S/N y Calle S/n");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Recibo.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 310, 70));

        txtClfa.setEditable(false);
        txtClfa.setBorder(null);
        Recibo.add(txtClfa, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 330, 30));

        tablRecibo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tablRecibo.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        tablRecibo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cant.", "Descripcion", "V. Unit.", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablRecibo.setEnabled(false);
        jScrollPane4.setViewportView(tablRecibo);
        if (tablRecibo.getColumnModel().getColumnCount() > 0) {
            tablRecibo.getColumnModel().getColumn(0).setMaxWidth(60);
            tablRecibo.getColumnModel().getColumn(2).setMaxWidth(60);
            tablRecibo.getColumnModel().getColumn(3).setMaxWidth(60);
        }

        Recibo.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 880, 350));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setOpaque(false);

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel23.setText("Ruc o Cedula :");

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel28.setText("Telefono :");

        txtTelFac.setBorder(null);

        txtCeFac.setEditable(false);
        txtCeFac.setBorder(null);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(422, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTelFac, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(30, 30, 30)
                        .addComponent(txtCeFac, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCeFac, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelFac, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        Recibo.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 880, -1));

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.setOpaque(false);

        jLabel24.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel24.setText("Sub Total :");

        jLabel25.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel25.setText("IVA 12% :");

        jLabel26.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel26.setText("Total :");

        txtSub_Total.setText("12.30");
        txtSub_Total.setBorder(null);

        txtIVa12.setText("0.70");
        txtIVa12.setBorder(null);

        txtTotalf.setText("13");
        txtTotalf.setBorder(null);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(44, 44, 44)
                        .addComponent(txtSub_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIVa12, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                            .addComponent(txtTotalf))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtSub_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtIVa12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        Recibo.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 640, 210, 160));

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel27.setText("Cliente :");
        Recibo.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 100, 29));

        jPanel8.setOpaque(false);

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel30.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel30.setText("Recibi Conforme");

        jLabel29.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel29.setText("Autorizacion");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Recibo.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 660, 560, 70));

        txtDirFac.setBorder(null);
        Recibo.add(txtDirFac, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 270, 30));

        javax.swing.GroupLayout FacturacionLayout = new javax.swing.GroupLayout(Facturacion.getContentPane());
        Facturacion.getContentPane().setLayout(FacturacionLayout);
        FacturacionLayout.setHorizontalGroup(
            FacturacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Recibo, javax.swing.GroupLayout.DEFAULT_SIZE, 946, Short.MAX_VALUE)
        );
        FacturacionLayout.setVerticalGroup(
            FacturacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Recibo, javax.swing.GroupLayout.PREFERRED_SIZE, 836, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Facturar");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbltelefono.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbltelefono.setForeground(new java.awt.Color(255, 255, 255));
        lbltelefono.setText("Telefono :");
        jPanel1.add(lbltelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 40, 120, 35));

        lblRuc.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblRuc.setForeground(new java.awt.Color(255, 255, 255));
        lblRuc.setText("RUC o Cedula :");
        jPanel1.add(lblRuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 130, 35));

        txtClienteA.setEditable(false);
        txtClienteA.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        txtClienteA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClienteAActionPerformed(evt);
            }
        });
        jPanel1.add(txtClienteA, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, 180, 35));

        txtDir.setEditable(false);
        txtDir.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.add(txtDir, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 145, 307, 35));

        txtRuc.setEditable(false);
        txtRuc.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.add(txtRuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 96, 307, 35));

        btnBuscarClientes.setText("BUSCAR");
        btnBuscarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClientesActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscarClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 40, 90, 30));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Fecha :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, 70, 35));

        btnBuscarProductos.setText("Agregar Producto ");
        btnBuscarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProductosActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscarProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 150, 150, 30));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Cliente :");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 80, -1));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Correo :");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 97, 35));

        txtClienteN.setEditable(false);
        txtClienteN.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.add(txtClienteN, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 42, 190, 35));

        txtTelef.setEditable(false);
        txtTelef.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        txtTelef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefActionPerformed(evt);
            }
        });
        jPanel1.add(txtTelef, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, 180, 35));
        jPanel1.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 102, 180, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 1060, 210));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("                                                                                         LISTA DE PRODUCTOS");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 1060, 40));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel3.setOpaque(false);

        tablaFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cantidad", "Descripcion", "Precio Unitario", "Total"
            }
        ));
        jScrollPane1.setViewportView(tablaFactura);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("SUB TOTAL :");

        txtSub_total.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("TOTAL :");

        txtIva.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("IVA :");

        txtTotal.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnGenerarFactura.setText("Generar Factura");
        btnGenerarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarFacturaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1010, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSub_total, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(122, 122, 122)
                        .addComponent(btnGenerarFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 26, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSub_total, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnGenerarFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 1060, 340));

        lblFondo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoprogramaciondealgoritmos/Imagenes/fondo.jpg"))); // NOI18N
        getContentPane().add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1110, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtClienteAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClienteAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteAActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        //LLAMAMOS A NUESTRO METOD RESGITRACLIENTE
        op.registrarCliente(PersonalEmp);
        //cREMOS EL REGISTRO
        crearRegistros();
        //y VOLMENOS A LLENAR NUESTRA TABLA
        llenarTablaCliente();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnBuscarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClientesActionPerformed
        // TODO add your handling code here:
        //CREMOS UN VENTANTA CON SIG CATERECTERISTICA
        Cliente.setSize(900, 650);//TAMAÑO
        Cliente.setLocationRelativeTo(null);//LUGAR DE UBICACION EN LA PANTALL
        Cliente.setModal(true);//EL MODELO QUE VA TENER
        Cliente.setVisible(true);//LA PONEMOS VISIBLE

    }//GEN-LAST:event_btnBuscarClientesActionPerformed
// EVENTO DE LA TABLA SELECCIONADA
    private void tablaClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClienteMouseClicked
        // TODO add your handling code here:
        //cREAMOS UN VARIBALE DE TIPO INT PARA SABER EN Q FILA ESTA SELECCIONADA
        int selection = tablaCliente.rowAtPoint(evt.getPoint());
        //CON LA ATUDA DE NUESTRA VARIABLE INT MANDAMOS LOS DATOS DE LA FILA SELECCIONADA A LOS TXT
        txtNomb.setText(String.valueOf(tablaCliente.getValueAt(selection, 0)));
        txtApellido.setText(String.valueOf(tablaCliente.getValueAt(selection, 1)));
        txtCedula.setText(String.valueOf(tablaCliente.getValueAt(selection, 2)));
        txtDireccion.setText(String.valueOf(tablaCliente.getValueAt(selection, 3)));
        txtTelefono.setText(String.valueOf(tablaCliente.getValueAt(selection, 4)));

    }//GEN-LAST:event_tablaClienteMouseClicked
//BOTON AGREGAR CLIENTES A LA REGISTOR DE VENTA
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
//CON UNA VARIBLE INT VEMOS SI ALGUNA FILA ESTA SELECIONADA
        int fsl = tablaCliente.getSelectedRow();
        //eNCERRAMOS EN UN TRY -CATCH PARA CAPTURA CUALQUIER POSIBLE ERROR Q SE NOS GENERE
        try {
            //cREMOS VARIBLES PARA RECIBRI LOS VALORES DE LA TABLA
            String nombre, apellido, direccion, cedula, telefono;
            //EN CASO DE QUE NO ESTE SELECCIONADA 
            if (fsl == -1) {
                //MANDAMOS UN MENSAJE DE ERRR
                JOptionPane.showMessageDialog(null, "Debe seleccionar un CLIENTE", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {// EN CASO CONTRARIO
                m = (DefaultTableModel) tablaCliente.getModel();
                //cAPTURAMOS LAS VALORES QUE CONTIENE EN ESA FILA EN LA DIFERENTES VARIABLE
                apellido = tablaCliente.getValueAt(fsl, 1).toString();
                cedula = tablaCliente.getValueAt(fsl, 2).toString();
                direccion = tablaCliente.getValueAt(fsl, 3).toString();
                nombre = tablaCliente.getValueAt(fsl, 0).toString();
                telefono = tablaCliente.getValueAt(fsl, 4).toString();
                //lE ESTABLECEMOS A NUESTROS TXT  LOS VALORESD LA VARIBLES
                txtClienteN.setText(nombre);
                txtClienteA.setText(apellido);
                txtRuc.setText(cedula);
                txtDir.setText(direccion);
                txtTelef.setText(telefono);

            }
        } catch (HeadlessException e) {
        }

    }//GEN-LAST:event_btnAgregarActionPerformed
    //AGREGAR PRODUCTOS A LA TABLA DE VENTA
    private void btnAgregarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPActionPerformed
        // TODO add your handling code here:
        //CON UNA VARIBLE INT VEMOS SI ALGUNA FILA ESTA SELECIONADA
        int fsl = tablaProducto.getSelectedRow();
        //eNCERRAMOS EN UN TRY -CATCH PARA CAPTURA CUALQUIER POSIBLE ERROR Q SE NOS GENERE
        try {
            //CREMOS VARIBALE SPARA RECIBIR LOS VALORES DE LA TABLA
            String nombre, precio, cant, paga;
            double calcula, x, ivas = 0.0;
            //vERIFICAMOS QUE ESTE SELECIONADA ALGUNA FILA DE LA TABLA
            if (fsl == -1) {
                //PRESENTAMOS UN MENSAJE DE ERROR EN CASO DE Q NO ESTE SELCIONAD
                JOptionPane.showMessageDialog(null, "Debe seleccionar un PRODUCTO", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {// EN CASO DE QUE ESETE SELECCIONDA
                //LE AGREGAMOS A NUESTRA VARIBLE  CANT LA CANTIDA QUE NOS PIDE EL USUARIO
                cant = txtCant.getText();

                //USMAOS A IF CON EL METODO VENDER PRODUCTO QUE NOS DEVUELVE UN VALOR BOOLEAM
                if (op.venderProducto(catalogo, fsl, Integer.parseInt(cant))) {//EN CASO DE QUE SEA TRUE
                    //rEALIZA LAS SIGUIENTES OPERACIONES
                    precio = String.valueOf(op.Modificar(catalogo, fsl));
                    llenarTabla();
                    m = (DefaultTableModel) tablaProducto.getModel();
                    nombre = tablaProducto.getValueAt(fsl, 0).toString();

                    //Calculos              
                    x = (Double.parseDouble(precio)) * Integer.parseInt(cant);
                    paga = String.valueOf(x);
                    m = (DefaultTableModel) tablaFactura.getModel();

                    String filaEle[] = {cant, nombre, precio, paga};
                    m.addRow(filaEle);
                    calcula = (Double.parseDouble(precio)) * Integer.parseInt(txtCant.getText());

                    total = total + calcula;
                    ivas = total * 0.12;
                    iva = ivas;
                    sub_total = total - ivas;

                    txtSub_total.setText(" " + sub_total);
                    txtIva.setText(" " + iva);
                    txtTotal.setText(" " + total);

                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Verificar la selecion", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btnAgregarPActionPerformed
//bOTON PARA GENERA LA VENTA DE BUSQUEDA DE PRODUCTOS
    private void btnBuscarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductosActionPerformed
        // TODO add your handling code here:

        Producto.setSize(970, 650);
        Producto.setLocationRelativeTo(null);
        Producto.setModal(true);
        Producto.setVisible(true);

    }//GEN-LAST:event_btnBuscarProductosActionPerformed
//BOTON PARA GENERAR LA FACTURA
    private void btnGenerarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarFacturaActionPerformed
        // TODO add your handling code here:
        Facturacion.setVisible(true);
        Facturacion.setSize(963, 930);
        Facturacion.setModal(true);
        Facturacion.setLocationRelativeTo(null);

        String nombre, apellido, cedula, direccion, telefono;
        nombre = txtClienteN.getText();
        apellido = txtClienteA.getText();
        cedula = txtRuc.getText();
        direccion = txtDir.getText();
        telefono = txtTelef.getText();
        txtClfa.setText(nombre + " " + apellido);
        txtDirFac.setText(direccion);
        txtCeFac.setText(cedula);
        txtTelFac.setText(telefono);
        String subtotal, totalf, iva12;
        subtotal = txtSub_total.getText();
        totalf = txtTotal.getText();
        iva12 = txtIva.getText();
        txtSub_Total.setText(" " + subtotal);
        txtTotalf.setText(" " + totalf);
        txtIVa12.setText(" " + iva12);
        modelo = (DefaultTableModel) tablRecibo.getModel();
        guardarVentas(total);
        for (int i = 0; i < tablaFactura.getRowCount(); i++) {
            String[] datos = new String[4];
            datos[0] = tablaFactura.getValueAt(i, 0).toString();
            datos[1] = tablaFactura.getValueAt(i, 1).toString();
            datos[2] = tablaFactura.getValueAt(i, 2).toString();
            datos[3] = tablaFactura.getValueAt(i, 3).toString();
            modelo.addRow(datos);
        }


    }//GEN-LAST:event_btnGenerarFacturaActionPerformed

    private void txtTelefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Cliente;
    private javax.swing.JDialog Facturacion;
    private javax.swing.JDialog Producto;
    private javax.swing.JPanel Recibo;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAgregarP;
    private javax.swing.JButton btnBuscarClientes;
    private javax.swing.JButton btnBuscarProductos;
    private javax.swing.JButton btnGenerarFactura;
    private javax.swing.JButton btnRegistrar;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblFondo1;
    private javax.swing.JLabel lblRuc;
    private javax.swing.JLabel lbltelefono;
    private javax.swing.JTable tablRecibo;
    private javax.swing.JTable tablaCliente;
    private javax.swing.JTable tablaFactura;
    private javax.swing.JTable tablaProducto;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCant;
    private javax.swing.JTextField txtCeFac;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtClfa;
    private javax.swing.JTextField txtClienteA;
    private javax.swing.JTextField txtClienteN;
    private javax.swing.JTextField txtDir;
    private javax.swing.JTextField txtDirFac;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtIVa12;
    private javax.swing.JTextField txtIva;
    private javax.swing.JTextField txtNomb;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtSub_Total;
    private javax.swing.JTextField txtSub_total;
    private javax.swing.JTextField txtTelFac;
    private javax.swing.JTextField txtTelef;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotalf;
    // End of variables declaration//GEN-END:variables
}
