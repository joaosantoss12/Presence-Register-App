package pt.isec.pd.a2020136093.client;

import pt.isec.pd.a2020136093.client.communication.ManageConnections;
import pt.isec.pd.a2020136093.client.ui.text.ClientUI;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMain {
    private static final Boolean useGUI = false;

    public static void main(String[] args) {

        if(args.length != 2){
            System.err.println("Invalid number of arguments");
            System.exit(-1);
        }

        final String SERVER_IP = args[0];
        final int SERVER_PORT = Integer.parseInt(args[1]);


        if(useGUI) {

        }
        else {
            ManageConnections mc = new ManageConnections(SERVER_IP, SERVER_PORT);
            ClientUI ui = new ClientUI(mc);
            ui.start();
        }

    }
}
