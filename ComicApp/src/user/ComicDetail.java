package user;

import Model.User;
import Model.Comic;
import Model.Library;
import Model.Chapter;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class ComicDetail extends javax.swing.JFrame {

    private List<Library> libraryList;
    private static final String LIBRARY_FILE = "LIBRARY.TXT";

    private List<Comic> comicList;
    private static final String COMIC_FILE = "COMIC.TXT";

    private List<Chapter> chapterList;
    private static final String CHAPTER_FILE = "CHAPTER.TXT";

    private String comicId;
    private Home home;
    private User user;

    public ComicDetail() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public ComicDetail(User user, Home home, String comicID) {
        initComponents();
        setLocationRelativeTo(null);
        this.user = user;
        this.home = home;
        this.comicId = comicID;
        setLbComicIcon();
        LoadComicsFromFile();
        LoadChaptersFromFile();
        LoadLibraryFromFile();
        displayComicInfo();
        checkFollowStatus();
        LoadChaptersToTable();
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

    private void LoadChaptersFromFile() {
        File file = new File(CHAPTER_FILE);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (file.length() > 0) {
            try (BufferedReader reader = new BufferedReader(new FileReader(CHAPTER_FILE))) {
                String line;
                chapterList = new ArrayList<>();
                while ((line = reader.readLine()) != null) {
                    String[] chapterData = line.split(";");
                    if (chapterData.length >= 3) {
                        try {
                            String comicID = chapterData[0].trim();
                            int chapterNumber = Integer.parseInt(chapterData[1].trim());
                            String chapterTitle = chapterData[2].trim();

                            Chapter chapter = new Chapter(comicID, chapterNumber, chapterTitle);
                            chapterList.add(chapter);
                        } catch (NumberFormatException e) {
                            System.out.println("Skipping invalid chapter line: " + line);
                        }
                    }
                }
                System.out.println("Loaded " + chapterList.size() + " chapters.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            chapterList = new ArrayList<>();
            System.out.println("No data in chapter file.");
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
    
    public void setLbComicIcon() {
        String fileIconPath = "ComicImage/" + comicId + "/icon.png";
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
                return comic;
            }
        }
        return null;
    }

    private void displayComicInfo() {
        Comic selectedComic = findComicByID(comicId);
        lbName.setText("Title: " + selectedComic.getComicName());
        lbAuthor.setText("Author: " + selectedComic.getComicAuthor());
        lbCategory.setText("Category: " + selectedComic.getComicCategory());
        lbStatus.setText("Status: " + selectedComic.getComicStatus());
    }

    private List<Chapter> findChaptersByComicID(String comicID, List<Chapter> chapterList) {
        List<Chapter> selectedChapters = new ArrayList<>();
        for (Chapter chapter : chapterList) {
            if (chapter.getComicID().equals(comicID)) {
                selectedChapters.add(chapter);
            }
        }
        return selectedChapters;
    }

    private void checkFollowStatus() {
        boolean isFollowed = false;

        for (Library library : libraryList) {
            if (library.getUserID().equals(user.getUserID())) {
                if (library.getFollowedComicIDs().contains(comicId)) {
                    isFollowed = true;
                }
                break;
            }
        }
        btnFollow.setText(isFollowed ? "Unfollow" : "Follow");
        btnFollow.setBackground(isFollowed ? Color.RED : Color.GREEN);
    }

    public void updateLibraryData(boolean isFollow) {
    boolean isUserFound = false;
    for (Library library : libraryList) {
        if (library.getUserID().equals(user.getUserID())) {
            isUserFound = true;
            Set<String> comicIDs = library.getFollowedComicIDs();

            if (isFollow) {
                comicIDs.add(comicId);
            } else {
                comicIDs.remove(comicId);
            }

            library.setFollowedComicIDs(comicIDs);
            break;
        }
    }

    if (!isUserFound) {
        Set<String> comicIDs = new HashSet<>();
        if (isFollow) {
            comicIDs.add(comicId);
        }
        Library newLibrary = new Library(user.getUserID(), comicIDs);
        libraryList.add(newLibrary);
    }

    writeLibraryToFile();
}

    
    private void LoadChaptersToTable() {
        DefaultTableModel model = (DefaultTableModel) ChapterTable.getModel();
        model.setRowCount(0);

        List<Chapter> selectedChapters = findChaptersByComicID(comicId, chapterList);

        for (int i = 0; i < selectedChapters.size(); i++) {
            Chapter chapter = selectedChapters.get(i);
            model.addRow(new Object[]{chapter.getChapterNumber(), chapter.getTitle()});
        }
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
        btnFollow = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ChapterTable = new javax.swing.JTable();

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

        btnFollow.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnFollow.setText("Follow");
        btnFollow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFollowActionPerformed(evt);
            }
        });

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
                            .addComponent(lbStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnFollow, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbCategory))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(lbNChapters)))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbAuthor)
                    .addComponent(btnFollow, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        ChapterTable.setModel(new javax.swing.table.DefaultTableModel(
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
        ChapterTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(ChapterTable);
        if (ChapterTable.getColumnModel().getColumnCount() > 0) {
            ChapterTable.getColumnModel().getColumn(0).setResizable(false);
            ChapterTable.getColumnModel().getColumn(1).setResizable(false);
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
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

    private void btnFollowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFollowActionPerformed

        if (btnFollow.getText().equals("Follow")) {
            btnFollow.setText("Unfollow");
            btnFollow.setBackground(Color.RED);
            updateLibraryData(true);
        } else if (btnFollow.getText().equals("Unfollow")) {
            btnFollow.setText("Follow");
            btnFollow.setBackground(Color.GREEN);
            updateLibraryData(false);
        }
    }//GEN-LAST:event_btnFollowActionPerformed


    public static void main(String args[]) {
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ComicDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ComicDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ComicDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ComicDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ComicDetail().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ChapterTable;
    private javax.swing.JButton btnFollow;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbAuthor;
    private javax.swing.JLabel lbCategory;
    private javax.swing.JLabel lbComicIcon;
    private javax.swing.JLabel lbNChapters;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbStatus;
    // End of variables declaration//GEN-END:variables
}
