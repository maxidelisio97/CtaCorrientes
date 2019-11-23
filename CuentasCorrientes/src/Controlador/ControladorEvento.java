
package Controlador;

import Modelo.BaseDatos;
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
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


public class ControladorEvento {
    
   public Principal vista;
   FrameObra frameObra;
   FrameRemito frameRemito;
   FrameCliente frameCliente;
   FrameVerRemitos frameVerRemitos;
   private BaseDatos bd;
   private DefaultTableModel modeloTablaRemitos= new DefaultTableModel();
   
    
    public ControladorEvento(Principal vista, BaseDatos bd,FrameVerRemitos frameVerRemitos){
        
        this.vista = vista;
        this.bd = bd;
        this.frameVerRemitos= frameVerRemitos;
        //frameVerRemitos = new FrameVerRemitos();
        cargarModeloTabla();
        
        
       this.vista.labelNuevaObra.addMouseListener(new MouseAdapter(){
            
           
           public void mousePressed(MouseEvent e){
               
               if(frameObra == null){
                        frameObra = new FrameObra();
                       vista.escritorio.add(frameObra);
                 vista.escritorio.getDesktopManager().maximizeFrame(frameObra);
                    frameObra.setVisible(true);
               }else{
                      vista.escritorio.getDesktopManager().maximizeFrame(frameObra);
               }
               }
           
           
           
       });
       
        this.vista.labelNuevoRemito.addMouseListener(new MouseAdapter(){
            
           
           public void mousePressed(MouseEvent e){
               
               if(frameRemito == null){
                        frameRemito = new FrameRemito();
                       vista.escritorio.add(frameRemito);
                 vista.escritorio.getDesktopManager().maximizeFrame(frameRemito);
                    frameRemito.setVisible(true);
               }else{
                      vista.escritorio.getDesktopManager().maximizeFrame(frameRemito);
               }
               }
          
       });
        
           this.vista.labelNuevoCliente.addMouseListener(new MouseAdapter(){
            
           
           public void mousePressed(MouseEvent e){
               
               if(frameCliente == null){
                        frameCliente = new FrameCliente();
                       vista.escritorio.add(frameCliente);
                 vista.escritorio.getDesktopManager().maximizeFrame(frameCliente);
                    frameCliente.setVisible(true);
               }else{
                      vista.escritorio.getDesktopManager().maximizeFrame(frameCliente);
               }
               }
          
       });
              this.vista.lblVerRemitos.addMouseListener(new MouseAdapter(){
            
           
           public void mousePressed(MouseEvent e){
                              
               
               
               if(!frameVerRemitos.isVisible()){
                      
                       vista.escritorio.add(frameVerRemitos);
                 vista.escritorio.getDesktopManager().maximizeFrame(frameVerRemitos);
                    frameVerRemitos.setVisible(true);
               }else{
                      vista.escritorio.getDesktopManager().maximizeFrame(frameVerRemitos);
               }
               }
          
       });
              
        frameVerRemitos.txtBuscarRemito.addKeyListener(new KeyAdapter (){
            
            @Override
            public void keyPressed(KeyEvent e){
               limpiarTabla();
               
               String criterio= frameVerRemitos.txtBuscarRemito.getText();
               
                   recibeCTAPorCriterio(criterio);
               
                frameVerRemitos.tablaRemitos.setModel(modeloTablaRemitos);
            }
        });
        
    }
    
    public void recibeCTAPorCriterio(String criterio){
        
        ResultSet rs=null;
        
        if(frameVerRemitos.radioCliente.isSelected()){
           
        rs = bd.dameCtaPorCliente(criterio);
        }else{
           
            rs =bd.dameCtaPorObra(criterio);
        }
      try {
           
      ResultSetMetaData metaData = rs.getMetaData();
           int numColumnas= metaData.getColumnCount();
           
           while(rs.next()){
               
            Object fila[] = new Object[numColumnas];
            
            for(int i=0;i<numColumnas;i++){
                
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
    
    public void limpiarTabla(){
        int filas = modeloTablaRemitos.getRowCount();
        
        if(filas>0){
            
            for(int i= filas-1;i>=0;i--){
                modeloTablaRemitos.removeRow(i);
            }
        }
        
    }
    
    
    
    public void cargarModeloTabla(){
        
        modeloTablaRemitos.addColumn("Nombre cliente");
        modeloTablaRemitos.addColumn("Nombre obra");
        modeloTablaRemitos.addColumn("Instalador");
        modeloTablaRemitos.addColumn("Numero remito");
        modeloTablaRemitos.addColumn("Archivo");
    }
    
    
}      
              

