package pt.isec.pd.a2020136093.client.ui.gui;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import pt.isec.pd.a2020136093.client.communication.ManageConnections;

public class LoginUI extends BorderPane {
    ManageConnections mc;

    VBox vbox1, vbox2;
    TextField emailField;
    TextField passwordField;

    Label lblRetorno, lblTitle, lblEmail, lblPassword;
    Button btnLogin,btnBack;

    public LoginUI(ManageConnections mc) {
        this.mc = mc;

        //titleFont = FontManager.loadFont("PAC-FONT.TTF",69);
        //buttonsFont = FontManager.loadFont("PressStart2P-Regular.ttf",12);

        createViews();
        registerHandlers();
        update();
    }


    private void createViews() {
        this.setBackground(new Background(new BackgroundFill(Color.rgb(240, 240, 240), null, null)));

        lblTitle = new Label("LOGIN");
        lblTitle.setStyle("-fx-text-fill: #333; -fx-font-size: 36px; -fx-font-weight: bold;");

        vbox1 = new VBox();
        emailField = new TextField();
        //emailField.setPromptText("Email");
        emailField.setMaxWidth(690);
        lblEmail = new Label("Email");
        vbox1.getChildren().addAll(lblEmail, emailField);
        vbox1.alignmentProperty().setValue(Pos.CENTER);

        vbox2 = new VBox();
        passwordField = new TextField();
        //passwordField.setPromptText("Password");
        passwordField.setMaxWidth(690);
        lblPassword = new Label("Password");
        vbox2.getChildren().addAll(lblPassword, passwordField);
        vbox2.alignmentProperty().setValue(Pos.CENTER);

        lblRetorno = new Label("");
        lblRetorno.setVisible(false);


        btnLogin = createStyledButton("LOGIN");
        btnLogin.setMinWidth(120);
        btnBack = createStyledButton("VOLTAR");
        btnBack.setMinWidth(120);


        VBox vBox = new VBox(lblTitle, vbox1, vbox2, lblRetorno, btnLogin, btnBack);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);
        VBox.setMargin(btnLogin, new Insets(25, 0, 0, 0)); // Set top margin for the button

        this.setCenter(vBox);
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle(" -fx-text-fill: black; -fx-font-size: 16px; ");
        return button;
    }




    private void registerHandlers() {
        RootPane.addPropertyChangeListener("SHOWMENU", evt -> { update(); });
        RootPane.addPropertyChangeListener("SHOWLOGIN", evt -> { update(); });


        btnLogin.setOnAction( event -> {

            if (mc.login(emailField.getText(), passwordField.getText())) {
                /*lblRetorno.setVisible(true);
                lblRetorno.setText("Login efetuado com sucesso!");
                lblRetorno.setStyle("-fx-text-fill: green; -fx-font-size: 16px; -fx-font-weight: bold;");*/

                RootPane.setShowLogin(false);

                if(mc.isAdmin())
                    RootPane.setShowAdminMenu(true);
                else
                    RootPane.setShowStudentMenu(true);

            }
            else {
                lblRetorno.setVisible(true);
                lblRetorno.setText("Credenciais erradas ou Conexão com servidor perdida!");
                lblRetorno.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
            }


        });

        btnBack.setOnAction( event -> {
            RootPane.setShowLogin(false);
            RootPane.setShowMainMenu(true);
        });



        //ExitAlertUI.exitAlert(btnExit);
    }


    private void update(){
        if(RootPane.showLogin){
            this.setVisible(true);
        }else{
            this.setVisible(false);
        }
    }


}
