
package VISTA;

import Controlador.ControladorEvento;
import Modelo.BaseDatos;
;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class main {


    public static void main(String[] args) {
        
      
        
        
        Principal vista = new Principal();
        BaseDatos bd = new BaseDatos();
       
        FrameCliente frameCliente = new FrameCliente();
        FrameRemito frameRemito = new FrameRemito();
        FrameObra frameObra = new FrameObra();
        FrameVerCtaCorriente frameCtaCorriente = new FrameVerCtaCorriente();
        
       vista.setVisible(true);
       
       ControladorEvento controlador = new ControladorEvento(vista,bd,frameCliente,frameObra,frameRemito,frameCtaCorriente);
       
      /*  SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                 NativeInterface.open();
            }
        
        });*/
       
      
                                                                                                                                                                                                      
                                                                                                                                                                                                               
        
          try {
            // Set cross-platform Java L&F (also called "Metal")
        UIManager.setLookAndFeel(
            UIManager.getCrossPlatformLookAndFeelClassName());
    } 
    catch (UnsupportedLookAndFeelException e) {
       // handle exception
    }
    catch (ClassNotFoundException e) {
       // handle exception
    }
    catch (InstantiationException e) {
       // handle exception
    }
    catch (IllegalAccessException e) {
       // handle exception
    }
        
     //Create and show the GUI.
}
     
       
    }
    

