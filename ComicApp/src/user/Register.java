package user;

import Model.History;
import Model.Library;
import Model.User;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Register extends javax.swing.JFrame implements FileOperations {

    private List<User> userList;
    private static final String USER_FILE = "USER.TXT";

    private List<History> historyList;
    private static final String HISTORY_FILE = "HISTORY.TXT";

    private List<Library> libraryList;
    private static final String LIBRARY_FILE = "LIBRARY.TXT";

    private Login login;

    public Register() {
        initComponents();
        setLocationRelativeTo(null);
        LoadUsersFromFile();
    }

    public Register(Login login) {
        this();
        LoadHistoryFromFile();
        LoadLibraryFromFile();
        this.login = login;
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Register.this.setVisible(false);
                login.setVisible(true);
            }
        });
    }

    public void LoadUsersFromFile() {
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

    public void LoadHistoryFromFile() {
        File file = new File(HISTORY_FILE);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (file.length() > 0) {
            try (BufferedReader reader = new BufferedReader(new FileReader(HISTORY_FILE))) {
                String line;
                historyList = new ArrayList<>();

                while ((line = reader.readLine()) != null) {
                    String[] historyData = line.split(";");

                    String userID = historyData[0];
                    Set<String> comicIDs = new HashSet<>();

                    for (int i = 1; i < historyData.length; i++) {
                        comicIDs.add(historyData[i]);
                    }
                    History history = new History(userID, comicIDs);
                    historyList.add(history);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            historyList = new ArrayList<>();
        }
    }

    public void LoadLibraryFromFile() {
        File file = new File(LIBRARY_FILE);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (file.length() > 0) {
            try (BufferedReader br = new BufferedReader(new FileReader(LIBRARY_FILE))) {
                libraryList = new ArrayList<>();
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    String userID = data[0].trim();
                    Set<String> comicIDList = new HashSet<>();
                    for (int i = 1; i < data.length; i++) {
                        comicIDList.add(data[i].trim());
                    }
                    Library library = new Library(userID, comicIDList);
                    libraryList.add(library);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            libraryList = new ArrayList<>();
        }
    }

    public boolean checkSignUpInformation() {
        if (txtUsername.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Username is required", "Warning", 2);
            return false;
        }
        if (txtUsername.getText().toLowerCase().contains("admin")) {
            JOptionPane.showMessageDialog(rootPane, "Username cannot contain the word 'admin'", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (String.valueOf(txtPassword.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Password is required!", "Warning", 2);
            return false;
        }
        return true;
    }

    private String getIdIncreased() {
        String tmp = "";
        if (userList.size() > 0) {
            Integer tmpIdCounter = Integer.parseInt(userList.get(userList.size() - 1).getUserID());
            tmpIdCounter += 1;
            tmp = tmpIdCounter.toString();
        } else {
            tmp = "1";
        }
        return tmp;
    }

    public boolean isUsernameExist(String username) {
        if (userList.size() == 0) {
            return false;
        } else {
            for (User user : userList) {
                if (username.equals(user.getUsername())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void LoadComicsFromFile() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void LoadChaptersFromFile() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void writeLibraryToFile() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void writeHistoryToFile() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void WriteUsersToFile() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnSignUp = new javax.swing.JButton();
        lbHidden = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtCfPassword = new javax.swing.JPasswordField();
        lbHidden1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setText("Register");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel2.setText("Username");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel3.setText("Password");

        btnSignUp.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSignUp.setText("Sign Up");
        btnSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignUpActionPerformed(evt);
            }
        });

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

        btnBack.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel4.setText("Confirm Password");

        txtCfPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCfPasswordActionPerformed(evt);
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
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbHidden)))
                        .addGap(0, 37, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbHidden1)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbHidden, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbHidden1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBack)
                    .addComponent(btnSignUp))
                .addContainerGap(20, Short.MAX_VALUE))
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

    private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignUpActionPerformed
        if (checkSignUpInformation()) {
            String id = getIdIncreased();
            String username = txtUsername.getText();
            String password = String.valueOf(txtPassword.getPassword());
            String confirmPassword = String.valueOf(txtCfPassword.getPassword());
            if (isUsernameExist(username)) {
                JOptionPane.showMessageDialog(rootPane, "Username already exists", "Warning", 2);
                return;
            }
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(rootPane, "Passwords do not match!", "Warning", 2);
                return;
            }
            login.setAccount(new User(id, username, password));
            historyList.add(new History(id, new HashSet<>()));
            libraryList.add(new Library(id, new HashSet<>()));
            login.setHistoryListData(historyList);
            login.updateLibraryData(libraryList);
            login.setUsernameAndPasswordToDefault();
            login.setVisible(true);
            this.dispose();

        }
    }//GEN-LAST:event_btnSignUpActionPerformed


    private void lbHiddenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHiddenMouseClicked
        if (txtPassword.getEchoChar() == '•') {
            lbHidden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/show.png")));
            txtPassword.setEchoChar((char) 0);
        } else {
            lbHidden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/hide.png")));
            txtPassword.setEchoChar('•');
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


    private void txtCfPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCfPasswordActionPerformed

    }//GEN-LAST:event_txtCfPasswordActionPerformed

    private void lbHidden1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHidden1MouseClicked
        if (txtCfPassword.getEchoChar() == '•') {
            lbHidden1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/show.png")));
            txtCfPassword.setEchoChar((char) 0);
        } else {
            lbHidden1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/hide.png")));
            txtCfPassword.setEchoChar('•');
        }
    }//GEN-LAST:event_lbHidden1MouseClicked

    private void lbHidden1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHidden1MouseEntered
        lbHidden1.setBackground(Color.LIGHT_GRAY);
        lbHidden1.setOpaque(true);
    }//GEN-LAST:event_lbHidden1MouseEntered

    private void lbHidden1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHidden1MouseExited
        lbHidden1.setBackground(null);
        lbHidden1.setOpaque(false);
    }//GEN-LAST:event_lbHidden1MouseExited

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSignUp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbHidden;
    private javax.swing.JLabel lbHidden1;
    private javax.swing.JPasswordField txtCfPassword;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
