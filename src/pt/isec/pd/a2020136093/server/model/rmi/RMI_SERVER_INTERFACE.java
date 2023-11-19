package pt.isec.pd.a2020136093.server.model.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMI_SERVER_INTERFACE extends Remote {

    byte[] getDatabaseCopy_chunk(long offset) throws RemoteException;

}
