package pt.isec.pd.a2020136093.client.ui.gui.RESOURCES;

import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.util.ArrayList;

public class PopUpCreator {

    public static String editName() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Atualizar Nome");
        dialog.setHeaderText("Introduza o novo nome:");
        dialog.setContentText("Nome:");

        // Traditional way to get the response value.
        return dialog.showAndWait()
                .map(result -> {
                    System.out.println();

                    return result;
                })
                .orElse(null);
    }

    public static String editEmail() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Atualizar Email");
        dialog.setHeaderText("Introduza o novo email:");
        dialog.setContentText("Email:");

        // Traditional way to get the response value.
        return dialog.showAndWait()
                .map(result -> {
                    System.out.println();

                    return result;
                })
                .orElse(null);
    }

    public static String editPassword() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Atualizar Password");
        dialog.setHeaderText("Introduza a nova password:");
        dialog.setContentText("Password:");

        // Traditional way to get the response value.
        return dialog.showAndWait()
                .map(result -> {
                    System.out.println();

                    return result;
                })
                .orElse(null);
    }

    public static String editIDNumber() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Atualizar Numero de Identificacao");
        dialog.setHeaderText("Introduza o novo numero de identificacao:");
        dialog.setContentText("Numero de Identificacao:");

        // Traditional way to get the response value.
        return dialog.showAndWait()
                .map(result -> {

                    return result;
                })
                .orElse(null);
    }

    public static String sendPresenceCode() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Submeter codigo de presenca");
        //dialog.setHeaderText("Introduza o codigo de presenca:");
        dialog.setContentText("Codigo de presenca:");

        // Traditional way to get the response value.
        return dialog.showAndWait()
                .map(result -> {
                    System.out.println();

                    return result;
                })
                .orElse(null);
    }




    public static void exitAlert(Button btnExit) {

        btnExit.setOnAction(event -> {
            Alert alert = new Alert(
                    Alert.AlertType.WARNING,
                    null,
                    ButtonType.YES, ButtonType.NO
            );

            alert.setTitle("Sair");
            alert.setHeaderText("Quer realmente sair?");

            //ImageView exitIcon = new ImageView(ImageManager.getImage("pacman-sad.png"));
            //exitIcon.setFitHeight(100);
            //exitIcon.setFitWidth(100);
            //alert.getDialogPane().setGraphic(exitIcon);

            alert.showAndWait().ifPresent(response -> {
                switch (response.getButtonData()){
                    case YES -> {
                        Platform.exit();
                    }
                    case NO -> {}
                }
            });
        });
    }

    public static String editLocal() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Atualizar Local");
        dialog.setHeaderText("Introduza o novo local:");
        dialog.setContentText("Local:");

        // Traditional way to get the response value.
        return dialog.showAndWait()
                .map(result -> {
                    System.out.println("Novo local: " + result);

                    return result;
                })
                .orElse(null);
    }

    public static String editData() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Atualizar Data");
        dialog.setHeaderText("Introduza a nova data:");
        dialog.setContentText("Data:");

        // Traditional way to get the response value.
        return dialog.showAndWait()
                .map(result -> {
                    System.out.println("Nova data: " + result);

                    return result;
                })
                .orElse(null);
    }

    public static String editHourStart() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Atualizar Hora de Inicio");
        dialog.setHeaderText("Introduza a nova hora de inicio:");
        dialog.setContentText("Hora de Inicio:");

        // Traditional way to get the response value.
        return dialog.showAndWait()
                .map(result -> {
                    System.out.println("Nova hora de inicio: " + result);

                    return result;
                })
                .orElse(null);
    }

    public static String editHourEnd() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Atualizar Hora de Fim");
        dialog.setHeaderText("Introduza a nova hora de fim:");
        dialog.setContentText("Hora de Fim:");

        // Traditional way to get the response value.
        return dialog.showAndWait()
                .map(result -> {
                    System.out.println("Nova hora de fim: " + result);

                    return result;
                })
                .orElse(null);
    }

    public static String deleteEventPopUp() {
        // SET A FIELD SO THE ADMIN WRITES AN EVENT ID
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Apagar Evento");
        dialog.setHeaderText("Introduza o ID do evento a apagar:");
        dialog.setContentText("ID:");

        return dialog.showAndWait()
                .map(result -> {
                    System.out.println();

                    return result;
                })
                .orElse(null);

    }

    public static void checkEventsPopUp(ArrayList<ArrayList<String>> listaEventos ) {
        StringBuilder content = new StringBuilder();
        content.append("Lista de Eventos\n");
        content.append(String.format("%-3s | %-15s | %-15s | %-15s | %-15s | %s\n", "ID", "Nome", "Local", "Data", "Hora de inicio", "Hora de fim"));
        content.append("--------------------------------------------------------------------------------------\n");

        for (int i = 0; i < listaEventos.size(); i++) {
            content.append(String.format("%-3s | %-15s | %-15s | %-15s | %-15s | %s\n",
                    listaEventos.get(i).get(0), listaEventos.get(i).get(1), listaEventos.get(i).get(2),
                    listaEventos.get(i).get(3), listaEventos.get(i).get(4), listaEventos.get(i).get(5)));
        }

        content.append("--------------------------------------------------------------------------------------\n");

        // Create an alert with information type
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Lista de Eventos");
        alert.setHeaderText(null);

        // Create a TextArea to display the content
        TextArea textArea = new TextArea(content.toString());
        textArea.setEditable(false);
        textArea.setWrapText(true);

        // Set the content of the alert to the TextArea
        alert.getDialogPane().setContent(textArea);

        // Expand the TextArea to fill the available space
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        // Show the alert
        alert.showAndWait();
    }


    public static void checkStudentPresencesPopUp_list(ArrayList<ArrayList<String>> listaPresencas){
        StringBuilder content = new StringBuilder();
        content.append("Lista de Presencas\n");

        content.append(String.format("%-3s | %-15s | %-15s | %-15s | %-15s | %s\n", "ID", "Nome", "Local", "Data", "Hora de inicio", "Hora de fim"));
        content.append("-------------------------------------------------------------------------------------------\n");

        for(int i=0; i<listaPresencas.size(); i++){
            content.append(String.format("%-3s | %-15s | %-15s | %-15s | %-15s | %s\n",
                    listaPresencas.get(i).get(0), listaPresencas.get(i).get(1), listaPresencas.get(i).get(2),
                    listaPresencas.get(i).get(3), listaPresencas.get(i).get(4), listaPresencas.get(i).get(5)));
        }
        content.append("-------------------------------------------------------------------------------------------\n");


        // Create an alert with information type
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Lista de Presenças");
        alert.setHeaderText(null);

        // Create a TextArea to display the content
        TextArea textArea = new TextArea(content.toString());
        textArea.setEditable(false);
        textArea.setWrapText(true);

        // Set the content of the alert to the TextArea
        alert.getDialogPane().setContent(textArea);

        // Expand the TextArea to fill the available space
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        // Show the alert
        alert.showAndWait();
    }

    public static void checkEventPresencesPopUp_list(ArrayList<ArrayList<String>> listaPresencas ) {
        StringBuilder content = new StringBuilder();
        content.append("Lista de Presencas\n");

        content.append(String.format("%-15s | %-20s | %s\n","Nome","Email","nIdentificacao"));
        content.append("--------------------------------------------------------------------------------------\n");

        for(int i=0; i<listaPresencas.size(); i++){
            content.append(String.format("%-15s | %-20s | %s\n",listaPresencas.get(i).get(0),listaPresencas.get(i).get(1),listaPresencas.get(i).get(2)));
        }
        content.append("--------------------------------------------------------------------------------------\n");


        // Create an alert with information type
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Lista de Presenças");
        alert.setHeaderText(null);

        // Create a TextArea to display the content
        TextArea textArea = new TextArea(content.toString());
        textArea.setEditable(false);
        textArea.setWrapText(true);

        // Set the content of the alert to the TextArea
        alert.getDialogPane().setContent(textArea);

        // Expand the TextArea to fill the available space
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        // Show the alert
        alert.showAndWait();
    }

    public static String generateCodePopUp() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Gerar Codigo");
        dialog.setHeaderText("Introduza o ID do evento:");
        dialog.setContentText("ID:");

        return dialog.showAndWait()
                .map(result -> {
                    System.out.println();

                    return result;
                })
                .orElse(null);
    }

    public static String checkEventPresencesPopUp() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Consultar presenças do evento");
        dialog.setHeaderText("Introduza o ID do evento:");
        dialog.setContentText("ID:");

        return dialog.showAndWait()
                .map(result -> {
                    System.out.println();

                    return result;
                })
                .orElse(null);
    }

    public static String generateCSV1PopUp() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Gerar CSV");
        dialog.setHeaderText("Introduza o ID do evento:");
        dialog.setContentText("ID:");

        return dialog.showAndWait()
                .map(result -> {
                    System.out.println();

                    return result;
                })
                .orElse(null);
    }

    public static String checkStudentPresencesPopUp() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Consultar Presencas do Aluno");
        dialog.setHeaderText("Introduza o email:");
        dialog.setContentText("Email:");

        return dialog.showAndWait()
                .map(result -> {
                    System.out.println();

                    return result;
                })
                .orElse(null);
    }



    public static void checkPresencesPopUp(ArrayList<ArrayList<String>> listaPresencas) {
        StringBuilder content = new StringBuilder();
        content.append("REGISTO DE PRESENÇAS\n");
        content.append(String.format("%-3s | %-15s | %-15s | %-15s | %-15s | %s\n", "ID", "Nome", "Local", "Data", "Hora de inicio", "Hora de fim"));
        content.append("--------------------------------------------------------------------------------------\n");

        for (int i = 0; i < listaPresencas.size(); i++) {
            content.append(String.format("%-3s | %-15s | %-15s | %-15s | %-15s | %s\n",
                    listaPresencas.get(i).get(0), listaPresencas.get(i).get(1), listaPresencas.get(i).get(2),
                    listaPresencas.get(i).get(3), listaPresencas.get(i).get(4), listaPresencas.get(i).get(5)));
        }

        content.append("--------------------------------------------------------------------------------------\n");

        // Create an alert with information type
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registo de Presenças");
        alert.setHeaderText(null);

        // Create a TextArea to display the content
        TextArea textArea = new TextArea(content.toString());
        textArea.setEditable(false);
        textArea.setWrapText(true);

        // Set the content of the alert to the TextArea
        alert.getDialogPane().setContent(textArea);

        // Expand the TextArea to fill the available space
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        // Show the alert
        alert.showAndWait();
    }

    public static String generateSCV2PopUp() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Gerar CSV");
        dialog.setHeaderText("Introduza o email do aluno::");
        dialog.setContentText("EMAIL:");

        return dialog.showAndWait()
                .map(result -> {
                    System.out.println();

                    return result;
                })
                .orElse(null);
    }





    public static String presencePopUp_idEvento() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Consultar presenças do evento");
        dialog.setHeaderText("Introduza o ID do evento:");
        dialog.setContentText("ID:");

        return dialog.showAndWait()
                .map(result -> {
                    System.out.println();

                    return result;
                })
                .orElse(null);
    }
    public static String presencePopUp_email() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Introduza o email");
        dialog.setContentText("Email:");

        return dialog.showAndWait()
                .map(result -> {
                    System.out.println();

                    return result;
                })
                .orElse(null);
    }






    public static String editEventPopUp() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Editar Evento");
        dialog.setHeaderText("Introduza o ID do evento a editar:");
        dialog.setContentText("ID:");

        return dialog.showAndWait()
                .map(result -> {
                    System.out.println();

                    return result;
                })
                .orElse(null);
    }
}