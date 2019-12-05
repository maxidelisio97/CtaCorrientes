/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ferc
 */
public class Conexion {
    public Conexion(){
        
        
    }
    
    public Connection dameConexion(){
        
        Connection conn=null;
        try {
             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbcta?serverTimezone=UTC","root","");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }
    
}
