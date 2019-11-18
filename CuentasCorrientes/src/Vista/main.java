
package Vista;

import Controlador.ControladorEvento;


public class main {


    public static void main(String[] args) {
        
        Principal vista = new Principal();
       vista.setVisible(true);
       
       ControladorEvento controlador = new ControladorEvento(vista);
       
    }
    
}
