package Main;

import Main.tools.ZoneBundle;
import Main.tools.ZoneCarte;
import java.awt.Color;
import java.awt.Container;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author pauli
 */
public class Pirate extends javax.swing.JFrame {
    Image pirateIcon;
    private ArrayList<ZoneCarte> cartes = new ArrayList();
    private ArrayList<ZoneBundle> bundles = new ArrayList();
    
    //a modifier lors de l'import sur eclipse
    private boolean player1 = true;
    
    /**
     * Creates new form Pirate
     */
    public Pirate() {
        File path = new File(System.getProperty("user.dir") + "\\src\\main\\java\\resource\\Projet_Pirate_Populaire.jpg");
        try {
            pirateIcon = ImageIO.read(path);
        } catch (IOException ex) {
            Logger.getLogger(Pirate.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        repaint();
    }
    
    @Override
    public void addNotify() {
        super.addNotify();
        zoneJeu1.setMainFrame(this);
        zoneHand1.setMainFrame(this);
        zoneDeck1.setMainFrame(this);
        glassPanelCreation();
    }
    
    public void debutTour(){
        debutBundle();
    }
    
    public void terminerTour(){
        if (!zoneJeu1.finTour()){
            System.out.println("nombre de cartes choisies invalide");
            return;
        }
        for (int i =0; i<cartes.size(); i++){
            cartes.get(i).setVisible(false);
            remove(cartes.get(i));
        }
        resetHand();
        System.out.println("calcul stat");
        changerJoueur();
    }
    
    private void debutBundle(){
        Container glassPane = (Container) this.getGlassPane();
        glassPane.setVisible(true);
        
        for (int i=0; i<3; i++){
            ZoneBundle bundle = new ZoneBundle();
            
            bundle.setSize(500, 190);
            bundle.setLocation((getWidth()-bundle.getWidth())/2,
                    ((getHeight()/4)*i)+(getHeight()/16)*(i+1));
            
            System.out.println(bundle.getLocation());

            this.add(bundle);
            bundle.setVisible(true);
            bundles.add(bundle);
            this.repaint();
        }
        
        
        
    }
    
    private void glassPanelCreation(){
        this.setGlassPane(new JComponent(){
            @Override
            protected void paintComponent(Graphics g){
                g.setColor(new Color(0,0,0,0));
                g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }});
        
        Container glassPane = (Container) this.getGlassPane();
        
        glassPane.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                for (int i =0; i<bundles.size(); i++){
                    Point temp = bundles.get(i).getLocation();
                    if (temp.x< e.getPoint().x && (temp.x+bundles.get(i).getWidth())>e.getPoint().x &&
                            temp.y< e.getPoint().y && temp.y+bundles.get(i).getHeight()>e.getPoint().y){
                        finBundle(i);
                        return;
                    }
                }
                
                
            }
        });
        
        repaint();
    }
    public void changerJoueur(){
        int pvJ1 = 2;
        int pvJ2 = 8;
        int popJ1 = -4;
        int popJ2 = 4;
        zoneDeck1.switchClickable();
        player1=!player1;
        zoneImageProfil1.ChangePlayer(player1);
        zoneImageProfil2.ChangePlayer(!player1);
        if (player1){
            zonePV1.updateStat(pvJ1);
            zonePV2.updateStat(pvJ2);
            zonePopularite1.updateStat(popJ1);
            zonePopularite2.updateStat(popJ2);
        }else{
            zonePV1.updateStat(pvJ2);
            zonePV2.updateStat(pvJ1);
            zonePopularite1.updateStat(popJ2);
            zonePopularite2.updateStat(popJ1);
        }
    }
    
    private void finBundle(int choix){
        Container glassPane = (Container) this.getGlassPane();
        glassPane.setVisible(false);
        
        System.out.println("le bundle choisi est: "+choix);
        
        for (int i=0; i<bundles.size(); i++){
            bundles.get(i).setVisible(false);
            remove(bundles.get(i));
        }
        bundles = new ArrayList();
        zoneHand1.spawnCard();
        zoneHand1.spawnCard();
        zoneHand1.spawnCard();
        zoneHand1.spawnCard();
        zoneHand1.spawnCard();
        cartes.getLast().setVisible(false);
        remove(cartes.getLast());
        cartes.removeLast();
        revalidate();
    }
    
    public ArrayList<ZoneCarte> getCartes(){
        return cartes;
    }
    
    public void addCarte(ZoneCarte carte){
        this.setComponentZOrder(carte, 0);
        this.repaint();
        cartes.add(carte);
    }
    
    public void resetHand(){
        cartes = new ArrayList();
        zoneHand1.reset();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        zonePopularite1 = new Main.tools.ZonePopularite();
        zonePV1 = new Main.tools.ZonePV();
        zoneJeu1 = new Main.tools.ZoneJeu();
        zoneHand1 = new Main.tools.ZoneHand();
        zoneImageProfil1 = new Main.tools.ZoneImageProfil();
        zoneImageProfil2 = new Main.tools.ZoneImageProfil();
        zonePopularite2 = new Main.tools.ZonePopularite();
        zonePV2 = new Main.tools.ZonePV();
        finTourButton = new javax.swing.JButton();
        zoneDeck1 = new Main.tools.ZoneDeck();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PiratePopulaire");
        setIconImage(pirateIcon);
        setMaximumSize(new java.awt.Dimension(1200, 600));
        setMinimumSize(new java.awt.Dimension(1200, 600));
        setResizable(false);

        javax.swing.GroupLayout zonePopularite1Layout = new javax.swing.GroupLayout(zonePopularite1);
        zonePopularite1.setLayout(zonePopularite1Layout);
        zonePopularite1Layout.setHorizontalGroup(
            zonePopularite1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );
        zonePopularite1Layout.setVerticalGroup(
            zonePopularite1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout zonePV1Layout = new javax.swing.GroupLayout(zonePV1);
        zonePV1.setLayout(zonePV1Layout);
        zonePV1Layout.setHorizontalGroup(
            zonePV1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );
        zonePV1Layout.setVerticalGroup(
            zonePV1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        zoneJeu1.setPreferredSize(new java.awt.Dimension(750, 300));

        zoneImageProfil1.setPreferredSize(new java.awt.Dimension(180, 180));

        javax.swing.GroupLayout zoneImageProfil1Layout = new javax.swing.GroupLayout(zoneImageProfil1);
        zoneImageProfil1.setLayout(zoneImageProfil1Layout);
        zoneImageProfil1Layout.setHorizontalGroup(
            zoneImageProfil1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );
        zoneImageProfil1Layout.setVerticalGroup(
            zoneImageProfil1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        zoneImageProfil2.setPreferredSize(new java.awt.Dimension(180, 180));

        javax.swing.GroupLayout zoneImageProfil2Layout = new javax.swing.GroupLayout(zoneImageProfil2);
        zoneImageProfil2.setLayout(zoneImageProfil2Layout);
        zoneImageProfil2Layout.setHorizontalGroup(
            zoneImageProfil2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );
        zoneImageProfil2Layout.setVerticalGroup(
            zoneImageProfil2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout zonePopularite2Layout = new javax.swing.GroupLayout(zonePopularite2);
        zonePopularite2.setLayout(zonePopularite2Layout);
        zonePopularite2Layout.setHorizontalGroup(
            zonePopularite2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );
        zonePopularite2Layout.setVerticalGroup(
            zonePopularite2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout zonePV2Layout = new javax.swing.GroupLayout(zonePV2);
        zonePV2.setLayout(zonePV2Layout);
        zonePV2Layout.setHorizontalGroup(
            zonePV2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        zonePV2Layout.setVerticalGroup(
            zonePV2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        finTourButton.setText("fin tour");
        finTourButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                finTourButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout zoneDeck1Layout = new javax.swing.GroupLayout(zoneDeck1);
        zoneDeck1.setLayout(zoneDeck1Layout);
        zoneDeck1Layout.setHorizontalGroup(
            zoneDeck1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        zoneDeck1Layout.setVerticalGroup(
            zoneDeck1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(zonePV2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(zoneImageProfil2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(zoneDeck1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 57, Short.MAX_VALUE)
                        .addComponent(zoneHand1, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(zonePopularite1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zoneImageProfil1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(zonePopularite2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(finTourButton)
                        .addGap(13, 13, 13))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(zoneJeu1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(zonePV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 187, Short.MAX_VALUE)
                                .addComponent(finTourButton)
                                .addGap(132, 132, 132)
                                .addComponent(zonePV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(zoneJeu1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(zoneHand1, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(zoneImageProfil1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(zonePopularite1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(zonePopularite2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(zoneImageProfil2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zonePV2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(zoneDeck1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void finTourButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_finTourButtonMouseClicked
        terminerTour();
    }//GEN-LAST:event_finTourButtonMouseClicked

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
            java.util.logging.Logger.getLogger(Pirate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pirate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pirate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pirate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pirate().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton finTourButton;
    private Main.tools.ZoneDeck zoneDeck1;
    private Main.tools.ZoneHand zoneHand1;
    private Main.tools.ZoneImageProfil zoneImageProfil1;
    private Main.tools.ZoneImageProfil zoneImageProfil2;
    private Main.tools.ZoneJeu zoneJeu1;
    private Main.tools.ZonePV zonePV1;
    private Main.tools.ZonePV zonePV2;
    private Main.tools.ZonePopularite zonePopularite1;
    private Main.tools.ZonePopularite zonePopularite2;
    // End of variables declaration//GEN-END:variables
}
