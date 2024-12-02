package user;

import Model.Comic;
import Model.History;
import Model.Library;
import Model.User;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class Home extends javax.swing.JFrame {

    private List<History> historyList;
    private static final String HISTORY_FILE = "HISTORY.TXT";

    private List<Library> libraryList;
    private static final String LIBRARY_FILE = "LIBRARY.TXT";

    private List<Comic> comicList;
    private static final String FILE_NAME_COMIC = "COMIC.TXT";

    private Login login;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Home() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public Home(User user, Login login) {
        this();
        this.user = user;
        this.login = login;
        setLbMyAccountText();
        LoadComicsFromFile();
        LoadHistoryFromFile();
        LoadLibraryFromFile();
        LoadComicsToTable();
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Home.this.setVisible(false);
                login.setVisible(true);
            }
        });
    }
    private void LoadComicsFromFile() {
        File file = new File(FILE_NAME_COMIC);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (file.length() > 0) {
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME_COMIC))) {
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
                System.out.println("Dữ liệu comicList: " + comicList.size() + " truyện");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Working Directory = " + System.getProperty("user.dir"));

            comicList = new ArrayList<>();
            System.out.println("Không có dữ liệu trong file.");
        }
    }

    private void LoadComicsToTable() {
        DefaultTableModel model = (DefaultTableModel) ComicTable.getModel();
        model.setRowCount(0);

        for (int i = 0; i < comicList.size(); i++) {
            Comic comic = comicList.get(i);
            model.addRow(new Object[]{i + 1, comic.getComicName(), comic.getComicAuthor(), comic.getComicCategory(), comic.getComicStatus()});
        }
    }

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

    public void setLbMyAccountText() {
        lbMyAccount.setText(user.getUsername());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lbHome = new javax.swing.JLabel();
        lbLibrary = new javax.swing.JLabel();
        lbHistory = new javax.swing.JLabel();
        lbMyAccount = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ComicTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/comic64.png"))); // NOI18N
        jLabel1.setText("COMIC READING");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));

        jPanel3.setBackground(new java.awt.Color(211, 211, 211));

        lbHome.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home.png"))); // NOI18N
        lbHome.setText("Home");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbHome, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbHome)
                .addContainerGap())
        );

        lbLibrary.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lbLibrary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/library.png"))); // NOI18N
        lbLibrary.setText("Library");
        lbLibrary.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbLibraryMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbLibraryMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbLibraryMouseExited(evt);
            }
        });

        lbHistory.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lbHistory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/history.png"))); // NOI18N
        lbHistory.setText("History");
        lbHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbHistoryMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbHistoryMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbHistoryMouseExited(evt);
            }
        });

        lbMyAccount.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lbMyAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/My Account.png"))); // NOI18N
        lbMyAccount.setText("My Account");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(lbLibrary)
                .addGap(30, 30, 30)
                .addComponent(lbHistory)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 346, Short.MAX_VALUE)
                .addComponent(lbMyAccount)
                .addGap(65, 65, 65))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbLibrary, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lbMyAccount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        ComicTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No. ", "Name", "Author", "Category", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ComicTable.getTableHeader().setReorderingAllowed(false);
        ComicTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ComicTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ComicTable);
        if (ComicTable.getColumnModel().getColumnCount() > 0) {
            ComicTable.getColumnModel().getColumn(0).setResizable(false);
            ComicTable.getColumnModel().getColumn(1).setResizable(false);
            ComicTable.getColumnModel().getColumn(2).setResizable(false);
            ComicTable.getColumnModel().getColumn(3).setResizable(false);
            ComicTable.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void lbLibraryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbLibraryMouseClicked
        // TODO add your handling code here:
        //Library library = new Library();
        //purchase.setVisible(true);
    }//GEN-LAST:event_lbLibraryMouseClicked

    private void lbHistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHistoryMouseClicked
        // TODO add your handling code here:
        //PurchaseDetai purchaseDetails = new PurchaseDetails(login, user, this);
       // purchaseDetails.setVisible(true);
    }//GEN-LAST:event_lbHistoryMouseClicked

    private void lbLibraryMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbLibraryMouseEntered
        // TODO add your handling code here:
        lbLibrary.setBackground(Color.LIGHT_GRAY); // Màu nền sáng
        lbLibrary.setOpaque(true); // Đảm bảo JLabel hiển thị màu nền
    }//GEN-LAST:event_lbLibraryMouseEntered

    private void lbLibraryMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbLibraryMouseExited
        // TODO add your handling code here:
        lbLibrary.setBackground(null); // Khôi phục màu nền mặc định
        lbLibrary.setOpaque(false); // Không hiển thị màu nền
    }//GEN-LAST:event_lbLibraryMouseExited

    private void lbHistoryMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHistoryMouseEntered
        // TODO add your handling code here:
        lbHistory.setBackground(Color.LIGHT_GRAY); // Màu nền sáng
        lbHistory.setOpaque(true); // Đảm bảo JLabel hiển thị màu nền
    }//GEN-LAST:event_lbHistoryMouseEntered

    private void lbHistoryMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHistoryMouseExited
        // TODO add your handling code here:
        lbHistory.setBackground(null); // Khôi phục màu nền mặc định
        lbHistory.setOpaque(false); // Không hiển thị màu nền
    }//GEN-LAST:event_lbHistoryMouseExited

    private void ComicTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ComicTableMouseClicked
        // TODO add your handling code here:
        int row = ComicTable.getSelectedRow();
                if (row != -1) {
                    // Lấy ID của truyện từ hàng được chọn (sử dụng row index để lấy Comic)
                    Comic comic = comicList.get(row); // Lấy đối tượng Comic từ danh sách
                    // Mở cửa sổ JFComic với ID truyện đã chọn
                    ComicDetail comicDetail = new ComicDetail(this, comic.getComicID());
                    comicDetail.setVisible(true);
                }
    }//GEN-LAST:event_ComicTableMouseClicked

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ComicTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbHistory;
    private javax.swing.JLabel lbHome;
    private javax.swing.JLabel lbLibrary;
    private javax.swing.JLabel lbMyAccount;
    // End of variables declaration//GEN-END:variables
}
