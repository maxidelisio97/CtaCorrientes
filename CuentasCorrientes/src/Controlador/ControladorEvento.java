
package Controlador;

import Vista.FrameCliente;
import Vista.FrameObra;
import Vista.FrameRemito;
import Vista.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ControladorEvento {
    
   public Principal vista;
   FrameObra frameObra;
   FrameRemito frameRemito;
   FrameCliente frameCliente;
    
    public ControladorEvento(Principal vista){
        
        this.vista = vista;
        
        
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
        
    }
    
}      
              /* if(vista.frameObra==null){
                   
                   System.out.println("null");
                   vista.escritorio.add(vista.frameObra);
                   vista.escritorio.getDesktopManager().maximizeFrame(vista.frameObra);
                   vista.frameObra.setVisible(true);
                   
               }else{
                     vista.escritorio.getDesktopManager().maximizeFrame(vista.frameObra);
               }*/
    
    

