package Controlador;

import Modelo.BaseDatos;
import Modelo.Cliente;
import Modelo.CrearCarpetaCliente;
import Modelo.Obra;
import Vista.FrameCliente;
import Vista.FrameObra;
import Vista.FrameRemito;
import Vista.FrameVerRemitos;
import Vista.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

public class ControladorEvento {

    public Principal vista;
    FrameObra frameObra;
    FrameRemito frameRemito;
    FrameCliente frameCliente;
    FrameVerRemitos frameVerRemitos;
    private BaseDatos bd;
    private DefaultTableModel modeloTablaRemitos = new DefaultTableModel();
    private DefaultComboBoxModel modeloComboObra = new DefaultComboBoxModel();
    private DefaultComboBoxModel modeloComboClientes = new DefaultComboBoxModel();
    private final String rutaPrincipal = "/home/ferc/Imagenes/remitos/";
    private CrearCarpetaCliente createFile = null;
    private Cliente cliente = null;
    private Obra obra = null;
    private String path = null;

    public ControladorEvento(Principal vista, BaseDatos bd, FrameVerRemitos frameVerRemitos, FrameCliente frameCliente, FrameObra frameObra,
                                                                                                                                                                                                            FrameRemito frameRemito) {

        this.vista = vista;
        this.bd = bd;
        this.frameVerRemitos = frameVerRemitos;
        this.frameCliente = frameCliente;
        this.frameObra = frameObra;
        this.frameRemito = frameRemito;
        //frameVerRemitos = new FrameVerRemitos();
        cargarModeloTabla();
        cargarModeloCombocliente();
        cargarModeloComboObra();

        this.vista.labelNuevaObra.addMouseListener(new MouseAdapter() {

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

        this.vista.btnFrameRemitos.addActionListener(new ActionListener() {

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

        this.vista.labelNuevoCliente.addMouseListener(new MouseAdapter() {

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

        this.vista.lblVerRemitos.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {

                if (!frameVerRemitos.isVisible()) {

                    vista.escritorio.add(frameVerRemitos);
                    vista.escritorio.getDesktopManager().maximizeFrame(frameVerRemitos);
                    frameVerRemitos.setVisible(true);
                } else {
                    vista.escritorio.getDesktopManager().maximizeFrame(frameVerRemitos);
                }
            }

        });

        frameCliente.btnInsertaCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Cliente nuevoCliente = enviarCliente();
                bd.insertNuevoCliente(nuevoCliente);
            }

        });

        frameRemito.btnSubirRemito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                createFile = new CrearCarpetaCliente();
                
                String nombreCarpetaCliente = frameRemito.ComboClientes.getSelectedItem().toString();
                String nombreCarpetaObra = frameRemito.ComboObra.getSelectedItem().toString();
                //GUARDO LA RUTA DONDE CREE EL CLIENTE
                createFile.crearCarpeta(rutaPrincipal + nombreCarpetaCliente , nombreCarpetaObra);
                                                              
            }

        });

        frameRemito.ComboClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                modeloComboObra.removeAllElements();

                ArrayList<Obra> listaObra = bd.selectObra(frameRemito.ComboClientes.getSelectedIndex() + 1);

                for (Obra c : listaObra) {

                    modeloComboObra.addElement(c);

                }

                frameRemito.ComboObra.setModel(modeloComboObra);

            }

        });

        frameVerRemitos.txtBuscarRemito.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                limpiarTabla();

                String criterio = frameVerRemitos.txtBuscarRemito.getText();

                recibeCTAPorCriterio(criterio);

                frameVerRemitos.tablaRemitos.setModel(modeloTablaRemitos);
            }

        });

    }

    public void recibeCTAPorCriterio(String criterio) {

        ResultSet rs = null;

        if (frameVerRemitos.radioCliente.isSelected()) {

            rs = bd.dameCtaPorCliente(criterio);
        } else {

            rs = bd.dameCtaPorObra(criterio);
        }
        try {

            ResultSetMetaData metaData = rs.getMetaData();
            int numColumnas = metaData.getColumnCount();

            while (rs.next()) {

                Object fila[] = new Object[numColumnas];

                for (int i = 0; i < numColumnas; i++) {

                    fila[0] = rs.getObject(1);
                    fila[1] = rs.getObject(2);
                    fila[2] = rs.getObject(3);
                    fila[3] = rs.getObject(4);
                    fila[4] = rs.getObject(5);

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

        return cliente;

    }

    public Obra enviarObra() {

        String nuevaObra = frameObra.txtNuevaObra.getText();

        Obra obra = new Obra(nuevaObra);

        return obra;

    }

    public void cargarModeloTabla() {

        modeloTablaRemitos.addColumn("Nombre cliente");
        modeloTablaRemitos.addColumn("Nombre obra");
        modeloTablaRemitos.addColumn("Instalador");
        modeloTablaRemitos.addColumn("Numero remito");
        modeloTablaRemitos.addColumn("Archivo");
    }

    private void cargarModeloCombocliente() {
        ArrayList<Cliente> listaCliente = new ArrayList<>();
        listaCliente = bd.selectClientes();

        for (Cliente c : listaCliente) {
            modeloComboClientes.addElement(c);
            
           

        }
        frameRemito.ComboClientes.setModel(modeloComboClientes);
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
    public String getNameFile() {

        JFileChooser fc = new JFileChooser();

        int seleccion = fc.showOpenDialog(frameRemito);

        if (seleccion == JFileChooser.APPROVE_OPTION) {

             fichero = fc.getSelectedFile();

            path = fichero.getPath();

            //System.out.println(path);
        }
        return path;

    }
    
    private File fichero;

}
