package com.example.bookstore.listitems;

import com.example.bookstore.customclasses.ColordLabel;
import com.example.bookstore.mainclasses.Book;
import com.example.bookstore.utilities.ColorUtilities;
import com.example.bookstore.utilities.FontUlilities;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class BookListItem extends VBox {
    private static final double CARD_WIDTH = 200;
    private static final double CARD_HEIGHT = CARD_WIDTH * 1.424;
    final int BORDER_WIDTH=3;
    final int PADDING_WIDTH=3;
    final Color CARDCOLOR= Color.DARKGRAY;
    // Rectangle card=new Rectangle(0,0,CARD_WIDTH*1.1,CARD_HEIGHT*1.3);

    ImageView  bookcover = new ImageView("USERLOGO.png");
    ColordLabel bookName = new ColordLabel("Unnamed", FontUlilities.heading4, ColorUtilities.BABYBLUE);
    ColordLabel cost = new ColordLabel("UnPriced", FontUlilities.heading5bold, ColorUtilities.BABYBLUE);
    ColordLabel discont = new ColordLabel("%off!", FontUlilities.heading5bold, Color.RED.darker());
    Text description = new Text("No Description");
    Book book;
    public boolean hasDiscount() {
        return hasDiscount;
    }

    public ImageView getBookcover() {
        return bookcover;
    }

    boolean hasDiscount;
    int idfornow = 0;

    public ColordLabel getBookName() {
        return bookName;
    }

    public ColordLabel getCost() {
        return cost;
    }

    public ColordLabel getDiscont() {
        return discont;
    }

    int getIdfornow() {
        return idfornow;
    }

    String FinalCost(double cost) {
        return String.format("%.2f", cost);
    }

    private BookListItem() {
        super(5);
        super.setMaxWidth(CARD_WIDTH + 20);
        super.setMaxHeight(CARD_HEIGHT + 20);

        this.setPadding(new Insets(PADDING_WIDTH));

        bookcover.setFitHeight(CARD_HEIGHT - (int) (CARD_HEIGHT / 20));
        bookcover.setFitWidth(CARD_WIDTH - (int) (CARD_WIDTH / 20));

        this.getChildren().add(bookcover);
        this.getChildren().add(bookName);
        this.getChildren().add(cost);


        this.setBorder(new Border(new BorderStroke(CARDCOLOR, BorderStrokeStyle.SOLID, new CornerRadii(CARD_WIDTH / 30), new BorderWidths(BORDER_WIDTH))));


    }

    public Text getDescription() {
        return description;
    }



    public BookListItem(Book book) {

        super(5);
        super.setMaxWidth(CARD_WIDTH);
        super.setMaxHeight(CARD_HEIGHT);
        this.setPadding(new Insets(PADDING_WIDTH));
        //this.SetOnClickListener();
        VBox.setMargin(this,new Insets(10));
        this.book=book;

            try {
                this.bookcover.setImage(new Image(book.getCoverurl()));
            }
            catch (Exception e){
                System.out.println((book.getCoverurl()));
            }

        this.bookName.setText(book.getName());
        this.hasDiscount = book.hasDiscount();
        this.idfornow = book.getId();
        this.description.setText(book.getDescription());


        this.discont= new ColordLabel(String.valueOf(book.getDiscount()) + "%off!",this.discont);

        this.setLayoutX(CARD_WIDTH * 2);
        this.setWidth(CARD_WIDTH * 1.1);

        bookcover.setFitHeight(CARD_HEIGHT - (int) (CARD_HEIGHT / 17));
        bookcover.setFitWidth(CARD_WIDTH - (int) (CARD_WIDTH / 17));

        this.getChildren().add(bookcover);
        this.getChildren().add(this.bookName);
        if (!hasDiscount)
        {
            this.cost = new ColordLabel(FinalCost(book.getCost()) + " EGP ", this.cost);
            this.getChildren().add(cost);
        }
        else
        {
            this.cost = new ColordLabel(String.valueOf(FinalCost(((1.0 - book.getDiscount() / 100) * book.getCost()))) + "  EGP ", this.cost);
            GridPane CostwithDiscount = getCostAfterdiscount();
            this.getChildren().add(CostwithDiscount);
        }
        this.setBorder(new Border(new BorderStroke(CARDCOLOR, BorderStrokeStyle.SOLID, new CornerRadii(CARD_WIDTH / 30), new BorderWidths(BORDER_WIDTH))));
    }

    public BookListItem(Book book,boolean isOwned) {

        super(5);
        super.setMaxWidth(CARD_WIDTH);
        super.setMaxHeight(CARD_HEIGHT);
        this.setPadding(new Insets(PADDING_WIDTH));
        //this.SetOnClickListener();
        VBox.setMargin(this,new Insets(10));
        this.book=book;

        try {
            this.bookcover.setImage(new Image(book.getCoverurl()));
        }
        catch (Exception e){
            System.out.println((book.getCoverurl()));
        }

        this.bookName.setText(book.getName());
        this.hasDiscount = book.hasDiscount();
        this.idfornow = book.getId();
        this.description.setText(book.getDescription());


        this.discont= new ColordLabel(String.valueOf(book.getDiscount()) + "%off!",this.discont);

        this.setLayoutX(CARD_WIDTH * 2);
        this.setWidth(CARD_WIDTH * 1.1);

        bookcover.setFitHeight(CARD_HEIGHT - (int) (CARD_HEIGHT / 17));
        bookcover.setFitWidth(CARD_WIDTH - (int) (CARD_WIDTH / 17));

        this.getChildren().add(bookcover);
        this.getChildren().add(this.bookName);
        this.setBorder(new Border(new BorderStroke(CARDCOLOR, BorderStrokeStyle.SOLID, new CornerRadii(CARD_WIDTH / 30), new BorderWidths(BORDER_WIDTH))));
    }



    public Book getBook(){
        return  this.book;
    }
    GridPane getCostAfterdiscount() {
        GridPane CostwithDiscount = new GridPane();
        CostwithDiscount.add(cost, 0, 0);
        CostwithDiscount.add(this.discont, 1, 0);
        return CostwithDiscount;
    }

}
