package user;

import Model.Comic;
import Model.Library;
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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ComicDetail extends javax.swing.JFrame {

    private List<Library> libraryList;
    private static final String LIBRARY_FILE = "LIBRARY.TXT";

    private List<Comic> comicList;
    private static final String FILE_NAME_COMIC = "COMIC.TXT";

    private String comicId;
    private Home home;

    public ComicDetail() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public ComicDetail(Home home, String comicID) {
        initComponents();
        setLocationRelativeTo(null);
        this.home = home;
        this.comicId = comicID;
        setLbComicIcon();
        LoadComicsFromFile();
        LoadLibraryFromFile();
        displayComicInfo();
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ComicDetail.this.setVisible(false);
            }
        });
    }

    /* */
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

    public void setLbComicIcon() {
        String fileIconPath = "C:/Users/admin/Downloads/Wassup/ComicApp/ComicImage/" + comicId + "/icon.png"; // Đường dẫn tuyệt đối
        java.io.File imgFile = new java.io.File(fileIconPath);

        if (imgFile.exists()) {
            lbComicIcon.setIcon(new javax.swing.ImageIcon(imgFile.getAbsolutePath()));
        } else {
            System.out.println("Image not found at: " + fileIconPath);
        }
    }

    private Comic findComicByID(String comicID) {
        for (Comic comic : comicList) {
            if (comic.getComicID().equals(comicID)) {
                return comic; // Trả về truyện tranh tìm được
            }
        }
        return null; // Trường hợp không tìm thấy truyện
    }

    private void displayComicInfo() {
        Comic selectedComic = findComicByID(comicId);
        lbName.setText("Title: " + selectedComic.getComicName());
        lbAuthor.setText("Author: " + selectedComic.getComicAuthor());
        lbCategory.setText("Category: " + selectedComic.getComicCategory());
        lbStatus.setText("Status: " + selectedComic.getComicStatus());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbComicIcon = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        lbCategory = new javax.swing.JLabel();
        lbAuthor = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();
        lbNChapters = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbComicIcon.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        lbName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbName.setText("Name:");

        lbCategory.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbCategory.setText("Category:");

        lbAuthor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbAuthor.setText("Author:");

        lbStatus.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbStatus.setText("Status:");

        lbNChapters.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbNChapters.setText("Number of Chapters:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbComicIcon))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbAuthor, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                            .addComponent(lbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbNChapters, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                            .addComponent(lbStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbName)
                        .addComponent(lbStatus))
                    .addComponent(lbComicIcon))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCategory)
                    .addComponent(lbNChapters))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(lbAuthor)
                .addGap(36, 36, 36))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Chapter", "Title"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1033, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ComicDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ComicDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ComicDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ComicDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new ComicDetail().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbAuthor;
    private javax.swing.JLabel lbCategory;
    private javax.swing.JLabel lbComicIcon;
    private javax.swing.JLabel lbNChapters;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbStatus;
    // End of variables declaration//GEN-END:variables
}
