/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.management.system;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Kaviraj
 */
public class diagnosticScreen extends javax.swing.JFrame {

    /**
     * Creates new form diagnosticScreen
     */
    public save_diagnostic sd1;
public client_doctor doctRefresh;
    
    public diagnosticScreen(String nameval, String snameval, String reasonval, String dateval, String checkId,client_doctor doc1) {
        initComponents();

        //this will set the frame to maximised size
        this.setExtendedState(this.getExtendedState() | myPatients.MAXIMIZED_BOTH);

        patientDetDiag.setBackground(new Color(0, 51, 153, 180));
        txtPane.getViewport().setBackground(new Color(0, 51, 153, 180));
        txtAreaDiagnosis.setBackground(new Color(0, 51, 153, 180));

        namerpl.setText(nameval);
        surnamerpl.setText(snameval);
        reasonrpl.setText(reasonval);
        daterpl.setText(dateval);

        doctRefresh = doc1;
        save_diagnostic sd = new save_diagnostic(txtAreaDiagnosis, specialtrtCombo, checkId);
        sd1 = sd;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        diagnosisMenu = new javax.swing.JPanel();
        diagnosisTitle = new javax.swing.JLabel();
        iconDiagnosis = new javax.swing.JLabel();
        backPanel = new javax.swing.JPanel();
        backBtn = new javax.swing.JLabel();
        diagnosisMain = new JPanel() {  
            public void paintComponent(Graphics g) {  
                Image img = Toolkit.getDefaultToolkit().getImage(  
                    diagnosticScreen.class.getResource("/Picture_icon/receptionistbg.jpg"));  
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
            }  
        };
        patientDetDiag = new javax.swing.JPanel();
        namelbl = new javax.swing.JLabel();
        namerpl = new javax.swing.JLabel();
        surnamelbl = new javax.swing.JLabel();
        surnamerpl = new javax.swing.JLabel();
        reasonlbl = new javax.swing.JLabel();
        reasonrpl = new javax.swing.JLabel();
        datelbl = new javax.swing.JLabel();
        daterpl = new javax.swing.JLabel();
        detlbl = new javax.swing.JLabel();
        diagnosisArea = new javax.swing.JPanel();
        writeTitle = new javax.swing.JLabel();
        txtPane = new javax.swing.JScrollPane();
        txtAreaDiagnosis = new javax.swing.JTextArea();
        saveDiagnosis = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        specialtrtLbl = new javax.swing.JLabel();
        specialtrtCombo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        diagnosisMenu.setBackground(new java.awt.Color(0, 51, 153));
        diagnosisMenu.setForeground(new java.awt.Color(0, 51, 153));
        diagnosisMenu.setLayout(new java.awt.BorderLayout());

        diagnosisTitle.setBackground(new java.awt.Color(0, 51, 153));
        diagnosisTitle.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        diagnosisTitle.setForeground(new java.awt.Color(255, 255, 255));
        diagnosisTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        diagnosisTitle.setText("Diagnosis");
        diagnosisTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        diagnosisTitle.setPreferredSize(new java.awt.Dimension(34, 72));
        diagnosisMenu.add(diagnosisTitle, java.awt.BorderLayout.CENTER);

        iconDiagnosis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/cross.jpg"))); // NOI18N
        diagnosisMenu.add(iconDiagnosis, java.awt.BorderLayout.LINE_END);

        backPanel.setBackground(new java.awt.Color(0, 51, 153));
        backPanel.setPreferredSize(new java.awt.Dimension(100, 34));
        backPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backPanelMouseExited(evt);
            }
        });
        backPanel.setLayout(new java.awt.BorderLayout());

        backBtn.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        backBtn.setForeground(new java.awt.Color(255, 255, 255));
        backBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        backBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/back.png"))); // NOI18N
        backBtn.setText("Back");
        backBtn.setToolTipText("");
        backBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backBtnMouseExited(evt);
            }
        });
        backPanel.add(backBtn, java.awt.BorderLayout.CENTER);

        diagnosisMenu.add(backPanel, java.awt.BorderLayout.LINE_START);

        getContentPane().add(diagnosisMenu, java.awt.BorderLayout.PAGE_START);

        patientDetDiag.setPreferredSize(new java.awt.Dimension(450, 536));
        java.awt.GridBagLayout jPanel3Layout = new java.awt.GridBagLayout();
        jPanel3Layout.columnWidths = new int[] {0, 35, 0, 35, 0};
        jPanel3Layout.rowHeights = new int[] {0, 35, 0, 35, 0, 35, 0, 35, 0, 35, 0};
        patientDetDiag.setLayout(jPanel3Layout);

        namelbl.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        namelbl.setForeground(new java.awt.Color(255, 255, 255));
        namelbl.setText("Name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        patientDetDiag.add(namelbl, gridBagConstraints);

        namerpl.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        namerpl.setForeground(new java.awt.Color(255, 255, 255));
        namerpl.setText("name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        patientDetDiag.add(namerpl, gridBagConstraints);

        surnamelbl.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        surnamelbl.setForeground(new java.awt.Color(255, 255, 255));
        surnamelbl.setText("Surname:");
        surnamelbl.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        patientDetDiag.add(surnamelbl, gridBagConstraints);

        surnamerpl.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        surnamerpl.setForeground(new java.awt.Color(255, 255, 255));
        surnamerpl.setText("Surname");
        surnamerpl.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        patientDetDiag.add(surnamerpl, gridBagConstraints);

        reasonlbl.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        reasonlbl.setForeground(new java.awt.Color(255, 255, 255));
        reasonlbl.setText("Reason:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        patientDetDiag.add(reasonlbl, gridBagConstraints);

        reasonrpl.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        reasonrpl.setForeground(new java.awt.Color(255, 255, 255));
        reasonrpl.setText("Reason");
        reasonrpl.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        patientDetDiag.add(reasonrpl, gridBagConstraints);

        datelbl.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        datelbl.setForeground(new java.awt.Color(255, 255, 255));
        datelbl.setText("Date:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        patientDetDiag.add(datelbl, gridBagConstraints);

        daterpl.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        daterpl.setForeground(new java.awt.Color(255, 255, 255));
        daterpl.setText("Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        patientDetDiag.add(daterpl, gridBagConstraints);

        detlbl.setBackground(new java.awt.Color(255, 255, 255));
        detlbl.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        detlbl.setForeground(new java.awt.Color(255, 255, 255));
        detlbl.setText("Details");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        patientDetDiag.add(detlbl, gridBagConstraints);

        diagnosisArea.setOpaque(false);
        diagnosisArea.setLayout(new java.awt.GridBagLayout());

        writeTitle.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        writeTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        writeTitle.setText("Write Diagnosis");
        writeTitle.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(50, 0, 0, 0);
        diagnosisArea.add(writeTitle, gridBagConstraints);

        txtAreaDiagnosis.setColumns(20);
        txtAreaDiagnosis.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        txtAreaDiagnosis.setForeground(new java.awt.Color(255, 255, 255));
        txtAreaDiagnosis.setRows(5);
        txtPane.setViewportView(txtAreaDiagnosis);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 141;
        gridBagConstraints.ipady = 56;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(50, 50, 0, 50);
        diagnosisArea.add(txtPane, gridBagConstraints);

        saveDiagnosis.setBackground(new java.awt.Color(0, 51, 153));
        saveDiagnosis.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        saveDiagnosis.setForeground(new java.awt.Color(255, 255, 255));
        saveDiagnosis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/save.png"))); // NOI18N
        saveDiagnosis.setText("Save");
        saveDiagnosis.setToolTipText("");
        saveDiagnosis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                saveDiagnosisMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                saveDiagnosisMouseExited(evt);
            }
        });
        saveDiagnosis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveDiagnosisActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 50, 0);
        diagnosisArea.add(saveDiagnosis, gridBagConstraints);

        jSplitPane1.setBackground(new java.awt.Color(0, 51, 153));
        jSplitPane1.setPreferredSize(new java.awt.Dimension(350, 35));

        specialtrtLbl.setBackground(new java.awt.Color(0, 51, 153));
        specialtrtLbl.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        specialtrtLbl.setForeground(new java.awt.Color(255, 255, 255));
        specialtrtLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        specialtrtLbl.setText("Special Treatments");
        specialtrtLbl.setToolTipText("");
        specialtrtLbl.setMaximumSize(new java.awt.Dimension(500, 24));
        specialtrtLbl.setMinimumSize(new java.awt.Dimension(300, 24));
        specialtrtLbl.setOpaque(true);
        specialtrtLbl.setPreferredSize(new java.awt.Dimension(250, 24));
        jSplitPane1.setLeftComponent(specialtrtLbl);

        specialtrtCombo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        specialtrtCombo.setForeground(new java.awt.Color(255, 255, 255));
        specialtrtCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "ENT", "ICU", "X-Ray", "MRI" }));
        jSplitPane1.setRightComponent(specialtrtCombo);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 0, 0);
        diagnosisArea.add(jSplitPane1, gridBagConstraints);

        javax.swing.GroupLayout diagnosisMainLayout = new javax.swing.GroupLayout(diagnosisMain);
        diagnosisMain.setLayout(diagnosisMainLayout);
        diagnosisMainLayout.setHorizontalGroup(
            diagnosisMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(diagnosisMainLayout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(patientDetDiag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(diagnosisArea, javax.swing.GroupLayout.DEFAULT_SIZE, 1179, Short.MAX_VALUE)
                .addContainerGap())
        );
        diagnosisMainLayout.setVerticalGroup(
            diagnosisMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, diagnosisMainLayout.createSequentialGroup()
                .addComponent(diagnosisArea, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                .addGap(31, 31, 31))
            .addComponent(patientDetDiag, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(diagnosisMain, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveDiagnosisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveDiagnosisActionPerformed
        try {
            // TODO add your handling code here:

            if (sd1.checkDiag() == false) {
                return;
            }
            sd1.updateChkUp();
            sd1.addTreatment();

            doctRefresh.refresh();
            this.dispose();
        } catch (RemoteException ex) {
            Logger.getLogger(diagnosticScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(diagnosticScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(diagnosticScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(diagnosticScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(diagnosticScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_saveDiagnosisActionPerformed

    private void backPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backPanelMouseEntered
        // TODO add your handling code here:
        backPanel.setBackground(Color.white);
        backPanel.setForeground(new Color(0, 51, 153));
    }//GEN-LAST:event_backPanelMouseEntered

    private void backPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backPanelMouseExited
        // TODO add your handling code here:
        backPanel.setBackground(new Color(0, 51, 153));
        backPanel.setForeground(Color.white);
    }//GEN-LAST:event_backPanelMouseExited

    private void backBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backBtnMouseEntered
        // TODO add your handling code here:
        backPanel.setBackground(Color.white);
        backBtn.setForeground(new Color(0, 51, 153));
    }//GEN-LAST:event_backBtnMouseEntered

    private void backBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backBtnMouseExited
        // TODO add your handling code here:
        backPanel.setBackground(new Color(0, 51, 153));
        backBtn.setForeground(Color.white);
    }//GEN-LAST:event_backBtnMouseExited

    private void backBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backBtnMouseClicked
        // TODO add your handling code here: 
        this.dispose();
    }//GEN-LAST:event_backBtnMouseClicked

    private void saveDiagnosisMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveDiagnosisMouseEntered
        // TODO add your handling code here:
        saveDiagnosis.setBackground(Color.white);
        saveDiagnosis.setForeground(new Color(0, 51, 153));
    }//GEN-LAST:event_saveDiagnosisMouseEntered

    private void saveDiagnosisMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveDiagnosisMouseExited
        // TODO add your handling code here:
        saveDiagnosis.setBackground(new Color(0, 51, 153));
        saveDiagnosis.setForeground(Color.white);
    }//GEN-LAST:event_saveDiagnosisMouseExited

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backBtn;
    private javax.swing.JPanel backPanel;
    private javax.swing.JLabel datelbl;
    private javax.swing.JLabel daterpl;
    private javax.swing.JLabel detlbl;
    private javax.swing.JPanel diagnosisArea;
    private javax.swing.JPanel diagnosisMain;
    private javax.swing.JPanel diagnosisMenu;
    private javax.swing.JLabel diagnosisTitle;
    private javax.swing.JLabel iconDiagnosis;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel namelbl;
    private javax.swing.JLabel namerpl;
    private javax.swing.JPanel patientDetDiag;
    private javax.swing.JLabel reasonlbl;
    private javax.swing.JLabel reasonrpl;
    private javax.swing.JButton saveDiagnosis;
    private javax.swing.JComboBox<String> specialtrtCombo;
    private javax.swing.JLabel specialtrtLbl;
    private javax.swing.JLabel surnamelbl;
    private javax.swing.JLabel surnamerpl;
    private javax.swing.JTextArea txtAreaDiagnosis;
    private javax.swing.JScrollPane txtPane;
    private javax.swing.JLabel writeTitle;
    // End of variables declaration//GEN-END:variables
}
