package Controlador;

import Modelo.BaseDatos;
import Modelo.Cliente;
import Modelo.CopiarFicheros;
import Modelo.CrearCarpetaCliente;
import Modelo.CtaCorriente;
import Modelo.Obra;
import Modelo.Remito;
import VISTA.FrameCliente;
import VISTA.FrameObra;
import VISTA.FrameRemito;
import VISTA.Principal;
import VISTA.CambiaPanel;
import VISTA.FrameVerCtaCorriente;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorEvento {

    public Principal vista;
    FrameObra frameObra;
    FrameRemito frameRemito;
    FrameCliente frameCliente;
    FrameVerCtaCorriente frameCtaCorriente;

    private BaseDatos bd;
    private DefaultTableModel modeloTablaRemitos = new DefaultTableModel();

    private DefaultComboBoxModel modeloComboObra = new DefaultComboBoxModel();
    public DefaultComboBoxModel modeloComboClientes = new DefaultComboBoxModel();
   
    private final String rutaPrincipal = "C:/Users/maxid/remitos";
    private CrearCarpetaCliente createFile;
    String rutaRemito = "";
    String seleccion1 = "";

    private String path = null;

    public ControladorEvento(Principal vista, BaseDatos bd, FrameCliente frameCliente, FrameObra frameObra,
            FrameRemito frameRemito, FrameVerCtaCorriente frameCtaCorriente) {

        this.vista = vista;
        this.bd = bd;

        this.frameCliente = frameCliente;
        this.frameObra = frameObra;
        this.frameRemito = frameRemito;
        this.frameCtaCorriente = frameCtaCorriente;

        cargarModeloTabla();
         cargarModeloTablaCtaCorriente();
        cargarModeloCombocliente();
        cargarModeloComboObra();
        frameObra.comboClientes.setModel(modeloComboClientes);

        /*       vista.btnLamina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int posicion = vista.panelMenu.getX();
                if (posicion > -1) {
                    Animacion.Animacion.mover_izquierda(0, -270, 2, 2, vista.panelMenu);

                } else {
                    Animacion.Animacion.mover_derecha(-270, 0, 2, 2, vista.panelMenu);
                }
            }

        });*/
        this.frameRemito.tablaRemitos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaGastosMouseClicked(evt);
            }
        });
        
        this.frameCtaCorriente.btnEliminarRegistro.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    int numFilas = frameCtaCorriente.tablaCtaCorriente.getSelectedRow();
        int filaSelec =frameCtaCorriente.modeloTablaCtaCorriente.getRowCount();
        if (filaSelec > 0) {
            int quitar = JOptionPane.showConfirmDialog(frameCtaCorriente, "¿ Desea eliminar el registro seleccionado ?");
            if (quitar == 0) {
                if (numFilas == -1) {
                    JOptionPane.showMessageDialog(frameCtaCorriente, "Debe seleccionar el registro que desea quitar", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    CtaCorriente cta = (CtaCorriente) frameCtaCorriente.modeloTablaCtaCorriente.getValueAt(frameCtaCorriente.tablaCtaCorriente.getSelectedRow(), 1);
                    bd.eliminarFilaCtaCorrienteCliente(cta);

                    cargarCtaCorrienteCliente();
                }
            }
        }
            }
            
        });

        this.frameRemito.btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }

        });

        this.vista.btnVerRemito.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (!frameCtaCorriente.isVisible()) {

                    vista.escritorio.add(frameCtaCorriente);
                    vista.escritorio.getDesktopManager().maximizeFrame(frameCtaCorriente);
                    frameCtaCorriente.setVisible(true);
                } else {
                    vista.escritorio.getDesktopManager().maximizeFrame(frameCtaCorriente);
                }

            }

        });

        this.vista.btnLabelObra.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {

                if (!frameObra.isVisible()) {

                    vista.escritorio.add(frameObra);
                    vista.escritorio.getDesktopManager().maximizeFrame(frameObra);
                    frameObra.setVisible(true);
                } else {
                    vista.escritorio.getDesktopManager().maximizeFrame(frameObra);
                }
            }

        });

        this.vista.btnRemito1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (!frameRemito.isVisible()) {

                    vista.escritorio.add(frameRemito);
                    vista.escritorio.getDesktopManager().maximizeFrame(frameRemito);
                    frameRemito.setVisible(true);
                } else {
                    vista.escritorio.getDesktopManager().maximizeFrame(frameRemito);
                }

            }

        });
        
        this.frameCtaCorriente.btnBuscarPorFecha.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaComoCadena = sdf.format(frameCtaCorriente.txtFecha.getDate());
        String fechaDeHoy = sdf.format(new java.util.Date().getTime());
        int idCliente = frameCtaCorriente.comboClientes.getSelectedIndex() +1;
        ArrayList<CtaCorriente> lista = bd.ctaCorrienteClientePorFechas(idCliente,fechaComoCadena, fechaDeHoy);
        int numeroCta = lista.size();
        frameCtaCorriente.modeloTablaCtaCorriente.setNumRows(numeroCta);

        for (int i = 0; i < numeroCta; i++) {

            CtaCorriente cta = lista.get(i);
            int idCta = cta.getIdCta();
            String fecha = cta.getFecha();
            String descripcion = cta.getDescripcion();
            double debe = cta.getDebe();
            double haber = cta.getHaber();
            double saldo = cta.getSaldo();
            int id_Cliente = cta.getIdCliente();

            frameCtaCorriente.modeloTablaCtaCorriente.setValueAt(fecha, i, 0);
            frameCtaCorriente.modeloTablaCtaCorriente.setValueAt(cta, i, 1);
             frameCtaCorriente.modeloTablaCtaCorriente.setValueAt(debe, i, 2);
            frameCtaCorriente.modeloTablaCtaCorriente.setValueAt(haber, i, 3);
            frameCtaCorriente.modeloTablaCtaCorriente.setValueAt(saldo, i, 4);

        }
           double sumatoria = getSaldoCtaCorriente();
            frameCtaCorriente.txtSaldo.setText(String.valueOf(sumatoria));

            }
            
        });

        this.vista.btnLabelCliente.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {

                if (!frameCliente.isVisible()) {

                    vista.escritorio.add(frameCliente);
                    vista.escritorio.getDesktopManager().maximizeFrame(frameCliente);
                    frameCliente.setVisible(true);
                } else {
                    vista.escritorio.getDesktopManager().maximizeFrame(frameCliente);
                }
            }

        });

        frameObra.lblInsertarObra.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {

                Obra obra = enviarObra();
                bd.insertNuevaObra(obra);

            }

        });

        frameCliente.btnInsertaCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Cliente nuevoCliente = enviarCliente();
                bd.insertNuevoCliente(nuevoCliente);

            }

        });

        this.frameRemito.btnSubirRemito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double importe = 0;

                createFile = new CrearCarpetaCliente();
                File remito = getFile();
                String nombreCarpetaObra = frameRemito.ComboObra.getSelectedItem().toString();
                String path = "";
                int idCliente = frameRemito.ComboClientes.getSelectedIndex() + 1;
                int idObra = frameRemito.ComboObra.getSelectedIndex() + 1;
                String pathRemito = remito.getAbsolutePath();
                String numRemito = frameRemito.txtNumRemito.getText();
                String fechaRemito = frameRemito.txtFechaRemito.getText();

                String nombreRemito = remito.getName();
                String nombreCarpetaCliente = frameRemito.ComboClientes.getSelectedItem().toString();

                if (numRemito != null || fechaRemito != null) {

                    createFile.crearCarpeta(pathRemito, rutaPrincipal + "/" + nombreCarpetaCliente + "/" + nombreCarpetaObra + "/" + nombreRemito);
                    createFile.renameFile(pathRemito, rutaPrincipal + "/" + nombreCarpetaCliente + "/" + nombreCarpetaObra + "/" + numRemito + " " + fechaRemito + ".pdf");
                    createFile.moveFile(pathRemito, rutaPrincipal + "/" + nombreCarpetaCliente + "/" + nombreCarpetaObra + "/" + nombreRemito);
                    if (frameRemito.checkbox.isSelected()) {
                        importe = Double.parseDouble(frameRemito.txtImporteRemito.getText());
                    } else {
                        importe = 0;
                    }
                    insertaRemito(numRemito, fechaRemito, rutaPrincipal + "/" + nombreCarpetaCliente + "/" + nombreCarpetaObra + "/" + numRemito + " " + fechaRemito + ".pdf", idObra, idCliente, importe);

                }
            }

        });

        frameRemito.checkbox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                boolean state = frameRemito.checkbox.isSelected();

                if (state == true) {
                    frameRemito.txtImporteRemito.setEnabled(true);
                } else {
                    frameRemito.txtImporteRemito.setEnabled(false);
                }

            }
        });

        this.vista.txtBuscarRemitoObra.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    limpiarTabla();
                    String criterio = vista.txtBuscarRemitoCliente.getText();
                    String obra = vista.txtBuscarRemitoObra.getText();
                    //   recibeCTAPorCriterio(criterio, obra);
                    if (obra.equals("")) {

                        recibeCTAPorCliente(criterio);

                    } else if (!obra.equals("") && !criterio.equals("")) {

                        recibeCTAPorClienteyObra(criterio, obra);

                    }

                    frameRemito.tablaRemitos.setModel(modeloTablaRemitos);
                }
            }
        });

        this.vista.txtBuscarRemitoCliente.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    limpiarTabla();
                    String criterio = vista.txtBuscarRemitoCliente.getText();
                    String obra = vista.txtBuscarRemitoObra.getText();

                    if (obra.equals("")) {

                        recibeCTAPorCliente(criterio);

                    } else if (!obra.equals("") && !criterio.equals("")) {

                        recibeCTAPorClienteyObra(criterio, obra);

                    }
                    //     recibeCTAPorCriterio(criterio,obra);

                    frameRemito.tablaRemitos.setModel(modeloTablaRemitos);

                }

            }

        });

        this.frameCtaCorriente.btnBuscarCta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               cargarCtaCorrienteCliente();

            }

        });
        
        this.frameCtaCorriente.btnGuardar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                  int filas = frameCtaCorriente.modeloTablaCtaCorriente.getRowCount();
        if (filas == 0) {
            insertarMovimientoEnCtaCorriente();
        } else {
            ActualizarCuentaCorriente();
        }
        frameCtaCorriente.txtDebe.setText("");
         frameCtaCorriente.txtPaga.setText("");
            }
            
        });

        frameRemito.ComboClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frameRemito.ComboObra.setModel(modeloComboObra);

                modeloComboObra.removeAllElements();

                ArrayList<Obra> listaObra = bd.selectObra(frameRemito.ComboClientes.getSelectedIndex() + 1);

                for (Obra c : listaObra) {

                    modeloComboObra.addElement(c);

                }

            }

        });

        frameRemito.txtBuscarRemito.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {

                limpiarTabla();

                String criterio = frameRemito.txtBuscarRemito.getText();

                recibeCTAPorCriterio(criterio);
                frameRemito.tablaRemitos.setModel(modeloTablaRemitos);
            }

        });

    }

    public void recibeCTAPorCriterio(String criterio) {

        ResultSet rs = null;

        if (frameRemito.radioCliente.isSelected()) {

            rs = bd.dameCtaPorCliente(criterio);
        } else {

            rs = bd.dameCtaPorObra(criterio);
        }
        try {

            ResultSetMetaData metaData = rs.getMetaData();
            int numColumnas = metaData.getColumnCount();

            while (rs.next()) {

                Object fila[] = new Object[numColumnas + 1];

                for (int i = 0; i < numColumnas; i++) {

                    fila[0] = rs.getObject(1);
                    fila[1] = rs.getObject(2);
                    fila[2] = rs.getObject(3);
                    fila[3] = rs.getObject(4);
                    fila[4] = rs.getObject(5);
                    fila[5] = this.frameRemito.btnVerFactura;

                }

                modeloTablaRemitos.addRow(fila);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ControladorEvento.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void recibeCTAPorCliente(String cliente) {

        ResultSet rs = null;

        rs = bd.dameCtaPorCliente2(cliente);
        try {

            ResultSetMetaData metaData = rs.getMetaData();
            int numColumnas = metaData.getColumnCount();

            while (rs.next()) {

                Object fila[] = new Object[numColumnas + 1];

                for (int i = 0; i < numColumnas; i++) {

                    fila[0] = rs.getObject(1);
                    fila[1] = rs.getObject(2);
                    fila[2] = rs.getObject(3);
                    fila[3] = rs.getObject(4);
                    fila[4] = rs.getObject(5);
                    fila[5] = this.frameRemito.btnVerFactura;

                }

                modeloTablaRemitos.addRow(fila);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ControladorEvento.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void recibeCTAPorClienteyObra(String cliente, String obra) {

        ResultSet rs = null;

        rs = bd.dameCtaPorClienteyObra(cliente, obra);
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int numColumnas = metaData.getColumnCount();

            while (rs.next()) {

                Object fila[] = new Object[numColumnas + 1];

                for (int i = 0; i < numColumnas; i++) {

                    fila[0] = rs.getObject(1);
                    fila[1] = rs.getObject(2);
                    fila[2] = rs.getObject(3);
                    fila[3] = rs.getObject(4);
                    fila[4] = rs.getObject(5);
                    fila[5] = this.frameRemito.btnVerFactura;

                }

                modeloTablaRemitos.addRow(fila);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ControladorEvento.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void limpiarTabla() {
        int filas = modeloTablaRemitos.getRowCount();

        if (filas > 0) {

            for (int i = filas - 1; i >= 0; i--) {
                modeloTablaRemitos.removeRow(i);
            }
        }

    }

    public Cliente enviarCliente() {

        String nombreCliente = frameCliente.txtNombreCliente.getText();
        String telCliente = frameCliente.txtTelCliente.getText();
        String email = frameCliente.txtEmailCliente.getText();
        boolean esInst = false;

        if (frameCliente.CheckTrue.isSelected()) {
            esInst = true;
        } else {
            esInst = false;

        }

        Cliente cliente = new Cliente(nombreCliente, telCliente, email, esInst);
        JOptionPane.showMessageDialog(null, "El cliente ha sido registrado satisfactoriamente");
        frameCliente.txtNombreCliente.setText("");
        frameCliente.txtTelCliente.setText("");
        frameCliente.txtEmailCliente.setText("");
        return cliente;

    }

    public Obra enviarObra() {

        String nuevaObra = frameObra.txtNuevaObra.getText();

        int idCliente = frameObra.comboClientes.getSelectedIndex() + 1;

        Obra obra = new Obra(nuevaObra, idCliente);

        return obra;

    }

    public void cargarModeloTablaCtaCorriente() {

        //   modeloTablaRemitos.addColumn("Id");
        frameCtaCorriente.modeloTablaCtaCorriente.addColumn("Fecha");
        frameCtaCorriente.modeloTablaCtaCorriente.addColumn("Descripción");
        frameCtaCorriente.modeloTablaCtaCorriente.addColumn("Debe");
        frameCtaCorriente.modeloTablaCtaCorriente.addColumn("Pagó");
        frameCtaCorriente.modeloTablaCtaCorriente.addColumn("Saldo");
       
    }
    public void cargarModeloTabla() {

        //   modeloTablaRemitos.addColumn("Id");
        modeloTablaRemitos.addColumn("Nombre cliente");
        modeloTablaRemitos.addColumn("Nombre obra");
        modeloTablaRemitos.addColumn("Instalador");
        modeloTablaRemitos.addColumn("Numero remito");
        modeloTablaRemitos.addColumn("Importe");
        modeloTablaRemitos.addColumn("Archivo");
    }

    private void cargarModeloCombocliente() {
        ArrayList<Cliente> listaCliente = new ArrayList<>();
        listaCliente = bd.selectClientes();

        for (Cliente c : listaCliente) {
            modeloComboClientes.addElement(c);

        }
        frameRemito.ComboClientes.setModel(modeloComboClientes);
        frameCtaCorriente.comboClientes.setModel(modeloComboClientes);
    }

    private void cargarModeloComboObra() {
        ArrayList<Obra> listaObra = new ArrayList<>();
        listaObra = bd.selectObra();

        for (Obra c : listaObra) {
            modeloComboObra.addElement(c);

        }
        frameRemito.ComboObra.setModel(modeloComboObra);
    }

    //OBTIENE EL NOMBRE DEL ARCHIVO 
    public File getFile() {

        JFileChooser fc = new JFileChooser();

        int seleccion = fc.showOpenDialog(frameRemito);

        if (seleccion == JFileChooser.APPROVE_OPTION) {

            fichero = fc.getSelectedFile();

            //System.out.println(path);
        } else {
            System.out.println("NO A ELEGIDO NADA");
        }
        return fichero;

    }

    public void verRemito(String numRemito) {

        ImageIcon ImagenGasto = null;

        byte[] b = null;

        try {

            ResultSet rs = bd.getRemito(numRemito);

            while (rs.next()) {

                rutaRemito = rs.getString("RUTA_ARCHIVO");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    private void tablaGastosMouseClicked(java.awt.event.MouseEvent evt) {
        int rown = this.frameRemito.tablaRemitos.rowAtPoint(evt.getPoint());
        int column = this.frameRemito.tablaRemitos.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / this.frameRemito.tablaRemitos.getRowHeight();

        if (row < this.frameRemito.tablaRemitos.getRowCount() && row >= 0 && column < this.frameRemito.tablaRemitos.getColumnCount() && column >= 0) {
            Object value = this.frameRemito.tablaRemitos.getValueAt(row, column);

            if (value instanceof JButton) {

                ((JButton) value).doClick();
                JButton boton = (JButton) value;

                if (boton.getName().equals("v")) {

                    String numRemito = (String) this.frameRemito.tablaRemitos.getValueAt(rown, 3);

                    verRemito(numRemito);

                    try {
                        Desktop.getDesktop().open(new File(rutaRemito));
                    } catch (IOException ex) {
                        Logger.getLogger(ControladorEvento.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }

    }

    public void insertaRemito(String numRemito, String fechaRemito, String rutaArchivo, int idObra, int idCliente, double importe) {

        Remito remito = new Remito(numRemito, fechaRemito, idObra, idCliente, rutaArchivo, importe);

        bd.insertRemito(remito);

    }
    
    private void cargarCtaCorrienteCliente(){
         int idCliente = frameCtaCorriente.comboClientes.getSelectedIndex() + 1;

                ArrayList<CtaCorriente> ctaCliente = bd.getCtaCorriente(idCliente);
                int numeroCta = ctaCliente.size();
                frameCtaCorriente.modeloTablaCtaCorriente.setNumRows(numeroCta);

                if (numeroCta == 0) {
                    JOptionPane.showMessageDialog(null, "El cliente no tiene iniciada una cuenta corriente ");

                } else {

                    for (int i = 0; i < numeroCta; i++) {

                        CtaCorriente cta = ctaCliente.get(i);
                        int idCta = cta.getIdCta();
                        String fecha = cta.getFecha();
                        String descripcion = cta.getDescripcion();
                        double debe = cta.getDebe();
                        double haber = cta.getHaber();
                        double saldo = cta.getSaldo();
                        int id_Cliente = cta.getIdCliente();

                        frameCtaCorriente.modeloTablaCtaCorriente.setValueAt(fecha, i, 0);
                        frameCtaCorriente.modeloTablaCtaCorriente.setValueAt(cta, i, 1);
                        frameCtaCorriente.modeloTablaCtaCorriente.setValueAt(debe, i, 2);
                        frameCtaCorriente.modeloTablaCtaCorriente.setValueAt(haber, i, 3);
                        frameCtaCorriente.modeloTablaCtaCorriente.setValueAt(saldo, i, 4);

                    }
                    double sumatoria = getSaldoCtaCorriente();
                    frameCtaCorriente.txtSaldo.setText(String.valueOf(sumatoria));
                }
    }
    
      private double getSaldoCtaCorriente() {

        int numFilas = frameCtaCorriente.modeloTablaCtaCorriente.getRowCount() - 1;
        double sumatoria = 0;

        double importe = (double) frameCtaCorriente.modeloTablaCtaCorriente.getValueAt(numFilas, 4);
        sumatoria += (importe);

        return sumatoria;

    }
    
     private void insertarMovimientoEnCtaCorriente() {
        try{
        double debe = 0;
        double saldo = 0;
        double pago = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaComoCadena = sdf.format(frameCtaCorriente.txtFecha.getDate());
        String descripcion = frameCtaCorriente.txtDescripcion.getText();
        String stringDebe = frameCtaCorriente.txtDebe.getText();
        String stringPago = frameCtaCorriente.txtPaga.getText();
        int idCliente = frameCtaCorriente.comboClientes.getSelectedIndex() + 1;
        

        if (!stringDebe.isEmpty() && !stringPago.isEmpty()) {
            debe = Double.parseDouble(stringDebe);
             pago = Double.parseDouble(stringPago);
             saldo = debe - pago;
           CtaCorriente movimientoCta = new CtaCorriente(fechaComoCadena, descripcion, debe, pago, saldo, idCliente);

            bd.insertarCtaCorrienteCliente(movimientoCta);
           
        }else if (!stringPago.isEmpty()){
              pago = Double.parseDouble(stringPago);
            saldo -= pago;
            CtaCorriente movimientoCta = new CtaCorriente(fechaComoCadena, descripcion, debe, pago, saldo, idCliente);

            bd.insertarCtaCorrienteCliente(movimientoCta);
            
        }
        else {
            debe = Double.parseDouble(stringDebe);
            saldo += debe;
            CtaCorriente movimientoCta = new CtaCorriente(fechaComoCadena, descripcion, debe, pago, saldo, idCliente);

            bd.insertarCtaCorrienteCliente(movimientoCta);
        }
        cargarCtaCorrienteCliente();
        double sumatoria = getSaldoCtaCorriente();
        frameCtaCorriente.txtSaldo.setText(String.valueOf(sumatoria));       
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "La fecha debe ser ingresada");
        }
    }
    private void ActualizarCuentaCorriente() {
       try{
        double debe = 0;
        double pago = 0;
        double saldoActualizado = 0;
        int fila = frameCtaCorriente.modeloTablaCtaCorriente.getRowCount() - 1;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaComoCadena = sdf.format(frameCtaCorriente.txtFecha.getDate());
        String descripcion = frameCtaCorriente.txtDescripcion.getText();
        int idCliente = frameCtaCorriente.comboClientes.getSelectedIndex() + 1;
        String stringDebe =frameCtaCorriente.txtDebe.getText();
        String stringPago = frameCtaCorriente.txtPaga.getText();
        double saldo = (double) frameCtaCorriente.modeloTablaCtaCorriente.getValueAt(fila, 4);

        if (!stringDebe.isEmpty() && !stringPago.isEmpty()) {
              debe = Double.parseDouble(stringDebe);
             pago = Double.parseDouble(stringPago);
          saldoActualizado = (saldo +debe) - pago;
              CtaCorriente movimientoCta = new CtaCorriente(fechaComoCadena, descripcion, debe, pago, saldoActualizado, idCliente);

            bd.insertarCtaCorrienteCliente(movimientoCta);
        } else if ( !stringPago.isEmpty()) {
               pago = Double.parseDouble(stringPago);
            saldoActualizado = saldo - pago;
            CtaCorriente movimientoCta = new CtaCorriente(fechaComoCadena, descripcion, debe, pago, saldoActualizado, idCliente);

            bd.insertarCtaCorrienteCliente(movimientoCta);
        } else if (!stringDebe.isEmpty()) {
             debe = Double.parseDouble(stringDebe);
            saldoActualizado = saldo + debe;
            CtaCorriente movimientoCta = new CtaCorriente(fechaComoCadena, descripcion, debe, pago, saldoActualizado, idCliente);

            bd.insertarCtaCorrienteCliente(movimientoCta);
        }
        cargarCtaCorrienteCliente();       
        double sumatoria = getSaldoCtaCorriente();
        frameCtaCorriente.txtSaldo.setText(String.valueOf(sumatoria));
       }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "La fecha debe ser ingresada");
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Carácter inválido","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    private File fichero;

}
