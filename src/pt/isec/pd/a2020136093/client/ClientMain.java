package pt.isec.pd.a2020136093.client;

import javafx.application.Application;
import pt.isec.pd.a2020136093.client.communication.ManageConnections;
import pt.isec.pd.a2020136093.client.rmi.RMI_CLIENT;
import pt.isec.pd.a2020136093.client.ui.gui.MainJFX;
import pt.isec.pd.a2020136093.client.ui.text.ClientUI;
import pt.isec.pd.a2020136093.server.model.rmi.RMI_SERVER_INTERFACE;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientMain {
    public static ManageConnections mc;
    public static void main(String[] args) {
        boolean useGUI = true;


        if(args.length != 2){
            System.err.println("Invalid number of arguments");
            System.exit(-1);
        }

        final String SERVER_IP = args[0];
        final int SERVER_PORT = Integer.parseInt(args[1]);

        mc = new ManageConnections(SERVER_IP, SERVER_PORT);

        if(useGUI) {
            Application.launch(MainJFX.class, args);
        }
        else {
            ClientUI ui = new ClientUI(mc);
            ui.start();
        }

    }
}
