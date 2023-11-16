package pt.isec.pd.a2020136093.client.ui.gui.ADMIN;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pt.isec.pd.a2020136093.client.ui.gui.RootPane;

public class MenuAdminUI extends BorderPane {
    Font titleFont, buttonsFont;

    Label lblTitle;
    Button btnLogin,btnRegister,btnExit;

    public MenuAdminUI() {

        //titleFont = FontManager.loadFont("PAC-FONT.TTF",69);
        //buttonsFont = FontManager.loadFont("PressStart2P-Regular.ttf",12);

        createViews();
        registerHandlers();
        update();
    }


    private void createViews() {
        this.setBackground(new Background(new BackgroundFill(Color.rgb(240, 240, 240), null, null)));

        lblTitle = new Label("Bem-vindo admin");
        lblTitle.setStyle("-fx-text-fill: #333; -fx-font-size: 36px; -fx-font-weight: bold;");

        btnLogin = createStyledButton("Criar novo evento");
        btnLogin.setMinWidth(120);
        btnRegister = createStyledButton("Editar evento");
        btnRegister.setMinWidth(120);
        btnExit = createStyledButton("Eliminar evento");
        btnExit.setMinWidth(120);
        btnExit = createStyledButton("Consultar eventos");
        btnExit.setMinWidth(120);
        btnExit = createStyledButton("Gerar codigo para evento");
        btnExit.setMinWidth(120);
        btnExit = createStyledButton("Consultar presencas em evento");
        btnExit.setMinWidth(120);
        btnExit = createStyledButton("Gerar ficheiro CSV(presencas em evento");
        btnExit.setMinWidth(120);
        btnExit = createStyledButton("Consultar presencas em eventos (por aluno");
        btnExit.setMinWidth(120);
        btnExit = createStyledButton("Gerar ficheiro CSV (presencas por aluno");
        btnExit.setMinWidth(120);
        btnExit = createStyledButton("Eliminar presencas de um evento");
        btnExit.setMinWidth(120);
        btnExit = createStyledButton("Inserir presenca em evento");
        btnExit.setMinWidth(120);
        btnExit = createStyledButton("Logout");
        btnExit.setMinWidth(120);



        VBox vBox = new VBox(lblTitle, btnLogin, btnRegister, btnExit);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);
        VBox.setMargin(btnLogin, new Insets(25, 0, 0, 0)); // Set top margin for the button

        this.setCenter(vBox);
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-text-fill: black; -fx-font-size: 16px; ");
        return button;
    }




    private void registerHandlers() {
        RootPane.addPropertyChangeListener("SHOWMENU", evt -> { update(); });
        RootPane.addPropertyChangeListener("SHOWLOGIN", evt -> { update(); });

        btnLogin.setOnAction( event -> {
            RootPane.setShowMainMenu(false);
            RootPane.setShowLogin(true);
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
        if(RootPane.showMainMenu){
            this.setVisible(true);
        }else{
            this.setVisible(false);
        }
    }
}
