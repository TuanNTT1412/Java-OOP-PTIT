package user;

import Model.Comic;
import Model.History;
import Model.Library;
import Model.User;
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
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ChapterDetail extends javax.swing.JFrame implements FileOperations {

    private List<History> historyList;
    private static final String HISTORY_FILE = "HISTORY.TXT";

    private List<Comic> comicList;
    private static final String COMIC_FILE = "COMIC.TXT";

    private ComicDetail comicDetail;
    private Home home;
    private LibraryDetail libraryDetail;
    private HistoryDetail historyDetail;
    private String comicId;
    private String chapterNumber;

    public ChapterDetail() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initChapterDetail(ComicDetail comicDetail, Home home, LibraryDetail libraryDetail, HistoryDetail historyDetail, String comicId, String chapterNumber) {
        this.comicDetail = comicDetail;
        this.home = home;
        this.libraryDetail = libraryDetail;
        this.historyDetail = historyDetail;
        this.comicId = comicId;
        this.chapterNumber = chapterNumber;

        LoadComicsFromFile();
        LoadHistoryFromFile();
        displayChapterImage();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ChapterDetail.this.setVisible(false);
                if (comicDetail != null) {
                    comicDetail.setVisible(true);
                }
            }
        });
    }

    public ChapterDetail(ComicDetail comicDetail, Home home, String comicId, String chapterNumber) {
        this();
        initChapterDetail(comicDetail, home, null, null, comicId, chapterNumber);
    }

    public ChapterDetail(ComicDetail comicDetail, Home home, LibraryDetail libraryDetail, String comicId, String chapterNumber) {
        this();
        initChapterDetail(comicDetail, home, libraryDetail, null, comicId, chapterNumber);
    }

    public ChapterDetail(ComicDetail comicDetail, Home home, HistoryDetail historyDetail, String comicId, String chapterNumber) {
        this();
        initChapterDetail(comicDetail, home, null, historyDetail, comicId, chapterNumber);
    }

    public void LoadComicsFromFile() {
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

    private void displayChapterImage() {
        String imagePath = "ComicImage/" + comicId + "/" + chapterNumber + ".png";

        File imgFile = new File(imagePath);
        if (imgFile.exists()) {
            ImageIcon imageIcon = new ImageIcon(imgFile.getAbsolutePath());
            chapterIMG.setIcon(imageIcon);

            int imageHeight = imageIcon.getIconHeight();

            scrollChapter.setMaximum(imageHeight);

            scrollChapter.setBlockIncrement(50);
            scrollChapter.setUnitIncrement(10);

            scrollChapter.addAdjustmentListener(e -> {
                int y = -scrollChapter.getValue();
                chapterIMG.setLocation(0, y);
            });

        } else {
            chapterIMG.setText("Image not found for Chapter " + chapterNumber);
        }
    }

    @Override
    public void LoadUsersFromFile() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void LoadLibraryFromFile() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void LoadChaptersFromFile() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void WriteUsersToFile() {
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnPre = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        chapterIMG = new javax.swing.JLabel();
        scrollChapter = new java.awt.Scrollbar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnPre.setText("Previous");
        btnPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreActionPerformed(evt);
            }
        });
        jPanel1.add(btnPre);

        btnHome.setText("HOME");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        jPanel1.add(btnHome);

        btnNext.setText("Next");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        jPanel1.add(btnNext);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        chapterIMG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chapterIMG, javax.swing.GroupLayout.DEFAULT_SIZE, 997, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollChapter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollChapter, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                    .addComponent(chapterIMG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        int prevChapter = Integer.parseInt(chapterNumber) - 1;

        String prevImagePath = "ComicImage/" + comicId + "/" + prevChapter + ".png";
        File prevImgFile = new File(prevImagePath);

        if (prevImgFile.exists()) {
            if (historyDetail != null) {
                new ChapterDetail(comicDetail, home, historyDetail, comicId, String.valueOf(prevChapter)).setVisible(true);
                this.dispose();
            } else if (libraryDetail != null) {
                new ChapterDetail(comicDetail, home, libraryDetail, comicId, String.valueOf(prevChapter)).setVisible(true);
                this.dispose();
            } else {
                new ChapterDetail(comicDetail, home, comicId, String.valueOf(prevChapter)).setVisible(true);
                this.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(this, "No previous chapter available.", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnPreActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        if (libraryDetail != null && libraryDetail.isVisible()) {
            libraryDetail.dispose();
        }

        if (historyDetail != null && historyDetail.isVisible()) {
            historyDetail.dispose();
        }
        home.setVisible(true);
        comicDetail.dispose();
        this.dispose();
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed

        int nextChapter = Integer.parseInt(chapterNumber) + 1;

        String nextImagePath = "ComicImage/" + comicId + "/" + nextChapter + ".png";
        File nextImgFile = new File(nextImagePath);

        if (nextImgFile.exists()) {
            ChapterDetail nextChapterDetail = new ChapterDetail(comicDetail, home, comicId, String.valueOf(nextChapter));
            nextChapterDetail.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No next chapter available.", "Warning", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btnNextActionPerformed

    public static void main(String args[]) {
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
                new ChapterDetail().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPre;
    private javax.swing.JLabel chapterIMG;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private java.awt.Scrollbar scrollChapter;
    // End of variables declaration//GEN-END:variables
}
