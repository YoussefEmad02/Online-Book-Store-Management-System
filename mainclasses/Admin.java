package com.example.bookstore.mainclasses;

import com.example.bookstore.utilities.DataBaseManipulationType;

import java.util.ArrayList;

public class Admin {
    private String id;
    private String password;

    private Admin(){}
    public Admin (String id,String password){
        this.id=id;
        this.password=password;
    }

    public void addBook(ArrayList<Book> bookslist, Book book){
        bookslist.add(book);
        BooksManager.UpdateDatabase(bookslist.get(bookslist.size() - 1), DataBaseManipulationType.NEWBOOK);
    }
    public void updateBook( Book book){
        BooksManager.UpdateDatabase(book, DataBaseManipulationType.EDITEDBOOK);
    }
    public void deleteBook(Book book) {
        book.setIsdeleted(true);
        BooksManager.UpdateDatabase(book, DataBaseManipulationType.DELETEDBOOK);
    }

    public ArrayList<Book> find(String bookname, ArrayList<Book> booklist) {
        return BooksManager.search(bookname,booklist);
    }

}
