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
import pt.isec.pd.a2020136093.client.communication.ManageConnections;
import pt.isec.pd.a2020136093.client.ui.gui.RootPane;

public class MenuAdminUI extends BorderPane {
    ManageConnections mc;
    Font titleFont, buttonsFont;

    Label lblTitle;
    Button btnCreateNewEvent, btnEditEvent,btnExit;

    public MenuAdminUI(ManageConnections mc) {

        this.mc = mc;

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

        btnCreateNewEvent = createStyledButton("Criar novo evento");
        btnCreateNewEvent.setMinWidth(120);
        btnEditEvent = createStyledButton("Editar evento");
        btnEditEvent.setMinWidth(120);
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



        VBox vBox = new VBox(lblTitle, btnCreateNewEvent, btnEditEvent, btnExit);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);
        VBox.setMargin(btnCreateNewEvent, new Insets(25, 0, 0, 0)); // Set top margin for the button

        this.setCenter(vBox);
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-text-fill: black; -fx-font-size: 16px; ");
        return button;
    }




    private void registerHandlers() {
        RootPane.addPropertyChangeListener("SHOWMENU", evt -> { update(); });
        RootPane.addPropertyChangeListener("SHOWADMINMENU", evt -> { update(); });

        btnCreateNewEvent.setOnAction(event -> {
        //ligar AO CreateNewEventUI
        });

        btnEditEvent.setOnAction(event -> {
            //tinyPacmanManager.setShowTopFive(true);
        });

        btnExit.setOnAction( event -> {
            //tinyPacmanManager.setShowCredits(true);
        });


        //ExitAlertUI.exitAlert(btnExit);
    }


    private void update(){
        if(RootPane.showAdminMenu){
            this.setVisible(true);
        }else{
            this.setVisible(false);
        }
    }
}
