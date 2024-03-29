package hospital.management.system;

import static hospital.management.system.environment.client_address_ent;
import static hospital.management.system.environment.ent_port;
import static hospital.management.system.environment.port;
import static hospital.management.system.environment.server_address;
import hospitalInterfaces.SpecialTreatmentInterface;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Evans Henri
 */
public class ListOfENTPatients extends javax.swing.JFrame {

    String patientID;
    String specialTreatmentID;
    DatagramSocket clientSocket;
    InetAddress IPAddress;

    public entTreatment lent;

    /**
     * Creates new form Checkup
     *
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public ListOfENTPatients() throws ClassNotFoundException, SQLException, JSONException, IOException {

        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getPatients();
        entTreatment en = new entTreatment(patient_table);
        lent = en;

        int PORT = ent_port;
        System.setProperty("java.rmi.server.hostname", client_address_ent);
        Registry reg = LocateRegistry.createRegistry(PORT);

        rtImplSt p = new rtImplSt(lent);

        reg.rebind("rtST", p);
        System.out.println("Server from ent running ! ");

        /* Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                getPatients();

            }
        }, 0, 1000);
         */
    }

    public void getPatients() {
        DefaultTableModel model = (DefaultTableModel) patient_table.getModel();
        model.setRowCount(0);
        try {

            Registry reg = LocateRegistry.getRegistry(server_address, port);
            SpecialTreatmentInterface myPatients = (SpecialTreatmentInterface) reg.lookup("SpecialTreatmentService");

            String serverResponse = myPatients.getPatientsForSpecialTreatment(1, "Incomplete");
            JSONArray responseArr = new JSONArray(serverResponse);

            //displaying data on UI
            Object[] row;

            for (int i = 0; i < responseArr.length(); i++) {

                row = new Object[7];
                JSONObject o = responseArr.getJSONObject(i);
                row[0] = o.getString("fname");
                row[1] = o.getString("lname");
                row[2] = o.getString("address");
                row[3] = o.getString("gender");
                row[4] = o.getString("phone");
                row[5] = o.getString("dob");
                row[6] = o.getString("specialtreatment_id");
                model.addRow(row);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        patient_table = new javax.swing.JTable();
        cancel_btn = new javax.swing.JButton();
        editPatient_button = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 600));
        jPanel1.setName(""); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 600));
        jPanel1.setLayout(null);

        patient_table.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        patient_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First name", "Last name", "Address", "Gender", "Phone number", "DOB", "STID"
            }
        ));
        patient_table.setRowHeight(70);
        patient_table.setRowMargin(10);
        patient_table.setSelectionBackground(new java.awt.Color(153, 153, 153));
        jScrollPane1.setViewportView(patient_table);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(0, 120, 1390, 450);

        cancel_btn.setText("Cancel");
        cancel_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_btnActionPerformed(evt);
            }
        });
        jPanel1.add(cancel_btn);
        cancel_btn.setBounds(740, 610, 140, 40);

        editPatient_button.setText("Edit Patient");
        editPatient_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPatient_buttonActionPerformed(evt);
            }
        });
        jPanel1.add(editPatient_button);
        editPatient_button.setBounds(480, 610, 140, 40);

        jLabel2.setBackground(new java.awt.Color(0, 51, 153));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ENT Department");
        jLabel2.setOpaque(true);
        jPanel1.add(jLabel2);
        jLabel2.setBounds(0, 70, 1390, 40);

        jLabel3.setBackground(new java.awt.Color(0, 51, 153));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Hospital Management System");
        jLabel3.setOpaque(true);
        jPanel1.add(jLabel3);
        jLabel3.setBounds(0, 0, 1390, 60);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture_icon/checkupbgedit.jpg"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(160, 240, 1000, 530);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1389, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void editPatient_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPatient_buttonActionPerformed
        // TODO add your handling code here:

        DefaultTableModel model = (DefaultTableModel) patient_table.getModel();
        int row = patient_table.getSelectedRow();
        String selectedPatientFname = model.getValueAt(row, 0).toString();
        String selectedPatientLname = model.getValueAt(row, 1).toString();
        String selectedPatientID = this.patientID;
        String selectedPatientSpecialTreatmentID = model.getValueAt(row, 6).toString();

        ENTPatient object;
        try {
            object = new ENTPatient(selectedPatientFname, selectedPatientLname, selectedPatientID, selectedPatientSpecialTreatmentID);
            object.setVisible(true);

        } catch (ParseException | JSONException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ListOfENTPatients.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_editPatient_buttonActionPerformed

    private void cancel_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_btnActionPerformed
        // TODO add your handling code here:
        this.dispose();

    }//GEN-LAST:event_cancel_btnActionPerformed

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
            java.util.logging.Logger.getLogger(ListOfENTPatients.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListOfENTPatients.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListOfENTPatients.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListOfENTPatients.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new ListOfENTPatients().setVisible(true);

                } catch (IOException ex) {
                    Logger.getLogger(ListOfENTPatients.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ListOfENTPatients.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ListOfENTPatients.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JSONException ex) {
                    Logger.getLogger(ListOfENTPatients.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel_btn;
    private javax.swing.JButton editPatient_button;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable patient_table;
    // End of variables declaration//GEN-END:variables
}
