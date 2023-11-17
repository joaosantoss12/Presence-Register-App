package pt.isec.pd.a2020136093.client.ui.gui.STUDENT;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import pt.isec.pd.a2020136093.client.communication.ManageConnections;
import pt.isec.pd.a2020136093.client.ui.gui.RESOURCES.PopUpCreator;
import pt.isec.pd.a2020136093.client.ui.gui.RootPane;

import java.util.ArrayList;

public class MenuStudentUI extends BorderPane {
    ManageConnections mc;
    Font titleFont, buttonsFont;

    Label lblTitle, lblResultado;
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

        lblResultado = new Label("");
        lblResultado.setVisible(false);

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


        VBox vBox = new VBox(lblTitle, btnEditData, btnSubmitCode, btnPresences, btnCSV, btnLogout, lblResultado);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);
        VBox.setMargin(btnEditData, new Insets(25, 0, 0, 0)); // Set top margin for the button
        VBox.setMargin(lblResultado, new Insets(50, 0, 0, 0)); // Set top margin for the button

        this.setCenter(vBox);
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-text-fill: black; -fx-font-size: 16px; ");
        return button;
    }


    private void registerHandlers() {
        RootPane.addPropertyChangeListener("SHOWMENU", evt -> {
            update();
        });
        RootPane.addPropertyChangeListener("SHOWSTUDENTMENU", evt -> {
            update();
        });

        btnEditData.setOnAction(event -> {
            Stage stage = new Stage();
            Scene scene = new Scene(new EditDataUI(mc), 900, 725);
            //stage.getIcons().add(ImageManager.getImage("pacman-icon.png"));
            stage.setScene(scene);
            stage.setTitle("Editar dados da conta");

            stage.setMinWidth(1000);
            stage.setMinHeight(700);

            stage.show();
        });

        btnSubmitCode.setOnAction(event -> {
            if (mc.submitCode(PopUpCreator.sendPresenceCode())) {
                lblResultado.setText("Codigo submetido com sucesso!");
                lblResultado.setStyle("-fx-text-fill: green; -fx-font-size: 25px; -fx-font-weight: bold;");
                lblResultado.setVisible(true);

                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(e -> {
                    lblResultado.setVisible(false);
                });
                pause.play();
            }
            else {
                lblResultado.setText("Codigo invalido ou presença já registada!");
                lblResultado.setStyle("-fx-text-fill: red; -fx-font-size: 25px; -fx-font-weight: bold;");
                lblResultado.setVisible(true);

                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(e -> {
                    lblResultado.setVisible(false);
                });
                pause.play();
            }
        });

        btnPresences.setOnAction(event -> {
            ArrayList<ArrayList<String>> listaPresencas = mc.checkPresences();

            PopUpCreator.checkPresencesPopUp(listaPresencas);
        });


        btnCSV.setOnAction(event -> {
            if (mc.generateCSV_student_own()) {
                lblResultado.setText("Ficheiro CSV gerado com sucesso!");
                lblResultado.setStyle("-fx-text-fill: green; -fx-font-size: 25px; -fx-font-weight: bold;");
                lblResultado.setVisible(true);

                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(e -> {
                    lblResultado.setVisible(false);
                });
                pause.play();
            }
            else {
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


        btnLogout.setOnAction(event -> {
            mc.logout();
            RootPane.setShowStudentMenu(false);
            RootPane.setShowLogin(true);
        });

    }


    private void update(){
        if(RootPane.showStudentMenu){
            this.setVisible(true);
            lblTitle.setText("Bem-vindo " + mc.getName());
        }
        else{
            this.setVisible(false);
        }
    }

}
