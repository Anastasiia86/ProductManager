package ru.netology.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.product.Book;
import ru.netology.product.Product;
import ru.netology.product.Smartphone;

public class ProductRepositoryTest {

    ProductRepository repository = new ProductRepository();
    Product bookOne = new Book(1, "Мастер и Маргарита", 2000, "Михаил Булгаков");
    Product bookTwo = new Book(2, "Собачье сердце", 1000, "Михаил Булгаков");
    Product bookThree = new Book(3, "Преступление и наказание", 2500, "Федор Достоевский");
    Product bookFour = new Book(4, "Приключения Шерлока Холмса", 500, "Артур Конан-Дойль");
    Product bookFive = new Book(5, "Унесённые ветром", 1500, "Маргарет Митчелл");
    Product smartphoneOne = new Smartphone(6, "iPhone 14", 200_000, "Apple");
    Product smartphoneTwo = new Smartphone(7, "iPhone 13 ", 150_000, "Apple");
    Product smartphoneThree = new Smartphone(8, "Galaxy M23", 15_000, "Samsung");
    Product smartphoneFour = new Smartphone(9, "Galaxy S10+", 130_000, "Samsung");
    Product smartphoneFive = new Smartphone(10, "Samsung Galaxy Z Flip3 ", 100_000, "Samsung");


    @BeforeEach
    public void setup() {
        repository.add(bookOne);
        repository.add(bookTwo);
        repository.add(bookThree);
        repository.add(bookFour);
        repository.add(bookFive);
        repository.add(smartphoneOne);
        repository.add(smartphoneTwo);
        repository.add(smartphoneThree);
        repository.add(smartphoneFour);
        repository.add(smartphoneFive);
    }

    @Test
    public void shouldFindById() {
        Product expected = bookFive;
        int id = 5;
        Assertions.assertEquals(expected, repository.findById(id));
    }

    @Test
    public void shouldFindByIdFail() {
        int id = 15;
        Assertions.assertNull(repository.findById(id));
    }


    @Test
    public void shouldRemoveId() {
        Product[] expected = new Product[]{
                bookOne,
                bookTwo,
                bookThree,
                bookFour,
                bookFive,
                smartphoneOne,
                smartphoneThree,
                smartphoneFour,
                smartphoneFive};
        int id = 7;
        repository.removeId(id);
        Assertions.assertArrayEquals(expected, repository.findAll());
    }


    @Test
    public void shouldAddProduct() {
        Product bookSix = new Book(11, "Робинзон Крузо", 1500, "Даниель Дефо");
        Product[] expected = new Product[]{
                bookOne,
                bookTwo,
                bookThree,
                bookFour,
                bookFive,
                smartphoneOne,
                smartphoneTwo,
                smartphoneThree,
                smartphoneFour,
                smartphoneFive,
                bookSix};
        repository.add(bookSix);
        Assertions.assertArrayEquals(expected, repository.findAll());
    }


}
