package Controlador;

import Modelo.BaseDatos;
import Modelo.Cliente;
import Modelo.CopiarFicheros;
import Modelo.CrearCarpetaCliente;
import Modelo.Obra;
import Modelo.Remito;
import VISTA.FrameCliente;
import VISTA.FrameObra;
import VISTA.FrameRemito;
import VISTA.Principal;
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
    
    private BaseDatos bd;
    private DefaultTableModel modeloTablaRemitos = new DefaultTableModel();
    
    private DefaultComboBoxModel modeloComboObra = new DefaultComboBoxModel();
    private DefaultComboBoxModel modeloComboClientes = new DefaultComboBoxModel();
    private final String rutaPrincipal = "C:/Users/maxid/remitos";
    private CrearCarpetaCliente createFile;
    String rutaRemito = "";

    private String path = null;

    public ControladorEvento(Principal vista, BaseDatos bd,  FrameCliente frameCliente, FrameObra frameObra,
            FrameRemito frameRemito) {

        this.vista = vista;
        this.bd = bd;
   
        this.frameCliente = frameCliente;
        this.frameObra = frameObra;
        this.frameRemito = frameRemito;

        cargarModeloTabla();
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

        

        this.frameRemito.btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
                     double  importe=0;
              
             
                createFile = new CrearCarpetaCliente();
                File remito = getFile();
                String nombreCarpetaObra =  frameRemito.ComboObra.getSelectedItem().toString();
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
                         createFile.renameFile(pathRemito, rutaPrincipal + "/" + nombreCarpetaCliente + "/" + nombreCarpetaObra + "/" + numRemito + " " + fechaRemito+ ".pdf" );
                        createFile.moveFile(pathRemito, rutaPrincipal + "/" + nombreCarpetaCliente + "/" + nombreCarpetaObra + "/" + nombreRemito);
                          if(frameRemito.checkbox.isSelected()){
                       importe =Double.parseDouble(frameRemito.txtImporteRemito.getText());
                }else{
                    importe=0;
                }
                insertaRemito(numRemito, fechaRemito, rutaPrincipal + "/" + nombreCarpetaCliente + "/" + nombreCarpetaObra + "/" + numRemito + " " + fechaRemito + ".pdf", idObra, idCliente,importe);
                    

                }
            }
            
        });
        
     frameRemito.checkbox.addActionListener(new ActionListener() {
           
         public void actionPerformed(ActionEvent e) {
                 boolean state =   frameRemito.checkbox.isSelected();
                
                 if(state==true){
                       frameRemito.txtImporteRemito.setEnabled(true);
                 }else{
                        frameRemito.txtImporteRemito.setEnabled(false);
                 }
             
                
                
               
                
            }
     });
     

        frameRemito.ComboClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                System.out.println(frameRemito.ComboClientes.getSelectedItem());
                System.out.println(frameRemito.ComboObra.getSelectedItem());
                modeloComboObra.removeAllElements();

                ArrayList<Obra> listaObra = bd.selectObra(frameRemito.ComboClientes.getSelectedIndex() + 1);

                for (Obra c : listaObra) {

                    modeloComboObra.addElement(c);

                }

                frameRemito.ComboObra.setModel(modeloComboObra);

            }

        });

        frameRemito.txtBuscarRemito.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

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

        int idCliente = frameObra.comboClientes.getSelectedIndex() + 1;

        Obra obra = new Obra(nuevaObra, idCliente);

        return obra;

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

    public void insertaRemito(String numRemito, String fechaRemito, String rutaArchivo, int idObra, int idCliente,double importe) {

        Remito remito = new Remito(numRemito, fechaRemito, idObra, idCliente, rutaArchivo,importe);

        bd.insertRemito(remito);

    }

    private File fichero;

}
