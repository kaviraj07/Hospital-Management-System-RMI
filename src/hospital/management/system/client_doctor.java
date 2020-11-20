/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.management.system;

import static hospital.management.system.environment.port;
import static hospital.management.system.environment.server_address;
import hospitalInterfaces.CheckupInterface;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Kaviraj
 */
public class client_doctor {

  
    public String doctor_id = "2";
    public JTable patientsTbl;

    //constructor
    public client_doctor(JTable patientsTbl) {
        this.patientsTbl = patientsTbl;
    }

    DefaultTableModel model;

    //updates table with values 
    public void refresh() throws SocketException, UnknownHostException, IOException, RemoteException, SQLException, NotBoundException {
        JSONObject request = new JSONObject();
        JSONObject docDet = new JSONObject();
        JSONArray reqArr = new JSONArray();

        try {
            Registry reg = LocateRegistry.getRegistry(server_address, port);
            CheckupInterface myPatients = (CheckupInterface) reg.lookup("CheckupService");

            String patients = myPatients.getCheckups("incomplete", Integer.parseInt(doctor_id));
            JSONArray receivedJson = new JSONArray(patients);
            JSONParser parser = new JSONParser();
            model.setRowCount(0);
            for (int i = 0; i < receivedJson.length(); i++) {
                JSONObject o = receivedJson.getJSONObject(i);

                String data[] = {
                    o.getString("checkup_id"),
                    o.getString("checkup_date"),
                    o.getString("patient_lname"),
                    o.getString("patient_fname"),
                    o.getString("checkup_reason"),
                    o.getString("checkup_status")

                };
                DefaultTableModel patientmdl = (DefaultTableModel) patientsTbl.getModel();
                patientmdl.addRow(data);
            }
        } catch (JSONException ex) {
            Logger.getLogger(myPatients.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //define table model
    public void setMyTable() {
        model = new DefaultTableModel();
        patientsTbl.setModel(model);
        model.addColumn("Checkup ID");
        model.addColumn("Date");
        model.addColumn("Surname");
        model.addColumn("Name");
        model.addColumn("Reason");
        model.addColumn("Status");
    }
}
