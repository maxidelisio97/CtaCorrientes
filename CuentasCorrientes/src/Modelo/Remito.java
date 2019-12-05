/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;

/**
 *
 * @author maxid
 */
public class Remito {
    
    public Remito(){
        
    }
    
    private int idRemito;
    private String numRemito;
    private String fechaRemito;
    private int idObra;
    private int idCliente;
    private String rutaPdf;

    public Remito(int idRemito, String numRemito, String fechaRemito, int idObra, int idCliente, String rutaPdf) {
        this.idRemito = idRemito;
        this.numRemito = numRemito;
        this.fechaRemito = fechaRemito;
        this.idObra = idObra;
        this.idCliente = idCliente;
        this.rutaPdf = rutaPdf;
    }

    public Remito(String numRemito, String fechaRemito, int idObra, int idCliente, String rutaPdf) {
        this.numRemito = numRemito;
        this.fechaRemito = fechaRemito;
        this.idObra = idObra;
        this.idCliente = idCliente;
        this.rutaPdf = rutaPdf;
    }

    public int getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(int idRemito) {
        this.idRemito = idRemito;
    }

    public String getNumRemito() {
        return numRemito;
    }

    public void setNumRemito(String numRemito) {
        this.numRemito = numRemito;
    }

    public String getFechaRemito() {
        return fechaRemito;
    }

    public void setFechaRemito(String fechaRemito) {
        this.fechaRemito = fechaRemito;
    }

    public int getIdObra() {
        return idObra;
    }

    public void setIdObra(int idObra) {
        this.idObra = idObra;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getRutaPdf() {
        return rutaPdf;
    }

    public void setRutaPdf(String rutaPdf) {
        this.rutaPdf = rutaPdf;
    }
    
    
}
