package Controlador;

import Modelo.BaseDatos;
import Modelo.Cliente;
import Modelo.CopiarFicheros;
import Modelo.CrearCarpetaCliente;
import Modelo.Obra;
import VISTA.FrameCliente;
import VISTA.FrameObra;
import VISTA.FrameRemito;
import VISTA.FrameVerRemitos;
import VISTA.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
    private final String rutaPrincipal = "/home/ferc/Imagenes/remitos";
    private CrearCarpetaCliente createFile;

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
        this.vista.btnVerRemito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!frameVerRemitos.isVisible()) {

                    vista.escritorio.add(frameVerRemitos);
                    vista.escritorio.getDesktopManager().maximizeFrame(frameVerRemitos);
                    frameVerRemitos.setVisible(true);
                } else {
                    vista.escritorio.getDesktopManager().maximizeFrame(frameVerRemitos);
                }
            }

        });

        this.frameRemito.btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /*String numRemito = frameRemito.txtNumRemito.getText();
                String fecha = frameRemito.txtFechaRemito.getText();

                File remito = getFile();
                
                String rutaPrimera = remito.getAbsolutePath();
                //String rutaSegunda = remito.getAbsolutePath() 
                
                //createFile.renameFile(rutaPrimera, fecha);
                
                System.out.println(rutaPrimera);*/
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

                /* new CambiaPanel(vista.escritorio, vista.panelMenu);
                if (vista.btnRemito1.isSelected()) {
                    vista.btnObra.setColorNormal(new Color(214, 217, 223));
                    vista.btnObra.setColorHover(new Color(204, 204, 204));
                    vista.btnObra.setColorPressed(new Color(214, 217, 223));

                    vista.btnRemito1.setColorNormal(new Color(204, 204, 204));
                    vista.btnRemito1.setColorHover(new Color(204, 204, 204));
                    vista.btnRemito1.setColorPressed(new Color(204, 204, 204));

                    vista..setColorNormal(new Color(214, 217, 223));
                    vista.labelNuevoCliente.setColorHover(new Color(204, 204, 204));
                    vista.labelNuevoCliente.setColorPressed(new Color(214, 217, 223));

                    this.btnVerRemito.setColorNormal(new Color(214, 217, 223));
                    this.btnVerRemito.setColorHover(new Color(204, 204, 204));
                    this.btnVerRemito.setColorPressed(new Color(214, 217, 223));

                } else {
                    this.btnRemito1.setColorNormal(new Color(214, 217, 223));
                    this.btnRemito1.setColorHover(new Color(204, 204, 204));
                    this.btnRemito1.setColorPressed(new Color(214, 217, 223));

                }*/
                if (!frameRemito.isVisible()) {

                    vista.escritorio.add(frameRemito);
                    vista.escritorio.getDesktopManager().maximizeFrame(frameRemito);
                    frameRemito.setVisible(true);
                } else {
                    vista.escritorio.getDesktopManager().maximizeFrame(frameRemito);
                }

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

                createFile = new CrearCarpetaCliente();
                File remito = getFile();
                String nombreRemito = remito.getName();
                String nombreCarpetaCliente = frameRemito.ComboClientes.getSelectedItem().toString();
                String nombreCarpetaObra = frameRemito.ComboObra.getSelectedItem().toString();
                String pathRemito = remito.getAbsolutePath();
                String numRemito = frameRemito.txtNumRemito.getText();
                String fechaRemito = frameRemito.txtFechaRemito.getText();

               
                createFile.crearCarpeta(rutaPrincipal, nombreCarpetaCliente + "/" + nombreCarpetaObra);
                String rutaDestino = rutaPrincipal + "/" + nombreCarpetaCliente + "/" + nombreCarpetaObra + "/" + nombreRemito;

                createFile.moveFile(pathRemito, rutaDestino);
              
                if(numRemito!=null || fechaRemito!=null){
                
                String path = rutaPrincipal + "/" + nombreCarpetaCliente + "/" + nombreCarpetaObra + "/" + numRemito + " "+ fechaRemito;

                createFile.renameFile(pathRemito, path);
                
                }


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

    private File fichero;

}
