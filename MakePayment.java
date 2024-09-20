/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Hotel_Reservation_System;

import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author Ukasha Gamer
 */
public class MakePayment extends javax.swing.JFrame {

    /**
     * Creates new form MakePayment
     */
    public MakePayment() {
        initComponents();
    }
    private String customerName;
    private java.sql.Date checkInDate;
    private java.sql.Date checkOutDate;
    private String roomNumber;
    private String roomType;
    private double totalPrice;

    public MakePayment(String customerName, java.sql.Date checkInDate, java.sql.Date checkOutDate, String roomNumber, String roomType, double totalPrice) {
        initComponents();

        this.customerName = customerName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.totalPrice = totalPrice;

        // Display reservation details on the Payment page
        CustomerNametxt.setText(customerName);
        CheckInDatetxt.setText(checkInDate.toString());
        CheckkOutDatetxt.setText(checkOutDate.toString());
        roomNotxt.setText(roomNumber);
        roomTypetxt.setText(roomType);
        totalPricetxt.setText(String.format("PKR %.2f", totalPrice));

    }

    private boolean validatePaymentInformation() {
        // Check if payment method is selected
        if (cbPaymentMethod.getSelectedItem() == null || cbPaymentMethod.getSelectedItem().toString().equals("Select  your payment method...")) {
            JOptionPane.showMessageDialog(this, "Please select payment method.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void insertReservationDataPending() {
        String sql = "INSERT INTO Reservations ( customer_name, check_in_date, check_out_date, room_number, room_type, total_price,  payment_method, payment_status ) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?,  'Pending' )";

        try (
                Connection con = DBConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, CustomerNametxt.getText());
            pstmt.setDate(2, java.sql.Date.valueOf(CheckInDatetxt.getText()));
            pstmt.setDate(3, java.sql.Date.valueOf(CheckkOutDatetxt.getText()));
            pstmt.setString(4, roomNotxt.getText()); // Updated to use room number
            pstmt.setString(5, roomTypetxt.getText()); // Assuming you have a text field for room type
            pstmt.setDouble(6, Double.parseDouble(totalPricetxt.getText().replace("PKR ", "")));
            pstmt.setString(7, cbPaymentMethod.getSelectedItem().toString());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving reservation details. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void insertReservationDataComplete() {
        String sql = "INSERT INTO Reservations ( customer_name, check_in_date, check_out_date, room_number, room_type, total_price,  payment_method, payment_status ) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?,   'Completed' )";

        try (
                Connection con = DBConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, CustomerNametxt.getText());
            pstmt.setDate(2, java.sql.Date.valueOf(CheckInDatetxt.getText()));
            pstmt.setDate(3, java.sql.Date.valueOf(CheckkOutDatetxt.getText()));
            pstmt.setString(4, roomNotxt.getText()); // Updated to use room number
            pstmt.setString(5, roomTypetxt.getText()); // Assuming you have a text field for room type
            pstmt.setDouble(6, Double.parseDouble(totalPricetxt.getText().replace("PKR ", "")));
            pstmt.setString(7, cbPaymentMethod.getSelectedItem().toString());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving reservation details. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateReservationStatusToCompleted() {
        String sql = "UPDATE Reservations SET payment_status = 'Completed' WHERE customer_name = ? AND check_in_date = ? AND check_out_date = ? AND room_number = ? AND payment_status = 'Pending'";

        try (
                Connection con = DBConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, CustomerNametxt.getText());
            pstmt.setDate(2, java.sql.Date.valueOf(CheckInDatetxt.getText()));
            pstmt.setDate(3, java.sql.Date.valueOf(CheckkOutDatetxt.getText()));
            pstmt.setString(4, roomNotxt.getText()); // Updated to use room number

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Payment completed, reservation status updated.");
            } else {
                JOptionPane.showMessageDialog(this, "No matching reservation found or payment is already completed.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating reservation status. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean reservationExists() {
        String sql = "SELECT COUNT(*) FROM Reservations WHERE customer_name = ? AND check_in_date = ? AND check_out_date = ? AND room_number = ? AND payment_status = 'Pending'";

        try (
                Connection con = DBConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, CustomerNametxt.getText());
            pstmt.setDate(2, java.sql.Date.valueOf(CheckInDatetxt.getText()));
            pstmt.setDate(3, java.sql.Date.valueOf(CheckkOutDatetxt.getText()));
            pstmt.setString(4, roomNotxt.getText()); // Updated to use room number

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error checking reservation status.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        cbPaymentMethod = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        totalPricetxt = new app.bolivia.swing.JCTextField();
        CustomerNametxt = new app.bolivia.swing.JCTextField();
        CheckkOutDatetxt = new app.bolivia.swing.JCTextField();
        CheckInDatetxt = new app.bolivia.swing.JCTextField();
        roomNotxt = new app.bolivia.swing.JCTextField();
        roomTypetxt = new app.bolivia.swing.JCTextField();
        reserveNowbtn = new necesario.RSMaterialButtonCircle();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        FinishNcNFRM = new necesario.RSMaterialButtonCircle();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(250, 240, 230));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(250, 240, 230));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbPaymentMethod.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        cbPaymentMethod.setForeground(new java.awt.Color(255, 255, 255));
        cbPaymentMethod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select  your payment method...", "Credit Card", "Debit Card", "Jazz Cash", "PayPal" }));
        jPanel3.add(cbPaymentMethod, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 90, 310, 30));

        jLabel8.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel8.setText("Payment Options: ");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 90, 150, 30));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/makepay.jpg"))); // NOI18N
        jLabel15.setText("jLabel2");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 840, 211));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 220));

        jPanel7.setBackground(new java.awt.Color(139, 69, 19));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 230, 10, 290));

        jLabel16.setFont(new java.awt.Font("Stencil", 0, 26)); // NOI18N
        jLabel16.setText("Reservation Check & Payment");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 250, 430, 40));

        totalPricetxt.setText(" ");
        totalPricetxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        totalPricetxt.setPlaceholder(" Enter Customer Name...");
        totalPricetxt.setSelectionColor(new java.awt.Color(153, 0, 0));
        jPanel1.add(totalPricetxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 440, 320, 40));

        CustomerNametxt.setText(" ");
        CustomerNametxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CustomerNametxt.setPlaceholder(" Customer Name...");
        CustomerNametxt.setSelectionColor(new java.awt.Color(153, 0, 0));
        jPanel1.add(CustomerNametxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, 320, 40));

        CheckkOutDatetxt.setText(" ");
        CheckkOutDatetxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CheckkOutDatetxt.setPlaceholder(" Enter Customer Name...");
        CheckkOutDatetxt.setSelectionColor(new java.awt.Color(153, 0, 0));
        jPanel1.add(CheckkOutDatetxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, 320, 40));

        CheckInDatetxt.setText(" ");
        CheckInDatetxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CheckInDatetxt.setPlaceholder(" Enter Customer Name...");
        CheckInDatetxt.setSelectionColor(new java.awt.Color(153, 0, 0));
        CheckInDatetxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckInDatetxtActionPerformed(evt);
            }
        });
        jPanel1.add(CheckInDatetxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 440, 320, 40));

        roomNotxt.setText(" ");
        roomNotxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        roomNotxt.setPlaceholder(" Enter Customer Name...");
        roomNotxt.setSelectionColor(new java.awt.Color(153, 0, 0));
        roomNotxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomNotxtActionPerformed(evt);
            }
        });
        jPanel1.add(roomNotxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 320, 320, 40));

        roomTypetxt.setText(" ");
        roomTypetxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        roomTypetxt.setPlaceholder(" Enter Customer Name...");
        roomTypetxt.setSelectionColor(new java.awt.Color(153, 0, 0));
        jPanel1.add(roomTypetxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 380, 320, 40));

        reserveNowbtn.setBackground(new java.awt.Color(78, 52, 46));
        reserveNowbtn.setText("RESERVE NOW");
        reserveNowbtn.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        reserveNowbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reserveNowbtnActionPerformed(evt);
            }
        });
        jPanel1.add(reserveNowbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 370, 190, 60));

        jPanel8.setBackground(new java.awt.Color(139, 69, 19));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 1590, 10));

        jPanel9.setBackground(new java.awt.Color(139, 69, 19));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 10, 290));

        jPanel10.setBackground(new java.awt.Color(139, 69, 19));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, 230, 1590, 10));

        FinishNcNFRM.setBackground(new java.awt.Color(78, 52, 46));
        FinishNcNFRM.setText(" Finish & Confirm");
        FinishNcNFRM.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        FinishNcNFRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FinishNcNFRMActionPerformed(evt);
            }
        });
        jPanel1.add(FinishNcNFRM, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 430, 260, 60));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, -1, 720));

        jPanel4.setBackground(new java.awt.Color(51, 0, 0));
        jPanel4.setLayout(null);

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel9.setText(" ");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 60, -1));

        jPanel4.add(jPanel6);
        jPanel6.setBounds(0, 0, 80, 48);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/istockphoto-1450275141-612x612.jpg"))); // NOI18N
        jLabel10.setText(" ");
        jPanel4.add(jLabel10);
        jLabel10.setBounds(800, 0, 533, 260);

        jLabel11.setText("jLabel3");
        jPanel4.add(jLabel11);
        jLabel11.setBounds(17304, 123, 348, 26);

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("X");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel12);
        jLabel12.setBounds(1340, 0, 38, 30);

        jLabel13.setFont(new java.awt.Font("Stencil", 1, 40)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Finalize Your Reservation");
        jPanel4.add(jLabel13);
        jLabel13.setBounds(100, 70, 610, 60);

        jLabel14.setFont(new java.awt.Font("Serif", 0, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("- Proceed with Payment   ");
        jPanel4.add(jLabel14);
        jLabel14.setBounds(530, 120, 230, 24);

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, 220));

        setBounds(0, 0, 1590, 752);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        MakeReservations reserve = new MakeReservations();
        reserve.setVisible(true);
        dispose();


    }//GEN-LAST:event_jLabel9MouseClicked

    private void reserveNowbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reserveNowbtnActionPerformed
        // TODO add your handling code here:
        if (validatePaymentInformation()) {
            insertReservationDataPending();
            JOptionPane.showMessageDialog(this, "Reservation confirmed with Pending status.", "Success", JOptionPane.INFORMATION_MESSAGE);
            // this.setState(JFrame.ICONIFIED);
            DashBoard dash = new DashBoard();
            dash.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_reserveNowbtnActionPerformed

    private void CheckInDatetxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckInDatetxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CheckInDatetxtActionPerformed

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void FinishNcNFRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FinishNcNFRMActionPerformed
        // TODO add your handling code here:
        if (validatePaymentInformation()) {
            if (reservationExists()) {
                updateReservationStatusToCompleted();
            } else {
                insertReservationDataComplete();
            }
            JOptionPane.showMessageDialog(this, "Reservation and Payment completed.", "Success", JOptionPane.INFORMATION_MESSAGE);
            // this.setState(JFrame.ICONIFIED);
            DashBoard dash = new DashBoard();
            dash.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_FinishNcNFRMActionPerformed

    private void roomNotxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomNotxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roomNotxtActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MakePayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MakePayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MakePayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MakePayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MakePayment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private app.bolivia.swing.JCTextField CheckInDatetxt;
    private app.bolivia.swing.JCTextField CheckkOutDatetxt;
    private app.bolivia.swing.JCTextField CustomerNametxt;
    private necesario.RSMaterialButtonCircle FinishNcNFRM;
    private javax.swing.JComboBox<String> cbPaymentMethod;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private necesario.RSMaterialButtonCircle reserveNowbtn;
    private app.bolivia.swing.JCTextField roomNotxt;
    private app.bolivia.swing.JCTextField roomTypetxt;
    private app.bolivia.swing.JCTextField totalPricetxt;
    // End of variables declaration//GEN-END:variables
}
