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
            
            ps.executeQuery();
            
            
            
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
            
            ps = conn.prepareStatement("INSERT INTO OBRA(NOM_OBRA) VALUES(?)");
            
            ps.setString(1, nuevaObra.getNombreObra());
          
            
            ps.executeQuery();
            
            
            
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
            
            ps = conn.prepareStatement("INSERT INTO REMITO(NUM_REMITO,FECHA_REMITO,ID_OBRA,ID_CLIENTE,ARCHIVO) VALUES(?,?,?,?,?)");
            
            ps.setString(1, remito.getNumRemito());
            ps.setDate(2, remito.getFechaRemito());
            ps.setInt(3, remito.getIdObra());
            ps.setInt(4, remito.getIdCliente());
            ps.setString(5, remito.getRutaPdf());
            
            ps.executeQuery();
            
          
        }catch(Exception e){
            e.printStackTrace();
        }
       
    }
    
     
      public ArrayList selectObreishon(){
        
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
