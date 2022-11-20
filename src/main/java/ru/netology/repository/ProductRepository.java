package ru.netology.repository;


import lombok.Data;
import ru.netology.product.Product;

@Data
public class ProductRepository {
    private Product[] products = new Product[0];


    public void add(Product product) {
        Product[] tmp = new Product[products.length + 1];
        for (int i = 0; i < products.length; i++) {
            tmp[i] = products[i];
        }
        tmp[tmp.length - 1] = product;
        products = tmp;

    }

    public void removeId(int id) {
        Product[] tmp = new Product[products.length - 1];
        int indexTo = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[indexTo] = product;
                indexTo++;
            }
        }
        products = tmp;

    }


    public Product[] findAll() {
        return getProducts();

    }
}

