package pt.isec.pd.a2020136093.client.ui.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainJFX extends Application {

    @Override
    public void init() throws Exception{
        super.init();
    }

    @Override
    public void start(Stage stage){
        initGUI(new Stage(), "Presence Register");
    }

    private void initGUI(Stage stage, String title){
        RootPane rootPane = new RootPane();
        Scene scene = new Scene(rootPane,900,725);
        //stage.getIcons().add(ImageManager.getImage("pacman-icon.png"));
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setMinWidth(1000);
        stage.setMinHeight(700);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}
