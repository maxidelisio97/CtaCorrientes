
package Modelo;

import java.io.File;

public class CrearCarpetaCliente  {

    public CrearCarpetaCliente(){ }
    
    public void crearCarpeta(String rutaDirectorio, String Obra){
        
         nombreCliente = new File(rutaDirectorio);
         
         nombreCliente.mkdir();
         
         
        
    }
    
    public String dameRuta(){
        
        return null;
        
    }
    
    private File nombreCliente;
    
}

