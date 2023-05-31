package com.example.bookstore.mainclasses;

import java.util.ArrayList;

public class Reader {
    // Date fields
    int id;
    private String username;
    private String mail;
    private String password;
    private String address;
    private String phoneNumber;
    //  private (class that extends cart for add and search and so on ) userLibrary;
    private Cart cart;
    private CreditCard creditCard;
    private ArrayList<Book> library = new ArrayList<>();

    public Cart getCart() {
        return cart;
    }

    public String getLibrarystring() {
        return librarystring;
    }

    public void setLibrarystring(String librarystring) {
        this.librarystring = librarystring;
    }

    private String librarystring;

    // public parameterized constructor
    public Reader(int id, String username, String mail, String password, String address, String phoneNumber) {
        this.id=id;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.creditCard = new CreditCard();
        cart = new Cart();
    }

    public Reader(int id, String username, String mail, String password, String address, String phoneNumber, CreditCard creditCard) {
        this.id=id;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.creditCard = creditCard;
        cart = new Cart();
    }

    public Reader(int id, String username, String mail, String password, String address, String phoneNumber, CreditCard userCreditCard, String librarystring) {
        this.id=id;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.creditCard = userCreditCard;
        cart = new Cart();
        this.librarystring = librarystring;
    }

    public int getId() {
        return id;
    }



    // methods

    // setter and getter for username
    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    // setter and getter for mail
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    // setter and getter for address
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // setter and getter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // setter and getter of phone number
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getMoney() {
        return creditCard.getMoney();
    }


    public void addToCart(Book userBook) {
        cart.addBook(userBook);
    }
    public void removeFromCart(Book userBook) {
        cart.removeBook(userBook);
    }

    public ArrayList<Book> find(String bookname, ArrayList<Book> booklist) {
        return BooksManager.search(bookname,booklist);
    }

    public void generateLibrary(String libstr) {
        StringBuilder id = new StringBuilder();
        if(libstr!="null"){
            for (int i = 0; i < libstr.length(); i++) {
                if (libstr.charAt(i) != ',') {
                    id.append(libstr.charAt(i));
                } else {
                    library.add(BooksManager.getBookById(Integer.parseInt(id.toString())));
                    System.out.println(id);
                    id = new StringBuilder();
                }

            }
            if(!id.isEmpty()&&!libstr.equals("null")){
                System.out.println(id);
                library.add(BooksManager.getBookById(Integer.parseInt(id.toString())));
                System.out.println(id);
                id = new StringBuilder();
            }
        }
    }
    public boolean owns(Book book) {
        for (Book b : library) {
            if (b.equals(book)) {
                return true;
            }
        }
        return false;
    }
    public ArrayList<Book> getLibrary() {
        return this.library;
    }

    public void pay() throws Exception {
        try {
            creditCard.pay(cart.getTotalPrice());
            for (Book b : cart.getBooks()) {
                if (librarystring.equals("null")||librarystring.equals("")) {
                    librarystring="";
                    librarystring += b.getId();
                } else {
                    librarystring += ","+b.getId();
                }
                library.add(b);
            }

        }catch (Exception e){
           // e.printStackTrace();
            throw e;
        }
    }
}

