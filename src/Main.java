import managers.ProductManager;

public class Main {
    public static void main(String[] args) {
        ProductManager manager = new ProductManager();
        manager.addProducts();
        manager.sortProducts();
        manager.wantToAddToCart();
    }
}