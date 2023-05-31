package com.example.bookstore.customclasses;

import com.example.bookstore.utilities.ColorUtilities;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class FancyButton extends Button {
    Color stroke = null;
    Color fill = null;
    String Text = "Empty";

    FancyButton() {
        super();
    }

    FancyButton(String Text, Color color) {
        super(Text);
        super.setStyle("-fx-border-color:" + ColorUtilities.getColorhex(color) + ";");
    }

    public FancyButton(String Text, Font f) {
        super(Text);
        super.setFont(f);
    }

    FancyButton(String Text, Color bordercolor, Color fillcolor, Color textColor, Font f, int radius) {
        super(Text);
        super.setStyle("-fx-border-color:" + ColorUtilities.getColorhex(bordercolor) + ";" + "-fx-background-color:" + ColorUtilities.getColorhex(fillcolor)
                + ";" + "-fx-background-radius:" + radius + ";-fx-border-radius:" + radius + ";-fx-text-fill:" + ColorUtilities.getColorhex(textColor) + ";");
        // super.setTextFill();
        super.setFont(f);

    }

    public FancyButton(String Text, Color fillcolor, Color textColor, Font f, int radius) {
        super(Text);
        super.setStyle("-fx-background-color:" + ColorUtilities.getColorhex(fillcolor) + ";" + "-fx-background-radius:" + radius +
                ";-fx-border-radius:" + radius + ";-fx-text-fill:" + ColorUtilities.getColorhex(textColor));
        // super.setTextFill();
        super.setFont(f);

    }


    FancyButton(String Text, Color color, Color stroke) {
        super(Text);


    }


}
