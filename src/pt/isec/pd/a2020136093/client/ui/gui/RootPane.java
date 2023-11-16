package pt.isec.pd.a2020136093.client.ui.gui;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.*;
import pt.isec.pd.a2020136093.client.communication.ManageConnections;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class RootPane extends BorderPane {
    ManageConnections mc;
    static PropertyChangeSupport pcs;
    public static boolean showMainMenu = true;
    public static boolean showLogin = false;
    public static boolean showRegister = false;


    public RootPane(ManageConnections manageConnections){
        this.mc = manageConnections;

        if(this.mc == null)
            System.out.println("NULO NO ROOTPANE");

        pcs = new PropertyChangeSupport(this);

        createViews();
        registerHandlers();
        update();
    }

    private void createViews(){
        //CSSManager.applyCSS(this,"styles.css");

        StackPane stackPane = new StackPane(
                new MainMenuUI(mc),
                new LoginUI(mc),
                new RegisterUI(mc)
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
    public static void setShowRegister(boolean b){
        showRegister = b;
        pcs.firePropertyChange("SHOWREGISTER", null, null);
    }


}