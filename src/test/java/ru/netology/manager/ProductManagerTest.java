package ru.netology.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.netology.product.Book;
import ru.netology.product.Product;
import ru.netology.product.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.mockito.Mockito.doReturn;

public class ProductManagerTest {

    ProductManager managerEmpty = new ProductManager();
    ProductManager managerOneProduct = new ProductManager();
    ProductManager managerAllProduct = new ProductManager();

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
        managerOneProduct.add(bookOne);
        managerAllProduct.add(bookOne);
        managerAllProduct.add(bookTwo);
        managerAllProduct.add(bookThree);
        managerAllProduct.add(bookFour);
        managerAllProduct.add(bookFive);
        managerAllProduct.add(smartphoneOne);
        managerAllProduct.add(smartphoneTwo);
        managerAllProduct.add(smartphoneThree);
        managerAllProduct.add(smartphoneFour);
        managerAllProduct.add(smartphoneFive);
    }


    @Test
    public void addRepositoryEmpty() {
        Product[] expected = new Product[]{smartphoneOne};
        managerEmpty.add(smartphoneOne);
        Assertions.assertArrayEquals(expected, managerEmpty.findAll());
    }

    @Test
    public void addRepositoryWithOneProduct() {
        Product[] expected = new Product[]{
                bookOne,
                smartphoneOne};
        managerOneProduct.add(smartphoneOne);
        Assertions.assertArrayEquals(expected, managerOneProduct.findAll());
    }

    @Test
    public void addRepositoryWithTenProduct() {
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
        managerAllProduct.add(bookSix);
        Assertions.assertArrayEquals(expected, managerAllProduct.findAll());
    }

    @Test
    public void removeOneIdFromRepository() {
        int id = 1;
        Product[] expected = new Product[0];
        managerOneProduct.removeId(id);
        Assertions.assertArrayEquals(expected, managerOneProduct.findAll());
    }

    @Test
    public void removeIdFromRepositoryTenProducts() {
        int idBook = 3;
        int idSmartphone = 7;
        Product[] expected = new Product[]{
                bookOne,
                bookTwo,
                bookFour,
                bookFive,
                smartphoneOne,
                smartphoneThree,
                smartphoneFour,
                smartphoneFive};
        managerAllProduct.removeId(idBook);
        managerAllProduct.removeId(idSmartphone);
        Assertions.assertArrayEquals(expected, managerAllProduct.findAll());
    }


    @Test
    public void shouldMatchesTrue() {
        Assertions.assertTrue(managerEmpty.matches(smartphoneOne, "iPhone 14"));
    }

    @Test
    public void shouldMatchesFalse() {
        Assertions.assertFalse(managerEmpty.matches(smartphoneOne, "Apple"));
    }

    @Mock
    ProductRepository repository = Mockito.mock(ProductRepository.class);
    @InjectMocks
    ProductManager managerWithMock = new ProductManager(repository);

    @Test
    public void searchByTitleEmptyMock() {
        Product[] returnedEmpty = new Product[0];
        doReturn(returnedEmpty).when(repository).findAll();
        Product[] expected = new Product[0];
        Assertions.assertArrayEquals(expected, managerWithMock.searchByTitle(" "));
    }

    @Test
    public void searchByTitleMockOneProductFound() {
        Product[] returnedOneProduct = new Product[]{smartphoneFour};
        doReturn(returnedOneProduct).when(repository).findAll();
        Product[] expected = new Product[]{smartphoneFour};
        Assertions.assertArrayEquals(expected, managerWithMock.searchByTitle("S10+"));
    }

    @Test
    public void searchByTitleMockOneProductUnFound() {
        Product[] returnedOneProduct = new Product[]{smartphoneFour};
        doReturn(returnedOneProduct).when(repository).findAll();
        Product[] expected = new Product[0];
        Assertions.assertArrayEquals(expected, managerWithMock.searchByTitle("iPhone"));
    }

    @Test
    public void searchByTitleMockWithAllProductFoundOneResult() {
        Product[] returnedAllProduct = new Product[]{
                bookOne,
                bookTwo,
                bookThree,
                bookFour,
                bookFive,
                smartphoneOne,
                smartphoneTwo,
                smartphoneThree,
                smartphoneFour,
                smartphoneFive};
        doReturn(returnedAllProduct).when(repository).findAll();
        Product[] expected = new Product[]{bookThree};
        Assertions.assertArrayEquals(expected, managerWithMock.searchByTitle("наказание"));
    }


    @Test
    public void searchByTitleMockAllProductUnFound() {
        Product[] returnedAllProduct = new Product[]{
                bookOne,
                bookTwo,
                bookThree,
                bookFour,
                bookFive,
                smartphoneOne,
                smartphoneTwo,
                smartphoneThree,
                smartphoneFour,
                smartphoneFive};
        doReturn(returnedAllProduct).when(repository).findAll();
        Product[] expected = new Product[0];
        Assertions.assertArrayEquals(expected, managerWithMock.searchByTitle("митчелл"));
    }
}
