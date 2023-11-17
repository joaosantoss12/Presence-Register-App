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
import pt.isec.pd.a2020136093.client.ui.gui.RESOURCES.PopUpCreator;
import pt.isec.pd.a2020136093.client.ui.gui.RootPane;

public class EditDataEventUI extends BorderPane {
    ManageConnections mc;
    Font titleFont, buttonsFont;

    String id;

    Label lblTitle;
    Button btnEditName, btnEditLocal, btnEditData, btnEditHourStart, btnBack,btnEditHourEnd;

    public EditDataEventUI(ManageConnections mc, String id) {

        //titleFont = FontManager.loadFont("PAC-FONT.TTF",69);
        //buttonsFont = FontManager.loadFont("PressStart2P-Regular.ttf",12);

        createViews();
        registerHandlers();
        update();
    }


    private void createViews() {
        this.setBackground(new Background(new BackgroundFill(Color.rgb(240, 240, 240), null, null)));

        lblTitle = new Label("Editar dados do Evento");
        lblTitle.setStyle("-fx-text-fill: #333; -fx-font-size: 36px; -fx-font-weight: bold;");

        btnEditName = createStyledButton("Editar nome");
        btnEditName.setMinWidth(120);

        btnEditLocal = createStyledButton("Editar local");
        btnEditLocal.setMinWidth(120);

        btnEditData = createStyledButton("Editar data");
        btnEditData.setMinWidth(120);

        btnEditHourStart = createStyledButton("Editar hora de inicio");
        btnEditHourStart.setMinWidth(120);

        btnEditHourEnd = createStyledButton("Editar hora de fim");
        btnEditHourEnd.setMinWidth(120);

        btnBack = createStyledButton("Voltar");
        btnBack.setMinWidth(120);


        VBox vBox = new VBox(lblTitle, btnEditName, btnEditLocal, btnEditData, btnEditHourStart, btnEditHourEnd,btnBack);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);
        VBox.setMargin(btnEditName, new Insets(25, 0, 0, 0)); // Set top margin for the button

        this.setCenter(vBox);
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-text-fill: black; -fx-font-size: 16px; ");
        return button;
    }




    private void registerHandlers() {
        //RootPane.addPropertyChangeListener("SHOWMENU", evt -> { update(); });
        //RootPane.addPropertyChangeListener("SHOWLOGIN", evt -> { update(); });

        btnEditName.setOnAction(event -> {
            PopUpCreator.editName();
        });

        btnEditLocal.setOnAction(event -> {
            PopUpCreator.editLocal();
        });

        btnEditData.setOnAction(event -> {
            PopUpCreator.editData();
        });

        btnEditHourStart.setOnAction(event -> {
            PopUpCreator.editHourStart();
        });

        btnEditHourEnd.setOnAction(event -> {
            PopUpCreator.editHourEnd();
        });


        //ExitAlertUI.exitAlert(btnExit);
    }


    private void update(){
        /*if(RootPane.showMainMenu){
            this.setVisible(true);
        }else{
            this.setVisible(false);
        }*/
    }

}