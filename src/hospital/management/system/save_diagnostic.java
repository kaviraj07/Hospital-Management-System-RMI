/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.management.system;

import hospitalInterfaces.CheckupInterface;
import hospitalInterfaces.SpecialTreatmentInterface;
import java.io.IOException;
import java.net.DatagramSocket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Kaviraj
 */
public class save_diagnostic {

    public JComboBox<String> specialtrtCombo;
    public JTextArea txtAreaDiagnosis;

    public String server_address = environment.server_address;
    public int port = environment.port;

    public String cid = "";

    //constructor
    public save_diagnostic(JTextArea txtAreaDiagnosis, JComboBox<String> specialtrtCombo, String cid) {
        this.txtAreaDiagnosis = txtAreaDiagnosis;
        this.specialtrtCombo = specialtrtCombo;
        this.cid = cid;
    }

    //used to check for empty diagnostic
    public boolean checkDiag() {
        String mydiagnosis = txtAreaDiagnosis.getText().trim();
        if (mydiagnosis.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter a diagnosis please !");
            return false;
        }
        return true;
    }

    //used to update the checkup status only
    public void updateChkUp() throws RemoteException, NotBoundException, SQLException {
        // change status and diagnosis of a checkup
        String mydiagnosis = txtAreaDiagnosis.getText().trim();
        JSONObject dataObj = new JSONObject();
        JSONObject serviceObj = new JSONObject();
        JSONArray dataArr = new JSONArray();

        try {
            dataObj.put("checkupid", cid);
            dataObj.put("status", "complete");
            dataObj.put("diagnosis", mydiagnosis);
            dataArr.put(dataObj);

            serviceObj.put("action", "update_checkup");
            serviceObj.put("data", dataArr);

        } catch (JSONException ex) {
            Logger.getLogger(diagnosticScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Registry reg = LocateRegistry.getRegistry(server_address, port);
            CheckupInterface myPatients = (CheckupInterface) reg.lookup("CheckupService");

            boolean patients = myPatients.updateCheckup(Integer.parseInt(cid), mydiagnosis, "complete");

        } catch (IOException ex) {
            Logger.getLogger(diagnosticScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //used to add the special treatment only
    public void addTreatment() throws RemoteException, NotBoundException, SQLException {
        String spTrt = specialtrtCombo.getSelectedItem().toString();
        String deptId = "";

        if (!spTrt.equals(
                "None")) {

            switch (spTrt) {
                case "ENT":
                    deptId = "1";
                    break;
                case "X-Ray":
                    deptId = "1";
                    break;
                case "ICU":
                    deptId = "1";
                    break;
                case "MRI":
                    deptId = "1";
                    break;

            }

            JSONObject infoObj = new JSONObject();
            JSONObject sendObj = new JSONObject();
            JSONArray infoArr = new JSONArray();

            //get the actual date and convert tostring
            LocalDate myDate = LocalDate.now();
            String myDateStr = myDate.toString();

            DatagramSocket clientSocket;

            try {
                Registry reg = LocateRegistry.getRegistry(server_address, port);
                SpecialTreatmentInterface myPatients = (SpecialTreatmentInterface) reg.lookup("SpecialTreatmentService");

                boolean patients = myPatients.createSpecialTreatment(Integer.parseInt(cid), myDateStr, Integer.parseInt(deptId));

              
            } catch (IOException ex) {
                Logger.getLogger(diagnosticScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
