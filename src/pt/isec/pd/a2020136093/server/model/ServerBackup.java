package pt.isec.pd.a2020136093.server.model;

import pt.isec.pd.a2020136093.server.model.data.Heartbeat;
import pt.isec.pd.a2020136093.server.model.rmi.RMI_SERVER_INTERFACE;
import pt.isec.pd.a2020136093.server.threads.Multicast_ReadHearbeat;

import java.io.File;
import java.io.IOException;
import java.net.*;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import static pt.isec.pd.a2020136093.server.model.data.CONSTANTS.*;
import static pt.isec.pd.a2020136093.server.model.data.CONSTANTS.NETWORK_INTERFACE_NAME;

public class ServerBackup {

    private final String DB_BACKUP_PATH;
    Heartbeat serverData;

    public ServerBackup(String dbPath) {
        this.DB_BACKUP_PATH = dbPath;

    }

    public void start() {
        File db_directory = new File(DB_BACKUP_PATH.trim());
        serverData = new Heartbeat(null, 0, 0, null, 0);


        if(!db_directory.isDirectory() || !db_directory.exists()){
            System.out.println("Diretório da base de dados não existe ou não é válido!");
            System.exit(-1);
        }

        File[] files = db_directory.listFiles();
        if(files != null){
            if(files.length != 0){
                System.out.println("Diretório não está vazio!");
                System.exit(-1);
            }
        }


        // ================================= MULTICAST =================================
        MulticastSocket multicastSocket;
        try {
            InetAddress group = InetAddress.getByName(MULTICAST_IP);
            int port = MULTICAST_PORT;
            NetworkInterface nif;
            try {
                nif = NetworkInterface.getByInetAddress(InetAddress.getByName(NETWORK_INTERFACE_NAME));
            } catch (SocketException | UnknownHostException e) {
                nif = NetworkInterface.getByName(NETWORK_INTERFACE_NAME);
            }

            multicastSocket = new MulticastSocket(port);
            multicastSocket.joinGroup(new InetSocketAddress(group, port), nif);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        new Multicast_ReadHearbeat(multicastSocket, serverData).start();



        // ================================= RMI =================================
        do {
            try {
                Thread.sleep(1000);
                System.out.println("RMI_NAME: " + serverData.getRMI_NAME());

                String registration = "rmi://" + "localhost" + "/" + serverData.getRMI_NAME();
                RMI_SERVER_INTERFACE rmiServerInterface = (RMI_SERVER_INTERFACE) Naming.lookup(registration);

                rmiServerInterface.printHello();

            }catch (NotBoundException e) {
                System.out.println("No remoteTime service available!");
            } catch (RemoteException e) {
                System.out.println("RMI Error - " + e);
            } catch (Exception e) {
                System.out.println("Error - " + e);
            }

        }while(serverData.getRMI_NAME() == null);

    }

}
