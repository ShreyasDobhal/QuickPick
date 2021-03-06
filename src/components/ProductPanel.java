/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import database.Product;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import quickpick.AppState;
import quickpick.MainWindow;
import quickpick.ProductWindow;
import utility.Application;

/**
 *
 * @author Shreyas
 */
public class ProductPanel extends javax.swing.JPanel{

    private Product product;
    private String ip=AppState.getInstance().ip;
    
    /**
     * Creates new form ProductPanel
     */
    public ProductPanel() {
        initComponents();
        setPreferredSize(new Dimension(700,200));
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        
        addMouseListener(new FocusListener());
        
    }
    
    public void setProduct(Product prod) {
        product=prod;
        
        txtDescription.setText(convertToMultiline(product.description));
        txtProductName.setText(product.productName);
        txtSeller.setText(product.sellerName);
        txtCost.setText(product.cost+"");
        if (prod.discount.equals("null"))
            txtDiscount.setText("");
        else
            txtDiscount.setText("Discount : "+prod.discount+" %");
        
        setThumbnail(imgLabel, "http://"+ip+"/QuickPickphp/"+prod.imagePath, 120, 100);
        
    }
    
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
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imgLabel = new javax.swing.JLabel();
        txtProductName = new javax.swing.JLabel();
        txtDescription = new javax.swing.JLabel();
        txtSeller = new javax.swing.JLabel();
        txtCost = new javax.swing.JLabel();
        txtDiscount = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 255), new java.awt.Color(204, 204, 255), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));

        imgLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/thumbnail.png"))); // NOI18N

        txtProductName.setFont(new java.awt.Font("DialogInput", 0, 25)); // NOI18N
        txtProductName.setText("Product Name");

        txtDescription.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtDescription.setText("product description");

        txtSeller.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtSeller.setText("Seller");

        txtCost.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtCost.setText("Cost");

        txtDiscount.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtDiscount.setText("Discount = 10 %");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imgLabel)
                    .addComponent(txtSeller, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                        .addComponent(txtCost, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(imgLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSeller, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDiscount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    public static String convertToMultiline(String orig)
    {
        return "<html>" + orig.replaceAll("\n", "<br>");
    }
    
    class FocusListener implements MouseListener {
    
        @Override
        public void mouseEntered(MouseEvent e)
        {
            ((JPanel)e.getSource()).setBackground(new Color(200, 200, 200));
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            
            ProductWindow window=new ProductWindow();
            window.product=product;
            Application.goToFrame(window);
            
//            JFrame frame = AppState.getInstance().frame;
//            if (frame instanceof MainWindow) {
//                ((MainWindow)frame).productClicked(product);
//                
//            } else if (frame instanceof ProductWindow) {
//                ((ProductWindow)frame).product
//            }
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            ((JPanel)e.getSource()).setBackground(new Color(255, 255, 255));
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imgLabel;
    private javax.swing.JLabel txtCost;
    private javax.swing.JLabel txtDescription;
    private javax.swing.JLabel txtDiscount;
    private javax.swing.JLabel txtProductName;
    private javax.swing.JLabel txtSeller;
    // End of variables declaration//GEN-END:variables

    
}

