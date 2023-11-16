package pt.isec.pd.a2020136093.client.ui.gui;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class RootPane extends BorderPane {
    static PropertyChangeSupport pcs;
    public static boolean showMainMenu = true;
    public static boolean showLogin = false;


    public RootPane(){
        pcs = new PropertyChangeSupport(this);

        createViews();
        registerHandlers();
        update();
    }

    private void createViews(){
        //CSSManager.applyCSS(this,"styles.css");

        StackPane stackPane = new StackPane(
                new MainMenuUI(),
                new LoginUI()
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


    public static void addPropertyChangeListener(String property, PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(property, listener);
    }

    public static void setShowMainMenu(boolean b){
        showMainMenu = b;
        pcs.firePropertyChange("SHOWMENU", null, null);
    }
    public static void setShowLogin(boolean b){
        showLogin = b;
        pcs.firePropertyChange("SHOWLOGIN", null, null);
    }


}