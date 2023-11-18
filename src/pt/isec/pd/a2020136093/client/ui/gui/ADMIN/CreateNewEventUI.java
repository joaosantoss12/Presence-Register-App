package pt.isec.pd.a2020136093.client.ui.gui.ADMIN;

import javafx.animation.PauseTransition;
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
import javafx.stage.Stage;
import javafx.util.Duration;
import pt.isec.pd.a2020136093.client.communication.ManageConnections;
import pt.isec.pd.a2020136093.client.ui.gui.RootPane;

public class CreateNewEventUI extends BorderPane {
    ManageConnections mc;
    VBox vbox1;
    TextField EventNameField, EventLocalField, EventDateField, EventStartHourField, EventFinishHourField;

    Label lblTitle, lblNameEvent, lblLocalEvent, lblDateEvent, lblHourEventStart, lblHourEventEnd, lblResultado;
    Button btnCreateEvent,btnBack;

    public CreateNewEventUI(ManageConnections mc) {
        this.mc = mc;

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

        lblResultado = new Label();
        lblResultado.setVisible(false);


        btnCreateEvent = createStyledButton("Criar Evento");
        btnCreateEvent.setMinWidth(120);

        btnBack = createStyledButton("Voltar");
        btnBack.setMinWidth(120);


        VBox vBox = new VBox(lblTitle, lblNameEvent, EventNameField, lblLocalEvent, EventLocalField, lblDateEvent, EventDateField, lblHourEventStart, EventStartHourField, lblHourEventEnd, EventFinishHourField, lblResultado, btnCreateEvent, btnBack);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);
        VBox.setMargin(lblNameEvent, new Insets(25, 0, 0, 0)); // Set top margin for the button
        VBox.setMargin(btnCreateEvent, new Insets(30, 0, 0, 0)); // Set top margin for the button

        this.setCenter(vBox);
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle(" -fx-text-fill: black; -fx-font-size: 16px; ");
        return button;
    }




    private void registerHandlers() {
        btnCreateEvent.setOnAction(event -> {
            if(mc.criarEvento(
                    EventNameField.getText(),
                    EventLocalField.getText(),
                    EventDateField.getText(),
                    EventStartHourField.getText(),
                    EventFinishHourField.getText()
            )){
                EventNameField.setText("");
                EventLocalField.setText("");
                EventDateField.setText("");
                EventStartHourField.setText("");
                EventFinishHourField.setText("");

                lblResultado.setText("Evento criado com sucesso!");
                lblResultado.setStyle("-fx-text-fill: green; -fx-font-size: 25px; -fx-font-weight: bold;");
                lblResultado.setVisible(true);

                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(e -> {
                    lblResultado.setVisible(false);
                });
                pause.play();
            }
            else{
                EventNameField.setText("");
                EventLocalField.setText("");
                EventDateField.setText("");
                EventStartHourField.setText("");
                EventFinishHourField.setText("");

                lblResultado.setText("Houve um erro ao criar o evento!");
                lblResultado.setStyle("-fx-text-fill: red; -fx-font-size: 25px; -fx-font-weight: bold;");
                lblResultado.setVisible(true);

                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(e -> {
                    lblResultado.setVisible(false);
                });
                pause.play();
            }
        });


        btnBack.setOnAction(event -> {
            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.close();
        });
    }


    private void update(){

    }


}


