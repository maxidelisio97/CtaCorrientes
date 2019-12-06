/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA;

import Modelo.Cliente;
import Modelo.Obra;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;





public class FrameRemito extends javax.swing.JInternalFrame {

  
    
  //  JWebBrowser navergador = new JWebBrowser();
    
    public FrameRemito() {
        initComponents();
        //this.webPane.setLayout(new BorderLayout());
        //navergador.navigate("");
        //this.webPane.add(navergador,BorderLayout.CENTER);
         tablaRemitos.setDefaultRenderer(Object.class, new Render());
        btnVerFactura.setName("v");
        
      
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        ComboObra = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtNumRemito = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtFechaRemito = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtBuscarRemito = new javax.swing.JTextField();
        ComboClientes = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnSubirRemito = new javax.swing.JButton();
        radioObra = new javax.swing.JRadioButton();
        radioCliente = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaRemitos = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        txtImporteRemito = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        checkbox = new javax.swing.JCheckBox();

        setBorder(null);

        jPanel1.setPreferredSize(new java.awt.Dimension(629, 400));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ComboObra.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboObra.setBorder(null);
        ComboObra.setLightWeightPopupEnabled(false);
        jPanel1.add(ComboObra, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 170, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 204, 255));
        jLabel1.setText("Num.R");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, 30));

        txtNumRemito.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtNumRemito.setForeground(new java.awt.Color(51, 204, 255));
        txtNumRemito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNumRemito.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel1.add(txtNumRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 170, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 204, 255));
        jLabel3.setText("Fecha");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 50, -1));

        txtFechaRemito.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtFechaRemito.setForeground(new java.awt.Color(51, 204, 255));
        txtFechaRemito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaRemito.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel1.add(txtFechaRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 170, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 204, 255));
        jLabel4.setText("Obra");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 50, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 204, 255));
        jLabel5.setText("Buscar");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 204, 255));
        jLabel6.setText("Cliente");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        txtBuscarRemito.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtBuscarRemito.setForeground(new java.awt.Color(51, 204, 255));
        txtBuscarRemito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscarRemito.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txtBuscarRemito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarRemitoActionPerformed(evt);
            }
        });
        jPanel1.add(txtBuscarRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 430, 30));

        ComboClientes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboClientes.setBorder(null);
        ComboClientes.setLightWeightPopupEnabled(false);
        jPanel1.add(ComboClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 170, 30));

        btnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosMaxi/addgris.png"))); // NOI18N
        btnGuardar.setBorder(null);
        btnGuardar.setBorderPainted(false);
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setFocusTraversalPolicyProvider(true);
        btnGuardar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosMaxi/add1.png"))); // NOI18N
        btnGuardar.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosMaxi/add1.png"))); // NOI18N
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 80, 60, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosMaxi/user.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 30, 30));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosMaxi/papers.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosMaxi/location.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 20, 30));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosMaxi/calendar_month.png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 30, 30));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosMaxi/construction_worker.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 30, 30));

        btnSubirRemito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosMaxi/arrow_upblack.png"))); // NOI18N
        btnSubirRemito.setBorder(null);
        btnSubirRemito.setBorderPainted(false);
        btnSubirRemito.setContentAreaFilled(false);
        btnSubirRemito.setFocusPainted(false);
        btnSubirRemito.setOpaque(true);
        btnSubirRemito.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosMaxi/arrow_up.png"))); // NOI18N
        jPanel1.add(btnSubirRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 60, 40));

        buttonGroup2.add(radioObra);
        radioObra.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        radioObra.setForeground(new java.awt.Color(102, 204, 255));
        radioObra.setText("Obra");
        jPanel1.add(radioObra, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 140, 80, -1));

        buttonGroup2.add(radioCliente);
        radioCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        radioCliente.setForeground(new java.awt.Color(102, 204, 255));
        radioCliente.setSelected(true);
        radioCliente.setText("Cliente");
        jPanel1.add(radioCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, 70, -1));

        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));

        tablaRemitos.setFont(new java.awt.Font("Decker", 0, 12)); // NOI18N
        tablaRemitos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaRemitos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 600, 340));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 204, 255));
        jLabel11.setText("Importe");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        txtImporteRemito.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtImporteRemito.setEnabled(false);
        jPanel1.add(txtImporteRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 150, 30));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 130, 30, 40));

        checkbox.setText("Cierre cuenta");
        jPanel1.add(checkbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarRemitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarRemitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarRemitoActionPerformed

     public JButton btnVerFactura = new JButton(new ImageIcon(getClass().getResource("/iconosMaxi/acrobat.png")));

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<Cliente> ComboClientes;
    public javax.swing.JComboBox<Obra> ComboObra;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnSubirRemito;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    public javax.swing.JCheckBox checkbox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JRadioButton radioCliente;
    public javax.swing.JRadioButton radioObra;
    public javax.swing.JTable tablaRemitos;
    public javax.swing.JTextField txtBuscarRemito;
    public javax.swing.JTextField txtFechaRemito;
    public javax.swing.JTextField txtImporteRemito;
    public javax.swing.JTextField txtNumRemito;
    // End of variables declaration//GEN-END:variables
}
