/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author maxid
 */
public class Obra {
    
    public Obra(){
        
    }
    
    private int idObra;
    private String nombreObra;
    private int idCliente;

    public Obra(int idObra, String nombreObra) {
        this.idObra = idObra;
        this.nombreObra = nombreObra;
      
    }

    public Obra(String nombreObra,int idCliente) {
       
        this.nombreObra = nombreObra;
        this.idCliente=idCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdObra() {
        return idObra;
    }

    public void setIdObra(int idObra) {
        this.idObra = idObra;
    }

    public String getNombreObra() {
        return nombreObra;
    }

    public void setNombreObra(String nombreObra) {
        this.nombreObra = nombreObra;
    }

    public Obra(String nombreObra) {
        this.nombreObra = nombreObra;
    }

    @Override
    public String toString() {
        return    nombreObra;
    }

    
    
}
