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
public class Cliente { 
    
    public Cliente(){
        
    }
    
    private int idCliente;
    private String nombreCliente;
    private String telCliente;

    public Cliente(int idCliente, String nombreCliente, String telCliente, String email, boolean esInstalador) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.telCliente = telCliente;
        this.email = email;
        this.esInstalador = esInstalador;
    }
    private String email;
    private boolean esInstalador;

    public Cliente(String nombreCliente, String telCliente, String email, boolean esInstalador) {
        this.nombreCliente = nombreCliente;
        this.telCliente = telCliente;
        this.email = email;
        this.esInstalador = esInstalador;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getTelCliente() {
        return telCliente;
    }

    public void setTelCliente(String telCliente) {
        this.telCliente = telCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEsInstalador() {
        return esInstalador;
    }

    public void setEsInstalador(boolean esInstalador) {
        this.esInstalador = esInstalador;
    }
            
    
}
