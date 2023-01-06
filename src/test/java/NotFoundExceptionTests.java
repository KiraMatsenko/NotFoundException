import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.product.product_manager.exception.AlreadyExistsException;
import ru.netology.product.product_manager.exception.NotFoundException;
import ru.netology.product.product_manager.product_item.Product;
import ru.netology.product.product_manager.product_repo.ProductRepository;

public class NotFoundExceptionTests {

    ProductRepository repo = new ProductRepository();
    Product item = new Product();

    Product item1 = new Book(1, "Книга 1", 100, "Автор 1");
    Product item2 = new Book(2, "КНига 2", 200, "Автор 2");
    Product item3 = new Smartphone(3, "Смартфон 1", 1000, "Бренд 1");
    Product item4 = new Smartphone(4, "Смартфон 2", 2000, "Бренд 2");

    @Test
    public void shouldFindById() {
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);

        Product expected = item2;
        Product actual = repo.findById(2);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotFindById() {
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);

        Product expected = null;
        Product actual = repo.findById(5);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void deletionShouldNotThrowException() {
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);

        Product[] expected = {item1, item3, item4};
        Product[] actual = repo.deleteById(2);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void deletionShouldThrowException() {
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);

        Assertions.assertThrows(NotFoundException.class, () -> repo.deleteById(5));
    }

    @Test
    public void addShouldAddItem() {
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);

        Product[] expected = {item1, item2, item3, item4};
        Product[] actual = repo.getItems();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void addShouldThrowException() {
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);

        Assertions.assertThrows(AlreadyExistsException.class, () -> repo.add(item1));
    }
}
