/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quickpick;

import components.Bill;
import components.MyMessageText;
import components.OtherMessageText;
import components.ProductPanel;
import database.Database;
import database.Heap;
import database.Product;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import python.pyExecuter;
import utility.Application;
import utility.Client;
import utility.Server;
import utility.Time;

/**
 *
 * @author Shreyas
 */
public class ProductWindow extends javax.swing.JFrame {

    private AppState states;
    private Database database;
    public Product product;
    private int wallpaperTime = 3*1000;
    private int wallpaperIndex = 0;
    private int wallpaperLength = 4;
    private Heap heap;
    private Trie trie;
    private boolean menuVisible = false;
    private long focusTime = 0;
    private static final long maxFocusTime = 10000;
    public String url;//="file:///C:/Users/Shreyas/Desktop/Softablitz/Images/background/cycle.jpg";//"http://192.168.1.219/img/image.png";//file:///C:/Users/Shreyas/Desktop/Softablitz/Images/background/cycle.jpg";
    public String pythonFile="correlation.py";
    private String ip=AppState.getInstance().ip;
    private Client client;
    private Server server;
    private boolean isServer;
    private boolean isDone;
    
    public ProductWindow() {
        initComponents();
        
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        states=AppState.getInstance();
        
        Thread background = new Thread(()->{
            try {
                wallpaperIndex = (int)(Math.random()*wallpaperLength);
                while (states.isRunning) {
                    backgroundLabel.setIcon(new ImageIcon(getClass().getResource("/images/background "+wallpaperIndex+".png")));
                    Thread.sleep(wallpaperTime);
                    wallpaperIndex = (wallpaperIndex+1)%wallpaperLength;
                }
                System.out.println("background thread ended");
            } catch (Exception e) {
                
            }
            System.out.println("Background thread of product page ended");
        });
        //background.start();
        
        
        database=Database.getInstance();
        server=Server.getInstance();
        client=Client.getInstance();
        
        
        //setThumbnail(imgDisplay, url,400,400);
        
        // Waiting for product information to load
        new Thread(()->{
            try {
                while (product==null && states.isRunning) {
                    Thread.sleep(100);
                }
                txtDescription.setText(product.description);
                txtProductName.setText(product.productName);
                txtPrice.setText(" ₹ "+product.cost);
                txtSellerName.setText(product.sellerName);
                if (product.discount.equals("null"))
                {
                    txtDiscount.setText("0");
                    txtTotalCost.setText(" ₹ "+product.cost);
                }
                else
                {
                    txtDiscount.setText(product.discount);
                    txtTotalCost.setText(" ₹ "+calcDiscount(product.cost,product.discount));
                }
                setThumbnail(imgDisplay, "http://"+ip+"/QuickPickphp/"+product.imagePath, 400, 400);
                System.out.println("python started");
                recommendProducts();
                System.out.println("python ended");
            } catch (Exception e) {
                
            }
            System.out.println("Product loading thread ended");
        }).start();
        
        
        // TODO: Load previous messages
        for (int i=0;i<5;i++)
            sendMessage(" ");
        
        // TODO: Listening for messages to come
        Thread readMessage = new Thread(()->{
            try {
                String msgs[]={"Hello","I didn't get you","Sorry there","please repeast","pardon","excuse me","its not working i guess"};
                int len=msgs.length;
                while (states.isRunning) {
                    int time=(int)(Math.random()*5000)+2000;
                    Thread.sleep(time);
                    showMessage(msgs[(int)(Math.random()*len)], 2);
                }
            } catch (Exception e) {
                
            }
            System.out.println("Message reading thread ended");
        });
        //readMessage.start();
        //isDone
        Thread chatting = new Thread(()->{
            try {
                while (product==null && states.isRunning) {
                    Thread.sleep(100);
                }
                
            } catch (Exception e) {
                
            }
            System.out.println(states.user.emailId);
            System.out.println(product.sellerId);
            if (states.user.emailId.equals(product.sellerId)) {
                isServer=true;
            }
            if (isServer) {
                try {
                    server.startServer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    client.startClient();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            isDone=true;
            
        });
        chatting.start();
        
        Thread reader = new Thread(()->{
            try {
                while (!isDone) {
                    Thread.sleep(10);
                }
                
                while (states.isRunning) {
                    String message="";
                    if (isServer) {
                        message=server.dis.readUTF();
                    }
                    else {
                        message=client.dis.readUTF();
                    }
                    showMessage(message,2);
                    Thread.sleep(10);
                }
                
                
                if (isServer) {
                    
                } else {
                    client.startClient();
                }
            } catch (Exception e) {
                
            }
        });
        reader.start();
        
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
        jLabel2 = new javax.swing.JLabel();
        btnProfileIcon = new javax.swing.JLabel();
        btnHome = new javax.swing.JLabel();
        btnExit = new javax.swing.JLabel();
        menuPanel = new javax.swing.JPanel();
        blankPanel = new javax.swing.JPanel();
        profileMenu = new components.ProfileMenu();
        jPanel2 = new javax.swing.JPanel();
        imgDisplay = new javax.swing.JLabel();
        txtProductName = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtDescription = new javax.swing.JLabel();
        txtSellerName = new javax.swing.JLabel();
        txtPrice = new javax.swing.JLabel();
        btnBuy = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtDiscount = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTotalCost = new javax.swing.JLabel();
        btnAddCart = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtMessageBar = new javax.swing.JTextField();
        btnSendMessage = new javax.swing.JButton();
        scrollMessagePane = new javax.swing.JScrollPane();
        panelMessagePane = new javax.swing.JPanel();
        productPanel1 = new components.ProductPanel();
        productPanel2 = new components.ProductPanel();
        productPanel3 = new components.ProductPanel();
        jLabel1 = new javax.swing.JLabel();
        backgroundLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1085, Short.MAX_VALUE)
                .addComponent(btnExit)
                .addGap(18, 18, 18)
                .addComponent(btnHome)
                .addGap(37, 37, 37)
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnHome)
                            .addComponent(btnProfileIcon)
                            .addComponent(btnExit))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 1400, 70));

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

        jPanel2.setBackground(new java.awt.Color(250, 250, 250));

        imgDisplay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/placeHolder.png"))); // NOI18N

        txtProductName.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        txtProductName.setForeground(new java.awt.Color(50, 50, 50));
        txtProductName.setText("Product Name");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setText("Seller : ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel4.setText("Cost : ");

        txtDescription.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txtDescription.setText("Product Description ................");

        txtSellerName.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        txtSellerName.setForeground(new java.awt.Color(50, 50, 50));
        txtSellerName.setText("Seller Name");

        txtPrice.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        txtPrice.setForeground(new java.awt.Color(50, 50, 50));
        txtPrice.setText("Price");

        btnBuy.setFont(new java.awt.Font("Dialog", 0, 25)); // NOI18N
        btnBuy.setForeground(new java.awt.Color(50, 50, 50));
        btnBuy.setText("Buy");
        btnBuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuyActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel5.setText("Discount :");

        txtDiscount.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        txtDiscount.setForeground(new java.awt.Color(50, 50, 50));
        txtDiscount.setText("100 %");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel6.setText("Total : ");

        txtTotalCost.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        txtTotalCost.setForeground(new java.awt.Color(50, 50, 50));
        txtTotalCost.setText("Price");

        btnAddCart.setFont(new java.awt.Font("Dialog", 0, 25)); // NOI18N
        btnAddCart.setForeground(new java.awt.Color(50, 50, 50));
        btnAddCart.setText("Add to Cart");
        btnAddCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imgDisplay)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(txtDescription))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtSellerName))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(177, 177, 177)
                                    .addComponent(txtProductName)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnBuy, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAddCart, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalCost, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imgDisplay)
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(txtProductName)
                .addGap(56, 56, 56)
                .addComponent(txtDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuy, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddCart, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtSellerName)
                    .addComponent(jLabel6)
                    .addComponent(txtTotalCost, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 1120, 430));

        txtMessageBar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMessageBarKeyTyped(evt);
            }
        });

        btnSendMessage.setText("send");
        btnSendMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendMessageActionPerformed(evt);
            }
        });

        panelMessagePane.setBackground(new java.awt.Color(250, 250, 250));
        panelMessagePane.setLayout(new java.awt.GridLayout(0, 1, 10, 2));
        scrollMessagePane.setViewportView(panelMessagePane);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollMessagePane)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtMessageBar, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSendMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollMessagePane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMessageBar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSendMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 150, 380, 430));
        getContentPane().add(productPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 610, 530, -1));
        getContentPane().add(productPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 610, 530, -1));
        getContentPane().add(productPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 610, 530, -1));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(50, 50, 50));
        jLabel1.setText("You might also like these");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 820, 350, 40));

        backgroundLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background 1.png"))); // NOI18N
        backgroundLabel.setText("jLabel1");
        getContentPane().add(backgroundLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1701, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProfileIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProfileIconMouseClicked
        if (!menuVisible) {
            setParentPanelTo(menuPanel,profileMenu);
            menuVisible=true;
        } else {
            setParentPanelTo(menuPanel,blankPanel);
            menuVisible=false;
        }
    }//GEN-LAST:event_btnProfileIconMouseClicked

    private void btnSendMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendMessageActionPerformed
        String str=txtMessageBar.getText();
        txtMessageBar.setText("");
        sendMessage(str);
    }//GEN-LAST:event_btnSendMessageActionPerformed

    private void txtMessageBarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMessageBarKeyTyped
        if (evt.isActionKey() || evt.getKeyChar()=='\n') {
            String str=txtMessageBar.getText();
            txtMessageBar.setText("");
            sendMessage(str);
        }
    }//GEN-LAST:event_txtMessageBarKeyTyped

    private void btnBuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuyActionPerformed
        
        Thread buy = new Thread(()->{
            database.dbQuery.updateProductAfterSelling(product.productId, states.user.name, states.user.emailId, Time.getCurrentDate(), "");
            Bill bill=new Bill(this,true);
            bill.setVisible(true);
        });
        buy.start();
        
    }//GEN-LAST:event_btnBuyActionPerformed

    private void btnHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseClicked
        MainWindow window=new MainWindow();
        Application.goToFrame(window);
    }//GEN-LAST:event_btnHomeMouseClicked

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
        JOptionPane.showMessageDialog(null,"Exiting");
        Application.closeApp();
    }//GEN-LAST:event_btnExitMouseClicked

    private void btnAddCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCartActionPerformed
        // TODO: add item to user's database
        if (!states.user.cartList.contains(product.productId)) {
            states.user.cartList.add(product.productId);
            states.user.generateWishList();
            Thread updateWish = new Thread(()->{
                database.dbQuery.updateUserWishlist(states.user.emailId, states.user.wishList);
                JOptionPane.showMessageDialog(null, "Added to Wishlist");
            });
            updateWish.start();
            
        } else {
            JOptionPane.showMessageDialog(null, "You already have this product in your wishlist");
        }
    }//GEN-LAST:event_btnAddCartActionPerformed

    
    
    
    /**
     * adds product to the scroll bar
     * might have to use panel.removeAll() before hand
     * if false then heap is not filled again
     * if true then product is inserted in heap
     * @param item
     * @param flag 
     */
    
    // TODO: Save the messages on database
    public void sendMessage(String msg) {
        try {
            if (isServer) {
                server.dos.writeUTF(msg);
                server.dos.flush();
            } else {
                client.dos.writeUTF(msg);
                client.dos.flush();
            }
            showMessage(msg, 1);
        } catch (Exception e) {
            
        }
        
    }
    
    private String calcDiscount(int cost,String discount) {
        double rebate=Integer.valueOf(discount);
        double price=cost-rebate*cost/100.0;
        int amount=(int)price;
        return Integer.toString(amount);
    }
    
    /**
     * Shows the message on scroll pane
     * if sender is 1 then it is user else other person
     * @param msg
     * @param sender 
     */
    public void showMessage(String msg,int sender) {
        scrollMessagePane.remove(panelMessagePane);
        if (sender==1) {
            MyMessageText txt=new MyMessageText();
            txt.setText(msg);
            panelMessagePane.add(txt);
        } else {
            OtherMessageText txt=new OtherMessageText();
            txt.setText(msg);
            panelMessagePane.add(txt);
        }
        scrollMessagePane.setViewportView(panelMessagePane);
        JScrollBar vertical = scrollMessagePane.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
    }
    
    /**
     * Loads the images at given URL and displays it on the label with given width and height
     * @param label
     * @param url
     * @param w
     * @param h 
     */
    public void setThumbnail(JLabel label,String url,int w,int h) {
        
        Thread loader = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BufferedImage brImg = ImageIO.read(new URL(url));
                    //int w=120,h=100;
                    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2 = resizedImg.createGraphics();
                    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                    g2.drawImage(brImg, 0, 0, w, h, null);
                    g2.dispose();
                    label.setIcon(new ImageIcon(resizedImg)); 
                }
                catch (Exception e) {
                    System.out.println("error");
                    e.printStackTrace();
                }
            }
        });
        loader.start();
        
    }
    
    // TODO: correect this
    
    /**
     * Runs python scripts to recommend products
     * 
     */
    public void recommendProducts() {
        pyExecuter pyexe = new pyExecuter();
        pyexe.runPython(pythonFile, product.type,(output)->{
            
            productPanel1.setProduct(product);
            productPanel2.setProduct(product);
            productPanel3.setProduct(product);
            
            
            String items[]=output.trim().split("\n");
            System.out.println("recommendations = "+Arrays.toString(items));
            
            // TODO: Query product according to type/name
            
            Product pro1=database.dbQuery.getAllProductByTag(items[0]).get(0);
            Product pro2=database.dbQuery.getAllProductByTag(items[1]).get(0);
            Product pro3=database.dbQuery.getAllProductByTag(items[2]).get(0);
            
            productPanel1.setProduct(pro1);
            productPanel2.setProduct(pro2);
            productPanel3.setProduct(pro3);
            
        });
    }
    
    
    
    private void setParentPanelTo(JPanel parent,JPanel panel) {
        //menuPanel.removeAll();
        parent.removeAll();
        parent.add(panel);
        parent.repaint();
        parent.revalidate();
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
            java.util.logging.Logger.getLogger(ProductWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProductWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundLabel;
    private javax.swing.JPanel blankPanel;
    private javax.swing.JButton btnAddCart;
    private javax.swing.JButton btnBuy;
    private javax.swing.JLabel btnExit;
    private javax.swing.JLabel btnHome;
    private javax.swing.JLabel btnProfileIcon;
    private javax.swing.JButton btnSendMessage;
    private javax.swing.JLabel imgDisplay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JPanel panelMessagePane;
    private components.ProductPanel productPanel1;
    private components.ProductPanel productPanel2;
    private components.ProductPanel productPanel3;
    private components.ProfileMenu profileMenu;
    private javax.swing.JScrollPane scrollMessagePane;
    private javax.swing.JLabel txtDescription;
    private javax.swing.JLabel txtDiscount;
    private javax.swing.JTextField txtMessageBar;
    private javax.swing.JLabel txtPrice;
    private javax.swing.JLabel txtProductName;
    private javax.swing.JLabel txtSellerName;
    private javax.swing.JLabel txtTotalCost;
    // End of variables declaration//GEN-END:variables
}
