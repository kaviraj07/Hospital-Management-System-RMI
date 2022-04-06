package hospitalImplementations;

import static hospitalImplementations.environment.client_address_doc;
import static hospitalImplementations.environment.doctor_port;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import hospitalInterfaces.CheckupInterface;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import realtimecheckupsinterface.RealTimeCheckupsInterface;

public class CheckupImplementation extends UnicastRemoteObject implements CheckupInterface {

    protected Connection conn;

    protected CheckupImplementation(Connection conn) throws RemoteException {
        super();
        this.conn = conn;
    }

    @Override
    public boolean createCheckup(Integer patientId, Integer doctorid, String reason, String date) throws SQLException {
        String query = "Insert into checkup(patientid,doctorid,reason,diagnosis,status,date) values(?,?,?,?,?,?)";
        PreparedStatement preparedStmt = this.conn.prepareStatement(query);
        preparedStmt.setInt(1, patientId);
        preparedStmt.setInt(2, doctorid);
        preparedStmt.setString(3, reason);
        preparedStmt.setString(4, "");
        preparedStmt.setString(5, "Incomplete");
        preparedStmt.setString(6, date);

        int result = preparedStmt.executeUpdate();

        System.out.println("Checkup created: " + result);

        if (result == 1) {

            //real time implementation - server acts client and request from 
            Registry rrt;
            try {
                rrt = LocateRegistry.getRegistry(client_address_doc, doctor_port);
                RealTimeCheckupsInterface newCheck = (RealTimeCheckupsInterface) rrt
                        .lookup("RealTimeCheckupService");
                newCheck.updateAvailable();

            } catch (RemoteException | NotBoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException ex) {
                Logger.getLogger(CheckupImplementation.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return result == 1;
    }

    @Override
    public String getCheckups(String status, int doctorid) throws SQLException, JSONException {
        String query = "select c.*,p.id as pid , p.fname as pfname,p.lname as plname, p.phone as pphone, p.gender as pgender,d.id as did, d.fname as dfname, d.lname as dlname, d.gender as dgender,d.phone as dphone  from checkup c, patient p, users d where c.patientid = p.id and c.doctorid = d.id and d.departmentid = 2 and date >= CURDATE() and status LIKE ? and c.doctorid = ?";
        PreparedStatement preparedStmt = this.conn.prepareStatement(query);
        preparedStmt.setString(1, status);
        preparedStmt.setInt(2, doctorid);

        ResultSet checkups = preparedStmt.executeQuery();
        JSONArray checkup_details = new JSONArray();

        while (checkups.next()) {
            String id = checkups.getString("id");
            String pid = checkups.getString("pid");
            String pfname = checkups.getString("pfname");
            String plname = checkups.getString("plname");
            String pphone = checkups.getString("pphone");
            String pgender = checkups.getString("pgender");
            String did = checkups.getString("did");
            String dfname = checkups.getString("dfname");
            String dlname = checkups.getString("dlname");
            String dgender = checkups.getString("dgender");
            String dphone = checkups.getString("dphone");
            String c_status = checkups.getString("status");
            String c_reason = checkups.getString("reason");
            String c_date = checkups.getString("date");

            JSONObject checkup = new JSONObject();
            checkup.put("checkup_id", id);
            checkup.put("checkup_reason", c_reason);
            checkup.put("checkup_date", c_date);
            checkup.put("checkup_status", c_status);
            checkup.put("patient_id", pid);
            checkup.put("patient_fname", pfname);
            checkup.put("patient_lname", plname);
            checkup.put("patient_phone", pphone);
            checkup.put("patient_gender", pgender);

            checkup_details.put(checkup);
        }
        return checkup_details.toString();
    }

    @Override
    public boolean updateCheckup(int checkupid, String diagnosis, String status) throws SQLException {
        String query = "Update checkup set status = ? , diagnosis = ? where id = ?";
        PreparedStatement p5 = this.conn.prepareStatement(query);
        p5.setString(1, status);
        p5.setString(2, diagnosis);
        p5.setInt(3, checkupid);

        return p5.executeUpdate() == 1;

    }

}
