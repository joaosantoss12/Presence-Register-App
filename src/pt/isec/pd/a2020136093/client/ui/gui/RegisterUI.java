package pt.isec.pd.a2020136093.client.ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pt.isec.pd.a2020136093.client.communication.ManageConnections;

public class RegisterUI extends BorderPane {
    ManageConnections mc;

    VBox vbox1, vbox2, vbox3, vbox4;
    TextField nameField, emailField, passwordField, nIdentificacaoField;

    Label lblTitle, lblName, lblEmail, lblPassword, lblNIdentificacao;
    Button btnRegister,btnBack;

    public RegisterUI(ManageConnections mc) {

        this.mc = mc;
        //titleFont = FontManager.loadFont("PAC-FONT.TTF",69);
        //buttonsFont = FontManager.loadFont("PressStart2P-Regular.ttf",12);

        createViews();
        registerHandlers();
        update();
    }


    private void createViews() {
        this.setBackground(new Background(new BackgroundFill(Color.rgb(240, 240, 240), null, null)));

        lblTitle = new Label("REGISTAR NOVA CONTA");
        lblTitle.setStyle("-fx-text-fill: #333; -fx-font-size: 36px; -fx-font-weight: bold;");

        vbox2 = new VBox();
        emailField = new TextField();
        //emailField.setPromptText("Email");
        emailField.setMaxWidth(690);
        lblEmail = new Label("Email");
        vbox2.getChildren().addAll(lblEmail, emailField);
        vbox2.alignmentProperty().setValue(Pos.CENTER);

        vbox3 = new VBox();
        passwordField = new TextField();
        //passwordField.setPromptText("Password");
        passwordField.setMaxWidth(690);
        lblPassword = new Label("Password");
        vbox3.getChildren().addAll(lblPassword, passwordField);
        vbox3.alignmentProperty().setValue(Pos.CENTER);

        vbox1 = new VBox();
        nameField = new TextField();
        //passwordField.setPromptText("Password");
        nameField.setMaxWidth(690);
        lblName = new Label("Nome");
        vbox1.getChildren().addAll(lblName, nameField);
        vbox1.alignmentProperty().setValue(Pos.CENTER);

        vbox4 = new VBox();
        nIdentificacaoField = new TextField();
        //passwordField.setPromptText("Password");
        nIdentificacaoField.setMaxWidth(690);
        lblNIdentificacao = new Label("Número Identificação");
        vbox4.getChildren().addAll(lblNIdentificacao, nIdentificacaoField);
        vbox4.alignmentProperty().setValue(Pos.CENTER);


        btnRegister = createStyledButton("REGISTAR");
        btnRegister.setMinWidth(120);
        btnBack = createStyledButton("VOLTAR");
        btnBack.setMinWidth(120);


        VBox vBox = new VBox(lblTitle, vbox1, vbox2, vbox3, vbox4, btnRegister, btnBack);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);
        VBox.setMargin(btnRegister, new Insets(25, 0, 0, 0)); // Set top margin for the button

        this.setCenter(vBox);
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle(" -fx-text-fill: black; -fx-font-size: 16px; ");
        return button;
    }




    private void registerHandlers() {
        RootPane.addPropertyChangeListener("SHOWMENU", evt -> { update(); });
        RootPane.addPropertyChangeListener("SHOWREGISTER", evt -> { update(); });


        btnRegister.setOnAction( event -> {

            RootPane.setShowRegister(false);
            RootPane.setShowMainMenu(true);

        });

        btnBack.setOnAction( event -> {
            RootPane.setShowRegister(false);
            RootPane.setShowMainMenu(true);
        });



        //ExitAlertUI.exitAlert(btnExit);
    }


    private void update(){
        if(RootPane.showRegister){
            this.setVisible(true);
        }else{
            this.setVisible(false);
        }
    }


}
