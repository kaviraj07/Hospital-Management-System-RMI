/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.management.system;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import realtimestinterface.RealTimeSTInterface;

/**
 *
 * @author Kaviraj
 */
public class rtImplSt extends UnicastRemoteObject implements RealTimeSTInterface {

    public entTreatment y;

    public rtImplSt(entTreatment x) throws RemoteException {
        super();
        this.y = x;
    }

    @Override
    public void newTreatment() {
        y.getPatients();
    }

}
