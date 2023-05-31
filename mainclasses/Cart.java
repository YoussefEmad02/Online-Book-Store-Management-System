package com.example.bookstore.mainclasses;

import java.util.ArrayList;

public class Cart {

    private ArrayList<Book> shoppingBooks=new ArrayList<>();

    public void addBook(Book b) {
        shoppingBooks.add(b);
    }

    public void removeBook(Book b) {
        shoppingBooks.remove(b);
    }
    public double getTotalPrice(){
    double total=0;
        for(Book b:shoppingBooks){
        total+=b.getFinalCost();
    }
        return total;
    }

    public ArrayList<Book> getBooks() {
        return shoppingBooks;
    }

    public boolean contains(Book book){
        for(Book b:shoppingBooks){
            if(book.equals(b)){
                return true;
            }
        }
        return false;
    }

    public void clear() {
        shoppingBooks.clear();
    }
}
