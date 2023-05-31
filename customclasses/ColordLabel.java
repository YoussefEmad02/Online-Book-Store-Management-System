package com.example.bookstore.customclasses;

import com.example.bookstore.utilities.ColorUtilities;
import com.example.bookstore.utilities.FontUlilities;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ColordLabel extends javafx.scene.control.Label {
    String Label;
    Font font;
    Color color;
    String output = "";
    boolean isunderlined;

    public void setLabel(String label) {
        Label = label;
    }


    public void setColor(Color color) {
        this.color = color;
        this.setTextFill(color);

    }

    public void setIsunderlined(boolean isunderlined) {

        if(isunderlined){
            super.setStyle("-fx-text-fill:" + ColorUtilities.getColorhex(color) + ";-fx-underline:" + isunderlined + ";");
        }else{
            super.setStyle("-fx-text-fill:" + ColorUtilities.getColorhex(color) + ";");
        }
        this.isunderlined = isunderlined;
    }

    public String getLabel() {
        return Label;
    }

    public Font getfont() {
        return font;
    }

    public Color getColor() {
        return color;
    }

    public boolean isIsunderlined() {
        return isunderlined;
    }

    ColordLabel() {
        super();
    }

    ColordLabel(String Label, Font f) {
        this.Label = Label;
        this.font = f;
        this.setText(Label);
        super.setFont(f);
    }


    public ColordLabel(String Label, Font f, Color color) {
        this(Label, f);
        this.color = color;
        this.setStyle("-fx-text-fill:" + ColorUtilities.getColorhex(color) + ";");
    }

    public ColordLabel(String Label, Font f, Color color, boolean isunderlined) {
        this(Label, f, color);
        super.setStyle("-fx-text-fill:" + ColorUtilities.getColorhex(color) + ";-fx-underline:" + isunderlined + ";");
    }

    public ColordLabel(String Label, boolean isunderlined) {
        super(Label);
        super.setStyle("-fx-underline:" + isunderlined + ";");
    }

    public ColordLabel(ColordLabel a) {

        this(a.getLabel(), a.getfont(), a.getColor(), a.isIsunderlined());
        this.Label = a.getLabel();
        this.font = a.getFont();
        this.color = a.getColor();
        this.isunderlined = a.isIsunderlined();

    }

    public ColordLabel(String s, ColordLabel a) {

        this(s, a.getfont(), a.getColor(), a.isIsunderlined());
        this.Label = s;
        this.font = a.getFont();
        this.color = a.getColor();
        this.isunderlined = a.isIsunderlined();

    }

    public String getOutput() {
        return output;
    }

    public void setClicklistener(ColordLabel a) {
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                LabledInput input = new LabledInput("Enter new value", ColorUtilities.BABYBLUE, FontUlilities.heading2bold);
                StackPane inputpne = new StackPane(input);
                inputpne.setPadding(new Insets(30));
                Scene inputScene = new Scene(inputpne);
                Stage stage = new Stage();
                stage.setTitle("Edit value");
                stage.getIcons().add(new Image("USERLOGO.png"));
                stage.setScene(inputScene);
                stage.show();
                input.getTextField().setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                            if(!input.getTextField().getText().isEmpty())
                            {
                                output = input.getTextField().getText();
                            a.setText(output);
                            a.setColor(Color.GREY);}
                            stage.close();
                        }
                    }
                });
            }
        });

    }


    public void setClicklistener(Hyperlink a) {
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                LabledInput input = new LabledInput("Enter new value", ColorUtilities.BABYBLUE, FontUlilities.heading2bold);
                StackPane inputpne = new StackPane(input);
                inputpne.setPadding(new Insets(30));
                Scene inputScene = new Scene(inputpne);
                Stage stage = new Stage();
                stage.setTitle("Edit value");
                stage.getIcons().add(new Image("USERLOGO.png"));
                stage.setScene(inputScene);
                stage.show();
                input.getTextField().setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                            if(!input.getTextField().getText().isEmpty())
                            {output = input.getTextField().getText();
                                a.setText(output);
                            }
                            stage.close();
                        }
                    }
                });
            }
        });

    }


    public void setClicklistener(Text a) {
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                LabledInput input = new LabledInput("Enter new value", ColorUtilities.BABYBLUE, FontUlilities.heading2bold);
                StackPane inputpne = new StackPane(input);
                inputpne.setPadding(new Insets(30));
                Scene inputScene = new Scene(inputpne);
                Stage stage = new Stage();
                stage.setTitle("Edit value");
                stage.getIcons().add(new Image("USERLOGO.png"));
                stage.setScene(inputScene);
                stage.show();
                input.getTextField().setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                            if(!input.getTextField().getText().isEmpty())
                            {output = input.getTextField().getText();
                                a.setText(output);
                            }
                            stage.close();
                        }
                    }
                });
            }
        });

    }

}
