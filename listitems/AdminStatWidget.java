package com.example.bookstore.listitems;

import com.example.bookstore.customclasses.ColordLabel;
import com.example.bookstore.mainclasses.Book;
import com.example.bookstore.utilities.ColorUtilities;
import com.example.bookstore.utilities.FontUlilities;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

 public class AdminStatWidget extends VBox{
    private static final double CARD_WIDTH = 200;
    // Rectangle card=new Rectangle(0,0,CARD_WIDTH*1.1,CARD_HEIGHT*1.3);

    private ImageView widgetIcon = new ImageView("USERLOGO.png" +
            "");
    private ColordLabel widgetLabel = new ColordLabel("Unnamed", FontUlilities.heading2bold, ColorUtilities.DEADBLUE);
    private ColordLabel statNumber = new ColordLabel("0", FontUlilities.heading4bold, Color.DARKBLUE);


    public ImageView getWidgetIcon() {
        return widgetIcon;
    }


    public ColordLabel getWidgetLabel() {
        return widgetLabel;
    }

    public ColordLabel getStatNumber() {
        return statNumber;
    }



    public AdminStatWidget() {
        super(5);
        widgetIcon.setFitHeight(CARD_WIDTH);
        widgetIcon.setFitWidth(CARD_WIDTH);
        this.setMaxWidth(CARD_WIDTH + 20);
        this.setMaxHeight(CARD_WIDTH + 20);
        this.setPadding(new Insets(3, 3, 3, 3));



        this.getChildren().add(widgetIcon);
        this.getChildren().add(widgetLabel);
        this.getChildren().add(statNumber);

        this.setAlignment(Pos.CENTER);

        this.setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, new CornerRadii(CARD_WIDTH / 30), new BorderWidths(5, 5, 5, 5))));


    }



    public AdminStatWidget(String iconuri, String widgetLabel, int statNumber) {

        super(5);
        super.setMaxWidth(CARD_WIDTH);
        super.setMaxHeight(CARD_WIDTH);
        widgetIcon.setFitHeight(CARD_WIDTH);
        widgetIcon.setFitWidth(CARD_WIDTH);

        // this.SetOnClickListener();
        try {
            this.widgetIcon.setImage(new Image(iconuri));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        this.widgetLabel.setText(widgetLabel);
        this.statNumber.setText(String.valueOf(statNumber));
        this.setPadding(new Insets(3, 3, 3, 3));



        this.getChildren().add(widgetIcon);
        this.getChildren().add(this.widgetLabel);
        this.getChildren().add(this.statNumber);

        this.setAlignment(Pos.CENTER);

        this.setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, new CornerRadii(CARD_WIDTH / 30), new BorderWidths(5, 5, 5, 5))));

    }


}
