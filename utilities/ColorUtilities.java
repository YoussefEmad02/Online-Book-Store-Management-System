package com.example.bookstore.utilities;

import javafx.scene.paint.Color;

import java.util.Locale;

public class ColorUtilities {

    public static Color BABYBLUE = new Color((double) 69 / 255, (double) 178 / 255, (double) 222 / 255, 1.00);
    public static Color DEADBLUE = new Color((double) 50 / 255, (double) 137 / 255, (double) 200 / 255, 1.00);
    public static String getColorhex(Color a) {
        String hex = String.format("#%02x%02x%02xFF",
                (int) (a.getRed() * 255),
                (int) (a.getGreen() * 255),
                (int) (a.getBlue() * 255)).toUpperCase(Locale.ROOT);
        return hex;
    }
}
