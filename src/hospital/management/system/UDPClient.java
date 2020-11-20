package hospital.management.system;

import static hospital.management.system.environment.port;
import static hospital.management.system.environment.server_address;
import hospitalInterfaces.PatientInterface;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import org.json.JSONException;

public class UDPClient {

    String fname, lname, address, phone, ID, DOB, gender;

    public UDPClient() {

    }

    public UDPClient(String fname, String lname, String phoneNum, String address, String patientID, String DOB, String gender) {
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.phone = phoneNum;
        this.DOB = DOB;
        this.gender = gender;

    }

    public void sendData() throws JSONException, RemoteException, SQLException, NotBoundException {
        try {
            Registry r = LocateRegistry.getRegistry(server_address, port);
            String result = "";
            String result1 = "";
            Boolean result2 = false;
            PatientInterface patient = (PatientInterface) r.lookup("PatientService");
            result = patient.getAllPatients();
            result2 = patient.createPatient(fname, lname, phone, address, DOB, gender);

            /*
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName(server_address);
            byte[] send_Data = new byte[1024];
            byte[] receive_Data = new byte[1024];

            JSONObject clientJson = new JSONObject();

            clientJson.put("fname", fname);
            clientJson.put("lname", lname);
            clientJson.put("phone", phone);
            clientJson.put("address", address);
            clientJson.put("dob", DOB);
            clientJson.put("gender", gender);

            JSONArray clientJsonArr = new JSONArray();
            clientJsonArr.put(clientJson);

            JSONObject finalObject = new JSONObject();
            finalObject.put("action", "add_patient");
            finalObject.put("data", clientJsonArr);
            String clientString = finalObject.toString();
            Arrays.fill(send_Data, (byte) 0);
            send_Data = clientString.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(send_Data, send_Data.length, IPAddress, port);
            clientSocket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receive_Data, receive_Data.length);
            clientSocket.receive(receivePacket);
            String serverResponse = new String(receivePacket.getData());

            System.out.print("RESPONSE FROM SERVER: " + serverResponse);
            clientSocket.close();

             */
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
