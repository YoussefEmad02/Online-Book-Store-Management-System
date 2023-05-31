package com.example.bookstore.listitems;

import com.example.bookstore.customclasses.ColordLabel;
import com.example.bookstore.customclasses.FancyButton;
import com.example.bookstore.mainclasses.Book;
import com.example.bookstore.utilities.ColorUtilities;
import com.example.bookstore.utilities.FontUlilities;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class CartListItem extends HBox{



    CartListItem() {
    }
    FancyButton removeBook= new FancyButton("Remove book", Color.RED, Color.WHITE, FontUlilities.heading4bold,(int) (FontUlilities.heading4bold.getSize()));

    public CartListItem(Book book) {
        super(20);
        this.setMaxWidth(700);
        ImageView cover = new ImageView(new Image(book.getCoverurl()));
        HBox.setMargin(this,new Insets(10));
        cover.setFitWidth(80);
        cover.setFitHeight(1.4*80);
        VBox data=new VBox(10);
        ColordLabel name= new ColordLabel(book.getName(), FontUlilities.heading3bold, ColorUtilities.BABYBLUE);
        ColordLabel cost= new ColordLabel("Price: "+ String.valueOf(book.getFinalCost()), FontUlilities.heading4bold, Color.DARKBLUE.darker());
        data.getChildren().addAll(name,cost);
        data.setMinWidth(380);
        this.getChildren().addAll(cover,data,removeBook);

        this.setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID,
                new CornerRadii(0), new BorderWidths(0, 0, 1, 0))));

    }

    public Button getRemoveBookButton() {
        return this.removeBook;
    }
}
