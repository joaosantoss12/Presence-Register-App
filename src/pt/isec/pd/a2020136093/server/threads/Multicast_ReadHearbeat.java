package pt.isec.pd.a2020136093.server.threads;

import pt.isec.pd.a2020136093.server.model.data.Heartbeat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

public class Multicast_ReadHearbeat extends Thread {
    public static int MAX_SIZE = 1000;
    protected MulticastSocket s;

    public Multicast_ReadHearbeat(MulticastSocket s) {
        this.s = s;
    }

    @Override
    public void run() {
        Object obj;
        DatagramPacket pkt;
        Heartbeat serverData;

        if (s == null)
            return;

        try {

            while (true) {

                pkt = new DatagramPacket(new byte[MAX_SIZE], MAX_SIZE);
                s.receive(pkt);

                try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(pkt.getData(), 0, pkt.getLength()))) {

                    obj = in.readObject();

                    if (obj instanceof Heartbeat) {

                        serverData = (Heartbeat) obj;

                        System.out.println();
                        System.out.print("RECEIVED HEARTBEAT FROM " + serverData.getServerIP() + ":" + serverData.getServerPort());
                        System.out.println("\n[INFO]\n-> DatabaseVersion: " + serverData.getServerDBVersion() + " RMI_NAME: " + serverData.getRMI_NAME() + " RMI_PORT: " + serverData.getRMI_PORT());

                    }
                    else if (obj instanceof String) {

                        System.out.println((String) obj + " (" + obj.getClass() + ")");
                    }

                } catch (ClassNotFoundException e) {
                    System.out.println();
                    System.out.println("Mensagem recebida de tipo inesperado! " + e);
                } catch (IOException e) {
                    System.out.println();
                    System.out.println("Impossibilidade de aceder ao conteudo da mensagem recebida! " + e);
                } catch (Exception e) {
                    System.out.println();
                    System.out.println("Excepcao: " + e);
                }
            }
        } catch(IOException e) {

        }
    }
}
