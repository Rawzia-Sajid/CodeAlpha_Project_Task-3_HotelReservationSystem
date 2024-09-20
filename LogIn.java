/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Hotel_Reservation_System;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Ukasha Gamer
 */
public class LogIn extends javax.swing.JFrame {

    /**
     * Creates new form LogIn
     */
    public LogIn() {
        initComponents();
    }

    // Method for validating login inputs
    public Boolean validateLogin() {
        String email = emailtxt.getText();
        String pwd = new String(pswrdtxt.getPassword());  // use getPassword() for security

        // Check if email is empty
        if (email.equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter email");
            return false;
        }

        // Check if password is empty
        if (pwd.equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter password");
            return false;
        }

        return true;
    }

    // Method to handle forgotten password
    public void forgotPassword() {
        String email = JOptionPane.showInputDialog(this, "Please enter your registered email:");

        // Validate email input
        if (email == null || email.equals("")) {
            JOptionPane.showMessageDialog(this, "Email cannot be empty.");
            return;
        }
        // Trim the input email string to remove leading or trailing spaces
        email = email.trim();

        try {
            // Establish connection with the MySQL database
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM users WHERE TRIM(email) = TRIM(?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, email);

            // Execute query and get result
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                // If email is found, prompt for new password
                String newPassword = JOptionPane.showInputDialog(this, "Enter your new password:");

                if (newPassword == null || newPassword.equals("")) {
                    JOptionPane.showMessageDialog(this, "Password cannot be empty.");
                    return;
                }

                // Update password in the database
                String updateQuery = "UPDATE users SET password = ? WHERE email = ?";
                PreparedStatement updatePst = con.prepareStatement(updateQuery);
                updatePst.setString(1, newPassword);
                updatePst.setString(2, email);

                int updatedRows = updatePst.executeUpdate();
                if (updatedRows > 0) {
                    JOptionPane.showMessageDialog(this, "Password reset successful.");
                } else {
                    JOptionPane.showMessageDialog(this, "Error updating password. Please try again.");
                }

                // Close resources
                updatePst.close();
            } else {
                // If email is not found, show an error message
                JOptionPane.showMessageDialog(this, "No account found with this email.");
            }

            // Close resources
            rs.close();
            pst.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void login() {
        if (!validateLogin()) {
            return;
        }

        String email = emailtxt.getText();
        String pwd = new String(pswrdtxt.getPassword());

        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, email);
            pst.setString(2, pwd);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String userEmail = rs.getString("email");
                String username = firstName + " " + lastName;

                showCustomDialog(username); // Call the method to display custom-styled dialog
                // Store user email in UserEmailSession and update login status
                // UserEmailSession.getInstance().setUserEmail(email);
                // UserEmailSession.getInstance().setLoginStatus(true);
                // Set the user's details in the UserEmailSession class
                UserEmailSession userEmailSession = UserEmailSession.getInstance();
                userEmailSession.setFirstName(firstName);
                userEmailSession.setLastName(lastName);
                userEmailSession.setUserEmail(userEmail);
                userEmailSession.setLoginStatus(true);

                this.dispose();
                DashBoard dash = new DashBoard();
                dash.setVisible(true);
                dispose();

            } else {
                JOptionPane.showMessageDialog(this, "Authentication error. Please verify your login details and try again.");

            }

            rs.close();
            pst.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static void showCustomDialog(String username) {
        // Create a JFrame to act as the parent window (optional, can be null)
        JFrame parentFrame = new JFrame();

        // Create a JPanel to hold the custom message
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);  // Set background to black

        // Create a JLabel with bold white text
        JLabel messageLabel = new JLabel("Login complete. Welcome back, " + username + "!");
        messageLabel.setForeground(Color.WHITE);  // Set text color to white
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));  // Set text to bold

        // Add the label to the panel
        panel.add(messageLabel);

        // Create the custom dialog
        JOptionPane.showMessageDialog(parentFrame, panel, "Login Successful", JOptionPane.PLAIN_MESSAGE);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        pswrdtxt = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        showPswrdcheckbox = new javax.swing.JCheckBox();
        jLabel17 = new javax.swing.JLabel();
        resetPswrdbtn = new javax.swing.JButton();
        logInbtn = new necesario.RSMaterialButtonCircle();
        emailtxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(250, 240, 230));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(51, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Stencil", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Welcome Back to Opall Grove Hotel");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 810, -1));

        jLabel5.setFont(new java.awt.Font("Serif", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("- Log In to Manage Your Reservations  ");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 60, -1, -1));

        jPanel1.add(jPanel2);
        jPanel2.setBounds(120, 20, 900, 100);

        jLabel15.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel15.setText(" Email");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(120, 170, 70, 23);

        jLabel16.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel16.setText(" Forgot Password? ");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(110, 400, 160, 22);

        pswrdtxt.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        pswrdtxt.setSelectionColor(new java.awt.Color(153, 0, 0));
        jPanel1.add(pswrdtxt);
        pswrdtxt.setBounds(120, 290, 340, 30);

        jButton1.setBackground(new java.awt.Color(102, 102, 102));
        jButton1.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jButton1.setText("Register Here");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(330, 600, 140, 40);

        jLabel8.setBackground(new java.awt.Color(102, 102, 102));
        jLabel8.setFont(new java.awt.Font("Stencil", 0, 18)); // NOI18N
        jLabel8.setText("Don't Have an Account?");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(90, 610, 224, 20);

        showPswrdcheckbox.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        showPswrdcheckbox.setText(" Show Password");
        showPswrdcheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPswrdcheckboxActionPerformed(evt);
            }
        });
        jPanel1.add(showPswrdcheckbox);
        showPswrdcheckbox.setBounds(120, 340, 150, 20);

        jLabel17.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel17.setText("Password");
        jPanel1.add(jLabel17);
        jLabel17.setBounds(120, 260, 110, 23);

        resetPswrdbtn.setBackground(new java.awt.Color(102, 102, 102));
        resetPswrdbtn.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        resetPswrdbtn.setText("Click here to reset your password  ");
        resetPswrdbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetPswrdbtnActionPerformed(evt);
            }
        });
        jPanel1.add(resetPswrdbtn);
        resetPswrdbtn.setBounds(270, 400, 250, 26);

        logInbtn.setBackground(new java.awt.Color(78, 52, 46));
        logInbtn.setText(" LOG IN");
        logInbtn.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        logInbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logInbtnActionPerformed(evt);
            }
        });
        jPanel1.add(logInbtn);
        logInbtn.setBounds(170, 470, 180, 50);

        emailtxt.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        emailtxt.setText(" ");
        emailtxt.setActionCommand("<Not Set>");
        emailtxt.setSelectionColor(new java.awt.Color(153, 0, 0));
        jPanel1.add(emailtxt);
        emailtxt.setBounds(120, 200, 340, 30);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/in.jpg"))); // NOI18N
        jLabel2.setText(" ");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(80, 120, 980, 610);

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("X");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel6);
        jLabel6.setBounds(1090, 0, 40, 30);

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel7.setText(" ");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(0, 4, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel6);
        jPanel6.setBounds(0, 0, 60, 50);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1135, 777));

        setSize(new java.awt.Dimension(1135, 777));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void showPswrdcheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPswrdcheckboxActionPerformed
        // TODO add your handling code here:

        if (showPswrdcheckbox.isSelected()) {
            // Show password as plain text
            pswrdtxt.setEchoChar((char) 0);
        } else {
            // Hide password by showing it as dots (default behavior)
            pswrdtxt.setEchoChar('*');
        }

    }//GEN-LAST:event_showPswrdcheckboxActionPerformed

    private void resetPswrdbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetPswrdbtnActionPerformed
        // TODO add your handling code here:
        forgotPassword();
    }//GEN-LAST:event_resetPswrdbtnActionPerformed

    private void logInbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logInbtnActionPerformed
        // TODO add your handling code here:
        login();
    }//GEN-LAST:event_logInbtnActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        HotelReservationSystem homepg = new HotelReservationSystem();
        homepg.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        RegistrationForm reg = new RegistrationForm();
        reg.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogIn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField emailtxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private necesario.RSMaterialButtonCircle logInbtn;
    private javax.swing.JPasswordField pswrdtxt;
    private javax.swing.JButton resetPswrdbtn;
    private javax.swing.JCheckBox showPswrdcheckbox;
    // End of variables declaration//GEN-END:variables
}
