package com.example.bookstore.mainclasses;

public class CreditCard {
    double money=1000;
    public CreditCard(){};
    public  CreditCard(double money){
        this.money=money;
    }
    double getMoney(){
        return money;
    }


    public void pay(double totalPrice) throws Exception{
        if (this.getMoney() < totalPrice) {
            throw new Exception("Insufficient funds");
        }else
        money-=totalPrice;
    }
}
