/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA;

import Modelo.Cliente;
import Modelo.Obra;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import java.awt.BorderLayout;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;





public class FrameRemito extends javax.swing.JInternalFrame {

  
    
    JWebBrowser navergador = new JWebBrowser();
    
    public FrameRemito() {
        initComponents();
        this.webPane.setLayout(new BorderLayout());
        navergador.navigate("");
        this.webPane.add(navergador);
        
      
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ComboObra = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtNumRemito = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtFechaRemito = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtDirRemito = new javax.swing.JTextField();
        ComboClientes = new javax.swing.JComboBox<>();
        laminaRemito = new javax.swing.JPanel();
        webPane = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnSubirRemito = new javax.swing.JButton();

        setBorder(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(629, 400));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ComboObra.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboObra.setBorder(null);
        ComboObra.setLightWeightPopupEnabled(false);
        jPanel1.add(ComboObra, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 170, -1));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 204, 255));
        jLabel1.setText("Num.R");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, 30));

        txtNumRemito.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtNumRemito.setForeground(new java.awt.Color(51, 204, 255));
        txtNumRemito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNumRemito.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel1.add(txtNumRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 170, 30));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 204, 255));
        jLabel3.setText("Fecha");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 50, -1));

        txtFechaRemito.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtFechaRemito.setForeground(new java.awt.Color(51, 204, 255));
        txtFechaRemito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaRemito.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel1.add(txtFechaRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 170, 30));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 204, 255));
        jLabel4.setText("Obra");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 50, -1));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 204, 255));
        jLabel5.setText("Dirección");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 204, 255));
        jLabel6.setText("Cliente");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        txtDirRemito.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtDirRemito.setForeground(new java.awt.Color(51, 204, 255));
        txtDirRemito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDirRemito.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txtDirRemito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDirRemitoActionPerformed(evt);
            }
        });
        jPanel1.add(txtDirRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 430, 30));

        ComboClientes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboClientes.setBorder(null);
        ComboClientes.setLightWeightPopupEnabled(false);
        jPanel1.add(ComboClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 170, 30));

        laminaRemito.setBackground(new java.awt.Color(102, 102, 102));

        webPane.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout laminaRemitoLayout = new javax.swing.GroupLayout(laminaRemito);
        laminaRemito.setLayout(laminaRemitoLayout);
        laminaRemitoLayout.setHorizontalGroup(
            laminaRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(webPane, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
        );
        laminaRemitoLayout.setVerticalGroup(
            laminaRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(laminaRemitoLayout.createSequentialGroup()
                .addComponent(webPane, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel1.add(laminaRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 610, 400));

        btnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/iconosMaxi/addgris.png"))); // NOI18N
        btnGuardar.setBorder(null);
        btnGuardar.setBorderPainted(false);
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setFocusTraversalPolicyProvider(true);
        btnGuardar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/iconosMaxi/add1.png"))); // NOI18N
        btnGuardar.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/iconosMaxi/add1.png"))); // NOI18N
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 80, 60, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/iconosMaxi/user.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/iconosMaxi/papers.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/iconosMaxi/location.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/iconosMaxi/calendar_month.png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/iconosMaxi/construction_worker.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, -1, -1));

        btnSubirRemito.setBackground(new java.awt.Color(255, 255, 255));
        btnSubirRemito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/iconosMaxi/arrow_upblack.png"))); // NOI18N
        btnSubirRemito.setBorder(null);
        btnSubirRemito.setBorderPainted(false);
        btnSubirRemito.setContentAreaFilled(false);
        btnSubirRemito.setFocusPainted(false);
        btnSubirRemito.setOpaque(true);
        btnSubirRemito.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/iconosMaxi/arrow_up.png"))); // NOI18N
        jPanel1.add(btnSubirRemito, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 60, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDirRemitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDirRemitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDirRemitoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<Cliente> ComboClientes;
    public javax.swing.JComboBox<Obra> ComboObra;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnSubirRemito;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel laminaRemito;
    public javax.swing.JTextField txtDirRemito;
    public javax.swing.JTextField txtFechaRemito;
    public javax.swing.JTextField txtNumRemito;
    public javax.swing.JLabel webPane;
    // End of variables declaration//GEN-END:variables
}
