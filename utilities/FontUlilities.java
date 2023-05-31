package com.example.bookstore.utilities;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class FontUlilities {
    private static final int Heading1size = 40;
    private static final int Heading2size = 30;
    private static final int Heading3size = 25;
    private static final int Heading4size = 20;
    private static final int Heading5size = 15;
    public static Font heading1 = new Font("SansSerif", Heading1size);
    public static Font heading1bold = Font.font("SansSerif", FontWeight.BOLD, FontPosture.REGULAR, Heading1size);
    public static Font heading2 = new Font("SansSerif", Heading2size);
    public static Font heading2bold = Font.font("SansSerif", FontWeight.BOLD, FontPosture.REGULAR, Heading2size);
    public static Font heading3 = new Font("SansSerif", Heading3size);
    public static Font heading3bold = Font.font("SansSerif", FontWeight.BOLD, FontPosture.REGULAR, Heading3size);
    public static Font heading4 = new Font("SansSerif", Heading4size);
    public static Font heading4bold = Font.font("SansSerif", FontWeight.BOLD, FontPosture.REGULAR, Heading4size);
    public static Font heading5 = new Font("SansSerif", Heading5size);
    public static Font heading5bold = Font.font("SansSerif", FontWeight.BOLD, FontPosture.REGULAR, Heading5size);


}
