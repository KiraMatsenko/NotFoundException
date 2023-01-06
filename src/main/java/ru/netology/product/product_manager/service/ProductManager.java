package ru.netology.product.product_manager.service;

import ru.netology.product.product_manager.product_item.Product;
import ru.netology.product.product_manager.product_repo.ProductRepository;

public class ProductManager {

    private ProductRepository repo = new ProductRepository();
    private Product[] items = new Product[0];

    public ProductManager(ProductRepository repo) {
        this.repo = repo;
    }

    public void add(Product item) {
        repo.add(item);
    }

    public Product[] searchByText(String text) {
        Product[] result = new Product[0];
        for (Product item: repo.getItems()) {
            if (matches(item, text)) {
                Product[] tmp = new Product[result.length + 1];
                tmp[result.length] = item;
                result = tmp;
            }
        }
        return result;
    }

    public boolean matches(Product product, String search) {
        if (product.getName().contains(search)) {
            return true;
        } else {
            return false;
        }
    }
}
