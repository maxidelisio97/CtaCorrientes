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
public class CtaCorriente {
    
    private int idCtaCorriente;
    private int idCliente;
    private int idObra;
    private int idRemito;

    public CtaCorriente(int idCtaCorriente, int idCliente, int idObra, int idRemito) {
        this.idCtaCorriente = idCtaCorriente;
        this.idCliente = idCliente;
        this.idObra = idObra;
        this.idRemito = idRemito;
    }

    public int getIdCtaCorriente() {
        return idCtaCorriente;
    }

    public void setIdCtaCorriente(int idCtaCorriente) {
        this.idCtaCorriente = idCtaCorriente;
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

    public int getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(int idRemito) {
        this.idRemito = idRemito;
    }
    
    
    
}
