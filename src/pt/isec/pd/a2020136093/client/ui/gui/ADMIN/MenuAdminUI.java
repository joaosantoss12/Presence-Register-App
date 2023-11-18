package pt.isec.pd.a2020136093.client.ui.gui.ADMIN;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import pt.isec.pd.a2020136093.client.communication.ManageConnections;
import pt.isec.pd.a2020136093.client.ui.gui.RESOURCES.PopUpCreator;
import pt.isec.pd.a2020136093.client.ui.gui.RootPane;
import pt.isec.pd.a2020136093.client.ui.gui.STUDENT.EditDataUI;

import java.util.ArrayList;

public class MenuAdminUI extends BorderPane {
    ManageConnections mc;
    Font titleFont, buttonsFont;

    Label lblTitle, lblResultado;
    Button btnCreateNewEvent, btnEditEvent, btnDeleteEvent, btnCheckEvents, btnGenerateCode, btnCheckEventPresences, btnGenerateCSV1, btnCheckStudentPresences, btnGenerateCSV2, btnDeletePresence, btnAddPresence, btnLogout;

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

        lblResultado = new Label("");
        lblResultado.setVisible(false);

        btnCreateNewEvent = createStyledButton("Criar novo evento");
        btnCreateNewEvent.setMinWidth(120);

        btnEditEvent = createStyledButton("Editar evento");
        btnEditEvent.setMinWidth(120);

        btnDeleteEvent = createStyledButton("Eliminar evento");
        btnDeleteEvent.setMinWidth(120);

        btnCheckEvents = createStyledButton("Consultar eventos");
        btnCheckEvents.setMinWidth(120);

        btnGenerateCode = createStyledButton("Gerar codigo para evento");
        btnGenerateCode.setMinWidth(120);

        btnCheckEventPresences = createStyledButton("Consultar presencas em evento");
        btnCheckEventPresences.setMinWidth(120);

        btnGenerateCSV1 = createStyledButton("Gerar ficheiro CSV (presencas em evento)");
        btnGenerateCSV1.setMinWidth(120);

        btnCheckStudentPresences = createStyledButton("Consultar presencas em eventos (por aluno)");
        btnCheckStudentPresences.setMinWidth(120);

        btnGenerateCSV2 = createStyledButton("Gerar ficheiro CSV (presencas por aluno)");
        btnGenerateCSV2.setMinWidth(120);

        btnDeletePresence = createStyledButton("Eliminar presencas de um evento");
        btnDeletePresence.setMinWidth(120);

        btnAddPresence = createStyledButton("Inserir presenca em evento");
        btnAddPresence.setMinWidth(120);

        btnLogout = createStyledButton("Logout");
        btnLogout.setMinWidth(120);

        VBox vBox1 = new VBox(btnCreateNewEvent, btnEditEvent, btnDeleteEvent, btnCheckEvents, btnGenerateCode, btnLogout);
        VBox vBox2 = new VBox(btnCheckEventPresences, btnGenerateCSV1, btnCheckStudentPresences, btnGenerateCSV2, btnDeletePresence, btnAddPresence);

        HBox hbox = new HBox(vBox1, vBox2);
        HBox.setMargin(vBox1, new Insets(0, 35, 0, 0));
        hbox.setAlignment(Pos.CENTER);


        VBox vBox = new VBox(lblTitle, hbox, lblResultado);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);
        VBox.setMargin(hbox, new Insets(15, 0, 0, 0)); // Set top margin
        VBox.setMargin(hbox, new Insets(35, 0, 0, 0)); // Set top margin

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
            Stage stage = new Stage();
            Scene scene = new Scene(new CreateNewEventUI(mc), 900, 725);
            //stage.getIcons().add(ImageManager.getImage("pacman-icon.png"));
            stage.setScene(scene);
            stage.setTitle("Criar novo evento");

            stage.setMinWidth(1000);
            stage.setMinHeight(700);

            stage.show();
        });

        btnEditEvent.setOnAction(event -> {

            String id = PopUpCreator.editEventPopUp();

            Stage stage = new Stage();
            Scene scene = new Scene(new EditDataEventUI(mc, id), 900, 725);
            //stage.getIcons().add(ImageManager.getImage("pacman-icon.png"));
            stage.setScene(scene);
            stage.setTitle("Editar dados do evento");

            stage.setMinWidth(1000);
            stage.setMinHeight(700);

            stage.show();
        });

        btnDeleteEvent.setOnAction(event -> {
            if(mc.deleteEvent(PopUpCreator.deleteEventPopUp())){
                lblResultado.setText("Evento apagado com sucesso!");
                lblResultado.setStyle("-fx-text-fill: green; -fx-font-size: 25px; -fx-font-weight: bold;");
                lblResultado.setVisible(true);

                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(e -> {
                    lblResultado.setVisible(false);
                });
                pause.play();
            }
            else{
                lblResultado.setText("Houve um erro ao apagar o evento!\n[Verifique se o evento tem presenças registadas]");
                lblResultado.setStyle("-fx-text-fill: red; -fx-font-size: 25px; -fx-font-weight: bold;");
                lblResultado.setVisible(true);

                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(e -> {
                    lblResultado.setVisible(false);
                });
                pause.play();
            }
        });

        btnCheckEvents.setOnAction(event->{
            ArrayList<ArrayList<String>> listaEventos = mc.checkEvents();

            PopUpCreator.checkEventsPopUp(listaEventos);
        });

        btnGenerateCode.setOnAction(event->{
            if(mc.generateEventCode(PopUpCreator.generateCodePopUp())){
                lblResultado.setText("Codigo gerado com sucesso!");
                lblResultado.setStyle("-fx-text-fill: green; -fx-font-size: 25px; -fx-font-weight: bold;");
                lblResultado.setVisible(true);

                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(e -> {
                    lblResultado.setVisible(false);
                });
                pause.play();
            }
            else{
                lblResultado.setText("Houve um erro ao gerar o código do evento!");
                lblResultado.setStyle("-fx-text-fill: red; -fx-font-size: 25px; -fx-font-weight: bold;");
                lblResultado.setVisible(true);

                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(e -> {
                    lblResultado.setVisible(false);
                });
                pause.play();
            }
        });

        btnCheckEventPresences.setOnAction(event->{
            String id = PopUpCreator.checkEventPresencesPopUp();

            ArrayList<ArrayList<String>> listaPresencas = mc.checkPresencesEvent(id);

            PopUpCreator.checkEventPresencesPopUp_list(listaPresencas);
        });

        btnGenerateCSV1.setOnAction(event->{
            PopUpCreator.generateCSV1PopUp();
        });


        btnCheckStudentPresences.setOnAction(event->{

            String email = PopUpCreator.checkStudentPresencesPopUp();

            ArrayList<ArrayList<String>> listaPresencas_doAluno = mc.checkPresences2(email);

            PopUpCreator.checkStudentPresencesPopUp_list(listaPresencas_doAluno);


        });
        btnGenerateCSV2.setOnAction(event->{
            String email = PopUpCreator.generateSCV2PopUp();

            if(mc.generateCSV_student(email)){
                lblResultado.setText("Ficheiro CSV gerado com sucesso!");
                lblResultado.setStyle("-fx-text-fill: green; -fx-font-size: 25px; -fx-font-weight: bold;");
                lblResultado.setVisible(true);

                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(e -> {
                    lblResultado.setVisible(false);
                });

                pause.play();
            }
            else{
                lblResultado.setText("Houve um erro ao gerar o ficheiro CSV!");
                lblResultado.setStyle("-fx-text-fill: red; -fx-font-size: 25px; -fx-font-weight: bold;");
                lblResultado.setVisible(true);

                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(e -> {
                    lblResultado.setVisible(false);
                });
                pause.play();
            }
        });

        btnDeletePresence.setOnAction(event->{
            String id = PopUpCreator.presencePopUp_idEvento();
            String email = PopUpCreator.presencePopUp_email();

            if(mc.deletePresence(id,email)){
                lblResultado.setText("Presença apagada com sucesso!");
                lblResultado.setStyle("-fx-text-fill: green; -fx-font-size: 25px; -fx-font-weight: bold;");
                lblResultado.setVisible(true);

                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(e -> {
                    lblResultado.setVisible(false);
                });
                pause.play();
            }
            else{
                lblResultado.setText("Houve um erro ao apagar a presença!");
                lblResultado.setStyle("-fx-text-fill: red; -fx-font-size: 25px; -fx-font-weight: bold;");
                lblResultado.setVisible(true);

                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(e -> {
                    lblResultado.setVisible(false);
                });
                pause.play();
            }
        });

       btnAddPresence.setOnAction(event->{
           String id= PopUpCreator.presencePopUp_idEvento();
           String email= PopUpCreator.presencePopUp_email();

           if(mc.addPresence(id,email)){
                lblResultado.setText("Presença adicionada com sucesso!");
                lblResultado.setStyle("-fx-text-fill: green; -fx-font-size: 25px; -fx-font-weight: bold;");
                lblResultado.setVisible(true);

                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(e -> {
                     lblResultado.setVisible(false);
                });
                pause.play();
              }
              else{
                lblResultado.setText("Houve um erro ao adicionar a presença!");
                lblResultado.setStyle("-fx-text-fill: red; -fx-font-size: 25px; -fx-font-weight: bold;");
                lblResultado.setVisible(true);

                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(e -> {
                     lblResultado.setVisible(false);
                });
                pause.play();
              }
        });




        btnLogout.setOnAction(event -> {
            mc.logout();
            RootPane.setShowAdminMenu(false);
            RootPane.setShowLogin(true);
        });

    }


    private void update(){
        if(RootPane.showAdminMenu){
            this.setVisible(true);
        }else{
            this.setVisible(false);
        }
    }
}
