package com.cis3296.virtualchess.Components;

import javafx.scene.paint.Color;

/**
 * Themes for the board.
 * Contains preset BoardStyles
 */
public class Style {

    // The name of the style
    public String styleName;
    // The color the black king will be on
    public Color squareColor1;
    // The color the white king will be on
    public Color squareColor2;

    // A Tan Theme
    public static final Style SANDCASTLE = new Style(
            "Sandcastle",
            Color.web("#e4c16f"),
            Color.web("#b88b4a")
    );

    // A Teal Theme
    public static final Style CORAL = new Style(
            "Coral",
            Color.web("#b1e4b9"),
            Color.web("#70a2a3")
    );

    // A Dark Purple Theme
    public static final Style DUSK = new Style(
            "Dusk",
            Color.web("#cbb7ae"),
            Color.web("#716677")
    );

    // A White and Yellow Theme
    public static final Style WHEAT = new Style(
            "Wheat",
            Color.web("#eaefce"),
            Color.web("#bbbe65")
    );

    // A Blue Theme
    public static final Style MARINE = new Style(
            "Marine",
            Color.web("#9dacff"),
            Color.web("#6f74d2")
    );

    // A Green Theme
    public static final Style EMERALD = new Style(
            "Emerald",
            Color.web("#adbd90"),
            Color.web("#6e8f72")
    );

    /**
     * Constructor for BoardStyle
     * @param name Name of the theme
     * @param color1 Black King Color
     * @param color2 White King Color
     */
    public Style(String name, Color color1, Color color2){
        this.styleName = name;
        this.squareColor1 = color1;
        this.squareColor2 = color2;
    }
}
