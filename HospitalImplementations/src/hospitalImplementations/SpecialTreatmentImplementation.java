package hospitalImplementations;

import static hospitalImplementations.environment.client_address_ent;
import static hospitalImplementations.environment.ent_port;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import hospitalInterfaces.SpecialTreatmentInterface;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import realtimestinterface.RealTimeSTInterface;

public class SpecialTreatmentImplementation extends UnicastRemoteObject implements SpecialTreatmentInterface {

    protected Connection conn;

    protected SpecialTreatmentImplementation(Connection conn) throws RemoteException {
        super();
        this.conn = conn;

    }

    @Override
    public String getPatientsForSpecialTreatment(int dept_id, String status) throws SQLException, JSONException {
        JSONArray st_details = new JSONArray();

        String query = "select st.id as stid ,p.*,st.* from specialtreatment st, checkup c, patient p where st.checkupid = c.id and c.patientid = p.id and st.departmentid = ? and st.date >= CURDATE() and st.status LIKE ?";
        PreparedStatement preparedStmt = this.conn.prepareStatement(query);
        preparedStmt.setInt(1, dept_id);
        preparedStmt.setString(2, status);
        ResultSet st = preparedStmt.executeQuery();

        while (st.next()) {
            String pid = st.getString("id");
            String stid = st.getString("stid");
            String fname = st.getString("fname");
            String lname = st.getString("lname");
            String phone = st.getString("phone");
            String address = st.getString("address");
            String dob = st.getString("dob");
            String gender = st.getString("gender");

            JSONObject patient = new JSONObject();
            patient.put("specialtreatment_id", stid);
            patient.put("patient_id", pid);
            patient.put("fname", fname);
            patient.put("lname", lname);
            patient.put("phone", phone);
            patient.put("address", address);
            patient.put("dob", dob);
            patient.put("gender", gender);
            st_details.put(patient);
        }

        return st_details.toString();
    }

    @Override
    public String getCheckupForSpecialTreatment(int specialtreatment_id) throws SQLException, JSONException {
        JSONArray st_details = new JSONArray();

        String query = "select st.id as specialtreatment_id, c.patientid as patientid, c.date as checkup_date, c.diagnosis from specialtreatment st, checkup c where st.checkupid = c.id and st.id = ?";
        PreparedStatement preparedStmt = this.conn.prepareStatement(query);
        preparedStmt.setInt(1, specialtreatment_id);
        ResultSet st = preparedStmt.executeQuery();

        while (st.next()) {
            String specialtreatment_id_table = st.getString("specialtreatment_id");
            String patientid = st.getString("patientid");
            String diagnosis = st.getString("diagnosis");
            String checkup_date = st.getString("checkup_date");

            JSONObject st_json = new JSONObject();
            st_json.put("patient_id", patientid);
            st_json.put("specialtreatment_id", specialtreatment_id_table);
            st_json.put("diagnosis", diagnosis);
            st_json.put("checkup_date", checkup_date);
            st_details.put(st_json);
        }

        return st_details.toString();
    }

    @Override
    public boolean createSpecialTreatment(int checkupid, String date, int departmentid) throws SQLException, AccessException {
        String query = "Insert into specialtreatment (checkupid,date,status,departmentid) values(?,?,?,?)";
        PreparedStatement preparedStmt = this.conn.prepareStatement(query);
        preparedStmt.setInt(1, checkupid);
        preparedStmt.setString(2, date);
        preparedStmt.setString(3, "Incomplete");
        preparedStmt.setInt(4, departmentid);

        int result = preparedStmt.executeUpdate();

        //real time implementation - server acts as client
        if (result == 1) {         
            Registry rt;
            try {
                rt = LocateRegistry.getRegistry(client_address_ent, ent_port);
                RealTimeSTInterface rtInter = (RealTimeSTInterface) rt.lookup("rtST");
                rtInter.newTreatment();

            } catch (RemoteException | NotBoundException e) {
                e.printStackTrace();
            }
        }

        return result == 1;

    }

    @Override
    public boolean updateSpecialTreatment(int specialtreatment_id, String givenTreatment, int specialist_id,
            String status) throws SQLException {
        String query = "Update specialtreatment set giventreatment = ?, specialistid = ? ,status = ? where id = ?";
        PreparedStatement preparedStmt = this.conn.prepareStatement(query);
        preparedStmt.setString(1, givenTreatment);
        preparedStmt.setInt(2, specialist_id);
        preparedStmt.setString(3, status);
        preparedStmt.setInt(4, specialtreatment_id);

        return preparedStmt.executeUpdate() == 1;
    }
}
