package hospitalImplementations;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import hospitalInterfaces.PatientInterface;

public class PatientImplementation extends UnicastRemoteObject implements PatientInterface {
    protected Connection conn;

    protected PatientImplementation(Connection conn) throws RemoteException {
        super();
        this.conn = conn;
    }

    @Override
    public String getAllPatients() throws SQLException, JSONException {
        JSONArray patients_details = new JSONArray();

        String query = "select * from patient";
        PreparedStatement preparedStmt = this.conn.prepareStatement(query);
        ResultSet patients = preparedStmt.executeQuery();

        while (patients.next()) {
            String id = patients.getString("id");
            String fname = patients.getString("fname");
            String lname = patients.getString("lname");
            String phone = patients.getString("phone");
            String address = patients.getString("address");
            String dob = patients.getString("dob");
            String gender = patients.getString("gender");

            JSONObject patient = new JSONObject();
            patient.put("id", id);
            patient.put("fname", fname);
            patient.put("lname", lname);
            patient.put("phone", phone);
            patient.put("address", address);
            patient.put("dob", dob);
            patient.put("gender", gender);
            patients_details.put(patient);
        }

        return patients_details.toString();
    }

    @Override
    public boolean createPatient(String fname, String lname, String phone, String address, String dob, String gender)
            throws SQLException {
        String query = "Insert into patient (fname,lname,phone,address,dob,gender) values(?,?,?,?,?,?)";
        PreparedStatement preparedStmt = this.conn.prepareStatement(query);
        preparedStmt.setString(1, fname);
        preparedStmt.setString(2, lname);
        preparedStmt.setString(3, phone);
        preparedStmt.setString(4, address);
        preparedStmt.setString(5, dob);
        preparedStmt.setString(6, gender);

        int result = preparedStmt.executeUpdate();

        System.out.println("Patient added : "+ result);


        return result == 1;
    }
}
