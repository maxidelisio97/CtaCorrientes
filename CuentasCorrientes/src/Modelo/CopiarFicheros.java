package Modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopiarFicheros {

    public CopiarFicheros() {
    }

    public CopiarFicheros(String origen, String destino) {

        File ORIGEN = new File(origen);

        File DESTINO = new File(destino);

        try {
            InputStream in = new FileInputStream(ORIGEN);
            OutputStream out = new FileOutputStream(DESTINO);

            byte[] buf = new byte[1024];

            int len;

            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            in.close();
            out.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

}


