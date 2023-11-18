package pt.isec.pd.a2020136093.server.model.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMI_SERVER extends UnicastRemoteObject implements RMI_SERVER_INTERFACE{
    public RMI_SERVER() throws RemoteException {

    }

    @Override
    public void printHello() throws RemoteException{
        System.out.println("Hello!");
    }
}
