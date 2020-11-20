/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.management.system;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import realtimecheckupsinterface.RealTimeCheckupsInterface;

/**
 *
 * @author Kaviraj
 */
public class rtImplCheckup extends UnicastRemoteObject implements RealTimeCheckupsInterface {
    
    
    public client_doctor doc1;

    public rtImplCheckup(client_doctor doc) throws RemoteException {
        super();
        this.doc1 = doc;
    }

    public void updateAvailable() throws RemoteException, UnknownHostException, IOException, SocketException, SocketException, SQLException, SQLException, NotBoundException {   
            doc1.refresh();
    }
}
