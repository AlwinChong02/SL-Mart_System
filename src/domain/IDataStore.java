package domain;

import java.util.List;

public interface IDataStore {

    public void addUser(User user);

    public void addProduct(Product product);

    public void addOrder(Order order);

    public List<User> getUsers();

    public List<Product> getProducts();

    public List<Order> getOrders();

    public User findUserById(String userId);

    public Product findProductById(String productId);

    public Order findOrderById(String orderId);

    public void preloadTestData();

    
}
