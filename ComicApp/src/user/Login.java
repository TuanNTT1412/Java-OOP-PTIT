package user;

import Model.Comic;
import Model.History;
import Model.Library;
import Model.User;
import java.awt.Color;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    private List<User> userList;
    private static final String USER_FILE = "USER.TXT";

    private List<Comic> comicList;
    private static final String COMIC_FILE = "COMIC.TXT";

    private List<Library> libraryList;
    private static final String LIBRARY_FILE = "LIBRARY.TXT";

    private List<History> historyList;
    private static final String HISTORY_FILE = "HISTORY.TXT";

    public Login() {
        initComponents();
        setLocationRelativeTo(null);
        init();
    }

    private void init() {
        LoadUsersFromFile();
        LoadComicsFromFile();
        LoadHistoryFromFile();
        LoadLibraryFromFile();
    }

    public void setUsernameAndPasswordToDefault() {
        txtUsername.setText("");
        txtPassword.setText("");
    }

    /* */
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

    private void WriteUsersToFile() {
        try (FileWriter fw = new FileWriter(USER_FILE, false); BufferedWriter bw = new BufferedWriter(fw)) {

            for (User user : userList) {
                bw.write(user.toString());
                bw.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setAccount(User user) {
        if (userList == null) {
            userList = new ArrayList<>();
        }
        userList.add(user);
        WriteUsersToFile();
    }

    public void UpdateUserData(List<User> ExternalUserList) {
        userList = ExternalUserList;
        WriteUsersToFile();
    }


    /* */
    private void LoadComicsFromFile() {
        File file = new File(COMIC_FILE);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (file.length() > 0) {
            try (BufferedReader reader = new BufferedReader(new FileReader(COMIC_FILE))) {
                String line;
                comicList = new ArrayList<>();
                while ((line = reader.readLine()) != null) {
                    String[] comicData = line.split(";");
                    if (comicData.length >= 5) {
                        String comicID = comicData[0];
                        String comicName = comicData[1];
                        String comicStatus = comicData[2];
                        String comicCategory = comicData[3];
                        String comicAuthor = comicData[4];

                        Comic comic = new Comic(comicID, comicName, comicStatus, comicCategory, comicAuthor);
                        comicList.add(comic);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            comicList = new ArrayList<>();
        }
    }

    /*
    private void writeDataToFileComic() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COMIC_FILE))) {
            for (Comic comic : comicList) {
                writer.write(comic.getComicID() + ";" + comic.getComicName() + ";" + comic.getComicStatus() + ";" + comic.getComicCategory() + ";" + comic.getComicAuthor() + ";" + comic.getComicImageUrl());
                writer.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setComicListData(List<Comic> ExternalComicList) {
        comicList = ExternalComicList;
        writeDataToFileComic();
    }
     */
 /* */
    private void LoadHistoryFromFile() {
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

    private void writeHistoryToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HISTORY_FILE, false))) {
            for (History history : historyList) {
                StringBuilder line = new StringBuilder();
                line.append(history.getUserID()).append(";");

                for (String comicID : history.getComicIDs()) {
                    line.append(comicID).append(";");
                }

                if (line.charAt(line.length() - 1) == ';') {
                    line.setLength(line.length() - 1);
                }

                writer.write(line.toString());
                writer.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setHistoryListData(List<History> ExternalHistoryList) {
        historyList = ExternalHistoryList;

        writeHistoryToFile();
    }

    /* */
    private void LoadLibraryFromFile() {
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

    private void writeLibraryToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LIBRARY_FILE, false))) {
            for (Library library : libraryList) {
                StringBuilder sb = new StringBuilder();
                sb.append(library.getUserID()).append(",");
                for (String comicID : library.getFollowedComicIDs()) {
                    sb.append(comicID).append(",");
                }
                bw.write(sb.toString().replaceAll(",$", ""));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateLibraryData(List<Library> ExternalLibraryList) {
        libraryList = ExternalLibraryList;
        writeLibraryToFile();
    }

    /* */
    private boolean checkUsernameAndPassword() {
        if (txtUsername.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Username is required!", "Warning", 2);
            return false;
        }
        if (String.valueOf(txtPassword.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Password is required!", "Warning", 2);
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        lbRegister = new javax.swing.JLabel();
        lbResetPassword = new javax.swing.JLabel();
        lbHidden = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setText("Login");

        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });

        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel2.setText("Username");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Password");

        btnLogin.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lbRegister.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lbRegister.setText("Register");
        lbRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbRegisterMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbRegisterMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbRegisterMouseExited(evt);
            }
        });

        lbResetPassword.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lbResetPassword.setText("Reset Password");
        lbResetPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbResetPasswordMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbResetPasswordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbResetPasswordMouseExited(evt);
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(lbRegister)
                        .addGap(33, 33, 33)
                        .addComponent(lbResetPassword))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbHidden)))))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbHidden))
                .addGap(27, 27, 27)
                .addComponent(btnLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRegister)
                    .addComponent(lbResetPassword))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 360));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed

    }//GEN-LAST:event_txtPasswordActionPerformed

    private void lbRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbRegisterMouseClicked
        Register register = new Register(this);
        register.setVisible(true);
        lbRegister.setBackground(null);
        lbRegister.setOpaque(false);
        this.dispose();
    }//GEN-LAST:event_lbRegisterMouseClicked

    private void lbResetPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbResetPasswordMouseClicked
        ResetPassword forgotPassword = new ResetPassword(this);
        forgotPassword.setVisible(true);
        lbResetPassword.setBackground(null);
        lbResetPassword.setOpaque(false);
        this.dispose();
    }//GEN-LAST:event_lbResetPasswordMouseClicked

    private void lbHiddenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHiddenMouseClicked
        if (txtPassword.getEchoChar() == '*') {
            lbHidden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/show.png")));
            txtPassword.setEchoChar((char) 0);
        } else {
            lbHidden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/hide.png")));
            txtPassword.setEchoChar('*');
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

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        if (checkUsernameAndPassword()) {
            String username = txtUsername.getText();
            String password = String.valueOf(txtPassword.getPassword());
            boolean check = false;
            for (User user : userList) {
                if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    User newUser = user;
                    Home userDashboard = new Home(newUser, this);
                    userDashboard.setVisible(true);
                    userDashboard.pack();

                    check = true;
                    this.dispose();
                    break;
                }
            }
            if (!check) {
                JOptionPane.showMessageDialog(rootPane, "Incorrect username or password", "Cannot Login", 0);
            }
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void lbRegisterMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbRegisterMouseEntered
        lbRegister.setBackground(Color.LIGHT_GRAY);
        lbRegister.setOpaque(true);
    }//GEN-LAST:event_lbRegisterMouseEntered

    private void lbRegisterMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbRegisterMouseExited
        lbRegister.setBackground(null);
        lbRegister.setOpaque(false);
    }//GEN-LAST:event_lbRegisterMouseExited

    private void lbResetPasswordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbResetPasswordMouseEntered
        lbResetPassword.setBackground(Color.LIGHT_GRAY);
        lbResetPassword.setOpaque(true);
    }//GEN-LAST:event_lbResetPasswordMouseEntered

    private void lbResetPasswordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbResetPasswordMouseExited
        lbResetPassword.setBackground(null);
        lbResetPassword.setOpaque(false);
    }//GEN-LAST:event_lbResetPasswordMouseExited

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed

    }//GEN-LAST:event_txtUsernameActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbHidden;
    private javax.swing.JLabel lbRegister;
    private javax.swing.JLabel lbResetPassword;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
