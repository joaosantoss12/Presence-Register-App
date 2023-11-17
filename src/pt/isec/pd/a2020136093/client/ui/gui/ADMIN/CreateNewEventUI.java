package pt.isec.pd.a2020136093.client.ui.gui.ADMIN;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import pt.isec.pd.a2020136093.client.communication.ManageConnections;
import pt.isec.pd.a2020136093.client.ui.gui.RootPane;

public class CreateNewEventUI extends BorderPane {
    ManageConnections mc;
    VBox vbox1;
    TextField EventNameField, EventLocalField, EventDateField, EventStartHourField, EventFinishHourField;

    Label lblTitle, lblNameEvent, lblLocalEvent, lblDateEvent, lblHourEventStart, lblHourEventEnd;
    Button btnCreateEvent,btnBack;

    public CreateNewEventUI(ManageConnections mc) {


        createViews();
        registerHandlers();
        update();
    }


    private void createViews() {
        this.setBackground(new Background(new BackgroundFill(Color.rgb(240, 240, 240), null, null)));

        lblTitle = new Label("Criar Evento");
        lblTitle.setStyle("-fx-text-fill: #333; -fx-font-size: 36px; -fx-font-weight: bold;");


        lblNameEvent = new Label("Nome do Evento");
        EventNameField = new TextField();
        EventNameField.setMaxWidth(690);

        lblLocalEvent = new Label("Local do Evento");
        EventLocalField = new TextField();
        EventLocalField.setMaxWidth(690);

        lblDateEvent = new Label("Data do Evento [DD-MM-YYYY]");
        EventDateField = new TextField();
        EventDateField.setMaxWidth(690);

        lblHourEventStart = new Label("Hora de inicio do Evento [HH:SS]");
        EventStartHourField = new TextField();
        EventStartHourField.setMaxWidth(690);

        lblHourEventEnd = new Label("Hora de fim do Evento [HH:SS]");
        EventFinishHourField = new TextField();
        EventFinishHourField.setMaxWidth(690);


        btnCreateEvent = createStyledButton("CRIAR EVENTO");
        btnCreateEvent.setMinWidth(120);


        VBox vBox = new VBox(lblTitle, lblNameEvent, EventNameField, lblLocalEvent, EventLocalField, lblDateEvent, EventDateField, lblHourEventStart, EventStartHourField, lblHourEventEnd, EventFinishHourField, btnCreateEvent);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);
        VBox.setMargin(btnCreateEvent, new Insets(25, 0, 0, 0)); // Set top margin for the button

        this.setCenter(vBox);
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle(" -fx-text-fill: black; -fx-font-size: 16px; ");
        return button;
    }




    private void registerHandlers() {
        //RootPane.addPropertyChangeListener("SHOWMENU", evt -> { update(); });
        //RootPane.addPropertyChangeListener("SHOWREGISTER", evt -> { update(); });


        btnCreateEvent.setOnAction(event -> {



        });



    }


    private void update(){
        /*if(RootPane.showRegister){
            this.setVisible(true);
        }else{
            this.setVisible(false);
        }*/
    }


}


