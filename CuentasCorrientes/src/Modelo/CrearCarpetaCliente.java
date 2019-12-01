package Modelo;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class CrearCarpetaCliente {

    public CrearCarpetaCliente() {
    }

    public void crearCarpeta( String setNameCarpetaCliente, String setNameCarpetaObra) {

        nombreCliente = new File(setNameCarpetaCliente,setNameCarpetaObra);
        
        nombreCliente.mkdirs();
        
        

    }

    public void moveFile(String urlOrigen, String urlDestino) {

        origenPath = FileSystems.getDefault().getPath(urlOrigen);

        destinoPath = FileSystems.getDefault().getPath(urlDestino);

        try {
            Files.move(origenPath, destinoPath, StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException e) {
            System.err.println(e);
        }

    }

    private File nombreObra;
    private Path destinoPath;
    private Path origenPath;
    private File nombreCliente;

}
