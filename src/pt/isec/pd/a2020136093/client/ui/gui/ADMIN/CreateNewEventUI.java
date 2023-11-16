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
import pt.isec.pd.a2020136093.client.ui.gui.RootPane;

public class CreateNewEventUI extends BorderPane {
    VBox vbox1, vbox2, vbox3, vbox4;
    TextField nameField, EventField, Event2Field, nIdentificacaoField;

    Label lblTitle, lblName, lblNameEvent, lblLocalEvent, lblNIdentificacao, lblDateEvent, lblHourEventStart, lblHourEventEnd;
    Button btnCreateEvent,btnBack;

    public CreateNewEventUI() {


        createViews();
        registerHandlers();
        update();
    }


    private void createViews() {
        this.setBackground(new Background(new BackgroundFill(Color.rgb(240, 240, 240), null, null)));

        lblTitle = new Label("Editar Evento");
        lblTitle.setStyle("-fx-text-fill: #333; -fx-font-size: 36px; -fx-font-weight: bold;");


        vbox2 = new VBox();
        EventField = new TextField();
        EventField.setMaxWidth(690);
        lblNameEvent = new Label("Nome do Evento");
        Event2Field = new TextField();
        Event2Field.setMaxWidth(690);
        lblLocalEvent = new Label("Local do Evento");
        Event2Field = new TextField();
        Event2Field.setMaxWidth(690);
        lblDateEvent = new Label("Data do Evento");
        Event2Field = new TextField();
        Event2Field.setMaxWidth(690);
        lblHourEventStart = new Label("Hora de inicio do Evento");
        Event2Field = new TextField();
        Event2Field.setMaxWidth(690);
        lblHourEventEnd = new Label("Hora de fim do Evento");
        vbox2.getChildren().addAll(lblNameEvent,lblLocalEvent,lblDateEvent, EventField);
        vbox2.alignmentProperty().setValue(Pos.CENTER);



        btnCreateEvent = createStyledButton("CRIAR EVENTO");
        btnCreateEvent.setMinWidth(120);


        VBox vBox = new VBox(lblTitle, vbox1, vbox2, vbox3, vbox4, btnCreateEvent);
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
        RootPane.addPropertyChangeListener("SHOWMENU", evt -> { update(); });
        RootPane.addPropertyChangeListener("SHOWREGISTER", evt -> { update(); });


        btnCreateEvent.setOnAction(event -> {

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


