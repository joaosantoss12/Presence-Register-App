package pt.isec.pd.a2020136093.client.threads;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ClientThread_CheckTCPConnection extends Thread {
    private Socket socket;

    public ClientThread_CheckTCPConnection(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {         // UNICA FORMA QUE ENCONTREI PARA O CLIENTE SABER SE O SERVIDOR FECHOU A CONEXÃO INSTANTANEAMENTE
        while (true) {          // PODIA SER TAMBEM NA PROXIMA REQUEST DO CLIENTE, DA UM EXCEPTION PORQUE JA NAO EXISTE CONEXAO
            try {
                InputStream inputStream = socket.getInputStream();
                byte[] buffer = new byte[1024];
                while (true) {
                    int bytesRead = inputStream.read(buffer);
                    if (bytesRead == -1) {
                        System.out.println("\nO servidor fechou a conexão por inatividade!");
                        System.exit(-1);
                    }
                    Thread.sleep(1000);
                }
            }catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}