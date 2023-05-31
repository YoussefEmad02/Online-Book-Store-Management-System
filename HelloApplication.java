package com.example.bookstore;


import com.example.bookstore.customclasses.ColordLabel;
import com.example.bookstore.customclasses.FancyButton;
import com.example.bookstore.customclasses.LabledInput;
import com.example.bookstore.customclasses.ScrollVeiw;
import com.example.bookstore.database.DatabaseManager;
import com.example.bookstore.listitems.AdminStatWidget;
import com.example.bookstore.listitems.BookListItem;
import com.example.bookstore.listitems.CartListItem;
import com.example.bookstore.mainclasses.*;
import com.example.bookstore.utilities.ColorUtilities;
import com.example.bookstore.utilities.DataBaseManipulationType;
import com.example.bookstore.utilities.FontUlilities;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class HelloApplication extends Application {

    private static final double ITEM_MARGIN =5 ;
    //Commonly used Alerts
    static void ShowError(String address, String hint) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText(address);
        errorAlert.setContentText(hint);
        errorAlert.showAndWait();
    }

    static void ShowSuccess(String address, String hint) {
        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setHeaderText(address);
        errorAlert.setContentText(hint);
        errorAlert.showAndWait();
    }

    static void ShowWarning(String address, String hint) {
        Alert errorAlert = new Alert(Alert.AlertType.WARNING);
        errorAlert.setHeaderText(address);
        errorAlert.setContentText(hint);
        errorAlert.showAndWait();
    }

    static ArrayList<Book> bookslist = BooksManager.getBooks();
    static Reader reader;
    static Admin admin;
    public  final  static  String ICON="icon-vector-circle-book-11563204175udzuikgo9t.png";
    public  final  static  String USERSICON="USERLOGO.png";
    public  final  static  String BOOKICON="book.png";


    @Override
    public void start(Stage stage) throws IOException {

        Scene dynamicscene = new Scene(LoadLoginPane(), 370, 650);
        stage.setTitle("iBook");
        stage.getIcons().add(new Image(ICON));
        stage.setScene(dynamicscene);
        stage.show();

    }


    public static void main(String[] args) {

        launch(args);
    }


    // Login pane
    GridPane LoadLoginPane() {
        DatabaseManager.CreateTables();

        //Login Pane
        GridPane LoggingPane = new GridPane();
        LoggingPane.setVgap(10);
        ImageView logo = new ImageView(new Image(ICON));
        logo.setFitHeight(130);
        logo.setFitWidth(130);
        GridPane.setHalignment(logo, HPos.CENTER);
        GridPane.setMargin(logo, new Insets(10));

        ColordLabel welcome = new ColordLabel("Welcome To iBook!", FontUlilities.heading2bold, ColorUtilities.BABYBLUE);
        GridPane.setHalignment(welcome, HPos.CENTER);
        ColordLabel Signin = new ColordLabel("Sign in", FontUlilities.heading3bold, ColorUtilities.BABYBLUE);
        LabledInput Username = new LabledInput("Username", Color.GRAY, FontUlilities.heading4bold);
        LabledInput Password = new LabledInput("Password", Color.GRAY, FontUlilities.heading4bold, false, true);
        Hyperlink signinasadmin = new Hyperlink("Sign in as admin");
        GridPane.setMargin(Username, new Insets(0, 0, 0, 15));
        GridPane.setMargin(Password, new Insets(0, 0, 0, 15));
        Button signinbutton = new FancyButton("Sign in", FontUlilities.heading4);
        Hyperlink signupLabel = new Hyperlink("Don't Have an account? Signup");
        GridPane.setMargin(signinbutton, new Insets(15, 0, 0, 0));
        LoggingPane.add(logo, 0, 0);
        LoggingPane.add(welcome, 0, 1);
        LoggingPane.add(Signin, 0, 2);
        LoggingPane.add(Username, 0, 3);
        LoggingPane.add(Password, 0, 4);
        LoggingPane.add(signinbutton, 0, 5);
        LoggingPane.add(signupLabel, 0, 6);
        LoggingPane.add(signinasadmin, 0, 7);
        GridPane.setHalignment(signinasadmin, HPos.LEFT);

        Password.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    signinbutton.fire();
                }
            }
        });
        LoggingPane.setAlignment(Pos.CENTER);


        signinbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //(verifyfunc)
                String username = Username.getText();
                String password = Password.getText();
                reader = DatabaseManager.verifyUser(username, password);
                if (reader == null) {
                    ShowError("Invalid Username or Password", "Invalid Username or Password");
                } else {
                    reader.generateLibrary(reader.getLibrarystring());
                    Screen screen = Screen.getPrimary();
                    Rectangle2D bounds = screen.getVisualBounds();
                    signinbutton.getScene().getWindow().setX(bounds.getMinX());
                    signinbutton.getScene().getWindow().setY(bounds.getMinY());
                    signinbutton.getScene().getWindow().setWidth(bounds.getWidth());
                    signinbutton.getScene().getWindow().setHeight(bounds.getHeight());
                    signinbutton.getScene().setRoot(LoadMainPane(LoadHomeScreen(bookslist)));

                }

            }
        });

        signinasadmin.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                signinasadmin.getScene().setRoot(LoadAdminLoginPane());
            }
        });
        GridPane.setHalignment(signinbutton, HPos.RIGHT);
        GridPane.setHalignment(signupLabel, HPos.RIGHT);
        signupLabel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                signupLabel.getScene().setRoot(LoadSignupPane());
            }
        });

        return LoggingPane;
    }


    // Signup pane
    ScrollVeiw LoadSignupPane() {
        //Login Pane
        GridPane LoggingPane = new GridPane();
        LoggingPane.setVgap(10);
        ImageView logo = new ImageView(new Image(ICON));
        logo.setFitHeight(130);
        logo.setFitWidth(130);
        GridPane.setHalignment(logo, HPos.CENTER);
        GridPane.setMargin(logo, new Insets(10));

        ColordLabel welcome = new ColordLabel("Welcome To iBook!", FontUlilities.heading2bold, ColorUtilities.BABYBLUE);
        GridPane.setHalignment(welcome, HPos.CENTER);
        ColordLabel Signin = new ColordLabel("Sign up", FontUlilities.heading3bold, ColorUtilities.BABYBLUE);
        LabledInput Username = new LabledInput("Username", Color.GRAY, FontUlilities.heading4bold);
        LabledInput Email = new LabledInput("E-mail", Color.GRAY, FontUlilities.heading4bold);
        LabledInput Password = new LabledInput("Password", Color.GRAY, FontUlilities.heading4bold, false, true);
        LabledInput Address = new LabledInput("Address", Color.GRAY, FontUlilities.heading4bold);
        LabledInput PhoneNumber = new LabledInput("Phone Number", Color.GRAY, FontUlilities.heading4bold);
        GridPane.setMargin(Username, new Insets(0, 0, 0, 15));
        GridPane.setMargin(Email, new Insets(0, 0, 0, 15));
        GridPane.setMargin(Password, new Insets(0, 0, 0, 15));
        GridPane.setMargin(Address, new Insets(0, 0, 0, 15));
        GridPane.setMargin(PhoneNumber, new Insets(0, 0, 0, 15));
        Button signupbutton = new FancyButton("Sign up", FontUlilities.heading4);
        Hyperlink signinLabel = new Hyperlink("Already have an account? Signin");
        GridPane.setHalignment(signinLabel, HPos.RIGHT);

        GridPane.setMargin(signupbutton, new Insets(15, 0, 0, 0));
        LoggingPane.add(logo, 0, 0);
        LoggingPane.add(welcome, 0, 1);
        LoggingPane.add(Signin, 0, 2);
        LoggingPane.add(Username, 0, 3);
        LoggingPane.add(Email, 0, 4);
        LoggingPane.add(Password, 0, 5);
        LoggingPane.add(PhoneNumber, 0, 6);
        LoggingPane.add(Address, 0, 7);
        LoggingPane.add(signupbutton, 0, 8);
        LoggingPane.add(signinLabel, 0, 9);

        LoggingPane.setAlignment(Pos.CENTER);

        ScrollVeiw signupview = new ScrollVeiw(LoggingPane, 5);
        LoggingPane.setPadding(new Insets(30));
        signupbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //(verifyfunc)
                String username = Username.getText();
                String email = Email.getText();
                String password = Password.getText();
                String address = Address.getText();
                String phonenumber = PhoneNumber.getText();

                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || address.isEmpty() || phonenumber.isEmpty()) {
                    ShowError("Empty fields", "All fields should be filled");
                }
                if (!DatabaseManager.checkUser(username, email, phonenumber, DataBaseManipulationType.NEWUSER)) {
                    ShowError("Already Used", "User Name, Email or password already used");
                } else {
                    DatabaseManager.addUser(new Reader(DatabaseManager.getUsersCount(), username, email, password, address, phonenumber));
                    ShowSuccess("Congratulations", "User Added Successfully");
                    signupbutton.getScene().setRoot(LoadLoginPane());
                }
            }
        });

        signinLabel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                signinLabel.getScene().setRoot(LoadLoginPane());
            }
        });

        return signupview;
    }

    // Main pane = upper bar (Book screen ,cart screen, Home screen)+ whaterver tab
    VBox LoadMainPane(Pane Tab) {

        VBox MainContaier = new VBox(5);

        //upper Bar
        HBox upperbar = new HBox(10);

        upperbar.setStyle("-fx-background-color:" + ColorUtilities.getColorhex(ColorUtilities.BABYBLUE));
        Button UserButton = new FancyButton("User", ColorUtilities.BABYBLUE, Color.WHITE, FontUlilities.heading4bold, 5);
        Button CartButton = new FancyButton("Cart", ColorUtilities.BABYBLUE, Color.WHITE, FontUlilities.heading4bold, 5);
        Button LogoutButton = new FancyButton("Logout", ColorUtilities.BABYBLUE, Color.WHITE, FontUlilities.heading4bold, 5);
        Button HomeButton = new FancyButton("Home", ColorUtilities.BABYBLUE, Color.WHITE, FontUlilities.heading4bold, 5);

        upperbar.getChildren().addAll(HomeButton, UserButton, CartButton, LogoutButton);

        upperbar.setAlignment(Pos.TOP_RIGHT);

        MainContaier.getChildren().add(upperbar);
        MainContaier.getChildren().add(Tab);

        HomeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                HomeButton.getScene().setRoot(LoadMainPane(LoadHomeScreen(bookslist)));
            }
        });

        LogoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LogoutButton.getScene().getWindow().setHeight(600);
                LogoutButton.getScene().getWindow().setWidth(350);
                LogoutButton.getScene().getWindow().centerOnScreen();
                LogoutButton.getScene().setRoot(LoadLoginPane());
            }
        });
        CartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                HomeButton.getScene().setRoot(LoadMainPane(LoadCartScreen(reader.getCart())));
            }
        });

        UserButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                UserButton.getScene().setRoot(LoadMainPane(LoadUserScreen(reader)));
            }
        });
        if (Tab.getChildren().get(0) instanceof ScrollVeiw) {
            try {
                StackPane containsScroll = (StackPane) (MainContaier.getChildren().get(1));
                ScrollVeiw containsbookscene = (ScrollVeiw) (containsScroll.getChildren().get(0));
                BorderPane containsBorders = (BorderPane) (containsbookscene.getContent());
                VBox containsinfo = (VBox) (containsBorders.getCenter());
                Text description = (Text) (containsinfo.getChildren().get(1));
                description.wrappingWidthProperty().bind(MainContaier.widthProperty().divide(2.3));
            } catch (Exception ignored) {
            }

        }
        return MainContaier;
    }

    //Home Screen Loader
    BorderPane LoadHomeScreen(ArrayList<Book> books) {
        //Home Tab
        BorderPane homeTab = new BorderPane();
        //maincontaner with home

        final int menusize = 250;


        // getting top pane (Home TEXT) ready
        StackPane homePane = new StackPane();
        ColordLabel Homelabel = new ColordLabel("Home", FontUlilities.heading1bold, ColorUtilities.BABYBLUE);
        homePane.getChildren().add(Homelabel);
        StackPane.setAlignment(Homelabel, Pos.CENTER_LEFT);
        Homelabel.setPadding(new Insets(30, 0, 30, menusize));
        //setting it to Border pane Top
        homeTab.setTop(homePane);


        //getting menu pane ready
        VBox menuPane = new VBox(30);
        ColordLabel Menulabel = new ColordLabel("Menu", FontUlilities.heading1bold, ColorUtilities.BABYBLUE);
        LabledInput search = new LabledInput("Find A book", ColorUtilities.BABYBLUE, FontUlilities.heading3);
        menuPane.setPadding(new Insets(30));

        menuPane.getChildren().addAll(Menulabel,search);
        StackPane.setAlignment(Menulabel, Pos.TOP_CENTER);
        StackPane.setMargin(Menulabel, new Insets(30, (int) (menusize / 3), 30, 50));
        //setting menu pane to Border left
        homeTab.setLeft(menuPane);

        //getting center pane ready
        FlowPane f =new FlowPane();

        for(Book b:bookslist){
            if(!b.isDeleted()&&b.getCount()>0){
                BookListItem item=new BookListItem(b);
                f.getChildren().add(item);
                item.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        item.getScene().setRoot(LoadMainPane(LoadBookScreen(item.getBook())));
                    }
                });
                FlowPane.setMargin(item,new Insets(ITEM_MARGIN));
            }
        }

        ScrollVeiw HomeScrollPane = new ScrollVeiw(f, 3);
        f.setMinWidth(homeTab.getWidth() - menusize);
        HomeScrollPane.setFitToWidth(true);
        HomeScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        search.getTextField().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                ArrayList<Book> found=reader.find(search.getText(),bookslist);
                FlowPane d=new FlowPane();
                for(Book b:found){
                    if(!b.isDeleted()&&b.getCount()>0){
                        BookListItem item=new BookListItem(b);
                        d.getChildren().add(item);
                        item.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                item.getScene().setRoot(LoadMainPane(LoadBookScreen(item.getBook())));
                            }
                        });
                        FlowPane.setMargin(item,new Insets(ITEM_MARGIN));
                    }
                }
                BorderPane.setAlignment(HomeScrollPane, Pos.TOP_LEFT);
                HomeScrollPane.setContent(d);
            }
        });


        homeTab.setCenter(HomeScrollPane);
        BorderPane.setAlignment(f, Pos.TOP_LEFT);


        return homeTab;
    }

    //Book screen Loader
    StackPane LoadBookScreen(Book book) {


        //Book tab

        final int BOOKWIDTH = 300;
        BorderPane bookTabBorderPane = new BorderPane();
        ScrollVeiw bookTabScrollView = new ScrollVeiw(bookTabBorderPane, 2);
        StackPane ALL = new StackPane(bookTabScrollView);


        //Center pane,Book info
        VBox centerPane = new VBox(10);
        centerPane.setMaxWidth(500);
        ColordLabel bookName = new ColordLabel(book.getName(), FontUlilities.heading2bold, ColorUtilities.BABYBLUE);
        bookName.setFont(FontUlilities.heading1bold);
        Text bookDescription = new Text(book.getDescription());

        bookDescription.wrappingWidthProperty().bind(centerPane.widthProperty());


      /*  ColordLabel commentslabel = new ColordLabel("Reviews", FontUlilities.heading1bold, ColorUtilities.BABYBLUE);

        //Comments vbox->will be scrollview soon
        VBox Reviews = new VBox(10);
        ScrollVeiw reviewssScrollView = new ScrollVeiw(Reviews, 2);
        reviewssScrollView.setMaxHeight(300);
        reviewssScrollView.setFitToWidth(true);
        reviewssScrollView.setFitToHeight(true);
        Reviews.setPadding(new Insets(0, 0, 20, 20));
        for (int i = 0; i < 30; i++) {
            Reviews.getChildren().add(new Text("Very Good"));
        }
*/
        centerPane.getChildren().addAll(bookName, bookDescription/*, commentslabel, reviewssScrollView*/);
        VBox.setMargin(bookDescription, new Insets(0, 0, 20, 20));
        centerPane.setPadding(new Insets(10, 0, 20, 30));

        //Assigning info to PaneCenter
        bookTabBorderPane.setCenter(centerPane);


        //Leftpane pane,Book cover

        StackPane leftPane = new StackPane();
        leftPane.setPadding(new Insets(20, 20, 20, 10));
        ImageView bookcover = new ImageView();
        try {
            bookcover = new ImageView(new Image(book.getCoverurl()));
        } catch (Exception e) {
            System.out.println(book.getCoverurl());
        }

        bookcover.setFitWidth(BOOKWIDTH);
        bookcover.setFitHeight(BOOKWIDTH * 1.414);

        leftPane.getChildren().add(bookcover);
        leftPane.setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID,
                new CornerRadii(5), new BorderWidths(0, 5, 0, 0))));
        StackPane.setAlignment(bookcover, Pos.TOP_LEFT);

        bookTabBorderPane.setLeft(leftPane);
        leftPane.setPrefWidth(BOOKWIDTH + 20);
        BorderPane.setMargin(leftPane, new Insets(10, 0, 0, 20));

        //Right  pane,ِAdd to Cart Button :)+ cost and discount if exists

        VBox rightPane = new VBox(20);

        if (book.hasDiscount()) {
            GridPane CostwithDiscount = new GridPane();
            ColordLabel cost = new ColordLabel(String.valueOf(book.getCost() * (1 - book.getDiscount() / 100)), FontUlilities.heading3bold, ColorUtilities.BABYBLUE);
            ColordLabel discount = new ColordLabel(String.valueOf(book.getDiscount()) + " %off", FontUlilities.heading5bold, Color.RED.darker());
            CostwithDiscount.add(cost, 0, 0);
            CostwithDiscount.add(discount, 0, 1);
            GridPane.setValignment(discount, VPos.BOTTOM);
            cost.setFont(FontUlilities.heading3bold);
            discount.setFont(FontUlilities.heading4bold);
            rightPane.getChildren().add(CostwithDiscount);
        } else {
            ColordLabel cost = new ColordLabel(String.valueOf(book.getCost()), FontUlilities.heading3bold, ColorUtilities.BABYBLUE);
            cost.setFont(FontUlilities.heading2bold);
            rightPane.getChildren().add(cost);

        }
        Button addToCartbutton = new FancyButton("Add To Cart", ColorUtilities.BABYBLUE, Color.WHITE, FontUlilities.heading3bold, (int) (FontUlilities.heading3bold.getSize()));
        rightPane.setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID,
                new CornerRadii(5), new BorderWidths(0, 0, 0, 5))));
        rightPane.setPadding(new Insets(20, 20, 20, 20));
        ;
        rightPane.getChildren().add(addToCartbutton);
        StackPane.setAlignment(addToCartbutton, Pos.TOP_CENTER);

        //Assigning Pane to rightpane
        bookTabBorderPane.setRight(rightPane);
        BorderPane.setMargin(rightPane, new Insets(10, 20, 0, 0));


        addToCartbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (reader.getCart().contains(book)) {
                    ShowError("Alert", "This book is already in your cart");
                } else if (reader.owns(book)) {
                    ShowError("Alert", "You have this book already, check your library");
                } else {
                    reader.addToCart(book);
                    ShowSuccess("Alert","This Book is added to cart successfully");
                }
            }
        });


        //leftPane.setMaxHeight(leftPane.getHeight());

        //Bottom pane: add Comment

      /*  LabledInput BottomPane = new LabledInput("Add Comment", ColorUtilities.BABYBLUE, FontUlilities.heading1bold, true);
        StackPane stackPane = new StackPane(BottomPane);
        stackPane.setPadding(new Insets(30, 30, 30, 30));
        StackPane.setAlignment(BottomPane, Pos.TOP_LEFT);

        //Assigning addcoment pant to booktabbottom

        bookTabBorderPane.setBottom(stackPane);*/



     /*   //com.example.bookstore.Main Container Composition +scene
        MainContaier.getChildren().addAll(upperbar, bookTab);
        Scene BookTabScene = new Scene(MainContaier, 1280, 720);
*/
        return ALL;
    }

    //cart screen loader
    BorderPane LoadCartScreen(Cart cart) {


        //cart tab

        final int BOOKWIDTH = 300;
        BorderPane bookTabBorderPane = new BorderPane();


        //Leftpane pane,Book cover

        StackPane leftPane = new StackPane();
        ColordLabel c = new ColordLabel("iBook", FontUlilities.heading1, ColorUtilities.BABYBLUE);
        leftPane.getChildren().add(c);
        StackPane.setMargin(c, new Insets(0, 100, 0, 100));
        StackPane.setAlignment(c, Pos.TOP_CENTER);
        bookTabBorderPane.setLeft(leftPane);

        // top pane

        StackPane topPane = new StackPane();

        ColordLabel t = new ColordLabel("Cart", FontUlilities.heading1, ColorUtilities.BABYBLUE);
        topPane.getChildren().add(t);
        StackPane.setMargin(t, new Insets(30, 0, 30, 250));
        StackPane.setAlignment(t, Pos.TOP_LEFT);
        bookTabBorderPane.setTop(topPane);
        //Right  pane,ِAdd to Cart Button :)+ cost and discount if exists

        VBox rightPane = new VBox(20);
        BorderPane.setMargin(rightPane, new Insets(50));
        VBox.setMargin(rightPane, new Insets(100));
        ColordLabel price = new ColordLabel("Total price", FontUlilities.heading2bold, ColorUtilities.BABYBLUE);
        ColordLabel pricevalue = new ColordLabel(String.format("%.2f", cart.getTotalPrice()) + " EGP", FontUlilities.heading4bold, Color.DARKBLUE);
        Button payButton = new FancyButton("Pay", ColorUtilities.BABYBLUE, Color.WHITE, FontUlilities.heading3bold, (int) (FontUlilities.heading3bold.getSize()));
        rightPane.setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID,
                new CornerRadii(5), new BorderWidths(0, 0, 0, 5))));
        rightPane.setPadding(new Insets(20, 20, 20, 20));
        rightPane.getChildren().addAll(price, pricevalue, payButton);
        //Assigning Pane to rightpane
        bookTabBorderPane.setRight(rightPane);
        BorderPane.setMargin(rightPane, new Insets(10, 20, 0, 0));


        //Center pane,Book info

        VBox centerPane = new VBox(10);
        ScrollVeiw centerView = new ScrollVeiw(centerPane, 5);
        centerView.setPrefHeight(400);
        centerView.setPrefWidth(600);
        for (Book b : cart.getBooks()) {
            CartListItem item = new CartListItem(b);
            item.prefWidthProperty().bind(centerView.widthProperty().multiply(2));
            centerPane.getChildren().add(item);
            VBox.setMargin(item, new Insets(0, 0, 0, 10));
            item.getRemoveBookButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    reader.removeFromCart(b);
                    centerPane.getChildren().remove(item);
                    pricevalue.setText(String.format("%.2f", cart.getTotalPrice()));
                }
            });
        }

        payButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (reader.getCart().getBooks().size() == 0) {
                    ShowError("Error", "Your cart is Empty");
                    payButton.getScene().setRoot(LoadMainPane(LoadHomeScreen(bookslist)));
                } else {
                    try {
                        reader.pay();
                        DatabaseManager.updateUser(reader);
                        pricevalue.setText(String.format("%.2f", cart.getTotalPrice())+ " EGP");
                        ShowSuccess("Done!", "Paid successfully ");
                        for (Book b : cart.getBooks()) {
                            System.out.println(b.getCount());
                            b.decreaseCount();
                            System.out.println(b.getCount());
                            BooksManager.UpdateDatabase(b, DataBaseManipulationType.EDITEDBOOK);
                        }
                        cart.clear();
                        payButton.getScene().setRoot(LoadMainPane(LoadHomeScreen(bookslist)));
                    } catch (Exception e) {
                        ShowError("Error", e.getMessage());
                    }
                }
            }
        });
        centerView.setPadding(new Insets(10, 0, 20, 0));
        //Assigning info to PaneCenter
        bookTabBorderPane.setCenter(centerView);


        //leftPane.setMaxHeight(leftPane.getHeight());

        //Bottom pane: add Comment

      /*  LabledInput BottomPane = new LabledInput("Add Comment", ColorUtilities.BABYBLUE, FontUlilities.heading1bold, true);
        StackPane stackPane = new StackPane(BottomPane);
        stackPane.setPadding(new Insets(30, 30, 30, 30));
        StackPane.setAlignment(BottomPane, Pos.TOP_LEFT);

        //Assigning addcoment pant to booktabbottom

        bookTabBorderPane.setBottom(stackPane);*/



     /*   //com.example.bookstore.Main Container Composition +scene
        MainContaier.getChildren().addAll(upperbar, bookTab);
        Scene BookTabScene = new Scene(MainContaier, 1280, 720);
*/
        return bookTabBorderPane;
    }

    //user info screen loader
    BorderPane LoadUserScreen(Reader reader) {


        //user tab

        final int BOOKWIDTH = 300;
        BorderPane userTabBorderPane = new BorderPane();

        ColordLabel youraccount = new ColordLabel("Your Account", FontUlilities.heading1bold, ColorUtilities.BABYBLUE);
        userTabBorderPane.setTop(youraccount);
        youraccount.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                LoadMainPane(LoadUserScreen(reader));
            }
        });
        BorderPane.setMargin(userTabBorderPane.getTop(), new Insets(50));
        youraccount.setPadding(new Insets(0, 0, 0, 250));
        //Center pane,Book info
        GridPane userinfoPane = new GridPane();


        ColordLabel usernamelabel = new ColordLabel("Username: ", FontUlilities.heading3bold, ColorUtilities.BABYBLUE);
        ColordLabel Emaillabel = new ColordLabel("Email: ", FontUlilities.heading3bold, ColorUtilities.BABYBLUE);
        ColordLabel Phonelabel = new ColordLabel("Phone: ", FontUlilities.heading3bold, ColorUtilities.BABYBLUE);
        ColordLabel Addresslabel = new ColordLabel("Address: ", FontUlilities.heading3bold, ColorUtilities.BABYBLUE);
        ColordLabel ownedbookslabel = new ColordLabel("You have: ", FontUlilities.heading3bold, ColorUtilities.BABYBLUE);

        ColordLabel usernameValue = new ColordLabel(reader.getUserName(), FontUlilities.heading3, ColorUtilities.BABYBLUE);
        ColordLabel EmailValue = new ColordLabel(reader.getMail(), FontUlilities.heading3, ColorUtilities.BABYBLUE);
        ColordLabel PhoneValue = new ColordLabel(reader.getPhoneNumber(), FontUlilities.heading3, ColorUtilities.BABYBLUE);
        ColordLabel AddressValue = new ColordLabel(reader.getAddress(), FontUlilities.heading3, ColorUtilities.BABYBLUE);
        ColordLabel ownedbooksvalue = new ColordLabel(String.valueOf(reader.getLibrary().size()) + " Book/s" + "\t \t" + String.format("%.2f", reader.getMoney()) + " EGP", FontUlilities.heading3, ColorUtilities.BABYBLUE);

        userinfoPane.add(usernamelabel, 0, 0);
        userinfoPane.add(Emaillabel, 0, 1);
        userinfoPane.add(Phonelabel, 0, 2);
        userinfoPane.add(Addresslabel, 0, 3);
        userinfoPane.add(ownedbookslabel, 0, 4);
        userinfoPane.add(usernameValue, 1, 0);
        userinfoPane.add(EmailValue, 1, 1);
        userinfoPane.add(PhoneValue, 1, 2);
        userinfoPane.add(AddressValue, 1, 3);
        userinfoPane.add(ownedbooksvalue, 1, 4);

        usernamelabel.setClicklistener(usernameValue);
        Emaillabel.setClicklistener(EmailValue);
        Phonelabel.setClicklistener(PhoneValue);
        Addresslabel.setClicklistener(AddressValue);

        FancyButton edit = new FancyButton("Save", ColorUtilities.BABYBLUE, Color.WHITE, FontUlilities.heading3bold, (int) (FontUlilities.heading3bold.getSize()));
        userTabBorderPane.setRight(edit);
        BorderPane.setMargin(userTabBorderPane.getRight(), new Insets(100));
        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {


                if (DatabaseManager.checkUser(usernameValue.getText(), EmailValue.getText(), PhoneValue.getText(), DataBaseManipulationType.UPDATEUSER)) {
                    reader.setUserName(usernameValue.getText());
                    reader.setMail(EmailValue.getText());
                    reader.setPhoneNumber(PhoneValue.getText());
                    reader.setAddress(AddressValue.getText());
                    DatabaseManager.updateUser(reader);
                } else {
                    ShowError("Already Used", "User Name, Email, Or password already exists");
                }
            }
        });

        //Assigning info to PaneCenter
        userTabBorderPane.setCenter(userinfoPane);


        //Leftpane pane,Book cover
        VBox leftPane = new VBox(20);
        leftPane.setPadding(new Insets(20, 20, 20, 10));

        ColordLabel changePassword = new ColordLabel("Change Password", FontUlilities.heading2, ColorUtilities.BABYBLUE);
        ColordLabel library = new ColordLabel("Library", FontUlilities.heading2, ColorUtilities.BABYBLUE);
        ColordLabel note = new ColordLabel("Note: Click on Value's name to Edit it.", FontUlilities.heading5, Color.RED.darker());

        changePassword.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                changePassword.setIsunderlined(true);
            }
        });
        changePassword.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                changePassword.setIsunderlined(false);
            }
        });

        changePassword.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                VBox doubleinput = new VBox(30);
                doubleinput.setPadding(new Insets(30));

                Scene scene = new Scene(doubleinput, 400, 280);
                Stage stage = new Stage();
                stage.setTitle("Edit value");
                stage.getIcons().add(new Image(ICON));
                stage.setScene(scene);
                stage.show();
                LabledInput Password = new LabledInput("New Password", Color.GRAY, FontUlilities.heading4bold, false, true);
                LabledInput Password2 = new LabledInput("Re-enter Password", Color.GRAY, FontUlilities.heading4bold, false, true);
                Password2.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                            edit.fire();
                        }
                    }
                });
                FancyButton Edit = new FancyButton("Save", ColorUtilities.BABYBLUE, Color.WHITE, FontUlilities.heading3bold, (int) (FontUlilities.heading3bold.getSize()));
                doubleinput.getChildren().addAll(Password, Password2, Edit);

                Edit.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                        if (!(Password.getText().isEmpty() && Password2.getText().isEmpty())) {
                            if (Password.getText().equals(Password2.getText())) {
                                reader.setPassword(Password.getText());
                                ShowWarning("We're not done yet", "Press save to update password");
                                stage.close();
                            } else
                                ShowError("Invalid Input", "Passwords don't match");
                        } else
                            ShowError("Invalid Input", "Fill both Fielsd");
                    }

                });

                changePassword.setBorder(null);
                edit.setBorder(null);
            }
        });
        library.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                library.setIsunderlined(true);
            }
        });
        library.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                library.setIsunderlined(false);
            }
        });

        library.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                FlowPane f = new FlowPane(20, 20);
                f.setMinWidth(userTabBorderPane.getWidth() -300);

                ScrollVeiw HomeScrollPane = new ScrollVeiw(f, 3);

                int ex = 0;
                for (int i = 0; i < bookslist.size(); i++)
                    if (!bookslist.get(i).isDeleted())
                        ex++;

                HomeScrollPane.setFitToWidth(true);
                HomeScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                ColordLabel Empty = new ColordLabel("No Books To Show :(", FontUlilities.heading1, Color.LIGHTGRAY);
                if (ex == 0)
                    f.getChildren().add(Empty);
                else {

                    for (Book book : reader.getLibrary()) {
                        //System.out.println(b.isDeleted());

                            BookListItem listitem = new BookListItem(book,true);
                            listitem.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    getHostServices().showDocument(listitem.getBook().getDownloadurl());
                                }
                            });
                            f.getChildren().add(listitem);
                            FlowPane.setMargin(listitem,new Insets(ITEM_MARGIN));

                    }
                }
                userTabBorderPane.setCenter(HomeScrollPane);
                userTabBorderPane.getChildren().remove(userTabBorderPane.getRight());
                BorderPane.setAlignment(f, Pos.TOP_LEFT);

            }
        });

        leftPane.getChildren().

                addAll(library, changePassword, note);
        leftPane.setBorder(new
                Border(new BorderStroke(Color.DARKBLUE, BorderStrokeStyle.SOLID,
                new CornerRadii(5), new

                BorderWidths(0, 1, 0, 0))));

        userTabBorderPane.setLeft(leftPane);
        BorderPane.setMargin(leftPane, new

                Insets(20));


        return userTabBorderPane;
    }

    // generates booklist to show for admin, listeners included
    FlowPane generateAdminBooklist(ArrayList<Book>found){
        FlowPane bookslistview = new FlowPane(10, 10);
        ScrollVeiw HomeScrollPane = new ScrollVeiw(bookslistview, 3);
        HomeScrollPane.setFitToWidth(true);
        HomeScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        ColordLabel Empty = new ColordLabel("No Books To Show :(", FontUlilities.heading1, Color.LIGHTGRAY);
        if (found.size() == 0)
            bookslistview.getChildren().add(Empty);
        else {

            for (Book b:found) {
                if (!b.isDeleted()) {
                    BookListItem listItem = new BookListItem(b);

                    listItem.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            getbookinfo(listItem.getBook().getId());
                        }
                    });
                    bookslistview.getChildren().add(listItem);

                    FlowPane.setMargin(bookslistview,new Insets(ITEM_MARGIN));
                }
            }
        }
        return  bookslistview;
    }

    //admin login pane
    GridPane LoadAdminLoginPane() {
        GridPane LoggingPane = new GridPane();
        LoggingPane.setVgap(10);
        ImageView logo = new ImageView(new Image(ICON));
        logo.setFitHeight(130);
        logo.setFitWidth(130);
        GridPane.setHalignment(logo, HPos.CENTER);
        GridPane.setMargin(logo, new Insets(10));
        ColordLabel welcome = new ColordLabel("iBook Admin", FontUlilities.heading2bold, ColorUtilities.BABYBLUE);
        GridPane.setHalignment(welcome, HPos.CENTER);
        ColordLabel Signin = new ColordLabel("Sign in", FontUlilities.heading3bold, ColorUtilities.BABYBLUE);
        LabledInput id = new LabledInput("Id", Color.GRAY, FontUlilities.heading4bold);
        LabledInput password = new LabledInput("Password", Color.GRAY, FontUlilities.heading4bold, false, true);
        Hyperlink signinasuser = new Hyperlink("Sign in as user");
        GridPane.setMargin(id, new Insets(0, 0, 0, 15));
        GridPane.setMargin(password, new Insets(0, 0, 0, 15));
        Button signinbutton = new FancyButton("Sign in", FontUlilities.heading4);
        GridPane.setMargin(signinbutton, new Insets(15, 0, 0, 0));
        LoggingPane.add(logo, 0, 0);
        LoggingPane.add(welcome, 0, 1);
        LoggingPane.add(Signin, 0, 2);
        LoggingPane.add(id, 0, 3);
        LoggingPane.add(password, 0, 4);
        LoggingPane.add(signinbutton, 0, 5);
        LoggingPane.add(signinasuser, 0, 6);
        GridPane.setHalignment(signinasuser, HPos.LEFT);
        GridPane.setHalignment(signinbutton, HPos.RIGHT);
        LoggingPane.setAlignment(Pos.CENTER);

        password.getTextField().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    signinbutton.fire();
                }
            }
        });
        signinbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    if (id.getText().isEmpty() || password.getText().isEmpty()) {
                        throw new Exception("Empty Field");
                    }
                    if (DatabaseManager.verifyAdmin(id.getText(), password.getText())) {
                        Screen screen = Screen.getPrimary();

                        Rectangle2D bounds = screen.getVisualBounds();

                        signinbutton.getScene().getWindow().setX(bounds.getMinX());
                        signinbutton.getScene().getWindow().setY(bounds.getMinY());
                        signinbutton.getScene().getWindow().setWidth(bounds.getWidth());
                        signinbutton.getScene().getWindow().setHeight(bounds.getHeight());
                        admin= new Admin(id.getText(),password.getText());
                        signinbutton.getScene().setRoot(LoadAdminPane());
                    } else {
                        ShowError("Invalid inputs", "Invalid username or password, try again");
                    }

                } catch (Exception e) {
                    ShowError(e.getMessage(), "Enter id and password");
                }


            }
        });

        signinasuser.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                signinasuser.getScene().setRoot(LoadLoginPane());
            }
        });

        return LoggingPane;
    }

    // Admin pane
    BorderPane LoadAdminPane() {

        BorderPane adminPane = new BorderPane();
        //maincontaner with home

        final int menusize = 300;


        // getting top pane (Home TEXT) ready
        StackPane homePane = new StackPane();
        ColordLabel Homelabel = new ColordLabel("Dashboard", FontUlilities.heading1bold, ColorUtilities.BABYBLUE);
        homePane.getChildren().add(Homelabel);
        StackPane.setAlignment(Homelabel, Pos.CENTER_LEFT);
        Homelabel.setPadding(new Insets(30, 0, 30, menusize + 50));

        //setting it to Border pane Top
        adminPane.setTop(homePane);


        //getting menu pane ready
        VBox menuPane = new VBox(20);
        menuPane.setPadding(new Insets(30));
        ColordLabel Menulabel = new ColordLabel("Menu", FontUlilities.heading1bold, ColorUtilities.BABYBLUE);
        menuPane.getChildren().add(Menulabel);
        StackPane.setAlignment(Menulabel, Pos.TOP_CENTER);
        StackPane.setMargin(Menulabel, new Insets(30, (int) (menusize / 3), 30, 50));
        //setting menu pane to Border left
        LabledInput search = new LabledInput("Find A book", ColorUtilities.BABYBLUE, FontUlilities.heading3);
        FancyButton addBook = new FancyButton("Add Book", ColorUtilities.BABYBLUE, Color.WHITE, FontUlilities.heading3bold, (int) (FontUlilities.heading3bold.getSize()));
        FancyButton logout = new FancyButton("Logout", ColorUtilities.BABYBLUE, Color.WHITE, FontUlilities.heading3bold, (int) (FontUlilities.heading3bold.getSize()));


        addBook.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                addBookPane();
            }
        });
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                logout.getScene().setRoot(LoadLoginPane());
            }
        });
        addBook.setPrefWidth(300);
        logout.setPrefWidth(300);
        adminPane.setLeft(menuPane);

        menuPane.getChildren().addAll(search, addBook, logout);
        //getting center pane ready

        FlowPane f = new FlowPane(20, 20);
        f.setMinWidth(adminPane.getWidth() - menusize);


        AdminStatWidget users = new AdminStatWidget(USERSICON,
                "Users", DatabaseManager.getUsersCount());
        int ex = 0;
        for (int i = 0; i < bookslist.size(); i++) {
            if (!bookslist.get(i).isDeleted())
                ex++;
        }
        AdminStatWidget books = new AdminStatWidget(BOOKICON,
                "Books", ex);
        f.getChildren().addAll(users, books);
        adminPane.setCenter(f);
        BorderPane.setAlignment(f, Pos.TOP_LEFT);
        BorderPane.setMargin(f, new Insets(50));

     /*   //com.example.bookstore.Main Container Composition +scene
        MainContaier.getChildren().addAll(upperbar,homeTab);
        Scene HomeScene = new Scene(MainContaier,1280,720);
*/

        search.getTextField().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                ArrayList<Book> found=admin.find(search.getText(),bookslist);
                FlowPane d= generateAdminBooklist(found);
                adminPane.setCenter(d);
                BorderPane.setAlignment(d, Pos.TOP_LEFT);
            }
        });

        int finalEx = ex;
        books.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FlowPane bookslistview = new FlowPane(10, 10);
                ScrollVeiw HomeScrollPane = new ScrollVeiw(bookslistview, 3);
                HomeScrollPane.setFitToWidth(true);
                HomeScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                ColordLabel Empty = new ColordLabel("No Books To Show :(", FontUlilities.heading1, Color.LIGHTGRAY);
                if (finalEx == 0)
                    bookslistview.getChildren().add(Empty);
                else {
                    int size = bookslist.size();
                    for (int i = 0; i < size; i++) {
                        if (!bookslist.get(i).isDeleted()) {
                            BookListItem listItem = new BookListItem(bookslist.get(i));

                            listItem.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    getbookinfo(listItem.getBook().getId());

                                }
                            });
                            bookslistview.getChildren().add(listItem);
                            FlowPane.setMargin(bookslistview,new Insets(ITEM_MARGIN));

                        }
                    }
                }
                adminPane.setCenter(HomeScrollPane);
                BorderPane.setAlignment(HomeScrollPane, Pos.TOP_LEFT);
            }
        });
        Homelabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                adminPane.setCenter(f);
            }
        });
        return adminPane;

        /*   r.getInt("id"),
                        r.getString("name"),
                        r.getString("description"),
                        r.getInt("count"),
                        r.getDouble("cost"),
                        r.getString("coverurl"),
                        r.getString("downloadurl"),
                        r.getBoolean("hasdiscount"),
                        r.getInt("discount")));*/


    }
    // adding new book stage
    void addBookPane() {


        VBox addBookPane = new VBox(15);
        addBookPane.setPrefWidth(350);
        ScrollVeiw scrollVeiw = new ScrollVeiw(addBookPane, 5);
        scrollVeiw.setPadding(new Insets(50));
        LabledInput bookname = new LabledInput("Book name", Color.GRAY, FontUlilities.heading5);
        LabledInput bookDescription = new LabledInput("Book Description", Color.GRAY, FontUlilities.heading5, true);
        LabledInput bookCount = new LabledInput("Book Count", Color.GRAY, FontUlilities.heading5);
        LabledInput bookCost = new LabledInput("Book Cost", Color.GRAY, FontUlilities.heading5);
        LabledInput coverUri = new LabledInput("Cover Uri ( Double \\\\ )", Color.GRAY, FontUlilities.heading5, true);
        LabledInput downloadUri = new LabledInput("Download Uri ( Double \\\\ )", Color.GRAY, FontUlilities.heading5, true);
        LabledInput hasDiscount = new LabledInput("Has discount? (1 or 0)", Color.GRAY, FontUlilities.heading5);
        LabledInput discount = new LabledInput("Discount", Color.GRAY, FontUlilities.heading5);

        FancyButton add = new FancyButton("Add Book", FontUlilities.heading4);
        addBookPane.getChildren().addAll(bookname, bookDescription, bookCount, bookCost, coverUri, downloadUri
                , hasDiscount, discount, add);
        add.setAlignment(Pos.BOTTOM_RIGHT);

        Scene scene = new Scene(scrollVeiw, 500, 500);
        Stage addBookStage = new Stage();
        addBookStage.setTitle("Add Book");
        addBookStage.getIcons().add(new Image(ICON));
        addBookStage.setScene(scene);
        addBookStage.show();

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    String name = bookname.getText();
                    String desc = bookDescription.getText();
                    String count = bookCount.getText();
                    String cost = bookCost.getText();
                    String curi = coverUri.getText();
                    System.out.println(curi);
                    String duri = downloadUri.getText();
                    String hasdiscount = hasDiscount.getText();
                    String discont = discount.getText();

                    if (name.isEmpty() || desc.isEmpty() || count.isEmpty() || cost.isEmpty() ||
                            curi.isEmpty() || duri.isEmpty() || hasdiscount.isEmpty() || discont.isEmpty()) {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setHeaderText("Empty fields");
                        errorAlert.setContentText("Some of the fields are empty");
                        errorAlert.showAndWait();
                    } else {
                        boolean imguriverified = true;
                        boolean downloadurlverified = true;
                        try {
                            Image img = new Image(curi);
                        } catch (Exception r) {
                            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                            errorAlert.setHeaderText("invalid Cover URI");
                            errorAlert.setContentText("make sure you used ( \\\\ )");
                            errorAlert.showAndWait();
                            imguriverified = false;
                        }
                        if (imguriverified) {
                            boolean disscont;
                            if (Integer.parseInt(hasdiscount) == 1)
                                disscont = true;
                            else
                                disscont = false;

                            Book newbook=new Book(bookslist.size(), name, desc, Integer.parseInt(count)
                                    , Double.parseDouble(cost), curi, duri, disscont,
                                    Integer.parseInt(discont), false);
                            admin.addBook(bookslist,newbook);

                            addBookStage.close();
                        }
                    }
                } catch (Exception e) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setHeaderText("invalid input");
                    errorAlert.setContentText(e.getMessage());
                    errorAlert.showAndWait();
                }
            }
        });

        // return adminPane;
    }

    // book info/ editing book name.
    void getbookinfo(int id) {

        GridPane bookview = new GridPane();
        Book book = bookslist.get(id);
        ImageView cover = new ImageView();
        try {
            cover = new ImageView(new Image(bookslist.get(id).getCoverurl()));
        } catch (Exception e) {
            cover = new ImageView(new Image("D:\\Materials\\adv prog\\Images\\download.png"));
            System.out.println(e.getMessage());
        }
        cover.setFitWidth(300);
        cover.setFitHeight(300 * 1.4);

        cover.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                LabledInput input = new LabledInput("Enter new value (\\\\)", ColorUtilities.BABYBLUE, FontUlilities.heading2bold);
                StackPane inputpne = new StackPane(input);
                inputpne.setPadding(new Insets(30));
                Scene inputScene = new Scene(inputpne);
                Stage stage = new Stage();
                stage.setTitle("Edit value");
                stage.getIcons().add(new Image(ICON));
                stage.setScene(inputScene);
                stage.show();
                input.getTextField().setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                            if (!input.getTextField().getText().isEmpty()) {
                                book.setCoverurl(input.getTextField().getText());
                                stage.close();
                            }
                        }
                    }
                });
            }

        });
        ColordLabel bookName = new ColordLabel("Bookname: ", FontUlilities.heading4, ColorUtilities.BABYBLUE);
        ColordLabel bookNameValue = new ColordLabel(book.getName(), FontUlilities.heading4, ColorUtilities.BABYBLUE);
        ColordLabel bookCost = new ColordLabel("Cost: ", FontUlilities.heading4, ColorUtilities.BABYBLUE);
        ColordLabel bookCostValue = new ColordLabel(String.valueOf(book.getCost()), FontUlilities.heading4, ColorUtilities.BABYBLUE);
        ColordLabel hasdiscount = new ColordLabel("Has discount(1 or 0): ", FontUlilities.heading4, ColorUtilities.BABYBLUE);
        ColordLabel hasdiscountValue = new ColordLabel(String.valueOf(book.hasDiscount()), FontUlilities.heading4, ColorUtilities.BABYBLUE);
        ColordLabel discount = new ColordLabel("Discount: ", FontUlilities.heading4, ColorUtilities.BABYBLUE);
        ColordLabel discountValue = new ColordLabel(String.valueOf(book.getDiscount()), FontUlilities.heading4, ColorUtilities.BABYBLUE);
        ColordLabel count = new ColordLabel("Count: ", FontUlilities.heading4, ColorUtilities.BABYBLUE);
        ColordLabel countValue = new ColordLabel(String.valueOf(book.getCount()), FontUlilities.heading4, ColorUtilities.BABYBLUE);
        ColordLabel downloadUri = new ColordLabel("Download URL (\\\\ On Edit): ", FontUlilities.heading4, ColorUtilities.BABYBLUE);
        Hyperlink downloadUriValue = new Hyperlink(book.getDownloadurl());
        ColordLabel note = new ColordLabel("Note: Click on Value's name to Edit it.", FontUlilities.heading5, Color.RED.darker());
        downloadUriValue.setPrefWidth(300);
        ColordLabel discribtion = new ColordLabel("Discription: ", FontUlilities.heading4, ColorUtilities.BABYBLUE);
        ColordLabel descriptionValue = new ColordLabel(book.getDescription(), FontUlilities.heading5, Color.GREY);
        ScrollVeiw descriptionVeiw = new ScrollVeiw(new StackPane(descriptionValue), 3);
        descriptionVeiw.setPrefWidth(300);
        descriptionVeiw.setPrefHeight(180);

        bookName.setClicklistener(bookNameValue);
        discribtion.setClicklistener(descriptionValue);
        bookCost.setClicklistener(bookCostValue);
        hasdiscount.setClicklistener(hasdiscountValue);
        discount.setClicklistener(discountValue);
        count.setClicklistener(countValue);
        downloadUri.setClicklistener(downloadUriValue);

        downloadUriValue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                getHostServices().showDocument(downloadUriValue.getText());
            }
        });

        double cost = ((((1.0 - book.getDiscount() / 100) * book.getCost())));
        ColordLabel currentCost = new
                ColordLabel("(Current Cost: " + String.format("%.2f", cost) + "  EGP)",
                FontUlilities.heading4, ColorUtilities.BABYBLUE);
        bookview.add(cover, 0, 0);
        VBox data = new VBox(20);
        data.getChildren().addAll(bookName, discribtion, bookCost, count, hasdiscount, discount, downloadUri);

        if (book.hasDiscount()) {
            data.getChildren().add(currentCost);
        }
        data.getChildren().add(note);

        VBox values = new VBox(20);
        VBox.setMargin(discribtion, new Insets(0, 0, 150, 0));
        values.getChildren().addAll(bookNameValue, descriptionVeiw, bookCostValue, countValue, hasdiscountValue, discountValue, downloadUriValue);

        FancyButton edit = new FancyButton("Save", ColorUtilities.BABYBLUE, Color.WHITE, FontUlilities.heading3bold, (int) (FontUlilities.heading3bold.getSize()));
        FancyButton delete = new FancyButton("Delete", ColorUtilities.BABYBLUE, Color.WHITE, FontUlilities.heading3bold, (int) (FontUlilities.heading3bold.getSize()));
        edit.setPrefWidth(120);
        delete.setPrefWidth(120);

        VBox Edit = new VBox(10);
        Edit.getChildren().addAll(edit, delete);
        bookview.add(data, 1, 0);
        bookview.add(values, 2, 0);
        bookview.add(Edit, 3, 0);
        GridPane.setValignment(Edit, VPos.BOTTOM);
        Scene scene = new Scene(bookview);
        bookview.setHgap(15);
        bookview.setPadding(new Insets(30));
        Stage stage = new Stage();
        stage.setTitle("Book");
        stage.getIcons().add(new Image(ICON));
        stage.setScene(scene);
        stage.show();

        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (!bookName.getOutput().isEmpty()) {
                    try {
                        book.setName(bookName.getOutput());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                if (!discribtion.getOutput().isEmpty()) {
                    try {
                        book.setDescription(discribtion.getOutput());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                if (!bookCost.getOutput().isEmpty()) {
                    try {
                        double newCost = Double.parseDouble(bookCost.getOutput());
                        book.setCost(newCost);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                if (!hasdiscount.getOutput().isEmpty()) {
                    /* change in array list then*/
                    try {
                        int newHasDiscount = Integer.parseInt(hasdiscount.getOutput());
                        book.setHasDiscount(newHasDiscount != 0);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                if (!discount.getOutput().isEmpty()) {
                    /* change in array list then*/
                    try {
                        double newDiscount = Double.parseDouble(discount.getOutput());
                        book.setDiscount(newDiscount);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                if (!count.getOutput().isEmpty()) {
                    /* change in array list then*/
                    try {
                        Integer newCount = Integer.parseInt(count.getOutput());
                        book.setCount(newCount);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                if (!downloadUri.getOutput().isEmpty()) {
                    /* change in array list then*/
                    try {
                        book.setDownloadurl(downloadUri.getOutput());

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                admin.updateBook(book);
                /*update database*/
                stage.close();
            }
        });
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                admin.deleteBook(book);
                stage.close();
            }
        });
    }

}
