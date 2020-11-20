package hospital.management.system;

import hospitalInterfaces.CheckupInterface;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import org.json.JSONException;

public class send_checkup {

    public String server_address = environment.server_address;
    public int port = environment.port;
    int patient_id;
    int doctor_id;
    String reason;
    String date;

    public send_checkup(String patient_id, String doctor_id, String reason, String date) {
        this.patient_id = Integer.parseInt(patient_id);
        this.doctor_id = Integer.parseInt(doctor_id);
        this.reason = reason;
        this.date = date;
    }

    public void send_patientData() throws JSONException, RemoteException, NotBoundException, SQLException {
        try {
            Registry reg = LocateRegistry.getRegistry(server_address, port);
            CheckupInterface myPatients = (CheckupInterface) reg.lookup("CheckupService");

            boolean patients = myPatients.createCheckup(patient_id, doctor_id, reason, date);

            System.out.print("RESPONSE FROM SERVER: saved");
            
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
