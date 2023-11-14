package pt.isec.pd.a2020136093.client.ui.gui;

import javafx.scene.text.Font;

import java.io.InputStream;

public class FontManager {

    private FontManager(){}

    public static Font loadFont(String filename, int size) {
        try(InputStream inputStreamFont =
                    FontManager.class.getResourceAsStream("fonts/" + filename)) {
            return Font.loadFont(inputStreamFont, size);
        } catch (Exception e) {
            return null;
        }
    }
}