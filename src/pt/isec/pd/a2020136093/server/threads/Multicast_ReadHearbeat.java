package pt.isec.pd.a2020136093.server.threads;

import pt.isec.pd.a2020136093.server.model.data.Heartbeat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

import static pt.isec.pd.a2020136093.server.model.data.CONSTANTS.TIMETOUT_SERVER_BACKUP;

public class Multicast_ReadHearbeat extends Thread {
    public static int MAX_SIZE = 1000;
    protected MulticastSocket s;
    protected Heartbeat serverData_backup;

    boolean timeout = false;
    int timeout_current_seconds = 0;

    public Multicast_ReadHearbeat(MulticastSocket s, Heartbeat serverData_backup){
        this.s = s;
        this.serverData_backup = serverData_backup;

        Thread timerThread = new Thread(() -> {
            while (true) {
                try {
                    ++timeout_current_seconds;

                    if (timeout_current_seconds == TIMETOUT_SERVER_BACKUP) {
                        System.out.println("Não foi recebido nenhum 'HEARBEAT' do servidor principal em 30 segundos!");
                        System.exit(-1);
                    }

                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        timerThread.start();
    }

    @Override
    public void run() {
        Object obj;
        DatagramPacket pkt;
        Heartbeat serverData;

        if (s == null)
            return;

        try {
            while (!timeout) {

                pkt = new DatagramPacket(new byte[MAX_SIZE], MAX_SIZE);
                s.receive(pkt);

                try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(pkt.getData(), 0, pkt.getLength()))) {

                    obj = in.readObject();

                    if (obj instanceof Heartbeat) {

                        serverData = (Heartbeat) obj;

                        System.out.println();
                        System.out.print("RECEIVED HEARTBEAT FROM " + serverData.getServerIP() + ":" + serverData.getServerPort());
                        System.out.println("\n[INFO]\n-> DatabaseVersion: " + serverData.getServerDBVersion() + " RMI_NAME: " + serverData.getRMI_NAME() + " RMI_PORT: " + serverData.getRMI_PORT());

                        serverData_backup.setRMI_NAME(serverData.getRMI_NAME());
                        serverData_backup.setRMI_PORT(serverData.getRMI_PORT());

                        timeout_current_seconds = 0;
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

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
