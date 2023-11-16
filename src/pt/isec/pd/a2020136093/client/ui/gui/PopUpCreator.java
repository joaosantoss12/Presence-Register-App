package pt.isec.pd.a2020136093.client.ui.gui;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

public class PopUpCreator {

    public static String editName() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Atualizar Nome");
        dialog.setHeaderText("Introduza o novo nome:");
        dialog.setContentText("Nome:");

        // Traditional way to get the response value.
        return dialog.showAndWait()
                .map(result -> {
                    System.out.println("Novo Nome: " + result);

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
                    System.out.println("Novo email: " + result);

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
                    System.out.println("Nova password: " + result);

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
                    System.out.println("Novo numero de identificacao: " + result);

                    return result;
                })
                .orElse(null);
    }

    public static String enterPresCode() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Submeter codigo de presenca");
        dialog.setHeaderText("Introduza o codigo de presenca:");
        dialog.setContentText("Codigo de presenca:");

        // Traditional way to get the response value.
        return dialog.showAndWait()
                .map(result -> {
                    System.out.println("Codigo de presenca: " + result);

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
}