module com.example.bookstore {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.bookstore;
    exports com.example.bookstore;
    exports com.example.bookstore.utilities;
    opens com.example.bookstore.utilities to javafx.fxml;
    exports com.example.bookstore.listitems;
    opens com.example.bookstore.listitems to javafx.fxml;
    exports com.example.bookstore.customclasses;
    opens com.example.bookstore.customclasses to javafx.fxml;
    exports com.example.bookstore.database;
    opens com.example.bookstore.database to javafx.fxml;

}