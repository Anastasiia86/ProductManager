package ru.netology.product;


public class Smartphone extends Product {
    protected String manufactured;

    public Smartphone(int id, String title, int price, String manufactured) {
        super(id, title, price);
        this.manufactured = manufactured;
    }

}
