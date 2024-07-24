

public class ProductFactory {
    public Product createProduct(String name, String category, double price, int stock) {
        return new Product(name, category, price, stock);
    }
}
