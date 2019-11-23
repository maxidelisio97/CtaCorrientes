/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.*;

public class BaseDatos {

    public BaseDatos() {

        miConexion = new Conexion();

    }

    public ResultSet dameCtaPorCliente(String nombreCliente) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn = miConexion.dameConexion();

            ps = conn.prepareStatement("SELECT NOM_CLIENTE,NOM_OBRA,ES_INSTALADOR_CLIENTE_,NUM_REMITO,ARCHIVO FROM dbcta.CTA_CORRIENTE INNER JOIN dbcta.CLIENTE ON dbcta.CTA_CORRIENTE.ID_CLIENTE = dbcta.CLIENTE.ID_CLIENTE INNER JOIN dbcta.OBRA ON dbcta.CTA_CORRIENTE.ID_OBRA=dbcta.OBRA.ID_OBRA INNER JOIN dbcta.REMITO ON dbcta.CTA_CORRIENTE.ID_REMITO = dbcta.REMITO.ID_REMITO WHERE NOM_CLIENTE LIKE" + "'"+ nombreCliente + "%'" );

            rs = ps.executeQuery();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return rs;
    }

    public ResultSet dameCtaPorObra(String nombreObra) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn = miConexion.dameConexion();

            ps = conn.prepareStatement("SELECT NOM_CLIENTE,NOM_OBRA,ES_INSTALADOR_CLIENTE_,NUM_REMITO,ARCHIVO FROM dbcta.CTA_CORRIENTE INNER JOIN dbcta.CLIENTE ON dbcta.CTA_CORRIENTE.ID_CLIENTE = dbcta.CLIENTE.ID_CLIENTE INNER JOIN dbcta.OBRA ON dbcta.CTA_CORRIENTE.ID_OBRA=dbcta.OBRA.ID_OBRA INNER JOIN dbcta.REMITO ON dbcta.CTA_CORRIENTE.ID_REMITO = dbcta.REMITO.ID_REMITO WHERE NOM_OBRA LIKE" + "'"+ nombreObra + "%'" );

            rs = ps.executeQuery();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return rs;
    }
    private Conexion miConexion;
}
