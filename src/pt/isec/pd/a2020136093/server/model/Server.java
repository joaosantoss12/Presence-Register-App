package pt.isec.pd.a2020136093.server.model;

import pt.isec.pd.a2020136093.server.model.data.Heartbeat;
import pt.isec.pd.a2020136093.server.threads.Multicast_SendHeartbeat;
import pt.isec.pd.a2020136093.server.threads.ProcessClientRequest;
import pt.isec.pd.a2020136093.server.model.jdbc.ManageDB;
import pt.isec.pd.a2020136093.server.threads.Multicast_ReadHearbeat;

import java.io.*;
import java.net.*;

import static pt.isec.pd.a2020136093.server.model.data.CONSTANTS.*;

public class Server {
    ManageDB manageDB;
    private final int PORT;
    private final String DB_PATH;
    private final String RMI_NAME;
    private final int RMI_PORT;

    Heartbeat serverData;

    public Server(int port, String dbPath, String rmiName, int rmiPort) {
        this.PORT = port;
        this.DB_PATH = dbPath;
        this.RMI_NAME = rmiName;
        this.RMI_PORT = rmiPort;

        manageDB = new ManageDB(DB_PATH);

        if (manageDB.connectDB()) {
            System.out.println("Conectado à base de dados");
            try {
                manageDB.createDB();
                serverData = new Heartbeat(InetAddress.getLocalHost().getHostAddress(), PORT, manageDB.getDB_version(), RMI_NAME, RMI_PORT);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            System.out.println("Não foi possível conectar à base de dados");
            System.exit(-1);
        }
    }

    public void start() {

        // MULTICAST
        MulticastSocket multicastSocket = null;
        try {
            InetAddress group = InetAddress.getByName(MULTICAST_IP);
            int port = MULTICAST_PORT;
            NetworkInterface nif = null;
            try{
                nif = NetworkInterface.getByInetAddress(InetAddress.getByName(NETWORK_INTERFACE_NAME));
            } catch (SocketException | UnknownHostException e) {
                nif = NetworkInterface.getByName(NETWORK_INTERFACE_NAME);
            }

            multicastSocket = new MulticastSocket(port);
            multicastSocket.joinGroup(new InetSocketAddress(group, port), nif);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        new Multicast_ReadHearbeat(multicastSocket).start();     // TESTE PARA LER MULTICAST
        new Multicast_SendHeartbeat(serverData).start();    // ENVIAR HEARBEAT PARA MULTICAST



        try (ServerSocket socket = new ServerSocket(PORT)) {

            System.out.println("Server: " + InetAddress.getLocalHost().getHostAddress() + " iniciado na porta " + PORT);

            while (true) {
                Socket nextClient = socket.accept();
                new ProcessClientRequest(nextClient, manageDB, serverData).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
