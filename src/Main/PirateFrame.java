package Main;

import Main.tools.WallPaper;
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
import boundary.*;
import control.ControlChoisirBundle;
import control.ControlJouerCarte;
import control.ControlPartie;
import entities.Deck;
import entities.Partie;
import entities.Pirate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author paulin
 */
public class PirateFrame extends javax.swing.JFrame {
    Image pirateIcon;
    private ArrayList<ZoneCarte> cartes = new ArrayList();
    private ArrayList<ZoneBundle> bundles = new ArrayList();
    
    private Pirate joueur1;
    private Pirate joueur2;
    private Partie partie;
    
    private BoundaryPartie boundaryPartie; 
	private BoundaryJouerCarte boundaryJouerCarte; 
	private BoundaryChoisirBundle boundaryChoisirBundle;
    
    
    //a modifier lors de l'import sur eclipse
    private boolean player1 = true;
    
    /**
     * Creates new form Pirate
     */
    public PirateFrame() {
        File icon = new File(System.getProperty("user.dir") + "\\src\\resource\\Projet_Pirate_Populaire.jpg");
        try {
            pirateIcon = ImageIO.read(icon);
        } catch (IOException ex) {
            Logger.getLogger(PirateFrame.class.getName()).log(Level.SEVERE, null, ex);
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
        initObject();
        repaint();
        
        
        WallPaper background = new WallPaper();
        background.adresse = "\\src\\resource\\background.jpg";
        background.fileSetup();
        background.setSize(getWidth(), getHeight());
        background.setLocation(0,0);
        this.add(background);
    }
    
    public void initObject() {
    	this.joueur1 = new Pirate(10, 0, 1); 
    	this.joueur2 = new Pirate(10, 0, 2); 
		
		Deck deckJoueur1 = new Deck(); 
		Deck deckJoueur2 = new Deck();
		Deck bundleAleatoire = new Deck();
		
		joueur1.setDeck(deckJoueur1);
		joueur2.setDeck(deckJoueur2);
		
		
		this.partie = new Partie(joueur1, joueur2);
		
		ControlPartie controlPartie = new ControlPartie(partie); 
		ControlJouerCarte controlJouerCarte = new ControlJouerCarte(controlPartie); 
		ControlChoisirBundle controlChoisirBundle = new ControlChoisirBundle(bundleAleatoire);
		
		this.boundaryPartie = new BoundaryPartie(controlPartie); 
		this.boundaryJouerCarte = new BoundaryJouerCarte(controlJouerCarte); 
		this.boundaryChoisirBundle = new BoundaryChoisirBundle(controlChoisirBundle);
		boundaryPartie.initPartie(); // pioche des 4 cartes
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
        setLayout(null);
        
        for (int i=0; i<3; i++){
            ZoneBundle bundle = new ZoneBundle();
            
            bundle.setSize(500, 190);
            bundle.setLocation((getWidth()-bundle.getWidth())/2,
                    ((bundle.getHeight())*i)+50);

            this.add(bundle);
            bundle.setVisible(true);
            this.setComponentZOrder(bundle, 0);
            bundles.add(bundle);
            repaint();
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
                            temp.y< (e.getPoint().y-30) && (temp.y+bundles.get(i).getHeight())>(e.getPoint().y)){
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
            .addGap(0, 180, Short.MAX_VALUE)
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
            .addGap(0, 0, Short.MAX_VALUE)
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
            .addGap(0, 180, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout zonePV2Layout = new javax.swing.GroupLayout(zonePV2);
        zonePV2.setLayout(zonePV2Layout);
        zonePV2Layout.setHorizontalGroup(
            zonePV2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 186, Short.MAX_VALUE)
        );
        zonePV2Layout.setVerticalGroup(
            zonePV2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        finTourButton.setBackground(new java.awt.Color(141, 111, 88));
        finTourButton.setText("Prochain Tour");
        finTourButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                finTourButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout zoneDeck1Layout = new javax.swing.GroupLayout(zoneDeck1);
        zoneDeck1.setLayout(zoneDeck1Layout);
        zoneDeck1Layout.setHorizontalGroup(
            zoneDeck1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        zoneDeck1Layout.setVerticalGroup(
            zoneDeck1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(123, Short.MAX_VALUE)
                        .addComponent(zoneDeck1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zoneHand1, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(zoneImageProfil2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(zonePV2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(zonePopularite2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(267, 267, 267)
                        .addComponent(zoneJeu1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(zonePopularite1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zoneImageProfil1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(zonePV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(finTourButton)
                .addGap(162, 162, 162))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(zonePopularite2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(zoneImageProfil2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(zonePV2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(7, 7, 7)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(zoneJeu1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addComponent(finTourButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(zoneDeck1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(zoneHand1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(zonePV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(zoneImageProfil1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(zonePopularite1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(32, 32, 32))
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
            java.util.logging.Logger.getLogger(PirateFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PirateFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PirateFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PirateFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PirateFrame().setVisible(true);
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
