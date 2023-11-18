package pt.isec.pd.a2020136093.client.communication;

import pt.isec.pd.a2020136093.utils.REQUESTS;
import pt.isec.pd.a2020136093.utils.REQUEST_CLIENT_TO_SERVER;
import pt.isec.pd.a2020136093.utils.REQUEST_ADMIN_TO_SERVER;
import pt.isec.pd.a2020136093.utils.RESPONSE_SERVER_TO_CLIENT_OR_ADMIN;
import pt.isec.pd.a2020136093.client.data.ClientData;
import pt.isec.pd.a2020136093.data.EventsList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class ManageConnections {
    private static final int TIMEOUT = 10;
    private String serverIP;
    private int serverPort;
    private Socket socket;
    private ObjectInputStream oin;
    private ObjectOutputStream oout;

    private ClientData clientData;
    private EventsList eventsList;

    public ManageConnections(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        clientData = new ClientData();

        establishConnection();
    }
    public void establishConnection(){
        try {
            socket = new Socket(InetAddress.getByName(serverIP), serverPort);
            oin = new ObjectInputStream(socket.getInputStream());
            oout = new ObjectOutputStream(socket.getOutputStream());

            //new ClientThread_CheckTCPConnection(socket).start();  DA ERRO POIS ACEDE AO SOCKET TBM
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void register(String email, String password, String name, String nIdentificacao) {
        REQUEST_CLIENT_TO_SERVER msg = new REQUEST_CLIENT_TO_SERVER();

        msg.msgCode = REQUESTS.CLIENT_REQUEST_REGISTER;
        msg.email = email;
        msg.password = password;
        msg.name = name;
        msg.nIdentificacao = nIdentificacao;

        try{

            //Serializa a string TIME_REQUEST para o OutputStream associado a socket
            oout.writeObject(msg);
            oout.flush();

            //Deserializa a resposta recebida em socket
            RESPONSE_SERVER_TO_CLIENT_OR_ADMIN response = (RESPONSE_SERVER_TO_CLIENT_OR_ADMIN) oin.readObject();

            if (response == null) {
                System.out.println("O servidor nao enviou qualquer respota antes de"
                        + " fechar aligacao TCP!");
            } else {
                System.out.println(response.response);
            }

        } catch (Exception e) {
            System.out.println("Ocorreu um erro no acesso ao socket:\n\t" + e);
        }
    }

    public boolean login(String email, String password) {
        REQUEST_CLIENT_TO_SERVER msg = new REQUEST_CLIENT_TO_SERVER();

        msg.msgCode = REQUESTS.CLIENT_REQUEST_LOGIN;
        msg.email = email;
        msg.password = password;

        try{

            //Serializa a string TIME_REQUEST para o OutputStream associado a socket
            oout.writeObject(msg);
            oout.flush();

            //Deserializa a resposta recebida em socket
            RESPONSE_SERVER_TO_CLIENT_OR_ADMIN response = (RESPONSE_SERVER_TO_CLIENT_OR_ADMIN) oin.readObject();

            if (response == null) {
                System.out.println("O servidor nao enviou qualquer respota antes de"
                        + " fechar aligacao TCP!");
            }
            else {
                System.out.println(response.response);
            }

            if(response.resultado) {
                clientData.setAdmin(Boolean.parseBoolean(response.clientData.get(0)));
                clientData.setName(response.clientData.get(1));
                clientData.setEmail(response.clientData.get(2));
                clientData.setPassword(response.clientData.get(3));
                clientData.setNIdentificacao(response.clientData.get(4));
            }

            return response.resultado;

        } catch (Exception e) {
            System.out.println("Ocorreu um erro no acesso ao socket:\n\t" + e);
        }

        return false;
    }


    // ADMIN
    public boolean criarEvento(String name, String local, String date, String timeStart, String timeEnd) {
        REQUEST_ADMIN_TO_SERVER msg = new REQUEST_ADMIN_TO_SERVER();
        msg.msgCode = REQUESTS.ADMIN_REQUEST_CREATE_EVENT;
        msg.name = name;
        msg.local = local;
        msg.date = date;
        msg.timeStart = timeStart;
        msg.timeEnd = timeEnd;

        try{

            //Serializa a string TIME_REQUEST para o OutputStream associado a socket
            oout.writeObject(msg);
            oout.flush();

            //Deserializa a resposta recebida em socket
            RESPONSE_SERVER_TO_CLIENT_OR_ADMIN response = (RESPONSE_SERVER_TO_CLIENT_OR_ADMIN) oin.readObject();

            if (response == null) {
                System.out.println("O servidor nao enviou qualquer respota antes de"
                        + " fechar aligacao TCP!");
            } else {
                System.out.println(response.response);
            }

            return response.resultado;

        } catch (Exception e) {
            return false;
        }

    }

    public boolean editEvent(String idEvento, int index, String alteracao) {
        REQUEST_ADMIN_TO_SERVER msg = new REQUEST_ADMIN_TO_SERVER();

        msg.msgCode = REQUESTS.ADMIN_REQUEST_EDIT_EVENT;
        msg.id = Integer.parseInt(idEvento);

        checkEvents();

        for (int i = 0; i < eventsList.getSize(); i++) {
            if (eventsList.getEvent(i).getId().equals(idEvento)) {
                msg.name = eventsList.getEvent(i).getName();
                msg.local = eventsList.getEvent(i).getLocal();
                msg.date = eventsList.getEvent(i).getDate();
                msg.timeStart = eventsList.getEvent(i).getTimeStart();
                msg.timeEnd = eventsList.getEvent(i).getTimeEnd();

                switch (index) {
                    case 1 -> {
                        msg.name = alteracao;
                    }
                    case 2 -> {
                        msg.local = alteracao;
                    }
                    case 3 -> {
                        msg.date = alteracao;
                    }
                    case 4 -> {
                        msg.timeStart = alteracao;
                    }
                    case 5 -> {
                        msg.timeEnd = alteracao;
                    }
                }
            }
        }

        try{

            //Serializa a string TIME_REQUEST para o OutputStream associado a socket
            oout.writeObject(msg);
            oout.flush();

            //Deserializa a resposta recebida em socket
            RESPONSE_SERVER_TO_CLIENT_OR_ADMIN response = (RESPONSE_SERVER_TO_CLIENT_OR_ADMIN) oin.readObject();

            if (response == null) {
                System.out.println("O servidor nao enviou qualquer respota antes de"
                        + " fechar aligacao TCP!");
            } else {
                System.out.println(response.response);
            }

            return response.resultado;

        } catch (Exception e) {
            return false;
        }

    }

    public boolean deleteEvent(String idEvento) {
        REQUEST_ADMIN_TO_SERVER msg = new REQUEST_ADMIN_TO_SERVER();
        msg.msgCode = REQUESTS.ADMIN_REQUEST_DELETE_EVENT;
        msg.id = Integer.parseInt(idEvento);

        try{

            //Serializa a string TIME_REQUEST para o OutputStream associado a socket
            oout.writeObject(msg);
            oout.flush();

            //Deserializa a resposta recebida em socket
            RESPONSE_SERVER_TO_CLIENT_OR_ADMIN response = (RESPONSE_SERVER_TO_CLIENT_OR_ADMIN) oin.readObject();

            if (response == null) {
                System.out.println("O servidor nao enviou qualquer respota antes de"
                        + " fechar aligacao TCP!");
            } else {
                System.out.println(response.response);
            }

            return response.resultado;

        } catch (Exception e) {
            System.out.println("Ocorreu um erro no acesso ao socket:\n\t" + e);
        }

        return false;
    }

    public ArrayList<ArrayList<String>> checkEvents() {
        REQUEST_ADMIN_TO_SERVER msg = new REQUEST_ADMIN_TO_SERVER();
        msg.msgCode = REQUESTS.ADMIN_REQUEST_CHECK_EVENTS;

        try{

            //Serializa a string TIME_REQUEST para o OutputStream associado a socket
            oout.writeObject(msg);
            oout.flush();

            //Deserializa a resposta recebida em socket
            RESPONSE_SERVER_TO_CLIENT_OR_ADMIN response = (RESPONSE_SERVER_TO_CLIENT_OR_ADMIN) oin.readObject();

            if (response == null) {
                System.out.println("O servidor nao enviou qualquer respota antes de"
                        + " fechar aligacao TCP!");
            } else {
                System.out.println(response.response);
            }

            eventsList = response.eventsList;

            ArrayList<ArrayList<String>> listEvents = new ArrayList<>();


            for (int i = 0; i < response.eventsList.getSize(); i++) {
                ArrayList<String> event = new ArrayList<>();

                event.add(response.eventsList.getEvent(i).getId());
                event.add(response.eventsList.getEvent(i).getName());
                event.add(response.eventsList.getEvent(i).getCode());
                event.add(response.eventsList.getEvent(i).getLocal());
                event.add(response.eventsList.getEvent(i).getDate());
                event.add(response.eventsList.getEvent(i).getTimeStart());
                event.add(response.eventsList.getEvent(i).getTimeEnd());
                event.add(response.eventsList.getEvent(i).getnPresences());

                listEvents.add(event);

            }


            return listEvents;

        } catch (Exception e) {
            System.out.println("Ocorreu um erro no acesso ao socket:\n\t" + e);
        }

        return null;
    }

    public boolean generateEventCode(String eventoID){
        Random random = new Random();
        int code = random.nextInt(999999-100000+1)+100000;

        boolean done = false;
        do {
            try {

                REQUEST_ADMIN_TO_SERVER msg = new REQUEST_ADMIN_TO_SERVER();
                msg.msgCode = REQUESTS.ADMIN_REQUEST_GENERATE_CODE;
                msg.id = Integer.parseInt(eventoID);
                msg.eventCode = code;

                //Serializa a string TIME_REQUEST para o OutputStream associado a socket
                oout.writeObject(msg);
                oout.flush();

                //Deserializa a resposta recebida em socket
                RESPONSE_SERVER_TO_CLIENT_OR_ADMIN response = (RESPONSE_SERVER_TO_CLIENT_OR_ADMIN) oin.readObject();

                if (response == null) {
                    System.out.println("O servidor nao enviou qualquer respota antes de"
                            + " fechar aligacao TCP!");
                }
                else {
                    System.out.println(response.response);
                }

                if(response.resultado)
                    done = true;

            } catch(Exception e){
                System.out.println("Ocorreu um erro no acesso ao socket:\n\t" + e);
                return false;
            }
        }while(!done);

        return true;
    }
    public ArrayList<ArrayList<String>> checkPresencesEvent(String id){
        REQUEST_ADMIN_TO_SERVER msg = new REQUEST_ADMIN_TO_SERVER();
        msg.msgCode = REQUESTS.ADMIN_REQUEST_CHECK_PRESENCES_EVENT;
        msg.id = Integer.parseInt(id);

        try{

            //Serializa a string TIME_REQUEST para o OutputStream associado a socket
            oout.writeObject(msg);
            oout.flush();

            //Deserializa a resposta recebida em socket
            RESPONSE_SERVER_TO_CLIENT_OR_ADMIN response = (RESPONSE_SERVER_TO_CLIENT_OR_ADMIN) oin.readObject();

            if (response == null) {
                System.out.println("O servidor nao enviou qualquer respota antes de"
                        + " fechar aligacao TCP!");
            }
            else {
                System.out.println(response.response);
            }

            return response.presencesADMIN;

        } catch (Exception e) {
            System.out.println("Ocorreu um erro no acesso ao socket:\n\t" + e);
        }

        return null;
    }

    public ArrayList<ArrayList<String>> checkPresences2(String email) {
        REQUEST_CLIENT_TO_SERVER msg = new REQUEST_CLIENT_TO_SERVER();

        msg.msgCode = REQUESTS.CLIENT_REQUEST_CHECK_PRESENCES;
        msg.email = email;

        try {

            //Serializa a string TIME_REQUEST para o OutputStream associado a socket
            oout.writeObject(msg);
            oout.flush();

            //Deserializa a resposta recebida em socket
            RESPONSE_SERVER_TO_CLIENT_OR_ADMIN response = (RESPONSE_SERVER_TO_CLIENT_OR_ADMIN) oin.readObject();

            if (response == null) {
                System.out.println("O servidor nao enviou qualquer respota antes de"
                        + " fechar aligacao TCP!");
            }



            return response.clientEventsData;

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deletePresence(String idEvento, String emailAluno){
        REQUEST_ADMIN_TO_SERVER msg = new REQUEST_ADMIN_TO_SERVER();
        msg.msgCode = REQUESTS.ADMIN_REQUEST_DELETE_PRESENCE;
        msg.id = Integer.parseInt(idEvento);
        msg.emailToManagePresence = emailAluno;

        try {

            //Serializa a string TIME_REQUEST para o OutputStream associado a socket
            oout.writeObject(msg);
            oout.flush();

            //Deserializa a resposta recebida em socket
            RESPONSE_SERVER_TO_CLIENT_OR_ADMIN response = (RESPONSE_SERVER_TO_CLIENT_OR_ADMIN) oin.readObject();

            if (response == null) {
                System.out.println("O servidor nao enviou qualquer respota antes de"
                        + " fechar aligacao TCP!");
            }

            return response.resultado;

        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
    }

    public String addPresence(String idEvento, String emailAluno){
        REQUEST_ADMIN_TO_SERVER msg = new REQUEST_ADMIN_TO_SERVER();

        msg.msgCode = REQUESTS.ADMIN_REQUEST_ADD_PRESENCE;
        msg.id = Integer.parseInt(idEvento);
        msg.emailToManagePresence = emailAluno;

        try {

            //Serializa a string TIME_REQUEST para o OutputStream associado a socket
            oout.writeObject(msg);
            oout.flush();

            //Deserializa a resposta recebida em socket
            RESPONSE_SERVER_TO_CLIENT_OR_ADMIN response = (RESPONSE_SERVER_TO_CLIENT_OR_ADMIN) oin.readObject();

            if (response == null) {
                System.out.println("O servidor nao enviou qualquer respota antes de"
                        + " fechar aligacao TCP!");
            }

            return response.resultado;

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean generateCSV_event(String idEvento){
        REQUEST_ADMIN_TO_SERVER msg = new REQUEST_ADMIN_TO_SERVER();

        msg.msgCode = REQUESTS.ADMIN_REQUEST_GENERATE_CSV_EVENT;
        msg.id = Integer.parseInt(idEvento);

        try {

            //Serializa a string TIME_REQUEST para o OutputStream associado a socket
            oout.writeObject(msg);
            oout.flush();

            //Deserializa a resposta recebida em socket
            RESPONSE_SERVER_TO_CLIENT_OR_ADMIN response = (RESPONSE_SERVER_TO_CLIENT_OR_ADMIN) oin.readObject();

            if (response == null) {
                System.out.println("O servidor nao enviou qualquer respota antes de"
                        + " fechar aligacao TCP!");
            }

            return response.resultado;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean generateCSV_student(String email){
        REQUEST_ADMIN_TO_SERVER msg = new REQUEST_ADMIN_TO_SERVER();

        msg.msgCode = REQUESTS.ADMIN_REQUEST_GENERATE_CSV_STUDENT;
        msg.emailToManagePresence = email;

        try {

            //Serializa a string TIME_REQUEST para o OutputStream associado a socket
            oout.writeObject(msg);
            oout.flush();

            //Deserializa a resposta recebida em socket
            RESPONSE_SERVER_TO_CLIENT_OR_ADMIN response = (RESPONSE_SERVER_TO_CLIENT_OR_ADMIN) oin.readObject();

            if (response == null) {
                System.out.println("O servidor nao enviou qualquer respota antes de"
                        + " fechar aligacao TCP!");
            }

            return response.resultado;
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
    }

    public boolean generateCSV_student_own(){
        REQUEST_CLIENT_TO_SERVER msg = new REQUEST_CLIENT_TO_SERVER();

        msg.msgCode = REQUESTS.CLIENT_REQUEST_GENERATE_CSV_STUDENT;
        msg.email = clientData.getEmail();

        try {

            //Serializa a string TIME_REQUEST para o OutputStream associado a socket
            oout.writeObject(msg);
            oout.flush();

            //Deserializa a resposta recebida em socket
            RESPONSE_SERVER_TO_CLIENT_OR_ADMIN response = (RESPONSE_SERVER_TO_CLIENT_OR_ADMIN) oin.readObject();

            if (response == null) {
                System.out.println("O servidor nao enviou qualquer respota antes de"
                        + " fechar aligacao TCP!");
            }

            return response.resultado;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }









    // ALUNO

    public boolean editData(int index, String alteracao) {
        REQUEST_CLIENT_TO_SERVER msg = new REQUEST_CLIENT_TO_SERVER();

        msg.msgCode = REQUESTS.CLIENT_REQUEST_EDIT_DATA;
        msg.name = clientData.getName();
        msg.novoEmail = clientData.getEmail();
        msg.email = clientData.getEmail();
        msg.password = clientData.getPassword();
        msg.nIdentificacao = clientData.getNIdentificacao();

        switch (index) {
            case 1 -> {
                msg.name = alteracao;
            }
            case 2 -> {
                msg.novoEmail = alteracao;
            }
            case 3 -> {
                msg.password = alteracao;
            }
            case 4 -> {
                msg.nIdentificacao = alteracao;
            }
        }

        try {

            //Serializa a string TIME_REQUEST para o OutputStream associado a socket
            oout.writeObject(msg);
            oout.flush();

            //Deserializa a resposta recebida em socket
            RESPONSE_SERVER_TO_CLIENT_OR_ADMIN response = (RESPONSE_SERVER_TO_CLIENT_OR_ADMIN) oin.readObject();

            if (response == null) {
                System.out.println("O servidor nao enviou qualquer respota antes de"
                        + " fechar aligacao TCP!");
            } else {
                System.out.println(response.response);
            }

            clientData.setAdmin(Boolean.parseBoolean(response.clientData.get(0)));
            clientData.setName(response.clientData.get(1));
            clientData.setEmail(response.clientData.get(2));
            clientData.setPassword(response.clientData.get(3));
            clientData.setNIdentificacao(response.clientData.get(4));

            return response.resultado;

        } catch (Exception e) {
            System.out.println("Ocorreu um erro no acesso ao socket:\n\t" + e);
        }

        return false;
    }

    public boolean submitCode(String code){
        REQUEST_CLIENT_TO_SERVER msg = new REQUEST_CLIENT_TO_SERVER();

        msg.msgCode = REQUESTS.CLIENT_REQUEST_SUBMIT_CODE;
        msg.email = clientData.getEmail();
        msg.eventCode = code;

        try {

            //Serializa a string TIME_REQUEST para o OutputStream associado a socket
            oout.writeObject(msg);
            oout.flush();

            //Deserializa a resposta recebida em socket
            RESPONSE_SERVER_TO_CLIENT_OR_ADMIN response = (RESPONSE_SERVER_TO_CLIENT_OR_ADMIN) oin.readObject();

            if (response == null) {
                System.out.println("O servidor nao enviou qualquer respota antes de"
                        + " fechar aligacao TCP!");
            }

            return response.resultado;

        } catch (Exception e) {
            System.out.println("Ocorreu um erro no acesso ao socket:\n\t" + e);
        }

        return false;
    }


    public ArrayList<ArrayList<String>> checkPresences() {
        REQUEST_CLIENT_TO_SERVER msg = new REQUEST_CLIENT_TO_SERVER();

        msg.msgCode = REQUESTS.CLIENT_REQUEST_CHECK_PRESENCES;
        msg.email = clientData.getEmail();

        try {

            //Serializa a string TIME_REQUEST para o OutputStream associado a socket
            oout.writeObject(msg);
            oout.flush();

            //Deserializa a resposta recebida em socket
            RESPONSE_SERVER_TO_CLIENT_OR_ADMIN response = (RESPONSE_SERVER_TO_CLIENT_OR_ADMIN) oin.readObject();

            if (response == null) {
                System.out.println("O servidor nao enviou qualquer respota antes de"
                        + " fechar aligacao TCP!");
            }


            return response.clientEventsData;

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    public boolean logout(){
        REQUEST_CLIENT_TO_SERVER msg = new REQUEST_CLIENT_TO_SERVER();

        msg.msgCode = REQUESTS.CLIENT_REQUEST_LOGOUT;
        msg.email = clientData.getEmail();

        try {

            //Serializa a string TIME_REQUEST para o OutputStream associado a socket
            oout.writeObject(msg);
            oout.flush();

            //Deserializa a resposta recebida em socket
            RESPONSE_SERVER_TO_CLIENT_OR_ADMIN response = (RESPONSE_SERVER_TO_CLIENT_OR_ADMIN) oin.readObject();

            if (response == null) {
                System.out.println("O servidor nao enviou qualquer respota antes de"
                        + " fechar aligacao TCP!");
            } else {
                System.out.println(response.response);
            }

            return response.resultado;

        } catch (Exception e) {
            System.out.println("Ocorreu um erro no acesso ao socket:\n\t" + e);
        }

        return false;
    }


    // CLIENT DATA
    public boolean isAdmin() {
        return clientData.getAdmin();
    }

    public String getName() {
        return clientData.getName();
    }

    public String getEmail() {
        return clientData.getEmail();
    }

    public String getPassword() {
        return clientData.getPassword();
    }

    public String getNIdentificacao() {
        return clientData.getNIdentificacao();
    }

}