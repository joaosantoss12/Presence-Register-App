package pt.isec.pd.a2020136093.client.ui.gui;

import javafx.scene.layout.*;


public class RootPane extends BorderPane {


    public RootPane(){

        createViews();
        registerHandlers();
        update();
    }

    private void createViews(){
        CSSManager.applyCSS(this,"styles.css");

        StackPane stackPane = new StackPane(
                //new MainMenuUI(tinyPacmanManager),
                //new TopFiveUI(tinyPacmanManager),
                //new CreditsUI(tinyPacmanManager),
                //new GameUI(tinyPacmanManager),
                //new PacmanWonUI(tinyPacmanManager),
                //new PacmanLostUI(tinyPacmanManager)
        );

        this.setCenter(stackPane);
    }

    private void registerHandlers(){
    }

    private void update() {
    }


}