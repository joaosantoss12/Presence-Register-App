package pt.isec.pd.a2020136093.client.ui.gui;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;

public class ExitAlertUI {

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