package pt.isec.pd.a2020136093.server.threads;

import pt.isec.pd.a2020136093.data.EventsData;
import pt.isec.pd.a2020136093.server.model.data.Heartbeat;
import pt.isec.pd.a2020136093.server.model.jdbc.ManageDB;
import pt.isec.pd.a2020136093.utils.REQUESTS;
import pt.isec.pd.a2020136093.utils.REQUEST_CLIENT_TO_SERVER;
import pt.isec.pd.a2020136093.utils.REQUEST_ADMIN_TO_SERVER;
import pt.isec.pd.a2020136093.utils.RESPONSE_SERVER_TO_CLIENT_OR_ADMIN;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.ArrayList;

import static pt.isec.pd.a2020136093.server.model.data.CONSTANTS.*;
import static pt.isec.pd.a2020136093.server.model.data.CONSTANTS.TIMEOUT_HEARTBEAT_MILLISECONDS;

public class ProcessClientRequest extends Thread {
    private Socket nextClient;
    ManageDB manageDB;
    Heartbeat serverData;

    public ProcessClientRequest(Socket nextClient, ManageDB manageDB, Heartbeat serverData) {
        this.nextClient = nextClient;
        this.manageDB = manageDB;
        this.serverData = serverData;

        try {
            nextClient.setSoTimeout(TIMEOUT_CLIENT_MILLISECONDS);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        System.out.println("Cliente -> " + nextClient.getInetAddress().getHostAddress() + ":" + nextClient.getPort() + " conectado");

        try (
                ObjectOutputStream oout = new ObjectOutputStream(nextClient.getOutputStream());
                ObjectInputStream oin = new ObjectInputStream(nextClient.getInputStream())
        ) {
            while (true) {

                REQUEST_CLIENT_TO_SERVER requestClientServer = null;
                REQUEST_ADMIN_TO_SERVER requestClientServerAdmin = null;

                Object receivedObject = oin.readObject();

                if (receivedObject instanceof REQUEST_CLIENT_TO_SERVER) {
                    requestClientServer = (REQUEST_CLIENT_TO_SERVER) receivedObject;
                } else if (receivedObject instanceof REQUEST_ADMIN_TO_SERVER) {
                    requestClientServerAdmin = (REQUEST_ADMIN_TO_SERVER) receivedObject;
                }

                RESPONSE_SERVER_TO_CLIENT_OR_ADMIN response = new RESPONSE_SERVER_TO_CLIENT_OR_ADMIN();

                // ADMIN
                if (requestClientServerAdmin != null) {
                    switch (requestClientServerAdmin.msgCode) {
                        case REQUESTS.ADMIN_REQUEST_CREATE_EVENT -> {
                            if (manageDB.addNewEvent(requestClientServerAdmin.name, requestClientServerAdmin.local, requestClientServerAdmin.date, requestClientServerAdmin.timeStart, requestClientServerAdmin.timeEnd)) {
                                manageDB.updateDB_version();
                                serverData.updateServerDBVersion(manageDB.getDB_version());
                                sendHearbeat_updatedDB();

                                response.response = "Evento criado com sucesso!";
                                response.resultado = true;
                                oout.writeObject(response);
                            } else {
                                response.response = "Evento não criado!";
                                response.resultado = false;
                                oout.writeObject(response);
                            }
                        }
                        case REQUESTS.ADMIN_REQUEST_EDIT_EVENT -> {
                            if (manageDB.editEvent(requestClientServerAdmin.id, requestClientServerAdmin.name, requestClientServerAdmin.local, requestClientServerAdmin.date, requestClientServerAdmin.timeStart, requestClientServerAdmin.timeEnd)) {
                                manageDB.updateDB_version();
                                serverData.updateServerDBVersion(manageDB.getDB_version());
                                sendHearbeat_updatedDB();

                                response.response = "Evento editado com sucesso!";
                                response.resultado = true;
                                oout.writeObject(response);
                            } else {
                                response.response = "Evento não editado!";
                                response.resultado = false;
                                oout.writeObject(response);
                            }
                        }
                        case REQUESTS.ADMIN_REQUEST_DELETE_EVENT -> {
                            if (manageDB.deleteEvent(requestClientServerAdmin.id)) {
                                manageDB.updateDB_version();
                                serverData.updateServerDBVersion(manageDB.getDB_version());
                                sendHearbeat_updatedDB();

                                response.response = "Evento eliminado com sucesso!";
                                response.resultado = true;
                                oout.writeObject(response);
                            } else {
                                response.response = "Evento não eliminado!";
                                response.resultado = false;
                                oout.writeObject(response);
                            }
                        }
                        case REQUESTS.ADMIN_REQUEST_CHECK_EVENTS -> {
                            ArrayList<ArrayList<String>> list = manageDB.checkEvents();
                            for (ArrayList<String> l : list) {
                                response.eventsList.addEvent(new EventsData(l.get(0), l.get(1), l.get(2), l.get(3), l.get(4), l.get(5), l.get(6), l.get(7)));
                            }

                            response.response = "LISTA DE EVENTOS";

                            oout.writeObject(response);
                        }
                        case REQUESTS.ADMIN_REQUEST_GENERATE_CODE -> {
                            if(manageDB.generateCode(requestClientServerAdmin.id, requestClientServerAdmin.eventCode)){
                                manageDB.updateDB_version();
                                serverData.updateServerDBVersion(manageDB.getDB_version());
                                sendHearbeat_updatedDB();

                                response.response = "Código gerado com sucesso!\n-> [" + requestClientServerAdmin.eventCode + "]";
                                response.resultado = true;
                                oout.writeObject(response);
                            }
                            else {
                                response.response = "Houve um erro ou código repetido!";
                                response.resultado = false;
                                oout.writeObject(response);
                            }
                        }
                        case REQUESTS.ADMIN_REQUEST_CHECK_PRESENCES_EVENT -> {
                            ArrayList<ArrayList<String>> presencesList = manageDB.checkPresencesEventID(requestClientServerAdmin.id);    // ID DO EVENTO

                            response.response = "LISTA DE PRESENÇAS NO EVENTO [ID]: "+ requestClientServerAdmin.id;
                            response.presencesADMIN = presencesList;


                            oout.writeObject(response);
                        }
                        case REQUESTS.ADMIN_REQUEST_DELETE_PRESENCE -> {
                            if(manageDB.deletePresence(requestClientServerAdmin.id, requestClientServerAdmin.emailToManagePresence)){
                                manageDB.updateDB_version();
                                serverData.updateServerDBVersion(manageDB.getDB_version());
                                sendHearbeat_updatedDB();

                                response.response = "Presença eliminada com sucesso!";
                                response.resultado = true;
                                oout.writeObject(response);
                            }
                            else {
                                response.response = "Presença não eliminada!";
                                response.resultado = false;
                                oout.writeObject(response);
                            }
                        }
                        case REQUESTS.ADMIN_REQUEST_ADD_PRESENCE -> {
                            if(manageDB.addPresence(requestClientServerAdmin.id, requestClientServerAdmin.emailToManagePresence)){
                                manageDB.updateDB_version();
                                serverData.updateServerDBVersion(manageDB.getDB_version());
                                sendHearbeat_updatedDB();

                                response.response = "Presença adicionada com sucesso!";
                                response.resultado = true;
                                oout.writeObject(response);
                            }
                            else {
                                response.response = "Presença já registada ou parâmetros inválidos!";
                                response.resultado = false;
                                oout.writeObject(response);
                            }
                        }
                        case REQUESTS.ADMIN_REQUEST_GENERATE_CSV_EVENT -> {
                            ArrayList<ArrayList<String>> presencesList = manageDB.checkPresencesEventID(requestClientServerAdmin.id);    // ID DO EVENTO
                            ArrayList<String> eventInfo = manageDB.checkEvent(requestClientServerAdmin.id);

                            generateCSV(presencesList, eventInfo);

                            response.response = "CSV gerado com sucesso!";
                            response.resultado = true;
                            oout.writeObject(response);
                        }
                        case REQUESTS.ADMIN_REQUEST_GENERATE_CSV_STUDENT -> {
                            ArrayList<ArrayList<String>> presencesList = manageDB.checkPresences(requestClientServerAdmin.emailToManagePresence);    // ID DO EVENTO
                            ArrayList<String> studentInfo = manageDB.checkStudent(requestClientServerAdmin.emailToManagePresence);

                            generateCSV2(presencesList, studentInfo);

                            response.response = "CSV gerado com sucesso!";
                            response.resultado = true;
                            oout.writeObject(response);
                        }
                    }
                }





                // ALUNO
                else if (requestClientServer != null) {

                    switch (requestClientServer.msgCode) {
                        case REQUESTS.CLIENT_REQUEST_LOGOUT -> {
                            response.response = "Utilizador deslogado com sucesso!";
                            response.resultado = true;
                            oout.writeObject(response);

                            nextClient.setSoTimeout(TIMEOUT_CLIENT_MILLISECONDS);
                        }

                        case REQUESTS.CLIENT_REQUEST_REGISTER -> {
                            if (manageDB.addNewUser(requestClientServer.name, requestClientServer.email, requestClientServer.password, requestClientServer.nIdentificacao)) {

                                // DISABLE SOCKET TIMEOUT
                                nextClient.setSoTimeout(0);

                                manageDB.updateDB_version();
                                serverData.updateServerDBVersion(manageDB.getDB_version());
                                sendHearbeat_updatedDB();

                                response.response = "Utilizador registado com sucesso!";
                                response.resultado = true;
                                oout.writeObject(response);

                                nextClient.setSoTimeout(TIMEOUT_CLIENT_MILLISECONDS);
                            } else {
                                response.response = "Utilizador já registado!";
                                response.resultado = false;
                                oout.writeObject(response);
                            }
                        }

                        case REQUESTS.CLIENT_REQUEST_LOGIN -> {
                            if (manageDB.login(requestClientServer.email, requestClientServer.password)) {

                                // DISABLE SOCKET TIMEOUT
                                nextClient.setSoTimeout(0);

                                response.response = "Bem-vindo " + requestClientServer.email + "!";
                                response.resultado = true;

                                ArrayList<String> list = manageDB.getClientData(requestClientServer.email);

                                response.clientData.addAll(list);

                                oout.writeObject(response);
                            } else {
                                response.response = "Credenciais invalidas!";
                                response.resultado = false;
                                oout.writeObject(response);
                            }
                        }

                        case REQUESTS.CLIENT_REQUEST_EDIT_DATA -> {
                            if (manageDB.editData(requestClientServer.novoEmail, requestClientServer.email, requestClientServer.password, requestClientServer.name, requestClientServer.nIdentificacao) != null) {
                                manageDB.updateDB_version();
                                serverData.updateServerDBVersion(manageDB.getDB_version());
                                sendHearbeat_updatedDB();

                                response.response = "Dados alterados com sucesso!";
                                response.resultado = true;

                                ArrayList<String> list = manageDB.getClientData(requestClientServer.email);

                                response.clientData.addAll(list);

                                oout.writeObject(response);
                            } else {
                                response.response = "Dados não alterados!";
                                response.resultado = false;
                                oout.writeObject(response);
                            }
                        }
                        case REQUESTS.CLIENT_REQUEST_SUBMIT_CODE -> {
                            if (manageDB.submitCode(requestClientServer.email, requestClientServer.eventCode)) {
                                manageDB.updateDB_version();
                                serverData.updateServerDBVersion(manageDB.getDB_version());
                                sendHearbeat_updatedDB();

                                response.response = "Código submetido com sucesso!";
                                response.resultado = true;
                                oout.writeObject(response);
                            }
                            else {
                                response.response = "Presença já registada ou código inválido!";
                                response.resultado = false;
                                oout.writeObject(response);
                            }
                        }
                        case REQUESTS.CLIENT_REQUEST_CHECK_PRESENCES -> {
                            ArrayList<ArrayList<String>> list = manageDB.checkPresences(requestClientServer.email);

                            response.clientEventsData = list;

                            oout.writeObject(response);
                        }
                        case REQUESTS.CLIENT_REQUEST_GENERATE_CSV_STUDENT -> {
                            ArrayList<ArrayList<String>> presencesList = manageDB.checkPresences(requestClientServer.email);    // ID DO EVENTO
                            ArrayList<String> studentInfo = manageDB.checkStudent(requestClientServer.email);

                            generateCSV2(presencesList, studentInfo);

                            response.response = "CSV gerado com sucesso!";
                            response.resultado = true;
                            oout.writeObject(response);
                        }
                    }
                }
            }
        }
        catch (SocketTimeoutException e) {  // FECHA O SOCKET AUTOMATICAMENTE
            System.out.println("Cliente -> " + nextClient.getInetAddress().getHostAddress() + ":" + nextClient.getPort() + " inativo por 10 segundos... A conexão será fechada!");
        }
        catch (ClassNotFoundException | IOException | SQLException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }



    public void sendHearbeat_updatedDB(){
        DatagramSocket datagramSocket;

        try {
            datagramSocket = new DatagramSocket();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);

            oos.writeObject(serverData);

            byte[] msgBytes = baos.toByteArray();

            InetAddress ipServer = InetAddress.getByName(MULTICAST_IP);

            DatagramPacket dpSend = new DatagramPacket(
                    msgBytes,
                    msgBytes.length,
                    ipServer,
                    MULTICAST_PORT
            );

            datagramSocket.send(dpSend);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public void generateCSV(ArrayList<ArrayList<String>> presencesList, ArrayList<String> eventInfo){

        String csvFile = CSV_FILES_PATH + "/" + eventInfo.get(0) + ".csv";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
            bw.write("\"Designação\";\"" + eventInfo.get(0) + "\"\n");
            bw.write("\"Local\";\"" + eventInfo.get(1) + "\"\n");
            bw.write("\"Data\";\"" + eventInfo.get(2) + "\"\n");
            bw.write("\"Hora início\";\"" + eventInfo.get(3) + "\"\n");
            bw.write("\"Hora fim\";\"" + eventInfo.get(4) + "\"\n");
            bw.write("\"Nome\";\"Número identificação\";\"Email\"\n");

            for (ArrayList<String> l : presencesList) {
                bw.write("\"" + l.get(0) + "\";\"" + l.get(1) + "\";\"" + l.get(2) + "\"\n");
            }

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void generateCSV2(ArrayList<ArrayList<String>> presencesList, ArrayList<String> studentInfo){

        String csvFile = CSV_FILES_PATH + "/" + studentInfo.get(0) + ".csv";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
            bw.write("\"Nome\";\"Número identificação\";\"Email\"\n");

            bw.write("\"" + studentInfo.get(0) + "\";\"" + studentInfo.get(2) + "\";\"" + studentInfo.get(1) + "\"\n");


            bw.write("\"Designação\";\"Local\";\"Data\";\"Hora início\"\n");
            for (ArrayList<String> l : presencesList) {
                bw.write("\"" + l.get(1) + "\";\"" + l.get(2) + "\";\"" + l.get(3) + "\";\"" + l.get(4) + "\"\n");
            }


            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
