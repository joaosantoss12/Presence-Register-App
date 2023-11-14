package pt.isec.pd.a2020136093.client.ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainMenuUI extends BorderPane {

    Font titleFont, buttonsFont;

    Label lblTitle;
    Button btnLogin,btnRegister,btnExit;

    public MainMenuUI() {

        //titleFont = FontManager.loadFont("PAC-FONT.TTF",69);
        //buttonsFont = FontManager.loadFont("PressStart2P-Regular.ttf",12);

        createViews();
        registerHandlers();
        update();
    }


    private void createViews() {
        this.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

        lblTitle = new Label("PRESENCE REGISTER");
        //lblTitle.setFont(titleFont);
        lblTitle.setId("mainMenuTitle");

        btnLogin = new Button("LOGIN");
        //btnLogin.setFont(buttonsFont);
        btnLogin.setId("mainMenuButton");
        btnLogin.setStyle("-fx-background-color: #fe6a00");

        btnRegister = new Button("REGISTAR");
        //btnRegister.setFont(buttonsFont);
        btnRegister.setId("mainMenuButton");
        btnRegister.setStyle("-fx-background-color: #00fff0");

        btnExit = new Button("SAIR");
        //btnExit.setFont(buttonsFont);
        btnExit.setId("mainMenuButton");
        btnExit.setStyle("-fx-background-color: #ff56c7");



        VBox vBox = new VBox(lblTitle,btnLogin,btnRegister,btnExit);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);
        VBox.setMargin(btnLogin, new Insets(75, 0, 0, 0)); // Set top margin for the button


        this.setCenter(vBox);
    }

    private void registerHandlers() {
        //tinyPacmanManager.addPropertyChangeListener(TinyPacmanManager.PROP_TOP_FIVE, evt -> { update(); });
        //tinyPacmanManager.addPropertyChangeListener(TinyPacmanManager.PROP_CREDITS, evt -> { update(); });


        btnLogin.setOnAction( event -> {
            // IR PARA OUTRA TELA


        });

        btnRegister.setOnAction( event -> {
            //tinyPacmanManager.setShowTopFive(true);
        });

        btnExit.setOnAction( event -> {
            //tinyPacmanManager.setShowCredits(true);
        });


        //ExitAlertUI.exitAlert(btnExit);
    }


    private void update(){
        //if(tinyPacmanManager.showTopFive() || tinyPacmanManager.showCredits() || tinyPacmanManager.getCurrentState() != null) {
            //this.setVisible(false);
            //return;
        //}
        //this.setVisible(true);
    }


}
