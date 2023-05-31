package com.example.bookstore.customclasses;

import com.example.bookstore.utilities.ColorUtilities;
import com.example.bookstore.utilities.FontUlilities;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LabledInput extends VBox {
    Label label;
    TextField textField = new TextField("");
    TextArea textArea;
    private static int FIELD_WIDTH=300;
    boolean isbig = false;

    LabledInput() {
        super();
    }

    LabledInput(String FieldName) {
        super(10);
        label = new Label(FieldName);
        label.setFont(FontUlilities.heading2bold);
        textField.setPrefColumnCount(25);
        textField.setPrefWidth(FIELD_WIDTH);
        super.getChildren().addAll(label, textField);
    }

    LabledInput(String FieldName, Color color) {
        super(5);
        label = new Label(FieldName);
        label.setFont(FontUlilities.heading2bold);
        label.setStyle("-fx-text-fill:" + ColorUtilities.getColorhex(color) + ";");
        textField.setPrefColumnCount(25);
        textField.setPrefWidth(FIELD_WIDTH);
        super.getChildren().addAll(label, textField);
    }


    public LabledInput(String FieldName, Color color, Font f) {
        super(5);
        label = new Label(FieldName);
        label.setFont(f);
        label.setStyle("-fx-text-fill:" + ColorUtilities.getColorhex(color) + ";");
        textField.setPrefColumnCount(25);
        super.getChildren().addAll(label, textField);
    }

    public LabledInput(String FieldName, Color color, Font f, boolean isBig) {
        super(5);
        label = new Label(FieldName);
        label.setFont(f);
        label.setStyle("-fx-text-fill:" + ColorUtilities.getColorhex(color) + ";");
        this.isbig = isBig;
        textField.setPrefColumnCount(25);
        if (isBig) {
            textArea = new TextArea();
            textArea.setPrefColumnCount(50);
            textArea.setPrefRowCount(10);
            super.getChildren().addAll(label, textArea);
        } else
            super.getChildren().addAll(label, textField);
    }

    public LabledInput(String FieldName, Color color, Font f, boolean isBig, boolean ispassword) {
        super(5);
        label = new Label(FieldName);
        label.setFont(f);
        label.setStyle("-fx-text-fill:" + ColorUtilities.getColorhex(color) + ";");
        this.isbig = isBig;
        textField.setPrefColumnCount(25);
        if (isBig) {
            textArea = new TextArea();
            textArea.setPrefColumnCount(50);
            textField.setPrefWidth(FIELD_WIDTH);
            super.getChildren().addAll(label, textArea);
        } else if (ispassword)
            this.textField = new PasswordField();
        super.getChildren().addAll(label, textField);
    }

    public TextField getTextField() {
        if (!isbig)
            return textField;

        return null;
    }

    public TextArea getTextArea() {
        if(isbig) return textArea;
        else return null;
    }



    public String getText() {
        if (isbig)
            return textArea.getText();
        else
            return textField.getText();
    }
}
