/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quickpick;

import components.ProductPanel;
import database.Database;
import database.Heap;
import database.Product;
import database.User;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Queue;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import python.pyExecuter;
import utility.Application;

/**
 *
 * @author Shreyas
 */
public class MainWindow extends javax.swing.JFrame {

    private AppState states;
    private Database database;
    public User user;
    private int wallpaperTime = 3*1000;
    private int wallpaperIndex = 0;
    private int wallpaperLength = 4;
    private Heap heap;
    private Trie trie;
    private boolean menuVisible = false;
    private long focusTime = 0;
    private static final long maxFocusTime = 10000;
    private static final String speechRecog = "speechPy.py";
    
    public MainWindow() {
        initComponents();
        
        recogWait.setVisible(false);
        
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        states=AppState.getInstance();
        Thread background = new Thread(()->{
            try {
                wallpaperIndex = (int)(Math.random()*wallpaperLength);
                while (states.isRunning) {
                    backgroundLabel.setIcon(new ImageIcon(getClass().getResource("/images/background "+wallpaperIndex+".png")));
                    Thread.sleep(wallpaperTime);
                    wallpaperIndex = (wallpaperIndex+1)%wallpaperLength;
                    //System.out.println("running");
                }
                System.out.println("background thread ended");
            } catch (Exception e) {
                
            }
            System.out.println("Background thread of main window page ended");
        });
        background.start();
        
        Thread searchSuggest=new Thread(()->{
            try {
                long finalTime=0;
                while (states.isRunning) {
                    finalTime=System.currentTimeMillis();
                    long duration = (finalTime-focusTime);
                    if (duration > maxFocusTime) {
                        setParentPanelTo(popupParentPanel,hidePanel);
                    }
                }
            } catch (Exception e) {
                
            }
            System.out.println("Background thread of search suggest ended");
        });
        searchSuggest.start();
        
        database=Database.getInstance();
        
        heap=new Heap();
        trie=database.trie;
        //trie=new Trie();
        
        
        
//        for (String tag:database.tag) {
//            trie.add(tag);
//        }
        
        initHomePage();
        startTrie();
        
        
        
//        for (int i=0;i<10;i++) {
//            addComponent();
//        }
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupParentPanel = new javax.swing.JPanel();
        hidePanel = new javax.swing.JPanel();
        txtSearchResultInfo = new javax.swing.JLabel();
        showPanel = new javax.swing.JPanel();
        searchScroll = new javax.swing.JScrollPane();
        searchPanel = new javax.swing.JPanel();
        recogWait = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnProfileIcon = new javax.swing.JLabel();
        txtSearchBar = new javax.swing.JTextField();
        btnMic = new javax.swing.JLabel();
        btnHome = new javax.swing.JLabel();
        btnExit = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        chkPriceInc = new javax.swing.JRadioButton();
        chkPriceDec = new javax.swing.JRadioButton();
        chkNewest = new javax.swing.JRadioButton();
        chkOldest = new javax.swing.JRadioButton();
        chkDiscount = new javax.swing.JCheckBox();
        menuPanel = new javax.swing.JPanel();
        blankPanel = new javax.swing.JPanel();
        profileMenu = new components.ProfileMenu();
        backgroundLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        popupParentPanel.setOpaque(false);
        popupParentPanel.setLayout(new java.awt.CardLayout());

        hidePanel.setOpaque(false);

        txtSearchResultInfo.setFont(new java.awt.Font("Dialog", 0, 17)); // NOI18N
        txtSearchResultInfo.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout hidePanelLayout = new javax.swing.GroupLayout(hidePanel);
        hidePanel.setLayout(hidePanelLayout);
        hidePanelLayout.setHorizontalGroup(
            hidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hidePanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(txtSearchResultInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        hidePanelLayout.setVerticalGroup(
            hidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hidePanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(txtSearchResultInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        popupParentPanel.add(hidePanel, "card2");

        searchPanel.setLayout(new java.awt.GridLayout(0, 1, 5, 5));
        searchScroll.setViewportView(searchPanel);

        javax.swing.GroupLayout showPanelLayout = new javax.swing.GroupLayout(showPanel);
        showPanel.setLayout(showPanelLayout);
        showPanelLayout.setHorizontalGroup(
            showPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(searchScroll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
        );
        showPanelLayout.setVerticalGroup(
            showPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(searchScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );

        popupParentPanel.add(showPanel, "card3");

        getContentPane().add(popupParentPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, 710, 110));

        recogWait.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/speechRecog.gif"))); // NOI18N
        getContentPane().add(recogWait, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 90, 400, -1));

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon.png"))); // NOI18N
        jLabel2.setText("jLabel2");

        btnProfileIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/profileSmall.png"))); // NOI18N
        btnProfileIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProfileIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProfileIconMouseClicked(evt);
            }
        });

        txtSearchBar.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        txtSearchBar.setForeground(new java.awt.Color(102, 102, 102));
        txtSearchBar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchBarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSearchBarFocusLost(evt);
            }
        });
        txtSearchBar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchBarKeyTyped(evt);
            }
        });

        btnMic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/micSmall.png"))); // NOI18N
        btnMic.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMicMouseClicked(evt);
            }
        });

        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home.png"))); // NOI18N
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHomeMouseClicked(evt);
            }
        });

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(217, 217, 217)
                .addComponent(txtSearchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnMic, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addComponent(btnExit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHome)
                .addGap(18, 18, 18)
                .addComponent(btnProfileIcon)
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnProfileIcon)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnMic, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSearchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnExit))
                            .addComponent(btnHome))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 1400, 70));

        panel.setBackground(new java.awt.Color(250, 250, 250));
        panel.setLayout(new java.awt.GridLayout(0, 1, 25, 25));
        scroll.setViewportView(panel);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 1016, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, 1040, 730));

        jPanel3.setBackground(new java.awt.Color(250, 250, 250));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filter", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 17))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        jLabel1.setText("Sort by : ");

        chkPriceInc.setBackground(new java.awt.Color(250, 250, 250));
        chkPriceInc.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        chkPriceInc.setSelected(true);
        chkPriceInc.setText(" Price (Increasing)");
        chkPriceInc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPriceIncActionPerformed(evt);
            }
        });

        chkPriceDec.setBackground(new java.awt.Color(250, 250, 250));
        chkPriceDec.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        chkPriceDec.setText(" Price (Decreasing)");
        chkPriceDec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPriceDecActionPerformed(evt);
            }
        });

        chkNewest.setBackground(new java.awt.Color(250, 250, 250));
        chkNewest.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        chkNewest.setText(" Newest");
        chkNewest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkNewestActionPerformed(evt);
            }
        });

        chkOldest.setBackground(new java.awt.Color(250, 250, 250));
        chkOldest.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        chkOldest.setText(" Oldest");
        chkOldest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkOldestActionPerformed(evt);
            }
        });

        chkDiscount.setBackground(new java.awt.Color(250, 250, 250));
        chkDiscount.setText(" Show discounted products");
        chkDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDiscountActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkOldest)
                            .addComponent(chkNewest)
                            .addComponent(chkPriceDec)
                            .addComponent(chkPriceInc)
                            .addComponent(chkDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addComponent(chkPriceInc)
                .addGap(18, 18, 18)
                .addComponent(chkPriceDec)
                .addGap(18, 18, 18)
                .addComponent(chkNewest)
                .addGap(18, 18, 18)
                .addComponent(chkOldest)
                .addGap(18, 18, 18)
                .addComponent(chkDiscount)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 270, 320));

        menuPanel.setOpaque(false);
        menuPanel.setLayout(new java.awt.CardLayout());

        blankPanel.setOpaque(false);

        javax.swing.GroupLayout blankPanelLayout = new javax.swing.GroupLayout(blankPanel);
        blankPanel.setLayout(blankPanelLayout);
        blankPanelLayout.setHorizontalGroup(
            blankPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );
        blankPanelLayout.setVerticalGroup(
            blankPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );

        menuPanel.add(blankPanel, "card2");
        menuPanel.add(profileMenu, "card3");

        getContentPane().add(menuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 120, 270, 320));

        backgroundLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background 1.png"))); // NOI18N
        backgroundLabel.setText("jLabel1");
        getContentPane().add(backgroundLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1701, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkPriceIncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPriceIncActionPerformed
        deselectAll();
        chkPriceInc.setSelected(true);
        filterProducts(Heap.SORT_BY_COST_INC);
    }//GEN-LAST:event_chkPriceIncActionPerformed

    private void chkPriceDecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPriceDecActionPerformed
        deselectAll();
        chkPriceDec.setSelected(true);
        filterProducts(Heap.SORT_BY_COST_DEC);
    }//GEN-LAST:event_chkPriceDecActionPerformed

    private void chkNewestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkNewestActionPerformed
        deselectAll();
        chkNewest.setSelected(true);
        filterProducts(Heap.SORT_BY_DOU_DEC);
    }//GEN-LAST:event_chkNewestActionPerformed

    private void chkOldestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkOldestActionPerformed
        deselectAll();
        chkOldest.setSelected(true);
        filterProducts(Heap.SORT_BY_DOU_INC);
    }//GEN-LAST:event_chkOldestActionPerformed

    private void btnProfileIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProfileIconMouseClicked
        if (!menuVisible) {
            setParentPanelTo(menuPanel,profileMenu);
            menuVisible=true;
        } else {
            setParentPanelTo(menuPanel,blankPanel);
            menuVisible=false;
        }
    }//GEN-LAST:event_btnProfileIconMouseClicked

    private void txtSearchBarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchBarFocusGained
        setParentPanelTo(popupParentPanel,showPanel);
        focusTime=System.currentTimeMillis();
    }//GEN-LAST:event_txtSearchBarFocusGained

    private void txtSearchBarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchBarFocusLost
        setParentPanelTo(popupParentPanel,hidePanel);
    }//GEN-LAST:event_txtSearchBarFocusLost

    private void txtSearchBarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchBarKeyTyped
        String str=txtSearchBar.getText();
        if (evt.isActionKey() || evt.getKeyChar()=='\n') {
            SearchWord(str);
        }
        setParentPanelTo(popupParentPanel,showPanel);
        focusTime=System.currentTimeMillis();
    }//GEN-LAST:event_txtSearchBarKeyTyped

    private void btnMicMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMicMouseClicked
        
        pyExecuter pyexe=new pyExecuter();
        pyexe.runPython(speechRecog, "", (out)->{
            if (recogWait.isVisible()) {
                if (!out.equals("-1")) {
                    txtSearchBar.setText(out);
                }
                recogWait.setVisible(false);
            }
            
        });
        
        if (recogWait.isVisible()) {
            recogWait.setVisible(false);
        } else {
            recogWait.setVisible(true);
        }
    }//GEN-LAST:event_btnMicMouseClicked

    private void btnHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseClicked
        MainWindow window=new MainWindow();
        Application.goToFrame(window);
    }//GEN-LAST:event_btnHomeMouseClicked

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
        JOptionPane.showMessageDialog(null,"Exiting");
        Application.closeApp();
    }//GEN-LAST:event_btnExitMouseClicked

    private void chkDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDiscountActionPerformed
        
        if (chkDiscount.isSelected()) {
            Thread loadResults = new Thread(()->{
                List<Product> products=database.dbQuery.getAllProductByDiscount();
                panel.removeAll();
                heap=new Heap();
                for (Product product:products) {
                    addProduct(product, true);
                }
            });
            loadResults.start();
        } else {
            panel.removeAll();
            heap=new Heap();
            initHomePage();
        }
        
        
    }//GEN-LAST:event_chkDiscountActionPerformed

    // TODO: Query the string
    /**
     * Searches for given word and displays them on the page
     * @param str 
     */
    public void SearchWord(String str) {
        focusTime=0;
        str=str.trim();
        if (trie.contains(str)) {
            txtSearchResultInfo.setText("");
        } else {
            str=BestMatch.find(str);
            txtSearchResultInfo.setText("word not found, so showing "+str+" instead");
        }
        System.out.println("searching for "+str);
        txtSearchBar.setText(str);
        
        //
        // TODO: Get list from database
        
        List<Product> list=database.dbQuery.getAllProductByTag(str);
        heap=new Heap();
        panel.removeAll();
        for (Product product:list) {
            addProduct(product,true);
            System.out.println(product.tags);
        }
        
    }
    
    // TODO: Handle with actual database
//    public void productClicked(Product product) {
//        ProductWindow window=new ProductWindow();
//        window.product=product;
//        Application.goToFrame(window);
//    }
    
    
    /**
     * Starts the thread for trie for auto suggestions and auto complete
     */
    private void startTrie() {
        new Thread(()->{
            try {
                String prev="";
                while (states.isRunning) {
                    String str=txtSearchBar.getText();
                    if (!prev.equals(str))
                    {
                        prev=str;
                        Queue<String> words=trie.wordSuggest(str);
                        String suggestions[]=new String[5];
                        searchPanel.removeAll();;
                        if (words==null || words.size()==0 || "".equals(str) || str==null) {
                            searchPanel.repaint();
                            focusTime=0;
                        }
                        else
                        {
                            for (int i=0;i<suggestions.length;i++) {
                                suggestions[i]=" ";
                            }
                            int index=0;
                            for (String word:words) {
                                suggestions[index++]=word;
                                if (index>=suggestions.length)
                                    break;
                            }
                            for (int i=0;i<suggestions.length;i++) {
                                searchScroll.remove(searchPanel);
                                String word=suggestions[i];
                                JLabel txt=new JLabel();
                                txt.setText(word);
                                txt.setFont(new java.awt.Font("Dialog", 0, 20));
                                txt.addMouseListener(new MouseAdapter() {
                                    public void mouseClicked(MouseEvent evt) {
                                        String str=txt.getText();
                                        System.out.println(str+" clicked");
                                        focusTime=0;
                                        txtSearchBar.setText(str);
                                    }
                                });
//                                txt.setPreferredSize(new Dimension(200,0));
//                                txt.setMinimumSize(txt.getPreferredSize());
//                                txt.setMaximumSize(txt.getPreferredSize());
                                searchPanel.add(txt);
                                searchScroll.setViewportView(searchPanel);

                            }
                        }
                        
                    }
                    
                    Thread.sleep(100);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Trie thread ended");
        }).start();
    }
    
    /**
     * Sorts the product list and displays them
     * @param sortType 
     */
    private void filterProducts(int sortType) {
        heap.buildHeap(sortType);
        panel.removeAll();
        for (Product product:heap.getList()) {
            addProduct(product,false);
        }
    }
    
    private void setParentPanelTo(JPanel parent,JPanel panel) {
        //menuPanel.removeAll();
        parent.removeAll();
        parent.add(panel);
        parent.repaint();
        parent.revalidate();
    }
    
    private void deselectAll() {
        chkPriceInc.setSelected(false);
        chkPriceDec.setSelected(false);
        chkNewest.setSelected(false);
        chkOldest.setSelected(false);
    }
    
    
    
    /**
     * adds product to the scroll bar
     * might have to use panel.removeAll() before hand
     * if false then heap is not filled again
     * if true then product is inserted in heap
     * @param item
     * @param flag 
     */
    private void addProduct(Product item,boolean flag) {
        scroll.remove(panel);
        ProductPanel product=new ProductPanel();
        product.setProduct(item);
        
        panel.add(product);
        if (flag) {
            heap.insert(item);
        }
        
        scroll.setViewportView(panel);
    }
    
    // TODO: show discounted products instead
    public void initHomePage() {
        Thread loadResults = new Thread(()->{
            List<Product> products=database.dbQuery.getAllProduct();
            heap=new Heap();
            for (Product product:products) {
                addProduct(product, true);
            }
        });
        loadResults.start();
        
    }
    
    private void addComponent() {
        String tag=Database.tag[(int)(Math.random()*Database.tag.length)];
        scroll.remove(panel);
        ProductPanel product=new ProductPanel();
        Product item=new Product(tag,"Very expensive "+tag+".\n First hand and best in the town right now","Shreyas",(int)(Math.random()*10000));
        //item.tags.add(tag);
        product.setProduct(item);
        
        panel.add(product);
        heap.insert(item);
        scroll.setViewportView(panel);
    }
    
    
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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundLabel;
    private javax.swing.JPanel blankPanel;
    private javax.swing.JLabel btnExit;
    private javax.swing.JLabel btnHome;
    private javax.swing.JLabel btnMic;
    private javax.swing.JLabel btnProfileIcon;
    private javax.swing.JCheckBox chkDiscount;
    private javax.swing.JRadioButton chkNewest;
    private javax.swing.JRadioButton chkOldest;
    private javax.swing.JRadioButton chkPriceDec;
    private javax.swing.JRadioButton chkPriceInc;
    private javax.swing.JPanel hidePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel popupParentPanel;
    private components.ProfileMenu profileMenu;
    private javax.swing.JLabel recogWait;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JScrollPane searchScroll;
    private javax.swing.JPanel showPanel;
    private javax.swing.JTextField txtSearchBar;
    private javax.swing.JLabel txtSearchResultInfo;
    // End of variables declaration//GEN-END:variables
}
