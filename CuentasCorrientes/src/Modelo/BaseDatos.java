/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.*;
import java.util.ArrayList;

public class BaseDatos {
    
    Connection conn = null;
        ResultSet rs = null;
        Statement st = null;
        PreparedStatement ps = null;

    public BaseDatos() {

        miConexion = new Conexion();

    }

     public ResultSet getRemito(String id){
       
        
        try{
            
            conn = miConexion.dameConexion();
            ps = conn.prepareStatement("SELECT RUTA_ARCHIVO FROM REMITO WHERE NUM_REMITO=?");
            ps.setString(1, id);
            rs = ps.executeQuery();
            
            
            
            
        }catch(Exception e){
            
        }
        return rs;
    }
    
    
    
    public ResultSet dameCtaPorClienteyObra(String cliente , String obra) {

        try {

            conn = miConexion.dameConexion();

            ps = conn.prepareStatement("SELECT  NOM_CLIENTE,NOM_OBRA,ES_INSTALADOR_CLIENTE_,NUM_REMITO ,IMPORTE_CIERRE,RUTA_ARCHIVO FROM dbcta.REMITO INNER JOIN dbcta.CLIENTE ON dbcta.REMITO.ID_CLIENTE = dbcta.CLIENTE.ID_CLIENTE INNER JOIN dbcta.OBRA ON dbcta.REMITO.ID_OBRA=dbcta.OBRA.ID_OBRA WHERE NOM_CLIENTE=? AND NOM_OBRA=?"  );

            ps.setString(1, cliente);
            ps.setString(2, obra);
            rs = ps.executeQuery();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return rs;
    }
    
     public ResultSet dameCtaPorCliente(String cliente) {


        try {

            conn = miConexion.dameConexion();

            ps = conn.prepareStatement("SELECT  NOM_CLIENTE,NOM_OBRA,ES_INSTALADOR_CLIENTE_,NUM_REMITO ,IMPORTE_CIERRE,RUTA_ARCHIVO FROM dbcta.REMITO INNER JOIN dbcta.CLIENTE ON dbcta.REMITO.ID_CLIENTE = dbcta.CLIENTE.ID_CLIENTE INNER JOIN dbcta.OBRA ON dbcta.REMITO.ID_OBRA=dbcta.OBRA.ID_OBRA WHERE NOM_CLIENTE LIKE" + "'"+ cliente + "%'" );

          
           
            rs = ps.executeQuery();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return rs;
    }

     
    public ResultSet dameCtaPorObra(String nombreObra) {

        try {

            conn = miConexion.dameConexion();

            ps = conn.prepareStatement("SELECT  NOM_CLIENTE,NOM_OBRA,ES_INSTALADOR_CLIENTE_,NUM_REMITO ,IMPORTE_CIERRE,RUTA_ARCHIVO FROM dbcta.REMITO INNER JOIN dbcta.CLIENTE ON dbcta.REMITO.ID_CLIENTE = dbcta.CLIENTE.ID_CLIENTE INNER JOIN dbcta.OBRA ON dbcta.REMITO.ID_OBRA=dbcta.OBRA.ID_OBRA WHERE NOM_OBRA LIKE" + "'"+ nombreObra + "%'" );

            
            rs = ps.executeQuery();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return rs;
    }
    
   
    public ResultSet dameCtaPorCliente2(String nombreCliente) {

 

        try {

            conn = miConexion.dameConexion();

            ps = conn.prepareStatement("SELECT  NOM_CLIENTE,NOM_OBRA,ES_INSTALADOR_CLIENTE_,NUM_REMITO ,IMPORTE_CIERRE,RUTA_ARCHIVO FROM dbcta.REMITO INNER JOIN dbcta.CLIENTE ON dbcta.REMITO.ID_CLIENTE = dbcta.CLIENTE.ID_CLIENTE INNER JOIN dbcta.OBRA ON dbcta.REMITO.ID_OBRA=dbcta.OBRA.ID_OBRA WHERE  NOM_CLIENTE=?");
            ps.setString(1, nombreCliente);
            rs = ps.executeQuery();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return rs;
    }
    
    
    public void insertNuevoCliente(Cliente nuevoCliente){
      
        
        try{
            
            conn = miConexion.dameConexion();
            
            ps = conn.prepareStatement("INSERT INTO CLIENTE(NOM_CLIENTE,TEL_CLIENTE,EMAIL_CLIENTE,ES_INSTALADOR_CLIENTE_) VALUES(?,?,?,?)");
            
            ps.setString(1, nuevoCliente.getNombreCliente());
            ps.setString(2, nuevoCliente.getTelCliente());
            ps.setString(3, nuevoCliente.getEmail());
            ps.setBoolean(4, nuevoCliente.isEsInstalador());
            
            ps.execute();
            
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void insertNuevaObra(Obra nuevaObra){
        
        
        try{
            
            conn = miConexion.dameConexion();
            
            ps = conn.prepareStatement("INSERT INTO OBRA(NOM_OBRA,ID_CLIENTE) VALUES(?,?)");
            
            ps.setString(1, nuevaObra.getNombreObra());
            ps.setInt(2, nuevaObra.getIdCliente());
          
            
            ps.execute();
            
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public ArrayList selectObra(int criterio){
        
        
         ArrayList<Obra> listaObra = new ArrayList<>();
        
         try{
             
             conn = miConexion.dameConexion();
             
             ps = conn.prepareStatement("SELECT NOM_OBRA FROM dbcta.OBRA WHERE ID_CLIENTE = ?");
             
             ps.setInt(1,criterio);
             
             rs = ps.executeQuery();
             
             while(rs.next()){
                 
                 String nombre = rs.getString("NOM_OBRA");
                 
                 Obra obra = new Obra(nombre);
                 
                 listaObra.add(obra);
                 
             }
             
         }catch(Exception e){}
        
         
         return listaObra;
    }
    
   public ArrayList<CtaCorriente> getCtaCorriente(int idcliente) {
        ArrayList<CtaCorriente> listarCta = new ArrayList<CtaCorriente>();

        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM cta_corriente WHERE ID_CLIENTE = " + idcliente + " ORDER BY `cta_corriente`.`FECHA` ASC");

            while (rs.next()) {
                //  ( clave, nombre, descripcion, stock, codigoProveedor
                int idCta= rs.getInt("ID_CTACORRIENTE");
                String fecha = rs.getString("FECHA");
                String descripcion = rs.getString("DESCRIPCION");
               double debe = rs.getDouble("DEBE");
               double haber = rs.getDouble("HABER");
               double saldo = rs.getDouble("SALDO");
               int idCliente = rs.getInt("ID_CLIENTE");

                CtaCorriente cta = new CtaCorriente(idCta, fecha, descripcion, debe,haber,saldo,idCliente);

                listarCta.add(cta);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return listarCta;
    }
    
    public ArrayList selectClientes(){
    
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        
        try{
            
            conn = miConexion.dameConexion();
            
            st = conn.createStatement();
            
            rs = st.executeQuery("SELECT NOM_CLIENTE FROM CLIENTE");
            
            while(rs.next()){
                
                String nombre = rs.getString("NOM_CLIENTE");
                
                Cliente cliente = new Cliente(nombre);
                
                listaClientes.add(cliente);
                
                
                
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaClientes;
    }
    
     public void insertRemito(Remito remito){
       
        try{
            
            conn = miConexion.dameConexion();
            
            ps = conn.prepareStatement("INSERT INTO remito(NUM_REMITO,FECHA_REMITO,RUTA_ARCHIVO,IMPORTE_CIERRE,ID_OBRA,ID_CLIENTE) VALUES(?,?,?,?,?,?)");
            
            ps.setString(1, remito.getNumRemito());
            ps.setString(2, remito.getFechaRemito());
            ps.setString(3, remito.getRutaPdf());
            ps.setDouble(4, remito.getImporte());
            ps.setInt(5, remito.getIdObra());
            ps.setInt(6, remito.getIdCliente());
            
            
            ps.execute();
            
          
        }catch(Exception e){
            e.printStackTrace();
        }
       
    }
    
     
      public ArrayList selectObra(){        
        
        
        ArrayList<Obra> listaobra = new ArrayList<>();
        
        try{
            
            conn = miConexion.dameConexion();
            
            st = conn.createStatement();
            
            rs = st.executeQuery("SELECT NOM_OBRA FROM OBRA");
            
            while(rs.next()){
                  
                String nombre = rs.getString("NOM_OBRA");
                
                Obra obra = new Obra(nombre);
                
                listaobra.add(obra);
            }
            
        
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaobra;
    }
    
    
    private Conexion miConexion;

    public void insertarCtaCorrienteCliente(CtaCorriente cta) {
          try {

            String sql = "INSERT INTO cta_corriente (FECHA,DESCRIPCION,DEBE,HABER,SALDO,ID_CLIENTE) VALUES (?,?,?,?,?,?)";

            ps = conn.prepareStatement(sql);
            ps.setString(1, cta.getFecha());
            ps.setString(2, cta.getDescripcion());
            ps.setDouble(3, cta.getDebe());
            ps.setDouble(4, cta.getHaber());
            ps.setDouble(5, cta.getSaldo());
            ps.setInt(6, cta.getIdCliente());

            ps.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void eliminarFilaCtaCorrienteCliente(CtaCorriente cta) {
         try {

            ps = conn.prepareStatement("DELETE FROM cta_corriente WHERE ID_CTACORRIENTE=?");

            ps.setInt(1, cta.getIdCta());

            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<CtaCorriente> ctaCorrienteClientePorFechas(int idCta, String fechaComoCadena, String fechaDeHoy) {
          ArrayList<CtaCorriente> listaCta = new ArrayList<CtaCorriente>();

        try {

            ps = conn.prepareStatement("SELECT * FROM cta_corriente WHERE ID_CLIENTE=? AND FECHA BETWEEN ? AND ? ORDER BY `cta_corriente`.`ID_CTACORRIENTE` ASC");
            ps.setInt(1,idCta);
            ps.setString(2, fechaComoCadena);
            ps.setString(3, fechaDeHoy);
            
            rs = ps.executeQuery();

            while (rs.next()) {

                int idCuenta = rs.getInt(1);
                String fecha = rs.getString(2);
                String descripcion = rs.getString(3);
                double debe = rs.getDouble(4);
                double haber = rs.getDouble(5);
                double saldo = rs.getDouble(6);
                int id_Cliente = rs.getInt(7);

                CtaCorriente cta = new CtaCorriente(idCta, fecha, descripcion, debe, haber, saldo, id_Cliente);

                listaCta.add(cta);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaCta;
    }
}
