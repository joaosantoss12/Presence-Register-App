package pt.isec.pd.a2020136093.client.ui.gui.STUDENT;

import javafx.animation.PauseTransition;
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
import javafx.stage.Stage;
import javafx.util.Duration;
import pt.isec.pd.a2020136093.client.communication.ManageConnections;
import pt.isec.pd.a2020136093.client.ui.gui.RESOURCES.PopUpCreator;

public class EditDataUI extends BorderPane {
    ManageConnections mc;
    Font titleFont, buttonsFont;

    Label lblTitle, lblResultado;
    Button btnEditName, btnEditEmail, btnEditPassword, btnEditIDNumber, btnBack;

    public EditDataUI(ManageConnections mc) {
        this.mc = mc;

        //titleFont = FontManager.loadFont("PAC-FONT.TTF",69);
        //buttonsFont = FontManager.loadFont("PressStart2P-Regular.ttf",12);

        createViews();
        registerHandlers();
        update();
    }


    private void createViews() {
        this.setBackground(new Background(new BackgroundFill(Color.rgb(240, 240, 240), null, null)));

        lblTitle = new Label("Editar dados de registo");
        lblTitle.setStyle("-fx-text-fill: #333; -fx-font-size: 36px; -fx-font-weight: bold;");

        lblResultado = new Label("");
        lblResultado.setVisible(false);

        btnEditName = createStyledButton("Editar nome");
        btnEditName.setMinWidth(120);
        btnEditEmail = createStyledButton("Editar email");
        btnEditEmail.setMinWidth(120);
        btnEditPassword = createStyledButton("Editar password");
        btnEditPassword.setMinWidth(120);
        btnEditIDNumber = createStyledButton("Editar numero de identificacao");
        btnEditIDNumber.setMinWidth(120);

        btnBack = createStyledButton("Voltar");
        btnBack.setMinWidth(120);


        VBox vBox = new VBox(lblTitle, btnEditName, btnEditEmail, btnEditPassword, btnEditIDNumber, btnBack, lblResultado);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);
        VBox.setMargin(btnEditName, new Insets(25, 0, 0, 0)); // Set top margin for the button
        VBox.setMargin(btnEditName, new Insets(50, 0, 0, 0)); // Set top margin for the button

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
            if(mc.editData( 1, PopUpCreator.editName())){
                lblResultado.setText("Nome alterado com sucesso!");
                lblResultado.setStyle("-fx-text-fill: green; -fx-font-size: 25px; -fx-font-weight: bold;");
                lblResultado.setVisible(true);

                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(e -> {
                    lblResultado.setVisible(false);
                });
                pause.play();
            }
            else{
                lblResultado.setText("Houve um erro ao alterar o nome!");
                lblResultado.setStyle("-fx-text-fill: red; -fx-font-size: 25px; -fx-font-weight: bold;");
                lblResultado.setVisible(true);

                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(e -> {
                    lblResultado.setVisible(false);
                });
                pause.play();
            }
        });

        btnEditEmail.setOnAction(event -> {
            if(mc.editData( 2, PopUpCreator.editEmail())){
                lblResultado.setText("Email alterado com sucesso!");
                lblResultado.setStyle("-fx-text-fill: green; -fx-font-size: 25px; -fx-font-weight: bold;");
                lblResultado.setVisible(true);

                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(e -> {
                    lblResultado.setVisible(false);
                });
                pause.play();
            }
            else{
                lblResultado.setText("Houve um erro ao alterar o email!");
                lblResultado.setStyle("-fx-text-fill: red; -fx-font-size: 25px; -fx-font-weight: bold;");
                lblResultado.setVisible(true);

                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(e -> {
                    lblResultado.setVisible(false);
                });
                pause.play();
            }
        });

        btnEditPassword.setOnAction(event -> {
            if(mc.editData( 3, PopUpCreator.editPassword())){
                lblResultado.setText("Password alterada com sucesso!");
                lblResultado.setStyle("-fx-text-fill: green; -fx-font-size: 25px; -fx-font-weight: bold;");
                lblResultado.setVisible(true);

                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(e -> {
                    lblResultado.setVisible(false);
                });
                pause.play();
            }
            else{
                lblResultado.setText("Houve um erro ao alterar a password!");
                lblResultado.setStyle("-fx-text-fill: red; -fx-font-size: 25px; -fx-font-weight: bold;");
                lblResultado.setVisible(true);

                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(e -> {
                    lblResultado.setVisible(false);
                });
                pause.play();
            }
        });

        btnEditIDNumber.setOnAction(event -> {
            if(mc.editData( 1, PopUpCreator.editIDNumber())){
                lblResultado.setText("Número de Indentificação alterado com sucesso!");
                lblResultado.setStyle("-fx-text-fill: green; -fx-font-size: 25px; -fx-font-weight: bold;");
                lblResultado.setVisible(true);

                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(e -> {
                    lblResultado.setVisible(false);
                });
                pause.play();
            }
            else{
                lblResultado.setText("Houve um erro ao alterar o número de identificação!");
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
        /*if(RootPane.showMainMenu){
            this.setVisible(true);
        }else{
            this.setVisible(false);
        }*/
    }

}
