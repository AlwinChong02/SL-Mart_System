package domain;

public class Manager extends Admin{
    
    public Manager() {
    }
    public Manager(String userID, String name, String password) {
        super(userID, name, password); //super to Admin
    }

    // Generate Inventory and Sales Report (manager only)
    public String generateInventoryAndSalesReport(User user, IDataStore dataLists){
        StringBuilder report = new StringBuilder("\nInventory Report:\n");
        for (Product product : dataLists.getProducts()) {
            report.append(String.format("Product ID: %s, Name: %s, Quantity: %d, Price:%.2f\n",
                    product.getProductId(), product.getName(), product.getQuantity(),
                    product.getPrice()));
        }
        // print total sales amount
        double totalSales = 0;
        for (Order order : dataLists.getOrders()) {
            for (Product product : order.getProducts()) {
                totalSales += product.getPrice() * product.getQuantity();
            }
        }
        report.append(String.format("\nSales Report\nTotalSales : %.2f " , totalSales));

        return report.toString();
    }
}
