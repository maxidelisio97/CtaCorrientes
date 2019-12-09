/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.*;
import java.util.ArrayList;

public class BaseDatos {

    public BaseDatos() {

        miConexion = new Conexion();

    }

     public ResultSet getRemito(String id){
        
        Connection conn =null;
        PreparedStatement ps = null;
        ResultSet rs =null;
       
        
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

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

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

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

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

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

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

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

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
        
        Connection conn=null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        
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
        
        Connection conn=null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        
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
        
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;
        PreparedStatement ps = null;
        
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
    
   
    
    public ArrayList selectClientes(){
        
        Connection conn = null;
        ResultSet rs = null;
        Statement st=null;
        
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
        
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps=null;
        
       
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
        
        Connection conn = null;
        ResultSet rs = null;
        Statement st=null;
        
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
}
