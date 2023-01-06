import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.product.product_manager.product_repo.ProductRepository;
import ru.netology.product.product_manager.product_item.Product;
import ru.netology.product.product_manager.service.ProductManager;

import java.util.Arrays;

public class ManagerAndRepoTests {

    ProductRepository repo = new ProductRepository();
    Product item = new Product();
    ProductManager manager = new ProductManager(repo);

    Product item1 = new Book(1, "Книга 1", 100, "Автор 1");
    Product item2 = new Book(2, "КНига 2", 200, "Автор 2");
    Product item3 = new Smartphone(3, "Смартфон 1", 1000, "Бренд 1");
    Product item4 = new Smartphone(4, "Смартфон 2", 2000, "Бренд 2");

    @Test
    public void repoShouldAddItem() {
        repo.add(item1);

        Product[] expected = {item1};
        Product[] actual = repo.getItems();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void repoShouldShowRepoContent() {
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);

        Product[] expected = {item1, item2, item3, item4};
        Product[] actual = repo.getItems();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void repoShouldDeleteById() {
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);
        repo.deleteById(4);

        Product[] expected = {item1, item2, item3};
        Product[] actual = repo.getItems();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test //здесь и ниже тесты на менеджер
    public void managerShouldAddToRepo() {
        manager.add(item1);
        manager.add(item2);
        manager.add(item3);
        manager.add(item4);

        Product[] expected = {item1, item2, item3, item4};
        Product[] actual = repo.getItems();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void managerShouldMatch() {

        boolean expected = true;
        boolean actual = manager.matches(item1, "Книга");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void managerShouldNotMatch() {
        boolean expected = false;
        boolean actual = manager.matches(item3, "Книга");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void managerShouldSearchById() {
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);

        Product[] expected = {item1};
        Product[] actual = manager.searchByText("Книга 1");

        Assertions.assertArrayEquals(expected, actual);
    }
}
