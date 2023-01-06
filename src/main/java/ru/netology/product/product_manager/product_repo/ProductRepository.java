package ru.netology.product.product_manager.product_repo;

import ru.netology.product.product_manager.exception.AlreadyExistsException;
import ru.netology.product.product_manager.exception.NotFoundException;
import ru.netology.product.product_manager.product_item.Product;

public class ProductRepository {

    private Product[] items = new Product[0];

    public void add(Product item) {
        if (findById(item.getId()) != null) {
            throw new AlreadyExistsException("Element with id: " + item.getId() + " already exists");
        }
        Product[] tmp = new Product[items.length + 1];
        for (int i = 0; i < items.length; i++) {
            tmp[i] = items[i];
        }
        tmp[tmp.length - 1] = item;
        items = tmp;
    }

    public Product findById(int id) {
        for (Product item : items) {
            if (id == item.getId()) {
                return item;
            }
        }
        return null;
    }

    public Product[] deleteById(int id) {
        if (findById(id) == null){
            throw new NotFoundException(
                    "Element with id: " + id + " not found");
        }
        Product[] tmp = new Product[items.length - 1];
        int copyToIndex = 0;
        for (Product item : items) {
            if (item.getId() != id) {
                tmp[copyToIndex] = item;
                copyToIndex++;
            }
        }
        items = tmp;
        return items;
    }

    public Product[] getItems() {
        return items;
    }
}
