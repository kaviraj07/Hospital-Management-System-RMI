/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.management.system;

import static hospital.management.system.environment.port;
import static hospital.management.system.environment.server_address;
import hospitalInterfaces.SpecialTreatmentInterface;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Kaviraj
 */
public class entTreatment {
    
    public JTable patient_table;
    
    //constructor
    public entTreatment(JTable patient_tbl){
        patient_table = patient_tbl;
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
}
