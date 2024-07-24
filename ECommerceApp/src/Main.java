import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        ProductFactory productFactory = new ProductFactory();

        Product product1 = productFactory.createProduct("Laptop", "Electronics", 3000, 50);
        Product product2 = productFactory.createProduct("Phone", "Electronics", 1500, 100);
        Product product3 = productFactory.createProduct("Book", "Education", 50, 200);

        Customer customer1 = new Customer("Cem", 28);
        Customer customer2 = new Customer("Ayşe", 32);

        Order order1 = new Order();
        order1.addProduct(product1);
        order1.addProduct(product2);

        Order order2 = new Order();
        order2.addProduct(product3);

        customer1.addOrder(order1);
        customer2.addOrder(order2);

        Invoice invoice1 = new Invoice(order1.getProducts().stream().mapToDouble(Product::getPrice).sum());
        Invoice invoice2 = new Invoice(order2.getProducts().stream().mapToDouble(Product::getPrice).sum());

        order1.setInvoice(invoice1);
        order2.setInvoice(invoice2);

        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);

        System.out.println("Total number of customers : " + customers.size());

        int cemProductCount = customers.stream()
                .filter(c -> c.getName().equals("Cem"))
                .flatMap(c -> c.getOrders().stream())
                .flatMap(o -> o.getProducts().stream())
                .mapToInt(p -> 1)
                .sum();
        System.out.println("Number of products bought by customers named Cem: " +  cemProductCount);

        double cemTotalAmount = customers.stream()
                .filter(c -> c.getName().equals("Cem") && c.getAge() > 25 && c.getAge() < 30)
                .flatMap(c -> c.getOrders().stream())
                .flatMap(o -> o.getProducts().stream())
                .mapToDouble(Product::getPrice)
                .sum();
        System.out.println("Total amount spent by customers named Cem aged between 25 and 30: " + cemTotalAmount);

        List<Invoice> invoicesOver1500 = customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .map(Order::getInvoice)
                .filter(invoice -> invoice.getTotalAmount() > 1500)
                .toList();

        System.out.println("Invoices over 1500 TL:");
        invoicesOver1500.forEach(invoice -> System.out.println("Invoice Amount: " + invoice.getTotalAmount()));
    }
}
