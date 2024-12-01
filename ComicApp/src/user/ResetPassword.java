package user;

import Model.User;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;

public class ResetPassword extends javax.swing.JFrame {

    private Login login;

    private List<User> userList;
    private static final String USER_FILE = "USER.TXT";

    public ResetPassword() {
        initComponents();
        setLocationRelativeTo(null);
        LoadUsersFromFile();
    }

    public ResetPassword(Login login) {
        this();
        this.login = login;
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ResetPassword.this.setVisible(false);
                login.setVisible(true);
            }
        });
    }

    private void LoadUsersFromFile() {
        File file = new File(USER_FILE);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (file.length() > 0) {
            try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
                String line;
                userList = new ArrayList<>();
                while ((line = br.readLine()) != null) {
                    String[] userFields = line.split(",");
                    if (userFields.length == 3) {
                        User user = new User(userFields[0], userFields[1], userFields[2]);
                        userList.add(user);
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            userList = new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCfPassword = new javax.swing.JPasswordField();
        txtNewPassword = new javax.swing.JPasswordField();
        lbHidden = new javax.swing.JLabel();
        lbHidden1 = new javax.swing.JLabel();

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel2.setText("Username");

        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel3.setText("New Password");

        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setText("Reset Password");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel4.setText("Confirm Password");

        lbHidden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/hide.png"))); // NOI18N
        lbHidden.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbHiddenMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbHiddenMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbHiddenMouseExited(evt);
            }
        });

        lbHidden1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/hide.png"))); // NOI18N
        lbHidden1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbHidden1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbHidden1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbHidden1MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(txtCfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(btnReset)
                                        .addGap(62, 62, 62)
                                        .addComponent(btnBack)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbHidden1))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txtNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbHidden))
                            .addComponent(jLabel3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2))))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbHidden))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbHidden1)
                    .addComponent(txtCfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReset)
                    .addComponent(btnBack))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    public boolean checkResetInformation() {
        if (txtUsername.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Username is required", "Warning", 2);
            return false;
        }
        if (txtUsername.getText().toLowerCase().contains("admin")) {
            JOptionPane.showMessageDialog(rootPane, "Can't not modify admin account", "Warning", 2);
            return false;
        }
        if (String.valueOf(txtNewPassword.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Password is required!", "Warning", 2);
            return false;
        }
        return true;
    }

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        if (checkResetInformation()) {
            boolean usernameExist = false;
            for (int i = 0; i < userList.size(); i++) {
                User user = userList.get(i);
                if (user.getUsername().equals(txtUsername.getText())) {
                    usernameExist = true;
                    break;
                }
            }
            if (!usernameExist) {
                JOptionPane.showMessageDialog(rootPane, "Username does not exist. Please try again.", "Username Not Found", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!txtNewPassword.getText().equals(txtCfPassword.getText())) {
                JOptionPane.showMessageDialog(rootPane, "Passwords do not match. Please check again.", "Password Mismatch", JOptionPane.ERROR_MESSAGE);
                return;
            }
            for (int i = 0; i < userList.size(); i++) {
                User user = userList.get(i);
                if (user.getUsername().equals(txtUsername.getText())) {
                    String tmpNewPassword = txtNewPassword.getText();
                    user.setPassword(tmpNewPassword);
                    JOptionPane.showMessageDialog(rootPane, "Your password has been successfully reset.", "Password Reset Success", JOptionPane.INFORMATION_MESSAGE);
                    login.UpdateUserData(userList);
                    this.dispose();
                    login.setVisible(true);
                    break;
                }
            }
        }
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed

    }//GEN-LAST:event_txtUsernameActionPerformed

    private void lbHiddenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHiddenMouseClicked
        if (txtNewPassword.getEchoChar() == '*') {
            lbHidden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/show.png")));
            txtNewPassword.setEchoChar((char) 0);
        } else {
            lbHidden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/hide.png")));
            txtNewPassword.setEchoChar('*');
        }
    }//GEN-LAST:event_lbHiddenMouseClicked

    private void lbHiddenMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHiddenMouseEntered
        lbHidden.setBackground(Color.LIGHT_GRAY);
        lbHidden.setOpaque(true);
    }//GEN-LAST:event_lbHiddenMouseEntered

    private void lbHiddenMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHiddenMouseExited
        lbHidden.setBackground(null);
        lbHidden.setOpaque(false);
    }//GEN-LAST:event_lbHiddenMouseExited

    private void lbHidden1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHidden1MouseClicked
        if (txtCfPassword.getEchoChar() == '*') {
            lbHidden1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/show.png")));
            txtCfPassword.setEchoChar((char) 0);
        } else {
            lbHidden1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/hide.png")));
            txtCfPassword.setEchoChar('*');
        }
    }//GEN-LAST:event_lbHidden1MouseClicked

    private void lbHidden1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHidden1MouseEntered
        lbHidden1.setBackground(Color.LIGHT_GRAY);
        lbHidden1.setOpaque(true); //    }//GEN-LAST:event_lbHidden1MouseEntered
    }
    private void lbHidden1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHidden1MouseExited
        lbHidden1.setBackground(null);
        lbHidden1.setOpaque(false);
    }//GEN-LAST:event_lbHidden1MouseExited

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ResetPassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnReset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbHidden;
    private javax.swing.JLabel lbHidden1;
    private javax.swing.JPasswordField txtCfPassword;
    private javax.swing.JPasswordField txtNewPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
