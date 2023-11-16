package pt.isec.pd.a2020136093.client.ui.gui.STUDENT;

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
import pt.isec.pd.a2020136093.client.ui.gui.PopUpCreator;
import pt.isec.pd.a2020136093.client.ui.gui.RootPane;

public class MenuStudentUI extends BorderPane {
    ManageConnections mc;
    Font titleFont, buttonsFont;

    Label lblTitle;
    Button btnEditData, btnSubmitCode, btnPresences, btnCSV, btnLogout;

    public MenuStudentUI(ManageConnections mc) {
        this.mc = mc;
        //titleFont = FontManager.loadFont("PAC-FONT.TTF",69);
        //buttonsFont = FontManager.loadFont("PressStart2P-Regular.ttf",12);

        createViews();
        registerHandlers();
        update();
    }


    private void createViews() {
        this.setBackground(new Background(new BackgroundFill(Color.rgb(240, 240, 240), null, null)));

        lblTitle = new Label("Bem-vindo user");
        lblTitle.setStyle("-fx-text-fill: #333; -fx-font-size: 36px; -fx-font-weight: bold;");

        btnEditData = createStyledButton("Editar dados de registo");
        btnEditData.setMinWidth(120);
        btnSubmitCode = createStyledButton("Submeter codigo de presenca");
        btnSubmitCode.setMinWidth(120);
        btnPresences = createStyledButton("Consultar presencas");
        btnPresences.setMinWidth(120);
        btnCSV = createStyledButton("Gerar fiheiro CSV (registo de presencas)");
        btnCSV.setMinWidth(120);
        btnLogout = createStyledButton("Logout");
        btnLogout.setMinWidth(120);


        VBox vBox = new VBox(lblTitle, btnEditData, btnSubmitCode, btnPresences, btnCSV, btnLogout);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);
        VBox.setMargin(btnEditData, new Insets(25, 0, 0, 0)); // Set top margin for the button

        this.setCenter(vBox);
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-text-fill: black; -fx-font-size: 16px; ");
        return button;
    }


    private void registerHandlers() {
        RootPane.addPropertyChangeListener("SHOWMENU", evt -> { update(); });
        RootPane.addPropertyChangeListener("SHOWSTUDENTMENU", evt -> { update(); });

        btnEditData.setOnAction(event -> {

        });

        btnSubmitCode.setOnAction(event -> {
            PopUpCreator.enterPresCode();
        });

        btnPresences.setOnAction(event -> {
            //tinyPacmanManager.setShowCredits(true);
        });


        //ExitAlertUI.exitAlert(btnExit);
    }


    private void update(){
        if(RootPane.showStudentMenu){
            this.setVisible(true);
        }else{
            this.setVisible(false);
        }
    }

}
